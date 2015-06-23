<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		
	</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
	 <script src="../js/jquery-1.7.1.js"></script>
	<script src="../js/jquery.form.js"></script>     
	<script src="../js/jquery.formalize.js"></script>   	
	<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
	<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
	<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>	
	<style>
	.deptTreeZone{display:none;}
	.redMark{color:red;display:inline;}
	.r_bor{border-right:#000 1px solid}
	        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }	
	</style>
	
<script>

$(document).ready(function(){
	$("#fileType").change(function(){
		var fileType = $(this).val();
		loadSubFileType(fileType);
	}).val($('#_fileType_').val());
	
	$("#fileTypeSub").val($('#_fileTypeSub_').val());
	
	checkboxInit('sendOutCheck',$('#sendOutW').val());
	
	$("#selectMainUnit").click(function(){
		selDept();
	});
	$("#selectLineUnit").click(function(){
		selLine();
	});
	$("#clearMainUnit").click(function(){
		clearMainUnit();
	});
	$("#clearLineUnit").click(function(){
		clearLineUnit();
	});	
	
	$('#validDate').datepicker({
		inline: true,  
        changeYear: true,  
        changeMonth: true, 
        showButtonPanel:true,     
        closeText:'清除',   
        currentText:'vd'//仅作为“清除”按钮的判断条件  
	});
	$('#pubDate').datepicker({
		inline: true,  
        changeYear: true,  
        changeMonth: true, 
        showButtonPanel:true,     
        closeText:'清除',   
        currentText:'pd'//仅作为“清除”按钮的判断条件  
	});
	$('#applyDate').datepicker({
		inline: true,  
        changeYear: true,  
        changeMonth: true, 
        showButtonPanel:true,     
        closeText:'清除',   
        currentText:'ad'//仅作为“清除”按钮的判断条件  
	});		
	$(".ui-datepicker-close").live("click", function (){
        if($(this).parent("div").children("button:eq(0)").text()=="vd") $("#validDate").val("");
        if($(this).parent("div").children("button:eq(0)").text()=="pd") $("#pubDate").val("");
        if($(this).parent("div").children("button:eq(0)").text()=="ad") $("#applyDate").val("");
    });
});
function loadSubFileType(pid){
	$("#sub_filetype").empty();
	$('.fileTypeSub').remove();
	if(!pid){
		return;
	}
	$.ajax({
		type : 'post',
		url : 'subFileTypeList.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			pid : pid,
			desc : $('#isUrgent').val() == '1' ? 'URGENT' : ''
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(data){
			var list = $(data);
			if(list.length > 0){
				var html = '— ';
				html += '<select name="fileTypeSub" class="validate[required]" id="fileTypeSub"><option value="">--请选择文件分类--</option>';
				list.each(function(){
					html += '<option value="'+this.id+'">' + this.name + '</option>';
				});
				html += '</select>';
				$("#sub_filetype").html(html);				
			}
		}
	});
}
function selectDept(zoneId){
	var url = "../operation/docSend/deptTree.jsp?zoneId="+zoneId+"&random="+Math.random();
	window.open(url);
}
function submitForm(){
		var checkResult = checkForm();
		if(checkResult){
			alert(checkResult);
			return false;
		}
		var flowCode;
		if($('#isUrgent').val()=='1'){
			flowCode = 'FLOW_OP_DOCSEND_UGT';
		}else if($('#fileTypeSub').val()=='5'){
			flowCode = 'FLOW_OP_DOCSEND_IMP';
		}else{
			flowCode = 'FLOW_OP_DOCSEND';
		}
 		if (confirm('确定提交此发文登记?')) {
 			$('#sendOutW').val(getCheckboxStrNew('sendOutCheck'));
 			$('#handlerName').val($("#handlerId option:selected").text());
 			$('#flowCode').val(flowCode);
			$("#form").submit();
		}
}
function checkForm(){
	if($("#fileType").val()==""){
		$("#fileType").focus();
		return("请选择文件分类！");
	}
	if($("#fileTypeSub").length > 0 && $("#fileTypeSub").val()==""){
		$("#fileTypeSub").focus();
		return("请选择二级文件分类！");
	}
	if($("#docTitle").val()==""){
		$("#docTitle").focus();
		return("请填写文件名称！");
	}
	if($("#sendMainW").val()==""){
		$("#sendMainW").focus();
		return("请选择发放范围！");
	}	
	if($("#remark").val().length>2000){
		$("#remark").focus();
		return("备注只限输入2000字！");
	}		
	if($("#validDate").val()==""){
		$("#validDate").focus();
		return("请选择有效日期！");
	}	
	if($("#pubDate").val()==""){
		$("#pubDate").focus();
		return("请选择发布日期！");
	}	
	if($("#applyDate").val()==""){
		$("#applyDate").focus();
		return("请选择起草日期！");
	}		
	if($("#contentAtt").val()==""||$("#contentAttCnt").val()=="0"){
		return("请上传文件附件！");
	}
	if($("#handlerId").val()==""){
		$("#handlerId").focus();
		return("请选择审核人！");
	}	
}
function terminate(){
	if (confirm('确定中止此发文登记?')) {
		$("#handle_zone_loading").show();
	   	$.ajax({
	   		type: 'POST',
	   		url: 'dealFlow.action',
	   		dataType:'json',
	   		data: "threadId="+$('#threadId').val()+"&choice=3&random="+Math.random(),
	   		cache : false,
	   		error:function(){alert('系统连接失败，请稍后再试！')},
	   		success: function(obj){		
				alert("提交成功!");
				window.location.href = "view.action?id="+$('#id').val();   			
	   		}	  
	   	});	
	}
}
function getCheckboxStrNew(objName){   
	var returnStr = '';  
    $("[name='"+objName+"']:checked").each(function(){   
    	returnStr += ',' + $(this).val();    		
    });     	
  	if(returnStr.length > 0){
  		returnStr = returnStr.substring(1);
  	}
    return returnStr;  
}  
function checkboxInit(objName,valueListStr){   
    $("[name='"+objName+"']").each(function(){     
        if(valueListStr.indexOf($(this).val()) > -1)  
        {    
            this.checked = true;  
        }  
    })  
}   
function selDept(){
    window.deptIdField = document.getElementById("sendMainId");
	window.deptNameField = document.getElementById("sendMainW");
    window.showModalDialog('/portal/opDocSend/selDept.action',window,'dialogWidth:1000px;dialogHeight:500px;scroll:auto');
}
function selLine(){
    window.deptIdField = document.getElementById("sendLineId");
	window.deptNameField = document.getElementById("sendLineW");
    window.showModalDialog('/portal/opDocSend/selLine.action',window,'dialogWidth:1000px;dialogHeight:500px;scroll:auto');
}
function clearMainUnit(){
	if(confirm("是否清除发放范围？")){
		$("#sendMainId").val("");
		$("#sendMainW").val("");
	}
	return false;
}
function clearLineUnit(){
	if(confirm("是否清除涉及线路/OCC/专业单位？")){
		$("#sendLineId").val("");
		$("#sendLineW").val("");
	}
	return false;
}
</script>
</head>
<body class="Flow" style="background:none">
<div id="bt"  class=" transparent" style="display:none;"></div>
<s:form action="startFlow" id="form" method="post" namespace="/opDocSend" theme="simple">
	<!-- 待办项时增加此变量 对应  DeptContactOperateVo。taskId = todoid-->
	<input type="hidden" id="id" name="id" value="<s:property value='#request.opDocSend.id'/>"/>
	<input type="hidden" id="flowCode" name="flowCode" value=""/>
	<input type="hidden" id="flowGroup" name="flowGroup" value="<s:property value='#request.opDocSend.flowGroup'/>"/>
	<input type="hidden" id="threadId" name="threadId" value="<s:property value='#request.thread.id'/>"/>
	<input type="hidden" id="sendMainId" name="sendMainId" value="<s:property value='#request.opDocSend.sendMainId'/>"/>
	<input type="hidden" id="sendLineId" name="sendLineId" value="<s:property value='#request.opDocSend.sendLineId'/>"/>
	<input type="hidden" id="sendOutW" name="sendOutW" value="<s:property value='#request.opDocSend.sendOutW'/>"/>
	<input type="hidden" id="isUrgent" name="isUrgent" value="<s:property value='#request.opDocSend.isUrgent'/>"/>
	<div class=" transparent" id="maskDiv" style="display:none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>

	<!-- <div class="f_bg_fw"> -->
	<div class="f_bg_fw" style="background:none">
        <div class="gray_bg" style="text-align:center;margin:0 auto;">
        	<div class="gray_bg2">
            	<div class="w_bg">
                	<div class="Bottom">
                    	<div class="Top_fw">
                        	<h1 class="t_c" id="title1">上海申通地铁集团有限公司<br>运营发文稿纸</h1>
							<div class="mb10">

								<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">
									<thead>
									<th colspan="4" class="lableTd6" style="padding-left:10px;padding-right:0;">
									<h5><b style="display:inline">运营发文:</b></h5>
									</th>
									</thead>
								  	<tbody>
								  	<tr class="content7">
	                                    <td class="lableTd t_c" style="width:15%"><font class="redMark">*&nbsp;</font>文件分类</td>
	                            	    <td class="pl18" colspan="3">
	                            	    	<span style="width: 10%; margin-right: 2px;display:inline">
		                            	    <input type="hidden" id="fileType_value" value="<s:property value='#request.opDocSend.fileType'/>"/>
							    			<select name="fileType" class="validate[required]" id="fileType">
							    				<option value="">--请选择文件分类--</option>
												<s:iterator value="#request.fileTypes">
													<option value="<s:property value='id'/>"><s:property value='name'/></option>
												</s:iterator>							    				
											</select>
											</span>
											<span style="width: 55%; margin-right: 2px; display:inline" id="sub_filetype">
												<s:if test="#request.fileSubTypes!=null">
								    			<select name="fileTypeSub" class="validate[required]" id="fileTypeSub">
								    				<option value="">--请选择文件分类--</option>
													<s:iterator value="#request.fileSubTypes">
														<option value="<s:property value='id'/>"><s:property value='name'/></option>
													</s:iterator>							    				
												</select>		
												</s:if>									
											</span>
		                            	</td>
	                          	    </tr>
								  	<tr class="content7">
	                                    <td class="lableTd t_c" style="width:15%">文件编号</td>
	                            	    <td class="pl18" colspan="3">
	                            	    	<input type="hidden" id="fileCode" name="fileCode" value="<s:property value='#request.opDocSend.fileCode'/>"/>
	                            	    	<span style="width: 10%; margin-right: 2px;display:inline">
		                            	    	<s:property value='#request.opDocSend.fileCode'/>
											</span>
		                            	</td>
	                          	    </tr>	                            	    
	                          	    </tbody>
	                          	  </table>
	                          	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2">  
	                          	    <tr class="content6">
	                                    <td class="lableTd t_c" style="width:15%"><font class="redMark">*&nbsp;</font>文件名称</td>
	                                    <td colspan="3" class="content6">
	                                    <input name="docTitle" type="text" class="input_large validate[required]" id="docTitle" style="width:100%;" maxlength="200" value="<s:property value='#request.opDocSend.docTitle'/>"/></td>
                                  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c" style="line-height:120px;"><font class="redMark">*&nbsp;</font>发放范围</td>
										<td class="pl18" colspan="3">
											<div style="width: 90%; margin-right: 2px; float: left;">
											<textarea rows="6" id="sendMainW" name="sendMainW" class="validate[required]" readonly="readonly" style="width:98%;overflow:visible"><s:property value='#request.opDocSend.sendMainW'/></textarea>
											</div>
											<div style="width: 5%;padding-top: 70px; float: left;">
											<input type="button" id="selectMainUnit" value="选择"/>
											<input id="clearMainUnit" type="button" value="清除"/>
											</div>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c">外发单位</td>
										<td class="pl18" colspan="3">
											<input type="checkbox" name="sendOutCheck" value="轨道总队"/>&nbsp;轨交总队
											<input type="checkbox" name="sendOutCheck" value="轨道办"/>&nbsp;轨道办
											<input type="checkbox" name="sendOutCheck" value="轨道消防支队"/>&nbsp;轨道消防支队
										</td>
								  	</tr>										  	
								  	<tr class="content6">
										<td class="lableTd t_c" style="vertical-align:middle">文件内容<br>涉及线路/<br>OCC/专业<br>单位参考</td>
										<td class="pl18" colspan="3">
											<div style="width: 90%; margin-right: 2px; float: left;">
											<textarea rows="6" id="sendLineW" name="sendLineW" readonly="readonly" style="width:98%;overflow:visible"><s:property value='#request.opDocSend.sendLineW'/></textarea>
											</div>
											<div style="width: 5%;padding-top: 70px; float: left;">
											<input type="button" id="selectLineUnit" value="选择"/>
											<input id="clearLineUnit" type="button" value="清除"/>
											</div>
										</td>
								  	</tr>
								  	<tr class="content6">
										<td class="lableTd t_c" style="vertical-align:middle">备注</td>
										<td class="pl18" colspan="3">
											<div style="width: 90%; margin-right: 2px; float: left;">
											<textarea rows="4" id="remark" name="remark" style="width:98%;overflow:visible"><s:property value='#request.opDocSend.remark'/></textarea>
											</div>
										</td>
								  	</tr>			
								  	<tr class="content6">
										<td class="lableTd t_c">起草人</td>
										<td class="pl18" style="width:35%;">
											<s:property value='#request.startThread.userName'/>
										</td>
										<td class="lableTd t_c" style="width:15%"><font class="redMark">*&nbsp;</font>起草日期</td>
										<td class="pl18">
											<input name="applyDate" type="text" class="input_large date" id="applyDate" value="<s:property value='#request.opDocSend.applyDate'/>" style="width:120px;" readonly="readonly"/></td>
										</td>										
								  	</tr>		
								  	<tr class="content6">
										<td class="lableTd t_c">审批意见</td>
										<td class="pl18" colspan="3">
											<table width="100%" border="0">
											<tbody>
											<s:iterator value="#request.approveThreads" id="data" status="s">
												<tr>
												<td style="border-bottom: 0px;">
														<div align="left">
														<s:property value="#data.contents"/><br>
														<div>
														<span style="float:right">
														<s:property value="#data.userName"/>&nbsp;<s:property value="#data.endTime"/>
														</span>
														<s:if test="#data.attachGroup!=null&&#data.attachCnt!=0">
														<a target="_blank" style="float:right" href="/portal/attachOld/loadFileOldList.action?fileGroupId=<s:property value='#data.attachGroup'/>&procType=view&targetType=frame&type=1&attachMemo=OP_DOCSEND">
														<img src="../operation/ui/images/fj.gif" style="cursor:hand" alt="附件">
														</a>
														</s:if>														
														</div>
														</div>
												</td>
												</tr>
											</s:iterator>
											</tbody>
											</table>
										</td>				
								  	</tr>								  					
								  	<tr class="content6">
										<td class="lableTd t_c">套头人</td>
										<td class="pl18" style="width:35%;">
											<s:property value='#request.endThread.userName'/>
										</td>
										<td class="lableTd t_c" style="width:15%">套头日期</td>
										<td class="pl18">
											<s:property value='#request.endThread.endTime'/>
										</td>										
								  	</tr>	
								  	<tr class="content6">
                                        <td class="lableTd t_c" style="width:15%"><font class="redMark">*&nbsp;</font>发布日期</td>
                                        <td class="pl18">
                                            <input name="pubDate" type="text" class="input_large date" id="pubDate" value="<s:property value="#request.opDocSend.pubDate"/>" style="width:120px;" readonly="readonly"/></td>
                                        </td>
										<td class="lableTd t_c"><font class="redMark">*&nbsp;</font>有效日期</td>
										<td class="pl18" style="width:35%;">
											<input name="validDate" type="text" class="input_large date" id="validDate" value="<s:property value="#request.opDocSend.validDate"/>" style="width:120px;" readonly="readonly"/></td>
										</td>

								  	</tr>										  							  												  					  								  	
								  	<tr class="content7">
								  		<td class="lableTd t_c"><font class="redMark">*&nbsp;</font>文件附件</td>
								  		<td colspan="3">
								  			<input type="hidden" name="contentAtt" id='contentAtt' value="<s:property value="#request.opDocSend.contentAtt"/>"/>
								  			<input type="hidden" id='contentAttCnt' value=""/>
