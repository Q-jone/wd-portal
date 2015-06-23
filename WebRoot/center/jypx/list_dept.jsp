<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>各直属单位年度培训<s:if test="#parameters.style[0] == 'plan'">计划</s:if><s:else>结果</s:else>录入</title>
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
$(function (){
	$('#add').attr('href','javascript:void(0)').click(add);
	$('.remove').attr('href','javascript:void(0)').click(remove);
	newRowHtml = $('#dataNew').html();
});
function save(){
	var i = 0;
	var p = '';
	var map = {};
	var deptName = '';
	var duplicate = false;
	$('.dataRow').each(function(){
		var tr = $(this);
		if($("td:eq(1) select",tr).length == 0){
			deptName = $("td:eq(1)",tr).text();
		}else{
			deptName = $("td:eq(1) select option:selected",tr).text();
		}
		if(map[deptName]){
			duplicate = true;
			return false;
		}
		map[deptName] = deptName;
		if($("input[name='isChanged']",tr).val() == '1'){
			$('input',tr).each(function(){	
				p+='datas['+i+'].'+$(this).attr('name')+'='+$(this).val()+'&';
			});
			$('select',tr).each(function(){	
				p+='datas['+i+'].deptId='+$(this).val()+'&';
				p+='datas['+i+'].deptName='+$('option:selected',$(this)).text()+'&';
			});		
			i++;			
		}
	});
	if(duplicate){
		alert('您添加了重复的单位，请删除重复单位后再提交！');
		return false;
	}
	p += 'num='+i;	
	$.ajax({
		type: 'POST',
		url: '/portal/train/saveDept.action?random='+Math.random(),
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
function checkPct(obj){
	$("input[name='isChanged']",$(obj).closest('tr')).val('1');
	alert("ssdsd");
	var r = /^(\d){1,3}(\.(\d){0,2})?$/;
	if(!r.test(obj.value)){
		alert('请输入一个百分比，如11.54，限2位小数！');
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
	        url: "/portal/train/delDept.action",
	        dataType: "json",
	        data:"id="+id+"&random="+Math.random(),
	        success: function (data) {
	        	
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	                alert("删除失败");
	        }
	});	 
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
                        	<h1 class="t_c">上海申通地铁集团有限公司<br>各直属单位年度培训<s:if test="#parameters.style[0] == 'plan'">计划</s:if><s:else>结果</s:else>录入
                       	  </h1>
                            <div class="mb10">
                           	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2" id="table">
                           	 	<tr class="disable">
        <td class="lableTd" colspan="7" style="text-align:center">各直属单位年度培训<s:if test="#parameters.style[0] == 'plan'">计划</s:if><s:else>结果</s:else>录入<span class="fr pt5"><a href="#" id="add"><img src="../css/default/images/+.png" width="15" height="15"></a></span></td>
                      		</tr>
					<tr class="disable">
        				<td class="lableTd" rowspan="2" style="width:40px;">年度</td>
        				<td class="lableTd" rowspan="2" style="width:100px;">单位名称</td>
        				<td class="lableTd" colspan="2" style="width:150px;">培训计划人次</td>
        				<td class="lableTd" colspan="2" style="width:150px;">培训完成人次</td>
        				<td class="lableTd" rowspan="2" style="width:130px;">员工课时覆盖率</td>
					</tr>
					<tr class="disable">
        				<td class="lableTd" style="width:75px;">管理类</td>
        				<td class="lableTd" style="width:75px;">生产类</td>
        				<td class="lableTd" style="width:75px;">管理类</td>
        				<td class="lableTd" style="width:75px;">生产类</td>			
					</tr>
					<s:iterator value="#request.depts" id="data" status="s">					       
                     <tr class="disable dataRow" >
                     	<td class="lableTd"><input type="hidden" name="id" value="<s:property value="#data.id" />"/>
                     	<input type="hidden" name="isChanged" value="0"/>
                     	<input type="hidden" name="mainId" value="<s:property value="#request.main.id" />"/>
                     	<s:property value="#request.main.year" />
                     	</td>
                     	<td class="lableTd"><s:property value="#data.deptName" /></td>
                     	<s:if test="#parameters.style[0] == 'plan'">
	                     	<td class="lableTd"><input type="text" name="managePlan" style="width:60px;" onblur="checkNum(this)" value="<s:property value="#data.managePlan" />" maxlength="9"/></td>
	                     	<td class="lableTd"><input type="text" name="prodPlan" style="width:60px;" onblur="checkNum(this)" value="<s:property value="#data.prodPlan" />" maxlength="9"/></td>
	                     	<td class="lableTd"><s:property value="#data.manageResult" /></td>
	                     	<td class="lableTd"><s:property value="#data.prodResult" /></td>
	                     	<td class="lableTd"><s:property value="#data.finishRate" />%
	                     	<span class="fr pt5"><a href="#" class='remove'><img src="../css/default/images/-.png" width="15" height="15"/></a></span></td>	                     	
                     	</s:if>
                     	<s:else>
	                     	<td class="lableTd"><s:property value="#data.managePlan" /></td>
	                     	<td class="lableTd"><s:property value="#data.prodPlan" /></td>
	                     	<td class="lableTd"><input type="text" name="manageResult" style="width:60px;" onblur="checkNum(this)" value="<s:property value="#data.manageResult" />" maxlength="9"/></td>
	                     	<td class="lableTd"><input type="text" name="prodResult" style="width:60px;" onblur="checkNum(this)" value="<s:property value="#data.prodResult" />" maxlength="9"/></td>
	                     	<td class="lableTd"><input type="text" name="finishRate" style="width:60px;" onblur="checkPct(this)" value="<s:property value="#data.finishRate" />" maxlength="9"/>%
	                     	<span class="fr pt5"><a href="#" class='remove'><img src="../css/default/images/-.png" width="15" height="15"/></a></span></td>	                     	
                     	</s:else>
					</tr>
					</s:iterator>
                    <tr class="disable" id="dataNew" style="display:none;">
                     	<td class="lableTd">
                     	<input type="hidden" name="isChanged" value="1"/>
                     	<input type="hidden" name="mainId" value="<s:property value="#request.main.id" />"/>
                     	<s:property value="#request.main.year" />
                     	</td>
                     	<td class="lableTd">
                     	<select name="deptId">
                     		<option value="2921">运一</option>
                     		<option value="2922">运二</option>
                     		<option value="2923">运三</option>
                     		<option value="2924">运四</option>
                     		<option value="2920">运管</option>
                     		<option value="2925">维保</option>
                     		<option value="2941">技术</option>
                     		<option value="2946">资产</option>
                     		<option value="2944">培训中心</option>
                     		<option value="2959">大桥</option>
                     		<option value="2943">隧道院</option>
                     		<option value="2962">磁浮</option>
                     		<option value="2945">股份公司</option>
                     	</select></td>
                     	<td class="lableTd"><input type="text" name="managePlan" style="width:60px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="prodPlan" style="width:60px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="manageResult" style="width:60px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>                     	
                     	<td class="lableTd"><input type="text" name="prodResult" style="width:60px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="finishRate" style="width:60px;" onblur="checkPct(this)" value="0" maxlength="9"/>%
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