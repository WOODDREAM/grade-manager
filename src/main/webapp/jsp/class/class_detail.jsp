<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="panel">
    <div class="panel-body">
        <div class="row">
            <section class="panel">
                <header class="panel-heading">
                    课程详情
                </header>
                <c:if test="${not empty classDetail}">
                    <div class="panel-body">
                        <div class="form">
                            <form class="cmxform form-horizontal tasi-form">
                                <section class="panel">
                                    <div class="form-group ">
                                        <label for="classTeacherName" class="control-label col-lg-2">教师名称</label>

                                        <div class="col-lg-10">
                                            <input class=" form-control" id="classTeacherName" name="className"
                                                   type="text" value="${classDetail.teacherName}" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="className" class="control-label col-lg-2">名称</label>

                                        <div class="col-lg-10">
                                            <input class=" form-control" id="className" name="className"
                                                   type="text" value="${classDetail.name}" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="classPeriod"
                                               class="control-label col-lg-2">学时</label>

                                        <div class="col-lg-10">
                                            <input class="form-control " id="classPeriod" name="classPeriod"
                                                   value="${classDetail.period}" readonly type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="classCredit" class="control-label col-lg-2">学分</label>

                                        <div class="col-lg-10">
                                            <input class="form-control " id="classCredit" name="classCredit"
                                                   value="${classDetail.credit}" readonly type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="classUnit" class="control-label col-lg-2">上课单位(周)</label>

                                        <div class="col-lg-10">
                                            <input class="form-control " id="classUnit" name="classUnit"
                                                   type="text" value="周" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="classFre" class="control-label col-lg-2">上课频率</label>

                                        <div class="col-lg-10">
                                            <input class="form-control " id="classFre" name="classUnit"
                                                   type="text" value="${classDetail.frequency}" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label class="control-label col-sm-4" for="dp3">开始时间</label>

                                        <div class="col-sm-6">
                                            <input id="dp3" type="text" value="${classDetail.startTime}" readonly
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-sm-4" for="dp2">结束时间</label>

                                        <div class="col-sm-6">
                                            <input id="dp2" type="text" value="${classDetail.endTime}" readonly
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label class="control-label col-sm-4"></label>
                                    </div>
                                </section>
                                <section class="panel">
                                    <header class="panel-heading no-border">
                                        上课时间
                                    </header>
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>星期</th>
                                            <th>节次</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${classDetail.classDetailVos}">
                                            <tr>
                                                <td>${item.weekDay}</td>
                                                <td>${item.part}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </section>
                            </form>
                        </div>
                    </div>
                </c:if>
            </section>
        </div>
    </div>
</section>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
