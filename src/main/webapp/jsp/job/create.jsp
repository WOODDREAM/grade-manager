<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="panel">
    <header class="panel-heading">
        创建作业
    </header>
    <div class="panel-body">
        <div class="form">
            <form class="cmxform form-horizontal tasi-form" id="createJodForm">
                <div class="form-group ">
                    <label for="createJodName" class="control-label col-lg-2">课程ID</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createJodForClassId" name="createJodForClassId" type="text"
                               value="${classId}" readonly/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createJodName" class="control-label col-lg-2">名称</label>

                    <div class="col-lg-10">
                        <input class=" form-control" id="createJodName" name="createJodName" type="text"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="createJodDetail" class="control-label col-lg-2">作业详情</label>

                    <div class="col-lg-10">
                        <textarea class="form-control " id="createJodDetail" name="createJodDetail"></textarea>
                    </div>
                </div>
                <div class="form-group ">
                    <label for="isNeedAnswer" class="control-label col-lg-2 col-sm-3">是否需要作答?</label>

                    <div class="col-lg-10 col-sm-9">
                        <input type="checkbox" style="width: 20px" class="checkbox form-control" id="isNeedAnswer"
                               name="isNeedAnswer"/>
                    </div>
                </div>
                <div class="form-group ">
                    <label class="control-label col-sm-4" for="dp2">结束时间</label>

                    <div class="col-sm-6">
                        <input id="dp2" type="text" value=""
                               class="form-control">
                    </div>
                </div>
                <div class="form-group ">
                    <header class="panel-heading">
                        考试类型不允许作答，只用于帮助教师记录考试成绩
                    </header>
                    <label for="usuallyJobType" class="control-label col-lg-2 col-sm-3">作业</label>

                    <div class="col-lg-10 col-sm-9">
                        <input type="checkbox" style="width: 20px" class="checkbox form-control" id="usuallyJobType"
                               name="type" value="1"/>
                    </div>
                    <label for="termJobType" class="control-label col-lg-2 col-sm-3">考试</label>

                    <div class="col-lg-10 col-sm-9">
                        <input type="checkbox" style="width: 20px" class="checkbox form-control" id="termJobType"
                               name="type" value="2"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-danger" type="button" id="createJobBtn">Save</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap-switch.js"></script>

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