package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;

import java.util.List;

public interface CommentService {
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
     * 根据帖子ID查询评论
     * @param articleId
     * @return
     */
    List<Comment> findComment(Integer articleId);

    /**
     * 插入一条评论
     * @param comment
     */
    void saveComment(Comment comment);
}
