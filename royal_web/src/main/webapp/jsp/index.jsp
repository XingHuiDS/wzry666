<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛111</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index-new.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>

    <script>

        $(function(){
            $("#searchBtn").click(function(){
                var zoneId = $("#zoneId").val();
                var keyWord = $("#keyWord").val().trim();
                if(keyWord == ''){
                    alert('请输入关键字');
                    return;
                }
                $("#keyWord").val(keyWord);
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"${pageContext.request.contextPath}/index/search?date="+new Date(),
                    // contentType:"application/json;charset=UTF-8",
                    data:$("#searchForm").serialize(),
                    type:"post",
                    success:function(data){
                        showArticleList(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        // 状态码
                        console.log(XMLHttpRequest.status);
                        // 状态
                        console.log(XMLHttpRequest.readyState);
                        // 错误信息
                        console.log(textStatus);
                    }
                });

            });



            $("#navUL li").click(function(){
                var zoneId = $(this).attr("id");
                $("#zoneId").val(zoneId);

                $("#article_zoneId").val(zoneId);

                $("#navUL li").removeClass("current");
                $(this).addClass('current');
                var keyWord = $("#keyWord").val().trim();
                var url;
                if(keyWord == ''){
                   url = '${pageContext.request.contextPath}/index/toggleTab?date='+new Date();
                }else{
                   $("#keyWord").val(keyWord);
                   url = '${pageContext.request.contextPath}/index/search?date='+new Date();
                }
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:url,
                    // contentType:"application/json;charset=UTF-8",
                    data:$("#searchForm").serialize(),
                    type:"post",
                    success:function(data){
                        showArticleList(data);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        // 状态码
                        console.log(XMLHttpRequest.status);
                        // 状态
                        console.log(XMLHttpRequest.readyState);
                        // 错误信息
                        console.log(textStatus);
                    }
                });
            });

        });

        function showArticleList(data) {
            var html = '<ul>';
            for(var i = 0;i < data.length;i++){
                var isTop = data[i].isTop;
                if(isTop == 1){
                    html = html + '<li class="clearfix ding">\n' +
                        '                                <div class="hm-index-title">\n' +
                        '                                    <i class="set-to-top">顶</i> <a href="${pageContext.request.contextPath}/comment/findComment.do?articleId='+data[i].articleId+'">'+data[i].title+'</a>\n' +
                        '                                </div>\n' +
                        '                                <div class="hm-index-con">' + data[i].content + '</div>\n' +
                        '                                <div class="hm-index-info l">\n' +
                        '                                    <span class="article-username">' + data[i].senderName + '</span>\n' +
                        '                                    <span class="post-time">'+ data[i].sendTimeStr +'</span>\n' +
                        '                                </div>\n' +
                        '                                <div class="hm-index-fun r">\n' +
                        '                                    <span class="icon-like"><i></i>' + data[i].upvoteCount + '</span>\n' +
                        '                                    <span class="icon-talk"><i></i>' + data[i].replyCount + '</span>\n' +
                        '                                </div>\n' +
                        '                            </li>';


                }else{
                    html = html + '<li class="clearfix">\n' +
                        '                                <div class="hm-index-title">\n' +
                        '                                    <i class="set-to-top">顶</i> <a href="${pageContext.request.contextPath}/comment/findComment.do?articleId='+data[i].articleId+'">'+data[i].title+'</a>\n' +
                        '                                </div>\n' +
                        '                                <div class="hm-index-con">' + data[i].content + '</div>\n' +
                        '                                <div class="hm-index-info l">\n' +
                        '                                    <span class="article-username">' + data[i].senderName + '</span>\n' +
                        '                                    <span class="post-time">'+ data[i].sendTimeStr +'</span>\n' +
                        '                                </div>\n' +
                        '                                <div class="hm-index-fun r">\n' +
                        '                                    <span class="icon-like"><i></i>' + data[i].upvoteCount + '</span>\n' +
                        '                                    <span class="icon-talk"><i></i>' + data[i].replyCount + '</span>\n' +
                        '                                </div>\n' +
                        '                            </li>';
                }

            }
            html = html + '</ul>';
            $("#testDiv").html(html);
        }

    </script>
</head>
<body>

