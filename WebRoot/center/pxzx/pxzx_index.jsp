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
<title>培训中心首页</title>
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
		<script src="../mscp/js/highcharts.js"></script>
		<script src="../../js/show.js"></script>
		<script src="js/pxzx_ajax.js"></script>
		<script src="js/pic.js" type="text/javascript"></script>
<%
String basePath = request.getContextPath(); 

String headNewsId="1893";
String newsIp1 = "1894";
String newsIp2 = "1895";
String newsIp3 = "1896";
String newsIp4 = "1897";
String newsIp5 = "1898";
//if("10.1.48.30".equals(request.getLocalAddr())||"10.1.48.39".equals(request.getLocalAddr())){
	headNewsId = "1972";
	newsIp1 = "1953";
	newsIp2 = "1973";
	newsIp3 = "1974";
	newsIp4 = "1975";
	newsIp5 = "1976";
//}

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


ShowJeecmsInfo dwgk=new ShowJeecmsInfo( request,response);
		
JSONObject contentObj=null;
String description="";
JSONArray contentList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+headNewsId+"'}");
if(contentList!=null){
	contentObj=contentList.getJSONObject(0);
	description=contentObj.getString("description");
	String txt=contentObj.getString("txt");
	if(description=="null"){
		if(txt=="null"){
			description=StringUtil.MySubstring(contentObj.getString("title"), 340);//减少字数
		}else{
			description=StringUtil.splitAndFilterString(txt, 340);
		}					
	}else{
		description=StringUtil.MySubstring(description, 340);
	}
}

String deptId = "";
String loginName = (String)session.getAttribute("cs_login_name");
if(loginName==null){
	loginName = "";
}
deptId = (String)session.getAttribute("deptId");
if(deptId==null){
	deptId = "";
}
%>		
<style>

ul.zyl{
	background:#fff;
}
ul.zyl li{
	background:#f8f8f8;
	border-top:#eee 1px solid;
	border-bottom:#eee 1px solid;
}
.color1{
	background: #ffe4cf; 
}
.color2{
	background: #e3ffd4; 
}
.color3{
	background: #e5dcff; 
}
.color4{
	background: #d5eeff; 
}
.color5{
	background:#ffeac1;
}

ul.zyl li{
	height:59px; 
	position:relative;
	margin-bottom:1.5px;
}

ul.zyl li .textcontainer{
	position:absolute; 
	z-index: 100; 
	width: 90%; 
	padding: .5em 1em;
}
ul.zyl li .ui-li-aside{
	font-size: 24px; 
	right: 1em;
	position: absolute;
	top: 1em;
	margin: 0;
	text-align: right;
	
}
ul.zyl li .colorbg{
	position:absolute; 
	z-index: 10; 
	width:1%; 
	height: 59px; 
	display: block; 
	clear: both;
}
.zyl h3{
	display: block;
	margin: .45em 0;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	
}

.intro{
	padding-left:9px;
	margin:0 10px;
	height:210px;
}
.intro .con{
	padding:11px 9px 11px 2px;
	height:192px;
}
.intro img, .intro .img{
	width:285px;
	height:188px;
}
.intro h3{
	font-style:italic;
	font-weight:normal;
}
.intro p{
	text-indent:24px;
	max-height:160px;
	overflow:hidden;
}
.menu_list{
	position:absolute;
	right:0;
	top:150px;
	z-index:999;
	width:192px;
	height:403px;
	padding-left:10px;
}
.menu_list .tit{
	width:20px;
	float:left;
	padding-top:45px;
}
.menu_list ul{
	padding:1px 12px;
	width:146px;
	float:left;
}
.menu_list li{
	line-height:28px;
	font-size:14px;
}
.menu_list li a{
	padding-left:8px;}
	

