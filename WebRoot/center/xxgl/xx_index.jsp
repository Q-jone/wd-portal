﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"/>
<title>信息管理首页</title>
<link rel="stylesheet" href="../../css/formalize.css"/>

<link rel="stylesheet" href="../../css/page.css"/>
<link rel="stylesheet" href="../../css/default/imgs.css"/>
<link rel="stylesheet" href="../../css/reset.css"/>
<link rel="stylesheet" href="../../css/doc.css"/>
<link rel="stylesheet" href="../../css/jquery.marquee.min.css"/>
<link type="text/css" href="../../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
<link rel="stylesheet" href="../../js/mopTip/mopTip-2.2.css"/>
<link rel="stylesheet" href="../../js/mopTip/commons.css"/>
<!--[if IE 6.0]>
<script src="js/iepng.js" type="text/javascript"></script>
<script type="text/javascript">
    EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
</script>
<![endif]-->
<script src="../../js/jquery-1.7.1.js"></script>
<script src="../../js/jquery.marquee.min.js"></script>
<script src="../../js/mopTip/mopTip-2.2.1.js"></script>
<script src="../../js/html5.js"></script>
<script src="../../js/jquery.formalize.js"></script>
<script src="../../js/copy.js"></script>
<script src="../../js/font.js"></script>
<script src="../../js/jquery.pngFix-1.2.js"></script>
<script src="../../js/show.js"></script>
<script src="js/xxgl_ajax.js"></script>
<script type="text/javascript">
var t;
function showAuto() {
    var listTmp = $("#play_list li").filter(function () {
        return $(this).css('display') != 'none';
    });
    var infoTmp = $("#play_info li").filter(function () {
        return $(this).css('display') != 'none';
    });
    var textTmp = $("#play_text li.current");
    //alert(listTmp.html());
    $("#play_list li").hide();
    $("#play_info li").hide();
    $("#play_text li").removeClass("current");
    if (listTmp.next().length == 0) {
        $("#play_list li").eq(0).show();
        $("#play_info li").eq(0).show();
        $("#play_text li").eq(0).addClass("current");
    } else {
        listTmp.fadeOut(500).next().fadeIn(1000);
        infoTmp.fadeOut(500).next().fadeIn(1000);
        textTmp.next().addClass("current");
    }

    //
}

