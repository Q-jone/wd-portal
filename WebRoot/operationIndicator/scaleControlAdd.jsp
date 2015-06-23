<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营生产指标管控设置</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
      
<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");			
});
		
function shut(){
	window.opener=null;
	window.open("","_self");
	window.close();	
}


function checkForm(){
	var status = true;
	$("input[type=text]").each(function(){
		
		if($(this).val()==null || $(this).val()==''){
			alert("请输入管控值！");
			status = false;
			$(this).focus();
			return false;
		}
		if(!$(this).val().match(/^[0-9]*[1-9][0-9]*$/)){
			alert("请输入数字！")
			status = false;
			$(this).focus();
			return false;
		}
		status = true;
	});
	return status;
}

		
</script>

</head>

<body>

<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../css/default/images/sideBar_arrow_right.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">运营管理</a></li>
	<li><a href="#">运营指标</a></li>	
	<li class="fin">运营生产指标添加</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li class="selected"><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>

<div class="mb10 pt45">

<s:form action="saveProductionControl" name="MetroProductionControl" namespace="/indicatorControl" method="post">
<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="reset" value="取 消" /> &nbsp;
		</th>
	</thead>
	<tbody>
		<tr>
		  <td class="t_r lableTd">线路</td>		  
		    <td colspan="3">
		    	<select name="metroProductionControlVO.indicatorLine" onchange="">
		    		<s:iterator value="#request.lineMap" id="map">
						<option value="<s:property value='#map.key'/>"><s:property value="#map.value"/></option>		    		
		    		</s:iterator>
		    	</select>	    
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">开行列次</td>		  
		    <td>
		    	<input type="text" name="metroProductionControlVO.metroColumnDaily" maxlength="10"/>&nbsp;列次/日	   		    
		    </td>
		    <td>
		    	<input type="text" name="metroProductionControlVO.metroColumnMonth" maxlength="10"/>&nbsp;列次/月	   		    
		    </td>
		    <td>
		    	<input type="text" name="metroProductionControlVO.metroColumnYear" maxlength="10"/>&nbsp;列次/年	   		    
		    </td>		    
		</tr>
		<tr>
		  <td class="t_r lableTd">运营里程</td>		  
		    <td>
		    	<input type="text" name="metroProductionControlVO.metroDistanceDaily" maxlength="10"/>&nbsp;车公里/日  		    
		    </td>
		    <td>
		    	<input type="text" name="metroProductionControlVO.metroDistanceMonth" maxlength="10"/>&nbsp;车公里/月  		    
		    </td>
		    <td>
		    	<input type="text" name="metroProductionControlVO.metroDistanceYear" maxlength="10"/>&nbsp;车公里/年  		    
		    </td>		    
		</tr>
		<tr>
		  <td class="t_r lableTd">客流量</td>		  
		    <td>
		    	<input type="text" name="metroProductionControlVO.passengerCapacityDaily" maxlength="10"/>&nbsp;人/日	   		    
		    </td>
		     <td>
		    	<input type="text" name="metroProductionControlVO.passengerCapacityMonth" maxlength="10"/>&nbsp;人/月	   		    
		    </td>
		     <td>
		    	<input type="text" name="metroProductionControlVO.passengerCapacityYear" maxlength="10"/>&nbsp;人/年	   		    
		    </td>		    
		</tr>
		<tr>
		  <td class="t_r lableTd">换乘人次</td>		  
		    <td>
		    	<input type="text" name="metroProductionControlVO.passengerTransferDaily" maxlength="10"/>&nbsp;人/日	   		    
		    </td>		    
		     <td>
		    	<input type="text" name="metroProductionControlVO.passengerTransferMonth" maxlength="10"/>&nbsp;人/月	   		    
		    </td>
		     <td>
		    	<input type="text" name="metroProductionControlVO.passengerTransferYear" maxlength="10"/>&nbsp;人/年   		    
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">客运收入</td>		  
		    <td>
		    	<input type="text" name="metroProductionControlVO.ticketIncomeDaily" maxlength="10"/>&nbsp;元/日	   		    
		    </td>		    
		     <td>
		    	<input type="text" name="metroProductionControlVO.ticketIncomeMonth" maxlength="10"/>&nbsp;元/月   		    
		    </td>
		     <td>
		    	<input type="text" name="metroProductionControlVO.ticketIncomeYear" maxlength="10"/>&nbsp;元/年	   		    
		    </td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="reset" value="取 消" />&nbsp;
		</td>
	</tr>
</table>
</s:form>

</div>
<!--Table End--></div>
</body>
</html>
