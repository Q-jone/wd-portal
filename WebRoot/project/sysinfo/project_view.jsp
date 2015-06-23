<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目基本信息查看</title>
<link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../../js/html5.js"></script>
        <script src="../../js/jquery-1.7.1.js"></script>
        <script src="js/jquery.form.js"></script>
		<script src="../../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../../js/show.js"></script>
		<script src="../../js/loading.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		
		<script type="text/javascript">
		$(function(){
			$(".odd tr:odd").css("background","#fafafa");			
			loadShow();		
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
			showLoading();
		}
		
        </script>
       </head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">项目基本信息查看</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form id="add">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">项目名称：</td>
                                <td>
                                <!--  <input type="text" id="projectName" name="projectName" class="input_xxlarge"/>
                                -->
                                <s:property value="project.projectName"/>
                                </td>
                                <td class="t_r lableTd">责任单位：</td>
                                <td>
                                <!-- <input type="text" id="department" name="department" class="input_xxlarge"/>
                                 --><s:property value="project.department"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">分管领导：</td>
                                <td>
                                <!-- <input type="text" id="leader" name="leader" class="input_xxlarge"/>
                                 --><s:property value="project.leader"/>
                                </td>
                                <td class="t_r lableTd">责任人：</td>
                                <td>
                                <!-- <input type="text" id="responsible" name="responsible" class="input_xxlarge"/>
                                 --><s:property value="project.responsible"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">项目类别：</td>
                                <td>
                                <!-- <input type="text" id="projectType" name="projectType" class="input_xxlarge"/>
                                 --><s:property value="project.projectType"/>
                                 </td>
                                <td class="t_r lableTd">项目编号：</td>
                                <td>
                                <!-- <input type="text" id="projectNumber" name="projectNumber" class="input_xxlarge"/>
                                --><s:property value="project.projectNumber"/>
                                </td>
                                
                                <tr>
                                <td class="t_r lableTd">等保定级：</td>
                                <td >
                                <!-- <input type="text" id="level" name="level" class="input_xxlarge"/>
                                 --><s:property value="project.level"/>
                                </td>
                                <td class="t_r lableTd">计划建成时间：</td>
                                <td>
                                <!-- <input type="text" id="planActivateTime" name="planActivateTime" class="input_xxlarge"/>
                                 <s:date name='project.planActivateTime' format='yyyy'/><s:property value="project.planActivateTime"/>-->
                                 <s:date name='project.planActivateTime' format='yyyy-MM-dd'/>
                                </td>
                                </tr>
                                <tr>
                                
                                </tr>
                                <tr>
                                <td class="t_r lableTd">总投资估算：</td>
                                <td colspan="10">
                                <!-- <input type="text" id="investEstimate" name="investEstimate" class="input_xxlarge"/>
                                 --><s:property value="project.investEstimate"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">项目描述：</td>
                                <td colspan="3">
                                <textarea cols="10" readonly="readonly" rows="5" id="projectDiscription" name="projectDiscription"><s:property value="project.projectDiscription"/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">项目目标：</td>
                                <td colspan="3">
                                <textarea cols="10" readonly="readonly" rows="5" id="projectGoal" name="projectGoal"><s:property value="project.projectGoal"/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">年度推进目标：</td>
                                <td colspan="3">
                                <textarea cols="10" readonly="readonly" rows="5" id="projectForwardGoals" name="projectForwardGoals"><s:property value="project.projectForwardGoals"/></textarea>
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">项目状态：</td>
                                <td colspan="3">
                                <textarea cols="10" readonly="readonly" rows="5" id="projectStatus" name="projectStatus"><s:property value="project.projectStatus"/></textarea>
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">说明：</td>
                                <td colspan="3">
                                <textarea cols="10"  readonly="readonly" rows="5" id="memo" name="memo"><s:property value="project.memo"/></textarea>
                                </td>
                                </tr>
                               
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r" style="text-align: center;">&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                &nbsp;</td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
