package com.t.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.OrderRecord;
import com.t.utils.BaseDao;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderRecordDao extends BaseDao<OrderRecord, Integer>{
	public OrderRecordDao(){
		this.entityClass = OrderRecord.class;
	}
	//获取当前购物车中商品Id的信息
	public OrderRecord getRecord(Integer cartId,Integer itemId){
		if(this.findByCriteria(Restrictions.eq("cartId", cartId),Restrictions.eq("itemId", itemId)).size() > 0)
			return this.findByCriteria(Restrictions.eq("cartId", cartId),Restrictions.eq("itemId", itemId)).get(0);
		else 
			return null;
	}
	//根据购物车Ids 获取相应的商品id;只显示那些number > 0的商品
	@SuppressWarnings("unchecked")
	public List<OrderRecord> getOrderItemByCart(List<Integer> carts){
		List<OrderRecord> records = new ArrayList<OrderRecord>();
		if(carts.size() > 0)
			records = this.createQuery("from OrderRecord o where o.cartId in (:list) and o.number > 0" ).setParameterList("list", carts).list();
		return records;
	}

}
