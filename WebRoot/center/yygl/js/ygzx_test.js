var comQualityObj;

//构造stat
function getStat(y,pre,control,unit1,unit2,divide){
    return "年累计完成" + (y/divide>=1?
            (y/divide).toFixed(2)+unit2 : y.toFixed(0)+
            unit1)+"，占比"+ $.getPercent(y,control)+"% 昨日"+
        (pre/10000>=1?
            (pre/10000).toFixed(2)+unit2 : pre.toFixed(0)+unit1);
}

//构造饼图
function pieObject(y,name,color,size,innerSize,xPos,yPos,unit,showPercent){
    this.y = y;
    this.name = name;
    this.color = color;
    this.size = size;
    this.innerSize = innerSize;
    this.xPos = xPos;
    this.yPos = yPos;
    this.unit = unit;
    this.showPercent = showPercent;
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
            //$("#expressLine").html(metroLine);
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
                if(obj[i].companyId != "0") {
                    metroCompany += "<h3 companyId='" + obj[i].companyId + "' class='com fl mr8 mouse'>" + obj[i].companyName + "</h3>";
                }
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
            height : '40'
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
            height : '40'
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
                for (var i = 0; i < comQualityObj.length; i++) {
                    options += "<option value='" + comQualityObj[i].lineId + "'>" + comQualityObj[i].lineName + "</option>";
                }
                //console.log(comQualityObj);
                $("#comOnTime,#comOnWork").children("select").html(options);
                $("#comOnTime,#comOnWork").show();
                setOnTimeInfo($("#comOnTime>select"));
                setOnWorkInfo($("#comOnWork>select"));
            }else{
                $(".thisYear").html(obj.date.substr(0,4));
                $(".lastYear").html(parseInt(obj.date.substr(0,4))-1);

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
                    height : '40'
                },null)

                $("#onWorkChart").spark_line(
                        {
                            data : obj.onWorkList,
                            control : obj.onWorkControlList,
                            suffix : '%',
                            title : '兑现率',
                            composite : true,
                            width : '150',
                            height : '40'
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
                    height : '40'
                },null)
                $("#runMetroChart").spark_bar(
                {
                    data : obj.runMetroList,
                    suffix : '列',
                    title : '运用列车数',
                    composite : false,
                    width : '150',
                    height : '40'
                },null)

                $("#passCapChart").spark_bar(
                    {
                        data : obj.passCapMonthList,
                        control : obj.passCapAvgMonthList,
                        suffix : '万人',
                        title : '客流量（含换乘）',
                        composite : true,
                        width : '150',
                        height : '40'
                    },null)
                $("#passCapChartDate").html(obj.passCapStartMonth.substr(0,7) + " 至 " +obj.passCapEndMonth.substr(0,7));
                $("#incomeChart").spark_bar(
                    {
                        data : obj.incomeMonthList,
                        control : obj.incomeAvgMonthList,
                        suffix : '万元',
                        title : '客运收入',
                        composite : true,
                        width : '150',
                        height : '40'
                    },null)
                $("#incomeChartDate").html(obj.incomeStartMonth.substr(0,7) + " 至 " +obj.incomeEndMonth.substr(0,7));
                $("#metroDistanceChart").spark_bar(
                    {
                        data : obj.metroDistanceMonthList,
                        control : obj.metroDistanceAvgMonthList,
                        suffix : '万车公里',
                        title : '运营里程',
                        composite : true,
                        width : '150',
                        height : '40'
                    },null)
                $("#metroDistanceChartDate").html(obj.metroDistanceStartMonth.substr(0,7) + " 至 " +obj.metroDistanceEndMonth.substr(0,7));
                //console.log(obj.passCapLastYear + " " +obj.passCapControlYear);
                //console.log(obj.passCapYear + " " +obj.passCapPlanMonth);
                //#EE6363 #FF0000 #FF4040
                if(obj.passCapYear < obj.passCapPlanMonth){
                    $("#passCapTotalChart").highchart_pie({
                        width : 290, height : 300,control : obj.passCapControlYear, unit:'万人',
                        pieArray:[
                            new pieObject(obj.passCapLastYear,'去年','#1874CD','85%','80%',200,240,'万人',false),
                            new pieObject(obj.passCapControlYear,'管控值','#87CEFF','80%','60%',200,50,'万人',true),
                            new pieObject(obj.passCapPlanMonth,'计划','#FFD700','80%','60%',0,240,'万人',true),
                            new pieObject(obj.passCapYear,'实际','#FF4040','80%','60%',90,130,'万人',true)
                        ]

                    },null)
                }else{
                    $("#passCapTotalChart").highchart_pie({
                        width : 290, height : 300,control : obj.passCapControlYear, unit:'万人',
                        pieArray:[
                            new pieObject(obj.passCapLastYear,'去年','#1874CD','85%','80%',200,240,'万人',false),
                            new pieObject(obj.passCapControlYear,'管控值','#87CEFF','80%','60%',200,50,'万人',true),
                            new pieObject(obj.passCapYear,'实际','#2E8B57','80%','60%',90,130,'万人',true),
                            new pieObject(obj.passCapPlanMonth,'计划','#FFD700','80%','60%',0,240,'万人',true)
                        ]

                    },null)
                }


                if(obj.incomeYear < obj.incomePlanMonth){
                    $("#incomeTotalChart").highchart_pie({
                        width : 290, height : 300,control : obj.incomeControlYear, unit:'万元',
                        pieArray:[
                            new pieObject(obj.incomeLastYear,'去年','#1874CD','85%','80%',200,240,'万元',false),
                            new pieObject(obj.incomeControlYear,'管控值','#87CEFF','80%','60%',200,50,'万元',true),
                            new pieObject(obj.incomePlanMonth,'计划','#FFD700','80%','60%',0,240,'万元',true),
                            new pieObject(obj.incomeYear,'实际','#FF4040','80%','60%',90,130,'万元',true)
                        ]

                    },null)
                }else{
                    $("#incomeTotalChart").highchart_pie({
                        width : 290, height : 300,control : obj.incomeControlYear, unit:'万元',
                        pieArray:[
                            new pieObject(obj.incomeLastYear,'去年','#1874CD','85%','80%',200,240,'万元',false),
                            new pieObject(obj.incomeControlYear,'管控值','#87CEFF','80%','60%',200,50,'万元',true),
                            new pieObject(obj.incomeYear,'实际','#2E8B57','80%','60%',90,130,'万元',true),
                            new pieObject(obj.incomePlanMonth,'计划','#FFD700','80%','60%',0,240,'万元',true)
                        ]

                    },null)
                }

                if(obj.metroDistanceYear < obj.metroDistancePlanMonth){
                    $("#metroDistanceTotalChart").highchart_pie({
                        width : 290, height : 300,control : obj.metroDistanceControlYear, unit:'万车公里',
                        pieArray:[
                            new pieObject(obj.metroDistanceLastYear,'去年','#1874CD','85%','80%',200,240,'万车公里',false),
                            new pieObject(obj.metroDistanceControlYear,'管控值','#87CEFF','80%','60%',200,50,'万车公里',true),
                            new pieObject(obj.metroDistancePlanMonth,'计划','#FFD700','80%','60%',0,240,'万车公里',true),
                            new pieObject(obj.metroDistanceYear,'实际','#FF4040','80%','60%',90,130,'万车公里',true)
                        ]

                    },null)
                }else{
                    $("#metroDistanceTotalChart").highchart_pie({
                        width : 290, height : 300,control : obj.metroDistanceControlYear, unit:'万车公里',
                        pieArray:[
                            new pieObject(obj.metroDistanceLastYear,'去年','#1874CD','85%','80%',200,240,'万车公里',false),
                            new pieObject(obj.metroDistanceControlYear,'管控值','#87CEFF','80%','60%',200,50,'万车公里',true),
                            new pieObject(obj.metroDistanceYear,'实际','#2E8B57','80%','60%',90,130,'万车公里',true),
                            new pieObject(obj.metroDistancePlanMonth,'计划','#FFD700','80%','60%',0,240,'万车公里',true)
                        ]

                    },null)
                }


                $("#passCapStat").html(getStat(obj.passCapYear,obj.passCapLast,
                    obj.passCapControlYear,"万人","亿人",10000));
                $("#incomeStat").html(getStat(obj.incomeYear,obj.incomeLast,
                    obj.incomeControlYear,"万元","亿元",10000));
                $("#metroDistanceStat").html(getStat(obj.metroDistanceYear,obj.metroDistanceLast,
                    obj.metroDistanceControlYear,"万车公里","亿车公里",10000));

                //$("#passCapDl,#incomeDlDetail,#metroDistanceDlDetail").hide();
            },
            "json"
        ).error(function() {});
    }
