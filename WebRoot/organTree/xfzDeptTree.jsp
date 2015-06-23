<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String checkNode = request.getParameter("checkNode");
	if(checkNode==null){
		checkNode = "";
	}
	String rootId = request.getParameter("rootId");
	if(rootId==null){
		rootId = "2500";
	}
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/demo.css" type="text/css">
<link rel="stylesheet" href="css/themes/base/jquery.ui.all.css" type="text/css">

	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery.ztree.all-3.2.min.js"></script>
		<link type="text/css" href="css/flick/jquery-ui-1.8.21.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript">

var arr = new Array();
var root = "<%=rootId%>";
var checkNodes = "<%=checkNode%>";


		<!--
		var setting = {
			async: {
				enable: true,
				url:"/portal/organTree/getChildNodes.action",
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
			if(arr.length>0){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				expand(treeNode);
				arr.splice(0,1);
				var node = zTree.getNodeByParam("id", arr[0] , null);
				if(arr.length>0){
					asyncNode(node);
				}else{
					var checkarr = checkNodes.split(",");
					for(var i=0;i<checkarr.length;i++){
						var node = zTree.getNodeByParam("id", checkarr[i], null);
						if(node!=null){
						//选中节点
							zTree.checkNode(node, true, false, true);
						}
					}
				}
			}
		}
		
		//展开节点
		function expand(node){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			status = "expand";
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
				v3 += nodes[i].name + ",";
			}
			if (v1.length > 0 ) v1 = v1.substring(0, v1.length-1);
			var cityObj = $("#citySel");
			cityObj.attr("value", v1);

			if (v3.length > 0 ) v3 = v3.substring(0, v3.length-1);
			var cityObj = $("#citySel3");
			cityObj.attr("value", v3);	
		}	
		
		
		//初始化树后  若有checknode  ajax得到该节点经过的路径  若路径最高点 大于 传入的 根节点  则舍弃
		function getNodePath(){
			$.ajax({
			type: 'POST',
			url: '/portal/organTree/getRelatedNodes.action?random='+Math.random(),
			data:{
					"id":checkNodes
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){	
				var j = 0;	
				for(var i=0;i<obj.length;i++){
					if(obj[i].id>=root*1){
						arr[j] = obj[i].id;
						j++;
					}
				}
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var node = zTree.getNodeByParam("id", root , null);
				asyncNode(node);
			}  
		});	
		}
	
		$(document).ready(function(){
				// Dialog
				$('#dialog').dialog({
					autoOpen: false,
					height: 600,
					width: 800,
					modal: true,
					buttons: {
						"确定": function() {
						//在此之前需要得到树对象
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");//获得树对象
						var nodes = treeObj.getCheckedNodes(true);
						if(nodes.length>0){
						dwbh = "";
						dwmc = "";
						for(var i=0;i<nodes.length;i++){
						dwbh += nodes[i].id+",";
						dwmc += nodes[i].name+",";
						}
						dwbh = dwbh.substring(0,dwbh.length-1);
						dwmc = dwmc.substring(0,dwmc.length-1);
						$("#ssdwbh").val(dwbh);
						$("#ssdwmc").val(dwmc);
						}
						$(this).dialog("close");
						},
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});
				
					// Dialog Link
				$('#dialog_link').click(function(){
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					if(checkNodes.length>0){
						getNodePath();
					}
					$('#dialog').dialog('open');
					return false;
				});
				
				
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//$.fn.zTree.init($("#treeDemo"), setting);
			//if(checkNodes.length>0){
				//getNodePath();
			//}
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
			<li class="title">&nbsp;&nbsp;SELECT: <input id="dialog_link" type="button" value="选择部门" style="width:120px;" />
			<li class="title">&nbsp;&nbsp;Test: <input id="ssdwbh" type="text" readonly value="" style="width:120px;" />
			<li class="title">&nbsp;&nbsp;Test: <input id="ssdwmc" type="text" readonly value="" style="width:120px;" />
		</ul>
	</div>
	
	
</div>

<div id="dialog" class="menuContent">
	<ul id="treeDemo" class="ztree" style="border:none;margin-top:0; width:800px; height: 600px;"></ul>
</div>



</body>
</html>