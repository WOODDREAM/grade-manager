<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <table class="table table-striped border-top" id="sample_1">
        <thead>
        <tr>
            <th hidden="hidden"></th>
            <th hidden="hidden"></th>
            <th><i class="icon-tags"></i> 作业名称</th>
            <th><i class="icon-time"></i> 提交日期</th>
            <th><i class="icon-legal"></i>成绩</th>
            <c:if test="${roleType ==2}">
                <th><i class="icon-star"></i> 学号</th>
                <th><i class="icon-star"></i> 姓名</th>
            </c:if>
            <th><i class="icon-bookmark"></i> 所属课程</th>
            <th><i class="icon-bookmark"></i>课程号</th>
            <th><i class="icon-arrow-down"></i>下载答案</th>
            <c:if test="${roleType ==1}">
                <th><i class="icon-bookmark"></i>教师姓名</th>
                <th><i class="icon-bookmark"></i>已作答?</th>
            </c:if>
            <c:if test="${roleType ==2}">
                <th><i class="icon-pencil"></i>录入成绩</th>

            </c:if>
            <c:if test="${roleType ==1}">
                <th><i class=" icon-edit-sign"></i>作答</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${answers}">
            <tr class="items">
                <td hidden="hidden" class="answerId">${item.answerId}</td>
                <td hidden="hidden" class="answerContent">${item.answer}</td>
                <td class="classItemName">${item.jobName}</td>
                <td>${item.createTime}</td>
                <td class="grade-answer">${item.grade}</td>
                <c:if test="${roleType ==2}">
                    <td>${item.studentNo}</td>
                    <td>${item.studentName}</td>
                </c:if>
                <td>${item.className}</td>
                <td>${item.classNo}</td>
                <td>
                    <c:if test="${item.answered ==true}">
                        <button class="btn btn-info btn-xs downAnswerbtn" name="downAnswerbtn"><i
                                class="icon-arrow-down"></i>
                        </button>
                    </c:if>
                    <c:if test="${item.answered ==false}">
                        <button class="btn btn-info btn-xs downAnswerbtn" name="downAnswerbtn" disabled><i
                                class="icon-arrow-down"></i>
                        </button>
                    </c:if>
                </td>
                <c:if test="${roleType ==1}">
                    <td>${item.teacherName}</td>
                    <c:if test="${item.answered == true}">
                        <td>是</td>
                    </c:if>
                    <c:if test="${item.answered == false}">
                        <td>否</td>
                    </c:if>
                </c:if>
                <c:if test="${roleType ==2}">
                    <td>
                        <a class="btn btn-info createGradebtn" data-toggle="modal" href="#myModal2"><i</a>
                    </td>

                </c:if>
                <c:if test="${roleType ==1}">
                    <td>
                        <c:if test="${item.timeEnded == false}">
                            <c:if test="${item.answered ==true}">
                                <a class="btn btn-info jobCreateAnswer" data-toggle="modal" href="#myModal">重答</a>
                            </c:if>
                            <c:if test="${item.answered ==false}">
                                <a class="btn btn-info jobCreateAnswer" data-toggle="modal" href="#myModal">作答</a>
                                <%--<button class="btn btn-info btn-xs jobCreateAnswer"><i--%>
                                <%--class=" icon-edit-sign"></i></button>--%>
                            </c:if>
                        </c:if>
                        <c:if test="${item.timeEnded == true}">
                            <button class="btn btn-success btn-xs jobCreateAnswer" disabled>作答</button>
                        </c:if>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title">提交</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="fileForm" action="/upload"
                          enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                 <span class="btn green fileinput-button">
                                     <input type="hidden" id="answerIdmm">
                                 </span>
                                <span class="btn green fileinput-button">
                                    <input type="file" multiple="" name="files[]" id="myFile">
                                </span>
                                <button type="submit" class="btn btn-send" id="submitFile">上传</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title">提交</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="gradeForm">
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                 <span class="btn green fileinput-button">
                                     <input type="hidden" class="form-control" name="answerIdmm" id="answerIdForGrade">
                                 </span>
                                <span class="btn green fileinput-button">
                                    <input type="text" multiple="" name="grade" id="grade">
                                </span>
                                <button type="button" class="btn btn-send" id="submitGrade">确认</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
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
<script src="/js/answer.js"/>
