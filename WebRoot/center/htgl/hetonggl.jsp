<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	//String investCostUrl ="http://10.1.48.40/investCost";
	String investCostUrl ="http://localhost:8080/investCost";
	//String investCostUrl ="http://10.1.40.202:7001/investCost";
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申通地铁集团综合业务协同平台</title>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>htxx/css/default/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>htxx/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script src="../../js/jquery.sparkline.min.js"></script>
<!--[if ie 6]>
	<script src="js/iepng.js" type="text/javascript"></script>
<![endif]-->
<style type="">
h1 {
    font-size: 26px;
}
</style>
<script>
 var type = "month";

$(function(){
	$('.left_menu ul').each(function(index1){
		$(this).children("li").each(function(index2){
			$(this).click(function(){
				$(this).addClass('li_cur').siblings().removeClass('li_cur');
				$('.view'+index1+' > div').eq(index2).removeClass('none').siblings().addClass('none');
			});
		});
	});
	
	$('#menu li').each(function(index){
		$(this).click(function(){
			var targetView = 'view'+index;				//内容
			var targetTitle = 'title'+index;			//左侧标题
			
			$('div[class^="view"]').hide();	
			$('ul[class^="title"]').hide();
			
			$('div[class="'+targetView+'"]').show();
			$('ul[class="'+targetTitle+'"]').show();
			
			var $targetA = $(this).find("a");
			var clazz = $targetA.attr("class");
			if(clazz.indexOf('_')==-1){
				clazz = clazz+'_cur';
			}
			$targetA.removeClass().addClass(clazz);
			
			$(this).siblings().each(function(){
				var $targetA = $(this).find("a");
				var clazz = $targetA.attr("class");
				if(clazz.indexOf('_')!=-1){
					clazz = clazz.substring(0,clazz.indexOf('_'));
				}
				$targetA.removeClass().addClass(clazz);
			});
		});
	});
	
	getLatestNewsAside("1653");
	
	showCoverAlarm();			//显示告警管控信息，合同-项目
	
	showMainChart();
	showTopStat();
	showCoverManagement();			//显示管控信息
	showCoverContractBid();			//显示合同采购方式,柱状图
	showCoverContractBidByYear();	//显示合同采购方式，数据表
});

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
				//alert(XMLHttpRequest);
				//alert(textStatus);
				//alert(errorThrown);
				alert("系统连接失败，请稍后再试！");
			},
			success: function(obj){			
				var newsLi = "";
				var newsP = "javascript:void(0)";
				if(obj){
					for(var i=0;i<obj.length;i++){
						newsLi += "<li><a href='<%=basePath%>jeecms/contentDetail.jsp?content="+obj[i].contentId+"' target='_blank'>"+obj[i].title+"</a></li>";
					}
				}else{
					newsLi = "<li>无相关信息</li>";
				}
				$("#ggb").html(newsLi);
			}
		});	
	}

	function showCoverAlarm(){
		$.ajax({
		type : 'post',
		url : '/portal/htxx/showCoverAlarm.action',
		dataType:'json',
		cache : false,
		data:{
			companyId:'',
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			var addHtml="";
			
			if(obj!=null){
				for(var i=1;i<=12;i++){
					addHtml +="<li class=\"gray_border counter w33p fl\"><div class=\"block\"><div class=\"corner h56 clearfix\">";
					
					if(i==1){
						addHtml +="<span class=\"c1 pt16\">立项后合同签订超时提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr1!=null && obj.attr1!='0')
							addHtml +="<a href='<%=investCostUrl%>/project/findByNewCondition.action?condition=condition1' target='_blank'><h1 class=\"fr\" style=\"color: red;\">"+obj.attr1+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: red;\">"+obj.attr1+"</h1>";
					}else if(i==2){
						addHtml +="<span class=\"c1 pt16\">项目批复概算超额告警提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr12!=null && obj.attr12!='0')
							addHtml +="<a href='<%=investCostUrl%>/project/findByNewCondition.action?condition=condition2' target='_blank'><h1 class=\"fr\" style=\"color: red;\">"+obj.attr2+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: red;\">"+obj.attr2+"</h1>";
					}else if(i==3){
						addHtml +="<span class=\"c1 pt16\">合同审批流程超时提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr3!=null && obj.attr3!='0')
							addHtml +="<a href='#'><h1 class=\"fr\" style=\"color: red;\">"+obj.attr3+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: red;\">"+obj.attr3+"</h1>";
					}else if(i==4){
						addHtml +="<span class=\"c1 pt16\">合同先执行后签订风险提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr4!=null && obj.attr1!='0')
							addHtml +="<a href='<%=investCostUrl%>/contract/findByNewCondition.action?condition=condition4' target='_blank'><h1 class=\"fr\" style=\"color: red;\">"+obj.attr4+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: red;\">"+obj.attr4+"</h1>";
					}else if(i==5){
						addHtml +="<span class=\"c1 pt16\">合同变更超额告警提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr5!=null && obj.attr5!='0')
							addHtml +="<a href='<%=investCostUrl%>/contract/findByNewCondition.action?condition=condition5' target='_blank'><h1 class=\"fr\" style=\"color: red;\">"+obj.attr5+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: red;\">"+obj.attr5+"</h1>";
					}else if(i==6){
						addHtml +="<span class=\"c1 pt16\">合同支付超付告警提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr6!=null && obj.attr6!='0')
							addHtml +="<a href='<%=investCostUrl%>/contract/findByNewCondition.action?condition=condition6' target='_blank'><h1 class=\"fr\" style=\"color: red;\">"+obj.attr6+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: red;\">"+obj.attr6+"</h1>";
					}else if(i==7){
						addHtml +="<span class=\"c2 pt16\">合同进展信息风险提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr7!=null && obj.attr7!='0')
							addHtml +="<a href='<%=investCostUrl%>/contract/findByNewCondition.action?condition=condition7' target='_blank'><h1 class=\"fr\" style=\"color: #ff9900;\">"+obj.attr7+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: #ff9900;\">"+obj.attr7+"</h1>";
					}else if(i==8){
						addHtml +="<span class=\"c2 pt16\">合同销号条件异常提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr8!=null && obj.attr8!='0')
							addHtml +="<a href='#'><h1 class=\"fr\" style=\"color: #ff9900;\">"+obj.attr8+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: #ff9900;\">"+obj.attr8+"</h1>";
					}else if(i==9){
						addHtml +="<span class=\"c2 pt16\">合同质保期到期提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr9!=null && obj.attr9!='0')
							addHtml +="<a href='#' ><h1 class=\"fr\" style=\"color: #ff9900;\">"+obj.attr9+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: #ff9900;\">"+obj.attr9+"</h1>";
					}else if(i==10){
						addHtml +="<span class=\"c3 pt16\">采购竞价合同申报待办提示</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr10!=null && obj.attr10!='0')
							addHtml +="<a href='#' ><h1 class=\"fr\" style=\"color: green;\">"+obj.attr10+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: green;\">"+obj.attr10+"</h1>";
					}else if(i==11){
						addHtml +="<span class=\"c3 pt16\">待补全项目信息</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr11!=null && obj.attr11!='0')
							addHtml +="<a href='<%=investCostUrl%>/project/findNewProjectByPageToBeComplemented.action' target='_blank' ><h1 class=\"fr\" style=\"color: green;\">"+obj.attr11+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: green;\">"+obj.attr11+"</h1>";
					}else if(i==12){
						addHtml +="<span class=\"c3 pt16\">待补全合同信息</span><span class=\"fr pt24 mr5\" >个</span>";
						if(obj.attr12!=null && obj.attr12!='0')
							addHtml +="<a href='<%=investCostUrl%>/contract/findNewContractByPageToBeComplemented.action' target='_blank'><h1 class=\"fr\" style=\"color: green;\">"+obj.attr12+"</h1></a>";
						else 
							addHtml +="<h1 class=\"fr\" style=\"color: green;\">"+obj.attr12+"</h1>";
					}
				}
				$("#managementTable").html(addHtml);
			}
			}
		});
	}

	//合同金额与数量（累计）
	function showMainChart(){
		$.ajax({
			type : 'post',
			url:'/portal/htxx/findCoverContractPrice.action',
			dataType:'json',
			cache : false,
			data:{
				type : type,
				random : Math.random()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(obj){
				if(obj!=null ){
					newColumn('contractPic',obj.controlYear,obj.contractCount,obj.contractPrice);
				}
			}
		});
	}
		

	function newPic(targetId,columnList,lineList){
		var chartOption ={
			chart: {
				renderTo:targetId,
				spacingTop: 20,
				spacingBottom:20,
				height:350
                //width:500
			},
			title: {
				text: '合同金额与数量（累计）',
				align:'left',
				x:100,
				margin:20,
				style: {
	                fontWeight: 'bold',
	                fontSize:'16px'	                
	            }
			},
			xAxis: {
				categories: ['建设类','运营类','维护类']
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
	                min:0,
	                title: {//y轴名称
	                    text: '合同金额（万元）',
	                    style: {
	                        color: '#89A54E'
	                    }
	                }
	            }, { // 第二个Y轴
	                title: {
	                    text: '合同数量（个）',
	                    style: {
	                        color: '#4572A7'
	                    }
	                },
	                min:0,
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
				x: 0,
				verticalAlign: 'top',
				y: 0,
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
						color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
					}
				},style: {		
	                fontSize:'9px'
	            }
			},
			series: [{
	                type: 'column',
					yAxis:1,
	                name: '合同数量',
	                data: columnList
	            },{
	                type: 'line',
	                name: '合同金额',
	                data: lineList             
	            }
	        ],
			credits:{					
				enabled:false
			}
		};
		new Highcharts.Chart(chartOption); 
	}
	
	//画柱状图
