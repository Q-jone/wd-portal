<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();

%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>前期办证信息</title>
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
        <script src="<%=basePath %>/beforeBuild/js/comm.js"></script>
        <link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
		<script type="text/javascript">
	
		
         $(function(){ 
			$(".odd tr:odd").css("background","#fafafa");	

			$("#statusTd").html($("#statusTd").html().replace("1","待办").replace("2","在办").replace("3","延误").replace("4","办结").replace("5","失效").replace("6","过期"));
		});
		
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin" id="titleName">前期办证信息查看</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                                 <td style="width:20%;" class="t_r lableTd">项目名称：</td>
                                <td style="width:30%;">
                                <s:property value='#request.info[0]'/>
                                </td>
                                <td class="t_r lableTd" style="width:20%;">单体名称：</td>
                                <td style="width:30%;">
                               <s:property value='#request.info[2]'/>
							   <tr>
                                <td class="t_r lableTd">证件名称：</td>
                                <td>
                                <s:property value='#request.info[4]'/>
                                </td>
                                <td class="t_r lableTd">当前状态：</td>
                                <td id="statusTd">
                                <s:property value='#request.info[6]'/>
                                </td>
                                </tr>
								  <tr>
                                <td class="t_r lableTd">计划开始时间：</td>
                                <td>
                                <s:property value='#request.info[11]'/>
                                </td>
                                <td class="t_r lableTd">计划结束时间：</td>
                                <td>
                               <s:property value='#request.info[12]'/>
                                </td>
                                </tr>
                                  <tr>
                                <td class="t_r lableTd">实际开始时间：</td>
                                <td>
                                <s:property value='#request.info[13]'/>
                                </td>
                                <td class="t_r lableTd">实际结束时间：</td>
                                <td>
                                <s:property value='#request.info[14]'/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">经办人：</td>
                                <td colspan="3">
                                <s:property value='#request.info[9]'/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">标的物：</td>
                                <td colspan="3">
                               	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value='#request.info[15]'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=view&targetType=frame"></iframe>
                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">&nbsp;</td>
                              </tr>
                             
                              
                            </table>
      </div>
        <!--Table End-->
</div>
</body>
</html>
