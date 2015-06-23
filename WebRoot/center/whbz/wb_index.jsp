<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>维护保障首页</title>
<link rel="stylesheet" href="../../css/formalize.css" />

<link rel="stylesheet" href="../../css/page.css" />
<link rel="stylesheet" href="../../css/default/imgs.css" />
<link rel="stylesheet" href="../../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       	<script src="../../js/jquery-1.7.1.js"></script>
        <script src="../../js/html5.js"></script>     
		<script src="../../js/jquery.formalize.js"></script>
		<script src="../../js/show.js"></script>
		<script src="js/whbz_ajax.js"></script>
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
			
		$(document).ready(function(){ 
			var cur = 0;
			var len = $(".next a").parent().prev("ul").children(".reportLi").length;
			var page = 5;
			
			$(".reportLi").each(function(i,n){
				$(n).click(function(){
					$(n).siblings("li").removeClass("selected");
					$(n).addClass("selected");
					$(n).parent().parent().parent().parent().next().children(".reportDiv").hide();
					$(".reportDiv:eq("+i+")").show();
				})
			})
			
			$(".next a").click(function(){
				$(this).parent().prev("ul").children(".reportLi").hide();
				for(var i=cur;i<cur+page;i++){
					//alert(i);
					$(this).parent().prev("ul").children(".reportLi").eq(i+1).show();	
				}
				//$(this).parent().parent().children(".reportLi").eq(cur+1).click();
				
				cur++;
				if(cur+page==len){
					$(this).parent().hide();
				}
				if(cur>0){
					$(".pre").show();
				}
				
			})
			
			$(".pre a").click(function(){
				$(this).parent().next("ul").children(".reportLi").hide();
				for(var i=cur;i<cur+page;i++){
					$(this).parent().next("ul").children(".reportLi").eq(i-1).show();	
				}
				//$(this).parent().parent().children(".reportLi").eq(cur).click();	
				
				cur--;
				if(cur==0){
					$(this).parent().hide();
					$(".next").show();
				}
			})
			
			$(".pre").hide();
			$(".next a").parent().prev("ul").children(".reportLi:gt(4)").hide();
			
			
			$("#play_list").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 6000);}); 

			$("#play_text li").live("click",function(){
				var pos = $(this).html();
				$("#play_list li").hide();
				$("#play_info li").hide();
				$("#play_text li").removeClass("current");
				$("#play_list li").eq(pos-1).show();
				$("#play_info li").eq(pos-1).show();
				$(this).addClass("current");
			});
			
			getWHBZHeadNews(" where pub_flag='1' and part='wbzx' order by id desc");
			getLatestNews("554","","0");
			getLatestNews("774","","1");
			getLatestNews("794","","2");
			getLatestNews("954","","3");
			getLatestNews("934","","4");
			getLatestNews("795","","5");
			getLatestNews("556","","6");
			getLatestNews("557","","7");
			getLatestNews("559","","8");
			getLatestNews("560","","9");
			getLatestNews("561","","10");
			getLatestNews("563","","11");
			getLatestNewsAside("398","","0",6);
			getLatestNewsAside("564","","1",3);
			getLatestItemAside("562","","0");
			getLatestItemAside("634","","1");
			getLatestItemAside("635","","2");
			getLatestItemAside("636","","3");
			getLatestItemAside("637","","4");
			getLatestItemAside("914","","5");
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			
			$(window).resize(function(){
				$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			})
					
			$(".reportLi:eq(0)").click();
			$(".reportLi:eq(8)").click();		
			//loadShow();
			t = setInterval("showAuto()", 6000);
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
                	<li><a href="javascript:window.location.href='/portal/center/whbz/wb_index.jsp'">维护保障</a></li>
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
                        	<h2 class="fl">维护保障</h2>
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
                <!--Panel_3-->
                <div class="panel_3 mb10">
                	<header class="clearfix"><div class="bg_3 clearfix">
                    	<h5 class="fl file">生产简报</h5>
                        <div class="fr tabs_1">
                        <div class="fl pre"><a href="javascript:void(0);">上一页</a></div>   
                            <ul class="fl">                       	
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>生产日报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>每日签报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>生产月报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>抢修预案</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>设施设备月报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>设备专报</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>用电统计</span></a></li>    
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>新线简报</span></a></li> 	                   	
                            </ul>
                        <div class="fl next"><a href="javascript:void(0);">下一页</a></div>       
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
                    	<h5 class="fl file">设备状态</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>基地信息</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>变电站信息</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>车辆信息</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>新车新线</span></a></li>
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
            </div>
        	<!--Aside-->
            <aside class="fr">
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>维保快讯</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl topic">
                    </ul>
            	</div></div></div>
            	 <div class="mb10">
            	 <table class="item" width="100%" border="0" cellspacing="0" cellpadding="0">
            	  <tr>
                     <td class="t_c"><a style="display:inline;" href="#"><img src="../../css/default/images/item_01.jpg" class="mauto" width="111" height="52" title="轨道信息"/></a></td>
                     <td class="t_c"><a style="display:inline;" href="#"><img src="../../css/default/images/item_02.jpg" class="mauto" width="111" height="52" title="桥梁信息"/></a></td>
                  </tr>
					<tr>
						<td class="t_c pt10"><a style="display:inline;" href="#"><img src="../../css/default/images/item_03.jpg" class="mauto" width="111" height="52" title="隧道信息"/></a></td>
						<td class="t_c pt10"><a style="display:inline;" href="#"><img src="../../css/default/images/item_04.jpg" class="mauto" width="111" height="52" title="车站土建"/></a></td>
					</tr>
					<tr>
						<td class="t_c pt10"><a style="display:inline;" href="#"><img src="../../css/default/images/item_05.jpg" class="mauto" width="111" height="52" title="触网信息"/></a></td>
						<td class="t_c pt10"><a style="display:inline;"  href="#"><img src="../../css/default/images/item_06.jpg" class="mauto" width="111" height="52" title="机房信息"/></a></td>
					</tr>
				</table>
            	 <!-- 
                 <ul class="item clearfix">
               		<div class="clearfix mb10">
                            <li><div class="bg"><a href="#" class="I1"><h3>轨道信息</h3></a></div></li>
                            <li><div class="bg"><a href="#" class="I2"><h3>桥梁信息</h3></a></div></li>
                        </div>
                    	<div class="clearfix mb10">
                            <li><div class="bg"><a href="#" class="I3"><h3>隧道信息</h3></a></div></li>
                            <li><div class="bg"><a href="#" class="I4"><h3>车站土建</h3></a></div></li>
                        </div>
                    	<div class="clearfix">
                            <li><div class="bg"><a href="#" class="I5"><h3>触网信息</h3></a></div></li>
                            <li><div class="bg"><a href="#" class="I6"><h3>机房信息</h3></a></div></li>
                        </div>
                 </ul> -->
            	</div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>值班表</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	
          </aside>
        	<!--Aside End-->
        </div>
	</div>
</body>
</html>
