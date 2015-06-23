<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营质量安全指标管控设置</title>
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
      
<script type="text/javascript"><!--
$(function(){
	$(".odd tr:odd").css("background","#fafafa");			
	var line = $("#hiddenLine").val();
	var date = $("#hiddenDate").val();
	var month = $("#hiddenMonth").val();
	if(line!='' && date!=''){
		line=='0' ? line='全网' : line+='号线';
		if(month=='0'){
			alert(date+"年,全年,"+line+"管控数据已存在！请重新选择日期或线路！");
		}else{
			alert(date+"年"+month+"月"+line+"管控数据已存在！请重新选择日期或线路！");		
		} 
	}
	
	var $ddlMonthChoose = $("#monthChoose");
	$(".tdDailyIndicator").hide();
	$(".tdMonthlyIndicator").hide();
	$ddlMonthChoose.change(function(){
		if($(this).val()==0){
			$(".tdDailyIndicator").hide();
			$(".tdMonthlyIndicator").hide();
			$(".tdAnnualIndicator").show();
		}else{
			$(".tdDailyIndicator").show();
			$(".tdMonthlyIndicator").show();
			$(".tdAnnualIndicator").hide();
		}
	});
	
});
		
function shut(){
	window.opener=null;
	window.open("","_self");
	window.close();	
}


function checkForm(){
	var status = true;
	$("input[type=text]").each(function(){
		if($(this).attr("name").indexOf("Describe")==-1){
			/*
			if($(this).val()==null || $(this).val()==''){
				alert("请输入管控值！");
				status = false;
				$(this).focus();
				return false;
			}*/
			if($(this).val()!=null && $(this).val()!=''){
				if(!$(this).val().match(/^[0-9]*.[0-9]*$/)){
					alert("请输入数字！")
					status = false;
					$(this).focus();
					return false;
				}
				if(parseFloat($(this).val())>100 || parseFloat($(this).val())<0){
					alert("输入范围为：0—100");
					status = false;
					$(this).focus();
					return false;
				}
				status = true;
			}
			
		}else{
			if($(this).val()!=null && $(this).val()!="" && $(this).val().indexOf("|")==-1){
				$(this).focus();
				alert("请用“|”来分隔管控说明！");
				status = false;
				return false;
			}
		}
	});
	return status;
}

function setData(){
	var value = $("input[type=text]:first").val();
	$("input[type=text]").each(function(){
		$(this).val(value);
	});
}
		
</script>

</head>

