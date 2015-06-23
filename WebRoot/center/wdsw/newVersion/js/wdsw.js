//日期格式化
function formatDate(span){
    var date = new Date();
    date.setDate(date.getDate()-span);
    var strYear = date.getFullYear();
    var strDay = date.getDate();
    var strMonth = date.getMonth()+1;
    if(strMonth<10)
    {
        strMonth="0"+strMonth;
    }
    if(strDay<10)
    {
        strDay="0"+strDay;
    }
    return strYear+"年"+strMonth+"月"+strDay+"日";
}

//检测窗口打开即关闭
var intHand=null;
var rtn=null;	
function checkWin(){
	if(rtn!=null && rtn.closed){
		clearInterval(intHand);
		intHand=null;
		rtn=null;
		getTodoItems();
	}
}
function openProcess(url,i){
	rtn = window.open(url+"&rand="+Math.random(),"workflowwdsw");
	intHand = setInterval("checkWin()",3000);
	return false;
}

//分页
function paging(page){
    console.log(page);
}



////老OAurl
//var oldOaUrl = "http://10.1.44.18";
//
////workflowUrl
//var workflowUrl = "http://10.1.48.101:8080/workflow";

//获取是否登陆老平台
function isLog(status){
    status = 1;
	//console.time("登陆");
	$.ajax({
		type: 'GET',
		url: '/portal/center/wdsw/getDept.jsp?random='+Math.random(),
		dataType:'html',
		cache : false,
		error:function(){},
		success: function(obj){
			if($(obj).find("select").find("option").length>0){
				//console.timeEnd("登陆");
				//console.time("日程安排");
				getDateInfo();
				//console.timeEnd("日程安排");
				//console.time("会议安排");
				getMeetInfo();
				//console.timeEnd("会议安排");
				//console.time("待办事项");
				getTodoItems();
				//console.timeEnd("待办事项");
                if("1"==status){
                    //console.log("status:="+status);
                    //console.time("后续跟踪事项");
                    getUnDoneItems(0);
                    //console.timeEnd("后续跟踪事项");
                    //console.time("近期办结事项");
                    getDoneItems(0);
                }else{
//                    $("#unDoneDiv").hide();
//                    $("#doneDiv").hide();
                   // $("#unDoneH3").html("后续跟踪事项<a class=\"fr mr defaultSet\" href=\"javascript:void(0);\"><strong class=\"text-warning\">设置</strong></a>");
                   // $("#doneH3").html("近期办结事项<a class=\"fr mr defaultSet\" href=\"javascript:void(0);\"><strong class=\"text-warning\">设置</strong></a>");
                    $("#unDoneH3").html("已办跟踪事项");
                    $("#doneH3").html("个人办结事项");
                    $("#unDoneTabContent").find("table").html("<tr><td class='nul'>您未设置跟踪事项</td></tr>");
                    $("#doneTabContent").find("table").html("<tr><td class='nul'>您未设置跟踪事项</td></tr>");
                }

				//console.timeEnd("近期办结事项");
			}else{
				setTimeout(isLog,5000);
			}
		}
	});
}

