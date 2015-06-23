<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.wonders.stpt.core.domainCross.util.DomainCrossUtil"%>
<%@page import="com.wonders.stpt.core.login.constant.LoginConstant"%>
<%
//
response.setCharacterEncoding("GB2312");
String result = DomainCrossUtil.domainParamCallback(request,response);
if("".equals(result)||"error".equals(result)){
	out.println("error");
}
else{
	out.println(result);
}
%>