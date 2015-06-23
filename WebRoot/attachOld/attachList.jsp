<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.io.*,java.util.*" %>

<%@page import="com.wonders.stpt.util.StringUtil"%>
<%@page import="com.wonders.stpt.attachOld.model.vo.UploadFile,com.wonders.stpt.attachOld.util.AttachUtil"%>
<%@page import="org.springframework.web.context.WebApplicationContext" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%@page import="java.sql.Date"%>
<%
	//备注下拉列表code
	String defaultListCode = "qita_att_dic";
	String extName = null;
	WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	
	//下拉列表查找方式
	String attachMemo1 = request.getParameter("attachMemo");
	String procType = request.getParameter("procType");
	String targetType = request.getParameter("targetType");
	if(StringUtil.isNull(targetType)){
		targetType = (String)request.getAttribute("targetType");
	}
	//文件组编号
	String fileGroup = (String)request.getParameter("fileGroup");
	request.setAttribute("fileGroup",fileGroup);
	String fileGroupId = (String)request.getParameter("fileGroupId");
	request.setAttribute("fileGroupId",fileGroupId);
	String fileGroupName = (String)request.getParameter("fileGroupName");
	//String loginName = StringUtil.getNotNullValueString((String)session.getAttribute("login_name"));
	if(StringUtil.isNull(fileGroupName)){
		fileGroupName = "uploadFile";
	}
	request.setAttribute("fileGroupName",fileGroupName);
	List fileList = (List)request.getAttribute("attachList");
	//用户名验证

	//String userName = new String(StringUtil.getNotNullValueString(session.getAttribute("user_name")).getBytes("ISO-8859-1"),FlowParameter.FLOW_CODE);
	String userName = (String)session.getAttribute("user_name");
	//String userName = "李名敏";
	//System.out.println("1234userName:"+userName);
	if(StringUtil.isNull(userName)){
		userName = StringUtil.getNotNullValueString(request.getParameter("userName"));
		//userName = new String(StringUtil.getNotNullValueString(request.getParameter("userName")).getBytes("ISO-8859-1"),FlowParameter.FLOW_CODE);
		//System.out.println("64354userName:"+userName);
		if(!StringUtil.isNull(userName)){
			session.setAttribute("user_name",userName);
		}
	}
	
	String uploaderLoginName = (String)session.getAttribute("login_name");
	if(StringUtil.isNull(uploaderLoginName)){
		uploaderLoginName = request.getParameter("loginName");
		if(!StringUtil.isNull(uploaderLoginName)){
			session.setAttribute("login_name",uploaderLoginName);
		}
	}
	//System.out.println("1234login_name:"+uploaderLoginName);
	AttachUtil attachUtil = AttachUtil.getInstance();
	//System.out.println("11====fileGroupId========"+fileGroupId+"|fileGroupName==="+fileGroupName);
	String fileTypes = request.getParameter("fileTypes");
	if(StringUtil.isNull(fileTypes)){
		fileTypes = (String)request.getAttribute("fileTypes");
	}
	fileTypes = "正文,附件";
	String[] fileTypeSel = null;
	if(!StringUtil.isNull(fileTypes)){
		fileTypeSel = fileTypes.split(",");
	}
	String fileType = request.getParameter("fileType");
	if(StringUtil.isNull(fileType)){
		fileType = (String)request.getAttribute("fileType");
	}
	String fileCntObjId = request.getParameter("fileCntObjId"); // 在调用页面上显示附件个数用



%>

