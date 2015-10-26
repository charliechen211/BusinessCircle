package com.t.action;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoException;
import com.t.core.entities.Data;
import com.t.service.interfaces.IDataTranstate;
import com.t.service.interfaces.ITagService;
import com.t.utils.BaseAction;

//本类用于不同数据表中数据的转换，与项目无关
public class DataTransAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private Integer what;
	private Integer pageId; //pageId 从1开始
	private Integer pageSize;
	private IDataTranstate iDataTranstate;
	
	public String trans(){
		return SUCCESS;
	}
	//将xujiahui表中信息导入到Merchant表中，包括计算商铺的经纬度
	public String transMerchant(){
		iDataTranstate.trans();
		return SUCCESS;
	}

	//提取test/xujiahui 表中的数据，pageId,pageSize的标签数据插入Smart/Merchant表中
	@Autowired
	ITagService insertTagService;
	public String transTag(){
		List<Data> datas = new ArrayList<Data>();
		datas = iDataTranstate.getData(pageId,pageSize);
		Data data = new Data();
		Integer userId;
		Random random = new Random();
		for(int i=0; i< pageSize;i++) 
		{	
			//插入标签
			data = datas.get(i);	
			List<String> tags = new ArrayList<String>(); 
			if(data.getTag1()!= null)
			{
				String t1[] = data.getTag1().split(" ");
				for (String string : t1) {
					tags.add(string);
				}
			}
			if(data.getTag2()!= null){
				String t2[] = data.getTag2().split(" ");
				for (String string : t2) {
					tags.add(string);
				}
			}
			if(data.getTag3()!= null){
				String t3[] = data.getTag3().split(" ");
				for (String string : t3) {
					tags.add(string);
				}
			}
			userId = Math.abs(random.nextInt())%10;
			try {
				insertTagService.insertTag(userId, Integer.valueOf(i+1),Long.valueOf(1), tags);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return FAIL;
			}
		}
		return SUCCESS;
	}
	//将Merchant表中数据导入到mongodb中
	public String insertMongoDB(){
		try {
			iDataTranstate.insertMongoDB();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Integer getWhat() {
		return what;
	}
	public void setWhat(Integer what) {
		this.what = what;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
