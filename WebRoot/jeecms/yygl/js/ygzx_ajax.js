	//显示头版头条新闻图片
var basePath="";
function setbasePath(path){
	basePath=path;
}
function setShortTitle(shortTitle,Title){
	if(shortTitle=="null"){
		return shortTitle;
	}else{
		return Title;
	}
}
//获取日期
function getPreday(span){   
	var date = new Date();
    var yesterday_milliseconds=date.getTime()-1000*60*60*24*parseInt(span);       
    var yesterday = new Date();       
        yesterday.setTime(yesterday_milliseconds);       
        
    var strYear = yesterday.getFullYear();    
    var strDay = yesterday.getDate();    
    var strMonth = yesterday.getMonth()+1;  
    if(strMonth<10)    
    {    
        strMonth="0"+strMonth;    
    }    
    if(strDay<10)    
    {    
    	strDay="0"+strDay;    
    }  
    datastr = strYear+"-"+strMonth+"-"+strDay;  
    return datastr;  
  }  

//线路
function getMetroLine(){
			$.ajax({
				type: 'POST',
				url: '/portal/metroExpress/findMetroLineConfig.action?random='+Math.random(),
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var metroLine = "";
					var lineId = "";
					var lineName = "";
					for(var i=0;i<obj.length;i++){
						lineId = obj[i].split(":")[0];
						lineName = obj[i].split(":")[1];
						metroLine +="<option value='"+lineId+"'>"+lineName+"</option>";
					}
					$("#indicatorLine").html(metroLine);
					$("#expressLine").html(metroLine);
				}	  
			});	
		}
	

var y1c = 26601,y2c = 23702,y3c = 17335,y4c = 9961,cfc = 6365;
var y1 ,y2 ,y3 ,y4 ,cf = 0;
//牵引电量
function getQyDl(){
			$.ajax({
				type: 'POST',
				url: '/portal/metroExpress/getQyDl.action?random='+Math.random(),
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					if(obj){
						y1 = (obj.y1).toFixed(2);
						y2 = (obj.y2).toFixed(2);
						y3 = (obj.y3).toFixed(2);
						y4 = (obj.y4).toFixed(2);
						cf = (obj.cf).toFixed(2);
						
						var y1p = (y1 / y1c *100).toFixed(2) +"%";
						var y2p = (y2 / y2c *100).toFixed(2) +"%";
						var y3p = (y3 / y3c *100).toFixed(2) +"%";
						var y4p = (y4 / y4c *100).toFixed(2) +"%";
						var cfp = "0.00%";
						
						$("#y1").html(y1);$("#y2").html(y2);$("#y3").html(y3);$("#y4").html(y4);
						$("#cf").html(cf);
						$("#y1p").html(y1p);$("#y2p").html(y2p);$("#y3p").html(y3p);$("#y4p").html(y4p);
						$("#cfp").html(cfp);
					}
					
				}	  
			});	
		}
	

