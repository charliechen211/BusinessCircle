package com.t.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;

import com.t.bean.CommentBean;
import com.t.service.interfaces.ICommentService;
import com.t.service.interfaces.ITagService;
import com.t.utils.BaseAction;

public class CommentAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CommentBean> comments;
	private Integer entityId;
	private Integer entityType;
	private Integer userId;
	private String content;
	private Double rate1;
	private Double rate2;
	private Double rate3;
	private String tags;
	private Double consume;

	@Autowired
	private ICommentService commentServe;
	@Autowired
	private ITagService tagServe;
	
	private int merchantId;
	private String getresul;
	
	

	public String getGetresul() {
		return getresul;
	}

	public void setGetresul(String getresul) {
		this.getresul = getresul;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Double getConsume() {
		return consume;
	}

	public void setConsume(Double consume) {
		this.consume = consume;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CommentBean> getComments() {
		return comments;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getRate1() {
		return rate1;
	}

	public void setRate1(Double rate1) {
		this.rate1 = rate1;
	}

	public Double getRate2() {
		return rate2;
	}

	public void setRate2(Double rate2) {
		this.rate2 = rate2;
	}

	public Double getRate3() {
		return rate3;
	}

	public void setRate3(Double rate3) {
		this.rate3 = rate3;
	}

	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}
	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}
	//拉取物品或商家的评论信息
	public String fetchComments(){
		try{
			comments = commentServe.fetchComments(entityId,entityType);
		}catch(NullPointerException e){
			result.put(STATE, FAIL);
			return SUCCESS;
		}
		result.put("results", comments);
		result.put(STATE,SUCCESS);
		return SUCCESS;
	}
	//将评论内容添加到物品或商品的评论中
	public String addComments()
	{
		int code = commentServe.addComments(entityId,entityType, userId, content, rate1, rate2, rate3,consume);
		List<String> tagList = new ArrayList<String>();
		tagList = Arrays.asList(tags.split(";"));
		if(tagList.size() > 0)
		{
			tagServe.insertTag(userId, entityId,Long.valueOf(String.valueOf( entityType)), tagList);
		}
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getAnalysis(){
		//System.out.println(merchantId);
		List<Float> analysisresult = new ArrayList<Float>();
		//ArrayList dataList = new ArrayList();
		try{  
			analysisresult = commentServe.getAnalysisResult(merchantId);
			
			result.put("getresult",analysisresult); 
			//System.out.println("json:"+ result);  
		}catch (Exception e) {  
			e.printStackTrace();  
		}  
		return SUCCESS;  
	}
	
	public String getShopComments(){
		try{  
			comments = commentServe.fetchComments(merchantId,1);
			result.put("comments",comments);
			//System.out.println("json:"+ result);  
		}catch (Exception e) {  
			e.printStackTrace();  
		}  
		return SUCCESS;  
	}

	
}
