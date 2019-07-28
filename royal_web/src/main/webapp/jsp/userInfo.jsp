<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        .hm-header-b {
            border-bottom: 1px solid #d9d9d9;
        }
    </style>
    <script>
        $(function () {
            //绑定tab切换
            $("#rightUL li").click(function () {
                var i = $(this).index();//获取li的下标
                $("#rightUL li").removeClass("cur");
                $(this).addClass('cur');
                $('form').css('display', 'none');

                $("#leftUL li").removeClass("cur");

                if (i < 2) {
                    $('#form_' + i).css('display', 'block');
                } else if (i == 2) {
                    if (${user.role == 1}) {
                        $('#form_2').css('display', 'block');
                    } else {
                        $('#form_3').css('display', 'block');
                    }

                }
            });


            $("#applyBtn").click(function () {
                var zoneName =  $('#zoneName').val().trim();
                if(zoneName == ''){
                    alert('请输入板块名称');
                    return;
                }

                $('#zoneName').val(zoneName);
                $.ajax({
                    url: "${pageContext.request.contextPath}/zoneapply/save",
                    // contentType:"application/json;charset=UTF-8",
                    data: $("#form_3").serialize(),
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        if (data) {
                            alert('添加成功！');
                        }
                        location.href = '${pageContext.request.contextPath}/index/show';
                    }
                });

            });

        });
        /*开始:邮箱验证*/
        var emailZT1 = false; //邮箱是否可用状态
        function emailF() {
            var emailid = $("#emailId").val();                      //文本款邮箱内容
            var xianshi = $("#emailSpan");                       //邮箱是否可用显示
            if (emailid.length == 0) {
            } else if (!/^\w+@[a-z0-9]+\.[a-z]{2,4}$/.test(emailid)) {
                xianshi.css("color", "red");
                xianshi.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱格式错误");
            } else {
                emailZT1 = true;
                xianshi.css("color", "green");
                xianshi.html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱可以使用");
            }
            return emailZT1;
        }/*结束:邮箱验证*/



        /*验证图片后缀名*/
        var touxiangZT1 = false;//图片是否可用状态
        function tipianF() {
            var tupian1Id = $("#tupian1Id").val();
            var xianshi = $("#tupian1Span");                       //图片是否可用显示

            tupian1Id = tupian1Id.substring(tupian1Id.lastIndexOf(".") + 1);
            $.post("${pageContext.request.contextPath}/userInfo/tuPian.do", {"tupian1Id": tupian1Id}, function (data) {
                if (data.zt) {
                    touxiangZT1 = true;
                    //图片可以使用
                    xianshi.css("color", "green");
                    xianshi.html(data.xs);
                } else {
                    //图片不能使用
                    touxiangZT1 = false;
                    xianshi.css("color", "red");
                    xianshi.html(data.xs);
                }
            }, "json");
            return touxiangZT1;
        }

        /*/验证图片后缀名*/
        /*提交校验*/
        $(function () {
            $("#baocun1bc").click(function () {
                if (emailZT1 && touxiangZT1) {

                    $("#regform").submit();

                    alert("邮箱与头像修改成功")
                } else if (emailZT1) {
                    alert("邮箱修改成功")
                } else if (touxiangZT1) {
                    alert("头像修改成功")
                } else {
                    alert("没有修改数据")
                }
            })

        })
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
                xianshi.html("&nbsp;&nbsp;请输入新的密码");
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
                xianshi.html("&nbsp;&nbsp;密码长度必须6-10位的英文、数字、下划线组成");
            } else {
                zhuangtai = true;
                xianshi.css("color", "green");
                xianshi.html("&nbsp;&nbsp;此密码可以使用");
            }
            return zhuangtai;
        }

        /*结束:新密码校验*/


        /*开始:保存*/
        $(function () {
            $("#BC1Input").click(function () {
                if (oldPasswordF() && newPasswordF()) {

                    var oldPassword = $("#oldPasswordID").val();
                    var newPassword = $("#newPasswordID").val();
                    $.post(${pageContext.request.contextPath}"/userInfo/passPD.do", {
                        "oldPassword": oldPassword,
                        "newPassword": newPassword
                    }, function (date) {
                        if (date.zt1) {
                            alert("密码修改成功")
                            location.href='${pageContext.request.contextPath}/userInfo/Quit.do';
                        } else {
                            alert("旧密码错误，请重新输入")
                        }
                    }, "json");
                } else {
                    alert("请输入正确的格式")
                }
            })

        })
        /*结束:保存*/


        //个人权限升级申请
        $(function () {
            var count=${sessionScope.countNum};


                var isupdating = ${sessionScope.isupdating};
                $("#update").click(function () {
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
                                    alert("申请成功，请耐心等待");
                                    location.reload();
                                }
                            })
                        }
                    }
                })



            })


    </script>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<!--头部信息-->
