<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <%
    	String investCostUrl ="http://10.1.48.40/investCost";
    	//String investCostUrl ="http://10.1.43.97:8080/investCost";
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <meta charset="utf-8" />
    <title>合同管理平台首页-图形报表</title>
    <link rel="stylesheet" href="css/formalize.css" />
    <link rel="stylesheet" href="css/page.css" />
    <link rel="stylesheet" href="css/default/imgs.css" />
    <link rel="stylesheet" href="css/reset.css" />
    <link type="text/css" href="css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
    <!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
    <script src="js/html5.js"></script>
    <script src="js/jquery-1.7.1.js"></script>
    <script src="js/flick/jquery-ui-1.8.18.custom.min.js"></script>
    <script src="js/jquery.formalize.js"></script>
    <script src="js/highcharts.js"></script>
    <script src="js/jquery.sparkline.min.js"></script>
        <script src="js/treaty.js"></script>

    <style type="text/css">
    	.ui-autocomplete {
		max-height: 150px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
		max-width: 300px;
		width: 300px;
	}
	/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
	* html .ui-autocomplete {
		height: 150px;
	}
	
    </style>
    <script type="text/javascript">
        var myDate = new Date();
		var thisYear = myDate.getFullYear();
		
		var month = myDate.toDateString();
		var yesterday = new Date();
		yesterday.setDate(myDate.getDate()-1);
		var showYesterday =yesterday.getFullYear()+"-";
		if(yesterday.getMonth()+1<10){
			showYesterday += "0";
		}
		showYesterday += (yesterday.getMonth()+1)+"-";
		if(yesterday.getDate()<10){
			showYesterday += "0";
		}
		showYesterday += (yesterday.getDate());
		$(function(){
			$("b[id='deadline']").html(showYesterday);
			
			getLatestNewsAside("1653");		//现场的
			//getLatestNewsAside("1497");		//863测试
		});
		
        var type = "default";
        var controlYear = thisYear;
    	var banner = "首页 - 数据范围:["+controlYear+"年]-[运维类]";
        var controlType = "2";
        var companyName = "";
        var companyId = "";
        
        $(document).ready(function() {
		var $tbInfo = $(".search_1 input:text");
		$tbInfo.each(function() {
			$(this).focus(function() {
				$(this).attr("placeholder", "");
			});
		});

		var $tblAlterRow = $(".table_1 tbody tr:odd");
		if ($tblAlterRow.length > 0)
			$tblAlterRow.css("background", "#f7f9fc");
		$("input[name=datepicker]").datepicker({
			inline : true,
			changeYear : true,
			changeMonth : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
	});

function arrMax(arr)
{
   return Math.max.apply(Math,arr);
}

function arrMin(arr)
{
   return Math.min.apply(Math,arr);
}

//点击右侧公司，切换值
function findByCompany(company_Id,companyName,target){
	companyId = company_Id;
	if(companyName!=null && ""!=companyName)
		$("#banner").html(banner+"-["+companyName+"]");
	$("#contractCompanyList").children("li").removeClass("selected");
	$("#contractCompanyList").find(".mlr10").children("li").removeClass("selected");
	if(target!=null)
		$(target).parent().addClass("selected");
	if(companyId=="other"){
		showControlInfo("other");
	}else{
		showControlInfo(companyId);
	}
	showMainChart();
	showTopPie("1");
	showTopPie("2");

	showTopLine("1");
	showTopLine("2");
}

//自动提示模糊搜索,项目编号
$(function(){
	//绑定搜索按钮
	$("#searchContract").click(function(){
		var searchUrl="";
		var contractId = $("#contractId").val();
		var contractNo = $("#contractNo").val();
		var contractName = $("#contractName").val();
		if(contractId!=""){
			searchUrl="<%=investCostUrl%>/contract/showView.action?id="+contractId+"&contractType="+controlType+"&cType=2&showOrHide=show";
		}else{
			if(contractNo!=""){
				alert("请选择正确的合同编号！");
				$("#contractNo").focus();
				return ;
			}
			if($("#contractName").val()==""){
				alert("请输入合同编号或合同名称");
				return ;
			}else{
				searchUrl="<%=investCostUrl%>/contract/findContractByPage.action?contractName="+contractName+"&contractType="+controlType+"&cType=2&showOrHide=show";
			}
		}
		window.open(searchUrl);
	});
	//绑定高级搜索按钮
	$("#advancedSearch").click(function(){
		var contractNo = $("#contractNo").val();
		var contractName = $("#contractName").val();
		var advancedSearch = "<%=investCostUrl%>/contract/findContractByPage.action?contractNo="+contractNo+"&contractName="+contractName+"&contractType="+controlType+"&showOrHide=show&cType=2";
		window.open(advancedSearch);
	});
	//显示banner
	$("#banner").html(banner);
	
	$("#contractNo" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "findWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"dataType" : "json",	
					"contractNo" : request.term,				//request.term是取到的值			
					"contractType" : controlType		
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.contractNo,
							id: item.id,
							contractName:item.contractName
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			$("#contractId").val("");
		},
		select: function( event, ui ) {
			$("#contractNo").val(ui.item.label);
			$("#contractName").val(ui.item.contractName);
			$("#contractId").val(ui.item.id);
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ){
				return false;
		},
		change : function(event,ui){
		}
	});

	//显示右侧合同数据
	$.ajax({
		url: "getContractCoverData.action?random="+Math.random(),
		dataType: "json",
		data: {
			"type" : 'post',
			"dataType" : "json",	
			"contractType" : controlType,
			"controlYear":controlYear		
		},
		success: function( data ) {			
			$("#contractAll").html(data.all.numbers);
			var company1all = 0;
			var company2all = 0;
			var company1Html ="";
			if(data.company1List!=null && data.company1List.length>0){
				for(var i=0; i<data.company1List.length; i++){
					var target = data.company1List[i];
					company1Html += "<li class='w49p fl'><a href='javascript:void(0)' onclick=findByCompany("+target.companyId+",'"+target.companyName+"',this)>"+target.companyName+" <i>("+target.numbers+")</i></a></li>";
					company1all += target.numbers;
				}
				//company1Html += "<li class='clearfix'></li>"
			}
			
			var centerCount = 0;
			var tempCompany1Html="";
			if(data.company2List!=null && data.company2List.length>0){
				for(var i=0; i<data.company2List.length; i++){
					var target = data.company2List[i];
					tempCompany1Html += "<li class='w45p fl' ><a href='javascript:void(0)' style='font-size:12px;' onclick=findByCompany("+target.companyId+",'"+target.companyName+"',this)>"+target.companyName+" <i>("+target.numbers+")</i></a></li>";
					company2all += target.numbers;
					centerCount += target.numbers;
				}
				//company1Html += "<li class='clearfix'></li>"
			}

			company1Html += "<div class='clear'></div>"
			company1Html += "<li class='hn'><a href='javascript:void(0)' onclick=findByCompany('center','维保中心',this)>维保中心 <i>("+centerCount+")</i></a></li>";
			company1Html +=	"<div class='mlr10' >"+tempCompany1Html+"</div>";
			company1Html +="<li class='w49p fl'><a href='javascript:void(0)' onclick='findByCompany(\"other\",\""+data.other.companyName+"\",this)'>"+data.other.companyName+"("+data.other.numbers+")</a></li>";
			
			$("#contractCompanyList").html(company1Html);

			var colors = ['#AA4643','#4572A7','#89A54E']; 
			//该data即为后台传过来的json
			 var data1 = "[{categorie : '运营公司',y: "+company1all+",drilldown: {categories: ['"+data.company1List[0].companyName+"', '"+data.company1List[1].companyName+"', '"+data.company1List[2].companyName+"', '"+data.company1List[3].companyName+"','"+data.company1List[4].companyName+"'],data: ["+data.company1List[0].numbers+", "+data.company1List[1].numbers+", "+data.company1List[2].numbers+", "+data.company1List[3].numbers+","+data.company1List[4].numbers+"]}},"+
				"{categorie : '维保中心',y: "+company2all+",drilldown: {categories: ['"+data.company2List[0].companyName+"', '"+data.company2List[1].companyName+"', '"+data.company2List[2].companyName+"', '"+data.company2List[3].companyName+"', '"+data.company2List[4].companyName+"','"+data.company2List[5].companyName+"'],data: ["+data.company2List[0].numbers+", "+data.company2List[1].numbers+", "+data.company2List[2].numbers+", "+data.company2List[3].numbers+" ,"+data.company2List[4].numbers+","+data.company2List[5].numbers+"]}},"+
				"{categorie : '其他',y: "+data.other.numbers+",drilldown: {categories: ['其他'],data: ["+data.other.numbers+"]}}]";

			 var data2 = "[{categorie : '运营公司',y: "+data.map.companyAll1+",drilldown: {categories: ['"+data.company1List[0].companyName+"', '"+data.company1List[1].companyName+"', '"+data.company1List[2].companyName+"', '"+data.company1List[3].companyName+"','"+data.company1List[4].companyName+"'],data: ["+data.map.c2541+", "+data.map.c2542+", "+data.map.c2543+", "+data.map.c2544+","+data.map.c2540+"]}},"+
				"{categorie : '维保中心',y: "+data.map.companyAll2+",drilldown: {categories: ['"+data.company2List[0].companyName+"', '"+data.company2List[1].companyName+"', '"+data.company2List[2].companyName+"', '"+data.company2List[3].companyName+"', '"+data.company2List[4].companyName+"','"+data.company2List[5].companyName+"'],data: ["+data.map.c2545+","+data.map.c2718+", "+data.map.c2719+", "+data.map.c2720+", "+data.map.c2721+" ,"+data.map.c2722+"]}},"+
				"{categorie : '其他',y: "+data.map.other+",drilldown: {categories: ['其他'],data: ["+data.map.other+"]}}]";
				
			 var pie1Data = eval(data1);
			 var pie2Data = eval(data2);

		     var outerData = [];
		     var innerData = [];
		     var outerData2 = [];
		     var innerData2 = [];
		     for (var i = 0; i < pie1Data.length; i++) {
		         outerData.push({
		             name: pie1Data[i].categorie,
		             y: pie1Data[i].y,
		             color: colors[i]
		         });
		         outerData2.push({
		        	 name: pie2Data[i].categorie,
		             y: pie2Data[i].y,
		             color: colors[i]
			     });
				
		         for (var j = 0; j < pie1Data[i].drilldown.data.length; j++) {
		             var brightness = 0.2 - (j / pie1Data[i].drilldown.data.length) / 5 ;
		             innerData.push({
		                 name: pie1Data[i].drilldown.categories[j],
		                 y: pie1Data[i].drilldown.data[j],
		                 color: Highcharts.Color(colors[i]).brighten(brightness).get()
		             });

		             innerData2.push({
		                 name: pie2Data[i].drilldown.categories[j],
		                 y: pie2Data[i].drilldown.data[j],
		                 color: Highcharts.Color(colors[i]).brighten(brightness).get()
		             });
		         }
		     }
			newPie("container","各执行单位运维类合同数量对比",outerData,innerData);
			newPie("container2","各执行单位运维类合同金额对比（万元）",outerData2,innerData2);
		}
	});
});
	
