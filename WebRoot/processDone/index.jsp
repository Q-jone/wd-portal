<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>已办结流程信息导入控制台</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
	.must{
		color:red;
	}
</style>


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");		
});


function addDatas(ptype){
	var startNum=$("#"+ptype+"_startNum").val();
	var length=$("#"+ptype+"_length").val();		
	$.ajax({
		type : 'post',
		url : 'updateProcessDone.action',
		dataType:'json',
		cache : false,
		data:{
			ptype:ptype,
			funcType:"addDatas",
			startNum:startNum,
			length:	length	
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			if(obj.success){
				alert("操作成功");
			}else{
				alert("操作失败："+obj.msg);
			}
		}
	});
}

function updateLatestDatas(ptype){
	var latestDays=$("#"+ptype+"_latestDays").val();
	$.ajax({
		type : 'post',
		url : 'updateProcessDone.action',
		dataType:'json',
		cache : false,
		data:{
			ptype:ptype,
			funcType:"updateLatestDatas",
			latestDays:latestDays
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			if(obj.success){
				alert(obj.msg);
			}else{
				alert("操作失败");
			}
		}
	});
}

function updateSingleData(ptype){
	var pid=$("#"+ptype+"_pid").val();
	$.ajax({
		type : 'post',
		url : 'updateProcessDone.action',
		dataType:'json',
		cache : false,
		data:{
			ptype:ptype,
			funcType:"updateSingleData",
			pid:pid
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			if(obj.success){
				alert(obj.msg);
			}else{
				alert("操作失败");
			}
		}
	});
}

function showList(){
	window.location.href="/portal/processDone/findYbxByPage.action?status=off";
}
</script>





</head>

<body>
<div class="main">
	<div class="ctrl clearfix">
		<div class="posi fl">
			<ul>
				<li class="fin">已办结流程信息&gt;&gt;控制台</li>
			</ul>
		</div>
	</div>
<div class="mb10">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="3" class="t_r">
				<input type="button" value="列表" onclick="showList()"/> &nbsp;
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd" width="5%" rowspan="3">发文流程</td>				
				<td width="85%">												
					<input type="button" value="批量导入" onclick="addDatas('docSend');"/>参数:startNum:<input id="docSend_startNum" type="text" value="">,length:<input id="docSend_length" type="text" value=""> &nbsp;	
				</td>						
			</tr>
			<tr>		
				<td width="85%">																		
					<input type="button" value="批量更新" onclick="updateLatestDatas('docSend');"/> 参数:latestDays:<input id="docSend_latestDays" type="text" value=""> &nbsp;						
				</td>						
			</tr>
			<tr>			
				<td width="85%">																	
					<input type="button" value="单条更新" onclick="updateSingleData('docSend');"/> 参数:pid:<input id="docSend_pid" type="text" value=""> &nbsp;		
				</td>						
			</tr>
			<tr>
				<td class="t_r lableTd" width="5%" rowspan="3">工作联系单</td>				
				<td width="85%">												
					<input type="button" value="批量导入" onclick="addDatas('jobContact','addDatas');"/>参数:startNum:<input id="jobContact_startNum" type="text" value="">,length:<input id="jobContact_length" type="text" value=""> &nbsp;	
				</td>						
			</tr>
			<tr>		
				<td width="85%">																		
					<input type="button" value="批量更新" onclick="updateLatestDatas('jobContact');"/> 参数:latestDays:<input id="jobContact_latestDays" type="text" value=""> &nbsp;						
				</td>						
			</tr>
			<tr>			
				<td width="85%">																	
					<input type="button" value="单条更新" onclick="updateSingleData('jobContact');"/> 参数:pid:<input id="jobContact_pid" type="text" value=""> &nbsp;		
				</td>						
			</tr>
														
		</tbody>
	</table>
</div>
<!--Table End--></div>
</body>
</html>
