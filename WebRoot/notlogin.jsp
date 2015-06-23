<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="java.io.FileInputStream"%>
<%@ page import="com.wonders.stpt.util.SpringBeanUtil" %>
<% 
	String urlCa=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("urlCa").trim();
	String caCross =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caCross").trim();
	if("0".equals(caCross)){
		urlCa = "/portal";
	}
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>未登录或登录超时</title>
<script src="/portal/js/jquery-1.7.1.js"></script>
</head>

你还没有登录或登录已超时<br>
					<span id="ns"></span>秒后进入登录页面<br>
					或<a href="<%=urlCa %>/login.jsp" target="_top">点此</a>登录
					
					
<script type="text/javascript">
var s = 0; // 0秒,不等待
function gotoLogin3Sec() {
	if (s == 0) {
		top.location.href = "<%=urlCa %>/login.jsp";
	}
	else {
		$("#ns").html(s);
		s--;
		var id = window.setTimeout("gotoLogin3Sec()", 1000);
	}
}

	gotoLogin3Sec();
</script>
</html>