//画sparkline饼图	
function newSparkPie(index,valueList,title){
	var data = '';
	if(valueList.length>0){
		for(var i=0; i<valueList.length; i++){
			data += valueList[i];
			data += ",";
			}
		data = data.substr(0,data.length-1);
	}else{
		data = "0";
	}
	if(data=="0"){
		$(index).html("");
	}
	else{
		$(index).html(data);
		$(index).sparkline('html',
				{type:'pie',
					width: '77px',
					height:'77px',
					tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 数量：{{value}}',
					tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+title
				}); 
	}
}

function formatNumberList(list){
	if(list==null || list.length<1) return list;
	for(var i=0; i<list.length; i++){
		if((list[i]+"").indexOf(".")!=-1){		//有小数点
			list[i] = list[i].toFixed(4);	
		}
	}
	return list;
}

//画sparkline双直线图
function newSparkDualLinear(index,value1List,value2List,title,unit){
	var maxData = arrMax(value1List);
	var minData = arrMin(value1List);
	if(maxData < arrMax(value2List)){
		maxData = arrMax(value2List);
	}
	if(minData > arrMin(value2List)){
		minData = arrMin(value2List);
	}
	$(index).sparkline(value1List,
		{type:'line',
			spotRadius:2,
			lineWidth:2,
			width:150,height:40,
			tooltipSuffix:unit,
			//composite: true, 
			tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 计划支付： {{prefix}}{{y}}{{suffix}}',
			fillColor: false, 
			lineColor: '#FFA042',
			highlightLineColor:'red',
			chartRangeMin:minData-0.1,
			chartRangeMax:maxData+0.1 
		}); 
		
	$(index).sparkline(value2List,
		{type:'line',
			spotColor:false,
			spotRadius:2,
			lineWidth:2,
			width:150,height:40,
			tooltipSuffix:unit,
			//tooltipSuffix:'%',
			composite: true, 
			tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 实际支付： {{prefix}}{{y}}{{suffix}}',
			fillColor: false, 
			lineColor: 'blue',
			highlightLineColor:'red',
			chartRangeMin:minData-0.1,
			chartRangeMax:maxData+0.1 
		}); 		
}