//获取2天外仍在进行的后续事项
function getUnDoneItems(tabIndex){
	//$("#"+tab);$("#"+content);$("#"+span);
	$.post(
			'/portal/doneNew/doneItem.action?random='+Math.random(),
			{"processStatus":"0","trackStatus":"1"},
			function(obj){
				var doneType = "";
				var doneRow = "";
				var doneMore = "<a class=\"btn btn-link\" href=\"/portal/done/doneItemList.action\" target=\"_blank\"><strong class=\"text-warning\">更多&gt;&gt;</strong></a>";
				$("#unDoneSpan").html(doneMore);
				if(obj !=null){
					$("#unDoneH3").html("已办理进行中事项 <strong class=\"ot\">"+obj.doneCount+"</strong> 项，未跟踪 <strong class=\"ot\">"+obj.unTrackCount+"</strong> 项，详情请 <a target=\"_blank\"  href=\"/portal/doneNew/unDoneTrack.action\"><strong class=\"text-warning\">点击</strong></a>"
                        +"<small class=\"fr\" style=\"line-height: 37px;font-size: 85%;\">点击<strong class=\"text-success\">\"取消\"</strong>按钮，取消流程跟踪&nbsp;&nbsp;</small> ");
					if(obj.type !=null && obj.type.length > 0){
						for(var i=0;i<obj.type.length;i++){
							doneType += "<li><a href=\"#t"+i+"\" data-toggle=\"tab\"> ";
							doneType += "<strong>"+obj.type[i]+"</strong><span cs=\"unDone\" title=\"取消跟踪\" class=\"rv notShow glyphicon glyphicon-remove\"></span></a></li>";
						}
                        var doneCount = 0;
						for(var i=0,j=0;i<obj.doneInfo.length;i++,j++){
							if(i==0 || obj.doneInfo[i].type!=obj.doneInfo[i-1].type){
								doneRow += "<div class=\"todoUl clearfix tab-pane fade\" id=\"t"+doneCount+"\">";
								doneRow += "<table class=\"table table-hover\">";
								doneCount ++ ;
								j =0 ;
							}

							doneRow += "<tr class=\"hand\" taskid=\""+obj.doneInfo[i].taskid+"\" pname=\""+obj.doneInfo[i].pname+"\" pincident=\""+obj.doneInfo[i].pincident+"\">";
							doneRow += "<td style=\"width: 5%;\">"+(j+1)+"</td>";
							doneRow += "<td style=\"width: 15%;\">["+obj.doneInfo[i].pname+"]</td>";
							doneRow += "<td style=\"width: 45%;\">"+obj.doneInfo[i].summary+"</td>";
							doneRow += "<td style=\"width: 5%;\">";
							doneRow += "<button cs=\"unDone\" mainId=\""+obj.doneInfo[i].id+"\" type=\"button\" class=\"cancelButton btn btn-xs btn-success\">取消</button>";
							doneRow += "</td>";
							doneRow += "<td style=\"width: 15%;\">"+obj.doneInfo[i].operatetime+" <strong class=\"mk\">办理</strong></td>";
							doneRow += "<td style=\"width: 15%;\">"+obj.doneInfo[i].stepname+"</td>";
							doneRow += "</tr>";
							if(i==obj.doneInfo.length-1 || obj.doneInfo[i].type!=obj.doneInfo[i+1].type){
								doneRow += "</table></div>";
							}

						}
						$("#unDoneTabContent").html(doneRow);
						$("#unDoneTab").html(doneType).find("li:eq("+tabIndex+") a").tab("show");
					}else{
						$("#unDoneTabContent").find("table").html("<tr><td class='nul'>您目前无已办跟踪事项</td></tr>");
						$("#unDoneTab").html("");
					}
				}else{
                    $("#unDoneTabContent").find("table").html("<tr><td class='nul'>您目前无已办跟踪事项</td></tr>");
					$("#unDoneTab").html("");
				}
			},
			"json"
		).error(function() {})
}
//获取一周内办结的后续事项
function getDoneItems(tabIndex){
	//$("#"+tab);$("#"+content);$("#"+span);
	$.post(
			'/portal/doneNew/doneItem.action?random='+Math.random(),
			{"processStatus":"1","trackStatus":"1"},
			function(obj){
				var doneType = "";
				var doneRow = "";
				var doneMore = "<a class=\"btn btn-link\" href=\"/portal/done/doneItemList.action\" target=\"_blank\"><strong class=\"text-warning\">更多&gt;&gt;</strong></a>";
				$("#doneSpan").html(doneMore);
				if(obj !=null){
					$("#doneH3").html("已办结事项<strong class=\"ot\">"+obj.doneCount+"</strong> 项，未跟踪 <strong class=\"ot\">"+obj.unTrackCount+"</strong> 项 （"+formatDate(7)+"至"+formatDate(0)+"），详情请 <a target=\"_blank\" href=\"/portal/doneNew/doneTrack.action\"><strong class=\"text-warning\">点击</strong></a>"
                        +"<small class=\"fr\" style=\"line-height: 37px;font-size: 85%;\">点击<strong class=\"text-success\">\"取消\"</strong>按钮，取消流程跟踪&nbsp;&nbsp;</small> ");
					if(obj.type !=null && obj.type.length > 0){
						for(var i=0;i<obj.type.length;i++){
							doneType += "<li><a href=\"#d"+i+"\" data-toggle=\"tab\"> ";
							doneType += "<strong>"+obj.type[i]+"</strong><span cs=\"done\" title=\"取消跟踪\" class=\"notShow rv glyphicon glyphicon-remove\"></span></a></li>";
						}
						var doneCount = 0;
						for(var i=0,j=0;i<obj.doneInfo.length;i++,j++){
							if(i==0 || obj.doneInfo[i].type!=obj.doneInfo[i-1].type){
								doneRow += "<div class=\"todoUl clearfix tab-pane fade\" id=\"d"+doneCount+"\">";
								doneRow += "<table class=\"table table-hover\">";
								doneCount ++ ;
								j =0 ;
							}

							doneRow += "<tr class=\"hand\" taskid=\""+obj.doneInfo[i].taskid+"\" pname=\""+obj.doneInfo[i].pname+"\" pincident=\""+obj.doneInfo[i].pincident+"\">";
							doneRow += "<td style=\"width: 5%;\">"+(j+1)+"</td>";
							doneRow += "<td style=\"width: 15%;\">["+obj.doneInfo[i].pname+"]</td>";
							doneRow += "<td style=\"width: 45%;\">"+obj.doneInfo[i].summary+"</td>";
							doneRow += "<td style=\"width: 5%;\">";
							doneRow += "<button cs=\"done\" mainId=\""+obj.doneInfo[i].id+"\" type=\"button\" class=\"cancelButton btn btn-xs btn-success\">取消</button>";
							doneRow += "</td>";
							doneRow += "<td style=\"width: 15%;\">"+obj.doneInfo[i].donetime+" <strong class=\"mk\">办结</strong></td>";
							doneRow += "<td style=\"width: 15%;\">"+ $.getNotNull(obj.doneInfo[i].stepname)+"</td>";
							doneRow += "</tr>";
							if(i==obj.doneInfo.length-1 || obj.doneInfo[i].type!=obj.doneInfo[i+1].type){
								doneRow += "</table></div>";
							}
							
						}
						$("#doneTabContent").html(doneRow);
						$("#doneTab").html(doneType).find("li:eq("+tabIndex+") a").tab("show");
					}else{
						$("#doneTabContent").find("table").html("<tr><td class='nul'>您目前无个人办结事项</td></tr>");
						$("#doneTab").html("");
					}
				}else{
                    $("#doneTabContent").find("table").html("<tr><td class='nul'>您目前无个人办结事项</td></tr>");
					$("#doneTab").html("");
				}
			},
			"json"
		).error(function() {})
}


