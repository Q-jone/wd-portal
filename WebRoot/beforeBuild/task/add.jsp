<%@ page language="java" contentType="text/html; charset=UTF-8"
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
String type = request.getParameter("type");
if(type==null){
	type = "";
}
String planStartTime = request.getParameter("planStartTime");
if(planStartTime==null){
	planStartTime = "";
}
String planFinishTime = request.getParameter("planFinishTime");
if(planFinishTime==null){
	planFinishTime = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>任务填报</title>
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

			$('#planStartTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'planStartTime'//仅作为“清除”按钮的判断条件						
			});
			
			$('#planFinishTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'planFinishTime'//仅作为“清除”按钮的判断条件						
			});

			$('#realStartTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realStartTime'//仅作为“清除”按钮的判断条件						
			});

			$('#forecastFinishTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'forecastFinishTime'//仅作为“清除”按钮的判断条件						
			});

			$('#realFinishTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'realFinishTime'//仅作为“清除”按钮的判断条件						
			});

			$('#warnTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'warnTime'//仅作为“清除”按钮的判断条件						
			});


			$('#invalidStartTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'invalidStartTime'//仅作为“清除”按钮的判断条件						
			});

			$('#invalidFinishTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'invalidFinishTime'//仅作为“清除”按钮的判断条件						
			});
	 		
	       //datepicker的“清除”功能
	         $(".ui-datepicker-close").live("click", function (){              
	           if($(this).parent("div").children("button:eq(0)").text()=="planStartTime") $("#planStartTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="planFinishTime") $("#planFinishTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="realStartTime") $("#realStartTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="forecastFinishTime") $("#forecastFinishTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="realFinishTime") $("#realFinishTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="warnTime") $("#warnTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="updateTime") $("#updateTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="invalidStartTime") $("#invalidStartTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="invalidFinishTime") $("#invalidFinishTime").val("");
	         });

	         //findMonomerPlans();

	         if("<%=id%>"!=""){
					if($("[name=status]").val()=='5'){//失效
						$("#valid_status").val('0');
						//$("#invalidTr").show();
					}
					$("#addButton").val("保  存");
					$("#titleName").html($("#titleName").html().replace("新增","修改"));
				}

	        
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}

		function findMonomerPlans(){
			$.ajax({
				url: "/portal/task/findMonomerPlans.action",
				type: 'post', 
				dataType: 'json', 
				data:{
					oldDeptId:'<%=oldDeptId%>',
					random:Math.random()
				},
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
		              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=monomerPlanId]").html(addHtml);
						$("[name=monomerPlanId]").val($("#h_monomerPlanId").val());
	              }
				}
			});	
		}
		
		function save(){
			if(check()){
				if($("#valid_status").val()=="0"){
					$("[name=status]").val("5");//失效
				}else{
					$("[name=status]").val("2");
				}
				
				var formOptions = {
					cache:false,
					type:'post',
					callback:null,
					dataType :'json',
					url:"/portal/task/save.action",
				    success:function(data){
						if(data!=null&&data.if_success=="yes"){
							alert("提交成功！");
							if("<%=id%>"!=""){
								window.close();
							}else{
								window.location.href = "toAdd.action?oldDeptId=<%=oldDeptId%>&monomerPlanId="+$("#h_monomerPlanId").val();
							}
						}else{
							alert("提交失败，请联系管理员！");
						}
						return false;
				    }
				};
				$("#form").ajaxSubmit(formOptions);  
			}
		}

		function check(){
			if($("[name=taskNum]").val()==""){
				alert("请填写任务编号！");
				$("[name=taskNum]").focus();
				return false;
			}

			if($("[name=taskName]").val()==""){
				alert("请填写任务名称！");
				$("[name=taskName]").focus();
				return false;
			}

			if($("[name=monomerPlanId]").val()==""){
				alert("请选择单体计划名称！");
				$("[name=monomerPlanId]").focus();
				return false;
			}

			if($("[name=realStartTime]").val()!=""&&$("[name=realFinishTime]").val()!=""&&$("[name=realStartTime]").val()>=$("[name=realFinishTime]").val()){
				alert("实际结束时间必须大于实际开始时间！");
				return false;
			}
			if($("[name=invalidStartTime]").val()!=""&&$("[name=invalidFinishTime]").val()!=""&&$("[name=invalidStartTime]").val()>=$("[name=invalidFinishTime]").val()){
				alert("有效结束时间必须大于有效开始时间！");
				return false;
			}
			
			if($("[name=remark]").val().length > 2000){
				alert("备注只能输入2000个字符，请重新输入！");
				return false;				
			}
			
			return true;
		}

		function showHideInvalidTr(obj){
			if($(obj).val()=='0'){
				$("#invalidTr").hide();
			}else{
				$("#invalidTr").show();
			}
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
            		<li class="fin" id="titleName">任务填报</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/task/save.action" id="form" name="form" method="post">
        	<input type="hidden" name="id" value="<%=id %>">
	        <input type="hidden" name="createTime" value="<s:property value='task.createTime'/>">
	        <input type="hidden" name="createUser" value="<s:property value='task.createUser'/>">
	        <input type="hidden" name="removed" value="0">
	        <input type="hidden" name="oldDeptId" value="<%=oldDeptId %>">
	        <input type="hidden" name="monomerPlanId" value="<s:property value='task.monomerPlanId'/>">
	        <input type="hidden" name="taskName" value="<s:property value='task.taskName'/>"/>
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <%--
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">任务编号：</td>
                                <td style="width:30%;">
                                <input type="text" name="taskNum" class="input_xxlarge" maxlength="50" value="<s:property value='task.taskNum'/>"/>
                                </td>
                                 
                                 <td class="t_r lableTd" style="width:20%;">单体计划名称：</td>
                                <td style="width:30%;">
                                <select name="monomerPlanId" ></select>
                                <input type="hidden" id="h_monomerPlanId" value="<s:property value='task.monomerPlanId'/>">
                                </td>
                                
                                <td style="width:20%;" class="t_r lableTd">任务名称：</td>
                                <td style="width:30%;">
                                <input type="text" name="taskName" class="input_xxlarge" maxlength="50" value="<s:property value='task.taskName'/>"/>
                                </td>
                                </tr>
                                --%>
                                 <tr>
                                <td class="t_r lableTd">计划开始时间：</td>
                                <td>
                                <input type="text" class="input_large" style="background-color:#EAEAEA;" readonly="readonly" value="<%=planStartTime %>">
                                </td>
                                <td class="t_r lableTd">计划完成时间：</td>
                                <td>
                                <input type="text" class="input_large" style="background-color:#EAEAEA;" readonly="readonly" value="<%=planFinishTime %>">
                                </td>
                                </tr>
                                
								  <tr part="hiddenPart">
                                <td class="t_r lableTd">实际开始时间：</td>
                                <td>
                                <input type="text" name="realStartTime" id="realStartTime" readonly="readonly" class="input_large" value="<s:property value='task.realStartTime'/>">
                                </td>
                                <td class="t_r lableTd" part="hiddenPart">实际完成时间：</td>
                                <td  part="hiddenPart">
                                <input type="text" name="realFinishTime" id="realFinishTime" readonly="readonly" class="input_large" value="<s:property value='task.realFinishTime'/>">
                                </td>
                                <%--
                                <td class="t_r lableTd">预计完成时间：</td>
                                <td>
                                <input type="text" name="forecastFinishTime" id="forecastFinishTime" class="input_large" value="<s:property value='task.forecastFinishTime'/>">
                                </td>
                                 --%>
                                </tr>

								
								  <tr>
                                
                                <td class="t_r lableTd">是否有效：</td>
                                 <td id="statusTd" colspan="3">
                                <select id="valid_status">
			                    	<option value="1">是</option>
			                    	<option value="0">否</option>
                                </select>
                                &nbsp;&nbsp;(如果因方案变更引起重新办证，请选择否，并重新填报该证件的计划)
                                <input type="hidden" name="status" value="<s:property value='task.status'/>">
                                </td>
                                <td class="t_r lableTd" part="showPart" style="display:none;">提醒日期：</td>
                                <td part="showPart" style="display:none;">
                                <input type="text" name="warnTime" id="warnTime" class="input_large" value="<s:property value='task.warnTime'/>">
                                </td>
                                </tr>
								
								<tr id="invalidTr">
                                <td class="t_r lableTd">有效开始时间：</td>
                                <td>
                                <input type="text" name="invalidStartTime" id="invalidStartTime" class="input_large" value="<s:property value='task.invalidStartTime'/>">
                                </td>
                                <td class="t_r lableTd" part="hiddenPart">有效结束时间：</td>
                                <td  part="hiddenPart">
                                <input type="text" name="invalidFinishTime" id="invalidFinishTime" class="input_large" value="<s:property value='task.invalidFinishTime'/>">
                                </td>
								
                                 <tr style="display:none;">
								
                                
                                  <td class="t_r lableTd">责任人：</td>
                                <td>
                                <input type="text" name="mainPerson" class="input_xxlarge" maxlength="50" value="<s:property value='task.mainPerson'/>"/>
                                </td>
                                <td class="t_r lableTd">手机号：</td>
                                <td>
                                <input type="text" name="phone" class="input_xxlarge" maxlength="50" value="<s:property value='task.phone'/>"/>
                                </td>
                                </tr>
                              
							    <tr>
								 <td class="t_r lableTd">更新人：</td>
                                <td>
                                <input type="text" name="updatePerson" class="input_large" style="background-color:#EAEAEA;" readonly="readonly" maxlength="50" value="<s:property value='task.updatePerson'/>"/>
                                </td>
                                <td class="t_r lableTd">更新时间：</td>
                                <td>
                                <input type="text" name="updateTime" id="updateTime" class="input_large" style="background-color:#EAEAEA;" readonly="readonly" value="<s:property value='task.updateTime'/>">
                                </td>
                                </tr>
								<tr part="hiddenPart">
	                                <td class="t_r lableTd">标的物：</td>
	                                <td colspan="3">
		                                <input type="hidden" name="biaodiwu" id="attach" value="<s:property value='task.biaodiwu'/>"/>
		        						<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=<s:property value='task.biaodiwu'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame"></iframe>
	                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" name="remark"><s:property value='task.remark'/></textarea>
                                </td>
                                 
                                </tr>
                                

                            
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="addButton" type="button" value="添  加" onclick="save();"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
