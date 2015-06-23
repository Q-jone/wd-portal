function toShow(percent,obj){
	var red = 'Red_bg';
	var green = 'Green_bg';
	var up = '<img src="images/up_ico.png" class="fL" />'+percent;
	var down = '<img src="images/down_ico.png" class="fL" />'+percent;
	if(percent.indexOf("-")>-1){
		obj.html(down.replace("-",""));
		obj.addClass(green);
	}else{
		obj.html(up.replace("-",""));
		obj.addClass(red);
	}
}


/**
 * 显示基本信息
 * 
 * @return
 */
function showMscpInfo(url) {
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		async : false,
		error : function() {
			//alert("error");
		},
		success : function(data) {
			if (data != null) {
				$('#data1').html(data.portalVisit);$('#data2').html(data.platformUserVisit);$('#data3').html(data.registerSupplyCount);
				$('#data4').html(data.qualifedSupplyCount);$('#data5').html(data.totalComplainCount);$('#data6').html(data.expertsCount);
				$('#data7').html(data.singleNoticeCount);$('#data8').html(data.bidNoticeCount);$('#data9').html(data.getBidNoticeCount);
				$('#data10').html(data.competeNoticeCount);$('#data11').html(data.competeResultPrice);
				$('#data13').html(data.purchaseCount);
				
				toShow(data.portalVisitY,$("#data1i"));
				toShow(data.platformUserVisitY,$("#data2i"));
				toShow(data.singleNoticeCountY,$("#data7i"))
				toShow(data.bidNoticeCountY,$("#data8i"));
				toShow(data.getBidNoticeCountY,$("#data9i"));
				toShow(data.competeNoticeCountY,$("#data10i"));
				toShow(data.competeResultPriceY,$("#data11i"));
			
				if(data.tradePrice!=null && data.tradePrice!=''){
					$('#data12').html(parseFloat(data.tradePrice).toFixed(0));
				}
				if(data.getBidNoticeMoney!=null && data.getBidNoticeMoney!=''){
					$('#data14').html(parseFloat(data.getBidNoticeMoney).toFixed(0));
				}
			}
		}
	});
}


function showOrganizationTrader(url,datatype,year,month,obj){
	$.ajax( {
		type : 'post',
		url : url,
		data :{
			type:datatype,
			year:year,
			month:month,
			random:Math.random()
		},
		dataType : 'json',
		async : false,
		error : function() {
			//alert("error");
		},
		success : function(data) {
			if (data != null) {
				var html = '';
				var even = '';
				for(var i=0; i<data.length; i++){
					if((i+1)%2==0){
						even = "even";
					}else{
						even = "";
					}
					var organName = data[i].organName;
					if(organName.length>5){
						//organName = organName.substring(0,5)+'...';
					}
					var tradesum = 0;
					if(data[i].tradeSum!=null && data[i].tradeSum!='' && data[i].tradeSum!='undefined'){
						tradesum = parseFloat(data[i].tradeSum/10000).toFixed(4);
					}
					html += '<tr class='+even+'><td>'+(i+1)+'</td><td style="text-align:left;">'+simpleName(organName)+'</td><td style="text-align:right;padding-right:20px;">'+data[i].tradeCount+'</td><td style="text-align:right;padding-right:10px;">'+tradesum+'</td></tr>';
				}
				if(datatype=='thisMonth'){
					$("#moreOrganizationTraderDataTbody").html(html);
					html += '<tr><td colspan="4" style="text-align:right;"><a id="showMoreOrganizationTrader" href="#moreOrganizationTraderData" style="margin-right:10px;">更多>></a></td></tr>';
					$('#organizationVisit').html(html);
					$('#organizationVisit').find('tr:even').attr('style','background-color:#d2e6ff');
					$("#moreOrganizationTraderDataTbody").find('tr:even').attr('style','background-color:#d2e6ff');
					$('#showMoreOrganizationTrader').facybox({
				        // noAutoload: true
				    });
					
				}else if(datatype=='switchMonth'){
					$(obj).siblings("table").children("tbody").html(html);
					$(obj).siblings("table").children("tbody").find('tr:even').attr('style','background-color:#d2e6ff');
				}
				
			}
		}
	});
}

