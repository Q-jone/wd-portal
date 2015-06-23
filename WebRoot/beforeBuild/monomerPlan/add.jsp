<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
String username = (String)session.getAttribute("userName");
if(username==null||"null".equals(username)){
	username = "";
}
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String id = request.getParameter("id");
if(id==null){
	id = "";
}
String selected_route_name = request.getParameter("selected_route_name");
if(selected_route_name==null){
	selected_route_name = "";
}
String selected_route_id = request.getParameter("selected_route_id");
if(selected_route_id==null){
	selected_route_id = "";
}
String monomerTypeName = request.getParameter("monomerTypeName");
if(monomerTypeName==null){
	monomerTypeName = "";
}
String monomerTypeId = request.getParameter("monomerTypeId");
if(monomerTypeId==null){
	monomerTypeId = "";
}
String monomerName = request.getParameter("monomerName");
if(monomerName==null){
	monomerName = "";
}
String monomerId = request.getParameter("monomerId");
if(monomerId==null){
	monomerId = "";
}
String ruleVersionId = request.getParameter("ruleVersionId");
if(ruleVersionId==null){
	ruleVersionId = "";
}
String warnDays = request.getParameter("warnDays");
if(warnDays==null){
	warnDays = "";
}
String mainPerson = request.getParameter("mainPerson");
if(mainPerson==null){
	mainPerson = "";
}
String phone = request.getParameter("phone");
if(phone==null){
	phone = "";
}
String default_phone = (String)request.getAttribute("default_phone");
if(default_phone==null||"null".equals(default_phone)){
	default_phone = "";
}
String linePlanName = request.getParameter("linePlanName");
if(linePlanName==null||"null".equals(linePlanName)){
	linePlanName = "";
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>单体办证计划</title>
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

	         if("<%=id%>"==""){
	         	$("[name=routeName]").val("<%=selected_route_name%>");
				$("[name=routeId]").val("<%=selected_route_id%>");
	         }
			findAllTypes();
			//findLines();
			//findAllTypes();
			findAllPapers();
			//findLinePlans();
			findRuleVersions();

			if("<%=id%>"!=""){
				//$("[name=ifNode]").val($("#h_ifNode").val());
				//$("[name=ifMilestone]").val($("#h_ifMilestone").val());
				//$("[name=status]").val($("#h_status").val());
				$("#addButton").val("保  存");
				$("#titleName").html($("#titleName").html().replace("新增","修改"));
			}else{
				if("<%=warnDays%>"==""){
					$("[name=warnDays]").val("3");
				}else{
					$("[name=warnDays]").val("<%=warnDays%>");
				}
				if("<%=mainPerson%>"==""){
					$("[name=mainPerson]").val("<%=username%>");
				}else{
					$("[name=mainPerson]").val("<%=mainPerson%>");
				}
				if("<%=phone%>"==""){
					$("[name=phone]").val("<%=default_phone%>");
				}else{
					$("[name=phone]").val("<%=phone%>");
				}
			}
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}

		function findLines(){
			$.ajax({
				url: "/portal/monomerPlan/findLines.action",
				type: 'post', 
				data:{oldDeptId:"<%=oldDeptId%>"},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' routeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=routeName]").html(addHtml);
					  if("<%=id%>"!=""){
							$("[name=routeName]").val($("#h_routeName").val());
						}else{
							$("[name=routeName]").val("<%=selected_route_name%>");
							$("[name=routeId]").val("<%=selected_route_id%>");
						}
					  findAllTypes();
	              }
				}
			});	
		}

		function selectRoute(obj){
			var option = $(obj).children("option:selected");
			$("[name=routeId]").val(option.attr("routeId"));
			$("[name=monomerTypeName]").val("");
			$("[name=monomerTypeId]").val("");
			$("[name=monomerName]").val("");
			$("[name=monomerId]").val("");
		}

		function findAllTypes(){
			$.ajax({
				url: "/portal/history/findAllTypes.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' monomerTypeId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=monomerTypeName]").html(addHtml);
					  if("<%=id%>"!=""){
						  $("[name=monomerTypeName]").val($("#h_monomerTypeName").val());
							findMonomerByRouteAndType();
						}else if("<%=monomerTypeName%>"!=""){
							$("[name=monomerTypeName]").val("<%=monomerTypeName%>");
							$("[name=monomerTypeId]").val("<%=monomerTypeId%>");
							findMonomerByRouteAndType();
						}
	              }
				}
			});	
		}

		function selectType(obj){
			var option = $(obj).children("option:selected");
			$("[name=monomerTypeId]").val(option.attr("monomerTypeId"));
			findMonomerByRouteAndType();
		}

		function findMonomerByRouteAndType(){
			$.ajax({
				url: "/portal/history/findMonomerByRouteAndType.action",
				type: 'post', 
				data:{
					routeId:$("[name=routeId]").val(),
					typeName:$("[name=monomerTypeName]").val(),
					projectId:"",
					typeId:$("[name=monomerTypeId]").val(),
					random:Math.random()
				},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
				   var addHtml = "<option value=''>---请选择---</option>";
		           if(obj!=null&&obj.length>0){
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][1]+"' monomerId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
	               }
		           $("[name=monomerName]").html(addHtml);
		           if("<%=id%>"!=""){
		        	   $("[name=monomerName]").val($("#h_monomerName").val());
					}else if("<%=monomerName%>"!=""){
						$("[name=monomerName]").val("<%=monomerName%>");
						$("[name=monomerId]").val("<%=monomerId%>");
						showPlanName();
					}
				}
			});	
		}

		function selectMonomer(obj){
			var option = $(obj).children("option:selected");
			$("[name=monomerId]").val(option.attr("monomerId"));
			showPlanName();
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

		function findLinePlans(){
			$.ajax({
				url: "/portal/monomerPlan/findLinePlans.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
		              if(obj!=null&&obj.length>0){
					  var addHtml = "<option value=''>---请选择---</option>";
					  for(var i=0;i<obj.length;i++){
						  addHtml += "<option value='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
					  }
					  $("[name=linePlanId]").html(addHtml);
						$("[name=linePlanId]").val($("#h_linePlanId").val());
	              }
				}
			});	
		}

		function findRuleVersions(){
			$.ajax({
				url: "/portal/monomerPlan/findRuleVersions.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null){
					  if("<%=id%>"==""){
						  $("[name=ruleVersionId]").val(obj);
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
			

			if($("[name=planName]").val()==""){
				alert("请填写计划名称！");
				$("[name=planName]").focus();
				return false;
			}

			if($("[name=linePlanId]").val()==""){
				alert("请选择线路计划名称！");
				$("[name=linePlanId]").focus();
				return false;
			}
			*/

			if($("[name=planStartTime]").val()==""){
				alert("请选择计划开始时间！");
				$("[name=planStartTime]").focus();
				return false;
			}

			if($("[name=planFinishTime]").val()==""){
				alert("请选择计划完成时间！");
				$("[name=planFinishTime]").focus();
				return false;
			}
			
			if($("[name=monomerName]").val()==""){
				alert("请选择单体！");
				$("[name=monomerName]").focus();
				return false;
			}

			if($("[name=paperName]").val()==""){
				alert("请选择证件名称！");
				$("[name=paperName]").focus();
				return false;
			}
			
			var warnDays = $.trim($("[name=warnDays]").val());
			if(warnDays!=""&&!isUnsignedInteger(warnDays)){
				alert("前N天提醒请输入正整数！");
				$("[name=warnDays]").focus();
				return false;
			}

			if($("[name=planStartTime]").val()!=""&&$("[name=planFinishTime]").val()!=""&&$("[name=planStartTime]").val()>=$("[name=planFinishTime]").val()){
				alert("计划结束时间必须大于计划开始时间！");
				return false;
			}
			
			var regPartton=/1[3-8]+\d{9}/;
			if($("[name=phone]").val()!=""){
				if(!regPartton.test($("[name=phone]").val())||$("[name=phone]").val().length!=11){
					alert("请输入有效手机号码！");
					$("[name=phone]").focus();
					return false;
				}
			}

			if($("[name=remark]").val().length>2000){
				alert("备注最多输入2000字！");
				return false;
			}

			return true;
		}

		function save(){
			if(check()){
				var formOptions = {
					cache:false,
					type:'post',
					callback:null,
					dataType :'json',
					url:"/portal/monomerPlan/save.action",
				    success:function(data){
						if(data!=null&&data.if_success=="yes"){
							alert("提交成功！");
							if("<%=id%>"!=""){
								window.close();
							}else{
								window.location.href = "toAdd.action?oldDeptId=<%=oldDeptId%>&linePlanId="+$("[name=linePlanId]").val()
											+"&selected_route_name="+encodeURI($("[name=routeName]").val())+"&selected_route_id="+$("[name=routeId]").val()
											+"&monomerTypeName="+encodeURI($("[name=monomerTypeName]").val())+"&monomerTypeId="+$("[name=monomerTypeId]").val()
											+"&monomerName="+encodeURI($("[name=monomerName]").val())+"&monomerId="+$("[name=monomerId]").val()
											+"&ruleVersionId="+$("[name=ruleVersionId]").val()+"&warnDays="+$("[name=warnDays]").val()
											+"&mainPerson="+encodeURI($("[name=mainPerson]").val())+"&phone="+encodeURI($("[name=phone]").val())
											+"&linePlanName="+encodeURI($("#linePlanName").val());
							}
						}else{
							alert("提交失败，请联系管理员！");
						}
						return false;
				    }
				};
				
				$.ajax({
					url: "/portal/monomerPlan/findIfExist.action",
					type: 'post', 
					dataType: 'json', 
					data:{
						linePlanId:$("[name=linePlanId]").val(),
						monomerId:$("[name=monomerId]").val(),
						paperId:$("[name=paperId]").val()
					},
					error:function(){
						alert("系统连接失败，请稍后再试！");
					},
					success: function(obj){			
		              if(obj!=null&&obj.if_exist=="no"){
		            	  $("#form").ajaxSubmit(formOptions);  
		              }else if(obj!=null&&obj.if_exist=="yes"){
		            	  if(confirm("证件重复，是否确认新增？")){
		            		  $("#form").ajaxSubmit(formOptions);  
		            	  }
		              }
					}
				});	
			}
		}

		function getNodeAndMilestone(){
			$.ajax({
				url: "/portal/ruleDetail/getNodeAndMilestone.action",
				type: 'post', 
				data:{
					ruleVersionId:$("[name=ruleVersionId]").val(),
					paperId:$("[name=paperId]").val(),
					random:Math.random()
				},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  $("[name=ifNode]").val(obj[0]);
					  $("[name=ifMilestone]").val(obj[1]);
	              }else{
	            	  $("[name=ifNode]").val("");
					  $("[name=ifMilestone]").val("");
	              }
				}
			});	
		}

		function getPaperByLine(){
			$.ajax({
				url: "/portal/linePlan/getPaperByLine.action",
				type: 'post', 
				data:{
					linePlanId:$("[name=linePlanId]").val(),
					random:Math.random()
				},
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  $("[name=paperId]").val(obj[0]);
					  $("[name=paperName]").val(obj[1]);
	              }else{
	            	  $("[name=paperId]").val("");
					  $("[name=paperName]").val("");
	              }
	              getNodeAndMilestone();
				}
			});	
		}

		function showPlanName(){
			var planOrder = "";
			$.ajax({
				url: "/portal/monomerPlan/getNextPlanOrder.action",
				type: 'post', 
				dataType: 'json', 
				data:{
					monomerId:$("[name=monomerId]").val(),
					random:Math.random()
				},
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  $("[name=planOrder]").val(obj);
	            	  planOrder = "-" + obj;
	              }
	              $("[name=planName]").val($("#linePlanName").val()+"-"+$("[name=monomerName]").val()+planOrder);
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
            		<li class="fin" id="titleName">单体办证计划新增</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/monomerPlan/save.action" id="form" name="form" method="post">
          <input type="hidden" name="id" value="<%=id %>">
	        <input type="hidden" name="createTime" value="<s:property value='monomerPlan.createTime'/>">
	        <input type="hidden" name="createUser" value="<s:property value='monomerPlan.createUser'/>">
	        <input type="hidden" name="removed" value="0">
	        <input type="hidden" name="oldDeptId" value="<%=oldDeptId %>">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">单体：</td>
                                <td colspan="3">
                                	线路：
                                	<s:if test="monomerPlan.routeName==null">
                                		<%=selected_route_name%>
                                	</s:if>
                                	<s:else>
                                		<s:property value='monomerPlan.routeName'/>
                                	</s:else>
                                	&nbsp;&nbsp;
                                	单体类型：<select name="monomerTypeName" onchange="selectType(this);"></select>&nbsp;&nbsp;
                                	单体名称：<select name="monomerName" onchange="selectMonomer(this);showPlanName();"></select>
                                	<span style="color:red;display:inline;">&nbsp;*</span>
                                	<input type="hidden" name="routeId" value="<s:property value='monomerPlan.routeId'/>">
                                	<input type="hidden" name="routeName" value="<s:property value='monomerPlan.routeName'/>">
	                                <input type="hidden" name="monomerTypeId" value="<s:property value='monomerPlan.monomerTypeId'/>">
	                                <input type="hidden" id="h_monomerTypeName" value="<s:property value='monomerPlan.monomerTypeName'/>">
	                                <input type="hidden" name="monomerId" value="<s:property value='monomerPlan.monomerId'/>">
	                                <input type="hidden" id="h_monomerName" value="<s:property value='monomerPlan.monomerName'/>">
	                                <input type="hidden" name="linePlanId" value="<s:property value='monomerPlan.linePlanId'/>">
	                                <input type="hidden" id="linePlanName" value="<%=linePlanName %>">
	                                <input type="hidden" name="ruleVersionId" value="<s:property value='monomerPlan.ruleVersionId'/>">
	                                <input type="hidden" name="checkStatus" value="<s:property value='monomerPlan.checkStatus'/>">
                                </td>
                                </tr>
                              <tr>
                              <%--
                                <td class="t_r lableTd">线路计划名称：</td>
                                <td>
                                <select name="linePlanId" onchange="showPlanName();getPaperByLine();"></select>
                                <input type="hidden" id="h_linePlanId" value="<s:property value='monomerPlan.linePlanId'/>">
                                </td>
                                 --%>
                                 <td style="width:20%;" class="t_r lableTd">计划名称：</td>
                                <td style="width:30%;">
                                <input type="text" name="planName" readonly="readonly" class="input_xxlarge" style="background-color:#EAEAEA;" maxlength="50" value="<s:property value='monomerPlan.planName'/>"/>
                                <input type="hidden" name="planOrder" value="<s:property value='monomerPlan.planOrder'/>">
                                </td>
                                <td class="t_r lableTd" style="width:20%;">证件名称：</td>
                                <td style="width:30%;">
                                <select name="paperName" onchange="selectPaper(this);"></select>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                               <input type="hidden" name="paperId" value="<s:property value='monomerPlan.paperId'/>">
                               <input type="hidden" id="h_paperName" value="<s:property value='monomerPlan.paperName'/>">
                                </td>
                                <%--
                                <td class="t_r lableTd">规则版本名称：</td>
                                <td>
                                
                                <select name="ruleVersionId" onchange="getNodeAndMilestone();"></select>
                                <input type="hidden" id="h_ruleVersionId" value="<s:property value='monomerPlan.ruleVersionId'/>">
                                 
                                
                                </td>
                                <td style="width:20%;" class="t_r lableTd">计划编号：</td>
                                <td style="width:30%;">
                                <input type="text" name="planNum" class="input_xxlarge" maxlength="50" value="<s:property value='monomerPlan.planNum'/>"/>
                                </td>
                                 
                                 
                                
                                </tr>
                                
                                 <tr>
                                <td class="t_r lableTd">是否节点：</td>
                                <td>
                                <select name="ifNode">
                                	<option value="">---请选择---</option>
                                	<option value="1">是</option>
                                	<option value="0">否</option>
                                </select>
                                <input type="hidden" id="h_ifNode" value="<s:property value='monomerPlan.ifNode'/>">
                                </td>
                                <td class="t_r lableTd">是否里程碑：</td>
                                <td>
                                <select name="ifMilestone">
                                	<option value="">---请选择---</option>
                                	<option value="1">是</option>
                                	<option value="0">否</option>
                                </select>
                                <input type="hidden" id="h_ifMilestone" value="<s:property value='monomerPlan.ifMilestone'/>">
                                </td>
                                </tr>
                              --%>
							   <tr>
                                <td class="t_r lableTd">计划开始时间：</td>
                                <td>
                                <input type="text" name="planStartTime" id="planStartTime" readonly="readonly" class="input_large" value="<s:property value='monomerPlan.planStartTime'/>">
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                <td class="t_r lableTd">计划完成时间：</td>
                                <td>
                                <input type="text" name="planFinishTime" id="planFinishTime" readonly="readonly" class="input_large" value="<s:property value='monomerPlan.planFinishTime'/>">
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                </tr>
								
								  
								
								  <tr>
                                <td class="t_r lableTd">经办人：</td>
                                <td>
                                <input type="text" name="mainPerson" class="input_large" maxlength="50" value="<s:property value='monomerPlan.mainPerson'/>"/>
                                </td>
                                <td class="t_r lableTd">经办人手机：</td>
                                <td>
                                <input type="text" name="phone" class="input_large" maxlength="50" value="<s:property value='monomerPlan.phone'/>"/>
                                </td>
                                </tr>
								<tr>
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
                                <input type="hidden" id="h_status" value="<s:property value='monomerPlan.status'/>">
                                </td>
                                 --%>
                                <td class="t_r lableTd">提前提醒天数：</td>
                                <td colspan="3">
                                <input type="text" name="warnDays" class="input_large" maxlength="50" value="<s:property value='monomerPlan.warnDays'/>"/>
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" name="remark"><s:property value='monomerPlan.remark'/></textarea>
                                </td>
                                 
                                </tr>
                                <s:if test="monomerPlan.checkStatus==4">
	                                <tr>
		                                <td class="t_r lableTd">审核意见：</td>
		                                <td colspan="3">
			                                <s:iterator value="#request.checkInfo" id="data" status="s">
				                                <p><s:property value='#data[0]'/></p>
				                                <p>审核人：<s:property value='#data[1]'/>&nbsp;&nbsp;&nbsp;&nbsp;审核时间：<s:property value='#data[2]'/></p>
				                                <br>
			                                </s:iterator>
		                                </td>
	                                </tr>
                                </s:if>
							

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
