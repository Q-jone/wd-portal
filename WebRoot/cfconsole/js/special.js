var root = "";//树根节点id
var checkNodes = "";//默认选中id
var zNodes = "";//顶层默认节点 json
var targetId = "";//父页面id input的id值
var targetNameId = "";//父页面名称input的id值
var arr = new Array();
var curStatus = "init";
var curCount = 0
//强行异步加载某个父节点下的子节点
function expandNodes(nodes,status){
	if (!nodes) return;
	curStatus = status;
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	for (var i=0, l=nodes.length; i<l; i++) {
		zTree.expandNode(nodes[i], true, false, false);
	}
}
//check已选节点
function chkSelectedNodes(){
	reset();
	//$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	expandNodes(zTree.getNodes(),"checkSelected");
}
//checkAll
function chkAllNodes(){
	reset();
	//$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	expandNodes(zTree.getNodes(),"checkAll");	
}

//check集团部门
function chkDeptNodes(){
	reset();
	//$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	expandNodes(zTree.getNodes(),"checkDept");	
}

//check运营公司下办公室
function chkMetroNodes(){
	reset();
	//$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	expandNodes(zTree.getNodes(),"checkMetro");	
}

//check项目公司下办公室
function chkProjectNodes(){
	reset();
	//$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	expandNodes(zTree.getNodes(),"checkProject");	
}


//check集团单位下办公室
function chkComNodes(){
	reset();
	//$.fn.zTree.init($("#"+"treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	expandNodes(zTree.getNodes(),"checkCom");	
}



//回调函数

function beforeAsync() {
	if (curStatus != "init" && curStatus != ""){
		showMask();
	}
	curCount++;
}

//异步加载成功回调函数
function onAsyncSuccess(event, treeId, treeNode, msg) {
	
	curCount--;
	if(curStatus=="checkSelected"&&arr.length>0){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		arr.splice(0,1);
		var nodes = zTree.getNodesByParam("id", arr[0] , null);
		if (arr.length>0) {
			expandNodes(nodes,"checkSelected");
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
	}else if(curStatus=="checkAll"){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeNode.children;
		if(nodes!=null&&nodes.length>0){
			expandNodes(nodes,"checkAll");
			for(var i=0;i<nodes.length;i++){
				if(nodes[i]!=null){
				//选中节点
					zTree.checkNode(nodes[i], true, false, true);
				}
			}
		}
	}else if(curStatus=="checkDept"){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeNode.children;
		if(nodes!=null&&nodes.length>0&&treeNode.typeId=="2501"){
			for(var i=0;i<nodes.length;i++){
				if(nodes[i]!=null&&nodes[i].typeId=="2"){
				//选中节点
					zTree.checkNode(nodes[i], true, false, true);
				}
			}
		}
	}else if(curStatus=="checkMetro"){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeNode.children;
		if(nodes!=null&&nodes.length>0){
			for(var i=0;i<nodes.length;i++){
				if(nodes[i]!=null&&nodes[i].name.substr(-4,4)=="运营公司"&&nodes[i].doCheck){
				//选中节点
					zTree.checkNode(nodes[i], true, false, true);
				}else if(nodes[i]!=null&&nodes[i].name.substr(-4,4)=="运营公司"&&!nodes[i].doCheck){
					var node = zTree.getNodesByParam("id", nodes[i].id , null);
					expandNodes(node,"checkMetro");
				}else if(treeNode.name.substr(-4,4)=="运营公司"&&nodes[i].name=="综合办公室"&&nodes[i].doCheck){
					zTree.checkNode(nodes[i], true, false, true);
				}
			}
		}
	}else if(curStatus=="checkProject"){
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeNode.children;
		if(nodes!=null&&nodes.length>0){
			for(var i=0;i<nodes.length;i++){
				if(nodes[i]!=null&&nodes[i].name.substr(-4,4)=="项目公司"&&nodes[i].doCheck){
				//选中节点
					zTree.checkNode(nodes[i], true, false, true);
				}else if(nodes[i]!=null&&nodes[i].name.substr(-4,4)=="项目公司"&&!nodes[i].doCheck){
					var node = zTree.getNodesByParam("id", nodes[i].id , null);
					expandNodes(node,"checkProject");
				}else if(treeNode.name.substr(-4,4)=="项目公司"&&nodes[i].name=="综合办公室"&&nodes[i].doCheck){
					zTree.checkNode(nodes[i], true, false, true);
				}
			}
		}
	}
	
	
	if (curCount <= 0) {
		if (curStatus != "init" && curStatus != "") {
			$("#maskDiv").hide();
			$("#msgDiv").hide();
		}
		curStatus = "";
	}
}

//遮盖层
function showMask(){
	var mask = $("#maskDiv");
	var msg = $("#msgDiv");
	if($(mask).is(":hidden")) 
	{ 
		var pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		var pageHeight = $(document).height();
		$(mask).animate({
				height :pageHeight,
				width :pageWidth,
				opacity :'show'
			},{
				duration :0,
				complete : function() {
					$(mask).css("filter","alpha(opacity=30)");
					var top = ($(window).height() - $(msg).height())/2;
					var left = ($(window).width() - $(msg).width())/2;
					$(msg).css("left",left+"px");
					$(msg).css("top",top+"px");
					$(msg).show();
				}
			});
	}
	$(mask).css("height",($.browser.version=="6.0")?$(document).width()-21:$(document).width()).css("width",$(document).height());
}
