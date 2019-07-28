package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论dao
 * @return
 * @throws
 */
public interface CommentDao {

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
    @Select("select * from bbs_comment_table where articleId=#{articleId}")
    List<Comment> findComment(Integer articleId);

    @Insert("insert into bbs_comment_table(commentContent,commentTime,commentUserName," +
            "articleId) values(#{commentContent},#{commentTime},#{commentUserName},#{articleId})")
    void saveComment(Comment comment);
}
