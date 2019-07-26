package com.bbs.controller;

import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String userRegist(User user){
        //设置注册的信息
        user.setUserName(user.getUserName());
        user.setUserPass(user.getUserPass());
        //
        if(StringUtils.isEmpty(user.getEmail())){
            user.setEmail(null);
        }else {
            user.setEmail(user.getEmail());
        }
        userService.userRegist(user);
        return null;
    }
}
