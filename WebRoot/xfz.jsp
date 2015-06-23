<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="com.wonders.stpt.CrossIpLogin" %> 
<%@ page import="com.wonders.stpt.UserInfo" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
CrossIpLogin crossIpLogin = new CrossIpLogin();
	UserInfo userInfo = new UserInfo();
	crossIpLogin.setUserInfo(request,userInfo);
	out.println(userInfo.getLoginName());
	out.println(userInfo.getDeptName());
	out.println(userInfo.getToken());
	out.println(userInfo.getUserName());

 %>
</head>

</html>