.intro{
	background:url(images/intro1.png) left top no-repeat;
}
.intro .con{
	background:url(images/intro2.png) right top no-repeat;
}
.intro h3{
	color:#1366ac;
}
.txt{
	color:#a9a9a9;}
.menu_list{
	background:url(images/menu-right.png) left top no-repeat;
}
.menu_list .tit a{
	color:#4f81bd;
}
.menu_list li{
	border-bottom:#fff 1px solid;
}
.menu_list li a{
	border-bottom:#cdcdcd 1px solid;
}
</style>

<script type="text/javascript">
		
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
			
			
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			
			$(window).resize(function(){
				$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			});
					
			$(".reportLi:eq(0)").click();
			t = setInterval("showAuto()", 6000);
			
	        getLatestNewsAside(<%=newsIp1%>,"0");
	        getLatestNewsAside(<%=newsIp2%>,"1");
	        getLatestNewsAside(<%=newsIp3%>,"2");
	        getLatestNewsAside(<%=newsIp4%>,"3");
	        getFGFW(<%=newsIp5%>,"0");
	        
	        });

		$(function(){
			$.ajax({
				type : 'post',
				url : '/portal/pxzx/findIfTrainLeader.action?random='+Math.random(),
				dataType:'json',
				data:{
					loginName:'<%=loginName%>'
				},
				cache : false,
				error:function(){
					//alert("系统连接失败，请稍后再试！")
				},
				success:function(object){
					if(object==null||object.length==0){
						$("#searchDiv").hide();
						drawChart("<%=deptId%>","dept");
					}else{
						drawChart("<%=deptId%>","leader");
					}
				}
			});
		})
		

		
	</script>
		
</head>

