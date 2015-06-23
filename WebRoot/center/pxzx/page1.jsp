<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>培训情况统计</title>

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
<!--<script src="../js/html5.js"></script>-->
<script src="../../js/jquery-1.7.1.js"></script>
<script src="../../js/jquery.formalize.js"></script>
<script src="../../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../mscp/js/highcharts.js"></script>
<script src="../../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="../../js/show.js"></script>

<!-- 监测屏幕分辨率，设置width,height -->
<script type="text/javascript">
$(function(){
	drawChart();
	showInfo();
});

function showInfo(){
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findYearTrainDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				$("#train1").html(object[0][1]);
				$("#train2").html(object[0][0]);
				$("#train3").html(object[0][1]);
				$("#train4").html(object[0][2]);
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findMonthlySkillDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				$("#skill1").html(object[0][0]);
				$("#skill2").html(object[0][0]);
				$("#skill3").html(object[0][1]);
				$("#skill4").html(object[0][2]);
			}
		}
	});
}

function drawChart(){
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findMonthlyTrained.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartLine(object[1],object[0],'chart1');
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findMonthlySkill.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartLine(object[1],object[0],'chart2');
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findPxzxDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartPie(object[0],'chart3');
				$("#pxzx1").html(object[0][0]);
				$("#pxzx2").html(object[0][1]);
				if(object[0][1]>0){
					$("#pxzx3").html((object[0][1]*100/(object[0][0]+object[0][1])).toFixed(2));
				}
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findManageDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartPie2(object[0],'chart4','管理类年度培训人次完成率');
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findProdDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartPie2(object[0],'chart5','生产类年度培训人次完成率');
			}
		}
	});
}

function newChartLine(valueList,dateList,renderTo){
 	var  chartOption ={
 		chart: {
 		    renderTo: renderTo,
 		    type: 'line',
 		    height: 250,
 		    borderWidth:0,
 		    width:380
 		},
 		credits:{					
 			enabled:false
 		},
 		legend: {					
 					enabled: false
 				},
 		title: {
 			text:null
 		}, 
 		tooltip: {
              formatter: function() {
                 return this.x + '<br>'+this.series.name+':'+ this.y +'';
             },
             style: {
                 padding: '10px',
                 fontWeight: 'bold',
                 fontSize:'12px'
             }
         },
 		xAxis: {
 			categories:dateList,
 			minPadding: 0.05,
 			maxPadding: 0.05,
 			labels: {
                style: {
                     fontSize:'9px'
                 }
             }
 		},
 		yAxis:{
 			title: {
 		    	text: null
 			} ,
 			labels: {
 				align: 'left',
                 style: {
                     fontSize:'9px'
                 }
             }
 		}
 	};
	
	chartOption.series = [{
		name:'值',
		data:valueList
	}];
 	  		
 	new Highcharts.Chart(chartOption); 
 }



/**
 * 画一个饼状图
 * @param valueList	饼图的值
 * @param renderTo	要显示位置的id
 */
function newChartPie(valueList,renderTo){
	if(valueList==null || valueList==""){
		valueList = new Array();
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
	}
	var  chartOption ={
		chart: {
			renderTo: renderTo,
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
	        borderWidth:0,
	        height: 250,
	        width:390
		},
		title: {
			text: '管理类、生产类培训人次分布'
		},
		credits: {
			enabled:false
		},
		tooltip: {
             formatter: function() {
               return this.point.name + '<br>值:'+ this.y +' 人';
            },
            style: {
                padding: '10px',
                fontWeight: 'bold',
                fontSize:'12px'
            }
        },
		plotOptions: {
			pie: {
       	 	size:'100%',
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,			
					color: 'black',			
					connectorColor: '#333',
					distance: -20,
					style: {		
                   fontSize:'12px'
                },
					formatter: function() {
						return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
					}
				}
			}
		},
		series: [{
			type: 'pie',
			name: '值',
			data: [
			    {	name :'管理类',
					y : valueList[0],
					color : '#4572A7'
				},
				{	name :'生产类',
					y : valueList[1],
					color : '#79BB25'
				}
			]
		}]
	};
	new Highcharts.Chart(chartOption); 
}

/**
 * 画一个饼状图
 * @param valueList	饼图的值
 * @param renderTo	要显示位置的id
 */
