<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="com.wonders.stpt.CrossIpLogin" %>
    <%@ page import="java.util.Properties" %>
    <%@ page import="java.io.FileInputStream" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String returnUrl = request.getParameter("returnUrl");

	CrossIpLogin crossIpLogin = new CrossIpLogin();
	crossIpLogin.connectPortal(response,returnUrl);

 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>

</body>
</html>