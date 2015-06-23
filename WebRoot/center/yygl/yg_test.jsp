<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%
    //System.out.println(request.getContextPath());  /portal
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    String start = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    String end = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta charset="utf-8"/>
<title>运营管理首页</title>
<link rel="stylesheet" href="../../css/formalize.css"/>

<link rel="stylesheet" href="../../css/page.css"/>
<link rel="stylesheet" href="../../css/default/imgs.css"/>
<link rel="stylesheet" href="../../css/reset.css"/>
<!--[if IE 6.0]>
<script src="js/iepng.js" type="text/javascript"></script>
<script type="text/javascript">
    EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
</script>
<![endif]-->
<script src="../../js/jquery-1.7.1.js"></script>
<script src="../../js/jquery.sparkline.min.js"></script>
    <script src="js/spark-plugin.js"></script>
<script src="js/highcharts.js"></script>
<script src="js/highchart-plugin.js"></script>
<script src="js/ygzx_test.js"></script>
<script src="../../js/html5.js"></script>
<script src="../../js/jquery.formalize.js"></script>
<script src="../../js/show.js"></script>
<link type="text/css" href="../../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
<script src="../../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../../js/flick/jquery.ui.datepicker-zh-CN.js"></script>

<style type="text/css">
    /*demo page css*/
    .demoHeaders {
        margin-top: 2em;
    }

    #dialog_link {
        padding: .4em 1em .4em 20px;
        text-decoration: none;
        position: relative;
    }

    #dialog_link span.ui-icon {
        margin: 0 5px 0 0;
        position: absolute;
        left: .2em;
        top: 50%;
        margin-top: -8px;
    }

    ul#icons {
        margin: 0;
        padding: 0;
    }

    ul#icons li {
        margin: 2px;
        position: relative;
        padding: 4px 0;
        cursor: pointer;
        float: left;
        list-style: none;
    }

    ul#icons span.ui-icon {
        float: left;
        margin: 0 4px;
    }

    .ui-datepicker-title span {
        display: inline;
    }

    /*隐藏datepicker控件 currentDay按钮*/
    button.ui-datepicker-current {
        display: none;
    }

    .mouse {
        cursor: pointer;
    }

    .mt25{
        margin-top:35px;
    }

    .lh26{
        line-height: 26px;
    }

    .panel_4 h3.com_select{
        color :#0664FF;
        font-weight: bold;
    }

    .pd10{
        padding: 3px 10px 3px 10px;
        cursor:pointer;
    }

    .lineUl{
        overflow-x: auto;
        overflow-y: hidden;
        width:210px;
        height: 30px;
    }
</style>
<style>
    .expressDetail {
        display: none;
        position: absolute;
        width: 380px;
        height: auto;
        background: #FBFACC;
    }
</style>

<script>


$(function () {
//    $("#onTimeChart").spark_line(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '%',
//                title : '正点率',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)
//
//    $("#onWorkChart").spark_line(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '%',
//                title : '兑现率',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)

//    $("#useMetroChart").spark_bar(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '列',
//                title : '运用列车数',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)
//    $("#runMetroChart").spark_bar(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '列',
//                title : '运用列车数',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)
//    $("#passCapChart").spark_bar(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '万人',
//                title : '客流量（含换乘）',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)
//    $("#incomeChart").spark_bar(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '万元',
//                title : '客运收入',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)
//    $("#metroDistanceChart").spark_bar(
//            {
//                data : [55,65,76,89,32,55,65,76,89,32,55,65,32,55],
//                control : [50,50,50,50,50,50,50,50,50,50,50,50,50,50],
//                suffix : '万车公里',
//                title : '运营里程',
//                composite : true,
//                width : '150',
//                height : '40'
//            },null)

    //#EE6363 红色
//    $("#passCapTotalChart").highchart_pie({
//        width : 290, height : 300,control : 27, unit:'万人',
//        pieArray:[
//            new pieObject(26,'去年','#1874CD','85%','80%',240,240,'万人'),
//            new pieObject(27,'管控值','#87CEFF','80%','60%',250,130,'万人'),
//            new pieObject(17.34,'实际','#2E8B57','80%','60%',125,130,'万人'),
//            new pieObject(16.38,'计划','#FFD700','80%','60%',10,240,'万人')
//        ]
//
//    },null)

//    $("#incomeTotalChart").highchart_pie({
//        width : 290, height : 300,control : 27, unit:'万元',
//        pieArray:[
//            new pieObject(26,'去年','#1874CD','85%','80%',240,240,'万元'),
//            new pieObject(27,'管控值','#87CEFF','80%','60%',250,130,'万元'),
//            new pieObject(17.34,'实际','#2E8B57','80%','60%',125,130,'万元'),
//            new pieObject(16.38,'计划','#FFD700','80%','60%',10,240,'万元')
//        ]
//
//    },null)
//
//    $("#metroDistanceTotalChart").highchart_pie({
//        width : 290, height : 300,control : 27, unit:'万车公里',
//        pieArray:[
//            new pieObject(26,'去年','#1874CD','85%','80%',240,240,'万车公里'),
//            new pieObject(27,'管控值','#87CEFF','80%','60%',250,130,'万车公里'),
//            new pieObject(17.34,'实际','#2E8B57','80%','60%',125,130,'万车公里'),
//            new pieObject(16.38,'计划','#FFD700','80%','60%',10,240,'万车公里')
//        ]
//
//    },null)



    $("dl[id$='Dl']").click(function(){
        $("dl[id$='DlDetail']").hide(1500);
        $("dl[id$='Dl']").show(1500);
        $(this).hide(1500);
        $(this).next("dl").show(1500);
        $.sparkline_display_visible();
    });

})

