<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <header class="panel-heading">
        我的作业
    </header>
    <table class="table table-striped border-top" id="job_list_table">
        <thead>
        <tr>
            <th><i class="icon-bullhorn"></i> ID</th>
            <th><i class="icon-bookmark"></i> 名称</th>
            <th><i class="icon-time"></i> 截止日期</th>
            <th><i class="icon-bookmark"></i> 是否需要作答</th>
            <th><i class="icon-bookmark"></i> 作业类型</th>
            <th><i class="icon-zoom-out"></i>查看</th>
            <c:if test="${type ==2}">
                <th><i class=" icon-edit"></i>编辑</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${jobList}">
            <tr class="items">
                <td class="jobId">${item.jobId}</td>
                <td>${item.name}</td>
                <td>${item.endTime}</td>
                <c:if test="${item.answer ==true}">
                    <td>是</td>
                </c:if>
                <c:if test="${item.answer ==false}">
                    <td>否</td>
                </c:if>
                <c:if test="${item.type ==1}">
                    <td>平时</td>
                </c:if>
                <c:if test="${item.type ==2}">
                    <td>考试</td>
                </c:if>
                <td>
                    <button class="btn btn-success btn-xs jobFindBtn"><i class="icon-zoom-out"></i></button>
                </td>
                <c:if test="${type ==2}">
                    <td>
                        <button class="btn btn-primary btn-xs jobUpdateBtn"><i class="icon-pencil"></i></button>
                        <button class="btn btn-danger btn-xs jobDeleteBtn"><i class="icon-trash "></i></button>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9" id="jobDetail">
    </div>
</div>

<!--main content end-->
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.scrollTo.min.js"></script>
<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="/assets/data-tables/DT_bootstrap.js"></script>

<!--script for this page only-->
<script src="/js/dynamic-job-table.js"></script>
<script src="/js/job.js"/>