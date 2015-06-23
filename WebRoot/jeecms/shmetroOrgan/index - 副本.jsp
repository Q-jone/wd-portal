<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<% String basePath = request.getContextPath(); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>集团机关首页</title>

    <link type="text/css" rel="stylesheet" href="css/formalize.css" />
    <link type="text/css" rel="stylesheet" href="css/page.css" />
    <link type="text/css" rel="stylesheet" href="css/default/imgs.css" />
    <link type="text/css" rel="stylesheet" href="css/reset.css" />
    <link type="text/css" rel="stylesheet" href="css/jquery.marquee.min.css" />
    <link type="text/css" rel="stylesheet" href="css/flick/jquery-ui-1.8.18.custom.css" />
    <link type="text/css" rel="stylesheet" href="css/mopTip-2.2.css" />
    <link type="text/css" rel="stylesheet" href="css/common.css" />

    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.marquee.min.js"></script>
    <script type="text/javascript" src="js/html5.js"></script>
    <script type="text/javascript" src="js/jquery.formalize.js"></script>
    <script type="text/javascript" src="js/jquery.pngFix-1.2.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="js/highcharts.js"></script>
    <script type="text/javascript" src="js/organ_ajax.js"></script>

    <script type="text/javascript">

        setbasePath("<%=basePath%>");
        $(function () {
        	var liLength = 0;
        	$("#maq").find("li").each(function(i,n){
        		liLength += $(n).width()+24;
        		})
        	$("#maq").width(liLength + 100);

            var t = "";

            var zyxwId = "2372";//重要新闻
            var tzggId = "2392";//通知公告
            var ghzjId = "2393";//工会之家
            var tzzshId = "2373";//团组织生活
            var dyfcId = "2374";//党员风采
            var dwgkId = "2394";//党务公开
            var zthdId = "2397";//主题活动
            var zbshId = "2398";//支部生活
            var jyhdId = "2399";//教育活动

            var jeecmsUrl = "http://10.1.48.20/jeecms";

            getXWTTPicNews(jeecmsUrl, zyxwId);//重要新闻
            getHeadNews(zyxwId);//重要新闻
            getXXGLNews(zyxwId);//重要新闻
            getLatestNews(tzggId, "0");//通知公告
            getDYFCPicNews(jeecmsUrl, dyfcId);//党员风采
            getLatestNews(zthdId, "1");//主题活动
            getLatestNews(zbshId, "2");//支部生活
            getLatestNews(jyhdId, "3");//教育活动
            getLatestNews(dwgkId, "4");//党务公开
            getLatestNews(ghzjId, "5");//工会之家
            getLatestNews(tzzshId, "6");//团组织生活

            $("#tabs li").click(
                    function () {
                        $(this).parent("ul").find("li").attr("class", "");
                        $(this).attr("class", "selected");

                        $(this).parents("#tabs").parents("header").next("div.con").find(
                                ".reportDiv").hide();

                        $(this).parents("#tabs").parents("header").next("div.con").find(
                                "#list_" + $(this).prop("id")).show();
                    });

            $("#newsCenter").width($(".news").width() - $("#play").width() - 40);

            $(window).resize(
                    function () {
                        $("#newsCenter").width(
                                $(".news").width() - $("#play").width() - 40);
                    });
            t = setInterval("showAuto()", 6000);

            $(".next a").click(
                    function () {
                        $(this).parent().prev("ul").children(".reportLi").hide();
                        for (var i = cur; i < cur + page; i++) {
                            //alert(i);
                            $(this).parent().prev("ul").children(".reportLi").eq(
                                    i + 1).show();
                        }
                        //$(this).parent().parent().children(".reportLi").eq(cur+1).click();

                        if (cur + page == len - 1) {
                            $(this).parent().hide();
                        } else {
                            $(".pre").show();
                        }

                        cur++;

                    });

            $(".pre a").click(
                    function () {
                        $(this).parent().next("ul").children(".reportLi").hide();
                        for (var i = cur; i < cur + page; i++) {
                            $(this).parent().next("ul").children(".reportLi").eq(
                                    i - 1).show();
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



            $('#container')
                    .highcharts(
                            {
                                chart: {
                                    type: 'column'
                                },
                                title: {
                                    text: '2015年集团机关党组织生活情况 (次)'
                                },
                                exporting: {
                                    enabled: false
                                },
                                credits: {
                                    enabled: false
                                },
                                xAxis: {
                                    type: 'category',
                                    categories: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                                    labels: {
                                        formatter: function () {
                                            var value1 = this.value;
                                            var value2 = this.value;
                                            if (value1.length > 3) {

                                                value2 = value1.substr(0, 6)
                                                        + "<br/>"
                                                        + value1.substring(6,
                                                                value1.length);

                                            }
                                            return value2;
                                        },

                                    }
                                },
                                yAxis: {
                                    min: 0,
                                    title: {
                                        text: ''
                                    }
                                },
                                tooltip: {
                                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
                                            + '<td style="padding:0"><b>{point.y} </b></td></tr>',
                                    footerFormat: '</table>',
                                    shared: true,
                                    useHTML: true
                                },
                                legend: {
                                    enabled: false
                                },

                                series: [{
                                    name: '次数',
                                    data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                                    dataLabels: {
                                        enabled: true,
                                        rotation: -90,
                                        color: '#FFFFFF',
                                        align: 'right',
                                        x: 4,
                                        y: 2,
                                        style: {
                                            fontSize: '10px',
                                            fontFamily: 'Verdana, sans-serif',
                                            textShadow: '0 0 3px black'
                                        }
                                    }
                                }]
                            });
        });
    </script>


    <style type="text/css">
        body {
            background: #fff;
        }

        .db .main {
            width: 960px;
            margin: 0 auto;
            background: none;
        }

        .db menu {
            background: #1d62a9;
        }

            .db menu ul {
                height: 40px;
                line-height: 36px;
            }

            .db menu li {
                padding: 0 12px;
                font-size: 14px;
                float: left;
                position: relative;
            }

                .db menu li a {
                    display: block;
                    color: #fff;
                }

                .db menu li:hover, .db menu li.selected {
                    border-bottom: 3px solid;
                    border-top: none;
                    border-left: none;
                    border-right: none;
                    background: #961f62;
                    border-color: #720543;
                }

                    .db menu li:hover a, .db menu li.selected a {
                        color: #fff;
                    }

                .db menu li ul {
                    display: none;
                    z-index: 9999;
                    position: absolute;
                    left: 12px;
                    top: 40px;
                    width: 120px;
                }

                .db menu li:hover ul {
                    display: block;
                    margin: 0 -12px;
                }

                .db menu li ul li {
                    float: none;
                    background: #961f62;
                    border-bottom: #720543 3px solid;
                }

                    .db menu li ul li:hover {
                        background: #679eda;
                        border-color: #4275ad;
                    }

        .img {
            margin: 0 auto;
            height: 180px;
            background: url(css/default/images/u70_new.jpg) left top no-repeat;
            filter: "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale')";
            background-size: 100% 100%;
        }

        .db-img {
            padding: 5px 5px 5px 105px;
            background: url(css/default/images/u133.png) left center no-repeat;
        }

            .db-img li {
                margin: 5px;
                font-size: 16px;
                float: left;
                padding-left: 70px;
                padding-top: 14px;
                line-height: 80px;
                letter-spacing: 2px;
                width: 90px;
                background: url(css/default/images/db.png) left top no-repeat;
            }

                .db-img li a {
                    color: #fff;
                    font-weight: bold;
                }

                    .db-img li a:hover {
                        color: #fcd20c;
                    }

        aside .con p {
            line-height: 20px;
            text-indent: 0px;
            height: 20px;
            overflow: hidden;
            padding: 0px;
            font-size: 12px;
        }

        .panel_3 .con li {
            padding-left: 12px;
            line-height: 24px;
            width: 100%;
            float: left;
            margin-left: 10px;
            display: inline;
        }

            .panel_3 .con li a {
                width: 90%;
                float: left;
            }

        .panel_1 .bg_2, .panel_3 .bg_2 {
            background: url(css/default/images/aside_p_bg2_01.png) 1px 1px repeat-x;
        }

        .panel_1 .bg_3, .panel_3 .bg_3 {
            background: url(css/default/images/aside_p_bg2.png) right 1px no-repeat;
        }

        #img {
            height: 180px;
            width: 200px;
            overflow: hidden;
            cursor: pointer;
            position: absolute;
            margin-right: 15px;
            margin-left: 14px;
        }

        #info {
            margin-top: 190px;
        }

            #info a {
                color: #0a438d;
                font-size: 10pt;
                position: absolute;
                font-weight: bold;
            }
    </style>
