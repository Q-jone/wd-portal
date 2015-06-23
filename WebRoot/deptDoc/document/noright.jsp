<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="<%=path %>/css/formalize.css" />
<link rel="stylesheet" href="<%=path%>/deptDoc/css/style.css" type="text/css">

</head>

<body style="height:500px;">
<div class="browseMessages">对不起，您没有查看权限！</div>
</body>
</html>