<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="com.wonders.stpt.jeecms.ShowJeecmsInfo"%>
<%@ page import="com.wonders.stpt.jeecms.StringUtil"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>党纪工团首页</title>
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
		<script src="js/djgt_ajax.js"></script>
<%
String basePath = request.getContextPath(); 
String jeecmsUrl="";
Properties properties = new Properties();
String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
try {
	FileInputStream fis = new FileInputStream(configPath);
	properties.load(fis);
	jeecmsUrl = properties.getProperty("urlCms");			
} catch (Exception e) {
	//e.printStackTrace();
	jeecmsUrl=basePath;
}

String headNewsId="2061";
ShowJeecmsInfo dwgk=new ShowJeecmsInfo( request,response);
		
JSONObject contentObj=null;
JSONArray contentList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+headNewsId+"'}");
if(contentList!=null){
	contentObj=contentList.getJSONObject(0);
}

String description=contentObj.getString("description");
String txt=contentObj.getString("txt");
if(description=="null"){
	if(txt=="null"){
		description=StringUtil.MySubstring(contentObj.getString("title"), 150);
	}else{
		description=StringUtil.splitAndFilterString(txt, 150);
	}					
}else{
	description=StringUtil.MySubstring(description, 150);
}
%>		
<script type="text/javascript">
var tbttId="2060";//头版头条  ???
var dtgzId="2061";//动态工作  ???
//纪检工作
var ljwhId="2021";
var fgfwId="2000";
var fxfkId="2002";
var zjjfId="2006";
var jjxgwjId="2008";

//工会天地
var xjwtId="2010";
var nggzId="2011";
var mgwqId="2032";
var jshdId="2033";
var zdtxId="2034";

//组工天地
var cxzyId="2041";
var zzjsId="2042";
var gzjlId="2043";
var djljId="2044";
var xjdxId="2045";

//申通团委
var zthdId="2047";
var tqdtId="2049";
var zdgzId="2050";
var stzzjsId="2051";
var wjglId="2052";

//侧边栏
var wmcjId="2056";	
var qywhId="2057";
var xcjyId="2058";
var zyzzcId="2059";
		
setbasePath("<%=basePath%>");

				
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
				});
			});
			
			$(".next a").click(function(){
				$(this).parent().prev("ul").children(".reportLi").hide();
				for(var i=cur;i<cur+page;i++){
					//alert(i);
					$(this).parent().prev("ul").children(".reportLi").eq(i+1).show();	
				}
				//$(this).parent().parent().children(".reportLi").eq(cur+1).click();
				
				if(cur+page==len-1){
					$(this).parent().hide();
				}else{
					$(".pre").show();
				}
					
				cur++;
				
			});
			
			$(".pre a").click(function(){
				$(this).parent().next("ul").children(".reportLi").hide();
				for(var i=cur;i<cur+page;i++){
					$(this).parent().next("ul").children(".reportLi").eq(i-1).show();	
				}
				//$(this).parent().parent().children(".reportLi").eq(cur).click();	
				
				if(cur-1==0){
					$(this).parent().hide();
				}else{
					$(".next").show();
				}
				
				cur--;
			});
			
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
			
			
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			
			$(window).resize(function(){
				$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			});
					
			$(".reportLi:eq(0)").click();
			$(".reportLi:eq(7)").click();
			$(".reportLi:eq(12)").click();
			$(".reportLi:eq(17)").click();		
			t = setInterval("showAuto()", 6000);
			
	        getDJGTPicNews("<%=jeecmsUrl%>",dtgzId);
	        getHeadNews(tbttId);
	        getDJGTNews(dtgzId);
	        getLatestNews(xjwtId,"5");
	        getLatestNews(nggzId,"6");
	        getLatestNews(mgwqId,"7");
	        getLatestNews(jshdId,"8");
	        getLatestNews(zdtxId,"9");
	        getLatestNews(cxzyId,"10");
	        getLatestNews(zzjsId,"11");
	        getLatestNews(gzjlId,"12");
	        getLatestNews(djljId,"13");
	        getLatestNews(xjdxId,"14");
	        getLatestNews(zthdId,"15");
	        getLatestNews(tqdtId,"16");
	        getLatestNews(zdgzId,"17");
	        getLatestNews(stzzjsId,"18");
	        getLatestNews(wjglId,"19");
	        getLatestNewsAside(wmcjId,"0");
	        getLatestNewsAside(qywhId,"1");
	        getLatestNewsAside(xcjyId,"2");
	        getLatestNewsAside(zyzzcId,"3");
	        getFGFW(ljwhId,"0");
	        getFGFW(fgfwId,"1");
	        getFGFW(fxfkId,"2");
	        getFGFW(zjjfId,"3");
	       getLatestNews(jjxgwjId,"4");
	        
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
                	<li><a href="javascript:window.location.href='/portal/center/djgt/dj_index.jsp'">党纪工团</a></li>
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
                        	<h2 class="fl">党纪工团</h2>
                        	<h6 class="fl">News</h6>
                            <p class="t_r"><a href="<%=basePath%>/jeecms/findByPage.action?channelId=<%=headNewsId%>" target="_self" class="more_2">更多</a></p>
                        </hgroup>
                        <section>
                        	<a href="../contentDetail.jsp?content=<%=contentObj.getString("contentId")%>" target="_blank" title="<%=description%>">
                        	<h5><%=contentObj.getString("title") %></h5>
                            <p><%=description%></p>
                            </a>
                        </section>
                        <ul class="txt_list">
                        	<li><span><a href="#" ></a></span><span></span></li>

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
                    	<h5 class="fl file">纪检工作</h5>
                        <div class="fr tabs_1">
                        
                            <ul class="fl">                       	
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>廉洁文化</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>法规服务</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>风险防控</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>执纪纠风</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>相关文件</span></a></li>   	                   	
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
                    	<h5 class="fl file">工会天地</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>宣教文体</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>女工工作</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>民管维权</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>竞赛活动</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>制度体系</span></a></li>
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
                    	<h5 class="fl file">组工天地</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>创先争优</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>组织建设</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>工作交流</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>党建联建</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>先进典型</span></a></li>
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
                    	<h5 class="fl file">申通团委</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>主题活动</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>团青动态</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>重点工作</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>组织建设</span></a></li>
                            	<li class="reportLi" ><a href="javascript:void(0);"><span>文件管理</span></a></li>
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
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>文明创建</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>企业文化</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>宣传教育</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>志愿者之窗</h5>
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
