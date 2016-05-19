<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="panel">
    <header class="panel-heading">
        作业详情。
    </header>
    <%--<c:if test="${not empty classDetail}">--%>
    <div class="panel-body">
        <div class="form">
            <form class="cmxform form-horizontal tasi-form" id="createUsualJodForm">
                <div class="form-group ">
                    <label for="createJodID" class="control-label col-lg-2">ID</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createJodID" name="createJodName" type="text"
                               value="${jobDetail.jobId}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createJodName" class="control-label col-lg-2">名称</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createJodName" name="createJodName" type="text"
                               value="${jobDetail.name}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createJodClassName" class="control-label col-lg-2">所属课程</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createJodClassName" name="createJodName" type="text"
                               value="${jobDetail.className}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createUsualJodDetail" class="control-label col-lg-2">作业详情</label>

                    <div class="col-lg-10">
                        <input class="form-control " id="createUsualJodDetail" name="createJodDetail"
                               value="${jobDetail.detail}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="isNeedAnswerUsual" class="control-label col-lg-2 col-sm-3">是否需要作答?</label>

                    <div class="col-lg-10 ">
                        <c:if test="${jobDetail.answer ==true}">
                            <input type="text" class=" form-control" id="isNeedAnswerUsual" name="isNeedAnswer"
                                   value="是" readonly/>
                        </c:if>
                        <c:if test="${jobDetail.answer ==false}">
                            <input type="text" class=" form-control" id="isNeedAnswerUsual" name="isNeedAnswer"
                                   value="否" readonly/>
                        </c:if>
                    </div>
                </div>
                <div class="form-group ">
                    <label class="control-label col-sm-4" for="dp3">结束时间</label>

                    <div class="col-sm-6">
                        <input id="dp3" type="text" value="${jobDetail.endTime}"
                               class="form-control" readonly>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div>
        <input type="hidden" value="${message}" id="message"/>
    </div>
</section>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
