﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.wonders.stpt.todoItem.util.ConfigUtil"  %> 
    <%
					String urlCa = ConfigUtil.getValueByKey("config.properties","urlCa");
						
					%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>新闻资讯首页</title>
<link rel="stylesheet" href="../../css/formalize.css" />

<link rel="stylesheet" href="../../css/page.css" />
<link rel="stylesheet" href="../../css/default/imgs.css" />
<link rel="stylesheet" href="../../css/reset.css" />
<link type="text/css" href="../../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       	<script src="../../js/jquery-1.7.1.js"></script>
<!--<script src="js/jquery-ui-1.9.2.custom.js"></script>
	<script src="../../js/showDialog.js"></script>-->
   		<script src="../../js/html5.js"></script>
		<script src="../../js/jquery.formalize.js"></script>
		<script src="../../js/show.js"></script>
		<script src="js/xwzx_ajax.js"></script>
		<script type="text/javascript">
		var t ;
		function showAuto() { 
			var listTmp = $("#play_list li").filter(function(){return $(this).css('display')!='none';});
			var infoTmp = $("#play_info li").filter(function(){return $(this).css('display')!='none';});
			var textTmp = $("#play_text li.current");
			//alert(listTmp.html());
			$("#play_list li").hide();
			$("#play_info li").hide();
			$("#play_text li").removeClass("current");
			if(listTmp.next().length==0){
				$("#play_list li").eq(0).show();
				$("#play_info li").eq(0).show();
				$("#play_text li").eq(0).addClass("current");
			}else{
				listTmp.fadeOut(500).next().fadeIn(1000); 
				infoTmp.fadeOut(500).next().fadeIn(1000); 
				textTmp.next().addClass("current");
			}
			
			//
		} 
		var locations = ["http://10.20.1.100/News/newsAction","http://10.83.0.83/admin/NewsManage/NewsStatistics/News_Index.aspx","http://10.108.64.242/pub/companyNews.aspx","http://10.10.2.251:800/oa_news/news.asp"];
		var branchFrame,intv;
		function toggleIframe(){
			branchFrame = $('#branchFrame');
	        var lis = $("#branch_text li");
	        
	        branchFrame.attr('src',locations[0]);
	        $(lis[0]).addClass("selected");
	        
	        startInterval();
		}
		function startInterval(){
	        intv = setInterval(function () {
				var cur = $("#branch_text li.selected");
				var pos = cur.attr('pos')*1;
				var lis = $("#branch_text li");
				var len = locations.length;
				
				lis.removeClass("selected");
				
	        	branchFrame.attr('src', locations[++pos % len]);
	        	$(lis[pos % len]).addClass("selected");
	        	
	        }, 30000);		
		}			
		$(document).ready(function(){ 
			$(".slogan div:first").show();
			var e = $(".slogan div");
			var i = 1;
			setInterval(function (){
				e.hide();
				$(e[i]).fadeIn();
				i = i == e.length - 1 ? 0 : i + 1;
			},5000);
			
			$("#dd").click(function(){
						window.open("/portal/exam/edit.action?examId=EXAMMAIN&random="+Math.random());
				});
			$(".reportLi").each(function(i,n){
				$(n).click(function(){
					$(n).siblings("li").removeClass("selected");
					$(n).addClass("selected");
					$(n).parent().parent().parent().parent().next().children(".reportDiv").hide();
					$(".reportDiv:eq("+i+")").show();
				});
			});
			
			$("#play_list").hover(function(){clearInterval(t);}, function(){t = setInterval("showAuto()", 6000);}); 

			$("#play_text li").live("click",function(){
				var pos = $(this).html();
				$("#play_list li").hide();
				$("#play_info li").hide();
				$("#play_text li").removeClass("current");
				$("#play_list li").eq(pos-1).show();
				$("#play_info li").eq(pos-1).show();
				$(this).addClass("current");
			});
			
			getHeadNews("37");
			getXWTTNews("37");
			getXWTTPicNews("37");
			getLatestNews("1","0");
			getLatestNews("834","1");
			getLatestNews("835","2");
			getLatestNews("674","3");
			getLatestNews("854","4");
			getLatestNews("40","5");
			getLatestNews("39","6");
			getLatestNews("716","7");
			getLatestDTB("8");
			getLatestNews("836","9");
			
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			
			$(window).resize(function(){
				$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			});
					
			$(".reportLi:eq(4)").click();
			$(".reportLi:eq(5)").click();
			//loadShow();
			t = setInterval("showAuto()", 6000);
			
			toggleIframe();
			
			$("#branch_text li").live("click",function(){
				var pos = $(this).attr('pos')*1;
				$("#branch_text li").removeClass("selected");
				$(this).addClass("selected");
				branchFrame.attr('src', locations[pos]);
				
				clearInterval(intv);
				startInterval();
			});
		});
		
		</script>
		
