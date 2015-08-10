package com.t.action;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.t.core.entities.PartTimeJob;
import com.t.service.interfaces.IPartTimeJobService;
import com.t.utils.BaseAction;

public class PartTimeJobAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int jobId;
	private int userId; //发帖者id
	private String title;  //标题
	private String content;//内容

	private int jobtype; //类别（1-实习 2-兼职）
	private String jobname; //职位名称
	private String place;//工作地
	private String requirement;//职位要求
	private String pay;
	private String linkway;   //联系方式

	private Timestamp date;
	private Integer pageId;
	private Integer pageSize;

	@Autowired
	private IPartTimeJobService ptServ;


	public String addJob(){
		try{
			PartTimeJob job = new PartTimeJob();
			//System.out.println(title+content);
			
			job.setTitle(title);
			job.setContent(content);
			job.setUserId(userId);
			job.setJobname(jobname);
			job.setJobtype(jobtype);
			job.setPay(pay);
			job.setPlace(place);
			job.setRequirement(requirement);
			job.setLinkway(linkway);

			ptServ.add(job);
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			e.printStackTrace();
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String getList(){
		try{
			result.put("result",ptServ.getList(jobtype,pageId,pageSize));  //根据类别（1-实习 2-兼职）获得职位列表
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public String getDetails(){
		try{
			result.put("result",ptServ.getDetails(jobId));
			result.put(STATE,SUCCESS);
			return SUCCESS;
		}catch (Exception e){
			result.put(STATE,FAIL);
			return FAIL;
		}
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getJobtype() {
		return jobtype;
	}

	public void setJobtype(int jobtype) {
		this.jobtype = jobtype;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getLinkway() {
		return linkway;
	}

	public void setLinkway(String linkway) {
		this.linkway = linkway;
	}


	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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
