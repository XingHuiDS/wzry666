package com.bbs.service;

import com.bbs.domain.Article;

import java.util.Date;
import java.util.List;

public interface ArticleService {

    /*
    * 发帖总数
    * */
    int findArticle();
    /*
    * 今日发帖总数
    * */
    int findTodayCount(String date);

    /*
    * 回显bbs_article_table表中的数据到前端页面
    * */
    List<Article> findUpvoteCount();





}
