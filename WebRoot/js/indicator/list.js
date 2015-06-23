
/**
 * 判断视图的类型
 * @return 1:日视图，2:月视图，3:年视图
 */
function getViewType(){
	$("input[id^=view]").each(function(){
		if($(this).attr("disabled")){
			btId = $(this).attr("id");
		}
	});
	if(btId.indexOf("Day")>0){
		return 1;	
	}else if(btId.indexOf("Month")>0){
		return 2; 
	}else if(btId.indexOf("Year")>0){
		return 3; 
	}
}


/**
 * 在id=chart1的区域画图 
 */
function drawChart1(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart1ByDay();break;
		case 2: drawChart1ByMonth();break;
		case 3: drawChart1ByYear();break;
	}
}

function drawChart1ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner1").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				
				var columnDaily = parseFloat(object.lastestProduction.metroColumnDaily);
				var columnColor = '';
				var cColumnDaily = '';
				if(object.metroProductionControlVO!=null){
					cColumnDaily = parseFloat(object.metroProductionControlVO.metroColumnDaily);
					columnColor = getControlColor(cColumnDaily,columnDaily,'chart1Info',object.metroProductionControlVO.metroColumnControl,object.metroProductionControlVO.metroColumnDescribe);
				}else{
					$("#chart1Info").attr("style","display:none");
				}
				$("#chart1_daily").html(object.lastestProduction.metroColumnDaily);	
				$("#chart1_lastyear").html(object.lastYearProduction.metroColumnDaily);
				$("#chart1_month").html(object.lastestProduction.metroColumnMonth);
				$("#chart1_year").html(object.lastestProduction.metroColumnYear);
				newChartColumn(object.columnList,object.dateList,'chart1',object.columnControlValue,columnColor);
			}
		}
	});
}

function drawChart1ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner1").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else {
				var length = object.length;
				var columnMonth = parseFloat(object[length-1].metroColumnMonth);
				var columnColor = '';
				var cColumnMonth = '';
				if(object[length-1].metroProductionControlVO!=null){
					cColumnMonth = parseFloat(object[length-1].metroProductionControlVO.metroColumnMonth);
					columnColor = getControlColor(cColumnMonth,columnMonth,'chart1Info',object[length-1].metroProductionControlVO.metroColumnControl,object[length-1].metroProductionControlVO.metroColumnDescribe);
				}else{
					$("#chart1Info").attr("style","display:none");
				}
				var columnList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					columnList[i] = object[i].metroColumnMonth;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.metroColumnMonth);
					}else{
						controlList.push(null);
					}
				}
				$("#chart1_daily").html(object[length-1].metroColumnMonth);
				$("#chart1_lastyear").html(object[length-1].lastyearMetroProduction.metroColumnMonth);
				$("#chart1_year").html(object[length-1].metroColumnYear);
				$("#chart1_month").parent("li").hide();//隐藏月累计
				$("#chart1_year").parent("li").show();
				newChartColumn(columnList,dateList,'chart1',controlList,columnColor);
			}
		}
	});
}

function drawChart1ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner1").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else{
				var length = object.length;
				var columnYear = parseFloat(object[length-1].metroColumnYear);
				var columnColor = '';
				var cColumnYear = '';
				if(object[length-1].metroProductionControlVO!=null){
					cColumnYear = parseFloat(object[length-1].metroProductionControlVO.metroColumnYear);
					columnColor = getControlColor(cColumnYear,columnYear,'chart1Info',object[length-1].metroProductionControlVO.metroColumnControl,object[length-1].metroProductionControlVO.metroColumnDescribe);
				}else{
					$("#chart1Info").attr("style","display:none");
				}
				var columnList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					columnList[i] = object[i].metroColumnMonth;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.metroColumnMonth);
					}else{
						controlList.push(null);
					}
				}
				$("#chart1_daily").html(object[length-1].metroColumnYear);
				$("#chart1_lastyear").html(object[length-1].lastyearMetroProduction.metroColumnYear);
				$("#chart1_year").parent("li").hide();
				$("#chart1_month").parent("li").hide();
				newChartColumn(columnList,dateList,'chart1',controlList,columnColor);
			}
		}
	});
}


/**
 *在id=chart2的区域画图 
 */
function drawChart2(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart2ByDay();break;
		case 2: drawChart2ByMonth();break;
		case 3: drawChart2ByYear();break;
	}
}

function drawChart2ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner2").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				var distanceDaily = parseFloat(object.lastestProduction.metroDistanceDaily);
				var distanceColor = '';
				var cDistanceDaily = '';
				if(object.metroProductionControlVO!=null){
					cDistanceDaily = parseFloat(object.metroProductionControlVO.metroDistanceDaily);
					distanceColor = getControlColor(cDistanceDaily,distanceDaily,'chart2Info',object.metroProductionControlVO.metroDistanceControl,object.metroProductionControlVO.metroDistanceDescribe);
				}else{
					$("#chart2Info").attr("style","display:none");
				}
				$("#chart2_daily").html(object.lastestProduction.metroDistanceDaily);	
				$("#chart2_lastyear").html(object.lastYearProduction.metroDistanceDaily);
				$("#chart2_month").html(object.lastestProduction.metroDistanceMonth);
				$("#chart2_year").html(object.lastestProduction.metroDistanceYear);
				$("#chart2_month").parent("li").show();
				$("#chart2_year").parent("li").show();
				
				if(object.distanceControlValue!=null && object.distanceControlValue.length>0){
					for(var i=0; i<object.distanceControlValue.length; i++){
						if(object.distanceControlValue[i]!=null){
							object.distanceControlValue[i] = parseFloat(object.distanceControlValue[i])/10000;
						}
					}
				}
				newChartColumn(object.distanceList,object.dateList,'chart2',object.distanceControlValue,distanceColor);
			}else{
				alert("无相关数据，请重新选择日期！");
			}
		}
	});
}

function drawChart2ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner2").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else{
				var length = object.length;
				var distanceMonth = parseFloat(object[length-1].metroDistanceMonth);
				var distanceColor = '';
				var cDistanceMonth = '';
				if(object[length-1].metroProductionControlVO!=null){
					cDistanceMonth = parseFloat(object[length-1].metroProductionControlVO.metroDistanceMonth);
					distanceColor = getControlColor(cDistanceMonth,distanceMonth,'chart2Info',object[length-1].metroProductionControlVO.metroDistanceControl,object[length-1].metroProductionControlVO.metroDistanceDescribe);
				}else{
					$("#chart2Info").attr("style","display:none");
				}
				$("#chart2_daily").html(object[length-1].metroDistanceMonth);	
				$("#chart2_lastyear").html(object[length-1].lastyearMetroProduction.metroDistanceMonth);
				$("#chart2_year").html(object[length-1].metroDistanceYear);
				var distanceList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					distanceList[i] = object[i].metroDistanceMonth;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.metroDistanceMonth);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(distanceList,dateList,'chart2',controlList,distanceColor);
			}
		}
	});
}

function drawChart2ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner2").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var length = object.length;
				var distanceYear = parseFloat(object[length-1].metroDistanceYear);
				var distanceColor = '';
				var cDistanceYear = '';
				if(object[length-1].metroProductionControlVO!=null){
					cDistanceYear = parseFloat(object[length-1].metroProductionControlVO.metroDistanceYear);
					distanceColor = getControlColor(cDistanceYear,distanceYear,'chart2Info',object[length-1].metroProductionControlVO.metroDistanceControl,object[length-1].metroProductionControlVO.metroDistanceDescribe);
				}else{
					$("#chart2Info").attr("style","display:none");
				}
				$("#chart2_daily").html(object[length-1].metroDistanceYear);	
				$("#chart2_lastyear").html(object[length-1].lastyearMetroProduction.metroDistanceYear);
				var distanceList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					distanceList[i] = object[i].metroDistanceYear;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.metroDistanceYear);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(distanceList,dateList,'chart2',controlList,distanceColor);
			}
		}
	});
}


/**
 *在id=chart3的区域画图 
 */
function drawChart3(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart3ByDay();break;
		case 2: drawChart3ByMonth();break;
		case 3: drawChart3ByYear();break;
	}
}

function drawChart3ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner3").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				
				var passengerCapacityDaily = parseFloat(object.lastestProduction.passengerCapacityDaily);
				var passengerCapacityColor = '';
				var cPassengerCapacityDaily ='';
				
				if(object.metroProductionControlVO!=null){
					cPassengerCapacityDaily = parseFloat(object.metroProductionControlVO.passengerCapacityDaily);
					passengerCapacityColor = getControlColor(cPassengerCapacityDaily,passengerCapacityDaily,'chart3Info',object.metroProductionControlVO.passengerCapacityControl,object.metroProductionControlVO.passengerCapacityDescribe);
				}else{
					$("#chart3Info").attr("style","display:none");
				}
				
				if(object.capacityControlValue!=null && object.capacityControlValue.length>0){
					for(var i=0; i<object.capacityControlValue.length; i++){
						if(object.capacityControlValue[i]!=null){
							object.capacityControlValue[i] = parseFloat(object.capacityControlValue[i])/10000;
						}
					}
				}
				$("#chart3_daily").html(object.lastestProduction.passengerCapacityDaily);	
				$("#chart3_lastyear").html(object.lastYearProduction.passengerCapacityDaily);
				$("#chart3_month").html(object.lastestProduction.passengerCapacityMonth);
				$("#chart3_year").html(object.lastestProduction.passengerCapacityYear);
				newChartColumn(object.passengerCapacityList,object.dateList,'chart3',object.capacityControlValue,passengerCapacityColor);
			}else{
				alert("无相关数据，请重新选择日期！");
			}
			
		}
	});
}

