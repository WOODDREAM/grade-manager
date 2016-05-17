<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <header class="panel-heading">
        我的课程
    </header>
    <table class="table table-striped table-advance table-hover" id="cla_table">
        <thead>
        <tr>
            <th><i class="icon-bullhorn"></i> ID</th>
            <th><i class="icon-tags"></i> 名称</th>
            <th><i class="icon-bookmark"></i> 学时</th>
            <th><i class="icon-bookmark"></i> 学分</th>
            <th><i class="icon-bookmark"></i> 每周节数</th>
            <th><i class=" icon-time"></i> 创建时间</th>
            <th><i class="  icon-edit-sign"></i>编辑</th>
            <c:if test="${roleType ==2}">
                <th><i class=" icon-edit"></i>创建作业</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${classList}">
            <tr class="items">
                <td class="classItemId">${item.classId}</td>
                <td class="classItemName">${item.name}</td>
                <td>${item.period}</td>
                <td>${item.credit}</td>
                <td>${item.frequency}</td>
                <td>${item.createTime}</td>
                <td>
                    <button class="btn btn-success btn-xs find_btn" name="find_btn"><i class="icon-zoom-out"></i>
                    </button>
                    <c:if test="${roleType ==2}">
                        <button class="btn btn-primary btn-xs update_btn" name="update_btn"><i class="icon-pencil"></i>
                        </button>
                        <button class="btn btn-danger btn-xs delete_btn" name="delete_btn"><i class="icon-trash "></i>
                        </button>
                    </c:if>
                </td>
                <c:if test="${roleType ==2}">
                    <td>
                        <button class="btn btn-success btn-xs create_job_btn"><i class="icon-edit-sign"></i>
                        </button>
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
    <div class="col-lg-9" id="myContainer2">
    </div>
</div>

<!--main content end-->
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>

<!-- js placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="/assets/data-tables/DT_bootstrap.js"></script>
<!--script for this page only-->
<script src="/js/dynamic-table.js"></script>
<script src="/js/class.js"/>