</head>

<body>
    <div class="db">
        <menu class="mb10">
            <ul class="main">
                <li class="selected">
                    <a href="#">上海申通地铁集团机关本部</a>
                </li>
                <marquee style="text-align:left; margin-left: 30px" width="600" height="40" behavior="scroll" scrollamount="5" onmouseout="this.start()" onmouseover="this.stop()">
                    <div id="maq">
                        <ul>
                            <li>
                                <a href="#">党办</a>
                            </li>
                            <li>
                                <a href="#">工会</a>
                            </li>
                            <li>
                                <a href="#">团委</a>
                            </li>
                            <li>
                                <a href="#">投资管理部</a>
                            </li>
                            <li>
                                <a href="#">组织人事部</a>
                            </li>
                            <li>
                                <a href="#">总体规划部</a>
                            </li>
                            <li>
                                <a href="#">审计室</a>
                            </li>
                            <li>
                                <a href="#">财务部</a>
                            </li>
                            <li>
                                <a href="#">安监室</a>
                            </li>
                            <li>
                                <a href="#">企业发展部</a>
                            </li>
                            <li>
                                <a href="#">行政办公室</a>
                            </li>
                            <li>
                                <a href="#">合约管理部</a>
                            </li>
                            <li>
                                <a href="#">监察室</a>
                            </li>
                            <li>
                                <a href="#">建管中心</a>
                            </li>
                            <li>
                                <a href="#">信息中心</a>
                            </li>
                            <li>
                                <a href="#">保卫部</a>
                            </li>
                        </ul>
                    </div>
                </marquee>
                <li style="float:right; margin-right: 50px">
                    <a href="#">机关部门</a>
                    <ul>
                        <li>
                            <a href="#">党办</a>
                        </li>
                        <li>
                            <a href="#">工会</a>
                        </li>
                        <li>
                            <a href="#">团委</a>
                        </li>
                        <li>
                            <a href="#">投资管理部</a>
                        </li>
                        <li>
                            <a href="#">组织人事部</a>
                        </li>
                        <li>
                            <a href="#">总体规划部</a>
                        </li>
                        <li>
                            <a href="#">审计室</a>
                        </li>
                        <li>
                            <a href="#">财务部</a>
                        </li>
                        <li>
                            <a href="#">安监室</a>
                        </li>
                        <li>
                            <a href="#">企业发展部</a>
                        </li>
                        <li>
                            <a href="#">行政办公室</a>
                        </li>
                        <li>
                            <a href="#">合约管理部</a>
                        </li>
                        <li>
                            <a href="#">监察室</a>
                        </li>
                        <li>
                            <a href="#">建管中心</a>
                        </li>
                        <li>
                            <a href="#">信息中心</a>
                        </li>
                        <li>
                            <a href="#">保卫部</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </menu>
        <div class="main mb10">
            <div class="img"></div>
        </div>
        <div class="main p10">
            <!------------- 第一个 --------------->
            <div class="clearfix">
                <div class="right_main">
                    <!--News-->
                    <div class="news clearfix">
                        <div class="news_r">
                            <div class="news_l">
                                <!--focusPic-->
                                <div class="focusPic fl" id="play">
                                    <ul class="fp_list" id="play_list"></ul>
                                    <ul class="word" id="play_info"></ul>
                                    <ul class="scrollnav" id="play_text"></ul>
                                </div>
                                <!--focusPic End-->
                                <!--Txt News-->
                                <div class="fl" id="newsCenter" style="width:295px;">
                                    <hgroup class="clearfix mb10">
                                        <h2 class="fl">重要新闻</h2>
                                        <h6 class="fl">News</h6>
                                        <p class="t_r">
                                            <a class="more_2">更多</a>
                                        </p>
                                    </hgroup>
                                    <section>
                                        <a href="#">
                                            <h5></h5>
                                            <p></p>
                                        </a>
                                    </section>
                                    <ul class="txt_list">
                                        <li>
                                            <a href="#"></a><span></span>
                                        </li>

                                    </ul>
                                </div>
                                <!--Txt News End-->
                            </div>
                        </div>
                    </div>
                    <!--News End-->

                </div>
                <aside>
                    <div class="panel_3" style="height:284px;">
                        <div class="bg_2">
                            <div class="bg_3">
                                <hgroup>
                                    <h3>通知公告</h3>
                                    <a href="#" class="more_1" style="display:none">更 多</a>
                                </hgroup>
                                <section>
                                    <a href="#">
                                        <h5></h5>
                                        <p></p>
                                    </a>
                                </section>
                                <div class="con">
                                    <div class="reportDiv clearfix" id="1">
                                        <ul class="columns clearfix mb10"></ul>
                                        <p class="fr"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
            </div>
            <!--------------- 第二个  ------------------>
            <div class="clearfix">
                <aside>
                    <div class="panel_1" style="height:284px;" id="fengcai">
                        <div class="bg_2">
                            <div class="bg_3">
                                <hgroup>
                                    <h3>党员风采</h3>
                                    <a class="more_1">更 多</a>
                                </hgroup>
                                <!--focusPic-->
                                <div class="focusPic fl" id="play">
                                    <ul class="fp_list" id="list"></ul>
                                    <ul class="word" id="info"></ul>
                                </div>
                                <!--focusPic End-->
                            </div>
                        </div>
                    </div>
                </aside>
                <div class="right_main">
                    <div class="panel_4 panel_8 mb10">
                        <header>
                            <div class="tit">
                                <div class="bg clearfix">
                                    <h5 class="fl stats">集团机关党支部组织生活情况</h5>
                                </div>
                            </div>
                        </header>
                        <div class="con clearfix" style="height: 243px;">
                            <!--4panles-->
                            <table width="100%" class="p0">
                                <tr>
                                    <td width="60%" style="height:243px;">
                                        <div class="gray_border" style="height:243px;">
                                            <div class="block" style="height:243px;">
                                                <div>
                                                    <div class="categories t_r mr5">
                                                        <a href="#">集团机关党支部组织生活情况</a>
                                                    </div>
                                                    <div class="clear"></div>
                                                    <div class="mr5 clearfix">
                                                        <div class="bor fl  mr8 w45p h125"
                                                             style="width:100%; height:215px">
                                                            <div id="container"
                                                                 style="width: 100%; height: 100%; max-width: 700px; margin: 0 auto"></div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>

                                </tr>
                            </table>
                            <!--4panles end-->
                        </div>
                    </div>
                </div>
            </div>
            <!----------------- 第三个 ------------------------>
            <div class="clearfix">
                <div class="right_main">
                    <!--Panel_3-->
                    <div class="panel_3 mb10" style="height: 244px;">
                        <header class="clearfix">
                            <h5 class="fl file">支部活动</h5>
                            <div id="tabs" class="fr tabs_1 clearfix">
                                <ul class="fl">
                                    <li class="selected" id="x">
                                        <a>
                                            <span>主题活动</span>
                                        </a>
                                    </li>
                                    <li id="y">
                                        <a><span>支部生活</span> </a>
                                    </li>
                                    <li id="z">
                                        <a><span>教育活动</span> </a>
                                    </li>
                                </ul>
                            </div>
                        </header>
                        <div class="con">
                            <div id="list_x" class="reportDiv clearfix">
                                <ul class="columns clearfix"></ul>
                                <p class="fr">
                                    <a class="more_3" href="#">更多</a>
                                </p>
                            </div>
                            <div id="list_y" class="reportDiv clearfix" style="display:none">
                                <ul class="columns clearfix"></ul>
                                <p class="fr">
                                    <a class="more_3" href="#">更多</a>
                                </p>
                            </div>
                            <div id="list_z" class="reportDiv clearfix" style="display:none">
                                <ul class="columns clearfix"></ul>
                                <p class="fr">
                                    <a class="more_3" href="#">更多</a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <!--Panel_3 End-->
                </div>
                <aside>
                    <div class="panel_3" style="height:244px;">
                        <div class="bg_2">
                            <div class="bg_3">
                                <hgroup>
                                    <h3>党务公开</h3>
                                    <a href="#" class="more_1" style="display:none">更 多</a>
                                </hgroup>
                                <div class="con">
                                    <div class="reportDiv clearfix">
                                        <ul class="columns clearfix mb10"></ul>
                                        <p class="fr"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
            </div>
            <!-------------------- 第四个 ----------------------->
            <div class="clearfix">
                <div class="main">
                    <ul class="clearfix db-img">
                        <li>
                            <a href="#">问卷调查</a>
                        </li>
                        <li>
                            <a href="http://10.1.48.16:7001/cpq/cpqQuestion/toQuestionList">网上测评</a>
                        </li>
                        <li>
                            <a href="#">意见征集</a>
                        </li>
                        <li>
                            <a href="#">问题解答</a>
                        </li>
                        <li>
                            <a href="#">监督投诉</a>
                        </li>
                    </ul>
                </div>

            </div>

            <!-------------------- 第五个 ----------------------->
            <div class="clearfix">

                <aside style="width: 50%">
                    <div class="panel_3" style="height:244px;">
                        <div class="bg_2">
                            <div class="bg_3">
                                <hgroup>
                                    <h3>工会之家</h3>
                                    <a href="#" class="more_1" style="display:none">更 多</a>
                                </hgroup>
                                <div class="con">
                                    <div class="reportDiv clearfix">
                                        <ul class="columns clearfix mb10"></ul>
                                        <p class="fr"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>

                <aside style="width: 50%">
                    <div class="panel_3" style="height:244px;">
                        <div class="bg_2">
                            <div class="bg_3">
                                <hgroup>
                                    <h3>团组织工作</h3>
                                    <a href="#" class="more_1" style="display:none">更 多</a>
                                </hgroup>
                                <div class="con">
                                    <div class="reportDiv clearfix">
                                        <ul class="columns clearfix mb10"></ul>
                                        <p class="fr"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
            </div>
        </div>
    </div>
</body>
</html>
