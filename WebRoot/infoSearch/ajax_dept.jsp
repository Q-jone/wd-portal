<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%@ page import="com.wonders.stpt.util.OldOaUtil"%>
<%@ page import="com.wonders.stpt.util.DBXInfoFunc"%>
<%@ page import="com.wonders.stpt.util.StringUtil"%>
<%@ page import="com.wonders.stpt.util.ActionWriter"%>
<%@ page import="com.wonders.module.common.ExecuteSql"%>

<%
	ActionWriter aw = new ActionWriter(response);
	Donull donull = new Donull();
	String userId = donull.dealNull((String)session.getAttribute("oldUserId"));
	List<Map<String,String>> list = OldOaUtil.getOldUserDeptId(userId);
	
	aw.writeJson(list);
	
%>
