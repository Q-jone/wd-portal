<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>运营指标</title>

<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
<!--<script src="../js/html5.js"></script>-->
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../js/highcharts.js"></script>
<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="../js/show.js"></script>


<style type="text/css">
        button.ui-datepicker-current { display: none; }
</style>




<script type="text/javascript">
$(document).ready(function () {
	showPie();
});

function showPie(){
	$.ajax({
		type : 'post',
		//url : 'http://10.0.1.21:8080/portal/build/getProcessData.action?random='+Math.random(),
		url : 'build/getProcessData.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				newChartPie(object[0],"process_chart");
			}
		}
	});
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
	}
	
	
	
	var  chartOption ={
		chart: {
			renderTo: renderTo,
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
	        borderWidth:0,
	        height: 450,
	        width:450
		},
		title: {
			text: ''
		},
		credits: {
			enabled:false
		},
		tooltip: {
             formatter: function() {
               return this.point.name + '<br>值:'+ this.y +' ';
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
					distance: -50,
					style: {		
                   fontSize:'12px'
                },
					formatter: function() {
						return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
					}
						
				},
				events: {
                    click: function(event) {
                        window.open("getProcessList.action");
                    }
                }
			}
		}
		,
		series: [{
			type: 'pie',
			name: '值',
			data: []
		}]
	};

	if(valueList[0]>0){
		var data1 = {	name :'已完成任务',	y : valueList[0],color : '#79BB25'};
		chartOption.series[0].data.push(data1);
	}
	if(valueList[1]>0){
		var data1 = {	name :'正常施工任务',	y : valueList[1],color : '#FFC002'};
		chartOption.series[0].data.push(data1);
	}
	if(valueList[2]>0){
		var data1 = {	name :'待开工任务',	y : valueList[2],color : '#4572A7'};
		chartOption.series[0].data.push(data1);
	}
	if(valueList[3]>0){
		var data1 = {	name :'异常任务',	y : valueList[3],color : '#FE5917'};
		chartOption.series[0].data.push(data1);
	}
	new Highcharts.Chart(chartOption); 
}

</script>

<script type="text/javascript">

</script>

<style type="text/css">
.panel_7 .highcharts-data-labels span {overflow:visible};
</style>

</head>

<body>
    <div  id="process_chart"></div>
</body>
</html>