<%@ page contentType="text/html; charset=UTF-8"%>
<%
response.setHeader("Charset","UTF-8'");
String path = request.getContextPath();
String zoneId = request.getParameter("zoneId");
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="../organTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="../organTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script src="../organTree/js/jquery-1.7.2.min.js"></script>
<script src="../organTree/js/jquery.ztree.all-3.5.min.js"></script>
		<link type="text/css" href="../organTree/css/flick/jquery-ui-1.8.21.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="../organTree/js/jquery-ui-1.8.21.custom.min.js"></script>
		<script src="js/special.js"></script>
	<script type="text/javascript">
		var zoneId = "<%=zoneId%>";
		
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
				beforeCheck: beforeCheck,
				onCheck: onCheck,
				beforeAsync: beforeAsync,
				onAsyncSuccess: onAsyncSuccess
				
			}
		};

	

		function filter(treeId, parentNode, childNodes) {
				if (!childNodes) return null;
				for (var i=0, l=childNodes.length; i<l; i++) {
					childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
				}
				return childNodes;
			}

		function beforeCheck(treeId, treeNode) {
			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			//zTree.checkNode(treeNode, !treeNode.checked, null, true);
			//alert(treeNode.doCheck);
			return (treeNode.doCheck !== false);
		}
		
		function onCheck(e, treeId, treeNode) {
			/**/
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
			var cityObj = $("#nodeId");
			cityObj.attr("value", v1);

			if (v3.length > 0 ) v3 = v3.substring(0, v3.length-1);
			var cityObj = $("#nodeName");
			cityObj.attr("value", v3);
			
		}	
		
		function reset(){
			$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
			$("#nodeId").val("");
			$("#nodeName").val("");
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
					//var node = zTree.getNodeByParam("id", root , null);
					chkSelectedNodes();
				}  
			});
		}

		//根据父页面提供信息初始化参数
		function initParamValue(){
			if(zoneId!=null){
				var zone = $("#"+zoneId,self.opener.document);
				root = $(zone).attr("root");
				targetId = $(zone).attr("nodeId");
				targetNameId = $(zone).attr("nodeName");
				var checkNodesZoneId = $(zone).attr("checkNode");
				checkNodes = $("#"+checkNodesZoneId,self.opener.document).val();
			}else{
				alert("zone null");
				return false;
			}
		}

		//自动展开并选中指定节点
		function initByZNodes(){
			$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
			if(checkNodes.length>0){
				getNodePath();
			}
		}

		//获得根节点json
		function initRootNodes(){
			if(zNodes==""){
				var url = "<%=path %>/organTree/getNodesInfo.action";
				$.getJSON(
					url,
					{
						id:root,
						rand:new Date().getTime()
					},
					function(json){
						if(json){
							json[0].nocheck=true;
							root = json[0].id;
							json[0].icon = "/portal/cfconsole/8.png";
							zNodes = json;
							initByZNodes();
						}
					}
				);
			}else{
				//alert(zNodes);
			}
		}

		
		
		function intiButton(){
			$("#choiceOk").click(function(){
				$("#"+targetId,window.opener.document).attr("value",$("#nodeId").val());
				$("#"+targetNameId,window.opener.document).attr("value",$("#nodeName").val());
				self.close();
				return false;
			});

			$("#choiceCancel").click(function(){
				self.close();
				return false;
			});
			$("#chkAllNodes").click(function(){
				chkAllNodes();
			});
			$("#chkDeptNodes").click(function(){
				chkDeptNodes();
			});
			$("#chkMetroNodes").click(function(){
				chkMetroNodes();
			});
			$("#chkProjectNodes").click(function(){
				chkProjectNodes();
			});
			$("#chkComNodes").click(function(){
				chkComNodes();
			});
			$("#reset").click(function(){
				reset();
			});
			
		}
		
		
		
		$(document).ready(function(){
			initParamValue();
			initRootNodes();
			intiButton();
		});
		
	</script>
	<style>
		.transparent{
			position:absolute;
			/*min-height: 100%;  For Modern Browsers */
			/*height: auto !important;  For Modern Browsers */
			/*height: 100%;  For IE */
			filter:alpha(opacity=30);
			-moz-opacity:0.3;
			opacity:0.3;
			z-index:999;
			top:0;
			left:0;
			width:100%;
			background:#000;
		}
	</style>
</head>
<body>
<div class="transparent" id="maskDiv" style="display: none;"></div>
<div id="msgDiv" style="font-size:12px;position:absolute;display:none;z-index:9999;">数据加载中，请稍候...</div>
<div style="width:650px;">
	<ul>
		<form>
		<font style="font-size:12px;">
		&nbsp;&nbsp;请选择: 
		</font>
		<input id="nodeName" type="text" readonly value="" style="width:75%;"/>
		<input id="nodeId" type="hidden" readonly value="" style="width:80%;"/>
		<input id="choiceOk" type="button" value="确定"/>
		<input id="choiceCancel" type="button" value="取消"/>
		<input id="chkAllNodes" type="button" value="全选"/>
		<input id="reset" type="button" value="清空"/>
		<input id="chkDeptNodes" type="button" value="check集团部门"/>
		<input id="chkMetroNodes" type="button" value="check运营公司"/>
		<input id="chkProjectNodes" type="button" value="check项目公司"/>
		<input id="chkComNodes" type="button" value="check集团单位下办公室"/>
		
		</form>
	</ul>
	<ul id="treeDemo" class="ztree" style="border:none;margin-top:0; width:100%; height: 600px;"></ul>
</div>

</body>
</html>