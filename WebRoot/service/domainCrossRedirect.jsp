<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.wonders.stpt.core.domainCross.util.DomainCrossUtil"%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>shentong</title>
</head>
<%
	String appName = (String)request.getParameter("appName");
	String returnUrl = (String)request.getParameter("returnUrl");
	String url = DomainCrossUtil.domainCrossAuthLogin(request,response);
	//System.out.println("url+"+url);
	if("error".equals(url)){
		response.sendRedirect("/portal/logout.jsp");
	}else{
		response.sendRedirect(url);
	}
%>
</html>