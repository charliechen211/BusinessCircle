package com.t.service.interfaces;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.MongoException;
import com.t.core.entities.Data;

public interface IDataTranstate {

	public Integer trans();
	public List<Data> getData(Integer pageNo,Integer pageSize);
	public void insertMongoDB() throws UnknownHostException, MongoException;
}