//运营指标
function getLatestIndicator(indicatorLine,indicatorDate,first){
	var inDate = '';
				$.ajax({
				type: 'POST',
				url: '/portal/metroIndicator/findMetroIndicatorLatestEvents.action?random='+Math.random(),
				data:{
					"indicatorLine" : indicatorLine,
					"indicatorDate" : indicatorDate
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){
					/*var ontimeMax = 0;
					var onworkMax = 0;
					var passCapMax = 0;
					if(obj.metroOntimeDailyCompare>150){
						ontimeMax = 150;
					}else{
						ontimeMax = obj.metroOntimeDailyCompare;
					}
					if(obj.metroOnworkDailyCompare>150){
						onworkMax = 150;
					}else{
						onworkMax = obj.metroOnworkDailyCompare;
					}
					if(obj.passengerCapacityDailyCompare>150){
						passCapMax = 150;
					}else{
						passCapMax = obj.passengerCapacityDailyCompare;
					}*/
					if(obj){
						$("#ontimeDaily").html(obj.metroOntimeDaily);
						//$("#ontimeDailyLast").html(obj.metroOntimeDailyLast+"%");					
						//$("#ontimeDiv").css("width",ontimeMax+"px");
						//$("#ontimeDiv").attr("title",obj.metroOntimeDailyCompare+"%");
						//$("#ontimeMonth").html(obj.metroOntimeMonth+"%");
						//$("#ontimeYear").html(obj.metroOntimeYear+"%");
						$("#onworkDaily").html(obj.metroOnworkDaily);
						//$("#onworkDailyLast").html(obj.metroOnworkDailyLast+"%");
						//$("#onworkDiv").css("width",onworkMax+"px");
						//$("#onworkDiv").attr("title",obj.metroOnworkDailyCompare+"%");
						//$("#onworkMonth").html(obj.metroOnworkMonth+"%");
						//$("#onworkYear").html(obj.metroOnworkYear+"%");
						$("#passCapDaily").html(obj.passengerCapacityDaily);
						$("#passCapDailyLast").html((obj.passengerCapacityDailyLast*1).toFixed(2));
						//$("#passCapDiv").css("width",passCapMax+"px");
						//$("#passCapDiv").attr("title",obj.passengerCapacityDailyCompare+"%");
						$("#passCapMonth").html((obj.passengerCapacityMonth*1).toFixed(2));
						$("#passCapYear").html((obj.passengerCapacityYear*1).toFixed(2));
						$("#distanceDaily").html(obj.metroDistanceDaily);
						$("#distanceMonth").html((obj.metroDistanceMonth*1).toFixed(2));
						$("#distanceYear").html((obj.metroDistanceYear*1).toFixed(2));
						$("#distanceDailyLast").html((obj.metroDistanceDailyLast*1).toFixed(2));	
						$("#incomeDaily").html(obj.ticketIncomeDaily);
						$("#incomeMonth").html((obj.ticketIncomeMonth*1).toFixed(2));
						$("#incomeYear").html((obj.ticketIncomeYear*1).toFixed(2));
						$("#incomeDailyLast").html((obj.ticketIncomeDailyLast*1).toFixed(2));	
						$("#lineDistance").html((obj.lineDistance*1).toFixed(0));	
						$("#stationCount").html(obj.stationCount);	
						$("#allocateDaily").html(parseInt(obj.allocateThreeSection)+parseInt(obj.allocateFourSection)+parseInt(obj.allocateSixSection)+parseInt(obj.allocateSevenSection)+parseInt(obj.allocateEightSection));	
						$("#allocateThree").html(obj.allocateThreeSection);	
						$("#allocateFour").html(obj.allocateFourSection);	
						$("#allocateSix").html(obj.allocateSixSection);	
						$("#allocateSeven").html(obj.allocateSevenSection);	
						$("#allocateEight").html(obj.allocateEightSection);	
						
						if(first=="1"){
							$("#indicatorDate").val(obj.indicatorDate);
							inDate = obj.indicatorDate;
							$("#indicatorDate").datepicker('option', 'maxDate', inDate); 
							//alert(inDate+" "+getPreday(2))
							if(getPreday(1)>inDate){
								$("#remind").html("[今日数据尚未更新]");
							}
							drawProduction(indicatorLine,inDate);
							drawQuality(indicatorLine,inDate);
							drawScale(indicatorLine,inDate);
						}
					}
				}	  
			});	
				
				
		}

