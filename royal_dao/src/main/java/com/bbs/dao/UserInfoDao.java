package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户信息dao
 *
 * @return
 * @throws
 */
public interface UserInfoDao {

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<Article> findAll() throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    Article findById(Integer id) throws Exception;


    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @Insert("insert into bbs_user_table(userName,userPass,email,picUrl) values(#{userName},#{userPass},#{email},#{picUrl})")
    void userRegister(UserInfo userInfo) throws Exception;

    /**
     * 用户名校验
     *
     * @param userName
     * @return
     */
    @Select("SELECT * FROM `bbs_user_table` WHERE userName=#{userName};")
    UserInfo userNameRegister(String userName);

    /**
     * 根据用户名查询发帖数量
          *
     * @param username
     * @return
     * @throws Exception
     */
    @Select("SELECT COUNT(1) FROM bbs_article_table WHERE senderName = #{username}")
    Integer CountArticleByUsername(String username) throws Exception;

    /**
     * 更改用户申请升级状态
     *
     * @param isupdating
     * @param userId
     * @throws Exception
     */
    @Update("UPDATE bbs_user_table SET isupdating = #{isupdating} WHERE userId = #{userId}")
    void changeIsUpdating(@Param("isupdating") Integer isupdating, @Param("userId") Integer userId) throws Exception;

    /**
     * 查询在线用户
     *
     * @param loginStatus
     * @return
     * @throws Exception
     */
    @Select("select * from bbs_user_table where loginStatus=#{loginStatus}")
    public List<UserInfo> findAllLogined(String loginStatus) throws Exception;

    /**
     * 管理员登录功能
     *
     * @param
     * @param
     * @return
     */
    @Select("Select * from bbs_user_table where userName = #{userName} and userPass= #{userPass}")
    UserInfo login(UserInfo user);


    /**
     * 用户登录
     *
     * @param userInfo
     * @return
     */
    @Select("SELECT * FROM `bbs_user_table`WHERE userName=#{userName} AND userPass=#{userPass}")
    UserInfo userLogin(UserInfo userInfo);

    /**
     * 账密校验
     *
     * @param userInfo
     * @return
     */
    @Select("SELECT * FROM `bbs_user_table`WHERE userName=#{userName} AND userPass=#{userPass}")
    UserInfo wuXing(UserInfo userInfo);

    /**
     * 用户信息查询
     * 方法：用script标签包围，然后像xml语法一样书写，实现动态sql
     *@Select("<script>"
     *             +"select * from mi_taobao where 1=1"
     *             +"<if test='status != null'>"
     *             +"and status = #{status}"
     *             +"</if>"
     *             +"</script>")
     * @return
     * @throws Exception
     */
    @Select("<script>"
            + "select * from bbs_user_table where 1=1 "
            + "<if test='userName!=null'>"
            + "and userName like CONCAT('%',#{userName},'%') "
            + "</if>"
            + "<if test='role != null'>"
            + "and role = #{role}"
            + "</if>"
            + "</script>")
    List<UserInfo> findByPage(UserInfo user) throws Exception;

    /**
     * 通过用户ID查询所有
     *
     * @param userId
     * @return
     */
    @Select("select * from bbs_user_table where userId = #{userId}")
    UserInfo selectByUserId(Integer userId);

    /**
     * 通过用户id更新用户信息
     *
     * @param user
     */
    @Update("update bbs_user_table set updateStatus = #{updateStatus}, role =#{role},isupdating = #{isupdating},talkStatus =#{talkStatus}  where userId=#{userId} ")
    void updateByUserId(UserInfo user);

    /**
     * 通过用户名修改头像路径
     *
     * @param userId
     * @param filename
     */
    @Update("UPDATE bbs_user_table SET picUrl = #{filename} WHERE userId = #{userId}")
    void tuPianXG(@Param("userId") Integer userId, @Param("filename") String filename);

    @Update("UPDATE bbs_user_table SET email = #{email} WHERE userId = #{userId}")
    void emailXG(@Param("userId") Integer userId, @Param("email") String email);

    /**
     * 查询当前用户权限升级的申请状态
     *
     * @param user
     * @return
     */
    @Select("SELECT isupdating FROM bbs_user_table WHERE userName = #{userName}")
    Integer findIsupdating(UserInfo user);

    /**
     * 验证旧密码
     *
     * @param oldPassword
     * @param userId
     * @return
     */
    @Select("SELECT * FROM bbs_user_table WHERE userpass=#{oldPassword}  AND USERID=#{userId}")
    UserInfo find_oldPassword(@Param("oldPassword") String oldPassword, @Param("userId") Integer userId);

    /**
     * 更新密码
     *
     * @param newPassword
     * @param userId
     */
    @Update("UPDATE bbs_user_table SET userpass =#{newPassword}   WHERE USERID =#{userId} ")
    void update_Password(@Param("newPassword") String newPassword, @Param("userId") Integer userId);

    /**
     * 更新登录状态:已登录
     */
    @Update("UPDATE bbs_user_table SET loginStatus=1,lastLoginTime=#{lastLoginTime} WHERE USERID = #{userId}")
    void update_loginStatusY(@Param("lastLoginTime") String lastLoginTimeStr, @Param("userId") Integer userId);

    /**
     * 更新登录状态:未登录
     */
    @Update("UPDATE bbs_user_table SET loginStatus = 0  WHERE USERID = #{userId}")
    void update_loginStatusN(Integer userId);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from bbs_user_table where userName = #{username}")
    UserInfo findUserByUsername(String username);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from bbs_user_table ")
    List<UserInfo> findAllUsers();
}
