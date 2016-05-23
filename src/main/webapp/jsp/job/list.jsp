<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- page start-->
<section class="panel">
    <header class="panel-heading">
        我的作业
    </header>
    <table class="table table-striped border-top" id="job_list_table">
        <thead>
        <tr>
            <th hidden="hidden"></th>
            <th><i class="icon-tags"></i> 名称</th>
            <th><i class="icon-time"></i> 截止日期</th>
            <th><i class="icon-star"></i> 需作答否</th>
            <th><i class="icon-bookmark"></i> 作业类型</th>
            <th><i class="icon-bookmark"></i>所属课程</th>
            <c:if test="${roleType ==1}">
                <th hidden="hidden"></th>
                <th><i class="icon-bookmark"></i>教师姓名</th>
                <th><i class="icon-bookmark"></i>成绩</th>
                <th><i class="icon-bookmark"></i>已作答?</th>
            </c:if>
            <th><i class="icon-zoom-out"></i>查看</th>
            <c:if test="${roleType ==2}">
                <th><i class="icon-pencil"></i>编辑</th>
            </c:if>
            <c:if test="${roleType ==2}">
                <th><i class="icon-legal"></i>成绩</th>
            </c:if>
            <c:if test="${roleType ==1}">
                <th><i class="icon-edit-sign"></i>作答</th>
                <th><i class="icon-arrow-down"></i>下载</th>
            </c:if>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="item" items="${jobList}">
            <tr class="items">
                <td hidden="hidden" class="jobId">${item.jobId}</td>
                <td>${item.name}</td>
                <td>${item.endTime}</td>
                <c:if test="${item.answer ==true}">
                    <td>是</td>
                </c:if>
                <c:if test="${item.answer ==false}">
                    <td>否</td>
                </c:if>
                <c:if test="${item.type ==1}">
                    <td>平时作业</td>
                </c:if>
                <c:if test="${item.type ==2}">
                    <td>考试记录</td>
                </c:if>
                <c:if test="${item.type ==3}">
                    <td>课堂记录</td>
                </c:if>
                <td>${item.className}</td>
                <c:if test="${roleType ==1}">
                    <td hidden="hidden" class="answerContent">${item.answerContent}</td>
                    <td>${item.teacherName}</td>
                    <td>${item.grade}</td>
                    <c:if test="${item.answered ==true}">
                        <td>是</td>
                    </c:if>
                    <c:if test="${item.answered ==false}">
                        <td>否</td>
                    </c:if>
                </c:if>
                <td>
                    <button class="btn btn-success btn-xs jobFindBtn"><i class="icon-eye-open"></i></button>
                </td>
                <c:if test="${roleType ==2}">
                    <td>
                        <button class="btn btn-primary btn-xs jobUpdateBtn"><i class="icon-pencil"></i></button>
                        <button class="btn btn-danger btn-xs jobDeleteBtn"><i class="icon-trash "></i></button>
                    </td>
                </c:if>
                <c:if test="${roleType ==2}">
                    <td>
                        <button class="btn btn-danger btn-xs jobFindGradeBtn"><i class="icon-legal"></i></button>
                    </td>
                </c:if>
                <c:if test="${roleType ==1}">
                    <td>
                        <c:if test="${item.answer ==true and item.timeEnded == false}">
                            <c:if test="${item.answered ==true}">
                                <a class="btn btn-info jobCreateAnswer" data-toggle="modal" href="#myModal">重答</a>
                                <%--<button class="btn btn-success btn-xs jobCreateAnswer"><i--%>
                                <%--class=" icon-eraser"></i></button>--%>
                            </c:if>
                            <c:if test="${item.answered ==false}">
                                <a class="btn btn-info jobCreateAnswer" data-toggle="modal" href="#myModal">作答</a>
                                <%--<button class="btn btn-info btn-xs jobCreateAnswer"><i--%>
                                <%--class=" icon-edit-sign"></i></button>--%>
                            </c:if>
                        </c:if>
                        <c:if test="${item.answer ==false or item.timeEnded == true}">
                            <button class="btn btn-success btn-xs jobCreateAnswer" disabled>作答</button>
                        </c:if>
                    </td>

                    <td>
                        <c:if test="${item.answer ==true or item.answered == true}">
                            <c:if test="${item.answered ==true}">
                                <button class="btn btn-success btn-xs downLoadAnswer"><i class=" icon-arrow-down">下载</i>
                                </button>
                            </c:if>
                            <c:if test="${item.answered ==false}">
                                <button class="btn btn-success btn-xs" disabled><i class=" icon-arrow-down">下载</i>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${item.answer ==false}">
                            <button class="btn btn-success btn-xs" disabled>作答</button>
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
                    <form class="form-horizontal" role="form" id="fileForm" action=""
                          enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <input type="hidden" class="form-control" name="jobId" id="jobIdmm">
                                <span class="btn green fileinput-button">
                                    <input type="file" multiple="" name="files[]" id="myFile">
                                </span>
                                <button type="button" class="btn btn-send" id="submitFile">上传</button>
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
    <div class="col-lg-9" id="jobDetail">
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
<script src="/js/dynamic-job-table.js"></script>
<script src="/js/job.js"/>