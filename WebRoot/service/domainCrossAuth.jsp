<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.wonders.stpt.core.domainCross.util.DomainCrossUtil"%>
<%@page import="com.wonders.stpt.core.login.constant.LoginConstant"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>shentong</title>
</head>

<%
String loginName = (String) request.getSession().getAttribute(LoginConstant.SECURITY_USER_ID);
String appName = (String)request.getParameter("appName");
String returnUrl = (String)request.getParameter("returnUrl");
if (loginName == null || "".equals(loginName)) {
	response.sendRedirect("/portal/login.jsp?appName="+appName+"&returnUrl="+returnUrl);
}else{
	String url = DomainCrossUtil.domainCrossAuthLogin(request,response);
	if("error".equals(url)){
		response.sendRedirect("/portal/logout.jsp");
	}else{
		response.sendRedirect(url);
	}
}

%>
</html>