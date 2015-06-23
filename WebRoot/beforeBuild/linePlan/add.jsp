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
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>线路计划</title>
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
	 		
	       //datepicker的“清除”功能
	         $(".ui-datepicker-close").live("click", function (){              
	           if($(this).parent("div").children("button:eq(0)").text()=="planStartTime") $("#planStartTime").val("");
	           if($(this).parent("div").children("button:eq(0)").text()=="planFinishTime") $("#planFinishTime").val("");
	         });

			findProjectAndLine();
			//findAllPlanTypes();
			//findAllPapers();

			if("<%=id%>"!=""){
				//$("[name=ifNode]").val($("#h_ifNode").val());
				//$("[name=ifMilestone]").val($("#h_ifMilestone").val());
				//$("[name=status]").val($("#h_status").val());
				$("#addButton").val("保  存");
				$("#titleName").html($("#titleName").html().replace("新增","修改"));
				$("[name=projectName]").attr("disabled","disabled");
				$("[name=planName]").attr("disabled","disabled");
			}else{
				//$("[name=measure]").val("个");
				
			}
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}

		function findProjectAndLine(){
			$.ajax({
				url: "/portal/history/findProjectAndLine.action?",
				type: 'post', 
				data:{oldDeptId:"<%=oldDeptId%>",type:"0"},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' projectId='"+obj[i][0]+"' routeId='"+obj[i][2]+"' routeName='"+obj[i][3]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=projectName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=projectName]").val($("#h_projectName").val());
						}
	              }
				}
			});	
		}

		function selectProject(obj){
			var option = $(obj).children("option:selected");
			$("[name=projectId]").val(option.attr("projectId"));
			$("[name=routeId]").val(option.attr("routeId"));
			$("[name=routeName]").val(option.attr("routeName"));
			showPlanName();
		}

		function save(){
			if(check()){
				var formOptions = {
					cache:false,
					type:'post',
					callback:null,
					dataType :'json',
					url:"/portal/linePlan/save.action",
				    success:function(data){
						if(data!=null&&data.if_success=="yes"){
							alert("提交成功！");
							if("<%=id%>"!=""){
								window.close();
							}else{
								window.location.href = "toAdd.action?oldDeptId=<%=oldDeptId%>";
							}
						}else{
							alert("提交失败，请联系管理员！");
						}
						return false;
				    }
				};
				$("[name=projectName]").attr("disabled",false);
				$("[name=planName]").attr("disabled",false);
				$("#form").ajaxSubmit(formOptions);  
			}
		}

		function findAllPlanTypes(){
			$.ajax({
				url: "/portal/linePlan/findAllPlanTypes.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' planTypeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=planTypeName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=planTypeName]").val($("#h_planTypeName").val());
						}else{
							$("[name=planTypeName]").val("前期办证");
						}
	              }
				}
			});	
		}

		function selectPlanType(obj){
			var option = $(obj).children("option:selected");
			$("[name=planTypeId]").val(option.attr("planTypeId"));
		}

		function findAllPapers(){
			$.ajax({
				url: "/portal/linePlan/findAllPapers.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' paperId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=paperName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=paperName]").val($("#h_paperName").val());
						}
	              }
				}
			});	
		}

		function selectPaper(obj){
			var option = $(obj).children("option:selected");
			$("[name=paperId]").val(option.attr("paperId"));
		}

		function check(){
			/*
			if($("[name=planNum]").val()==""){
				alert("请填写计划编号！");
				$("[name=planNum]").focus();
				return false;
			}
			*/
			
			if($("[name=projectName]").val()==""){
				alert("请选择项目！");
				$("[name=projectName]").focus();
				return false;
			}

			if($("[name=planStartTime]").val()==""){
				alert("请选择计划开始时间！");
				$("[name=planStartTime]").focus();
				return false;
			}

			if($("[name=planFinishTime]").val()==""){
				alert("请选择计划结束时间！");
				$("[name=planFinishTime]").focus();
				return false;
			}

			if($("[name=planStartTime]").val()!=""&&$("[name=planFinishTime]").val()!=""&&$("[name=planStartTime]").val()>=$("[name=planFinishTime]").val()){
				alert("计划结束时间必须大于计划开始时间！");
				return false;
			}
			
			if($("[name=remark]").val().length > 2000){
				alert("备注只能输入2000个字符，请重新输入！");
				return false;				
			}

			if($("[name=remark]").val().length>2000){
				alert("备注最多输入2000字！");
				return false;
			}

			/*
			var timeLimit = $.trim($("[name=timeLimit]").val());
			if(timeLimit!=""&&!isUnsignedInteger(timeLimit)){
				alert("工期请输入正整数！");
				$("[name=timeLimit]").focus();
				return false;
			}

			var workload = $.trim($("[name=workload]").val());
			if(workload!=""&&!isUnsignedInteger(workload)){
				alert("工作量请输入正整数！");
				$("[name=workload]").focus();
				return false;
			}
*/
			return true;
		}

		function showPlanName(){
			var planOrder = "";
			$.ajax({
				url: "/portal/linePlan/getNextPlanOrder.action",
				type: 'post', 
				dataType: 'json', 
				data:{
					projectId:$("[name=projectId]").val(),
					random:Math.random()
				},
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  $("[name=planOrder]").val(obj);
	            	  planOrder = "-计划-" + obj;
	              }
	              $("[name=planName]").val($("[name=projectName]").val()+planOrder);
				}
			});	
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
            		<li class="fin" id="titleName">线路计划新增</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/linePlan/save.action" id="form" name="form" method="post">
	        <input type="hidden" name="id" value="<%=id %>">
	        <input type="hidden" name="createTime" value="<s:property value='linePlan.createTime'/>">
	        <input type="hidden" name="createUser" value="<s:property value='linePlan.createUser'/>">
	        <input type="hidden" name="removed" value="0">
	        <input type="hidden" name="oldDeptId" value="<%=oldDeptId %>">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">项目：</td>
                                <td style="width:30%;">
                                <select name="projectName" onchange="selectProject(this);"></select>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                <input type="hidden" name="projectId" value="<s:property value='linePlan.projectId'/>">
                                <input type="hidden" id="h_projectName" value="<s:property value='linePlan.projectName'/>">
                                </td>
                                <td style="width:20%;" class="t_r lableTd">计划名称：</td>
                                <td style="width:30%;">
                                <input type="text" name="planName" class="input_xxlarge" maxlength="50" value="<s:property value='linePlan.planName'/>"/>
                                <input type="hidden" name="planOrder" value="<s:property value='linePlan.planOrder'/>">
                                </td>
                                <td class="t_r lableTd" style="display:none;">线路：</td>
                                <td style="display:none;">
                                <input type="text" name="routeName" class="input_large" readonly="readonly" value="<s:property value='linePlan.routeName'/>"/>
                                <input type="hidden" name="routeId" value="<s:property value='linePlan.routeId'/>">
                                </td>
                                </tr>
                                <%--
                                <td class="t_r lableTd">是否节点：</td>
                                <td>
                                <select name="ifNode">
                                	<option value="">---请选择---</option>
                                	<option value="1">是</option>
                                	<option value="0">否</option>
                                </select>
                                <input type="hidden" id="h_ifNode" value="<s:property value='linePlan.ifNode'/>">
                                </td>
                                --%>
                              
							  <tr style="display:none;">
                                <%--
                                <td class="t_r lableTd">是否里程碑：</td>
                                <td>
								 <select name="ifMilestone">
                                	<option value="">---请选择---</option>
                                	<option value="1">是</option>
                                	<option value="0">否</option>
                                </select>
                                <input type="hidden" id="h_ifMilestone" value="<s:property value='linePlan.ifMilestone'/>">
                                </td>
                                 --%>
                                <td class="t_r lableTd">证件名称：</td>
                                <td>
                               <select name="paperName" onchange="selectPaper(this);"></select>
                               <input type="hidden" name="paperId" value="<s:property value='linePlan.paperId'/>">
                               <input type="hidden" id="h_paperName" value="<s:property value='linePlan.paperName'/>">
                                </td>
                                <td class="t_r lableTd">计量单位：</td>
                                <td>
                                <input type="text" name="measure" class="input_xxlarge" maxlength="50" value="<s:property value='linePlan.measure'/>"/>
                                </td>
                                </tr>
								<tr style="display:none;">
                                <%--
                                <td style="width:20%;" class="t_r lableTd">计划编号：</td>
                                <td style="width:30%;">
                                <input type="text" name="planNum" class="input_xxlarge" maxlength="50" value="<s:property value='linePlan.planNum'/>"/>
                                </td>
                                 --%>
                                
                                <td style="width:20%;" class="t_r lableTd">计划类型：</td>
                                <td style="width:30%;">
                                <%--<select name="planTypeName" onchange="selectPlanType(this);"></select>
                                <input type="hidden" name="planTypeId" value="<s:property value='linePlan.planTypeId'/>">
                                <input type="hidden" id="h_planTypeName" value="<s:property value='linePlan.planTypeName'/>">
                                --%>
                                <input type="hidden" name="planTypeId" value="1">
                                <input type="hidden" name="planTypeName" value="前期办证">
                                </td>
                                </tr>
								 <tr>
                                <td class="t_r lableTd">计划开始时间：</td>
                                <td>
                                <input type="text" name="planStartTime" id="planStartTime" readonly="readonly" class="input_large" value="<s:property value='linePlan.planStartTime'/>">
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                <td class="t_r lableTd">计划完成时间：</td>
                                <td>
                                <input type="text" name="planFinishTime" id="planFinishTime" readonly="readonly" class="input_large" value="<s:property value='linePlan.planFinishTime'/>">
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                </tr>
								
								 <tr style="display:none;">
                                <td class="t_r lableTd">工期(天)：</td>
                                <td>
                                <input type="text" name="timeLimit" class="input_xxlarge" maxlength="50" value="<s:property value='linePlan.timeLimit'/>"/>
                                </td>
                                <td class="t_r lableTd">工作量：</td>
                                <td>
                                <input type="text" name="workload" class="input_xxlarge" maxlength="50" value="<s:property value='linePlan.workload'/>"/>
                                </td>
                                </tr>
								
                                
                                <%--
                                <td class="t_r lableTd">当前状态：</td>
                                <td>
                                <select name="status">
                                	<option value="">---请选择---</option>
			                    	<option value="1">待办</option>
			                    	<option value="2">在办</option>
			                    	<option value="3">延误</option>
			                    	<option value="4">已办</option>
                                </select>
                                <input type="hidden" id="h_status" value="<s:property value='linePlan.status'/>">
                                </td>
                                 --%>

                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" name="remark"><s:property value='linePlan.remark'/></textarea>
                                </td>
                                 
                                </tr>
                                
                               
                                
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="addButton" type="button" value="添  加" onclick="save();" title="将数据添加到数据库，并可继续添加新信息"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
