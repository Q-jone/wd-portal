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
 					enable: true
 				},  			
 				callback: {
 					onCheck: doCheck
 				}, 				
  				data: {
  					key: {
  						name: "NAME"
  					},
  					simpleData: {
  						enable: true
  					}
  				}
  			};

  			var zNodes = eval('${empNodes}');
  			$.fn.zTree.init($("#empTree"), setting, zNodes);
  			
  			init(window.dialogArguments.empIdField,
 				   window.dialogArguments.empNameField);
      });
      
  	//选择框初始化，将上次选择结果显示出来
  	function init(empId,empName) {
  		if (empId == null || empId.value == "") {
  			return false;
  		}
  		
  		var empIdArr = empId.value.split(",");
  			
  		var zTree = $.fn.zTree.getZTreeObj("empTree");
  		var checkboxes = zTree.getNodes();
  		if(checkboxes) {
  			for(var i=0; i<empIdArr.length; i++) {
  				for(var j=0; j<checkboxes.length; j++) {
  					if(empIdArr[i] == checkboxes[j].LOGIN_NAME) {
  						zTree.checkNode(checkboxes[j]);		
  					}
  				}
  			}
  			if(empName != null) {
  				document.getElementById("returnLable").value = empName.value;
  			}
  			if(empId != null) {
  				document.getElementById("returnValue").value = empId.value;
  			}
  		}
  	}	      
  	function doCheck(event, treeId, treeNode){
  		var zTree = $.fn.zTree.getZTreeObj("empTree");
		var returnLables = "";
		var returnValues = "";
		var checkboxes = zTree.getCheckedNodes();
		if(checkboxes) {
			for(var i=0; i<checkboxes.length; i++) {
				if(returnValues.indexOf(checkboxes[i].LOGIN_NAME)==-1) {
					returnLables += checkboxes[i].NAME + ",";
					returnValues += checkboxes[i].LOGIN_NAME + ",";
				}
			}		
		} 
		document.getElementById("returnLable").value = returnLables.substr(0, returnLables.length-1);
  		document.getElementById("returnValue").value = returnValues.substr(0, returnValues.length-1);	  		
  	}	
	//响应确定按钮，返回选择结果
	function doReturn() {
		var empId = document.getElementById('returnValue').value;
		var empName = document.getElementById('returnLable').value;
		if (empId.value == "") {
			alert("请您选择人员!");
			return false;
		}

		if(window.dialogArguments.empIdField != null) {
			window.dialogArguments.empIdField.value = empId;
		}
		if(window.dialogArguments.empNameField != null) {
			window.dialogArguments.empNameField.value = empName;
		}
		
		window.returnValue="1";				
		window.close();
	}  	
	
	function check_all(checked){
		var zTree = $.fn.zTree.getZTreeObj("empTree");
		zTree.checkAllNodes(checked);
		doCheck();
	}
</script>


</head>

<body>

<div style="width:230px;">
	<div><input type='text' id='returnLable' readonly='true' size='30'/>
	<input type='hidden'  id='returnValue'/>
				<input type='hidden' id='returnId'/>				
				<input class="btn" type='button' value='确定' onclick="doReturn()"/>
				<input class="btn" type='button' value='关闭' onclick='javascript:window.close()'/>
				<input class="btn" type='button' value='全选' onclick='check_all(true)'/>
				<input class="btn" type='button' value='清除' onclick='check_all(false)'/>
	</div>
	<ul id="empTree" class="ztree" style="height:310px;width:220px;"></ul>
</div>

</body>
</html>