package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @return
 * @throws
 */
public interface ArticleDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_article_table")
    List<Article> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_article_table where articleId=#{id}")
    Article findById(Integer id) throws Exception;

    /**
     * 统计今日帖子数
     * @return
     * @throws Exception
     */
    @Select("select count(1) from bbs_article_table where sendTime >= CURRENT_DATE")
    public Integer findTodayCount() throws Exception;

    /**
     * 统计所有帖子数
     * @return
     * @throws Exception
     */
    @Select("select count(1) from bbs_article_table")
    public Integer findAllCount() throws Exception;

    /**
     * 查询当前分区帖子
     * @param zoneId
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_article_table where zoneId = #{zoneId} and isReport = 0 order by isTop desc")
    public List<Article> findByZoneId(Integer zoneId) throws Exception;

    /**
     * 关键字搜索标题
     * @param zoneId
     * @param keyWord
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_article_table where zoneId = #{zoneId} and isReport = 0 and (title like #{keyWord}) order by isTop desc")
    public List<Article> findByZoneIdAndWords(@Param("zoneId") Integer zoneId, @Param("keyWord") String keyWord) throws Exception;


    /**
     * 更改置顶状态
     * @param isTop
     * @param articleId
     */
    @Update("update bbs_article_table set isTop=#{isTop} where articleId=#{articleId}")
    void changeStatusById(@Param("isTop") Integer isTop, @Param("articleId") Integer articleId);

    /**
     * 取消置顶状态
     * @param articleId
     */
    @Update("UPDATE bbs_article_table SET isTop = 0 WHERE articleId = #{articleId}")
    void changeStatusByIds(Integer articleId);

    /**
     * 根据ID删除
     * @param articleId
     * @return
     */
    @Delete("DELETE FROM bbs_article_table WHERE articleId=#{articleId} ")
    int deleteArticle(Integer articleId);

    @Update("UPDATE bbs_article_table SET replyCount = (replyCount+1) where articleId=#{articleId}")
    void addReplyCount(Integer articleId);

    /**
     * 点赞+1
     * @param articleId
     */
    @Update("update bbs_article_table set upvoteCount=(upvoteCount+1) where articleId=#{articleId}")
    void addUpvoteCount(Integer articleId);

    /**
     * 保存帖子
     *
     */
    @Insert("insert into bbs_article_table (title,content,senderName,zoneId) values (#{title},#{content},#{senderName},#{zoneId})")
    void saveArticle(Article article);


    /**
     * 模糊查询
     * @param title
     * @param senderName
     * @return
     */
    @Select("<script>"+"select * from bbs_article_table where 1=1"
            + "<if test='title!=null'>and title like CONCAT('%',#{title},'%')</if>"
            + "<if test='senderName!=null'>and senderName like CONCAT('%',#{senderName},'%')</if>"
            +"</script>")
    List<Article> findByName(String title, String senderName);

    /**
     * 帖子浏览数+1
     */
    @Update("UPDATE bbs_article_table SET browseCount = (browseCount+1) where articleId=#{articleId}")
    void addBrowserCount(Integer articleId);

    /**
     * 点赞-1
     * @param articleId
     */
    @Update("update bbs_article_table set upvoteCount=(upvoteCount-1) where articleId=#{articleId}")
    void deleteUpvoteCount(Integer articleId);

    /**
     * 改变帖子举报状态
     * @param articleId
     */
    @Update("update bbs_article_table set isReport = 0 where articleId = #{articleid}")
    void changeArticleStatus(Integer articleId);
}
