package com.bbs.service.impl;

import com.bbs.dao.UserDao;
import com.bbs.domain.User;
import com.bbs.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao Dao;


    @Override
    public void tuPianXG(Integer userId, String filename) {
        Dao.tuPianXG(userId, filename);
    }

    @Override
    public void emailXG(Integer userId, String email) {
        Dao.emailXG(userId, email);
    }

    @Override
    public Integer CountArticleByUsername(String username) throws Exception {
        System.out.println("service" + username);
        Integer i = Dao.CountArticleByUsername(username);
        System.out.println(i);
        return i;
    }

    @Override
    public Integer findIsupdating(User user) {
        return Dao.findIsupdating(user);
    }

    @Override
    public User zhangMiJY(User userInfo) {
        userInfo = Dao.zhangMiJY(userInfo);
        return userInfo;
    }

    @Override
    public void changeIsUpdating(Integer isupdating, Integer userId) {
        if (isupdating != 1) {
            isupdating = 1;
        }
       Dao.changeIsUpdating(isupdating, userId);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        User usr = Dao.userLogin(user);
        return usr;
    }

    @Override
    public void userRegister(User user) throws Exception {

       Dao.userRegister(user);
    }

    @Override
    public User checkUserNameRegister(String userName) {
        User userInfo =Dao.checkUserNameRegister(userName);
        return userInfo;
    }

    @Override
    public User find_oldPassword(String oldPassword, Integer userId) {
        return Dao.find_oldPassword(oldPassword, userId);
    }
    @Override
    public void update_Password(String newPassword, Integer userId) {
      Dao.update_Password(newPassword, userId);
    }
}

