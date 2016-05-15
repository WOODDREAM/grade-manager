<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <header class="panel-heading">
        我的作业
    </header>
    <table class="table table-striped table-advance table-hover">
        <thead>
        <tr>
            <th><i class="icon-bullhorn"></i> ID</th>
            <th><i class="icon-bookmark"></i> 名称</th>
            <th class="hidden-phone"><i class="icon-question-sign"></i> 详情</th>
            <th><i class="icon-bookmark"></i> 是否需要作答</th>
            <th><i class="icon-bookmark"></i> 作业类型</th>
            <th><i class=" icon-edit"></i>编辑</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${jobList}">
            <tr>
                <td><a href="#"></a>${item.classId}</td>
                <td>${item.jobId}</td>
                <td>${item.name}</td>
                <td>${item.detail}</td>
                <c:if test="${item.isAnswer}==1">
                    <td>是</td>
                </c:if>
                <c:if test="${item.isAnswer}==0">
                    <td>否</td>
                </c:if>
                <c:if test="${item.type}==1">
                    <td>平时</td>
                </c:if>
                <c:if test="${item.type}==2">
                    <td>期中</td>
                </c:if>
                <c:if test="${item.type}==3">
                    <td>期末</td>
                </c:if>
                <td>
                    <button class="btn btn-success btn-xs"><i class="icon-ok"></i></button>
                    <button class="btn btn-primary btn-xs"><i class="icon-pencil"></i></button>
                    <button class="btn btn-danger btn-xs"><i class="icon-trash "></i></button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<!--main content end-->

<!-- js placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="/assets/data-tables/DT_bootstrap.js"></script>
<!--script for this page only-->
<script src="js/dynamic-table.js"></script>