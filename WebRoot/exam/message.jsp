<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String key = (String) request.getAttribute("smsg");
	String turl = (String) request.getAttribute("turl");
	%>
	<base href="<%=basePath%>" />
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<style>
/** 通用样式 **/
	body,form,ul,li{margin:0; padding:0;}
	body,p,th,td,span,div,a,input{color:#000000;font-size:9pt;font-family:Arial}
	a{color:#000000;text-decoration:none}
	a:hover{color:#ff0000;text-decoration:underline}

/** 表格样式 **/
	.stable{border-collapse:collapse; border:solid 1px #def}
	.stable tr th{border-bottom:solid 1px #abc;padding:5px; background:#def; text-align:left}
	.stable tr td{border-bottom:solid 1px #def;padding:5px; text-align:left}
	.stable tr .thx{border-bottom:solid 1px #fff; background:#def; font-weight:bold; text-align:center}

	.stable2{border-collapse:collapse}
	.stable2 tr th{padding:5px; text-align:left; font-size:12pt;}
	.stable2 tr td{padding:5px; text-align:left}	
		*{font-size:12px;}
		input.btn{border:solid 1px #666; border-left:solid 1px #ccc; 
			  border-top:solid 1px #ccc; background:#ddd; padding:2px 10px; cursor:hand; height:25px}
		.btn{
			display:block; float:left; margin:0 7px 0 0; background-color:#eee;
			border:1px solid #aaa; border-top:1px solid #eee; border-left:1px solid #eee;color:#565656;
			padding:3px 10px;text-decoration:none
		}
	</style>
	<% 
	
	%>
	
  </head>
  <body>
    	<br/><br/><br/>
    	<table border="0" cellpadding="5" cellspacing="1" width="400" align="center" class="stable">
    		<tr>
    			<th colspan="2">系统提示</th>
    		</tr>
    		<tr>
    			<td width="30%" style="background:#ffffff;text-align:right"></td>
    			<td style="height:200px;text-align:left;font-size:14px;line-height:150%">
    				<%
    				out.println(key);
					%>    			
    				<br/><br/>
					<div style="clear:both"><a href="/portal/examManage/<%= turl %>" class="btn">返回列表</a></div>
    			</td>
    		</tr>
    	</table>
    	
    
  </body>
</html>
