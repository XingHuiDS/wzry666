package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Upvote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 更新dao
 * @return
 * @throws
 */
public interface UpvoteDao {

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
     * @param upvote
     */
    @Insert("insert into bbs_upvote_table values(#{upvoteUserName},#{upvoteArticleId},#{isUpvote})")
    void saveUpvote(Upvote upvote);

    /**
     * 根据用户名和帖子id查询点赞数
     * @param userName
     * @param articleId
     * @return
     */
    @Select("select * from bbs_upvote_table where upvoteUserName=#{upvoteUserName} and upvoteArticleId=#{upvoteArticleId}")
    Upvote findByUser(@Param("upvoteUserName") String userName, @Param("upvoteArticleId") Integer articleId);

    /**
     * 更新点赞
     * @param upvote
     */
    @Update("update bbs_upvote_table set isUpvote=#{isUpvote} where upvoteUserName=#{upvoteUserName} and upvoteArticleId=#{upvoteArticleId}")
    void updateUpvote(Upvote upvote);

    @Update("update bbs_upvote_table set isUpvote=#{isUpvote} where upvoteUserName=#{upvoteUserName} and upvoteArticleId=#{upvoteArticleId}")
    void deleteUpvoteCount(Upvote upvote);
}
