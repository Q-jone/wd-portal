$(function(){
	getAssetInfo("全网","",
				"QUANTITY_TYPE1,QUANTITY_TYPE2,QUANTITY_TYPE5,QUANTITY_TYPE4",
				"线路,房屋建筑,通信,车辆",
				"VALUE_TYPE1,VALUE_TYPE2,VALUE_TYPE5,VALUE_TYPE4",
				"线路,房屋建筑,通信,车辆",
				"1",
				"assetCount",
				"assetSum"
	);
	getInventoryInfo("09号线","",
			"OWNER_COMPANY,LINE,NOW_VALUE,ALL_VALUE",
			"权属单位,线路,总值,现值",
			"VALUE_TYPE1,VALUE_TYPE2,VALUE_TYPE5,VALUE_TYPE4",
			"线路,房屋建筑,通信,车辆",
			"3",
			"ownerSum"
	);

	columnHighChart("","","","","");
	$(".con_2").css("left",$(".con").offset().left);
	$(window).resize(function(){
		$(".con_2").css("left",$(".con").offset().left);
	})
	
	$(".lineList").find("a").each(function(){
		$(this).click(function(){
			getAssetInfo($.trim($(this).text()),"",
						"QUANTITY_TYPE1,QUANTITY_TYPE2,QUANTITY_TYPE5,QUANTITY_TYPE4",
						"线路,房屋建筑,通信,车辆",
						"VALUE_TYPE1,VALUE_TYPE2,VALUE_TYPE5,VALUE_TYPE4",
						"线路,房屋建筑,通信,车辆",
						"1",
						"assetCount",
						"assetSum"
			);
			getInventoryInfo($.trim($(this).text()),"",
						"OWNER_COMPANY,LINE,NOW_VALUE,ALL_VALUE",
						"权属单位,线路,总值,现值",
						"VALUE_TYPE1,VALUE_TYPE2,VALUE_TYPE5,VALUE_TYPE4",
						"线路,房屋建筑,通信,车辆",
						"3",
						"ownerSum"
			);
		})
	})
	
	//findStationByLine("二号线");
	
	findAllAssetCount();
	$("#show_today").html(getToday());
})

$(function(){
	var saveStatus = false;
	$("#assetId" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			$.ajax({
				url: "/portal/assetInfo/findAssetByAssetId.action?random="+Math.random(),
				dataType: "json",
				data: {
					"assetId" : request.term.replace(/(^\s*)|(\s*$)/g,'')
				},
				success: function( data ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.assetId,
							value: item.id,
							assetName:item.assetName
						}
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#assetId").val(ui.item.label);
			$("#assetName").val(ui.item.assetName);
			$("#paramId").val(ui.item.value);
			saveStatus = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
			//$( "#assetName" ).val("");
				//$("#paramId").val("");
				//return false;
		},
		change : function(){
			//if(!saveStatus){
				$("#assetName").val("");
				$("#paramId").val("");
			//}
		}
	});
});

function getToday(){
	var t_date = new Date();
	var year = t_date.getFullYear();
	var month = t_date.getMonth()+1;
	var day = t_date.getDate();
	return year+"-"+month+"-"+day;
}

function searchForAsset(){
	var paramId = $("#paramId").val();
	if(paramId!=""){
		window.open("http://10.1.48.40/stptm/asset/showView.action?paramId="+paramId);
	}else{
		alert("请输入正确的资产编号！");
	}
}

function moreSearch(){
	window.open ( "http://10.1.48.40/stptm/asset/showInventory.action?showOrHide=show");
}

function findAllAssetCount(){
	$.ajax({
		type : 'post',
		url : '/portal/assetInfo/findAllAssetCount.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null){
				//alert(object);
				$("#allAssetSpan").html(object+"件");
			}			
		}
	})
}



function findStationByLine(line){
	$.ajax({
		type : 'post',
		url : '/portal/assetInfo/findStationByLine.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line :	line
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object!=null&&object.length>0){
				//alert(object.length);
				
			}			
		}
	})
}

function getAssetInfo(line,station,counttype,counttypeCn,sumtype,sumtypeCn,flag,id1,id2){
	$.ajax({
		type : 'post',
		url : '/portal/assetInfo/getAssetInfo.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line 		:	line,
			station 	:	station,
			counttype	:	counttype,
			sumtype		:	sumtype,
			flag		:	flag,
			counttypeCn :	counttypeCn,
			sumtypeCn 	:	sumtypeCn
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			if(object.count!=null){
				pieHighChart(object.counttypeCn,object.count,id1);
				pieHighChart(object.sumtypeCn,object.sum,id2);
			}			
		}
	})
}


