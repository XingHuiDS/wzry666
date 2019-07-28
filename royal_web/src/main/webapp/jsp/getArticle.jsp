<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛详情页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/getArticle.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">

        <!--帖子标题，点赞数，回复数，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="${pageContext.request.contextPath}/images/default.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix">
                    <h2 class="l">${article.title}</h2>
                    <div class="hm-detail-fun l">
                        <span id="like" class="icon-like">
                              <a  href="javascript:;" id="upvoteCount" onclick="upvoteCount()" >点赞
                                     <i></i>
                                  ${article.upvoteCount}
                             </a>
					     </span>
                        <span class="icon-talk">评论
						     <i></i>${article.replyCount}
						</span>
                    </div>
                </div>
            </div>

        </div>


        <!--导航，回首页，帖子标题，排序-->
        <div class="detail-page-box clearfix">
            <a href="${pageContext.request.contextPath}/index/show">
                <i class="hm-ico-home"></i>首页
            </a>
            <span>></span>
            <a href="#">${article.title}</a>

        </div>


        <div class="detail-box">
            <ul class="detail-floors">

                <!--原帖楼-->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src="${pageContext.request.contextPath}/images/default.png"/></div>
                        <div class="floorer-name">${article.senderName}</div>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">${article.sendTimeStr}</div>
                            <div class="r">沙发</div>
                            <div class="r">浏览数:${article.browseCount}&nbsp;&nbsp;&nbsp;</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>${article.content}</p>
                            </div>
                            <div class="floor-ans"></div>
                        </div>

                        <span class="icon-comment">
                                <a href="#comment"> <i></i> 评论</a>
                            </span>

                    </div>
                </li>
            </ul>

            <ul id="comments">
                <c:forEach items="${commentList}" var="comment" varStatus="vsl">
                    <li class="floor clearfix">
                        <div class="floorer-info l">
                            <div class="floorer-photo"><img src="${pageContext.request.contextPath}/images/default.png"/></div>
                            <div class="floorer-name">${comment.commentUserName}</div>
                        </div>
                        <div class="floor-con l">
                            <div class="floor-info clearfix">
                                <div class="floor-time l">${comment.commentTimeStr}</div>
                                <div class="r">${vsl.index+2}楼</div>
                            </div>
                            <div class="floor-art-ans">
                                <div class="floor-art">
                                    <p>${comment.commentContent}</p>
                                </div>
                                <div class="floor-ans" id="rep">
                                    <ul id="replies${comment.commentId}">
                                        <!-- 回复部分,楼中楼 -->

                                    </ul>
                                </div>
                                <span class="icon-comment">
                                    <a href="javascript:;" onclick="showDialog(${vsl.index+2},${comment.commentId})"> <i></i> 回复</a>
                                </span>
                            </div>
                        </div>
                    </li>
                </c:forEach>

            </ul>
        </div>

        <!--发表评论-->
        <div class="detail-to-comment">
            <div class="tit"><a name="comment">发表评论</a></div>
            <!-- 未登录时候显示 <div class="con">您没有登录论坛，请登录后再进行回复</div>-->

            <!-- 登录后显示评论输入框-->
            <c:if test="${sessionScope.user != null}">
                <form action="${pageContext.request.contextPath}/comment/saveComment.do" method="post">
                    <div class="con con-loged">
                        <div class="con-t">
                            <textarea id="content" name="commentContent" placeholder="请在此输入您要回复的信息"></textarea>
                        </div>
                        <input type="hidden" name="commentUserName" value="${sessionScope.user.userName}">
                        <input type="hidden" name="articleId" value="${article.articleId}">
                        <div class="con-b">
                            <input type="submit" class="btn"/>
                            <span class="num">不能超过5000字</span>
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                <div class="con">您没有登录论坛，请
                    <a href="javascript:;" onclick="ala('#login')">登录</a>
                    后再进行回复</div>
            </c:if>
        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>


<!-- 回复弹出框 -->
<form id="replyForm" action=${pageContext.request.contextPath}"/reply/saveReply.do" method="post">
    <div class="pop-box ft-box" id="reply_pop">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">回复<span id="floorSpan"></span>楼</h4>
                <span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_b">
                    <textarea id="replyContent" name="replyContent" placeholder="回复内容限于40字以内"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="hidden" name="replyUserName" value="${sessionScope.user.userName}">
                    <input type="hidden" id="commentId" name="commentId"/>
                    <input type="hidden" id="articleId" name="articleId" value="${article.articleId}"/>
                    <input type="submit" class="btn" value="回复"/>

                </div>
            </div>
        </div>
    </div>
</form>

<%--弹出举报框--%>
<form action="${pageContext.request.contextPath}/report/sendReport" method="post">
    <div class="pop-box report-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">举报内容</h4><span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_b">
                    <textarea id="reportContent" name="reportContent" placeholder="正文"></textarea>
                </div>
                <div>
                    <input type="hidden" name="articleId" value="${article.articleId}">
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="发表" />
                </div>
            </div>
        </div>
    </div>
