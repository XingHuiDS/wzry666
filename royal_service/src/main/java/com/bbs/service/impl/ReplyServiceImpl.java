package com.bbs.service.impl;

import com.bbs.dao.ReplyDao;
import com.bbs.domain.Reply;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao replyDao;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Override
    public List<Reply> findAll() throws Exception {
        return null;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Reply findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Reply> findByCommentId(Integer commentId) {
       return replyDao.findByCommentId(commentId);
    }

    @Override
    public void saveReply(Reply reply) {

        replyDao.saveReply(reply);
    }


}
