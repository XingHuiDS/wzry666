package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.User;
import com.bbs.service.ArticleService;
import com.bbs.service.UserService;
import com.bbs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ArticleService articleService;


    //修改用户名密码头像
    @RequestMapping("/update.do")
    public String xiuGai1(HttpServletRequest request, @RequestParam("picUrl") MultipartFile picUrl, @RequestParam("email") String email) throws Exception {
        //获取session域中当前角色的ID
        User user = (User) request.getSession().getAttribute("user");//获取角色对象
        Integer userId = user.getUserId();///获取角色ID
        //获取路径名称字符串
        String filename = picUrl.getOriginalFilename();//得到上传时的文件名
        if (filename != null && filename.length() > 0) {
            //上传位置
            String path = request.getSession().getServletContext().getRealPath("/images/uploads/");//上传位置
            File file = new File(path);
            if (!file.exists()) {
                //判断路径是否存在,如果不存在,创建该文件夹
                file.mkdirs();
            }

            //文件名称设置唯一
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid + filename;
            //完成文件上传
            picUrl.transferTo(new File(path, filename));

            //数据库写入文件地址
            filename = "images/uploads/" + filename;
            userService.tuPianXG(userId, filename);
            user.setPicUrl(filename);
            request.getSession().setAttribute("user", user);
        }


        if (email != null && email.length() > 0) {
            userService.emailXG(userId, email);

        }

        User userInfo = (User) request.getSession().getAttribute("user");
        Integer countNum = 0;
        if (userInfo != null) {
            countNum = userService.CountArticleByUsername(userInfo.getUserName());
        }
        Integer isupdating = userService.findIsupdating(userInfo);
        request.getSession().setAttribute("countNum", countNum);
        request.getSession().setAttribute("isupdating", isupdating);


        return "userInfo";
    }

    @RequestMapping("/zhangMiJY.do")
    @ResponseBody
    public Map zhangMiJY(User userInfo) throws Exception {
        userInfo = userService.zhangMiJY(userInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userInfo != null
        ) {
            //存在zhangMiJY
            map.put("userExsit", true);
            map.put("msg", "账户不存在");
        } else {
            //不存在
            map.put("userExsit", false);
            map.put("msg", "账户可以使用");
        }

        return map;
    }

    @RequestMapping("/userMsg")
    public ModelAndView countArticleByUsername() throws Exception {
        ModelAndView mv = new ModelAndView();
        User userInfo = (User) request.getSession().getAttribute("user");
        Integer countNum = 0;
        if (userInfo != null) {
            countNum = userService.CountArticleByUsername(userInfo.getUserName());
        }
        Integer isupdating = userService.findIsupdating(userInfo);
        request.getSession().setAttribute("countNum", countNum);
        request.getSession().setAttribute("isupdating", isupdating);
        mv.setViewName("userInfo");
        return mv;
    }

    //用户升级申请
    @RequestMapping("/changeIsUpdating")
    public void changeIsUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userInfo = (User) request.getSession().getAttribute("user");
        Integer isupdating = userInfo.getIsupdating();
        Integer userId = userInfo.getUserId();
        userService.changeIsUpdating(isupdating, userId);
    }

    //首页数据回显
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
        System.out.println("图片:" + usr.getPicUrl());
        System.out.println("路径:" + usr.getRole());
        if (usr.getUserName() != null) {
            request.getSession().setAttribute("user", usr);
            System.out.println("成功");
            return "redirect:/jsp/index.jsp";
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

    @RequestMapping("passPD.do")
    @ResponseBody
    public Map passPD(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) throws Exception {
        Map<String, Object> map1 = new HashMap<String, Object>();
        //获取session域中当前角色的ID
        User user = (User) request.getSession().getAttribute("user");//获取角色对象
        Integer userId = user.getUserId();//获取角色ID
        //验证旧密码 find_oldPassword
        User userInfo = userService.find_oldPassword(oldPassword, userId);
        if (userInfo != null) {
            //密码正确替换成新密码
            userService.update_Password(newPassword, userId);
            map1.put("zt1", true);

        } else {
            //密码错误
            map1.put("zt1", false);

        }
        return map1;
    }
}

