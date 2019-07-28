package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Upvote;
import com.bbs.service.ArticleService;
import com.bbs.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/upvote")
public class UpvoteController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UpvoteService upvoteService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 点赞+1
     *
     * @return
     */
    @RequestMapping("/addUpvoteCount")
    public @ResponseBody
    Article addUpvoteCount(@RequestParam("isUpvote")Integer isUpvote, @RequestParam("articleId") Integer articleId, @RequestParam("upvoteUserName") String upvoteUserName) throws Exception {
        Upvote upvote = upvoteService.findByUser(upvoteUserName, articleId);
        if (upvote == null) {
            upvoteService.saveUpvote(isUpvote,upvoteUserName, articleId);
            articleService.addUpvoteCount(articleId);
        } else {
            upvoteService.updateUpvote(isUpvote,upvoteUserName, articleId);
            articleService.addUpvoteCount(articleId);
        }
        Article article = articleService.findById(articleId);
        return article;
    }

    /**
     * 取消点赞
     * @param isUpvote
     * @param articleId
     * @param upvoteUserName
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteUpvoteCount")
    public @ResponseBody
    Article deleteUpvoteCount(@RequestParam("isUpvote")Integer isUpvote, @RequestParam("articleId") Integer articleId, @RequestParam("upvoteUserName") String upvoteUserName) throws Exception {
        Upvote upvote = upvoteService.findByUser(upvoteUserName, articleId);
        upvoteService.deleteUpvoteCount(upvoteUserName, articleId);

        articleService.deleteUpvoteCount(articleId);
        Article article = articleService.findById(articleId);
        return article;
    }

}
