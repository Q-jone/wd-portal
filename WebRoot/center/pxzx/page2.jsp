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

<%
String deptId = request.getParameter("deptId");
if(deptId==null||"".equals(deptId)){
	deptId = "2921";
}

String deptName = request.getParameter("deptName");
if(deptName==null||"".equals(deptName)){
	deptName = "";
}

String role = request.getParameter("role");
if(role==null||"".equals(role)){
	role = "";
}
%>

<style>

.intro{
	padding-left:9px;
	margin:0 10px;
	height:210px;
}
.intro .con{
	padding:11px 9px 11px 2px;
	height:192px;
}
.intro img, .intro .img{
	width:285px;
	height:188px;
}
.intro h3{
	font-style:italic;
	font-weight:normal;
}
.intro p{
	text-indent:24px;
	max-height:160px;
	overflow:hidden;
}
.menu_list{
	position:absolute;
	right:0;
	top:150px;
	z-index:999;
	width:146px;
	height:403px;
	padding-left:10px;
}
.menu_list .tit{
	width:20px;
	float:left;
	padding-top:45px;
}
.menu_list ul{
	padding:1px 12px;
	width:100px;
	float:left;
}
.menu_list li{
	line-height:28px;
	font-size:14px;
}
.menu_list li a{
	padding-left:8px;}
	

.intro{
	background:url(image/intro1.png) left top no-repeat;
}
.intro .con{
	background:url(image/intro2.png) right top no-repeat;
}
.intro h3{
	color:#1366ac;
}
.txt{
	color:#a9a9a9;}
.menu_list{
	background:url(image/menu-right.png) left top no-repeat;
}
.menu_list .tit a{
	color:#4f81bd;
}
.menu_list li{
	border-bottom:#fff 1px solid;
}
.menu_list li a{
	border-bottom:#cdcdcd 1px solid;
}
</style>
<!-- 监测屏幕分辨率，设置width,height -->
<script type="text/javascript">
$(function(){
	if("<%=role%>"=="leader"){
		showDeptMenu();
	}else{
		$("#deptDiv").hide();
	}
	drawChart();
})

function showDeptMenu(){
	var addHtml = "";
	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findAllDepts.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				for(var i=0;i<object.length;i++){
					if("<%=deptId%>"==object[i][1]){
						addHtml += "<li><a style='color:blue;' href='/portal/center/pxzx/page2.jsp?deptId="+object[i][1]+"&deptName="+encodeURI(object[i][0])+"&role=<%=role%>'>"+object[i][0]+"</a></li>";
					}else{
						addHtml += "<li><a style='color:black;' href='/portal/center/pxzx/page2.jsp?deptId="+object[i][1]+"&deptName="+encodeURI(object[i][0])+"&role=<%=role%>'>"+object[i][0]+"</a></li>";
					}
				}
				$("#deptUl").html(addHtml);
			}
		}
	});
}


function drawChart(){

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findAllDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartPie(object[0],'chart1','#4572A7','#79BB25');
				$("#all1").html(object[0][0]);
				$("#all2").html(object[0][1]);
				if(object[0][1]>0){
					$("#all3").html((object[0][1]*100/(object[0][0]+object[0][1])).toFixed(2));
				}
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findAllManageDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartPie2(object[0],'chart2','管理类年度培训人次完成率','#4572A7','#79BB25');
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findAllProdDatas.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				//alert("无相关数据！");
			}else{
				newChartPie2(object[0],'chart3','生产类年度培训人次完成率','#4572A7','#79BB25');
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findDeptDatas.action?random='+Math.random(),
		dataType:'json',
		data:{
			deptId:"<%=deptId%>"
		},
		cache : false,
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据！");
			}else{
				newChartPie(object[0],'chart4','#FFC002','#FE5917');
				$("#dept1").html(object[0][0]);
				$("#dept2").html(object[0][1]);
				if(object[0][1]>0){
					$("#dept3").html((object[0][1]*100/(object[0][0]+object[0][1])).toFixed(2));
				}
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findDeptManageDatas.action?random='+Math.random(),
		dataType:'json',
		data:{
			deptId:"<%=deptId%>"
		},
		cache : false,
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据！");
			}else{
				newChartPie2(object[0],'chart5','管理类年度培训人次完成率','#FFC002','#FE5917');
			}
		}
	});

	$.ajax({
		type : 'post',
		url : '/portal/pxzx/findDeptProdDatas.action?random='+Math.random(),
		dataType:'json',
		data:{
			deptId:"<%=deptId%>"
		},
		cache : false,
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据！");
			}else{
				newChartPie2(object[0],'chart6','生产类年度培训人次完成率','#FFC002','#FE5917');
			}
		}
	});
}



