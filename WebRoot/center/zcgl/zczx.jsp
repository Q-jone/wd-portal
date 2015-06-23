<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
		<head>
		<meta charset="utf-8" />
		<title>资产中心首页</title>
		<link rel="stylesheet" href="../../css/formalize.css" />
		<link rel="stylesheet" href="../../css/page.css" />
		<link rel="stylesheet" href="../../css/default/imgs.css" />
		<link rel="stylesheet" href="../../css/reset.css" />
		<link type="text/css" href="../../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
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
			
		
		</script>
		
		<style type="text/css">
		body{
			background:url(../../css/default/images/win8_bg.jpg) left top repeat-x;
		}
		.win8style {
			width:950px;
			padding:0 5px;
			margin:0 auto;
		}
		.win_1{
			width:630px;
			height:282px;
			margin-right:10px;
		}
		/**.win_2{
			width:270px;
			height:282px;
			padding-left:40px;
			background:#f6f6f6;
		}*/
		.win_2{
			width:310px;
			height:282px;
			background:#f6f6f6;
		}
		.win_3{
			width:430px;
			height:180px;
			margin-right:10px;
		}
		.win_4{
			width:270px;
			height:180px;
			margin-right:10px;
		}
		.win_5{
			width:230px;
			height:180px;
		}
		.win_1, .win_3, .win_4, .win_5{
			position:relative;
			background:#fff;
		}
		.w_tit_1{
			height:28px;
			line-height:28px;
			background:#0d2449;
			filter: alpha(opacity = 50);
			-moz-opacity: 0.50;
			opacity: 0.50;
			color:#fff;
			font-weight:bold;
			padding:0 8px;
			overflow:hidden;
			position:absolute;
			top:152px;
			z-index:10;
		}
		.win_3 .w_tit_1{
			width:414px;
		}
		.win_4 .w_tit_1{
			width:254px;
		}
		.win_5 .w_tit_1{
			width:214px;
		}
		.w_tit_2{
			filter: alpha(opacity = 50);
			-moz-opacity: 0.50;
			opacity: 0.50;
			background:#000;
			height:56px;
			line-height:50px;
			position:absolute;
			top:190px;
			padding:0 10px;
			color:#fff;
			width:610px;
			font-size:25px;
			
		}
		.w_tit_3{
			background:#000;
			height:37px;
			line-height:40px;
			position:absolute;
			top:246px;
			padding:0 10px;
			color:#fff;
			width:610px;
			font-size:16px;
		}
		.tie{
			background:url(../../css/default/images/tie_bg.png) left 45px repeat-x;
			filter: alpha(opacity = 70);
			-moz-opacity: 0.70;
			opacity: 0.70;
			height:55px;
		}
		.tie .con{
			
			background:#E0EEEE;
			width:100%;
			height:45px;
			font-weight:bold;
			position:relative;
			margin:0 auto;
		}
		.tie .con ul.line001{
			width:950px;
			margin:0 auto;
			line-height:45px;
		}
		.tie .con ul li{
			float:left;
			display:inline;
			_display:inline-block;
		}
		.tie .con ul li{
			padding:0 8px;
		}
		 .con_2{
			filter: alpha(opacity = 70);
			-moz-opacity: 0.70;
			opacity: 0.70;
			background:#E0EEEE;
			position:absolute;
			top:383px;
			width:100%;
			z-index:9999;
			height:90px;
		}
		.con_2 ul.line002{
			width:950px;
			margin:0 auto;
			line-height:30px;
		}
		.con_2 .line002 li{
			float:left;
			display:inline;
			_display:inline-block;
			padding:0 14px;
			color:red;
			white-space:nowrap;
		}
		
		.table_1 td.fontFirst{
			font-weight:bold;
			font-size:16px;
			color:gray;
			border-left:1px solid gray;
			vertical-align:middle;
		}
		
		.table_1 td{
			font-size:14px;
			color:gray;
			border-right:0;
			border-top:0;
			border-bottom:1px solid gray;
			vertical-align:middle;
		}
		
		li.line{float:left; margin:8px; border-bottom-width:3px; }
		
		.win_2 span, .win_2 h2 , .win_2 b {display:inline;font-weight：normal;}
		
		.lineN{
			
			height:6px;
			display:inline;
			overflow:hidden;
			margin: 0 5px;
		}
		
		.lineH{
			width:50px;
			height:8px
		}
		
		.lineList{
			margin:10px 10px;
			height:186px;
		}
		
		.lineList  li{
			float:left;
			margin: 10px 8px;			
			padding:0px 8px;
			white-space:nowrap;
		}
		
		.assetList{
			margin:10px 10px;
			height:160px;
			padding:10px 0;
		}
		
		.assetList li{
			float:left;
			margin: 5px 5px;			
			padding:0px 3px;
			font-size:14px;
		}
		
		
				
		.Lbgb_01{
		      border-bottom:5px solid #E70012;
		}
		.Lbgb_02{
		       border-bottom:5px solid #8DC11F;
		}
		.Lbgb_03{
		       border-bottom:5px solid #FED700;
		}
		.Lbgb_04{
		       border-bottom:5px solid #471E86;
		}
		.Lbgb_05{
		       border-bottom:5px solid #944B9A;
		}
		.Lbgb_06{
		       border-bottom:5px solid #D30067;
		}
		.Lbgb_07{
		       border-bottom:5px solid #ED6D00;
		}
		
		.Lbgb_08{
		       border-bottom:5px solid #0194D9;
		}
		
		.Lbgb_09{
		       border-bottom:5px solid #7ACDF5;
		}
		.Lbgb_010{
		       border-bottom:5px solid #871B2B;
		}
		.Lbgb_011{
		       border-bottom:5px solid #8e2323;
		}
		.Lbgb_012{
			 border-bottom:5px solid #10614A;
		}
		.Lbgb_013{
			 border-bottom:5px solid #F4B8D2;
		}
		
		hgroup{
			line-height:35px;
		}
	
        </style>
		</head>

		<body>
		<div class="con_2" style="display:none">
                    	<ul class="line002">
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>
                        	<li>XX站</li>    
                        </ul>
                    </div>
        <div> 
        <!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:void(0);">资产管理</a></li>
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
          <div class="clearfix pt45">
          	<div class="win8style clearfix" style="padding-bottom:10px;">
            	<div class="win_1 fl">
            	<table id="ownerSumTable" class="table_1" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
	                    <td style="width:20%;" class="t_l fontFirst">权属单位</td>
	                    <td style="width:20%;" class="t_l fontFirst">项目来源</td>
	                    <td style="width:20%;" class="t_l fontFirst">总值（万元）</td>
	                    <td style="width:20%;" class="t_l fontFirst">现值（万元）</td>
	                    <td style="width:20%;" class="t_l fontFirst">大类价值分布   </td>
                  	</tr>
                </table>
                	<div class="w_tit_2">固定资产台账</div>
                	<div class="w_tit_3">
                		编号：<input type="text" value="" id="assetId"/>
                		名称：<input type="text" value="" id="assetName" disabled="disabled"/>
                		<input type="hidden" id="paramId">
                		<input type="button" value="检索" onclick="searchForAsset()">&nbsp;
                		<input type="button" value="高级检索" onclick="moreSearch()">
                	</div>
                </div>
                <div class="win_2 fl">
                	<hgroup class="clearfix" style="background-color:#8B5A00;color:#fff;">
                	<h2 class="fl">&nbsp;线路资产</h2>
                	<h4 class="fr mr5">总线路：<b class="L_07 "style="font-size:20px;">13</b>条</h4>
                	</hgroup>
                	<dl class="clearfix" style="padding-right:8px;">
                		<dd class="fr"><h6><span style="color:gray;display:inline;" id="allAssetSpan"></span></h6></dd>
                		<dt class="fr"><h6>全线资产总数:</h6></dt>
                	</dl>
                	<ul class="lineList">
                		<li class="t_c Lbgb_01"><a href="javascript:void(0)">01号线</a></li>
                		<li class="t_c Lbgb_02" ><a href="javascript:void(0)">02号线</a></li>
                		<li class="t_c Lbgb_03"><a href="javascript:void(0)">03号线</a></li>
                		<li class="t_c Lbgb_04"><a href="javascript:void(0)">04号线</a></li>
                		<li class="t_c Lbgb_05"><a href="javascript:void(0)">05号线</a></li>
                		<li class="t_c Lbgb_06"><a href="javascript:void(0)">06号线</a></li>
                		<li class="t_c Lbgb_07"><a href="javascript:void(0)">07号线</a></li>
                		<li class="t_c Lbgb_08"><a href="javascript:void(0)">08号线</a></li>
                		<li class="t_c Lbgb_09"><a href="javascript:void(0)">09号线</a></li>
                		<li class="t_c Lbgb_010"><a href="javascript:void(0)">10号线</a></li>
                		<li class="t_c Lbgb_011"><a href="javascript:void(0)">11号线</a></li>
                		<li class="t_c Lbgb_012"><a href="javascript:void(0)">12号线</a></li>
                		<li class="t_c Lbgb_013"><a href="javascript:void(0)">13号线</a></li>
                	</ul>
	               <div class="clearfix  t_r" style="background-color:#CD950C;color:#fff;padding-right:8px;">
	               		<h4 id="show_today"></h4>
	               	</div>

                </div>
            </div>
            <div class="mb10 tie" style="display:none">
            	<div class="con">
                    <ul class="line001" id="allLineUl">
                    	<li><a href="javascript:void(0);">全网</a></li>
                    	<li><a href="javascript:void(0);" style="background-color:white;"><div class="lineN Lbg_01">&nbsp;</div>01号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_02">&nbsp;</div>02号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_03">&nbsp;</div>03号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_04">&nbsp;</div>04号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_05">&nbsp;</div>05号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_06">&nbsp;</div>06号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_07">&nbsp;</div>07号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_08">&nbsp;</div>08号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_09">&nbsp;</div>09号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_010">&nbsp;</div>10号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_011">&nbsp;</div>11号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_012">&nbsp;</div>12号线</a></li>
                    	<li><a href="javascript:void(0);"><div class="lineN Lbg_013">&nbsp;</div>13号线</a></li>
                    </ul>
                	
                </div>
            </div>
          	<div class="win8style clearfix">
            	<div class="win_3 fl">
            		<div class="clearfix">
		            	<div class="fl" id="assetCount"></div>
		            	<div class="fr" id="assetSum"></div>
	            	</div>
	                <div class="w_tit_1">资产条目及价值</div>
                </div>
                <div class="win_4 fl">
                	<div id="assetColumn"></div>
                	<div class="w_tit_1">专项资产价值</div>
                </div>
                <div class="win_5 fl">
                	<div>
                		<ul class="assetList">
	                		<li class="t_c">待编资产</li>
	                		<li class="t_c L_07" >12345</li>
	                		<li class="t_c L_01">25%</li>
	                		<li class="t_c L_07">4</li>
	                		<li class="t_c">更新资产</li>
	                		<li class="t_c L_07" >12345</li>
	                		<li class="t_c L_01">25%</li>
	                		<li class="t_c L_07">4</li>
	                		<li class="t_c">改造资产</li>
	                		<li class="t_c L_07" >12345</li>
	                		<li class="t_c L_01">25%</li>
	                		<li class="t_c L_07">4</li>
	                		<li class="t_c">大修资产</li>
	                		<li class="t_c L_07" >12345</li>
	                		<li class="t_c L_01">25%</li>
	                		<li class="t_c L_07">4</li>	
                	</ul>
                	</div>
                	<div class="w_tit_1"><span class="fr">截止日期：2013-02-04</span></div>
                </div>
            </div>
          </div>
        </div>
</body>
</html>
