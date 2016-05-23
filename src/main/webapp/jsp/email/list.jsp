<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--mail inbox start-->
<div class="mail-box">
    <aside class="sm-side">
        <div class="user-head">
            <a href="javascript:;" class="inbox-avatar">
                <img src="/img/mail-avatar.jpg" alt="">
            </a>

            <div class="user-name">
                <h5><a href="#">${signBean.name}</a></h5>
                <span><a href="#">${signBean.email}</a></span>
            </div>
        </div>

        <ul class="inbox-nav inbox-divider">
            <li class="active">
                <a href="#"><i class="icon-inbox"></i> 收件箱 <span>${unReadEmailsCount}</span></a>

            </li>
            <li>
                <a href="#" id="wantSendMyEmail"><i class="icon-envelope-alt"></i> 写信</a>
            </li>
            <li>
                <a href="#" id="important"><i class="icon-bookmark-empty"></i> 星标邮件</a>
            </li>
            <li>
                <a href="#" id="send"><i class=" icon-trash"></i> 已发送</a>
            </li>
            <%--<li>--%>
                <%--<a href="#" id="trash"><i class=" icon-trash"></i> 垃圾箱</a>--%>
            <%--</li>--%>
        </ul>
        <ul class="nav nav-pills nav-stacked labels-info inbox-divider">
            <li><h4>标签</h4></li>
            <li><a href="#"> <i class=" icon-sign-blank text-danger"></i> 未读 </a></li>
            </li>
        </ul>
    </aside>
    <aside class="lg-side">
        <div class="inbox-head">
            <h3>邮箱</h3>

            <form class="pull-right position" action="#">
                <div class="input-append">
                    <input type="text" placeholder="查找" class="sr-input" id="selectCondition">
                    <button type="button" class="btn sr-btn" id="selectConditionBtn"><i class="icon-search"></i>
                    </button>
                </div>
            </form>
        </div>
        <div class="inbox-body" id="emailContant">
            <input hidden="hidden" value="${message}"/>
            <div class="mail-option">

                <div class="btn-group">
                    <a class="btn mini tooltips" href="#" data-toggle="dropdown" data-placement="top"
                       data-original-title="Refresh">
                        <i class=" icon-refresh"></i>
                    </a>
                </div>
                <%--<ul class="unstyled inbox-pagination">--%>
                <%--<li><span>1-50 of 234</span></li>--%>
                <%--<li>--%>
                <%--<a href="#" class="np-btn"><i class="icon-angle-left  pagination-left"></i></a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="#" class="np-btn"><i class="icon-angle-right pagination-right"></i></a>--%>
                <%--</li>--%>
                <%--</ul>--%>
            </div>
            <table class="table table-inbox table-hover">
                <tbody>
                <c:if test="${received == true}">
                    <c:forEach var="item" items="${unReadEmails}">
                        <tr>
                            <td class="inbox-small-cells"><i class="icon-star"></i></td>
                            <c:if test="roleType ==1">
                                <td class="view-message  dont-show">${item.teacherName}</td>
                            </c:if>
                            <c:if test="roleType ==2">
                                <td class="view-message  dont-show">${item.studentNo}</td>
                                <td class="view-message  dont-show">${item.studentName}</td>
                            </c:if>
                            <td class="view-message ">${item.subject}</td>
                            <c:if test="${not empty item.fileName}">
                                <td class="view-message readEmail"><span class="label label-danger pull-right">阅读</span></td>
                                <td class="view-message  inbox-small-cells emailFileName"><i class="icon-paper-clip">${item.fileName}</i></td>
                            </c:if>
                            <td class="view-message  text-right">${item.createTime}</td>
                        </tr>
                    </c:forEach>
                    <c:forEach var="item" items="${readedEmails}">
                        <tr>
                            <td class="inbox-small-cells"><i class="icon-star"></i></td>
                            <c:if test="roleType ==1">
                                <td class="view-message  dont-show">${item.teacherName}</td>
                            </c:if>
                            <c:if test="roleType ==2">
                                <td class="view-message  dont-show">${item.studentNo}</td>
                                <td class="view-message  dont-show">${item.studentName}</td>
                            </c:if>
                            <td class="view-message ">${item.subject}</td>
                            <c:if test="${not empty item.fileName}">
                                <td class="view-message readEmail"><span class="label label-danger pull-right">阅读</span></td>
                                <td class="view-message  inbox-small-cells emailFileName"><i class="icon-paper-clip"></i></td>
                            </c:if>
                            <td class="view-message  text-right">${item.createTime}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${important == true}">
                    <c:forEach var="item" items="${importantEmails}">
                        <tr>
                            <td class="inbox-small-cells"><i class="icon-star"></i></td>
                            <c:if test="roleType ==1">
                                <td class="view-message  dont-show">${item.teacherName}</td>
                            </c:if>
                            <c:if test="roleType ==2">
                                <td class="view-message  dont-show">${item.studentNo}</td>
                                <td class="view-message  dont-show">${item.studentName}</td>
                            </c:if>
                            <td class="view-message ">${item.subject}</td>
                            <c:if test="${item.readed == false}">
                                <td class="view-message readEmail"><span class="label label-danger pull-right">阅读</span>
                                </td>
                            </c:if>
                            <c:if test="${item.readed == false}">
                                <td class="view-message"><span class="label label-success pull-right">已读</span></td>
                            </c:if>
                            <c:if test="${not empty item.fileName}">
                                <td class="view-message  inbox-small-cells"><i class="icon-paper-clip"></i></td>
                            </c:if>
                            <td class="view-message  text-right">${item.createTime}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${delete == true}">
                    <c:forEach var="item" items="${readedEmails}">
                        <tr>
                            <td class="inbox-small-cells"><i class="icon-star"></i></td>
                            <c:if test="roleType ==1">
                                <td class="view-message  dont-show">${item.teacherName}</td>
                            </c:if>
                            <c:if test="roleType ==2">
                                <td class="view-message  dont-show">${item.studentNo}</td>
                                <td class="view-message  dont-show">${item.studentName}</td>
                            </c:if>
                            <td class="view-message ">${item.subject}</td>
                            <c:if test="${not empty item.fileName}">
                                <td class="view-message  inbox-small-cells"><i class="icon-paper-clip"></i></td>
                            </c:if>
                            <td class="view-message  text-right">${item.createTime}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${send == true}">
                    <c:forEach var="item" items="${readedEmails}">
                        <tr>
                            <td class="inbox-small-cells"><i class="icon-star"></i></td>
                            <c:if test="roleType ==1">
                                <td class="view-message  dont-show">${item.teacherName}</td>
                            </c:if>
                            <c:if test="roleType ==2">
                                <td class="view-message  dont-show">${item.studentNo}</td>
                                <td class="view-message  dont-show">${item.studentName}</td>
                            </c:if>
                            <td class="view-message ">${item.subject}</td>
                            <c:if test="${not empty item.fileName}">
                                <td class="view-message  inbox-small-cells"><i class="icon-paper-clip"></i></td>
                            </c:if>
                            <td class="view-message  text-right">${item.createTime}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
    </aside>
</div>
<!--mail inbox end-->
<script src="/js/jquery.js"></script>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>

<script src="/js/email.js"></script>
