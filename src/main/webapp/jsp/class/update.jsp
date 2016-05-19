<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="panel">
    <div class="panel-body">
        <div class="row">
            <section class="panel">
                <header class="panel-heading">
                    更新课程
                </header>
                <div class="panel-body">
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
                                                <label for="classTeacherName"
                                                       class="control-label col-lg-2">教师名称</label>

                                                <div class="col-lg-10">
                                                    <input class=" form-control" id="classTeacherName" name="className"
                                                           type="text" value="${classDetail.teacherName}" readonly/>
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="classId" class="control-label col-lg-2">名称</label>

                                                <div class="col-lg-10">
                                                    <input class=" form-control" id="classId" name="classId"
                                                           type="text" value="${classDetail.classId}" readonly/>
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="className" class="control-label col-lg-2">名称</label>

                                                <div class="col-lg-10">
                                                    <input class=" form-control" id="className" name="className"
                                                           type="text" value="${classDetail.name}" autofocus=""/>
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="classPeriod"
                                                       class="control-label col-lg-2">学时</label>

                                                <div class="col-lg-10">
                                                    <input class="form-control " id="classPeriod" name="classPeriod"
                                                           value="${classDetail.period}" type="text"/>
                                                </div>
                                            </div>
                                            <div class="form-group ">
                                                <label for="classCredit" class="control-label col-lg-2">学分</label>

                                                <div class="col-lg-10">
                                                    <input class="form-control " id="classCredit" name="classCredit"
                                                           value="${classDetail.credit}" type="text"/>
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
                                                    <input id="dp3" type="text" value="${classDetail.startTime}"
                                                           class="form-control">
                                                </div>
                                                <label class="control-label col-sm-4" for="dp2">结束时间</label>

                                                <div class="col-sm-6">
                                                    <input id="dp2" type="text" value="${classDetail.endTime}"
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
            </section>
        </div>
        <div class="row">
            <div class="col-sm-12 checkboxes">
                <section class="panel">
                    <header class="panel-heading no-border">
                        上课时间
                    </header>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>节次\星期</th>
                            <th>星期一</th>
                            <th>星期二</th>
                            <th>星期三</th>
                            <th>星期四</th>
                            <th>星期五</th>
                            <th>星期六</th>
                            <th>星期日</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td><input name="sample-checkbox-02" id="checkbox-11" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-12" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-13" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-14" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-15" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-16" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-17" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td><input name="sample-checkbox-02" id="checkbox-21" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-22" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-23" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-24" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-25" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-26" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-27" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td><input name="sample-checkbox-02" id="checkbox-31" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-32" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-33" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-34" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-35" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-36" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-37" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td><input name="sample-checkbox-02" id="checkbox-41" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-42" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-43" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-44" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-45" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-46" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-47" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td><input name="sample-checkbox-02" id="checkbox-51" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-52" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-53" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-54" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-55" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-56" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-57" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td><input name="sample-checkbox-02" id="checkbox-61" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-62" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-63" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-64" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-65" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-66" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-67" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td><input name="sample-checkbox-02" id="checkbox-71" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-72" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-73" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-74" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-75" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-76" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-77" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td><input name="sample-checkbox-02" id="checkbox-81" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-82" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-83" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-84" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-85" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-86" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-87" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td><input name="sample-checkbox-02" id="checkbox-91" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-92" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-93" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-94" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-95" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-96" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-97" value="1" type="checkbox"/></td>


                        </tr>
                        <tr>
                            <td>10</td>
                            <td><input name="sample-checkbox-02" id="checkbox-101" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-102" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-103" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-104" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-105" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-106" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-107" value="1" type="checkbox"/></td>


                        </tr>
                        <tr>
                            <td>11</td>
                            <td><input name="sample-checkbox-02" id="checkbox-111" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-112" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-113" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-114" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-115" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-116" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-117" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td><input name="sample-checkbox-02" id="checkbox-121" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-122" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-123" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-124" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-125" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-126" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-127" value="1" type="checkbox"/></td>
                        </tr>
                        <tr>
                            <td>13</td>
                            <td><input name="sample-checkbox-02" id="checkbox-131" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-132" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-133" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-134" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-135" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-136" value="1" type="checkbox"/></td>
                            <td><input name="sample-checkbox-02" id="checkbox-137" value="1" type="checkbox"/></td>
                        </tr>
                        </tbody>
                    </table>

                </section>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-1 col-lg-10">
                    <button class="btn btn-danger btn-lg" type="button" id="btn-class-update">确定</button>
                </div>
            </div>
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
<script src="/js/class.js"></script>