<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<!-- 主体部分 -->
<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="hm-banner"></div>




        <!--头部，帖子统计，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="${pageContext.request.contextPath}/images/bbs-icon.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix"><h2 class="l">王者荣耀</h2></div>
                <p>
                    <span>今日帖子<strong>${todayCount}</strong></span>
                    <span>全部帖子<strong>${allCount}</strong></span>
                </p>
            </div>
            <div class="search-box l">
                <form action="javascript:;" id="searchForm">
                    <input id="zoneId" name="zoneId" type="hidden" value="${zoneId}"/>
                    <input id="keyWord" name="keyWord" type="text" class="txt l" placeholder="请输入关键字">
                    <input id="searchBtn" type="button" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>




        <!-- 导航 -->
        <ul class="hm-bbs-nav border-lrb clearfix" id="navUl">
            <c:forEach items="${zoneList}" var="zone">
                <c:if test="${zone.isDef == 1}">
                    <li class="current" id="${zone.zoneId}">
                        <%--<a href="#"><em></em>${zone.zoneName}</a>--%>
                        <a href="javascript:void(0)"><em></em>${zone.zoneName}</a>
                    </li>
                </c:if>
                <c:if test="${zone.isDef != 1}">
                    <li id="${zone.zoneId}">
                        <a href="javascript:void(0)"><em></em>${zone.zoneName}</a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>




        <!-- 主体部分 -->
        <div class="hm-bbs-main border-lrb clearfix">
            <!-- 左侧列表 -->
            <div class="list-view l" id="testDiv">
                <ul>


                    <c:forEach items="${articleslist}" var="article">
                        <c:if test="${article.isTop == 1}">
                            <li class="clearfix ding">
                                <div class="hm-index-title">
                                    <i class="set-to-top">顶</i> <a href="${pageContext.request.contextPath}/comment/findComment.do?articleId=${article.articleId}">${article.title}</a>
                                </div>
                                <div class="hm-index-con">${article.content}</div>
                                <div class="hm-index-info l">
                                    <span class="article-username">${article.senderName}</span>
                                    <span class="post-time">${article.sendTimeStr}</span>
                                </div>
                                <div class="hm-index-fun r">
                                    <span class="icon-like"><i></i>${article.upvoteCount}</span>
                                    <span class="icon-talk"><i></i>${article.replyCount}</span>
                                </div>
                            </li>
                        </c:if>

                        <c:if test="${article.isTop == 0}">
                            <li class="clearfix">
                                <div class="hm-index-title">
                                    <i class="set-to-top">顶</i> <a href="${pageContext.request.contextPath}/comment/findComment.do?articleId=${article.articleId}">${article.title}</a>
                                </div>
                                <div class="hm-index-con">${article.content}</div>
                                <div class="hm-index-info l">
                                    <span class="article-username">${article.senderName}</span>
                                    <span class="post-time">${article.sendTimeStr}</span>
                                </div>
                                <div class="hm-index-fun r">
                                    <span class="icon-like"><i></i>${article.upvoteCount}</span>
                                    <span class="icon-talk"><i></i>${article.replyCount}</span>
                                </div>
                            </li>
                        </c:if>
                    </c:forEach>

                </ul>
            </div>




            <!-- 右侧侧边栏,在线用户 -->
            <div class="aside l">
                <div class="aside-box">
                    <h3 class="t">
                        <a href="javascript:;">在线用户(${userList.size()})</a>
                    </h3>
                    <ul class="b clearfix">
                        <c:forEach items="${userList}" var="user">
                            <li>
                                <div><img src='/${user.picUrl}' height="55"/>
                                </div>
                                <p>${user.userName}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>



        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>
<script>
$(function () {




    $('#newTopicBtn').click(function () {
        if (${empty sessionScope.user}){
            alert("请登录账户");
            ala("#login");
        }else  {
            $('.ft-box').fadeIn(120);
        }
    });


    $('#submmit_article').click(function () {


        var title = $('#title').val();
        var content = $('#content').val();
        if (title.length <1 || content.length <1){
            alert("标题或正文不能为空");
            return false;
        }
        var msg = '${sessionScope.user.talkStatus}';


        if (msg !== '1') {

            alert("发帖成功！！！！");
        }

    });

    var msg = '${sessionScope.user.talkStatus}';
    if (msg === '1'){
        alert("您已被禁言！");
    }





})

</script>


<!-- 右边发帖，回顶部 -->
<div class="fixedBar" id="j_fixedBar">
    <a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>发帖</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>

<!-- 发帖弹出框 -->
<form action="${pageContext.request.contextPath}/article/saveArticle" method="post">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">主题帖</h4><span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_t">
                    <input type="text" id="title" name="title" placeholder="帖子标题"/>
                </div>
                <div class="win_bd_b">
                    <textarea id="content" name="content" placeholder="正文"></textarea>
                </div>
                <div>
                    <input id="article_zoneId" type="hidden" name="zoneId" value="${zoneId}">
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input id="submmit_article" type="submit" class="btn" value="发表" />
                </div>
            </div>
        </div>
    </div>
</form>


</body>
</html>