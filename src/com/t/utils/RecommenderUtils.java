package com.t.utils;

import java.io.IOException;
import java.util.Properties;

public class RecommenderUtils {
	
	public static void main(String[] args) {
		System.out.println(recommenderUtils.getRecommenderUrl());
	}
	
	private static RecommenderUtils recommenderUtils = new RecommenderUtils();
	
	private static String recommenderUrl;

	private static String recPredictMethod;
	private static String hotPredictMethod;
	private static String cfPredictMethod;
	private static String insertUserTagMethod;
	private static String insertItemTagMethod;
	private static String collectItemMethod;
	private static String delCollectItemMethod;

	private RecommenderUtils() {
		Properties prop = new Properties();
        try {
            prop.load(RecommenderUtils.class.getClassLoader().getResourceAsStream("recommender-config.properties"));

            recommenderUrl = prop.getProperty("recommender.url");
            recPredictMethod = prop.getProperty("recommender.recPredict");
            hotPredictMethod = prop.getProperty("recommender.hotPredict");
            cfPredictMethod = prop.getProperty("recommender.cfPredict");
            insertUserTagMethod = prop.getProperty("recommender.insertUserTag");
            insertItemTagMethod = prop.getProperty("recommender.insertItemTag");
            collectItemMethod = prop.getProperty("recommender.collectItem");
            delCollectItemMethod = prop.getProperty("recommender.delCollectItem");
            
        } catch(IOException e) {
            e.printStackTrace();
        }
	}

	public static String getRecommenderUrl() {
		return recommenderUrl;
	}

	public static String getRecPredictMethod() {
		return recPredictMethod;
	}	

	public static String getHotPredictMethod() {
		return hotPredictMethod;
	}
	
	public static String getCfPredictMethod() {
		return cfPredictMethod;
	}
	
	public static String getInsertUserTagMethod() {
		return insertUserTagMethod;
	}

	public static String getInsertItemTagMethod() {
		return insertItemTagMethod;
	}

	public static String getCollectItemMethod() {
		return collectItemMethod;
	}

	public static String getDelCollectItemMethod() {
		return delCollectItemMethod;
	}
	
}
