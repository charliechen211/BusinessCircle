package com.t.utils;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.t.core.entities.Merchant;
import com.t.core.entities.MerchantOwner;

public class BaseAction extends ActionSupport implements SessionAware , ServletRequestAware , ServletResponseAware{

		private static final long serialVersionUID = 549799975921395119L;

		public static final String SUCCESS = "success";

		public static final String FAIL = "fail";
		
		public static final String Admin = "admin";
		
		public static final String CMAPUS = "campus";
		
		public static final String EMPTY = "empty";
		
		public static final String NOTEXIST = "noExist";
		
		public static final String STATE = "state";
		
		public static final SimpleDateFormat DATE_FORMATE = new SimpleDateFormat("yyyy-MM-dd");
		
		public Map<String,Object>result;
		
		public String state;

		public Vector<Integer> myshopids;
		
		public MerchantOwner merchantowner;
		
		public List<Merchant> mymerchantlist;
		public List<String> tableTypes;
		
		public String logFlag = "no" ;

		public String getLogFlag() {
			return logFlag;
		}

		public void setLogFlag(String logFlag) {
			this.logFlag = logFlag;
		}

		//记录action执行后的返回信息，用于界面显示
		public String actionResult = "";

		protected final Log logger = LogFactory.getLog(getClass());
		
		@SuppressWarnings({ "rawtypes" })
		protected Map session;	
		
		protected HttpServletRequest servletRequest = null;
		protected HttpServletResponse servletResponse = null;
		
		public BaseAction(){
			session = new HashMap<String, Object>(); 
			result = new HashMap<String, Object>();
			System.out.println(logFlag);
		}

		public String getActionResult() {
			return actionResult;
		}
		
		public void setActionResult(String str){
			actionResult = str;
		}

		public Map<String, Object> getResult() {
			//System.out.println("baseaction: Added");
			return result;
		}

		public void setResult(Map<String, Object> result) {
			this.result = result;
		}

		@SuppressWarnings("rawtypes")
		public Map getSession() {
			return session;
		}

		public void setServletRequest(HttpServletRequest request) {
			this.servletRequest =request;
			
		}

		public void setServletResponse(HttpServletResponse response) {
			this.servletResponse = response;
			
		}

		public HttpServletRequest getServletRequest() {
			return servletRequest;
		}

		public HttpServletResponse getServletResponse() {
			return servletResponse;
		}

		@SuppressWarnings("rawtypes")
		public void setSession(Map session) {
			this.session = session;
		}

		
		public MerchantOwner getMerchantOwner() {
			if (null == this.merchantowner)
			{
				Object user = session.get(Constants.SESSION_USER);
				if (null != user && user instanceof MerchantOwner)
				{
					this.merchantowner = (MerchantOwner)user;
				}
			}
			 
			return this.merchantowner;
		}

		@SuppressWarnings("unchecked")
		public void setMerchantOwner(MerchantOwner user) {
			this.merchantowner = user;
			session.put(Constants.SESSION_USER, this.merchantowner);
			 
		}

		@SuppressWarnings("unchecked")
		public Vector<Integer> getMyshopids() {
			if (null == this.myshopids)
			{
				Object ids = session.get(Constants.SESSION_MyShopIds);
				if (null != ids && ids instanceof Vector)
				{
					this.myshopids = (Vector<Integer>) ids;
				}
			}
			return this.myshopids;
		}

		/*获得登录用户开设的商铺id列表*/
		@SuppressWarnings("unchecked")
		public void setMyshopids(Vector<Integer> myshopids) {
			this.myshopids = myshopids;
			session.put(Constants.SESSION_MyShopIds, this.myshopids);
		}
		
		
		@SuppressWarnings("unchecked")
		public List<String> getTableTypes() {
			if (null == this.tableTypes)
			{
				Object tableTypes = session.get(Constants.SESSION_tableTypes);
				if (null != tableTypes && tableTypes instanceof List)
				{
					this.tableTypes = (List<String>) tableTypes;
				}
			}
			return this.tableTypes;
			 
		}

		@SuppressWarnings("unchecked")
		public void setTableTypes(List<String> tableTypes) {
			this.tableTypes = tableTypes;
			session.put(Constants.SESSION_tableTypes, this.tableTypes);
		}
		
		public String getState() {
			if (this.getFieldErrors().size()>0)
				this.state = FAIL;		
			else {
				this.state = SUCCESS;
			}
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Merchant> getMymerchantlist() {
			if (null == this.mymerchantlist)
			{
				Object shops = session.get(Constants.SESSION_mymerchantlist);
				if (null != shops && shops instanceof List )
				{
					this.mymerchantlist = (List<Merchant>) shops;
				}	
			}
			return this.mymerchantlist;
		}
		
		/*设置登录用户的商铺集合*/
		@SuppressWarnings("unchecked")
		public void setMymerchantlist(List<Merchant> mymerchantlist) {
			this.mymerchantlist = mymerchantlist;
			session.put(Constants.SESSION_mymerchantlist, this.mymerchantlist);
		}

}
