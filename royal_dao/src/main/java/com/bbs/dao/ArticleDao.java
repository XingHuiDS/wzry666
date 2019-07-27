package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface ArticleDao {

    /**
     * 查询总发帖数
     * @param
     * @return
     */
    @Select("select count(articleId) from bbs_article_table")
    int findArticle();

    /**
     * 查询今日发帖数
     * @param
     * @return
     */
    @Select("select count(articleId) from bbs_article_table where sendTime >= #{date}")
    int findTodayCount(String date) throws Exception;

    /**
     * 查询总点赞数
     * @param
     * @return
     */
@Select("select * from bbs_article_table")
    List<Article> findUpvoteCount();




}