function newChartPie2(valueList,renderTo,textValue){
	if(valueList==null || valueList==""){
		valueList = new Array();
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
		valueList.push(0);
	}
	var  chartOption ={
		chart: {
			renderTo: renderTo,
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
	        borderWidth:0,
	        height: 250,
	        width:290
		},
		title: {
			text: textValue
		},
		credits: {
			enabled:false
		},
		tooltip: {
             formatter: function() {
               return this.point.name + '<br>值:'+ this.y +' 人';
            },
            style: {
                padding: '10px',
                fontWeight: 'bold',
                fontSize:'12px'
            }
        },
		plotOptions: {
			pie: {
       	 	size:'100%',
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,			
					color: 'black',			
					connectorColor: '#333',
					distance: -20,
					style: {		
                   fontSize:'12px'
                },
					formatter: function() {
						return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
					}
				}
			}
		},
		series: [{
			type: 'pie',
			name: '值',
			data: [
			    {	name :'完成',
					y : valueList[0],
					color : '#4572A7'
				},
				{	name :'剩余',
					y : valueList[1],
					color : '#79BB25'
				}
			]
		}]
	};
	new Highcharts.Chart(chartOption); 
}
</script>

<style type="text/css">
        button.ui-datepicker-current { display: none; }
		.panel_7 .highcharts-data-labels span {overflow:visible};
</style>






</head>



<body>

	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix nwarp">
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl nwarp">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/xwzx/xw_index.jsp'">首页</a></li>
                	<li><a href="javascript:window.location.href='/portal/center/pxzx/pxzx_index.jsp'">培训中心</a></li>
                	<li class="fin">培训情况统计</li>
                </ul>
            </div>
            <div class="fr lit_nav nwarp">
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
        <div class="w1280">
   <!--tab1-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" >月度培训人次</h3>
                            <div class="clear"></div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">&nbsp;</h6>
                                <h1 class="fr mr5" id="train1_1">&nbsp;</h1>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor mr8" id="chart1"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix" style="width:210px;"><span style="width:150px;">年度计划：</span><h6 class="fr">人次</h6><b class="fr" id="train2"></b></li>
                            	<li class="clearfix"><span style="width:150px;">年度培训人次完成：</span><h6 class="fr">人次</h6><b class="fr" id="train3"></b></li>
                            	<li class="clearfix"><span style="width:150px;">年度培训人次计划完成率：</span><h6 class="fr">%</h6><b class="fr" id="train4"></b></li>
                            </ul>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	

        <!--tab1 End-->
        
        <!--tab2-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" >月度技能鉴定人数</h3>
                            <div class="clear"></div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">&nbsp;</h6>
                                <h1 class="fr mr5" id="skill1_1">&nbsp;</h1>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor mr8" id="chart2"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix" style="width:210px;"><span style="width:150px;">年度技能鉴定完成：</span><h6 class="fr">人次</h6><b class="fr" id="skill2"></b></li>
                            	<li class="clearfix"><span style="width:150px;">技能鉴定合格：</span><h6 class="fr">人次</h6><b class="fr" id="skill3"></b></li>
                            	<li class="clearfix"><span style="width:150px;">合格率：</span><h6 class="fr">%</h6><b class="fr" id="skill4"></b></li>
                            </ul>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	

        <!--tab2 End-->
        <div class="clear"></div>
        <!--tab3-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" >年度生产类、管理类培训完成情况</h3>
                            <div class="clear"></div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">&nbsp;</h6>
                                <h1 class="fr mr5">&nbsp;</h1>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor mr8" id="chart3"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix" style="width:210px;"><span style="width:150px;">已完成管理类培训：</span><h6 class="fr">人</h6><b class="fr" id="pxzx1"></b></li>
                            	<li class="clearfix"><span style="width:150px;">已完成生产类培训：</span><h6 class="fr">人</h6><b class="fr" id="pxzx2"></b></li>
                            	<li class="clearfix"><span style="width:150px;">生产类占比：</span><h6 class="fr">%</h6><b class="fr" id="pxzx3"></b></li>
                            </ul>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	

        <!--tab3 End-->
        
         <!--tab4-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" >年度生产类、管理类培训完成情况</h3>
                            <div class="clear"></div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">&nbsp;</h6>
                                <h1 class="fr mr5">&nbsp;</h1>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor mr8" id="chart4" style="width:290px;"></div>
                    	<div class="fl bor mr8" id="chart5" style="width:290px;"></div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	

        <!--tab4 End-->
        </div>
	</div>
	</div>
</body>
</html>