//画sparkline双直线图
function newSparkColumnLine(index,value1List,value2List,title,unit){
	var maxData = arrMax(value1List);
	var minData = arrMin(value1List);
	if(maxData < arrMax(value2List)){
		maxData = arrMax(value2List);
	}
	if(minData > arrMin(value2List)){
		minData = arrMin(value2List);
	}

	$(index).sparkline(value1List,
		{type:'line',
			spotRadius:2,
			lineWidth:2,
			width:150,height:40,
			tooltipSuffix:unit,
			//composite: true, 
			tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 变更金额： {{prefix}}{{y}}{{suffix}}',
			fillColor: false, 
			lineColor: '#FFA042',
			highlightLineColor:'red',
			chartRangeMin:minData-0.1,
			chartRangeMax:maxData+0.1 
		}); 		
	$(index).sparkline(value2List,
		{type:'bar',
			spotColor:false,
			spotRadius:2,
			barWidth:12,
			width:150,height:40,			
			//tooltipSuffix:'%',
			composite: true, 
			tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 变更数量： {{prefix}}{{value}}{{suffix}}',
			fillColor: false, 
			//lineColor: 'blue',
			highlightLineColor:'red',
			chartRangeMin:minData-0.1,
			chartRangeMax:maxData+0.1 
		}); 		
}

//画柱状图
function newColumn(targetId,categories,columnList,lineList){
	var chartOption ={
		chart: {
			renderTo:targetId,
			spacingTop: 20,
			spacingBottom:20
		},
		title: {
			text: '合同数量与金额统计',
			style: {
                fontWeight: 'bold',
                fontSize:'16px'
            }
		},
		xAxis: {
			categories: categories
		},
		 yAxis: [{ // 第一个Y轴
                labels: {//刻度
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#AA4643'
                    }
                },
                title: {//y轴名称
                    text: '合同价',
                    style: {
                        color: '#89A54E'
                    }
                }
            }, { // 第二个Y轴
                title: {
                    text: '合同数',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {color: '#4572A7'
                    }
                },
                opposite: true //表示是否跟第一个在反方向位置
            }],
		legend: {
			align: 'right',
			x: -50,
			verticalAlign: 'top',
			y: -10,
			floating: true,
			backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
			borderColor: '#CCC',
			borderWidth: 1,
			shadow: false
		},
		tooltip: {
			formatter: function() {
				return '<b>'+ this.x +'</b><br/>'+
					this.series.name +': '+ this.y +'<br/>';//'Total: '+ this.point.stackTotal;					
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal',
				 fontSize:'9px',
				dataLabels: {
					//enabled: true,
					color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
				}
			},style: {		
                fontSize:'9px'
            }
		},
		series: [{
                type: 'column',
				yAxis:1,
                name: '合同数',
                data: columnList
            },{
                type: 'line',
                name: '合同价',
                data: lineList             
            }
        ],
		credits:{					
			enabled:false
		}
	};
	new Highcharts.Chart(chartOption); 
}


//画highchart饼图
function newPie(targetId,pieTitle,outterValueList,innerValueList){
	var options = {
         chart: {
        	 renderTo : targetId,
             type: 'pie',
			height:320,			
			width:485
         },
         title: {
             text: pieTitle
         },
         yAxis: {
             title: {
                 text: ''
             }
         },
         plotOptions: {
        	 pie: {
				size:'10%',
				minSize:60,
                 allowPointSelect: true,
                 cursor: 'pointer',
                 dataLabels:{  
                 	enabled :true,//是否在点的旁边显示数据  
                    rotation:0  
                }, 
                 showInLegend: true
             }
         },
         tooltip: {
         },		 
         legend: {
			 enabled:false,
             layout: 'vertical',
             align: 'right',
             verticalAlign: 'top',
             borderWidth: 0,
             labelFormatter: function() {
					return this.name+'&nbsp';
				},
				useHTML:true
         },
         series: [{
             name: '值',
             data: outterValueList,
             size: '60%',
             dataLabels: {
                 formatter: function() {
                     return this.point.name;
                 },
                 color: 'white',
                 distance: -30
             }
         }, {
             name: '值',
             data: innerValueList,
             size: '80%',
             innerSize: '60%',
             dataLabels: {
                 formatter: function() {
                     // display only if larger than 1
                     return '<b>'+ this.point.name +':</b> '+ this.y;
                 }
             }
         }],
         credits : {
				enabled : false
			}
     };
	  new Highcharts.Chart(options);
}	


$(function(){
	
	showSelectYear();
	showMainChart();
	
	showTopPie("1");	
	showTopPie("2");
	
	showTopLine("1");
	showTopLine("2");
	
	showControlInfo("");


	//右下角合同、项目基本信息
	showContractBaseinfo();
	
})