//运营速报
function getLatestExpress(accidentLine){
			$.ajax({
				type: 'POST',
				url: '/portal/metroIndicator/findMetroExpressLatestEvents.action?random='+Math.random(),
				data:{
					"accidentLine" : accidentLine,
					"size"	: 6
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){		
					var expressDiv = "";	
					if(obj){
						for(var i=0;i<obj.length;i++){
							var line = obj[i].accidentLine;
							if(obj[i].accidentLine=='0'){
								line = "全网";
							}else if (obj[i].accidentLine=='99'){
								line = "磁浮线";
							}else{
								line = line+"号线";
							}
							expressDiv = "<div class='Alert_1'><div class='A_2'>";
                            expressDiv += "<div class='A_3'><div class='A_4'><div class='A_5'></div>";
                            expressDiv +="<div class='A_6'></div><div class='A_7'></div>";
                            expressDiv +="<div class='A_8'></div>";
                            expressDiv +="<div class='clearfix mb10'>";
                            expressDiv +="<h4 class='fl'>"+obj[i].accidentDate.substring(5)+"&nbsp;"+obj[i].accidentTime+"</h4>";
                            expressDiv +="<div class='fr line clearfix'>";
                            expressDiv +="<span class='mr5'><i class='L_0"+obj[i].accidentLine+"'>■</i>"+line+"</span>";
                            expressDiv +="<span class='mr5 Lb_0"+obj[i].accidentLine+"'>"+obj[i].accidentLocation+"</span>";
                           // expressDiv +="<span class='Lb_0"+obj[i].accidentLine+"'>车号</span>";
                            expressDiv +="</div></div><div>";
                            expressDiv +="<p><b>事件标题: "+obj[i].accidentTitle+"</b>";
                            
                            if(obj[i].remarkSimple==null || obj[i].remarkSimple==""){
                            	expressDiv +="<b class='d_il'>最新进展: </b><span class='d_il'>"+obj[i].detailSimple+"&nbsp;<a class='detaila' detailId='"+i+"' target='_blank' href='/portal/metroExpress/metroExpressView.action?metroExpressId="+obj[i].id+"'>详细&gt;&gt;</a>";
                            }else{
                            	expressDiv +="<b class='d_il'>最新进展: </b><span class='d_il'>"+obj[i].remarkSimple+"&nbsp;<a class='detaila' detailId='"+i+"' target='_blank' href='/portal/metroExpress/metroExpressView.action?metroExpressId="+obj[i].id+"'>详细&gt;&gt;</a>";
                            }
                            
                            expressDiv +="</span></p></div></div> </div></div></div>";
                            if(obj[i].remarkSimple==null || obj[i].remarkSimple==""){
                            	expressDiv +="<div class='expressDetail' style='z-index:9999;'>"+obj[i].accidentDetail+"</div>";
                            }else{
                            	expressDiv +="<div class='expressDetail' style='z-index:9999;'>"+obj[i].accidentRemarkAll+"</div>";
                            }
                                 // alert(expressDiv);
                           $("#expressTable td:eq("+i+")").html(expressDiv);
						}
						if(obj.length==0){
							$("#expressTable td:eq(0)").html("&nbsp;&nbsp;无相关信息。");
							$("#expressMore").hide();
						}else{
							$("#expressMore").show();
						}
					}
				}	  
			});	
		}

//cms
function getLatestNews(sj_id,pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += "<li class='clearfix'><a target='_blank' title='"+obj[i].copyTitle+"'";
							newsLi += " href='http://10.1.44.18/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a><span>"+obj[i].createTime.substring(5)+"</span></li>";
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID;
						}
						$(".reportDiv:eq("+pos+") ul").html(newsLi);
						$(".reportDiv:eq("+pos+") p").html("<a target='_self' class='more_3' href='"+newsP+"'>更多</a>");
						if(obj.length==0){
							$(".reportDiv:eq("+pos+") ul").html("&nbsp;&nbsp;无相关信息。");
							$(".reportDiv:eq("+pos+") p a").hide();
						}
					}
				}	  
			});	
			
		}
		
//侧边新闻
function getLatestNewsAside(channelID,pos){
	$.ajax({
		url:basePath+"/jeecms/getJeecmsInfo.action?random="+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:channelID,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'2'
		},
		error:function(){
			alert("系统连接失败，请稍后再试！");
		},
		success: function(obj){			
			var newsLi = "";
			var newsP = "javascript:void(0)";
			if(obj){
				for(var i=0;i<obj.length;i++){
					newsLi += "<li><a target='_blank' title='"+setShortTitle(obj[i].shortTitle,obj[i].title)+"'";
					newsLi += " href='../contentDetail.jsp?content="+obj[i].contentId+"'>"+obj[i].title+"</a></li>";
					newsP =basePath+ "/jeecms/findByPage.action?channelId="+obj[i].channelId;
				}
				
				$(".asideUl:eq("+pos+")").html(newsLi);
				$(".asideH:eq("+pos+") a").attr("href",newsP);
				if(obj.length==0){
					$(".asideUl:eq("+pos+")").html("&nbsp;&nbsp;无相关信息。");
					$(".asideH:eq("+pos+") a").hide();
				}
			}
			
			if(pos=="4"){
							$("#resourceIframe").attr("src","http://10.1.214.203/gjweb/jtsy.jsp");
							}
		}	  
	});	
}

