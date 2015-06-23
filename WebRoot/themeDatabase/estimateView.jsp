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
<title>成本概算统计</title>
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
	<style>
	.fontRed {color:red;}
	</style>
	<script type="text/javascript">

		$(function(){
			$("#tbody").find("tr:last").attr("style","background-color:#FFF8DC");

			var lineArray = new Array();
			var numArray1 = new Array();
			var numArray2 = new Array();
			var numArray3 = new Array();
			$("#tbody").find("tr").each(function(i){
				if(i!=$("#tbody").find("tr").length-1){
					lineArray[i] = $(this).children("td:eq(0)").html();
					numArray1[i] = parseFloat($(this).children("td:eq(1)").html());
					numArray2[i] = parseFloat($(this).children("td:eq(5)").html());
					numArray3[i] = parseFloat($(this).children("td:eq(6)").html());
				}
			});
			showColumn(lineArray,numArray1,numArray2,numArray3);
		});

		function showColumn(lineArray,numArray1,numArray2,numArray3){
	        $('#chart').highcharts({
	            chart: {
	                type: 'column'
	            },
	            title: {
	                text: '成本概算统计'
	            },
	            subtitle: {
	                text: ''
	            },
	            xAxis: {
	                categories: lineArray
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
	                    '<td style="padding:0"><b>{point.y} 万元</b></td></tr>',
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
	                name: '概算',
	                data: numArray1
	    
	            }, {
	                name: '已发生成本',
	                data: numArray2
	    
	            },{
	                name: '成本超概',
	                data: numArray3
	    
	            }]
	        });
		}
    </script>
</head>

<body>	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img id="show" onclick="showHide();" src="<%=basePath %>/css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">成本概算统计</li>
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
      <div id="chart"></div>   
        <!--Table-->
        <div class="mb10" id="listDiv">
        	<table width="100%;"  class="table_1" id="mytable">
        				<thead>
                              <tr class="tit">
                                <th class="t_c" style="white-space:nowrap;">项目</th>
                                 <th class="t_c" style="white-space:nowrap;">概算(万元)</th>
                                <th class="t_c" style="white-space:nowrap;">已签合同(万元)</th>
                                <th class="t_c" style="white-space:nowrap;">已批准变更(万元)</th>
                                <th class="t_c" style="white-space:nowrap;">待批准变更(万元)</th>
                                <th class="t_c" style="white-space:nowrap;">已发生成本(万元)</th>
                                <th class="t_c" style="white-space:nowrap;">成本超概(万元)</th>
                                </tr>
                              
                              </thead>
                              <tbody id="tbody">
	                             <s:iterator value="#request.showList" id="data" status="b">
	                             <tr>
	                             	<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[0]" /></td>
									<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[1]" /></td>
									<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[2]" /></td>
									<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[3]" /></td>
									<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[4]" /></td>
									<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[5]" /></td>
									<td style="white-space:nowrap;border:1px solid #ccc;" class="t_c"><s:property value="#data[6]" /></td>
									
								  </tr>
								</s:iterator>
                              </tbody>
                              <tr class="tfoot">
                              	<td colspan="50">&nbsp;</td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>