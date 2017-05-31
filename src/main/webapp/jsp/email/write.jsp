<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <h5>写邮件</h5>
    <table class="table table-striped border-top" id="sample_1">
        <thead>
        <tr>
            <th hidden="hidden"></th>
            <th><i class="icon-tags"></i> 姓名</th>
            <c:if test="${roleType ==2}">
                <th><i class="icon-bookmark"></i>学号</th>
            </c:if>
            <th><i class="icon-bookmark"></i>邮箱</th>
        </tr>
        </thead>

        <tbody>
        <c:if test="${roleType ==2}">
            <c:forEach var="item" items="${students}">
                <tr class="items">
                    <td hidden="hidden" class="studentId">${item.studentId}</td>
                    <td class="studentName">${item.name}</td>
                    <td>${item.studentNo}</td>
                    <td class="email-address"><a href="#myEmailModal" data-toggle="modal" id="emailStudentAddress">${item.email}</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${roleType ==1}">
            <c:forEach var="item" items="${teachers}">
                <tr class="items">
                    <td hidden="hidden" class="teacherId">${item.teacherId}</td>
                    <td class="teacherName">${item.name}</td>
                    <td class="email-address"><a href="#myEmailModal" data-toggle="modal" id="emailTeacherAddress">${item.email}</a></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="inbox-body">
        <!-- Modal -->
        <div class="modal fade" id="myEmailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">&times;</button>
                        <h4 class="modal-title">写邮件</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form" enctype="multipart/form-data" id="emailForm">
                            <div class="form-group">
                                <label class="col-lg-2 control-label">发送给</label>

                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="emailToMan"
                                           placeholder="" readonly>
                                    <input type="text" class="form-control" id="emailToManId"
                                           placeholder="" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">邮箱</label>

                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="emailToAddress"
                                           placeholder="" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">主题</label>

                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="emailSubject " placeholder="">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="icon-plus icon-white"></i>
                                                        <span>附件</span>
                                                        <input type="file" multiple="" name="files[]" id="emailFileName">
                                                      </span>
                                    <button type="button" class="btn btn-send" id="submitEmail">发送</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
    </div>
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
<script src="/js/email.js"/>
