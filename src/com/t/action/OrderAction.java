package com.t.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.t.bean.CartItemBean;
import com.t.bean.OrderBean;
import com.t.bean.OrderItemBean;
import com.t.service.interfaces.IMerchantService;
import com.t.service.interfaces.IOrderService;
import com.t.utils.BaseAction;

public class OrderAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer ItemId;
	private Integer number;
	private List<CartItemBean> cartItems;
	private List<OrderItemBean> orderItems;
	private List<OrderBean> orderlist;
	
	private Integer pageId;
	private Integer pageSize;
	private String changedOrders;

	private String merchantId;
	private int orderId;
	private boolean booleanResult;
	private String select;

	@Autowired
	private IOrderService orderServe;
	@Autowired
	private IMerchantService mservice;

	//管理我的购物车，增删改购物车中的商品
	public String manageCart(){
		orderServe.manageCart(userId, ItemId, number);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	//查看购物车中商品
	public String checkMyCart(){
		cartItems = orderServe.getCartItems(userId, select,pageId, pageSize);
		result.put("results", cartItems);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	//用户下单
	public String placeOrder(){
		//如果用户在提交订单页面修改购买商品数量，则先保存其数量
		try{
			JSONArray json = new JSONArray();
			json = new JSONArray(changedOrders);
			for(int i=0;i < json.length();i++){
				Integer itemId = json.getJSONObject(i).getInt("itemId");
				Integer number = json.getJSONObject(i).getInt("number");
				orderServe.manageCart(userId, itemId, number);
			}
		}catch(JSONException e){
			e.printStackTrace();
		}
		Integer n = orderServe.placeOrder(userId);
		if(n == -1){
			result.put(STATE, "NoItems");
		}else{
			result.put(STATE, SUCCESS);
		}
		return SUCCESS;
	}
	//用户查看历史订单
	public String checkMyOrders(){
		orderItems = orderServe.getOrderItems(userId, pageId, pageSize);
		result.put("results", orderItems);
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}


	/*商家端操作*/

	//先获得商铺列表
	public String OrderDealPre(){
	 
		try{
			if( this.getMymerchantlist()!=null){
				ActionContext.getContext().put("orderlist",null );
				ActionContext.getContext().put("shoplist", mservice.integratedSearch("-1","-1", "-1", this.getMyshopids()));
				return SUCCESS;
			}else
				return EMPTY;
		}catch(Exception e)
		{
			return FAIL;
		}

	}

	public String getList(){
		try {
			orderlist = orderServe.getOrderList(merchantId);
			ActionContext.getContext().put("merchantId",merchantId);
			ActionContext.getContext().put("orderlist",	orderlist);
			ActionContext.getContext().put("shoplist", this.getMymerchantlist());
			return SUCCESS;
		}catch (Exception e){
			System.out.println(e);
			return FAIL;
		}

	}

	public String checkOrder(){
		try{
		booleanResult = orderServe.checkOrder(orderId);
		ActionContext.getContext().put("shoplist", this.getMymerchantlist());
		return SUCCESS;
		}catch(Exception e){
			return FAIL;
		}
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<OrderBean> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<OrderBean> orderlist) {
		this.orderlist = orderlist;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getItemId() {
		return ItemId;
	}
	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public List<OrderItemBean> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemBean> orderItems) {
		this.orderItems = orderItems;
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
	public List<CartItemBean> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItemBean> cartItems) {
		this.cartItems = cartItems;
	}
	public String getChangedOrders() {
		return changedOrders;
	}
	public void setChangedOrders(String changedOrders) {
		this.changedOrders = changedOrders;
	}
	public boolean isBooleanResult() {
		return booleanResult;
	}
	public void setBooleanResult(boolean booleanResult) {
		this.booleanResult = booleanResult;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	
}
