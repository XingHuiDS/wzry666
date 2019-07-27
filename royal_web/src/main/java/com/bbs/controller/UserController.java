package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/findIndex.do")
    public String index(){
        return "index";
    }


    @RequestMapping("/login.do")
    public String userLogin(User user){
        ModelAndView mv = new ModelAndView();
        User usr = userService.userLogin(user);
        if(usr.getUserName() != null){
            request.getSession().setAttribute("user",usr);
            System.out.println("成功");
            return "redirect:/jsp/userInfo.jsp";
        }else {
            request.getSession().setAttribute("user",null);
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
