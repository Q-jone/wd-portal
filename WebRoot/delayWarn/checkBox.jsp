<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title></title>

	<script type="text/javascript">
	var s_pname = "";
	var s_pincident = "";
	var s_cname = "";
	var s_cincident = "";
	var s_starttime = "";
	var s_title = "";
	var s_dept = "";
	var s_taskid = "";
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
	
	function MessageBox(divId,maskId,pname,pincident,cname,cincident,starttime,title,dept,taskid,obj){
		s_pname = pname;
		s_pincident = pincident;
		s_cname = cname;
		s_cincident = cincident;
		s_starttime = starttime;
		s_title = title;
		s_dept = dept;
		s_taskid = taskid;
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
		s_pname = "";
		s_pincident = "";
		s_cname = "";
		s_cincident = "";
		s_starttime = "";
		s_title = "";
		s_dept = "";
		s_taskid = "";
		s_obj = null;
		$("[name=times]").val("7");
	}

	function checkFunc(){
		$.ajax({
			url: "/portal/delayWarn/checkFunc.action",
			type: 'post', 
			dataType: 'json', 
			data:{
				times:$("[name=times]").val(),
				pname:s_pname,
				pincident:s_pincident,
				cname:s_cname,
				cincident:s_cincident,
				starttime:s_starttime,
				title:s_title,
				dept:s_dept,
				taskid:s_taskid,
				random:Math.random()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(){			
				alert("操作成功！");
				if(s_obj!=null){
					closeDiv();
					$("#form").submit();
				}
			}
		});	
	}

    </script>
</head>

<body>	
	<div id="handle_zone" class="f_window" style="display:none;">
		<h3 class="clearfix mb10">
			<span class="fl">
			初审
			</span>
			<div class="fr close" id="handleDivClose" onclick="closeDiv()">关闭窗口</div>
		</h3>
		<div class="con">
	     	
			<table id="choiceZone2" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr height="30">
					<td class="td_1">
						&nbsp;
					</td>
					<td>请选择延时时间：
					<select name="times">
						<option value="7">1周</option>
						<option value="14">2周</option>
						<option value="21">3周</option>
						<option value="28">4周</option>
					</select>
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