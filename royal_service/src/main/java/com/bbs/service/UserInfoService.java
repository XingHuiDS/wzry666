package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    List<Article> findAll() throws Exception;

    /**
     * 根据ID查询帖子
     *
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;

    /**
     * 根据用户名查询发帖数量
     *
     * @param username
     * @return
     * @throws Exception
     */
    Integer CountArticleByUsername(String username) throws Exception;

    /**
     * 更改用户申请升级状态
     *
     * @param isupdating
     * @param userId
     * @throws Exception
     */
    void changeIsUpdating(int isupdating, Integer userId) throws Exception;

    /**
     * 查询在线用户
     *
     * @param loginStatus
     * @return
     * @throws Exception
     */
    public List<UserInfo> loadAllLogined(String loginStatus) throws Exception;

    /**
     * 后台管理员登录
     *
     * @param user
     * @return
     */
    UserInfo manageLogin(UserInfo user);


    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    void userRegister(UserInfo userInfo) throws Exception;

    /**
     * 用户名校验
     *
     * @param userName
     * @return
     */
    UserInfo userNameRegister(String userName);

    /**
     * 用户登录
     *
     * @param userInfo
     * @return
     */
    UserInfo userLogin(UserInfo userInfo);

    UserInfo wuXing(UserInfo userInfo);

    /**
     * 用户信息查询（分页）
     *
     * @param user
     * @return
     * @throws Exception
     */
    List<UserInfo> findByPage(UserInfo user) throws Exception;

    /**
     * 用户升级操作
     *
     * @param id
     */
    void changeApply(Integer id);

    /**
     * 用户驳回操作
     *
     * @param id
     */
    void changeApplyAndIsupdating(Integer id);

    /**
     * 用户禁言操作
     *
     * @param id
     */
    void changeTalkStatus(Integer id);


    /**
     * 通过名字查询UserInfo
     */
    void tuPianXG(Integer userId, String filename);

    /**
     * 修改邮箱
     *
     * @param userId
     * @param email
     */
    void emailXG(Integer userId, String email);

    /**
     * 查询当前用户权限升级的申请状态
     *
     * @param user
     * @return
     */
    Integer findIsupdating(UserInfo user);

    //查询验证旧密码
    UserInfo find_oldPassword(String oldPassword, Integer userId);

    //更新旧密码
    void update_Password(String newPassword, Integer userId);

    //更新状态未登录
    void update_loginStatusN(Integer userId);

    //更新状态以登录
    void update_loginStatusY(String lastLoginTime, Integer userId);

    /**
     * 根据username查询用户
     * @param username
     * @return
     */
    UserInfo findUserByUsername(String username);
}
