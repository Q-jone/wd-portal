﻿<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.wonders.stpt.CrossIpLogin" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<a href="requestHeader.jsp" target="_blank">ssssssssss</a>
<%

System.out.println("rotocol: " + request.getProtocol()); 
System.out.println("Scheme: " + request.getScheme()); 
System.out.println("Server Name: " + request.getServerName() ); 
System.out.println("Server Port: " + request.getServerPort()); 
System.out.println("rotocol: " + request.getProtocol()); 
System.out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo()); 
System.out.println("Remote Addr: " + request.getRemoteAddr()); 
System.out.println("Remote Host: " + request.getRemoteHost()); 
System.out.println("Character Encoding: " + request.getCharacterEncoding()); 
System.out.println("Content Length: " + request.getContentLength()); 
System.out.println("Content Type: "+ request.getContentType()); 
System.out.println("Auth Type: " + request.getAuthType()); 
System.out.println("HTTP Method: " + request.getMethod()); 
System.out.println("ath Info: " + request.getPathInfo()); 
System.out.println("ath Trans: " + request.getPathTranslated()); 
System.out.println("Query String: " + request.getQueryString()); 
System.out.println("Remote User: " + request.getRemoteUser()); 
System.out.println("Session Id: " + request.getRequestedSessionId()); 
System.out.println("Request URI: " + request.getRequestURI()); 
System.out.println("Servlet Path: " + request.getServletPath()); 
System.out.println("Accept: " + request.getHeader("Accept")); 
System.out.println("Host: " + request.getHeader("Host")); 
System.out.println("Referer : " + request.getHeader("Referer")); 
System.out.println("Accept-Language : " + request.getHeader("Accept-Language")); 
System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding")); 
System.out.println("User-Agent : " + request.getHeader("User-Agent")); 
System.out.println("Connection : " + request.getHeader("Connection")); 
System.out.println("Cookie : " + request.getHeader("Cookie")); 
System.out.println("Created : " + session.getCreationTime()); 
System.out.println("LastAccessed : " + session.getLastAccessedTime());


out.println("Protocol: " + request.getProtocol());
out.println("Scheme: " + request.getScheme());
out.println("Server Name: " + request.getServerName() );
out.println("Server Port: " + request.getServerPort());
out.println("Protocol: " + request.getProtocol());
out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo());
out.println("Remote Addr: " + request.getRemoteAddr());
out.println("Remote Host: " + request.getRemoteHost());
out.println("Character Encoding: " + request.getCharacterEncoding());
out.println("Content Length: " + request.getContentLength());
out.println("Content Type: "+ request.getContentType());
out.println("Auth Type: " + request.getAuthType());
out.println("HTTP Method: " + request.getMethod());
out.println("Path Info: " + request.getPathInfo());
out.println("Path Trans: " + request.getPathTranslated());
out.println("Query String: " + request.getQueryString());
out.println("Remote User: " + request.getRemoteUser());
out.println("Session Id: " + request.getRequestedSessionId());
out.println("Request URI: " + request.getRequestURI());
out.println("Servlet Path: " + request.getServletPath());
out.println("Accept: " + request.getHeader("Accept"));
out.println("Host: " + request.getHeader("Host"));
out.println("Referer : " + request.getHeader("Referer"));
out.println("Accept-Language : " + request.getHeader("Accept-Language"));
out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
out.println("User-Agent : " + request.getHeader("User-Agent"));
out.println("Connection : " + request.getHeader("Connection"));
out.println("Cookie : " + request.getHeader("Cookie"));
out.println("Created : " + session.getCreationTime());
out.println("LastAccessed : " + session.getLastAccessedTime());
%>