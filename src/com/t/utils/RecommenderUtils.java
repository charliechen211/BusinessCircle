package com.t.utils;

import java.io.IOException;
import java.util.Properties;

public class RecommenderUtils {
	
	public static void main(String[] args) {
		System.out.println(recommenderUtils.getRecommenderUrl());
	}
	
	private static RecommenderUtils recommenderUtils = new RecommenderUtils();
	
	private static String recommenderUrl;

	private static String predictMethod;
	private static String insertUserTagMethod;
	private static String insertItemTagMethod;
	private static String collectItemMethod;
	private static String delCollectItemMethod;

	private RecommenderUtils() {
		Properties prop = new Properties();
        try {
            prop.load(RecommenderUtils.class.getClassLoader().getResourceAsStream("recommender-config.properties"));

            recommenderUrl = prop.getProperty("recommender.url");
            predictMethod = prop.getProperty("recommender.predict");
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

	public static void setRecommenderUrl(String recommenderUrl) {
		RecommenderUtils.recommenderUrl = recommenderUrl;
	}

	public static String getPredictMethod() {
		return predictMethod;
	}

	public static void setPredictMethod(String predictMethod) {
		RecommenderUtils.predictMethod = predictMethod;
	}

	public static String getInsertUserTagMethod() {
		return insertUserTagMethod;
	}

	public static void setInsertUserTagMethod(String insertUserTagMethod) {
		RecommenderUtils.insertUserTagMethod = insertUserTagMethod;
	}

	public static String getInsertItemTagMethod() {
		return insertItemTagMethod;
	}

	public static void setInsertItemTagMethod(String insertItemTagMethod) {
		RecommenderUtils.insertItemTagMethod = insertItemTagMethod;
	}

	public static String getCollectItemMethod() {
		return collectItemMethod;
	}

	public static void setCollectItemMethod(String collectItemMethod) {
		RecommenderUtils.collectItemMethod = collectItemMethod;
	}

	public static String getDelCollectItemMethod() {
		return delCollectItemMethod;
	}

	public static void setDelCollectItemMethod(String delCollectItemMethod) {
		RecommenderUtils.delCollectItemMethod = delCollectItemMethod;
	}
	
}
