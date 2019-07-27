package com.bbs.service;

import com.bbs.domain.User;

public interface UserService {

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

}
