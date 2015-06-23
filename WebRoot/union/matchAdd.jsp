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
<script src="<%=path%>/union/js/messagebox.js"></script>

	<script type="text/javascript">
        $(document).ready(function () {
        	$.initContextPath("<%=path%>");
        	
        	initMessagebox();
        	initMessageboxClose();
        	
        	$('[name=matchType]').val('${match.matchType}');
        	$(".datepicker").datepicker({
        		inline: true,  
                changeYear: true,  
                changeMonth: true,
                yearRange: '1900:+1'
        	});
        	
            $("#departmentTree").wrapDepartEmployeeTree();
            $("#departmentTree").bind("initCompleted",function(event, nodes){
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].id.toString() == $(":hidden[name=deptId]").val()) {
                        $.fn.zTree.getZTreeObj("departmentTree").selectNode(nodes[i], true);
                    }
                    if (nodes[i].id.toString() == '2500') {
                        $.fn.zTree.getZTreeObj("departmentTree").expandNode(nodes[i], true);
                    }                    
                }
            });
            
            $("#selDepartment").click(function(){
                $("#departmentDialog").dialog({
                    autoOpen: true,
                    width: 376,
                    height: 400,
                    modal: true,
                    resizable: false,
                    buttons:{
                        "关闭": function() {
                            $( this ).dialog( "close" );
                        },
                        "确定": function() {
                            var nodes = $.fn.zTree.getZTreeObj("departmentTree").getSelectedNodes();
                            if(nodes && nodes.length > 0){
                            	if(nodes[0].nodeType!='e'){
                            		alert('请选择部门下人员！');
                            		return false;
                            	}else{
                                    $(":text[name=deptName]").val(nodes[0].getParentNode().name);
                                    $(":hidden[name=deptId]").val(nodes[0].getParentNode().id);           
                                    $(":text[name=operator]").val(nodes[0].name);
                                    $(":hidden[name=operatorId]").val(nodes[0].loginName);                                             
                                    $( this ).dialog( "close" );
                            	}                            	
                            }
                        }
                    }
                });
            });            
        });
          
          function checkForm(){
				if($('[name=matchName]').val()==''){
					$('[name=matchName]').focus();
					return '请输入专项主题名！';
				}
				if(!$('[name=matchType]').val()){
					$('[name=matchType]').focus();
					return '请选择类别！';
				}				
				if($('[name=beginDate]').val()==''){
					$('[name=beginDate]').focus();
					return '请选择开始日期！';
				}		
				if($('[name=endDate]').val()==''){
					$('[name=endDate]').focus();
					return '请选择结束日期！';
				}
				if($('[name=beginDate]').val() > $('[name=endDate]').val()){
					$('[name=beginDate]').focus();
					return '开始日期不能晚于结束日期！';					
				}
				if($('[name=deptName]').val()==''){
					$('[name=deptName]').focus();
					return '请选择考评部门！';
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
	.redMark{color:red;display:inline;}
	</style>

</head>

<body>
<div class="main">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="<%=path%>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
        <div class="posi fl">
            <ul>
                <li class="fin">竞赛专项表单</li>
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
        <form action="" id="form" method="post">
        	<input type="hidden" name="id" value="${match.id}"/>
        	<s:if test="#request.match==null">
        		<input type="hidden" name="themeId" value="${theme.id}"/>
        	</s:if>
        	<s:else>
        		<input type="hidden" name="themeId" value="${match.themeId}"/>
        	</s:else>        	
            <input type="hidden" name="deptId" value="${match.deptId}"/>
        	<input type="hidden" name="operatorId" value="${match.operatorId}"/>
        	<input type="hidden" name="status" value="0"/>
        	<input type="hidden" name="cUser" value="${match.cUser}"/>
        	<input type="hidden" name="cTime" value="${match.cTime}"/>        	
        	<input type="hidden" name="removed" value="0"/>      
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>专项主题名：</td>
                    <td>
                        <input class="input_xxlarge" type="text" name="matchName" maxlength="200" value="${match.matchName}">
                    </td>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>类别：</td>
                    <td>
                        <select name="matchType">
                            <option value="成果">成果</option>
                            <option value="普通">普通</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>开始日期：</td>
                    <td>
                        <input class="input_xxlarge datepicker" type="text" readonly name="beginDate"
                               value="${match.beginDate}">
                    </td>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>结束日期：</td>
                    <td>
                        <input class="input_xxlarge datepicker" type="text" readonly name="endDate"
                               value="${match.endDate}">
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd"><font class="redMark">*&nbsp;</font>考评部门及经办人：</td>
                    <td colspan="3">
                        <input readonly style="border:0px;" size="15" type="text" name="deptName" value="${match.deptName}"> 
                        <input readonly style="border:0px;" size="5" type="text" name="operator" value="${match.operator}">
                        <a id="selDepartment" style="display:inline;cursor:pointer;color:#0000ff;text-decoration:underline;">选择考评部门经办人</a>
                    </td>
                </tr>
                
                <tr>
                    <td class="t_r lableTd">附件：</td>
                    <td colspan="3">
						<input type="hidden" name="attach" id='attach' value="${match.attach}"/>
						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=${match.attach}&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
                    </td>
                </tr>   
                <tr class="tfoot nprint">
                    <td colspan="4" style="text-align:center">
                        <input type="button" id="todo_handle" value="提 交" id="addButton">&nbsp;
                        <input type="button" onclick="shut();" value="关 闭"/>&nbsp;
                    </td>
                </tr>                                  
				</tbody>	
                </table>
    	<div class="transparent" id="maskDiv" style="display: none;z-index:99;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
		<jsp:include page="flow/matchAdd.jsp"/>
    <!--Table End-->
    </form>                
    </div>
</div>
<div style="display:none;" id="departmentDialog" title="选择考评部门">
    <ul id="departmentTree" class="ztree"></ul>
</div>
</body>
</html>