<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>用户信息导入</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script src="uploadify/swfobject.js"></script>

<link href="uploadify/uploadify.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"> 
        $(document).ready(function() { 
            $("#fileupload").uploadify({ 
                /*注意前面需要书写path的代码*/ 
                'folder'    	 : '../image/'   ,     //要上传到的服务器路径，默认‘/’ 
                'uploader'       : 'uploadify/uploadify.swf', 
                'script'         : 'excelImport.action', 
                'cancelImg'      : 'uploadify/cancel.png', 
                'queueID'        : 'fileQueue', //和存放队列的DIV的id一致 
                'fileDataName'   : 'fileupload', //和以下input的name属性一致 
                'auto'           : false, //是否自动开始 
                'multi'          : true, //是否支持多文件上传 
                'buttonText'     : 'browse', //按钮上的文字 
                'simUploadLimit' : 3, //一次同步上传的文件数目 
                'sizeLimit'      : 1024000000, //设置单个文件大小限制  单位byte
                'queueSizeLimit' : 2, //队列中同时存在的文件个数限制 
                'fileDesc'       : '支持格式:*.', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 
                'fileExt'        : '*.*',//允许的格式    '*.jpg;*.bmp;*.png;*.gif'
	            onComplete: function (event, queueID, fileObj, response, data) {
	            	//alert(fileObj.name);
	            	//alert(response);
	            	
					$('<li></li>').appendTo('.files').text(response); 
					//$("#preview").append("<img src='"+"<%=request.getContextPath()%>/image/"+response+"'></img>");
					//$('#upload').attr("src","<%=request.getContextPath()%>/image/"+response); 
					//$("#preview").show(); 
					var f=  encodeURI(fileObj.name);
					$("#download").append("<a href='"+"<%=request.getContextPath()%>/userManage/excelDownload.action?saveName="+response+"&fileName="+f+"'>"+fileObj.name+"</a>"+"<br>");
					//$('#upload').attr("src","<%=request.getContextPath()%>/image/"+response); 
					$("#download").show(); 
					
					}, 
				onAllComplete:function(event,data) {  
				 //alert(data.filesUploaded);
				 },
				onError: function(event, queueID, fileObj) { 
					            alert("文件:" + fileObj.name + "上传失败"); 
					  }, 
				onCancel: function(event, queueID, fileObj){ 
					       alert("取消了" + fileObj.name); 
					  } 
					 }); 
					
				}); 
        </script>
		
		<script type="text/javascript"> 
		                  //必须的 
		function uploadifyUpload(){ 
		   $('#fileupload').uploadifyUpload(); 
		} 
		</script>

</head>

<body>
<table>
	<tr>
		<td>上传EXCEL：</td>
		<td><input type="file" name="fileupload" id="fileupload" />
		<div id="fileQueue"></div>
		<p><a href="javascript:void(0);"
			onClick="javascript:uploadifyUpload()">开始上传</a>&nbsp; <a
			href="javascript:jQuery('#fileupload').uploadifyClearQueue()">取消所有上传</a>
		</p>
		<ol class="files"></ol>
		</td>
	</tr>
</table>

<br>
预览
<div id="preview" style="display:none;"></div>


下载
<div id="download" style="display:none;"></div>
<!--Table End-->
</body>
</html>
