<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String checkNode = request.getParameter("checkNode");
	if(checkNode==null){
		checkNode = "";
	}
	String rootId = request.getParameter("rootId");
	if(rootId==null){
		rootId = "2549";
	}
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript">
var arr = new Array();
var root = "<%=rootId%>";
var checkNodes = "<%=checkNode%>";


		<!--
		var setting = {
			async: {
				enable: true,
				//url:"/portal/organTree/getDeptUsers.action",
				//url:"/portal/organTree/getDeptReceivers.action",
				//url:"/portal/organTree/getDeptLeaders.action",
				url:"/portal/organTree/getDeptSingleLeader.action",
				//autoParam:["id=nodesId"],
				autoParam:["id"],
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
					idKey: "id",
					pIdKey:"pid"
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck,
				onAsyncSuccess: onAsyncSuccess
			}
		};


		//强行异步加载某个父节点下的子节点
		function asyncNode(node){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.reAsyncChildNodes(node, "refresh", true);
		}
		
		//异步加载成功回调函数
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			expand(treeNode);
			var checkarr = checkNodes.split(",");
			for(var i=0;i<checkarr.length;i++){
				var node = zTree.getNodeByParam("id", checkarr[i], null);
				if(node!=null){
				//选中节点
					zTree.checkNode(node, true, false, true);
				}
		}
		}
		
		//展开节点
		function expand(node){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(node, true, false, false);
		}
		
		
		var zNodes =[ 
		{name:"上海申通地铁集团有限公司", id:root,pid:"0",isParent:true,nocheck:true}//初始化一个顶层默认节点 
		]; 

		

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
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v1 = "";
			v2 = "";
			v3 = "";
			v4 = "";
			v5 = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				v1 += nodes[i].id + ",";
				v2 += nodes[i].loginName + ",";
				v3 += nodes[i].name + ",";
			}
			if (v1.length > 0 ) v1 = v1.substring(0, v1.length-1);
			var cityObj = $("#citySel");
			cityObj.attr("value", v1);
			
			if (v2.length > 0 ) v2 = v2.substring(0, v2.length-1);
			var cityObj = $("#citySel2");
			cityObj.attr("value", v2);

			if (v3.length > 0 ) v3 = v3.substring(0, v3.length-1);
			var cityObj = $("#citySel3");
			cityObj.attr("value", v3);	
		}	
		

	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//$.fn.zTree.init($("#treeDemo"), setting);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("id", root , null);
			asyncNode(node);
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
			<li class="title">&nbsp;&nbsp;Test: <input id="citySel2" type="text" readonly value="" style="width:120px;" />
			<li class="title">&nbsp;&nbsp;Test: <input id="citySel3" type="text" readonly value="" style="width:120px;" />
			<li class="title">&nbsp;&nbsp;Test: <input id="citySel4" type="text" readonly value="" style="width:120px;" />
			<li class="title">&nbsp;&nbsp;Test: <input id="citySel5" type="text" readonly value="" style="width:120px;" />
		</ul>
	</div>
	
	<div id="menuContent" class="menuContent">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:300px; height: 600px;"></ul>
</div>
</div>

</body>
</html>