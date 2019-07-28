package com.bbs.manage.controller;


import com.bbs.common.CommonCode;
import com.bbs.common.ResponseResult;
import com.bbs.domain.UserInfo;
import com.bbs.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userService;

    /**
     * 登录操作，验证成功或失败
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public ResponseResult manageLogin(@RequestBody UserInfo user) {
        UserInfo loginUser = userService.manageLogin(user);
        if (loginUser == null) {
            //验证失败
            return new ResponseResult(CommonCode.LOGINFAIL);
        }
        //验证成功
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 用户信息查询（分页）
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByPage.do")
    public String findByPage(Model model,@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pageNum, UserInfo user) throws Exception{
        try {
            if (user.getUserName() != null) {
                user.setUserName(new String(user.getUserName().getBytes("ISO-8859-1"), "utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pageNum,6);
        List<UserInfo> userList = userService.findByPage(user);
        System.out.println(user.getUserName());
        PageInfo pageInfo = new PageInfo(userList,6);
        model.addAttribute("userMsgs",pageInfo);
        model.addAttribute("userSearch",user);
        return "/UserPage";
    }

    /**
     * 用户升级操作
     * @param id
     * @param pageNum
     * @param user
     * @param flag
     * @return
     */
    @RequestMapping("/changeApplyStatus.do")
    public String changeApplyStatus(@RequestParam("id") Integer id, @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pageNum, UserInfo user, Integer flag) throws Exception{
        if (flag==0){
            //升级
            userService.changeApply(id);
        }else if (flag==1){
            //驳回
            userService.changeApplyAndIsupdating(id);
        }
        String username = "";
        if (user.getUserName() != null) {
            username = user.getUserName();
        }
        if (user.getRole() != null) {
            return "redirect:findByPage.do?username=" + username + "&pn=" + pageNum + "&role=" + user.getRole();
        } else {
            return "redirect:findByPage.do?username=" + username + "&pn=" + pageNum + "&role=";
        }
    }

    /**
     * 用户禁言操作
     * @param id
     * @param pageNum
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/changeTalkStatus.do")
    public String changeTalkStatus(@RequestParam("id") Integer id, @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pageNum, UserInfo user) throws Exception{
        userService.changeTalkStatus(id);
        String username = "";
        if (user.getUserName() != null) {
            username = user.getUserName();
        }
        if (user.getRole() != null) {
            return "redirect:/user/findByPage.do?username=" + username + "&pn=" + pageNum + "&role=" + user.getRole();
        } else {
            return "redirect:/user/findByPage.do?username=" + username + "&pn=" + pageNum + "&role=";
        }
    }

}