function drawChart3ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner3").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else{
				var length = object.length;
				var passengerCapacityMonth = parseFloat(object[length-1].passengerCapacityMonth);
				var passengerCapacityColor = '';
				var cPassengerCapacityMonth ='';
				if(object[length-1].metroProductionControlVO!=null){
					cPassengerCapacityMonth = parseFloat(object[length-1].metroProductionControlVO.passengerCapacityMonth);
					passengerCapacityColor = getControlColor(cPassengerCapacityMonth,passengerCapacityMonth,'chart3Info',object[length-1].metroProductionControlVO.passengerCapacityControl,object[length-1].metroProductionControlVO.passengerCapacityDescribe);
				}else{
					$("#chart3Info").attr("style","display:none");
				}
				$("#chart3_daily").html(object[length-1].passengerCapacityMonth);	
				$("#chart3_lastyear").html(object[length-1].lastyearMetroProduction.passengerCapacityMonth);
				$("#chart3_year").html(object[length-1].passengerCapacityYear);
				var passengerCapacityList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					passengerCapacityList[i] = object[i].passengerCapacityMonth;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.passengerCapacityMonth);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(passengerCapacityList,dateList,'chart3',controlList,passengerCapacityColor);
			}
		}
	});
}

function drawChart3ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner3").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var length = object.length;
				var passengerCapacityYear = parseFloat(object[length-1].passengerCapacityYear);
				var passengerCapacityColor = '';
				var cPassengerCapacityYear ='';
				if(object[length-1].metroProductionControlVO!=null){
					cPassengerCapacityYear = parseFloat(object[length-1].metroProductionControlVO.passengerCapacityYear);
					passengerCapacityColor = getControlColor(cPassengerCapacityYear,passengerCapacityYear,'chart3Info',object[length-1].metroProductionControlVO.passengerCapacityControl,object[length-1].metroProductionControlVO.passengerCapacityDescribe);
				}else{
					$("#chart3Info").attr("style","display:none");
				}
				$("#chart3_daily").html(object[length-1].passengerCapacityYear);	
				$("#chart3_lastyear").html(object[length-1].lastyearMetroProduction.passengerCapacityYear);
				var passengerCapacityList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					passengerCapacityList[i] = object[i].passengerCapacityYear;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.passengerCapacityYear);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(passengerCapacityList,dateList,'chart3',controlList,passengerCapacityColor);
			}
		}
	});
}


/**
 *在id=chart4的区域画图 
 */
function drawChart4(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart4ByDay();break;
		case 2: drawChart4ByMonth();break;
		case 3: drawChart4ByYear();break;
	}
}

function drawChart4ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner4").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				
				var passengerTransferDaily = parseFloat(object.lastestProduction.passengerTransferDaily);
				var passengerTransferColor = '';
				var cPassengerTransferDaily ='';
				if(object.metroProductionControlVO!=null){
					cPassengerTransferDaily = parseFloat(object.metroProductionControlVO.passengerTransferDaily);
					passengerTransferColor = getControlColor(cPassengerTransferDaily,passengerTransferDaily,'chart4Info',object.metroProductionControlVO.passengerTransferControl,object.metroProductionControlVO.passengerTransferDescribe);
				}else{
					$("#chart4Info").attr("style","display:none");
				}
				if(object.transferControlValue!=null && object.transferControlValue.length>0){
					for(var i=0; i<object.transferControlValue.length; i++){
						if(object.transferControlValue[i]!=null){
							object.transferControlValue[i] = parseFloat(object.transferControlValue[i])/10000;
						}
					}
				}
				$("#chart4_daily").html(object.lastestProduction.passengerTransferDaily);	
				$("#chart4_lastyear").html(object.lastYearProduction.passengerTransferDaily);
				$("#chart4_month").html(object.lastestProduction.passengerTransferMonth);
				$("#chart4_year").html(object.lastestProduction.passengerTransferYear);
				newChartColumn(object.passengerTransferList,object.dateList,'chart4',object.transferControlValue,passengerTransferColor);
			}else{
				alert("无相关数据，请重新选择日期！");
			}
			
		}
	});
}

function drawChart4ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner4").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else{
				var length = object.length;
				var passengerTransferMonth = parseFloat(object[length-1].passengerTransferMonth);
				var passengerTransferColor = '';
				var cPassengerTransferMonth ='';
				if(object[length-1].metroProductionControlVO!=null){
					cPassengerTransferMonth = parseFloat(object[length-1].metroProductionControlVO.passengerTransferMonth);
					passengerTransferColor = getControlColor(cPassengerTransferMonth,passengerTransferMonth,'chart4Info',object[length-1].metroProductionControlVO.passengerTransferControl,object[length-1].metroProductionControlVO.passengerTransferDescribe);
				}else{
					$("#chart4Info").attr("style","display:none");
				}
				$("#chart4_daily").html(object[length-1].passengerTransferMonth);	
				$("#chart4_lastyear").html(object[length-1].lastyearMetroProduction.passengerTransferMonth);
				$("#chart4_year").html(object[length-1].passengerTransferYear);
				var passengerTransferList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					passengerTransferList[i] = object[i].passengerTransferMonth;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.passengerTransferMonth);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(passengerTransferList,dateList,'chart4',controlList,passengerTransferColor);
			}
		}
	});
}

function drawChart4ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner4").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var length = object.length;
				var passengerTransferYear = parseFloat(object[length-1].passengerTransferYear);
				var passengerTransferColor = '';
				var cPassengerTransferYear ='';
				if(object[length-1].metroProductionControlVO!=null){
					cPassengerTransferYear = parseFloat(object[length-1].metroProductionControlVO.passengerTransferYear);
					passengerTransferColor = getControlColor(cPassengerTransferYear,passengerTransferYear,'chart4Info',object[length-1].metroProductionControlVO.passengerTransferControl,object[length-1].metroProductionControlVO.passengerTransferDescribe);
				}else{
					$("#chart4Info").attr("style","display:none");
				}
				$("#chart4_daily").html(object[length-1].passengerTransferYear);	
				$("#chart4_lastyear").html(object[length-1].lastyearMetroProduction.passengerTransferYear);
				var passengerTransferList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					passengerTransferList[i] = object[i].passengerTransferYear;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.passengerTransferYear);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(passengerTransferList,dateList,'chart4',controlList,passengerTransferColor);
			}
		}
	});
}


/**
 *在id=chart5的区域画图 
 */
function drawChart5(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart5ByDay();break;
		case 2: drawChart5ByMonth();break;
		case 3: drawChart5ByYear();break;
	}
}

function drawChart5ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner5").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				var ticketIncomeDaily = parseFloat(object.lastestProduction.ticketIncomeDaily);
				var ticketIncomeColor = '';
				var cTicketIncomeDaily = ''; 
				if(object.metroProductionControlVO!=null){
					cTicketIncomeDaily = parseFloat(object.metroProductionControlVO.ticketIncomeDaily);
					ticketIncomeColor = getControlColor(cTicketIncomeDaily,ticketIncomeDaily,'chart5Info',object.metroProductionControlVO.ticketIncomeControl,object.metroProductionControlVO.ticketIncomeDescribe);
				}else{
					$("#chart5Info").attr("style","display:none");
				}
				if(object.ticketControlValue!=null && object.ticketControlValue.length>0){
					for(var i=0; i<object.ticketControlValue.length; i++){
						if(object.ticketControlValue[i]!=null){
							object.ticketControlValue[i] = parseFloat(object.ticketControlValue[i])/10000;
						}
					}
				}
				$("#chart5_daily").html(object.lastestProduction.ticketIncomeDaily);	
				$("#chart5_lastyear").html(object.lastYearProduction.ticketIncomeDaily);
				$("#chart5_month").html(object.lastestProduction.ticketIncomeMonth);
				$("#chart5_year").html(object.lastestProduction.ticketIncomeYear);
				newChartColumn(object.ticketIncomeList,object.dateList,'chart5',object.ticketControlValue,ticketIncomeColor);
			}else{
				alert("无相关数据，请重新选择日期！");
			}
			
		}
	});
}

function drawChart5ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner5").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else{
				var length = object.length;
				var ticketIncomeMonth = parseFloat(object[length-1].ticketIncomeMonth);
				var ticketIncomeColor = '';
				var cTicketIncomeMonth = ''; 
				if(object[length-1].metroProductionControlVO!=null){
					cTicketIncomeMonth = parseFloat(object[length-1].metroProductionControlVO.ticketIncomeMonth);
					ticketIncomeColor = getControlColor(cTicketIncomeMonth,ticketIncomeMonth,'chart5Info',object[length-1].metroProductionControlVO.ticketIncomeControl,object[length-1].metroProductionControlVO.ticketIncomeDescribe);
				}else{
					$("#chart5Info").attr("style","display:none");
				}
				$("#chart5_daily").html(object[length-1].ticketIncomeMonth);	
				$("#chart5_lastyear").html(object[length-1].lastyearMetroProduction.ticketIncomeMonth);
				$("#chart5_year").html(object[length-1].ticketIncomeYear);
				var ticketIncomeList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					ticketIncomeList[i] = object[i].ticketIncomeMonth;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.ticketIncomeMonth);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(ticketIncomeList,dateList,'chart5',controlList,ticketIncomeColor);
			}
		}
	});
}

function drawChart5ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner5").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var length = object.length;
				var ticketIncomeYear = parseFloat(object[length-1].ticketIncomeYear);
				var ticketIncomeColor = '';
				var cPassengerTransferYear ='';
				var cTicketIncomeYear = ''; 
				if(object[length-1].metroProductionControlVO!=null){
					cTicketIncomeYear = parseFloat(object[length-1].metroProductionControlVO.ticketIncomeYear);
					ticketIncomeColor = getControlColor(cTicketIncomeYear,ticketIncomeYear,'chart5Info',object[length-1].metroProductionControlVO.ticketIncomeControl,object[length-1].metroProductionControlVO.ticketIncomeDescribe);
				}else{
					$("#chart5Info").attr("style","display:none");
				}
				$("#chart5_daily").html(object[length-1].ticketIncomeYear);	
				$("#chart5_lastyear").html(object[length-1].lastyearMetroProduction.ticketIncomeYear);
				var ticketIncomeList = new Array();
				var dateList = new Array();
				var controlList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					ticketIncomeList[i] = object[i].ticketIncomeYear;
					if(object[i].metroProductionControlVO!=null){
						controlList.push(object[i].metroProductionControlVO.ticketIncomeYear);
					}else{
						controlList.push(null);
					}
				}
				newChartColumn(ticketIncomeList,dateList,'chart5',controlList,ticketIncomeColor);
			}
		}
	});
}


/**
 * 在id=chart6的区域画图
 */
