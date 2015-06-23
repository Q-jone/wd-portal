<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String rootId = request.getParameter("rootId");
	String checkNodeId = request.getParameter("checkNodeId");
	if(rootId==null){
		rootId = "2500";
	}
	if(checkNodeId==null){
		checkNodeId = "";
	}
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="../ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="../ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script src="../js/jquery-1.7.1.js"></script>
<script src="../ztree/jquery.ztree.all-3.2.min.js"></script>
<script type="text/javascript">
var zNodes =[]; 
function loadTree(){
	$.ajax({
			type: 'POST',
			url: '/portal/organTree/generatePrimeData.action?random='+Math.random(),
			data:{
					"rootId":"<%=rootId%>",
					"checkNodeId":"<%=checkNodeId%>"
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){			
				$.fn.zTree.init($("#treeDemo"), setting, obj);
				checkNode();	
			}  
		});	
}

		<!--
		var setting = {
			async: {
				enable: true,
				url:"/portal/organTree/getZtreeNodes.action",
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
				//onAsyncSuccess : onAsyncSuccess
			}
		};

		
		
		function checkNode(){
			//alert(1);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			key = "cid";
			value = "<%=checkNodeId%>";
			var arr = value.split(",");
			for(var m=0,n=arr.length;m<n;m++){
				var node = zTree.getNodeByParam(key, arr[m], null);
				//alert(node);
				if(node==null){}
				else{
					var parentNode = node.getParentNode();
					zTree.expandNode(parentNode, true, '', false,'');
					zTree.checkNode(node, true,false, true);
				}
			}
		}
		

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
				v4 += nodes[i].deptId + ",";
				v5 += nodes[i].deptName + ",";
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
			
			if (v4.length > 0 ) v4 = v4.substring(0, v4.length-1);
			var cityObj = $("#citySel4");
			cityObj.attr("value", v4);
			
			if (v5.length > 0 ) v5 = v5.substring(0, v5.length-1);
			var cityObj = $("#citySel5");
			cityObj.attr("value", v5);
		}

		

	
		$(document).ready(function(){
			loadTree();
			
			//onAsyncSuccess();
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
	<input type="button" onclick="searchNodes('','');">
	<div id="menuContent" class="menuContent">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:300px; height: 600px;"></ul>
</div>
</div>

</body>
<script type="text/javascript">
</script>
</html>