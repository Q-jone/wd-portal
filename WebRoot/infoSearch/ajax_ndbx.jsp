<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%@ page import="com.wonders.stpt.util.OldOaUtil"%>
<%@ page import="com.wonders.stpt.util.FlowUtil"%>
<%@ page import="com.wonders.stpt.util.StringUtil"%>
<%@ page import="com.wonders.stpt.util.ActionWriter"%>
<%@ page import="com.wonders.module.common.ExecuteSql"%>

<%
	ActionWriter aw = new ActionWriter(response);
	Donull donull = new Donull();
	String loginName = donull.dealNull((String)session.getAttribute("loginName"));
	String oldUserId = donull.dealNull((String)session.getAttribute("oldUserId"));
	String oldUserName = donull.dealNull((String)session.getAttribute("userName"));
	String size = donull.dealNull(request.getParameter("size"));
	String oldDeptId = donull.dealNull((String)session.getAttribute("oldDeptId"));
	//String userId = "1469";
	List<Map<String,Object>> list = FlowUtil.getActiveProcess("","","","ST/"+loginName,size);
	aw.writeJson(list);
%>
