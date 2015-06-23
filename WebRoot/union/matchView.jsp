<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.union.entity.bo.UnionMatch"%>
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
        	initMessagebox();
        	initMessageboxClose();
        });
        
      	var handleOptions = {
    			cache:false,
    			type:'post',
    			callback:null,
    			url:'<%=path%>/unionMatch/flowDeal.action',
    		    success:function(data){
    				if(data=="1"){
    					alert("操作成功");
						shut();
    				}
    				return false;
    		    },error: function() { alert("服务器连接失败，请稍后再试!"); }	
    		};
          
          function checkForm(){
			
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
            <input type="hidden" name="status" value="${match.status}"/>
            <input type="hidden" name="checkRole" value="${checkRole}"/>
            <table width="100%"  class="table_1">
                <thead>
                <th colspan="4" class="t_r">
                    &nbsp;</th>
                </thead>
                <tbody>
                <tr>
                    <td class="t_r lableTd">专项主题名：</td>
                    <td>
                        ${match.matchName}
                    </td>
                    <td class="t_r lableTd">类别：</td>
                    <td>
						${match.matchName}
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">开始日期：</td>
                    <td>
						${match.beginDate}
                    </td>
                    <td class="t_r lableTd">结束日期：</td>
                    <td>
						${match.endDate}
                    </td>
                </tr>

                <tr>
                    <td class="t_r lableTd">考评部门及经办人：</td>
                    <td colspan="3">
                        ${match.deptName}&nbsp;&nbsp;${match.operator}
                    </td>
                </tr>
                
                <tr>
                    <td class="t_r lableTd">附件：</td>
                    <td colspan="3">
						<input type="hidden" name="attach" id='attach'/>
						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=${match.attach}&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame&type=1"></iframe>
                    </td>
                </tr>       
                <tr class="tfoot nprint">
                    <td colspan="4" style="text-align:center">
                    <s:if test="#request.match.handlerId.substring(0,12)==#session.t_login_name && #request.match.status==@com.wonders.stpt.union.entity.bo.UnionMatch@MATCH_REVIEW_STATUS">
                        <input type="button" id="todo_handle" value="审 批" id="addButton">&nbsp;
                    </s:if>
                        <input type="button" onclick="shut();" value="关 闭"/>&nbsp;
                    </td>
                </tr>                               
				</tbody>		
                </table>
    	<div class="transparent" id="maskDiv" style="display: none;z-index:99;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
		<s:if test="#request.match.status==@com.wonders.stpt.union.entity.bo.UnionMatch@MATCH_REVIEW_STATUS">
			<jsp:include page="flow/matchAdd_deptReview.jsp"/>
		</s:if>
	</form>
        
    </div>
    <div>
<s:if test="#request.match.status==@com.wonders.stpt.union.entity.bo.UnionMatch@MATCH_REVIEW_STATUS">
        <table width="100%"  class="table_1">
            <thead>
            <th colspan="4" style="text-align:center;"><strong>审核信息</strong></th>
            </thead>
            <tbody>
		  	<tr class="content6">
				<td class="pl18" colspan="4">
					<table width="100%" border="0">
					<tbody>
					<s:iterator value="#request.approvalInfos" id="data" status="s">
						<tr>
						<td style="border-bottom: 0px;">
								<div align="left">
								<s:property value="#data.remark"/><br>
								<div>
								<span style="float:right">
								<s:property value="#data.nodeName"/>&nbsp;&nbsp;<s:property value="#data.checkUser"/>&nbsp;<s:property value="#data.checkTime"/>
								</span>
								</div>
								</div>
						</td>
						</tr>
					</s:iterator>
					</tbody>
					</table>
				</td>
		  	</tr>
            </tbody>
        </table>
	</s:if>		  
    </div>            
    <!--Table End-->
</div>
</body>
</html>