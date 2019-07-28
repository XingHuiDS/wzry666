<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛首页</title>
    <link rel="stylesheet" href="../css/common.css"/>
    <link rel="stylesheet" href="../css/common-new.css"/>
    <link rel="stylesheet" href="../css/user_info.css"/>
    <link rel="stylesheet" href="../css/search.css"/>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/hm-bbs.js"></script>
    <style type="text/css">
        .hm-header-b {
            border-bottom: 1px solid #d9d9d9;
        }
    </style>

    <script>
        /*/提交校验*/
        /*开始:旧密码校验*/
        function oldPasswordF() {
            var oldPasswordID = $("#oldPasswordID").val();         //获取文本输入框的值
            var xianshi = $("#oldPasswordFspan");                   //密码是否可用显示
            var zhuangtai = false;                                 //密码是否可用状态
            if (oldPasswordID.length == 0) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("&nbsp;&nbsp;请输入旧密码");
            } else {
                zhuangtai = true;
                xianshi.css("color", "green");
                xianshi.html("&nbsp;&nbsp;✔");
            }
            return zhuangtai;
        }

        /*结束:旧密码校验*/



        /*开始:新密码校验*/
        function newPasswordF() {
            var newPasswordID = $("#newPasswordID").val();   //获取文本输入框的值
            var xianshi = $("#newPasswordFspan");                   //密码是否可用显示
            var zhuangtai = false;                               //密码是否可用状态
            if (newPasswordID.length == 0) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("&nbsp;&nbsp;请输入新密码");
            } else if (!/^[a-zA-Z0-9_]{6,10}$/.test(newPasswordID)) {
                zhuangtai = false;
                xianshi.css("color", "red");
                xianshi.html("&nbsp;&nbsp;密码长度必须6~10位的英文、数字、下划线组成");
            } else {
                zhuangtai = true;
                xianshi.css("color", "green");
                xianshi.html("&nbsp;&nbsp;✔");
            }
            return zhuangtai;
        }

        /*结束:新密码校验*/
        $(function () {
            $("#BC1Input").click(function () {
                    if (oldPasswordF() && newPasswordF()) {
                    var oldPassword = $("#oldPasswordID").val();
                    var newPassword = $("#newPasswordID").val();
                    $.post(${pageContext.request.contextPath}"/user/passPD.do", {
                        "oldPassword": oldPassword,
                        "newPassword": newPassword
                    }, function (date) {
                        if (date.zt1) {
                            alert("密码修改成功")
                            location.href='${pageContext.request.contextPath}/user/Exit.do';
                        } else {
                            alert("旧密码错误")
                        }
                    }, "json");
                } else {
                    alert("密码未填写完整")
                }
            })

        })
    </script>
</head>
<body>

<!-- 头部 -->
<%@ include file="/common/header.jsp" %>


<%--
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l">
        </div>
        <div class="hm-inner-r r">
            <div class="box">
                欢迎<a href="user_info.html" style="margin-right:0px;padding:0px 5px;color:blue;">scott</a>回来！
                <a href="#">【注销】</a>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="../images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form action="" method="post">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName"
                                           class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password"
                                                            id="userPass" name="userPass" class="ipt"/></li>
                            <li><input type="submit" value="登录" class="submitBtn"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

--%>


<!--头部信息-->
<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="../images/logo.png" alt=""/></a>
            </h1>
            <%--  <div class="search-box l">
                  <form action="javascript:;">
                      <input type="text" class="txt l" placeholder="请输入关键字">
                      <input type="button" value="搜索" class="btn l"/>
                  </form>
              </div>--%>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="#">首页</a><span>></span>修改密码
        </div>
    </div>
</div>


<!--修改密码-->
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="user-info clearfix">
            <div class="user-info-t" style="height:20px;"></div>
            <div class="user-info-l l">
                <div class="user-info-l-t">
                    <img src='../${sessionScope.user.picUrl}' alt=""/>
                    <div class="username">张无忌</div>
                </div>
                <ul class="user-info-l-b">
                    <li><i class="info-icon"></i>我的资料</li>
                    <li class="cur"><i class="safe-icon"></i>修改密码</li>
                    <c:if test="${user.role == 1}">
                        <li><i class="safe-icon"></i>申请高级用户</li>
                    </c:if>
                    <c:if test="${user.role == 2}">
                        <li><i class="safe-icon"></i>开辟新板块</li>
                    </c:if>

                </ul>
            </div>


            <div class="user-info-r r">
                <ul class="clearfix hd">
                    <li><a href="userInfo.jsp">个人信息</a></li>
                    <li class="cur"><a href="#">修改密码</a></li>
                    <c:if test="${user.role == 1}">
                        <li><a href="#">申请高级用户</a></li>
                    </c:if>
                    <c:if test="${user.role == 2}">
                        <li><a href="#">开辟新板块</a></li>
                    </c:if>

                </ul>


                <form id="form_1" action=${pageContext.request.contextPath}"/user/passPD.do" method="post"
                      >
                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l"><i class="red">*</i>旧密码：</div>
                            <div class="info-r"><input type="password" name="oldPassword" class="txt" id="oldPasswordID"
                                                       onblur="oldPasswordF()"/><span
                                    id="oldPasswordFspan"></span></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"><i class="red">*</i>新密码：</div>
                            <div class="info-r"><input type="password" name="newPassword" class="txt"
                                                       onblur="newPasswordF()" id="newPasswordID"/><span
                                    id="newPasswordFspan"></span></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"></div>
                            <div class="info-r">
                                <input type="button" class="btn" value="保存" id="BC1Input"/>
                                <span style="color:red;" id="BC1Span"></span>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 底部 -->
<div class="hm-footer" style="padding-top:10px;">
    <div class="hm-inner">
        <div class="hm-footer-cpr">
            <p>Copyright@2006-2017 ITCAST. All Rights Reserved</p>
            <p>传智播客 版权所有</p>
        </div>
    </div>
</div>
</body>
</html>