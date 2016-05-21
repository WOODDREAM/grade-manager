var Script = function () {
    var jobType = "";
    $('#termJobType').on('click', function () {
        jobType = $("#termJobType").val();
        var term = document.getElementById("termJobType");
        term.checked = true;
        var classJob = document.getElementById("classJobType");
        classJob.checked = false;
    });
    $('#classJobType').on('click', function () {
        jobType = $("#classJobType").val();
        var term = document.getElementById("termJobType");
        term.checked = false;
        var classJob = document.getElementById("classJobType");
        classJob.checked = this;
    });
    $('#createJobBtn').on('click', function () {
        var name = $('#createJodName').val();
        var detail = $('#createJodDetail').val();
        var endTime = $('#dp2').val();
        var isNeedAnswer = 0;
        if (jobType == "") {
            $.gritter.add({
                title: '请选择!',
                text: '考试记录或者课堂记录必须选择一个！',
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
    $(".jobFindGradeBtn").click(function () {
        var jobId = $(this).parents().siblings(".jobId").text();
        var str = {
            jobId: jobId
        }
        $.get("/answer/find.do", str, function (data) {
            $('#myContainer').html(data);
        });
    });
    var jobId2 = "";
    $(".jobCreateAnswer").click(function () {
        jobId2 = $(this).parents().siblings(".jobId").text();
        var hh = document.getElementById("jobIdmm");
        hh.value = jobId2;
        //$.post("/job/delete.do", str, function (data) {
        //    $('#myContainer').html(data);
        //});
    });
    $('#submitFile').on('click', function () {
        var fileName = $('#myFile').val();

        if (jobId2 == "") {
            $.gritter.add({
                title: '警告!',
                text: "请选择作业！",
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
        var url = "/upload?jobId=" + jobId2;
        alert(url);
        document.getElementById("fileForm").action = url;
        $('#fileForm').submit();
        //window.location.href = "/upload.do?filename=" + fileName + "&jobId=" + jobId;
        //$.post("/upload", str, function () {
        //
        //});
    });
    $(".jobDeleteBtn").click(function () {
        var jobId = $(this).parents().siblings(".jobId").text();
        var str = {
            jobId: jobId
        }
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