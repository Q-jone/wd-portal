<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8"/>
    <title>超时统计</title>
    <link rel="stylesheet" href="../css/formalize.css"/>

    <link rel="stylesheet" href="../css/page.css"/>
    <link rel="stylesheet" href="../css/default/imgs.css"/>
    <link rel="stylesheet" href="../css/reset.css"/>
    <!--[if IE 6.0]>
    <script src="../js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
        EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
    <![endif]-->
    <script type="text/javascript" src="/portal/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="js/highcharts4.0.1.js"></script>
<style>
    #deptTb li {
        float:left; /* 往左浮动 */
        line-height: 30px;
    }
</style>

</head>

<body>
<div class="main mw1002">
    <!--Ctrl-->
    <div class="ctrl clearfix">
        <div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg"
                             width="46" height="30" alt="展开"></div>
        <div class="posi fl">
            <ul>
                <li><a href="javascript:window.location.href='/portal/center/yygl/yg_index.jsp'">超时统计</a></li>
                <li class="fin">首页</li>
            </ul>
        </div>

    </div>
    <!--Ctrl End-->
    <div class="clearfix pt45">
        <div class="mb10">

            <!--Panel_8-->
            <div class="panel_4 mb10 panel_8">
                <header>
                    <div class="tit">
                        <div class="bg clearfix">
                            <h5 class="fl stats">超时时长统计</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">

                    <div class="gray_border">
                        <div class="block">
                            <div id="pie" style="min-width:600px;height:300px"></div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="panel_4 mb10 panel_8">
                <header>
                    <div class="tit">
                        <div class="bg clearfix">
                            <h5 class="fl stats">超时部门统计</h5>

                        </div>
                    </div>
                </header>
                <div class="con clearfix">

                    <div class="gray_border">
                        <div class="block">
                            <div id="column" style="min-width:600px;height:300px;margin:10px"></div>

                            <div style="border:1px;width:100%;float:left;">

                                <table width="100%" class="table_1" id="mytable">
                                    <thead>
                                    <tr class="tit">
                                        <td class="t_c" width="20%">部门名称</td>
                                        <td class="t_c" width="10%">超时事项</td>
                                        <td class="t_c" width="10%">延时申请</td>
                                        <td class="t_c" width="10%">百分比</td>
                                        <td class="t_c" width="20%">部门名称</td>
                                        <td class="t_c" width="10%">超时事项</td>
                                        <td class="t_c" width="10%">延时申请</td>
                                        <td class="t_c" width="10%">百分比</td>
                                    </tr>
                                    </thead>
                                </table>
                                <div style="width:100%">
                                    <ul id="deptTb"  style="text-align: center;width:100%;float:left">

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>

        </div>
    </div>
</div>
<script>
    $(function () {

        //超时时长统计
        $.post("/portal/delayWarn/delayDays.action", function (data) {
            var pieData = [];
            $.each(data, function (i, value) {
                pieData[i] = [value.itemName, value.percent];
            });
            $('#pie').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: ''
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        <s:if test="#parameters.a[0]==\"1\"">

                        point:{
                            events:{
                                click:function(e){
                                    location.href="/portal/delayWarn/delayDetail.action?item="+e.point.name+"&type=dept";
                                }
                            }
                        },</s:if>
                        dataLabels: {
                            enabled: true,
                            color: '#000000',
                            connectorColor: '#000000',
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                        },
                        showInLegend: true
                    }
                },
                series: [
                    {
                        type: 'pie',
                        name: '占',
                        data: pieData
                    }
                ]
            });


        }, "json");

        //超时部门统计
        $.post("/portal/delayWarn/delayDepartment.action", function (data) {
            var columnData = [];
            var tbData = "";
            $.each(data, function (i, value) {
                columnData[i] = {"name": value.itemName, "y": value.itemQuantity};
                //添加表格数据
                tbData+="<li style='width:20%'><a href='/portal/delayWarn/delayDetail.action?item="+value.itemName+"&type=day'>"+value.itemName+"</a></li>"+"<li style='width:10%'>"+value.itemQuantity+"</li>"+"<li style='width:10%'>"+value.applayQuantity+"</li>"+"<li style='width:10%'>"+value.percent+"%</li>"

            });
            $("#deptTb").html(tbData);

            $('#column').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: ''
                },
                xAxis: {type: 'category', labels: {enabled: true, rotation: 300}
                },
                yAxis: {
                    min: 0, tickInterval: 10,
                    title: {
                        text: '超时流程数'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y} 个</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.1,
                        borderWidth: 0,
<s:if test="#parameters.a[0]==\"1\"">
                        point:{
                            events:{
                                click:function(e){
                                    location.href="/portal/delayWarn/delayDetail.action?item="+e.point.name+"&type=day";
                                }
                            }
                        }</s:if>
                    }
                },
                series: [
                    {
                        name: '超时流程',
                        data: columnData

                    }
                ]
            });


        }, "json");
    });
</script>
</body>
</html>
