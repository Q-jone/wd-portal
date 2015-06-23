<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="../ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="../ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script src="../js/jquery-1.7.1.js"></script>
<script src="../ztree/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript">
		<!--
		var setting = {
			async: {
				enable: true,
				url:"/portal/organTree/loadTreeForDept.action",
				autoParam:["cid"],
				dataFilter: filter
			},
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "cid",
					pIdKey:"pid"
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck,
				onAsyncSuccess : zTreeOnAsyncSuccess
			}
		};

		var zNodes =[ 
		{name:"上海申通地铁集团有限公司", cid:"2500,dept",pid:"0",isParent:true,nocheck:true} //初始化一个顶层默认节点 
		]; 

		
		function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		    alert(1);
		    if(treeNode.level==0){
		    	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		    	zTree.expandNode(treeNode, '', '', false,'');
		    }
		};

		function filter(treeId, parentNode, childNodes) {
				if (!childNodes) return null;
				for (var i=0, l=childNodes.length; i<l; i++) {
					childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
				}
				return childNodes;
			}

		function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			nodes = zTree.getCheckedNodes(true);
			v1 = "";
			v2 = "";
			v3 = "";
			v4 = "";
			v5 = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v1 += nodes[i].id + ",";
				v3 += nodes[i].name + ",";
			}
			if (v1.length > 0 ) v1 = v1.substring(0, v1.length-1);
			var cityObj = $("#citySel");
			cityObj.attr("value", v1);	
			
			if (v3.length > 0 ) v3 = v3.substring(0, v3.length-1);
			var cityObj = $("#citySel3");
			cityObj.attr("value", v3);
			
		}
		
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			
		});
		//-->
	</script>
	
</head>
<body>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul class="list">
			<li class="title">&nbsp;&nbsp;<span class="highlight_red">勾选 checkbox 或者 点击节点 进行选择</span></li>
			<li class="title">&nbsp;&nbsp;Test: <input id="citySel" type="text" readonly value="" style="width:120px;" />
			<li class="title">&nbsp;&nbsp;Test: <input id="citySel3" type="text" readonly value="" style="width:120px;" />
		</ul>
	</div>
	
	<div id="menuContent" class="menuContent">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:300px; height: 600px;"></ul>
</div>
</div>

</body>
</html>