
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
					columnColor = getColorOverIsGood(cColumnDaily,columnDaily,'chart1Info');
				}
				$("#chart1_daily").html(object.lastestProduction.metroColumnDaily);	
				$("#chart1_lastyear").html(object.lastYearProduction.metroColumnDaily);
				$("#chart1_month").html(object.lastestProduction.metroColumnMonth);
				$("#chart1_year").html(object.lastestProduction.metroColumnYear);
				newChartColumn(object.columnList,object.dateList,'chart1',cColumnDaily,columnColor);
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
					columnColor = getColorOverIsGood(cColumnMonth,columnMonth,'chart1Info');
				}
				var columnList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					columnList[i] = object[i].metroColumnMonth;
				}
				$("#chart1_daily").html(object[length-1].metroColumnMonth);
				$("#chart1_lastyear").html(object[length-1].lastyearMetroProduction.metroColumnMonth);
				$("#chart1_year").html(object[length-1].metroColumnYear);
				$("#chart1_month").parent("li").hide();//隐藏月累计
				$("#chart1_year").parent("li").show();
				newChartColumn(columnList,dateList,'chart1',cColumnMonth,columnColor);
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
					columnColor = getColorOverIsGood(cColumnYear,columnYear,"chart1Info");
				}
				var columnList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					columnList[i] = object[i].metroColumnMonth;
				}
				$("#chart1_daily").html(object[length-1].metroColumnYear);
				$("#chart1_lastyear").html(object[length-1].lastyearMetroProduction.metroColumnYear);
				$("#chart1_year").parent("li").hide();
				$("#chart1_month").parent("li").hide();
				newChartColumn(columnList,dateList,'chart1',cColumnYear,columnColor);
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
					distanceColor = getColorOverIsGood(cDistanceDaily,distanceDaily,'chart2Info');
				}
				$("#chart2_daily").html(object.lastestProduction.metroDistanceDaily);	
				$("#chart2_lastyear").html(object.lastYearProduction.metroDistanceDaily);
				$("#chart2_month").html(object.lastestProduction.metroDistanceMonth);
				$("#chart2_year").html(object.lastestProduction.metroDistanceYear);
				$("#chart2_month").parent("li").show();
				$("#chart2_year").parent("li").show();
				newChartColumn(object.distanceList,object.dateList,'chart2',cDistanceDaily,distanceColor);
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
					distanceColor = getColorOverIsGood(cDistanceMonth,distanceMonth,'chart2Info');
				}
				$("#chart2_daily").html(object[length-1].metroDistanceMonth);	
				$("#chart2_lastyear").html(object[length-1].lastyearMetroProduction.metroDistanceMonth);
				$("#chart2_year").html(object[length-1].metroDistanceYear);
				var distanceList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					distanceList[i] = object[i].metroDistanceMonth;
				}
				newChartColumn(distanceList,dateList,'chart2',cDistanceMonth,distanceColor);
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
				var distanceYear = parseFloat(object[length-1].metroDistanceYear);
				var distanceColor = '';
				var cDistanceYear = '';
				if(object[length-1].metroProductionControlVO!=null){
					cDistanceYear = parseFloat(object[length-1].metroProductionControlVO.metroDistanceYear);
					distanceColor = getColorOverIsGood(cDistanceYear,distanceYear,'chart2Info');
				}
				$("#chart2_daily").html(object[length-1].metroDistanceYear);	
				$("#chart2_lastyear").html(object[length-1].lastyearMetroProduction.metroDistanceYear);
				var distanceList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					distanceList[i] = object[i].metroDistanceMonth;
				}
				newChartColumn(distanceList,dateList,'chart2',cDistanceYear,distanceColor);
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
					passengerCapacityColor = getColorOverIsGood(cPassengerCapacityDaily,passengerCapacityDaily,'chart3Info');
				}
				$("#chart3_daily").html(object.lastestProduction.passengerCapacityDaily);	
				$("#chart3_lastyear").html(object.lastYearProduction.passengerCapacityDaily);
				$("#chart3_month").html(object.lastestProduction.passengerCapacityMonth);
				$("#chart3_year").html(object.lastestProduction.passengerCapacityYear);
				newChartColumn(object.passengerCapacityList,object.dateList,'chart3',cPassengerCapacityDaily,passengerCapacityColor);
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
					passengerCapacityColor = getColorOverIsGood(cPassengerCapacityMonth,passengerCapacityMonth,'chart3Info');
				}
				$("#chart3_daily").html(object[length-1].passengerCapacityMonth);	
				$("#chart3_lastyear").html(object[length-1].lastyearMetroProduction.passengerCapacityMonth);
				$("#chart3_year").html(object[length-1].passengerCapacityYear);
				var passengerCapacityList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					passengerCapacityList[i] = object[i].passengerCapacityMonth;
				}
				newChartColumn(passengerCapacityList,dateList,'chart3',cPassengerCapacityMonth,passengerCapacityColor);
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
					passengerCapacityColor = getColorOverIsGood(cPassengerCapacityYear,passengerCapacityYear,'chart3Info');
				}
				$("#chart3_daily").html(object[length-1].passengerCapacityYear);	
				$("#chart3_lastyear").html(object[length-1].lastyearMetroProduction.passengerCapacityYear);
				var passengerCapacityList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					passengerCapacityList[i] = object[i].passengerCapacityMonth;
				}
				newChartColumn(passengerCapacityList,dateList,'chart3',cPassengerCapacityYear,passengerCapacityColor);
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
					passengerTransferColor = getColorOverIsGood(cPassengerTransferDaily,passengerTransferDaily,'chart4Info');
				}
				$("#chart4_daily").html(object.lastestProduction.passengerTransferDaily);	
				$("#chart4_lastyear").html(object.lastYearProduction.passengerTransferDaily);
				$("#chart4_month").html(object.lastestProduction.passengerTransferMonth);
				$("#chart4_year").html(object.lastestProduction.passengerTransferYear);
				newChartColumn(object.passengerTransferList,object.dateList,'chart4',cPassengerTransferDaily,passengerTransferColor);
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
					passengerTransferColor = getColorOverIsGood(cPassengerTransferMonth,passengerTransferMonth,'chart4Info');
				}
				$("#chart4_daily").html(object[length-1].passengerTransferMonth);	
				$("#chart4_lastyear").html(object[length-1].lastyearMetroProduction.passengerTransferMonth);
				$("#chart4_year").html(object[length-1].passengerTransferYear);
				var passengerTransferList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					passengerTransferList[i] = object[i].passengerTransferMonth;
				}
				newChartColumn(passengerTransferList,dateList,'chart4',cPassengerTransferMonth,passengerTransferColor);
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
					passengerTransferColor = getColorOverIsGood(cPassengerTransferYear,passengerTransferYear,'chart4Info');
				}
				$("#chart4_daily").html(object[length-1].passengerTransferYear);	
				$("#chart4_lastyear").html(object[length-1].lastyearMetroProduction.passengerTransferYear);
				var passengerTransferList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					passengerTransferList[i] = object[i].passengerTransferMonth;
				}
				newChartColumn(passengerTransferList,dateList,'chart4',cPassengerTransferYear,passengerTransferColor);
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
					ticketIncomeColor = getColorOverIsGood(cTicketIncomeDaily,ticketIncomeDaily,'chart5Info');
				}
				$("#chart5_daily").html(object.lastestProduction.ticketIncomeDaily);	
				$("#chart5_lastyear").html(object.lastYearProduction.ticketIncomeDaily);
				$("#chart5_month").html(object.lastestProduction.ticketIncomeMonth);
				$("#chart5_year").html(object.lastestProduction.ticketIncomeYear);
				newChartColumn(object.ticketIncomeList,object.dateList,'chart5',cTicketIncomeDaily,ticketIncomeColor);
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
					ticketIncomeColor = getColorOverIsGood(cTicketIncomeMonth,ticketIncomeMonth,'chart5Info');
				}
				$("#chart5_daily").html(object[length-1].ticketIncomeMonth);	
				$("#chart5_lastyear").html(object[length-1].lastyearMetroProduction.ticketIncomeMonth);
				$("#chart5_year").html(object[length-1].ticketIncomeYear);
				var ticketIncomeList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					ticketIncomeList[i] = object[i].ticketIncomeMonth;
				}
				newChartColumn(ticketIncomeList,dateList,'chart5',cTicketIncomeMonth,ticketIncomeColor);
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
					ticketIncomeColor = getColorOverIsGood(cTicketIncomeYear,ticketIncomeYear,'chart5Info');
				}
				$("#chart5_daily").html(object[length-1].ticketIncomeYear);	
				$("#chart5_lastyear").html(object[length-1].lastyearMetroProduction.ticketIncomeYear);
				var ticketIncomeList = new Array();
				var dateList = new Array();
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					ticketIncomeList[i] = object[i].ticketIncomeMonth;
				}
				newChartColumn(ticketIncomeList,dateList,'chart5',cTicketIncomeYear,ticketIncomeColor);
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
				$("#chart6_all").html(online[0]+online[1]+online[2]+online[3]);	
				$("#chart6_4").html(online[0]);
				$("#chart6_6").html(online[1]);
				$("#chart6_7").html(online[2]);
				$("#chart6_8").html(online[3]);
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
				$("#chart7_all").html(backup[0]+backup[1]+backup[2]+backup[3]);	
				$("#chart7_4").html(backup[0]);
				$("#chart7_6").html(backup[1]);
				$("#chart7_7").html(backup[2]);
				$("#chart7_8").html(backup[3]);
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
					ontimeColor = getColorOverIsGood(cMetroOntimeDaily,object.lastestQuality.metroOntimeDaily,"chart8Info");
				}
				$("#chart8_daily").html(object.lastestQuality.metroOntimeDaily);
				$("#chart8_lastyear").html(object.lastYearQuality.metroOntimeDaily);
				$("#chart8_month").html(object.lastestQuality.metroOntimeMonth);
				$("#chart8_year").html(object.lastestQuality.metroOntimeYear);
				newChartLine(object.onTimeList,object.dateList,'chart8',cMetroOntimeDaily,ontimeColor);
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
				var cMetroOntimeDaily = '';
				var ontimeColor = '';
				if(last.metroQualityControlVO!=null){
					cMetroOntimeDaily = parseFloat(last.metroQualityControlVO.metroOntimeDaily);
					ontimeColor = getColorOverIsGood(cMetroOntimeDaily,last.metroOntimeDaily,'chart8Info');
				}
				//显示图表8数据
				$("#chart8_daily").html(last.metroOntimeMonth);
				$("#chart8_lastyear").html(last.lastyearQualityVo.metroOntimeMonth);			
				$("#chart8_year").html(last.metroOntimeYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					onTimeList[i] = object[i].metroOntimeMonth;
				}
				newChartLine(onTimeList,dateList,'chart8',cMetroOntimeDaily,ontimeColor);
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
			var cMetroOntimeDaily = '';
			var ontimeColor = '';
			if(last.metroQualityControlVO!=null){
				cMetroOntimeDaily = parseFloat(last.metroQualityControlVO.metroOntimeDaily);
				ontimeColor = getColorOverIsGood(cMetroOntimeDaily,last.metroOntimeDaily,'chart8Info');
			}
			if(object!=null){
				var onTimeList = new Array();
				var dateList = new Array();
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkYear);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					onTimeList[i] = object[i].metroOntimeMonth;
				}
				newChartLine(onTimeList,dateList,'chart8',cMetroOntimeDaily,ontimeColor);
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
					onworkColor = getColorOverIsGood(cMetroOnworkDaily,object.lastestQuality.metroOnworkDaily,"chart9Info");
				}
				$("#chart9_daily").html(object.lastestQuality.metroOntimeDaily);
				$("#chart9_lastyear").html(object.lastYearQuality.metroOntimeDaily);
				$("#chart9_month").html(object.lastestQuality.metroOntimeMonth);
				$("#chart9_year").html(object.lastestQuality.metroOntimeYear);
				newChartLine(object.onWorkList,object.dateList,'chart9',cMetroOnworkDaily,onworkColor);
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
				var cMetroOnworkDaily = '';
				var onworkColor = ''
				if(last.metroQualityControlVO!=null){
					cMetroOnworkDaily = parseFloat(last.metroQualityControlVO.metroOnworkDaily); 
					onworkColor = getColorOverIsGood(cMetroOnworkDaily,last.metroOnworkDaily,'chart9Info');
				}
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkMonth);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkMonth);
				$("#chart9_year").html(last.lastyearQualityVo.metroOnworkYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					onWorkList[i] = object[i].metroOnworkMonth;
				}
				newChartLine(onWorkList,dateList,'chart9',cMetroOnworkDaily,onworkColor);
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
				var cMetroOnworkDaily = '';
				var onworkColor = ''
				if(last.metroQualityControlVO!=null){
					cMetroOnworkDaily = parseFloat(last.metroQualityControlVO.metroOnworkDaily); 
					onworkColor = getColorOverIsGood(cMetroOnworkDaily,last.metroOnworkDaily,'chart9Info');
				}
				//显示图表9数据
				$("#chart9_daily").html(last.metroOnworkYear);
				$("#chart9_lastyear").html(last.lastyearQualityVo.metroOnworkYear);
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					onWorkList[i] = object[i].metroOnworkMonth;
				}
				newChartLine(onWorkList,dateList,'chart9',cMetroOnworkDaily,onworkColor);
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
			date : $("#datepickerInner12").val()
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
			date : $("#datepickerInner12").val()
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
				$("#chart12_all").html(object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateFourSection,object.allocateFourSection,object.allocateSevenSection,object.allocateEightSection];
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
	if(result>=max || result<=min){
		//$("#"+id+"").css("color","#FF3000").css("font-size",'18px').html("管控说明：危险");
		return "#FF3000";
	}else if((result>min && result<=min2) || (result>max2 && result<=max)){
		//$("#"+id+"").css("color","#FFCB16").css("font-size",'18px').html("管控说明：警告");
		return "#FFCB16";
	}
	//$("#"+id+"").css("color","#80B600").css("font-size",'18px').html("管控说明：正常");
	return '#80B600';
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
				
				var columnDaily = parseFloat(object.lastestProduction.metroColumnDaily);
				var distanceDaily = parseFloat(object.lastestProduction.metroDistanceDaily);
				var passengerCapacityDaily = parseFloat(object.lastestProduction.passengerCapacityDaily);
				var passengerTransferDaily = parseFloat(object.lastestProduction.passengerTransferDaily);
				var ticketIncomeDaily = parseFloat(object.lastestProduction.ticketIncomeDaily);
				var columnColor = '';
				var distanceColor = '';
				var passengerCapacityColor = '';
				var passengerTransferColor = '';
				var ticketIncomeColor = '';
				
				var cColumnDaily = '';
				var cDistanceDaily = '';
				var cPassengerCapacityDaily ='';
				var cPassengerTransferDaily ='';
				var cTicketIncomeDaily = ''; 
				
				if(object.metroProductionControlVO!=null){
					cColumnDaily = parseFloat(object.metroProductionControlVO.metroColumnDaily);
					columnColor = getColorOverIsGood(cColumnDaily,columnDaily,'chart1Info');
					
					cDistanceDaily = parseFloat(object.metroProductionControlVO.metroDistanceDaily);
					distanceColor = getColorOverIsGood(cDistanceDaily,distanceDaily,'chart2Info');
					
					cPassengerCapacityDaily = parseFloat(object.metroProductionControlVO.passengerCapacityDaily);
					passengerCapacityColor = getColorOverIsGood(cPassengerCapacityDaily,passengerCapacityDaily,'chart3Info');
					
					cPassengerTransferDaily = parseFloat(object.metroProductionControlVO.passengerTransferDaily);
					passengerTransferColor = getColorOverIsGood(cPassengerTransferDaily,passengerTransferDaily,'chart4Info');
					
					cTicketIncomeDaily = parseFloat(object.metroProductionControlVO.ticketIncomeDaily);
					ticketIncomeColor = getColorOverIsGood(cTicketIncomeDaily,ticketIncomeDaily,'chart5Info');
				}
			
			//显示图表1数据
			$("#chart1_daily").html(columnDaily);	
			$("#chart1_lastyear").html(object.lastYearProduction.metroColumnDaily);
			$("#chart1_month").html(object.lastestProduction.metroColumnMonth);
			$("#chart1_year").html(object.lastestProduction.metroColumnYear);
			$("#chart1_month").parent("li").show();
			$("#chart1_year").parent("li").show();
			//显示图表2数据
			$("#chart2_daily").html(object.lastestProduction.metroDistanceDaily);	
			$("#chart2_lastyear").html(object.lastYearProduction.metroDistanceDaily);
			$("#chart2_month").html(object.lastestProduction.metroDistanceMonth);
			$("#chart2_year").html(object.lastestProduction.metroDistanceYear);
			$("#chart2_month").parent("li").show();
			$("#chart2_year").parent("li").show();
			//显示图表3数据
			$("#chart3_daily").html(object.lastestProduction.passengerCapacityDaily);	
			$("#chart3_lastyear").html(object.lastYearProduction.passengerCapacityDaily);
			$("#chart3_month").html(object.lastestProduction.passengerCapacityMonth);
			$("#chart3_year").html(object.lastestProduction.passengerCapacityYear);
			$("#chart3_month").parent("li").show();
			$("#chart3_year").parent("li").show();
			//显示图表4数据
			$("#chart4_daily").html(object.lastestProduction.passengerTransferDaily);	
			$("#chart4_lastyear").html(object.lastYearProduction.passengerTransferDaily);
			$("#chart4_month").html(object.lastestProduction.passengerTransferMonth);
			$("#chart4_year").html(object.lastestProduction.passengerTransferYear);
			$("#chart4_month").parent("li").show();
			$("#chart4_year").parent("li").show();
			//显示图表5数据
			$("#chart5_daily").html(object.lastestProduction.ticketIncomeDaily);	
			$("#chart5_lastyear").html(object.lastYearProduction.ticketIncomeDaily);
			$("#chart5_month").html(object.lastestProduction.ticketIncomeMonth);
			$("#chart5_year").html(object.lastestProduction.ticketIncomeYear);
			$("#chart5_month").parent("li").show();
			$("#chart5_year").parent("li").show();
			//显示图表6数据
			var online = object.onlineSectionList;
			$("#chart6_all").html(online[0]+online[1]+online[2]+online[3]);	
			$("#chart6_4").html(online[0]);
			$("#chart6_6").html(online[1]);
			$("#chart6_7").html(online[2]);
			$("#chart6_8").html(online[3]);
			//显示图表7数据
			var backup = object.backupSectionList;
			$("#chart7_all").html(backup[0]+backup[1]+backup[2]+backup[3]);	
			$("#chart7_4").html(backup[0]);
			$("#chart7_6").html(backup[1]);
			$("#chart7_7").html(backup[2]);
			$("#chart7_8").html(backup[3]);
			newChartColumn(object.columnList,object.dateList,'chart1',cColumnDaily,columnColor);
			newChartColumn(object.distanceList,object.dateList,'chart2',cDistanceDaily,distanceColor);
			newChartColumn(object.passengerCapacityList,object.dateList,'chart3',cPassengerCapacityDaily,passengerCapacityColor);
			newChartColumn(object.passengerTransferList,object.dateList,'chart4',cPassengerTransferDaily,passengerTransferColor);
			newChartColumn(object.ticketIncomeList,object.dateList,'chart5',cTicketIncomeDaily,ticketIncomeColor);
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
			if(object==null){
				alert("无相关数据，请重新选择日期!");
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
					columnColor = getColorOverIsGood(cColumnMonth,columnMonth,"chart1Info");
					
					cDistanceMonth = parseFloat(object[length-1].metroProductionControlVO.metroDistanceMonth);
					distanceColor = getColorOverIsGood(cDistanceMonth,distanceMonth,"chart2Info");
					
					cPassengerCapacityMonth = parseFloat(object[length-1].metroProductionControlVO.passengerCapacityMonth);
					passengerCapacityColor = getColorOverIsGood(cPassengerCapacityMonth,passengerCapacityMonth,"chart3Info");
					cPassengerTransferMonth = parseFloat(object[length-1].metroProductionControlVO.passengerTransferMonth);
					passengerTransferColor = getColorOverIsGood(cPassengerTransferMonth,passengerTransferMonth,"chart4Info");
					
					cTicketIncomeMonth = parseFloat(object[length-1].metroProductionControlVO.ticketIncomeMonth);
					ticketIncomeColor = getColorOverIsGood(cTicketIncomeMonth,ticketIncomeMonth,"chart5Info");
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
				onlineSectionList[0]=object[length-1].onlineFourSection;
				onlineSectionList[1]=object[length-1].onlineSixSection;
				onlineSectionList[2]=object[length-1].onlineSevenSection;
				onlineSectionList[3]=object[length-1].onlineEightSection;
				$("#chart6_all").html(object[length-1].onlineFourSection+object[length-1].onlineSixSection+object[length-1].onlineSevenSection+object[length-1].onlineEightSection);	
				$("#chart6_4").html(object[length-1].onlineFourSection);
				$("#chart6_6").html(object[length-1].onlineSixSection);
				$("#chart6_7").html(object[length-1].onlineSevenSection);
				$("#chart6_8").html(object[length-1].onlineEightSection);
				//显示图表7数据
				var backupSectionList = new Array();
				backupSectionList[0]=object[length-1].backupFourSection;
				backupSectionList[1]=object[length-1].backupSixSection;
				backupSectionList[2]=object[length-1].backupSevenSection;
				backupSectionList[3]=object[length-1].backupEightSection;
				$("#chart7_all").html(object[length-1].backupFourSection+object[length-1].backupSixSection+object[length-1].backupSevenSection+object[length-1].backupEightSection);	
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
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					columnList[i] = object[i].metroColumnMonth;
					distanceList[i] = object[i].metroDistanceMonth;
					passengerCapacityList[i] = object[i].passengerCapacityMonth;
					passengerTransferList[i] = object[i].passengerTransferMonth;
					ticketIncomeList[i] = object[i].ticketIncomeMonth;
				}
				newChartColumn(columnList,dateList,'chart1',cColumnMonth,columnColor);
				newChartColumn(distanceList,dateList,'chart2',cDistanceMonth,distanceColor);
				newChartColumn(passengerCapacityList,dateList,'chart3',cPassengerCapacityMonth,passengerCapacityColor);
				newChartColumn(passengerTransferList,dateList,'chart4',cPassengerTransferMonth,passengerTransferColor);
				newChartColumn(ticketIncomeList,dateList,'chart5',cTicketIncomeMonth,ticketIncomeColor);
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
			if(object==null){
				alert("无相关数据，请重新选择日期!");
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
					columnColor = getColorOverIsGood(cColumnYear,columnYear,"chart1Info");
					cDistanceYear = parseFloat(object[length-1].metroProductionControlVO.metroDistanceYear);
					distanceColor = getColorOverIsGood(cDistanceYear,distanceYear,"chart2Info");
					cPassengerCapacityYear = parseFloat(object[length-1].metroProductionControlVO.passengerCapacityYear);
					passengerCapacityColor = getColorOverIsGood(cPassengerCapacityYear,passengerCapacityYear,"chart3Info");
					cPassengerTransferYear = parseFloat(object[length-1].metroProductionControlVO.passengerTransferYear);
					passengerTransferColor = getColorOverIsGood(cPassengerTransferYear,passengerTransferYear,"chart4Info");
					cTicketIncomeYear = parseFloat(object[length-1].metroProductionControlVO.ticketIncomeYear);
					ticketIncomeColor = getColorOverIsGood(cTicketIncomeYear,ticketIncomeYear,"chart5Info");
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
				onlineSectionList[0]=object[length-1].onlineFourSection;
				onlineSectionList[1]=object[length-1].onlineSixSection;
				onlineSectionList[2]=object[length-1].onlineSevenSection;
				onlineSectionList[3]=object[length-1].onlineEightSection;
				$("#chart6_all").html(object[length-1].onlineFourSection+object[length-1].onlineSixSection+object[length-1].onlineSevenSection+object[length-1].onlineEightSection);	
				$("#chart6_4").html(object[length-1].onlineFourSection);
				$("#chart6_6").html(object[length-1].onlineSixSection);
				$("#chart6_7").html(object[length-1].onlineSevenSection);
				$("#chart6_8").html(object[length-1].onlineEightSection);
				//显示图表7数据
				var backupSectionList = new Array();
				backupSectionList[0]=object[length-1].backupFourSection;
				backupSectionList[1]=object[length-1].backupSixSection;
				backupSectionList[2]=object[length-1].backupSevenSection;
				backupSectionList[3]=object[length-1].backupEightSection;
				$("#chart7_all").html(object[length-1].backupFourSection+object[length-1].backupSixSection+object[length-1].backupSevenSection+object[length-1].backupEightSection);	
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
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,4);
					columnList[i] = object[i].metroColumnYear;
					distanceList[i] = object[i].metroDistanceYear;
					passengerCapacityList[i] = object[i].passengerCapacityYear;
					passengerTransferList[i] = object[i].passengerTransferYear;
					ticketIncomeList[i] = object[i].ticketIncomeYear;
				}
				newChartColumn(columnList,dateList,'chart1',cColumnYear,columnColor);
				newChartColumn(distanceList,dateList,'chart2',cDistanceYear,distanceColor);
				newChartColumn(passengerCapacityList,dateList,'chart3',cPassengerCapacityYear,passengerCapacityColor);
				newChartColumn(passengerTransferList,dateList,'chart4',cPassengerTransferYear,passengerTransferColor);
				newChartColumn(ticketIncomeList,dateList,'chart5',cTicketIncomeYear,ticketIncomeColor);
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
					ontimeColor = getColorOverIsGood(cMetroOntimeDaily,object.lastestQuality.metroOntimeDaily,"chart8Info");
					cMetroOnworkDaily = parseFloat(object.metroQualityControlVO.metroOnworkDaily); 
					onworkColor = getColorOverIsGood(cMetroOnworkDaily,object.lastestQuality.metroOnworkDaily,"chart9Info");
				}
				//显示图表8数据
				$("#chart8_daily").html(object.lastestQuality.metroOntimeDaily);
				$("#chart8_lastyear").html(object.lastYearQuality.metroOntimeDaily);
				$("#chart8_month").html(object.lastestQuality.metroOntimeMonth);
				$("#chart8_year").html(object.lastestQuality.metroOntimeYear);
				$("#chart8_month").parent("li").show();
				$("#chart8_year").parent("li").show();
				//显示图表9数据
				$("#chart9_daily").html(object.lastestQuality.metroOnworkDaily);
				$("#chart9_lastyear").html(object.lastYearQuality.metroOnworkDaily);
				$("#chart9_month").html(object.lastestQuality.metroOnworkMonth);
				$("#chart9_year").html(object.lastestQuality.metroOnworkYear);
				$("#chart9_month").parent("li").show();
				$("#chart9_year").parent("li").show();
				newChartLine(object.onTimeList,object.dateList,'chart8',cMetroOntimeDaily,ontimeColor);
				newChartLine(object.onWorkList,object.dateList,'chart9',cMetroOnworkDaily,onworkColor);
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
			alert("无相关数据，请重新选择日期！")
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
				if(last.metroQualityControlVO!=null){
					cMetroOntimeDaily = parseFloat(last.metroQualityControlVO.metroOntimeMonth);
					ontimeColor = getColorOverIsGood(cMetroOntimeDaily,last.metroOntimeMonth,'chart8Info');
					cMetroOnworkDaily = parseFloat(last.metroQualityControlVO.metroOnworkMonth); 
					onworkColor = getColorOverIsGood(cMetroOnworkDaily,last.metroOnworkMonth,'chart9Info');
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
				for(var i=0; i<length; i++){
					dateList[i] = object[i].indicatorDate.substr(0,7);
					onTimeList[i] = object[i].metroOntimeMonth;
					onWorkList[i] = object[i].metroOnworkMonth;
				}
				newChartLine(onTimeList,dateList,'chart8',cMetroOntimeDaily,ontimeColor);
				newChartLine(onWorkList,dateList,'chart9',cMetroOnworkDaily,onworkColor);
			}else{
				alert("无相关数据,请重新选择日期查询");
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
				if(last.metroQualityControlVO!=null){
					cMetroOntimeDaily = parseFloat(last.metroQualityControlVO.metroOntimeYear);
					ontimeColor = getColorOverIsGood(cMetroOntimeDaily,last.metroOntimeYear,'chart8Info');
					cMetroOnworkDaily = parseFloat(last.metroQualityControlVO.metroOnworkYear); 
					onworkColor = getColorOverIsGood(cMetroOnworkDaily,last.metroOnworkYear,'chart9Info');
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
					onTimeList[i] = object[i].metroOntimeMonth;
					onWorkList[i] = object[i].metroOnworkMonth;
				}
				newChartLine(onTimeList,dateList,'chart8',cMetroOntimeDaily,ontimeColor);
				newChartLine(onWorkList,dateList,'chart9',cMetroOnworkDaily,onworkColor);
			}else{
				alert("无相关数据,请重新选择日期查询");
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
				alert("无相关数据，请重新选择日期！");
			}else{
				//显示图表10数据
				$("#chart10_all").html(object.lineDistance);
				//显示图表11数据
				$("#chart11_all").html(object.stationCount);
				//显示图表12数据
				$("#chart12_all").html(object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateFourSection,object.allocateFourSection,object.allocateSevenSection,object.allocateEightSection];
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
				$("#chart12_all").html(object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateFourSection,object.allocateFourSection,object.allocateSevenSection,object.allocateEightSection];
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
				$("#chart12_all").html(object.allocateFourSection+object.allocateSixSection+object.allocateSevenSection+object.allocateEightSection);
				$("#chart12_4").html(object.allocateFourSection);
				$("#chart12_6").html(object.allocateSixSection);
				$("#chart12_7").html(object.allocateSevenSection);
				$("#chart12_8").html(object.allocateEightSection);
				var valueList = [object.allocateFourSection,object.allocateFourSection,object.allocateSevenSection,object.allocateEightSection];
				newChartPie(valueList,"chart12");			
			}else{
				alert("无相关数据，请重新选择日期!");
			}
		}
	});
}


 /**
  * 画一个柱状图
  * @param valueList 图表的值
  * @param dateList	日期值
  * @param renderTo	要显示位置的id
  * @param controlValue 管控值
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
 	
 	if(controlValue!=''){
 		var controlData = new Array();
 		var realData = new Array();
 		var innerData;
 		for(var i=0; i<valueList.length; i++){
 			controlData.push(controlValue);
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
	 			data:controlData,
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
 	var  chartOption ={
 		chart: {
 			renderTo: renderTo,
 			plotBackgroundColor: null,
 			plotBorderWidth: null,
 			plotShadow: false,
 			height: h,
 	        borderWidth:0,
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
                return this.point.name +
                     '<br>值:'+ this.y +'';
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
 				{	name :"4节编组",
 					y : valueList[0],
 					color : '#79BB25'
 				},
 				{	name :"6节编组",
 					y : valueList[1],
 					color : '#FFC002'
 				},
 				{	name :"7节编组",
 					y : valueList[2],
 					color : '#4572A7'
 				},
 				{	name :"8节编组",
 					y : valueList[3],
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
  * @return
  */
 function newChartLine(valueList,dateList,renderTo,controlValue,colorA){
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
	if(controlValue!=''){
 		var controlData = new Array();
 		var realData = new Array();
 		var innerData;
 		for(var i=0; i<valueList.length; i++){
 			controlData.push(controlValue);
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
	 			data:controlData,
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











