function deptSwitch(oldDept){
	$.ajax({
		//url :		"http://10.1.41.252:8080/switchOldDept.action?random="+Math.random(),
		url :		"http://10.1.44.18/switchOldDept.action?random="+Math.random(),
		type:		"post",
		data:		{
						"deptid":oldDept
					},
		dataType: 	"jsonp",//跨域必须用jsonp   
		jsonp:		"jsoncallback",
	   cache: 	false,
	   error:	function(){/**alert('系统连接失败，请稍后再试！')*/},
	 success: 	function(obj){	
					if(obj&&obj.length>0){
						//findDbx(oldDept);
						upOld(oldDept);
						findDataFromCaAuditInfo(oldDept);
						//getJrswDbx(oldDept);
					}else{
						alert("切换失败，请稍后再试!")
					}
				}
	});
}

function findDbx(oldDept){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/ajax_dbx.jsp?random='+Math.random(),
		data:{
			"size" : "7",
			"oldDeptId" : oldDept
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){	
			var dbxLi = "";
			var dbxP = "<a target='_blank' class='more_3'";
			dbxP +=" href='http://10.1.44.18/ultimus/dbx.jsp'>更多</a>";
			if(obj&&obj.length>0){
				for(var i=0;i<obj.length;i++){
					var su = "";
					if(obj[i].summaryZS.length>40){
						su = obj[i].summaryZS.substr(0,40)+"...";
					}else{
						su = obj[i].summaryZS;
					}
					dbxLi += "<li class=\"clearfix\">" +
							"<a href=\"javascript:void(0);\"  style=\"color:#f17003;font-weight:bold;font-size:14px;\" title=\""+obj[i].summaryZS+"\"";
					dbxLi += " onclick=\"openForm("+i+")\" >"+su+" "+"("+obj[i].endtime.substr(0,10)+")"+"</a>";
					dbxLi += " <div style=\"display:none;\" id=\"zhoushun"+i+"\"" ;
					dbxLi += " distinct=\""+obj[i].distinct+"\" incident=\""+obj[i].incident+"\"";
					dbxLi += " taskid=\""+obj[i].taskid+"\" UserName=\""+obj[i].UserName+"\"";
					dbxLi += " pname=\""+obj[i].pname+"\" pincident=\""+obj[i].pincident+"\"";
					dbxLi += " steplabel=\""+obj[i].steplabel+"\"  task_Type=\""+obj[i].task_Type+"\"";
					dbxLi += " processname=\""+obj[i].processname+"\"></div></li>";
				}
			}else{
				dbxLi += "<li style='border:none;background:none;line-height:30px;' class='clearfix'><a ";
				dbxLi +=" style='color:#f17003;font-weight:bold;font-size:14px;' href='javascript:void(0);'>您目前无待办事项</a></li>";
			}
			
			$("#todolist").html(dbxLi);
			$("#todolist").next("p").html(dbxP);
		}
	})
}

function findNewDbx(){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/ajax_ndbx.jsp?random='+Math.random(),
		data:{
			"size" : "7"
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){	
			var dbxLi = "";
			var dbxP = "<a target='_blank' class='more_3'";
			dbxP +=" href='http://10.1.44.18/ultimus/dbx.jsp'>更多</a>";
			if(obj&&obj.length>0){
				for(var i=0;i<obj.length;i++){
					var showTime = obj[i].UPDATE_TIME;
					if(showTime == null){
						showTime = obj[i].OPERATE_DATE;
					}
					var su = "";
					if(obj[i].THEME.length>40){
						su = obj[i].THEME.substr(0,40)+"...";
					}else{
						su = obj[i].THEME;
					}
					var tmp = "'"+obj[i].PNAME+"','"+obj[i].PINCIDENT+"','"+obj[i].CNAME+"','"+obj[i].CINCIDENT+"','"+obj[i].STEPLABEL+"'";
					dbxLi += "<li class=\"clearfix\">" +
							"<a href=\"javascript:void(0);\"  style=\"color:#f17003;font-weight:bold;font-size:14px;\" title=\""+obj[i].THEME+"\"";
					dbxLi += " onclick=\"openFormNew("+tmp+")\" >"+su+" "+"("+showTime.substr(0,10)+")"+"</a>";
					dbxLi += "</li>";
				}
			}else{
				dbxLi += "<li style='border:none;background:none;line-height:30px;' class='clearfix'><a ";
				dbxLi +=" style='color:#f17003;font-weight:bold;font-size:14px;' href='javascript:void(0);'>您目前无待办事项</a></li>";
			}
			
			$("#todolistnew").html(dbxLi);
			$("#todolistnew").next("p").html(dbxP);
		}
	})
}

