<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code=request.getParameter("code");
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>上海申通地铁集团综合业务协同平台</title>
<frameset rows="79,*,35" frameborder="no" border="0" framespacing="0">
  <frame src="/portal/mainFrame/topp.jsp?code=<%=code%>" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <!--<frame src="main_frame.html" name="mainFrame" id="mainFrame" title="mainFrame" />  -->
  <frameset id="main" cols="7,*" frameborder="no" border="0" framespacing="0">
  	<frame src="" class="frame_bl" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
  	<frame src="" class="frame_br" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
  <frame src="bottom.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="copyright" />
</frameset>
<noframes><body>
</body></noframes>
</html>