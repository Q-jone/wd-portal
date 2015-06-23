<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.wonders.stpt.core.cookie.util.CookieUtil" %>
<%@ page import="com.wonders.stpt.util.SpringBeanUtil" %>
<%
CookieUtil.clearCookie(response);
session.invalidate();
String caCross =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caCross").trim();
if("0".equals(caCross)){
	response.sendRedirect("/portal/login.jsp");
}else{
	response.sendRedirect("/portal/caClient.jsp?action=logout");
}
%>