<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营生产指标管控查看</title>
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
	<li class="fin">运营生产管控设置</li>
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
		<th colspan="6" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
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
		    	<s:if test="#request.vo.ext1!=null">
		    		<s:property value="#request.vo.ext1"/>年
		    	</s:if>
		    	<s:if test="#request.vo.ext2==0">
		    		全年
		    	</s:if>
		    	<s:else>
		    		<s:property value="#request.vo.ext2"/>月
		    	</s:else>
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">开行列次</td>
		  		<s:if test="#request.vo.ext2==0">
		    		  <td>
				    	<s:property value="#request.vo.metroColumnYear"/>&nbsp;&nbsp;列次/年
				    </td>
		    	</s:if>
		    	<s:else>
		    		 <td>
				    	<s:property value="#request.vo.metroColumnDaily"/>&nbsp;&nbsp;列次/日	   		    
				    </td>
				    <td>
				    	<s:property value="#request.vo.metroColumnMonth"/>&nbsp;&nbsp;列次/月
				    </td>
		    	</s:else>		  
	    	<td>
	    		管控说明：
	    		<s:if test="#request.vo.metroColumnControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.vo.metroColumnControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.vo.metroColumnControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.vo.metroColumnDescribe"/>
	    	</td>    
		</tr>
		<tr>
		  <td class="t_r lableTd">运营里程</td>
		  
		  <s:if test="#request.vo.ext2==0">
    		 <td>
		    	<s:property value="#request.vo.metroDistanceYear"/>&nbsp;&nbsp;车公里/年
		    </td>
    	  </s:if>
    	  <s:else>
    		 <td>
		    	<s:property value="#request.vo.metroDistanceDaily"/>&nbsp;&nbsp;车公里/日
		    </td>
		    <td>
		    	<s:property value="#request.vo.metroDistanceMonth"/>&nbsp;&nbsp;车公里/月
		    </td>
    	   </s:else>		  
	    	<td>
	    		管控说明：
	    		<s:if test="#request.vo.metroDistanceControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.vo.metroDistanceControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.vo.metroDistanceControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.vo.metroDistanceDescribe"/>
	    	</td>     		    
		</tr>
		<tr>
		<td class="t_r lableTd">客流量</td>
    	<s:if test="#request.vo.ext2==0">
    		<td>
		     	<s:property value="#request.vo.passengerCapacityYear"/>&nbsp;&nbsp;人/年
		    </td>
    	</s:if>
    	<s:else>
    		<td>
		    	<s:property value="#request.vo.passengerCapacityDaily"/>&nbsp;&nbsp;人/日
		    </td>
		     <td>
		     	<s:property value="#request.vo.passengerCapacityMonth"/>&nbsp;&nbsp;人/月
		    </td>
    	</s:else>
	    	<td>
	    		管控说明：
	    		<s:if test="#request.vo.passengerCapacityControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.vo.passengerCapacityControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.vo.passengerCapacityControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.vo.passengerCapacityDescribe"/>
	    	</td>     	    
		</tr>
		<tr>
		  <td class="t_r lableTd">换乘人次</td>
		  <s:if test="#request.vo.ext2==0">
    		 <td>
		     	<s:property value="#request.vo.passengerTransferYear"/>&nbsp;&nbsp;人/年
		    </td>
    	</s:if>
    	<s:else>
    		<td>
		    	<s:property value="#request.vo.passengerTransferDaily"/>&nbsp;&nbsp;人/日
		    </td>
		     <td>
		     	<s:property value="#request.vo.passengerTransferMonth"/>&nbsp;&nbsp;人/月
		    </td>
    	</s:else>
	    	<td>
	    		管控说明：
	    		<s:if test="#request.vo.passengerTransferControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.vo.passengerTransferControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.vo.passengerTransferControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.vo.passengerTransferDescribe"/>
	    	</td>     
		</tr>
		<tr>
		  <td class="t_r lableTd">客运收入</td>
		  <s:if test="#request.vo.ext2==0">
		    <td>
		     	<s:property value="#request.vo.ticketIncomeYear"/>&nbsp;&nbsp;元/年
		    </td>
	      </s:if>
	    	<s:else>
	    		 <td>
			    	<s:property value="#request.vo.ticketIncomeDaily"/>&nbsp;&nbsp;元/日
			    </td>		    
			     <td>
			     	<s:property value="#request.vo.ticketIncomeMonth"/>&nbsp;&nbsp;元/月
			    </td>
	    	</s:else>
	    	<td>
	    		管控说明：
	    		<s:if test="#request.vo.ticketIncomeControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.vo.ticketIncomeControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.vo.ticketIncomeControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.vo.ticketIncomeDescribe"/>
	    	</td>    
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="6" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</td>
	</tr>
</table>
</s:form>

</div>
<!--Table End--></div>
</body>
</html>
