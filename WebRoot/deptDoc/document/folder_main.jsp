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
<title>部门文件柜</title>
<link rel="stylesheet" href="<%=path %>/css/formalize.css" />
<link rel="stylesheet" href="<%=path%>/deptDoc/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/deptDoc/css/style.css" type="text/css">

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>

<script type="text/javascript">
      $(document).ready(function () {
    	  var zNodes = eval('${folderNodes}');
    	  buildTree(zNodes);
    	  showDocs(zNodes[0].SID,zNodes[0].LEVEL);
    	  
    	  $("#folderTree").height($(window).height()-50);
    	  $("#documentsIframe").height($(window).height()-50);
    	  
      });
      
      function buildTree(zNodes){
			var setting = {
	 				callback: {
	 					onClick: onDirClick
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

	  			$.fn.zTree.init($("#folderTree"), setting, zNodes);
      }
      
      function onDirClick(event, treeId, treeNode, clickFlag){
    	  showDocs(treeNode.SID,treeNode.LEVEL);
      }
      
      function showDocs(catalogId, catalogLevel){
    	  $('#documentsIframe').attr('src','<%=path%>/zdocsFile/documents.action?catalogId='+catalogId+'&catalogLevel='+catalogLevel);
      }
      
      function reloadDirTree(selectedNodeId){
    	  var zTree = $.fn.zTree.getZTreeObj("folderTree");
    	  zTree.destroy();
   		  $.post(
				'/portal/zdocsCatalog/folderTree.action?flag=${param.flag}&random='+Math.random(),
				{},
				function(obj, textStatus, jqXHR){
					buildTree(eval(obj));
			   		var zTree = $.fn.zTree.getZTreeObj("folderTree");
			   		if(selectedNodeId){
				   		var node = zTree.getNodeByParam("SID", selectedNodeId, null);
				   		zTree.expandNode(node, true);
				   		zTree.selectNode(node);			   			
			   		}
				},
				"json"
			).error(function() { alert("服务器连接失败，请稍后再试!"); });
   		

   		
      }
</script>


</head>

<body>

<div class="content_wrap">
<div class="content">
	<div class="left" style="border-right:1px dashed #000;width:20%;">
	<ul id="folderTree" class="ztree"></ul>
	</div>
<div id="docs" class="right" style="width:70%">
<iframe id="documentsIframe" frameborder="0" style="padding-left:20px;display: inline-block; height: 560px; width: 100%;" src="" scrolling="auto" ></iframe>
</div>
</div>
</div>

</body>
</html>