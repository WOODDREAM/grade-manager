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
        if (document.getElementById("isNeedAnswerUsual").checked == true || document.getElementById("isNeedAnswerUsual").checked == "checked") {
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

    $(".jobFindBtn").click(function () {
        var jobId = $(this).parents().siblings(".jobId").text();
        var str = {
            jobId: jobId
        }
        $.post("/job/detail.do", str, function (data) {
            $('#jobDetail').html(data);
        });
    })

    $(".jobUpdateBtn").click(function () {
        var jobId = $(this).parents().siblings(".jobId").text();
        var str = {
            jobId: jobId
        }
        $.get("/job/update.do", str, function (data) {
            $('#myContainer').html(data);
        });
    });

    $(".jobDeleteBtn").click(function () {
        var jobId = $(this).parents().siblings(".jobId").text();
        var str = {
            jobId: jobId
        }
        alert(jobId)
        //$.post("/job/delete.do", str, function (data) {
        //    $('#myContainer').html(data);
        //});
    });

    $('#updateJobBtn').on("click", function () {
        var name = $('#updateJodName').val();
        var jobId = $('#updateJodID').val();
        var detail = $('#updateJodDetail').val();
        var endTime = $('#dp3').val();
        var isNeedAnswer = 0;
        var jobType = 1;
        if (document.getElementById("isNeedAnswer").checked == true || document.getElementById("isNeedAnswer").checked == "checked") {
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
            detail = $('#jodDetail').val()
            if (detail == "") {
                $.gritter.add({
                    title: '警告!',
                    text: "作业详情不能为空！",
                    sticky: false,
                    time: ''
                });
                return false;
            }
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
            jobId: jobId,
            name: name,
            detail: detail,
            isNeedAnswer: isNeedAnswer,
            type: jobType,
            endTime: endTime
        }
        $.post("/job/update.do", str, function (data) {
            $('#myContainer').html(data);
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