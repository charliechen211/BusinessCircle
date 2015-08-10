package com.t.service.interfaces;

import java.util.List;


import com.t.bean.CartItemBean;
import com.t.bean.OrderBean;
import com.t.bean.OrderItemBean;

public interface IOrderService {
	//增删购物车,查看购物车
	public Integer manageCart(Integer userId,Integer itemId,Integer number);
	public List<CartItemBean> getCartItems(Integer userId,String select,Integer pageId,Integer pageSize);
	//下单,查看历史订单
	public Integer placeOrder(Integer userId);
	public List<OrderItemBean> getOrderItems(Integer userId,Integer pageId,Integer pageSize);
	
	public List<OrderBean> getOrderList(String merchantId);//发给商家
	public boolean checkOrder(int orderId);

}
