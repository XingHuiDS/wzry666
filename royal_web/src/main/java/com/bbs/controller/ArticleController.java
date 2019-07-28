package com.bbs.controller;
import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.service.ArticleService;
import com.bbs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/saveArticle")
    public ModelAndView saveArticle(Article article, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();

        UserInfo user = (UserInfo) request.getSession().getAttribute("user");

        if(user != null){
            UserInfo newUser = userInfoService.findUserByUsername(user.getUserName());

            Integer talkStatus = newUser.getTalkStatus();

            if (talkStatus == 0){
                String userName = user.getUserName();
                article.setSenderName(userName);
                articleService.saveArticle(article);
                request.getSession().setAttribute("user",newUser);
                mv.addObject("msg","0");

            }

            if (talkStatus == 1){
                mv.addObject("msg","1");

                request.getSession().setAttribute("user",newUser);
            }
        }else {
            mv.addObject("msg","0");

        }

        mv.setViewName("redirect:/index/show");
        return mv;
    }
}
