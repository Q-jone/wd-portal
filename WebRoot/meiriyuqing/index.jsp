<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String basePath = request.getContextPath(); 
     %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>舆情动态</title>
<link rel="stylesheet" href="../css/formalize.css" />

<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       	<script src="../js/jquery-1.7.1.js"></script>
        <script src="../js/html5.js"></script>     
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/show.js"></script>
		<script src="js/xw_ajax.js"></script>
		<script type="text/javascript">
		setbasePath("<%=basePath%>");
		
$(function(){
		$(".reportLi").each(function(i,n){
			$(n).click(function(){
				$(n).siblings("li").removeClass("selected");
				$(n).addClass("selected");
				$(n).parent().parent().parent().parent().next().children(".reportDiv").hide();
				$(".reportDiv:eq("+i+")").show();
			});
		});
		
		
		getLatestNews("1772","0","reportDivTop");
		getLatestNews("1773","1","reportDivTop");
		getLatestNews("1774","2","reportDivTop");
		getStfbLatestNews("836","2","reportDiv");
		getLatestNews("1792","1","reportDiv");
		getLatestNews("1794","0","reportDiv");//地铁文化
		//getLatestNews("1793","2","reportDiv");//天下事
		
		$("#newsCenter").width($(".news").width()-$("#play").width()-40);
		
		$(window).resize(function(){
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
		});
				
		$(".reportLi:eq(0)").click();
});
		</script>
		
</head>

<body>
	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="/portal/center/xwzx/xw_index.jsp" href="_self">新闻资讯</a></li>
                	<li class="fin">舆情动态</li>
                </ul>
            </div>
            <div class="fr lit_nav nwarp">
             <!--  <ul class="fr">
                    <li class="selected"><a class="print" href="#">打印</a></li>
                    <li><a class="express" href="#">导出数据</a></li>
                    <li class="selected"><a class="table" href="#">表格模式</a></li>
                    <li><a class="treeOpen" href="#">打开树</a></li>
                    <li><a class="filterClose" href="#">关闭过滤</a></li> 
                </ul>-->
              
            </div>
   		</div>
        <!--Ctrl End-->
         <div class="clearfix pt45">
        	<div class="mb10">
            	  <!---->
        <div class="mb10 clearfix">
			<!--Panel_3-->
                <div class="panel_3 m6 fl" style="width:31%;">
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl richeng">今日焦点</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="reportDivTop clearfix">
                            <ul class="columns clearfix mb10" style="height:160px;">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        
                    </div>
                </div>
                <!--Panel_3 End-->
			<!--Panel_3-->
                <div class="panel_3 m6 fl" style="width:31%;">
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl meeting">地铁周边</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="reportDivTop clearfix">
                            <ul class="columns clearfix mb10" style="height:160px;">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        
                    </div>
                </div>
                <!--Panel_3 End-->
                <!--Panel_3-->
                <div class="panel_3 m6 fl" style="width:31%;">
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl richeng">百姓地铁</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="reportDivTop clearfix">
                            <ul class="columns clearfix mb10" style="height:160px;">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        
                    </div>
                </div>
                <!--Panel_3 End-->
                <div class="clear"></div>
			
			
                
                 
        </div>
        
        <!--Panel_3-->
                <div class="panel_3 mb10" style="width:95%;">
                	<header class="clearfix"><div class="bg_3 clearfix">
                    	
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>地铁文化</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>同行看点</span></a></li>
                            	 <%-- <li class="reportLi" ><a href="javascript:void(0);"><span>天下事</span></a></li>  --%>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>每日舆情简报</span></a></li>
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
                    </div>
                </div>
                <!--Panel_3 End-->
                
        <!-- End-->	
            </div>
        	
        </div>
	</div>
</body>
</html>
