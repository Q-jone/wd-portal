<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>
<%@page import="com.wonders.stpt.core.domainCross.util.*" %>
<%@page import="com.wonders.stpt.core.publicFun.util.*" %>
<%@page import="com.wonders.stpt.organTree.entity.vo.*" %>
<%@page import="com.wondersgroup.framework.core.web.vo.VOUtils" %>


<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<meta charset="utf-8" />
	<head>
		<title>欢迎</title>
		<script src="js/jquery-1.7.1.js"></script>
		
		<script type="text/javascript">
		
		
	$.ajax({
		 url:	"/portal/service/test.action?random="+Math.random(),
		type:	"post",
	   cache: 	false,
	   dataType : "json",
	   error:	function(){alert('系统连接失败，请稍后再试！')},
	 success: 	function(obj){	
	 				var temp = "";
					if(obj){
						for(var i=0;i<obj.length;i++){
							temp += obj[i].userName;
						}
					$("#zs2").html(temp);
					}		//html，即要显示的信息，后台已完成拼接
				}
	});
		
		//--------------------------------------------------------------------------------------------
		$.ajax({
		// url:	"/portal/constructionNotice/showLineInfo.action?random="+Math.random(),
		url :	"http://10.1.41.252:8088/portal/constructionNotice/showLineInfoCross.action?jsoncallback=?",
		type:	"post",
	data:	{
				"startDate" : "2012/04/08",
				"endDate"	: "2012/04/12"
				},
		dataType : "jsonp",//跨域必须用jsonp   
	   cache: 	false,
	   error:	function(){alert('系统连接失败，请稍后再试！')},
	 success: 	function(obj){	
					if(obj){
						alert(obj);
					}		//html，即要显示的信息，后台已完成拼接
				}
	});
	
$.getJSON("http://10.1.41.252:8088/portal/constructionNotice/showLineInfoCross.action?jsoncallback=?",
				{"startDate" : "2012/04/08",
			"endDate"	: "2012/04/12"},
function(json){   
	alert(json);
	var result ="";
	for(var i=0;i<json.length;i++){
			result += json[i];
		}
		
		$("#zs").html(result);
});  
$.getJSON("http://10.1.41.252:8088/portal/constructionNotice/showLineInfo.action?jsoncallback=?",function(json){   
	alert(json); 
});  

//--------------------------------------------------------------------------------------------

		//showConstructionLineInfo();
			$(document).ready(function(){
				$.ajax({
			type: 'POST',
			url: 'metroIndicator/findMetroIndicatorLatestEvents.action',
			data:{
					"indicatorLine" : "",
					"indicatorDate" : "2012-03-08",
					"random" : Math.random()
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){			
				var tmp = "";
				if(obj){
					tmp +="<tr><td>日期</td><td>"+obj.indicatorDate+"</td><tr>";
					tmp +="<tr><td>日正点率</td><td>"+obj.metroOntimeDaily+"</td><tr>";
					tmp +="<tr><td>月正点率</td><td>"+obj.metroOntimeMonth+"</td><tr>";
					tmp +="<tr><td>年正点率</td><td>"+obj.metroOntimeYear+"</td><tr>";
					tmp +="<tr><td>日兑现率</td><td>"+obj.metroOnworkDaily+"</td><tr>";
					tmp +="<tr><td>月兑现率</td><td>"+obj.metroOnworkMonth+"</td><tr>";
					tmp +="<tr><td>年兑现率</td><td>"+obj.metroOnworkYear+"</td><tr>";
					tmp +="<tr><td>日客流量</td><td>"+obj.passengerCapacityDaily+"</td><tr>";
					tmp +="<tr><td>月客流量</td><td>"+obj.passengerCapacityMonth+"</td><tr>";
					tmp +="<tr><td>年客流量</td><td>"+obj.passengerCapacityYear+"</td><tr>";
					tmp +="<tr><td>日收入</td><td>"+obj.ticketIncomeDaily+"</td><tr>";
					tmp +="<tr><td>月收入</td><td>"+obj.ticketIncomeMonth+"</td><tr>";
					tmp +="<tr><td>年收入</td><td>"+obj.ticketIncomeYear+"</td><tr>";
				}
				$("#kpi").html(tmp);
			}	  
		});	
		
		$.ajax({
			type: 'POST',
			url: 'metroIndicator/findMetroExpressLatestEvents.action',
			data:{
					"accidentLine" : "",
					"accidentEmergency" : "",
					"random" : Math.random()
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){			
				var tmp = "";
				for(var i=0;i<obj.length;i++){
					tmp +="<tr><td>ID</td><td>"+obj[i].id+"</td><tr>";
					tmp +="<tr><td>时间</td><td>"+obj[i].accidentTime+"</td><tr>";
					tmp +="<tr><td>线路</td><td>"+obj[i].accidentLine+"</td><tr>";
					tmp +="<tr><td>紧急</td><td>"+obj[i].accidentEmergency+"</td><tr>";
					tmp +="<tr><td>上报人</td><td>"+obj[i].operatePerson+"</td><tr>";
					tmp +="<tr><td>地点</td><td>"+obj[i].accidentLocation+"</td><tr>";
					tmp +="<tr><td>概述</td><td>"+obj[i].accidentDetail+"</td><tr>";
					tmp +="<tr><td>-------</td><td>----------</td><tr>";
				}
				$("#ex").html(tmp);
			}	  
		});	
		
		
			})
		</script>
	</head>
	
	<body>
		
		<table id="kpi">
		</table>
		
		<br>
		
		<table id="ex">
		</table>
		
		<br>
		<br>
		
		
		<s:iterator value="#session.deptList" id="t">
			<s:property value="#t.deptId"/>
			<s:property value="#t.deptName"/>
			<br>
		</s:iterator>
		
		
		
		<div id="zs"></div>
		<div id="zs2"></div>
	</body>
	<%
		List<DefaultUserVo> list = new ArrayList<DefaultUserVo>();
		list = PublicFunction.findDeptLeadersByUserId("2855");
		for(DefaultUserVo vo:list){
			vo.setToken("1111111111");
		}
		
		DefaultUserVo vo = new DefaultUserVo();
		vo.setUserId("2855");
		vo.setLoginName("G001000001612549");
		vo.setUserName("李名敏");
		vo.setDeptId("2549");
		vo.setDept("信息管理中心");
		vo.setToken("XXXXXXXXXXX");
		out.println(VOUtils.getJsonData(vo));
		out.println(VOUtils.getJsonDataFromCollection(list));
	 %>
</html>