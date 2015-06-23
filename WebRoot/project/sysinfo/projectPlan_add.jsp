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
<title>项目计划信息录入
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
        <script src="<%=basePath %>project/project/js/jquery.form.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="<%=basePath %>js/show.js"></script>
		<script src="<%=basePath %>js/loading.js"></script>
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<script type="text/javascript" src="<%=basePath %>project/sysinfo/js/projectPlan.js"></script>
		<script type="text/javascript">
		
		var count=0;
         $(function(){
        	 
        	 
        	 /**
        	 $("#submit").click(function(){
        		 	$("#add").ajaxSubmit(addOptions);
        	 });**/
      		 
        	 count=$("#mytable tr").length;
        	 if(count<=1){
        		 $("input[id=save]").hide();
        		 $("input[id=back]").hide();
        	 }
        	 //将保存按钮动态显示        	 
        	 function sh(count){
        		 if(count>=1){
        			 $("input[id=save]").show();
            		 $("input[id=back]").show();
        		 }
        	 }
        	//新增一行表单便于添加记录
        	
        	 $("#addline").click(function(){
        		 count=$("#mytable tr").length;//计算当前表格的总行数
        		 count=count;
        		 var trs="<tr id='dataTr'><td class='t_c'>"+count+"</td>"
        		 +"<td class='t_c'><select id='planName"+(count-1)+"' name='projectPlan["+(count-1)+"].planName' style='width:100%;'>"
					+"<option value='1'>前期策划</option>"
					+"<option value='2'>立项采购</option>"
					+"<option value='3'>实施推进</option>"
					+"</select></td>"
        		 +"<td class='t_c'><select id='subPlanName"+(count-1)+"' name='projectPlan["+(count-1)+"].subPlanName' style='width:100%;'>"
        		 	+"<option value='12'>目标及效果</option>"
					+"<option value='13'>需求及功能</option>"
					+"<option value='14'>技术方案</option>"
					+"</select></td>"
        		 +"<td class='t_c'><input readonly='readonly' id='planBeginDate"+(count-1)+"' name='projectPlan["+(count-1)+"].planBeginDate' type='date' style='width:100%;' value=''></td>"
        		 +"<td class='t_c'><input readonly='readonly' id='planEndDate"+(count-1)+"' name='projectPlan["+(count-1)+"].planEndDate' type='date' style='width:100%;'></td>"
        		 +"<td class='t_c'><input readonly='readonly' id='realBeginDate"+(count-1)+"' name='projectPlan["+(count-1)+"].realBeginDate' type='date' style='width:100%;'></td>"
        		 +"<td class='t_c'><input readonly='readonly' id='realEndDate"+(count-1)+"' name='projectPlan["+(count-1)+"].realEndDate' type='date' style='width:100%;'></td>"
        		 +"<td class='t_c' colspan='2' ><input type='text' id='memo"+(count-1)+"' name='projectPlan["+(count-1)+"].memo' style='width:100%;'></td>"
        		 +"<td class='t_c'><input type='submit' id='' value='历史计划'/><input type='submit' id='' value='删除' /></td></tr>";
        		 
        	 	 $("tbody").append(trs);
        	 	 sh(count);
        	 });
        	 
        	 /** jQuery("tr").each(
     		function(){
     		var ids= $(this).attr("id");
     		alert(ids);
     		});*/
     		
               /**  //用jQuery获取table中td值
                 $("#mytable td").click(function(){
                     alert("table td值："+$(this).text());
                 });*/
                 
                 //jQuery获取table中点击位置所在行的id
                 $("#mytable td").live("mouseenter",function(){//、根据获取的id操作 
                	 var timeId=$(this).children().attr("id");
                	 //alert(timeId);//planName
                	 var substr=timeId.substr(0,4);
                	 //alert(substr);
                	 if(substr=="real"||substr=="plan"){
                		 if(substr=="plan"&&timeId.substr(0,5)=="planN"){//不是时间控件
                			 
                		 }else{
                			 addTime("#"+timeId);
                		 }
                	 	
                	 }
                	 //alert($("#"+timeId).val());
                	 //alert("planName"==timeId.substr(0,8));
                	 if(timeId.substr(0,5)=="planN"||timeId.substr(0,5)=="subPl")
                	 	$("#"+timeId).click(function(){//添加可以
                	 		cascade(timeId);
                	 	});
						
                 });
                 /**
                 $("#mytable td").click(function() {
                     //td的id 
                     //alert($(this).attr("id"));
                     alert($(this).children().attr("id"));
                     //tr的id  children()
                     //alert($(this).parent().attr("id"));
                 });*/
                 
                 //级联下拉框
                 function cascade(timeId){
                	 if("planName"==timeId.substr(0,8)){
	                	 var selectsub="";
	                	 
	                	 if($("#"+timeId).val()=="1"){
	                		 selectsub+="<option value='12'>目标及效果</option>"
	     					+"<option value='13'>需求及功能</option>"
	     					+"<option value='14'>技术方案</option>";
	                	 }
						 if($("#"+timeId).val()=="2"){
							 selectsub+="<option value='4'>立项审价</option>"
			     			 +"<option value='5'>立项审核</option>"
			     			 +"<option value='6'>采购技术文</option>"
			     			 +"<option value='7'>完成采购</option>"
			     			 +"<option value='17'>软件升级完成</option>";
	                	 }
						 if($("#"+timeId).val()=="3"){
							 selectsub+="<option value='8'>项目开工</option>"
			     			 +"<option value='9'>实施方案</option>"
			     			 +"<option value='10'>项目初验</option>"
			     			 +"<option value='11'>项目验收</option>"
			     			 +"<option value='15'>试运行</option>"
							+"<option value='16'>阶段验收</option>"
							+"<option value='18'>项目实施</option>";
	                	 }
						 var subId="subPlanName"+timeId.substr(8);
						 //alert(subId);
						 
						 $("#"+subId).empty();
						 $("#"+subId).append(selectsub);
                	 }
                 }
                 
                 function addTime(timeId) {

					$(timeId).datepicker({
						//inline: true
						"changeYear" : true,
						"showButtonPanel" : true,
						"closeText" : '清除',
						"currentText" : timeId//仅作为“清除”按钮的判断条件
					});
					$(".ui-datepicker-close").live("click",function() {
						if ($(this).parent("div").children("button:eq(0)").text() == timeId)
							$(timeId).val("");
					});
				}

				$(".odd tr:odd").css("background", "#fafafa");
				$('#planBeginDate').datepicker({
					//inline: true
					"changeYear" : true,
					"showButtonPanel" : true,
					"closeText" : '清除',
					"currentText" : '#planBeginDate'//仅作为“清除”按钮的判断条件
				});
				$('#planEndDate').datepicker({
					//inline: true
					"changeYear" : true,
					"showButtonPanel" : true,
					"closeText" : '清除',
					"currentText" : 'planEndDate'//仅作为“清除”按钮的判断条件						
				});

				$('#realBeginDate').datepicker({
					//inline: true								
					"changeYear" : true,
					"showButtonPanel" : true,
					"closeText" : '清除',
					"currentText" : 'realBeginDate'//仅作为“清除”按钮的判断条件						
				});

				$('#realEndDate').datepicker({
					//inline: true								
					"changeYear" : true,
					"showButtonPanel" : true,
					"closeText" : '清除',
					"currentText" : 'realEndDate'//仅作为“清除”按钮的判断条件						
				});
				//datepicker的“清除”功能
				$(".ui-datepicker-close").live("click",function() {
					if ($(this).parent("div").children("button:eq(0)").text() == "planBeginDate")
								$("#planBeginDate").val("");
							if ($(this).parent("div").children("button:eq(0)").text() == "planEndDate")
								$("#planEndDate").val("");
							if ($(this).parent("div").children("button:eq(0)").text() == "realBeginDate")
								$("#realBeginDate").val("");
							if ($(this).parent("div").children("button:eq(0)").text() == "realEndDate")
								$("#realEndDate").val("");
						});

				//loadShow();
				$("select[id^=planName]").click(function(){//subPlanName
	               	 //alert("aaa");
	               	 var id=$(this).attr("id");
	               	cascade(id);
                });
				
				$("a[id^=deletes]").click(function(){
					var id=$(this).attr("id");
					
					//alert(id.substr(7));
					var removed="removed"+id.substr(7);
					$("#"+removed).val("1");
					var tid="td"+id.substr(7);
					$("."+tid).hide();
				});
				
			});
         function backs(){
        	 window.opener=null;
      		  window.open("","_self");
      		  window.close();
         }
         function history(projectPlanId){
        	 window.open("/portal/projectPlanHistory/sysinfo/history.action?projectPlanId="+projectPlanId);
			}
       function  deletes(projectPlanId){
    	   alert(projectPlanId+"ads");
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
            		<li class="fin">项目计划信息录入</li>
				</ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        
        <div class="mb10 pt45">
        <div style="background-color: ;">
		                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h5 class="fl"><a href="#" class="colSelect fl">项目计划信息列表</a></h5>
		             &nbsp;<input type="button" name="addline" id="addline"  value="新 增" class="fr">
		           <!--  <input type="button" name="excelButton" id="excelButton" value="导 出当页至 EXCEL" class="fl">
		              &nbsp;<input type="button" name="excelAllButton" id="excelAllButton" value="导 出全部至 EXCEL" class="fl">
		            --> </div>
		            <s:form action="saves" id="add" method="post" namespace="/projectPlan/sysinfo">
        <!-- <form id="add" method="POST" action="projectPlan/saves.action" > -->
        <input  type="hidden" id="projectId" name="projectId" value="<s:property value='project.projectId'/>"/>
        <table width="100%"  class="table_1" id="mytable">
        	<thead>
        		<tr>
	        		<td style="width:3%;" class="t_c">序号</td>
	        		<td style="width:8%;" class="t_c">阶段</td>
	        		<td style="width:8%;" class="t_c">计划</td>
	        		<td style="width:10%;" class="t_c">计划开始时间</td>
	        		<td style="width:10%;" class="t_c">计划结束时间</td>
	        		<td style="width:10%;" class="t_c">实际开始时间</td>
	        		<td style="width:10%;" class="t_c">实际结束时间</td>
	        		<td style="width:21%;" colspan="2" class="t_c">说明</td>
	        		<td style="width:10%;" class="t_c">操作</td>
	        	</tr>
        	</thead>
        	<tbody>
        	<s:set name="plan" value="projectPlan"></s:set>
        		<s:iterator value="plan" id="pp" status="p">
        			<input type="hidden" id="projectPlanId<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].projectPlanId" value="<s:property value='projectPlanId'/>" />
        			<input type="hidden" id="removed<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].removed" value="<s:property value='removed'/>" />
        			<tr id="dataTr" class="td<s:property value='#p.index'/>">
        		
        				<td class="t_c"><s:property value='#p.index+1'/></td>
        				<td class="t_c">
        				<input type="hidden" id="plan<s:property value='#p.index'/>" value="<s:property value='planName'/>" />
        					<select id="planName<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].planName" style="width:100%;">
        						<s:if test="planName==1">
	        						<option value="1" selected="selected">前期策划</option>
	        						<option value="2" >立项采购</option>
	        						<option value="3" >实施推进</option>
	        					</s:if>
	        					<s:if test="planName==2">
	        						<option value="1" >前期策划</option>
	        						<option value="2" selected="selected">立项采购</option>
	        						<option value="3" >实施推进</option>
	        					</s:if>
	        					<s:if test="planName==3">
	        						<option value="1" >前期策划</option>
	        						<option value="2">立项采购</option>
	        						<option value="3"  selected="selected">实施推进</option>
	        					</s:if>
        					</select>
        				<td class="t_c">
        				<input type="hidden" id="sub<s:property value='#p.index'/>" value="<s:property value='subPlanName'/>" />
        					<select style="width:100%;" id="subPlanName<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].subPlanName">
        						<s:if test="planName==1">
        							<s:if test="subPlanName==12">
        								<option value="12"  selected="selected">目标及效果</option>
        								<option value="13">需求及功能</option>
        								<option value="14">技术方案</option>
        							</s:if>
        							<s:if test="subPlanName==13">
        								<option value="12">目标及效果</option>
        								<option value="13"  selected="selected">需求及功能</option>
        								<option value="14">技术方案</option>
        							</s:if>
        							<s:if test="subPlanName==14">
        								<option value="12">目标及效果</option>
        								<option value="13">需求及功能</option>
        								<option value="14"  selected="selected">技术方案</option>
        								
        							</s:if>
        						</s:if>
        						<s:if test="planName==2">
        							<s:if test="subPlanName==4">
        								<option value="4"  selected="selected">立项审价</option>
        								<option value="5">立项审核</option>
        								<option value="6">采购技术文件</option>
        								<option value="7">完成采购</option>
        								<option value="17">软件升级完成</option>
        							</s:if>
        							<s:if test="subPlanName==5">
        								<option value="4">立项审价</option>
        								<option value="5"  selected="selected">立项审核</option>
        								<option value="6">采购技术文件</option>
        								<option value="7">完成采购</option>
        								<option value="17">软件升级完成</option>
        							</s:if>
        							<s:if test="subPlanName==6">
        								<option value="4">立项审价</option>
        								<option value="5">立项审核</option>
        								<option value="6"  selected="selected">采购技术文件</option>
        								<option value="7">完成采购</option>
        								<option value="17">软件升级完成</option>
        							</s:if>
        							<s:if test="subPlanName==7">
        								<option value="4">立项审价</option>
        								<option value="5">立项审核</option>
        								<option value="6">采购技术文件</option>
        								<option value="7"   selected="selected">完成采购</option>
        								<option value="17">软件升级完成</option>
        							</s:if>
        							<s:if test="subPlanName==17">
        								<option value="4">立项审价</option>
        								<option value="5">立项审核</option>
        								<option value="6">采购技术文件</option>
        								<option value="7">完成采购</option>
        								<option value="17" selected="selected">软件升级完成</option>
        							</s:if>
        						</s:if>
        						<s:if test="planName==3">
        						<s:if test="subPlanName==8">
        								<option value="8"  selected="selected">项目开工</option>
        								<option value="9">实施方案</option>
        								<option value="10">项目初验</option>
        								<option value="11">项目收验</option>
        								<option value="15">试运行</option>
        								<option value="16">阶段验收</option>
        								<option value="18">项目实施</option>
        							</s:if>
        							<s:if test="subPlanName==9">
        								<option value="8">项目开工</option>
        								<option value="9"   selected="selected">实施方案</option>
        								<option value="10">项目初验</option>
        								<option value="11">项目收验</option>
        								<option value="15">试运行</option>
        								<option value="16">阶段验收</option>
        								<option value="18">项目实施</option>
        							</s:if>
        							<s:if test="subPlanName==10">
        								<option value="8">项目开工</option>
        								<option value="9">实施方案</option>
        								<option value="10"   selected="selected">项目初验</option>
        								<option value="11">项目收验</option>
        								<option value="15">试运行</option>
        								<option value="16">阶段验收</option>
        								<option value="18">项目实施</option>
        							</s:if>
        							<s:if test="subPlanName==11">
        								<option value="8">项目开工</option>
        								<option value="9">实施方案</option>
        								<option value="10">项目初验</option>
        								<option value="11"   selected="selected">项目收验</option>
        								<option value="15">试运行</option>
        								<option value="16">阶段验收</option>
        								<option value="18">项目实施</option>
        							</s:if>
        							<s:if test="subPlanName==15">
        								<option value="8">项目开工</option>
        								<option value="9">实施方案</option>
        								<option value="10">项目初验</option>
        								<option value="11">项目收验</option>
        								<option value="15"    selected="selected">试运行</option>
        								<option value="16">阶段验收</option>
        								<option value="18">项目实施</option>
        							</s:if>
        							<s:if test="subPlanName==16">
        								<option value="8">项目开工</option>
        								<option value="9">实施方案</option>
        								<option value="10">项目初验</option>
        								<option value="11">项目收验</option>
        								<option value="15">试运行</option>
        								<option value="16"    selected="selected">阶段验收</option>
        								<option value="18">项目实施</option>
        							</s:if>
        							<s:if test="subPlanName==18">
        								<option value="8">项目开工</option>
        								<option value="9">实施方案</option>
        								<option value="10">项目初验</option>
        								<option value="11">项目收验</option>
        								<option value="15">试运行</option>
        								<option value="16">阶段验收</option>
        								<option value="18" selected="selected">项目实施</option>
        							</s:if>
        						</s:if>
        					</select>
        				</td>
        				<td class="t_c">
        					<input readonly="readonly" class="test" id="planBeginDate<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].planBeginDate" type="text" style="width:100%;" value="<s:date name='planBeginDate' format='yyyy-MM-dd'/>"></td>
        				<td class="t_c">
        					<input readonly="readonly" id="planEndDate<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].planEndDate" type="text" style="width:100%;" value="<s:date name='planEndDate' format='yyyy-MM-dd'/>"></td>
        				<td class="t_c">
        					<input readonly="readonly" id="realBeginDate<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].realBeginDate" type="text" style="width:100%;" value="<s:date name='realBeginDate' format='yyyy-MM-dd'/>"></td>
        				<td class="t_c">
        					<input readonly="readonly" id="realEndDate<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].realEndDate" type="text" style="width:100%;" value="<s:date name='realEndDate' format='yyyy-MM-dd'/>"></td>
        				<td class="t_c" colspan="2"><input type="text" style="width:100%;" id="memo<s:property value='#p.index'/>" name="projectPlan[<s:property value='#p.index'/>].memo" value="<s:property value='memo'/>"></td>
        				<td class="t_c" >
        					<a href="javascript:void(0);" onclick="history('<s:property value='projectPlanId'/>');" id="history<s:property value='#p.index'/>" style="float: left;">历史计划</a>
        					<a href="javascript:void(0);" id="deletes<s:property value='#p.index'/>">删除</a>
        				</td>
        			</tr>
        		</s:iterator>
        	</tbody>
        			<input type="submit" id="save" onclick="return checkout();" name="save" value="保存" />
        			<input type="button" id="back" name="back" onclick="location.href='/portal/project/sysinfo/list.action'" value="后退" />
        		
        	</table>
       </s:form>
      </div>
        <!--Table End-->
</div>
</body>
</html>
