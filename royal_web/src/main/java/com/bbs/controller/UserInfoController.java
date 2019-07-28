package com.bbs.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.UserInfoService;
import com.bbs.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

  @Autowired
  private UserInfoService userInfoService;

  @Autowired
  private HttpServletRequest request;


  /**
   * 统计发帖数量
   *
   * @return
   * @throws Exception
   */
  @RequestMapping("/userMsg")
  public ModelAndView countArticleByUsername() throws Exception {
    ModelAndView mv = new ModelAndView();
    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
    Integer countNum = 0;
    if (userInfo != null) {
      countNum = userInfoService.CountArticleByUsername(userInfo.getUserName());
    }
    //查询当前用户权限升级的申请状态
    Integer isupdating = userInfoService.findIsupdating(userInfo);
    request.getSession().setAttribute("countNum", countNum);
    request.getSession().setAttribute("isupdating", isupdating);
    mv.setViewName("userInfo");
    return mv;
  }

  /**
   * 更改用户申请升级状态
   *
   * @param request
   * @param response
   * @throws Exception
   */
  @RequestMapping("/changeIsUpdating")
  public void changeIsUpdating(HttpServletRequest request, HttpServletResponse response) throws Exception {
    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
    Integer isupdating = userInfo.getIsupdating();
    Integer userId = userInfo.getUserId();
    userInfoService.changeIsUpdating(isupdating, userId);
  }

  /**
   * 查询用户当前申请状态
   *
   * @return
   * @throws Exception
   */
  @RequestMapping("/findIsupdating")
  public ModelAndView findIsupdating() throws Exception {
    ModelAndView mv = new ModelAndView();
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");
    Map map = new HashMap();
    Integer isupdating = userInfoService.findIsupdating(user);

    return mv;
  }


  /**
   * 用户注册
   *
   * @param userInfo
   * @return
   * @throws Exception
   */
  @RequestMapping("/wuxinghui.do")
  public String userRegister(UserInfo userInfo) throws Exception {

    //注册
    userInfoService.userRegister(userInfo);
    //登录
    UserInfo user = userInfoService.userLogin(userInfo);
    //存入Session域中
    request.getSession().setAttribute("user", user);
    //更新用户登录状态码:1
    this.update_loginStatusY();


    return "success";
  }


  /**
   * 用户名校验
   *
   * @param userName
   * @return
   * @throws Exception
   */
  @RequestMapping("/userNameRegister.do")
  @ResponseBody
  public Map userNameRegister(String userName) throws Exception {
    UserInfo userInfo = userInfoService.userNameRegister(userName);
    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfo != null
    ) {
      //存在
      map.put("userExsit", true);
      map.put("msg", "账户已被注册");
    } else {
      //不存在
      map.put("userExsit", false);
      map.put("msg", "账户可以使用");
    }

    return map;
  }


  /**
   * 账密校验
   *
   * @param userInfo
   * @return
   * @throws Exception
   */
  @RequestMapping("/wu.do")
  @ResponseBody
  public Map xingHui(UserInfo userInfo) throws Exception {
    userInfo = userInfoService.wuXing(userInfo);
    Map<String, Object> map = new HashMap<String, Object>();
    if (userInfo != null
    ) {
      //存在
      map.put("userExsit", true);
      map.put("msg", "账户不存在");
    } else {
      //不存在
      map.put("userExsit", false);
      map.put("msg", "账户可以使用");
    }

    return map;
  }


  /**
   * 游客登录
   */

  @RequestMapping("/login.do")
  public String userLogin(UserInfo userInfo) throws Exception {
    ModelAndView mv = new ModelAndView();
    UserInfo user = userInfoService.userLogin(userInfo);
    //判断用户是否存在
    //存在登录状态激活并携带参数跳转值主页
    if (user.getUserName() != null) {
      request.getSession().setAttribute("user", user);
      update_loginStatusY();//更新用户登录状态码:1

    } else {
      request.getSession().setAttribute("user", null);

    }
    String referer = request.getHeader("Referer");

    return "redirect:" + referer;
  }

  /**
   * 游客退出
   */
  @RequestMapping("/Quit.do")
  public String userQuit() throws Exception {
    this.update_loginStatusN();
    request.getSession().removeAttribute("user");

    return "redirect:/index/show";
  }

  /**
   * 判断图片格式
   */
  @RequestMapping("/tuPian.do")
  @ResponseBody
  public Map tupianYY(String tupian1Id) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (tupian1Id.equals("jpg") || tupian1Id.equals("gif") || tupian1Id.equals("png")) {
      //图片格式正确
      map.put("zt", true);
      map.put("xs", "图片格式正确！");
    } else {
      //图片格式错误
      map.put("zt", false);
      map.put("xs", "图片格式错误！");
    }


    return map;
  }

  /**
   * 修改邮箱和头像 上传
   */

  @RequestMapping("/xiuGai1.do")
  public String xiuGai1(HttpServletRequest request, @RequestParam("picUrl") MultipartFile picUrl, @RequestParam("email") String email) throws Exception {
    //获取session域中当前角色的对象
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");
    Integer userId = user.getUserId();
    //获取路径名称字符串
    String filename = picUrl.getOriginalFilename();
    if (filename != null && filename.length() > 0) {
      //上传位置
      String path = request.getSession().getServletContext().getRealPath("/uploads/");//上传位置
      File file = new File(path);
      if (!file.exists()) {
        //创建该文件夹
        file.mkdirs();
      }

      //文件名称设置唯一
      String uuid = UUID.randomUUID().toString().replace("-", "");
      filename = uuid + filename;
      //完成文件上传
      picUrl.transferTo(new File(path, filename));

      //数据库写入文件地址
      //images/touxiang/xxxxxx.jpg
      filename = "uploads/" + filename;
      userInfoService.tuPianXG(userId, filename);
      user.setPicUrl(filename);
      request.getSession().setAttribute("user", user);
    }


    if (email != null && email.length() > 0) {
      //保存图片
      userInfoService.emailXG(userId, email);

    }

    UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
    Integer countNum = 0;
    if (userInfo != null) {
      countNum = userInfoService.CountArticleByUsername(userInfo.getUserName());
    }
    Integer isupdating = userInfoService.findIsupdating(userInfo);
    request.getSession().setAttribute("countNum", countNum);
    request.getSession().setAttribute("isupdating", isupdating);

    return "userInfo";
  }

  /**
   * 判断密码
   */
  @RequestMapping("passPD.do")
  @ResponseBody
  public Map passPD(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) throws Exception {
    Map<String, Object> map1 = new HashMap<String, Object>();
    //获取session域中当前角色的ID
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");//获取角色对象
    Integer userId = user.getUserId();//获取角色ID
    //验证旧密码 find_oldPassword
    UserInfo userInfo = userInfoService.find_oldPassword(oldPassword, userId);
    if (userInfo != null) {
      //密码正确替换成新密码
      userInfoService.update_Password(newPassword, userId);
      map1.put("zt1", true);

    } else {
      //密码错误
      map1.put("zt1", false);

    }
    return map1;
  }

  /**
   * 更新状态已登录
   */
  public void update_loginStatusY() {
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");
    Integer userId = user.getUserId();
    userInfoService.update_loginStatusY(DateUtils.date2String(new Date(), "yyyy-MM-dd hh:m:ss"), userId);
  }

  /**
   * 更新状态未登录
   */

  public void update_loginStatusN() {
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");
    Integer userId = user.getUserId();
    userInfoService.update_loginStatusN(userId);

  }
}
















































