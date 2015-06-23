<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<% String basePath = request.getContextPath(); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
    <title>申通机关部门内页</title>

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
	<script type="text/javascript" src="js/subIndex.js"></script>

    <script type="text/javascript">
	QueryString.Initial();
        setbasePath("<%=basePath%>");
        $(function () {
            var liLength = 0;
            $("#maq").find("li").each(function (i, n) {
                liLength += $(n).width() + 24;
            })
            $("#maq").width(liLength + 100);

            var t = "";

            var zyxwId = QueryString.GetValue('zyxwId');//重要新闻
            var tzggId = QueryString.GetValue('tzggid');//通知公告
           

            var jeecmsUrl = "http://10.1.48.20/jeecms";

            getXWTTPicNews(jeecmsUrl, zyxwId);//重要新闻
            getHeadNews(zyxwId);//重要新闻
            getXXGLNews(zyxwId);//重要新闻
            getLatestNews(tzggId, "0");//通知公告
           

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
        <menu class="mb10">
            <div class="main clearfix">
				
				<ul class="fl">
					<li class="selected">
						<a href="#">上海申通地铁集团机关本部</a>
					</li>
				</ul>
				<marquee style="text-align:left; margin-left: 30px" width="600" height="40" behavior="scroll" scrollamount="5" onmouseout="this.start()" onmouseover="this.stop()" class="fl">
                    <div id="maq" style="width:1500px;">
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
            <!--------------- 第二个  ------------------>
            
            <!----------------- 第三个 ------------------------>
           
            <!-------------------- 第四个 ----------------------->
          

            <!-------------------- 第五个 ----------------------->
            
        </div>
    </div>
</body>
</html>
