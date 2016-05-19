var sessionKey = "user_sign";
var Script = function () {


    $('#a-class-list').on('click', function () {
            var studentHttp = "/class/student.do";
            var teacherHttp = "/class/teacher.do";
            if (sessionStorage.getItem(sessionKey) == "") {
                window.location.href = "/user/login.do"
            }
            var type = $('#type').val();
            var http = "";
            if (type == "1") {
                http = studentHttp;
            } else {
                http = teacherHttp;
            }
            makeLiactive($('#li-class-list'), "2");
            $.get(http, function (data) {
                $('#myContainer').html(data);
            })
        }
    );
    $('#a-login-out').on('click', function () {
        $.get("/user/login_out.do", function (data) {
            window.location.href = "/user/login.do"
        })
    });
    $('#a-class-create').on('click', function () {
        makeLiactive($('#li-class-create'), "2");
        $.get("/class/create.do", function (data) {
            $('#myContainer').html(data);
        })
    });
    $('#a-class-join').on('click', function () {
        makeLiactive($('#li-class-join'), "2")
        $.get("/user/join.do", function (data) {
            $('#myContainer').html(data);
        })
    });

    $('#a-job-list').on('click', function () {
            makeLiactive($('#li-job-list'), "3")
            $.get("/job/find.do", function (data) {
                $('#myContainer').html(data);
            })
        }
    );
    $('#a-job-create').on('click', function () {
        makeLiactive($('#li-job-create'), "3")
        $.get("/job/create.do", function (data) {
            $('#myContainer').html(data);
        })
    });

    $('#a-main').on('click', function () {
        var http = "/relationship/main.do";
        $.post(http, function (data) {
            $('#myContainer').html(data);
        })
    });
    //type=1综合页  2 课程   3 作业  4 邮件   5 个人信信息  6 聊天
    function makeLiactive(v, type) {
        $('#li-main').removeClass('active');
        $('#li-class-info').removeClass('active');
        $('#li-class-info-child').removeClass('active');
        $('#li-class-list').removeClass('active');
        $('#li-class-create').removeClass('active');

        $('#li-job-info').removeClass('active');
        $('#li-job-info-child').removeClass('active');
        $('#li-job-list').removeClass('active');
        $('#li-job-create').removeClass('active');

        $('#li-email-list').removeClass('active');

        $('#li-person-info').removeClass('active');

        $('#li-comment').removeClass('active');
        if (type == "1") {
            $('#li-main').addClass('active');
        }
        if (type == "2") {
            $('#li-class-info').addClass('active');
        }
        if (type == "3") {
            $('#li-job-info').addClass('active');
        }
        if (type == "4") {
            $('#li-email-list').addClass('active');
        }
        if (type == "5") {
            $('#li-person-info').addClass('active');
        }
        if (type == "6") {
            $('#li-comment').addClass('active');
        }
        v.addClass('active');
    }
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
