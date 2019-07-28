package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Upvote;

import java.util.List;

public interface UpvoteService {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Article> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;

    /**
     * 增加点赞信息
     * @param upvoteUserName
     * @param articleId
     */
    void saveUpvote(Integer isUpvote, String upvoteUserName, Integer articleId);

    /**
     * 根据用户名和帖子id查询点赞数
     * @param userName
     * @param articleId
     * @return
     */
    Upvote findByUser(String userName, Integer articleId);

    /**
     * 增加点赞数
     * @param upvoteUserName
     * @param articleId
     */
    void updateUpvote(Integer isUpvote, String upvoteUserName, Integer articleId);

    /**
     * 删除点赞数
     * @param upvoteUserName
     * @param articleId
     */
    void deleteUpvoteCount(String upvoteUserName, Integer articleId);
}