<!DOCTYPE html>
<html lang="cn">
<head>
<title>附件上传</title>
<script src="../js/jquery-1.7.1.js"></script>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
 <script type="text/javascript">

	function btnClose(num) {
		var result=new Array;    
        result[0]=document.getElementById('fileGroup').value;
        result[1]=document.getElementById('fileGroupId').value;
        result[2]=document.getElementById('fileGroupName').value;
        result[3]=document.getElementById('procType').value;
        result[4]=document.getElementById('targetType').value;
        result[5]=document.getElementById('fileType').value;
        result[6]=document.getElementById('fileCntObjId').value;
        result[7]=document.getElementById('attachMemo1').value;
        result[8]=document.getElementById('count').value;
        //alert(result[8]);
        window.returnValue=result;
        if (num == 2) {
        	window.close();
        }
	}
	function getFileSize(filename){ 
	    var size = null; 
	    var file = null; 
	    if(filename){ 
	    	var fileSizeImg = new Image();
			fileSizeImg.src = filename;
	        var size = fileSizeImg.fileSize;
	    } 
	    return(size); 
	}
	
	
	function upFile(){
		fileInpVal = document.forms[0].<%=fileGroupName%>.value;
		//alert(fileInpVal);
		if(fileInpVal==""){
			alert("请选取文件再上传。");
			return;
		}else if(fileInpVal.indexOf('.')<0){
			alert("您上传的文件没有扩展名");
			return;
		}
		//alert(getFileSize(document.forms[0].<%=fileGroupName%>.value));
		form = document.forms[0];
		form.action = "<%=request.getContextPath()%>/attachOld/upFileOldJsp.action";
		form.submit();
		
		
        //window.close();
	}
	function loadVersionFiles(groupId,fileId){
		url = "<%=request.getContextPath()%>/attachOld/loadVersionOldFilesList.action?groupId="+groupId+"&fileId="+fileId;
		//alert(url);
		window.open(url);
	}

	function delete_confirm(e)  
	{ 
		 if (event.srcElement.alt=="删除附件" ) 
		 	//event.returnValue=confirm("您确认执行删除操作么？");	
		 	if(confirm("您确认执行删除操作么？")){
		 		
		 	}	 
	}  

	function getExplorer() {
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
		return "ie";
		}
		//firefox 
		else if (explorer.indexOf("Firefox") >= 0) {
			return "Firefox";
		}
		//Chrome
		else if(explorer.indexOf("Chrome") >= 0){
			return "Chrome";
		}
		//Opera
		else if(explorer.indexOf("Opera") >= 0){
			return "Opera";
		}
		//Safari
		else if(explorer.indexOf("Safari") >= 0){
			return "Safari";
		}
	}

</script> 
</head>
<body onunload="btnClose(1)" class="Flow1"  style="width:100%;background-color:transparent;">
<div id="bt"></div>
<div class="f_window1" style="width:95%;">
<div class="con1">
<s:form theme="simple" action="upFileOldJsp"  enctype="multipart/form-data" method="post" namespace="/attachOld">          
<input type="hidden" name="fileGroup"  id="fileGroup" value="<%=fileGroup %>" />
<input type="hidden" name="fileGroupId" id="fileGroupId" value="<%=fileGroupId %>" />
<input type="hidden" name="fileGroupName" id="fileGroupName" value="<%=fileGroupName %>" />
<input type="hidden" name="procType"  id="procType" value="<%=procType %>" />
<input type="hidden" name="targetType"  id="targetType" value="<%=targetType %>" />
<input type="hidden" name="fileType"   id="fileType" value="<%=fileType %>" />
<input type="hidden" name="fileCntObjId"  id="fileCntObjId" value="<%=fileCntObjId %>" />
<input type="hidden" name="attachMemo1"  id="attachMemo1" value="<%=attachMemo1 %>">
<input type="hidden" name="count"  id="count" value="<%= fileList == null ? 0 : fileList.size()%>">

<!-- 
<font size="2">全部</font></br>
 -->