function drawChart6(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner6").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var online = object.onlineSectionList;
				$("#chart6_all").html(online[0]+online[1]+online[2]+online[3]+online[4]);	
				$("#chart6_3").html(online[0]);
				$("#chart6_4").html(online[1]);
				$("#chart6_6").html(online[2]);
				$("#chart6_7").html(online[3]);
				$("#chart6_8").html(online[4]);
				newChartPie(object.onlineSectionList,"chart6");
			}
		}
	});
}


/**
* 在id=chart7的区域画图
*/
function drawChart7(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner7").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else {
				var backup = object.backupSectionList;
				$("#chart7_all").html(backup[0]+backup[1]+backup[2]+backup[3]+backup[4]);	
				$("#chart7_3").html(backup[0]);
				$("#chart7_4").html(backup[1]);
				$("#chart7_6").html(backup[2]);
				$("#chart7_7").html(backup[3]);
				$("#chart7_8").html(backup[4]);
				newChartPie(object.backupSectionList,"chart7");
			}
		}
	});
}


/**
 * 在id=chart8的区域画图
 * @return
 */
function drawChart8(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart8ByDay();break;
		case 2: drawChart8ByMonth();break;
		case 3: drawChart8ByYear();break;
	}
}

function drawChart8ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQuality.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner8").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var cMetroOntimeDaily = '';
				var ontimeColor = '';
				if(object.metroQualityControlVO!=null){
					cMetroOntimeDaily = parseFloat(object.metroQualityControlVO.metroOntimeDaily);
					ontimeColor = getControlColor(cMetroOntimeDaily,object.lastestQuality.metroOntimeDaily,'chart8Info',object.metroQualityControlVO.ontimeControl,object.metroQualityControlVO.ontimeDescribe);
				}
				$("#chart8_daily").html(object.lastestQuality.metroOntimeDaily);
				$("#chart8_lastyear").html(object.lastYearQuality.metroOntimeDaily);
				$("#chart8_month").html(object.lastestQuality.metroOntimeMonth);
				$("#chart8_year").html(object.lastestQuality.metroOntimeYear);
				newChartLine(object.onTimeList,object.dateList,'chart8',object.ontimeControlValueList,ontimeColor);
			}
		}
	});
}

function drawChart8ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQualityByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner8").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			var length ;
			var last ; 
			if(object!=null){
				length = object.length;
				last = object[length-1];
				var onTimeList = new Array();
				var dateList = new Array();
				var cMetroOntimeMonth = new Array();
				var ontimeColor = '';
				var ontimeControlValueList = new Array();	//准点管控制list
				if(last.metroQualityControlVO!=null){
					cMetroOntimeMonth.push(parseFloat(object[length-1].metroQualityControlVO.metroOntimeMonth));
//					ontimeColor = getColorOverIsGood(cMetroOntimeDaily,last.metroOntimeDaily,'chart8Info');
					ontimeColor = getControlColor(cMetroOntimeMonth,object[length-1].metroOntimeMonth,'chart8Info',object[length-1].metroQualityControlVO.ontimeControl,object[length-1].metroQualityControlVO.ontimeDescribe);
				}
				//显示图表8数据
				$("#chart8_daily").html(last.metroOntimeMonth);
				$("#chart8_lastyear").html(last.lastyearQualityVo.metroOntimeMonth);			
				$("#chart8_year").html(last.metroOntimeYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					onTimeList[i] = object[i].metroOntimeMonth;
					if(object[i].metroQualityControlVO!=null){
						ontimeControlValueList.push(object[i].metroQualityControlVO.metroOntimeMonth);
					}else{
						ontimeControlValueList.push(null);
					}
				}
				
				//newChartLine(onTimeList,dateList,'chart8',cMetroOntimeDaily,ontimeColor);
				newChartLine(onTimeList,dateList,'chart8',ontimeControlValueList,ontimeColor);
			}else{
				alert("无相关数据,请重新选择日期查询");
			}
		}
	});
}

function drawChart8ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQualityByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner8").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			var length = object.length;
			var last = object[length-1];
			var onTimeList = new Array();
			var dateList = new Array();
			var cMetroOntimeYear = new Array();
			var ontimeColor = '';
			if(last.metroQualityControlVO!=null){
				cMetroOntimeYear.push(parseFloat(last.metroQualityControlVO.metroOntimeYear));
				ontimeColor = getColorOverIsGood(cMetroOntimeYear,last.metroOntimeYear,'chart8Info');
			}
			if(object!=null){
				var onTimeList = new Array();
				var dateList = new Array();
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkYear);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					onTimeList[i] = object[i].metroOntimeYear;
				}
				newChartLine(onTimeList,dateList,'chart8',cMetroOntimeYear,ontimeColor);
			}else{
				alert("无相关数据,请重新选择日期查询");
			}
		}
	});
}


/**
 * 在id=chart9的区域画图
 */
function drawChart9(){
	var viewType = getViewType();
	switch(viewType){
		case 1: drawChart9ByDay();break;
		case 2: drawChart9ByMonth();break;
		case 3: drawChart9ByYear();break;
	}
}

function drawChart9ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQuality.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner9").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var cMetroOntimeDaily = '';
				var onworkColor = ''
				if(object.metroQualityControlVO!=null){
					cMetroOnworkDaily = parseFloat(object.metroQualityControlVO.metroOnworkDaily); 
					onworkColor = getControlColor(cMetroOnworkDaily,object.lastestQuality.metroOnworkDaily,'chart9Info',object.metroQualityControlVO.onworkControl,object.metroQualityControlVO.onworkDescribe);
				}
				$("#chart9_daily").html(object.lastestQuality.metroOntimeDaily);
				$("#chart9_lastyear").html(object.lastYearQuality.metroOntimeDaily);
				$("#chart9_month").html(object.lastestQuality.metroOntimeMonth);
				$("#chart9_year").html(object.lastestQuality.metroOntimeYear);
				newChartLine(object.onWorkList,object.dateList,'chart9',object.onworkControlValueList,onworkColor);
			}
		}
	});
}

function drawChart9ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQualityByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner9").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			var length ;
			var last ; 
			if(object!=null){
				length = object.length;
				last = object[length-1];
				var onWorkList = new Array();
				var dateList = new Array();
				var cMetroOnworkMonth = new Array();
				var onworkColor = ''
				var onworkControlValueList = new Array();	//兑现管控值list
				if(last.metroQualityControlVO!=null){
					cMetroOnworkMonth = parseFloat(object[length-1].metroQualityControlVO.metroOnworkMonth); 
					onworkColor = getControlColor(cMetroOnworkMonth,object[length-1].metroOnworkMonth,'chart9Info',object[length-1].metroQualityControlVO.onworkControl,object[length-1].metroQualityControlVO.onworkDescribe);
				}
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkMonth);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkMonth);
				$("#chart9_year").html(last.lastyearQualityVo.metroOnworkYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					onWorkList[i] = object[i].metroOnworkMonth;
					if(object[i].metroQualityControlVO!=null){
						onworkControlValueList.push(object[i].metroQualityControlVO.metroOnworkMonth);
					}else{
						onworkControlValueList.push(null);
					}
				}
				newChartLine(onWorkList,dateList,'chart9',onworkControlValueList,onworkColor);
			}else{
				alert("无相关数据,请重新选择日期查询");
			}
		}
	});
}

function drawChart9ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQualityByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepickerInner9").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			var length ;
			var last ; 
			if(object!=null){
				length = object.length;
				last = object[length-1];
				var onWorkList = new Array();
				var dateList = new Array();
				var cMetroOnworkYear = new Array();
				var onworkColor = ''
				if(last.metroQualityControlVO!=null){
					cMetroOnworkYear.push(parseFloat(last.metroQualityControlVO.metroOnworkYear)); 
					onworkColor = getColorOverIsGood(cMetroOnworkYear,last.metroOnworkYear,'chart9Info');
				}
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkYear);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					onWorkList[i] = object[i].metroOnworkYear;
				}
				newChartLine(onWorkList,dateList,'chart9',cMetroOnworkYear,onworkColor);
			}else{
				alert("无相关数据,请重新选择日期查询");
			}
		}
	});
}


/**
 * 在id=chart10的区域画图
 * 注意：暂时不用画图，就显示数据
 */
function drawChart10(){
	$.ajax({
		type : 'post',
		url : 'findChartDataScale.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner10").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				$("#chart10_all").html(object.lineDistance);
			}
		}
	});
}


/**
* 在id=chart11的区域画图
* 注意：暂时不用画图，就显示数据
*/
function drawChart11(){
	$.ajax({
		type : 'post',
		url : 'findChartDataScale.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner11").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				$("#chart11_all").html(object.stationCount);
			}
		}
	});
}


/**
* 在id=chart12的区域画图
* 注意：暂时不用画图，就显示数据
*/
function drawChart12(){
	$.ajax({
		type : 'post',
		url : 'findChartDataScale.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepickerInner12").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else {
				$("#chart12_all").html(object.allocateThreeSection+object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_3").html(object.allocateThreeSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateThreeSection,object.allocateFourSection,object.allocateSixSection,object.allocateSevenSection,object.allocateEightSection];
				newChartPie(valueList,"chart12");
			}
		}
	});
}

var max = 1.10;	
var min = 0.90;
var max2 = 1.05;
var min2 = 0.95
 /**
  * 根据数据大小获取颜色,数据比管控值大好
  * @param control 管控值
  * @param real	真实值
  * @return 颜色的字符串
  */
function getColorOverIsGood(control,real,id){
	var result = real/control;
	if(control==0)
		return;
	if(result>=max || result<=min){
		$("#"+id+"").css("color","#FF3000").css("font-size",'18px').html("管控说明：危险");
		return "#FF3000";
	}else if((result>min && result<=min2) || (result>max2 && result<=max)){
		$("#"+id+"").css("color","#FFCB16").css("font-size",'18px').html("管控说明：警告");
		return "#FFCB16";
	}
	$("#"+id+"").css("color","#80B600").css("font-size",'18px').html("管控说明：正常");
	return '#80B600';
}

/**
 * 
 * @param controlValue	管控值
 * @param realValue	真实值
 * @param id	要显示位置的d
 * @param type	管控类型（1--高好，2--低好，3--接近好）
 * @param describe	管控描述（用“|”号分隔）
 * @return
 */
