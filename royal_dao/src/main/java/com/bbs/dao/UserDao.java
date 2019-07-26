package com.bbs.dao;

import com.bbs.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Insert("insert into bbs_user_table(userName,userPass,email) values(#{userName},#{userPass},#{email})")
    public void userRegister(User user);
}