function showContractBaseinfo(){
	$.ajax({
		type : 'post',
		url : 'showBaseinfo.action',
		dataType:'json',
		cache : false,
		data:{
			contractType : controlType,
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			
			if(obj.projectCountCurrentMonth!=null && obj.projectCountCurrentMonth!="0"){
				$("#thisMonthNumProject").html('<a href="<%=investCostUrl%>/project/findProjectByPageToBeComplemented.action?complemented=no&pType=0&current='+showYesterday+'&showOrHide=show" target="_blank" style="color: #016FBB;font-weight: normal;float: right;font-size: 26px;">'+obj.projectCountCurrentMonth+'</a>');
			}else{
				//$("#thisMonthNumProject").html('<a href="</%=investCostUrl%>/project/findProjectByPageToBeComplemented.action?complemented=no&pType=0&current='+showYesterday+'&showOrHide=show" target="_blank" style="color: #016FBB;font-weight: normal;float: right;font-size: 26px;">'+obj.projectCountCurrentMonth+'</a>');
				$("#thisMonthNumProject").html(obj.projectCountCurrentMonth);
			}
			//$("#thisMonthNumProject").html('<a href="<%=investCostUrl%>/project/findProjectByPageToBeComplemented.action?complemented=no&pType=0&current='+showYesterday+'" target="_blank" style="color: #016FBB;font-weight: normal;float: right;font-size: 26px;">'+obj.projectCountCurrentMonth+'</a>');
			
			$("#thisMonthPriceProject").html(obj.projectMoneyCurrentMonth);
			//$("#sumNumProject").html(obj.projectCountAll);
			$("#sumNumProject").html("<a href='<%=investCostUrl%>/project/findProjectByPageToBeComplemented.action?complemented=no&pType=0' target='_blank' style='color: #016FBB;font-weight: normal;float: right;font-size: 26px;'>"+obj.projectCountAll+"</a>");			
			$("#sumPriceProject").html(obj.projectMoneyAll);

			
			//$("#dbqxmProject").html("<a href='<%=investCostUrl%>/project/findProjectByPageToBeComplemented.action' target='_blank' style='color: #016FBB;font-weight: normal;float: right;font-size: 26px;'>"+obj.needToCompleteProject+"</a>");
			$("#dbqProject").text(obj.needToCompleteProject);
			if(obj.contractCountCurrentMonth!=null && obj.contractCountCurrentMonth!="0"){
				$("#thisMonthNumContract").html('<a href="<%=investCostUrl%>/contract/findContractByPageToBeComplemented.action?complemented=no&cType=2&current='+showYesterday+'&showOrHide=show" target="_blank" style="color: #016FBB;font-weight: normal;float: right;font-size: 26px;">'+obj.contractCountCurrentMonth+'</a>');
			}else{
				//$("#thisMonthNumContract").html('<a href="</%=investCostUrl%>/contract/findContractByPageToBeComplemented.action?complemented=no&cType=2&current='+showYesterday+'&showOrHide=show" target="_blank" style="color: #016FBB;font-weight: normal;float: right;font-size: 26px;">'+obj.contractCountCurrentMonth+'</a>');
				$("#thisMonthNumContract").html(obj.contractCountCurrentMonth);
			}
			//$("#thisMonthNumContract").html('<a href="</%=investCostUrl%>/contract/findContractByPageToBeComplemented.action?complemented=no&cType=2&current='+showYesterday+'" target="_blank" style="color: #016FBB;font-weight: normal;float: right;font-size: 26px;">'+obj.contractCountCurrentMonth+'</a>');
			
			$("#thisMonthPriceContract").html(obj.contractMoneyCurrentMonth);
			//$("#sumNumContract").html(obj.contractCountAll);
			$("#sumNumContract").html("<a href='<%=investCostUrl%>/contract/findContractByPageToBeComplemented.action?complemented=no&contractType=2,' target='_blank' style='color: #016FBB;font-weight: normal;float: right;font-size: 26px;'>"+obj.contractCountAll+"</a>");
			$("#sumPriceContract").html(obj.contractMoneyAll);
			//$("#dbqhtContract").html("<a href='</%=investCostUrl%>/contract/findContractByPageToBeComplemented.action' target='_blank' style='color: #016FBB;font-weight: normal;float: right;font-size: 26px;'>"+obj.needToCompleteContract+"</a>");
			$("#dbqContract").text(obj.needToCompleteContract);
		}
	});
}

function showSelectYear(){
	var startYear = 2007;
	var addHtml = "";
	for(var i=0;i<(thisYear-startYear);i++){
		addHtml += "<option value='"+(startYear+i)+"'>"+(startYear+i)+"年</option>";
	}
	addHtml += "<option value='"+thisYear+"' selected='selected'>"+thisYear+"年</option>";
	$("#yearSelect").html(addHtml);
}

//中间的折线、柱状图
function showMainChart(){
	$.ajax({
		type : 'post',
		url : '/portal/contract/findDwContractPriceNumber.action',
		dataType:'json',
		cache : false,
		data:{
			controlYear : controlYear,
			controlType : controlType,
			companyId : companyId,
			type : type,
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			var columnList = obj.contractNum;
			var lineList = obj.contractPrice;
			var categories = obj.controlDate;
			newColumn(column,categories,columnList,lineList);
		}
	});
}

//画上方小饼图
function showTopPie(assignType){
	$.ajax({
		type : 'post',
		url : '/portal/contract/findDwContractAssignType.action',
		dataType:'json',
		cache : false,
		data:{
			assignType : assignType,
			controlYear : controlYear,
			contractType : controlType,
			controlCompanyId : companyId,
			type : type,
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			if(assignType=="1"){
				newSparkPie("#pie"+assignType,obj,"合同采购方式");
			}else{
				newSparkPie("#pie"+assignType,obj,"合同状态统计");
			}
			
			if(assignType==1){
				$("[id=topPie"+assignType+"]").each(function(i){
					if(obj[i]==0){
						$(this).text(obj[i]);
					}else{
						var addHtml = "<a href='<%=investCostUrl%>/contract/findByCondition.action?contractType="+controlType+"&condition=condition10"+"&inviteBidType="+(i+1)+"&controlYear="+controlYear+"&companyId="+companyId+"' target='_blank' style='color:#016FBB;'>"+obj[i]+"</a>"; 
						$(this).html(addHtml);
					}
				});
			}else{
				$("[id=topPie"+assignType+"]").each(function(i){
					if(obj[i]==0){
						$(this).text(obj[i]);
					}else{
						var addHtml = "<a href='<%=investCostUrl%>/contract/findByCondition.action?contractType="+controlType+"&condition=condition11"+"&contractStatus="+(i)+"&controlYear="+controlYear+"&companyId="+companyId+"' target='_blank' style='color:#016FBB;'>"+obj[i]+"</a>"; 
						$(this).html(addHtml);
					}
				});
			}	
				
		}
	});
}

