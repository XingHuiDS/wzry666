<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
    <script>
        /*开始:校验账号密码是否正确*/
        $(function () {
            $("#wuxinghui").click(function () {
                var userName = $("#userName").val();//获取输入框中的值
                var userPass = $("#userPass").val();//获取输入框中的值


                $.post("${pageContext.request.contextPath}/userInfo/wu.do", {
                    "userName": userName,
                    "userPass": userPass
                }, function (data) {

                    if (data.userExsit) {
                        alert("登录成功")
                        $("#form").submit();
                    } else {
                        alert("账户或密码错误")
                    }
                }, "json");
            })

        })
        /*结束:校验账号密码是否正确*/
    </script>

</head>
<body>
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l"></div>
        <div class="hm-inner-r r">
            <div class="box">
                <c:if test="${sessionScope.user != null}">
                    <%--欢迎++用户名--%>
                    <a  id="login1"
                       class="to-login">欢迎 ${sessionScope.user.roleString} ${sessionScope.user.userName}</a>
                    <%--个人中心--%>
                    <a href=${pageContext.request.contextPath}"/userInfo/userMsg" id="login2"
                       class="to-login">个人中心</a>
                    <%--登出--%>
                    <a href="/userInfo/Quit.do" id="login3"
                       class="to-login">注销</a>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <a href="javascript:;" id="login" class="to-login">游客登录</a>
                    <a href=${pageContext.request.contextPath}"/jsp/register.jsp">【新用户注册】</a>

                </c:if>

                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="${pageContext.request.contextPath}/images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form id="form" action="${pageContext.request.contextPath}/userInfo/login.do" method="post">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/>
                            </li>
                            <li><input type="button" value="登录" class="submitBtn" id="wuxinghui"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    function ala(ele) {
        //显示弹框
        var className = $(ele).attr('class');
        $('#dialogBg').fadeIn(300);
        $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
        $('#userName').focus();
        $("#j_fixedBar").hide();

    }

    $(function () {
        $('.box #login').click(function () {
            ala(this);
        });


        //关闭弹窗
        $('.closeDialogBtn').click(function () {
            $('#dialogBg').fadeOut(300, function () {
                $('#dialog').addClass('bounceOutUp').fadeOut();
                $("#j_fixedBar").show();
            });
        });
    });
</script>
</html>