var Script = function () {
    var reMobile = /^1[0-9]{10}/;
    var reEmail = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
    $('#submitEditInfo').on('click', function () {
        var name = $("#person-name").val();
        var school = $("#person-school").val();
        var email = $("#person-email").val();
        if (name == "" && school == "" && email == "") {
            $.gritter.add({
                title: '请填写!',
                text: '没有填写修改项！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (email != "" && !reEmail.test(email)) {
            $.gritter.add({
                title: '警告!',
                text: '请填写正确的邮箱！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var str = {
            name: name,
            school: school,
            email: email
        };
        $.post("/user/updateInfo.do", str, function (data) {
            if (data.code == 1) {
                $.gritter.add({
                    title: '提示!',
                    text: '修改成功！',
                    sticky: false,
                    time: ''
                });
                var i = document.getElementById("indexUserName");
                i.innerHTML = data.data.name;
                var n = document.getElementById("personNamed");
                n.innerHTML = data.data.name;
                var m = document.getElementById("personSchooled");
                m.innerHTML = data.data.school;
                var e = document.getElementById("personEmailed");
                e.innerHTML = data.data.email;
            } else {
                alert("失败");
                $.gritter.add({
                    title: '提示!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });
    $('#submitPassword').on('click', function () {
        var npass = $("#n-password").val();
        var cpass = $("#confirm_password").val();

        if (npass == "" || cpass == "") {
            $.gritter.add({
                title: '请填写!',
                text: '没有填写修改项！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (npass != cpass) {
            $.gritter.add({
                title: '警告!',
                text: '两次填写不一致！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var str = {
            password: npass
        }
        $.post("/user/updatePassword.do", str, function (data) {
            if (data.code == 1) {
                $.gritter.add({
                    title: '提示!',
                    text: '修改成功！',
                    sticky: false,
                    time: ''
                });
            } else {
                $.gritter.add({
                    title: '提示!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });
    $('#submitMobileForm').on("click", function () {
        var mobile = $('#mobileEdit').val();
        var code = $('#mobileCode').val();
        if (code == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写验证码！',
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
        var srt = {
            mobile: mobile,
            code: code
        }
        $.post("/user/updateMobile.do", srt, function (data) {
            if (!!data) {
                if (data.code == "1") {
                    var e = document.getElementById("personMobiled");
                    e.innerHTML = data.data.mobile;
                }
            }
        });
    });
    $('#mobileCode').blur(function () {
        var mobile = $('#mobileEdit').val();
        var code = $('#mobileCode').val();
        if (code == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写验证码！',
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
        var srt = {
            mobile: mobile,
            code: code
        };
        $.post("/sms/verify.do", srt, function (data) {
            if (!!data) {
                if (data.code == "1") {
                    $('#submitMobileForm').removeAttr("disabled");
                }
            }
        });
    });
    $('#getCode').on('click', function () {
        var mobile = $('#mobileEdit').val();
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
        $.post("/sms/send_code", srt, function (data) {
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