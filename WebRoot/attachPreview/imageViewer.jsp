<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String path = request.getContextPath();

	String state = (String)request.getAttribute("state");
	String dirName = (String)request.getAttribute("dirName");
	String imageName = (String)request.getAttribute("imageName");	
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<title>附件在线预览</title>
<style type="text/css"> 
body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; } 
td { font-size: 12px; line-height: 150%; } 
</style> 
<script type="text/javascript"> 
drag = 0 
move = 0 

// 拖拽对象 
// 参见：http://blog.sina.com.cn/u/4702ecbe010007pe 
var ie=document.all; 
var nn6=document.getElementById&&!document.all; 
var isdrag=false; 
var y,x; 
var oDragObj; 

function moveMouse(e) { 
if (isdrag) { 
oDragObj.style.top = (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y)+"px"; 
oDragObj.style.left = (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x)+"px"; 
return false; 
} 
} 

function initDrag(e) { 
var oDragHandle = nn6 ? e.target : event.srcElement; 
var topElement = "HTML"; 
while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") { 
oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement; 
} 
if (oDragHandle.className=="dragAble") { 
isdrag = true; 
oDragObj = oDragHandle; 
nTY = parseInt(oDragObj.style.top+0); 
y = nn6 ? e.clientY : event.clientY; 
nTX = parseInt(oDragObj.style.left+0); 
x = nn6 ? e.clientX : event.clientX; 
document.onmousemove=moveMouse; 
return false; 
} 
} 
document.onmousedown=initDrag; 
document.onmouseup=new Function("isdrag=false"); 

function clickMove(s){ 
if(s=="up"){ 
dragObj.style.top = parseInt(dragObj.style.top) + 100; 
}else if(s=="down"){ 
dragObj.style.top = parseInt(dragObj.style.top) - 100; 
}else if(s=="left"){ 
dragObj.style.left = parseInt(dragObj.style.left) + 100; 
}else if(s=="right"){ 
dragObj.style.left = parseInt(dragObj.style.left) - 100; 
} 

} 

function smallit(){ 
var height1=images1.height; 
var width1=images1.width; 
images1.height=height1/1.2; 
images1.width=width1/1.2; 
} 

function bigit(){ 
var height1=images1.height; 
var width1=images1.width; 
images1.height=height1*1.2; 
images1.width=width1*1.2; 
} 
function realsize() 
{ 
images1.height=images2.height; 
images1.width=images2.width; 
block1.style.left = 0; 
block1.style.top = 0; 

} 
function featsize() 
{ 
var width1=images2.width; 
var height1=images2.height; 
var width2=360; 
var height2=200; 
var h=height1/height2; 
var w=width1/width2; 
if(height1<height2&&width1<width2) 
{ 
images1.height=height1; 
images1.width=width1; 
} 
else 
{ 
if(h>w) 
{ 
images1.height=height2; 
images1.width=width1*height2/height1; 
} 
else 
{ 
images1.width=width2; 
images1.height=height1*width2/width1; 
} 
} 
block1.style.left = 0; 
block1.style.top = 0; 
} 

</SCRIPT> 
<script language="JavaScript" type="text/JavaScript"> 
<!-- 
function MM_reloadPage(init) { //reloads the window if Nav4 resized 
if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) { 
document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }} 
else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload(); 
} 
MM_reloadPage(true); 
//--> 
</script> 
<style type="text/css"> 
<!-- 
td, a { font-size:12px; color:#000000 } 
#Layer1 { position:absolute; z-index:100; top: 10px; width:98%;} 
#Layer2 { position:absolute; z-index:1; } 
--> 
</style> 
</head>
<body > 
<div id="Layer1" > 
<table border="0" cellspacing="2" cellpadding="0" align="right"> 
<!-- 
<tr> 
<td> </td>  
<td><img src="../images/img/up.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('down')" title="向上"></td> 
<td> </td> 
</tr> 
-->
<tr>
<!-- 
<td><img src="../images/img/left.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('right')" title="向左"></td> 
 --> 
<td></td>
<td><img src="<%=request.getContextPath()%>/css/default/images/files/zoom.gif" width="20" height="20" style="cursor:hand" onClick="realsize();" title="还原"></td> 
<!-- 
<td><img src="../images/img/right.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('left')" title="向右"></td> 
 -->
</tr> 
<!-- 
<tr> 
<td> </td> 
<td><img src="../images/img/down.gif" width="20" height="20" style="cursor:hand" onClick="clickMove('up')" title="向下"></td> 
<td> </td> 
</tr> 
 -->
<tr> 
<td> </td> 
<td><img src="<%=request.getContextPath()%>/css/default/images/files/zoom_in.gif" width="20" height="20" style="cursor:hand" onClick="bigit();" title="放大"></td> 
<td> </td> 
</tr> 
<tr> 
<td> </td> 
<td><img src="<%=request.getContextPath()%>/css/default/images/files/zoom_out.gif" width="20" height="20" style="cursor:hand" onClick="smallit();" title="缩小"></td> 
<td> </td> 
</tr> 
</table> 
</div> 
<p>
 
<div id='hiddenPic' style='position:absolute; left:100px; top:0px; width:0px; height:0px; z-index:1; visibility: hidden;'><img name='images2' src="../preview_images/<%=dirName %>/<%=imageName %>" border='0' /></div> 
<div id='block1' onmouseout='drag=0' onmouseover='dragObj=block1; drag=1;' align="center"  class="dragAble"> <img name='images1' src="../preview_images/<%=dirName %>/<%=imageName %>" border='0' align="center" /></div> 
</body> 
</html>