<%
if(StringUtil.isNull(procType)||!procType.equals("view")){	//不可操作模式
 %>
<input type="file" id="<%=fileGroupName%>" name="<%=fileGroupName%>"/>
<%
if("OP_DOCSEND".equals(attachMemo1)){
%>
<input type="hidden" name="attachMemo"  id="attachMemo" value="<%=attachMemo1 %>">
<%}else{ %>
<span style='font-size:9pt;display:inline;'>附件说明:</span>
<select name="attachMemo">
	<option value="正文">正文</option>
	<option value="正文之附件">正文之附件</option>
	<option value="其他材料">其他材料</option>
</select><%} %>&nbsp;<input type="button" name="upBut" value="上传"  onclick="javascript:upFile()" class="btn" alt="附件最大允许20M" />
&nbsp;单个附件不允许超过20M
<%} %>
<table  width="100%" bordercolorlight="#eeeeee" bordercolordark="#eeeeee" border="1" cellpadding="2" cellspacing="0">
<%
	if(fileList==null||fileList.size()==0){
%>
	<tr><td><font style="font-family: 黑体;"><b>当前没有附件。</b></font></td></tr>
<% 
	}else{
 %>
	<tr>
		<td align='center' nowrap="nowrap" width='45%'><font style="font-family: 黑体;"><b>文件名</b></font></td>
        <td align='center' nowrap="nowrap" width='5%'><font style="font-family: 黑体;"><b>大小</b></font></td>
        <td align='center' nowrap="nowrap" width='18%'><font style="font-family: 黑体;"><b>上传时间</b></font></td>
		<td align='center' nowrap="nowrap" width='5%'><font style="font-family: 黑体;"><b>上传人</b></font></td>
		<td align='center' nowrap="nowrap" width='10%'><font style="font-family: 黑体;"><b>版本</b></font></td>
		<td align='center' nowrap="nowrap" width='25%' ><font style="font-family: 黑体;"><b>备注</b></font></td>
		<!--<td align='center' nowrap="nowrap" width='10%' ><font style="font-family: 黑体;"><b>预览</b></font></td>-->
	<!--            
		<td nowrap="nowrap"><font style="font-family: 黑体;"><b>下载</b></font></td>
	
		<%
		if(StringUtil.isNull(procType)||!procType.equals("view")){	//不可操作模式
		 %>
		<td nowrap="nowrap" ><font style="font-family: 黑体;"><b>删除</b></font></td>
		<%} %>
		-->	
	</tr>
<%
		for(int i=0;i<fileList.size();i++){
			UploadFile file = (UploadFile)fileList.get(i);
		%>
		<script type="text/javascript">
			function delete_confirm()  
			{ 
			  c=window.confirm("确认删除吗?");   
			  if(c==true)
			  		return true;   
			  	else   
			    	return false;   				 
			}  
		</script>
		<tr>
			<td style="line-height: 15px">
			<%
			String fileExtName = file.getFileExtName();
			if(!StringUtil.isNull(fileExtName)){
				fileExtName= fileExtName.toLowerCase();
			}
			extName = attachUtil.getFileImageName(fileExtName); %>
				<img src="<%=request.getContextPath()%>/css/default/images/files/<%= extName!= null ? extName.toLowerCase():"null" %>.gif"  style="display:inline;"/>
				<a href="<%=request.getContextPath()%>/attachOld/downloadOldFile.action?fileId=<%=file.getAttachFileOld().getId()%>" target="_blank" style="display:inline;"><%=file.getFileAllName() %></a>&nbsp;
				<%
				if(StringUtil.isNull(procType)||!procType.equals("view")){
					//if(userName.equals(file.getUploader())&&(file.getUploadDate().startsWith(new Date(System.currentTimeMillis()).toString()))){	//不可操作模式
					if(userName.equals(file.getUploader())){	//不可操作模式
			 	%>
				<a style="display:inline;" href="<%=request.getContextPath()%>/attach/deleteFilesOldJsp.action?deleteData=<%=file.getAttachFileOld().getId()%>&fileGroup=<%=fileGroup%>&fileGroupId=<%=fileGroupId%>&fileGroupName=<%=fileGroupName%>&targetType=<%= targetType%>&fileCntObjId=<%= fileCntObjId%>&attachMemo1=<%=attachMemo1%>" onClick="return delete_confirm();"><img src='<%=request.getContextPath()%>/css/default/images/files/delete.gif' style='cursor:hand;display:inline;' alt='删除附件'/></a>
					<%}%>
				<%}%>
			</td>
			<td style="line-height: 15px;"><%= file.getFileSize() > 1024*1024 ? file.getFileSize() * 100 / 1024 / 1024 / 100.0 + " M" : (file.getFileSize() > 1024 ? file.getFileSize() * 100 / 1024 / 100.0 + " K" : file.getFileSize() + " B") %></td>
			<td style="line-height: 15px"><%=file.getUploadDate() %></td>
			<td style="line-height: 15px"><%=file.getUploader() == null ? "" : file.getUploader() %></td>
			<%
			int verion = 1;
			if(!StringUtil.isNull(file.getVersion())){
				verion = Integer.parseInt(file.getVersion());
			}
			 %>
			 <%
			 if(verion==1){
			  %>
			<td style="line-height: 15px">v<%=file.getVersion() %></td>
			<%
			}else{
			 %>
			 <td style="line-height: 15px"><a style="display:inline;" href="#" onclick="javascript:loadVersionFiles('<%=file.getGroupId() %>','<%=file.getAttachFileOld().getId() %>')" >v<%=file.getVersion() %></a><input type="button" name="str" value="历" onclick="javascript:loadVersionFiles('<%=file.getGroupId() %>','<%=file.getAttachFileOld().getId() %>')"></td>
			 <%} %>
			<!--td style="line-height: 15px"><a href="<%=request.getContextPath()%>/attach/downloadFile.action?fileId=<%=file.getAttachFileOld().getId()%>" target="_blank">下载</a></td>
			<%
			if(StringUtil.isNull(procType)||!procType.equals("view")){	//不可操作模式
			 %>
			<td style="line-height: 15px"><a href="<%=request.getContextPath()%>/attach/deleteFilesJsp.action?deleteData=<%=file.getAttachFileOld().getId()%>&fileGroup=<%=fileGroup%>&fileGroupId=<%=fileGroupId%>&fileGroupName=<%=fileGroupName%>"><font color=red>删除</font></a></td>
			<%} %>
			-->
			<td style="word-break:break-all">
				<%
					String memo=file.getAttachFileOld().getMemo();
					memo=(memo==null)?"":memo;
					out.println(memo);
				%>&nbsp;
			</td>
			
			<!-- 在线预览 -->
			<!--<td style="word-break:break-all">
				<%
					if("pdf".equalsIgnoreCase(file.getFileExtName())) {					
				%>
					<a style="display:inline;" href="<%=request.getContextPath()%>/attachOld/pdfOldPreview.action?fileId=<%=file.getAttachFileOld().getId()%>" target="_blank">
					<img src="<%=request.getContextPath()%>/css/default/images/files/find.gif" />
					</a>
				<%
					}
				%>
				<%
					if("jpg".equalsIgnoreCase(file.getFileExtName()) ||
					   "jpeg".equalsIgnoreCase(file.getFileExtName()) ||	
					   "gif".equalsIgnoreCase(file.getFileExtName()) ||
					   "bmp".equalsIgnoreCase(file.getFileExtName()) ||
					   "tiff".equalsIgnoreCase(file.getFileExtName()) ||
					   "png".equalsIgnoreCase(file.getFileExtName()) ) {					
				%>
					<a style="display:inline;" href="<%=request.getContextPath()%>/attachOld/imageOldPreview.action?fileId=<%=file.getAttachFileOld().getId()%>" target="_blank">
					<img src="<%=request.getContextPath()%>/css/default/images/files/find.gif" />
					</a>
				<%
					}
				%>
				&nbsp;
			</td>-->
			<!-- 在线预览 -->
			
		</tr>
		<% 
		}
	}
 %>	