function showSupplyBid(url,datatype,year,month,obj){
	$.ajax( {
		type : 'post',
		url : url,
		data :{
			type:datatype,
			year:year,
			month:month,
			random:Math.random()
		},
		dataType : 'json',
		async : false,
		error : function() {
			//alert("error");
		},
		success : function(data) {
			if (data != null) {
				var html = '';
				var even = '';
				for(var i=0; i<data.length; i++){
					if((i+1)%2==0){
						even = "even";
					}else{
						even = "";
					}
					var supplyName = data[i].supplyName;
					if(supplyName.length>5){
						supplyName = supplyName.substring(0,5)+'...';
					}
					html += '<tr class='+even+'><td>'+(i+1)+'</td><td style="text-align:left;" title="'+data[i].supplyName+'">'+supplyName+'</td><td style="text-align:right;padding-right:20px;">'+data[i].bidCount+'</td><td style="text-align:right;padding-right:10px;">'+parseFloat(data[i].bidSum/10000).toFixed(4)+'</td></tr>';
				}
				if(datatype=='thisMonth'){
					$("#moreSupplyBidDataTbody").html(html);
					html += '<tr><td colspan="4" style="text-align:right;"><a id="showMoreSupplyBid" href="#moreSupplyBidData" style="margin-right:10px;">更多>></a></td></tr>';
					$('#supplyBid').html(html);
					$('#supplyBid').find('tr:even').attr('style','background-color:#d2e6ff');
					$("#moreSupplyBidDataTbody").find('tr:even').attr('style','background-color:#d2e6ff');
					$('#showMoreSupplyBid').facybox({
				        // noAutoload: true
				    });
					
				}else if(datatype=='switchMonth'){
					$(obj).siblings("table").children("tbody").html(html);
					$(obj).siblings("table").children("tbody").find('tr:even').attr('style','background-color:#d2e6ff');
				}
				
			}
		}
	});
}

function findMscpExpertStat(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		async : false,
		error : function() {
			//alert("error");
		},
		success : function(data) {
			if (data != null) {
				var html = '';
				var even = '';
				for(var i=0; i<data.length; i++){
					if((i+1)%2==0){
						even = "even";
					}else{
						even = "";
					}
					html += '<tr class='+even+'><td style="text-align:left;padding-left: 20px;">'+data[i].typeName+'</td><td style="text-align:right;padding-right: 20px;">'+data[i].countNum+'</td></tr>';
				}
				$('#expertStat').html(html);
				$('#expertStat').find('tr:even').attr('style','background-color:#d2e6ff');
			}
		}
	});
}


function showPortalVisit(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for (var i = object.length-1; i >=0; i--) {
					categories.push(object[i].statDate);
					var se = [ object[i].statDate, parseInt(object[i].visitCount)];
					series.push(se);
				}
			}
			$('#portalVisit').highcharts({
			    chart: {
			        type: 'area',
			        height: 250
			    },
			    title: {
			        text: '月度门户访问量统计',
			        style: {
				        	color: '#000',
				        	fontSize: '14px',
				        	fontWeight: 'bold',
				        	fontFamily: "Microsoft Yahei"
				        	}
			    },
			    colors: ['#63B8FF'],
			    xAxis: {
			        categories: categories
			    },
			    yAxis:{
			    	title:{
			    		text:''
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">访问量： </td>' +
			            '<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
			        footerFormat: '</table>',
			        shared: true,
			        useHTML: true
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        },
			        series: {
		                fillOpacity: 0.5
		            }
			    },
			    series: [{
			        data:series
			    }]
			});
			
		}
	});
}