function getControlColor(controlValue,realValue,id,type,describe){
	if(isNaN(controlValue) || controlValue==null || controlValue==0){
		$("#"+id+"").attr("style","display:none");
		return ;
	}
	var result = realValue/controlValue;
	
	var describeArray = describe.split("|");		//描述
	if(describeArray==null || describeArray.length<2){	//如果描述不存在
		describeArray = new Array();
		describeArray.push("暂无管控描述");
		describeArray.push("暂无管控描述");
		describeArray.push("暂无管控描述");
	}
	
	$("#"+id+"").attr("style","display:bolck");
	
	if(parseInt(type)==1){			//类型，高好
//alert("高好,"+realValue+"/"+controlValue+"="+result);		
		if(parseFloat(result)>=1.0){		//实际值>管控值
			$("#"+id+"").css("color","#80B600").css("font-size",'18px').html("管控类型：高好<br/>管控说明："+describeArray[2]);
			return "#80B600";	//绿色
		}else if(result>=min2){			
			$("#"+id+"").css("color","#FFCB16").css("font-size",'18px').html("管控类型：高好<br/>管控说明："+describeArray[1]);
			return "#FFCB16";	//橙色
		}else{
			$("#"+id+"").css("color","#FF3000").css("font-size",'18px').html("管控类型：高好<br/>管控说明："+describeArray[0]);
			return "#FF3000"
		}
	}else if(parseInt(type)==2){	//类型，低好
//alert("低好,"+realValue+"/"+controlValue+"="+result);			
		if(result<=1.0){		//实际值<管控值			
			$("#"+id+"").css("color","#80B600").css("font-size",'18px').html("管控类型：低好<br/>管控说明："+describeArray[2]);
			return '#80B600';	//绿色
		}else if(result<=max2){
			$("#"+id+"").css("color","#FFCB16").css("font-size",'18px').html("管控类型：低好<br/>管控说明："+describeArray[1]);
			return "#FFCB16";	//橙色
		}else{
			$("#"+id+"").css("color","#FF3000").css("font-size",'18px').html("管控类型：低好<br/>管控说明："+describeArray[0]);
			return "#FF3000";	//红色
		}
	}else if(parseInt(type)==3){	//类型，接近好
//alert("接近好,"+realValue+"/"+controlValue+"="+result);		
		if(result>=max || result<=min){
			$("#"+id+"").css("color","#FF3000").css("font-size",'18px').html("管控类型：接近好<br/>管控说明："+describeArray[0]);
			return "#FF3000";
		}else if((result>min && result<=min2) || (result>max2 && result<=max)){
			$("#"+id+"").css("color","#FFCB16").css("font-size",'18px').html("管控类型：接近好<br/>管控说明："+describeArray[1]);
			return "#FFCB16";
		}
		$("#"+id+"").css("color","#80B600").css("font-size",'18px').html("管控类型：接近好<br/>管控说明："+describeArray[2]);
		return '#80B600';
	}else{		//后台管控类型没有选择
		if(describeArray==null){
			$("#"+id+"").css("color","#C9C9C9").css("font-size",'18px').html("管控类型：暂无<br/>管控说明：暂无");
		}else{
			$("#"+id+"").css("color","#C9C9C9").css("font-size",'18px').html("管控类型：暂无");
		}
		
	}
	
}







/**
 * 显示tab1,日视图
 */
function showTab1ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProduction.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			
			if(object==null){
				alert("无相关数据，请重新选择日期!");
			}else{
				var columnDaily ;
				var distanceDaily ;
				var passengerCapacityDaily ;
				var passengerTransferDaily ;
				var ticketIncomeDaily ;
				if(object.lastestProduction!=null){
					columnDaily = parseFloat(object.lastestProduction.metroColumnDaily);
					distanceDaily = parseFloat(object.lastestProduction.metroDistanceDaily);
					passengerCapacityDaily = parseFloat(object.lastestProduction.passengerCapacityDaily);
					passengerTransferDaily = parseFloat(object.lastestProduction.passengerTransferDaily);
					ticketIncomeDaily = parseFloat(object.lastestProduction.ticketIncomeDaily);
				}else{
					alert("无相关数据，请重新选择线路或日期！");
					columnDaily = 0;
					distanceDaily = 0;
					passengerCapacityDaily = 0;
					passengerTransferDaily = 0;
					ticketIncomeDaily = 0;
				}
				
				var columnColor = '';		//管控颜色
				var distanceColor = '';
				var passengerCapacityColor = '';
				var passengerTransferColor = '';
				var ticketIncomeColor = '';
				
				var cColumnDaily = new Array();		//管控值
				var cDistanceDaily = new Array();
				var cPassengerCapacityDaily = new Array();
				var cPassengerTransferDaily = new Array();
				var cTicketIncomeDaily = new Array(); 
				
				
				//设置最后一条数据的管控颜色
				if(object.metroProductionControlVO!=null){
					cColumnDaily = parseFloat(object.metroProductionControlVO.metroColumnDaily);
					columnColor = getControlColor(cColumnDaily,columnDaily,'chart1Info',object.metroProductionControlVO.metroColumnControl,object.metroProductionControlVO.metroColumnDescribe);
					
					cDistanceDaily = parseFloat(object.metroProductionControlVO.metroDistanceDaily);
					distanceColor = getControlColor(cDistanceDaily,distanceDaily,'chart2Info',object.metroProductionControlVO.metroDistanceControl,object.metroProductionControlVO.metroDistanceDescribe);
					
					cPassengerCapacityDaily = parseFloat(object.metroProductionControlVO.passengerCapacityDaily);
					passengerCapacityColor = getControlColor(cPassengerCapacityDaily,passengerCapacityDaily,'chart3Info',object.metroProductionControlVO.passengerCapacityControl,object.metroProductionControlVO.passengerCapacityDescribe);
					
					cPassengerTransferDaily = parseFloat(object.metroProductionControlVO.passengerTransferDaily);
					passengerTransferColor = getControlColor(cPassengerTransferDaily,passengerTransferDaily,'chart4Info',object.metroProductionControlVO.passengerTransferControl,object.metroProductionControlVO.passengerTransferDescribe);
					
					cTicketIncomeDaily = parseFloat(object.metroProductionControlVO.ticketIncomeDaily);
					ticketIncomeColor = getControlColor(cTicketIncomeDaily,ticketIncomeDaily,'chart5Info',object.metroProductionControlVO.ticketIncomeControl,object.metroProductionControlVO.ticketIncomeDescribe);
				}else{
					$("#chart1Info").attr("style","display:none");
					$("#chart2Info").attr("style","display:none");
					$("#chart3Info").attr("style","display:none");
					$("#chart4Info").attr("style","display:none");
					$("#chart5Info").attr("style","display:none");
				}
				
			
			//显示图表1数据
			$("#chart1_daily").html(columnDaily);	
			$("#chart1_lastyear").html(object.lastYearProduction.metroColumnDaily);
			
			if(object.lastestProduction!=null){
				$("#chart1_month").html(object.lastestProduction.metroColumnMonth);
				$("#chart1_year").html(object.lastestProduction.metroColumnYear);
			}else{
				$("#chart1_month").html("0");
				$("#chart1_year").html("0");
			}
			$("#chart1_month").parent("li").show();
			$("#chart1_year").parent("li").show();
			
			//显示图表2数据
			if(object.lastestProduction!=null){
				$("#chart2_daily").html(object.lastestProduction.metroDistanceDaily);
				$("#chart2_month").html(object.lastestProduction.metroDistanceMonth);
				$("#chart2_year").html(object.lastestProduction.metroDistanceYear);
			}else{
				$("#chart2_daily").html("0");
				$("#chart2_month").html("0");
				$("#chart2_year").html("0");
			}
			$("#chart2_lastyear").html(object.lastYearProduction.metroDistanceDaily);
			$("#chart2_month").parent("li").show();
			$("#chart2_year").parent("li").show();
			//显示图表3数据
			if(object.lastestProduction!=null){
				$("#chart3_daily").html(object.lastestProduction.passengerCapacityDaily);
				$("#chart3_month").html(object.lastestProduction.passengerCapacityMonth);
				$("#chart3_year").html(object.lastestProduction.passengerCapacityYear);
			}else{
				$("#chart3_daily").html("0");
				$("#chart3_month").html("0");
				$("#chart3_year").html("0");
			}
			$("#chart3_lastyear").html(object.lastYearProduction.passengerCapacityDaily);
			$("#chart3_month").parent("li").show();
			$("#chart3_year").parent("li").show();
			//显示图表4数据
			if(object.lastestProduction!=null){
				$("#chart4_daily").html(object.lastestProduction.passengerTransferDaily);
				$("#chart4_month").html(object.lastestProduction.passengerTransferMonth);
				$("#chart4_year").html(object.lastestProduction.passengerTransferYear);
			}else{
				$("#chart4_daily").html("0");
				$("#chart4_month").html("0");
				$("#chart4_year").html("0");
			}
			$("#chart4_lastyear").html(object.lastYearProduction.passengerTransferDaily);
			$("#chart4_month").parent("li").show();
			$("#chart4_year").parent("li").show();
			//显示图表5数据
			if(object.lastestProduction!=null){
				$("#chart5_daily").html(object.lastestProduction.ticketIncomeDaily);
				$("#chart5_month").html(object.lastestProduction.ticketIncomeMonth);
				$("#chart5_year").html(object.lastestProduction.ticketIncomeYear);
			}else{
				$("#chart5_daily").html("0");
				$("#chart5_month").html("0");
				$("#chart5_year").html("0");
			}
			$("#chart5_lastyear").html(object.lastYearProduction.ticketIncomeDaily);
			$("#chart5_month").parent("li").show();
			$("#chart5_year").parent("li").show();
			//显示图表6数据
			var online = object.onlineSectionList;
			if(online!=null && online!=""){
				$("#chart6_all").html(online[0]+online[1]+online[2]+online[3]+online[4]);	
				$("#chart6_3").html(online[0]);
				$("#chart6_4").html(online[1]);
				$("#chart6_6").html(online[2]);
				$("#chart6_7").html(online[3]);
				$("#chart6_8").html(online[4]);
			}else{
				$("#chart6_all").html("0");	
				$("#chart6_3").html("0");
				$("#chart6_4").html("0");
				$("#chart6_6").html("0");
				$("#chart6_7").html("0");
				$("#chart6_8").html("0");
			}
			
			//显示图表7数据
			var backup = object.backupSectionList;
			if(backup!=null && backup!=""){
				$("#chart7_all").html(backup[0]+backup[1]+backup[2]+backup[3]+backup[4]);	
				$("#chart7_3").html(backup[0]);
				$("#chart7_4").html(backup[1]);
				$("#chart7_6").html(backup[2]);
				$("#chart7_7").html(backup[3]);
				$("#chart7_8").html(backup[4]);
			}else{
				$("#chart7_all").html("0");	
				$("#chart7_3").html("0");
				$("#chart7_4").html("0");
				$("#chart7_6").html("0");
				$("#chart7_7").html("0");
				$("#chart7_8").html("0");
			}
			
			
			//画图
			if(object.columnControlValue!=null && object.distanceControlValue.length>0 && 
			object.capacityControlValue.length>0 && object.transferControlValue.length>0 && object.ticketControlValue.length >0){
				for(var i=0; i<object.columnControlValue.length; i++){
					if(object.distanceControlValue[i]!=null){
						object.distanceControlValue[i] = parseFloat(object.distanceControlValue[i])/10000;
					}
					if(object.capacityControlValue[i]!=null){
						object.capacityControlValue[i] = parseFloat(object.capacityControlValue[i])/10000;
					}
					if(object.transferControlValue[i]!=null){
						object.transferControlValue[i] = parseFloat(object.transferControlValue[i])/10000;
					}
					if(object.ticketControlValue[i]!=null){
						object.ticketControlValue[i] = parseFloat(object.ticketControlValue[i])/10000;
					}
				}
			}
			newChartColumn(object.columnList,object.dateList,'chart1',object.columnControlValue,columnColor);
			newChartColumn(object.distanceList,object.dateList,'chart2',object.distanceControlValue,distanceColor);
			newChartColumn(object.passengerCapacityList,object.dateList,'chart3',object.capacityControlValue,passengerCapacityColor);
			newChartColumn(object.passengerTransferList,object.dateList,'chart4',object.transferControlValue,passengerTransferColor);
			newChartColumn(object.ticketIncomeList,object.dateList,'chart5',object.ticketControlValue,ticketIncomeColor);
			
			newChartPie(object.onlineSectionList,"chart6",'');
			newChartPie(object.backupSectionList,"chart7",'');
			}
		}
	});
}

