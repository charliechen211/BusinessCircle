package com.t.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final String SESSION_USER = "user";
	
	public static final String JSON_USER = "user";
	public static final String JSON_ERRORS = "errors";
	public static final String JSON_STATE = "state";
	public static final String JSON_STATE_SUCCESS = "success";
	public static final String JSON_STATE_ERROR = "error";
	public static final String SESSION_MyShopIds = "shopids";
	public static final String SESSION_mymerchantlist = "mymerchantlist";
	public static final String SESSION_tableTypes = "tableTypes";
	
	//商铺和商品的审核通过与未通过
	public static final String BC_PASS = "1";  //过
	public static final String BC_REFUSE = "-1";  //未过
	public static final String BC_TOBE_CHECKED = "0";  //默认值---待审核
	
	public static final Integer DEFAUT_PAGE_SIZE = 20;
	
	public static final Integer EXIST_YES = 1;
	public static final Integer EXIST_NO = 0;
	
	// tag types
	public static final Integer TAG_TYPE_MERCHANT = 1;
	public static final Integer TAG_TYPE_USER = 0;
	public static final Integer TAG_TYPE_ITEM = 2;
    //service 返回到action的状态
	public static final Integer ERROR = -1;
	//购物车、订单的状态
	public static final Integer CHECKED = 1;
	public static final Integer UNCHECKED = 0;
	
	//二手 鹊桥 兼职模块回复进同一张表，type区分不同的来源
	public static final Integer SecondHand = 1; //二手
	public static final Integer LoveBridge = 2;  //鹊桥
	public static final Integer PartTime = 3;  //兼职
	public static final Integer Campaign= 4;  //校园活动
	
	//用户订阅或者取消订阅
	public static final Integer Follow = 1; //订阅
	public static final Integer DelFollow = 2; //取消订阅
	
	//大的左侧分类tab
	public final static Map<Integer, String> Tabmap = new HashMap();  
	static {  
		Tabmap.put(1, "校园周边");  
		Tabmap.put(2, "校园资讯");  
		Tabmap.put(3, "兼职招聘");  
		Tabmap.put(4, "二手市场");  
		Tabmap.put(5, "鹊桥相会");  
		Tabmap.put(6, "互动墙壁");  
		Tabmap.put(7, "校园活动");  
	} 
	
}
