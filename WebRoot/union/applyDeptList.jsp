<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<meta charset="utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=9">
<title>工会立功竞赛</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/reset.css" />
<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
        <script src="<%=path%>/js/jquery.form.js"></script>
		<script src="<%=path%>/js/jquery.formalize.js"></script>
	<script type="text/javascript">
		var total = '${param.quantity}';
		var edit = '${param.edit}';
	
		var newRowHtml = null;
		$(function (){
			$('#add').click(add);
			$('.remove').attr('href','javascript:void(0)').click(remove);
			newRowHtml = $('#dataNew').html();
			
			if(edit == 'false'){
				$('.op').hide();				
			}
		});
          
        function doSubmit(){
      		var checkResult = checkForm();
    		if(checkResult){
    			alert(checkResult);
    			return false;
    		}        	  
    		
    		var i = 0;
    		var p = '';
    		var map = {};
    		var deptName = '';
    		var duplicate = false;	
    		$('.dataRow').each(function(){
    			var tr = $(this);
    			if($("td:eq(0) select",tr).length == 0){
    				deptName = $("td:eq(0)",tr).text();
    			}else{
    				deptName = $("td:eq(0) select option:selected",tr).text();
    			}
    			if(map[deptName]){
    				duplicate = true;
    				return false;
    			}
    			map[deptName] = deptName;		
    			if($("input[name='isChanged']",tr).val() == '1'){
    				$('input',tr).each(function(){	
    					p+='applyDepartmentList['+i+'].'+$(this).attr('name')+'='+$(this).val()+'&';
    				});
    				$('select',tr).each(function(){	
    					p+='applyDepartmentList['+i+'].deptId='+$(this).val()+'&';
    					p+='applyDepartmentList['+i+'].deptName='+$('option:selected',$(this)).text()+'&';
    				});		
    				i++;			
    			}
    		});
    		if(duplicate){
    			alert('您添加了重复的部门，请删除重复部门后再提交！');
    			return false;
    		}	    		
    		p += 'num='+i;	
    		if(confirm('确认要提交吗？')){
    		$.ajax({
    			type: 'POST',
    			beforeSend:function(){$('#saveBtn').attr('disabled','true');},
    			url: '<%=path%>/unionPrize/saveApplyDept.action?random='+Math.random(),
    			data: p,
    			dataType:'json',
    			cache : false,
    			error:function(){alert('系统连接失败，请稍后再试！'); $('#saveBtn').removeAttr('disabled');},
    			success: function(obj){			
					alert("操作成功");
					parent.closeDialog();
					$('#saveBtn').removeAttr('disabled');
					return false;
    			}	  
    		});	    		
    		}
        	//if(confirm('确认要提交吗？')){$('#form').ajaxSubmit(handleOptions);}
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
        
          function checkForm(){
        	  var sum = 0;
        	  var foundZero = false;
			 $(".dataRow input[name='quantity']").each(function(){
				 var n = this.value;
				 if(!n){
					 n = 0;
				 }
				 if(n == 0){
					 foundZero = true;
					 return false;
				 }
				 sum += n*1;
			 })
			 if(foundZero){
				 return '分配名额请输入大于0的整数！';
			 }
			 if(sum != total){
				 return '分配名额合计应=总数('+total+')';
			 }
          }
          
          function add(){
        		var newTr = $('<tr class="dataRow"></tr>').append(newRowHtml);
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
        		        url: "/portal/unionPrize/delApplyDept.action",
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

	<style>

	</style>

</head>

<body>
<div>
<span class="fr pt5 op"><input id="add" type="button" value="添加"/><%-- <img src="<%=path%>/union/images/+.png" width="15" height="15"> --%></span>
</div>
<div>
	<s:form action="" id="form" method="post">
	<table id="table" style="width:240px;">
	<tr>
    	<td class="lableTd" style="width:140px;">单位名称</td>
    	<td class="lableTd" style="width:100px;">分配名额</td>
    	<td></td>
	</tr>	
	<s:iterator value="#request.applyDepts" id="data" status="s">
	<tr class="dataRow">
		<td>${data.deptName}</td>
		<td>
			<input type="text" name="quantity" value="${data.quantity}" style="width:40px;" onblur="checkNum(this)" maxlength="9"/>
			
			<input type="hidden" name="id" value="${data.id}"/>
			<input type="hidden" name="isChanged" value="0"/>
			<input type="hidden" name="prizeId" value="<s:property value="#request.prizeId" />"/>
			<input type="hidden" name="deptName" value="${data.deptName}"/>
			<input type="hidden" name="deptId" value="${data.deptId}"/>			
		</td>
		<td><span class="fr pt5 op"><a href="#" class='remove'><img src="<%=path%>/union/images/-.png" width="15" height="15"/></a></span></td>	
	</tr>
	</s:iterator>
                <tr id="dataNew" style="display:none;">
                     	<td class="lableTd">
                     	<input type="hidden" name="isChanged" value="1"/>
                     	<input type="hidden" name="prizeId" value="<s:property value="#request.prizeId" />"/>
							<select name="depts">
	                     	<s:iterator value="#request.allDepts" id="dept" status="s">
		                     	<option value="${dept.ID}">${dept.NAME}</option>
	                     	</s:iterator>
<!-- 	                     	<option value="9000">集团机关团总支</option>
	                     	<option value="9001">标准化室</option> -->
	                     	</select>
                     	</td>
                     	<td>
							<input type="text" name="quantity" style="width:40px;" onblur="checkNum(this)" value="0" maxlength="9"/>
						</td>
						<td><span class="fr pt5 op"><a href="#" class='remove'><img src="<%=path%>/union/images/-.png" width="15" height="15"/></a></span></td>						
				</tr>				
	</table>
	<div style="text-align:center;margin:20px;" class="op">	
	<input class="btn" type="button" value="提交" id="saveBtn" onclick="doSubmit()"/>
	</div>
	</s:form>
</div>	
</body>
</html>