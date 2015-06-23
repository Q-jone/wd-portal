<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>会议管理统计报表</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
        <script src="<%=basePath %>/js/jquery.formalize.js"></script>
        <link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		<style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>	

	<script type="text/javascript">
		var todate = new Date();
		var toyear = todate.getFullYear();
		function showYear(){
			var addHtml = "";
			for(var i=0;i<3;i++){
				if(toyear-2+i>2013){
					addHtml += "<option value='"+(toyear-2+i)+"'>"+(toyear-2+i)+"年度</option>";
				}
			}
			$("[name=year]").append(addHtml);
		}

		function showMonth(){
			$("[name=month]").remove();
			var addHtml = "<select name='month'>";
	 		if($("[name=timeFrame]").val()=='1'){
	 			for(var i=1;i<13;i++){
					addHtml += "<option value='"+i+"'>"+i+"月</option>";
	 			}
	 		}else if($("[name=timeFrame]").val()=='2'){
	 			for(var i=1;i<5;i++){
					addHtml += "<option value='"+i+"'>"+i+"季度</option>";
	 			}
	 		}else if($("[name=timeFrame]").val()=='3'){
	 			addHtml += "<option value='1'>上半年度</option><option value='2'>下半年度</option>";
	 		}
	 		addHtml += "</select>";
	 		if($("[name=timeFrame]").val()=='4'){
	 			addHtml = "";
	 		}
	 		$("[name=year]").after(addHtml);
		}

	 	$(function(){
	 		showYear();
	 		$("[name=year]").val(toyear);
	 		if($("#h_timeFrame").val()!=""){
				$("[name=timeFrame]").val($("#h_timeFrame").val());
	 		}
	 		showMonth();
	 		if($("#h_year").val()!=""){
				$("[name=year]").val($("#h_year").val());
	 		}
	 		if($("#h_month").val()!=""){
				$("[name=month]").val($("#h_month").val());
	 		}
	 		if($("#h_meetingType").val()!=""){
				$("[name=meetingType]").val($("#h_meetingType").val());
	 		}
	 		if($("[name=countType]").val()=="1"){
				$("#table1").show();
				$("#table2").hide();
				$("[name=meetingType]").hide();
	 		}
		 })
    </script>
</head>