//画上方折线图
function showTopLine(assignType){	
//alert("assignType="+assignType+",controlYear="+controlYear+",controlType="+controlType+",companyId="+companyId+",type="+type);	
	$.ajax({
		type : 'post',
		url : '/portal/contract/findDwContractAssignStats.action',
		dataType:'json',
		cache : false,
		data:{
			assignType : assignType,
			controlYear : controlYear,
			contractType : controlType,
			controlCompanyId : companyId,
			type : type,
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			var sumPrice = 0;
			var sumNum = 0;
			for(var i=0;i<obj.contractPrice.length;i++){
				sumPrice += parseFloat(obj.contractPrice[i]);			
				sumNum += parseFloat(obj.contractNum[i]);
			}
			if((sumNum+"").indexOf(".")!=-1){	//小数
				sumNum = sumNum.toFixed(4);
			}
			if(assignType=="1"){
				newSparkColumnLine("#columnLine",obj.contractPrice,obj.contractNum,"bbb","万元");
				$("#columnLinePrice").text(obj.yearMoney);
				if(obj.yearCount==0){
					$("#columnLineNum").text(obj.yearCount);	
				}else{
					var addHtml = "<a href='<%=investCostUrl%>/contract/findByCondition.action?contractType="+controlType+"&condition=condition12&controlYear="+controlYear+"&companyId="+companyId+"' target='_blank' style='color:#016FBB;'>"+obj.yearCount+"</a>";
					$("#columnLineNum").html(addHtml);
				}
			}else if(assignType=="2"){		//type=2的话，sumPrice是计划支付，sumNum是实际支付
				newSparkDualLinear("#duallinear",obj.contractPrice,obj.contractNum,"aaa","万元");
				$("#duallinearPrice1").text(sumPrice);
				$("#duallinearPrice2").text(sumNum);
			}
		}
	});
}

//显示下方采购平台业务数据
function showControlInfo(companyId){
	$.ajax({
		type : 'post',
		url : '/portal/contract/findDwContractManagement.action',
		dataType:'json',
		cache : false,
		data:{
			companyId:companyId,
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			var addHtml = "";
			var array1= [0,1,3,4,6,7];
			var array2= [5,8,9];
			var array3= [2,10,11];

			if(obj!=null && obj.length==12){
				var condition ="";
				for(var i=0; i<array1.length; i++){
					condition = "condition"+(array1[i]+1);
					addHtml += "<li class=\"gray_border counter w33p fl\"><div class=\"block\"><div class=\"corner h56 clearfix\">";
					addHtml += "<span class=\"c1 pt16\">";
					addHtml += obj[array1[i]].name;
					if(obj[array1[i]].name=='合同先执行后签订风险提示'){
						$.ajax({
							url: '<%=investCostUrl%>/api/contract/getKpiClearCount.action?callback=?',
							type: 'post',
							dataType: 'jsonp',
							success: function(data){
								if(data.result[0].kpiType=='1'){
									var number = obj[array1[i]].value - data.result[0].count;
									if(number<0){
										number = 0;
									}
									$("#numId").text(number);
								}
							}
						});
					}
					if(obj[array1[i]].value!=0 && array1[i]!=3){
					//if(array1[i]!=3 && array1[i]!=4){
						if(array1[i]==0 || array1[i]==1){
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><a href='<%=investCostUrl%>/project/findByCondition.action?projectType="+controlType+"&condition="+condition+"&companyId="+companyId+"' target='_blank'><h1 class=\"fr\" style=\"color: red;\">";
						}else{
							if(array1[i]==4){
								addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><a href='<%=investCostUrl%>/contract/findByCondition.action?contractType="+controlType+",&condition="+condition+"&companyId="+companyId+"' target='_blank'>";
				                addHtml += "<h1 id='numId' class=\"fr\" style=\"color: red;\">";
							}else{
								addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><a href='<%=investCostUrl%>/contract/findByCondition.action?contractType="+controlType+",&condition="+condition+"&companyId="+companyId+"' target='_blank'>";
				                addHtml += "<h1 class=\"fr\" style=\"color: red;\">";
							}
						}
						addHtml += obj[array1[i]].value;
	 	                addHtml += "</h1></a>";
					}else{
						if(array1[i]==0 || array1[i]==1){
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><h1 class=\"fr\" style=\"color: red;\">";
						}else{
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span>";
			                addHtml += "<h1 class=\"fr\" style=\"color: red;\">";
						}
						addHtml += obj[array1[i]].value;
	 	                addHtml += "</h1>";
					}
				}
				for(var i=0; i<array2.length; i++){
					condition = "condition"+(array2[i]+1);
					addHtml += "<li class=\"gray_border counter w33p fl\"><div class=\"block\"><div class=\"corner h56 clearfix\">";
					addHtml += "<span class=\"c2 pt16\">";
					addHtml += obj[array2[i]].name;
					if(obj[array2[i]].value!=0){
						addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><a href='<%=investCostUrl%>/contract/findByCondition.action?contractType="+controlType+",&condition="+condition+"&companyId="+companyId+"' target='_blank'>";
						addHtml += "<h1 class=\"fr\" style=\"color: #ff9900;\">";
						addHtml += obj[array2[i]].value;
	 	                addHtml += "</h1></a>";
					}else{
						addHtml += "</span><span class=\"fr pt24 mr5\" >个</span>";
						addHtml += "<h1 class=\"fr\" style=\"color: #ff9900;\">";
						addHtml += obj[array2[i]].value;
	 	                addHtml += "</h1>";
					}
				}
				for(var i=0; i<array3.length; i++){
					condition = "condition"+(array3[i]+1);
					addHtml += "<li class=\"gray_border counter w33p fl\"><div class=\"block\"><div class=\"corner h56 clearfix\">";
					addHtml += "<span class=\"c3 pt16\">";
					addHtml += obj[array3[i]].name;
					if(array3[i]==10){
						if(obj[array3[i]].value!=0){
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><a href='<%=investCostUrl%>/project/findProjectByPageToBeComplemented.action?companyId="+companyId+"' target='_blank'><h1 class=\"fr\" style=\"color: green;\">";
		                	addHtml += obj[array3[i]].value;
		 	                addHtml += "</h1></a>";
						}else{
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><h1 class=\"fr\" style=\"color: green;\">";
		                	addHtml += obj[array3[i]].value;
		 	                addHtml += "</h1>";
						}
					}else if(array3[i]==11){
						if(obj[array3[i]].value!=0){
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><a href='<%=investCostUrl%>/contract/findContractByPageToBeComplemented.action?companyId="+companyId+"' target='_blank'><h1 class=\"fr\" style=\"color: green;\">";
		                	addHtml += obj[array3[i]].value;
		 	                addHtml += "</h1></a>";
						}else{
							addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><h1 class=\"fr\" style=\"color: green;\">";
		                	addHtml += obj[array3[i]].value;
		 	                addHtml += "</h1>";
						}						
					}else{
						addHtml += "</span><span class=\"fr pt24 mr5\" >个</span><h1 class=\"fr\" style=\"color: green;\">";
	                	addHtml += obj[array3[i]].value;
	 	                addHtml += "</h1>";
					}
				}
				$("#controlInfo").html(addHtml);
			}
		}
	});
}