</form>

<div class="fixedBar" id="j_fixedBar">
    <a href="#comment" class="newTopic"><span></span>回复</a>
    <a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>举报</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>

<input type="hidden" id="flag" name="commentUserName" value="${upvote.isUpvote}">



<script>
    //获取所有评论回复
    $(function () {

        if(${empty sessionScope.user.userName}){
            $("#like").attr("class","icon-like");
        }else {
            if($("#flag").val() === '0') {
                $("#like").attr("class","icon-like");
            }else {
                $("#like").attr("class","icon-liked").html('<a  href="javascript:;" id="upvoteCount" onclick="upvoteCount()" >已点赞\n' +
                    '                                     <i></i>\n' +
                    '                                  ${upvote.isUpvote}\n' +
                    '                             </a>');
            }
        }



        var array = [];
        <c:forEach items="${commentList}" var="t">
        array.push(${t.commentId}); //js中可以使用此标签，将EL表达式中的值push到数组中
        </c:forEach>
        for(var i=0;i<array.length;i++){
            getReply(array[i]);
        }





    });

    //更新点赞数
    function upvoteCount() {
        if('${sessionScope.user.userName}'!==''){
            if($("#flag").val() === '0'){

                $.ajax({
                    "url":${pageContext.request.contextPath}"/upvote/addUpvoteCount?isUpvote=1&articleId="+'${article.articleId}'+"&upvoteUserName="+'${sessionScope.user.userName}',
                    "type":"POST",
                    "success":function (data) {
                        // alert(data.upvoteCount);
                        $("#flag").val(1);
                        $("#like").attr("class","icon-liked").html('<a  href="javascript:;" id="upvoteCount" onclick="upvoteCount()" >已点赞\n' +
                            '                                     <i></i>\n' +
                            '                                  '+data.upvoteCount+'\n' +
                            '                             </a>');
                        <%--location.href = ${pageContext.request.contextPath}"/comment/findComment.do"--%>
                    }
                })
            }else {

                $.ajax({
                    "url":${pageContext.request.contextPath}"/upvote/deleteUpvoteCount?isUpvote=0&articleId="+'${article.articleId}'+"&upvoteUserName="+'${sessionScope.user.userName}',
                    "type":"POST",
                    "success":function (data) {
                        // alert(data.upvoteCount);
                        $("#like").attr("class","icon-like").html("<a  href=\"javascript:;\" id=\"upvoteCount\" onclick=\"upvoteCount()\" >点赞\n" +
                            "                                     <i></i>\n" +
                            "                                  "+data.upvoteCount+"\n" +
                            "                             </a>");
                        $("#flag").val(0);
                        <%--location.href = ${pageContext.request.contextPath}"/comment/findComment.do?articleId="+'${article.articleId}'--%>
                    }
                })
            }
        }else {
            alert("请登录");
            ala($('#login'));
        }

    }


</script>
</body>

<script type="text/javascript">

    <%--获取评论回复--%>


    //弹出举报
    $(function () {
        $('#newTopicBtn').click(function () {
            if (${sessionScope.user == null}){
                alert("请登录账户");
                ala("#login");
            }else {
                if ('${article.senderName}' === '${sessionScope.user.userName}'){
                    alert("不能举报自己");
                }else {$('.report-box').fadeIn(120);}

            }
        });

        if('${sessionScope.msg}'==='1'){
            alert("您已被禁言!");
        }
    });


    <%--获取评论回复函数--%>
    function getReply(commentId) {
        $.ajax({
            "url": ${pageContext.request.contextPath}"/reply/findByCommentId.do?commentId="+commentId,
            "contentType": "application/json;charset=UTF-8",
            "type": "post",
            "success": function (data) {
                var replies = "";
                $.each(data,function (index, ele) {
                    replies += '<li class="clearfix"><div class="floor-ans-pho l">\n' +
                        ' <img src="${pageContext.request.contextPath}/images/default.png"/>\n' +
                        '</div>\n' +
                        '<div class="floor-ans-con l" id="reply">\n' +
                        '<span class="name" id="replyUsername">' + ele.replyUserName + '</span>：' + ele.replyContent + '\n' +
                        '<span class="ans-time" id="replyTime">' + ele.replyTimeStr + '</span>\n' +
                        '</div></li>';
                    // alert(ele.commentId);
                    // alert(ele.replyContent);
                    $("#replies"+ele.commentId).html(replies);
                })
            }
        })
    }

    //弹出回复框
    function showDialog(num, commentId) {
        var loginUser = "${sessionScope.user.userName}";
        if (!loginUser) {
            alert("请登录");
            ala($('#login'));
            return;
        };
        $("#commentId").val(commentId);
        $('#reply_pop').css('display', 'block');
        $("#floorSpan").html(num);
    }

</script>
</html>