<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.wonders.stpt.CrossIpLogin" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
  response.setHeader("P3P","CP=CAO PSA OUR");//解决在IE下iframe中无法存入cookie的问题
  String token = request.getParameter("token");
  String returnUrl = request.getParameter("returnUrl");
  CrossIpLogin crossIpLogin = new CrossIpLogin();
  String result = crossIpLogin.postSecret(token);
  if("error".equals(result)){
  	response.sendRedirect("error.jsp");
  }else{
    
    //result = "<userInfo><userId>应名洪董事长</userId></userInfo>";
    System.out.println("result=========="+result);
    crossIpLogin.saveCookie(result,response);
    
    //ss.setUserInfo(request);
    response.sendRedirect(returnUrl);
  }
 
 %>
</body>
</html>