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
<script src="../../js/jquery.sparkline.js"></script>
<script src="js/spark-plugin.js"></script>
<script src="js/highcharts.js"></script>
<script src="js/highchart-plugin.js"></script>
<script src="js/ygzx_ajax.js"></script>
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

    .mt25 {
        margin-top: 35px;
    }

    .lh26 {
        line-height: 26px;
    }

    .panel_4 h3.com_select {
        color: #0664FF;
        font-weight: bold;
    }

    .pd10 {
        padding: 3px 10px 3px 10px;
        cursor: pointer;
    }

    .lineUl {
        overflow-x: auto;
        overflow-y: hidden;
        width: 210px;
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
    .faceStat {
        display: none;
        position: absolute;
        background: #faf2cc;
        font-size: 14px;
    }
</style>

<script type="text/javascript">
    $(function () {
        $("#passCapDl,#incomeDl,#metroDistanceDl").on("mouseenter mouseleave",".statTd",function(event){
            if(event.type == "mouseenter"){
                var offset = $(this).offset();
                $("#statDiv").css("left", offset.left+10);
                $("#statDiv").css("top", offset.top + 30);
                $("#statDiv").show();
            }else if(event.type == "mouseleave"){
                $("#statDiv").hide();
            }
        });

        $('#indicatorDate').datepicker({
            inline: true,
            changeYear: true,
            changeMonth: true,
            minDate: '2014-01-01'
            //showButtonPanel:true,
            //closeText:'清除',
            //currentText:'inDate'//仅作为“清除”按钮的判断条件

        });

        //$("#indicatorDate").datepicker('option', 'maxDate', inDate); 若设置maxDate小于今天 则无currentButton 按钮
        /*$(".ui-datepicker-close").live("click", function (){
         alert($(this).parent("div").children("button:eq(0)").text());
         if($(this).parent("div").children("button:eq(0)").text()=="inDate")
         {
         alert(1);
         $("#indicatorDate").val("");
         }
         });*/

        $("#indicatorLine").change(function () {
            getMetroScaleInfo("0", $(this).val(), $("#indicatorDate").val(), "", "");
            getMetroQualityInfo("0", $(this).val(), $("#indicatorDate").val(), "", "");
            getMetroProductionInfo("0", $(this).val(), $("#indicatorDate").val(), "", "");
            $("#indicatorCompany>.com").removeClass("com_select");
            $("#comOnTime,#comOnWork").hide();
        });

        $("#indicatorDate").change(function () {
            if ($("#indicatorCompany>.com_select").length > 0) {
                getMetroScaleInfo("1", $("#indicatorCompany>.com_select").attr("companyId"), $(this).val(), "", "");
                getMetroQualityInfo("1", $("#indicatorCompany>.com_select").attr("companyId"), $(this).val(), "", "");
                getMetroProductionInfo("1", $("#indicatorCompany>.com_select").attr("companyId"), $(this).val(), "", "");
            } else {
                getMetroScaleInfo("0", $("#indicatorLine").val(), $(this).val(), "", "");
                getMetroQualityInfo("0", $("#indicatorLine").val(), $(this).val(), "", "");
                getMetroProductionInfo("0", $("#indicatorLine").val(), $(this).val(), "", "");
            }

        });

        $("#indicatorCompany").on("click", ".com", function () {
            getMetroScaleInfo("1", $(this).attr("companyId"), $("#indicatorDate").val(), "", "");
            getMetroQualityInfo("1", $(this).attr("companyId"), $("#indicatorDate").val(), "", "");
            getMetroProductionInfo("1", $(this).attr("companyId"), $("#indicatorDate").val(), "", "");
            $("#indicatorCompany>.com").removeClass("com_select");
            $(this).addClass("com_select");
        });

        $("#comOnTime").on("change", "select", function () {
            setOnTimeInfo($(this));
        });
        $("#comOnWork").on("change", "select", function () {
            setOnWorkInfo($(this));
        });

        $("#expressLine").change(function () {
            $("#expressTable td").html("<div class='Alert_1'></div>");
            getLatestExpress($("#expressLine").val());
        })

        $(".reportLi").each(function (i, n) {
            $(n).click(function () {
                $(n).siblings("li").removeClass("selected");
                $(n).addClass("selected");
                $(".reportDiv").hide();
                $(".reportDiv:eq(" + i + ")").show();
            })
        })

        $(".detaila").live("mouseenter", function () {
            var offset = $(this).offset();
            var i = $(this).attr("detailId");
            var h = $(".expressDetail").eq(i).height();
            $(".expressDetail").eq(i).css("left", offset.left + 25);
            $(".expressDetail").eq(i).css("top", offset.top - h - 5);
            $(".expressDetail").eq(i).show();
            //$(".expressDetail").eq(i).fadeTo(100,0.4)
        });
        $(".detaila").live("mouseleave", function () {
            var i = $(this).attr("detailId");
            //$(".expressDetail").eq(i).fadeIn(100,0.4)
            $(".expressDetail").eq(i).hide();
            $(".expressDetail").eq(i).css("left", "");
            $(".expressDetail").eq(i).css("top", "");
        });

        getValidDate();
        getMetroLine();
        getMetroCompany();

        //getLatestIndicator("0", "", "1");
        getLatestExpress("");
        getQyDl();
        getLatestNews("1174", "0");
        getLatestNews("432", "1");
        getLatestNews("396", "2");
        getLatestNews("754", "3");
        getLatestNewsAside("1234", "0");
        getLatestNewsAside("1834", "1");
        getLatestNewsAside("1774", "2");
        //getLatestNewsAside("974","1");
        showConstructionLineInfo("<%=start%>", "<%=end%>", "974", "3");
        getLatestNewsAside("534", "4");
        $(".reportLi:eq(0)").click();
        //loadShow();


        $("dl[id$='Dl']").click(function () {
            $("dl[id$='DlDetail']").hide(1500);
            $("dl[id$='Dl']").show(1500);
            $(this).hide(1500);
            $(this).next("dl").show(1500);
            $.sparkline_display_visible();
        });

    });

    function showDetail(chartId) {
        if (chartId <= 7) {
            window.open("/portal/operationIndicator/showProductionDetailPage.action?lineNo=" + $("#indicatorLine").val() + "&endDate=" + $("#indicatorDate").val() + "&chartId=" + chartId);
        } else if (chartId > 7 && chartId < 12) {
            window.open("/portal/operationIndicator/showQualityDetailPage.action?lineNo=" + $("#indicatorLine").val() + "&endDate=" + $("#indicatorDate").val() + "&chartId=" + chartId);
        } else {
            window.open("/portal/operationIndicator/showScaleDetailPage.action?lineNo=" + $("#indicatorLine").val() + "&endDate=" + $("#indicatorDate").val() + "&chartId=" + chartId);
        }
    }
</script>
</head>

<body>
<div id="statDiv" class="faceStat">&nbsp;&nbsp;注：&nbsp;
    <img style='display:inline;' src='smile.png'/>：实际完成值超过当日累计管控值；
    <img style='display:inline;' src='sad.png'/>：实际完成值未超过当日累计管控值；
</div>
<div class="main mw1280">
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
<div style="width:44.92%;" class="fl clearfix">
    <div class="gray_border">
        <div class="block">
            <div class="corner">
                <div class="categories t_r"><a href="/portal/operationIndicator/showQualityListPage.action"
                                               target="_self">质量安全</a></div>
                <dl class="b_bor">
                    <table width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2">
                                <h3 class="fl thisYear"></h3>

                                <h3 class="fl"> 年累计正点率：</h3>

                                <div class="fl" id="comOnTime" style="display: none;margin-right: 10px;">
                                    <select></select>
                                </div>

                                <div class="clearfix fl">
                                    <h1 class="fl mouse" id="onTimeYear">...</h1>
                                    <h6 class="pt10" style="float:left;">%</h6>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl lastYear"></span><span class="fl"> 年累计正点率：</span>
                                        <b class="fl mr5" id="onTimeLastYear">...</b>
                                        <h6 style="float:left;">%</h6>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl showDate"></span>
                                        <span class="fl">正点率：</span>
                                        <b class="fl mr5" id="onTimeDaily">...</b>
                                        <h6 style="float:left;">%</h6>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl">管控值：</span>
                                        <b class="fl mr5" id="onTimeControl">...</b>
                                        <h6 style="float:left;">%</h6>
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
                                <h3 class="fl thisYear"></h3>

                                <h3 class="fl"> 年累计兑现率：</h3>

                                <div class="fl" id="comOnWork" style="display: none;margin-right: 10px;">
                                    <select></select>
                                </div>

                                <div class="clearfix fl">
                                    <h1 class="fl mouse" id="onWorkYear">...</h1>
                                    <h6 class="pt10" style="float:left;">%</h6>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl lastYear"></span><span class="fl"> 年累计兑现率：</span>
                                        <b class="fl mr5" id="onWorkLastYear">...</b>
                                        <h6 style="float:left;">%</h6>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl showDate"></span>
                                        <span class="fl">兑现率：</span>
                                        <b class="fl mr5" id="onWorkDaily">...</b>
                                        <h6 style="float:left;">%</h6>
                                    </li>
                                    <li class="clearfix">
                                        <span class="fl">管控值：</span>
                                        <b class="fl mr5" id="onWorkControl">...</b>
                                        <h6 style="float:left;">%</h6>
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

                                <div class="clearfix fl">
                                    <h1 class="fl mouse" id="useMetroDaily">...</h1>
                                    <h6 class="pt10" style="float:left;">列</h6>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl">运用车上线率：</span>
                                        <b class="fl mr5" id="useMetroRateDaily">...</b>
                                        <h6 style="float:left;">%</h6>
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

                                <div class="clearfix fl">
                                    <h1 class="fl mouse" id="runMetroDaily">...</h1>
                                    <h6 class="pt10" style="float:left;">列</h6>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <ul>
                                    <li class="clearfix">
                                        <span class="fl">月累计：</span>
                                        <b class="fl mr5" id="runMetroMonth">...</b>
                                        <h6 style="float:left;">列</h6>
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
<div class="fl w55p clearfix">
<div class="gray_border">
<div class="block">
<div class="corner">
<!--客流量 -->
<div id="passCapDiv">
    <dl class="b_bor mouse" id="passCapDl" style="display: none;">
        <table width="100%">
            <tr>
                <td colspan="2" class="statTd">
                    <h3 style="font-size:14px;" class="fl"><img src="pointer.png" style="display: inline;"/>&nbsp;客流量（含换乘）：</h3>

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

                    <div class="clearfix fl">
                        <h1 class="fl mouse" id="passCapDaily">...</h1>
                        <h6 class="pt10" style="float:left;">万人</h6>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="4" style="width:65%;">
                    <div id="passCapTotalChart"></div>
                </td>
                <td class="pt5" width="160">
                    <div class="border_gary fr ">&nbsp;<span id="passCapChart"></span></div>
                    <div id="passCapChartDate" class="clearfix fr"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10 mt25">
                        <li class="clearfix">
                            <span class="fl lastYears"></span>
                            <span class="fl">日均：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapAvgLastYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl thisYears"></span>
                            <span class="fl">日均：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapAvgYear">...</b>
                        </li>
                        <li class="clearfix">
                            <!--
                            <span class="fl">日均管控：</span>
                            <h6>万人</h6>
                            <b class="fr mr5" id="passCapAvgControl">...</b>
                            -->
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl thisYears"></span>
                            <span class="fl">峰值：</span>
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
                <td colspan="2" class="statTd">
                    <h3 style="font-size:14px;" class="fl"><img src="pointer.png" style="display: inline;"/>&nbsp;客运收入：</h3>

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

                    <div class="clearfix fl">
                        <h1 class="fl mouse" id="incomeDaily">...</h1>
                        <h6 class="pt10" style="float:left;">万元</h6>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="4" style="width:65%;">
                    <div id="incomeTotalChart"></div>
                </td>
                <td class="pt5" width="160">
                    <div class="border_gary fr">&nbsp;<span id="incomeChart"></span></div>
                    <div id="incomeChartDate" class="clearfix fr"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10 mt25">
                        <li class="clearfix">
                            <span class="fl lastYears"></span>
                            <span class="fl">日均：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeAvgLastYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl thisYears"></span>
                            <span class="fl">日均：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeAvgYear">...</b>
                        </li>
                        <li class="clearfix">
                            <!--
                            <span class="fl">日均管控：</span>
                            <h6>万元</h6>
                            <b class="fr mr5" id="incomeAvgControl">...</b>
                            -->
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl thisYears"></span>
                            <span class="fl">峰值：</span>
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
                <td colspan="2" class="statTd">
                    <h3 style="font-size:14px;" class="fl"><img src="pointer.png" style="display: inline;"/>&nbsp;运营里程：</h3>

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

                    <div class="clearfix fl">
                        <h1 class="fl mouse" id="metroDistanceDaily">...</h1>
                        <h6 class="pt10" style="float:left;">万车公里</h6>
                    </div>
                </td>
            </tr>
            <tr>
                <td rowspan="4" style="width:65%;">
                    <div id="metroDistanceTotalChart"></div>
                </td>
                <td class="pt5" width="160">
                    <div class="border_gary fr ">&nbsp;<span id="metroDistanceChart"></span></div>
                    <div id="metroDistanceChartDate" class="clearfix fr"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10 mt25">
                        <li class="clearfix">
                            <span class="fl lastYears"></span>
                            <span class="fl">日均：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceAvgLastYear">...</b>
                        </li>
                        <li class="clearfix">
                            <span class="fl thisYears"></span>
                            <span class="fl">日均：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceAvgYear">...</b>
                        </li>
                        <li class="clearfix">
                            <!--
                            <span class="fl">日均管控：</span>
                            <h6>万车公里</h6>
                            <b class="fr mr5" id="metroDistanceAvgControl">...</b>
                            -->
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <ul class="mb10">
                        <li class="clearfix">
                            <span class="fl thisYears"></span>
                            <span class="fl">峰值：</span>
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
</div>
<!--Panel_8 End-->

<!--Panel_8-->
<div class="panel_4 mb10 panel_8">
    <header>
        <div class="tit">
            <div class="bg clearfix">
                <h5 class="fl stats">用能指标（调试中）</h5>
            </div>
        </div>
    </header>
    <div class="con clearfix">
        <div class="fl w50p">
            <div class="gray_border">
                <div class="block">
                    <div class="corner h195">
                        <dl class="b_bor" style="font-size:13px;">
                            <div class="clear" style="padding-bottom:10px;"></div>
                            <table width="100%" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>牵引电量</td>
                                    <td>
                                        <div class="fr clearfix">单位：万度</div>
                                    </td>
                                </tr>
                            </table>
                            <style>
                                .source {
                                    border: 1px solid #8B8386;
                                }

                                .source tr:first-child {
                                    background-color: rgb(209, 238, 250);
                                }

                                .source tr:first-child td:first-child, .source tr:first-child td + td + td + td {
                                    color: #000;
                                }

                                .source tr td:first-child {
                                    padding-left: 20px;
                                    text-align: left;
                                    color: #979797;
                                }

                                .source tr td + td + td + td {
                                    padding-right: 20px;
                                    text-align: right;
                                    color: #0664ff;
                                }

                                .source tr td {
                                    text-align: right;
                                }
                            </style>
                            <div class="clear" style="padding-bottom:20px;"></div>
                            <table class="source" align="center" width="90%" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td>运营公司</td>
                                    <td>年累计</td>
                                    <td>考核指标</td>
                                    <td>完成率</td>
                                </tr>
                                <tr>
                                    <td>第一运营公司</td>
                                    <td id="y1">0</td>
                                    <td id="y1c">25501</td>
                                    <td id="y1p">0%</td>
                                </tr>
                                <tr>
                                    <td>第二运营公司</td>
                                    <td id="y2">0</td>
                                    <td id="y2c">22244</td>
                                    <td id="y2p">0%</td>
                                </tr>
                                <tr>
                                    <td>第三运营公司</td>
                                    <td id="y3">0</td>
                                    <td id="y3c">17091</td>
                                    <td id="y3p">0%</td>
                                </tr>
                                <tr>
                                    <td>第四运营公司</td>
                                    <td id="y4">0</td>
                                    <td id="y4c">9383</td>
                                    <td id="y4p">0%</td>
                                </tr>
                                <tr>
                                    <td>磁浮公司</td>
                                    <td id="cf">0</td>
                                    <td id="cfc">0</td>
                                    <td id="cfp">0%</td>
                                </tr>


                            </table>
                        </dl>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="fl w50p">
            <div class="gray_border">
                <div class="block">
                    <div class="corner h195">
                        <dl class="b_bor">
                            <iframe id="resourceIframe" height=180px; width="100%" frameborder="no" border="0"
                                    marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
                        </dl>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<!--Panel_8 End-->


<!--Panel_3-->
<div class="panel_3 mb10" style="display:none;">
    <header class="clearfix">
        <div class="bg_3 clearfix">
            <h5 class="fl file">运营报告</h5>

            <div class="fr tabs_1">
                <ul>
                    <li class="reportLi"><a href="javascript:void(0);"><span>领导登乘</span></a></li>
                    <li class="reportLi"><a href="javascript:void(0);"><span>每日签报</span></a></li>
                    <li class="reportLi"><a href="javascript:void(0);"><span>运营日报</span></a></li>
                    <li class="reportLi"><a href="javascript:void(0);"><span>运营分析</span></a></li>
                </ul>
            </div>
        </div>
    </header>
    <div class="con">
        <div class="reportDiv clearfix">
            <ul class="columns clearfix mb10">

            </ul>
            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
        </div>
        <div class="reportDiv clearfix">
            <ul class="columns clearfix mb10">

            </ul>
            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
        </div>
        <div class="reportDiv clearfix">
            <ul class="columns clearfix mb10">

            </ul>
            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
        </div>
        <div class="reportDiv clearfix">
            <ul class="columns clearfix mb10">

            </ul>
            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
        </div>
    </div>
</div>
<!--Panel_3 End-->
<!--Panel_3-->
<div class="panel_3 mb10">
    <header class="clearfix">
        <div class="bg_3 clearfix">
            <h5 class="fl train">运营速报</h5>

            <div class="fr pt5 mr5 clearfix">
                <!-- <ul class="fl clearfix A_dot">
                    <li class="red"><a href="#">一级</a></li>
                    <li class="orange"><a href="#">一级</a></li>
                    <li class="yellow"><a href="#">一级</a></li>
                    <li class="blue"><a href="#">一级</a></li>
                </ul> -->
                <select id="expressLine" class="input_small mr5 fr">

                </select>
            </div>
        </div>
    </header>
    <div class="con">
        <table id="expressTable" width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="50%">

                </td>
                <td>

                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>

                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>

                </td>
            </tr>
        </table>
        <p id="expressMore" class="fr"><a class="more_3" href="/portal/metroExpress/findMetroExpressListByPage.action"
                                          target="_self">更多</a></p>
    </div>
</div>
<!--Panel_3 End-->
</div>

<!--Aside-->
<aside class="fr">
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup>
                    <h5>安全隐患排查跟踪</h5>
                    <!--<h6>Topic</h6>-->
                    <a href="http://10.1.48.40/stptm/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?importance=0"
                       class="more_1" target="_self">更 多</a>
                </hgroup>
                <ul class="topic">
                    <li><a target="_self"
                           href="http://10.1.48.40/stptm/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?importance=0">运营安全隐患问题整改推进表</a>
                    </li>
                    <!--
                    <li><a target="_self" href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/prophase/xyhpc_search.do?b_query=true&which_table=1">新线遗留设施设备隐患整改推进表</a></li>
                    -->
                </ul>
            </div>
        </div>
    </div>

    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup class="asideH">
                    <h5>一周重点关注</h5>
                    <a target="_self" href="#" class="more_1">更 多</a>
                </hgroup>
                <ul class="asideUl list">
                </ul>
            </div>
        </div>
    </div>

    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup class="asideH">
                    <h5>新线试运营演练情况</h5>
                    <a target="_self" href="#" class="more_1">更 多</a>
                </hgroup>
                <ul class="asideUl list">
                </ul>
            </div>
        </div>
    </div>

    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup class="asideH">
                    <h5>行车安全和应急管理</h5>
                    <a target="_self" href="#" class="more_1">更 多</a>
                </hgroup>
                <ul class="asideUl list">
                </ul>
            </div>
        </div>
    </div>

    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup class="asideH">
                    <h5>施工组织公告</h5>
                    <a target="_self" href="#" class="more_1">更 多</a>
                </hgroup>
                <div id="week" style="text-align:left;margin:5px 10px;font-weight:bold;">本周[<%=start %>~<%=end %>]施工信息
                </div>
                <ul class="asideUl list">
                </ul>
            </div>
        </div>
    </div>

    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup class="asideH">
                    <h5>舆情分析</h5>
                    <a target="_self" href="#" class="more_1">更 多</a>
                </hgroup>
                <ul class="asideUl list">
                </ul>
            </div>
        </div>
    </div>
</aside>
<!--Aside End-->
</div>
</div>
</body>
</html>
