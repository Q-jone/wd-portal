//lineStatus,线路的状态,-1:没有改变，1：改变了
function changeLineStatus(){
	lineStatus = 1;
}

function enabledDatepicker(){
	var lineNo = $("#lineNo").val();
	$("#"+lineNo+"op").attr("selected",true);
	
	var dates = $( "#datepicker1, #datepicker2" ).datepicker({
		inline: true,
		changeYear:true,
		minDate:$("#firstDate").val(),
		maxDate:$("#lastDate").val(),
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			var option = this.id == "datepicker1" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
					instance.settings.dateFormat ||
					$.datepicker._defaults.dateFormat,
					selectedDate, instance.settings );
			dates.not( this ).datepicker( "option", option, date );
		}
	});
}

//跳转到指定页
function goPage(pageNo,type){
    if(type=="0"){
    	var pageCount = $("#totalPageCount").val();
       	var number = $("#number").val();
       	if(!number.match(/^[0-9]*$/)){
  			alert("请输入数字");
  			$("#number").val("");
  			$("#number").focus();
  			return ;
      	}
    	if(parseInt(number)>parseInt(pageCount)){
    		$("#number").val(pageCount);
    		$("#pageNo").val(pageCount);
    	}else{
    		$("#pageNo").val(number);
    	}
	}else if(type=="1"){	//type=1,跳转到上一页
    	$("#pageNo").val(parseInt($("#number").val())-1);
    }else if(type=="2"){	//type=2,跳转到下一页
    	$("#pageNo").val(parseInt($("#number").val())+1);
    }else if(type=="3"){	//type=3,跳转到最后一页,或第一页
   		$("#pageNo").val(pageNo);
    }
    $("#startDateHide").val($("#datepicker1").val());
    $("#endDateHide").val($("#datepicker2").val());
    $("#form").submit();
}

//画一个柱状图
function newChartColumn(valueList,dateList,renderTo,unit,interval,controlList){
	
	if(interval=='undefined') interval=1;
	var chart1 = new Highcharts.Chart({
		chart: {
		    renderTo: renderTo,
		    type: 'column',
		    borderWidth:0
		},
		credits:{					//右边下标HighCharts.com去除
			enabled:false
		},
		legend: {					//正下方数据线名称，点击可以显示和删除
					enabled: false
				},
		title: {
			text:unit,
			style:{
				fontSize:'24px',
				color:'#4572A7'
			}
		},
		plotOptions: {
            series: {
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
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
			 	staggerLines: 2,
                style: {
                    fontSize:'10px'
                }
            },
            tickInterval:interval
		},
		yAxis:{
			title: {
		    	text: null
			}, 
			labels: {
                style: {
                    fontSize:'10px'
                }
            }
		},
		series: [
			{
				name: "值",
	  			data: valueList
	  		},{
	  			name:'管控线',
	  			data:controlList,
	  			type:'line',
	  			color:'#80699B'
	  		}]
	});
}

//画一个饼状图
function newChartPie(valueList,renderTo){
	var chart = new Highcharts.Chart({
		chart: {
			renderTo: renderTo,
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
       		borderWidth:0
		},
		title: {
			text: ''
		},
		credits: {
			enabled:false
		},
		legend:{
			layout:'vertical',
			align:'right',
			floating: true
		},
		tooltip: {
            formatter: function() {
              return "<b>"+this.point.name + '<b/>：'+ this.y +' 列';
           },
           style: {
               padding: '10px',
               //fontWeight: 'bold',
               fontSize:'14px'
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
                        fontSize:'16px'
                    },
					formatter: function() {
						return this.point.name+'：'+this.percentage.toFixed(2)+'%';	//this.percentage 百分比
					}
				},
				showInLegend:true
				
			}
		},
		series: [{
			type: 'pie',
			name: '值',
			data: [
				{	name :"3节编组",
					y : valueList[0],
					color : '#8B1A1A'
				},
				{	name :"4节编组",
					y : valueList[1],
					color : '#79BB25'
				},
				{	name :"6节编组",
					y : valueList[2],
					color : '#FFC002'
				},
				{	name :"7节编组",
					y : valueList[3],
					color : '#4572A7'
				},
				{	name :"8节编组",
					y : valueList[4],
					color : '#FE5917'
				}
			]
		}]
	});
}


//画一个折线图
function newChartLine(valueList,dateList,renderTo,interval,controlList){
	if(interval=='undefined') interval=1;
	var chart1 = new Highcharts.Chart({
		chart: {
		    renderTo: renderTo,
		    type: 'line',
		    borderWidth:0
		},
		credits:{					//右边下标HighCharts.com去除
			enabled:false
		},
		legend: {					//正下方数据线名称，点击可以显示和删除
					enabled: false
				},
		title: {
			text:null
		},
		tooltip: {
            formatter: function() {
               return this.x + '<br>'+this.series.name+":"+ this.y +'';
           },
           style: {
               padding: '10px',
               fontWeight: 'bold',
               fontSize:'12px'
           }
		},
		plotOptions:{
			dataLables:{
				enabled:true
			},
			series: {
                marker: {
                    enabled: false,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
		},
		xAxis: {
			categories:dateList,
			minPadding: 0.05,
			maxPadding: 0.05,
			tickInterval:interval
		},
		yAxis:{
			title: {
		    	text: null
			} 
		},
		series: [{
			name:"值",
	  		data: valueList
		},{
  			name:'管控线',
  			data:controlList,
  			type:'line',
  			color:'#80699B'
  		}]
	});
}

function getStartDate(clickNum){
	var end = ($("#datepicker2").val().toString()).split("-");
	var eDate = new Date(end.join("/"));
	switch(clickNum){
		case 1:
			eDate.setDate(eDate.getDate()-14);
			return eDate.getFullYear()+"-"+getNomalDate(eDate.getMonth())+"-"+formatDate(eDate.getDate());
		case 2:
			eDate.setMonth(eDate.getMonth()-1);
			return eDate.getFullYear()+"-"+getNomalDate(eDate.getMonth())+"-"+formatDate(eDate.getDate());
		case 3:
			eDate.setMonth(eDate.getMonth()-3);
			return eDate.getFullYear()+"-"+getNomalDate(eDate.getMonth())+"-"+formatDate(eDate.getDate());
		case 4:
			eDate.setMonth(eDate.getMonth()-6);
			return eDate.getFullYear()+"-"+getNomalDate(eDate.getMonth())+"-"+formatDate(eDate.getDate());
		case 5:
			eDate.setFullYear(eDate.getFullYear()-1);
			return eDate.getFullYear()+"-"+getNomalDate(eDate.getMonth())+"-"+formatDate(eDate.getDate());
	}
}


/**
 * 分页数据居中显示 
 */
function setWordCenter(){
	$("#tbody").children("tr").each(function(index,trObj){
		$(trObj).children("td").each(function(index,tdObj){
			$(tdObj).css("text-align","center");	
		});
	});
}

/**
 * 间隔
 * @param start
 * @param end
 * @return
 */
function getInterval(){
	var end = ($("#datepicker2").val().toString()).split("-");
	var eDate = new Date(end.join("/"));
	var start = ($("#datepicker1").val().toString()).split("-");
	var sDate = new Date(start.join("/"));
	var daySpan = (eDate - sDate)/(60*60*24*1000);
	if(daySpan<=15){
		return 1;
	}else if(daySpan<=100){
		return 5;
	}else if(daySpan<=200){
		return 10;
	}else if(daySpan<=400){
		return 20;
	}else if(daySpan<=4800){
		return 40;
	}
}











