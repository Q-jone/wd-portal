<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>

<%!
public static String genHtml(String fromFile,String toFile) throws Exception {
String result = "";
			java.net.URL url = new java.net.URL(fromFile);
			java.net.HttpURLConnection conn =(java.net.HttpURLConnection) url.openConnection();
			try{
				if (conn.getResponseCode() == 200) {
				
					try{
  						BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));  
						 String inputLine;  
                    while ((inputLine = in.readLine()) != null) {  
                        result += inputLine;  
                    }  
                    in.close();  
                    //result = "["+result+"]";  
						
					}catch(Exception e){
						
					}
				}else{
				
				}
			}catch(Exception e){
					e.printStackTrace();
				
			}
			return result;
		}
		%>
		
		<% out.println(genHtml("http://tq.360.cn/",""));%>