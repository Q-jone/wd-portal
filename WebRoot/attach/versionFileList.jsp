<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.io.*,java.util.*" %>
<%@page import="com.wonders.stpt.attach.model.vo.UploadFile,com.wonders.stpt.attach.util.AttachUtil"%>
<%
	AttachUtil attachUtil = AttachUtil.getInstance();
	List fileList = (List)request.getAttribute("fileList");
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
</head>
<body style="width:100%;background-color:transparent;">
<table  width="100%" bordercolorlight="#eeeeee" bordercolordark="#eeeeee" border="1" cellpadding="2" cellspacing="0">
<%
if(fileList!=null&&fileList.size()>0){
 %>
 	<tr>
 		<td colspan="7">历史版本</td>
 	</tr>
	<tr>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>文件名</b></font></td>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>大小</b></font></td>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>上传时间</b></font></td>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>上传者</b></font></td>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>版本</b></font></td>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>状态</b></font></td>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>下载</b></font></td>
	</tr>
<%
	for(int i=0;i<fileList.size();i++){
		UploadFile file = (UploadFile)fileList.get(i);
	%>
	<tr>
		<td style="line-height: 15px"><img style="display:inline;" src="<%=request.getContextPath()%>/css/default/images/files/<%=attachUtil.getFileImageName(file.getFileExtName()) %>.gif" /><%=file.getFileAllName() %></td>
		<td style="line-height: 15px"><%=file.getFileSize() %></td>
		<td style="line-height: 15px"><%=file.getUploadDate() %></td>
		<td style="line-height: 15px"><%=file.getUploader() %></td>
		<td style="line-height: 15px">v<%=file.getVersion() %></td>
		<td style="line-height: 15px"><%=file.getStatusStr() %></td>
		<td style="line-height: 15px"><a href="<%=request.getContextPath()%>/downloadFile.action?fileId=<%=file.getAttachFile().getId()%>&ver=old" target="_blank">下载</a></td>
	</tr>
	<% 
	}
 %>	
<%
}else{
 %>
 <tr><td>
<font style="font-family: 黑体;"><b>没有历史版本。</b></font>
</td></tr>
 <%
 }
  %>
</table>
</body>
</html>