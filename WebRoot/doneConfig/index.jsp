<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	

  </head>
  
  <body>
    后台管理:<br><br>
  <a href="<%=basePath %>doneConfig/process/list.action" target="blank">信息管理:</a>portal/doneConfig/process/list.action<br><br>
   <a href="<%=basePath %>doneConfig/classic/list.action" target="blank">类型管理:</a>portal/doneConfig/classic/list.action<br><br>
   <a href="<%=basePath %>doneConfig/userInfo/list.action" target="blank">个人管理:</a>portal/doneConfig/userInfo/list.action<br><br>
   <a href="<%=basePath %>doneConfig/userInfo/init.action" target="blank">初始化管理:</a>portal/doneConfig/userInfo/init.action<br><br>
  </body>
</html>
