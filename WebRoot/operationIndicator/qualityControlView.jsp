<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营质量安全指标管控查看</title>
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

<s:form action="saveQualityControl" name="MetroQualityControl" namespace="/indicatorControl" method="post">
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
				<s:if test="#request.mqc.indicatorLine==0">全网</s:if>
		    	<s:else><s:property value="#request.mqc.indicatorLine"/>号线</s:else>
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">年份/月份</td>		  
		    <td colspan="5">
		    	<s:property value="#request.mqc.ext1"/>年
		    	<s:if test="#request.mqc.ext2==0">
		    		全年
		    	</s:if>
		    	<s:else>
		    		<s:property value="#request.mqc.ext2"/>月
		    	</s:else>
		    </td>
		</tr>
		<tr>
		  <td class="t_r lableTd">正点率</td>
		  	<s:if test="#request.mqc.ext2==0">
		   		 <td>
		    	<s:property value="#request.mqc.metroOntimeYear"/>%&nbsp;&nbsp;每年
		    </td>
		   	</s:if>
		   	<s:else>
		   		 <td>
			    	<s:property value="#request.mqc.metroOntimeDaily"/>%&nbsp;&nbsp;每日   		    
			    </td>
			    <td>
			    	<s:property value="#request.mqc.metroOntimeMonth"/>%&nbsp;&nbsp;每月   		 	   		    
			    </td>
		   	</s:else>
	    	<td>
	    		管控说明：
	    		 <s:if test="#request.mqc.ontimeControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.mqc.ontimeControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.mqc.ontimeControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.mqc.ontimeDescribe"/>
	    	</td>     	  	    
		</tr>
		<tr>
		  <td class="t_r lableTd">兑现率</td>	
		  	<s:if test="#request.mqc.ext2==0">
	    		 <td>
			    	<s:property value="#request.mqc.metroOnworkYear"/>%&nbsp;&nbsp;每年	  		    
			    </td>
	    	</s:if>
	    	<s:else>
	    		<td>
			    	<s:property value="#request.mqc.metroOnworkDaily"/>%&nbsp;&nbsp;每日
			    </td>
			    <td>
			    	<s:property value="#request.mqc.metroOnworkMonth"/>%&nbsp;&nbsp;每月	   		    
			    </td>
	    	</s:else>
	    	<td>
	    		管控说明：
	    		 <s:if test="#request.mqc.onworkControl==1">
		    		高好
	    		</s:if>
	    		<s:elseif test="#request.mqc.onworkControl==2">
	    			低好
	    		</s:elseif>
	    		<s:elseif test="#request.mqc.onworkControl==3">
	    			接近好
	    		</s:elseif>
	    		<s:property value="#request.mqc.onworkDescribe"/>
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
