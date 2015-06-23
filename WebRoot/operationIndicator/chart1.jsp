<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>详细</title>

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
<script src="../js/exporting.js"></script>
<script src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="../js/indicator/detailPage.js"></script>
<script src="../js/indicator/dateFormat.js"></script>
<style type="text/css">
        button.ui-datepicker-current { display: none; }
</style>

<script type="text/javascript">
	$(document).ready(function () {
		var $tbInfo = $(".filter .query input:text");
		$tbInfo.each(function () {
			$(this).focus(function () {
				$(this).attr("placeholder", "");
			});
		});
		var $tblAlterRow = $(".table_1 tbody tr:even");
		if ($tblAlterRow.length > 0)
			$tblAlterRow.css("background","#fafafa");	
		setWordCenter();		
	});
	
	
//按钮效果转换
function showButton(obj){
	$("input[id^=view]").each(function(){
		$(this).attr("disabled",false);
	});
	$(obj).attr("disabled",true);
}	
	
//日视图
function viewDay(object){
	showButton(object);
	$("#custom span").attr("style","display:block");
}

//月视图
function viewMonth(object){
	showButton(object);
	$("#custom span").attr("style","display:none");
}


//年视图
function viewYear(object){
	showButton(object);
	$("#custom span").attr("style","display:none");
}



//是否显示data区域，search区域
$(function(){
	var search = $("#searchHiddenValue").val();
	if(search=="hide"){
		$("#searchArea").attr("style","display:none");
	}else{
		$("#searchArea").attr("style","display:block");
	}
	var data = $("#dataHiddenValue").val();
	if(data=="hide"){
		$("#dataArea").attr("style","display:none");
	}else{
		$("#dataArea").attr("style","display:block");
	}
});	

//显示或隐藏搜索条件
$(function(){
	$("#switchSearch").toggle(
		function(){
			$("#searchArea").slideDown();
			$("#searchHiddenValue").val("show");
		},
		function(){
			$("#searchArea").slideUp();
			$("#searchHiddenValue").val("hide");
		}
	);
	
	$("#switchData").toggle(
		function(){
			$("#dataArea").slideDown();
			$("#dataHiddenValue").val("show");
		},
		function(){
			$("#dataArea").slideUp();
			$("#dataHiddenValue").val("hide");
		}
	);
	
});
</script>




<!-- 显示tab1图表 -->
<script type="text/javascript">

var lineStatus = -1;	//线路是否改变

/**
*	点击‘加减号’显示数据
*	type: 0:减，1：加
*/
var clickNum = 0;
function imgClick(type){
	var newstartDate = '';
	if(type==0){
		if(clickNum==1){
			return;
		}else if(clickNum==2) {
			$("button:eq(0)").attr("disabled",true);
		}
		clickNum--;
		newstartDate = getStartDate(clickNum);
		$("button:eq(1)").attr("disabled",false);
	}else{
		if(clickNum==4) $("button:eq(1)").attr("disabled",true);
		clickNum++;
		newstartDate = getStartDate(clickNum);
		$("button:eq(0)").attr("disabled",false);
	}
	switch(clickNum){
		case 1: $("#dateInfo").html("15天数据");break;
		case 2: $("#dateInfo").html("1个月数据");break;
		case 3: $("#dateInfo").html("3个月数据");break;
		case 4: $("#dateInfo").html("6个月数据");break;
		case 5: $("#dateInfo").html("1年数据");break;
	}
	if(newstartDate!=''){
		$("#datepicker1").val(newstartDate);
		findData();
	}
}

$(document).ready(function(){
	enabledDatepicker();
	findData();
});