//获取待办项
function getTodoItems(){
	$.post(
			'/portal/todoNew/todoItem.action?random='+Math.random(),
			function(obj){
				var todoRow = "";
				var todoMore = "<a class=\"btn btn-link\" href=\"/portal/todo/todoItemList.action\" target=\"_blank\"><strong class=\"text-warning\">更多&gt;&gt;</strong></a>";
				$("#todoSpan").html(todoMore);
				if(obj !=null && obj.todoInfo !=null && obj.todoInfo.length > 0){
					$("#todoH3").html("待办事项 <strong class=\"ot\">"+obj.count+"</strong> 项，超期 <strong class=\"ot\">"+obj.otCount+"</strong> 项");
					for(var i=0;i<obj.todoInfo.length;i++){
							todoRow += "<tr class=\"hand\" onclick=\"openProcess('"+obj.todoInfo[i].url+"',"+i+")\">"
							todoRow += "<td style=\"width: 5%;\">"+(i+1)+"</td>";
							todoRow += "<td style=\"width: 15%;\">["+obj.todoInfo[i].pname+"]</td>";
							todoRow += "<td style=\"width: 45%;\">"+obj.todoInfo[i].summary+"</td>";
							todoRow += "<td style=\"width: 15%;\"><strong class=\"ot\">"+obj.todoInfo[i].overtime+"</strong></td>";
							todoRow += "<td style=\"width: 15%;\">"+obj.todoInfo[i].occurtime+" <strong class=\"mk\">到达</strong></td>";
							todoRow += "</tr>";							
					}
				}else{
					todoRow += "<tr><td class='nul'>您目前无待办事项</td></tr>";
				}
				$("#todoTable").html(todoRow);
			},
			"json"
		).error(function() {

        })
}

