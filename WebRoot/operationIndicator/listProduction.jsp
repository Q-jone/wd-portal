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
<script src="../js/indicator/list.js"></script>

<!-- 监测屏幕分辨率，设置width,height -->
<script type="text/javascript">
	var w ,h ;
	var menuWidth;
	$(document).ready(function () {
	
	if ($(parent.document).find("frameset[id=main]").attr("cols") == "210,*"){
		menuWidth = 210;
	}else{
		menuWidth = 0;
	}
		if($(window).width()-menuWidth>1024){
				$("div[id^=tab][id$=div]").removeClass("w1024");
				$("div[id^=tab][id$=div]").addClass("w1280");
				w = 360;
				h = 240;		
			}else{
				$("div[id^=tab][id$=div]").removeClass("w1280");
				$("div[id^=tab][id$=div]").addClass("w1024");
				w = 288;
				h = 240;
			}
		$(window).resize(function(){
		if ($(parent.document).find("frameset[id=main]").attr("cols") == "210,*"){
			menuWidth = 210
		}else{
			menuWidth = 0;
		}
	
			if($(window).width()-menuWidth>1024){
				$("div[id^=tab][id$=div]").removeClass("w1024");
				$("div[id^=tab][id$=div]").addClass("w1280");	
				w = 360;
				h = 240;		
			}else{
				$("div[id^=tab][id$=div]").removeClass("w1280");
				$("div[id^=tab][id$=div]").addClass("w1024");
				w = 288;
				h = 240;
			}
			$("li[id^=tab]").each(function(){
			if($(this).hasClass("selected")){
				if($(this).attr("id").indexOf(1)>0){
					showTab1ByDay();
				}else if($(this).attr("id").indexOf(2)>0){
					showTab2ByDay();
				}else if($(this).attr("id").indexOf(3)>0){
					showTab3ByDay();
				}	
			}
		});
		})
		var $tbInfo = $(".filter .query input:text");
		$tbInfo.each(function () {
			$(this).focus(function () {
				$(this).attr("placeholder", "");
			});
		});
		
		var $tblAlterRow = $(".table_1 tbody tr:even");
		if ($tblAlterRow.length > 0)
			$tblAlterRow.css("background","#fafafa");			
	});
	
	
	

</script>

<style type="text/css">
        button.ui-datepicker-current { display: none; }
		.panel_7 .highcharts-data-labels span {overflow:visible};
</style>
<script type="text/javascript">
$(document).ready(function () {
	showTab1ByDay();
});	

//日期改变后，设置隐藏域日期的值
function setParamDate(object){
	$(object).parent().next("div").children("input").val($(object).val());
}
</script>




