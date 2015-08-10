package com.t.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.ResultBean;
import com.t.bean.SearchResultBean;
import com.t.core.entities.TagEntity;
import com.t.service.interfaces.IMerchantService;
import com.t.service.interfaces.ITagEntityService;
import com.t.service.interfaces.ITagService;
import com.t.service.interfaces.IUserService;
import com.t.utils.BaseAction;
import com.t.utils.ComparatorIndex;

public class SearchAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 942378876091191848L;

	@Autowired
	private IMerchantService shopManager;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private ITagEntityService tagEntityService;
	
	@Autowired
	private IUserService userManager;

	private String keyword;
	private int circleId = 1;
	private int userid;
	private int type;
	private int currentClickShopId;
	private int recommended;

	private List<SearchResultBean> searchResultBeans;
	private List<ResultBean> resultBeans;

	@SuppressWarnings("unchecked")
	public String searchWithWord() throws Exception {
		List<TagEntity> interests = tagEntityService.getUserTags(userid);
		
		if (keyword != null && !keyword.replaceAll(" ", "").equals("")) {
			searchResultBeans = shopManager.getShopListByKeyword(circleId,
					type, interests, keyword,recommended);
		} else {
			searchResultBeans = shopManager.getShopList(circleId, type,
					interests,recommended);
		}
		
		
		
		
		
		resultBeans = new ArrayList<ResultBean>();
		for (int i = 0; i < searchResultBeans.size(); i++) {
			ResultBean r = new ResultBean();
			r.setId(searchResultBeans.get(i).getShopBean().getMerchantId());
			r.setName(searchResultBeans.get(i).getShopBean().getMerchantName());
			r.setRecommended(0);
			r.setWeight((float) (1 - searchResultBeans.get(i).getWeight() / 2));
			resultBeans.add(r);
		}
		Collections.sort(resultBeans, new ComparatorIndex());
		if (resultBeans.size() > 0 && (resultBeans.get(0).getWeight() == 0.0)) {
			for (ResultBean bean : resultBeans) {
				bean.setWeight((float) Math.random() * 0.5f + 0.5f);
			}
			Collections.sort(resultBeans, new ComparatorIndex());
		}
		
		for (int i = 0; i < resultBeans.size(); i++) {
			if(i==1){
				float w = resultBeans.get(i).getWeight();
				if ((w=w-0.2f)<=0.55) {
					w = 0.55f;
				}
				resultBeans.get(i).setWeight(w);
			}else if(i==2){
				float w = resultBeans.get(i).getWeight();
				if ((w=w-0.1f)<=0.7) {
					w = 0.7f;
				}
				resultBeans.get(i).setWeight(w);
			}else if(i>=3){
				float w = resultBeans.get(i).getWeight();
				if ((w=w+0.025f*i)>=1) {
					w = 1;
				}
				resultBeans.get(i).setWeight(w);
			}
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String searchWithShop() throws Exception {
		
		List<TagEntity> interests = tagEntityService.getUserTags(userid);
		searchResultBeans = shopManager.getShopListByShopId(currentClickShopId,
				circleId, type, interests,recommended);
		
		
		resultBeans = new ArrayList<ResultBean>();
		for (int i = 0; i < searchResultBeans.size(); i++) {
			ResultBean r = new ResultBean();
			r.setId(searchResultBeans.get(i).getShopBean().getMerchantId());
			r.setName(searchResultBeans.get(i).getShopBean().getMerchantName());
			r.setRecommended(0);
			r.setWeight((float) (1 - searchResultBeans.get(i).getWeight() / 2));
			resultBeans.add(r);
		}
		Collections.sort(resultBeans, new ComparatorIndex());
		for (int i = 0; i < resultBeans.size(); i++) {
			if(i==1){
				float w = resultBeans.get(i).getWeight();
				if ((w=w-0.2f)<=0.55) {
					w = 0.55f;
				}
				resultBeans.get(i).setWeight(w);
			}else if(i==2){
				float w = resultBeans.get(i).getWeight();
				if ((w=w-0.1f)<=0.7) {
					w = 0.7f;
				}
				resultBeans.get(i).setWeight(w);
			}else if(i>=3){
				float w = resultBeans.get(i).getWeight();
				if ((w=w+0.025f*i)>=1) {
					w = 1;
				}
				resultBeans.get(i).setWeight(w);
			}
		}
		return SUCCESS;
	}

	public IMerchantService getShopManager() {
		return shopManager;
	}

	public void setShopManager(IMerchantService shopManager) {
		this.shopManager = shopManager;
	}

	public ITagService getTagService() {
		return tagService;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public ITagEntityService getTagEntityService() {
		return tagEntityService;
	}

	public void setTagEntityService(ITagEntityService tagEntityService) {
		this.tagEntityService = tagEntityService;
	}

	public IUserService getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserService userManager) {
		this.userManager = userManager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCurrentClickShopId() {
		return currentClickShopId;
	}

	public void setCurrentClickShopId(int currentClickShopId) {
		this.currentClickShopId = currentClickShopId;
	}

	public int getRecommended() {
		return recommended;
	}

	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}

	public List<SearchResultBean> getSearchResultBeans() {
		return searchResultBeans;
	}

	public void setSearchResultBeans(List<SearchResultBean> searchResultBeans) {
		this.searchResultBeans = searchResultBeans;
	}

	public List<ResultBean> getResultBeans() {
		return resultBeans;
	}

	public void setResultBeans(List<ResultBean> resultBeans) {
		this.resultBeans = resultBeans;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
