<%--
  Created by IntelliJ IDEA.
  User: liaoting
  Date: 2016-05-12
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Shoppy Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <!-- Custom Theme files -->
    <link href="/css/shoppy/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!--js-->
    <script src="/js/jquery-2.1.1.min.js"></script>

    <!--icons-css-->
    <link href="/css/font-awesome.css" rel="stylesheet">
    <link rel="shortcut icon" href="/images/favicon.ico">
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/assets/gritter/css/jquery.gritter.css"/>
    <!--Google Fonts-->
    <link href='http://fonts.useso.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
    <!--static chart-->
</head>
<body>
<div class="login-page">
    <div class="login-main">
        <div class="login-head">
            <h1>Login</h1>
        </div>
        <div class="login-block">
            <form id="login_form" action="/user/login" method="post">
                <input type="text" id="loginMobile" name="mobile" placeholder="Mobile" required="" autofocus>
                <input type="password" id="loginPassWord" name="passWord" class="lock" placeholder="Password"
                       required="">

                <div class="forgot-top-grids">
                    <div class="forgot-grid">
                        <ul>
                            <li>
                                <input type="checkbox" id="loginTeacher" name="type" value="2">
                                <label for="loginTeacher"><span></span>教师</label>
                            </li>
                            <li>
                                <input type="checkbox" id="loginStudent" name="type" value="1">
                                <label for="loginStudent"><span></span>学生</label>
                            </li>
                        </ul>
                    </div>
                    <div class="forgot">
                        <a href="#" id="a-forgetPassword">忘记密码?</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <button type="button" class="btn btn-info btn-lg btn-block" id="login">登录</button>
                <h3>没有账号?<a href="#" id="a-signup"> 注册</a></h3>
                <input type="hidden" id="message" value=${message}>
            </form>
        </div>
    </div>
</div>
<!--inner block end here-->
<!--copy rights start here-->
<div class="copyrights">
    <p>Copyright &copy; 2016.Company name All rights reserved.
    </p>
</div>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/grade.js"></script>
<!--COPY rights end here-->
</body>
</html>



