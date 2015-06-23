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
<meta http-equiv="x-ua-compatible" content="IE=8">
<title>规则详情新增</title>
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
		
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/jquery-ui-multiselect-widget-master/jquery.multiselect.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/jquery-ui-multiselect-widget-master/demos/assets/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/jquery-ui-multiselect-widget-master/demos/assets/prettify.css" />
		<script type="text/javascript" src="<%=basePath %>/js/jquery-ui-multiselect-widget-master/demos/assets/prettify.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/jquery-ui-multiselect-widget-master/src/jquery.multiselect.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
        button span {color:black;font-weight:normal}
		</style>	
		
		<script type="text/javascript">
	
		
         $(function(){ 
			$(".odd tr:odd").css("background","#fafafa");	

			findAllPapers("paperName");
			findAllPapers("s_prePaperId");
			findAllPapers("s_nextPaperId");
			findRuleVersions();
			if("<%=id%>"!=""){
				$("[name=ifNode]").val($("#h_ifNode").val());
				$("[name=ifMilestone]").val($("#h_ifMilestone").val());
				$("#addButton").val("保  存");
				$("#titleName").html($("#titleName").html().replace("新增","修改"));
				$(document).attr('title',$(document).attr('title').replace("新增","修改"));
			}else{
				$("[name=miniDays]").val("5");
			}

		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function findAllPapers(objName){
			$.ajax({
				url: "/portal/linePlan/findAllPapers.action",
				type: 'post', 
				dataType: 'json', 
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(obj){			
	              if(obj!=null&&obj.length>0){
					  var addHtml = "";
					  if(objName=="paperName"){
						  addHtml = "<option value=''>---请选择---</option>";
					  }
					  for(var i=0;i<obj.length;i++){
						  if(objName=="paperName"){
						  	  addHtml += "<option value='"+obj[i][1]+"' paperId='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
						  }else{
							  addHtml += "<option value='"+obj[i][0]+"'>"+obj[i][1]+"</option>";
						  }
					  }
					  if(objName=="paperName"){
						  $("[name="+objName+"]").html(addHtml);
						  if("<%=id%>"!=""){
								$("[name="+objName+"]").val($("#h_"+objName).val());
							}
					  }else{
						    $("#"+objName).html(addHtml);
						    if("<%=id%>"!=""){
								var ovalue = $("[name="+objName.replace("s_","")+"]").val();
								var ovalue_split = ovalue.split(",");
								if(ovalue_split!=null&&ovalue_split.length>0){
									for(var i=0;i<ovalue_split.length;i++){
										$("#"+objName).children("option").each(function(){
											if($(this).val()==ovalue_split[i]){
												$(this).attr("selected","selected");
											}
										});
									}
								}
						    }
					  		$("#"+objName).multiselect({
					  			selectedList: 35
							});
					  }
	              }
				}
			});	
		}

		function selectPaper(obj,objId){
			var option = $(obj).children("option:selected");
			$("[name="+objId+"]").val(option.attr("paperId"));
			findDefaultDays();
		}

		function check(){
			var orderNum = $.trim($("[name=orderNum]").val());
			if(orderNum==""){
				alert("请输入顺序号！");
				$("[name=orderNum]").focus();
				return false;
			}else if(!isUnsignedInteger(orderNum)){
				alert("顺序号请输入正整数！");
				$("[name=orderNum]").focus();
				return false;
			}

			var paperName = $.trim($("[name=paperName]").val());
			if(paperName==""){
				alert("请选择证件名称！");
				$("[name=paperName]").focus();
				return false;
			}
			
			

			var miniDays = $.trim($("[name=miniDays]").val());
			if(miniDays==""){
				alert("请输入最小办理天数！");
				$("[name=miniDays]").focus();
				return false;
			}else if(!isUnsignedInteger(miniDays)){
				alert("最小办理天数请输入正整数！");
				$("[name=miniDays]").focus();
				return false;
			}

			var planDays = $.trim($("[name=planDays]").val());
			if(planDays==""){
				alert("请输入常规办理天数！");
				$("[name=planDays]").focus();
				return false;
			}else if(!isUnsignedInteger(planDays)){
				alert("常规办理天数请输入正整数！");
				$("[name=planDays]").focus();
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
					url:"/portal/ruleDetail/save.action",
				    success:function(data){
						if(data!=null&&data.if_success=="yes"){
							alert("提交成功！");
							if("<%=id%>"!=""){
								window.close();
							}else{
								window.location.href = "toAdd.action?oldDeptId=<%=oldDeptId%>&ruleVersionId="+$("#h_ruleVersionId").val();
							}
						}else{
							alert("提交失败，请联系管理员！");
						}
						return false;
				    }
				};
				$("[name=prePaperName]").val($("#s_prePaperId").siblings("button").children("span:eq(1)").html().replace("---请选择---",""));
				$("[name=prePaperId]").val($("#s_prePaperId").val());
				$("[name=nextPaperName]").val($("#s_nextPaperId").siblings("button").children("span:eq(1)").html().replace("---请选择---",""));
				$("[name=nextPaperId]").val($("#s_nextPaperId").val());
				$("#form").ajaxSubmit(formOptions);  
			}
		}

		function findRuleVersions(){
			$.ajax({
				url: "/portal/ruleVersion/findRuleVersions.action",
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
					  $("[name=ruleVersionId]").html(addHtml);
						$("[name=ruleVersionId]").val($("#h_ruleVersionId").val());
	              }
				}
			});	
		}

		function findDefaultDays(){
			if("<%=id%>"==""){
				$.ajax({
					url: "/portal/ruleDetail/findDefaultDays.action",
					type: 'post', 
					data:{
						paperId:$("[name=paperId]").val(),
						random:Math.random()
					},
					dataType: 'json', 
					error:function(){
						alert("系统连接失败，请稍后再试！");
					},
					success: function(obj){			
		              if(obj!=null&&obj.length>0){
						  $("[name=planDays]").val(obj);
		              }else{
		            	  $("[name=planDays]").val("");
		              }
					}
				});	
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
            		<li class="fin" id="titleName">规则详情新增</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45">
        <form action="<%=basePath %>/ruleDetail/save.action" id="form" name="form" method="post">
          <input type="hidden" name="id" value="<%=id %>">
	        <input type="hidden" name="createTime" value="<s:property value='ruleDetail.createTime'/>">
	        <input type="hidden" name="createUser" value="<s:property value='ruleDetail.createUser'/>">
	        <input type="hidden" name="removed" value="0">
	        <input type="hidden" name="oldDeptId" value="<%=oldDeptId %>">
          <table width="100%"  class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td style="width:20%;" class="t_r lableTd">顺序号：</td>
                                <td style="width:30%;">
                                <input type="text" name="orderNum" class="input_large" maxlength="50" value="<s:property value='ruleDetail.orderNum'/>"/>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                <td style="width:20%;" class="t_r lableTd">证件名称：</td>
                                <td style="width:30%;">
                                <select name="paperName" onchange="selectPaper(this,'paperId');"></select>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                               <input type="hidden" name="paperId" value="<s:property value='ruleDetail.paperId'/>">
                               <input type="hidden" id="h_paperName" value="<s:property value='ruleDetail.paperName'/>">
                                </td>
                                </tr>
								<tr>
                                <td style="width:20%;" class="t_r lableTd">前序证件名：</td>
                                <td style="width:30%;">
                                <select id="s_prePaperId" multiple="multiple"></select>
                               <input type="hidden" name="prePaperId" value="<s:property value='ruleDetail.prePaperId'/>">
                               <input type="hidden" name="prePaperName" value="<s:property value='ruleDetail.prePaperName'/>">
                                </td>
                                <td style="width:20%;" class="t_r lableTd">后续证件名：</td>
                                <td style="width:30%;">
                                <select id="s_nextPaperId" multiple="multiple"></select>
                               <input type="hidden" name="nextPaperId" value="<s:property value='ruleDetail.nextPaperId'/>">
                               <input type="hidden" name="nextPaperName" value="<s:property value='ruleDetail.nextPaperName'/>">
                                </td>
                                </tr>
								
								<tr>
								<%--
                                <td style="width:20%;" class="t_r lableTd">有效天数：</td>
                                <td style="width:30%;">
                                <input type="text" name="validDays" class="input_xxlarge" maxlength="50" value="<s:property value='ruleDetail.validDays'/>"/>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                 --%>
                                <td style="width:20%;" class="t_r lableTd">最小办理天数：</td>
                                <td style="width:30%;">
                                <input type="text" name="miniDays" class="input_large" maxlength="50" value="<s:property value='ruleDetail.miniDays'/>"/>
                                <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                <td  class="t_r lableTd">常规办理天数：</td>
                                <td>
                                <input type="text" name="planDays" class="input_large" maxlength="50" value="<s:property value='ruleDetail.planDays'/>"/>
                               <span style="color:red;display:inline;">&nbsp;*</span>
                                </td>
                                </tr>
                                <tr style="display:none;">
                                
                                <td class="t_r lableTd" >规则版本名称：</td>
                                <td>
                                <select name="ruleVersionId" style="display:none;"></select>
                                <input type="hidden" id="h_ruleVersionId" value="<s:property value='ruleDetail.ruleVersionId'/>">
                                </td>
                                </tr>
                                <tr>
                                <td class="t_r lableTd">管理层关注：</td>
                                <td>
                                <select name="ifNode">
                                	<option value="">---请选择---</option>
                                	<option value="1">是</option>
                                	<option value="0">否</option>
                                </select>
                                <input type="hidden" id="h_ifNode" value="<s:property value='ruleDetail.ifNode'/>">
                                </td>
                                <td class="t_r lableTd">决策层关注：</td>
                                <td>
                                <select name="ifMilestone">
                                	<option value="">---请选择---</option>
                                	<option value="1">是</option>
                                	<option value="0">否</option>
                                </select>
                                <input type="hidden" id="h_ifMilestone" value="<s:property value='ruleDetail.ifMilestone'/>">
                                </td>
                                </tr>
                                
                                
                                <tr>
                                <td class="t_r lableTd">备注：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" name="remark"><s:property value='ruleDetail.remark'/></textarea>
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
