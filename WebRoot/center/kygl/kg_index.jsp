<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>科研管理首页</title>
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
		<script src="js/kygl_ajax.js"></script>
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
			var page = 2;
			$("#gldtSelect").change(function(){
				var index = $(this).get(0).selectedIndex;
				$(".gldtDiv").hide();
				$(".gldtDiv:eq("+index+")").show();
				$(".gldtH a").attr("href",$(this).parent().parent().children("div").eq(index).attr("moreLink"));
			})
			
			$("#zdxmSelect").change(function(){
				var index = $(this).get(0).selectedIndex;
				$(".zdxmUl").hide();
				$(".zdxmUl:eq("+index+")").show();
				$(".asideH a").attr("href",$(this).parent().parent().children("ul").eq(index).attr("moreLink"));
			})
			
			$("#msg").click(function(){
				window.open("http://10.1.44.18/kygl/jyxc/addMessage.jsp");
			})
			
			$(".next a").each(function(i,n){
				$(n).parent().parent("div").attr("len",$(n).parent().prev("ul").children(".reportLi").length);
				$(n).parent().prev("ul").children(".reportLi:gt(1)").hide();
				
			})
			$(".reportLi").each(function(i,n){
				$(n).click(function(){
					$(n).siblings("li").removeClass("selected");
					$(n).addClass("selected");
					$(n).parent().parent().parent().next().children(".reportDiv").hide();
					$(".reportDiv:eq("+i+")").show();
				})
			})
			
			$(".next a").click(function(){
				var len = parseInt($(this).parent().parent("div").attr("len"));
				var cur = parseInt($(this).parent().parent("div").attr("cur"));
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
					$(this).parent().parent("div").find(".pre").show();
				}
						
				$(this).parent().parent("div").attr("len",len);
				$(this).parent().parent("div").attr("cur",cur);
				
			})
			
			$(".pre a").click(function(){
				var len = parseInt($(this).parent().parent("div").attr("len"));
				var cur = parseInt($(this).parent().parent("div").attr("cur"));
				$(this).parent().next("ul").children(".reportLi").hide();
				for(var i=cur;i<cur+page;i++){
					$(this).parent().next("ul").children(".reportLi").eq(i-1).show();	
				}
				//$(this).parent().parent().children(".reportLi").eq(cur).click();	
				cur--;
				if(cur==0){
					$(this).parent().hide();
					$(this).parent().parent("div").find(".next").show();
				}
									
				$(this).parent().parent("div").attr("len",len);
				$(this).parent().parent("div").attr("cur",cur);
			})
			
			$(".pre").hide();
					
			
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
			
			getKYGLHeadNews(" where pub_flag='1' and part='kygl' order by id desc");
			getLatestNews("737","","0");
			getLatestNews("738","","1");
			getLatestNews("739","","2");
			getLatestNews("740","","3");
			getLatestNews("741","","4");
			getLatestNews("742","","5");
			getLatestNews("743","","6");
			getLatestNews("718","","7");
			getLatestNews("719","","8");
			getLatestNews("720","","9");
			getLatestNews("721","","10");
			getLatestItemAside("010101","11");
			getLatestItemAside("010102","12");
			getLatestItemAside("010103","13");
			getLatestItemAside("010104","14");
			getContentAside("722","","0","1");
			getContentAside("723","","1","1");
			getLatestNewsAside("724","","0","3");
			getLatestNewsAside("725","","1","3");
			getLatestNewsAside("726","","2","3");
			
			$("#gldtSelect").get(0).selectedIndex=0;
			$("#zdxmSelect").get(0).selectedIndex=0;
			$(".gldtDiv:eq(0)").show();
			$(".zdxmUl:eq(0)").show();
			
			
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			
			$(window).resize(function(){
				$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			})
					
			$(".reportLi:eq(0)").click();
			$(".reportLi:eq(3)").click();	
			$(".reportLi:eq(7)").click();	
			$(".reportLi:eq(11)").click();		
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
                	<li><a href="javascript:window.location.href='/portal/center/kygl/kg_index.jsp'">技术管理</a></li>
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
                        	<h2 class="fl">技术管理</h2>
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
                <div class="js_bg">
                	<div class="con clearfix">
                        <img src="http://10.1.44.18/stfb/frame/kygl/images/zhengshu3.jpg" width="143" height="118" class="mr5 fl" />                        
                        <img src="http://10.1.44.18/stfb/frame/kygl/images/zhengshu2.jpg" width="143" height="118" class="mr5 fl" />                   
                        <img src="http://10.1.44.18/stfb/frame/kygl/images/zhengshu3.jpg" width="143" height="118" class="mr5 fl" />    
                    </div>                    
                </div>
            </div>
        	<!--Aside-->
            <aside class="fr">
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="gldtH">
                    	<h3>管理动态</h3>
                        <a target="_self" href="#" class="more_1">更 多</a>
                        <select id="gldtSelect" class="fr mr5 input_medium">
                        	<option>累计完成</option>
                        	<option>下两个月计划验收</option>
                        </select>
                  </hgroup>
                 <div style="display:none;" class="con gldtDiv">
                   	<p class="para"></p>
                 </div>
                 <div  style="display:none;" class="con gldtDiv">
                   	<p class="para"></p>
                 </div>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h3>重点项目</h3>
                        <a href="#" class="more_1">更 多</a>
                        <select id="zdxmSelect" class="fr mr5 input_medium">
                        	<option>国家级</option>
                        	<option>上海市</option>
                        	<option>其他</option>
                        </select>
                  </hgroup>
                 <ul style="display:none;" class="asideUl list zdxmUl">
                 </ul>
                  <ul style="display:none;" class="asideUl list zdxmUl">
                 </ul>
                 <ul style="display:none;" class="asideUl list zdxmUl">
                 </ul>
            	</div></div></div>
          </aside>
        	<!--Aside End-->
        	<div class="clear"></div>
        	<table>
            	<tr>
                	<td width="50%">
                	 <!--Panel_3-->
                <div class="panel_3 mb10">
                	<header class="clearfix">
                    	<h5 class="fl file">科技成果</h5>
                        <div cur="0" len="0" id="tabs2" class="fr tabs_1 clearfix">
                    	  <div class="fl pre"><a href="javascript:void(0);">上一页</a></div>
                    	  <ul class="fl">
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>科技奖项</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>知识产权</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>成果登记</span></a></li>
                  	     </ul>
                    	  <div class="fl next"><a href="javascript:void(0);">下一页</a></div>
                  	  </div>
                    </header>
                    <div class="con">
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->
                </td>
                 <td>
                  <!--Panel_3-->
                 <div class="panel_3 mb10">
                	<header class="clearfix">
                    	<h5 class="fl file">企业标准</h5>
                        <div cur="0" len="0" id="tabs2" class="fr tabs_1 clearfix">
                    	  <div class="fl pre"><a href="javascript:void(0);">上一页</a></div>
                    	  <ul class="fl">
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>指导意见</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>招标通用</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>标准图集</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>技术规程</span></a></li>
                  	     </ul>
                    	  <div class="fl next"><a href="javascript:void(0);">下一页</a></div>
                  	  </div>
                    </header>
                    <div class="con">
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
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
                    	<h5 class="fl file">常用软件</h5>
                        <div cur="0" len="0" id="tabs2" class="fr tabs_1 clearfix">
                    	  <div class="fl pre"><a href="javascript:void(0);">上一页</a></div>
                    	  <ul class="fl">
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>发展规划</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>管理规定</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>常用表式</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>办事指南</span></a></li>
                  	     </ul>
                    	  <div class="fl next"><a href="javascript:void(0);">下一页</a></div>
                  	  </div>
                    </header>
                    <div class="con">
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->
                </td>
                 <td>
                  <!--Panel_3-->
                 <div class="panel_3 mb10">
                	<header class="clearfix">
                    	<h5 class="fl file">建言献策 </h5>
                        <div cur="0" len="0" id="tabs2" class="fr tabs_1 clearfix">
                    	  <div class="fl pre"><a href="javascript:void(0);">上一页</a></div>
                    	  <ul class="fl">
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>运营类</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>维保类</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>建设类</span></a></li>
                    	    <li class="reportLi" ><a href="javascript:void(0);"><span>其他</span></a></li>
                  	     </ul>
                    	  <div class="fl next"><a href="javascript:void(0);">下一页</a></div>
                  	  </div>
                    </header>
                    <div class="con">
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                         
                         <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                         <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                         <div class="m6 t_r"><input id="msg" name="msg" value="我要发言" type="button"></div>
                    </div>
                </div>
                <!--Panel_3 End-->
                </td>
                </tr>
            </table>
        </div>
	</div>
</body>
</html>