<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attachOld/loadFileOldList.action?fileGroup=contentAtt&fileGroupName=attachGroupOp&fileGroupId=<s:property value='#request.opDocSend.contentAtt'/>&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1&attachMemo=OP_DOCSEND&fileCntObjId=contentAttCnt"></iframe>
								  		</td>
								  	</tr>
                          	  	</table>
                        	</div>
	                        <%-- --%>
	                       
							<div class="mb10 t_c">
								<div id="leaderTr" style="display:inline">
								<s:property value='#request.opDocSend.isUrgent==0?"部门领导":"应急发文审核人"'/>部门领导：								
								<input type="hidden" name="handlerName" id='handlerName' value=""/>
								<select name="handlerId" id="handlerId" class="validate[required] input_small">
									<option value="">---请选择---</option>
									<s:iterator value="#request.leaders" id="leaders">
										<option value="<s:property value='#leaders[0]'/>"><s:property value='#leaders[1]'/></option>
									</s:iterator>										
								</select>
								<font class="redMark">*&nbsp;</font>&nbsp;
								</div>							
								<input type="button" id="formSubmit" value="重新申请" onclick="submitForm();"/>&nbsp;
								<input type="button" id="closeBtn" value="中止申请" onclick="terminate()"/>&nbsp;
								<div id="handle_zone_loading" style="width:100%;align:center;line-height:20px;display:none;" class="">
							      <p style="width:auto;display:inline;"><img src="../operation/ui/images/loading.gif" style="display:inline;"/>
							      <b style="color:green;display:inline;">&nbsp;正在提交</b></p>
							    </div>
							</div>
							<input type="hidden" id="_fileType_" value="<s:property value='#request.opDocSend.fileType'/>"/>
							<input type="hidden" id="_fileTypeSub_" value="<s:property value='#request.opDocSend.fileTypeSub'/>"/>
							<div class="footer"></div>
						</div>
                    </div>
                </div>
            </div>
        </div>
 	</div>
 	
 	
 	<div class="transparent" id="maskDiv" style="display: none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>

	</s:form>
	
	<div class="transparent" id="maskDiv1" style="display: none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>

</body>
</html>