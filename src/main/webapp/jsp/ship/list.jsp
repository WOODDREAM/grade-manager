<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <c:if test="${roleType == 1}">
        <div class="col-lg-6">
            <section class="panel">
                <header class="panel-heading">
                    已同意加入课程
                </header>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden="hidden"></th>
                        <th>课程名</th>
                        <th>课程号</th>
                        <th>任课教师</th>
                        <th>学分</th>
                        <th>学号</th>
                        <th>退课</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty agreeClass}">

                        <c:forEach var="item" items="${agreeClass}">
                            <tr>
                                <td hidden="hidden" class="reId">${item.relstionId}</td>
                                <td>${item.className}</td>
                                <td>${item.classNo}</td>
                                <td>${item.teacherName}</td>
                                <td>${item.credit}</td>
                                <td>${item.studentNo}</td>
                                <td>
                                    <button class="btn btn-danger btn-xs out_re_btn"><i
                                            class="icon-trash "></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </section>
        </div>
        <div class="col-lg-6">
            <section class="panel">
                <header class="panel-heading">
                    未同意加入课程(请联系教师解决)
                </header>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden="hidden"></th>
                        <th>课程名</th>
                        <th>课程号</th>
                        <th>任课教师</th>
                        <th>学分</th>
                        <th>学号</th>
                        <th>退课</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty notAgreeClass}">
                        <c:forEach var="item" items="${notAgreeClass}">
                            <tr>
                                <td hidden="hidden" class="reId">${item.relstionId}</td>
                                <td>${item.className}</td>
                                <td>${item.classNo}</td>
                                <td>${item.teacherName}</td>
                                <td>${item.credit}</td>
                                <td>${item.studentNo}</td>
                                <td>
                                    <button class="btn btn-danger btn-xs delete_un_class_btn"><i
                                            class="icon-trash "></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </section>
        </div>
    </c:if>
    <c:if test="${roleType == 2}">
        <div class="col-lg-6">
            <section class="panel">
                <header class="panel-heading">
                    已同意加入课程的学生
                </header>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden="hidden"></th>
                        <th>课程名</th>
                        <th>课程号</th>
                        <th>姓名</th>
                        <th>学号</th>
                        <th>学分</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty agreeClass}">
                        <c:forEach var="item" items="${agreeClass}">
                            <tr>
                                <td class="reId" hidden="hidden">${item.relstionId}</td>
                                <td>${item.className}</td>
                                <td>${item.classNo}</td>
                                <td>${item.studentName}</td>
                                <td>${item.studentNo}</td>
                                <td>${item.credit}</td>
                                <td>
                                    <button class="btn btn-danger btn-xs delete_re_btn"><i
                                            class="icon-trash "></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </section>
        </div>
        <div class="col-lg-6">
            <section class="panel">
                <header class="panel-heading">
                    未同意加入课程的学生
                </header>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th hidden="hidden"></th>
                        <th>课程名</th>
                        <th>课程号</th>
                        <th>姓名</th>
                        <th>学号</th>
                        <th>学分</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty notAgreeClass}">
                        <c:forEach var="item" items="${notAgreeClass}">
                            <tr>
                                <td class="reId" hidden="hidden">${item.relstionId}</td>
                                <td>${item.className}</td>
                                <td>${item.classNo}</td>
                                <td>${item.studentName}</td>
                                <td>${item.studentNo}</td>
                                <td>${item.credit}</td>
                                <td>
                                    <button class="btn btn-primary btn-xs agree_re_btn"><i
                                            class="icon-check"></i>
                                    </button>
                                    <button class="btn btn-danger btn-xs delete_re_btn"><i
                                            class="icon-trash "></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </section>
        </div>
    </c:if>
    <div>
        <input type="hidden" value="${message}" id="message"/>
    </div>
</div>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/relation.js"></script>
