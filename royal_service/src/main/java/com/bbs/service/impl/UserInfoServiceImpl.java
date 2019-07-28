package com.bbs.service.impl;

import com.bbs.dao.UserInfoDao;
import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<Article> findAll() throws Exception {
        return null;
    }

    @Override
    public Article findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public Integer CountArticleByUsername(String username) throws Exception {
        System.out.println("service" + username);
        Integer i = userInfoDao.CountArticleByUsername(username);
        System.out.println(i);
        return i;
    }

    @Override
    public void changeIsUpdating(int isupdating, Integer userId) throws Exception {
        if (isupdating != 1) {
            isupdating = 1;
        }
        userInfoDao.changeIsUpdating(isupdating, userId);
    }


    @Override
    public List<UserInfo> loadAllLogined(String loginStatus) throws Exception {
        return userInfoDao.findAllLogined(loginStatus);
    }

    /**
     * 后台管理员登录
     *
     * @param user
     * @return
     */
    @Override
    public UserInfo manageLogin(UserInfo user) {
        UserInfo u = userInfoDao.login(user);
        if (u == null) {
            return null;
        }
        Integer role = u.getRole();
        if (role != 3) {
            return null;
        }
        return u;
    }

    /**
     * 用户注册
     * @param userInfo
     * @throws Exception
     */
    @Override
    public void userRegister(UserInfo userInfo) throws Exception {
        userInfo.setPicUrl("images/default.png");
        userInfoDao.userRegister(userInfo);

    }

    /**
     * 用户名校验
     * @param userName
     * @return
     */
    @Override
    public UserInfo userNameRegister(String userName) {

        UserInfo userInfo = userInfoDao.userNameRegister(userName);
        return userInfo;
    }

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    @Override
    public UserInfo userLogin(UserInfo userInfo) {
        userInfo = userInfoDao.userLogin(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo wuXing(UserInfo userInfo) {
        userInfo = userInfoDao.wuXing(userInfo);
        return userInfo;
    }


    /**
     * 用户信息查询（分页）
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findByPage(UserInfo user) throws Exception {
        return userInfoDao.findByPage(user);
    }

    /**
     * 用户升级操作
     * @param userId
     */
    @Override
    public void changeApply(Integer userId) {
        UserInfo user = userInfoDao.selectByUserId(userId);
        user.setUpdateStatus(1);
        user.setRole(2);
        userInfoDao.updateByUserId(user);
    }

    /**
     * 用户驳回操作
     * @param userId
     */
    @Override
    public void changeApplyAndIsupdating(Integer userId) {
        UserInfo user = userInfoDao.selectByUserId(userId);
        user.setIsupdating(0);
        userInfoDao.updateByUserId(user);
    }

    /**
     * 用户禁言操作
     * @param userId
     */
    @Override
    public void changeTalkStatus(Integer userId) {
        UserInfo user = userInfoDao.selectByUserId(userId);
        if (user.getTalkStatus() == 0) {
            user.setTalkStatus(1);
        } else if (user.getTalkStatus() == 1) {
            user.setTalkStatus(0);
        }
        userInfoDao.updateByUserId(user);
    }

    /**
     * 通过用户名修改头像路径
     * @param userId
     * @param filename
     */
    @Override
    public void tuPianXG(Integer userId, String filename) {
        userInfoDao.tuPianXG(userId, filename);

    }

    @Override
    public void emailXG(Integer userId, String email) {
        userInfoDao.emailXG(userId, email);
    }

    @Override
    public Integer findIsupdating(UserInfo user) {
        return userInfoDao.findIsupdating(user);
    }

    @Override
    public UserInfo find_oldPassword(String oldPassword, Integer userId) {
        return userInfoDao.find_oldPassword(oldPassword, userId);
    }

    @Override
    public void update_Password(String newPassword, Integer userId) {
        userInfoDao.update_Password(newPassword, userId);
    }

    /**
     * 更新状态未登录
     * @param userId
     */
    @Override
    public void update_loginStatusN(Integer userId) {
        userInfoDao.update_loginStatusN(userId);
    }

    /**
     * 更新状态已登录
     *
     * @param lastLoginTime
     */
    @Override
    public void update_loginStatusY(String lastLoginTime,Integer userId) {
        userInfoDao.update_loginStatusY(lastLoginTime,userId);
    }

    @Override
    public UserInfo findUserByUsername(String username) {
        return userInfoDao.findUserByUsername(username);
    }

    @Override
    public List<UserInfo> findAllUsers() {
        return userInfoDao.findAllUsers();
    }


}