/**
 * 显示tab1月视图
 */
function showTab1ByMonth(btObject){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			
			if(object==null || object==""){
				alert("无相关数据，请重新选择线路或日期！")
				//显示图表1数据
				$("#chart1_daily").html("0");
				$("#chart1_lastyear").html("0");
				$("#chart1_year").html("0");
				$("#chart1_month").parent("li").hide();//隐藏月累计
				$("#chart1_year").parent("li").show();
				//显示图表2数据
				$("#chart2_daily").html("0");	
				$("#chart2_lastyear").html("0");
				$("#chart2_year").html("0");
				$("#chart2_month").parent("li").hide();//隐藏月累计
				$("#chart2_year").parent("li").show();
				//显示图表3数据
				$("#chart3_daily").html("0");	
				$("#chart3_lastyear").html("0");
				$("#chart3_year").html("0");
				$("#chart3_month").parent("li").hide();//隐藏月累计
				$("#chart3_year").parent("li").show();
				//显示图表4数据
				$("#chart4_daily").html("0");	
				$("#chart4_lastyear").html("0");
				$("#chart4_year").html("0");
				$("#chart4_month").parent("li").hide();//隐藏月累计
				$("#chart4_year").parent("li").show();
				//显示图表5数据
				$("#chart5_daily").html("0");	
				$("#chart5_lastyear").html("0");
				$("#chart5_year").html("0");
				$("#chart5_month").parent("li").hide();//隐藏月累计
				$("#chart5_year").parent("li").show();
				//显示图表6数据
				$("#chart6_all").html("0");	
				$("#chart6_3").html("0");
				$("#chart6_4").html("0");
				$("#chart6_6").html("0");
				$("#chart6_7").html("0");
				$("#chart6_8").html("0");
				//显示图表7数据
				$("#chart7_all").html("0");	
				$("#chart7_3").html("0");
				$("#chart7_4").html("0");
				$("#chart7_6").html("0");
				$("#chart7_7").html("0");
				$("#chart7_8").html("0");
				
				newChartColumn(null,dateList,'chart1',columnControlList,null);
				newChartColumn(null,dateList,'chart2',distanceControlList,null);
				newChartColumn(null,dateList,'chart3',passengerCapacityControlList,null);
				newChartColumn(null,dateList,'chart4',passengerTransferControlList,null);
				newChartColumn(null,dateList,'chart5',ticketIncomeControlList,null)
				
				newChartPie(null,"chart6");
				newChartPie(null,"chart7");
			}else{
				var length = object.length;
				var columnMonth = parseFloat(object[length-1].metroColumnMonth);
				var distanceMonth = parseFloat(object[length-1].metroDistanceMonth);
				var passengerCapacityMonth = parseFloat(object[length-1].passengerCapacityMonth);
				var passengerTransferMonth = parseFloat(object[length-1].passengerTransferMonth);
				var ticketIncomeMonth = parseFloat(object[length-1].ticketIncomeMonth);
				var columnColor = '';
				var distanceColor = '';
				var passengerCapacityColor = '';
				var passengerTransferColor = '';
				var ticketIncomeColor = '';
				
				var cColumnMonth = '';
				var cDistanceMonth = '';
				var cPassengerCapacityMonth ='';
				var cPassengerTransferMonth ='';
				var cTicketIncomeMonth = ''; 
			
				if(object[length-1].metroProductionControlVO!=null){
					
					cColumnMonth = parseFloat(object[length-1].metroProductionControlVO.metroColumnMonth);
					columnColor = getControlColor(cColumnMonth,columnMonth,'chart1Info',object[length-1].metroProductionControlVO.metroColumnControl,object[length-1].metroProductionControlVO.metroColumnDescribe);
					
					cDistanceMonth = parseFloat(object[length-1].metroProductionControlVO.metroDistanceMonth);
					distanceColor = getControlColor(cDistanceMonth,distanceMonth,'chart2Info',object[length-1].metroProductionControlVO.metroDistanceControl,object[length-1].metroProductionControlVO.metroDistanceDescribe);
					
					cPassengerCapacityMonth = parseFloat(object[length-1].metroProductionControlVO.passengerCapacityMonth);
					passengerCapacityColor = getControlColor(cPassengerCapacityMonth,passengerCapacityMonth,'chart3Info',object[length-1].metroProductionControlVO.passengerCapacityControl,object[length-1].metroProductionControlVO.passengerCapacityDescribe);
					
					cPassengerTransferMonth = parseFloat(object[length-1].metroProductionControlVO.passengerTransferMonth);
					passengerTransferColor = getControlColor(cPassengerTransferMonth,passengerTransferMonth,'chart4Info',object[length-1].metroProductionControlVO.passengerTransferControl,object[length-1].metroProductionControlVO.passengerTransferDescribe);
					
					cTicketIncomeMonth = parseFloat(object[length-1].metroProductionControlVO.ticketIncomeMonth);
					ticketIncomeColor = getControlColor(cTicketIncomeMonth,ticketIncomeMonth,'chart5Info',object[length-1].metroProductionControlVO.ticketIncomeControl,object[length-1].metroProductionControlVO.ticketIncomeDescribe);
				}else{
					$("#chart1Info").attr("style","display:none");
					$("#chart2Info").attr("style","display:none");
					$("#chart3Info").attr("style","display:none");
					$("#chart4Info").attr("style","display:none");
					$("#chart5Info").attr("style","display:none");
				}
				
				
				//显示图表1数据
				$("#chart1_daily").html(columnMonth);
				$("#chart1_lastyear").html(object[length-1].lastyearMetroProduction.metroColumnMonth);
				$("#chart1_year").html(object[length-1].metroColumnYear);
				$("#chart1_month").parent("li").hide();//隐藏月累计
				$("#chart1_year").parent("li").show();
				//显示图表2数据
				$("#chart2_daily").html(distanceMonth);	
				$("#chart2_lastyear").html(object[length-1].lastyearMetroProduction.metroDistanceMonth);
				$("#chart2_year").html(object[length-1].metroDistanceYear);
				$("#chart2_month").parent("li").hide();//隐藏月累计
				$("#chart2_year").parent("li").show();
				//显示图表3数据
				$("#chart3_daily").html(passengerCapacityMonth);	
				$("#chart3_lastyear").html(object[length-1].lastyearMetroProduction.passengerCapacityMonth);
				$("#chart3_year").html(object[length-1].passengerCapacityYear);
				$("#chart3_month").parent("li").hide();//隐藏月累计
				$("#chart3_year").parent("li").show();
				//显示图表4数据
				$("#chart4_daily").html(passengerTransferMonth);	
				$("#chart4_lastyear").html(object[length-1].lastyearMetroProduction.passengerTransferMonth);
				$("#chart4_year").html(object[length-1].passengerTransferYear);
				$("#chart4_month").parent("li").hide();//隐藏月累计
				$("#chart4_year").parent("li").show();
				//显示图表5数据
				$("#chart5_daily").html(ticketIncomeMonth);	
				$("#chart5_lastyear").html(object[length-1].lastyearMetroProduction.ticketIncomeMonth);
				$("#chart5_year").html(object[length-1].ticketIncomeYear);
				$("#chart5_month").parent("li").hide();//隐藏月累计
				$("#chart5_year").parent("li").show();
				//显示图表6数据
				var onlineSectionList = new Array();
				onlineSectionList[0]=object[length-1].onlineThreeSection;
				onlineSectionList[1]=object[length-1].onlineFourSection;
				onlineSectionList[2]=object[length-1].onlineSixSection;
				onlineSectionList[3]=object[length-1].onlineSevenSection;
				onlineSectionList[4]=object[length-1].onlineEightSection;
				$("#chart6_all").html(object[length-1].onlineThreeSection+object[length-1].onlineFourSection+object[length-1].onlineSixSection+object[length-1].onlineSevenSection+object[length-1].onlineEightSection);	
				$("#chart6_3").html(object[length-1].onlineThreeSection);
				$("#chart6_4").html(object[length-1].onlineFourSection);
				$("#chart6_6").html(object[length-1].onlineSixSection);
				$("#chart6_7").html(object[length-1].onlineSevenSection);
				$("#chart6_8").html(object[length-1].onlineEightSection);
				//显示图表7数据
				var backupSectionList = new Array();
				backupSectionList[0]=object[length-1].backupThreeSection;
				backupSectionList[1]=object[length-1].backupFourSection;
				backupSectionList[2]=object[length-1].backupSixSection;
				backupSectionList[3]=object[length-1].backupSevenSection;
				backupSectionList[4]=object[length-1].backupEightSection;
				$("#chart7_all").html(object[length-1].backupThreeSection+object[length-1].backupFourSection+object[length-1].backupSixSection+object[length-1].backupSevenSection+object[length-1].backupEightSection);	
				$("#chart7_3").html(object[length-1].backupThreeSection);
				$("#chart7_4").html(object[length-1].backupFourSection);
				$("#chart7_6").html(object[length-1].backupSixSection);
				$("#chart7_7").html(object[length-1].backupSevenSection);
				$("#chart7_8").html(object[length-1].backupEightSection);
				var columnList = new Array();
				var distanceList = new Array();
				var passengerCapacityList = new Array();
				var passengerTransferList = new Array();
				var ticketIncomeList = new Array();
				var dateList = new Array();
				
				var columnControlList = new Array();
				var distanceControlList = new Array();
				var passengerCapacityControlList = new Array();
				var passengerTransferControlList = new Array();
				var ticketIncomeControlList = new Array();
				
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					columnList[i] = object[i].metroColumnMonth;
					distanceList[i] = object[i].metroDistanceMonth;
					passengerCapacityList[i] = object[i].passengerCapacityMonth;
					passengerTransferList[i] = object[i].passengerTransferMonth;
					ticketIncomeList[i] = object[i].ticketIncomeMonth;
					if(object[i].metroProductionControlVO!=null){
						columnControlList.push(object[i].metroProductionControlVO.metroColumnMonth);					
						if(object[i].metroProductionControlVO.metroDistanceMonth!=null){
							distanceControlList.push(parseFloat(object[i].metroProductionControlVO.metroDistanceMonth));
						}
						if(object[i].metroProductionControlVO.passengerCapacityMonth!=null){
							passengerCapacityControlList.push(parseFloat(object[i].metroProductionControlVO.passengerCapacityMonth));
						}
						if(object[i].metroProductionControlVO.passengerTransferMonth!=null){
							passengerTransferControlList.push(parseFloat(object[i].metroProductionControlVO.passengerTransferMonth));
						}
						if(object[i].metroProductionControlVO.ticketIncomeMonth!=null){
							ticketIncomeControlList.push(parseFloat(object[i].metroProductionControlVO.ticketIncomeMonth));
						}
					}else{
						columnControlList.push(null);
						distanceControlList.push(null);
						passengerCapacityControlList.push(null);
						passengerTransferControlList.push(null);
						ticketIncomeControlList.push(null);
					}
				}				
				
				newChartColumn(columnList,dateList,'chart1',columnControlList,columnColor);
				newChartColumn(distanceList,dateList,'chart2',distanceControlList,distanceColor);
				newChartColumn(passengerCapacityList,dateList,'chart3',passengerCapacityControlList,passengerCapacityColor);
				newChartColumn(passengerTransferList,dateList,'chart4',passengerTransferControlList,passengerTransferColor);
				newChartColumn(ticketIncomeList,dateList,'chart5',ticketIncomeControlList,ticketIncomeColor)
				
				newChartPie(onlineSectionList,"chart6");
				newChartPie(backupSectionList,"chart7");
			}
		}
	});
}

