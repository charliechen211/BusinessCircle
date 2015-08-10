package com.t.test;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.t.core.entities.Merchant;

public class MongoDBTest {

	public final static String HOST_ADDR = "localhost";
	public final static int PORT = 27017;
	public final static String COLLECTION_NAME = "merchant";
	public final static String DB_NAME = "test";
	public final static String COORDINATE_COL_NAME = "coordinate";
	public final static int R = 6371;//KM
	@SuppressWarnings("unchecked")
	public List<Merchant> getNearByMerchants(double longitude,double latitude){
		try {
			Mongo mongo = new Mongo(HOST_ADDR, PORT);
			DB db = mongo.getDB(DB_NAME);
			double[] coordinate = new double[2];
			coordinate[0]=longitude;
			coordinate[1]=latitude;
			DBObject cmd = new BasicDBObject();
			cmd.put("geoNear", COLLECTION_NAME);
			cmd.put("near", coordinate);
			cmd.put("maxDistance", 5.0/6371);
			cmd.put("spherical", true);
			cmd.put("distanceMultiplier", 6371);
			CommandResult result= db.command(cmd);
			List<BasicDBObject> resultList = (List<BasicDBObject>)result.get("results");
			for (int i = 0; i < resultList.size(); i++) {
				BasicDBObject bo = new BasicDBObject();
				BasicDBObject o = new BasicDBObject();
				bo = resultList.get(i);
				o = (BasicDBObject)bo.get("obj");
				Integer merchantId = (Integer)o.get("_id");
				String merchantName = (String)o.get("merchantName");
				System.out.println(bo);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		MongoDBTest test = new MongoDBTest();
		test.getNearByMerchants(121.439813, 31.194009);
	}

}
