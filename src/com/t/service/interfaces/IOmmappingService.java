package com.t.service.interfaces;

import java.util.List;
import java.util.Vector;

import com.t.core.entities.Merchant;
import com.t.core.entities.Ommapping;

public interface IOmmappingService {
    public void addUser(Ommapping mo);
    @SuppressWarnings("unchecked")
	public Vector getMyshopIds(int ownerid);//获得登录用户所开商铺的id数组
    public void deleteByshopId(int shopid);
    
   
}
