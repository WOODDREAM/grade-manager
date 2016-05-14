<%--
  Created by IntelliJ IDEA.
  User: liaoting
  Date: 2016-05-14
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Forget</title>
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
<link href="/css/style.css" rel="stylesheet">
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
<body class="login-body">

<div class="container">

    <form class="form-signin" action="/user/verify/login" id="form-forget-password" method="post">
        <h2 class="form-signin-heading">验证码登录</h2>

        <div class="login-wrap">
            <input type="number" class="form-control" placeholder="mobile" id="forgetMobile" name="mobile"
                   autofocus>
        </div>
        <div class="login-wrap">
            <input type="number" class="form-control" placeholder="验证码" id="forgetCode" name="code">
        </div>
        <input type="hidden" id="message" value=${message}>

        <button class="btn btn-sm btn-login btn-block" id="btn-forget-send" type="button">发送验证码</button>

        <button class="btn btn-sm btn-login btn-block" id="btn-forget-verify" type="button">验证</button>
    </form>
    <div class="copyrights">
        <p>Copyright &copy; 2016.Company name All rights reserved.
        </p>
    </div>
</div>
<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/grade.js"></script>
</body>
</html>