/**
 * 显示tab1,年视图 
 */
function showTab1ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataProductionByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null || object==""){
				alert("无相关数据，请重新选择线路或日期!");
				//显示图表1数据
				$("#chart1_daily").html("0");
				$("#chart1_lastyear").html("0");
				$("#chart1_year").parent("li").hide();
				$("#chart1_month").parent("li").hide();
				//显示图表2数据
				$("#chart2_daily").html("0");	
				$("#chart2_lastyear").html("0");
				$("#chart2_year").parent("li").hide();
				$("#chart2_month").parent("li").hide();
				//显示图表3数据
				$("#chart3_daily").html("0");	
				$("#chart3_lastyear").html("0");
				$("#chart3_year").parent("li").hide();
				$("#chart3_month").parent("li").hide();
				//显示图表4数据
				$("#chart4_daily").html("0");	
				$("#chart4_lastyear").html("0");
				$("#chart4_year").parent("li").hide();
				$("#chart4_month").parent("li").hide();
				//显示图表5数据
				$("#chart5_daily").html("0");	
				$("#chart5_lastyear").html("0");
				$("#chart5_year").parent("li").hide();
				$("#chart5_month").parent("li").hide();
				//显示图表6数据
				$("#chart6_all").html("0");	
				$("#chart6_3").html("0");
				$("#chart6_4").html("0");
				$("#chart6_6").html("0");
				$("#chart6_7").html("0");
				$("#chart6_8").html("0");
				//显示图表7数据
				$("#chart7_all").html("0");	
				$("#chart7_3").html("0");
				$("#chart7_4").html("0");
				$("#chart7_6").html("0");
				$("#chart7_7").html("0");
				$("#chart7_8").html("0");
				newChartColumn(null,dateList,'chart1',columnControlList,null);
				newChartColumn(null,dateList,'chart2',distanceControlList,null);
				newChartColumn(null,dateList,'chart3',passengerCapacityControlList,null);
				newChartColumn(null,dateList,'chart4',passengerTransferControlList,null);
				newChartColumn(null,dateList,'chart5',ticketIncomeControlList,null)
				
				newChartPie(null,"chart6");
				newChartPie(null,"chart7");
				
			}else{
				var length = object.length;
				var columnYear = parseFloat(object[length-1].metroColumnYear);
				var distanceYear = parseFloat(object[length-1].metroDistanceYear);
				var passengerCapacityYear = parseFloat(object[length-1].passengerCapacityYear);
				var passengerTransferYear = parseFloat(object[length-1].passengerTransferYear);
				var ticketIncomeYear = parseFloat(object[length-1].ticketIncomeYear);
				var columnColor = '';
				var distanceColor = '';
				var passengerCapacityColor = '';
				var passengerTransferColor = '';
				var ticketIncomeColor = '';
				var cColumnYear = '';
				var cDistanceYear = '';
				var cPassengerCapacityYear ='';
				var cPassengerTransferYear ='';
				var cTicketIncomeYear = ''; 
				if(object[length-1].metroProductionControlVO!=null){
					cColumnYear = parseFloat(object[length-1].metroProductionControlVO.metroColumnYear);
					columnColor = getControlColor(cColumnYear,columnYear,'chart1Info',object[length-1].metroProductionControlVO.metroColumnControl,object[length-1].metroProductionControlVO.metroColumnDescribe);
					
					cDistanceYear = parseFloat(object[length-1].metroProductionControlVO.metroDistanceYear);
					distanceColor = getControlColor(cDistanceYear,distanceYear,'chart2Info',object[length-1].metroProductionControlVO.metroDistanceControl,object[length-1].metroProductionControlVO.metroDistanceDescribe);
					
					cPassengerCapacityYear = parseFloat(object[length-1].metroProductionControlVO.passengerCapacityYear);
					passengerCapacityColor = getControlColor(cPassengerCapacityYear,passengerCapacityYear,'chart3Info',object[length-1].metroProductionControlVO.passengerCapacityControl,object[length-1].metroProductionControlVO.passengerCapacityDescribe);
					
					cPassengerTransferYear = parseFloat(object[length-1].metroProductionControlVO.passengerTransferYear);
					passengerTransferColor = getControlColor(cPassengerTransferYear,passengerTransferYear,'chart4Info',object[length-1].metroProductionControlVO.passengerTransferControl,object[length-1].metroProductionControlVO.passengerTransferDescribe);
					
					cTicketIncomeYear = parseFloat(object[length-1].metroProductionControlVO.ticketIncomeYear);
					ticketIncomeColor = getControlColor(cTicketIncomeYear,ticketIncomeYear,'chart5Info',object[length-1].metroProductionControlVO.ticketIncomeControl,object[length-1].metroProductionControlVO.ticketIncomeDescribe);
				}else{
					$("#chart1Info").attr("style","display:none");
					$("#chart2Info").attr("style","display:none");
					$("#chart3Info").attr("style","display:none");
					$("#chart4Info").attr("style","display:none");
					$("#chart5Info").attr("style","display:none");
				}
				//显示图表1数据
				$("#chart1_daily").html(columnYear);
				$("#chart1_lastyear").html(object[length-1].lastyearMetroProduction.metroColumnYear);
				$("#chart1_year").parent("li").hide();
				$("#chart1_month").parent("li").hide();
				//显示图表2数据
				$("#chart2_daily").html(distanceYear);	
				$("#chart2_lastyear").html(object[length-1].lastyearMetroProduction.metroDistanceYear);
				$("#chart2_year").parent("li").hide();
				$("#chart2_month").parent("li").hide();
				//显示图表3数据
				$("#chart3_daily").html(passengerCapacityYear);	
				$("#chart3_lastyear").html(object[length-1].lastyearMetroProduction.passengerCapacityYear);
				$("#chart3_year").parent("li").hide();
				$("#chart3_month").parent("li").hide();
				//显示图表4数据
				$("#chart4_daily").html(passengerTransferYear);	
				$("#chart4_lastyear").html(object[length-1].lastyearMetroProduction.passengerTransferYear);
				$("#chart4_year").parent("li").hide();
				$("#chart4_month").parent("li").hide();
				//显示图表5数据
				$("#chart5_daily").html(ticketIncomeYear);	
				$("#chart5_lastyear").html(object[length-1].lastyearMetroProduction.ticketIncomeYear);
				$("#chart5_year").parent("li").hide();
				$("#chart5_month").parent("li").hide();
				//显示图表6数据
				var onlineSectionList = new Array();
				onlineSectionList[0]=object[length-1].onlineThreeSection;
				onlineSectionList[1]=object[length-1].onlineFourSection;
				onlineSectionList[2]=object[length-1].onlineSixSection;
				onlineSectionList[3]=object[length-1].onlineSevenSection;
				onlineSectionList[4]=object[length-1].onlineEightSection;
				$("#chart6_all").html(object[length-1].onlineThreeSection+object[length-1].onlineFourSection+object[length-1].onlineSixSection+object[length-1].onlineSevenSection+object[length-1].onlineEightSection);	
				$("#chart6_3").html(object[length-1].onlineThreeSection);
				$("#chart6_4").html(object[length-1].onlineFourSection);
				$("#chart6_6").html(object[length-1].onlineSixSection);
				$("#chart6_7").html(object[length-1].onlineSevenSection);
				$("#chart6_8").html(object[length-1].onlineEightSection);
				//显示图表7数据
				var backupSectionList = new Array();
				backupSectionList[0]=object[length-1].backupThreeSection;
				backupSectionList[1]=object[length-1].backupFourSection;
				backupSectionList[2]=object[length-1].backupSixSection;
				backupSectionList[3]=object[length-1].backupSevenSection;
				backupSectionList[4]=object[length-1].backupEightSection;
				$("#chart7_all").html(object[length-1].backupThreeSection+object[length-1].backupFourSection+object[length-1].backupSixSection+object[length-1].backupSevenSection+object[length-1].backupEightSection);	
				$("#chart7_3").html(object[length-1].backupThreeSection);
				$("#chart7_4").html(object[length-1].backupFourSection);
				$("#chart7_6").html(object[length-1].backupSixSection);
				$("#chart7_7").html(object[length-1].backupSevenSection);
				$("#chart7_8").html(object[length-1].backupEightSection);
				var columnList = new Array();
				var distanceList = new Array();
				var passengerCapacityList = new Array();
				var passengerTransferList = new Array();
				var ticketIncomeList = new Array();
				var dateList = new Array();
				
				var columnControlList = new Array();
				var distanceControlList = new Array();
				var passengerCapacityControlList = new Array();
				var passengerTransferControlList = new Array();
				var ticketIncomeControlList = new Array();
				
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					columnList[i] = object[i].metroColumnYear;
					distanceList[i] = object[i].metroDistanceYear;
					passengerCapacityList[i] = object[i].passengerCapacityYear;
					passengerTransferList[i] = object[i].passengerTransferYear;
					ticketIncomeList[i] = object[i].ticketIncomeYear;
					
					if(object[i].metroProductionControlVO!=null){
						columnControlList.push(object[i].metroProductionControlVO.metroColumnYear);					
						if(object[i].metroProductionControlVO.metroDistanceYear!=null){
							distanceControlList.push(parseFloat(object[i].metroProductionControlVO.metroDistanceYear));
						}
						if(object[i].metroProductionControlVO.passengerCapacityYear!=null){
							passengerCapacityControlList.push(parseFloat(object[i].metroProductionControlVO.passengerCapacityYear));
						}
						if(object[i].metroProductionControlVO.passengerTransferYear!=null){
							passengerTransferControlList.push(parseFloat(object[i].metroProductionControlVO.passengerTransferYear));
						}
						if(object[i].metroProductionControlVO.ticketIncomeYear!=null){
							ticketIncomeControlList.push(parseFloat(object[i].metroProductionControlVO.ticketIncomeYear));
						}
					}else{
						columnControlList.push(null);
						distanceControlList.push(null);
						passengerCapacityControlList.push(null);
						passengerTransferControlList.push(null);
						ticketIncomeControlList.push(null);
					}
				}
				
				newChartColumn(columnList,dateList,'chart1',columnControlList,columnColor);
				newChartColumn(distanceList,dateList,'chart2',distanceControlList,distanceColor);
				newChartColumn(passengerCapacityList,dateList,'chart3',passengerCapacityControlList,passengerCapacityColor);
				newChartColumn(passengerTransferList,dateList,'chart4',passengerTransferControlList,passengerTransferColor);
				newChartColumn(ticketIncomeList,dateList,'chart5',ticketIncomeControlList,ticketIncomeColor)
				
				newChartPie(onlineSectionList,"chart6");
				newChartPie(backupSectionList,"chart7");
			}
		}
	});
}


