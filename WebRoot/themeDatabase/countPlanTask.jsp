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
<title>工程建设进度统计</title>
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
		<script type="text/javascript" src="js/highcharts.js"></script>
		<link type="text/css" href="<%=basePath %>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>	

	<script type="text/javascript">
	function showColumn1(obj,obj2){
        $('#chart1').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '（全网）项目工程建设总进度'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: obj2
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                allowDecimals:false
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            credits:{
				enabled:false
            },
            series: [{
                name: '工程总量',
                data: obj[0]
    
            }, {
                name: '累计完成量（至上年底）',
                data: obj[1]
    
            }]
        });
	}

	function showColumn2(obj,obj2){
        $('#chart2').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '（全网）本年度项目工程建设进度'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: obj2
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                allowDecimals:false
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            credits:{
				enabled:false
            },
            series: [{
                name: '本年度计划量',
                data: obj[2]
    
            }, {
                name: '本年度已完成量',
                data: obj[3]
    
            }]
        });
	}
	
	function listCountData(obj2){
		$.ajax({
			url: "/portal/themeDatabase/countPlanTask.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length==4){
					var addHtml = "";
					for(var i=0;i<obj[0].length;i++){
						addHtml += "<tr><td class='t_c'>"+obj2[i]+"</td><td class='t_c'>"+obj[0][i]+"</td><td class='t_c'>"+obj[1][i]+"</td><td class='t_c'>"+obj[2][i]+"</td><td class='t_c'>"+obj[3][i]+"</td></tr>";
					}
					$("#dataTbody").append(addHtml);
				  showColumn1(obj,obj2);
				  showColumn2(obj,obj2);
              }
			}
		});	
	}

	function findAllTypes(){
		$.ajax({
			url: "/portal/themeDatabase/findAllTypes.action",
			type: 'post', 
			dataType: 'json', 
			error:function(){
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
              if(obj!=null&&obj.length>0){
            	  listCountData(obj);
              }
			}
		});	
	}

	$(function(){
		findAllTypes();
	})
    </script>
</head>

<body>	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">工程建设进度统计</li>
                </ul>
            </div>
            <div style="display:none;" class="fr lit_nav nwarp">
            	<ul>
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="#">打开树</a></li>
                    <li><a class="filterClose" href="#">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
      <div class="pt45">
        <!--Filter-->
       <div id="chart1"></div>   
      <div id="chart2"></div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <th class="t_c">类型</th>
                                <th class="t_c">工程总量</th>
                                 <th class="t_c">累计完成量（至上年底）</th>
                                <th class="t_c">本年度计划量</th>
                                 <th class="t_c">本年度已完成量</th>
                                </tr>
                              </thead>
                              <tbody id="dataTbody">
                              </tbody>
                             </table>
		</div>
        <!--Table End-->
</div>
</div>
</body>
</html>