function changeType(chartType){
	type = chartType;
	showMainChart();
}

//年份改变后，显示各图
function changeYear(obj){
	
	controlYear = $(obj).val();
	showMainChart();
	
	showTopPie("1");	
	showTopPie("2");

	showTopLine("1");
	showTopLine("2");
	$("#banner").html("首页 - 数据范围:["+controlYear+"年]-[运维类]");
	banner = "首页 - 数据范围:["+controlYear+"年]-[运维类]";
	changeType('month');	//点击月视图
	findByCompany("",null,null);
}

function changeContractType(contractType){
	controlType = contractType;
	if(contractType=="1"){
		$("#contractType1").attr("class","selected");
		$("#contractType2").attr("class","");
	}else if(contractType=="2"){
		$("#contractType2").attr("class","selected");
		$("#contractType1").attr("class","");
	}
	window.location.href = "/portal/htxx/htgl_index.jsp";
}

//侧边新闻
function getLatestNewsAside(channelID){
	
	var url = "<%=basePath%>jeecms/getJeecmsInfo.action?random="+Math.random();
	
	$.ajax({
		async:false, 
		url: url, // 跨域URL 
		type: 'post', 
		dataType: 'json', 
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'3'
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(XMLHttpRequest);
			alert(textStatus);
			alert(errorThrown);
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			var newsP = "javascript:void(0)";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
					newsLi += " href='<%=basePath%>jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a></li>";
					newsP ="<%=basePath%>jeecms/findStfbNewsByPage.action?channelId="+obj[i].channelId;
				}
				
				$(".asideUl:eq(0)").html(newsLi);
				$(".asideH:eq(0) a").attr("href",newsP);
				if(obj.length==0){
					$(".asideUl:eq(0)").html("&nbsp;&nbsp;无相关信息。");
					$(".asideH:eq(0) a").hide();
				}
			}else{
				$(".asideUl:eq(0)").html("&nbsp;&nbsp;无相关信息。");
				$(".asideH:eq(0) a").hide();
			}
		}	  
	});	
	

}
function setShortTitle(shortTitle,Title){
	if(shortTitle=="null"){
		return shortTitle;
	}else{
		return Title;
	}
}
    </script>
    </head>
    <body>
<div class="main" >
<!--Ctrl-->
<div class="ctrl clearfix">
      <div class="fl"><img src="css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
      <div class="posi fl">
    <ul>
          <li><a href="#">合约管理</a></li>
          <li class="fin" id="banner">首页 - 数据范围:[2012年]-[运维类]</li>
        </ul>
  </div>
      <div class="fr lit_nav">
    <ul>
          <li class="selected"><a class="print" href="#">打印</a></li>
          <li><a class="storage" href="#">网络硬盘</a></li>
          <li><a class="rss" href="#">订阅</a></li>
          <li><a class="chat" href="#">聊天</a></li>
          <li><a class="query" href="#">查询</a></li>
        </ul>
  </div>
    </div>
