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
<meta http-equiv="x-ua-compatible" content="IE=9">
<title>工会立功竞赛</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=path%>/union/css/tree/style.css" type="text/css">

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/union/js/tree/jquery.tree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/union/js/tree/jquery.tree.excheck.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/js/jquery.form.js"></script>
<script src="<%=path%>/js/jquery.formalize.js"></script>
<script src="<%=path%>/union/js/custom_widgets.js"></script>

	<script type="text/javascript">
        $(document).ready(function () {
        	$.initContextPath("<%=path%>");
        	
        	$(".datepicker").datepicker();
            $('#deptId').val('${accountApply.deptId}');
        });
        
      	var handleOptions = {
    			cache:false,
    			type:'post',
    			callback:null,
    			beforeSubmit:function(){$('#saveBtn').attr('disabled','true');},
    			url:'<%=path%>/unionAccountApply/save.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("保存成功");
						shut();
    				}
    				$('#saveBtn').removeAttr('disabled');
    				return false;
    		    },error: function() { alert("服务器连接失败，请稍后再试!"); $('#saveBtn').removeAttr('disabled');}	
    		};
          
        function doSubmit(){
      		var checkResult = checkForm();
    		if(checkResult){
    			alert(checkResult);
    			return false;
    		}        	  
    		$('input[name=deptName]').val($("#deptId option:selected").text());
        	if(confirm('确认要提交吗？')){$('#form').ajaxSubmit(handleOptions);}
          }
          
          function checkForm(){
				if($('input[name=name]').val()==''){
					$('input[name=name]').focus();
					return '请输入姓名！';
				}
				
				if($('input[name=loginName]').val()==''){
					$('input[name=loginName"]').focus();
					return '请输入工号！';
				}				
				
				if($('#deptId').val()==''){
					$('#deptId').focus();
					return '请选择部门！';
				}								
							
				if($("[name=remark]").val().length>500){
					$("[name=remark]").focus();
					return("备注说明只限输入500字！");
				}						
          }  
          
  		function shut(){
  		  window.opener=null;
  		  window.open("","_self");
  		  window.close();
  		}          
    </script>

	<style>
	span{display: inline;}
	</style>

</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=path%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">集体奖项申报资料</li>
            </ul>
        </div>
        <div style="display:none;" class="fr lit_nav nwarp">
            <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="express" href="#">导出数据</a></li>
                <li class="selected"><a class="table" href="#">表格模式</a></li>
                <li><a class="treeOpen" href="#">打开树</a></li>
                <li><a class="filterClose" href="#">关闭过滤</a></li>
            </ul>
        </div>
    </div>
    <!--Ctrl End-->
    <!--Filter--><!--Filter End-->
    <!--Table-->

    <div class="mb10 pt45">
        <form id="form" method="post">
            <input type="hidden" name="id" value="${accountApply.id}"/>
            <input type="hidden" name="cUser" value="${accountApply.cUser}"/>
            <input type="hidden" name="cTime" value="${accountApply.cTime}"/>           
            <input type="hidden" name="deptName" value="${accountApply.deptName}"/>           
        	<s:if test="#request.accountApply!=null">
        		<input type="hidden" name="status" value="${accountApply.status}"/>
        	</s:if>              
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd" width="20%">姓名：</td>
                    <td width="30%">
                        <input type="text" class="input_xxlarge" maxlength="40" name="name" value="${accountApply.name}">
                    </td>
                    <td class="t_r lableTd" width="20%">工号：</td>
                    <td width="30%">
                        <input type="text" class="input_xxlarge" maxlength="40" name="loginName" value="${accountApply.loginName}"/>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">所属部门：</td>
                    <td colspan="3">
						<select name="deptId" id="deptId">
							<option value="">---------请选择---------</option>
							<s:iterator value="#request.depts" id="dept">
									<option value="<s:property value="id"/>"><s:property value="name"/></option>
							</s:iterator>
						</select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">备注说明：</td>
                    <td colspan="3">
                        <textarea  rows="5" name="remark">${accountApply.remark}</textarea>
                    </td>
                </tr>
                
                </tbody>
                <tr class="tfoot">
                    <td colspan="4" class="t_r"><input type="button" id="saveBtn" onclick="doSubmit();" value="保 存"/>&nbsp;
                        <input type="reset" value="重 置" />&nbsp;</td>
                </tr>

            </table>
        </form>
    </div>
    <!--Table End-->
</div>
</body>
</html>