<body>	
	
	
	<div class="main" style="background-image:none;">
    	<!--Ctrl-->
        <!--Ctrl End-->
      <div style="padding-top:10px;">
      
        <!--Filter-->
      <div class="">
        	<div class="query">
        	<div class="p8 ">
        	<s:form action="meetingRoomCount" id="form" method="post"  namespace="/meetingCount">
        	 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	     <td class="t_r">时间范围：</td>
        	     <td>
        	     	 <select name="timeFrame" onchange="showMonth();">
                    	<option value="1">按月度统计</option>
                    	<option value="2">按季度统计</option>
                    	<option value="3">按半年度统计</option>
                    	<option value="4">按年度统计</option>
                    </select>
                    <input type="hidden" id="h_timeFrame" value="<s:property value='#request.timeFrame'/>">
                    <input type="hidden" id="h_year" value="<s:property value='#request.year'/>">
                    <input type="hidden" id="h_month" value="<s:property value='#request.month'/>">
                    <input type="hidden" id="h_meetingType" value="<s:property value='#request.meetingType'/>">
                    <input type="hidden" name="countType" value="<s:property value='#request.countType'/>">
                    <select name="year"></select>
                    <select name="meetingType">
                    	<option value="1">会议总计</option>
                    	<option value="2">会议室会议合计</option>
                    	<option value="3">视频会议合计</option>
                    </select>
                    <input type="submit" value="统 计" />
        	     </td>
      	      	</tr> 
      	    </table>
      	    </s:form>
      	    </div>
        	</div>     
		      </div>


      
        <!--Filter End-->
        <!--Table-->
       <div align="center" style="font-size:20px;font-weight: bold;"> 集团机关召开会议情况统计</div>
       <div align="right" style="font-size:15px;margin-right:20px;"><s:property value="#request.dateString"/></div>
        <div align="center">
       		<table id="table1" width="98%" style="border-collapse:collapse;background-color:white;font-size:15px;display:none;">
                              <tbody>
                              <tr style="background-color:#CCCCCC;height:50px;">
                                <td class="t_c" style="width:10%;border:1px solid #000;vertical-align:middle;" rowspan="2"></td>
                                <td class="t_c" style="width:20%;border:1px solid #000;vertical-align:middle;" rowspan="2">会议形式及分类</td>
                                <td class="t_c" style="width:10%;border:1px solid #000;vertical-align:middle;" colspan="2">集团会议室会议</td>
                                <td class="t_c" style="width:10%;border:1px solid #000;vertical-align:middle;" colspan="2">视频会议</td>
					        	<td class="t_c" style="width:20%;border:1px solid #000;vertical-align:middle;" colspan="4">合计</td>
					        	<td class="t_c" style="width:30%;border:1px solid #000;vertical-align:middle;" colspan="6">总计</td>
                              </tr>
                              <tr style="background-color:#CCCCCC;height:50px;">
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">次</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">人数</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">次</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">人数</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">次</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">%</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">人数</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">%</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">次</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">环比</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">同比</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">人数</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">环比</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;">同比</td>
                              </tr>
                              <tr style="height:50px;">
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;" rowspan="4">按等级分类</td>
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">30人以上或10个部室单位以上</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(1)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(1)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(1)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(1)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="4"><s:property value="#request.sumAll1"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="4"><s:property value="#request.huanbi1"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="4"><s:property value="#request.tongbi1"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="4"><s:property value="#request.sumAll2"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="4"><s:property value="#request.huanbi2"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="4"><s:property value="#request.tongbi2"/></td>
                                </tr>
                                <tr style="height:50px;">
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">8~30人或3~10个部室单位</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(0)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(0)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(0)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(0)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[0]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[0]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[0]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[0]"/></td>
                                </tr>
                                <tr style="height:50px;">
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">工作例会</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(2)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(2)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(2)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(2)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[2]"/></td>
                                </tr>
                                <tr style="height:50px;">
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">其他会议</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(3)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list1.get(3)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(3)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list2.get(3)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[3]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[3]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[3]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[3]"/></td>
                                </tr>
                                
                                <tr style="height:50px;">
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;" rowspan="3">按会议主题分类</td>
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">党群工作</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list3.get(1)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list3.get(1)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list4.get(1)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list4.get(1)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[5]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[5]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[5]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[5]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="3"><s:property value="#request.sumAll1"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="3"><s:property value="#request.huanbi1"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="3"><s:property value="#request.tongbi1"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="3"><s:property value="#request.sumAll2"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="3"><s:property value="#request.huanbi2"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;" rowspan="3"><s:property value="#request.tongbi2"/></td>
                                </tr>
                                <tr style="height:50px;">
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">行政事务</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list3.get(0)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list3.get(0)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list4.get(0)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list4.get(0)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[4]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[4]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[4]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[4]"/></td>
                                </tr>
                                <tr style="height:50px;">
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">对外接待</td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list3.get(2)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list3.get(2)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list4.get(2)[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.list4.get(2)[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum1[6]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per1[6]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sum2[6]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.per2[6]"/></td>
                                </tr>
                                <tr style="height:50px;">
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">合计/2</td>
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sumDown[0]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sumDown[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sumDown[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.sumDown[3]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                </tr>
                                <tr style="height:50px;">
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;">%/2</td>
                               	<td class="t_c" style="border:1px solid #000;vertical-align:middle;background-color:#CCCCCC;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.perDown[0]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.perDown[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.perDown[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.perDown[3]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                                </tr>
                              </tbody>
                            </table>
                            
            <table id="table2" width="98%" style="border-collapse:collapse;background-color:white;font-size:15px;">
                              <tbody>
                              <tr style="background-color:#CCCCCC;height:50px;">
                                <td class="t_c" style="width:22%;border:1px solid #000;vertical-align:middle;">部室名称</td>
                                <td class="t_c" style="width:13%;border:1px solid #000;vertical-align:middle;">次数</td>
                                <td class="t_c" style="width:13%;border:1px solid #000;vertical-align:middle;">占比(%)</td>
                                <td class="t_c" style="width:13%;border:1px solid #000;vertical-align:middle;">人数</td>
					        	<td class="t_c" style="width:13%;border:1px solid #000;vertical-align:middle;">占比(%)</td>
					        	<td class="t_c" style="width:13%;border:1px solid #000;vertical-align:middle;">次数环比(%)</td>
					        	<td class="t_c" style="width:13%;border:1px solid #000;vertical-align:middle;">次数同比(%)</td>
                              </tr>
                              <s:iterator value="#request.showlist" id="data" status="s">
                              <tr style="height:50px;">
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[0]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[1]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[2]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[3]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[4]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[5]"/></td>
                                <td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#data[6]"/></td>
                                </tr>
                                </s:iterator>
                              <tr style="background-color:#8DB6CD;height:50px;">
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;">合计</td>
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.count_sum"/></td>
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;">100%</td>
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;"><s:property value="#request.people_sum"/></td>
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;">100%</td>
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                              	<td class="t_c" style="border:1px solid #000;vertical-align:middle;"></td>
                              </tr>
                              </tbody>
                            </table>

      </div>
      <br>&nbsp;

        <!--Table End-->
</div>
</div>
</body>
</html>