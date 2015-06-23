<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String oldDeptId = request.getParameter("oldDeptId");
if(oldDeptId==null){
	oldDeptId = "";
}
String checkRole = request.getParameter("checkRole");
if(checkRole==null){
	checkRole = "";
}
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title></title>

	<script type="text/javascript">
	var s_monomerPlanId = "";
	var s_obj = null;

 	function divPosition(div){
		var top = ($(document).height() - $(div).height())/2;
		var left = ($(document).width() - $(div).width())/2;
		
		$(div).css("left",left+"px");
		$(div).css("top",top+"px");
	}
	
	function divMaskPosition(mask){
		pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		pageHeight = $(document).height();
		$(mask).css("height",pageHeight).css("width",pageWidth);
	}
	
	function MessageBox(divId,maskId,monomerPlanId,obj){
		s_monomerPlanId = monomerPlanId;
		s_obj = obj;
		
		pageWidth = ($.browser.version=="6.0")?$(document).width()-21:$(document).width();
		pageHeight = $(document).height();
		
		var div=$("#"+divId);
		var mask = $("#"+maskId);
		
		$(mask).animate({
				height :pageHeight,
				width :pageWidth,
				opacity :'show'
			},{
				duration :500,
				complete : function() {
					$(mask).css("filter","alpha(opacity=30)");
					divPosition(div);
					$(div).animate({
							opacity :'show'
						},{
							duration :500,
							complete : function() {
								divMaskPosition(mask);
								var offsetHeight = (document.body.clientHeight - $(div).height()) / 2
								if (offsetHeight < 0) offsetHeight = 0;
								ScrollTop = $(div).offset().top	- offsetHeight;
								$(document).scrollTop(ScrollTop);
								
							}
						}
					);
					/**/
				}
			}
		);
	}
	
	function closeMessageBox(divId,maskId){
		$("#"+divId).animate({
			  opacity:'hide'
		},{duration:1000,complete:function(){}});
		
		$("#"+maskId).animate({
			  opacity:'hide'
		},{duration:1000,complete:function(){}});
	}
	
	function closeDiv(){
		closeMessageBox("handle_zone","maskDiv");
		s_monomerPlanId = "";
		s_obj = null;
		$("[name=choice]").attr("checked",false);
		$("#remark").val("");
		$("#red").attr("style","display:none;");
	}

	function checkFunc(){
		if($("[name=choice]:checked").length==0){
			alert("请选择通过或未通过！");
		}else if($("[name=choice]:checked").val()=="0"&&$.trim($("#remark").val())==""){
			alert("请填写不通过的理由！");
		}else{
			var statusText = "";
			if($("[name=choice]:checked").val()=="0"){
	 			statusText = "返回修改";
	 		}else if("<%=checkRole%>"=="1"){
	 			statusText = "已初审";
	 		}else if("<%=checkRole%>"=="2"){
	 			statusText = "已终审";
	 		}else{
	 			statusText = "未审核";
	 		}
			$.ajax({
				url: "/portal/monomerPlan/checkFunc.action",
				type: 'post', 
				dataType: 'json', 
				data:{
					checkRole:'<%=checkRole%>',
					result:$("[name=choice]:checked").val(),
					remark:$("#remark").val(),
					monomerPlanId:s_monomerPlanId,
					random:Math.random()
				},
				error:function(){
					alert("系统连接失败，请稍后再试！");
				},
				success: function(){			
					alert("操作成功！");
					if(s_obj!=null){
						$(s_obj).parents("td").siblings("#checkStatusTd").html(statusText);
						$(s_obj).parents("td").siblings("#checkStatusTd").attr("style","");
						$(s_obj).hide();
						closeDiv();
					}else{
						$("#form").submit();
					}
				}
			});	
		}
	}

	$(function(){
		$("[name=choice]").each(function(){
			$(this).click(function(){
				if($(this).val()=="0"){
					$("#red").attr("style","display:inline;");
				}else{
					$("#red").attr("style","display:none;");
				}
			});
		});
	})
    </script>
</head>

<body>	
	<div id="handle_zone" class="f_window" style="display:none;">
		<h3 class="clearfix mb10">
			<span class="fl">
			<%if("1".equals(checkRole)) {%>
			初审
			<%}else if("2".equals(checkRole)) { %>
			终审
			<%} %>
			</span>
			<div class="fr close" id="handleDivClose" onclick="closeDiv()">关闭窗口</div>
		</h3>
		<div class="con">
	     	
			<table id="choiceZone2" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr height="30">
					<td class="td_1">
						&nbsp;
					</td>
					<td>
					<input type="radio" name="choice" value="1"/>通过&nbsp;&nbsp;
					<input type="radio" name="choice" value="0"/>不通过
					</td>
				</tr>
				<tr height="30">
					<td class="td_1">
						
					</td>
					<td>
						审核意见：<font color="red" id="red" style="display:none;">*</font>
						<br/>
						<textarea rows="3" id="remark" ></textarea>
					</td>
				</tr>
			</table>
			
		</div>
		<div class="button t_c">
			<input id="handleSubmit" type="button" value="确 认" onclick="checkFunc(this);">
			<input id="handleClose" type="button" value="取 消" onclick="closeDiv();">
		</div>
		
	</div>
	<div class="transparent" id="maskDiv" style="display: none;" style="filter:alpha(opacity=30);opacity:0.3;"></div>
</body>
</html>