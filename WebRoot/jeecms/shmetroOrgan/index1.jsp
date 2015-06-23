﻿<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<% String basePath = request.getContextPath(); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
    <title>集团机关首页</title>
	<!-- 
    <link type="text/css" rel="stylesheet" href="css/formalize.css" />
    <link type="text/css" rel="stylesheet" href="css/page.css" />
    <link type="text/css" rel="stylesheet" href="css/default/imgs.css" />
    <link type="text/css" rel="stylesheet" href="css/reset.css" />
    <link type="text/css" rel="stylesheet" href="css/jquery.marquee.min.css" />
    <link type="text/css" rel="stylesheet" href="css/flick/jquery-ui-1.8.18.custom.css" />
    <link type="text/css" rel="stylesheet" href="css/mopTip-2.2.css" />
    <link type="text/css" rel="stylesheet" href="css/common.css" />
    -->
    
    
		<link type="text/css" rel="stylesheet" href="css/formalize.css" /> 
		<link type="text/css" rel="stylesheet" href="css/page.css" /> 
		<link type="text/css" rel="stylesheet" href="../../css/default/imgs.css" /> 
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
        var partyLifeSet=[];
        setbasePath("<%=basePath%>");
        $(function () {
            var liLength = 0;
            $("#maq").find("li").each(function (i, n) {
                liLength += $(n).width() + 24;
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
					//var zyzhdId = "2592";//志愿者活动 申通现场测试环境：http://10.1.48.16:7001
					//var fwrcapId = "2593";//服务日程安排
					//var fwdtId = "2594";//服务动态
				    var zyzhdId = "2612";//志愿者活动 申通现场正式环境：http://10.1.48.20
				  	var fwrcapId = "2613";//服务日程安排
				  	var fwdtId = "2614";//服务动态
				
            var jeecmsUrl = "http://10.1.48.20/jeecms";

            getXWTTPicNews(jeecmsUrl, zyxwId);//重要新闻
            getHeadNews(zyxwId);//重要新闻
            getXXGLNews(zyxwId);//重要新闻
            getLatestNews(tzggId, "0");//通知公告
            getDYFCPicNews(jeecmsUrl, dyfcId);//党员风采
            getLatestNews(zthdId, "1");//主题活动
            getLatestNews(zbshId, "2");//支部生活
            getLatestNews(jyhdId, "3");//教育活动
            getLatestNews(fwrcapId, "4");//服务日程安排
						getLatestNews(fwdtId, "5");//服务动态
						
            getLatestNews(dwgkId, "6");//党务公开
            getLatestNews(ghzjId, "7");//工会之家
            
        //	getLatestNews(tzzshId, "6");//团组织生活
				//	getLatestNews(zyzhdId, "6");//志愿者活动
						

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


					$.ajax({
	             type : "get",
	             async:false,
	             url : "http://10.1.48.69:6001/cpq/partyLifeApi/getCnt/2015",
	             dataType : "jsonp",
	             jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
	             jsonpCallback:"success_jsonpCallback",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
	             success : function(data){
	                 //alert(data);
	                 var arr = data.result.split(',');
		  						for(var i in arr){
		  							partyLifeSet.push(parseFloat(arr[i]));
		  						}
		  						
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
                                    //data: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                                    data : partyLifeSet,
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
	             },
	             error:function(){
	                 //alert('fail');
	             }
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
                line-height: 37px;
				font-size: 14px;
            }
			
			.db menu li{
                float: left;
                position: relative;
			}

            .db menu li a {
                padding: 0 12px;
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

                    .db menu lihover, .db menu li.selected {
						color:#fff;
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
					lineheight:36px;
					height:38px;
                }

                    .db menu li ul li:hover {
                        background: #679eda;
                        border-color: #4275ad;
                    }

        
		.img{
			margin:0 auto;
			height:180px;
			background:url(css/default/images/u70_new.jpg) left top no-repeat;
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
        <!--<menu class="mb10">
			<div class="main clearfix">
				
				<ul class="fl">
					<li class="selected">
						<a href="#">上海申通地铁集团机关本部</a>
					</li>
				</ul>
				<marquee style="text-align:left; margin-left: 30px" width="600" height="40" behavior="scroll" scrollamount="5" onmouseout="this.start()" onmouseover="this.stop()" class="fl">
                    <div id="maq">
                        <ul>
                            <li>
                            <a href="subIndex.jsp?zyxwId=2493&tzggid=2512" target="_blank">党办</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2492&tzggid=2495" target="_blank">工会</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2414&tzggid=2405" target="_blank">团委</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2415&tzggid=2406" target="_blank">投资管理部</a>
                        </li>
                        <li>
                            <a href="http://10.1.48.69:7001/hrPortal/hrPortal/frame.html" target="_blank">组织人事部</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2417&tzggid=2408" target="_blank">总体规划部</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2418&tzggid=2409" target="_blank">审计室</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2419&tzggid=2425" target="_blank">财务部</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2420&tzggid=2410" target="_blank">安监室</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2400&tzggid=2426" target="_blank">企业发展部</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2401&tzggid=2411" target="_blank">行政办公室</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2402&tzggid=2432" target="_blank">合约管理部</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2421&tzggid=2433" target="_blank">监察室</a>
                        </li>
                        <li>
                            <a href="http://10.1.48.30/portal/center/xwzx/xw_company.jsp?headId=1349&activeId=1359&parentId=1358" target="_blank">建管中心</a>
                        </li>
                        <li>
                            <a href="http://10.1.48.30/portal/center/xwzx/xw_company.jsp?headId=1363&activeId=1380&parentId=1379" target="_blank">信息中心</a>
                        </li>
                        <li>
                            <a href="subIndex.jsp?zyxwId=2424&tzggid=2434" target="_blank">保卫部</a>
                        </li>
                        </ul>
                    </div>
                </marquee>
				<ul class="fr" style="margin-right: 50px">
					<li>
						<a href="#">机关部门</a>
						<ul>
							<li>
								<a href="subIndex.jsp?zyxwId=2493&tzggid=2512" target="_blank">党办</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2492&tzggid=2495" target="_blank">工会</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2414&tzggid=2405" target="_blank">团委</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2415&tzggid=2406" target="_blank">投资管理部</a>
							</li>
							<li>
								<a href="http://10.1.48.69:7001/hrPortal/hrPortal/frame.html" target="_blank">组织人事部</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2417&tzggid=2408" target="_blank">总体规划部</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2418&tzggid=2409" target="_blank">审计室</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2419&tzggid=2425" target="_blank">财务部</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2420&tzggid=2410" target="_blank">安监室</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2400&tzggid=2426" target="_blank">企业发展部</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2401&tzggid=2411" target="_blank">行政办公室</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2402&tzggid=2432" target="_blank">合约管理部</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2421&tzggid=2433" target="_blank">监察室</a>
							</li>
							<li>
								<a href="http://10.1.48.30/portal/center/xwzx/xw_company.jsp?headId=1349&activeId=1359&parentId=1358" target="_blank">建管中心</a>
							</li>
							<li>
								<a href="http://10.1.48.30/portal/center/xwzx/xw_company.jsp?headId=1363&activeId=1380&parentId=1379" target="_blank">信息中心</a>
							</li>
							<li>
								<a href="subIndex.jsp?zyxwId=2424&tzggid=2434" target="_blank">保卫部</a>
							</li>
						</ul>
					</li>
				</ul>
			</div> 
        </menu>
        <div class="main mb10">
            <div class="img"></div>
        </div>-->
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
                                        <!--<li>
                                            <a target="_blank" title="集团机关网页即将于2015年1月12日正式上线" href="../contentDetail.jsp?content=103863">集团机关网页即将于2015年1月12日正式上线</a><span>01-08</span>
                                        </li>-->
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
                                        <ul class="columns clearfix mb10">
                                        </ul>
                                        <p class="fr"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </aside>
            </div>
            <!--------------- 第二个  ------------------  >
            <div class="clearfix">
                <aside>
                    <div class="panel_1" style="height:284px;" id="fengcai">
                        <div class="bg_2">
                            <div class="bg_3">
                                <hgroup>
                                    <h3>党员风采</h3>
                                    <a class="more_1">更 多</a>
                                </hgroup>
                                <!--focusPic-- >
                                <div class="focusPic fl" id="play">
                                    <ul class="fp_list" id="list"></ul>
                                    <ul class="word" id="info"></ul>
                                </div>
                                <!--focusPic End-- >
                            </div>
                        </div>
                    </div>
                </aside>
                <div class="right_main">
									<!--Panel_3-- >
									<div class="panel_3 mb10" style="height:284px;">
										<header class="clearfix">
											<h5 class="fl file">支部活动</h5>
											<div id="tabs" class="fr tabs_1 clearfix">
												<ul class="fl">
													<li class="selected" id="x"><a><span>主题活动</span>
													</a>
													</li>
													<li id="y"><a><span>支部生活</span> </a>
													</li>
													<li id="z"><a><span>教育活动</span> </a>
													</li>
												</ul>
											</div>
										</header>
										<div class="con">
											<div id="list_x" class="reportDiv clearfix">
												<ul class="columns clearfix">
													
												</ul>
												<p class="fr">
													
												</p>
											</div>
											<div id="list_y" class="reportDiv clearfix" style="display:none">
												<ul class="columns clearfix">
													
												</ul>
												<p class="fr">
													
												</p>
											</div>
											<div id="list_z" class="reportDiv clearfix" style="display:none">
												<ul class="columns clearfix">
													
												</ul>
												<p class="fr">
													
												</p>
											</div>
										</div>
									</div>
									<!--Panel_3 End-- >
								</div>
            </div>
            <!----------------- 第三个 ------------------------  >
            <div class="clearfix">
                <div class="right_main">
                    <!--Panel_3--  >
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
                    <!--Panel_3 End-- >
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
            </div>-->
            <!--
            <div class="clearfix">
							<div class="right_main">
								<!--Panel_3-- >
								<div class="panel_3 mb10" style="height: 244px;">
									<header class="clearfix">
										<h5 class="fl file">志愿者活动</h5>
										<div id="tabs" class="fr tabs_1 clearfix">
											<ul class="fl">
												<li class="selected" id="x"><a><span>服务日程安排</span>
												</a>
												</li>
												<li id="y"><a><span>服务动态</span> </a>
												</li>
											</ul>
										</div>
									</header>
									<div class="con">
										<div id="list_x" class="reportDiv clearfix">
											<ul class="columns clearfix">
												
											</ul>
											<p class="fr">
												
											</p>
										</div>
										<div id="list_y" class="reportDiv clearfix" style="display:none">
											<ul class="columns clearfix">
												
											</ul>
											<p class="fr">
												
											</p>
										</div>
									</div>
								</div>
								<!--Panel_3 End-- >
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
												<div class="reportDiv clearfix" >
													<ul class="columns clearfix mb10">
			
													</ul>
													<p class="fr"></p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</aside>
						</div>
            
            
            <!-------------------- 第四个 -----------------------  >
            <div class="clearfix">
                <div class="main">
                    <ul class="clearfix db-img">
                        <li>
                            <a href="#">问卷调查</a>
                        </li>
                        <li>
                            <a href="http://10.1.48.69:6001/cpq/cpqQuestion/toQuestionList">网上测评</a>
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

            </div>-->
						
						<!--
						<div class="clearfix">
						<!--	<aside>
								<div  style="height:124px;">
									<div class="db-img" >
										
									</div>
								</div>
							</aside>-- >
							<div class="right_main" style="width:100%;" >
								<!--Panel_3-- >
								<div class="panel_3 mb10" style="height: 124px;">
									<header class="clearfix">
										<h5 class="fl file">互动交流</h5>
										<div id="tabs" class="fr tabs_1 clearfix">
											<ul class="fl">
												<li class="selected" id="a"><a><span>问卷调查</span>
												</a>
												</li>
												<li id="b"><a><span>网上测评</span> </a>
												</li>
												<li id="c"><a><span>意见征集</span> </a>
												</li>
												<li id="d"><a><span>问题解答</span> </a>
												</li>
												<li id="e"><a><span>监督投诉</span> </a>
												</li>
											</ul>
										</div>
									</header>
									<div class="con">
										<div id="list_a" class="reportDiv clearfix">
											<div style="padding:0px 10px;" >
												<label style="font-size:16px;font-weight:900;">&nbsp &nbsp 问卷调查</label>面向各类用户，以问卷形式了解基层单位和广大职工对某项工作、某个方面的认识（态度或意见），收集答案，分析掌握“企情民意”，帮助机关管理部门决策和改变机关管理人员思想、工作作风提供参考意见。
												<p class="fr">
													<a class="more_2" href="http://10.1.48.69:6001/icp/qteUserAnswer/qteUserAnswerList">点击进入</a>
												</p>
											</div>
												
										</div>
										<div id="list_b" class="reportDiv clearfix" style="display:none">
											<div style="padding:0px 10px;">
												<label style="font-size:16px;font-weight:900;">&nbsp &nbsp 网上测评</label>是指基层单位干部职工利用信息系统，在网络上对机关部门员工的管理服务工作进行满意度测评。通过群众满意度测评，反映基层单位对机关各部门的工作作风、工作质量、工作效率的评价。
												<p class="fr">
													<a class="more_2" href="http://10.1.48.69:6001/cpq/cpqQuestion/toQuestionList">点击进入</a>
												</p>
											</div>
										</div>
										<div id="list_c" class="reportDiv clearfix" style="display:none">
											<div style="padding:0px 10px;">
												<label style="font-size:16px;font-weight:900;">&nbsp &nbsp 意见征集</label>主要指专题性的议题需要征集意见和基层单位职工对集团机关工作提出意见、建议，把基层单位、部门职工意见和建议收集到集团机关相关部门。
												<p class="fr">
													<a class="more_2" href="http://10.1.48.69:6001/icp/actSuggestion/actSuggestionList">点击进入</a>
												</p>
											</div>
										</div>
										<div id="list_d" class="reportDiv clearfix" style="display:none">
											<div style="padding:0px 10px;">
												<label style="font-size:16px;font-weight:900;">&nbsp &nbsp 问题解答</label>主要针对经常遇到的各类热点问题和职工群众关心的问题进行归类和解答，有些是属于知识性的问题、也有些是专业性、政策性的问题，通过专业部门和专业人士来给大家释疑解惑。
												<p class="fr">
													<a class="more_2" href="http://10.1.48.30/portal/jeecms/findByPage.action?channelId=2592">点击进入</a>
												</p>
											</div>
										</div> 
										<div id="list_e" class="reportDiv clearfix" style="display:none">
											<div style="padding:0px 10px;">
												<label style="font-size:16px;font-weight:900;">&nbsp &nbsp 监督投诉</label>主要反映集团机关干部、职工的思想、服务、作风、廉洁建设等方面的问题（包括基层单位需要投诉的其他问题），以便及时纠正机关干部效率低下、管理缺位等现象。
												<p class="fr">
													<a class="more_2" href="http://10.1.48.69:6001/icp/cptComplaint/cptComplaintSave">点击进入</a>
												</p>
											</div>
										</div>
									</div>
								</div>
								<!--Panel_3 End-->
							</div>
							
						</div>
            <!-------------------- 第五个 -----------------------    >-- >
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
            -->
			
						
        </div>
    </div>
</body>
</html>