<script type="text/javascript">
//使用日期控件
$(document).ready(function () {
	var firstDate = $("#firstDate").val();
	var lastDate = $("#lastDate").val();
	$('#datepicker').datepicker({
		inline: true,
		changeYear:true,
		minDate:firstDate,
		maxDate:lastDate,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("#datepickerMonth").datepicker({
		inline: true,
		changeYear:true
	});
	
	$("input[id^=datepickerInner]").each(function(){
		$(this).datepicker({
			inline: true,
			changeYear:true,
			minDate:firstDate,
			maxDate:lastDate,
			changeMonth:true,
			showOtherMonths: true,
			selectOtherMonths: true
		});
	});
	
	loadShow();
});
//按钮效果转换
function showButton(obj){
	$("input[id^=view]").each(function(){
		$(this).attr("disabled",false);
	});
	$(obj).attr("disabled",true);
}

//点击日视图按钮
function viewDay(btObject){
	showButton(btObject);
	$("li[id^=tab]").each(function(){
		if($(this).hasClass("selected")){
			showTab1ByDay();
		}
	});
}
//点击月视图按钮
function viewMonth(btObject){
	showButton(btObject);
	$("li[id^=tab]").each(function(){
		if($(this).hasClass("selected")){
			showTab1ByMonth();
		}
	});
}
//点击年视图按钮
function viewYear(btObject){
	showButton(btObject);
	$("li[id^=tab]").each(function(){
		if($(this).hasClass("selected")){
			showTab1ByYear();
		}
	});
}
</script>

<script type="text/javascript">

//点击查询按钮后提交
function submitForm(){
	var btId = $("input:disabled").attr("id");
	var type = getViewType();
	
	$("p[id^=chart]").each(function(){
		$(this).html("");
	});
	switch(type){
    	case 1:showTab1ByDay();break;
		case 2:showTab1ByMonth();break;
	  	case 3:showTab1ByYear();break;
	};
	
	$("input[id^=datepickerInner]").each(function(){
		$(this).val($("#datepicker").val());
		$(this).parent().next("div").children("input").val($("#datepicker").val());
	});
}

//跳转到详细页面
function showDetail(chartId,object){
	var endDate = $(object).next("input").val();
	window.open("showProductionDetailPage.action?lineNo="+$("#lineNo").val()+"&endDate="+endDate+"&chartId="+chartId);
}


//跳转到管控设置页面
function showProductionControl(){
	window.open("/portal/indicatorControl/showProductionControlPage.action");
}
</script>


</head>



<body>

<input type="hidden" value="<s:property value='#request.lastDate'/>" id="lastDate">
<input type="hidden" value="<s:property value='#request.firstDate'/>" id="firstDate">
	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix nwarp">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl nwarp">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">运营管理</a></li>
                	<li class="fin">运营指标</li>
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
        <!--Tabs_2-->
        <div class="tabs_2 nwarp">
        	<ul>
            	<li class="selected" id="tab1"><a href="#"><span>运营生产指标</span></a></li>
				<li id="tab2"><a href="showQualityListPage.action" ><span>运营质量安全指标</span></a></li>
				<li id="tab3"><a href="showScaleListPage.action"><span>运营规模指标</span></a></li>
            </ul>
        </div>
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
      	<div class="query">
	      	<div class="filter_sandglass p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
	      	  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
	      	    <tr>
	      	      <td class="t_r">线路</td>
	      	      <td>
	      	      	<select name="lineNo" class="input_large" id="lineNo">
						<s:iterator value="#request.lineMap" id="st" status="s">
							<option value="<s:property value='#st.key'/>" id="op<s:property value='#s.index'/>"><s:property value="#st.value"/></option>
						</s:iterator>
					</select>
	      	      </td>
	      	      <td class="t_r">日期</td>
	     	      <td>
					<input type="text" class="date" id="datepicker" name="date" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
					<input type="text" class="date" id="datepickerMonth" name="date" readonly="readonly" style="display: none;"/>
					<input type="button" value="查询" onclick="submitForm()"/>
				  </td> 
	     		  <td class="t_c">
					<input type="button" value="日视图" onclick="viewDay(this);" id="viewDay" disabled="disabled"/> &nbsp; 
					<input type="button" value="月视图" onclick="viewMonth(this);" id="viewMonth"/> &nbsp; 
					<input type="button" value="年视图" onclick="viewYear(this);" id="viewYear"/>
					<input type="button" value="管控设置" onclick="showProductionControl();" id="viewYear"/>
				  </td>
	    	    </tr>
	    	  </table>
	        </div>
      	</div>
      </div>
      <!--Filter End-->
        
   <!--tab1-->
      <div  id="tab1div">
      
        	<!--1.开行列次-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl" >开行列次</h3>
                            <div class="fr setting"><a href="javascript:void(0)">设置</a></div>
                            <div class="clear"></div>
                            <div class="fl">
                              <input type="text" onchange="drawChart1();setParamDate(this);" class="date"  id="datepickerInner1" name="datepicker" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
                            </div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">列次</h6>
                                <h1 class="fr mr5" id="chart1_daily" onclick="showDetail(1,this)"></h1>
                                <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate1">
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor Hcharts mr8" id="chart1"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix"><span>去年同期：</span><h6 class="fr">列次</h6><b class="fr" id="chart1_lastyear"></b></li>
                            	<li class="clearfix"><span>月累计：</span><h6 class="fr">列次</h6><b class="fr" id="chart1_month"></b></li><!-- <b class="L_01 fr" id="chart1_month"></b>红色字体 -->
                            	<li class="clearfix"><span>年累计：</span><h6 class="fr">列次</h6><b class="fr" id="chart1_year"></b></li>
                            </ul>
                            
                            <p id="chart1Info" style="display: none;"></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	
        	<!--2.运营里程-->
        	<div class="fl panel_7">
        	  <div class="p_right">
        	  	<div class="hgroup">
        	    <hgroup>
        	      <div class="text clearfix">
        	        <h3 class="fl">运营里程</h3>
        	        <div class="fr setting"><a href="javascript:void(0)" >设置</a></div>
        	        <div class="clear"></div>
        	        <div class="fl">
        	          <input type="text" onchange="drawChart2();setParamDate(this);" class="date"  id="datepickerInner2" name="datepicker2" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
      	          </div>
        	        <div class="fr clearfix data">
        	          <h6 class="fr pt10">万车公里</h6>
        	          <h1 class="fr mr5" id="chart2_daily" onclick="showDetail(2,this)"></h1>
        	          <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate2">
      	          </div>
        	        <div class="clear"></div>
      	        </div>
      	      </hgroup>
      	      </div>
        	    <div class="con clearfix">
        	      <div class="conner"></div>
        	      <div class="fl bor Hcharts mr8" id="chart2"></div>
        	      <div class="fl">
        	        <ul class="mb10">
        	          <li class="clearfix"><span>去年同期：</span><h6 class="fr">万公里</h6><b class="fr" id="chart2_lastyear"></b></li>
        	          <li class="clearfix"><span>月累计：</span><h6 class="fr">万公里</h6><b class="fr" id="chart2_month"></b></li>
        	          <li class="clearfix"><span>年累计：</span><h6 class="fr">万公里</h6><b class="fr" id="chart2_year"></b></li>
      	          </ul>
        	        <p id="chart2Info"></p>
      	        </div>
        	      <div class="clear"></div>
      	      </div>
      	    </div>
      	  </div>
         <!--End-->
          <div class="clear"></div>
          
          
          <!--3.客流量-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl">客流量</h3>
                            <div class="fr setting"><a href="javascript:void(0)" >设置</a></div>
                            <div class="clear"></div>
                            <div class="fl">
                              <input type="text" onchange="drawChart3();setParamDate(this);" class="date"  id="datepickerInner3" name="datepicker" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
                            </div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">万人/日</h6>
                                <h1 class="fr mr5" id="chart3_daily" onclick="showDetail(3,this)"></h1>
                                <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate3">
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor Hcharts mr8" id="chart3"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix"><span>去年同期：</span><h6 class="fr">万人</h6><b class="fr" id="chart3_lastyear"></b></li>
                            	<li class="clearfix"><span>月累计：</span><h6 class="fr">万人</h6><b class="fr" id="chart3_month"></b></li>
                            	<li class="clearfix"><span>年累计：</span><h6 class="fr">万人</h6><b class="fr" id="chart3_year"></b></li>
                            </ul>
                            <p id="chart3Info"></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	
        	<!--4.换乘人次-->
        	<div class="fl panel_7">
        	  <div class="p_right">
        	  	<div class="hgroup">
        	    <hgroup>
        	      <div class="text clearfix">
        	        <h3 class="fl">换乘人次</h3>
        	        <div class="fr setting"><a href="javascript:void(0)" >设置</a></div>
        	        <div class="clear"></div>
        	        <div class="fl">
        	          <input type="text" onchange="drawChart4();setParamDate(this);" class="date"  id="datepickerInner4" name="datepicker2" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
      	          </div>
        	        <div class="fr clearfix data">
        	          <h6 class="fr pt10">万人/日</h6>
        	          <h1 class="fr mr5" id="chart4_daily" onclick="showDetail(4,this)"></h1>
        	          <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate4">
      	          </div>
        	        <div class="clear"></div>
      	        </div>
      	      </hgroup>
      	      </div>
        	    <div class="con clearfix">
        	      <div class="conner"></div>
        	      <div class="fl bor Hcharts mr8" id="chart4"></div>
        	      <div class="fl">
        	        <ul class="mb10">
        	          <li class="clearfix"><span>去年同期：</span><h6 class="fr">万人</h6><b class="fr" id="chart4_lastyear"></b></li>
        	          <li class="clearfix"><span>月累计：</span><h6 class="fr">万人</h6><b class="fr" id="chart4_month"></b></li>
        	          <li class="clearfix"><span>年累计：</span><h6 class="fr">万人</h6><b class="fr" id="chart4_year"></b></li>
      	          </ul>
        	        <p id="chart4Info"></p>
      	        </div>
        	      <div class="clear"></div>
      	      </div>
      	    </div>
      	  </div>
         <!--End-->
          <div class="clear"></div>
          
          
           <!--5.客运收入-->
        	<div class="fl panel_7">
            	<div class="p_right">
            		<div class="hgroup">
                    <hgroup>
                    	<div class="text clearfix">
                        	<h3 class="fl">客运收入</h3>
                            <div class="fr setting"><a href="javascript:void(0)" >设置</a></div>
                            <div class="clear"></div>
                            <div class="fl">
                              <input type="text" onchange="drawChart5();setParamDate(this);" class="date"  id="datepickerInner5" name="datepicker" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
                            </div>
                            <div class="fr clearfix data">
                                <h6 class="fr pt10">万元</h6>
                                <h1 class="fr mr5" id="chart5_daily" onclick="showDetail(5,this)"></h1>
                                <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate5">
                            </div>
                            <div class="clear"></div>
                        </div>
                    </hgroup>
                    </div>
                    <div class="con clearfix">
                    	<div class="conner"></div>
                    	<div class="fl bor Hcharts mr8" id="chart5"></div>
                        <div class="fl">
                       	  <ul class="mb10">
                            	<li class="clearfix"><span>去年同期：</span><h6 class="fr">万元</h6><b class="fr" id="chart5_lastyear"></b></li>
                            	<li class="clearfix"><span>月累计：</span><h6 class="fr">万元</h6><b class="fr" id="chart5_month"></b></li>
                            	<li class="clearfix"><span>年累计：</span><h6 class="fr">万元</h6><b class="fr" id="chart5_year"></b></li>
                            </ul>
                            <p id="chart5Info"></p>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        	<!--End-->
        	
        	<!--6.上线车辆编组数----饼状-->
        	<div class="fl panel_7">
        	  <div class="p_right">
        	  	<div class="hgroup">
        	    <hgroup>
        	      <div class="text clearfix">
        	        <h3 class="fl">上线车辆编组数</h3>
        	        <div class="fr setting"><a href="javascript:void(0)" >设置</a></div>
        	        <div class="clear"></div>
        	        <div class="fl">
        	          <input type="text" onchange="drawChart6();setParamDate(this);" class="date"  id="datepickerInner6" name="datepicker2" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
      	          </div>
        	        <div class="fr clearfix data">
        	          <h6 class="fr pt10">列</h6>
        	          <h1 class="fr mr5" id="chart6_all" onclick="showDetail(6,this)"></h1>
        	          <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate6">
      	          </div>
        	        <div class="clear"></div>
      	        </div>
      	      </hgroup>
      	      </div>
        	    <div class="con clearfix">
        	      <div class="conner"></div>
        	      <div class="fl bor Hcharts mr8" id="chart6"></div>
        	      <div class="fl">
        	        <ul class="mb10">
        	          <li class="clearfix"><span>3节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart6_3"></b></li>
        	          <li class="clearfix"><span>4节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart6_4"></b></li>
        	          <li class="clearfix"><span>6节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart6_6"></b></li>
        	          <li class="clearfix"><span>7节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart6_7"></b></li>
        	          <li class="clearfix"><span>8节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart6_8"></b></li>
      	          </ul>
      	        </div>
        	      <div class="clear"></div>
      	      </div>
      	    </div>
      	  </div>
         <!--End-->
          <div class="clear"></div>
          
          
          
          <!--7.备用车辆编组数----饼状-->
        	<div class="fl panel_7">
        	  <div class="p_right">
        	  	<div class="hgroup">
        	    <hgroup>
        	      <div class="text clearfix">
        	        <h3 class="fl">备用车辆编组数</h3>
        	        <div class="fr setting"><a href="javascript:void(0)" >设置</a></div>
        	        <div class="clear"></div>
        	        <div class="fl">
        	          <input type="text" onchange="drawChart7();setParamDate(this);" class="date"  id="datepickerInner7" readonly="readonly" value="<s:property value='#request.lastDate'/>"/>
      	          </div>
        	        <div class="fr clearfix data">
        	          <h6 class="fr pt10">列</h6>
        	          <h1 class="fr mr5" id="chart7_all" onclick="showDetail(7,this)"></h1>
        	          <input type="hidden" value="<s:property value='#request.lastDate'/>" id="valueDate7">
      	          </div>
        	        <div class="clear"></div>
      	        </div>
      	      </hgroup>
      	      </div>
        	    <div class="con clearfix">
        	      <div class="conner"></div>
        	      <div class="fl bor Hcharts mr8" id="chart7"></div>
        	      <div class="fl">
        	        <ul class="mb10">
        	          <li class="clearfix"><span>3节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart7_3"></b></li>
        	          <li class="clearfix"><span>4节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart7_4"></b></li>
        	          <li class="clearfix"><span>6节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart7_6"></b></li>
        	          <li class="clearfix"><span>7节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart7_7"></b></li>
        	          <li class="clearfix"><span>8节编组：</span><h6 class="fr">列</h6><b class="fr" id="chart7_8"></b></li>
      	          </ul>
      	        </div>
        	      <div class="clear"></div>
      	      </div>
      	    </div>
      	  </div>
         <!--End-->

      </div>
        <!--tab1 End-->
        
        
	</div>
	</div>
</body>
</html>