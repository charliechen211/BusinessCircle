package com.t.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.t.core.entities.Merchant;

public class MongoClient {  
    private final static String HOST_ADDR = "localhost";
    private final static int PORT = 27017;
    private static Mongo mongo;
    private static DB db;
    private static DBCollection users;
    static{
    	try{
    		mongo = new Mongo(HOST_ADDR,PORT);
    		db = mongo.getDB("test");  
    		users = db.getCollection("merchant");
    		System.out.print("*************************** MongoDB SUCCESSFULLY");
    	}catch(Exception e){
    		System.out.print("*************************** MongoDB FAILED");
    		e.printStackTrace();
    	}
    }
    //将merchant表插入到mongodb中
    public static void insertMongodb(List<Merchant> merchants){
    	List<DBObject> mus = new ArrayList<DBObject>();
   // 	List<Merchant> merchants = new ArrayList<Merchant>();   //对user赋值
    	for (Merchant m : merchants) {
    		DBObject mu = new BasicDBObject();
    		BasicDBObject cor = new BasicDBObject();
    		mu.put("_id", m.getMerchantId());
    		mu.put("merchantName", m.getMerchantName());
    		mu.put("averageConsume", m.getAverageConsume());
    		mu.put("averageValue", m.getAverageValue());
    		mu.put("address", m.getAddress());
    		cor.put("latitude", m.getLatitude());
    		cor.put("longitude", m.getLongitude());
    		mu.put("coordinate", cor);
    		users.save(mu);
		}
    }
    //从user表统计生成user_item表
    public void createUserItem() {  
    	String map = "function() {"+
	    "var ev = {entityId:0,value:0};"+
	    "ev.entityId = this.entityId; ev.value=this.value;"+
       "emit(this.userId, ev);}";
    	
    	String reduce =" function(userId,ev){"+
    	"return {userId:userId,entity_value:ev};}";
    	DBObject query = new BasicDBObject("eType",new BasicDBObject("$eq",2));  //eType=2商品
    	DBObject command = new BasicDBObject();
    	command.put("mapreduce","user");
    	command.put("query",query);
    	command.put("map", map);
    	command.put("reduce", reduce);
    	command.put("out", "user_item");
    	MapReduceOutput mro = users.mapReduce(command);
    	for (DBObject o : mro.results()) {  
    		System.out.println(o.toString());  
    	}
  } 
    //从user表统计生成user_merchant表
    public void createUserMerchant() {  
    	String map = "function() {"+
	    "var ev = {entityId:0,value:0};"+
	    "ev.entityId = this.entityId; ev.value=this.value;"+
       "emit(this.userId, ev);}";
    	
    	String reduce =" function(userId,ev){"+
    	"return {userId:userId,entity_value:ev};}";
    	DBObject query = new BasicDBObject("eType",new BasicDBObject("$eq",1));   //eType=1 商家
    	DBObject command = new BasicDBObject();
    	command.put("mapreduce","user");
    	command.put("query",query);
    	command.put("map", map);
    	command.put("reduce", reduce);
    	command.put("out", "user_merchant");
    	MapReduceOutput mro = users.mapReduce(command);
    	for (DBObject o : mro.results()) {  
    		System.out.println(o.toString());  
    	}
  } 
    //从user表统计生成item_user表
    public void createItemUser() {  
    	String map = "function() {"+
	    "var uv = {userId:0,value:0};"+
	    "uv.userId = this.userId; uv.value=this.value;"+
       "emit(this.entityId, uv);}";
    	
    	String reduce =" function(entityId,uv){"+
    	"return {entityId:entityId,user_value:uv};}";
    	DBObject query = new BasicDBObject("eType",new BasicDBObject("$eq",2));   //eType=2 商品
    	DBObject command = new BasicDBObject();
    	command.put("mapreduce","user");
    	command.put("query",query);
    	command.put("map", map);
    	command.put("reduce", reduce);
    	command.put("out", "item_user");
    	MapReduceOutput mro = users.mapReduce(command);
    	for (DBObject o : mro.results()) {  
    		System.out.println(o.toString());  
    	}
  }
    //从user表统计生成merchant_user表
    public void createMerchantUser() {  
    	String map = "function() {"+
	    "var uv = {userId:0,value:0};"+
	    "uv.userId = this.userId; uv.value=this.value;"+
       "emit(this.entityId, uv);}";
    	
    	String reduce =" function(entityId,uv){"+
    	"return {entityId:entityId,user_value:uv};}";
    	DBObject query = new BasicDBObject("eType",new BasicDBObject("$eq",1));   //eType=2 商品
    	DBObject command = new BasicDBObject();
    	command.put("mapreduce","user");
    	command.put("query",query);
    	command.put("map", map);
    	command.put("reduce", reduce);
    	command.put("out", "merchant_user");
    	MapReduceOutput mro = users.mapReduce(command);
    	for (DBObject o : mro.results()) {  
    		System.out.println(o.toString());  
    	}
  }
   	public static void main(String args[]){
   			//MongoClient mongoClient = new MongoClient();
   		String fileName =String.valueOf(new Date().getTime());
   		System.out.println(fileName);
   			
    }
}  
