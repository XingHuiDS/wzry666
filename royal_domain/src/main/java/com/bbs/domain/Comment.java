package com.bbs.domain;

import java.io.Serializable;
import java.util.Date;

/*
	评论
*/
public class Comment implements Serializable {
	private Integer commentId; //编号
	private String commentContent; //内容
	private Date commentTime; //评论时间
	private String commentUserName; //评论人姓名
	private Integer commentStatus; //评论状态，1代表屏蔽，0代表解除
	private Integer articleId; //该评论所属的帖子
	
	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public Date getCommentTime() {
		return commentTime;
	}
	
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	public String getCommentUserName() {
		return commentUserName;
	}
	
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	
	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Integer getArticleId() {
		return articleId;
	}
	
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
}
