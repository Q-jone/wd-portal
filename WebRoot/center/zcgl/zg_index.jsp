<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资产管理首页</title>
<link rel="stylesheet" href="../../css/formalize.css" />

<link rel="stylesheet" href="../../css/page.css" />
<link rel="stylesheet" href="../../css/default/imgs.css" />
<link rel="stylesheet" href="../../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       	<script src="../../js/jquery-1.7.1.js"></script>
        <script src="../../js/html5.js"></script>     
		<script src="../../js/jquery.formalize.js"></script>
		<script src="../../js/highcharts.js"></script>
		<script src="../../js/jquery.sparkline.min.js"></script>
		<script src="../../js/show.js"></script>
		<script src="js/zcgl_ajax.js"></script>
		<link type="text/css" href="../../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script src="../../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		
		<script type="text/javascript">
			$(function(){
				getAssetInfo("二号线","",
							"QUANTITY_TYPE1,QUANTITY_TYPE2,QUANTITY_TYPE5,QUANTITY_TYPE4",
							"线路,房屋建筑,通信,车辆",
							"VALUE_TYPE1,VALUE_TYPE2,VALUE_TYPE5,VALUE_TYPE4",
							"线路,房屋建筑,通信,车辆",
							"1",
							"assetCount",
							"assetSum"
				);
				
				getInventoryInfo("","",
							"OWNER_COMPANY,LINE,NOW_VALUE,ALL_VALUE",
							"权属单位,线路,总值,现值",
							"VALUE_TYPE1,VALUE_TYPE2,VALUE_TYPE5,VALUE_TYPE4",
							"线路,房屋建筑,通信,车辆",
							"3",
							"ownerSum"
				)
			})
		</script>
</head>

<body>
	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/zcgl/zg_index.jsp'">资产管理</a></li>
                	<li class="fin">首页</li>
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
	</div>
	
	 <div style="position:relative;top:200px;border:1px solid red; width:800px;margin:0 auto;">
     	<div style="position:relative;width:500px;margin:0 auto;" id="assetCount"> </div>
     	<div style="position:relative;width:500px;margin:0 auto;" id="assetSum"> </div>
     </div>
     
     <div id="ownerSumDiv" style="position:relative;top:700px;border:1px solid red; width:800px;margin:0 auto;">
     	
     </div>
</body>
</html>