function findData(){
	var id = $("#chartId").val();
	if(lineStatus==-1){
		$.ajax({
			type : 'post',
			url : 'findProductionBetweenDays.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				line : $("#lineNoSelect").val(),
				startDate : $("#datepicker1").val(),
				endDate : $("#datepicker2").val()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				if(object==null || object==""){
					alert("无相关数据，请重新选择线路和日期！");
				}else{
					var dataList = new Array();
					var dateList = new Array();
					var controlList = new Array();
					var controlValue ;
					if(object[object.length-1].metroProductionControlVO!=null){
						for(var i=0; i<object.length; i++){
							if(object[i].metroProductionControlVO!=null){
								controlValue = object[i].metroProductionControlVO.metroColumnDaily;
								
								if(controlValue!=null && !isNaN(controlValue) && controlValue!=''){
									controlList.push(parseInt(controlValue));
								}else{
									controlList.push(null);
								}
							}else{
								controlList.push(null);
							}
							dataList[i] = object[i].metroColumnDaily;
							dateList[i] = object[i].indicatorDate.substr(5);
							
						}
					}else{
						for(var i=0; i<object.length; i++){
							dataList[i] = object[i].metroColumnDaily;
							dateList[i] = object[i].indicatorDate.substr(5);
						}
					}
					newChartColumn(dataList,dateList,'chart1','单位：列次',getInterval(),controlList);
				}
			}
		});
	}else{
		var startDate = $("#datepicker1").val();
		var endDate = $("#datepicker2").val();
		window.location.href="showProductionDetailPage.action?lineNo="+$("#lineNoSelect").val()+"&startDate="+startDate+"&endDate="+endDate+"&chartId=1"
	}
}

function submitForm(){
	findData();
	$("#dateInfo").html("自定义");
	clickNum = 0;
	$("button:eq(0)").attr("disabled",true)
}


 //跳转到制定页

</script>



</head>