//获取通知通告
function getStfbNews(sj_id){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"size":"10"
				},
				dataType:'json',
				cache : false,
				error:function(){},
				success: function(obj){			
					var noticeRow = "";
					var noticeMore = "";
					if(obj){
						for(var i=0;i<obj.length;i++){
							noticeRow += "<li class=\"fl w48p list-group-item\"><a class=\"gray w80p pull-left\" target='_blank' title='"+obj[i].copyTitle+"'";
							noticeRow += " href='"+oldOaUrl+"/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a><span class=\"w40 gray pull-right\">"+obj[i].createTime.substring(5)+"</span></li>";
						}
						noticeMore = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[0].SJ_ID;
						if(obj.length==0){
							$("#noticeUl").html("无相关信息。");
							$("#noticeSpan").hide();
						}else{
                            $("#noticeUl").html(noticeRow);
                            $("#noticeSpan").html("<a class=\"btn btn-link\" href=\""+noticeMore+"\" target=\"_blank\"><strong class=\"text-warning\">更多&gt;&gt;</strong></a>");
                        }
					}
				}	  
			});	
			
}

//载入天气预报
function getWeather(address){
	$("#weatherFrame").attr("src",address);
}


//获取日程安排
function getDateInfo(){
	var d = new Date();
	var year = d.getFullYear();
	var month = d.getMonth()+1;
	var day = d.getDate();
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/ajax_date.jsp?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){},
		success: function(obj){	
			var dateRow = "";
			var dateMore = "<a class=\"btn btn-link\" href=\""+oldOaUrl+"/listDateManagerInfo.action?year="+year+"&month="+month+"&day="+day+"\" target=\"_blank\"><strong class=\"text-warning\">更多&gt;&gt;</strong></a>";
			if(obj&&obj.length==2){
				var cur = obj[0];
				var later = obj[1];
				if(cur&&cur.length>0){
					for(var i=0;i<cur.length;i++){
						dateRow += "<tr><td><a class='orange' target='_blank' title='"+cur[i].content+"'";
						dateRow += " href='"+oldOaUrl+"/showDateManagerInfo.action?id="+cur[i].id+"'>"+cur[i].datetime2;
						if(cur[i].datetime2!="全天"&&cur[i].datetime3!="全天"){
							dateRow += "-"+cur[i].datetime3;
						}
						dateRow += "&nbsp;"+cur[i].topic+"</a></td></tr>";
					}
				}else{
					dateRow += "<tr><td class='nul'><a target='_blank' class='orange' href='"+oldOaUrl+"/listDateManagerInfo.action?year="+year+"&month="+month+"&day="+day+"'>今天没有日程安排</a></td></tr>";
				}
				
				if(later&&later.length>0){
					dateRow += "<tr><td class='nul'>近期日程安排</td></tr>";
					for(i=0;i<later.length;i++){
						dateRow += "<tr><td><a class='orange' target='_blank' title='"+later[i].content+"'";
						dateRow += " href='"+oldOaUrl+"/showDateManagerInfo.action?id="+later[i].id+"'>"+later[i].datetime1+"&nbsp;"+later[i].datetime2;
						dateRow +="&nbsp;"+later[i].topic+"</a></td></tr>";
					}
				}
			}
			$("#dateTable").html(dateRow);
			$("#dateSpan").html(dateMore);
	}
	})
}

//获取会议安排
function getMeetInfo(){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/ajax_meet.jsp?random='+Math.random(),
		data:{
		},
		dataType:'json',
		cache : false,
		error:function(){},
		success: function(obj){
			var meetRow = "";
			var meetMore = "<a class=\"btn btn-link\" href=\""+oldOaUrl+"/stoa/publicConn.jsp?urlPath=/meeting/meeting.do?b_quanxian=true\" target=\"_blank\"><strong class=\"text-warning\">更多&gt;&gt;</strong></a>";
			if(obj&&obj.length>0){
				for(var i=0;i<obj.length;i++){
					var str = obj[i][3] + "(" + obj[i][2] + ")" +"&nbsp;"+ obj[i][4] + "(" + obj[i][5] + "-" + obj[i][6] + ")" +"&nbsp;"+ obj[i][9];
					meetRow += "<tr><td><a class='orange' target='_blank' title='"+obj[i][3]+"'";
					meetRow += " href='"+oldOaUrl+"/stoa/publicConn.jsp?urlPath=/meeting/meeting.do?b_see=true&onlyFind=onlyfind&id="+obj[i][0]+"'>"+str+"</a></td></tr>";
				}
			}else{
				meetRow += "<tr><td class='nul'>近期没有会议通知</td></tr>";
			}
			
			$("#meetTable").html(meetRow);
			$("#meetSpan").html(meetMore);
		}
	})
}


