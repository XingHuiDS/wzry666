package com.bbs.manage.controller;

import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**更改置顶状态
 * 删除帖子信息
 * @date 2019/6/25 10:24
 */

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
   private ArticleService articleService;

    @RequestMapping("/findByPage")
    public ModelAndView findByPage(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Article> wordList = articleService.findByPage(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo(wordList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("article-page-list");
        return mv;
    }


    /**
     * 根据ID删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteArticle.do")
    //@ResponseBody //一旦加了这个注解，那么你返回的就是一个json
    public String  deleteArticle(@RequestParam(name = "id",required = true)Integer id) throws Exception {
        articleService.deleteByArticleId(id);
        return "redirect:findByPage";

    }

    /**
     * 根据ID更改置顶状态
     * @param
     * @return
     */
    @RequestMapping("/changeStatus.do")
    public String changeStatus (@RequestParam(name="id",required = true) Integer articleId,@RequestParam(name="isTop",required = true) Integer isTop){
        articleService.changeStatusById(isTop,articleId);
        return "redirect:findByPage";
    }


    /**
     * 模糊查询
     */
    @RequestMapping("/findByName")
    public ModelAndView findByName(String title,String senderName) {
        ModelAndView mv = new ModelAndView();
        List<Article> articles = articleService.findByName(title, senderName);
        mv.addObject("articles", articles);
        mv.setViewName("article-page-list");
        return mv;
    }

    /**
     * 根据用户id查询信息
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/findById.do")
    public Article findById(Integer id) throws Exception{
        Article article=articleService.findById(id);
        return article;
    }

}