</script>

<script type="text/javascript">

    $(function () {
        $('#indicatorDate').datepicker({
            inline: true,
            changeYear: true,
            changeMonth: true,
            minDate: '2014-01-01'
        });


        $("#indicatorLine").change(function () {
            getMetroScaleInfo("0",$(this).val(),$("#indicatorDate").val(),"","");
            getMetroQualityInfo("0",$(this).val(),$("#indicatorDate").val(),"","");
            getMetroProductionInfo("0",$(this).val(),$("#indicatorDate").val(),"","");
            $("#indicatorCompany>.com").removeClass("com_select");
            $("#comOnTime,#comOnWork").hide();
        });

        $("#indicatorDate").change(function () {
            if($("#indicatorCompany>.com_select").length > 0){
                getMetroScaleInfo("1",$("#indicatorCompany>.com_select").attr("companyId"),$(this).val(),"","");
                getMetroQualityInfo("1",$("#indicatorCompany>.com_select").attr("companyId"),$(this).val(),"","");
                getMetroProductionInfo("1",$("#indicatorCompany>.com_select").attr("companyId"),$(this).val(),"","");
            }else{
                getMetroScaleInfo("0",$("#indicatorLine").val(),$(this).val(),"","");
                getMetroQualityInfo("0",$("#indicatorLine").val(),$(this).val(),"","");
                getMetroProductionInfo("0",$("#indicatorLine").val(),$(this).val(),"","");
            }

        });

        $("#indicatorCompany").on("click",".com",function(){
            getMetroScaleInfo("1",$(this).attr("companyId"),$("#indicatorDate").val(),"","");
            getMetroQualityInfo("1",$(this).attr("companyId"),$("#indicatorDate").val(),"","");
            getMetroProductionInfo("1",$(this).attr("companyId"),$("#indicatorDate").val(),"","");
            $("#indicatorCompany>.com").removeClass("com_select");
            $(this).addClass("com_select");

        });

        $("#comOnTime").on("change","select",function(){
            setOnTimeInfo($(this));
        });
        $("#comOnWork").on("change","select",function(){
            setOnWorkInfo($(this));
        });

        getValidDate();
        getMetroLine();
        getMetroCompany();



    });
</script>
</head>

<body>
<div class="main mw1002">
<!--Ctrl-->
<div class="ctrl clearfix">
    <div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg"
                         width="46" height="30" alt="展开"></div>
    <div class="posi fl">
        <ul>
            <li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">运营管理</a></li>
            <li class="fin">首页</li>
        </ul>
    </div>
    <div style="display:none;" class="fr lit_nav nwarp">
        <ul>
            <li class="selected"><a class="print" href="#">打印</a></li>
            <li><a class="express" href="#">导出数据</a></li>
            <li class="selected"><a class="table" href="#">表格模式</a></li>
            <li><a class="treeOpen" href="#">打开树</a></li>
            <li><a class="filterClose" href="#">关闭过滤</a></li>
        </ul>
    </div>
</div>
<!--Ctrl End-->
<div class="clearfix pt45">
<div class="right_main mb10">

<!--Panel_8-->
<div class="panel_4 mb10 panel_8">
<header>
    <div class="tit">
        <div class="bg clearfix">
            <h5 class="fl stats">运营指标</h5>

            <div class="fr pt5">
                <select class="input_small" id="indicatorLine">

                </select>
                <input readonly="readonly" name="indicatorDate" id="indicatorDate" type="text" class="input_small date">
            </div>
            <div class="fr pt5 mr8" id="indicatorCompany">
            </div>
            <span id="remind" class="mr5 fr"></span>
        </div>
    </div>
