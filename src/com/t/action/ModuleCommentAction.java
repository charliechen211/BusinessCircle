package com.t.action;
/**
 * 兼职，鹊桥，二手，校园活动的评论都用该action
 */
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.t.bean.ModuleCommentBean;
import com.t.service.interfaces.IModuleCommentService;
import com.t.utils.BaseAction;

public class ModuleCommentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private Integer objectId;  //帖子的Id
	private String comment;    //回复内容
	private String timestamp;
	private Integer type;   //不同模块的回复 （1-二手 2-鹊桥 3-兼职 4-校园活动）
	private List<ModuleCommentBean> comments;
	
	@Autowired
	private IModuleCommentService commentService;
	
	//获取评论列表
	public String fetchComments() {
		try{
			comments = commentService.fetchComments(objectId, type);
		}catch(NullPointerException e){
			result.put(STATE, FAIL);
			return SUCCESS;
		}
		result.put("results", comments);
		result.put(STATE,SUCCESS);
		return SUCCESS;
	}
	
	//提交评论
	public String addComment() throws UnsupportedEncodingException
	{
		int code = 0;
		//comment = new String(comment.getBytes("ISO-8859-1"), "UTF-8");
		try{
			code = commentService.addComment(userId, objectId, comment, type);
		} catch(NullPointerException e){
			e.printStackTrace();
			result.put(STATE, FAIL);
			return SUCCESS;
		}
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	
	//(取消)点赞
	public String setGood()
	{
		int code = 0;
		try{
			code = commentService.setGood(userId, objectId, type);
		} catch(NullPointerException e){
			result.put(STATE, FAIL);
			return SUCCESS;
		}
		result.put(STATE, SUCCESS);
		return SUCCESS;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	 
	
}