<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="images/logo.png" height="64" width="168" alt=""/></a>
            </h1>

        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href=${pageContext.request.contextPath}"/index/show">首页</a><span>></span>个人信息
        </div>
    </div>
</div>


<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="user-info clearfix">
            <div class="user-info-t" style="height:20px;"></div>

            <!--左侧用户名，头像-->
            <div class="user-info-l l">
                <div class="user-info-l-t">
                    <img src='/${sessionScope.user.picUrl}'/>
                    <div class="username">${sessionScope.user.userName}</div>
                </div>
                <ul class="user-info-l-b" id="leftUL">
                    <li class="cur"><i class="info-icon"></i>我的资料</li>
                    <li><i class="safe-icon"></i>修改密码</li>
                    <li><i class="safe-icon"></i>申请高级用户</li>
                </ul>
            </div>


            <!--右侧用户信息-->
            <div class="user-info-r r">
                <ul class="clearfix hd" id="rightUL" style="border: 0px solid red;">
                    <li class="cur"><a href="#">个人信息</a></li>
                    <li><a href="#">修改密码</a></li>
                    <c:if test="${user.role == 1}">
                        <li><a href="#">申请高级用户</a></li>
                    </c:if>
                    <c:if test="${user.role == 2}">
                        <li><a href="#">开辟新板块</a></li>
                    </c:if>
                </ul>

                <form id="form_0" action=${pageContext.request.contextPath}"/userInfo/xiuGai1.do" method="post"
                      enctype="multipart/form-data">

                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l"><i class="red">*</i>用户名：</div>
                            <div class="info-r"><input type="text" class="txt" value="${sessionScope.user.userName}"
                                                       readonly="readonly"/></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l">邮箱地址：</div>
                            <div class="info-r"><input type="text" name="email" class="txt" value="${sessionScope.user.email}" id="emailId"
                                                       onblur="emailF()"/><span class="tips" id="emailSpan"></span>
                            </div>


                        </li>
                        <li class="clearfix">
                            <div class="info-l">上传头像：</div>
                            <div class="info-r"><input type="file" name="picUrl" class="file-btn" onchange="tipianF()"
                                                       id="tupian1Id"/>
                                <span class="tips" id="tupian1Span"></span></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"></div>
                            <div class="info-r">
                                <input type="submit" class="btn" value="保存" id="baocun1bc"/>
                                <span id="xiugaiZT"></span><%--提示修改状态--%>
                            </div>
                        </li>
                    </ul>
                </form>


                <form id="form_1" action=${pageContext.request.contextPath}"/userInfo/passPD.do" method="post"
                      style="display: none">
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


                <form id="form_2" action="#" method="post" style="display: none">
                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l"><i class="red">高级特权：</i></div>
                            <div class="info-1"><i>开辟新版块</i></div>
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

                <form id="form_3" action="#" method="post" enctype="multipart/form-data" style="display: none;">
                    <input name="userName" value="${user.userName}" type="hidden"/>
                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l">板块名称：</div>
                            <div class="info-r"><input type="text" id="zoneName" name="zoneName" class="txt" /></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l">申请理由：</div>
                            <div class="info-r">
                                <%--<input type="text" style="text-align:left;height:260px" name="email" class="txt" value="" placeholder="请在此输入申请理由"/>--%>
                                <textarea name="reason" id="" cols="20" rows="5" placeholder="请在此输入申请理由"
                                          style="border:1px solid #ddd;width:205px;"></textarea>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"></div>
                            <div class="info-r">
                                <%--<input type="submit" class="btn" value="申请">--%>
                                <input id="applyBtn" type="button" class="btn" value="申请">
                                <%--<span style="color:red;">修改成功！</span>--%>
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