</header>
<div class="con clearfix">
<div class="clearfix">
    <dl style="padding:0">
        <table class="t_bor" width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td width="30%">
                    <h6 class="pt10">列</h6>

                    <h1 class="fr" id="allocateDaily">...</h1>

                    <h3 class="fr">配属数：</h3>
                </td>
                <td width="30%">
                    <h6 class="pt10">公里</h6>

                    <h1 class="fr" id="lineDistance">...</h1>

                    <h3 class="fr">线路长度：</h3>
                </td>
                <td>

                    <h6 class="pt10">站</h6>

                    <h1 class="fr" id="stationCount">...</h1>

                    <h3 class="fr">车站数：</h3>
                </td>
                <td>
                    <div class="categories t_r"><a href="/portal/operationIndicator/showScaleListPage.action"
                                                   target="_self">规模指标</a></div>
                </td>
            </tr>
        </table>
    </dl>
</div>
<div class="fl w50p clearfix">
    <div class="gray_border">
        <div class="block">
            <div class="corner">
                <div class="categories t_r"><a href="/portal/operationIndicator/showQualityListPage.action"
                                               target="_self">质量安全</a></div>
                <dl class="b_bor">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2">
                                <h3 class="fl thisYear"></h3><h3 class="fl"> 年累计正点率：</h3>

                                <div class="fl" id="comOnTime" style="display: none;">
                                    <select></select>
                                </div>

                                <div class="clearfix fr">
                                    <h6 class="pt10">%</h6>

                                    <h1 class="fr mouse" id="onTimeYear">...</h1>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl lastYear"></span><span class="fl"> 年正点率：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="onTimeLastYear">...</b>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl">正点率：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="onTimeDaily">...</b>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl">管控值：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="onTimeControl">...</b>
                                    </li>
                                </ul>
                            </td>
                            <td class="pt5" width="180">
                                <div class="border_gary">&nbsp;<span id="onTimeChart"></span></div>
                            </td>
                        </tr>
                    </table>
                </dl>
                <dl class="t_bor">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2">
                                <h3 class="fl thisYear"></h3><h3 class="fl"> 年累计兑现率：</h3>

                                <div class="fl" id="comOnWork" style="display: none;">
                                    <select></select>
                                </div>

                                <div class="clearfix fr">
                                    <h6 class="pt10">%</h6>

                                    <h1 class="fr mouse" id="onWorkYear">...</h1>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl lastYear"></span><span class="fl"> 年兑现率：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="onWorkLastYear">...</b>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl">兑现率：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="onWorkDaily">...</b>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl">管控值：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="onWorkControl">...</b>
                                    </li>
                                </ul>
                            </td>
                            <td class="pt5" width="180">
                                <div class="border_gary">&nbsp;<span id="onWorkChart"></span></div>
                            </td>
                        </tr>
                    </table>
                </dl>
            </div>
        </div>
    </div>
    <div class="gray_border">
        <div class="block">
            <div class="corner">
                <div class="categories t_r"><a href="/portal/operationIndicator/showProductionListPage.action"
                                               target="_self">运营生产</a></div>
                <dl class="b_bor">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2">
                                <h3 class="fl">运用列车数（含备用）：</h3>

                                <div class="clearfix fr">
                                    <h6 class="pt10">列</h6>

                                    <h1 class="fr mouse" id="useMetroDaily">...</h1>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl">运用车上线率：</span>
                                        <h6>%</h6>
                                        <b class="fr mr5" id="useMetroRateDaily">...</b>
                                    </li>
                                </ul>
                            </td>
                            <td class="pt5" width="180">
                                <div class="border_gary">&nbsp;<span id="useMetroChart"></span></div>
                            </td>
                        </tr>
                    </table>
                </dl>
                <dl class="t_bor">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2">
                                <h3 class="fl">开行列车：</h3>

                                <div class="clearfix fr">
                                    <h6 class="pt10">列</h6>

                                    <h1 class="fr mouse" id="runMetroDaily">...</h1>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl">月累计：</span>
                                        <h6>列</h6>
                                        <b class="fr mr5" id="runMetroMonth">...</b>
                                    </li>
                                </ul>
                            </td>
                            <td class="pt5" width="180">
                                <div class="border_gary">&nbsp;<span id="runMetroChart"></span></div>
                            </td>
                        </tr>
                    </table>
                </dl>
            </div>
        </div>
    </div>
