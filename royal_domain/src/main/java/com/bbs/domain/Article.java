package com.bbs.domain;

import com.bbs.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子信息表
 */
public class Article{
	private Integer articleId; //帖子编号
	private String title; //标题
	private String content; //内容
	private Date sendTime; //发送时间
	private String sendTimeStr; //发送时间格式串
	private String senderName; //发送人姓名
	private Integer isTop; //是否置顶，如果是0，代表不置顶；如果是1，代表置顶；
	private Integer replyCount; //评论数
	private Integer upvoteCount; //点赞数
	private Integer browseCount; //浏览数
	private Integer zoneId; //所在交流区
	private Integer isReport; //举报状态

	@Override
	public String toString() {
		return "Article{" +
				"articleId=" + articleId +
				", title='" + title + '\'' +
				", cont='" + content + '\'' +
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

	public Integer getIsReport() {
		return isReport;
	}

	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}

	public String getSendTimeStr() {
		if(sendTime != null){
			sendTimeStr = DateUtils.date2String(sendTime,"yyyy-MM-dd HH:mm:ss");
		}
		return sendTimeStr;
	}

	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}
}
