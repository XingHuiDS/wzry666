<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛注册页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script>

        /*需检查   开始:用户名校验*/
        function fcheckUserNameRegister() {
            var checkUserNameRegister = $("#checkUserNameRegister").val();//获取userName文本输入框的值
            var xianshi = $("#userNameTips");                   //用户名是否可用显示
            var zhuangtai = true;                               //用户名是否可用状态
            if (checkUserNameRegister.length == 0) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("账户不能为空");
            } else if (!/^[a-zA-Z0-9_]{6,10}$/.test(checkUserNameRegister)) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("账户长度必须6~10位的英文、数字、下划线组成");
            } else {
                $.post("${pageContext.request.contextPath}/user/checkUserNameRegister.do", {"userName": checkUserNameRegister}, function (data) {
                    if (data.userExist) {
                        zhuangtai = false;
                        //用户名不能使用
                        xianshi.css("color", "red");
                        xianshi.html(data.msg);
                    } else {
                        //用户名不能可以
                        xianshi.css("color", "green");
                        xianshi.html(data.msg);
                    }
                }, "json");
            }
            return zhuangtai;
        } /*结束:用户名校验*/
        /*需检查   开始:密码校验*/
        function fuserPassRegister() {
            var userPassRegister = $("#userPassRegister").val();//获取userPass文本输入框的值
            var xianshi = $("#userPassTips");                   //密码是否可用显示
            var zhuangtai = true;                               //密码是否可用状态
            if (userPassRegister.length == 0) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("密码不能为空");
            } else if (!/^[a-zA-Z0-9_]{6,10}$/.test(userPassRegister)) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("密码长度必须6~10位的英文、数字、下划线组成");
            } else {
                zhuangtai = true;
                xianshi.css("color", "green");
                xianshi.html("密码可以使用");
            }
            return zhuangtai;
        }/*结束:密码校验*/

        /*需检查   开始:邮箱校验*/
        function femailRegister() {
            var emailRegister = $("#emailRegister").val();//获取email文本输入框的值
            var xianshi = $("#emailTips");                   //邮箱是否可用显示
            var zhuangtai = true;                               //邮箱是否可用状态
            if (emailRegister.length == 0) {
                zhuangtai = true;
                /* xianshi.css("color", "red");
                 xianshi.html("邮箱不能为空");*/
            } else if (!/^\w+@[a-z0-9]+\.[a-z]{2,4}$/.test(emailRegister)) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("邮箱格式错误");
            } else {
                zhuangtai = true;
                xianshi.css("color", "green");
                xianshi.html("邮箱可以使用");
            }
            return zhuangtai;

        }/*结束:邮箱校验*/



        /*需检查   开始:登录校验*/
        $(function () {
            $("#fsubmit").click(function () {
                if (fcheckUserNameRegister() && fuserPassRegister() && femailRegister()) {
                    $("#regform").submit();
                } else if (!fcheckUserNameRegister()) {
                    alert("注册失败:账户不可用")
                } else if (!fuserPassRegister()) {
                    alert("注册失败:密码不可用")
                } else if (!femailRegister()) {
                    alert("注册失败:邮箱不可用")
                }
            })

        })/*结束:登录校验*/






    </script>

</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="/images/logo.png" height="64" width="168" alt=""/></a>
            </h1>
            <div class="search-box l">

            </div>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href=${pageContext.request.contextPath}"/index/show">首页</a><span>></span>注册页面

        </div>
    </div>
</div>


<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="reg-box">
            <h2>用户注册<span>（红色型号代表必填）</span></h2>
            <div class="reg-info">
                <form id="regform" action="/user/register.do" method="post">
                    <ul>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 用户名：<br>

                            </div>
                            <div class="reg-c">
                                <input type="text" id="checkUserNameRegister" name="userName" class="txt"
                                       onblur="fcheckUserNameRegister()"/>
                            </div>
                            <span class="tips" id="userNameTips">账户长度必须6~10位的英文、数字、下划线组成</span>
                        </li>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 密&nbsp;&nbsp;&nbsp;码：
                            </div>
                            <div class="reg-c">
                                <input type="password" id="userPassRegister" name="userPass" class="txt"
                                       onblur="fuserPassRegister()"/>
                            </div>
                            <span class="tips" id="userPassTips">密码长度必须6~10位的英文、数字、下划线组成</span>
                        </li>
                        <li class="no-tips">
                            <div class="reg-l">&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;箱：</div>
                            <div class="reg-c">
                                <input type="text" id="emailRegister" name="email" class="txt" value=""
                                       onblur="femailRegister()"/>

                            </div>
                            <span class="tips" id="emailTips"></span>
                        </li>
                        <li>
                            <div class="reg-l"></div>
                            <div class="reg-c">
                                <input id="fsubmit" type="button" class="submit-btn" value="注册"/><br/>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>


</body>


</html>