//获取年份
function getTheYear(span){
    return new Date().getFullYear() + span;
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

//获取日期
function getPredayByDate(d,span){
    var date = new Date(d.replace(/-/g,"/"));
    var yesterday_milliseconds=date.getTime()-1000*60*60*24*parseInt(span);
    var yesterday = new Date();
    yesterday.setTime(yesterday_milliseconds);
    var strYear = yesterday.getFullYear();
    var strDay = yesterday.getDate();
    var strMonth = yesterday.getMonth()+1;

    datastr = strMonth+"月"+strDay+"日"+"";
    return datastr;
}

var comQualityObj;

//构造stat
function getStat(date,y,dayilyControl,pre,control,unit1,unit2,divide){
    return date.substr(0,4)+"年累计完成" + (y/divide>=1?
        (y/divide).toFixed(2)+unit2 : y.toFixed(0)+
        unit1)+"，占比"+ $.getPercent(y,control)+
        "% "+($.getPercent(y,dayilyControl)>100?"<img style='display:inline;' src='smile.png'/>":"<img style='display:inline;' src='sad.png'/>")+
        " &nbsp;"+getPredayByDate(date,1)+"："+
        (pre/10000>=1?
            (pre/10000).toFixed(2)+unit2 : pre.toFixed(0)+unit1);
}

//构造饼图
function pieObject(y,name,color,size,innerSize,xPos,yPos,unit,overPlan,phtml){
    this.y = y;
    this.name = name;
    this.color = color;
    this.size = size;
    this.innerSize = innerSize;
    this.xPos = xPos;
    this.yPos = yPos;
    this.unit = unit;
    this.overPlan = overPlan;
    this.phtml = phtml;
}

//得到有效数据时间
function getValidDate(){
    $.get(
            '/portal/metroCover/getValidDate.action?random='+Math.random(),
        function(obj){
            var metroLine = "";
            //$(".thisYear").html(obj.substr(0,4));
            //$(".lastYear").html(parseInt(obj.substr(0,4))-1);
            $("#indicatorDate").val(obj);
            $("#indicatorDate").datepicker('option', 'maxDate', obj);
            getMetroScaleInfo("","","","","");
            getMetroQualityInfo("","","","","");
            getMetroProductionInfo("","","","","");
            //$("#expressLine").html(metroLine);

        },
        "text"
    ).error(function() {});
}

//得到线路
function getMetroLine(){
    $.get(
            '/portal/metroCover/getMetroLine.action?random='+Math.random(),
        function(obj){
            var metroLine = "";
            for(var i=0;i<obj.length;i++){
                metroLine +="<option value='"+obj[i].lineId+"'>"+obj[i].lineName+"</option>";
            }
            $("#indicatorLine").html(metroLine);
            $("#expressLine").html(metroLine);
        },
        "json"
    ).error(function() {});
}

//得到项目公司
function getMetroCompany(){
    $.get(
            '/portal/metroCover/getMetroCompany.action?random='+Math.random(),
        function(obj){
            var metroCompany = "";
            for(var i=0;i<obj.length;i++){
                //if(obj[i].companyId != "0") {
                    metroCompany += "<h3 companyId='" + obj[i].companyId + "' class='com fl mr8 mouse'>" + obj[i].companyName + "</h3>";
                //}
            }
            $("#indicatorCompany").html(metroCompany);
            //$("#expressLine").html(metroLine);
        },
        "json"
    ).error(function() {});
}

//规模指标 type 0 线路 1 公司
function getMetroScaleInfo(type,id,date,span,yearSpan){
    $.get(
            '/portal/metroCover/getMetroScaleInfo.action?random='+Math.random(),
        {
            "id":id,
            "type":type,
            "span":span,
            "yearSpan":yearSpan,
            "date":date
        },
        function(obj){
            $("#allocateDaily").html(obj.sections);
            $("#lineDistance").html(obj.lineDistance);
            $("#stationCount").html(obj.stationCount);
        },
        "json"
    ).error(function() {});
}

//质量安全 分视图
function setOnTimeInfo(object){
    var i =object.children("option:selected").index();
    i==-1?0:i;
    $("#onTimeYear").html(comQualityObj[i].onTimeYear);
    $("#onTimeLastYear").html(comQualityObj[i].onTimeLastYear);
    $("#onTimeDaily").html(comQualityObj[i].onTimeDaily);
    $("#onTimeControl").html(comQualityObj[i].onTimeControl);
    $("#onTimeChart").spark_line(
        {
            data : comQualityObj[i].onTimeList,
            control : comQualityObj[i].onTimeControlList,
            suffix : '%',
            title : '正点率',
            composite : true,
            width : '150',
            height : '40',
            xdata : getDayArr(comQualityObj[i].date,comQualityObj[i].onTimeList.length)
        },null)
}

function setOnWorkInfo(object){
    var i =object.children("option:selected").index();
    $("#onWorkYear").html(comQualityObj[i].onWorkYear);
    $("#onWorkLastYear").html(comQualityObj[i].onWorkLastYear);
    $("#onWorkDaily").html(comQualityObj[i].onWorkDaily);
    $("#onWorkControl").html(comQualityObj[i].onWorkControl);
    $("#onWorkChart").spark_line(
        {
            data : comQualityObj[i].onWorkList,
            control : comQualityObj[i].onWorkControlList,
            suffix : '%',
            title : '兑现率',
            composite : true,
            width : '150',
            height : '40',
            xdata : getDayArr(comQualityObj[i].date,comQualityObj[i].onWorkList.length)
        },null)
}

//质量安全指标 type 0 线路 1 公司
function getMetroQualityInfo(type,id,date,span,yearSpan){
    $.get(
            '/portal/metroCover/getMetroQualityInfo.action?random='+Math.random(),
        {
            "id":id,
            "type":type,
            "span":span,
            "yearSpan":yearSpan,
            "date":date
        },
        function(obj){
            //公司对应线路
            if(type=="1" && obj.list.length > 0) {
                var options = "";
                comQualityObj = obj.list;
                if(id!="0") {
                    for (var i = 0; i < comQualityObj.length; i++) {
                        options += "<option value='" + comQualityObj[i].lineId + "'>" + comQualityObj[i].lineName + "</option>";
                    }
                    //console.log(comQualityObj);
                    $("#comOnTime,#comOnWork").children("select").html(options);
                    $("#comOnTime,#comOnWork").show();
                }else{
                    $("#comOnTime,#comOnWork").hide();
                }
                setOnTimeInfo($("#comOnTime>select"));
                setOnWorkInfo($("#comOnWork>select"));
            }else{
                $(".thisYear").html(obj.date.substr(0,4));
                $(".lastYear").html(parseInt(obj.date.substr(0,4))-1);

                $(".showDate").html(getPredayByDate(obj.date,0));
                $("#onTimeYear").html(obj.onTimeYear);
                $("#onTimeLastYear").html(obj.onTimeLastYear);
                $("#onTimeDaily").html(obj.onTimeDaily);
                $("#onTimeControl").html(obj.onTimeControl);
                $("#onWorkYear").html(obj.onWorkYear);
                $("#onWorkLastYear").html(obj.onWorkLastYear);
                $("#onWorkDaily").html(obj.onWorkDaily);
                $("#onWorkControl").html(obj.onWorkControl);

                $("#onTimeChart").spark_line(
                    {
                        data : obj.onTimeList,
                        control : obj.onTimeControlList,
                        suffix : '%',
                        title : '正点率',
                        composite : true,
                        width : '150',
                        height : '40',
                        xdata : getDayArr(obj.date,obj.onTimeList.length)
                    },null)

                $("#onWorkChart").spark_line(
                    {
                        data : obj.onWorkList,
                        control : obj.onWorkControlList,
                        suffix : '%',
                        title : '兑现率',
                        composite : true,
                        width : '150',
                        height : '40',
                        xdata : getDayArr(obj.date,obj.onWorkList.length)
                    },null)

            }
        },
        "json"
    ).error(function() {});

}

//生产指标 type 0 线路 1 公司
function getMetroProductionInfo(type,id,date,span,yearSpan){
    $.get(
            '/portal/metroCover/getMetroProductionInfo.action?random='+Math.random(),
        {
            "id":id,
            "type":type,
            "span":span,
            "yearSpan":yearSpan,
            "date":date
        },
        function(obj){
            var thisYear = obj.date.substr(0,4);
            var lastYear = parseInt(obj.date.substr(0,4))-1;
            $(".thisYears").html(thisYear + "年");
            $(".lastYears").html(lastYear + "年");

            $("#useMetroDaily").html(obj.useMetroDaily);
            $("#useMetroRateDaily").html(obj.useMetroRateDaily);
            $("#runMetroDaily").html(obj.runMetroDaily);
            $("#runMetroMonth").html(obj.runMetroMonth);

            $("#passCapDaily").html(obj.passCapDaily);
            $("#passCapAvgLastYear").html(obj.passCapAvgLastYear);
            $("#passCapAvgYear").html(obj.passCapAvgYear);
            $("#passCapAvgControl").html(obj.passCapAvgControl);
            $("#passCapMaxYear").html(obj.passCapMaxYear);
            $("#passCapMaxYearDate").html(obj.passCapMaxYearDate);
            $("#passCapMaxLast").html(obj.passCapMaxLast);
            $("#passCapMaxLastDate").html(obj.passCapMaxLastDate);

            $("#incomeDaily").html(obj.incomeDaily);
            $("#incomeAvgLastYear").html(obj.incomeAvgLastYear);
            $("#incomeAvgYear").html(obj.incomeAvgYear);
            $("#incomeAvgControl").html(obj.incomeAvgControl);
            $("#incomeMaxYear").html(obj.incomeMaxYear);
            $("#incomeMaxYearDate").html(obj.incomeMaxYearDate);
            $("#incomeMaxLast").html(obj.incomeMaxLast);
            $("#incomeMaxLastDate").html(obj.incomeMaxLastDate);

            $("#metroDistanceDaily").html(obj.metroDistanceDaily);
            $("#metroDistanceAvgLastYear").html(obj.metroDistanceAvgLastYear);
            $("#metroDistanceAvgYear").html(obj.metroDistanceAvgYear);
            $("#metroDistanceAvgControl").html(obj.metroDistanceAvgControl);
            $("#metroDistanceMaxYear").html(obj.metroDistanceMaxYear);
            $("#metroDistanceMaxYearDate").html(obj.metroDistanceMaxYearDate);
            $("#metroDistanceMaxLast").html(obj.metroDistanceMaxLast);
            $("#metroDistanceLastDate").html(obj.metroDistanceMaxLastDate);

            $("#useMetroChart").spark_bar(
                {
                    data : obj.useMetroList,
                    suffix : '列',
                    title : '运用列车数',
                    composite : false,
                    width : '150',
                    height : '40',
                    xdata : getDayArr(obj.date,obj.useMetroList.length)
                },null)
            $("#runMetroChart").spark_bar(
                {
                    data : obj.runMetroList,
                    suffix : '列',
                    title : '运用列车数',
                    composite : false,
                    width : '150',
                    height : '40',
                    xdata : getDayArr(obj.date,obj.runMetroList.length)
                },null)

            $("#passCapChart").spark_bar(
                {
                    data : obj.passCapMonthList,
                    control : obj.passCapAvgMonthList,
                    controlName:'月均值',
                    suffix : '万人',
                    title : '客流量（含换乘）',
                    composite : true,
                    width : '150',
                    height : '40',
                    xdata : getMonthArr(obj.date,obj.passCapMonthList.length)
                },null)
            $("#passCapChartDate").html(obj.passCapStartMonth.substr(0,7) + " 至 " +obj.passCapEndMonth.substr(0,7)
            +"<br>月均值："+obj.passCapAvgMonth+"万人");
            $("#incomeChart").spark_bar(
                {
                    data : obj.incomeMonthList,
                    control : obj.incomeAvgMonthList,
                    controlName:'月均值',
                    suffix : '万元',
                    title : '客运收入',
                    composite : true,
                    width : '150',
                    height : '40',
                    xdata : getMonthArr(obj.date,obj.incomeMonthList.length)
                },null)
            $("#incomeChartDate").html(obj.incomeStartMonth.substr(0,7) + " 至 " +obj.incomeEndMonth.substr(0,7)
                +"<br>月均值："+obj.incomeAvgMonth+"万元");
            $("#metroDistanceChart").spark_bar(
                {
                    data : obj.metroDistanceMonthList,
                    control : obj.metroDistanceAvgMonthList,
                    controlName:'月均值',
                    suffix : '万车公里',
                    title : '运营里程',
                    composite : true,
                    width : '150',
                    height : '40',
                    xdata : getMonthArr(obj.date,obj.metroDistanceMonthList.length)
                },null)
            $("#metroDistanceChartDate").html(obj.metroDistanceStartMonth.substr(0,7) + " 至 " +obj.metroDistanceEndMonth.substr(0,7)
                +"<br>月均值："+obj.metroDistanceAvgMonth+"万车公里");
            //console.log(obj.passCapLastYear + " " +obj.passCapControlYear);
            //console.log(obj.passCapYear + " " +obj.passCapPlanMonth);
            //#EE6363 #FF0000 #FF4040
            if(obj.passCapYear < obj.passCapPlanMonth){
                $("#passCapTotalChart").highchart_pie({
                    width : 290, height : 300,control : obj.passCapControlYear, unit:'万人',
                    pieArray:[
                        new pieObject(obj.passCapLastYear,lastYear+'年<br>年度客流量','#1874CD','85%','80%',205,235,'万人',false,
                                lastYear+'年<br>年度客流量<br>'),
                        new pieObject(obj.passCapControlYear,thisYear+'年<br>年度管控值','#87CEFF','80%','60%',205,40,'万人',false,
                                thisYear+'年<br>年度管控值<br>'),
                        new pieObject(obj.passCapPlanMonth,thisYear+'年<br>月度计划累计值','#FF9900','80%','60%',20,290,'万人',false,
                                thisYear+'年月度计划累计值'),
                        new pieObject(obj.passCapYear,thisYear+'年<br>年累计值','#FF4040','80%','60%',90,130,'万人',true,
                                thisYear+'年<br>已完成'+ $.getPercent(obj.passCapYear,obj.passCapControlYear)+'%<br>年累计值<br>')
                    ]

                },null)
            }else{
                $("#passCapTotalChart").highchart_pie({
                    width : 290, height : 300,control : obj.passCapControlYear, unit:'万人',
                    pieArray:[
                        new pieObject(obj.passCapLastYear,lastYear+'年<br>年度客流量','#1874CD','85%','80%',205,235,'万人',false,
                                lastYear+'年<br>年度客流量<br>'),
                        new pieObject(obj.passCapControlYear,thisYear+'年管控值','#87CEFF','80%','60%',205,40,'万人',false,
                                thisYear+'年<br>年度管控值<br>'),
                        new pieObject(obj.passCapYear,thisYear+'年累计值','#2E8B57','80%','60%',90,130,'万人',true,
                                thisYear+'年<br>已完成'+ $.getPercent(obj.passCapYear,obj.passCapControlYear)+'%<br>年累计值<br>'),
                        new pieObject(obj.passCapPlanMonth,thisYear+'年<br>月度计划累计值','#FF9900','80%','60%',20,290,'万人',false,
                                thisYear+'年月度计划累计值')
                    ]

                },null)
            }


            if(obj.incomeYear < obj.incomePlanMonth){
                $("#incomeTotalChart").highchart_pie({
                    width : 290, height : 300,control : obj.incomeControlYear, unit:'万元',
                    pieArray:[
                        new pieObject(obj.incomeLastYear,lastYear+'年<br>年度客流量','#1874CD','85%','80%',205,235,'万元',false,
                                lastYear+'年<br>年度客流量<br>'),
                        new pieObject(obj.incomeControlYear,thisYear+'年<br>年度管控值','#87CEFF','80%','60%',205,40,'万元',false,
                                thisYear+'年<br>年度管控值<br>'),
                        new pieObject(obj.incomePlanMonth,thisYear+'年<br>月度计划累计值','#FF9900','80%','60%',20,290,'万元',false,
                                thisYear+'年月度计划累计值'),
                        new pieObject(obj.incomeYear,thisYear+'年<br>年累计值','#FF4040','80%','60%',90,130,'万元',true,
                                thisYear+'年<br>已完成'+ $.getPercent(obj.incomeYear,obj.incomeControlYear)+'%<br>年累计值<br>')
                    ]

                },null)
            }else{
                $("#incomeTotalChart").highchart_pie({
                    width : 290, height : 300,control : obj.incomeControlYear, unit:'万元',
                    pieArray:[
                        new pieObject(obj.incomeLastYear,lastYear+'年<br>年度客流量','#1874CD','85%','80%',205,235,'万元',false,
                                lastYear+'年<br>年度客流量<br>'),
                        new pieObject(obj.incomeControlYear,thisYear+'年管控值','#87CEFF','80%','60%',205,40,'万元',false,
                                thisYear+'年<br>年度管控值<br>'),
                        new pieObject(obj.incomeYear,thisYear+'年累计值','#2E8B57','80%','60%',90,130,'万元',true,
                                thisYear+'年<br>已完成'+ $.getPercent(obj.incomeYear,obj.incomeControlYear)+'%<br>年累计值<br>'),
                        new pieObject(obj.incomePlanMonth,thisYear+'年<br>月度计划累计值','#FF9900','80%','60%',20,290,'万元',false,
                                thisYear+'年月度计划累计值')
                    ]

                },null)
            }

            if(obj.metroDistanceYear < obj.metroDistancePlanMonth){
                $("#metroDistanceTotalChart").highchart_pie({
                    width : 290, height : 300,control : obj.metroDistanceControlYear, unit:'万车公里',
                    pieArray:[
                        new pieObject(obj.metroDistanceLastYear,lastYear+'年<br>年度客流量','#1874CD','85%','80%',205,235,'万车公里',false,
                                lastYear+'年<br>年度客流量<br>'),
                        new pieObject(obj.metroDistanceControlYear,thisYear+'年<br>年度管控值','#87CEFF','80%','60%',205,40,'万车公里',false,
                                thisYear+'年<br>年度管控值<br>'),
                        new pieObject(obj.metroDistancePlanMonth,thisYear+'年<br>月度计划累计值','#FF9900','80%','60%',20,290,'万车公里',false,
                                thisYear+'年月度计划累计值'),
                        new pieObject(obj.metroDistanceYear,thisYear+'年<br>年累计值','#FF4040','80%','60%',90,130,'万车公里',true,
                                thisYear+'年<br>已完成'+ $.getPercent(obj.metroDistanceYear,obj.metroDistanceControlYear)+'%<br>年累计值<br>')
                    ]

                },null)
            }else{
                $("#metroDistanceTotalChart").highchart_pie({
                    width : 290, height : 300,control : obj.metroDistanceControlYear, unit:'万车公里',
                    pieArray:[
                        new pieObject(obj.metroDistanceLastYear,lastYear+'年<br>年度客流量','#1874CD','85%','80%',205,235,'万车公里',false,
                                lastYear+'年<br>年度客流量<br>'),
                        new pieObject(obj.metroDistanceControlYear,thisYear+'年管控值','#87CEFF','80%','60%',205,40,'万车公里',false,
                                thisYear+'年<br>年度管控值<br>'),
                        new pieObject(obj.metroDistanceYear,thisYear+'年累计值','#2E8B57','80%','60%',90,130,'万车公里',true,
                                thisYear+'年<br>已完成'+ $.getPercent(obj.metroDistanceYear,obj.metroDistanceControlYear)+'%<br>年累计值<br>'),
                        new pieObject(obj.metroDistancePlanMonth,thisYear+'年<br>月度计划累计值','#FF9900','80%','60%',20,290,'万车公里',false,
                                thisYear+'年月度计划累计值')
                    ]

                },null)
            }


            $("#passCapStat").html(getStat(obj.date,obj.passCapYear,obj.passCapDailyControl,obj.passCapLast,
                obj.passCapControlYear,"万人","亿人",10000));
            $("#incomeStat").html(getStat(obj.date,obj.incomeYear,obj.incomeDailyControl,obj.incomeLast,
                obj.incomeControlYear,"万元","亿元",10000));
            $("#metroDistanceStat").html(getStat(obj.date,obj.metroDistanceYear,obj.metroDistanceDailyControl,obj.metroDistanceLast,
                obj.metroDistanceControlYear,"万车公里","亿车公里",10000));

            //$("#passCapDl,#incomeDlDetail,#metroDistanceDlDetail").hide();
        },
        "json"
    ).error(function() {});
}


//获取最近 几天的日期
function getDayArr(date,span){
    var result = [];
    var newDate = new Date(date.replace(/-/g,"/"));
    newDate.setDate(newDate.getDate() + (-1*(parseInt(span)-1)));
    result.push(formatDate(newDate,'d'));
    var i = 1 ;
    while(i<span){
        newDate.setDate(newDate.getDate() + 1);
        result.push(formatDate(newDate,'d'));
        i++;
    }

    return result
}

function getMonthArr(date,yearSpan){
    var result = [];
    var newDate = new Date(date.replace(/-/g,"/"));
    newDate.setMonth(newDate.getMonth()-1);
    newDate.setMonth(newDate.getMonth() + (-1*(parseInt(yearSpan)-1)));
    result.push(formatDate(newDate,'m'));
    var i = 1 ;
    while(i<yearSpan){
        newDate.setMonth(newDate.getMonth() + 1);
        result.push(formatDate(newDate,'m'));
        i++;
    }
    return result
}


function formatDate(date,type){
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = month < 10 ? "0"+month : month + "";
    var date = date.getDate();
    date = date < 10 ? "0"+date : date + "";

    switch(type){
        case 'd' : return year + "-" + month + "-" + date;
        break;
        case 'm' : return year + "-" + month;
        break;
    }

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
		
//aside cms
function getLatestNewsAside(sj_id,pos){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"size"	:	"2"
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						for(var i=0;i<obj.length;i++){
							newsLi += "<li><a target='_blank' title='"+obj[i].copyTitle+"'";
							newsLi += " href='http://10.1.44.18/stfb"+obj[i].url+".htm'>"+obj[i].title+"</a></li>";
							newsP = "/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID;
						}
						$(".asideUl:eq("+pos+")").html(newsLi);
						$(".asideH:eq("+pos+") a").attr("href",newsP);
						if(sj_id=="974"){
								$(".asideH:eq("+pos+") a").attr("href","/ConstructionNotice/TConstructionNotice/findTConstructionNoticeOnly.action");
						}
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