function showPlatformVisit(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = object.length-1; i >=0; i--) {
					categories.push(object[i].statDate);
					var se = [ object[i].statDate, parseInt(object[i].visitCount)];
					series.push(se);
				}
			}
			$('#platformVisit').highcharts({
			    chart: {
			        type: 'area',
			        height:250
			    },
			    title: {
			        text: '月度平台访问量统计',
			        style: {
			        	color: '#000',
			        	fontSize: '14px',
			        	fontWeight: 'bold',
			        	fontFamily: "Microsoft Yahei"
			        	}
			    },
			    colors:['#BCEE68'],
			    xAxis: {
			        categories: categories
			    },
			    yAxis:{
			    	title:{
			    		text:''
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">访问量： </td>' +
			            '<td style="padding:0"><b>{point.y} </b></td></tr>',
			        footerFormat: '</table>',
			        shared: true,
			        useHTML: true
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        },
			        series: {
		                fillOpacity: 0.5
		            }
			    },
			    series: [{
			        data:series
			    }]
			});
			
		}
	});
}


function showMonthNotice(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				
				var bidNoticeNum=[];
				var bidResultNum=[];
				var ifbNoticeNum=[];
				var wabSourceNum=[];
				var sourceNoticeNum=[];
				for ( var i = object.length-1; i >=0; i--) {
					categories.push(object[i].statDate);
					bidNoticeNum.push(parseInt(object[i].bidNoticeNum));
					bidResultNum.push(parseInt(object[i].bidResultNum));
					ifbNoticeNum.push(parseInt(object[i].ifbNoticeNum));
					wabSourceNum.push(parseInt(object[i].wabNoticeNum));
					sourceNoticeNum.push(parseInt(object[i].sourceNoticeNum));
				}
				
			}
			var betweenPoints = new Highcharts.BetweenPoints();
			$('#monthNotice').highcharts({
			    chart: {
			        type: 'column'
			    },
			    title: {
			        text: '月度公告分类数量统计',
			        style: {
			        	color: '#000',
			        	fontSize: '14px',
			        	fontWeight: 'bold',
			        	fontFamily: "Microsoft Yahei"
			        	}
			    },
			    xAxis: {
			        categories: categories
			    },
			    yAxis:{
			    	title:{
			    		text:''
			    	}
			    },
			    legend:{
			    	enabled:true,
			    	layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'bottom',
	                floating: false,
	                borderWidth: 1,
	                backgroundColor: '#FFFFFF',
	                shadow: true,
	               
	                itemMarginTop: 6,
	                itemMarginBottom: 6
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0,
			            point: {
		                    events: {
		                        click: function(event) {
		                            betweenPoints.between(this); 
		                        }
		                    }
		                }
			        }
			    },
			    series: [{
			        name:'竞价公告数量',
			        data:bidNoticeNum,
			        color:'#63B8FF'
			    },{
			        name:'竞价结果数量',
			        data:bidResultNum,
			        color:'#2F4F4F'
			    },{
			        name:'招标公告数量',
			        data:ifbNoticeNum,
			        color:'#BCEE68'
			    },{
			        name:'中标公告数量',
			        data:wabSourceNum,
			        color:'#CD0000'
			    },{
			        name:'单一来源公告数量',
			        data:sourceNoticeNum,
			        color:'#00F5FF'
			    }]
			});
			
		}
	});
}

function findMonthBidByMonth(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			if (object != null && object.length > 0) {
				$('#monthBidStat').highcharts({
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '月度分类交易金额统计',
				        style: {
				        	color: '#000',
				        	fontSize: '14px',
				        	fontWeight: 'bold',
				        	fontFamily: "Microsoft Yahei"
				        	}
				    },
				    xAxis: {
				        categories: object[0].reverse()
				    },
				    yAxis:{
				    	title:{
				    		text:''
				    	}
				    },
				    legend:{
				    	enabled:true,
				    	layout: 'vertical',
		                align: 'right',
		                verticalAlign: 'bottom',
		                floating: false,
		                borderWidth: 1,
		                backgroundColor: '#FFFFFF',
		                shadow: true,
		               
		                itemMarginTop: 6,
		                itemMarginBottom: 6
				    },
				    credits:{
				    	enabled:false
				    },
				    tooltip: {
				        
				    },
				    series: [{
				        name:'中标公告金额',
				        data:object[1].reverse(),
				        color:'#63B8FF'
				    },{
				        name:'网上交易金额',
				        data:object[2].reverse(),
				        color:'#BCEE68'
				    }]
				});
			}
		}
	});
}


