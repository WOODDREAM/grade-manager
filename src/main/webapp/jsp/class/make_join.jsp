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
                    <div class="panel-body row">
                        <div class="form">
                            <form class="cmxform form-horizontal tasi-form">
                                <section class="panel">
                                    <div class="form-group ">
                                        <label for="className" class="control-label col-lg-2">名称</label>

                                        <div class="col-lg-10">
                                            <input class=" form-control" id="className" name="className"
                                                   type="text" value="${classDetail.name}" readonly/>
                                            <input class=" form-control" id="classId" name="classId"
                                                   type="hidden" value="${classDetail.classId}" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="classPeriod" class="control-label col-lg-2">学时</label>

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
                            </form>

                        </div>
                    </div>
                    <div class="container">

                        <form class="form-signin" id="form-join-class">
                            <h2 class="form-signin-heading">加入课程</h2>

                            <div class="login-wrap">
                                <input type="number" class="form-control" placeholder="手机号码" id="mobile" name="mobile"
                                       autofocus>
                            </div>
                            <div class="login-wrap">
                                <input type="number" class="form-control" placeholder="学号" id="studentNO"
                                       name="studentNO">
                            </div>
                            <div class="login-wrap">
                                <input type="text" class="form-control" placeholder="姓名" id="studentName"
                                       name="studentName">
                            </div>
                            <button class="btn btn-sm btn-login btn-block" id="btn-make-join-class" type="button">确认加入
                            </button>
                        </form>
                    </div>
                </c:if>
            </section>
        </div>
    </div>
    <div>
        <input type="hidden" value="${message}" id="message"/>
    </div>
</section>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/class.js"/>