function newColumn(targetId,categories,columnList,lineList){
	if(lineList!=null && lineList.length>0){
		for(var i=0;i<lineList.length;i++){
			lineList[i] = parseFloat(lineList[i].toFixed(6));
		}
	}
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
                    text: '合同价(万元)',
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
                color:'#4572A7',
                data: columnList
            },{
                type: 'line',
                name: '合同价',
                color:'#AA4643',
                data: lineList             
            }
        ],
		credits:{					
			enabled:false
		}
	};
	new Highcharts.Chart(chartOption); 
	//['#AA4643','#4572A7','#89A54E']; 
}

function changeType(chartType){
	type = chartType;
	showMainChart();
}

function showSparklineBar(id,data,title,unit){

	$("#"+id+"").sparkline(data,{
		type: 'bar', 
		height: '60px',
		width:'180px',
		tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+title,
		tooltipSuffix:unit,
		tooltipPrefix:title+'：',
		barWidth:7,
		barSpacing:3});
}	

function showTopStat(){
$.ajax({
		async:false, 
		url:'/portal/htxx/findCoverProjectContractStat.action',
		type: 'post', 
		dataType: 'json', 
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){	
			if(obj!=null){
				$('#dayProjectCount').html(obj.projectCount[obj.projectCount.length-1]);
				$('#monthProjectCount').html(obj.coverProjectContractStat.monthProjectCount);
				$('#yearProjectCount').html(obj.coverProjectContractStat.yearProjectCount);
				$('#dayProjectPrice').html(obj.projectPrice[obj.projectPrice.length-1]);
				$('#monthProjectPrice').html(obj.coverProjectContractStat.monthProjectPrice);
				$('#yearProjectPrice').html(obj.coverProjectContractStat.yearProjectPrice);
				$('#dayContractCount').html(obj.contractCount[obj.contractCount.length-1]);
				$('#monthContractCount').html(obj.coverProjectContractStat.monthContractCount);
				$('#yearContractCount').html(obj.coverProjectContractStat.yearContractCount);
				$('#dayContractPrice').html(obj.contractPrice[obj.contractPrice.length-1]);
				$('#monthContractPrice').html(obj.coverProjectContractStat.monthContractPrice);
				$('#yearContractPrice').html(obj.coverProjectContractStat.yearContractPrice);
				$('#dayPayPrice').html(obj.payPrice[obj.payPrice.length-1]);
				$('#monthPayPrice').html(obj.coverProjectContractStat.monthPayPrice);
				$('#yearPayPrice').html(obj.coverProjectContractStat.yearPayPrice);
				
				showSparklineBar('projectCountPic',obj.projectCount,'项目数量','个');
				showSparklineBar('projectPricePic',obj.projectPrice,'项目金额','万元');
				showSparklineBar('payPricePic',obj.payPrice,'支付金额','万元');
				showSparklineBar('contractCountPic',obj.contractCount,'合同数量','个');
				showSparklineBar('contractPricePic',obj.contractPrice,'合同金额','万元');
			}
		}

	});	
}

