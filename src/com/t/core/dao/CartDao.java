package com.t.core.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.t.core.entities.Cart;
import com.t.utils.BaseDao;
import com.t.utils.Constants;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CartDao extends BaseDao<Cart, Integer>{
	public CartDao(){
		this.entityClass = Cart.class;
	}
	//获取用户的购物车编号。若该用户有未提交的购物车，则返回该购物车；否则创建新的购物车编号。
	public Integer getCartId(Integer userId){
		if(this.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("status", Constants.UNCHECKED)).size() > 0)
			return this.findByCriteria(Restrictions.eq("userId", userId),Restrictions.eq("status", Constants.UNCHECKED)).get(0).getCartId();
		else{
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setStatus(Constants.UNCHECKED);
			Integer cartId = this.save(cart);
			this.flush();
			return cartId;
		}
	}

}
