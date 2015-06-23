jQuery.notNull = function(options){
    if (typeof(options) == "undefined" || options== null){
        return "";
    }else {
        return options;
    }
}

jQuery.isPage = function(num,total){
    if(!num.match(/^[0-9]*$/)){
        alert("请输入数字");
        return false;
    }
    if(parseInt(num)>parseInt(total)){
        alert("请输入正确的页数");
        return false;
    }
    return true;
}

function list(){
    $.blockUI({ message: $('#domMessage') });
    $("#list").ajaxSubmit(listOptions);
}

$(function(){


    $("#prePage").click(function(){
        if($("#current").val() == 1 || $("#current").val() == 0){}
        else{
            $("#current").val(parseInt($("#current").val()-1));
            $("#page").val($("#current").val());
            list();
        }

    })

    $("#nextPage").click(function(){
        if(($("#current").val() == $("#totalPage_out").val()) || $("#current").val() ==0){}
        else{
            $("#current").val(parseInt($("#current").val())+1);
            $("#page").val($("#current").val());
            list();
        }
    })


    $("#submit").click(function(){
        list();
    })

    $(document).on("click","#redirect",function(){
        if($.isPage($("#page_out").val(),$("#totalPage_out").val())){
            $("#page").val($("#page_out").val());
            list();
        }
    })

    $(document).on("change","#pageSize_out",function(){
        if($("#current").val()!=0){
            $("#pageSize").val($(this).val());
            list();
        }
    })

    $("#authorityButton , #cancelButton").click(function(){
        if($(".clk:checked").length == 0){
            alert("请选择文件！");
        }else{
            if($(this).attr("id")=="cancelButton"){
                $("#authorityFlag").val("0");
            }else if($(this).attr("id")=="authorityButton"){
                $("#authorityFlag").val("1");
            }
            $.post(
                    '/portal/deptDoc/process/getDeptPerson.action?random='+Math.random(),
                {},
                function(obj, textStatus, jqXHR){
                    if(obj != null && obj.length > 0){
                        var rows = "";
                        for(var i =0;i<obj.length;i++){
                            rows += "<input class='pclk' type='checkbox' value='"+$.notNull(obj[i].LOGINNAME)+"'>"+$.notNull(obj[i].USERNAME)+"<br>";
                        }
                        $("#selectZone").html(rows);
                    }
                    $( "#peopleSelectZone" ).dialog( "open" );
                },
                "json"
            ).error(function() { alert("服务器连接失败，请稍后再试!"); })
        }
    });


    $(document).on("click","#checkboxAll",function(){
        //alert($(this).prop("checked"));
        $("tbody").find("input[type=checkbox]").prop("checked",$(this).prop("checked"));
    });

    $(document).on("click",".clk",function(){
        if($(".clk:checked").length != $(".clk").length){
            $("#checkboxAll").prop("checked",false);
        }else{
            $("#checkboxAll").prop("checked",true);
        }
    });

    $("#peopleSelectZone").dialog({
        modal: true,
        autoOpen: false,
        width: 330,
        height: 510,
        zIndex: 9999,
        buttons: [
            {
                text: "确认",
                click: function() {

                    if($(".pclk:checked").length == 0){
                        alert("请选择人员！");
                    }else{
                        var pchks = "";
                        var chks = "";
                        $(".pclk:checked").each(function(){
                            pchks += $(this).val()+",";
                        })
                        $(".clk:checked").each(function(){
                            chks += $(this).val()+",";
                        })
                        chks = chks.substr(0,(chks.length)-1);
                        pchks = pchks.substr(0,(pchks.length)-1);
                        $.post(
                                $("#authorityFlag").val() == "1" ?
                                '/portal/deptDoc/process/authority.action?random='+Math.random():
                                '/portal/deptDoc/process/cancel.action?random='+Math.random(),
                            {
                                "empIds":pchks,
                                "fileIds":chks
                            },
                            function(obj, textStatus, jqXHR){
                                if(obj=="1"){
                                    alert("操作成功!");
                                    $('#peopleSelectZone').dialog("close");
                                    list();
                                }else{
                                    alert("操作失败！");
                                    $('#peopleSelectZone').dialog("close");
                                }
                            },
                            "text"
                        ).error(function() { alert("服务器连接失败，请稍后再试!"); })
                    }
                }
            },
            {
                text: "取消",
                click: function() {
                    $( this ).dialog( "close" );
                }
            }
        ],
        close: function(event, ui) {}
    });


    list();

})