</div>
<div class="fl w50p clearfix">
<div class="gray_border">
<div class="block">
<div class="corner">
<!--客流量 -->
<div id="passCapDiv">
    <dl class="b_bor mouse" id="passCapDl" style="display: none;">
        <table width="100%">
            <tr>
                <td colspan="2">
                    <h3 class="fl">客流量（含换乘）：</h3>

                    <div class="clearfix fr lh26">
                        <h5 style="font-size: 12px;" class="fr mouse" id="passCapStat">
                            ...
                        </h5>
                    </div>
                </td>
            </tr>
        </table>
    </dl>
    <dl class="b_bor" id="passCapDlDetail">
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="2">
                    <h3 class="fl">客流量（含换乘）：</h3>

                    <div class="clearfix fr">
                        <h6 class="pt10">万人</h6>

                        <h1 class="fr mouse" id="passCapDaily">...</h1>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="4" style="width:65%;">
                    <div id="passCapTotalChart"></div>
                </td>
                <td class="pt5" width="160">
                    <div class="border_gary fr ">&nbsp;<span id="passCapChart"></span></div>
                    <div id="passCapChartDate" class="clearfix"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10 mt25">
                        <li class="clearfix">
                            <span class="fl">去年日均：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapAvgLastYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">今年日均：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapAvgYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">日均管控：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapAvgControl">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl">今年峰值：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapMaxYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">峰值日期：</span>
                            <h6></h6>
                            <b class="fr mr5" id="passCapMaxYearDate">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl">历史峰值：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapMaxLast">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">峰值日期：</span>
                            <h6></h6>
                            <b class="fr mr5" id="passCapMaxLastDate">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
        </table>
    </dl>
</div>
<!-- 客流量  -->

<!-- 客运收入  -->
<div id="incomeDiv">
    <dl class="b_bor mouse" id="incomeDl">
        <table width="100%">
            <tr>
                <td colspan="2">
                    <h3 class="fl">客运收入：</h3>

                    <div class="clearfix fr lh26">
                        <h5 style="font-size: 12px;" class="fr mouse" id="incomeStat">
                            ...
                        </h5>
                    </div>
                </td>
            </tr>
        </table>
    </dl>
    <dl class="b_bor" id="incomeDlDetail" style="display: none;">
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="2">
                    <h3 class="fl">客运收入：</h3>

                    <div class="clearfix fr">
                        <h6 class="pt10">万元</h6>

                        <h1 class="fr mouse" id="incomeDaily">...</h1>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="4" style="width:65%;">
                    <div id="incomeTotalChart"></div>
                </td>
                <td class="pt5" width="160">
                    <div class="border_gary fr">&nbsp;<span id="incomeChart"></span></div>
                    <div id="incomeChartDate" class="clearfix"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10 mt25">
                        <li class="clearfix">
                            <span class="fl">去年日均：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeAvgLastYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">今年日均：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeAvgYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">日均管控：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeAvgControl">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl">今年峰值：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeMaxYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">峰值日期：</span>
                            <h6></h6>
                            <b class="fr mr5" id="incomeMaxYearDate">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl">历史峰值：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeMaxLast">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">峰值日期：</span>
                            <h6></h6>
                            <b class="fr mr5" id="incomeMaxLastDate">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
        </table>
    </dl>
</div>
<!-- 客运收入  -->

<!--运营里程-->
<div id="metroDistanceDiv">
    <dl class="b_bor mouse" id="metroDistanceDl">
        <table width="100%">
            <tr>
                <td colspan="2">
                    <h3 class="fl">运营里程：</h3>

                    <div class="clearfix fr lh26">
                       <h5 style="font-size: 12px;" class="fr mouse" id="metroDistanceStat">
                            ...
                       </h5>
                    </div>
                </td>
            </tr>
        </table>
    </dl>
    <dl class="b_bor" id="metroDistanceDlDetail" style="display: none;">
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td colspan="2">
                    <h3 class="fl">运营里程：</h3>

                    <div class="clearfix fr">
                        <h6 class="pt10">万车公里</h6>

                        <h1 class="fr mouse" id="metroDistanceDaily">...</h1>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="4" style="width:65%;">
                    <div id="metroDistanceTotalChart"></div>
                </td>
                <td class="pt5" width="160">
                    <div class="border_gary fr ">&nbsp;<span id="metroDistanceChart"></span></div>
                    <div id="metroDistanceChartDate" class="clearfix"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10 mt25">
                        <li class="clearfix">
                            <span class="fl">去年日均：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceAvgLastYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">今年日均：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceAvgYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">日均管控：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceAvgControl">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl">今年峰值：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceMaxYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">峰值日期：</span>
                            <h6></h6>
                            <b class="fr mr5" id="metroDistanceMaxYearDate">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl">历史峰值：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceMaxLast">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl">峰值日期：</span>
                            <h6></h6>
                            <b class="fr mr5" id="metroDistanceLastDate">...</b>
                        </li>
                    </ul>
                </td>
            </tr>
        </table>
    </dl>
</div>
<!--运营里程-->
</div>
</div>
</div>
</div>
</div>
<!--Panel_8 End-->


</div>
</div>
</div>
</div>
</body>
</html>