/**
 * 显示tab2,日视图
 */
function showTab2ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQuality.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择日期！");
			}else{
				var cMetroOntimeDaily = '';
				var cMetroOnworkDaily = '';
				var ontimeColor = '';
				var onworkColor = ''
				if(object.metroQualityControlVO!=null){
					cMetroOntimeDaily = parseFloat(object.metroQualityControlVO.metroOntimeDaily);
					ontimeColor = getControlColor(cMetroOntimeDaily,object.lastestQuality.metroOntimeDaily,'chart8Info',object.metroQualityControlVO.ontimeControl,object.metroQualityControlVO.ontimeDescribe);
									
					cMetroOnworkDaily = parseFloat(object.metroQualityControlVO.metroOnworkDaily); 
					onworkColor = getControlColor(cMetroOnworkDaily,object.lastestQuality.metroOnworkDaily,'chart9Info',object.metroQualityControlVO.onworkControl,object.metroQualityControlVO.onworkDescribe);
				}else{
					$("#chart8Info").attr("style","display:none");
					$("#chart9Info").attr("style","display:none");
				}
				//显示图表8数据
				if(object.lastestQuality ==null){
					alert("无相关数据，请重新选择线路或日期！");
					$("#chart8_daily").html("0");
					$("#chart8_month").html("0");
					$("#chart8_year").html("0");
				}else{
					$("#chart8_daily").html(object.lastestQuality.metroOntimeDaily);
					$("#chart8_month").html(object.lastestQuality.metroOntimeMonth);
					$("#chart8_year").html(object.lastestQuality.metroOntimeYear);
				}
				$("#chart8_month").parent("li").show();
				$("#chart8_year").parent("li").show();
				$("#chart8_lastyear").html(object.lastYearQuality.metroOntimeDaily);
				//显示图表9数据
				if(object.lastestQuality ==null){
					$("#chart9_daily").html("0");
					$("#chart9_month").html("0");
					$("#chart9_year").html("0");
				}else{
					$("#chart9_daily").html(object.lastestQuality.metroOnworkDaily);
					$("#chart9_month").html(object.lastestQuality.metroOnworkMonth);
					$("#chart9_year").html(object.lastestQuality.metroOnworkYear);
				}
				$("#chart9_lastyear").html(object.lastYearQuality.metroOnworkDaily);
				$("#chart9_month").parent("li").show();
				$("#chart9_year").parent("li").show();
				
				newChartLine(object.onTimeList,object.dateList,'chart8',object.ontimeControlValueList,ontimeColor);
				newChartLine(object.onWorkList,object.dateList,'chart9',object.onworkControlValueList,onworkColor);
				
			}
		}
	});
}

/**
 * 显示tab2,月视图 
 */
function showTab2ByMonth(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQualityByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			var length ;
			var last ; 
			if(object!=null){
				length = object.length;
				last = object[length-1];
				var onTimeList = new Array();
				var onWorkList = new Array();
				var dateList = new Array();
				
				var cMetroOntimeDaily = new Array();
				var cMetroOnworkDaily = new Array();
				var ontimeColor = '';
				var onworkColor = ''
				
				var ontimeControlValueList = new Array();	//准点管控制list
				var onworkControlValueList = new Array();	//兑现管控值list
					
				if(last.metroQualityControlVO!=null){
					cMetroOntimeDaily.push(parseFloat(object[length-1].metroQualityControlVO.metroOntimeMonth));
					ontimeColor = getControlColor(cMetroOntimeDaily,object[length-1].metroOntimeMonth,'chart8Info',object[length-1].metroQualityControlVO.ontimeControl,object[length-1].metroQualityControlVO.ontimeDescribe);
					
					cMetroOnworkDaily.push(parseFloat(object[length-1].metroQualityControlVO.metroOnworkMonth)); 
					onworkColor = getControlColor(cMetroOnworkDaily,object[length-1].metroOnworkMonth,'chart9Info',object[length-1].metroQualityControlVO.onworkControl,object[length-1].metroQualityControlVO.onworkDescribe);
				}else{
					$("#chart8Info").attr("style","display:none");
					$("#chart9Info").attr("style","display:none");
				}
				
				if(length !=0 ){
					for(var i=0; i<length; i++){
						dateList[i] = object[i].indicatorDate.substr(0,7);
						onTimeList[i] = object[i].metroOntimeMonth;
						onWorkList[i] = object[i].metroOnworkMonth;
						
						if(object[i].metroQualityControlVO!=null){
							ontimeControlValueList.push(object[i].metroQualityControlVO.metroOntimeMonth);
						}else{
							ontimeControlValueList.push(null);
						}
						
						if(object[i].metroQualityControlVO!=null){
							onworkControlValueList.push(object[i].metroQualityControlVO.metroOnworkMonth);
						}else{
							onworkControlValueList.push(null);
						}
					}
				}
				
				//显示图表8数据
				$("#chart8_daily").html(last.metroOntimeMonth);
				$("#chart8_lastyear").html(last.lastyearQualityVo.metroOntimeMonth);			
				$("#chart8_year").html(last.metroOntimeYear);
				$("#chart8_month").parent("li").hide();
				$("#chart8_year").parent("li").show();
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkMonth);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkMonth);
				$("#chart9_year").html(last.lastyearQualityVo.metroOnworkYear);
				$("#chart9_month").parent("li").hide();
				$("#chart9_year").parent("li").show();
				newChartLine(onTimeList,dateList,'chart8',ontimeControlValueList,ontimeColor);
				newChartLine(onWorkList,dateList,'chart9',onworkControlValueList,onworkColor);
			}else{
				alert("无相关数据,请重新选择线路或日期！");
				//显示图表8数据
				$("#chart8_daily").html("0");
				$("#chart8_lastyear").html("0");			
				$("#chart8_year").html("0");
				$("#chart8_month").parent("li").hide();
				$("#chart8_year").parent("li").show();
				//显示图表9数据
				$("#chart9_daily").html("0");
				$("#chart9_lastyear").html("0");
				$("#chart9_year").html("0");
				$("#chart9_month").parent("li").hide();
				$("#chart9_year").parent("li").show();
				newChartLine(0,dateList,'chart8',0,ontimeColor);
				newChartLine(0,dateList,'chart9',0,onworkColor);
			}
		}
	});
}

