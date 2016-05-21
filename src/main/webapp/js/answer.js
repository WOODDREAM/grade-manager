var Script = function () {
    var answerId2 = "";
    var reDouble = /^[-\+]?\d+(\.\d+)?$/;
    $(".jobCreateAnswer").click(function () {
        var answerId = $(this).parents().siblings(".answerId").text();
        var hh = document.getElementById("answerIdmm");
        hh.value = answerId;
    });

    $('#submitFile').on('click', function () {
        var fileName = $('#myFile').val();
        var answerId = $(this).parents().siblings(".answerId").text();
        if (answerId2 == "") {
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
        var url = "/upload?answerId=" + answerId2;
        alert(url);
        document.getElementById("fileForm").action = url;
        $('#fileForm').submit();
    });
    $(".createGradebtn").click(function () {
        var answerId = $(this).parents().siblings(".answerId").text();
        var hh = document.getElementById("answerIdForGrade");
        hh.value = answerId;
    });
    $(".submitGrade").click(function () {
        var gradeAnswer = $(this).parents().siblings(".grade-answer");
        var answerId = $('#answerIdForGrade').val();
        var grade = $('#grade').val();
        if (grade == "" || !(reDouble.test(grade))) {
            $.gritter.add({
                title: '警告!',
                text: "请填写正确的成绩！",
                sticky: false,
                time: ''
            });
        }
        var str = {
            grade: grade,
            answerId: answerId
        }
        $.post("/grade/create.do", str, function (data) {
            if (data.code == 1) {
                gradeAnswer = data.data;
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
    $(".downAnswerbtn").click(function () {
        var answer = $(this).parents().siblings(".answerContent").text();
        alert(answer)
        window.location.href = "/downLoad?filename=" + answer;
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