/**
 * 画一个饼状图
 * @param valueList	饼图的值
 * @param renderTo	要显示位置的id
 */
function newChartPie(valueList,renderTo,color1,color2){
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
	        width:300
		},
		title: {
			text: '管理、生产类培训人次分布'
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
					color : color1
				},
				{	name :'生产类',
					y : valueList[1],
					color : color2
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
function newChartPie2(valueList,renderTo,textValue,color1,color2){
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
						if(valueList[1]==0&&valueList[0]==0){
							return '';
						}else if(valueList[1]<0&&this.point.name=='完成'){
							return this.point.name+'：'+(100*valueList[0]/(valueList[0]+valueList[1])).toFixed(2)+'%';
						}else{
							return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
						}
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
					color : color1
				},
				{	name :'剩余',
					y : valueList[1],
					color : color2
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
		<div class="menu_list clearfix" id="deptDiv">
        	<div class="tit" style="color:blue;">按<br>单<br>位<br>查<br>看</div>
            <ul id="deptUl">
            	
            </ul>
        </div>
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
        	<div class="fl panel_7" style="width:520px;">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" >集团各直属单位——年度生产类、管理类培训完成总体情况(人次)</h3>
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
                    	<div class="fl bor mr8" id="chart1"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix" style="width:200px;"><span style="width:150px;">已完成管理类培训：</span><h6 class="fr">人</h6><b class="fr" id="all1"></b></li>
                            	<li class="clearfix"><span style="width:150px;">已完成生产类培训：</span><h6 class="fr">人</h6><b class="fr" id="all2"></b></li>
                            	<li class="clearfix"><span style="width:150px;">生产类占比：</span><h6 class="fr">%</h6><b class="fr" id="all3"></b></li>
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
                        	<h3 class="fl" >集团各直属单位——生产类、管理类培训计划总体完成率(人次)</h3>
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
                    	<div class="fl bor mr8" id="chart2" style="width:290px;"></div>
                    	<div class="fl bor mr8" id="chart3" style="width:290px;"></div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	

        <!--tab2 End-->
        	

        <!--tab2 End-->
        <div class="clear"></div>
        <!--tab3-->
        	<div class="fl panel_7" style="width:520px;">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" ><%=deptName %>——年度生产类、管理类培训完成情况(人次)</h3>
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
                    	<div class="fl bor mr8" id="chart4"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix" style="width:200px;"><span style="width:150px;">已完成管理类培训：</span><h6 class="fr">人</h6><b class="fr" id="dept1"></b></li>
                            	<li class="clearfix"><span style="width:150px;">已完成生产类培训：</span><h6 class="fr">人</h6><b class="fr" id="dept2"></b></li>
                            	<li class="clearfix"><span style="width:150px;">生产类占比：</span><h6 class="fr">%</h6><b class="fr" id="dept3"></b></li>
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
                        	<h3 class="fl" ><%=deptName %>——生产类、管理类培训计划完成率(人次)</h3>
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
                    	<div class="fl bor mr8" id="chart5" style="width:290px;"></div>
                    	<div class="fl bor mr8" id="chart6" style="width:290px;"></div>
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