<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="panel">
    <header class="panel-heading">
        创建平时类型作业，可选择是否作答。
    </header>
    <div class="panel-body">
        <div class="form">
            <form class="cmxform form-horizontal tasi-form" id="createUsualJodForm">
                <div class="form-group ">
                    <label for="createUsualJodForClassId" class="control-label col-lg-2">课程ID</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createUsualJodForClassId" name="createUsualJodForClassId"
                               type="text"
                               value="${classId}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createUsualJodForClassName" class="control-label col-lg-2">课程名</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createUsualJodForClassName" name="createUsualJodForClassName"
                               type="text"
                               value="${className}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createUsualJodName" class="control-label col-lg-2">名称</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createUsualJodName" name="createJodName" type="text"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createUsualJodDetail" class="control-label col-lg-2">作业详情</label>

                    <div class="col-lg-10">
                        <textarea class="form-control " id="createUsualJodDetail" name="createJodDetail"></textarea>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="isNeedAnswerUsual" class="control-label col-lg-2 col-sm-3">是否需要作答?</label>

                    <div class="col-lg-10 col-sm-9">
                        <input type="checkbox" style="width: 20px" class="checkbox form-control" id="isNeedAnswerUsual"
                               name="isNeedAnswer"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label class="control-label col-sm-4" for="dp3">结束时间</label>

                    <div class="col-sm-6">
                        <input id="dp3" type="text" value=""
                               class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-danger" type="button" id="createUsualJobBtn">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<div class="row">
    <div class="col-lg-1">
    </div>
    <div class="col-lg-9" id="usuallyJobCreateDetail">
    </div>
</div>
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