//constructionNotice
function showConstructionLineInfo(start,end,sj_id,pos){
	$.ajax({
		 url:	"/portal/constructionNotice/showLineInfo.action?random="+Math.random(),
		type:	"post",
		data:	{
				"startDate" : start,
				"endDate"	: end
				},
	dataType:	"json",
	   cache: 	false,
	   error:	function(){/**alert('系统连接失败，请稍后再试！')*/},
	 success: 	function(obj){
		   			//alert(obj);
		   			var newsLi = "";
		   			var newsLiAll = "";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += obj[i]+"&nbsp;";
							if((i+1)%4==0){
								newsLiAll += "<li><a title='"+newsLi+"'>"+newsLi+"</a></li>";
								newsLi = "";
							}
						}
						$(".asideUl:eq("+pos+")").html(newsLiAll);
						$(".asideH:eq("+pos+") a").attr("href","http://10.1.48.40/stptm/defaut.jsp?returnUrl=http://10.1.48.40/stptm/construction/findTConstructionNoticeOnly.action");
						if(obj.length==0){
							$(".asideUl:eq("+pos+")").html("&nbsp;&nbsp;无相关信息。");
							$(".asideH:eq("+pos+") a").hide();
						}
					}
				}
	});
}

var max = 1.10;	
var min = 0.90;
var max2 = 1.05;
var min2 = 0.95

function payAttention(id,control){
	if(control==0){
		$(id).parent("div").removeClass("attention");
		$(id).parent("div").css("paddingRight","0px");
		return;
	}
	
	var real = $(id).html();
	//real = real.replace("%","");
	real = parseFloat(real);
	//con = parseFloat(control);
	//alert(real);alert(control);
	/*var result = real/con;
	//alert(result)
		
	if(result>=max || result<=min){
		$(id).parent("div").addClass("attention");
		$(id).parent("div").css("paddingRight","55px");
	}else{
		$(id).parent("div").removeClass("attention");
		$(id).parent("div").css("paddingRight","0px");
	}
	*/
	if(real<control){
		$(id).parent("div").addClass("attention");
		$(id).parent("div").css("paddingRight","55px");
	}else{
		$(id).parent("div").removeClass("attention");
		$(id).parent("div").css("paddingRight","0px");
	}
}

function loseAttention(id){
	$(id).parent("div").removeClass("attention");
	$(id).parent("div").css("paddingRight","0px");
}

//画图
function drawProduction(line,date){
	$.ajax({
		type : 'post',
		url : '/portal/operationIndicator/findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			"lineNo" : line,
			"date" : date,
			"daysSpan" : 15
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				var distanceControl = new Array();
				var incomeControl = new Array();
				var passCapControl = new Array();
				if(object.distanceControlValue!=null&&object.ticketControlValue!=null&&object.capacityControlValue!=null){
					//distanceControl = parseFloat(object.metroProductionControlVO.metroDistanceDaily);
					distanceControl = convertArrP(object.distanceControlValue);
					//incomeControl = parseFloat(object.metroProductionControlVO.ticketIncomeDaily);
					incomeControl = convertArrP(object.ticketControlValue);
					//passCapControl = parseFloat(object.metroProductionControlVO.passengerCapacityDaily);
					passCapControl = convertArrP(object.capacityControlValue);
					$("#distanceControl").html(distanceControl[distanceControl.length-1]);	
					$("#incomeControl").html(incomeControl[incomeControl.length-1]);	
					$("#passCapControl").html(passCapControl[passCapControl.length-1]);	
					
					payAttention("#passCapDaily",passCapControl[passCapControl.length-1]);
					payAttention("#incomeDaily",incomeControl[incomeControl.length-1]);
					payAttention("#distanceDaily",distanceControl[distanceControl.length-1]);
				}else{
					loseAttention("#passCapDaily");
					loseAttention("#incomeDaily");
					loseAttention("#distanceDaily");
				}
				
				
				
				newSparkBar("#distanceChart",object.dateList,object.distanceList,distanceControl,"运营里程","万车公里");
				newSparkBar("#passCapChart",object.dateList,object.passengerCapacityList,passCapControl,"客流量","万人");
				newSparkBar("#incomeChart",object.dateList,object.ticketIncomeList,incomeControl,"客运收入","万元");
			}else{
				//alert("生产指标无相关数据，请重新选择日期！");
				
				loseAttention("#passCapDaily");
				loseAttention("#incomeDaily");
				loseAttention("#distanceDaily");
				
				newSparkBar("#distanceChart",0,[0],[0],"运营里程","万车公里");
				newSparkBar("#passCapChart",0,[0],[0],"客流量","万人");
				newSparkBar("#incomeChart",0,[0],[0],"客运收入","万元");
				$("#distanceControl").html("0");	
				$("#incomeControl").html("0");	
				$("#passCapControl").html("0");	
			}
			
		}
	});
}

