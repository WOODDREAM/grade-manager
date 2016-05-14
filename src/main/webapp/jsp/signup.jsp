<%--
  Created by IntelliJ IDEA.
  User: liaoting
  Date: 2016-05-12
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
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
    <link href="/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="/assets/gritter/css/jquery.gritter.css"/>
    <link rel="shortcut icon" href="/images/favicon.ico">
    <!--Google Fonts-->
    <link href='http://fonts.useso.com/css?family=Carrois+Gothic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Work+Sans:400,500,600' rel='stylesheet' type='text/css'>
    <!--//charts-->
</head>
<body>
<!--inner block start here-->
<div class="signup-page-main">
    <div class="signup-main">
        <div class="signup-head">
            <h1>Sign Up</h1>
        </div>
        <div class="signup-block">
            <form id="signupForm" action="/user/signup" method="post">
                <input type="text" name="name" id="signName" placeholder="Name" autofocus>
                <input type="text" name="mobile" id="signupMobile" class="lock" placeholder="Mobile">
                <input type="text" name="email" id="signEmail" class="lock" placeholder="Email">
                <input type="text" name="school" id="signSchool" class="lock" placeholder="School">
                <input type="password" name="passWord" id="signPassword" class="lock" placeholder="Password">
                <input type="password" name="signConfirmPassword" id="signConfirmPassword" class="lock"
                       placeholder="Confirm password">

                <div class="row">
                    <div class="forgot-grid col-lg-4">
                        <ul>
                            <li>
                                <input type="checkbox" id="signBoy" name="sex" value="1">
                                <label for="signBoy"><span></span>男</label>
                            </li>
                            <li>
                                <input type="checkbox" id="signGirl" name="sex" value="2">
                                <label for="signGirl"><span></span>女</label>
                            </li>
                        </ul>
                    </div>
                    <div class="forgot-grid col-lg-4">
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
                </div>
                <div class="row">
                    <div class="forgot-grid col-lg-4">
                        <ul>
                            <li>
                                <input type="checkbox" id="agree" value="">
                                <label for="agree"><span></span>同意本系统条约</label>
                            </li>
                        </ul>
                    </div>
                </div>
                <button type="button" class="btn btn-info btn-lg btn-block" id="btn-signup">注册</button>
            </form>
            <div class="sign-down">
                <input type="hidden" id="message" value=${message}>
                <h4>已经有账号? <a href="#" id="a-login"> 直接登录</a></h4>
            </div>
        </div>
    </div>
</div>
<!--inner block end here-->
<!--copy rights start here-->
<div class="copyrights">
    <p>Copyright &copy; 2016.Company name All rights reserved.</p>
</div>
<!--COPY rights end here-->
<!--scrolling js-->
<script src="/js/jquery.nicescroll.js"></script>
<script src="/js/scripts.js"></script>
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<!--//scrolling js-->
<script src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/grade.js"></script>
<!-- mother grid end here-->
</body>
</html>





