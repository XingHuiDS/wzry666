package com.bbs.domain;

import com.bbs.utils.DateUtils;

import java.util.Date;

public class Comment {
	private Integer commentId; //评论编号

	private String commentContent; //评论内容

	private Date commentTime; //评论时间

	private String commentTimeStr; //评论时间字符串

	private String commentUserName; //评论人

	private Integer commentStatus; //评论状态，1代表屏蔽，0代表解除

	private Integer articleId; //帖子编号

	@Override
	public String toString() {
		return "Comment{" +
				"commentId=" + commentId +
				", commentContent='" + commentContent + '\'' +
				", commentTime=" + commentTime +
				", commentTimeStr='" + commentTimeStr + '\'' +
				", commentUserName='" + commentUserName + '\'' +
				", commentStatus=" + commentStatus +
				", articleId=" + articleId +
				'}';
	}

	public String getCommentTimeStr() {
		if(commentTime != null){
			commentTimeStr = DateUtils.date2String(commentTime,"yyyy-MM-dd HH:mm:ss");
		}
		return commentTimeStr;
	}

	public void setCommentTimeStr(String commentTimeStr) {
		this.commentTimeStr = commentTimeStr;
	}

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
