var Script = function () {
    var reNumber = /\d*/i;
    var reMobile = /^1[0-9]{10}/;
    var reDouble = /^[-\+]?\d+(\.\d+)?$/;
    $('#btn-class-create').on('click', function () {
        var name = $('#classCreateName').val();
        var credit = $('#classCreateCredit').val();
        var period = $('#classCreatePeriod').val();
        var unit = "1";
        var startTime = $('#dp3').val();
        var endTime = $('#dp2').val();
        if (name == "") {
            $.gritter.add({
                title: '请填写!',
                text: '请填写课程名！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (period == "" || !(reDouble.test(period) || reNumber.test(period))) {
            $.gritter.add({
                title: '请填写!',
                text: '请填写正确的学时！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (credit == "" || !(reDouble.test(credit) || reNumber.test(credit))) {
            $.gritter.add({
                title: '请填写!',
                text: '请填写正确的学分！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (startTime == "") {
            $.gritter.add({
                title: '请选择!',
                text: '请选择开始时间！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (endTime == "") {
            $.gritter.add({
                title: '请选择!',
                text: '请选择结束时间！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var schedule = "";
        for (var i = 1; i <= 7; i++) {
            for (var j = 1; j <= 13; j++) {
                if (document.getElementById("checkbox-" + j + i).checked != null) {
                    if (document.getElementById("checkbox-" + j + i).checked == true || document.getElementById("checkbox-" + j + i).checked == "checked") {
                        if (schedule == "") {
                            schedule = "{";
                        }
                        if (schedule.length > 2) {
                            schedule = schedule + ","
                        }
                        schedule = schedule + i + ":" + j;
                    }
                }
            }
        }
        if (schedule == "") {
            $.gritter.add({
                title: '请选择!',
                text: '必须选择上课时间！',
                sticky: false,
                time: ''
            });
            return false;
        }
        schedule = schedule + "}";
        var str = {
            name: name,
            period: period,
            credit: credit,
            frequencyUnit: unit,
            startTime: startTime,
            endTime: endTime,
            schoolTimes: schedule
        };
        $.post("/class/create.do", str, function (data) {
            $('#myContainer').html(data);
        });
    });

    $(".find_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.post("/class/detail.do", str, function (data) {
            $('#myContainer2').html(data);
        });
    })
    $(".find_job_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.post("/job/find.do", str, function (data) {
            $('#myContainer').html(data);
        });
    })
    $(".make_student_join_class_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.post("/class/make_join.do", str, function (data) {
            $('#myContainer').html(data);
        });
    })
    $("#btn-make-join-class").click(function () {
        var classId = $('#classId').val();
        var mobile = $('#mobile').val();
        var studentNO = $('#studentNO').val();
        var studentName = $('#studentName').val();
        if (studentNO == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写学号！',
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
        if (studentName == "") {
            $.gritter.add({
                title: '警告!',
                text: '请填写姓名！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var str = {
            classId: classId,
            mobile: mobile,
            studentNo: studentNO,
            studentName: studentName
        }
        $.post("/class/make_join.do", str, function (data) {
            $('#myContainer').html(data);
        });
    })

    $(".student_class_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.post("/relationship/main.do", str, function (data) {
            $('#myContainer').html(data);
        });
    })
    $(".end_class_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var classItemNo = $(this).parents().siblings(".classItemNo").text();
        var str = {
            classId: classItemId,
            classNo: classItemNo
        }
        $.post("/class/end.do", str, function (data) {
            $.gritter.add({
                title: '!',
                text: data.message,
                sticky: false,
                time: ''
            });
        });
    })

    $(".update_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.get("/class/update.do", str, function (data) {
            $('#myContainer').html(data);
        });
    });

    $(".delete_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        //$.post("/class/delete.do", str, function (data) {
        //    if (!!data && data.code == 1) {
        //        $.get("/class/teacher.do", function (data) {
        //            $('#myContainer').html(data);
        //        })
        //    }
        //});
    });
    $(".delete_class_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.post("/relationship/out.do", str, function (data) {
            if (!!data && data.code == 1) {
                $.get("/class/student.do", function (data) {
                    $('#myContainer').html(data);
                })
            } else {
                $.gritter.add({
                    title: '警告!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });
    $(".create_job_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var className = $(this).parents().siblings(".classItemName").text();
        var str = {
            classId: classItemId,
            className: className
        }
        $.get("/job/create.do", str, function (data) {
            $('#myContainer').html(data);
        });
    });
    $(".start_class_btn").click(function () {
        var classItemId = $(this).parents().siblings(".classItemId").text();
        var str = {
            classId: classItemId
        }
        $.post("/class/start.do", str, function (data) {
            if (!!data && data.code == 1) {
                $.gritter.add({
                    title: '开课成功!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
                $.get("/class/teacher.do", function (data) {
                    $('#myContainer').html(data);
                })
            } else {
                $.gritter.add({
                    title: '开课失败!',
                    text: data.message,
                    sticky: false,
                    time: ''
                });
            }
        });
    });

    $('#btn-class-update').click(function () {
        var name = $('#className').val();
        var credit = $('#classCredit').val();
        var period = $('#classPeriod').val();
        var startTime = $('#dp3').val();
        var endTime = $('#dp2').val();
        var classId = $('#classId').val();
        if (name == "") {
            $.gritter.add({
                title: '请填写!',
                text: '请填写课程名！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (period == "" || !(reDouble.test(period) || reNumber.test(period))) {
            $.gritter.add({
                title: '请填写!',
                text: '请填写正确的学时！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (credit == "" || !(reDouble.test(credit) || reNumber.test(credit))) {
            $.gritter.add({
                title: '请填写!',
                text: '请填写正确的学分！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (startTime == "") {
            $.gritter.add({
                title: '请选择!',
                text: '请选择开始时间！',
                sticky: false,
                time: ''
            });
            return false;
        }
        if (endTime == "") {
            $.gritter.add({
                title: '请选择!',
                text: '请选择结束时间！',
                sticky: false,
                time: ''
            });
            return false;
        }
        var schedule = "";
        for (var i = 1; i <= 7; i++) {
            for (var j = 1; j <= 13; j++) {
                if (document.getElementById("checkbox-" + j + i).checked != null) {
                    if (document.getElementById("checkbox-" + j + i).checked == true || document.getElementById("checkbox-" + j + i).checked == "checked") {
                        if (schedule == "") {
                            schedule = "{";
                        }
                        if (schedule.length > 2) {
                            schedule = schedule + ","
                        }
                        schedule = schedule + i + ":" + j;
                    }
                }
            }
        }
        if (schedule == "") {
            $.gritter.add({
                title: '请选择!',
                text: '必须选择上课时间！',
                sticky: false,
                time: ''
            });
            return false;
        }
        schedule = schedule + "}";
        var str = {
            classId: classId,
            name: name,
            period: period,
            credit: credit,
            startTime: startTime,
            endTime: endTime,
            schoolTimes: schedule
        };
        $.post("/class/update.do", str, function (data) {
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