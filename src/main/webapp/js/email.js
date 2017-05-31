var Script = function () {
    $(".emailStudentAddress").click(function () {
        alert("email");
        var roleId = $(this).parents().siblings(".studentId").text();
        var address = $(this).parents().siblings(".email-address").text();
        var roleName = $(this).parents().siblings(".studentName").text();
        alert(roleId);
        var name = document.getElementById("emailToMan");
        name.value = roleName;
        var id = document.getElementById("emailToManId");
        id.value = roleId;
        var a = document.getElementById("emailToAddress");
        a.value = address;
        alert(address);
    });
    $(".emailTeacherAddress").click(function () {
        var roleId = $(this).parents().siblings(".teacherId").text();
        var address = $(this).parents().siblings(".email-address").text();
        var roleName = $(this).parents().siblings(".teacherName").text();
        var name = document.getElementById("emailToMan");
        name.value = roleName;
        var id = document.getElementById("emailToManId");
        id.value = roleId;
        var a = document.getElementById("emailToAddress");
        a.value = address;
        alert(address);
    });
    $('#important').on('click', function () {
        var str = {
            important:true
        }
        $.get("/email/find.do",str,function(data){
            $("#myContainer").html(data);
        });
    });
    $('#send').on('click', function () {
        var srt = {
            send:true
        }
        $.get("/email/find.do",function(data){
            $("#myContainer").html(data);
        });
    });
    $('#wantSendMyEmail').on('click', function () {
        $.get("/user/email.do",function(data){
            $("#emailContant").html(data);
        });
    })
    $('#submitEmail').on('click', function () {

        var fileName = $('#emailFileName').val();
        var emailToAddress = $('#emailToAddress').val();
        var emailToManId = $("#emailToManId").val();
        var emailSubject = $("#emailSubject").val();
        alert(emailToManId);
        if (emailToAddress == "") {
            $.gritter.add({
                title: '警告!',
                text: "请选择发邮箱！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (emailToManId == "") {
            $.gritter.add({
                title: '警告!',
                text: "请选择发送人！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (emailSubject == "") {
            $.gritter.add({
                title: '警告!',
                text: "请填写邮件主题！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (fileName == "") {
            $.gritter.add({
                title: '警告!',
                text: "请选择上传文件！",
                sticky: false,
                time: ''
            });
            return false;
        }
        var url = "/upload?emailToManId=" + emailToManId  + "emailSubject" + emailSubject;
        document.getElementById("emailForm").action = url;
        $('#emailForm').submit();
    });
    $(".readEmail").click(function () {
        var fileName = $(this).parents().siblings(".emailFileName").text();
        window.location.href = "/downLoad?filename=" + fileName;
    })




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
    if (sessionStorage.getItem(sessionKey) == "") {
        window.location.href = "/user/login.do"
    }
});