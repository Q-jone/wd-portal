<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<% response.setStatus(200); // 200 = HttpServletResponse.SC_OK %>
<!DOCTYPE html>
<html lang="cn">
<head>
<title>500 内部服务器错误</title>
</head>
<BODY >
   <H1>错误：</H1><%=exception%>
     <H2>错误内容：</H2>
     <%
         exception.printStackTrace(response.getWriter());
     %>
 错误码： <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
     信息： <%=request.getAttribute("javax.servlet.error.message")%> <br>
     异常： <%=request.getAttribute("javax.servlet.error.exception_type")%> <br>
</body>
</html>