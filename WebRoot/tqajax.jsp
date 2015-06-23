<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8" />
<title>天气预报_360安全网址导航</title>
<link rel="stylesheet" href="http://h.qhimg.com/zodiac/reset-grids-comm.css" />
<link rel="stylesheet" href="http://h.qhimg.com/zodiac/frameset-1.0.4.css" />
<!-- custom-css-begin -->
 
<link rel="stylesheet" href="http://s0.qhimg.com/hao-weather/;;style;index/d03be1e8.css" />
<!-- custom-css-end -->
<base target="_blank"/>

<meta charset="utf-8" />
		<title>欢迎</title>
		<script src="js/jquery-1.7.1.js"></script>
		
		<script type="text/javascript">
		$.ajax({
		 url:	"/portal/tq.jsp?random="+Math.random(),
		type:	"post",
	   cache: 	false,
	   dataType : "html",
	   error:	function(){alert('系统连接失败，请稍后再试！')},
	 success: 	function(obj){	
	 				alert($(obj).find(".ipt-searcharea").html());
	 				$("#zs").html($(obj).html());
				}
	});
		
	
</script>
	</head>
	
	<div id="zs">
	
	
	</div>
</html>