$(function(){
    $.getNotNull = function(options){
        if (typeof(options) == "undefined" || options== null){
            return "";
            //}else if(options.length > 75){
            //	return options.substr(0,75) + "... [请点击]";
        }else{
            return options;
        }
    }

    $("#unDoneTab,#doneTab,#myTab").on("mouseover mouseout",".rv",function(event){
        if(event.type == "mouseover"){
            $(this).parent("a").addClass("rv-a");
        }else if(event.type == "mouseout"){
            //鼠标离开
            $(this).parent("a").removeClass("rv-a");
        }
    });

    $("#unDoneTabContent,#doneTabContent,#tTabContent").on("click",".hand",function(){
        window.open(workflowUrl+"/common/view.action?pname="+encodeURI($(this).attr("pname"))
            +"&pincident="+$(this).attr("pincident")
            +"&taskid="+$(this).attr("taskid"));
    })


    $("#unDoneTab,#doneTab").on("click",".rv",function(){
       if(confirm("确认取消此列事项的跟踪吗？")){
           var cs = $(this).attr("cs");
           var type = $(this).prev("strong").html();
           type = type.substr(0,type.indexOf("（"));
            $.post(
                '/portal/doneNew/trackBatch.action',
                {"type":type,"trackStatus":"0"},
                function(obj){
                    if(obj.success){
                        alert("操作成功！");
                        if(cs=="unDone"){
                            getUnDoneItems(0);
                        }else{
                            getDoneItems(0);
                        }
                    }else{
                        alert("操作失败！");
                    }
                },"json"
            ).error(function() {});

        }
        return false;
    });

    $("#myTab").on("click",".rv",function(){
        if(confirm("确认跟踪此列事项吗？")){
            var type = $(this).prev("strong").html();
            type = type.substr(0,type.indexOf("（"));
            $.post(
                '/portal/doneNew/trackBatch.action',
                {"type":type,"trackStatus":"1"},
                function(obj){
                    if(obj.success){
                        alert("操作成功！");
                        window.location.href = window.location.href;
                    }else{
                        alert("操作失败！");
                    }
                },"json"
            ).error(function() {});

        }
        return false;
    });

    $("#tTabContent").on("click",".trackButton",function(){
		if(confirm("确认跟踪吗？")){
			$.post(
			'/portal/doneNew/track.action',
			{"id":$(this).attr("mainId"),"trackStatus":"1"},
			function(obj){
				if(obj.success){
                    alert("操作成功！");
					window.location.href = window.location.href;
				}else{
					alert("操作失败！");
				}
			},"json"
			).error(function() {});
			
		}
		return false;
	});

    $("#unDoneTabContent,#doneTabContent").on("click",".cancelButton",function(){
		var cs = $(this).attr("cs");
		var pos = $(this).parents("div.todoUl").index();
        var title = $(this).parent("td").prev().html();
		if(confirm("您是否确认取消 ["+title+"] 流程的跟踪？")){
			$.post(
			'/portal/doneNew/track.action',
			{"id":$(this).attr("mainId"),"trackStatus":"0"},
			function(obj){
                //alert(obj);
				if(obj.success){
					alert("["+title+"] 流程跟踪已取消！");

					if(cs=="unDone"){
						getUnDoneItems(pos);
					}else{
						getDoneItems(pos);
					}
				}else{
					alert("操作失败！");
				}

			},"json"
			).error(function() {});
		}
		return false;
	});

    $(document).on("click",".defaultSet",function(){
       window.open(caUrl + "/userInfo/changeUserInfoById.action?type=trackStatus");
    });


    $(".nav-tabs").on("mouseover mouseout","li",function(event){
        if(event.type == "mouseover"){
            $(this).find(".rv").css("display","inline");
        }else if(event.type == "mouseout"){
            $(this).find(".rv").css("display","none");
        }
    });


});