<body>
<div class="main">
	<div class="ctrl clearfix">
		<div class="fl"><img src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">运营管理</a></li>
				<li><a href="#">运营指标</a></li>	
				<li class="fin">运营质量安全管控设置</li>
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
		<input type="hidden" value="<s:property value='#request.metroQualityControlVO.ext1'/>" id="hiddenDate">
		<input type="hidden" value="<s:property value='#request.metroQualityControlVO.ext2'/>" id="hiddenMonth">
		<input type="hidden" value="<s:property value='#request.metroQualityControlVO.indicatorLine'/>" id="hiddenLine">
		<s:form action="saveQualityControl" name="MetroQualityControl" namespace="/indicatorControl" method="post">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="6" class="t_r">
					<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
					<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
					<input type="reset" value="取 消" /> &nbsp;
				</th>
			</thead>
			<tbody>
				<tr>
				  <td class="t_r lableTd">线路</td>		  
				    <td colspan="5">
				    	<select name="metroQualityControlVO.indicatorLine" >
				    		<s:iterator value="#request.lineMap" id="map">
								<option value="<s:property value='#map.key'/>"><s:property value="#map.value"/></option>		    		
				    		</s:iterator>
				    	</select>	    
				    </td>
				</tr>
				<tr>
				  <td class="t_r lableTd">年份/月份</td>		  
				    <td colspan="4">
				    	<select name="metroQualityControlVO.ext1" id="yearChoose">
				    		<option>2000</option>
				    		<option>2001</option>
				    		<option>2002</option>
				    		<option>2003</option>
				    		<option>2004</option>
				    		<option>2005</option>
				    		<option>2006</option>
				    		<option>2007</option>
				    		<option>2008</option>
				    		<option>2009</option>
				    		<option>2010</option>
				    		<option>2011</option>
				    		<option selected="selected">2012</option>
				    		<option>2013</option>
				    		<option>2014</option>
				    		<option>2015</option>
				    		<option>2016</option>
				    		<option>2017</option>
				    		<option>2018</option>
				    		<option>2019</option>
				    		<option>2020</option>
				    	</select>
				    	年
				    	<select name="metroQualityControlVO.ext2" id="monthChoose">
				    		<option value="0">全年</option>
				    		<option>1</option>
				    		<option>2</option>
				    		<option>3</option>
				    		<option>4</option>
				    		<option>5</option>
				    		<option>6</option>
				    		<option>7</option>
				    		<option>8</option>
				    		<option>9</option>
				    		<option>10</option>
				    		<option>11</option>
				    		<option>12</option>
				    	</select>
				    	月
				    </td>
				</tr>
				<tr>
				  <td class="t_r lableTd">正点率</td>		  
				    <td class="tdDailyIndicator">
				    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOntimeDaily" maxlength="10" value="<s:property value='#request.metroQualityControlVO.metroOntimeDaily'/>"/>%&nbsp;&nbsp;每日	   		    
				    </td>
				    <td class="tdMonthlyIndicator">
				    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOntimeMonth" maxlength="10" value="<s:property value='#request.metroQualityControlVO.metroOntimeMonth'/>"/>%&nbsp;&nbsp;每月	   		    
				    </td>
				    <td class="tdAnnualIndicator">
				    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOntimeYear" maxlength="10" value="<s:property value='#request.metroQualityControlVO.metroOntimeYear'/>"/>%&nbsp;&nbsp;每年	   		    
				    </td>	
				   <td>
				       	管控说明：
				       	<select name="metroQualityControlVO.ontimeControl">
				    		<s:if test="#request.metroQualityControlVO.ontimeControl==1">
				    			<option value="0">---请选择---</option>
					    		<option value="1" selected="selected">高好</option>
					    		<option value="2">低好</option>
					    		<option value="3">接近好</option>
				    		</s:if>
				    		<s:elseif test="#request.metroQualityControlVO.ontimeControl==2">
				    			<option value="0">---请选择---</option>
					    		<option value="1">高好</option>
					    		<option value="2"  selected="selected">低好</option>
					    		<option value="3">接近好</option>
				    		</s:elseif>
				    		<s:elseif test="#request.metroQualityControlVO.ontimeControl==3">
				    			<option value="0">---请选择---</option>
					    		<option value="1">高好</option>
					    		<option value="2">低好</option>
					    		<option value="3" selected="selected">接近好</option>
				    		</s:elseif>
				    		<s:else>
				    			<option value="0">---请选择---</option>
					    		<option value="1">高好</option>
					    		<option value="2">低好</option>
					    		<option value="3">接近好</option>
				    		</s:else> 	
				    	</select>	   	
				       	<input type="text" name="metroQualityControlVO.ontimeDescribe" maxlength="20" value="<s:property value='#request.metroQualityControlVO.ontimeDescribe'/>" />  		    
				   </td>		    
				</tr>
				<tr>
				  <td class="t_r lableTd">兑现率</td>		  
				    <td class="tdDailyIndicator"> 
				    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOnworkDaily" maxlength="10" value="<s:property value='#request.metroQualityControlVO.metroOnworkDaily'/>"/>%&nbsp;&nbsp;每日  		    
				    </td>
				    <td class="tdMonthlyIndicator">
				    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOnworkMonth" maxlength="10" value="<s:property value='#request.metroQualityControlVO.metroOnworkMonth'/>"/>%&nbsp;&nbsp;每月  		    
				    </td>
				    <td class="tdAnnualIndicator"> 
				    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOnworkYear" maxlength="10" value="<s:property value='#request.metroQualityControlVO.metroOnworkYear'/>"/>%&nbsp;&nbsp;每年  		    
				    </td>
				   <td>
				       	管控说明：
				       	<select name="metroQualityControlVO.onworkControl">
				    		<s:if test="#request.metroQualityControlVO.onworkControl==1">
				    			<option value="0">---请选择---</option>
					    		<option value="1" selected="selected">高好</option>
					    		<option value="2">低好</option>
					    		<option value="3">接近好</option>
				    		</s:if>
				    		<s:elseif test="#request.metroQualityControlVO.onworkControl==2">
				    			<option value="0">---请选择---</option>
					    		<option value="1">高好</option>
					    		<option value="2" selected="selected">低好</option>
					    		<option value="3">接近好</option>
				    		</s:elseif>
				    		<s:elseif test="#request.metroQualityControlVO.onworkControl==3">
				    			<option value="0">---请选择---</option>
					    		<option value="1">高好</option>
					    		<option value="2">低好</option>
					    		<option value="3" selected="selected">接近好</option>
				    		</s:elseif>
				    		<s:else>
					    		<option value="0">---请选择---</option>
					    		<option value="1">高好</option>
					    		<option value="2">低好</option>
					    		<option value="3">接近好</option>
				    		</s:else>
				    	</select>	
				       	<input type="text" name="metroQualityControlVO.onworkDescribe" maxlength="20" value="<s:property value='#request.metroQualityControlVO.onworkDescribe'/>" />  		    
				   </td>			    
				</tr>
			</tbody>
			<tr class="tfoot">
				<td colspan="6" class="t_r">
					<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
					<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
					<input type="reset" value="取 消" />&nbsp;
				</td>
			</tr>
		</table>
		</s:form>
	</div>
</div>
</body>
</html>