function drawQuality(line,date){
	$.ajax({
		type : 'post',
		url : '/portal/operationIndicator/findChartDataQuality.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			"lineNo" : line,
			"date" : date,
			"daysSpan" : 15
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				var ontimeControl = new Array();
				var onworkControl = new Array();
				if(object.ontimeControlValueList!=null&&object.onworkControlValueList!=null){
					//ontimeControl = parseFloat(object.metroQualityControlVO.metroOntimeDaily);
					ontimeControl = convertArrQ(object.ontimeControlValueList);
					//onworkControl = parseFloat(object.metroQualityControlVO.metroOnworkDaily);
					onworkControl = convertArrQ(object.onworkControlValueList);
					$("#ontimeControl").html(ontimeControl[ontimeControl.length-1]);	
					$("#onworkControl").html(onworkControl[onworkControl.length-1]);	

					payAttention("#ontimeDaily",ontimeControl[ontimeControl.length-1]);
					payAttention("#onworkDaily",onworkControl[onworkControl.length-1]);
				}else{
					loseAttention("#ontimeDaily");
					loseAttention("#onworkDaily");
				}
				

				newSparkLine("#ontimeChart",object.dateList,object.onTimeList,ontimeControl,"正点率");
				newSparkLine("#onworkChart",object.dateList,object.onWorkList,onworkControl,"兑现率");
				//newChartColumn(object.distanceList,object.dateList,'chart2',cDistanceDaily);
			}else{
				//alert("质量指标无相关数据，请重新选择日期！");
				
				loseAttention("#ontimeDaily");
				loseAttention("#onworkDaily");
				
				newSparkLine("#ontimeChart",0,[0],[0],"正点率");
				newSparkLine("#onworkChart",0,[0],[0],"兑现率");
				$("#ontimeControl").html("0");	
				$("#onworkControl").html("0");	
			}
			
		}
	});
}

function drawScale(line,date){
	$.ajax({
		type : 'post',
		url : '/portal/operationIndicator/findChartDataScale.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			"lineNo" : line,
			"date" : date
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				var threeSection = object.allocateThreeSection;
				var fourSection = object.allocateFourSection;
				var sixSection = object.allocateSixSection;
				var sevenSection = object.allocateSevenSection;
				var eightSection = object.allocateEightSection;
				
				var valueList = new Array();
				if(threeSection > 0){valueList.push(threeSection);}
				if(fourSection > 0){valueList.push(fourSection);}
				if(sixSection > 0){valueList.push(sixSection);}
				if(sevenSection > 0){valueList.push(sevenSection);}
				if(eightSection > 0){valueList.push(eightSection);}
				newSparkPie("#allocateChart",valueList,"车辆编组");

			}else{
				//alert("规模指标无相关数据，请重新选择日期！");
				newSparkPie("#allocateChart",[0],"车辆编组");
				
			}
			
		}
	});
}

function arrMax(arr)
{
   return Math.max.apply(Math,arr);
}

function arrMin(arr)
{
   return Math.min.apply(Math,arr);
}

