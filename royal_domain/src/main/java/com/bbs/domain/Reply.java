package com.bbs.domain;

import java.io.Serializable;
import java.util.Date;

/*
	回复评论
*/
public class Reply implements Serializable {
	private Integer replyId; //回复编号
	private String replyContent; //内容
	private Date replyTime; //回复时间
	private String replyUserName; //回复人
	private Integer commentId; //该回复所属的评论
	
	public Integer getReplyId() {
		return replyId;
	}
	
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	
	public String getReplyContent() {
		return replyContent;
	}
	
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	public Date getReplyTime() {
		return replyTime;
	}
	
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
}
