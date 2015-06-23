<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%@ page import="com.wonders.stpt.util.OldOaUtil"%>
<%@ page import="com.wonders.stpt.util.DBXInfoFunc"%>
<%@ page import="com.wonders.stpt.util.StringUtil"%>
<%@ page import="com.wonders.stpt.util.ActionWriter"%>
<%@ page import="com.wonders.module.common.ExecuteSql"%>

<%
	ActionWriter aw = new ActionWriter(response);
	Donull donull = new Donull();
	String loginName = donull.dealNull((String)session.getAttribute("cs_login_name"));
	String oldUserId = donull.dealNull((String)session.getAttribute("oldUserId"));
	String oldUserName = donull.dealNull((String)session.getAttribute("userName"));
	String size = donull.dealNull(request.getParameter("size"));
	String oldDeptId = donull.dealNull(request.getParameter("oldDeptId"));
	if(oldDeptId==null||"".equals(oldDeptId)||oldDeptId.length()==0){
		oldDeptId = donull.dealNull((String)session.getAttribute("oldDeptId"));
	}
	request.getSession().setAttribute("oldDeptId",oldDeptId);
	//String userId = "1469";
	List<Map<String,String>> list = DBXInfoFunc.findDBX(loginName,oldUserName,oldDeptId,size);
	aw.writeJson(list);
	
%>
