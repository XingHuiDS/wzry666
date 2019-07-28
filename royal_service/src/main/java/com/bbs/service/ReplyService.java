package com.bbs.service;

import com.bbs.domain.Reply;

import java.util.List;


public interface ReplyService {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Reply> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Reply findById(Integer id) throws Exception;

    /**
     * 根据评论的id查询回复
     * @param commentId
     * @return
     */
    List<Reply> findByCommentId(Integer commentId);


    /**
     * 增加评论的回复
     * @param reply
     */
    void saveReply(Reply reply);
}
