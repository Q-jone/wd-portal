<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="<%=path %>/css/formalize.css" />
<link rel="stylesheet" href="<%=path%>/deptDoc/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/deptDoc/css/style.css" type="text/css">

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>

<script type="text/javascript">
      $(document).ready(function () {
    	    //alert('${folderNodes}');
  			var setting = {
 				check: {
 					enable: true,
 					chkboxType: { "Y": "", "N": "" }
 				},  							
  				data: {
  					key: {
  						name: "CATALOG_NAME"
  					},
  					simpleData: {
  						enable: true,
  						idKey: "SID",
  						pIdKey: "PARENT_SID"
  					}
  				}
  			};

  			var zNodes = eval('${folderNodes}');

  			$(document).ready(function(){
  				$.fn.zTree.init($("#folderTree"), setting, zNodes);
  			});
      });
      
      function doCopy(){
    	  var fileIds = '${param.fileIds}';
    	  var op = '${param.op}';
    	  var destDirIds = '';
		  var zTree = $.fn.zTree.getZTreeObj("folderTree");
		  var nodes = zTree.getCheckedNodes();
		  if(nodes.length == 0){
			  alert('没有勾选目标文件夹！');
			  return;
		  }
		  for (var i=0, l=nodes.length; i<l; i++) {
			  destDirIds += ','+nodes[i].SID;
		  }
		  destDirIds = destDirIds.substr(1);
     		$.post(
					'/portal/zdocsFile/copyToFolders.action?op='+op+'&random='+Math.random(),
					{
						"fileIds": 	fileIds,
						"destDirIds": 	destDirIds
					},
					function(obj, textStatus, jqXHR){
						if(obj == '1'){
							alert('文件已成功复制到目标文件夹！');	
							parent.list();
						}else{
							alert("服务器连接失败，请稍后再试!");
						}
					},
					"json"
				).error(function() { alert("服务器连接失败，请稍后再试!"); });
      }
</script>


</head>

<body>

<div>
	<ul id="folderTree" class="ztree" style="height:310px;width:270px;"></ul>
 	<div style="text-align:center;margin:10px;">
 		<input class="btn" type="button" value="提交" onclick="doCopy()"/>
 	</div>
</div>

</body>
</html>