var d = new Date();
var year = d.getFullYear();
var month = d.getMonth()+1;
var day = d.getDate();
function getDateInfo(){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/ajax_date.jsp?random='+Math.random(),
		data:{
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){	
			var dateLi = "";
			var dateP = "<a target='_blank' class='more_3'";
			dateP +=" href='http://10.1.44.18/listDateManagerInfo.action?year="+year+"&month="+month+"&day="+day+"'>更多</a>";
			if(obj&&obj.length==2){
				var cur = obj[0];
				var later = obj[1];
				if(cur&&cur.length>0){
					for(var i=0;i<cur.length;i++){
						dateLi += "<li class='clearfix'><a style='color:#f17003;font-weight:bold;font-size:14px;' target='_blank' title='"+cur[i].content+"'";
						dateLi += " href='http://10.1.44.18/showDateManagerInfo.action?id="+cur[i].id+"'>"+cur[i].datetime2;
						if(cur[i].datetime2!="全天"&&cur[i].datetime3!="全天"){
							dateLi += "-"+cur[i].datetime3;
						}
						dateLi +="&nbsp;&nbsp;"+cur[i].topic+"</a></li>";
					}
				}else{
					dateLi += "<li style='border:none;background:none;line-height:30px;' class='clearfix'><a target='_blank'";
					dateLi +=" style='color:#f17003;font-weight:bold;font-size:14px;' href='http://10.1.44.18/listDateManagerInfo.action?year="+year+"&month="+month+"&day="+day+"'>今天没有日程安排</a></li>";
				}
				
				if(later&&later.length>0){
					dateLi += "<li class='clearfix' style='border:none;background:none;line-height:30px;'><font size='2' color='red'><strong>近期日程安排</strong></font></li>";
					for(var i=0;i<later.length;i++){
						dateLi += "<li class='clearfix'><a style='color:#f17003;font-weight:bold;font-size:14px;' target='_blank' title='"+later[i].content+"'";
						dateLi += " href='http://10.1.44.18/showDateManagerInfo.action?id="+later[i].id+"'>"+later[i].datetime1+"&nbsp;"+later[i].datetime2;
						dateLi +="&nbsp;"+later[i].topic+"</a></li>";
					}
				}
			}
			$("#dateManage").html(dateLi);
			$("#dateManage").next("p").html(dateP);
	}
	})
}

function getMeetInfo(){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/ajax_meet.jsp?random='+Math.random(),
		data:{
		},
		dataType:'json',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){
			var meetLi = "";
			var meetP = "<a target='_blank' class='more_3'";
			meetP +=" href='http://10.1.44.18/stoa/publicConn.jsp?urlPath=/meeting/meeting.do?b_quanxian=true'>更多</a>";
			if(obj&&obj.length>0){
				for(var i=0;i<obj.length;i++){
					var str = obj[i][3] + "(" + obj[i][2] + ")     " + obj[i][4] + "(" + obj[i][5] + "-" + obj[i][6] + ")  " + obj[i][9];
					meetLi += "<li class='clearfix'><a style='color:#f17003;font-weight:bold;font-size:14px;' target='_blank' title='"+obj[i][3]+"'";
					meetLi += " href='http://10.1.44.18/stoa/publicConn.jsp?urlPath=/meeting/meeting.do?b_see=true&onlyFind=onlyfind&id="+obj[i][0]+"'>"+str+"</a></li>";
				}
			}else{
				meetLi += "<li style='border:none;background:none;line-height:30px;' class='clearfix'><a ";
				meetLi +=" style='color:#f17003;font-weight:bold;font-size:14px;' href='javascript:void(0);'>近期没有会议通知</a></li>";
			}
			
			$("#meetManage").html(meetLi);
			$("#meetManage").next("p").html(meetP);
		}
	})
}
//cms
function getLatestNews(sj_id,pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"size":"10"
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += "<li class='clearfix'><a target='_blank' title='"+obj[i].copyTitle+"'";
							newsLi += " href='http://10.1.44.18/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a><span>"+obj[i].createTime.substring(5)+"</span></li>";
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID;
						}
						$(".reportDiv:eq("+pos+") ul").html(newsLi);
						$(".reportDiv:eq("+pos+") p").html("<a target='_self' class='more_3' href='"+newsP+"'>更多</a>");
						if(obj.length==0){
							$(".reportDiv:eq("+pos+") ul").html("&nbsp;&nbsp;无相关信息。");
							$(".reportDiv:eq("+pos+") p a").hide();
						}
					}
				}	  
			});	
			
}

