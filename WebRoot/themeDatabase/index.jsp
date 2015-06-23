<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主题数据库</title>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<!--[if ie 6]>
	<script src="js/iepng.js" type="text/javascript"></script>
<![endif]-->

<script>
	$(function(){
		$(".subway").each(function(i){
			if(i%2==0){
				$(this).addClass("m_l_12");
			}
		});
		showPlanTask();
		showQuality();
	});

	function showPlanTask(){
		$.ajax({
			url: "/portal/themeDatabase/showPlanTask.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				 for(var i=0;i<obj.length;i++){
						$("#"+obj[i][0]).find("#progressTable").append("<tr><td width='300'>"+obj[i][1]+"</td><td width='150'>"+obj[i][2]+"</td><td>个</td></tr>");
				 }
              }
			}
		});	
	}

	function showQuality(){
		$.ajax({
			url: "/portal/themeDatabase/showQuality.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
				 for(var i=0;i<obj.length;i++){
						if(obj[i][1]=='新增'){
							$("#"+obj[i][0]).find("#quality1").html(obj[i][2]);
						}else if(obj[i][1]=='待解决'){
							$("#"+obj[i][0]).find("#quality2").html(obj[i][2]);
						}else if(obj[i][1]=='已解决'){
							$("#"+obj[i][0]).find("#quality3").html(obj[i][2]);
						}
				 }
              }
			}
		});	
	}
</script>
</head>

<body>
<div class="Main clearfix">
  
  <s:iterator value="#request.projectList" id="data" status="s">
	  <div class="subway" id="<s:property value="#data[1]"/>" style="height:280px;">
	  	<h3 class="sbw1"><span>
	  		<a style="display:none;" href="/portal/themeDatabase/toView.action?projectId=<s:property value="#data[1]"/>" target="_blank">总体计划</a>
	  		<a href="/portal/themeDatabase/toEstimate.action?projectId=<s:property value="#data[1]"/>" target="_blank">项目概算</a>
	  		<a href="/portal/themeDatabase/toIntroduction.action?id=<s:property value="#data[0]"/>" target="_blank">项目简介</a>
	  	</span><i><s:property value="#data[3]"/></i></h3>
	    <h4>支付情况（万元）</h4>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <thead>
	      <tr>
	        <th>概算</th>
	        <th>已签合同</th>
	        <th>已批准变更</th>
	        <th>待批准变更</th>
	        <th>累计支付</th>
	        <th>本月拟支付</th>
	      </tr>
	      </thead>
	      <tr>
	        <td><s:property value="#request.assetList.get(#s.index)[7]"/></td>
	        <td><s:property value="#request.assetList.get(#s.index)[2]"/></td>
	        <td><s:property value="#request.assetList.get(#s.index)[3]"/></td>
	        <td><s:property value="#request.assetList.get(#s.index)[4]"/></td>
	        <td><s:property value="#request.assetList.get(#s.index)[5]"/></td>
	        <td><s:property value="#request.assetList.get(#s.index)[6]"/></td>
	      </tr>
	    </table>
	    <!-- 
		<h4>月项目进度</h4>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <thead>
	      <tr>
	        <th width="300">类别</th>
	        
	        <th width="150">数量</th>
	        <th>计量单位</th>
	      </tr>
	      </thead>
	    </table>
	    <div class="progress">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="progressTable">
	        </table>
	    </div>
	     -->
	    <h4>月质量安全情况</h4>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <thead>
	      <tr>
	        <th width="360" class="tl">统计项</th>
	        <th>数量</th>
	        <th>计量单位</th>
	      </tr>
	      </thead>
	      <tr>
	        <td class="tl">月【新增】安全风险</td>
	        <td id="quality1">0</td>
	        <td>项</td>
	      </tr>
	      <tr>
	        <td class="tl">月【待解决】安全风险</td>
	        <td id="quality2">0</td>
	        <td>项</td>
	      </tr>
	      <tr class="none">
	        <td class="tl">月【已解决】安全风险</td>
	        <td id="quality3">0</td>
	        <td>项</td>
	      </tr>
	    </table>
	  </div>
  </s:iterator>
  
  
  
  
</div>
</body>
</html>