</table>
</s:form>

<script type="text/javascript">
	var hidden = null, fileCntObj = null;
<%
if (StringUtil.isNull(targetType)||targetType.equalsIgnoreCase("open")) {	//按弹出页面处理



	%>
	try {
		hidden = window.opener.document.getElementById("<%=fileGroup%>");
		fileCntObj = window.opener.document.getElementsByTagName('span');
	} catch (e) {}
	<%
} else if (targetType.equalsIgnoreCase("frame")){	//按iframe处理
	%>
	try {
		hidden = parent.document.getElementById("<%=fileGroup%>");
		fileCntObj = parent.document.getElementById('<%=fileCntObjId%>');
	} catch (e) {}
	<%
} else if (targetType.equalsIgnoreCase("dialog")){
	%>
	try {
		var pwin = window.dialogArguments;
		hidden = pwin.document.getElementById("<%=fileGroup%>");
		fileCntObj = pwin.document.getElementsByTagName('span');
	} catch (e) {}
	<%
}

String newGroupId = request.getParameter("newGroupId");
if(!StringUtil.isNull(newGroupId)) {
%>
	if (hidden != null) {
		hidden.value = '<%=newGroupId%>';
	}
<%
}
%>
	if (fileCntObj != null) {
		if(fileCntObj.length){
		for (var i=0; i<fileCntObj.length; i++) {
			if (fileCntObj[i].id == '<%=fileCntObjId%>') {
				if(getExplorer() == 'Firefox'){
					fileCntObj[i].textContent = "<%= fileList == null ? 0 : fileList.size()%>";
				}else{
					fileCntObj[i].innerText = "<%= fileList == null ? 0 : fileList.size()%>";					
				}
			}
		}
		}else{
			fileCntObj.value = "<%= fileList == null ? 0 : fileList.size()%>";
		}
	}
</script>

	</div>
</div>

</body>
</html>
<%
	if("1".equals(request.getParameter("error"))){
		%>
		<script>
			alert("您上传的文件大小超过了20M限制，请分卷压缩后重新上传！");
		</script>
		<%
	}
%>