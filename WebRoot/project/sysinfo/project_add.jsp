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
<title>项目基本信息
<s:if test="project.projectId!=null&&project.projectId!=''">
编辑
</s:if>
<s:else>
录入</s:else>
</title>
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
        <script src="<%=basePath %>js/html5.js"></script>
        <script src="<%=basePath %>js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>project/sysinfo/js/jquery.form.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=basePath %>js/show.js"></script>
		<script src="<%=basePath %>js/loading.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript" src="js/project_add.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		
		<script type="text/javascript">
		var addOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/project/sysinfo/save.action',
			    success:function(data){
			    	
						if(data !=null && data.length > 0){
							alert(data);
							if(data=="添加成功！！！"){
								window.location.href = "/portal/project/sysinfo/project_add.jsp";
							}else if(data=="修改成功！！！"){
								//window.location.href = "/portal/project/sysinfo/list.action";
							}
						}else{
							alert("添加失败！");
							window.location.href = "/portal/project/sysinfo/project_add.jsp";
						}
					return false;
			    }
			};
		
		
         $(function(){
        	 $("#submit").click(function(){
        		 if(checkout())
        		 	$("#add").ajaxSubmit(addOptions);
        	 });
      
        	 
			$(".odd tr:odd").css("background","#fafafa");	
			$('#pfTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,
				"closeText":'清除',
				"currentText":'pfTime'//仅作为“清除”按钮的判断条件
			});
			$('#bTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				"currentText":'bTime'//仅作为“清除”按钮的判断条件						
			});
			
			$('#planActivateTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',
				
				"currentText":'planActivateTime'//仅作为“清除”按钮的判断条件						
			});
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="pfTime") $("#pfTime").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="bTime") $("#bTime").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="planActivateTime") $("#planActivateTime").val("");                    
            });
			
			loadShow();
			
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
			
			//showLoading();
			
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
            		<li class="fin">项目基本信息
							<s:if test="project.projectId!=null&&project.projectId!=''">
							编辑
							</s:if>
							<s:else>
							录入</s:else></li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <form id="add">
        <input  type="hidden" id="projectId" name="projectId" value="<s:property value='project.projectId'/>"/>
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">项目名称：</td>
                                <td>
                                <input type="text" id="projectName" name="project.projectName" class="input_xxlarge" value="<s:property value='project.projectName'/>"/>
                                </td>
                                <td class="t_r lableTd">责任单位：</td>
                                <td>
                                <input type="text" id="department" name="project.department" class="input_xxlarge" value="<s:property value='project.department'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">分管领导：</td>
                                <td>
                                <input type="text" id="leader" name="project.leader" class="input_xxlarge" value="<s:property value='project.leader'/>"/>
                                </td>
                                <td class="t_r lableTd">责任人：</td>
                                <td>
                                <input type="text" id="responsible" name="project.responsible" class="input_xxlarge" value="<s:property value='project.responsible'/>"/>
                                </td>
                                </tr><tr>
                                <td class="t_r lableTd">项目类别：</td>
                                <td>
                                <input type="text" id="projectType" name="project.projectType" class="input_xxlarge" value="<s:property value='project.projectType'/>"/>
                                </td>
                                <td class="t_r lableTd">项目编号：</td>
                                <td>
                                <input type="text" id="projectNumber" name="project.projectNumber" class="input_xxlarge" value="<s:property value='project.projectNumber'/>"/>
                                </td>
                                
                                <tr>
                                <td class="t_r lableTd">等保定级：</td>
                                <td>
                                <input type="text" id="level" name="project.level" class="input_xxlarge" value="<s:property value='project.level'/>"/>
                                </td>
                                <td class="t_r lableTd">计划建成时间：</td>
                                <td>
                                
                               <!-- <select id="planActivateTime" name="project.planActivateTime">
                                	<input type="hidden" id="time" name="time" value="<s:date name='project.planActivateTime' format='yyyy-MM-dd'/>" >
                                	<option value="2014">2014</option>
                                	<option value="2015">2015</option>
                                	<option value="2016">2016</option>
                                	<option value="2017">2017</option>
                                	<option value="">--请选择计划时间--</option>
                                </select>-->
                                <!--  -->
                                <input readonly="readonly" type="text" id="planActivateTime" name="project.planActivateTime" class="input_xxlarge" value="<s:date name='project.planActivateTime' format='yyyy-MM-dd'/>"/> 
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">总投资估算：</td>
                                <td colspan="10">
                                <input type="text" id="investEstimate" name="project.investEstimate" class="input_xxlarge" value="<s:property value='project.investEstimate'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">项目描述：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="projectDiscription" name="project.projectDiscription" value=""><s:property value='project.projectDiscription'/></textarea>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">项目目标：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="projectGoal" name="project.projectGoal" value=""><s:property value='project.projectGoal'/></textarea>
                                </td>
                                </tr>
                                <!-- <tr>
                                <td class="t_r lableTd">年度推进目标：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="projectForwardGoals" name="project.projectForwardGoals" value=""><s:property value='project.projectForwardGoals'/></textarea>
                                </td>
                                </tr> -->
                                
                                <tr>
                                <td class="t_r lableTd">项目状态：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="projectStatus" name="project.projectStatus" value=""><s:property value='project.projectStatus'/></textarea>
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">说明：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="memo" name="project.memo" value=""><s:property value='project.memo'/></textarea>
                                </td>
                                </tr>
                               
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="submit" type="button"  value="添加"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                             
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
