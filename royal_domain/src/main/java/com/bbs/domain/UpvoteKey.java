package com.bbs.domain;

import java.io.Serializable;

/**
 *暂时没用的
 */
public class UpvoteKey implements Serializable {
    private String upvoteusername;//点赞的用户名

    private Integer upvotearticleid;//点赞文章id

    public String getUpvoteusername() {
        return upvoteusername;
    }

    public void setUpvoteusername(String upvoteusername) {
        this.upvoteusername = upvoteusername == null ? null : upvoteusername.trim();
    }

    public Integer getUpvotearticleid() {
        return upvotearticleid;
    }

    public void setUpvotearticleid(Integer upvotearticleid) {
        this.upvotearticleid = upvotearticleid;
    }
}