function showMonthBid(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var bidNum1 = [];var bidNum2=[];var bidNum3=[];
			var bidSum1 = [];var bidSum2=[];var bidSum3=[];
			var monthBidNum1 = 0;var monthBidNum2=0;var monthBidNum3=0;
			var monthBidSum1 = 0;var monthBidSum2=0;var monthBidSum3=0;
			if (object != null && object.xs !=null && object.ys != null) {
				$.each(object.xs,function(index,value){
				   categories.push(value);
				});
				
				var ysCount = (object.ys).length;
				$.each(object.ys,function(index,value){
					for(var i=0;i<value.length;i++){
						if(value[i].bidType=='综合竞价'){
				        	bidNum1.push(parseInt(value[i].bidNum));
				        	bidSum1.push(parseFloat(parseFloat(value[i].bidSum).toFixed(2)));
				        	if(index==ysCount-1){
				        		monthBidNum1 += parseInt(value[i].bidNum);
				        		monthBidSum1 += parseFloat(parseFloat(value[i].bidSum).toFixed(2));
				        	}
				        }else if(value[i].bidType=='核价竞价'){
				        	bidNum2.push(parseInt(value[i].bidNum));
				        	bidSum2.push(parseFloat(parseFloat(value[i].bidSum).toFixed(2)));
				        	if(index==ysCount-1){
				        		monthBidNum2 += parseInt(value[i].bidNum);
				        		monthBidSum2 += parseFloat(parseFloat(value[i].bidSum).toFixed(2));
				        	}
				        }else if(value[i].bidType=='公开竞价'){
				        	bidNum3.push(parseInt(value[i].bidNum));
				        	bidSum3.push(parseFloat(parseFloat(value[i].bidSum).toFixed(2)));
				        	if(index==ysCount-1){
				        		monthBidNum3 += parseInt(value[i].bidNum);
				        		monthBidSum3 += parseFloat(parseFloat(value[i].bidSum).toFixed(2));
				        	}
				        }
					}
					
				});
				
			}
			if(monthBidNum1==0&&monthBidNum2==0&&monthBidNum3==0){
				$("#totalCountPie").css("textAlign","center").css("lineHeight",$("#totalCountPie").height()+"px").
				html("<font style='color:#000;font-weight:bold;font-size:14px;font-family:Microsoft Yahei;'>当月竞价单数均为0</font>");
			}else{
			$("#totalCountPie").highcharts({
				chart: {
		 			plotBackgroundColor: null,
		 			plotBorderWidth: null,
		 			plotShadow: false,
		 	        borderWidth:0,
		 	        height: 250
		 		},
		 		 title: {
				        text: '竞价总单数',
				        style: {
				        	color: '#000',
				        	fontSize: '14px',
				        	fontWeight: 'bold',
				        	fontFamily: "Microsoft Yahei"
				        	}
				    },
		 		credits: {
		 			enabled:false
		 		},
		 		tooltip: {
		              formatter: function() {
		                return this.point.name + '<br>'+ this.y +' 个';
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
								if (this.point.y > 0){
									return this.point.name;
								}
							}
						}
		 			}
		 		},
		 		series: [{
		 			type: 'pie',
		 			name: '值',
		 			data: [
		 			    {	name :'综合竞价',
							y : monthBidNum1,
							color :'#63B8FF'
						},
		 				{	name :'核价竞价',
		 					y : monthBidNum2,
		 					color : '#EEC900'
		 				},
		 				{	name :'公开竞价',
		 					y : monthBidNum3,
		 					color : '#A2CD5A'
		 				}
		 			]
		 		}]
			});
			}
			
			if(monthBidSum1==0&&monthBidSum2==0&&monthBidSum3==0){
				$("#totalMoneyPie").css("textAlign","center").css("lineHeight",$("#totalMoneyPie").height()+"px").
				html("<font style='color:#000;font-weight:bold;font-size:14px;font-family:Microsoft Yahei;'>当月竞价金额均为0</font>");
			}else{
			$("#totalMoneyPie").highcharts({
				chart: {
		 			plotBackgroundColor: null,
		 			plotBorderWidth: null,
		 			plotShadow: false,
		 	        borderWidth:0,
		 	        height: 250
		 		},
		 		 title: {
				        text: '竞价中标金额',
				        style: {
				        	color: '#000',
				        	fontSize: '14px',
				        	fontWeight: 'bold',
				        	fontFamily: "Microsoft Yahei"
				        	}
				    },
		 		credits: {
		 			enabled:false
		 		},
		 		tooltip: {
		              formatter: function() {
		                return this.point.name + '<br>'+ this.y +' 元';
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
								if (this.point.y > 0){
									return this.point.name;
								}
							}
						}
		 			}
		 		},
		 		series: [{
		 			type: 'pie',
		 			name: '值',
		 			data: [
		 			    {	name :'综合竞价',
							y : monthBidSum1,
							color :'#63B8FF'
						},
		 				{	name :'核价竞价',
		 					y : monthBidSum2,
		 					color : '#EEC900'
		 				},
		 				{	name :'公开竞价',
		 					y : monthBidSum3,
		 					color : '#A2CD5A'
		 				}
		 			]
		 		}]
			});
			}
			 
			$('#totalColumn').highcharts({
			    chart: {
			    	height:250
			    },
			    title: {
			        text: ''
			    },
			    xAxis: {
			        categories: categories
			    },
			    yAxis:[{
		            
		            min:0,
		            title:{
		                text :''
		            }
		        },{
		        	min:0,
		         title:{
		                text :''
		            },
		            
		            opposite:true
		        }],
			    legend:{
			    	enabled:true,
			    	layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'bottom',
	                floating: false,
	                borderWidth: 1,
	                backgroundColor: '#FFFFFF',
	                shadow: true,
	                y:-30,
	                itemMarginTop: 6,
	                itemMarginBottom: 6
			    },
			    
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        }
			    },
			    series: [{
			    	type:'line',
			        name:'综合竞价（金额）',
			        data:bidSum1,
			        color:'#63B8FF',
			        yAxis:1
			    },{
			    	type:'line',
			        name:'核价竞价（金额）',
			        data:bidSum2,
			        color:'#EEC900',
			        yAxis:1
			    },{
			    	type:'line',
			        name:'公开竞价（金额）',
			        data:bidSum3,
			        color:'#A2CD5A',
			        yAxis:1
			    },
			    {
			    	type:'column',
			        name:'综合竞价（单数）',
			        data:bidNum1,
			        color:'#63B8FF',
			        yAxis:0
			    },{
			    	type:'column',
			        name:'核价竞价（单数）',
			        data:bidNum2,
			        color:'#EEC900',
			        yAxis:0
			    },{
			    	type:'column',
			        name:'公开竞价（单数）',
			        data:bidNum3,
			        color:'#A2CD5A',
			        yAxis:0
			    }]
			});
			
			
		}
	});
}



