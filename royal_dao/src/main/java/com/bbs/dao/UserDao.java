package com.bbs.dao;

import com.bbs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    @Select("select * from`bbs_user_table`WHERE userName=#{userName} and userPass=#{userPass}")
    User userLogin(User userInfo);

    /**
     * 用户注册
     * @param user
     * @return
     * @throws Exception
     */
    @Insert("insert into bbs_user_table(userName,userPass,email) values(#{userName},#{userPass},#{email})")
    void userRegister(User user);

    /**
     * 注册用户名校验
     * @param userName
     * @return
     */
    @Select("select * from `bbs_user_table` where userName=#{userName};")
    User checkUserNameRegister(String userName);
}
