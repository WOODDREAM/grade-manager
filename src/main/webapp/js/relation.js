var Script = function () {
    $(".delete_re_btn").click(function () {
        var reId = $(this).parents().siblings(".reId").text();
        var str = {
            relationshipId: reId
        }
        $.post("/relationship/delete.do", str, function (data) {
            if (!!data && data.code == 1) {
                $.gritter.add({
                    title: '成功!',
                    text: '成功将此学生移除',
                    sticky: false,
                    time: ''
                });
                var http = "/relationship/main.do";
                $.post(http, function (data) {
                    $('#myContainer').html(data);
                })
            } else {
                $.gritter.add({
                    title: '失败!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });
    $(".agree_re_btn").click(function () {
        var reId = $(this).parents().siblings(".reId").text();
        var str = {
            relationshipId: reId
        }
        $.post("/relationship/agree.do", str, function (data) {
            if (!!data && data.code == 1) {
                $.gritter.add({
                    title: '成功!',
                    text: "已经同意此学生加入课程！",
                    sticky: false,
                    time: ''
                });
                var http = "/relationship/main.do";
                $.post(http, function (data) {
                    $('#myContainer').html(data);
                })
            } else {
                $.gritter.add({
                    title: '失败!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });
    $(".out_re_btn").click(function () {
        var reId = $(this).parents().siblings(".reId").text();
        var str = {
            relationshipId: reId
        }
        $.post("/relationship/out.do", str, function (data) {
            if (!!data && data.code == 1) {
                $.gritter.add({
                    title: '成功!',
                    text: "已经退出此课程！",
                    sticky: false,
                    time: ''
                });
                $.get("/relationship/main.do", function (data) {
                    $('#myContainer').html(data);
                })
            } else {
                $.gritter.add({
                    title: '失败!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });
    $(".delete_un_class_btn").click(function () {
        var reId = $(this).parents().siblings(".reId").text();
        var str = {
            relationshipId: reId,
            type: 1
        }
        $.post("/relationship/delete.do", str, function (data) {
            if (!!data && data.code == 1) {
                $.gritter.add({
                    title: '成功!',
                    text: "已经退出课程！",
                    sticky: false,
                    time: ''
                });
                var http = "/relationship/main.do";
                $.post(http, function (data) {
                    $('#myContainer').html(data);
                })
            } else {
                $.gritter.add({
                    title: '失败!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
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
    if (sessionStorage.getItem(sessionKey) == "") {
        window.location.href = "/user/login.do"
    }
});