<body>

	<div class="main mw1002">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" alt="展开"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#'">培训中心</a></li>
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
            	<div class="news clearfix" style="height:200px;">
                  <div>
                        <div class="news_l" style="height:200px;background-image: none;">
                	<!--focusPic-->
                	<div class="focusPic fl" id="play" style="height:180px;">
                        <ul class="fp_list" id="play_list" style="height:180px;">
                        	<li>
                        		<img src="image/pic.jpg" style="height:180px;"></img>
                        	</li>
                        </ul>
                    </div>
                	<!--focusPic End-->
                	<!--Txt News-->
                    <div class="fl" id="newsCenter">
                        <div class="fl mlr10">
                        	<h3>培训中心简介</h3>
                            <p><%=description%></p>
                            <div class="t_r"><a href="../../jeecms/contentDetail.jsp?content=<%=contentObj.getString("contentId")%>" target="_blank" class="more_2">详情</a></div>
                        </div>
                    </div>
                	<!--Txt News End-->
                </div>
                </div>
                </div>
                <!--News End-->
                
                
                <div class="panel_4 mb10 panel_8">
                	<header>
                    	<div class="tit">
                        	<div class="bg clearfix">
                    			<h5 class="fl stats" id="h5_title"></h5>
                            </div>
                        </div>
                    </header>
                    <div class="con clearfix">
                    	<div class="fl w50p">
                        	<div class="gray_border">
                                <div class="block">
                                    <div class="corner ">
                                    	<div class="categories t_r" style="font-size: 18px;">
                                    		各板块年度总计划完成率>
                                    	</div>
	                                    <div class="bor">
											<ul class="zyl">
												<li>
											        <div class="textcontainer clearfix">
											          <h3 class="fl">集团年度总计划完成率(人次)</h3>
											          <p class="ui-li-aside fr" id="value1"></p>
											        </div>
											        <div class="colorbg color1 clearfix"></div>
											      </li>
												<li>
											        <div class="textcontainer clearfix">
											          <h3 class="fl">上级党校和在线学习年度总计划完成率(人次)</h3>
											          <p class="ui-li-aside fr" id="value2"></p>
											        </div>
											        <div class="colorbg color2 clearfix"></div>
											      </li>
											    <li>
											        <div class="textcontainer clearfix">
											          <h3 class="fl">各直属单位年度总计划完成率(人次)</h3>
											          <p class="ui-li-aside fr" id="value3"></p>
											        </div>
											        <div class="colorbg color3 clearfix"></div>
											      </li>
											    <li>
											        <div class="textcontainer clearfix">
											          <h3 class="fl">培训中心年度总计划完成率(人次)&nbsp;&nbsp;<a href="page1.jsp" target="_blank" style="display:inline;color: blue;text-decoration: underline;">详情>></a></h3>
											          <p class="ui-li-aside fr" id="value4"></p>
											        </div>
											        <div class="colorbg color4 clearfix"></div>
											      </li>
											</ul>
	                                    	<!--<table>
	                                    		<tr>
	                                    			<td>集团年度总计划完成率(人次)</td><td id="value1"></td><td>&nbsp;</td>
	                                    		</tr>
	                                    		<tr>
	                                    			<td>上级党校年度总计划完成率(人次)</td><td id="value2"></td><td>&nbsp;</td>
	                                    		</tr>
	                                    		<tr>
	                                    			<td>各直属单位年度总计划完成率(人次)</td><td id="value3"></td><td>&nbsp;</td>
	                                    		</tr>
	                                    		<tr>
	                                    			<td>培训中心年度总计划完成率(人次)</td><td id="value4"></td><td><a href="page1.jsp" target="_blank">详情>></a></td>
	                                    		</tr>
	                                    	</table>-->
	                            		</div>
                                    </div>
                                </div>
                        	</div>
                        </div>
                        <div class="fl w50p">
                        	<div class="gray_border">
                                <div class="block">
                                    <div class="corner ">
                                    	<div class="categories t_r" style="font-size: 18px;">
                                    		年度技能鉴定合格人数等级分布>
                                    	</div>
	                                    <div id="piePic" class="bor">
	                            		</div>
                                    </div>
                                </div>
                        	</div>
                        </div>
                        <div class="fl w50p">
                        	<div class="gray_border">
                                <div class="block">
                                    <div class="corner ">
                                    	<div class="categories t_r" style="font-size: 18px;">
                                    		各直属单位年度培训计划人次完成率>
                                    	</div>
	                                    <div id="blockPic1" class="bor">
	                            		</div>
                                    </div>
                                </div>
                        	</div>
                        </div>
                        <div class="fl w50p">
                        	<div class="gray_border">
                                <div class="block">
                                    <div class="corner ">
                                    	<div class="categories t_r" style="font-size: 18px;">
                                    		员工年度培训课时完成覆盖率>
                                    	</div>
	                                    <div id="blockPic2" class="bor">
	                            		</div>
                                    </div>
                                </div>
                        	</div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
                <!--Panel_3-->
                <div class="panel_3 mb10">
                	<header class="clearfix"><div class="bg_3 clearfix">
                    	<h5 class="fl file">近期工作重点</h5>
                        <div class="fr tabs_1" style="display:none;">
                            <ul class="fl">                       	
                            	<li class="reportLi" ><a href="javascript:void(0);"><span></span></a></li>
                            </ul>
                              
                        </div></div>
                    </header>
                    <div class="con">
                        <div class="reportDiv clearfix">
                            <ul class="columns clearfix mb10">
                                
                            </ul>
                            <p class="fr"><a target="_self" class="more_3" href="#">更多</a></p>
                        </div>
                        
                    </div>
                </div>
            </div>
        	<!--Aside-->
            <aside class="fr">
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>培训信息</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>公告信息</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>办事信息</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div class="panel_1" id="searchDiv"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH">
                    	<h5>技能鉴定成绩查询</h5>
                        <a target="_self" href="#" class="more_1">更 多</a>
                  </hgroup>
                 <ul class="asideUl list">
                    </ul>
            	</div></div></div>
            	<div>
            	<table width="100%" border="0" cellspacing="0" cellpadding="0" style=" margin-top:15px;">
            <tr>
              <td align="left" valign="top"><a id="xxxt" target="_blank" href="http://10.1.44.100/login.php"><img src="image/xt.jpg" name="Image34" width="187" height="64" border="0" id="Image34" /></a></td>
            </tr>
          </table>
          </div>
          </aside>
        	<!--Aside End-->
        </div>
	</div>
</body>
</html>
