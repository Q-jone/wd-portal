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
		if($(this).attr("name").indexOf("Describe")==-1){
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

<s:form action="updateQualityControl" name="metroQualityControlVO" namespace="/indicatorControl" method="post">
<input type="hidden" name="id" value="<s:property value='#request.vo.id'/>">

<table width="100%" class="table_1">
<input type="hidden" value="<s:property value='#request.vo.id'/>" name="id">
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
		    	<s:if test="#request.vo.indicatorLine==0">全网</s:if>
		    	<s:else><s:property value="#request.vo.indicatorLine"/>号线</s:else>   
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">年份/月份</td>		  
		    <td colspan="5">
		    	<s:property value="#request.vo.ext1"/>年
		    	<s:if test="#request.vo.ext2==0">
		    		全年
		    	</s:if>
		    	<s:else>
		    		<s:property value="#request.vo.ext2"/>月
		    	</s:else>
		    	<input type="hidden" value="<s:property value='#request.vo.ext1'/>" name="metroQualityControlVO.ext1">
		    	<input type="hidden" value="<s:property value='#request.vo.ext2'/>" name="metroQualityControlVO.ext2">   
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">正点率</td>
	  		<s:if test="#request.vo.ext2==0">
	    		  <td>
			    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOntimeYear" maxlength="10" value="<s:property value='#request.vo.metroOntimeYear'/>"/>%&nbsp;&nbsp;每年	   		    
			    </td>
	    	</s:if>
	    	<s:else>
	    		 <td>
			    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOntimeDaily" maxlength="10" value="<s:property value='#request.vo.metroOntimeDaily'/>"/>%&nbsp;&nbsp;每日	   		    
			    </td>
			    <td>
			    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOntimeMonth" maxlength="10" value="<s:property value='#request.vo.metroOntimeMonth'/>"/>%&nbsp;&nbsp;每月	   		    
			    </td>
	    	</s:else>		  
		   <td>
		       	管控说明：
		       	<select name="metroQualityControlVO.ontimeControl">
		    		<s:if test="#request.vo.ontimeControl==1">
		    			<option value="0">---请选择---</option>
			    		<option value="1" selected="selected">高好</option>
			    		<option value="2">低好</option>
			    		<option value="3">接近好</option>
		    		</s:if>
		    		<s:elseif test="#request.vo.ontimeControl==2">
		    			<option value="0">---请选择---</option>
			    		<option value="1">高好</option>
			    		<option value="2"  selected="selected">低好</option>
			    		<option value="3">接近好</option>
		    		</s:elseif>
		    		<s:elseif test="#request.vo.ontimeControl==3">
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
		       	<input type="text" name="metroQualityControlVO.ontimeDescribe" maxlength="20" value="<s:property value='#request.vo.ontimeDescribe'/>" />  		    
		   </td>		    
		</tr>
		<tr>
		  <td class="t_r lableTd">兑现率</td>
	    	<s:if test="#request.vo.ext2==0">
	    		 <td>
			    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOnworkYear" maxlength="10" value="<s:property value='#request.vo.metroOnworkYear'/>"/>%&nbsp;&nbsp;每年  		    
			    </td>
	    	</s:if>
	    	<s:else>
	    		<td>
			    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOnworkDaily" maxlength="10" value="<s:property value='#request.vo.metroOnworkDaily'/>"/>%&nbsp;&nbsp;每日  		    
			    </td>
			    <td>
			    	<input style="text-align: right;" type="text" name="metroQualityControlVO.metroOnworkMonth" maxlength="10" value="<s:property value='#request.vo.metroOnworkMonth'/>"/>%&nbsp;&nbsp;每月  		    
			    </td>
	    	</s:else>
		   <td>
		       	管控说明：
		       	<select name="metroQualityControlVO.onworkControl">
		    		<s:if test="#request.vo.onworkControl==1">
		    			<option value="0">---请选择---</option>
			    		<option value="1" selected="selected">高好</option>
			    		<option value="2">低好</option>
			    		<option value="3">接近好</option>
		    		</s:if>
		    		<s:elseif test="#request.vo.onworkControl==2">
		    			<option value="0">---请选择---</option>
			    		<option value="1">高好</option>
			    		<option value="2" selected="selected">低好</option>
			    		<option value="3">接近好</option>
		    		</s:elseif>
		    		<s:elseif test="#request.vo.onworkControl==3">
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
		       	<input type="text" name="metroQualityControlVO.onworkDescribe" maxlength="20" value="<s:property value='#request.vo.onworkDescribe'/>" />  		    
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
<!--Table End--></div>
</body>
</html>
