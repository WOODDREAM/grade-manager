<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <header class="panel-heading">
        我的课程
    </header>
    <table class="table table-striped table-advance table-hover">
        <thead>
        <tr>
            <th><i class="icon-bullhorn"></i> ID</th>
            <th><i class="icon-bookmark"></i> 学分</th>
            <th class="hidden-phone"><i class="icon-question-sign"></i> 学时</th>
            <th><i class="icon-bookmark"></i> 学分</th>
            <th><i class="icon-bookmark"></i> 创建时间</th>
            <th><i class="icon-bookmark"></i> 每周节数</th>
            <th><i class=" icon-edit"></i>编辑</th>
            <th></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${classList}">
            <tr>
                <td><a href="#"></a>${item.classId}</td>
                <td>${item.name}</td>
                <td>${item.period}</td>
                <td>${item.credit}</td>
                <td>${item.createTime}</td>
                <td>${item.frequency}</td>
                <td><span class="label label-info label-mini">${item.classId}</span></td>
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
