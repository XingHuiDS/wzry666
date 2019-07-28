package com.bbs.controller;

import com.bbs.domain.*;
import com.bbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/comment")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private WordService wordService;

    @Autowired
    private UpvoteService upvoteService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 查询所有评论
     * 查询帖子详细信息
     * 查询登录用户是否点赞
     * @param articleId
     * @return
     */
    @RequestMapping("/findComment.do")
    public ModelAndView findDetail(Integer articleId) throws Exception {
        ModelAndView mv = new ModelAndView();
         //查询所有评论
        List<Comment> comments = commentService.findComment(articleId);
        //查询当前帖子
        Article article = articleService.findById(articleId);
        UserInfo userImg=  articleService.findByUserInfo(article);
        //帖子浏览数量加一
        articleService.addBrowserCount(articleId);
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        //设置点赞数
        Upvote upvote = null;
        if(user != null){
            upvote = upvoteService.findByUser(user.getUserName(), articleId);
            if(upvote == null){
                upvote = new Upvote();
                upvote.setIsUpvote(0);
            }
            mv.addObject("upvote",upvote);
        }
        //敏感词过滤
        List<Word> words = wordService.findByStatus(1);
        filter(article,words);
        mv.addObject("userImg",userImg);
        mv.addObject("article",article);
        mv.addObject("article",article);
        mv.addObject("commentList",comments);
        mv.setViewName("getArticle");

        if(user != null){
            UserInfo userInfo = userInfoService.findUserByUsername(user.getUserName());

            if(userInfo.getTalkStatus() == 0){
                request.getSession().setAttribute("msg",0);
                request.getSession().setAttribute("user",userInfo);
            }else {
                request.getSession().setAttribute("msg",1);
                request.getSession().setAttribute("user",userInfo);
            }
        }else {

            request.getSession().setAttribute("msg",0);
        }

        return mv;
    }

    /**
     * 增加一条评论
     * @param comment
     * @return
     */
    @RequestMapping("/saveComment.do")
    public String saveComment(Comment comment){
        ModelAndView mv = new ModelAndView();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        UserInfo userInfo = userInfoService.findUserByUsername(user.getUserName());
        if(userInfo.getTalkStatus() == 0){
            commentService.saveComment(comment);
            articleService.addReplyCount(comment.getArticleId());
            request.getSession().setAttribute("msg",0);
            request.getSession().setAttribute("user",userInfo);
        }else {
            request.getSession().setAttribute("msg",1);
            request.getSession().setAttribute("user",userInfo);
        }


        return "redirect:findComment.do?articleId="+comment.getArticleId();
    }

    /**
     * 敏感词过滤
     * @param article
     * @param words
     * @throws Exception
     */
    private void filter(Article article, List<Word> words) throws Exception {
            for (Word word : words) {
                article.setTitle(article.getTitle().replaceAll(word.getWord(),"**"));
                article.setContent(article.getContent().replaceAll(word.getWord(),"**"));
            }
    }


}
