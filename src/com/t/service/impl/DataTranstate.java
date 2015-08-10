package com.t.service.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.t.core.dao.DataDao;
import com.t.core.dao.ItemDao;
import com.t.core.dao.MerchantDao;
import com.t.core.entities.Data;
import com.t.core.entities.Item;
import com.t.core.entities.Merchant;
import com.t.service.interfaces.IDataTranstate;
import com.t.utils.Crawl;
import com.t.utils.Page;
/*
 * By liangyunlong
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataTranstate implements IDataTranstate {
	@Autowired
	MerchantDao merchantDao;
	@Autowired
	DataDao dataDao;
	@Autowired
	ItemDao itemDao;
	public List<Data> getData(Integer pageNo,Integer pageSize){
		List<Data> datas = new ArrayList<Data>();
		Page<Data> page = new Page<Data>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page = dataDao.findByProperty(page, "circle", "徐汇区");
		datas = page.getResult();
		return datas;
	}
	//将Merchant表中数据插入到MongoDB中
	public void insertMongoDB()throws UnknownHostException,MongoException{
		 Mongo mg = new Mongo();
		 DB db = mg.getDB("test");
		 DBCollection merchants = db.getCollection("merchant");
		
		 List<DBObject> mos = new ArrayList<DBObject>();
		 List<Merchant> list = merchantDao.findByProperty("circle", 1);
		 int size = list.size();
		 Merchant m = new Merchant();
		 for(int i=0;i < size;i++){
			 m = list.get(i);
			 DBObject mo = new BasicDBObject();
			 mo.put("_id", m.getMerchantId());
			 //mo.put("type", m.getType());
			 mo.put("merchantName", m.getMerchantName());
			 //mo.put("branch", m.getBranch());
			 //mo.put("description", m.getDescription());
			 //mo.put("telNumber", m.getTelNumber());
			 mo.put("address", m.getAddress());
			 mo.put("averageConsume",m.getAverageConsume());
			 mo.put("averageValue", m.getAverageValue());
			 //mo.put("picture", m.getPicture());
			 //mo.put("circle", m.getCircle());
			 BasicDBObject temp = new BasicDBObject();
			 temp.put("longitude", m.getLongitude());
			 temp.put("latitude", m.getLatitude());	
			 mo.put("coordinate",temp);	
			 mos.add(mo);
		 }
		 merchants.insert(mos);
	}  
	//将xujiahui表中前300条数据的信息导入Merchant表中，计算商铺的经纬度
	public Integer trans() {
		List<Data> datas = new ArrayList<Data>();
		Page<Data> page = new Page<Data>();
		page.setPageNo(2);
		page.setPageSize(500);
		page = dataDao.findByProperty(page, "circle", "徐汇区");
		datas = page.getResult();
		int size = datas.size();
		String phone;
		Data data = new Data();
		for(int i=0; i< size;i++)
		{
			//插入商家
			
			//解析商家的基本信息
			Merchant m = new Merchant();
			data = datas.get(i);
			m.setMerchantId(data.getDataId());
			m.setMerchantName(data.getMerchantName());		
			m.setBranch(data.getBranchName());			
			m.setDescription(data.getCommentDetail());
			if(data.getPhoneNumber() !=null)
			{
			if(data.getPhoneNumber().startsWith("电话"))
				phone = data.getPhoneNumber().substring(3);
			else {
				phone = data.getPhoneNumber();
			}
			m.setTelNumber(phone);
			}
			m.setAddress(data.getAddress());
			if(data.getSpending()!=null)
				m.setAverageConsume(Double.valueOf(String.valueOf( data.getSpending())));
			if(data.getRate()!=null)
				m.setAverageValue(Double.valueOf(String.valueOf(data.getRate() / 10)));
			m.setType(1);
			m.setCircle(1);
			
			//计算商铺的经纬度
			String shop = data.getUrl();
			int index = shop.lastIndexOf("/");
			int shopId =Integer.valueOf(shop.substring(index+1));
			String location = "";
			try {
				location = Crawl.crawlpage(shopId);
				String loc[] = location.split(",");
				m.setLatitude(Double.valueOf(loc[0]));  //纬度  
				m.setLongitude(Double.valueOf(loc[1]));   //经度
			}catch(NumberFormatException e){
				e.printStackTrace();
				return 1;
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			int merchantId = merchantDao.save(m);
		   //插入商品
			if(data.getItems() !=null){
				String items[] = data.getItems().split(" ");
				int length = items.length;
				for(int j=0;j < length;j++)
				{
					Item e = new Item();
					e.setMerchant(merchantId);
					e.setItemName(items[j]);
					if(data.getSpending()!=null)
						e.setPrice(Double.valueOf(String.valueOf( data.getSpending())));
					if(data.getRate()!=null)
						e.setRate(Double.valueOf(String.valueOf(data.getRate() / 10)));
					e.setType(6);
					itemDao.save(e);
				}
			}
		}
		return datas.size();
	}
}