function newSparkLine(index,dateList,valueList,controlValue,title){
		//controlValue = "99.5";
		var data = '';
		var maxData = arrMax(valueList);
		var minData = arrMin(valueList);
		var cmaxData = arrMax(controlValue);
		var cminData = arrMin(controlValue);
		if(controlValue!=null&&controlValue.length>0){
			if(cmaxData>maxData){
				maxData = cmaxData;
			}
			if(cminData<minData&&cminData!=0){
				minData = cminData;
			}
		}
		for(var i=0; i<valueList.length; i++){
			data += valueList[i];
			data += ",";
 		}
		data = data.substr(0,data.length-1);
		$(index).html(data);
		$(index).sparkline('html', 
		{ type:'line',
			width:150,height:40,
			lineWidth:1,
			spotRadius:2,
			spotColor:false,
			tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+title,
			tooltipSuffix:'%',
			tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 实际值： {{prefix}}{{y}}{{suffix}}',
			fillColor: false ,
			lineColor: '#84C1FF',
			chartRangeMin:minData-0.1,
			chartRangeMax:maxData+0.1 
		});
		
		if(controlValue!=''&&controlValue.length>0&&cmaxData!=0&&cminData!=0){
			var control = controlValue;
			
			$(index).sparkline(control,
					{type:'line',
						spotColor:false,
						spotRadius:2,
						lineWidth:2,
						width:150,height:40,
						tooltipSuffix:'%',
						composite: true, 
						tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 管控值： {{prefix}}{{y}}{{suffix}}',
						fillColor: false, 
						lineColor: '#FFA042',
						highlightLineColor:'red',
						chartRangeMin:minData-0.1,
						chartRangeMax:maxData+0.1
					}); 		
		}
	
}

function newSparkBar(index,dateList,valueList,controlValue,title,measure){
	var data = '';
	var maxData = arrMax(valueList);
	var minData = arrMin(valueList);
	var cmaxData = arrMax(controlValue);
	var cminData = arrMin(controlValue);
	//controlValue = ((maxData+minData)/2).toFixed(4);
	if(controlValue!=null&&controlValue.length>0){
		if(cmaxData>maxData){
			maxData = cmaxData;
		}
		if(cminData<minData&&cminData!=0){
			minData = cminData;
		}
	}
	
	for(var i=0; i<valueList.length; i++){
		data += valueList[i];
		data += ",";
		}
	data = data.substr(0,data.length-1);
	$(index).html(data);
	$(index).sparkline('html',
			{type:'bar',
				barColor:'#0072E3',
				width:150,
				height:40,
				barWidth:7,
				barSpacing:3,
				tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+title,
				tooltipSuffix:measure,
				tooltipFormat: '  <span style="display:inline;color: {{color}}">&#9679;</span> 实际值： {{prefix}}{{value}}{{suffix}}',
				chartRangeMin:0,
				chartRangeMax:maxData
			}); 
	
	if(controlValue!=''&&controlValue.length>0&&cmaxData!=0&&cminData!=0){
		var control = controlValue;
		//for(var i=0; i<valueList.length; i++){
		//	control[i] = controlValue;
		//}
		
		$(index).sparkline(control,
				{type:'line',
					spotRadius:2,
					lineWidth:2,
					width:150,height:40,
					tooltipSuffix:measure,
					composite: true, 
					tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> 管控值： {{prefix}}{{y}}{{suffix}}',
					fillColor: false, 
					lineColor: '#FFA042',
					highlightLineColor:'red',
					chartRangeMin:0,
					chartRangeMax:maxData
				}); 		
	}

}


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
					height: '78px',
					offset:'-90',
					tooltipFormat: '  <span style="display:inline;color: {{color}}">&#9679;</span> 编组数：{{value}}',
					tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+title
				}); 
	}
}

function convertArrP(arr){
	var a = new Array();
	if(arr!=null&&arr.length>0){
		for(var i=0;i<arr.length;i++){
			if(arr[i]==null){
				a[i] = 0;
			}else{
				a[i] = parseFloat(arr[i])/10000;
			}
		}
	}
	return a;
}

function convertArrQ(arr){
	var a = new Array();
	if(arr!=null&&arr.length>0){
		for(var i=0;i<arr.length;i++){
			if(arr[i]==null){
				a[i] = 0;
			}else{
				a[i] = parseFloat(arr[i]);
			}
		}
	}
	return a;
}
