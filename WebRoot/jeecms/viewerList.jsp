<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.stpt.jeecms.ShowJeecmsInfo"%>
<%@ page import="com.wonders.stpt.jeecms.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
String contentId=request.getParameter("content");
String pageNum=request.getParameter("page");
if(pageNum==null){
	pageNum="1";
}

int nTotalNum=0;
int pageSize=15;

ShowJeecmsInfo dwgk=new ShowJeecmsInfo(request,response);
JSONObject viewerObj=null;
JSONArray viewerList=dwgk.getInfoList("JEECMS.GET_VIEWER_NUM","{'contentId':'"+contentId+"'}");
if(viewerList!=null){
	viewerObj=viewerList.getJSONObject(0);
	nTotalNum=viewerObj.getInt("viewerNum");
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>已浏览用户列表</title>
<style type="text/css">

	.list-boder
	{
		border:solid 1px #000000;
		border-collapse:collapse;

	 }
 	.list-td {
		border:solid 1px #000000;
		border-collapse:collapse;
		text-align:center; 
	}
	.list-title
	{ 
		text-align:center;  
		font-size: 12px;
		height:24px;
		color:#2C4353;
		background: url(images/desktop/images/title_fill.jpg);
		font-weight:bold;
	}
	.split-page {
		text-align:center;  
		font-size: 12px;
		font-weight:bold;
	}
</style>
</head>
<body>
<form action="viewerList.jsp" method="post" >
<input type="hidden" name = "content" value="<%=contentId%>" />
<input type="hidden" name = "page" value="1"/>
	<h3 align="center">已浏览用户列表</h3>
	<table class="list-boder" align ="center" width="300">
		<tr class="list-title">
			<td class="list-td">序号</td>
			<td class="list-td">查阅人</td>
			<td class="list-td">查阅时间</td>
		</tr>
<%
JSONArray recList=null;
JSONObject rec=null;
int rownum=0;
recList=dwgk.getInfoList("JEECMS.GET_VIEWER_LIST","{'contentId':'"+contentId+"','page':'"+pageNum+"','rownum':'"+pageSize+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		rownum=i+((Integer.parseInt(pageNum)-1)*pageSize+1); 
		%>
		<tr>
			<td class="list-td"><%=rownum %></td>
			<td class="list-td"><%=rec.getString("name") %></td>
			<td class="list-td"><%=rec.getString("viewTime")  %></td>
		</tr>
<%
	}
}
%>   
		
	</table>
	<table align="center">
		<tr>
			<td height="8"></td>
		</tr>
		<tr> 
			<td colspan="3" align="center" class="split-page">
			
<%
		int nTotalPage=(nTotalNum/pageSize)+(nTotalNum%pageSize==0?0:1);
		if(nTotalNum > pageSize){
%>
			[<a href="#" onclick="document.forms[0].page.value='1';document.forms[0].submit();">首页</a>]&nbsp;
<%			
			if(Integer.parseInt(pageNum) > 1){
%>			
			[<a href="#" onclick="document.forms[0].page.value='<%=(Integer.parseInt(pageNum) - 1) %>';document.forms[0].submit();">上一页</a>]&nbsp;
<%			
			}
			if(Integer.parseInt(pageNum)<nTotalPage){
%>			
			[<a href="#" onclick="document.forms[0].page.value='<%=(Integer.parseInt(pageNum) + 1) %>';document.forms[0].submit();">下一页</a>]
<%				
			}
%>
			&nbsp;[<a href="#" onclick="document.forms[0].page.value='<%=nTotalPage %>';document.forms[0].submit();">尾页</a>]
<%							
		}
%>
		&nbsp;当前页 <input name='txtPageNo' type='text' style='FONT-SIZE: 9pt;height:12px;width:18px' class='input5' size='1' value='<%=pageNum %>'>/<%=nTotalPage %>&nbsp;<a href="#" onclick="toPage()">转到</a>&nbsp;记录总数：<%=nTotalNum %>
		<script>
			function toPage(){
				var pageValue=document.forms[0].txtPageNo.value;
				if(parseInt(pageValue)><%=nTotalPage %>){
					document.forms[0].txtPageNo.value=<%=nTotalPage %>;
				}else if(parseInt(pageValue)<1){
					document.forms[0].txtPageNo.value=1;
				}
				document.forms[0].page.value=document.forms[0].txtPageNo.value;
				document.forms[0].submit();
			}
		</script> 			
			</td>
		</tr>
	</table>
</form>
</body>
</html>