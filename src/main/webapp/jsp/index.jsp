<%--
  Created by IntelliJ IDEA.
  User: liaoting
  Date: 2016-05-12
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="yrm" content="Mosaddek">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <link rel="shortcut icon" href="/images/favicon.ico">

    <title>平时成绩管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-reset.css" rel="stylesheet">
    <!--icons-css-->
    <%--<link href="/css/font-awesome.css" rel="stylesheet">--%>
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/assets/gritter/css/jquery.gritter.css"/>

    <link rel="stylesheet" type="text/css" href="/assets/bootstrap-datepicker/css/datepicker.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/bootstrap-colorpicker/css/colorpicker.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/bootstrap-daterangepicker/daterangepicker.css"/>

    <!--external css-->
    <link href="/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css"
          media="screen"/>
    <link rel="stylesheet" href="/css/owl.carousel.css" type="text/css">
    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<section id="container" class="">
    <!--header start-->
    <header class="header white-bg">
        <div class="sidebar-toggle-box">
            <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
        </div>
        <!--logo start-->
        <a href="#" class="logo"><span>Manager</span></a>
        <!--logo end-->
        <div class="top-nav ">
            <!--search & user info start-->
            <ul class="nav pull-right top-menu">
                <%--<li>--%>
                <%--<input type="text" class="form-control search" placeholder="Search">--%>
                <%--</li>--%>
                <!-- user login dropdown start-->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <img alt="" src="/img/avatar1_small.jpg">
                        <span class="username">${person.name}</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu extended logout">
                        <div class="log-arrow-up"></div>
                        <li><a href="#"><i class=" icon-suitcase"></i>个人档案</a></li>
                        <li><a href="#"><i class="icon-cog"></i> 修改个人信息</a></li>
                        <li><a href="#"><i class="icon-bell-alt"></i>通知</a></li>
                        <li><a href="#" id="a-login-out"><i class="icon-key"></i>退出登录</a></li>
                    </ul>
                </li>
                <!-- user login dropdown end -->
            </ul>
            <!--search & user info end-->
        </div>
    </header>

    <!--header end-->
    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li id="li-main">
                    <a class="" href="#" id="a-main">
                        <i class="icon-dashboard"></i>
                        <span>待处理</span>
                    </a>
                </li>
                <li class="sub-menu" id="li-class-info">
                    <a href="javascript:;" class="" id="a-class-info">
                        <i class="icon-group" id="li-class-info-child"></i>
                        <span>课程信息</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">
                        <li id="li-class-list"><a class="" href="#" id="a-class-list">课程列表</a></li>
                        <c:if test="${type ==2}">
                            <li id="li-class-create"><a class="" href="#" id="a-class-create">添加课程</a></li>
                        </c:if>
                        <c:if test="${type ==1}">
                            <li id="li-class-join"><a class="" href="#" id="a-class-join">加入课程</a></li>
                        </c:if>
                        <li><a class="" href="charts.html">数据统计</a></li>
                    </ul>
                </li>
                <li class="sub-menu" id="li-job-info">
                    <a href="javascript:;" class="" id="a-job-info">
                        <i class="icon-cogs" id="li-job-info-child"></i>
                        <span>作业信息</span>
                        <span class="arrow"></span>
                    </a>
                    <ul class="sub">
                        <li id="li-job-list"><a class="" href="#" id="a-job-list">作业列表</a></li>
                        <c:if test="${type ==2}">
                            <li id="li-job-create"><a class="" href="#" id="a-job-create">添加作业</a></li>
                        </c:if>
                        <li><a class="" href="#">数据统计</a></li>
                    </ul>
                </li>

                <li id="li-email-list">
                    <a class="" href="#" id="a-email-list">
                        <i class="icon-envelope"></i>
                        <span>邮箱</span>
                        <span class="label label-danger pull-right mail-info">${unreadEmailCount}</span>
                    </a>
                </li>
                <li class="sub-menu" id="li-person-info">
                    <a href="#" class="" id="a-person-info">
                        <i class="icon-user"></i>
                        <span>个人信息</span>
                    </a>
                </li>
                <%--<li id="li-comment">--%>
                <%--<a class="" href="#" id="a-comment">--%>
                <%--<i class="icon-comment"></i>--%>
                <%--<span>课堂聊天</span>--%>
                <%--</a>--%>
                <%--</li>--%>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div><input type="hidden" value="${type}" id="type"/></div>
                <div class="col-lg-12" id="myContainer">
                    <div><input type="hidden" value="${message}" id="message"/></div>
                    <div class="row col-lg-2"></div>
                    <div class="row col-lg-8">
                        <aside class="profile-nav col-lg-3">
                            <section class="panel">
                                <div class="user-heading round">
                                    <%--<a href="#">--%>
                                    <%--<img src="/img/profile-avatar.jpg" alt="">--%>
                                    <%--</a>--%>

                                    <h1>${person.name}</h1>

                                    <p>${person.email}</p>
                                </div>
                            </section>
                        </aside>
                        <aside class="profile-info col-lg-9">
                            <section class="panel">
                                <div class="panel-body bio-graph-info">
                                    <h1>个人档案</h1>

                                    <div class="row">
                                        <div class="bio-row">
                                            <p><span>姓名 </span>: ${person.name}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>邮箱 </span>: ${person.email}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>手机号码 </span>: ${person.mobile}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>所在学校 </span>: ${person.school}</p>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </aside>
                    </div>

                </div>
            </div>

        </section>
    </section>
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/js/jquery.js"></script>
<%--<script src="/js/jquery-1.8.3.min.js"></script>--%>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.scrollTo.min.js"></script>
<script src="/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/js/jquery.sparkline.js" type="text/javascript"></script>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="/js/owl.carousel.js"></script>
<script src="/js/jquery.customSelect.min.js"></script>

<!--common script for all pages-->
<%--<script src="/js/common-scripts.js"></script>--%>

<!--script for this page-->
<script src="/js/sparkline-chart.js"></script>
<script src="/js/easy-pie-chart.js"></script>
<script src="/js/asider.js"></script>
</body>
</html>