</head>

<body>
	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="javascript:window.location.href='/portal/center/xwzx/xw_index.jsp'">新闻资讯</a></li>
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
                        	<h2 class="fl">资讯中心</h2>
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
                <!--Panel_4-->
                <div class="panel_3 mb10" id="branchNews">
                    <header class="clearfix">
                        <h5 class="fl file">各单位新闻</h5>
                        <div id="tabs" class="fr tabs_1 clearfix">
                            <ul class="fl" id="branch_text">
                                <li pos="0"><a href="javascript:void(0);"><span>运管中心</span></a></li>
                                <li pos="1"><a href="javascript:void(0);"><span>第三运营公司</span></a></li>
                                <li pos="2"><a href="javascript:void(0);"><span>第四运营公司</span></a></li>
                                <li pos="3"><a href="javascript:void(0);"><span>维保公司</span></a></li>
                            </ul>
                        </div>
                    </header>
                    <div class="con">
                        <div class="clearfix">
                            <iframe id="branchFrame" frameborder="0" width="100%;" height="320px"></iframe>
                        </div>
                    </div>
                </div>
                <!--Panel_4 End-->

                <!--Panel_3-->
                <div class="panel_3 mb10">
                	<header class="clearfix"><div class="bg_3 clearfix">
                    	<h5 class="fl file">新闻资讯</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>指挥部简报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>集团简报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>基层简报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>外单位信息</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>领导讲话</span></a></li>
                            </ul>
                        </div></div>
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
                	<header class="clearfix"><div class="bg_3 clearfix">
                    	<h5 class="fl file">新闻资讯</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>公示公告</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>行业动态</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>重点专项</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>地铁报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>每日舆情</span></a></li>
                            </ul>
                        </div></div>
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
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix mb10">
                               
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->

            </div>
        	<!--Aside-->
             <aside class="fr">
            <table>
            	<tr><td colspan="2"></td></tr>
            	<div style="width:316px;">
            	<div class="panel_2">
                	<div class="slogan">
                    	<div>
                            <h3>集团共同使命</h3>
                            <h5><span>申</span>城地铁  <span>通</span>向都市新生活</h5>
                        </div>
                    	<div>
                            <h3>集团核心价值观</h3>
                            <h6>社会责任第一  安全质量第一  团队协作第一</h6>
                        </div>
                    	<div>
                            <h3>集团廉洁文化理念</h3>
                            <h5>安全地铁 健康身心</h5>
                        </div>
                    </div>
                </div></div>
            	
            	<div class="panel_1" style="width:300px;">
            	<div class="bg_2"><div class="bg_3">
           		  <hgroup>
                    	<h5>专题类</h5>
                        <!--<h6>Topic</h6>-->
                    </hgroup>
                    <ul class="topic">
                    	<li><a target="_blank" style="background:url(/portal/css/default/images/dh.png) -3px 3px no-repeat" href="/portal/shibada/zhonggongshibada.jsp"><b>十八大专题</b></a></li>
                    	
						<li><a target="_blank" href="/portal/qzlx/qzlx_index.jsp" style="color:#605f5f;background:url(null) left -510px no-repeat;"><b>党的群众路线教育实践活动</b></a>
                    		<!--&nbsp;<a id="dd" target="_blank" style="color:#605f5f;background:url() left -510px no-repeat;display:inline;"
                    			href="javascript:void(0);"><b>整改情况测评表</b></a>-->
                    		</li>
							
                    		<li>
							<a style="float:left" target="_blank" href="http://10.1.44.18/stdwfb/publicConn.jsp?urlPath=/">党务公开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
							<a style="float:left" target="_blank" href="/portal/jeecms/findStfbNewsByPage.action?channelId=1672">"六五"普法</a>
						</li>
						<li>
							<a style="float:left" target="_blank" href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/gzzd/gzzdinfo.do?b_query=true">制度建设&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
							<a style="float:left" target="_blank" href="http://10.1.48.30/portal/center/syh/index.jsp">思研会之窗</a>
						</li>
						<li>
							<a style="float:left" target="_self" href="/portal/meiriyuqing/index.jsp">每日舆情</a>
						</li>
                    	<!--
						<li><a target="_blank" href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/gzzd/gzzdinfo.do?b_query=true">制度建设</a></li>               
                    	<li><a target="_blank" href="http://10.1.48.30/portal/center/syh/index.jsp">思研会之窗</a></li>
						-->
					</ul>
            	</div></div></div>
            	<tr>
            		
            		<td>
            		<div class="panel_1" style="margin-right:5px;"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH" style="margin-bottom:15px;">
                    	<h5>新闻站点链接</h5>
                  </hgroup>
                 <ul class="list">
                   	<li style="background-color:#33ddff"><a href="javascript:void(0);" target="_self">申通集团</a></li> 
				    <li><a onclick="window.open('http://10.61.1.160');" href="xw_company.jsp?headId=1324&activeId=1335&parentId=1334" target="_self">第一运营公司</a></li>
					<li ><a href="xw_company.jsp?headId=1325&activeId=1326&parentId=1336" target="_self">第二运营公司</a></li>
					<li><a onclick="window.open('http://10.83.0.83/');" href="xw_company.jsp?headId=1331&activeId=1340&parentId=1330" target="_self">第三运营公司</a></li>
					<li><a onclick="window.open('http://10.108.64.242/pub/default.aspx');" href="xw_company.jsp?headId=1343&activeId=1332&parentId=1342" target="_self">第四运营公司</a></li>
					<li><a href="xw_company.jsp?headId=1347&activeId=1355&parentId=1354" target="_self">维保中心</a></li>
					<li><a onclick="window.open('http://10.20.1.100/');" href="xw_company.jsp?headId=1357&activeId=1348&parentId=1356" target="_self">运管中心</a></li>
					<li><a href="xw_company.jsp?headId=1349&activeId=1359&parentId=1358" target="_self">建管中心</a></li>
					<li><a href="xw_company.jsp?headId=1375&activeId=1362&parentId=1374" target="_self">技术中心</a></li>
					<li><a href="xw_company.jsp?headId=1377&activeId=1378&parentId=1376" target="_self">培训中心</a></li>
					<li><a href="xw_company.jsp?headId=1363&activeId=1380&parentId=1379" target="_self">信息中心</a></li>
					<li><a href="xw_company.jsp?headId=1381&activeId=1365&parentId=1364" target="_self">资金中心</a></li>
					<li><a href="xw_company.jsp?headId=1369&activeId=1370&parentId=1383" target="_self">资产中心</a></li>
					<li><a onclick="window.open('http://10.88.0.110');" href="xw_company.jsp?headId=1371&activeId=1372&parentId=1384" target="_self">资产公司</a></li>
					<li><a href="xw_company.jsp?headId=1386&activeId=1373&parentId=1385" target="_self">隧道设计院</a></li>
					<li><a onclick="window.open('http://10.1.33.70:8181/');" href="xw_company.jsp?headId=1389&activeId=1390&parentId=1395" target="_self">股份公司</a></li>
					<li><a onclick="window.open('http://www.hpjdq.com/login.asp');" href="xw_company.jsp?headId=1387&activeId=1388&parentId=1394" target="_self">黄浦江大桥建设公司</a></li>
					<li><a href="xw_company.jsp?headId=1636&activeId=1654&parentId=1635" target="_self">磁浮公司</a></li>
                 </ul>
                 
            	</div></div></div>
            		</td>
            		<td>
            		<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH" style="margin-bottom:15px;">
                    	<h5 style="margin-right:15px;">集团业务管理系统</h5>
                  </hgroup>
            <ul class="list">
            	<li><a href="http://20.1.2.2" target="_blank">财务管理系统</a></li> 
            	<li><a href="http://10.1.44.116" target="_blank">人事管理系统</a></li> 
            	<li><a href="/portal/htxx/htgl_index.jsp" target="_self">合同管理系统</a></li> 
            	<li><a href="http://10.1.48.20/AssetWeb/jsp/homepage.jsp" target="_blank">资产管理系统</a></li>
            	<li><a href="http://10.1.48.49:8888/standardWork/standardWork/index.jsp" target="_blank">标准管理系统</a></li>
							<li><a href="http://10.1.44.100" target="_blank">远程培训系统</a></li> 
            	<li><a href="<%=urlCa%>/iamCross.jsp?target=Greata" target="_blank">项目执行管理系统(GREATA)</a></li> 
            	<li><a href="http://www.st-ycjk.com/huibao/H_Loginst.aspx" target="_blank">工程建设安监系统</a></li> 
            	<li><a href="<%=urlCa%>/iamCross.jsp?target=EAM" target="_blank">企业资产管理系统(EAM)</a></li> 
             	<li><a href="http://10.1.214.203" target="_blank">能耗监测系统</a></li>
                <li><a href="/portal/center/mscp/mscp.jsp">阳光采购平台(MSCPII)</a></li>
							<li><a href="/portal/ws/ws_index.html">外事信息系统</a></li>
							<li><a href="/portal/xinfang/xinfang_index.html">信访管理系统</a></li>
							<li><a href="/portal/validFile/list.jsp" target="_self">规范性文件</a></li>
							
						</ul>
                 
            	</div></div></div>
            		</td>
            		</tr>
            	
            	</table>
            	
            	
            	
				<a href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/prophase/improvePlanSearch.do?b_query=true" target="_blank">
                 	<img src="../../css/default/images/improve_plan.jpg"></img>
                 </a>
               
			
          </aside>
        	<!--Aside End-->
        </div>
	</div>
</body>
</html>