package com.bbs.domain;

import java.io.Serializable;

/*
	交流区
*/
public class Upvote implements Serializable {
	private Integer upvoteArticleId; //帖子编号
	private String upvoteUserName; //点赞人
	private Integer isUpvote; //点赞状态（暂时不需要）
	
	public Integer getUpvoteArticleId() {
		return upvoteArticleId;
	}
	
	public void setUpvoteArticleId(Integer upvoteArticleId) {
		this.upvoteArticleId = upvoteArticleId;
	}
	
	public String getUpvoteUserName() {
		return upvoteUserName;
	}
	
	public void setUpvoteUserName(String upvoteUserName) {
		this.upvoteUserName = upvoteUserName;
	}
	
	public Integer getIsUpvote() {
		return isUpvote;
	}
	
	public void setIsUpvote(Integer isUpvote) {
		this.isUpvote = isUpvote;
	}
	
}
