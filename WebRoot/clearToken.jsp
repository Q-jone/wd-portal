<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>      
<%@ page import="com.wonders.stpt.util.SpringBeanUtil" %>
<%
String urlCa =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("urlCa").trim();
String appName=request.getParameter("appName");
/**清除相关session及cookie***/
clearCookie(request,response,"token");
 
response.sendRedirect(urlCa+"/login.jsp?appName="+appName);	
%>
<%!
	public static void clearCookie(HttpServletRequest request,HttpServletResponse response,String name){  
		/**此处清除session及cookie**/
		Cookie cookie = new Cookie("token", null);  
        cookie.setMaxAge(0);  
     	cookie.setPath("/");	
        response.addCookie(cookie);	 
        request.getSession().invalidate();
    }  
%>
