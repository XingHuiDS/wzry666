package com.bbs.service.impl;

import com.bbs.dao.CommentDao;
import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Article> findAll() throws Exception {
        return null;
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Comment> findComment(Integer articleId) {
        return commentDao.findComment(articleId);
    }

    @Override
    public void saveComment(Comment comment) {
        comment.setCommentTime(new Date());
        commentDao.saveComment(comment);
    }


}
