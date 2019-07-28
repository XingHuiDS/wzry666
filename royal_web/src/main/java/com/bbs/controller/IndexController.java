package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.domain.Word;
import com.bbs.domain.Zone;
import com.bbs.service.ArticleService;
import com.bbs.service.UserInfoService;
import com.bbs.service.WordService;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private WordService wordService;

//    @Autowired获取到的是装饰器对象
//    方法参数获取到的是真实对象 session同理--星辉语录
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/show")
    public ModelAndView show() throws Exception {
        ModelAndView mv = new ModelAndView();
        //登录状态1为登入
        String loginStatus = "1";
        List<UserInfo> userList = userInfoService.loadAllLogined(loginStatus);
        List<Zone> zoneList = zoneService.findAll();

        Integer zoneId = -1;
        for (Zone zone : zoneList) {
            if(zone.getIsDef() == 1){
                zoneId = zone.getZoneId();
            }
        }
        List<Article> articleList = articleService.loadByZoneId(zoneId);

        //敏感词过滤 状态为1为敏感词
        List<Word> words = wordService.findByStatus(1);
        filter(articleList,words);
        //统计今日帖子数
        Integer todayCount = articleService.loadTodayCount();
        //统计总帖子数
        Integer allCount = articleService.loadAllCount();

        mv.addObject("zoneList",zoneList);
        mv.addObject("userList",userList);
        mv.addObject("articleList",articleList);
        mv.addObject("todayCount",todayCount);
        mv.addObject("allCount",allCount);

        mv.addObject("zoneId",zoneId);
        mv.setViewName("index");

        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if(user != null){
            UserInfo newUser = userInfoService.findUserByUsername(user.getUserName());

            Integer talkStatus = newUser.getTalkStatus();

             //0默认的未屏蔽发言
            if (talkStatus == 0){
                String userName = user.getUserName();
//                Date date = new Date();
                request.getSession().setAttribute("user",newUser);
                mv.addObject("msg","0");

            }

            if (talkStatus == 1){
                mv.addObject("msg","1");

                request.getSession().setAttribute("user",newUser);
            }
        }


        return mv;
    }

    /**
     * 关键词搜索 只搜索标题
     * @param zoneId
     * @param keyWord
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<Article> search(Integer zoneId, String keyWord) throws Exception {
        List<Article> articleList = articleService.loadByZoneIdAndWords(zoneId,keyWord);
        //敏感词过滤 状态为1为敏感词
        List<Word> words = wordService.findByStatus(1);
        filter(articleList,words);

        return articleList;

    }

    /**
     * 查询所有帖子
     * @param zoneId
     * @return
     * @throws Exception
     */
    @RequestMapping("/toggleTab")
    @ResponseBody
    public List<Article> toggleTab(Integer zoneId) throws Exception {
        List<Article> articleList = articleService.loadByZoneId(zoneId);
        //敏感词过滤 状态为1为敏感词
        List<Word> words = wordService.findByStatus(1);
        filter(articleList,words);

        return articleList;

    }

    /**
     * 敏感词过滤
     * @param articleList
     * @param words
     * @throws Exception
     */
    private void filter(List<Article> articleList, List<Word> words) throws Exception {
        for (Article article : articleList) {
            for (Word word : words) {
                //替换敏感词标题
                article.setTitle(article.getTitle().replaceAll(word.getWord(),"**"));
                //替换敏感词内容
                article.setContent(article.getContent().replaceAll(word.getWord(),"**"));
            }
        }
    }

}
