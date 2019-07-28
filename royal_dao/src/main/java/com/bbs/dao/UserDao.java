package com.bbs.dao;

import com.bbs.domain.User;

import org.apache.ibatis.annotations.*;

public interface UserDao {


    @Update("update bbs_user_table set email=#{email} where userName=#{userName}")
    String update(String username, String email);

    //修改图片
    @Update("UPDATE bbs_user_table SET picUrl = #{filename} WHERE userId = #{userId}")
    void tuPianXG(@Param("userId") Integer userId, @Param("filename") String filename);

    //修改邮箱
    @Update("UPDATE bbs_user_table SET email = #{email} WHERE userId = #{userId}")
    void emailXG(@Param("userId") Integer userId, @Param("email") String email);

    //根据用户名查询发帖数量
    @Select("SELECT COUNT(1) FROM bbs_article_table WHERE senderName = #{username}")
    Integer CountArticleByUsername(String username) throws Exception;

    // 查询当前用户权限升级的申请状态
    @Select("SELECT isupdating FROM bbs_user_table WHERE userName = #{userName}")
    Integer findIsupdating(User user);

    //验证账号密码(头部信息)
    @Select("SELECT * FROM `bbs_user_table`WHERE userName=#{userName} AND userPass=#{userPass}")
    User zhangMiJY(User userInfo);

    //更改用户升级状态
    @Update("UPDATE bbs_user_table SET isupdating = #{isupdating} WHERE userId = #{userId}")
    void changeIsUpdating(Integer isupdating, Integer userId);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Select("select * from`bbs_user_table`WHERE userName=#{userName} and userPass=#{userPass}")
    User userLogin(User user);

    /**
     * 用户注册
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Insert("insert into bbs_user_table(userName,userPass,email) values(#{userName},#{userPass},#{email})")
    void userRegister(User user);

    /**
     * 注册用户名校验
     *
     * @param userName
     * @return
     */
    @Select("select * from `bbs_user_table` where userName=#{userName};")
    User checkUserNameRegister(String userName);
    //验证旧密码

    @Select("SELECT * FROM bbs_user_table WHERE userpass=#{oldPassword}  AND USERID=#{userId}")
    User find_oldPassword(@Param("oldPassword") String oldPassword, @Param("userId") Integer userId);

    //更新新密码
    @Update("UPDATE bbs_user_table SET userpass =#{newPassword}   WHERE USERID =#{userId} ")
    void update_Password( @Param("newPassword")String newPassword, @Param("userId") Integer userId);
}
