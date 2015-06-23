<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.FileInputStream"%>
<%
String urlCa = "";
Properties properties = new Properties();
String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
try {
	FileInputStream fis = new FileInputStream(configPath);
	properties.load(fis);
	urlCa = properties.getProperty("urlCa");			
} catch (Exception e) {
	
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>建设管理首页</title>
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
       	<script src="../mscp/js/highcharts.js"></script>
        <script src="../../js/html5.js"></script>     
		<script src="../../js/jquery.formalize.js"></script>
		<script src="../../js/show.js"></script>
		<script src="js/jsgl_ajax.js"></script>
		
		<link type="text/css" href="../../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script src="../../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="../../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
	
		<style type="text/css">
			/*demo page css*/
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
			.ui-datepicker-title span {display:inline;}
			/*隐藏datepicker控件 currentDay按钮*/
			 button.ui-datepicker-current { display: none; }
			
		</style>	
		<style>
			.expressDetail {display:none;position:absolute;width:380px;height:auto;background:#FBFACC;}
			
			.table_tr {border-top:#ccc 1px solid;border-bottom:#ccc 1px solid;line-height:32px;}
			.table_td {border-right:#ccc 1px solid;}
		</style>
		
		<script type="text/javascript">
		
		/* function date2str(x,y) { 
			var z = {M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()}; 
			y = y.replace(/(M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-2)}); 
			return y.replace(/(y+)/g,function(v) {return x.getFullYear().toString().slice(-v.length)}); 
			} 
			alert(date2str(new Date(),"yyyy-MM-dd hh:mm:ss")); 
			alert(date2str(new Date(),"yyyy-M-d h:m:s")); 
		
		 */
		
		
		//function showTable1(null,paperid);
		$(function(){
			//showTable1(null);
			
			showTable1(null,'3');//施工许可、规划用地、规划方案
			
			showTable2();//投资管理
			showTable3();//平台应用
			//右侧前期办证、安全监控。车辆预警
			getLatestNewsAside("514","","0");
			getLatestNewsAside("371","","1");
			getZttp("2");//车辆预警信息
			//select * from zttp t where part = 'czyj' order by id desc
		});
		
		$(document).ready(function () {
			var yearOption="<option value='all'>全部</option><option value='now'>截止今天</option>";
			for(var i=2010;i<2016;i++){//建设动态选择年份
				//alert(i);
				yearOption+="<option value='"+i+"'>"+i+"</option>";
			}
			//alert(yearOption);
			$("#year").empty().append(yearOption);
            
			
			var $tblAlterRow = $("table tbody tr:odd");
            if ($tblAlterRow.length > 0)
                $tblAlterRow.css("background","#f8f8f8");
			$("#year").change(function(){
				//alert($("#year").val()+"------"+$("#paperId").val()+"====="+new Date().getDate());//new Date().getFullYear()
				if("审批部门维度"==$("#paperId").val()){
					showTable1_3(null);
				}else if("审批部门维度"==$("#paperId").val()){
					showTable1_2(null);
				}else{
					showTable1(null,$("#paperId").val());
				}
			});
            $("[id=whiteTr]").attr("style","background:#fff;line-height:30px;");   
            $("[id=whiteTr1]").attr("style","background:#fff;line-height:36px;");   
            $("[id=grayTr]").attr("style","background:#bababa;line-height:42px; ");  
            
            $(window).resize(function(){
            	//$("#pic1").width($(".gray_border").width()-16);
            	if("审批部门维度"==$("#paperId").val()){
            		showTable1_3(null);
            	}else if("证件维度"==$("#paperId").val()){
            		showTable1_2(null);
            	}else{
            		showTable1(null,$("#paperId").val());
            	}
            	showTable2();//投资管理
    			showTable3();//平台应用
            });
            

        });
		//时间
		function check(paperId){
			alert(paperId);
			//showTable1(null,paperId,$("#year").val());
		}

        function showDiv(showDiv,hideDiv){
			$("#"+showDiv).show();
			$("#"+hideDiv).hide();
        }

        function showBlock(obj){
			if($(obj).val()=="0"){
				$("#block0").show();
				$("#block1").hide();
				$("#ul0").show();
				$("#ul1").hide();
			}else if($(obj).val()=="1"){
				$("#block1").show();
				$("#block0").hide();
				$("#ul1").show();
				$("#ul0").hide();
			}
        }
		</script>
</head>

<body>
	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/jsgl/jsgl_index.jsp'">建设管理</a></li>
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
        	<div class="right_main mb10">
			 <!--Panel_8-->
            	<div class="panel_4 mb10 panel_8">
                	<header>
                    	<div class="tit">
                        	<div class="bg clearfix">
                    			<h5 class="fl stats">建设动态</h5>
                                <div class="fr pt5 clearfix">
	                                <h6 style="float:left;color:black;font-weight:bold;font-size:7 ">年份选择:</h6><select class="input_small mr5 fl" id="year">
		                                  <option value="all">全部</option>
		                                  <option value="2012">2012</option>
		                                  <option value="2013">2013</option>
		                                  <option value="2014">2014</option><!-- 前期办证 -->
		                                  <option value="2015">2015</option>
		                                  <option value="2016">2016</option>
		                                  <option value="2017">2017</option>
	                                </select>
	                                <ul class="clearfix fl mr5 pt5">
	                                	<li class="fl mr5"><a href="javascript:void(0);" title="以表格形式展现" onclick="showDiv('table_1','pic1')"><img src="imgs/table.png"/></a></li>
	                                	<li class="fl mr5"><a href="javascript:void(0);" title="以图表形式展现" onclick="showDiv('pic1','table_1')"><img src="imgs/chart.png"/></a></li>
	                                </ul>  
								</div>
								<span id="remind" class="mr5 fr"></span>
                            </div>
                        </div>
                    </header>
                    <div class="con clearfix"><div class="gray_border">
                    	<div class="block">
	                    	<div class="clearfix">
		                    	<h3 class="fl plr8" id="h3">各项目“施工许可证”办理情况</h3>
		                    	 
		                    	<ul class="fr mlr10 clearfix">
		                    		
		                    		<li class="fl mr5">
		                    			<a class="Lb_08" href="javascript:void(0)" onclick="showTable1(this,'3');">规划用地</a><!-- showTable1(this,'3') -->
		                    		</li>
		                    		<li class="fl mr5">
		                    			<a href="javascript:void(0)" onclick="showTable1(this,'2');">规划方案</a><!-- showTable1(this,'2') -->
		                    		</li>
		                    		<li class="fl mr5">
		                    			<a href="javascript:void(0)" onclick="showTable1(this,'20');">施工许可</a><!-- showTable1(this,'20') -->
		                    		</li>
		                    		<li class="fl mr5">
		                    			<a href="javascript:void(0)" onclick="showTable1_2(this);">证件维度</a><!--  -->
		                    		</li>
		                    		<li class="fl mr5">
		                    			<a href="javascript:void(0)" onclick="showTable1_3(this);">审批部门维度</a><!--  -->
		                    		</li>
		                    	</ul>
		                    	 
		                    </div>
	                    	<div id="pic1" class="p8"></div>
	                    	<div id="table_1" style="display:none;">
	                    		<div>
	                    		<input type="hidden" id="paperId" name="paperId" value=""/>
		                    		<table style="width:100%">
		                    			<tr class="table_tr" style="background:#fff;line-height:36px;">
		                    				<td class="t_c table_td" style="font-weight: bold;">项目名称</td>
		                    				<td class="t_c table_td" style="font-weight: bold;">施工证数</td>
		                    				<td class="t_c table_td" style="font-weight: bold;">完成数</td>
		                    				<td class="t_c table_td" style="font-weight: bold;">完成比(%)</td>
		                    			</tr>
		                    		</table>
	                    		</div>
                        	</div>
                        	</div>
                        	
                        </div></div>
                </div>
                <!--Panel_8 End-->
                	<!--Panel_8-->
            	<div class="panel_4 mb10 panel_8">
                	<header>
                    	<div class="tit">
                        	<div class="bg clearfix">
                    			<h5 class="fl stats">建设管理</h5>
                                <div class="fr pt5 clearfix" width="300px">
	                                <select class="input_small mr5 fl" width="70px" onchange="showBlock(this);">
	                                  <option value="0">投资进度</option>
	                                  <option value="1">平台应用</option>
	                                </select> 
	                                <ul class="clearfix fl mr5 pt5" id="ul0">
	                                	<li class="fl mr5"><a href="javascript:void(0);" title="以表格形式展现" onclick="showDiv('table_2','pic2')"><img src="imgs/table.png"/></a></li>
	                                	<li class="fl mr5"><a href="javascript:void(0);" title="以图表形式展现" onclick="showDiv('pic2','table_2')"><img src="imgs/chart.png"/></a></li>
	                                	<li class="fl mr5"><a href="<%=urlCa%>/iamCross.jsp?target=Greata" target="_blank" title="登录建管平台"><img src="imgs/monitor-network.png"/></a></li>
	                                </ul>  
	                                
	                                <ul class="clearfix fl mr5 pt5" id="ul1" style="display:none;">
	                                	<li class="fl mr5"><a href="javascript:void(0);" title="以表格形式展现" onclick="showDiv('table_3','pic3')"><img src="imgs/table.png"/></a></li>
	                                	<li class="fl mr5"><a href="javascript:void(0);" title="以图表形式展现" onclick="showDiv('pic3','table_3')"><img src="imgs/chart.png"/></a></li>
	                                	<li class="fl mr5"><a href="<%=urlCa%>/iamCross.jsp?target=GREATA" target="_blank" title="登录建管平台"><img src="imgs/monitor-network.png"/></a></li>
	                                </ul>
								</div>
								<span id="remind" class="mr5 fr"></span>
                            </div>
                        </div>
                    </header>
                    <div class="con clearfix"><div class="gray_border">
                    	<div class="block" id="block0" style="background-image:none;">
	                    	<div class="clearfix">
		                    	<h3 class="fl plr8">各线路“成本概算”情况</h3>
		                    </div>
	                    	<div id="pic2" class="p8"></div>
	                    	<div id="table_2" style="display:none;overflow-y:scroll;height:250px;">
	                    		<table style="width:100%;">
	                    			<tr class="table_tr" style="background:#fff;line-height:36px;" id="whiteTr1">
	                    				<td id="td_0_0" class="t_c table_td" style="font-weight: bold;"></td>
	                    				<td id="td_0_1" class="t_c table_td" style="font-weight: bold;"></td>
	                    				<td id="td_0_2" class="t_c table_td" style="font-weight: bold;"></td>
	                    				<td id="td_0_3" class="t_c table_td" style="font-weight: bold;"></td>
	                    				<td id="td_0_4" class="t_c table_td" style="font-weight: bold;"></td>
	                    				<td id="td_0_5" class="t_c table_td" style="font-weight: bold;"></td>
	                    				<td id="td_0_6" class="t_c table_td" style="font-weight: bold;"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_1_0" class="t_l table_td"></td>
	                    				<td id="td_1_1" class="t_r table_td"></td>
	                    				<td id="td_1_2" class="t_r table_td"></td>
	                    				<td id="td_1_3" class="t_r table_td"></td>
	                    				<td id="td_1_4" class="t_r table_td"></td>
	                    				<td id="td_1_5" class="t_r table_td"></td>
	                    				<td id="td_1_6" class="t_r table_td"></td>
	                    			</tr>
	                    			
	                    			<tr class="table_tr">
	                    				<td id="td_2_0" class="t_l table_td"></td>
	                    				<td id="td_2_1" class="t_r table_td"></td>
	                    				<td id="td_2_2" class="t_r table_td"></td>
	                    				<td id="td_2_3" class="t_r table_td"></td>
	                    				<td id="td_2_4" class="t_r table_td"></td>
	                    				<td id="td_2_5" class="t_r table_td"></td>
	                    				<td id="td_2_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_3_0" class="t_l table_td"></td>
	                    				<td id="td_3_1" class="t_r table_td"></td>
	                    				<td id="td_3_2" class="t_r table_td"></td>
	                    				<td id="td_3_3" class="t_r table_td"></td>
	                    				<td id="td_3_4" class="t_r table_td"></td>
	                    				<td id="td_3_5" class="t_r table_td"></td>
	                    				<td id="td_3_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_4_0" class="t_l table_td"></td>
	                    				<td id="td_4_1" class="t_r table_td"></td>
	                    				<td id="td_4_2" class="t_r table_td"></td>
	                    				<td id="td_4_3" class="t_r table_td"></td>
	                    				<td id="td_4_4" class="t_r table_td"></td>
	                    				<td id="td_4_5" class="t_r table_td"></td>
	                    				<td id="td_4_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_5_0" class="t_l table_td"></td>
	                    				<td id="td_5_1" class="t_r table_td"></td>
	                    				<td id="td_5_2" class="t_r table_td"></td>
	                    				<td id="td_5_3" class="t_r table_td"></td>
	                    				<td id="td_5_4" class="t_r table_td"></td>
	                    				<td id="td_5_5" class="t_r table_td"></td>
	                    				<td id="td_5_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_6_0" class="t_l table_td"></td>
	                    				<td id="td_6_1" class="t_r table_td"></td>
	                    				<td id="td_6_2" class="t_r table_td"></td>
	                    				<td id="td_6_3" class="t_r table_td"></td>
	                    				<td id="td_6_4" class="t_r table_td"></td>
	                    				<td id="td_6_5" class="t_r table_td"></td>
	                    				<td id="td_6_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_7_0" class="t_l table_td"></td>
	                    				<td id="td_7_1" class="t_r table_td"></td>
	                    				<td id="td_7_2" class="t_r table_td"></td>
	                    				<td id="td_7_3" class="t_r table_td"></td>
	                    				<td id="td_7_4" class="t_r table_td"></td>
	                    				<td id="td_7_5" class="t_r table_td"></td>
	                    				<td id="td_7_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_8_0" class="t_l table_td"></td>
	                    				<td id="td_8_1" class="t_r table_td"></td>
	                    				<td id="td_8_2" class="t_r table_td"></td>
	                    				<td id="td_8_3" class="t_r table_td"></td>
	                    				<td id="td_8_4" class="t_r table_td"></td>
	                    				<td id="td_8_5" class="t_r table_td"></td>
	                    				<td id="td_8_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_9_0" class="t_l table_td"></td>
	                    				<td id="td_9_1" class="t_r table_td"></td>
	                    				<td id="td_9_2" class="t_r table_td"></td>
	                    				<td id="td_9_3" class="t_r table_td"></td>
	                    				<td id="td_9_4" class="t_r table_td"></td>
	                    				<td id="td_9_5" class="t_r table_td"></td>
	                    				<td id="td_9_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_10_0" class="t_l table_td"></td>
	                    				<td id="td_10_1" class="t_r table_td"></td>
	                    				<td id="td_10_2" class="t_r table_td"></td>
	                    				<td id="td_10_3" class="t_r table_td"></td>
	                    				<td id="td_10_4" class="t_r table_td"></td>
	                    				<td id="td_10_5" class="t_r table_td"></td>
	                    				<td id="td_10_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_11_0" class="t_l table_td"></td>
	                    				<td id="td_11_1" class="t_r table_td"></td>
	                    				<td id="td_11_2" class="t_r table_td"></td>
	                    				<td id="td_11_3" class="t_r table_td"></td>
	                    				<td id="td_11_4" class="t_r table_td"></td>
	                    				<td id="td_11_5" class="t_r table_td"></td>
	                    				<td id="td_11_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_12_0" class="t_l table_td"></td>
	                    				<td id="td_12_1" class="t_r table_td"></td>
	                    				<td id="td_12_2" class="t_r table_td"></td>
	                    				<td id="td_12_3" class="t_r table_td"></td>
	                    				<td id="td_12_4" class="t_r table_td"></td>
	                    				<td id="td_12_5" class="t_r table_td"></td>
	                    				<td id="td_12_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr">
	                    				<td id="td_13_0" class="t_l table_td"></td>
	                    				<td id="td_13_1" class="t_r table_td"></td>
	                    				<td id="td_13_2" class="t_r table_td"></td>
	                    				<td id="td_13_3" class="t_r table_td"></td>
	                    				<td id="td_13_4" class="t_r table_td"></td>
	                    				<td id="td_13_5" class="t_r table_td"></td>
	                    				<td id="td_13_6" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr style="background:#bababa;line-height:36px;"  id="grayTr">
	                    				<td id="td_14_0" class="t_l table_td"></td>
	                    				<td id="td_14_1" class="t_r table_td"></td>
	                    				<td id="td_14_2" class="t_r table_td"></td>
	                    				<td id="td_14_3" class="t_r table_td"></td>
	                    				<td id="td_14_4" class="t_r table_td"></td>
	                    				<td id="td_14_5" class="t_r table_td"></td>
	                    				<td id="td_14_6" class="t_r table_td"></td>
	                    			</tr>
	                    		</table>
                        	</div>
                        	</div>
                        	
                        	<div class="block" id="block1" style="display:none;">
	                    	<div class="clearfix">
		                    	<h3 class="fl plr8">GREATA系统各项目工作区使用情况统计表</h3>
		                    </div>
	                    	<div id="pic3" class="p8"></div>
	                    	<div id="table_3" style="display:none;">
	                    		<table style="width:100%">
	                    			<tr class="table_tr" style="background:#fff;line-height:18px;" id="whiteTr">
	                    				<td rowspan="2" class="t_c table_td" style="padding-top:15px;font-weight: bold;">项目名称</td>
	                    				<td colspan="2" class="t_c table_td" style="font-weight: bold;">数据量</td>
	                    				<td colspan="2" class="t_c table_td" style="font-weight: bold;">流程交易量</td>
	                    				<td colspan="2" class="t_c table_td" style="font-weight: bold;">平台登录数</td>
	                    				<td colspan="2" class="t_c table_td" style="font-weight: bold;">数据量排名</td>
	                    			</tr>
	                    			<tr class="table_tr" style="background:#fff;line-height:18px;" id="whiteTr">
	                    				<td class="t_c table_td" style="font-weight: bold;">当天</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">累计</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">当天</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">累计</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">当天</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">累计</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">昨天排名</td>
	                    				<td class="t_c table_td" style="font-weight: bold;">总排名</td>
	                    			</tr>
	                    			<tr class="table_tr" id="tr3">
	                    				<td id="td3_0_0" class="t_l table_td"></td>
	                    				<td id="td3_0_1" class="t_r table_td"></td>
	                    				<td id="td3_0_2" class="t_r table_td"></td>
	                    				<td id="td3_0_3" class="t_r table_td"></td>
	                    				<td id="td3_0_4" class="t_r table_td"></td>
	                    				<td id="td3_0_5" class="t_r table_td"></td>
	                    				<td id="td3_0_6" class="t_r table_td"></td>
	                    				<td id="td3_0_7" class="t_r table_td"></td>
	                    				<td id="td3_0_8" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr" id="tr3">
	                    				<td id="td3_1_0" class="t_l table_td"></td>
	                    				<td id="td3_1_1" class="t_r table_td"></td>
	                    				<td id="td3_1_2" class="t_r table_td"></td>
	                    				<td id="td3_1_3" class="t_r table_td"></td>
	                    				<td id="td3_1_4" class="t_r table_td"></td>
	                    				<td id="td3_1_5" class="t_r table_td"></td>
	                    				<td id="td3_1_6" class="t_r table_td"></td>
	                    				<td id="td3_1_7" class="t_r table_td"></td>
	                    				<td id="td3_1_8" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr" id="tr3">
	                    				<td id="td3_2_0" class="t_l table_td"></td>
	                    				<td id="td3_2_1" class="t_r table_td"></td>
	                    				<td id="td3_2_2" class="t_r table_td"></td>
	                    				<td id="td3_2_3" class="t_r table_td"></td>
	                    				<td id="td3_2_4" class="t_r table_td"></td>
	                    				<td id="td3_2_5" class="t_r table_td"></td>
	                    				<td id="td3_2_6" class="t_r table_td"></td>
	                    				<td id="td3_2_7" class="t_r table_td"></td>
	                    				<td id="td3_2_8" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr class="table_tr" id="tr3">
	                    				<td id="td3_3_0" class="t_l table_td"></td>
	                    				<td id="td3_3_1" class="t_r table_td"></td>
	                    				<td id="td3_3_2" class="t_r table_td"></td>
	                    				<td id="td3_3_3" class="t_r table_td"></td>
	                    				<td id="td3_3_4" class="t_r table_td"></td>
	                    				<td id="td3_3_5" class="t_r table_td"></td>
	                    				<td id="td3_3_6" class="t_r table_td"></td>
	                    				<td id="td3_3_7" class="t_r table_td"></td>
	                    				<td id="td3_3_8" class="t_r table_td"></td>
	                    			</tr>
	                    			<tr style="background:#bababa;line-height:42px;" id="grayTr">
	                    				<td id="td3_4_0" class="t_l table_td"></td>
	                    				<td id="td3_4_1" class="t_r table_td"></td>
	                    				<td id="td3_4_2" class="t_r table_td"></td>
	                    				<td id="td3_4_3" class="t_r table_td"></td>
	                    				<td id="td3_4_4" class="t_r table_td"></td>
	                    				<td id="td3_4_5" class="t_r table_td"></td>
	                    				<td id="td3_4_6" class="t_r table_td"></td>
	                    				<td id="td3_4_7" class="t_r table_td"></td>
	                    				<td id="td3_4_8" class="t_r table_td"></td>
	                    			</tr>
	                    		</table>
                        	</div>
                        	</div>
                        </div></div>
                </div>
                <!--Panel_8 End-->
            </div>
        	<!--Aside-->
            <aside class="fr">
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>前期办证</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>安全监控</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>车站预警</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
          </aside>
        	<!--Aside End-->
        </div>
	</div>
</body>
</html>
