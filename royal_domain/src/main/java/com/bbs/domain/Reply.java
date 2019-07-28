package com.bbs.domain;

import com.bbs.utils.DateUtils;

import java.util.Date;

/*
	回复评论
*/
public class Reply {
	private Integer replyId; //回复编号
	private String replyContent; //内容
	private Date replyTime; //回复时间
    private String replyTimeStr; //时间字符串格式
	private String replyUserName; //回复人
	private Integer commentId; //该回复所属的评论

    @Override
    public String toString() {
        return "Reply{" +
                "replyId=" + replyId +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                ", replyUserName='" + replyUserName + '\'' +
                ", commentId=" + commentId +
                '}';
    }

    public String getReplyTimeStr() {
        if(replyTime != null){
            replyTimeStr = DateUtils.date2String(replyTime, "yyyy-MM-dd,HH:mm:ss");
        }
        return replyTimeStr;
    }

    public void setReplyTimeStr(String replyTimeStr) {
        this.replyTimeStr = replyTimeStr;
    }

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
