<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String path = request.getContextPath();

	String state = (String)request.getAttribute("state");
	String fileName = (String)request.getAttribute("fileName");
	String[] imageNames = (String[])request.getAttribute("imageNames");	
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<link href="picshow.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript"> 
	function smallit(){ 
		var imgs = document.getElementsByName("imgs");
		for(var i=0;i<imgs.length;i++){
			imgs[i].height = imgs[i].height/1.2;
			imgs[i].width = imgs[i].width/1.2;
		}
	} 
	
	function bigit(){ 
		var imgs = document.getElementsByName("imgs");
		for(var i=0;i<imgs.length;i++){
			imgs[i].height = imgs[i].height*1.2;
			imgs[i].width = imgs[i].width*1.2;
		}
	}
	
	var firstPage = 1;
	var endPage = <%=imageNames.length %>;
	var currentPage = 1;
	function back(){
		if(currentPage >= firstPage && currentPage < endPage){
			currentPage = currentPage + 1;
			window.location.hash = currentPage;
		}
	}
	
	function forward(){
		if(currentPage > firstPage && currentPage <= endPage){
			currentPage = currentPage - 1;
			window.location.hash = currentPage;
		}
	}
</script> 
</head>

<body >

<jsp:include page="processbar.jsp"></jsp:include>

<div style="width:100%; overflow: auto; cursor: default; display: inline; position: absolute; top:0;height:98%;">
<table cellpadding='0' cellspacing='0' align="center" width="100%">
	<tr class="FixedTitleRow" align="right">
		<td>
			<div class="controlbar-embedded">
			<div class="goog-inline-block controlbar-controls" >
				<div title="上一页" id="controlbarBackButton" class="goog-inline-block controlbar-item goog-custom-button" onclick="forward()">
					<div class="controlbar-image controlbar-back-image"></div>
				</div>
				<div title="下一页" id="controlbarForwardButton" class="goog-inline-block controlbar-item goog-custom-button" onclick="back()">
					<div class="controlbar-image controlbar-forward-image"></div>
				</div>
				<div title="缩小" id="controlbarZoomOutButton" class="goog-inline-block controlbar-item goog-custom-button" onclick="smallit()">
					<div class="controlbar-image controlbar-minus-image"></div>
				</div>
				<div title="放大" id="controlbarZoomInButton" class="goog-inline-block controlbar-item goog-custom-button" onclick="bigit()">
					<div class="controlbar-image controlbar-plus-image"></div>
				</div>
			</div>
			</div>
		</td>
	</tr>
	<tr>
		<td>
		<div id="gallery" >
			<%
					if("f".equals(state)) {
				%>
			<div align="center">
				<img src="<%=path%>/images/pop_1.jpg" />
				<span><b>未找到预览文件！</b>
				</span>
			</div>
			<%
					}else {
				%>

			<div id="fullsize">
				<% 
							for(int i=0; i < imageNames.length; i++ ){
						%>
				<div>										
					<a name="<%=i+1 %>" title="第<%=i+1 %>页,共<%=imageNames.length %>页">	
						<img name="imgs" style="border:1px #666666 solid;cursor:hand "
							src="../preview_images/<%=fileName %>/<%=imageNames[i] %>" />
					 </a>
					 <span style="font-size: 12px;">第<%=i+1 %>页,共<%=imageNames.length %>页</span>
				</div>
				<%
							}	
						%>
			</div>
			<%
					}
				%>
		</div>
		</td>
	</tr>
</table>
</div>
</body>
</html>