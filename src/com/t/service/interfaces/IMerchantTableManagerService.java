package com.t.service.interfaces;

import java.util.List;

import com.t.core.entities.MerchantTableInfo;

public interface IMerchantTableManagerService {
	public List<MerchantTableInfo> findByMerchantId(int merchantId);
	public Boolean addMerchantTableInfo(MerchantTableInfo table);
	public Boolean removeMerchantTableInfo(MerchantTableInfo table);
	public Boolean modifyMerchantTableInfo(MerchantTableInfo table);
	public MerchantTableInfo findTableById(int tableid);
}
