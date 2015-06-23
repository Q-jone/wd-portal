<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>


<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<meta charset="utf-8" />
	<head>
		<title>欢迎</title>
		<script src="js/jquery-1.7.1.js"></script>
		
		<script type="text/javascript">

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
	   error:	function(){},
	 success: 	function(obj){	
					if(obj){
						//alert(obj);
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


	$.ajax({
		// url:	"/portal/constructionNotice/showLineInfo.action?random="+Math.random(),
		url :	"http://10.1.41.252:8088/portal/service/test.action?jsoncallback=?",
		type:	"post",
		dataType : "jsonp",//跨域必须用jsonp   
	   cache: 	false,
	   error:	function(){},
	 success: 	function(obj){	
					if(obj){
						//alert(obj[1].userName);
					}		//html，即要显示的信息，后台已完成拼接
				}
	});
	
	
	$.ajax({
		// url:	"/portal/constructionNotice/showLineInfo.action?random="+Math.random(),
		url :	"https://api.weibo.com/2/friendships/followers.json?uid=1658601213&access_token=2.00Tw_PoBdBYEND600e0aa317Sm_qoC",
		type:	"get",
		dataType : "jsonp",//跨域必须用jsonp   
	   cache: 	false,
	   error: function(XMLHttpRequest, textStatus, errorThrown) {
           alert(XMLHttpRequest.status);
           alert(XMLHttpRequest.readyState);
           alert(textStatus);
       },
	 success: 	function(obj){	
					if(obj){
						alert(obj.data.users[0].screen_name);
					}		//html，即要显示的信息，后台已完成拼接
				}
	});
	
	$.ajax({
		// url:	"/portal/constructionNotice/showLineInfo.action?random="+Math.random(),
		url :	"http://10.1.14.20:8088/workflowController/service/todo/addTaskRest",
		type:	"get",
		jsonp:'jsonpcallback',
		data:	{
					"data":"{\"app\": \"zhoushuntest\",\"type\": 0,"
						+ "\"occurTime\": \"2013-11-14 11:22:02\",\"title\": \"-------流程标题-------\","
						+ "\"loginName\": \"ST/G01008000311\",\"status\": 0,\"removed\": 0,"
						+ " \"typename\": \"流程名称11\","
						+ "\"url\": \"流程名称11\","
						+ "\"pname\": \"主流程名称\",\"pincident\": 1,"
						+ "\"cname\": \"子流程实例号\",\"cincident\": 1,"
						+ "\"stepName\": \"当前步骤\","
						+ "\"initiator\": \"ST/G01008000311\"}"
				},
		dataType : "jsonp",//跨域必须用jsonp   
	   cache: 	false,
	   error: function(XMLHttpRequest, textStatus, errorThrown) {
           alert(XMLHttpRequest.status);
           alert(XMLHttpRequest.readyState);
           alert(textStatus);
       },
	 success: 	function(obj){	
					alert(obj.id);
				}
	});
	
	
</script>

</head>

<div id="zs"></div>