<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>培训中心年度技能鉴定结果</title>
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
	var levels = '';
	var duplicate = false;	
	$('.dataRow').each(function(){
		var tr = $(this);
		if($("td:eq(2) select",tr).length == 0){
			levels = $("td:eq(2)",tr).text().substr(0,1);
		}else{
			levels = $("td:eq(2) select option:selected",tr).text();
		}
		if(map[levels]){
			duplicate = true;
			return false;
		}
		map[levels] = levels;		
		if($("input[name='isChanged']",tr).val() == '1'){
			$('input',tr).each(function(){	
				p+='datas['+i+'].'+$(this).attr('name')+'='+$(this).val()+'&';
			});
			$('select',tr).each(function(){	
				p+='datas['+i+'].'+$(this).attr('name')+'='+$(this).val()+'&';
			});		
			i++;			
		}
	});
	if(duplicate){
		alert('您添加了重复的级别，请删除重复级别后再提交！');
		return false;
	}	
	p += 'num='+i;	
	$.ajax({
		type: 'POST',
		url: '/portal/train/saveLevel.action?random='+Math.random(),
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
	        url: "/portal/train/delLevel.action",
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
                        	<h1 class="t_c">上海申通地铁集团有限公司<br>技能鉴定结果录入
                       	  </h1>
                            <div class="mb10">
                           	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_2" id="table">
                           	 	<tr class="disable">
        <td class="lableTd" colspan="5" style="text-align:center">培训中心年度技能鉴定结果<span class="fr pt5"><a href="#" id="add"><img src="../css/default/images/+.png" width="15" height="15"></a></span></td>
                      		</tr>
					<tr class="disable">
        				<td class="lableTd" style="width:40px;">年度</td>
        				<td class="lableTd" style="width:80px;">类别</td>
        				<td class="lableTd" style="width:80px;">级别</td>
        				<td class="lableTd" >鉴定人次</td>
        				<td class="lableTd" >合格人次</td>
					</tr>
					<s:iterator value="#request.levels" id="data" status="s">					       
                     <tr class="disable dataRow" >
                     	<td class="lableTd"><input type="hidden" name="id" value="<s:property value="#data.id" />"/>
                     	<input type="hidden" name="isChanged" value="0"/>
                     	<input type="hidden" name="mainId" value="<s:property value="#request.main.id" />"/>
                     	<s:property value="#request.main.year" />
                     	</td>
                     	<td class="lableTd"><s:property value="#request.main.name" /></td>
                     	<td class="lableTd"><s:property value="#data.levels" />级</td>
                     	<td class="lableTd"><input type="text" name="tested" style="width:80px;" onblur="checkNum(this)" value="<s:property value="#data.tested" />" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="passed" style="width:80px;" onblur="checkNum(this)" value="<s:property value="#data.passed" />" maxlength="9"/>
                     	<span class="fr pt5"><a href="#" class='remove'><img src="../css/default/images/-.png" width="15" height="15"/></a></span></td>
					</tr>
					</s:iterator>
                    <tr class="disable" id="dataNew" style="display:none;">
                     	<td class="lableTd">
                     	<input type="hidden" name="isChanged" value="1"/>
                     	<input type="hidden" name="mainId" value="<s:property value="#request.main.id" />"/>
                     	<s:property value="#request.main.year" />
                     	</td>
                     	<td class="lableTd"><s:property value="#request.main.name" /></td>
                     	<td class="lableTd">
                     	<select name="levels">
                     		<option>1</option>
                     		<option>2</option>
                     		<option>3</option>
                     		<option>4</option>
                     		<option>5</option>
                     	</select></td>
                     	<td class="lableTd"><input type="text" name="tested" style="width:80px;" onblur="checkNum(this)" value="0" maxlength="9"/></td>
                     	<td class="lableTd"><input type="text" name="passed" style="width:80px;" onblur="checkNum(this)" value="0" maxlength="9"/>
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