package com.t.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.bean.CartItemBean;
import com.t.bean.OrderBean;
import com.t.bean.OrderItemBean;
import com.t.core.dao.CartDao;
import com.t.core.dao.ItemDao;
import com.t.core.dao.OrderDao;
import com.t.core.dao.OrderRecordDao;
import com.t.core.dao.UserDao;
import com.t.core.entities.Cart;
import com.t.core.entities.Item;
import com.t.core.entities.Order;
import com.t.core.entities.OrderRecord;
import com.t.core.entities.User;
import com.t.service.interfaces.IOrderService;
import com.t.utils.Constants;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderService implements IOrderService{
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderRecordDao orderRecordDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private UserDao userDao;

	//编辑购物车
	public Integer manageCart(Integer userId,Integer itemId,Integer number){
		Integer cartId = cartDao.getCartId(userId);
		OrderRecord record = new OrderRecord();
		if(orderRecordDao.getRecord(cartId, itemId) != null)
			record = orderRecordDao.getRecord(cartId, itemId);
		record.setCartId(cartId);
		record.setItemId(itemId);
		record.setNumber(number);
		orderRecordDao.saveOrUpdate(record);
		return 1;
	}

	//查看购物车；只显示那些 number大于0的商品； 暂时忽略分页
	public List<CartItemBean> getCartItems(Integer userId,String select,Integer pageId,Integer pageSize){
		List<CartItemBean> itemBeans = new ArrayList<CartItemBean>();
		List<OrderRecord> records = new ArrayList<OrderRecord>();
		Integer cartId = cartDao.getCartId(userId);
		records = orderRecordDao.findByCriteria(Restrictions.eq("cartId", cartId),Restrictions.gt("number", 0));
		for(OrderRecord record : records){
			Integer itemId = record.getItemId();
			Query query = itemDao.createQuery("from Item i where i.itemId =:itemId and i.itemName like :name");
			query.setString("name", "%"+select+"%");
			query.setInteger("itemId", itemId);
			CartItemBean bean = null;
			if(query.list() !=null && query.list().size() >0){
				 bean = new CartItemBean((Item)query.list().get(0));	
				 bean.setNumber(record.getNumber());
				 itemBeans.add(bean);
			}
		}
		return itemBeans;
	}

	//下单
	public Integer placeOrder(Integer userId){
		Integer cartId = cartDao.getCartId(userId);
		OrderRecord record = new OrderRecord();
		Order order = new Order();
		//如果用户当前购物车中的商品数量为0则下单失败
		if(orderRecordDao.findByProperty("cartId", cartId).size() == 0)
			return -1;
		record = orderRecordDao.findByProperty("cartId", cartId).get(0);
		Item item = new Item();
		item = itemDao.findByProperty("itemId", record.getItemId()).get(0);
		order.setCartId(cartId);
		order.setUserId(userId);
		order.setMerchantId(item.getMerchant());
		order.setStatus(Constants.UNCHECKED);
		orderDao.save(order);
		//修改购物车状态为已check
		Cart cart = cartDao.findUniqueByProperty("cartId", cartId);
		cart.setStatus(Constants.CHECKED);
		cartDao.saveOrUpdate(cart);
		return 1;
	}

	//查看历史订单；暂时忽略分页,商品的订单时间为下单时间
	public List<OrderItemBean> getOrderItems(Integer userId,Integer pageId,Integer pageSize){
		List<Cart> carts = new ArrayList<Cart>();
		List<OrderItemBean> orderItemBeans = new ArrayList<OrderItemBean>();
		List<Integer> cartIds = new ArrayList<Integer>();
		List<OrderRecord> records = new ArrayList<OrderRecord>();
		//只查看所有已下单的购物车
		carts = cartDao.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("status", Constants.CHECKED));
		for(Cart cart : carts)
			cartIds.add(cart.getCartId());
		//获得所有number > 0的商品
		records = orderRecordDao.getOrderItemByCart(cartIds);
		for(OrderRecord record : records){
			OrderItemBean bean = new OrderItemBean(itemDao.findUniqueByProperty("itemId", record.getItemId()));
			Order order = new Order();
			order = orderDao.findByCriteria(Restrictions.eq("cartId", record.getCartId()),Restrictions.eq("userId", userId)).get(0);
			bean.setOrderTime(order.getOrderTime());
			bean.setNumber(record.getNumber());
			orderItemBeans.add(bean);
		}
		return orderItemBeans;
	}


	/*商铺获得订单列表*/
	@SuppressWarnings("unchecked")
	public List<OrderBean> getOrderList(String merchantId) {	
		List<Order> orders = new ArrayList<Order>();
		List<OrderBean> orderlist = new  ArrayList<OrderBean>();
		List<OrderRecord> records = new  ArrayList<OrderRecord>();

		User user = new User();
		Item item = new Item();
		String itemNames = "";

		orders = orderDao.findByCriteria(Restrictions.eq("merchantId", Integer.parseInt(merchantId)),
				                         Restrictions.eq("status", Constants.UNCHECKED));
		
		if(orders != null){
		//	System.out.print(orders.size());
			for(Order a:orders){
				OrderBean orderbean = new OrderBean();
				orderbean.setOrderId(a.getOrderId());
				String time = String.valueOf(a.getOrderTime());
				orderbean.setOrderTime(time.substring(0,time.length()-2));
				user = userDao.findUniqueByProperty("userId", a.getUserId());
				orderbean.setUserName(user.getUserName());
				orderbean.setUserTele(user.getMobilePhone());

				records = orderRecordDao.findByCriteria(Restrictions.eq("cartId",a.getCartId()),
						                                Restrictions.gt("number",0));
				for(OrderRecord b:records){
					item = itemDao.findUniqueByProperty("itemId", b.getItemId());
					itemNames = itemNames+item.getItemName()+"("+b.getNumber().toString()+"份)"+"+";
				}
				itemNames = itemNames.substring(0,itemNames.length()-1);//去除最后一个"+"
				orderbean.setItemNames(itemNames);

				orderlist.add(orderbean);
				itemNames = "";
			}
			return orderlist;
		}else
			return null;
	}


	public boolean checkOrder(int orderId) {
		Order order = orderDao.findUniqueByProperty("orderId", orderId);
		try {
			order.setStatus(1);
			orderDao.saveOrUpdate(order);
			return true;
		}catch(Exception e){
			return false;
		}
	}



}