$(document).ready(function () {
    var cur = 0;
    var len = $(".next a").parent().prev("ul").children(".reportLi").length;
    var page = 2;

    $(".reportLi").each(function (i, n) {
        $(n).click(function () {
            $(n).siblings("li").removeClass("selected");
            $(n).addClass("selected");
            //$(n).parent().parent().parent().parent().next().children(".reportDiv").hide();
            $(n).parents(".panel_3").find(".reportDiv").hide();
            $(".reportDiv:eq(" + i + ")").show();
        });
    });

    $(".next a").click(function () {
        $(this).parent().prev("ul").children(".reportLi").hide();
        for (var i = cur; i < cur + page; i++) {
            //alert(i);
            $(this).parent().prev("ul").children(".reportLi").eq(i + 1).show();
        }
        //$(this).parent().parent().children(".reportLi").eq(cur+1).click();

        if (cur + page == len - 1) {
            $(this).parent().hide();
        } else {
            $(".pre").show();
        }

        cur++;

    });

    $(".pre a").click(function () {
        $(this).parent().next("ul").children(".reportLi").hide();
        for (var i = cur; i < cur + page; i++) {
            $(this).parent().next("ul").children(".reportLi").eq(i - 1).show();
        }
        //$(this).parent().parent().children(".reportLi").eq(cur).click();

        if (cur - 1 == 0) {
            $(this).parent().hide();
        } else {
            $(".next").show();
        }

        cur--;
    });

    $(".pre").hide();
    $(".next a").parent().prev("ul").children(".reportLi:gt(1)").hide();

    $("#play_list").hover(function () {
        clearInterval(t);
    }, function () {
        t = setInterval("showAuto()", 6000);
    });

    $("#play_text li").live("click", function () {
        var pos = $(this).html();
        $("#play_list li").hide();
        $("#play_info li").hide();
        $("#play_text li").removeClass("current");
        $("#play_list li").eq(pos - 1).show();
        $("#play_info li").eq(pos - 1).show();
        $(this).addClass("current");
    });

    getHeadNews("1363");
    getXWTTNews("1380");
    getXWTTPicNews("1380");
    getLatestNews("1936", "", "1");
    getLatestNews("1995", "", "2");
    getLatestNews("1994", "", "3");
    getLatestNews("1996", "", "4");
    getLatestNews("1954", "", "5");
    getLatestNews("1974", "", "6");

    getLatestNews("2055", "", "7");//信息前沿
    getLatestNews("2037", "", "8");//应用指南
    getLatestNews("2038", "", "9");//项目建设
    getLatestNews("2039", "", "10");//运维情况

    $(".reportLi:eq(0)").click();
    $(".reportLi:eq(1)").click();
    $(".reportLi:eq(5)").click();
    $(".reportLi:eq(8)").click();
    $(".reportLi:eq(10)").click();
    $("#newsCenter").width($(".news").width() - $("#play").width() - 40);

    $(window).resize(function () {
        $("#newsCenter").width($(".news").width() - $("#play").width() - 40);
    });

    //loadShow();
    t = setInterval("showAuto()", 6000);

    $("#infoId").css("top", ($(document).height() - 100) + "px").css("cursor", "pointer").show().click(function () {
        window.open("http://10.1.48.101:7001/pdfPreview/preview/infoProject.jsp");
    });


    $.post('<s:url action="marquee" namespace="/project/index"/>',function(data) {
        var html="";
        $.each(data.eventList,function(i,n){
            html=html+ "【信息安全事件】"+ n[0]+"公司事件共<span style='color:red;display:inline'>"+n[1]+"</span>件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        });
        $.each(data.riskList,function(i,n){
            html=html+"【安全风险管理】"+ n.department+"本月发现<span style='color:red;display:inline'>"+n.creator+"</span>个风险"+(n.riskInfo != "" ? ",高风险有<span style='color:red;display:inline'>"+n.riskInfo+"</span>个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        });
        $.each(data.inspectList,function(i,n){
            html=html+"【安全内控问题】"+ n.department+"本月发现<span style='color:red;display:inline'>"+n.creator+"</span>个问题"+(n.tractMemo != "" ? ",严重的有<span style='color:red;display:inline'>"+n.tractMemo+"</span>个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" : "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        });
        $.each(data.notBegin,function(i,n){
            html=html+"【等保测评工作】"+ n.department+"的"+ n.sysName+"计划内未开始"+(n.year>0?",延期<span style='color:red;display:inline'>"+n.year+"</span>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;":"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        });
        $.each(data.notEnd,function(i,n){
            html=html+ "【等保测评工作】"+ n.department+"的"+ n.sysName+"计划内未完成"+(n.year>0?",延期<span style='color:red;display:inline'>"+n.year+"</span>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;":"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");

        });
        $.each(data.periodEx,function(i,n){
            html=html+ "【信息化项目推进情况】"+ n.projectName+"已经阶段延期,目前处于"+ n.creator+"阶段,延期<span style='color:red;display:inline'>"+n.finish+"</span>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        });
        $.each(data.planEx,function(i,n){
            html=html+ "【信息化项目推进情况】"+ n.projectName+"已经计划延期,目前处于"+ n.creator+"阶段,延期<span style='color:red;display:inline'>"+n.finish+"</span>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        });
        
        
        $("#marquee > li").append(html);
        $("#marquee").marquee({scrollSpeed: 12,yScroll:"bottom" });
    },"json");


    //信息上墙
    $.post('<s:url action="index" namespace="/project/work"/>',function(data) {
        $("#workSeclude").find("h3[name=total]").text(data.total);
        $("#workSeclude").find("h3[name=normal]").text(data.normal);
        $("#workSeclude").find("h3[name=exception]").text(data.exception);
        createTipsHtml(data.exceptionObjs,"#workSecludeException",$("#workExceptionObj"),"seclude");
    },"json");
    //事件
    $.post('<s:url action="index" namespace="/project/event"/>',function(data) {
        $("#event").find("h3[name=yeartotal]").text(data.yeartotal);
        $("#event").find("h3[name=eventstotal]").text(data.eventstotal);
        $("#event").find("h3[name=monthtotal]").text(data.monthtotal);
        createTipsHtml(data.monthObj,"#eventException",$("#eventExceptionObj"),"event");
    },"json");

    $.post('<s:url action="index" namespace="/project/security"/>',function(data) {
        $("#workSecurity").find("h3[name=total]").text(data.total);
//        $("#workSecurity").find("h3[name=normal]").text(data.normal);
        $("#workSecurity").find("h3[name=completed]").text(data.completed);
//        $("#workSecurity").find("h3[name=inPlanNotBegin]").text(data.inPlanNotBegin);
        $("#workSecurity").find("h3[name=inPlanNotEnd]").text(data.inPlanNotEnd);
//        createTipsHtml(data.inPlanNotBeginObj,"#inPlanNotBeginException",$("#inPlanNotBeginObj"),"security");
        createTipsHtml(data.inPlanNotEndObj,"#inPlanNotEndException",$("#inPlanNotEndObj"),"security");
    },"json");

    $.post('<s:url action="index" namespace="/project/inspect"/>',function(data) {
        $("#inspect").find("h3[name=total]").text(data.total);
        $("#inspect").find("h3[name=normal]").text(data.normal);
        $("#inspect").find("h3[name=increase]").text(data.increase);
        $("#inspect").find("h3[name=history]").text(data.history);

        createTipsHtml(data.historyObj,"#inspectHistory",$("#inspectHistoryObj"),"inspect");
        createTipsHtml(data.increaseObj,"#inspectIncrease",$("#inspectIncreaseObj"),"inspect");
    },"json");

    $.post('<s:url action="index" namespace="/project/risk"/>',function(data) {
        $("#risk").find("h3[name=total]").text(data.total);
        $("#risk").find("h3[name=normal]").text(data.normal);
        $("#risk").find("h3[name=increase]").text(data.increase);
        $("#risk").find("h3[name=history]").text(data.history);

        createTipsHtml(data.historyObj,"#riskHistory",$("#riskHistoryObj"),"risk");
        createTipsHtml(data.increaseObj,"#riskIncrease",$("#riskIncreaseObj"),"risk");

    },"json");

    $.post('<s:url action="index" namespace="/project/sysinfo"/>',function(data) {
        $("#sysinfo").find("h3[name=total]").text(data.total);
        $("#sysinfo").find("h3[name=normalP]").text((data.normal/data.total*100).toFixed(2));
        $("#sysinfo").find("h3[name=planExceptionP]").text((data.planException/data.total*100).toFixed(2));
        $("#sysinfo").find("h3[name=periodExceptionP]").text((data.periodException/data.total*100).toFixed(2));
        $("#sysinfo").find("h3[name=normal]").text(data.normal);
        $("#sysinfo").find("h3[name=planException]").text(data.planException);
        $("#sysinfo").find("h3[name=periodException]").text(data.periodException);

        $("#le").find("h3[name=p1]").text(data.p1);
        $("#le").find("h3[name=p2]").text(data.p2);
        $("#le").find("h3[name=p3]").text(data.p3);
        $("#le").find("h3[name=completed]").text(data.completed);
        createTipsHtml(data.planExceptionObjs,"#planException",$("div[name='planExceptionObj']"),"sysinfo");
        createTipsHtml(data.periodExceptionObjs,"#periodException",$("div[name='periodExceptionObj']"),"sysinfo");

    },"json");

});

function createTipsHtml(data,exception,target,type){
    var html = "<ul>";
    if(data.length > 0){
        $.each(data,function(i,n){
            if(i>2)
            return;
            if("sysinfo" ==  type)
                html+= "<li>"+n.projectName+"</li>";
            if("seclude" ==  type)
                html+= "<li>"+n.workName+"</li>";
            if("risk" ==  type)
                html+= "<li>"+n.department+"</li>";
            if("inspect" ==  type)
                html+= "<li>"+n.department+"</li>";
            if("security" ==  type)
                html+= "<li>"+n.sysName+"</li>";
            if("event"==type)
            	html+= "<li>"+n.companyName+"</li>";
        });
        if(data.length > 0){
            if(data.length == 2){
                target.mopTip({'w':150,'style':"overOut",'get':exception,'yOff':-90});
            }else if(data.length == 1){
                target.mopTip({'w':150,'style':"overOut",'get':exception,'yOff':-70});

            }else{
                target.mopTip({'w':150,'style':"overOut",'get':exception,'yOff':-110});
            }
        }


    }
    html+="</ul>";
    $(exception).html(html);
}

</script>
<style>
    .gray_border {
        border-bottom: #d4d2cd 1px solid;
        border-right: #d4d2cd 1px solid;
        
    }

    .block {
        background-color: #efeeee;
        border: #fbfaf8 1px solid;
        /*background-image: url(../../css/default/images/panel_8.png);*/
    }

    .categories a {
        background-image: url(../../css/default/images/panel_8.png);
        background-position: right -995px;
        padding-right: 12px;
        margin-bottom: 3px;
        margin-right: 12px;
        background-repeat: no-repeat;
    }

    .lh {
        line-height: 25px;
    }
    .h125{
        height:146px;
    }

.mf25{

    margin-left: 25px;
}

</style>
</head>

<body>
<%--<img id="infoId" src="./js/ip.png" style="display:none;position:absolute;right:100px;z-index:9999" />--%>

<div class="main mw1070">
<!--Ctrl-->
<div class="ctrl clearfix">
    <div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg"
                         width="46" height="30" alt="展开"></div>
    <div class="posi fl">
        <ul>
            <li><a href="javascript:window.location.href='/portal/center/xxgl/xx_index.jsp'">信息管理</a></li>
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
    <!--News-->
    <div class="news clearfix">
        <div class="news_r">
            <div class="news_l">
                <!--focusPic-->
                <div class="focusPic fl" id="play">
                    <ul class="fp_list" id="play_list">
                    </ul>
                    <ul class="word" id="play_info">
                    </ul>
                    <ul class="scrollnav" id="play_text">
                    </ul>
                </div>
                <!--focusPic End-->
                <!--Txt News-->
                <div class="fl" id="newsCenter">
                    <hgroup class="clearfix mb10">
                        <h2 class="fl">信息管理</h2>
                        <h6 class="fl">News</h6>

                        <p class="t_r"><a href="#" target="_self" class="more_2">更多</a></p>
                    </hgroup>
                    <section>
                        <a href="#" target="_blank">
                            <h5></h5>

                            <p></p>
                        </a>
                    </section>
                    <ul class="txt_list">
                        <li><a href="#"></a><span></span></li>

                    </ul>
                </div>
                <!--Txt News End-->
            </div>
        </div>
    </div>
    <!--News End-->
</div>
<!--Aside-->
<aside>
    <div class="panel_1">
        <div class="bg_2">
            <div class="bg_3">
                <hgroup>
                    <h3>业务申请</h3>
                    <a href="/portal/infoSearch/findStfbNewsByPage.action?sj_id=2037" target="_blank"
                       class="more_1">帮助</a>
                </hgroup>
                <div class="con">
                    <ul class="xx clearfix mb10">
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/software/stpt_approval.xls" target="_blank">
                                <dl>
                                    <dt class="d1"></dt>
                                    <dd>协同后台<br>修改申请</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/downloadFile.action?fileId=87942" target="_blank">
                                <dl>
                                    <dt class="d2"></dt>
                                    <dd>无线网络<br>申请</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/downloadFile.action?fileId=87928" target="_blank">
                                <dl>
                                    <dt class="d3"></dt>
                                    <dd>VPN网络<br>接入申请</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/downloadFile.action?fileId=87929" target="_blank">
                                <dl>
                                    <dt class="d4"></dt>
                                    <dd>信息化<br>资源申请</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/software/degreeProtectInfo.doc" target="_blank">
                                <dl>
                                    <dt class="d5"></dt>
                                    <dd>等级保护<br>备案表</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.48.16:7001/iims/projectPlan/projectApplicationEdit" target="_blank">
                                <dl>
                                    <dt class="d6"></dt>
                                    <dd>信息化<br>项目申报</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/downloadFile.action?fileId=5289536" target="_blank">
                                <dl>
                                    <dt class="d8"></dt>
                                    <dd>信息化<br>支出报备</dd>
                                </dl>
                            </a>
                        </li>
                        <li style="margin-bottom:18px;">
                            <a href="http://10.1.44.18/software/citrix.html" target="_blank">
                                <dl>
                                    <dt style="background-image:url('/portal/css/default/images/citrix.png')"></dt>
                                    <dd>移动办公<br>应用申请</dd>
                                </dl>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</aside>
<!--Aside End-->
<div class="clear"></div>
<div class="clear"></div>
<table width="100%" cellpadding="0" cellspacing="0" border="0">

<tr><td colspan="2">
<table width="100%">
    <tr>
        <td colspan="2">

            <div class="panel_3 mb10" style="margin-left: 10px;">

                <table width="100%" class="p0">
                    <tbody>
                    <tr>
                        <td>
                            <div class="gray_border">
                                <div  class="block h125"style="height:25px;">
                                    <div>
                                        <div class="mr5">
                                            <div class="lh">
                                                <ul id="marquee"  class="marquee">
                                                    <li>



                                                    </li>
                                                </ul>

                                        </div>
                                    </div>
                                </div>
                            </div>
</div>
                        </td>
                    </tr>
                    </tbody></table>
            </div>
        </td>
    </tr>
    <tr>
        <td width="75%">
            <div class="panel_3 mb10" style="margin-right: 20px;">
                <header class="clearfix">
                    <h5 class="fl file">重点工作情况</h5>
                </header>

                <table width="100%" class="p0">
                    <tbody>
                    <tr>
                        <td width="33%">
                            <div class="gray_border">
                                <div class="block h125" style="height:199px">
                                    <div>
                                        <div class="categories t_r mr5"><a href="<s:url value="/project/work/report.action?workSeclude.year=2014&workSeclude.confirm=1"/>">重点工作推进情况</a></div>
                                        <div class="clear"></div>
                                        <div class="mr5">
                                            <div class="lh" id="workSeclude">
                                                <div class="clearfix"><span class="fl mr8">本年度工作总数</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="total"></h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">正常推进的工作</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="normal"></h3>
                                                </div>
                                                <div class="clearfix L_01" id="workExceptionObj"><span class="fl mr8">尚未推进的工作</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="exception"></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>
                        <td width="33%">
                            <div class="gray_border">
                                <div class="block h125" style="height:199px">
                                    <div>
                                        <div class="categories t_r mr5"><a href="<s:url value="/project/sysinfo/report.action"/>">信息化项目推进情况</a></div>
                                        <div class="clear"></div>
                                        <div class="mr5">
                                            <div class="lh" id="sysinfo">
                                                <div class="clearfix"><span class="fl mr8">本年度项目总数</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="total"></h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">正常开展项目数量</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="normal"></h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">正常开展项目百分比</span> <span class="fr">%</span>

                                                    <h3 class="fr" name="normalP"></h3>
                                                </div>
                                                <div class="clearfix L_01"  name="planExceptionObj"><span class="fl mr8">节点延期项目数量</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="planException"></h3>
                                                </div>
                                                <div class="clearfix L_01" name="periodExceptionObj"><span class="fl mr8">节点延期项目百分比</span> <span class="fr">%</span>

                                                    <h3 class="fr" name="planExceptionP"></h3>
                                                </div><div class="clearfix L_01"  name="planExceptionObj"><span class="fl mr8">里程碑延期项目数量</span> <span class="fr">个</span>

                                                <h3 class="fr" name="periodException"></h3>
                                            </div>
                                                <div class="clearfix L_01" name="periodExceptionObj"><span class="fl mr8">里程碑延期项目百分比</span> <span class="fr">%</span>

                                                    <h3 class="fr" name="periodExceptionP"></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="gray_border">
                                <div class="block h125" style="height:199px">
                                    <div>
                                        <div class="categories t_r mr5"><a href="<s:url value="/project/security/report.action"/>">等保测评工作情况</a></div>
                                        <div class="clear"></div>
                                        <div class="mr5">
                                            <div class="lh" id="workSecurity">
                                                <div class="clearfix"><span class="fl mr8">本年度需测评系统总数</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="total"></h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">已完成测评系统数</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="completed"></h3>
                                                </div>
                                                <div class="clearfix L_01" id="inPlanNotEndObj"><span class="fl mr8">计划内未完成的系统数</span> <span
                                                        class="fr">个</span>

                                                    <h3 class="fr" name="inPlanNotEnd"></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody></table>
            </div>
        </td>

        <td width="25%">

            <div class="panel_3 mb10" style="margin-left: 0px;">
                <header class="clearfix">
                    <h5 class="fl file">专项工作情况</h5>
                </header>

                <table width="100%" class="p0">
                    <tbody>
                    <tr>
                        <td>
                            <div class="gray_border">
                                <div class="block h125" style="height:199px">
                                    <div>
                                        <div class="categories t_r mr5"><a href="<s:url value="/project/terminal/report.action"/>">终端隔离工作本部实施情况</a></div>
                                        <div class="clear"></div>
                                        <div class="mr5">
                                            <div class="lh" name="sysinfo">
                                                <div class="clearfix"><span class="fl mr8">涉及部门总数</span> <span class="fr">个</span>

                                                    <h3 class="fr">17</h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">当前所属阶段</span> <span class="fr">管理网终端入网</span>

                                                    <h3 class="fr"></h3>
                                                </div>
                                                    <%--<div class="clearfix "><span class="fl mr8">现阶段已完成部门</span> <span class="fr">个</span>--%>

                                                    <%--<h3 class="fr"></h3>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="clearfix "><span class="fl mr8">现阶段进行中部门</span> <span class="fr">个</span>--%>

                                                    <%--<h3 class="fr"></h3>--%>
                                                    <%--</div>--%>
                                                    <%--<div class="clearfix L_01"><span class="fl mr8">现阶段已延迟部门</span> <span class="fr">个</span>--%>

                                                    <%--<h3 class="fr">0</h3>--%>
                                                    <%--</div>--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>
                    </tr>
                    </tbody></table>
            </div>
        </td>
    </tr>
</table>

    </td>
</tr>

<tr>

    <td width="50%">
        <div class="panel_3 mb10">
            <header class="clearfix">
                <h5 class="fl file">信息化台帐</h5>
            </header>

            <table width="100%" class="p0">
                <tbody>
                <tr>
                    <td width="25%">
                        <div class="gray_border">
                            <div class="block h125" style="height:139px;">
                                <div>
                                    <div class="categories t_r mr5"><a href="<s:url value="/project/sysinfo/ledger.action"/>">信息化项目台帐</a></div>
                                    <div class="clear"></div>
                                    <div class="mr5">
                                        <div class="lh" id="le">
                                            <div class="clearfix"><span class="fl mr8">前期策划阶段</span> <span class="fr">个</span>

                                                <h3 class="fr" name="p1"></h3>
                                            </div>
                                            <div class="clearfix"  ><span class="fl mr8">立项采购阶段</span> <span class="fr">个</span>

                                                <h3 class="fr" name="p2"></h3>
                                            </div>
                                            <div class="clearfix"><span class="fl mr8">实施推进阶段</span> <span class="fr">个</span>

                                                <h3 class="fr" name="p3"></h3>
                                            </div>
                                            <div class="clearfix"><span class="fl mr8">项目已完成</span> <span class="fr">个</span>

                                                <h3 class="fr" name="completed"></h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </td>
                   
                   
                   
                    <s:if test="#session.loginName == 'G020001000492549' || #session.loginName == 'G001000001702549' || #session.loginName == 'ADMIN2549' || #session.loginName =='G001000001612549'">
                        <td width="25%">
                            <div class="gray_border">
                                <div class="block h125" style="height:139px;">
                                    <div>
                                        <div class="categories t_r mr5"><a href="<s:url value="/project/inspect/report.action"/>">安全内控检查问题台帐</a></div>
                                        <div class="clear"></div>
                                        <div class="mr5">
                                            <div class="lh" id="inspect">
                                                <div class="clearfix"><span class="fl mr8">年度问题总数</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="total"></h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">已消除问题</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="normal"></h3>
                                                </div>
                                                <div class="clearfix L_01" id="inspectHistoryObj"><span class="fl mr8">未消除问题</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="history"></h3>
                                                </div>
                                                <div class="clearfix L_01" id="inspectIncreaseObj"><span class="fl mr8">本月新增</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="increase"></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td width="25%">
                            <div class="gray_border">
                                <div class="block h125" style="height:139px;">
                                    <div>
                                        <div class="categories t_r mr5"><a href="<s:url value="/project/risk/report.action"/>">安全风险管理台帐</a></div>
                                        <div class="clear"></div>
                                        <div class="mr5">
                                            <div class="lh" id="risk">
                                                <div class="clearfix"><span class="fl mr8">本年度风险总数</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="total"></h3>
                                                </div>
                                                <div class="clearfix"><span class="fl mr8">已验证风险</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="normal"></h3>
                                                </div>
                                                <div class="clearfix L_01" id="riskHistoryObj"><span class="fl mr8">未验证风险</span> <span
                                                        class="fr">个</span>

                                                    <h3 class="fr" name="history"></h3>
                                                </div>
                                                <div class="clearfix L_01" id="riskIncreaseObj"><span class="fl mr8">本月新增</span> <span class="fr">个</span>

                                                    <h3 class="fr" name="increase"></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </s:if>
                    
                    <td >
                        <div class="gray_border">
                            <div class="block h125" style="height:139px;">
                                <div>
                                    <div class="categories t_r mr5"><a href="<s:url value="/project/event/report.action"/>">信息安全事件管理台账</a></div>
                                    <div class="clear"></div>
                                    <div class="mr5">
                                        <div class="lh" id="event">
                                            <div class="clearfix"><span class="fl mr8">年度事件总数</span> <span class="fr">个</span>

                                                <h3 class="fr" name="yeartotal"></h3>
                                            </div>
                                            <div class="clearfix"  ><span class="fl mr8">当月较大事件总数</span> <span class="fr">个</span>

                                                <h3 class="fr" name="eventstotal"></h3>
                                            </div>
                                            <div class="clearfix L_01" id="eventExceptionObj"><span class="fl mr8">当月新发生事件总数</span> <span class="fr">个</span>

                                                <h3 class="fr" name="monthtotal"></h3>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </td>
                </tr>
                </tbody></table>
        </div>
    </td>

    <td>
        <!--Panel_3-->
        <div class="panel_3 mb10">
            <header class="clearfix">
                <h5 class="fl file">信息安全</h5>

                <div id="tabs2" class="fr tabs_1 clearfix">
                    <div class="fl pre"><a href="javascript:void(0);">上一页</a></div>
                    <ul class="fl">
                        <li class="reportLi"><a href="javascript:void(0);"><span>安全动态</span></a></li>
                        <li class="reportLi"><a href="javascript:void(0);"><span>法律法规</span></a></li>
                        <li class="reportLi"><a href="javascript:void(0);"><span>技术标准</span></a></li>
                        <li class="reportLi"><a href="javascript:void(0);"><span>培训材料</span></a></li>
                        <!-- 	<li class="reportLi"><a href="javascript:void(0);"><span>相关政策</span></a></li>-->
                        <!-- <li class="reportLi"><a href="javascript:void(0);"><span>等保评测</span></a></li> -->

                    </ul>
                    <div class="fl next"><a href="javascript:void(0);">下一页</a></div>
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
    </td>
</tr>

<tr>
    <td width="50%">

        <!--Panel_3-->
        <div class="panel_3 mb10">
            <header class="clearfix">
                <h5 class="fl file">实时跟踪</h5>

                <div id="tabs" class="fr tabs_1 clearfix">
                    <ul class="fl">
                        <li class="reportLi"><a href="javascript:void(0);"><span>业务状态</span></a></li>
                    </ul>
                </div>
            </header>
            <div class="con">
                <div class="reportDiv clearfix">
                    <iframe src="http://10.1.41.51/www_new/zdgztj.asp" frameborder="0" width="100%;" height="129"></iframe>
                </div>
            </div>
        </div>
        <!--Panel_3 End-->
    </td>
    <td>
        <!--Panel_3-->
        <div class="panel_3 mb10">
            <header class="clearfix">
                <h5 class="fl file">规划与制度</h5>

                <div id="tabs" class="fr tabs_1 clearfix">

                    <ul class="fl">
                        <li class="reportLi"><a href="javascript:void(0);"><span>体系建设</span></a></li>
                        <li class="reportLi"><a href="javascript:void(0);"><span>信息规划</span></a></li>
                    </ul>

                </div>
            </header>
            <div class="con">
                <div class="reportDiv clearfix">
                    <ul class="columns clearfix mb10">

                    </ul>
                    <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                </div>
                <div class="reportDiv clearfix">
                    <ul class="columns clearfix mb10" style="height:100px">

                    </ul>
                    <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                </div>
            </div>
        </div>
        <!--Panel_3 End-->
    </td>
</tr>


<tr>
    <td width="50%" style="height: 100%;">
    <!--Panel_3-->
        <div class="panel_3 mb10" style="height: 100%;">
            <header class="clearfix" style="height: 100%;">
                <h5 class="fl file">技术与服务</h5>

                <div id="tabs" class="fr tabs_1 clearfix">
                    <ul class="fl">
                        <li class="reportLi"><a href="javascript:void(0);"><span>信息前沿</span></a></li>
                        <li class="reportLi"><a href="javascript:void(0);"><span>应用指南</span></a></li>
                    </ul>
                </div>
            </header>
            <div class="con"style="height: 100%;">
                <div class="reportDiv clearfix">
                    <ul class="columns clearfix mb10">

                    </ul>
                    <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                </div>
                <div class="reportDiv clearfix" style="height: 100%;">
                    <ul class="columns clearfix mb10">

                    </ul>
                    <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                </div>
            </div>
        </div>
        <!--Panel_3 End-->
        
    </td>
    <td width="50%">
        <!--Panel_3-->
        <div class="panel_3 mb10">
            <header class="clearfix">
                <h5 class="fl file">建设及运行</h5>

                <div id="tabs" class="fr tabs_1 clearfix">
                    <ul class="fl">
                        <li class="reportLi"><a href="javascript:void(0);"><span>项目建设</span></a></li>
                        <li class="reportLi"><a href="javascript:void(0);"><span>运维情况</span></a></li>
                    </ul>
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
            </div>
        </div>
        <!--Panel_3 End-->
    </td>
</tr>
</table>
</div>
</div>
<div id="exceptionInfo" style="display:none;">
    <div id="workSecludeException"></div>
    <div id="planException"></div>
    <div id="periodException"></div>
    <div id="inPlanNotEndException"></div>
    <div id="inspectHistory"></div>
    <div id="riskHistory"></div>
    <div id="riskIncrease"></div>
    <div id="inspectIncrease"></div>
    <div id="eventException"></div>
</div>
</body>
</html>
