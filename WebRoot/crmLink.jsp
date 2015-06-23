<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.wonders.stpt.util.Donull"%>

<!DOCTYPE html>

<html lang="cn">
<head>
<meta charset="utf-8">
<title>shentong</title>
</head>
<!-- 
李名敏，111111

胡波，snap1234

金涛，jt-122144

方政，111111

黄天印，111111

孔琰，111111

郑思浩，111111

 -->
<%
	Donull donull = new Donull();
	String name = donull.dealNull((String)session.getAttribute("userName"));
	String password = "";
	if("李名敏".equals(name)){
		password = "111111";
	}else if("胡波".equals(name)){
		password = "snap1234";
	}else if("金涛".equals(name)){
		password = "jt-122144";
	}else if("方政".equals(name)){
		password = "111111";
	}else if("黄天印".equals(name)){
		password = "111111";
	}else if("孔琰".equals(name)){
		password = "111111";
	}else if("郑思浩".equals(name)){
		password = "111111";
	}
	
	String url = "http://10.1.41.51/index.asp?name="+java.net.URLEncoder.encode(name,"UTF-8")+"&password="+password;
	response.sendRedirect(url);
	
%>
</html>