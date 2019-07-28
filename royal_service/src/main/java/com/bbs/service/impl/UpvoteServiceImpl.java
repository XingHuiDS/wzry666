package com.bbs.service.impl;

import com.bbs.dao.UpvoteDao;
import com.bbs.domain.Article;
import com.bbs.domain.Upvote;
import com.bbs.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UpvoteServiceImpl implements UpvoteService {

    @Autowired
    private UpvoteDao upvoteDao;

    @Override
    public List<Article> findAll() throws Exception {
        return null;
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }


    @Override
    public void saveUpvote(Integer isUpvote,String upvoteUserName, Integer articleId) {
        Upvote upvote = new Upvote();
        upvote.setIsUpvote(isUpvote);
        upvote.setUpvoteUserName(upvoteUserName);
        upvote.setUpvoteArticleId(articleId);
        upvoteDao.saveUpvote(upvote);
    }

    @Override
    public Upvote findByUser(String userName, Integer articleId) {
        return upvoteDao.findByUser(userName,articleId);
    }

    @Override
    public void updateUpvote(Integer isUpvote,String upvoteUserName, Integer articleId) {
        Upvote upvote = new Upvote();
        upvote.setUpvoteUserName(upvoteUserName);
        upvote.setUpvoteArticleId(articleId);
        upvote.setIsUpvote(isUpvote);
        upvoteDao.updateUpvote(upvote);
    }

    @Override
    public void deleteUpvoteCount(String upvoteUserName, Integer articleId) {
        Upvote upvote = new Upvote();
        upvote.setUpvoteUserName(upvoteUserName);
        upvote.setUpvoteArticleId(articleId);
        upvote.setIsUpvote(0);
        upvoteDao.deleteUpvoteCount(upvote);
    }
}
