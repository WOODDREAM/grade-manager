$('#login22').on('click', function () {
    var mobile = $("#loginMobile").val();
    var passWord = $("#loginPassWord").val();
    var type = $("#loginTeacher").val();
    var srt = {
        mobile: mobile,
        pass_word: passWord,
        type: type
    };
    $.post("/user/login.do", srt, function (data) {
        if (!!data) {
            if (!!data.code && data.code == 1) {

            }
        }
        alert(data.code)
        alert(data.data.name)
        console.log(data);
    })
});