<!--Ctrl End-->
<div class="clearfix pt45">
      <table>
    <tr>
          <td style="min-width:970px;"><!--News-->
        
        <div class="panel_4 panel_8 mb10">
          <header>
            <div class="tit">
                <div class="bg clearfix">
                	<h5 class="fl stats">合同平台业务统计</h5>
                <div class="fr pt5"></div>
              </div>
            </div>
          </header>
              <div class="con clearfix"> 
            <!--4panles-->
            <table width="100%" class="mb10 p0">
                  <tr>
                <td width="230"><div class="gray_border">
                    <div class="block h125">
                      <div>
                        <div class="categories t_r mr5"><a href="#">合同采购方式</a></div>
                        <div class="clear"></div>
                        <div class="mr5 clearfix">
                          <div class="border_gary_mini fl  mr8" id="pie1"> </div>
                          <div class="fl clearfix lh"> <span class="fl mr8">公开招标</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie1"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">采购平台</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie1"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">直接委托</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie1"></h3>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div></td>
                <td width="230"><div class="gray_border">
                    <div class="block h125">
                      <div>
                        <div class="categories t_r mr5"><a href="#">合同状态统计</a></div>
                        <div class="clear"></div>
                        <div class="mr5 clearfix">
                          <div class="border_gary_mini fl  mr8" id="pie2"> </div>
                          <div class="fl clearfix lh" style="line-height:20px"> <span class="fl mr8">已备案</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie2"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">已实施</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie2"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">已验收</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie2"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">已销号</span> <span class="fr">个</span>
                            <h3 class="fr" id="topPie2"></h3>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div></td>
                <td><div class="gray_border">
                    <div class="block h125">
                      <div>
                        <div class="categories t_r mr5"><a href="#">合同变更统计</a></div>
                        <div class="clear"></div>
                        <div class="mr5 mb10">
                          <div class="clearfix"> <span class="fl mr8">变更金额</span> <span class="fr pt5">万元</span>
                            <h3 class="fr" id="columnLinePrice"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">变更数量</span> <span class="fr pt5">个</span>
                            <h3 class="fr" id="columnLineNum"></h3>
                          </div>
                          <div class="border_gary h40" id="columnLine"> 
                            <!--<img src="css/default/images/zxt.png" height="40">--> 
                          </div>
                        </div>
                      </div>
                    </div>
                  </div></td>
                <td><div class="gray_border">
                    <div class="block h125">
                      <div>
                        <div class="categories t_r mr5"><a href="#">合同支付统计</a></div>
                        <div class="clear"></div>
                        <div class="mr5 mb10">
                          <div class="clearfix"> <span class="fl mr8">计划支付</span> <span class="fr pt5">万元</span>
                            <h3 class="fr" id="duallinearPrice1"></h3>
                            <div class="clear"></div>
                            <span class="fl mr8">实际支付</span> <span class="fr pt5">万元</span>
                            <h3 class="fr" id="duallinearPrice2"></h3>
                          </div>
                          <div class="border_gary h40" id="duallinear"> </div>
                        </div>
                      </div>
                    </div>
                  </div></td>
              </tr>
                </table>
            <!--4panles end-->
            <div class="b_bor plr8">
                  <div class="bor h280 mb10" id="column"></div>
                </div>
            <div class="clearfix p8">
                  <div class="fl">
                <label for="textfield">合同编号</label>
                <input type="text" class="mr8 input_large" name="contractNo" id="contractNo">
                <label for="textfield2">合同名称</label>
                <input type="text" class="mr8 input_large" name="contractName" id="contractName">
                <input type="hidden" name="contractId" id="contractId">
                <input type="submit" class="mr5" name="searchContract" id="searchContract" value="检索">
                <input type="submit" class="mr5" name="advancedSearch" id="advancedSearch" value="高级检索">
              </div>
                  <div class="fr">
                <input type="button" class="mr5" id="chartType" value="月视图" onclick="changeType('month')">
                <input type="button" class="mr5" id="chartType" value="年视图" onclick="changeType('year')">
              </div>
                </div>
          </div>
            </div>
        
        <!--News End--> 
        <!--Panel_3-->
        
        <div class="panel_4 mb10 panel_8">
              <header>
            <div class="tit">
                  <div class="bg clearfix">
                <h5 class="fl stats">运维类合同数量、金额信息统计</h5>
              </div>
                </div>
          </header>
              <div class="con clearfix">
            <div class="fl w50p">
                  <div class="gray_border">
                <div class="block">
                      <div>
                    <div class="categories t_r mr5"><a href="#">更多</a></div>
                    <div class="clear"></div>
                    <div class="t_c plr8">
                          <div class="bor" id="container"> </div>
                          <b class="t_c"><!--最小比例330×208--></b> </div>
                  </div>
                    </div>
              </div>
                </div>
            <div class="fl w50p">
                  <div class="gray_border">
                <div class="block">
                      <div class="corner">
                    <div class="categories t_r mr5"><a href="#">更多</a></div>
                    <div class="clear"></div>
                    <div class="t_c plr8">
                          <div class="bor" id="container2">  </div>
                          <b class="t_c"><!--最大472×297--></b> </div>
                  </div>
                    </div>
              </div>
                </div>
          </div>
            </div>
        
        <!--Panel_3 End--> 
        <!--Panel_3-->
        
        <div class="panel_4 mb10 panel_8">
              <header>
            <div class="tit">
                  <div class="bg clearfix">
                <h5 class="fl stats">合同业务管控信息提示</h5>
              </div>
                </div>
          </header>
              <div class="con">
            <ul class="clearfix" id="controlInfo"></ul>
          </div>
            </div>
        
        <!--Panel_3 End--></td>
          <td><!--Aside-->
        
        <aside style="width:280px;">
        	<div class="panel_1">
				<div class="bg_2">
					<div class="bg_3">
			          	<hgroup class="asideH"><h5>相关链接</h5></hgroup>
						<ul class="list">
							<li><a target="_blank" title="招标计划管理" href="http://10.1.48.69:8088/bid/index">招标计划管理</a></li>
						</ul>
			        </div>
				</div>
			</div>

            <div class="panel_1">
                <div class="bg_2">
                    <div class="bg_3">
                        <hgroup class="asideH"><h5>长期合作协议单位</h5><a target="_blank" href="http://10.1.48.16:7001/investCost/treaty/report.action" class="more_1">更 多</a></hgroup>
                        <div  class="wrap">
                            <ul  class="list" id="treatyList">

                            </ul></div>
                    </div>
                </div>
            </div>

        	<div class="panel_1"><div class="bg_2"><div class="bg_3">
          		  <hgroup class="asideH">
                   	<h5>公告板</h5>
                       <a target="_blank" href="#" class="more_1">更 多</a>
                 </hgroup>
                <ul class="asideUl list" id="asideUl">
                   </ul>
           	</div></div></div>
        
              <div class="panel_1">
            <div class="bg_2">
                  <div class="bg_3">
                <hgroup class="mb10">
                      <h5 class="t_r mr5">
                    <select id="yearSelect" onchange="changeYear(this)">
                          
                    </select>
                  </h5>
                      <ul class=" t_r clearfix pt5">
                    <li class="fr mr5"><a href="/portal/htxx/htgl_index.jsp" class="gk_2 selected">图形报表</a></li>
                    <li class="fr mr5"><a href="/portal/htxx/projectList.action?contractType=2," class="gk_1">数据报表</a></li>
                  </ul>
                </hgroup>
                <div class="tabs_3">
                  <ul>
                    <li id="contractType1"><a href="javascript:void(0)">建设类</a></li>
                    <li id="contractType2" class="selected"><a href="javascript:void(0)" onclick="changeContractType('2')">运维类</a></li>
                  </ul>
                    </div>
                <div class="clearfix plr8"> <span class="fl pt10">全部合同数量</span>
                      <h1 class="fr" id="contractAll"></h1>
                </div>
                <ul class="list_2 clearfix" id="contractCompanyList">
                	<!-- 
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2541)">运一公司 <i>(61)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2542)">运二公司 <i>(44)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2543)">运三公司 <i>(50)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2544)">运四公司 <i>(32)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2540)">运管中心 <i>(1)</i></a></li>
                      <li class="clearfix"></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2718)">车辆公司 <i>(205)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2719)">供电公司 <i>(129)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2720)">通号公司 <i>(267)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2721)">工务公司 <i>(92)</i></a></li>
                      <li class="w49p fl"><a href="javascript:void(0)" onclick="changeCompany(2722)">物资公司 <i>(1044)</i></a></li>
                      <li class="clearfix"></li>
                      -->
                      <li class="w49p fl"><a href="javascript:void(0)"></a></li>
                  </ul>
              </div>
                </div>
          </div>
              <div class="mb10 clearfix mr8">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                  <tr>
                <td><div class="mb10 mr_5 hy_button">
                      <a href="<%=investCostUrl %>/contract/showBatchUploadCustomized.action" target="_blank" class="pllr">批量导入</a></div></td>
                <td><div class="mb10 ml_5 hy_button">
                      <a href="<%=investCostUrl %>/contract/showAdd.action" target="_blank" class="htzl">合同后审核</a></div></td>
              </tr>
                  <tr>
                <td><div class="mr_5 hy_button">
                      <a href="#" class="wsht">变更录入</a></div></td>
                <td><div class="ml_5 hy_button">
                      <a href="<%=investCostUrl %>/contractStatus/showBatchUpload.action" class="dtxx" target="_blank">支付录入</a></div></td>
              </tr>
                </table>
          </div>
              <div class="panel_1">
            <div class="bg_2">
                  <div class="bg_3">
                <hgroup>
                      <h5>项目统计</h5>
                      <a href="<%=investCostUrl%>/project/findProjectByPage.action" target="_blank" class="more_1">更 多</a> <span class="t_r mr_5">统计截止日期：<b id="deadline"></b></span></hgroup>
                <div class="b_dash mb10 p8"> <b>当月发生</b>
                      <ul>
                    <li class="clearfix"> <span class="fl pt16">项目数量</span> <span class="fr pt16">个</span>
                          <h1 class="fr" id="thisMonthNumProject"></h1>
                        </li>
                    <li class="clearfix"> <span class="fl pt16">项目金额</span> <span class="fr pt16">万元</span>
                          <h1 class="fr" id="thisMonthPriceProject"></h1>
                        </li>
                  </ul>
                    </div>
                <div class="plr8"> <b>累计统计</b>
                      <ul>
                    <li class="clearfix"> <span class="fl pt16">项目数量</span> <span class="fr pt16">个</span>
                          <h1 class="fr" id="sumNumProject"></h1>
                        </li>
                    <li class="clearfix"> <span class="fl pt16">项目金额</span> <span class="fr pt16">万元</span>
                          <h1 class="fr" id="sumPriceProject"></h1>
                        </li>
                        <!-- 
                        <li class="clearfix"> <span class="fl pt16">待补全信息的项目</span> <span class="fr pt16">个</span>
                    <h1 class="fr" id="dbqxmProject"></h1>
                     -->
                  </li>
                  </ul>
                    </div>
              </div>
                </div>
          </div>
              <div class="panel_1">
              <div class="bg_2">
            <div class="bg_3">
                <hgroup>
                <h5>合同统计</h5>
                <a href="<%=investCostUrl%>/contract/findContractByPage.action" target="_blank" class="more_1">更 多</a> <span class="t_r mr_5">统计截止日期：<b id="deadline"></b></span> 
                </hgroup>
                  <div class="b_dash mb10 p8"> <b>当月发生</b>
                <ul>
                      <li class="clearfix"> <span class="fl pt16">合同数量</span> <span class="fr pt16">个</span>
                    <h1 class="fr" id="thisMonthNumContract"></h1>
                  </li>
                      <li class="clearfix"> <span class="fl pt16">合同金额</span> <span class="fr pt16">万元</span>
                    <h1 class="fr" id="thisMonthPriceContract"></h1>
                  </li>                 
                    </ul>
              </div>
                  <div class="plr8"> <b>累计统计</b>
                <ul>
                      <li class="clearfix"> <span class="fl pt16">合同数量</span> <span class="fr pt16">个</span>
                    <h1 class="fr" id="sumNumContract"></h1>
                  </li>
                      <li class="clearfix"> <span class="fl pt16">合同金额</span> <span class="fr pt16">万元</span>
                    <h1 class="fr" id="sumPriceContract"></h1>
                  </li>
                  <!-- 
                   <li class="clearfix"> <span class="fl pt16">待补全信息的合同</span> <span class="fr pt16">个</span>
                    <h1 class="fr" id="dbqhtContract"></h1>
                  </li>
                   -->
                    </ul>
              </div>
                </div>
          </div>
          </div>
            </aside>
        
        <!--Aside End--></td>
        </tr>
  </table>
      <div class="right_main mb10" style="width:100%"> </div>
    </div>
    </div>
</body>
</html>
<!-- 
"fr">1.234</h1>
                        </li>
                        <li class="clearfix"> <span class="fl pt16">计划支付</span> <span class="fr pt16">万元</span>
                          <h1 class="fr">0.987</h1>
                        <li class="clearfix"> <span class="fl pt16">实际支付</span> <span class="fr pt16">万元</span>
                          <h1 class="fr">0.987</h1>
                        </li>
                      </ul>
                  </div>
                </div>
              </div>
            </aside>
            
            </td>
        </tr>
      </table>
      <div class="right_main mb10" style="width:100%"> </div>
    </div>
</body>
</html>
 -->
