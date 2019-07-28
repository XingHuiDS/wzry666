package com.bbs.service;

import com.bbs.domain.User;

public interface UserService {


    void tuPianXG(Integer userId, String filename);

    void emailXG(Integer userId, String email);

    Integer CountArticleByUsername(String userName) throws Exception;

    Integer findIsupdating(User userInfo);

    User zhangMiJY(User userInfo);

    void changeIsUpdating(Integer isupdating, Integer userId);
    /**
     * 用户登录
     * @param user
     * @return
     */
    User userLogin(User user);

    /**
     * 用户注册
     * @param user
     * @return
     * @throws Exception
     */
    void userRegister(User user) throws Exception;

    /**
     * 校验用户名是否已经存在
     * @param userName
     * @return
     */
    User checkUserNameRegister(String userName);

    User find_oldPassword(String oldPassword, Integer userId);

    void update_Password(String newPassword, Integer userId);
}