var intHand=null;
var rtn=null;	
	function checkWin(){
		if(rtn!=null && rtn.closed){
			clearInterval(intHand);
			intHand=null;
			rtn=null;
			findDbx("");
			//getJrswDbx($("#oldDept").val());
		}
	}
	
function openForm(i){
	var task_id = $("#zhoushun"+i).attr("taskid");
	var task_user_name = $("#zhoushun"+i).attr("UserName");
	var model_id = $("#zhoushun"+i).attr("pname");
	var instance_id = $("#zhoushun"+i).attr("pincident");
	var step_name = $("#zhoushun"+i).attr("steplabel");
	var task_type = $("#zhoushun"+i).attr("task_Type");
	var processName = $("#zhoushun"+i).attr("processname");
	var pinstance_id = $("#zhoushun"+i).attr("incident");
	var distinct = $("#zhoushun"+i).attr("distinct");

	var url ='';
	//新版工作联系单

	if(distinct=="1"){
		//alert("");
		url += 'http://10.1.44.18/ToOperateJob.action';
		url +="?groupId="+encodeURI(task_id);
		url +="&modelName=" + encodeURI(model_id);
		url +="&task_type=1";
		url +="&dbxtype=process";

		rtn = window.open(url, 'w' + task_id);
		intHand=setInterval("checkWin()",3000);
		
	}else{
		//openFormOrg(task_id,task_user_name,model_id,instance_id,step_name,task_type,processName,pinstance_id,starttime);
		var url ='';
		if((processName=='非招标合同审批流程')||(processName=='非招标合同审批流程直接备案')||(processName=='非招标合同审批会签子流程')||(processName=='非招标合同审批会签确认子流程')||(processName=='非招标合同审批领导审批子流程')){
			url = 'http://10.1.44.17';
			url +='/slogin/workflow/OpenForm.aspx?TaskID=' + encodeURI(task_id) + '&UserName='+encodeURI(task_user_name);
		}else{
			url += 'http://10.1.44.18/openflowform.action';
			url +="?task_id="+encodeURI(task_id);
			url +="&task_user_name="+ encodeURI(task_user_name);
			if (model_id == ''){
				url +="&model_id=" + encodeURI(processName);
			}else{
				url +="&model_id=" + encodeURI(model_id);
			}

			if (instance_id == ''){
				url +="&instance_id="+ encodeURI(pinstance_id);
			}else{
				url +="&instance_id="+ encodeURI(instance_id);
			}
			url +="&step_name=" + encodeURI(step_name);
			url +="&pinstance_id=" + encodeURI(pinstance_id);
			url +="&processName=" + encodeURI(processName);
			url +="&task_type=" + task_type;
		}

		rtn = window.open(url, 'w' + task_id);
		intHand=setInterval("checkWin()",3000);
	}
	
	return false;
}

var intHand2=null;
var rtn2=null;	
	function checkWin2(){
		if(rtn2!=null && rtn2.closed){
			clearInterval(intHand2);
			intHand2=null;
			rtn2=null;
			findNewDbx("");
			//getJrswDbx($("#oldDept").val());
		}
	}
