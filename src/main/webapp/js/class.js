var Script = function () {
    var reNumber = /\d*/i;
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

    function findClass(button){
        var index  = -1;
        for(var i=0; i<$('#cl_table').find_btn.length ;i++){
            if($('#cl_table').find_btn[i] == button){
                index = i;
                break;
            }
        }
        alert("行"+(index+1)+" 元素值为 "+$('#cl_table').td[index].value);
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