<body>
<input id="firstDate" value="<s:property value='#request.firstDate'/>" type="hidden"/>
<input id="lastDate" value="<s:property value='#request.lastDate'/>" type="hidden"/>
<input id="startDate" value="<s:property value='#request.startDate'/>" type="hidden"/>
<input id="endDate" value="<s:property value='#request.endDate'/>" type="hidden"/>



	<div class="main mw1002">
		<div class="ctrl clearfix nwarp">
        	<div class="fl"><img src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl nwarp">
            	<ul>
                	<li><a href="#">首页</a></li>
                	<li><a href="#">运营指标</a></li>
                	<li class="fin">开行列次</li>
                </ul>
            </div>
            <div class="fr lit_nav nwarp">
            	<ul>
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="javascript:void(0);" id="switchData">打开树</a></li>
                    <li><a class="filterClose" href="javascript:void(0);" id="switchSearch">关闭过滤</a></li>
                </ul>
            </div>
   		</div>
   		<div class="pt45">
   		<!--Filter-->
	      <div class="filter" id="searchArea">
	      	<div class="query">
		      	<div class="filter_sandglass p8"><!--这里根据内容做样式调整。1、筛选：filter_sandglass 2、搜索filter_search 3、提示：.filter_tips 背景图会改变。-->
		      	  <table class="nwarp" width="100%" border="0" cellspacing="0" cellpadding="0">
		      	    <tr>
		      	      <td class="t_r" width="5%;">线路</td>
		      	      <td width="15%;">
		      	      	<select name="lineNo" class="input_large" id="lineNoSelect" onchange="changeLineStatus();">
							<s:iterator value="#request.lineMap" id="st" status="s">
								<option value="<s:property value='#st.key'/>" id="<s:property value='#st.key'/>op"><s:property value="#st.value"/></option>
							</s:iterator>
						</select>
		      	      </td>
		      	      <td class="t_r" width="5%;">日期:</td>
		     	      <td width="26%;">
						<input type="text" class="date" id="datepicker1" name="date" readonly="readonly" value="<s:property value='#request.startDate'/>"/>-
						<input type="text" class="date" id="datepicker2" name="date" readonly="readonly" value="<s:property value='#request.endDate'/>"/>
						<input type="button" value="查询" onclick="submitForm();"/>
					  </td>
					  <td width="14%;" id="custom">
					  	<span>
						  	<p id="dateInfo" style="float:left;">自定义</p>&nbsp;&nbsp;&nbsp;
						  	<button onclick="imgClick(0);" disabled="disabled">-</button>
							<button onclick="imgClick(1);">+</button>
						</span>
					  </td>
					  <td width="18%;">
					  	<input type="button" value="日视图" onclick="viewDay(this);" id="viewDay" disabled="disabled"/> &nbsp; 
						<input type="button" value="月视图" onclick="viewMonth(this);" id="viewMonth"/> &nbsp; 
						<input type="button" value="年视图" onclick="viewYear(this);" id="viewYear"/>
					  </td>
					  
					  <!--<td width="*%;">
					  	<select>
					  		<option>均线</option>
					  		<option>管控线</option>
					  		<option>均线</option>
					  	</select>
					  </td>
		    	    --></tr>
		    	  </table>
		        </div>
	      	</div>
	      </div>
        <!--Filter End-->
   		
   		
        <div id="chart1"></div>
        
	</div>
	
	
	
	<!--Table-->
	<div class="mb10" id="dataArea">
		<table width="100%" class="table_1" >
			<!--<thead>
				<th colspan="15"></th>
			</thead>
			-->
			<tbody id="tbody">
				<tr class="tit">
					<td>线路</td>
					<td>日期</td>
					<td>日累计&nbsp;(单位：列次)</td>
					<td>去年同期&nbsp;(单位：列次)</td>
					<td>月累计&nbsp;(单位：列次)</td>
					<td>年累计&nbsp;(单位：列次)</td>
				</tr>
				<s:iterator value="#request.page.result" id="pm" >
					<tr>
						<td id="td_1">
							<s:if test="#pm.indicatorLine==0">全网</s:if>
							<s:else><s:property value="#pm.indicatorLine"/>号线</s:else>
						</td>
						<td id="td_2"><s:property value="#pm.indicatorDate"/></td>
						<td id="td_3"><s:property value="#pm.metroColumnDaily"/></td>
						<td id="td_3"><s:property value="#pm.lastyearMetroProduction.metroColumnDaily"/></td>
						<td id="td_4"><s:property value="#pm.metroColumnMonth"/></td>
						<td id="td_5"><s:property value="#pm.metroColumnYear"/></td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="tfoot">
			      <td colspan="11"><div class="clearfix">
			      <span class="fl">
			      	  <s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
				      <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
				      	<s:property value="#request.page.totalSize"/>条
				     </s:if>
				      <s:else>
				      	<s:property value="#request.page.start+#request.page.pageSize-1"/>条
				      </s:else>
			      	</span>
			        <ul class="fr clearfix pager">
			          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
			          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
			            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
			            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
		           	  </li>
		           	  
		               <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
		               	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		               </s:if>
		               <s:else>
		                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
		               </s:else>
		                
		              <li>
		              	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
		              		<a href="javascript:void(0)">下一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
		              	</s:else>
		              </li>
		              
		              <li>
		              	<s:if test="#request.page.currentPageNo==1">
		              		<a href="javascript:void(0)">上一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
		              	</s:else>
		              </li>
		              
		              <s:if test="#request.page.currentPageNo==1">
		              	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
		              </s:if>
		              <s:else>
		              	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
		              </s:else>
		         </ul>
		       </div></td>
		     </tr>
		</table>
	</div>
	</div>
	<s:form action="showProductionDetailPage" name="productionIndicator" namespace="/operationIndicator" id="form" method="post">
		<input type="hidden" name="pageNo" id="pageNo">
		<input type="hidden" value="<s:property value='#request.lineNo'/>" id="lineNo" name="lineNo">
		<input type="hidden" value="<s:property value='#request.startDate'/>" id="startDateHide" name="startDate">
   		<input type="hidden" value="<s:property value='#request.endDate'/>" id="endDateHide" name="endDate">
   		<input type="hidden" value="<s:property value='#request.chartId'/>" id="chartId" name="chartId">
   		
   		<input id="searchHiddenValue" type="hidden" name="searchArea" value="<s:property value='#request.searchArea'/>">
		<input id="dataHiddenValue" type="hidden" name="dataArea" value="<s:property value='#request.dataArea'/>">
		<input name="sameChart" type="hidden" value="true">
		
	</s:form>
	
	
</body>
</html>