function openFormNew(pname,pincident,cname,cincident,steplabel){
	var url = "http://10.1.48.60/workflowLocal/deptContact/forward.action?"
		+"pname="+encodeURI(pname)+"&"
		+"pincident="+pincident+"&"
		+"cname="+encodeURI(cname)+"&"
		+"cincident="+cincident+"&"
		+"steplabel="+encodeURI(steplabel)+"&"
		+"rand="+Math.random();
	rtn2 = window.open(url,'w' + pname+pincident);
	intHand2=setInterval("checkWin2()",3000);
	return false;
}

//replaceall方法
String.prototype.replaceAll  = function(s1,s2){  
	 return this.replace(new RegExp(s1,"gm"),s2);   
}

function getImageUrlOld(url){
  url = url.replace("background-image:","").replace("_background-image: none","").replace("url(","").replace(")","").replaceAll(";","").replaceAll("'","").replaceAll("\"","");
	//兼容ie7
	url = url.replace("none","").replace("filter:","").replace("progid:dximagetransform.microsoft.alphaimageloader","").replace("(src=","").replace(",","").replace("sizingmethod=image","");
	if(url.indexOf("big")==-1&&url.indexOf("small")==-1){
	  url = url.replace("weather","weather/big");
	}
	//兼容chrome
	var index = url.indexOf("_progid");
	if(index!=-1){
	  url = url.substring(0,index);
	}
	return url;
}
	
function getImageUrl(url){
	var index = url.indexOf(".png");
	if(url.substring(index-2,index-1)=="/"){
		url = url.substring(index-1,index+4);
	}else{
		url = url.substring(index-2,index+4);
	}			
	url = "images/weather/"+url;
	return url;				
}

