<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>板块审核</title>
</head>
<style type="text/css">
    html,body{
        overflow:auto;
        height:100%;
    }

    .line-limit-length {
        max-width: 220px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }


</style>
<script>
    function uptStatus(applyZoneId,zoneName,flag) {
        var param;
        var url;
        param = {'applyZoneId':applyZoneId,'zoneName':zoneName,'flag':flag};
        url = '${pageContext.request.contextPath}/mgr_zoneapply/deal?date='+new Date();

        $.ajax({
            // 编写json格式，设置属性和值
            url:url,
            data:param,
            type:"post",
            success:function(data){
            }
        });

        var str;
        var html;
        str = '已处理 ';
        html = '<a href="#" disabled="disabled" onclick="" role="button" class="btn btn-primary" >通过</a> \n' +
            '    <a href="#" disabled="disabled" onclick="" role="button" class="btn btn-danger" >驳回</a>';

        $("#tr_"+applyZoneId+" td:nth-child(5)").html(str);
        $("#tr_"+applyZoneId+" td:nth-child(6)").html(html);

    }
</script>
<body>
<div class="hrms_dept_container">
    <!-- 导航栏-->
    <%@ include file="commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div class="hrms_dept_body">
        <!-- 左侧栏 -->
        <%@ include file="commom/leftsidebar.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">
            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div >
                    <ol class="breadcrumb">
                        <li><a href="#">板块审核</a></li>
                        <li class="active">板块审核</li>
                    </ol>
                </div>
                <hr>
                <!-- Table -->
                <div style="clear:both"></div>
                <hr>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>板块名</th>
                        <th>用户名</th>
                        <th>申请原因</th>
                        <th>处理状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="zoneapply" varStatus="vs">
                            <tr id="tr_${zoneapply.applyZoneId}">
                                <td width="10%" class="line-limit-length">
                                    ${vs.index+1}
                                </td>
                                <td width="15%" class="line-limit-length">${zoneapply.zoneName}</td>
                                <td width="15%" class="line-limit-length">${zoneapply.userName}</td>
                                <td width="15%" class="line-limit-length">${zoneapply.reason}</td>
                                <td width="10%">
                                    <c:if test="${zoneapply.status == 0}" >
                                        未处理
                                    </c:if>
                                    <c:if test="${zoneapply.status == 1}" >
                                        已处理
                                    </c:if>
                                </td>
                                <td width="15%">
                                    <c:if test="${zoneapply.status == 0}">
                                        <a href="#" onclick="uptStatus(${zoneapply.applyZoneId},'${zoneapply.zoneName}',1)" role="button" class="btn btn-primary" >通过</a>
                                        <a href="#" onclick="uptStatus(${zoneapply.applyZoneId},'${zoneapply.zoneName}',0)" role="button" class="btn btn-danger" >驳回</a>
                                    </c:if>
                                    <c:if test="${zoneapply.status == 1}">
                                        <a href="#" disabled="disabled" onclick="uptStatus(${zoneapply.applyZoneId},'${zoneapply.zoneName}',1)" role="button" class="btn btn-primary" >通过</a>
                                        <a href="#" disabled="disabled" onclick="uptStatus(${zoneapply.applyZoneId},'${zoneapply.zoneName}',0)" role="button" class="btn btn-danger" >驳回</a>
                                    </c:if>
                                </td>
                            </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div><!-- /.panel panel-success -->
            <!--显示分页信息-->
            <div class="row">
                <!--文字信息-->
                <div class="col-md-6">
                    当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
                </div>

                <!--点击分页-->
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <!--首页-->
                            <li><a href="${pageContext.request.contextPath}/mgr_zoneapply/findByPage?page=1&size=${pageInfo.pageSize}" >首页</a></li>
                            <!--上一页-->
                            <li>
                                <c:if test="${pageInfo.hasPreviousPage}">
                                        <a href="${pageContext.request.contextPath}/mgr_zoneapply/findByPage?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}"  aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                </c:if>
                            </li>

                            <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                                <c:if test="${page_num == pageInfo.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/mgr_zoneapply/findByPage?page=${page_num}&size=${pageInfo.pageSize}">${page_num}</a></li>
                                </c:if>
                                <c:if test="${page_num != pageInfo.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/mgr_zoneapply/findByPage?page=${page_num}&size=${pageInfo.pageSize}" >${page_num}</a></li>
                                </c:if>
                            </c:forEach>

                            <!--下一页-->
                            <li>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/mgr_zoneapply/findByPage?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}" aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:if>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/mgr_zoneapply/findByPage?page=${pageInfo.pages}&size=${pageInfo.pageSize}" >尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div><!-- /.dept_info -->
        <!-- 尾部-->
        <%@ include file="commom/foot.jsp"%>
    </div><!-- /.hrms_dept_body -->

</div><!-- /.hrms_dept_container -->

<%--<%@ include file="ArticleAdd.jsp"%>--%>
</body>

</html>
