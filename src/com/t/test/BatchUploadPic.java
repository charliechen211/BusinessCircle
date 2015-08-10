package com.t.test;
/*批量上传图片，添加测试数据*/
import java.io.File;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.core.dao.ItemDao;
import com.t.core.dao.MerchantDao;
import com.t.core.entities.Item;
import com.t.core.entities.Merchant;
import com.t.service.interfaces.IItemService;
import com.t.service.interfaces.IMerchantService;
import com.t.utils.BaseAction;
import com.t.utils.BaseDao;

public class BatchUploadPic extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemServe;
	@Autowired
	private IMerchantService mservice;
    private MerchantDao mdao = new MerchantDao();
    private ItemDao idao = new ItemDao();
	
	public static void main(String[] args) {
		try {
			BatchUploadPic a = new BatchUploadPic();
			//a.uploadshops();
			a.uploaditems();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public  void uploadshops() throws Exception {
		File dir = new File("商家");
		File[] files = dir.listFiles();
		Merchant shop = new Merchant();
		for (File file : files) {
			String Id = file.getName().substring(0,file.getName().length()-4);
			
			//shop = mdao.findUniqueByProperty("merchantId",Id);
			String newname = getExtention(file.getName());
			System.out.println(Id+" : "+newname);
			//shop.setPicture(newname);
			//mdao.saveOrUpdate(shop);
			File file2 = new File(dir,newname);
			boolean success = file.renameTo(file2);
			Thread.sleep(100);
			
		}
	}

	public void uploaditems() throws Exception {
		File dir = new File("商品");
		File[] files = dir.listFiles();
		Item it = new Item();
		for (File file : files) {
			String Id = file.getName().substring(0,file.getName().length()-4);
			 
			//it = itemServe.findItemById(Id);
			String newname = getExtention(file.getName());
			//it.setPicture(newname);
			//itemServe.updateItem(it);
			System.out.println(Id+" : "+newname);
			File file2 = new File(dir,newname);
			boolean success = file.renameTo(file2);
			Thread.sleep(100);
		}
	}

	// 转换图片名称
	public String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return new Date().getTime() + fileName.substring(pos);
	}

}