function showWeather360(){
	$.ajax({
		type: 'POST',
		url: 'tqyb_360.jsp',
		dataType:'html',
		cache : false,
		error:function(){/**alert('系统连接失败，请稍后再试！')*/},
		success: function(obj){	
			var flag = true;
			
			var image_today_url = "";
			var today_date = "";
			var today_day = "";					
			var today_weather = "";
			var today_temp = "";
			
			var image_tomorrow_url = "";
			var tomorrow_date = "";
			var tomorrow_day = "";
			var tomorrow_weather = "";
			var tomorrow_temp = "";
			
			var image_dayAfterTomorrow_url = "";
			var dayAfterTomorrow_date = "";
			var dayAfterTomorrow_day = "";
			var dayAfterTomorrow_weather = "";
			var dayAfterTomorrow_temp = "";
			
			var ul = $(obj).find(".g-list");
			
			//第一天的天气
			var h4_first = ul.children("li:eq(0)").children("h4");					
			var div_first = ul.children("li:eq(0)").children("div");
			image_today_url = div_first.children("div").children("p:eq(0)").attr("style");
			today_date = h4_first.children("em").html();
			today_day = h4_first.html();
			today_weather = div_first.children("div").children("p:eq(1)").html();
			today_temp = div_first.children("p").children("em").html();
			if(image_today_url!=null&&today_date!=null&&today_day!=null&&today_weather!=null&&today_temp!=null){
				image_today_url = getImageUrl(image_today_url);				
				//today_day = "今天（"+today_day.replace(today_date,"").replace("<em></em>","").replace("今天","")+"）";
				today_day = "今天";
				today_weather = today_weather.replace("白天：","");
			}else{
				flag = false;
			}
			
			//第二天的天气
			if(flag){
				var h4_second = ul.children("li:eq(1)").children("h4");
				var div_second = ul.children("li:eq(1)").children("div");
				image_tomorrow_url = div_second.children("div").children("p:eq(0)").attr("style");						
				tomorrow_date = h4_second.children("em").html();
				tomorrow_day = h4_second.html();
				tomorrow_weather = div_second.children("div").children("p:eq(1)").html();
				tomorrow_temp = div_second.children("p").children("em").html();
				if(image_tomorrow_url!=null&&tomorrow_date!=null&&tomorrow_day!=null&&tomorrow_weather!=null&&tomorrow_temp!=null){
					image_tomorrow_url = getImageUrl(image_tomorrow_url);	
					//tomorrow_day = "明天（"+tomorrow_day.replace(tomorrow_date,"").replace("<em></em>","").replace("明天","")+"）";
					tomorrow_day = "明天";
					tomorrow_weather = tomorrow_weather.replace("白天：","");
				}else{
					flag = false;
				}
			}
			
			//第三天的天气
			if(flag){
				var ul3 = $(obj).find(".future").children("ul");
				var h4_third = ul3.children("li:eq(0)").children("h4");
				var div_third = ul3.children("li:eq(0)").children("div");
				image_dayAfterTomorrow_url = div_third.children("p:eq(0)").attr("style");
				dayAfterTomorrow_date = h4_third.html();
				dayAfterTomorrow_day = dayAfterTomorrow_date;
				dayAfterTomorrow_weather = div_third.children("p:eq(1)").html();
				dayAfterTomorrow_temp = div_third.children("p:eq(2)").children("em").html();
				if(image_dayAfterTomorrow_url!=null&&dayAfterTomorrow_date!=null&&dayAfterTomorrow_weather!=null&&dayAfterTomorrow_temp!=null){
					image_dayAfterTomorrow_url = getImageUrl(image_dayAfterTomorrow_url);
					dayAfterTomorrow_date = dayAfterTomorrow_date.replace("周一","").replace("周二","").replace("周三","").replace("周四","").replace("周五","").replace("周六","").replace("周日","").replace("（）","");
					//dayAfterTomorrow_day = "后天"+dayAfterTomorrow_day.replaceAll("1","").replaceAll("2","").replaceAll("3","").replaceAll("4","").replaceAll("5","").replaceAll("6","").replaceAll("7","").replaceAll("8","").replaceAll("9","").replaceAll("0","").replace("月日","");
					dayAfterTomorrow_day = "后天";
				}else{
					flag = false;
				}						
			}
			
			if(flag){
				$("#img1").attr("src",image_today_url);
				$("#p1").html(today_date);
				$("#p2").html(today_day);
				$("#p3").html(today_weather);
				$("#p4").html(today_temp);
				
				$("#img2").attr("src",image_tomorrow_url);
				$("#p5").html(tomorrow_date);
				$("#p6").html(tomorrow_day);
				$("#p7").html(tomorrow_weather);
				$("#p8").html(tomorrow_temp);
				
				$("#img3").attr("src",image_dayAfterTomorrow_url);						
				$("#p9").html(dayAfterTomorrow_date);
				$("#p10").html(dayAfterTomorrow_day);
				$("#p11").html(dayAfterTomorrow_weather);
				$("#p12").html(dayAfterTomorrow_temp);
			}else{
				$("#weather360").attr("style","display:none");
				$("#weatherBak").attr("style","margin:10px auto");
			}
		}
	});
}

		function getJrswDbx(oldDept){
		    //var deptId = $("#oldDept").val();
			//var deptId = "<s:property value='#session.deptId'/>";			
		    var allDeptId = "";
			$("#oldDept").children("option").each(function(index){
			    if(index==0){
			    	allDeptId = $(this).val();
			    }else{
			    	allDeptId += ","+$(this).val();
			    }
			});
			
			$.ajax({
				type: 'POST',
				url: '/portal/section/getJrswDbx.action?random='+Math.random(),
				data:{
					"deptId" : oldDept,
					"allDeptId" : allDeptId
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){	
					if(obj==null){
					    //alert("系统忙");
					}else{
					    //$("#dbx_num").text(obj.dbx);
						$("#csx_num").text(obj.csx);
						$("#csTime_num").text(obj.csTime);
						getUrgeCount(oldDept);
					}
					
				}
			});
		}
		
		function getUrgeCount(oldDept){
		    //var deptId = $("#oldDept").val();
		    var allDeptId = "";
			$("#oldDept").children("option").each(function(index){
			    if(index==0){
			    	allDeptId = $(this).val();
			    }else{
			    	allDeptId += ","+$(this).val();
			    }
			});
			$.ajax({
				type: 'POST',
				url: '/portal/section/getUrgeCount.action?random='+Math.random(),
				data:{
					"deptId" : oldDept,
					"allDeptId" : allDeptId
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){	
					if(obj==null){
					    alert("系统繁忙");
					}else{
						$("#cbx_num").text(obj.num);
						getJbxCount(oldDept);
					}
				}
			});
		}
		
		function getJbxCount(oldDept){
		   
			$.ajax({
				type: 'POST',
				url: '/portal/section/countJbx.action?random='+Math.random(),
				data:{
					"deptId" : oldDept
				},
				dataType:'json',
				cache : false,
				error:function(){
					//alert('系统连接失败，请稍后再试！');
				},
				success: function(obj){	
					if(obj==null){
					    alert("系统繁忙");
					}else{
						//alert(obj.num);
						//alert(oldDept);
						$("#jbx_num").text(obj.num);
						score();
					}
				}
			});
		}
		
		function getNoticeCount(){
				$.ajax({
						type: 'POST',
						url: '/portal/section/countMessage.action?random='+Math.random(),
						dataType:'json',
						cache : false,
						error:function(){/**alert('系统连接失败，请稍后再试！')*/},
						success: function(obj){	
							$("#notice_num").text(obj.num);
						}
				});
		}
		
		function findDataFromCaAuditInfo(oldDept){
				$.ajax({
						type: 'POST',
						url: '/portal/section/findDataFromCaAuditInfo.action?random='+Math.random(),
						data:{
									"deptId" : oldDept
								},
						dataType:'json',
						cache : false,
						error:function(){/**alert('系统连接失败，请稍后再试！')*/},
						success: function(obj){	
							if(obj!=null){
								//$("#dbx_num").text(obj.dbx);
								$("#csx_num").text(obj.csx);
								$("#csTime_num").text(obj.csTime);
								$("#cbx_num").text(obj.cbx);
								$("#notice_num").text(obj.notice);
								$("#jbx_num").text(obj.jbx);
								score();
							}else{
									//console.log("active but no audit data,tomorrow will be ok.");
									//getJrswDbx(oldDept);
									//getNoticeCount();
							}
						}
				});
		}
		
		function score(){
				var count = "";
				$("#rightUl").find("li").each(function(){						
						count = $.trim($(this).find("h1").html());						
						if(count!="/"){
								//console.log(count);
								if(parseInt(count)==0){
										$(this).attr("class","green");
										$(this).find("h1").attr("style","color:green");
								}else if(parseInt(count)>10){
										$(this).attr("class","red");
										$(this).find("h1").attr("style","color:red");
								}else{
										$(this).attr("class","blue");
										$(this).find("h1").attr("style","color:blue");
								}
						}
				});
		}
		
		function showCountInfo(){
				
						$.ajax({
								type: 'POST',
								url: '/portal/section/findIfActiveUser.action?random='+Math.random(),
								dataType:'json',
								cache : false,
								error:function(){/**alert('系统连接失败，请稍后再试！')*/},
								success: function(obj){	
									if(obj.num>0){
											//console.log("active");
												findDataFromCaAuditInfo($("#oldDept").val());
											
									}else{
											//console.log("inactive");
												getJrswDbx($("#oldDept").val());
												getNoticeCount();
									}
								}
						});
						
		}

		
		$(function(){
			//setTimeout(getDept,2000);
		});
		
		
		function getDept(){
			$.ajax({
				type: 'POST',
				url: 'getDept.jsp',
				dataType:'html',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){	
					if($(obj).find("select").find("option").length>0){
						//console.log($(obj).find("select").html());
						//clearInterval(s_id);
						$("#oldDept").html($(obj).find("select").html());
						showCountInfo();
						//console.log("dept=="+$("#oldDept").val());
						//findDataFromCaAuditInfo($("#oldDept").val());
						//findDbx("");
						//findNewDbx();
						upOld("");
						getDateInfo();
			getMeetInfo();
					}else{
						//console.log("fail");
						setTimeout(getDept,2000);
					}
				}
			});
		}