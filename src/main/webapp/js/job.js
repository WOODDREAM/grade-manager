var Script = function () {
    var jobType = 0;
    $('#createJobBtn').on('click', function () {
        var classId = $('#createJodForClassId').val();
        var name = $('#createJodName').val();
        var detail = $('#createJodDetail').val();
        var endTime = $('#dp2').val();
        var isNeedAnswer = 0;
        if (document.getElementById("isNeedAnswer").checked == true || document.getElementById("isNeedAnswer").checked == "checked") {
            isNeedAnswer = 1;
        }
        if (jobType == 0) {
            $.gritter.add({
                title: '请选择!',
                text: '请选择作业类型！',
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

    });


    $('#usuallyJobType').on('click', function () {
        jobType = $("#usuallyJobType").val();
        var usually = document.getElementById("usuallyJobType");
        usually.checked = true;
        var student = document.getElementById("termJobType");
        student.checked = false;
    });
    $('#termJobType').on('click', function () {
        jobType = $("#termJobType").val();
        var term = document.getElementById("usuallyJobType");
        term.checked = false;
        var student = document.getElementById("termJobType");
        student.checked = this;
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