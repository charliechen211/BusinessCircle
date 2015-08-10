package com.t.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Crawl {
	public static String getWebContent(String urlString, final String charset,
			int timeout) throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		urlString = (urlString.startsWith("http://") || urlString
				.startsWith("https://")) ? urlString : ("http://" + urlString)
				.intern();
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		conn.setRequestProperty("Accept", "text/html");
		conn.setConnectTimeout(timeout);
		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println(conn.getResponseCode());
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		InputStream input = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input,
				charset));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		if (reader != null) {
			reader.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
		return sb.toString();
	}

	public static String getWebContent(String urlString) throws IOException {
		return getWebContent(urlString, "iso-8859-1", 5000);
	}
	public static String crawlpage(int shopid) throws IOException{
		String s ="";
		String location = "";
		try{
			s = Crawl.getWebContent("http://m.dianping.com/shop/"+shopid+"/map");
			int indexstart = s.indexOf("href=\"http://mo.amap.com/?to=")+29;
			int indexend = s.indexOf("(",indexstart);
			location = s.substring(indexstart,indexend);
		}catch(NullPointerException e){
			e.printStackTrace();
			location = "0,0";
		}
	
		return location;
	}	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String location = crawlpage(6452583);
		String[] loc = location.split(",");
		double longtitude = Double.parseDouble(loc[1]);
		double latitude = Double.parseDouble(loc[0]);
		System.out.println("-----------------经纬度");
		System.out.println(longtitude + " "+ latitude);
//		String baidu = Crawl.getWebContent("http://220.181.112.233/geocoder/v2/?ak=61f8bd72d68aef3a7b66537761d29d82&callback=renderReverse&location="+latitude+","+longtitude+"&output=json&pois=0");
//		baidu = new String(baidu.getBytes("iso-8859-1"), "utf-8");
//		baidu = baidu.substring(baidu.indexOf("formatted_address")+20);
//		System.out.println("-----------------百度地图结果");
	}

}