function getInventoryInfo(line,owner,specialtype,specialtypeCn,sumtype,sumtypeCn,flag,id){
	$.ajax({
		type : 'post',
		url : '/portal/assetInfo/getInventoryInfo.action?random='+Math.random(),
		dataType:'json',
		cache : false,
		data:{
			line 		:	line,
			owner 		:	owner,
			specialtype	:	specialtype,
			sumtype		:	sumtype,
			flag		:	flag,
			specialtypeCn :	specialtypeCn,
			sumtypeCn 	:	sumtypeCn
		},
		error:function(){
			//alert("系统连接失败，请稍后再试！")
		},
		success:function(object){
			$("#ownerSumTable").find("tr:gt(0)").remove();
			if(object.length > 0){
				for(var i=0;i<object.length;i++){
					var temp = "<tr>" +
							"<td>"+object[i].special[0]+"</td>" +
							"<td>"+object[i].special[1]+"</td>" +
							"<td>"+object[i].special[3]+"</td>" +
							"<td>"+object[i].special[2]+"</td>" +
							"<td><div id='"+id+i+"'></div></td>" +
							"</tr>";
					$("#ownerSumTable").append(temp);
					pieSparkLine(object[i].value,id+i,object[i].special[0]);
				}
			}
		}
	})
}

function pieSparkLine(yaxis,index,owner){
	//alert(index);
	var com = 0;
	var data = '';
	if(yaxis.length>0){
		for(var i=0; i<yaxis.length; i++){
			data += yaxis[i];
			data += ",";
			com += yaxis[i];
			}
		data = data.substr(0,data.length-1);
	}else{
		data = "0";
	}
	//alert(data);
	if(data=="0" || com==0){
		$("#"+index).html("");
	}
	else{
		$("#"+index).html(data);
		$("#"+index).sparkline('html',
				{type:'pie',
					sliceColors:['#63B8FF','#7FFF00','#EEC900','#CD69C9'],
					height: '45px',
					offset:'-90',
					tooltipFormat: '  <span style="display:inline;color: {{color}}">&#9679;</span> 条目数：{{value}}',
					tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+owner
				}); 
	}
}
function columnHighChart(description,xaxis,yaxis,controlaxis,id){
	var  chartOption ={
		chart: {
	    	renderTo: 'assetColumn',
	    	height: 180,
	 		borderWidth:0,
	 		width:260
		},
		credits:{					
 			enabled:false
 		},
 		legend: {					
 					enabled: false
 		},
 				
		title: {
		    text: ''
		},
		xAxis: {
		    categories: ['车辆','通信','车站','轨道','XX','YY','ZZ','WW'],
		    minPadding: 0.05,
 			maxPadding: 0.05,
 			labels: {
                 style: {
                     fontSize:'7px'
                 }
             }
		},
		yAxis:{
			title: {
		    	text: null
			}, 
			labels: {
                 style: {
                     fontSize:'7px'
                 }
             },
			max:110,
			tickInterval: 25
		},
		plotOptions: {
			series: {
				pointWidth: 12
			}
		},
		tooltip: {
		    formatter: function() {
		        var s;
		        if (this.point.name) { // the pie chart
		            s = ''+
		                this.point.name +': '+ this.y +' fruits';
		        } else {
		            s = ''+
		                this.x  +': '+ this.y;
		        }
		        return s;
		    }
		},
		credits:{ 
		enabled:false
		},
		legend: { 
		    enabled: false
		  },
		series: [{
		    type: 'column',
		    name: '条目',
		  data: [{y:98},
		          {y:80},
		          {y:70},
		          {y:95},
		         {y:68},
		         {y:89},
		          {y:49},
		          {y:79}
		          ]
		},
		 {
		    type: 'line',
		    name: '控制线',
		    data: [100,100,100,100,100,100,100,100],
		    color:'#222',
		    marker: {
			 radius:3    
           }
		}]
	};
	new Highcharts.Chart(chartOption); 
}

function pieHighChart(description,yaxis,id){
	//initList(xaxis,description.length);
	initList(yaxis,description.length);
	var data = new Array();
	initData(data,description,yaxis,description.length);
 	var  chartOption ={
 		chart: {
 			renderTo: id,
 			plotBackgroundColor: null,
 			plotBorderWidth: null,
 			plotShadow: false,
 			borderWidth:0,
 			height: 170,
 			width:210,
 			backgroundColor: 'rgba(255, 255, 255, 0)', //背景透明
 			plotBorderColor : null
 		},
 		title: {
 			text: ''
 		},
 		credits: {
 			enabled:false
 		},
 		tooltip: {
              formatter: function() {
                return this.point.name + '<br>值:'+ this.y ;
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
					distance: -30,
					style: {		
                    fontSize:'10px'
                 },
					formatter: function() {
						//return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
						return this.point.name+'：'+this.point.y;	//this.percentage 百分比
					}
				}
 			}
 		},
 		series: [{
 			type: 'pie',
 			name: '值',
 			data: data
 		}]
 	};
 	new Highcharts.Chart(chartOption); 
}

function initList(list,size){
	if(list == null || list == "" || list.length == 0){
		list = new Array();
		for(var i=0;i<size;i++){
			list.push(0);
		}
	}
}

function initData(data,description,yaxis,size){
	var color = ['#63B8FF','#7FFF00','#EEC900','#CD69C9'];
	for(var i=0;i<size;i++){
		data.push({name:description[i],y:yaxis[i],color:color[i]});
	}
}