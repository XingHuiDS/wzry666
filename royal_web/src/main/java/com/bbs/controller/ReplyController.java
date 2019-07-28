package com.bbs.controller;

import com.bbs.domain.Reply;
import com.bbs.service.ArticleService;
import com.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {


    @Autowired
    private ReplyService replyService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/findByCommentId.do")
    public @ResponseBody List<Reply> findByCommentId(Integer commentId) throws IOException {
        //获取所有评论的回复
        return replyService.findByCommentId(commentId);
    }

    @RequestMapping("/saveReply.do")
    public String saveReply(Reply reply, @RequestParam(name = "articleId",required = true)Integer articleId){

        replyService.saveReply(reply);
        articleService.addReplyCount(articleId);
        return "redirect:/comment/findComment.do?articleId="+articleId;
    }


}
