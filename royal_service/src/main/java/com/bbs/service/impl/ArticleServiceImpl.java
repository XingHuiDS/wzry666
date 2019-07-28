package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> findAll() throws Exception {
        return articleDao.findAll();
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return articleDao.findById(id);
    }


    @Override
    public Integer loadTodayCount() throws Exception {
        return articleDao.findTodayCount();
    }

    @Override
    public Integer loadAllCount() throws Exception {
        return articleDao.findAllCount();
    }

    @Override
    public List<Article> loadByZoneId(Integer zoneId) throws Exception {
        return articleDao.findByZoneId(zoneId);
    }


    @Override
    public List<Article> loadByZoneIdAndWords(Integer zoneId, String keyWord) throws Exception {
        keyWord = "%" + keyWord + "%";
        return articleDao.findByZoneIdAndWords(zoneId,keyWord);
    }

    @Override
    public int deleteByArticleId(Integer articleId) throws Exception {
        return articleDao.deleteArticle(articleId);

    }

    @Override
    public void changeStatusById(Integer isTop,Integer articleId) {
        articleDao.changeStatusById(isTop,articleId);
    }

    @Override
    public void changeStatusByIds(Integer articleId) {
        articleDao.changeStatusByIds(articleId);
    }

    @Override
    public List<Article> findByPage(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page, size);
        return articleDao.findAll();
    }

    @Override
    public List<Article> findByName(String title, String senderName) {
        return articleDao.findByName(title,senderName);
    }

    @Override
    public void addReplyCount(Integer articleId) {
        articleDao.addReplyCount(articleId);
    }

    @Override
    public void addUpvoteCount(Integer articleId) {
        articleDao.addUpvoteCount(articleId);
    }

    @Override
    public void saveArticle(Article article) {
        articleDao.saveArticle(article);
    }

    @Override
    public void addBrowserCount(Integer articleId) {
       articleDao.addBrowserCount(articleId);
    }

    @Override
    public void deleteUpvoteCount(Integer articleId) {
        articleDao.deleteUpvoteCount(articleId);
    }

    /**
     * 改变帖子举报状态
     * @param articleId
     */
    @Override
    public void changeArticleStatus(Integer articleId) {
        articleDao.changeArticleStatus(articleId);
    }

    @Override
    public UserInfo findByUserInfo(Article article) {
        return articleDao.findByUserInfo(article);
    }

}