/**
 * 显示tab2,年视图
 */
function showTab2ByYear(){
	$.ajax({
		type : 'post',
		url : 'findChartDataQualityByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			var length ;
			var last ; 
			if(object!=null){
				length = object.length;
				last = object[length-1];
				var onTimeList = new Array();
				var onWorkList = new Array();
				var dateList = new Array();
				var cMetroOntimeDaily = '';
				var cMetroOnworkDaily = '';
				var ontimeColor = '';
				var onworkColor = ''
					
				var ontimeControlValueList = new Array();	//准点管控制list
				var onworkControlValueList = new Array();	//兑现管控值list
					
				if(last.metroQualityControlVO!=null){
					cMetroOntimeDaily = parseFloat(object[length-1].metroQualityControlVO.metroOntimeYear);
					ontimeColor = getControlColor(cMetroOntimeDaily,object[length-1].metroOntimeYear,'chart8Info',object[length-1].metroQualityControlVO.ontimeControl,object[length-1].metroQualityControlVO.ontimeDescribe);
					
					
					cMetroOnworkDaily = parseFloat(object[length-1].metroQualityControlVO.metroOnworkYear); 
					onworkColor = getControlColor(cMetroOnworkDaily,object[length-1].metroOnworkYear,'chart9Info',object[length-1].metroQualityControlVO.onworkControl,object[length-1].metroQualityControlVO.onworkDescribe);
				}else{
					$("#chart8Info").attr("style","display:none");
					$("#chart9Info").attr("style","display:none");
				}
				//显示图表8数据
				$("#chart8_daily").html(last.metroOntimeYear);
				$("#chart8_lastyear").html(last.lastyearQualityVo.metroOntimeYear);
				$("#chart8_month").parent("li").hide();
				$("#chart8_year").parent("li").hide();
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkYear);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkYear);
				$("#chart9_month").parent("li").hide();
				$("#chart9_year").parent("li").hide();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					onTimeList[i] = object[i].metroOntimeYear;
					onWorkList[i] = object[i].metroOnworkYear;
					if(object[i].metroQualityControlVO!=null){
						ontimeControlValueList.push(object[i].metroQualityControlVO.metroOntimeYear);
					}else{
						ontimeControlValueList.push(null);
					}
					
					if(object[i].metroQualityControlVO!=null){
						onworkControlValueList.push(object[i].metroQualityControlVO.metroOnworkYear);
					}else{
						onworkControlValueList.push(null);
					}
				}
				newChartLine(onTimeList,dateList,'chart8',ontimeControlValueList,ontimeColor);
				newChartLine(onWorkList,dateList,'chart9',onworkControlValueList,onworkColor);
			}else{
				alert("无相关数据,请重新选择线路或日期！");
				//显示图表8数据
				$("#chart8_daily").html("0");
				$("#chart8_lastyear").html("0");
				$("#chart8_month").parent("li").hide();
				$("#chart8_year").parent("li").hide();
				//显示图表9数据
				$("#chart9_daily").html("0");
				$("#chart9_lastyear").html("0");
				$("#chart9_month").parent("li").hide();
				$("#chart9_year").parent("li").hide();
				newChartLine(0,dateList,'chart8',0,ontimeColor);
				newChartLine(0,dateList,'chart9',0,onworkColor);
			}
		}
	});
}


/**
 * 显示tab3,日视图 
 */
function showTab3ByDay(){
	$.ajax({
		type : 'post',
		url : 'findChartDataScale.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			lineNo : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object==null){
				alert("无相关数据，请重新选择线路或日期！");
				//显示图表10数据
				$("#chart10_all").html("0");
				//显示图表11数据
				$("#chart11_all").html("0");
				//显示图表12数据
				$("#chart12_all").html("0");
				$("#chart12_3").html("0");
				$("#chart12_4").html("0");
				$("#chart12_6").html("0");
				$("#chart12_7").html("0");
				$("#chart12_8").html("0");
				newChartPie(0,"chart12");
			}else{
				//显示图表10数据
				$("#chart10_all").html(object.lineDistance);
				//显示图表11数据
				$("#chart11_all").html(object.stationCount);
				//显示图表12数据
				$("#chart12_all").html(object.allocateThreeSection+object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_3").html(object.allocateThreeSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateThreeSection,object.allocateFourSection,object.allocateSixSection,object.allocateSevenSection,object.allocateEightSection];
				newChartPie(valueList,"chart12");
			}
		}
	});
}


/**
 * 显示tab3,月视图 
 */
function showTab3ByMonth(){

	$.ajax({
		type : 'post',
		url : 'findChartDataScaleByMonth.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				//显示图表10数据
				$("#chart10_all").html(object.lineDistance);
				//显示图表11数据
				$("#chart11_all").html(object.stationCount);
				//显示图表12数据
				$("#chart12_all").html(object.allocateThreeSection+object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_3").html(object.allocateThreeSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateThreeSection,object.allocateFourSection,object.allocateFourSection,object.allocateSevenSection,object.allocateEightSection];
				newChartPie(valueList,"chart12");			
			}else{
				alert("无相关数据，请重新选择日期!");
			}
		}
	});
}


/**
 * 显示tab3,年视图
 */
function showTab3ByYear(){

	$.ajax({
		type : 'post',
		url : 'findChartDataScaleByYear.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line : $("#lineNo").val(),
			date : $("#datepicker").val()
		},
		error:function(){
			alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				$("#chart10_all").html(object.lineDistance);
				$("#chart11_all").html(object.stationCount);
				$("#chart12_all").html(object.allocateThreeSection+object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_3").html(object.allocateThreeSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateThreeSection,object.allocateFourSection,object.allocateFourSection,object.allocateSevenSection,object.allocateEightSection];
				newChartPie(valueList,"chart12");			
			}else{
				alert("无相关数据，请重新选择日期!");
			}
		}
	});
}


 /**
  * 画一个柱状图
  * @param valueList 图表的值 (数组)
  * @param dateList	日期值	(数组)
  * @param renderTo	要显示位置的id
  * @param controlValue 管控值 (数组)
  * @colorA 最后一条数据显示的颜色
  */
 function newChartColumn(valueList,dateList,renderTo,controlValue,colorA){
 	var  chartOption ={
 		chart: {
 		    renderTo: renderTo,
 		    type: 'column',
 		    height: h,
 		    borderWidth:0,
 		    width:w
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
                 return this.x + '<br>值:'+ this.y +'';
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
 			}, 
 			labels: {
 				align: 'left',
                 style: {
                     fontSize:'9px'
                 }
             }
 		}
 	};
 	
 	var controlList = new Array();
 	if(controlValue!=null && controlValue.length>0){
 		
 		for(var i=0; i<controlValue.length; i++){
 			if(controlValue[i]!=null && !isNaN(controlValue[i]) && controlValue[i]!=""){
 				controlList.push(parseInt(controlValue[i]));
 			}else{
 				//controlList.push(0);	//0能成功成功
 				controlList.push(null);
 			}
 		}
 		
 		
 		var realData = new Array();
 		var innerData;
 		for(var i=0; i<valueList.length; i++){
 			if(i!=valueList.length-1){
 				innerData = {y:valueList[i],color:'#4572A7'};
 			}else{
 				innerData = {y:valueList[i],color:colorA=='' ? '#4572A7' : colorA};
 			}
 			realData.push(innerData);
 		}
 		chartOption.series = [{
 			data:realData},
 	  		{name:	"管控线",
	 			data:controlList,
	 			type:'line',
	 			color:'#80699B'}
 		];
 	}else{
 		chartOption.series = [{
 			name:'值',
 			data:valueList
 		}]
 	  		
 	}
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
 	        height: h,
 	        width:w
 		},
 		title: {
 			text: ''
 		},
 		credits: {
 			enabled:false
 		},
 		tooltip: {
              formatter: function() {
                return this.point.name + '<br>值:'+ this.y +' 列';
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
 			    {	name :'3节编组',
					y : valueList[0],
					color : '#8B1A1A'
				},
 				{	name :'4节编组',
 					y : valueList[1],
 					color : '#79BB25'
 				},
 				{	name :'6节编组',
 					y : valueList[2],
 					color : '#FFC002'
 				},
 				{	name :'7节编组',
 					y : valueList[3],
 					color : '#4572A7'
 				},
 				{	name :'8节编组',
 					y : valueList[4],
 					color : '#FE5917'
 				}
 			]
 		}]
 	};
 	new Highcharts.Chart(chartOption); 
 }


 /**
  * 画一个折线图
  * @param valueList	值
  * @param dateList	日期值
  * @param renderTo	显示的位置的id
  *	@param controlValueList	管控值，list
  * @param colorA	颜色
  * @return
  */
 function newChartLine(valueList,dateList,renderTo,controlValueList,colorA){
 	var  chartOption ={
 		chart: {
 		    renderTo: renderTo,
 		    type: 'line',
 		    height: h,
 		    borderWidth:0,
 		    width:w
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
 		/*series: [{
 			name:"值",
 	  		data: valueList
 		}]*/
	
	if(controlValueList!=''){
 		var realData = new Array();
 		var innerData;
 		var controlData = new Array();
	
 		for(var i=0; i<controlValueList.length; i++){
			if(controlValueList[i]!=null && !isNaN(parseFloat(controlValueList[i]))){
 				controlData.push(parseFloat(controlValueList[i]));
 			}else{
 				controlData.push(null);
 			}
 			if(i!=valueList.length-1){
 				innerData = {y:valueList[i],color:'#4572A7'};
 			}else{
 				innerData = {y:valueList[i],color:colorA=='' ? '#4572A7' : colorA};
 			}
 			realData.push(innerData);
 		}
 		chartOption.series = [{
 			name:"值",
 			data:realData},
 	  		{name:	"管控线",
 				data:controlData,
	 			color:'#80699B'}
 		];
 	}else{
 		chartOption.series = [{
 			name:'值',
 			data:valueList
 		}]
 	  		
 	}
 	new Highcharts.Chart(chartOption); 
 }











