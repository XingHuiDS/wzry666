package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
@Controller
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        User usr = userDao.userLogin(user);
        return usr;
    }

    @Override
    public void userRegister(User user) throws Exception {
        //user.setPicUrl("images/default.png");
        userDao.userRegister(user);
    }

    @Override
    public User checkUserNameRegister(String userName) {
        User userInfo = userDao.checkUserNameRegister(userName);
        return userInfo;
    }
}
