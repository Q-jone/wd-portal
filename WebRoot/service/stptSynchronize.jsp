<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.wonders.stpt.core.synchronize.util.SynchronizeUtil"%>
<%
	SynchronizeUtil.synchronizeSession(request,response);
	response.sendRedirect(request.getParameter("returnUrl"));
%>