package com.bbs.controller;
import com.bbs.domain.Article;
import com.bbs.domain.User;
import com.bbs.service.ArticleService;
import com.bbs.service.UserService;
import com.bbs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

   /*
   * 超
   * */
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/findIndex.do")
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView();
        String currentDate = DateUtils.date2String(new Date(), "yyyy-MM-dd");
        int todayCount = articleService.findTodayCount(currentDate);
        int count = articleService.findArticle();
        /*
        *
        * 回显今日发帖数和总发帖数
        * */
        mv.addObject("allCount", count);
        mv.addObject("todayCount", todayCount);
        /*
        * 回显点赞数和前端页面内容
        *
        * */
        List<Article> list = articleService.findUpvoteCount();
        mv.addObject("articleslist", list);
        mv.setViewName("index");
        return mv;



    }


    @RequestMapping("/login.do")
    public String userLogin(User user) {
        ModelAndView mv = new ModelAndView();
        User usr = userService.userLogin(user);
        if (usr.getUserName() != null) {
            request.getSession().setAttribute("user", usr);
            System.out.println("成功");
            return "redirect:/jsp/userInfo.jsp";
        } else {
            request.getSession().setAttribute("user", null);
            System.out.println("失败");
            return "index";
        }
    }

    @RequestMapping("/Exit.do")
    public String userExit() throws Exception {
        request.getSession().removeAttribute("user");
        return "index";
    }

    @RequestMapping("/register.do")
    public String userRegister(User user) throws Exception {
        userService.userRegister(user);//注册
        User usr = userService.userLogin(user);//登录
        request.getSession().setAttribute("user", usr);//存入Session域中
        return "success";
    }

    @RequestMapping("/checkUserNameRegister.do")
    @ResponseBody
    public Map userNameRegister(String userName) throws Exception {
        User userInfo = userService.checkUserNameRegister(userName);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userInfo != null
        ) {
            //存在
            map.put("userExist", true);
            map.put("msg", "用户名已被注册");
        } else {
            //不存在
            map.put("userExist", false);
            map.put("msg", "用户名可以使用");
        }
        return map;
    }


}
















