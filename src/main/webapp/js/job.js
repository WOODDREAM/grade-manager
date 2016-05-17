var Script = function () {
    $('#createJobBtn').on('click', function () {
        var name = $('#createJodName').val();
        var detail = $('#createJodDetail').val();
        var endTime = $('#dp2').val();
        var isNeedAnswer = 0;
        var jobType = 2;
        if (name == "") {
            $.gritter.add({
                title: '警告!',
                text: "名称不能为空！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (name == "") {
            $.gritter.add({
                title: '警告!',
                text: "名称不能为空！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (detail == "") {
            $.gritter.add({
                title: '警告!',
                text: "详情不能为空！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (endTime == "") {
            $.gritter.add({
                title: '警告!',
                text: "请选择结束日期！",
                sticky: false,
                time: ''
            });
            return false;
        }
        var str = {
            name: name,
            detail: detail,
            isNeedAnswer: isNeedAnswer,
            type: jobType,
            endTime: endTime
        }
        $.post("/job/create.do", str, function (data) {
            $('#myContainer').html(data);
        });

    });

    $('#createUsualJobBtn').on('click', function () {
        var classId = $('#createUsualJodForClassId').val();
        var name = $('#createUsualJodName').val();
        var detail = $('#createUsualJodDetail').val();
        var endTime = $('#dp3').val();
        var isNeedAnswer = 0;
        var jobType = 1;
        if (document.getElementById("isNeedAnswerUsual").checked == true || document.getElementById("isNeedAnswer").checked == "checked") {
            isNeedAnswer = 1;
        }
        if (name == "") {
            $.gritter.add({
                title: '警告!',
                text: "名称不能为空！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (detail == "") {
            $.gritter.add({
                title: '警告!',
                text: "详情不能为空！",
                sticky: false,
                time: ''
            });
            return false;
        }
        if (endTime == "") {
            $.gritter.add({
                title: '警告!',
                text: "请选择结束日期！",
                sticky: false,
                time: ''
            });
            return false;
        }
        var str = {
            name: name,
            detail: detail,
            classId: classId,
            isNeedAnswer: isNeedAnswer,
            type: jobType,
            endTime: endTime
        }
        $.post("/job/create.do", str, function (data) {
            $('#myContainer').html(data);
        });
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