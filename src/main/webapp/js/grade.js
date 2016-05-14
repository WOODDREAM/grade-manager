var Script = function () {
    var type = "";
    var typeSex = "";
    var reMobile = /^1[0-9]{10}/;
    var reEmail = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
    $('#login').on('click', function () {
        var mobile = $("#loginMobile").val();
        var passWord = $("#loginPassWord").val();

        if (mobile == "" || passWord == "") {
            $.gritter.add({
                title: '请填写!',
                text: '手机号或者密码必须填写！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (type == "") {
            $.gritter.add({
                title: '请选择!',
                text: '教师或者学生必须选择一个！',
                sticky: false,
                time: ''
            });
            return false;
        }
        $("#login_form").submit();
    });
    $('#loginTeacher').on('click', function () {
        type = $("#loginTeacher").val();
        var teacher = document.getElementById("loginTeacher");
        teacher.checked = true;
        var student = document.getElementById("loginStudent");
        student.checked = false;
    });
    $('#loginStudent').on('click', function () {
        type = $("#loginStudent").val();
        var teacher = document.getElementById("loginTeacher");
        teacher.checked = false;
        var student = document.getElementById("loginStudent");
        student.checked = this;
    });

    $('#signBoy').on('click', function () {
        typeSex = $("#signBoy").val();
        var signBoy = document.getElementById("signBoy");
        signBoy.checked = true;
        var signGirl = document.getElementById("signGirl");
        signGirl.checked = false;
    });
    $('#signGirl').on('click', function () {
        typeSex = $("#signGirl").val();
        var signBoy = document.getElementById("signBoy");
        signBoy.checked = false;
        var signGirl = document.getElementById("signGirl");
        signGirl.checked = this;
    });


    $('#a-signup').on('click', function () {
        window.location.href = "/user/signup.do";
    });
    $('#a-login').on('click', function () {
        window.location.href = "/user/login.do";
    });
    $('#a-forgetPassword').on('click', function () {
        window.location.href = "/user/forget_password.do";
    });
    $('#signupMobile').focus(function () {
        $.gritter.add({
            title: '警告!',
            text: '请确保手机号和邮箱正确，忘记密码时需验证登录！',
            sticky: false,
            time: ''
        });
    });
    $('#signEmail').focus(function () {
        $.gritter.add({
            title: '警告!',
            text: '请确保手机号和邮箱正确，忘记密码时需验证登录！',
            sticky: false,
            time: ''
        });
    });
    $('#btn-signup').on('click', function () {
        var name = $('#signName').val();
        var mobile = $('#signupMobile').val();
        var email = $('#signEmail').val()
        var password = $('#signPassword').val();
        var signConfirmPassword = $('#signConfirmPassword').val();
        var signSchool = $('#signSchool').val();
        if (name == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写姓名！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (mobile == "" || !reMobile.test(mobile)) {
            $.gritter.add({
                title: '警告!',
                text: '请填写正确的手机号！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (email == "" || !reEmail.test(email)) {
            $.gritter.add({
                title: '警告!',
                text: '请填写正确的邮箱！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (signSchool == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写学校！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (password == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写密码！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (signConfirmPassword == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填确认密码！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (signConfirmPassword != password) {
            $.gritter.add({
                title: '警告!',
                text: '密码不一致！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (typeSex == "") {
            $.gritter.add({
                title: '请选择!',
                text: '性别必须选择一个！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (type == "") {
            $.gritter.add({
                title: '请选择!',
                text: '教师或者学生必须选择一个！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var agree = document.getElementById("agree");
        if (!agree.checked) {
            $.gritter.add({
                title: '警告!',
                text: '请同意我们的条约！',
                sticky: false,
                time: ''
            });
            return false;
        }
        $('#signupForm').submit();
    });

    $('#btn-forget-send').on('click', function () {
        var mobile = $('#forgetMobile').val();
        if (mobile == "" || !reMobile.test(mobile)) {
            $.gritter.add({
                title: '警告!',
                text: '请填写正确的手机号！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var srt = {
            mobile: mobile
        }
        $.post("/sms/send_code.do", srt, function (data) {
            if (!!data) {
                if (data.code == 1) {
                    $.gritter.add({
                        title: '提示!',
                        text: '发送成功！',
                        sticky: false,
                        time: ''
                    });
                } else {
                    var message = data.message;
                    $.gritter.add({
                        title: '提示!',
                        text: message + "  请联系15757115785解决",
                        sticky: false,
                        time: ''
                    });
                }
            }
            console.log(data);
        });
    });

    $('#btn-forget-verify').on('click', function () {
        var mobile = $('#forgetMobile').val();
        var code = $('#forgetCode').val();
        if (mobile == "" || !reMobile.test(mobile)) {
            $.gritter.add({
                title: '警告!',
                text: '请填写正确的手机号！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (code == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写验证码！',
                sticky: false,
                time: ''
            });
            return false;
        }
        $('#form-forget-password').submit();
    });

}();

$().ready(function () {
    var message = $('#message').val();
    if (message != "") {
        $.gritter.add({
            title: '警告!',
            text: message,
            sticky: false,
            time: ''
        });
    }
});



