package com.bbs.domain;

import com.bbs.utils.DateUtils;
import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;
import java.util.Date;

/*
	帖子
*/
public class Article implements Serializable {
	private Integer articleId; //帖子编号
	private String title; //标题
	private String content; //内容
	private Date sendTime; //发帖时间
	private String senderName; //发帖人姓名
	private Integer isTop; //是否置顶，0代表不置顶，1代表置顶
	private Integer replyCount; //回复数
	private Integer upvoteCount; //点赞数
	private Integer browseCount; //浏览数
	private Integer zoneId; //交流区
	private Integer isReport;//举报状态
    private String sendTimeStr;//转换sendTime类型为string类型，方便前端页面获取

	public String getSendTimeStr() {
		return sendTimeStr;
	}

	public void setSendTimeStr(Date sendTime) {

	String date=DateUtils.date2String(sendTime,"yyyy-MM-dd");
		this.sendTimeStr = date;
	}

	public Integer getIsReport() {
		return isReport;
	}

	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}

	public Integer getArticleId() {
		return articleId;
	}
	
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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
	
	public Date getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	public String getSenderName() {
		return senderName;
	}
	
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public Integer getIsTop() {
		return isTop;
	}
	
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
	public Integer getReplyCount() {
		return replyCount;
	}
	
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	
	public Integer getUpvoteCount() {
		return upvoteCount;
	}
	
	public void setUpvoteCount(Integer upvoteCount) {
		this.upvoteCount = upvoteCount;
	}
	
	public Integer getBrowseCount() {
		return browseCount;
	}
	
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	@Override
	public String toString() {
		return "Article{" +
				"articleId=" + articleId +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", sendTime=" + sendTime +
				", senderName='" + senderName + '\'' +
				", isTop=" + isTop +
				", replyCount=" + replyCount +
				", upvoteCount=" + upvoteCount +
				", browseCount=" + browseCount +
				", zoneId=" + zoneId +
				", isReport=" + isReport +
				'}';



	}
}
