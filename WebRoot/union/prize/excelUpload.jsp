<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=9">
<title>文件上传</title>

<link rel="stylesheet" href="<%=path%>/union/js/uploadify/uploadify.css" />
<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/union/js/uploadify/jquery.uploadify-3.1.js"></script>

<link rel="stylesheet" href="<%=path%>/css/formalize.css" />
<link rel="stylesheet" href="<%=path%>/css/page.css" />
<link rel="stylesheet" href="<%=path%>/css/imgs.css" />
<link rel="stylesheet" href="<%=path%>/css/reset.css" />
<script src="<%=path%>/js/html5.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>

<script type="text/javascript">
var uploadUrls = {
	'1' : '<%=path%>/unionPersonalPrize/batchUpload.action',
	'2' : '<%=path%>/unionTeamPrize/batchUpload.action',
	'3' : '<%=path%>/unionProjectPrize/batchUpload.action',
	'4' : '<%=path%>/unionAchivementPrize/batchUpload.action'
}
var templateFileName = {
	'1' : 'personalTemplate.xls',
	'2' : 'teamTemplate.xls',
	'3' : 'projectTemplate.xls',
	'4' : 'achivementTemplate.xls'		
}

//关闭窗口
function shut(){
  window.opener=null;
  window.open("","_self");
  window.close();
}
var queueUploadStart = false;
var responseDate = '';
$(function(){
	$("#fileupload").uploadify({
       'auto' : false,
       'multi' : true, //是否支持多文件上传 
       'method' : "post",
       'height' : 20,
       'width' : 100,
       'swf' : '<%=path%>/union/js/uploadify/uploadify.swf', 
       'uploader' : uploadUrls['${prize.prizeType}']+'?prizeId=${prize.id}&applyId=${param.applyId}',
       'fileTypeDesc' : '格式:xls',		//描述
       'fileTypeExts' : '*.xls',			//文件类型
       'fileSizeLimit' : '30000KB',			//文件大小
       'buttonText' : '选择文件',			//按钮名称
       'fileObjName'	:'fileupload',
       'successTimeout' : 300,
       'requeueErrors' : false,
       'removeTimeout' : 1,
       'removeCompleted' : true,
       'onUploadStart': function(file){
    	   if(!queueUploadStart){
        		$("#showTR").show();
          		$("#showId").html($('#fileUpload_processing').html());
          		responseDate = '';
          		queueUploadStart = true;
    	   }
      },       
       'onSelectError' : function(file,errorCode,errorMsg) {
       		if(errorCode==-110){
       			this.queueData.errorMsg = "文件太大，无法上传！";
       		}
        }, 
       'onUploadSuccess' : function(file, data, response){
    	   responseDate += data;
    	},
    	'onQueueComplete' : function(queueData){
    		$("#showId").html(responseDate);
    		queueUploadStart = false;
    	}
	});
});

function downloadTemplete(){
	window.location.href='<%=path%>/unionPrize/downloadTemplete.action?fileName='+templateFileName['${prize.prizeType}'];
}

</script>

</head>

<body>
<div class="mb10">

<table width="100%" class="table_1">
	<thead>
		<th colspan="2" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd" width="200px;">
				上传文件<br/>
				<a href='javascript:void(0);' onclick="downloadTemplete();" style="color:#0000ff;text-decoration:underline;">下载模板</a>
			</td>
			<td>
				<input type="file" name="fileupload" id="fileupload" />
				<input type="button" value="上传" onclick="$('#fileupload').uploadify('upload','*');">
				<!-- <input type="button" value="取消" onclick="$('#uploadify').uploadify('stop');"> -->
			</td>
		</tr>
		 
		<tr id="showTR" style="display: none;">
			<td class="t_r">反馈结果</td>
			<td id="showId"></td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="2" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/>
		</td>
	</tr>
</table>

</div>
<!--Table End-->
<div id="fileUpload_processing" style="width:100%;align:center;line-height:20px;display:none;" class="">
     <p style="width:auto;display:inline;"><img src="<%=path %>/operation/ui/images/loading.gif" style="display:inline;"/>
     <b style="color:green;display:inline;">&nbsp;正在处理</b></p>
</div>
</body>
</html>
