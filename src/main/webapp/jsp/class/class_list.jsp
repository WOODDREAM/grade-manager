<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <header class="panel-heading">
        我的课程
    </header>
    <div class="row">
        <c:if test="${roleType ==2}">
            <div class="form-group ">
                <div class="col-lg-10 col-sm-9">
                    <label for="1" class="control-label col-lg-2 col-sm-3">重新开课</label>
                    <button class="btn btn-success btn-xs" disabled><i class="icon-undo" id="1"></i></button>
                </div>
                <div class="col-lg-10 col-sm-9">
                    <label for="2" class="control-label col-lg-2 col-sm-3">开课</label>
                    <button class="btn btn-success btn-xs" disabled><i class="icon-play-sign" id="2"></i></button>
                </div>
                <div class="col-lg-10 col-sm-9">
                    <label for="3" class="control-label col-lg-2 col-sm-3">结束开课</label>
                    <button class="btn btn-success btn-xs end_class_btn" id="3" disabled><i class="icon-off"></i>
                    </button>
                </div>
                <div class="col-lg-10 col-sm-9">
                    <label for="4" class="control-label col-lg-2 col-sm-3">查看课程下的学生</label>
                    <button class="btn btn-success btn-xs" id="4" disabled><i class="icon-group"></i></button>
                </div>
                <div class="col-lg-10 col-sm-9">
                    <label for="5" class="control-label col-lg-2 col-sm-3">加入学生</label>
                    <button class="btn btn-success btn-xs" id="5" disabled><i class="icon-user"></i></button>
                </div>

            </div>
        </c:if>
    </div>
    <table class="table table-striped border-top" id="sample_1">
        <thead>
        <tr>
            <th hidden="hidden"></th>
            <th><i class="icon-tags"></i> 名称</th>
            <th><i class="icon-bookmark"></i> 学时</th>
            <th><i class="icon-bookmark"></i> 学分</th>
            <th><i class="icon-bookmark"></i> 每周节数</th>
            <th><i class="icon-time"></i> 创建时间</th>
            <th><i class="icon-ambulance"></i>开课码</th>
            <th><i class="icon-bookmark"></i>状态</th>
            <c:if test="${roleType ==1}">
                <th><i class="icon-play-sign"></i>是否被同意</th>
            </c:if>
            <th><i class="icon-zoom-out"></i>查看详情</th>
            <th><i class="icon-zoom-out"></i>查看作业</th>
            <c:if test="${roleType ==2}">
                <th><i class="icon-edit"></i>编辑</th>
                <th><i class=" icon-edit"></i>创建作业</th>
                <th><i class="icon-play-sign"></i>操作</th>
            </c:if>
            <c:if test="${roleType ==1}">
                <th><i class="icon-play-sign"></i>退课</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${classList}">
            <tr class="items">
                <td hidden="hidden" class="classItemId">${item.classId}</td>
                <td class="classItemName">${item.name}</td>
                <td>${item.period}</td>
                <td>${item.credit}</td>
                <td>${item.frequency}</td>
                <td>${item.createTime}</td>
                <td class="classItemNo">${item.classNo}</td>
                <c:if test="${item.state == 1}">
                    <td>开课中</td>
                </c:if>
                <c:if test="${item.state == 2}">
                    <td>已开课</td>
                </c:if>
                <c:if test="${item.state == 3}">
                    <td>未开课</td>
                </c:if>
                <c:if test="${roleType ==1}">
                    <c:if test="${item.agree == true}">
                        <td>是</td>
                    </c:if>
                    <c:if test="${item.agree == false}">
                        <td>否</td>
                    </c:if>
                </c:if>
                <td>
                    <button class="btn btn-success btn-xs find_btn" name="find_btn"><i class="icon-eye-open"></i>
                    </button>
                </td>
                <td>
                    <button class="btn btn-success btn-xs find_job_btn" name="find_job_btn"><i
                            class="icon-zoom-out"></i>
                    </button>
                </td>
                <c:if test="${roleType ==2}">
                    <td>
                        <button class="btn btn-primary btn-xs update_btn" name="update_btn"><i class="icon-pencil"></i>
                        </button>
                        <button class="btn btn-danger btn-xs delete_btn" name="delete_btn"><i class="icon-trash "></i>
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-success btn-xs create_job_btn"><i class="icon-edit-sign"></i>
                        </button>
                    </td>
                    <td>
                            <%--开课结束--%>
                        <c:if test="${item.state == 2 or item.state == 1}">
                            <button class="btn btn-success btn-xs student_class_btn"><i class="icon-group"></i></button>
                        </c:if>
                            <%--未开课--%>
                        <c:if test="${item.state == 3}">
                            <button class="btn btn-success btn-xs start_class_btn"><i class="icon-play-sign"></i>
                            </button>
                        </c:if>
                            <%--开课中--%>
                        <c:if test="${item.state == 1}">
                            <button class="btn btn-success btn-xs start_class_btn"><i class="icon-undo"></i></button>
                            <button class="btn btn-success btn-xs end_class_btn"><i class="icon-off"></i></button>
                        </c:if>
                        <button class="btn btn-success btn-xs make_student_join_class_btn"><i class="icon-user"></i>
                        </button>
                    </td>
                </c:if>
                <c:if test="${roleType ==1}">
                    <td>
                        <c:if test="${item.state ==1 or item.state == 3}">
                            <button class="btn btn-success btn-xs delete_class_btn"><i class="icon-trash"></i>
                            </button>
                        </c:if>
                        <c:if test="${item.state ==2}">
                            <button class="btn btn-success btn-xs" disabled><i class="icon-trash"></i>
                            </button>
                        </c:if>
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
        <div>
            <input type="hidden" value="${message}" id="message"/>
        </div>
    </div>
</div>

<!--main content end-->
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.scrollTo.min.js"></script>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="/assets/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="/assets/data-tables/DT_bootstrap.js"></script>

<!--script for this page only-->
<script src="/js/dynamic-table.js"></script>
<script src="/js/class.js"/>
