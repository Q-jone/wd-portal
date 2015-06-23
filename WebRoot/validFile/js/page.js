jQuery.notNull = function (options) {
    if (typeof(options) == "undefined" || options == null) {
        return "";
    } else {
        return options;
    }
}

jQuery.isPage = function (num, total) {
    if (!num.match(/^[0-9]*$/)) {
        alert("请输入数字");
        return false;
    }
    if (parseInt(num) > parseInt(total)) {
        alert("请输入正确的页数");
        return false;
    }
    return true;
}


function list() {
    $.blockUI({ message: $('#domMessage') });
    $("#list").ajaxSubmit(listOptions);
}

$(function () {

    $("#prePage").click(function () {
        if ($("#current").val() == 1 || $("#current").val() == 0) {
        }
        else {
            $("#current").val(parseInt($("#current").val() - 1));
            $("#page").val($("#current").val());
            list();
        }

    })

    $("#nextPage").click(function () {
        if (($("#current").val() == $("#totalPage_out").val()) || $("#current").val() == 0) {
        }
        else {
            $("#current").val(parseInt($("#current").val()) + 1);
            $("#page").val($("#current").val());
            list();
        }
    })


    $("#submit").click(function () {
        list();
    })

    $(document).on("click", "#redirect", function () {
        if ($.isPage($("#page_out").val(), $("#totalPage_out").val())) {
            $("#page").val($("#page_out").val());
            list();
        }
    })

    $(document).on("change", "#pageSize_out", function () {
        if ($("#current").val() != 0) {
            $("#pageSize").val($(this).val());
            list();
        }
    })


    $('#senddate_startDate').datepicker({
        //inline: true
        "changeYear": true,
        "showButtonPanel": true,
        "closeText": '清除',
        "currentText": 'senddate_startDate'//仅作为“清除”按钮的判断条件
    });
    $('#senddate_endDate').datepicker({
        //inline: true
        "changeYear": true,
        "showButtonPanel": true,
        "closeText": '清除',
        "currentText": 'senddate_endDate'//仅作为“清除”按钮的判断条件
    });
//datepicker的“清除”功能
    $(document).on("click", ".ui-datepicker-close", function () {
        if ($(this).parent("div").children("button:eq(0)").text() == "senddate_startDate") $("#senddate_startDate").val("");
        if ($(this).parent("div").children("button:eq(0)").text() == "senddate_endDate") $("#senddate_endDate").val("");
    });

    list();

})