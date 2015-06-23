<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>各板块年度培训<s:if test="#parameters.style[0] == 'plan'">计划</s:if><s:else>结果</s:else>录入</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />

<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/html5.js"></script>     
<script src="../js/jquery.formalize.js"></script>

</head>
<style>
.lableTd{
text-align:center;
vertical-align:middle;
}
.table_2 td{
	border: 1px solid #e4e4e4;
}
</style>
<script>
var newRowHtml = null;
var thisYear = '<s:property value="#request.thisYear" />';
$(function (){
	$('#add').attr('href','javascript:void(0)').click(add);
	$('.remove').attr('href','javascript:void(0)').click(remove);
	newRowHtml = $('#dataNew').html();
	$("#selYear option[value='"+thisYear+"']").attr("selected",true);
});
function save(){
	var i = 0;
	var p = '';
	var map = {};
	var name = '';
	var duplicate = false;	
	$('.dataRow').each(function(){
		var tr = $(this);
		if($("td:eq(1) select",tr).length == 0){
			name = $("td:eq(1)",tr).text();
		}else{
			name = $("td:eq(1) select option:selected",tr).text();
		}
		if(map[name]){
			duplicate = true;
			return false;
		}
		map[name] = name;		
		if($("input[name='isChanged']",tr).val() == '1'){
			$('input',tr).each(function(){	
				p+='datas['+i+'].'+$(this).attr('name')+'='+$(this).val()+'&';
			});
			$('select',tr).each(function(){	
				if($(this).attr('name') == 'type'){
					p+='datas['+i+'].name='+$('option:selected',$(this)).text()+'&';
				}
				p+='datas['+i+'].'+$(this).attr('name')+'='+$(this).val()+'&';
			});		
			i++;			
		}
	});
	if(duplicate){
		alert('您添加了重复的类别，请删除重复类别后再提交！');
		return false;
	}	
	p += 'num='+i;	
	p += '&thisYear='+thisYear;
	p += '&deadline='+$('#deadline').val();
	$.ajax({
		type: 'POST',
		url: '/portal/train/save.action?random='+Math.random(),
		data: p,
		dataType:'json',
		cache : false,
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){			
			alert('保存成功！');
			window.location.reload();
		}	  
	});	
}
function checkNum(obj){
	$("input[name='isChanged']",$(obj).closest('tr')).val('1');
	
	var r = /^\d+$/;
	if(!r.test(obj.value)){
		alert('请输入一个整数！');
		obj.value = 0;
		obj.focus();
	}
}
function add(){
	var newTr = $('<tr class="disable dataRow"></tr>').append(newRowHtml);
	$('.remove',newTr).click(remove);
	$('#table').append(newTr);
}
function remove(){
	if(confirm('您确定要删除该条数据吗！')){
		var tr = $(this).closest('tr');
		var id = $("input[name='id']",tr);
		if(id.length > 0){
			doDel($(id).val());
		}		
		tr.remove();		
	}
}
function doDel(id){
	 $.ajax({
	        type: "post",
	        url: "/portal/train/delMain.action",
	        dataType: "json",
	        data:"id="+id+"&random="+Math.random(),
	        success: function (data) {
	        	
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	                alert("删除失败");
	        }
	});	 
}
function changeYear(obj){
	window.location.href='/portal/train/list.action?style=<s:property value="#parameters.style[0]" />&year='+obj.value;
}
</script>
<body class="Flow">
	<div class="f_bg">
      <!--Panel_6--><!--Panel_6 End-->	
  <div class="logo_1"></div>
        <div class="gray_bg">
        	<div class="gray_bg2" style='background: url("../images/flow_p4.png") no-repeat scroll -787px bottom rgba(0, 0, 0, 0);'>
        	<form>
            	<div class="w_bg">
                	<div class="Bottom">
                    	<div class="Top">
                        	<h1 class="t_c">上海申通地铁集团有限公司<br>培训<s:if test="#parameters.style[0] == 'plan'">计划</s:if><s:else>结果</s:else>录入
                       	  </h1>
                            <div class="mb10">
                           	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2" id="table">
                           	 	<tr class="disable">
        <td class="lableTd" colspan="7" style="text-align:center">
        				<span class="fl">
                        <select id="selYear" onchange="changeYear(this)">
                     		<option value="2014">2014</option>
                     		<option value="2015">2015</option>
                     		<option value="2016">2016</option>
                     		<option value="2017">2017</option>
                     		<option value="2018">2018</option>
                     	</select>
                     	</span>
        	各板块年度培训<s:if test="#parameters.style[0] == 'plan'">计划</s:if><s:else>结果</s:else>录入<span class="fr pt5"><a href="#" id="add"><img src="../css/default/images/+.png" width="15" height="15"></a></span></td>
                      		</tr>
					<tr class="disable">
						<td class="lableTd" colspan="7" style="text-align:center">
						<span class="fl">
						所有统计截止日期到：<input type="text" id="deadline" style="width:140px;" value="<s:property value="#request.profile.deadline" />" maxlength="200"/>
						</span>
						</td>
                    </tr>                      		
					<tr class="disable">
        				<td class="lableTd" rowspan="2" style="width:60px;">年度</td>
        				<td class="lableTd" rowspan="2" style="width:150px;">类别</td>
        				<td class="lableTd" colspan="2">计划</td>
        				<td class="lableTd" colspan="2">完成人次</td>
        				<td class="lableTd" rowspan="2">操作</td>
					</tr>
					<tr class="disable">
        				<td class="lableTd" style="width:80px;">管理类</td>
        				<td class="lableTd" style="width:80px;">生产类</td>
        				<td class="lableTd" style="width:80px;">管理类</td>
        				<td class="lableTd" style="width:80px;">生产类</td>
					</tr>
					<s:iterator value="#request.mains" id="data" status="s">					       
                     <tr class="disable dataRow" >
                     	<td class="lableTd"><input type="hidden" name="id" value="<s:property value="#data.id" />"/>
                     	<input type="hidden" name="isChanged" value="0"/>
                     	<s:property value="#data.year" />
                     	</td>
                     	<td class="lableTd"><s:property value="#data.name" /></td>
                     	<s:if test="#parameters.style[0] == 'plan'">
	                     	<td class="lableTd"><input type="text" name="managePlan" style="width:70px;" onblur="checkNum(this)" value="<s:property value="#data.managePlan" />" maxlength="9"/></td>
	                     	<td class="lableTd"><input type="text" name="prodPlan" style="width:70px;" onblur="checkNum(this)" value="<s:property value="#data.prodPlan" />" maxlength="9"/></td>
	                     	<td class="lableTd"><s:property value="#data.manageResult" /></td>
	                     	<td class="lableTd"><s:property value="#data.prodResult" /></td>
                     	</s:if>
                     	<s:else>
	                     	<td class="lableTd"><s:property value="#data.managePlan" /></td>
	                     	<td class="lableTd"><s:property value="#data.prodPlan" /></td>
	                     	<td class="lableTd"><input type="text" name="manageResult" style="width:70px;" onblur="checkNum(this)" value="<s:property value="#data.manageResult" />" maxlength="9"/></td>
	                     	<td class="lableTd"><input type="text" name="prodResult" style="width:70px;" onblur="checkNum(this)" value="<s:property value="#data.prodResult" />" maxlength="9"/></td>
                     	</s:else>
                     	<td class="lableTd" style="width:140px;">
                     		<s:if test="#data.type == 2"><a href="/portal/train/listDept.action?mainId=<s:property value="#data.id" />&style=<s:property value="#parameters.style[0]" />" target="_blank">分单位录入</a></s:if>
                     		<s:if test="#data.type == 3 && #parameters.style[0] == 'result'">
                     			<a style="display:inline" href="/portal/train/listMonth.action?mainId=<s:property value="#data.id" />" target="_blank">月度结果</a>&nbsp;
                     			<a style="display:inline" href="/portal/train/listLevel.action?mainId=<s:property value="#data.id" />" target="_blank">技能鉴定</a>
                     		</s:if>
                     	</td>
					</tr>
					</s:iterator>
                    <tr class="disable" id="dataNew" style="display:none;">
                     	<td class="lableTd">
                     	<input type="hidden" name="isChanged" value="1"/>
                     	<input type="hidden" name="year" value="<s:property value="#request.thisYear" />"/>
							<s:property value="#request.thisYear" />
                     	</td>
                     	<td class="lableTd">
                     	<select name="type">
                     		<option value="1">上级党校及在线学习</option>
                     		<option value="2">各直属单位</option>
                     		<option value="3">培训中心</option>
                     	</select></td>
                     	<td class="lableTd"><input type="text" name="managePlan" style="width:70px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="prodPlan" style="width:70px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="manageResult" style="width:70px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>                     	
                     	<td class="lableTd"><input type="text" name="prodResult" style="width:70px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd">
                     	<span class="fr pt5"><a href="#" class='remove'><img src="../css/default/images/-.png" width="15" height="15"/></a></span></td>
						</tr>						
                       	      </table>
                          </div>
                          <div class="mb10 t_c">
                          <input type="button" value="提交" onclick="save();"/>
                                  &nbsp;
<input type="button" value="关 闭" onclick="if(confirm('您确定要关闭窗口吗?')){window.close()}"/>
&nbsp;
<input type="reset" value="重 置" />&nbsp;
                          </div>
                            <div class="footer"></div>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>