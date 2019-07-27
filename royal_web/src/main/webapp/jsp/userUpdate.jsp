<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_info.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
    <style type="text/css">
        .hm-header-b { border-bottom: 1px solid #d9d9d9; }
    </style>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp" />



<!--头部信息-->
<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="images/logo.png" alt=""/></a>
            </h1>

        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="/index.jsp" >首页</a><span>></span>申请高级用户
        </div>
    </div>
</div>


<!--权限升级-->
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="user-info clearfix">
            <div class="user-info-t" style="height:20px;"></div>
            <div class="user-info-l l">
                <div class="user-info-l-t">
                    <img src="images/default.png" alt=""/>
                    <div class="username">${pageContext.request.getSession(true).getAttribute("user").userName}</div>
                </div>
                <ul class="user-info-l-b">
                    <li><i class="info-icon"></i>我的资料</li>
                    <li class="cur"><i class="safe-icon"></i>申请高级用户</li>
                </ul>
            </div>


            <div class="user-info-r r">
                <ul class="clearfix hd">
                    <li><a href="getUser.do?method=userInfo">个人信息</a></li>
                    <li class="cur"><a href="getUser.do?method=userPwd">申请高级用户</a></li>
                </ul>
                <form action="#" method="post">
                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l"><i class="red">高级特权：</i></div>
                            <div class="info-r">开辟新版块</div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"><i class="red">申请条件：</i></div>
                            <div class="info-1"><i>发帖数≥5</i></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"><i class="red">*</i>当前发帖数：</div>
                            <div id="count" class="info-1">${countNum}</div>
                        </li>
                        <li class="clearfix">
                            <div class="info-r">
                                <input id="update" type="button" class="btn" value="申请" />
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
<script>

$(function () {
    $('#update').click(function () {
        var count = ${countNum};
        var isupdating = ${isupdating};
        if (isupdating === 1) {
            alert("请勿重复申请");
        }else {

            if (count< 5){
                alert("发帖数量不足");
            } else {
                $.ajax({
                    "url":"${pageContext.request.contextPath}/userInfo/changeIsUpdating",
                    'type':"post",
                    "success":function (data) {
                        alert("申请成功，请耐心等待审批");
                        location.reload();
                    }
                })
            }
        }

        
    })
})


</script>
<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>


</body>
</html>