package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    /*
     * 总发帖数
     * */
    @Override
    public int findArticle() {
        int count = articleDao.findArticle();

        return count;
    }

    /*
     *
     * 今日发帖数
     * */
    @Override
    public int findTodayCount(String date) {
        int todayCount = 0;
        try {
            todayCount = articleDao.findTodayCount(date);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return todayCount;


    }
/*
*
* 回显后台bbs_article_table表到前端页面
*
* */
    @Override
    public List<Article> findUpvoteCount() {

        return  articleDao.findUpvoteCount();
    }


}