function showTotalBid(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				var html='';
				for ( var i = object.length-1; i >=0; i--) {
					categories.push(object[i].bidType);
					html +=  '<tr>'+
                    '<td style="border-left:none;" width="145"><strong>'+object[i].bidType+'</strong></td>'+
                    '<td width="148">'+object[i].totalNum+'</td>'+
                    '<td width="148">'+object[i].finishNum+'</td>'+
                    '<td width="148">'+object[i].abortNum+'</td>'+
                    '<td width="148">'+object[i].execNum+'</td>'+
                    '<td width="148">'+parseFloat(object[i].wabSum).toFixed(2)+'</td>'+
                  '</tr>';
				}
				$('#thead').parent().append(html);
			}
		}
	});
}


function simpleName(str){
	var returnStr = str;
	if(str=='合约管理部'){
		returnStr = '集团部门';
	}else if(str=='十六号线项目公司'){
		returnStr = '十六号线';
	}else if(str=='十三号线项目公司'){
		returnStr = '十三号线';
	}else if(str=='上海磁浮交通发展有限公司'){
		returnStr = '磁浮公司';
	}else if(str=='平台采购代理-机电'){
		returnStr = '机电代理';
	}else if(str=='平台采购代理-联合'){
		returnStr = '联合代理';
	}else if(str=='上海轨道交通培训中心'){
		returnStr = '培训中心';
	}else if(str=='上海轨道交通维修保障中心'){
		returnStr = '维保公司';
	}else if(str=='上海地铁第一运营有限公司'){
		returnStr = '运一公司';
	}else if(str=='上海地铁第二运营有限公司'){
		returnStr = '运二公司';
	}else if(str=='上海地铁第三运营有限公司'){
		returnStr = '运三公司';
	}else if(str=='上海地铁第四运营有限公司'){
		returnStr = '运四公司';
	}else if(str=='上海轨道交通运营管理中心'){
		returnStr = '运管中心';
	}else if(str=='上海轨道交通资产管理中心'){
		returnStr = '资产中心';
	}else if(str=='十号线项目公司'){
		returnStr = '十号线';
	}else if(str=='杨浦线项目公司'){
		returnStr = '八号线';
	}else if(str=='申松线项目公司'){
		returnStr = '九号线';
	}else if(str=='申嘉线项目公司'){
		returnStr = '十一号线';
	}
	return returnStr;
}


