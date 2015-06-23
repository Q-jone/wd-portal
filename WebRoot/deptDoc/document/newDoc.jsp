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
      
  	var handleOptions = {
			cache:false,
			type:'post',
			callback:null,
			url:'/portal/zdocsFile/uploadDocs.action',
		    success:function(data){
				if(data=="1"){
					alert("添加成功");
					parent.refresh();
				}else{
					alert("服务器连接失败，请稍后再试!"); 
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
    	  if(!$('#fileName').val()){
              $('#fileName').focus();
              return '请输入文件标题！';
          }   
    	  if(!$('#keyword').val()){
    		  $('#keyword').focus();
    		  return '请输入主题词！';
    	  }    	  
    	  if($('#attachCnt').val()=='0'){
    		  return '请上传文件！';
    	  }    	  
      }
</script>


</head>

<body>
	<s:form theme="simple" action="uploadDocs" id="handleForm" method="post" namespace="/zdocsFile">
		<input type="hidden" name="catalogId" id="catalogId" value="${param.catalogId}"/>
		<input type="hidden" name="flag" id="flag" value="${param.flag}"/>
		<input type="hidden" name="attachGroup" id="attachGroup" value=""/>
		<input type="hidden" id="attachCnt" value="0"/>	
		<table class="toolTbl">
	    <tr>
	        <td><font class="redMark">*&nbsp;</font>文件标题：</td>
	        <td><input class="input_xlarge" type="text" name="fileName" id="fileName" maxlength="500"/></td>
	    </tr>
	    <tr>
            <td><font class="redMark">*&nbsp;</font>主题词：</td>
            <td><input class="input_xlarge" type="text" name="keyword" id="keyword" maxlength="500"/></td>
        </tr>
	    </table>
		<br>
		<iframe scrolling="auto" height="200" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attachOld/loadFileOldList.action?fileGroup=attachGroup&fileGroupName=attachGroupDoc&fileGroupId=&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1&fileCntObjId=attachCnt"></iframe>
		<div style="text-align:center;">	
		<input class="btn" type="button" value="提交" onclick="doSubmit()"/>
		</div>
	</s:form>
</body>
</html>