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
            <form id="login_form" action="/login.do" method="post">
                <input type="text" id="loginMobile" name="mobile" placeholder="Mobile">
                <input type="password" id="loginPassWord" name="password" class="lock" placeholder="Password">
                <div class="forgot-top-grids">
                    <div class="forgot-grid">
                        <ul>
                            <li>
                                <input type="checkbox" id="loginTeacher" name="type" value="1">
                                <label for="loginTeacher"><span></span>TEACHER?</label>
                            </li>
                        </ul>
                    </div>
                    <div class="forgot">
                        <a href="#">忘记密码?</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <input type="submit" name="Sign In" value="Login" id="loginIn">
                <h3>没有账号?<a href="signup.html"> 注册</a></h3>
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
<!--COPY rights end here-->

<!--scrolling js-->
<script src="/js/grade.js"/>
<script src="/js/jquery.nicescroll.js"></script>
<script src="/js/scripts.js"></script>
<!--<script src="js/grade.js"/>-->
<!--//scrolling js-->
<script src="/js/bootstrap.js"></script>

<!-- mother grid end here-->
</body>
</html>



