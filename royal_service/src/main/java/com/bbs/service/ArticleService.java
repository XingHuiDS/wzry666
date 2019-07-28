package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;

import java.util.List;

public interface ArticleService {
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
     * 统计今日帖子数
     * @return
     * @throws Exception
     */
    public Integer loadTodayCount() throws Exception;

    /**
     * 统计所有帖子数
     * @return
     * @throws Exception
     */
    public Integer loadAllCount() throws Exception;

    /**
     * 查询当前分区帖子
     * @param zoneId
     * @return
     * @throws Exception
     */
    public List<Article> loadByZoneId(Integer zoneId) throws Exception;

    /**
     *关键字搜索
     * @param zoneId
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Article> loadByZoneIdAndWords(Integer zoneId, String keyWord) throws Exception;


    /**
     * 根据ID删除
     * @param  articleId
     * @throws Exception
     */
    int deleteByArticleId(Integer articleId)throws Exception;


    /**
     *根据ID更改置顶状态
     * @param articleId
     * @param
     */
    void changeStatusById(Integer isTop, Integer articleId);

    /**
     * 取消置顶状态
     * @param articleId
     */
    void changeStatusByIds(Integer articleId);
    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    List<Article> findByPage(Integer page, Integer size) throws Exception;


    /**
     * 模糊查询
     * 吴元陶
     * @param title
     * @param senderName
     * @return
     */
    List<Article> findByName(String title, String senderName);

    /**
     * 给指定帖子增加一条评论数
     * @param articleId
     */
    void addReplyCount(Integer articleId);

    /**
     * 点赞+1
     */
    void addUpvoteCount(Integer articleId);

    void saveArticle(Article article);

    /**
     * 帖子浏览数+1
     */
    void addBrowserCount(Integer articleId);

    /**
     * 点赞-1
     * @param articleId
     */
    void deleteUpvoteCount(Integer articleId);

    /**
     * 改变帖子举报状态
     * @param articleId
     */
    void changeArticleStatus(Integer articleId);

  UserInfo findByUserInfo(Article article);
}
