<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>敏感词汇管理页面</title>
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
    function uptStatus(wid,curStatus) {
        var status;
        if(curStatus == 0){
            status = 1;
        }else{
            status = 0;
        }

        $.ajax({
            // 编写json格式，设置属性和值
            url:"${pageContext.request.contextPath}/mgr_word/uptStatus?wordId="+wid+"&status="+status,
            type:"post",
            success:function(data){
            }
        });

        var str;
        var html;
        if(status == 1){
            str = '使用中';
            html = '<a href="#" onclick="uptStatus('+wid+','+status+')" role="button" class="btn btn-danger" >停用</a>';
        }else{
            str = '已停用 ';
            html = '<a href="#" onclick="uptStatus('+wid+','+status+')" role="button" class="btn btn-primary" >启用</a>';
        }

        $("#tr_"+wid+" td:nth-child(3)").html(str);
        $("#tr_"+wid+" td:nth-child(4)").html(html);

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
                        <li><a href="#">敏感词汇管理</a></li>
                        <li class="active">敏感词汇信息</li>
                    </ol>
                </div>
                <hr>

                <a href="#" id="addBtn" onclick="" role="button" class="btn btn-primary" >新增敏感词</a>

                <div style="clear:both"></div>
                <hr>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>敏感词</th>
                        <th>是否启动</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="wd" varStatus="vs">
                            <tr id="tr_${wd.wordId}">
                                <td width="10%" class="line-limit-length">
                                    ${vs.index+1}
                                </td>
                                <td width="50%" class="line-limit-length">${wd.word}</td>
                                <td width="10%">
                                    <c:if test="${wd.status == 0}" >
                                        已停用
                                    </c:if>
                                    <c:if test="${wd.status == 1}" >
                                        使用中
                                    </c:if>
                                </td>
                                <td width="5%">
                                    <c:if test="${wd.status == 0}">
                                        <a href="#" onclick="uptStatus(${wd.wordId},${wd.status})" role="button" class="btn btn-primary" >启用</a>
                                    </c:if>
                                    <c:if test="${wd.status == 1}">
                                        <a href="#" onclick="uptStatus(${wd.wordId},${wd.status})" role="button" class="btn btn-danger" >停用</a>
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
                            <li><a href="${pageContext.request.contextPath}/mgr_word/findByPage?page=1&size=${pageInfo.pageSize}" >首页</a></li>
                            <!--上一页-->
                            <li>
                                <c:if test="${pageInfo.hasPreviousPage}">
                                        <a href="${pageContext.request.contextPath}/mgr_word/findByPage?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}"  aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                </c:if>
                            </li>

                            <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                                <c:if test="${page_num == pageInfo.pageNum}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/mgr_word/findByPage?page=${page_num}&size=${pageInfo.pageSize}">${page_num}</a></li>
                                </c:if>
                                <c:if test="${page_num != pageInfo.pageNum}">
                                    <li><a href="${pageContext.request.contextPath}/mgr_word/findByPage?page=${page_num}&size=${pageInfo.pageSize}" >${page_num}</a></li>
                                </c:if>
                            </c:forEach>

                            <!--下一页-->
                            <li>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/mgr_word/findByPage?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}" aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:if>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/mgr_word/findByPage?page=${pageInfo.pages}&size=${pageInfo.pageSize}" >尾页</a></li>
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

<div class="modal fade article-detail-modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="article-detail-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">新增敏感词</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" class="form-horizontal article_detail_form" >
                    <div class="form-group">
                        <label for="sensitive_words" class="col-sm-2 control-label">敏感词</label>
                        <div class="col-sm-8">
                            <%--<textarea class="form-control" rows="3" name="title" id="detail_title" disabled></textarea>--%>
                            <input id="sensitive_words" type="text" name="word" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group" style="text-align: center ">
                        <a href="#" id="submitBtn" onclick="" role="button" class="btn btn-primary" >确定</a>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script>

    $(function () {
        $('#addBtn').click(function() {
            $('#myModal').modal('show');
        });
        $("#submitBtn").click(function(){
            var sensitive_words =  $('#sensitive_words').val().trim();
            if(sensitive_words == ''){
                alert('请输入敏感词');
                return;
            }

            $('#sensitive_words').val(sensitive_words);
            $.ajax({
                url:"${pageContext.request.contextPath}/mgr_word/save?date="+new Date(),
                data:$("#addForm").serialize(),
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data){
                        alert('添加成功！');
                    }
                    location.href = '${pageContext.request.contextPath}/mgr_word/findByPage';
                }
            });

        });
    });
</script>
</body>
</html>
