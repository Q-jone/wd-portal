<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<script src="../js/jquery-1.7.1.js"></script>
<script src="js/common.js"></script>
<script src="../js/loading.js"></script>
<script src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
<script src="uploadify/swfobject.js"></script>
<script src="js/excelTable.js"></script>
<link href="uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<style >
#userTable td{
font-size:12px;
line-height:25px;
border:1px solid #000000;
}
#userTable {
font-size:12px;
border-collapse:collapse;
} 
</style>
</head>
<body>
<form action="addUser.action" method="post">
<table>
<tr>
	<td>
		部门ID：<input type="text" id="deptId" name="deptId"  style="width:40%;">
		<br>
		部门名称：<input type="text" id="deptName" name="deptName"  style="width:40%;">
		&nbsp;<input type="button" id="selectDept" value="..."/>&nbsp;<input id="clearDept" type="button" value="清除"/>
		<font id="deptTreeZone" root="2500" checkNode="deptId" nodeId="deptId" nodeName="deptName"></font>
		<br>
		顺序：<input type="text" id="orders" name="orders"  style="width:40%;">
	</td>
</tr>
<tr><td><input type="button" onclick="addUserRow();" value="新增"/></td></tr>
</table>
<hr>
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

<hr>
<table id="userTable">
<tr>
	<td>工号</td><td>姓名</td><td>群组</td>
</tr>

</table>
<br>
<input type="button" onclick="clip()" value='粘贴' />
<br>
<input type="submit" onclick="return check();"/>
</form>
<table id="zs" style="display:none;">
	<tr name="userTr">
		<td><input type="text" name="loginName" id="loginName"><input type="hidden" name="count" id="count"></td>
		<td><input type="text" name="userName" id="userName"></td>
		<td><input type="text" name="group" id="group"></td>
	</tr>
</table>

</body>
</html>