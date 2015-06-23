<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,com.wonders.stpt.operation.entity.bo.OpDictionary"%>
<%
	String contextPath = (String) request.getContextPath();

	List<OpDictionary> xlDepts = (List<OpDictionary>)request.getAttribute("xlDepts");
	List<OpDictionary> occDepts = (List<OpDictionary>)request.getAttribute("occDepts");
	List<OpDictionary> zyDepts = (List<OpDictionary>)request.getAttribute("zyDepts");

%>

<HTML>
<HEAD>
	<title>涉及线路/OCC/专业单位多选</title>
	<link href="<%=contextPath %>/css/organTree.css" rel="stylesheet" type="text/css">
	<script src="../js/jquery-1.7.1.js"></script>
</HEAD>	

<BODY onload="init(window.dialogArguments.deptIdField,
				   window.dialogArguments.deptNameField)">
	<div style="width:100%; overflow: auto; cursor: default; display: inline; position: absolute; height: 100%;">
	<table cellpadding='0' cellspacing='0'>
		<tr class="FixedTitleRow" >
		<td><input type='text' id='returnLable' readonly='true' size='40'/>
				<input type='hidden' id='returnValue'/>
				<input type='hidden' id='deptRecvId'/>
				<input type='hidden' id='deptRecvName'/>
				<input type='hidden' id='deptLeaderId'/>
				<input type='hidden' id='deptLeaderName'/>
				<input type='button' class="btn" value='确定' onclick='doReturn()'/>
				<input type='button' class="btn" value='关闭' onclick='javascript:window.close()'/>
		</td></tr>
	</table>
	<div style="width:25%;float:left;"><b>线路：</b><input type="checkbox" name="checkboxall" onclick="doCheckAll(this,'xl')"/><span>全选</span><table>			
		<%
		OpDictionary deptInfo;
			for (Iterator iter = xlDepts.iterator(); iter.hasNext();) {
				deptInfo = (OpDictionary) iter.next();
		%>
			<tr>
				<td>
					<input type="checkbox" name="checkboxes" value="<%=deptInfo.getId()%>" lable="<%=deptInfo.getName()%>" class="xl"
							onclick="doCheck(this)"/>
					<span><%=deptInfo.getName()%></span>
				</td>
			</tr>
		<% 
			}
		%>
	</table></div>
	<div style="width:25%;float:left;"><b>OCC：</b><input type="checkbox" name="checkboxall" onclick="doCheckAll(this,'OCC')"/><span>全选</span><table>			
		<%
		OpDictionary deptInfo2;
			for (Iterator iter = occDepts.iterator(); iter.hasNext();) {
				deptInfo2 = (OpDictionary) iter.next();
		%>
			<tr>
				<td>
					<input type="checkbox" name="checkboxes" value="<%=deptInfo2.getId()%>" lable="<%=deptInfo2.getName()%>" class="OCC"
							onclick="doCheck(this)"/>
					<span><%=deptInfo2.getName()%></span>
				</td>
			</tr>
		<% 
			}
		%>
	</table></div>
	<div style="width:25%;float:left;"><b>专业公司：</b><input type="checkbox" name="checkboxall" onclick="doCheckAll(this,'zy')"/><span>全选</span><table>			
		<%
		OpDictionary deptInfo3;
			for (Iterator iter = zyDepts.iterator(); iter.hasNext();) {
				deptInfo3 = (OpDictionary) iter.next();
		%>
			<tr>
				<td>
					<input type="checkbox" name="checkboxes" value="<%=deptInfo3.getId()%>" lable="<%=deptInfo3.getName()%>" class="zy"
							onclick="doCheck(this)"/>
					<span><%=deptInfo3.getName()%></span>
				</td>
			</tr>
		<% 
			}
		%>
	</table></div>
	</div>
</BODY>
	<script language="javascript">
	
	//选择框初始化，将上次选择结果显示出来
	function init(deptId,deptName) {
		if (deptName == null || deptName.value == "") {
			return false;
		}
		
		var deptNameArr = deptName.value.split(",");
			
		var checkboxes = document.getElementsByName("checkboxes");
		if(checkboxes) {
			for(var i=0; i<deptNameArr.length; i++) {
				for(var j=0; j<checkboxes.length; j++) {
					if(deptNameArr[i] == $(checkboxes[j]).attr('lable')) {
						checkboxes[j].checked = "checked";		
					}
				}
			}
			if(deptName != null) {
				document.getElementById("returnLable").value = deptName.value;
			}
			if(deptId != null) {
				document.getElementById("returnValue").value = deptId.value;
			}
		}
	}	
	
	//响应多选按钮

	function doCheck(node){
		var checkboxes = document.getElementsByName("checkboxes");
		var returnLables = "";
		var returnValues = "";
		
		if(checkboxes) {
			for(var i=0; i<checkboxes.length; i++) {
				if(checkboxes[i].checked&&returnValues.indexOf(checkboxes[i].value)==-1) {
					returnLables += $(checkboxes[i]).attr('lable') + ",";
					returnValues += checkboxes[i].value + ",";
				}
			}		
		} 
		document.getElementById("returnLable").value = returnLables.substr(0, returnLables.length-1);
  		document.getElementById("returnValue").value = returnValues.substr(0, returnValues.length-1);	  		
  	}	
	
	function doCheckAll(node,classname){
		$('.'+classname).each(function(){
			this.checked = node.checked;
		})
		
		doCheck(node);
  	}	
	
	//响应确定按钮，返回选择结果
	function doReturn() {
		// 取得存放部门相关信息的控件

		var checkboxes = document.getElementsByName("checkboxes");
		var deptId = document.getElementById('returnValue').value;
		var deptName = document.getElementById('returnLable').value;
		if (deptId.value == "") {
			alert("请您选择部门!");
			return false;
		}

		// 将部门相关信息添加到父页面中控件
		if(window.dialogArguments.deptIdField != null) {
			window.dialogArguments.deptIdField.value = deptId;
		}
		if(window.dialogArguments.deptNameField != null) {
			window.dialogArguments.deptNameField.value = deptName;
		}
		
		window.returnValue="1";				
		window.close();
	}
	
	//对ajax返回值进行处理

	function dataExtractor(jsonContent,node) {
		if( !(typeof(jsonContent.deptRecvLoginName) == "undefined")) {	
			node.deptRecvId = jsonContent.deptRecvLoginName;
		}
		
		if( !(typeof(jsonContent.deptRecvName) == "undefined")) {
			node.deptRecvName = jsonContent.deptRecvName;
		}
		
		if( !(typeof(jsonContent.deptLeaderLoginName) == "undefined")) {
			node.deptLeaderId = jsonContent.deptLeaderLoginName;
		}
		
		if( !(typeof(jsonContent.deptLeaderName) == "undefined")) {
			node.deptLeaderName = jsonContent.deptLeaderName;
		}	
	}
	</script>
</HTML>