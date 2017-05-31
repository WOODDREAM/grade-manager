<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="panel">
    <header class="panel-heading">
        更新作业
    </header>
    <%--<c:if test="${not empty classDetail}">--%>
    <div class="panel-body">
        <div class="form">
            <form class="cmxform form-horizontal tasi-form" id="createUsualJodForm">
                <div class="form-group ">
                    <label for="updateJodID" class="control-label col-lg-2">ID</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="updateJodID" name="updateJodName" type="text"
                               value="${jobDetail.jobId}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="updateJodClassName" class="control-label col-lg-2">所属课程</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="updateJodClassName" name="updateJodClassName" type="text"
                               value="${jobDetail.className}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="updateJodName" class="control-label col-lg-2">名称</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="updateJodName" name="updateJodName" type="text"
                               value="${jobDetail.name}" autofocus=""/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="jodDetail" class="control-label col-lg-2">作业详情</label>

                    <div class="col-lg-10">
                        <input class="form-control " id="jodDetail" name="updateJodDetail"
                               value="${jobDetail.detail}"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="updateJodDetail" class="control-label col-lg-2">修改作业详情</label>

                    <div class="col-lg-10">
                        <textarea class="form-control " id="updateJodDetail" name="updateJodDetail"></textarea>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="isNeedAnswer" class="control-label col-lg-2 col-sm-3">是否需要作答?</label>

                    <div class="col-lg-10 col-sm-9">
                        <c:if test="${jobDetail.answer ==true}">
                            <input type="checkbox" id="isNeedAnswer" name="isNeedAnswer" value="是" checked/>
                        </c:if>
                        <c:if test="${jobDetail.answer ==false}">
                            <input type="checkbox" id="isNeedAnswer" name="isNeedAnswer" value="否"/>
                        </c:if>
                    </div>
                </div>
                <div class="form-group ">
                    <label class="control-label col-sm-4" for="dp3">结束时间</label>

                    <div class="col-sm-6">
                        <input id="dp3" type="text" value="${jobDetail.endTime}"
                               class="form-control">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-danger" type="button" id="updateJobBtn">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div>
        <input type="hidden" value="${message}" id="message"/>
    </div>
</section>
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap-switch.js"></script>

<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>

<!--custom tagsinput -->
<script src="/js/jquery.tagsinput.js"></script>

<script type="text/javascript" src="/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/assets/bootstrap-daterangepicker/date.js"></script>
<script type="text/javascript" src="/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="/assets/ckeditor/ckeditor.js"></script>
<!--script for this page-->
<script src="/js/form-component.js"></script>
<script src="/js/job.js"></script>
