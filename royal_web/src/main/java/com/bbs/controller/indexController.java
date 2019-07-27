package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.User;
import com.bbs.domain.Zone;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/index")
public class indexController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/show")
    public ModelAndView show() throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        return mv;
    }
}
