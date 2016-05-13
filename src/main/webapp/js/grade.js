var type = "";
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
    if(type==""){
        $.gritter.add({
            title: '请选择!',
            text: '教师或者学生必须选择一个！',
            sticky: false,
            time: ''
        });
        return false;
    }
    $("#login_form").submit();
    //var srt = {
    //    mobile: mobile,
    //    passWord: passWord,
    //    type: type
    //};
    //
    //$.post("/user/login.do", srt, function (data) {
    //    if (!!data) {
    //
    //    }
    //    alert(data)
    //    console.log(data);
    //})
});
$('#loginTeacher').on('click', function () {
    type = $("#loginTeacher").val();
});
$('#loginStudent').on('click', function () {
    type = $("#loginStudent").val();
});

