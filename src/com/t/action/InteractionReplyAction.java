package com.t.action;
/**
 * 互动墙的回复entity
 */

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.core.entities.InterReplyBean;
import com.t.core.entities.InteractionReply;
import com.t.service.interfaces.IInteractionReplyService;
import com.t.utils.BaseAction;

public class InteractionReplyAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int id;
	private int topicId;
	private Timestamp date;
	private String content;
	private Integer pageId;
	private Integer pageSize;


	@Autowired
	public IInteractionReplyService ReplyServ;

	public String addReply(){
		InteractionReply reply = new InteractionReply();

		try {
			//content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
			reply.setContent(content);
			reply.setTopicId(topicId);
			ReplyServ.addReply(reply);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		} catch (Exception e) {
			//e.printStackTrace();
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String getReplyList(){
		List<InterReplyBean> list = new ArrayList<InterReplyBean>();
		try{
			list = ReplyServ.getReplies(topicId/*, pageId, pageSize*/);
			result.put("result",list);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