function showSupplierNum(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		async : false,
		error : function() {
			//alert("error");
		},
		success : function(data) {
			if (data != null&&data.length>0) {
				var html = '';
				for(var i=0; i<data.length; i++){
					html += '<tr style="height:30px;"><td style="border:1px solid #d0d0d0;line-height:30px;"><strong>'
						+data[i][0]+'</strong></td><td style="border:1px solid #d0d0d0;line-height:30px;">'
						+data[i][1]+'</td><td style="border:1px solid #d0d0d0;line-height:30px;">'
						+data[i][2]+'</td></tr>';
				}
				$('#supplierTbody').html(html);
			}
		}
	});
}

function showSupplierStatPig(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		cache : false,
		async:false,
		error : function() {
			//alert("系统连接失败，请稍后再试！")
		},
		success : function(object) {
			var categories = [];
			var series = [];
			if (object != null && object.length > 0) {
				for ( var i = object.length-1; i >=0; i--) {
					categories.push(object[i][0]);
					var se = [ object[i][0], parseInt(object[i][1])];
					series.push(se);
				}
			}
			$('#supplierStatPig').highcharts({
			    chart: {
			        type: 'area',
			        height:250
			    },
			    title: {
			        text: '新增合格供应商数据统计(月度)',
			        style: {
			        	color: '#000',
			        	fontSize: '14px',
			        	fontWeight: 'bold',
			        	fontFamily: "Microsoft Yahei"
			        	}
			    },
			    colors:['#FFAEB9'],
			    xAxis: {
			        categories: categories
			    },
			    yAxis:{
			    	title:{
			    		text:''
			    	}
			    },
			    legend:{
			    	enabled:false
			    },
			    credits:{
			    	enabled:false
			    },
			    tooltip: {
			        pointFormat: '<tr><td style="color:{series.color};padding:0">数量： </td>' +
			            '<td style="padding:0"><b>{point.y} </b></td></tr>',
			        footerFormat: '</table>',
			        shared: true,
			        useHTML: true
			    },
			    plotOptions: {
			        column: {
			            pointPadding: 0.2,
			            borderWidth: 0
			        },
			        series: {
		                fillOpacity: 0.5
		            }
			    },
			    series: [{
			        data:series
			    }]
			});
			
		}
	});
}

function findMscpAlarmStat(url){
	$.ajax( {
		type : 'post',
		url : url,
		dataType : 'json',
		async : false,
		error : function() {
			//alert("error");
		},
		success : function(data) {
			if (data != null&&data.length>0) {
				var html = '';
				for(var i=0; i<data.length; i++){
					html += '<div class="Num_02" style="width:221px;height:100px;"><em>'+data[i].typeName+'</em><h3>'+data[i].countNum+'</h3></div>';
				}
				$('#alarmDiv').html(html);
			}
		}
	});
}
