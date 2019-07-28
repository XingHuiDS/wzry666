package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 回复dao
 * @return
 * @throws
 */
public interface ReplyDao {

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
     * 根据评论的id查询回复
     * @param commentId
     * @return
     */
    @Select("select * from bbs_reply_table where commentId=#{commentId}")
    List<Reply> findByCommentId(Integer commentId);

    /**
     * 增加评论的回复
     * @param reply
     */
    @Insert("insert into bbs_reply_table(replyContent,replyUserName,commentId) values(#{replyContent},#{replyUserName},#{commentId})")
    void saveReply(Reply reply);


}
