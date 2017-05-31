<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">

    <form class="form-signin" id="form-join-class">
        <h2 class="form-signin-heading">加入课程</h2>

        <div class="login-wrap">
            <input type="number" class="form-control" placeholder="课程码" id="classNO" name="classNO" autofocus>
        </div>

        <div class="login-wrap">
            <input type="number" class="form-control" placeholder="手机号码" id="mobile" name="mobile"
                   autofocus>
        </div>
        <div class="login-wrap">
            <input type="number" class="form-control" placeholder="验证码" id="code" name="code">
        </div>
        <div class="login-wrap">
            <input type="number" class="form-control" placeholder="学号" id="studentNO" name="studentNO">
        </div>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="姓名" id="studentName" name="studentName">
        </div>
        <button class="btn btn-sm btn-login btn-block" id="btn-send" type="button">发送验证码</button>

        <button class="btn btn-sm btn-login btn-block" id="btn-join-class" type="button" disabled="disabled">确认加入
        </button>
    </form>
</div>

<script type="text/javascript" src="/assets/gritter/js/jquery.gritter.js"></script>
<script src="/js/join.js"></script>