package com.t.utils;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class BaseHttpClient {

    private static final Logger logger = Logger.getLogger(BaseHttpClient.class.getName());
    private String url;
    
    public BaseHttpClient(String url) {
    	this.url = url;
    }
    
    public JSONObject post(JSONObject request) {
		HttpClient httpClient = new HttpClient();
		System.out.println(url);
		System.out.println(request.toString());
    	PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("request", request.toString());
    	
        JSONObject response = null;
        try {
            httpClient.executeMethod(postMethod);
            System.out.println(postMethod.getStatusCode());
            System.out.println(postMethod.getResponseBodyAsString());
            response = new JSONObject(postMethod.getResponseBodyAsString());
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            logger.error(e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }
        return response;
    }

}