function showCoverManagement(){
	$.ajax({
			type : 'post',
			url:'/portal/htxx/showCoverManagement.action',
			dataType:'json',
			cache : false,
			data:{
				type : type,
				random : Math.random()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(obj){
				if(obj!=null && obj.length>0){
					var addHtml = '<thead><tr>'+
	                                '<th></th>'+
	                                '<th class="th_b_l">立项后合同签订超时</th>'+
									'<th class="th_b_l">项目批复概算超额</th>'+
									'<th class="th_b_l">合同先执行后签订</th>'+
									'<th class="th_b_l">合同支付超付</th>'+
	                              '</tr></thead>';
					for(var i=0;i<obj.length;i++){
						addHtml +=  '<tr >'+
	                                '<td width="110"><i>'+obj[i].companyName+'</i></td>'+
	                                '<td width="109"><b><a href="<%=investCostUrl%>/project/findByNewCondition.action?condition=condition1&companyId='+obj[i].companyId+'" target="_blank">'+obj[i].contractOvertime+'</a></b></td>'+
	                                '<td width="109"><b><a href="<%=investCostUrl%>/project/findByNewCondition.action?condition=condition2&companyId='+obj[i].companyId+'" target="_blank">'+obj[i].projectBudgetOverpay+'</a></b></td>'+
	                                '<td width="109" class="tr_b_r"><b><a href="<%=investCostUrl%>/contract/findByNewCondition.action?condition=condition4&companyId='+obj[i].companyId+'" target="_blank">'+obj[i].contractExecuteFirst+'</a></b></td>'+
	                                '<td width="109" class="td_b_r"><b><a href="<%=investCostUrl%>/contract/findByNewCondition.action?condition=condition6&companyId='+obj[i].companyId+'" target="_blank">'+obj[i].contractOverpay+'</a></b></td>'+
	                              '</tr>';
					}
					$('#coverManegement').html(addHtml);
				}
			}
		});
}

function showCoverContractBid(){
	$.ajax({
		type : 'post',
		url:'/portal/htxx/showCoverContractBid.action',
		dataType:'json',
		cache : false,
		data:{
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			if(obj!=null ){
				$('#columnPic').highcharts({ 
					chart: { 
						type: 'column',
						width:'1014'
					}, 
					title: { text: '合同采购方式' }, 
					xAxis: { categories: obj.categories }, 
					yAxis: { min: 0, title: { text: '' } }, 
					tooltip: { pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>', shared: true }, 
					plotOptions: { column: { stacking: 'percent' } }, 
					 credits: {
               			  enabled:false
               		  },
					series: [
						{ name: '公开招标', color:'#89A54E',data: obj.bid1List }, 
						{ name: '采购平台', color:'#AA4643',data: obj.bid2List }, 
						{ name: '直接委托', color:'#4572A7',data: obj.bid3List }] 
						
						//['#AA4643','#4572A7','#89A54E']; 
				});
			}
		}
	});
}

function showCoverContractBidByYear(year){
	if(year==null) year = $('#contractBidYear').val(); 
	$.ajax({
		type : 'post',
		url:'/portal/htxx/showCoverContractBidByYear.action',
		dataType:'json',
		cache : false,
		data:{
			year : year,
			random : Math.random()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(obj){
			if(obj!=null && obj.length>0){
				var addHtml = '<thead><tr>'+
	                                '<th></th>'+
	                                '<th class="th_b_l" colspan="2">公开招标</th>'+
	                                '<th class="th_b_l" colspan="2">直接委托</th>'+
									'<th class="th_b_l" colspan="2">采购平台</th>'+
	                              '</tr>'+
	                          '</thead>';
	                addHtml += '<tr>'+
	                              	'<td></td>'+
	                              	'<td>数量(个)</td><td>金额(万元)</td>'+
	                              	'<td>数量(个)</td><td>金额(万元)</td>'+
	                              	'<td>数量(个)</td><td>金额(万元)</td>'+
	                              '</tr>';
					for(var i=0;i<obj.length;i++){
						addHtml +=  '<tr>'+
	                                '<td width="110"><i>'+obj[i][6]+'</i></td>'+
	                                '<td width="109"><b>'+obj[i][0]+'</b></td>'+
	                                '<td width="109"><b>'+obj[i][1]+'</b></td>'+
	                                '<td width="109"><b>'+obj[i][2]+'</b></td>'+
	                                '<td width="109"><b>'+obj[i][3]+'</b></td>'+
	                                '<td width="109"><b>'+obj[i][4]+'</b></td>'+
	                                '<td width="109"><b>'+obj[i][5]+'</b></td>'+
	                              '</tr>';
					}
					$('#contractBidTable').html(addHtml);
			}
		}
	});
}
</script>

</head>

<body>
<div class="Main clearfix">
	<div class="Hetong_c clearfix">
		<div class="c_numlist fL" style="font-size: 14px;color: #979797; ">
			<div class="Num_03 b_l_no" style="width: 294px;">
				<dl >
					<table width="100%">
						<tr>
                          	<td colspan="2">
                                <h3 class="fl ml5" style="color: #000000;font-weight: bold;font-size: 16px;font-style: normal;">&nbsp;项目数量</h3>
                                <div class="clearfix fr mr5">
	                                <h6 class="pt10 fr">个</h6>
	                                <h1 class="fr mr5" id="dayProjectCount" style="cursor:pointer;color: #0664FF;font-size: 26px;" onclick=""></h1>
                                </div>
                            </td>
                        </tr>
						<tr>
							<td>
							<ul class="mr5">
								<li class="clearfix">
									<span class="fl" >月累计：</span>
									<h6 class="fr">个</h6>
									<b class="fr mr5" id="monthProjectCount" style="color: black;"></b>
								</li>
								<li>&nbsp;</li>
								<li class="clearfix">
									<span class="fl" >年累计：</span>
									<h6 class="fr">个</h6>
									<b class="fr mr5" id="yearProjectCount" style="color: black;"></b>
								</li>
							</ul>
							</td>
							<td class="pt5" width="50">
								<div class="border_gary fr mr5">&nbsp;<span id="projectCountPic"></span></div>
							</td>
						</tr>
					</table>
				</dl>
			</div>
			<div class="Num_03 b_l_no" style="width: 294px;">
				<dl >
					<table width="100%">
						<tr>
                          	<td colspan="2">
                                <h3 class="fl" style="color: #000000;font-weight: bold;font-size: 16px;font-style: normal;">&nbsp;项目金额</h3>
                                 <div class="clearfix fr mr5">
	                                <h6 class="pt10 fr">万元</h6>
	                                <h1 class="fr mr5" id="dayProjectPrice" style="cursor:pointer;color: #0664FF;font-size: 26px;" onclick=""></h1>
                                </div>
                            </td>
                        </tr>
						<tr>
							<td>
							<ul class="mr5">
								<li class="clearfix">
									<span class="fl" >月累计：</span>
									<h6 class="fr">万元</h6>
									<b class="fr mr5" id="monthProjectPrice" style="color: black;"></b>
								</li>
								<li>&nbsp;</li>
								<li class="clearfix">
									<span class="fl" >年累计：</span>
									<h6 class="fr">万元</h6>
									<b class="fr mr5" id="yearProjectPrice" style="color: black;"></b>
								</li>
							</ul>
							</td>
							<td class="pt5" width="50">
								<div class="border_gary fr mr5">&nbsp;<span id="projectPricePic"></span></div>
							</td>
						</tr>
					</table>
				</dl>
			</div>
			<div class="Num_03 b_l_no b_r_no" style="width: 294px;">
				<dl >
					<table width="100%">
						<tr>
                          	<td colspan="2">
                                <h3 class="fl" style="color: #000000;font-weight: bold;font-size: 16px;font-style: normal;">&nbsp;支付金额</h3>
                                 <div class="clearfix fr mr5">
	                                <h6 class="pt10 fr">万元</h6>
	                                <h1 class="fr mr5" id="dayPayPrice" style="cursor:pointer;color: #0664FF;font-size: 26px;" onclick=""></h1>
                                </div>
                            </td>
                        </tr>
						<tr>
							<td>
							<ul class="mr5">
								<li class="clearfix">
									<span class="fl" >月累计：</span>
									<h6 class="fr">万元</h6>
									<b class="fr mr5" id="monthPayPrice" style="color: black;"></b>
								</li>
								<li>&nbsp;</li>
								<li class="clearfix">
									<span class="fl" >年累计：</span>
									<h6 class="fr">万元</h6>
									<b class="fr mr5" id="yearPayPrice" style="color: black;"></b>
								</li>
							</ul>
							</td>
							<td class="pt5" width="50">
								<div class="border_gary fr">&nbsp;<span id="payPricePic"></span></div>
							</td>
						</tr>
					</table>
				</dl>
			</div>
			<div class="Num_03 b_l_no" style="width: 294px;">
				<dl >
					<table width="100%">
						<tr>
                          	<td colspan="2">
                                <h3 class="fl" style="color: #000000;font-weight: bold;font-size: 16px;font-style: normal;">&nbsp;合同数量</h3>
                                <div class="clearfix fr mr5">
	                                <h6 class="pt10 fr">个</h6>
	                                <h1 class="fr mr5" id="dayContractCount" style="cursor:pointer;color: #0664FF;font-size: 26px;" onclick=""></h1>
                                </div>
                            </td>
                        </tr>
						<tr>
							<td>
							<ul class="mr5">
								<li class="clearfix">
									<span class="fl" >月累计：</span>
									<h6 class="fr">个</h6>
									<b class="fr mr5" id="monthContractCount" style="color: black;"></b>
								</li>
								<li>&nbsp;</li>
								<li class="clearfix">
									<span class="fl" >年累计：</span>
									<h6 class="fr">个</h6>
									<b class="fr mr5" id="yearContractCount" style="color: black;"></b>
								</li>
							</ul>
							</td>
							<td class="pt5" width="50">
								<div class="border_gary fr mr5">&nbsp;<span id="contractCountPic"></span></div>
							</td>
						</tr>
					</table>
				</dl>
			</div>
			<div class="Num_03 b_l_no" style="width: 294px;">
				<dl >
					<table width="100%">
						<tr>
                          	<td colspan="2">
                                <h3 class="fl" style="color: #000000;font-weight: bold;font-size: 16px;font-style: normal;">&nbsp;合同金额</h3>
                                <div class="clearfix fr mr5">
	                                <h6 class="pt10 fr">万元</h6>
	                                <h1 class="fr mr5" id="dayContractPrice" style="cursor:pointer;color: #0664FF;font-size: 26px;" onclick=""></h1>
                                </div>
                            </td>
                        </tr>
						<tr>
							<td>
							<ul class="mr5">
								<li class="clearfix">
									<span class="fl" >月累计：</span>
									<h6 class="fr">万元</h6>
									<b class="fr mr5" id="monthContractPrice" style="color: black;"></b>
								</li>
								<li>&nbsp;</li>
								<li class="clearfix">
									<span class="fl" >年累计：</span>
									<h6 class="fr">万元</h6>
									<b class="fr mr5" id="yearContractPrice" style="color: black;"></b>
								</li>
							</ul>
							</td>
							<td class="pt5" width="50">
								<div class="border_gary fr mr5">&nbsp;<span id="contractPricePic"></span></div>
							</td>
						</tr>
					</table>
				</dl>
			</div>
           <!--
           <div class="Num_03 b_t_l">
               <em>当月项目金额（万元）</em>
               <h3 id="project_month_money_sum"></h3>
               <i id="month_project_money_persent"></i>
           </div>
           <div class="Num_03 b_t_l">
               <em>当月合同数量</em>
               <h3 id="contract_month_count_sum"></h3>
               <i id="month_contract_count_persent"></i>
           </div>
           <div class="Num_03 b_t_l b_r_no">
               <em>当月合同金额（万元）</em>
               <h3 id="contract_month_money_sum"></h3>
               <i id="month_contract_money_persent"></i>
           </div>
             -->
        </div>
        <div class="ggb fR">
        	<h3><a href="<%=basePath%>jeecms/findStfbNewsByPage.action?channelId=1653" target="_blank">公告板</a></h3>
            <ul id="ggb">
            </ul>
        </div>
    </div>
  <div class="M_body">
    	<div class="m_b_top">
        	<span class="fL"><em class="home"><a href="index.html">首页</a> > <a href="###">全部</a>  > <i>合同金额数量</i></em></span>
            <div class="menu_r fR">
            	<ul id="menu">
                	<li><a href="###" class="icoall_cur">全部</a></li>
                    <li><a href="###" class="ico02">建设</a></li>
                    <li><a href="###" class="ico03"> &nbsp;&nbsp;运营</a></li>
                    <li><a href="###" class="ico04">维护</a></li>
                </ul>
            </div>
      </div>
        <div class="m_b_box">
        	<div class="left_menu">
                <ul class="title0">
                	<li class="li_cur"><a href="javascript:void(0)">合同金额数量</a></li>
					<li><a href="javascript:void(0)">合同监管告警</a></li>
                </ul>
                <ul style="display: none;"></ul>
                <ul style="display: none;"></ul>
                <ul class="title3" style="display: none;">
                	<li class="li_cur"><a href="javascript:void(0)">合同采购方式</a></li>
					<li><a href="javascript:void(0)">合同监管告警</a></li>
                </ul>
            </div>
            <div class="view0">
                <div class="right_content">
                    <div class="c_box02" style="width: 100%;">
                        <div id="contractPic"></div>
                    </div>
                    <!-- 
                    <div class="c_box02">
                        <div id="contractLine"></div>
                    </div>
                     -->
                    <div class="clear"></div>
                    <div class="text_c"><p>
                    	<input type="button" class="mr5" id="chartType" value="月视图" onclick="changeType('month')">
                		<input type="button" class="mr5" id="chartType" value="年视图" onclick="changeType('year')">
                    </p></div>
                </div>
                
                <div class="right_content none">
                	<div class="ht_lx clearfix" style="height:25px;">
                    <div class="c_box03" style="height:25px;">
                        <h3 style="border: none;">合同业务管控信息提示</h3>
                    </div>
                    </div>
                    <div class="years fL panel_4 mb10 panel_8" style="margin: 0;">
                    	 <div class="con">
				            <ul class="clearfix" id="managementTable"></ul>
				          </div>
                    </div>
                    <div class="clear"></div>
                </div>
                
            </div>
            
            <div class="view3" style="display: none;">
            	<div class="right_content">
                    <div class="c_box02" style="width: 100%;">
                        <div id="columnPic"></div>
                    </div>
                    <div class="clear"></div>
                    <div class="text_c">
                    	<div style="float: right;">
                    		统计年份
                    		<select id="contractBidYear" onchange="showCoverContractBidByYear(this.value);">
	                    		<option>2014</option>
	                    		<option>2013</option>
	                    		<option>2012</option>
	                    		<option>2011</option>
	                    		<option>2010</option>
	                    		<option>2009</option>
	                    		<option>2008</option>
	                    	</select>
                    	</div>
                    
                    	
                    	<p>
                    	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="R_c_body years_c" id="contractBidTable" style="border: 1px solid #C4C4C4;"> 
                         </table>
                    	</p>
                    </div>
                </div>
                <div class="right_content none">
                	<!-- 
                	<div class="ht_lx clearfix">
	                    <div class="c_box03">
	                        <div id="rangePic"></div>
	                    </div>
	                    <span class="unit fR">单位：（万元）</span>
                    </div>
                     -->
                    <div class="years fL">
                    	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="R_c_body years_c" id="coverManegement"> 
                            
                         </table>
                    </div>
                    <div class="clear"></div>
                </div>
                
            </div>
            
        </div>
        <div class="m_b_bottom"></div>
    </div>
    </div>
</body>
</html>

