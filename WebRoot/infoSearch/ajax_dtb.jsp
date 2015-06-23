<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%
StringBuffer sb = new StringBuffer();
String ip_choice = "";
if ("".equals(ip_choice)) ip_choice="10.1.44.17:88";
ip_choice = "http://"+ ip_choice;
Donull donull=new Donull();
String today=donull.DateToString(new java.util.Date());

sb.append("<table id='dtb' width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr class='tit'><td width='30%' align='left'><b>报刊内容</b></td><td width='20%' align='left'><b>出版日期</b></td>");
sb.append("<td width='15%' align='left'><b>当年期号</b></td><td width='15%' align='left'><b>报刊总期号</b></td><td width='30%' align='left'><b>上传时间</b></td></tr>");
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
try{
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
con = DriverManager.getConnection("jdbc:sqlserver://10.1.44.24:1433;databaseName=IPS","wonders","wonders");
stmt = con.createStatement();
String sql = "SELECT top 5 c.NAME, c.KEYWORD, c.CATEGORY_ID, c.CREATE_DATE, a.NAME AS newname,a.SAVED_FILE "
+" FROM AP_CONTENT c INNER JOIN AP_ATTACH a ON c.ID = a.MST_ID"
+" WHERE (c.STATUS = 'y') AND (c.create_date < '"+today+"') ORDER BY c.CREATE_DATE DESC";
rs = stmt.executeQuery(sql);
while(rs.next()){
String pub_date = rs.getString("name");
String year_num = rs.getString("category_id");
String total_num = rs.getString("keyword");
String create_date = rs.getString("create_date");
String newname = rs.getString("newname");
String saved_file = rs.getString("SAVED_FILE");
	          
sb.append("<tr class='dtbTR'><td><a class='news_list' href='"+ip_choice +"/dtb/file_dtb/"+saved_file +"' target='_blank'>"+newname +"</a></td>");
sb.append("<td>"+pub_date+"</td><td>"+year_num+"</td><td>"+total_num +"</td><td>"+create_date.substring(0,10)+"</td></tr><tr><td colspan='2' class='right_list_line'></td></tr>");

} 
}catch(Exception e){
}finally{
try{
if(rs!=null) rs.close();
if(stmt!=null) stmt.close();
if(con!=null) con.close();
}catch(Exception e){
e.printStackTrace();
}}
sb.append("</table>");
out.println(sb.toString());
 %>
