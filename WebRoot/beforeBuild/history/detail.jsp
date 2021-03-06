﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String id = request.getParameter("id");
if(id==null){
	id = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>历史数据填报</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>/js/jquery.formalize.js"></script>
        <script src="<%=basePath %>/beforeBuild/js/jquery.form.js"></script>
        <link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
		
		<script type="text/javascript">
		var projectNameValue = "";
		var projectIdValue = "";
		var routeNameValue = "";
		var routeIdValue = "";
		var typeNameValue = "";
		var typeIdValue = "";
		var monomerNameValue = "";
		var monomerIdValue = "";
		
         $(function(){ 
			$(".odd tr:odd").css("background","#fafafa");	

			$('#realFinishTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realFinishTime'//仅作为“清除”按钮的判断条件						
			});
	 		
	       //datepicker的“清除”功能
	         $(".ui-datepicker-close").live("click", function (){              
	           if($(this).parent("div").children("button:eq(0)").text()=="realFinishTime") $("#realFinishTime").val("");
	                           
	         });
			
		});

 		function shut(){
 			  window.opener=null;
 			  window.open("","_self");
 			  window.close();
 			}
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">历史数据详情</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/history/save.action" id="form" name="form" method="post">
        <input type="hidden" name="id" value="<%=id %>">
        <input type="hidden" name="createTime">
        <input type="hidden" name="createUser">
        <input type="hidden" name="removed" value="0">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">项目名称：</td>
                                <td style="width:30%;">
                                <s:property value="#request.history.projectName" />
                                </td>
                                <td style="width:20%;" class="t_r lableTd">所属线路：</td>
                                <td style="width:30%;">
                                <s:property value="#request.history.routeName" />
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">单体：</td>
                                <td>
                                	<!-- 行政区划：<select name="areaName" onchange="selectArea(this);"></select>&nbsp;&nbsp;<input type="text" name="areaId"> -->
                                	单体类型：<s:property value="#request.history.typeName" />&nbsp;&nbsp;
                                	单体名称：<s:property value="#request.history.monomerName" />
                                	<input type="hidden" name="typeId">
                                	<input type="hidden" name="monomerId">
                                </td>
                                <td class="t_r lableTd">证件：</td>
                                <td>
									<s:property value="#request.history.paper" />
                                </td>                                
                                <tr>
                                <td class="t_r lableTd">实际开始时间：</td>
                                <td>
									<s:property value="#request.history.realStartTime" />
                                </td>
                                <td class="t_r lableTd">实际完成时间：</td>
                                <td>
                                	<s:property value="#request.history.realFinishTime" />
                                </td>
                                </tr>
                                <tr>
	                                <td class="t_r lableTd">成果：</td>
	                                <td colspan="3">
		                                <input type="hidden" name="result" id="attach" value=""/>
		                                <s:if test="#request.history.source==2">
		        						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadOldFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="#request.history.result" />&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame"></iframe>
		        						</s:if>
		        						<s:else>
		        						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value="#request.history.result" />&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame"></iframe>
		        						</s:else>
	                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
