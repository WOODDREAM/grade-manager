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
        <div class="nav notify-row" id="top_menu">
            <!--  notification start -->
            <ul class="nav top-menu">
                <!-- settings start -->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-tasks"></i>
                        <span class="badge bg-success">6</span>
                    </a>
                    <ul class="dropdown-menu extended tasks-bar">
                        <div class="notify-arrow notify-arrow-green"></div>
                        <li>
                            <p class="green">You have 6 pending tasks</p>
                        </li>
                        <li>
                            <a href="#">
                                <div class="task-info">
                                    <div class="desc">Dashboard v1.3</div>
                                    <div class="percent">40%</div>
                                </div>
                                <div class="progress progress-striped">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                        <span class="sr-only">40% Complete (success)</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="task-info">
                                    <div class="desc">Database Update</div>
                                    <div class="percent">60%</div>
                                </div>
                                <div class="progress progress-striped">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (warning)</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="task-info">
                                    <div class="desc">Iphone Development</div>
                                    <div class="percent">87%</div>
                                </div>
                                <div class="progress progress-striped">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 87%">
                                        <span class="sr-only">87% Complete</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="task-info">
                                    <div class="desc">Mobile App</div>
                                    <div class="percent">33%</div>
                                </div>
                                <div class="progress progress-striped">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 33%">
                                        <span class="sr-only">33% Complete (danger)</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="task-info">
                                    <div class="desc">Dashboard v1.3</div>
                                    <div class="percent">45%</div>
                                </div>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar" role="progressbar" aria-valuenow="45" aria-valuemin="0"
                                         aria-valuemax="100" style="width: 45%">
                                        <span class="sr-only">45% Complete</span>
                                    </div>
                                </div>

                            </a>
                        </li>
                        <li class="external">
                            <a href="#">See All Tasks</a>
                        </li>
                    </ul>
                </li>
                <!-- settings end -->
                <!-- inbox dropdown start-->
                <li id="header_inbox_bar" class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-envelope-alt"></i>
                        <span class="badge bg-important">5</span>
                    </a>
                    <ul class="dropdown-menu extended inbox">
                        <div class="notify-arrow notify-arrow-red"></div>
                        <li>
                            <p class="red">你有5个未读邮件</p>
                        </li>
                        <li>
                            <a href="#">
                                <span class="photo"><img alt="avatar" src="/img/avatar-mini.jpg"></span>
                                    <span class="subject">
                                    <span class="from">Jonathan Smith</span>
                                    <span class="time">Just now</span>
                                    </span>
                                    <span class="message">
                                        Hello, this is an example msg.
                                    </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="photo"><img alt="avatar" src="/img/avatar-mini2.jpg"></span>
                                    <span class="subject">
                                    <span class="from">Jhon Doe</span>
                                    <span class="time">10 mins</span>
                                    </span>
                                    <span class="message">
                                     Hi, Jhon Doe Bhai how are you ?
                                    </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="photo"><img alt="avatar" src="/img/avatar-mini3.jpg"></span>
                                    <span class="subject">
                                    <span class="from">Jason Stathum</span>
                                    <span class="time">3 hrs</span>
                                    </span>
                                    <span class="message">
                                        This is awesome dashboard.
                                    </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="photo"><img alt="avatar" src="/img/avatar-mini4.jpg"></span>
                                    <span class="subject">
                                    <span class="from">Jondi Rose</span>
                                    <span class="time">Just now</span>
                                    </span>
                                    <span class="message">
                                        Hello, this is metrolab
                                    </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">See all messages</a>
                        </li>
                    </ul>
                </li>
                <!-- inbox dropdown end -->
                <!-- notification dropdown start-->
                <li id="header_notification_bar" class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                        <i class="icon-bell-alt"></i>
                        <span class="badge bg-warning">7</span>
                    </a>
                    <ul class="dropdown-menu extended notification">
                        <div class="notify-arrow notify-arrow-yellow"></div>
                        <li>
                            <p class="yellow">你有7条短信未读</p>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-danger"><i class="icon-bolt"></i></span>
                                Server #3 overloaded.
                                <span class="small italic">34 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-warning"><i class="icon-bell"></i></span>
                                Server #10 not respoding.
                                <span class="small italic">1 Hours</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-danger"><i class="icon-bolt"></i></span>
                                Database overloaded 24%.
                                <span class="small italic">4 hrs</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-success"><i class="icon-plus"></i></span>
                                New user registered.
                                <span class="small italic">Just now</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-info"><i class="icon-bullhorn"></i></span>
                                Application error.
                                <span class="small italic">10 mins</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">See all notifications</a>
                        </li>
                    </ul>
                </li>
                <!-- notification dropdown end -->
            </ul>
            <!--  notification end -->
        </div>
        <div class="top-nav ">
            <!--search & user info start-->
            <ul class="nav pull-right top-menu">
                <li>
                    <input type="text" class="form-control search" placeholder="Search">
                </li>
                <!-- user login dropdown start-->
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <img alt="" src="/img/avatar1_small.jpg">
                        <span class="username">Jhon Doue</span>
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
                        <span>综合信息页</span>
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
                        <span>邮件</span>
                        <span class="label label-danger pull-right mail-info">2</span>
                    </a>
                </li>
                <li class="sub-menu" id="li-person-info">
                    <a href="#" class="" id="a-person-info">
                        <i class="icon-user"></i>
                        <span>个人信息</span>
                    </a>
                </li>
                <li id="li-comment">
                    <a class="" href="#" id="a-comment">
                        <i class="icon-comment"></i>
                        <span>课堂聊天</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-1">
                </div>
                <div class="col-lg-4">
                    <h4>今天</h4>
                    <section class="panel">
                        <div class="weather-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <i class="icon-tint"></i>
                                        雨
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="degree">
                                            24°
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <footer class="weather-category">
                            <ul>
                                <li class="active">
                                    <h5>湿度</h5>
                                    56%
                                </li>
                                <li>
                                    <h5>降雨量</h5>
                                    1.50 in
                                </li>
                                <li>
                                    <h5>风速</h5>
                                    10 mph
                                </li>
                            </ul>
                        </footer>
                    </section>
                </div>
                <div class="col-lg-1">
                </div>
                <div class="col-lg-4">
                    <h4>明天</h4>
                    <section class="panel">
                        <div class="weather-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <i class="icon-cloud"></i>
                                        阴
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="degree">
                                            14°
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <footer class="weather-category">
                            <ul>
                                <li class="active">
                                    <h5>湿度</h5>
                                    56%
                                </li>
                                <li>
                                    <h5>降雨量</h5>
                                    1.50 in
                                </li>
                                <li>
                                    <h5>风速</h5>
                                    10 mph
                                </li>
                            </ul>
                        </footer>
                    </section>
                </div>
            </div>
            <div class="row">
                <div><input type="hidden" value="${type}" id="type"/></div>
                <div class="col-lg-12" id="myContainer">
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
<script src="/js/common-scripts.js"></script>

<!--script for this page-->
<script src="/js/sparkline-chart.js"></script>
<script src="/js/easy-pie-chart.js"></script>
<script src="/js/asider.js"></script>
</body>
</html>

