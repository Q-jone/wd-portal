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
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		
<script src="<%=path%>/js/jquery-1.7.1.js"></script>
		<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
        <script src="<%=path%>/js/jquery.form.js"></script>
		<script src="<%=path%>/js/jquery.formalize.js"></script>
<script type="text/javascript">
      $(document).ready(function () {
     	 
      });
      
      function selEmp(){
    	  window.empIdField = document.getElementById("empIds");
          window.empNameField = document.getElementById("empNames");
    	    window.showModalDialog('/portal/zdocsCatalog/selEmp.action?deptId=${catalog.deptId}',window,'dialogWidth:250px;dialogHeight:400px;scroll:auto');
    	}
      
  	function clearEmp(){
  		document.getElementById("empIds").value = '';
        document.getElementById("empNames").value = '';
  	}            
      
  	var handleOptions = {
			cache:false,
			type:'post',
			callback:null,
			url:'/portal/zdocsCatalog/saveDir.action',
		    success:function(data){
				if(data=="1"){
					alert("修改成功");
					parent.reloadDirTree();
				}
				return false;
		    },error: function() { alert("服务器连接失败，请稍后再试!"); }	
		};
      
    function doSubmit(){
  		var checkResult = checkForm();
		if(checkResult){
			alert(checkResult);
			return false;
		}        	  
    	$('#handleForm').ajaxSubmit(handleOptions);
      }
      
      function checkForm(){
    	  if(!$('#catalogName').val()){
    		  $('#catalogName').focus();
    		  return '请输入目录名称！';
    	  }    	  
      }          
</script>


</head>

<body>
	<s:form theme="simple" action="saveDir" id="handleForm" method="post" namespace="/zdocsCatalog">
	<input type="hidden" name="id" value="${catalog.sid}"/>
	<input type="hidden" name="pid" value="${catalog.parentSid}"/>
	<input type="hidden" name="empIds" id="empIds" value="${empIds}"/>
	<table class="toolTbl">
	<tr>
		<td><font class="redMark">*&nbsp;</font>目录名称：</td>
		<td><input type="text" name="catalogName" id="catalogName" value="${catalog.catalogName}"/></td>
	</tr>
	<s:if test="#request.catalog.flag==100">
	<tr>
		<td>可浏览此目录的人员：</td>
		<td><input type="text" name="empNames" id="empNames" value="${empNames}" readonly="readonly"/>
			&nbsp;<input class="btn" type="button" value="请选择" onclick = "selEmp()"></input>
			&nbsp;<input class="btn" type="button" value="清空" onclick = "clearEmp()"></input>
		</td>
	</tr>	
	</s:if>
	</table>
	<div style="text-align:center;margin:10px;">	
	<input class="btn" type="button" value="提交" onclick="doSubmit()"/>
	</div>
	</s:form>
</body>
</html>