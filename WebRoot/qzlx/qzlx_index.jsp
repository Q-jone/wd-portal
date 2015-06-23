<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.FileInputStream"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

int channel1 = 1614;//上级单位党的群众路线教育实践活动
int channel2 = 1615;//集团公司党的群众路线教育实践活动
int channel3 = 1633;//领导讲话
int channel4 = 1632;//权威声音
int channel5 = 1634;//资料下载
 %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>党的群众路线教育实践活动</title>
<style>
	body{
		background: #fff url(images/bg.png) left top repeat-x;
		padding: 0;
		margin: 0;
		font:12px/1.5  Arial, 'Helvetica Neue', 'Microsoft YaHei', 'Liberation Sans', FreeSans, sans-serif;

	}
	a,
	h1,
	h2,
	h3,
	h4,
	h5,
	h6,
	li,
	ul,
	div,
	dl,
	dt,
	dd{
	  border: 0;
	  margin: 0;
	  padding: 0;
	  text-decoration:none;
	  display: block;
	  font-weight: normal;
	}
	h1 {
		font-size: 24px;
	}

	h2 {
		font-size: 18px;
	}

	h3 {
		font-size: 16px;
	}

	h4 {
		font-size: 15px;
	}

	h5 {
		font-size: 14px;
	}

	h6 {
		font-size: 11px;
	}
	img {
	  color: transparent;
	  font-size: 0;
	  vertical-align: middle;
	  border:none;
	/*
	  For IE.
	  http://css-tricks.com/ie-fix-bicubic-scaling-for-images
	*/
	  -ms-interpolation-mode: bicubic;
	}

	ol,
	ul {
	  list-style: none;
	}

	li {
	/*
	  For IE6 + IE7:

	  "display: list-item" keeps bullets from
	  disappearing if hasLayout is triggered.
	*/
	  display: list-item;
	}
	/* http://sonspring.com/journal/clearing-floats */

.clear {
	  clear: both;
	  display: block;
	  overflow: hidden;
	  visibility: hidden;
	  width: 0;
	  height: 0;
	}

	/* http://www.yuiblog.com/blog/2010/09/27/clearfix-reloaded-overflowhidden-demystified */

	.clearfix:before,
	.clearfix:after {
	  content: '.';
	  display: block;
	  overflow: hidden;
	  visibility: hidden;
	  font-size: 0;
	  line-height: 0;
	  width: 0;
	  height: 0;
	}

	.clearfix:after{
	  clear: both;
	}

	/*
	  The following zoom:1 rule is specifically for IE6 + IE7.
	  Move to separate stylesheet if invalid CSS is a problem.
	*/

	.clearfix {
	  zoom: 1;
	}
	.fl{
		float:left;
	}
	.fr{
		float:right;
	}

	.t_r{
		text-align:right;
	}
	.t_c{
		text-align:center;
	}
	.mb10{
		margin-bottom:10px;
	}
	.m10{
		margin:10px;
	}

	.body{
		background: url(images/bg.jpg) 50% top no-repeat;
		/*height: 100%;*/
		padding-top: 159px;
	}
	.main{
		width: 960px;
		padding: 0 10px;
		margin: 0 auto;
		background: #fff /* url(images/main_bg.png) left top repeat-y*/;
	}
	h2.date{
		background: #e1e1e1;
		line-height: 34px;
		color: #ac7d6b;
		padding: 0 10px;
		margin: 0 10px;
	}
	footer{
		background: #e1e1e1;
		line-height: 34px;
		color: #a88d6e;
		font-size: 11px;
		margin: 0 10px 0;
		text-align: center;
	}
	.news{
		width: 459px;
		margin: 10px 10px 0 11px;
		
		background: #fff;
	}
	.news h1{
		height:50px;
		background:url(images/h1.png) 0 0 no-repeat;
		padding-left:50px;
		color:#a97608;
		line-height:50px;
		font-weight:normal;
		text-shadow:#fff 1px 1px 1px;
	}
	.news ul{
		padding:9px;
	}
	.news li{
		/*border-bottom:#c7cbd0 1px dashed;*/
		line-height:24px;
		background:url(images/dot.png) 0px 10px no-repeat;
		padding:0 0 4px 10px;
		font-size:14px;
	}
	
	.news li a{
		color:#323131;
		display:block;
		width:440px;
		overflow:hidden;
	}
	.news li a b{
		color:#7f5907;
		display:block;
	}
	.news li a span{
		display:block;
		height:40px;
		line-height:20px;
		overflow:hidden;
	}
	.news .imgnew{
		border-bottom:#d3d3d3 1px solid;
		padding:0 0 8px 0;
		position: relative;
	}
	.news .imgnew img{
		width:420px;
		height:220px;
		padding-left:20px;
	}
	.news .imgnew ul{
		padding:0;
		border:none;
	}
	.news .imgnew li{
		border:none;
		background:none;
		padding:0;
	}
	
	.news .imgnew li.img a{
		color:#fff;
		display:block;
		width:420px;
		overflow:hidden;
	}
	.news .imgnew .tit{
		background:#000;
		filter:alpha(opacity=80);
		-moz-opacity:0.8;
		opacity:0.8;
		position:absolute;
		z-index:999;
		top:180px;
		left:20px;
		width:405px;
		height:33px;
		padding:6px 8px;
		overflow:hidden;
		color:#fff;
	}
	
	.news .imgnew .tit a{
		color:#fff;
		height:18px;
		line-height:18px;
		margin-bottom:4px;
		width:400px;
	}
	.new{
		background:url(images/new.gif) right 9px no-repeat;
		padding-right:5px;
	}
	/*.news .imgnew dt{
		font-weight:bold;
		line-height:20px;
	}
	.news .imgnew dd{
		line-height:20px;
	}*/
	.news .imgnew .scrollnav{
		height:11px;
		padding-left:151px;
	}
	.news .imgnew .scrollnav li{
		background : url(images/scrollnav.png) left top no-repeat;
		float: left;
		margin: 0 3px;
		cursor: pointer;
		width: 0px;
		height: 0px;
		overflow: hidden;
		padding-left: 12px;
		padding-top: 11px;
		background-repeat: no-repeat;
	}
	.news .imgnew .scrollnav li.current, .news .imgnew .scrollnav li:hover{
	background-position:left -11px;
}
	
	.news ul{
		border-top:#fff 1px solid;
	}
	.imglink img{
		width: 219px;
		margin: 0 10px 10px 11px;
		height: 45px;
		float: left;;
	}
</style>
<script src="../js/jquery-1.7.1.js"></script>
<script>
function showPic(obj){
	obj.find("#play_text li").live("click",function(){
		var pos = $(this).html();
		obj.find("[id=play_list]").hide();
		obj.find("[id=play_info]").hide();
		obj.find("#play_text li").removeClass("current");
		obj.find("[id=play_list]:eq("+(pos-1)+")").show();
		obj.find("[id=play_info]:eq("+(pos-1)+")").show();
		obj.find(this).addClass("current");
	});
}

$(function(){
	getPicNews(<%=channel1%>,0,0);
	getNewss(<%=channel1%>,0);
	
	getPicNews(<%=channel2%>,4,1);
	getNewss(<%=channel2%>,1);
	
	showPic($("#tit1"));
	showPic($("#tit2"));

	/*
	var d = new Date();
	var strYear = d.getFullYear();    
    var strDay = d.getDate();    
    var strMonth = d.getMonth()+1;  
    var strWeek = d.getDay();  
		var day = "";
    switch (strWeek) {
              case 0:
                  day = "星期日";
                  break;
              case 1:
                  day="星期一";
                  break;
              case 2:
                  day = "星期二";
                  break;
              case 3:
                  day = "星期三";
                  break;
              case 4:
                  day = "星期四";
                  break;
              case 5:
                  day = "星期五";
                  break;
              case 6:
                  day = "星期六";
                  break;
          }
    datastr = "今天是"+strYear+"年"+strMonth+"月"+strDay+"日"+"（"+day+"）";
    var d18 = new Date();
    d18.setFullYear(2012,10,7);
    var time18 =  d18.getTime();
    d18.setFullYear(strYear,strMonth-1,strDay);
  	var span = (d18.getTime() - time18) / 86400000;  
    $(".date").html(datastr);
	*/
});


function getPicNews(sj_id,pos,pos2){
	$.ajax({
		type: 'POST',
		url: '/portal/jeecms/getJeecmsInfo.action?random='+Math.random(),
		type:"post",
		dataType:'json',
		cache : false,
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:sj_id,
			hasTitleImg:"0,1",
			typeId:'2',
			rownum:'4'
		},
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){			
			if(obj){
					//var hiddenText = "";
					for(var i=0;i<obj.length;i++){
						//if(i==0){
							if(obj[i].titleImg!=null&&obj[i].titleImg.indexOf("jeecms")>0){
								obj[i].titleImg = obj[i].titleImg.replace("/jeecms","");
							}
							//if(obj[i].txt==null||obj[i].txt=="null"||obj[i].txt=="unchecked"){
							//	hiddenText = "";
							//}else{
								
							//	hiddenText = obj[i].txt.replace(/<.*?>/g,"");
								
							//}	
							if(obj[i].title!=null&&obj[i].title.length>30){
								obj[i].title = obj[i].title.substring(0,30)+"...";
							}
							if(obj[i].description==null){
								obj[i].description = "";
							}
							var m = "<a target='_blank' title='"+obj[i].description+
								"' href='/portal/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+
								"<dt>"+obj[i].title+"</dt>"+
								//"<dd>"+hiddenText+"</dd>"+
								"</a>";
								
							var n = "<a target='_blank' href='/portal/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+
								"<img src='<%=jeecmsUrl%>"+obj[i].titleImg+"' /></a>";
							$("[id=play_info]:eq("+(pos+i)+")").html(m);
							$("[id=play_list]:eq("+(pos+i)+")").html(n);
							if(i==0){
								$("[id=play_text]:eq("+pos2+")").append("<li class='current'>"+(i+1)+"</li>");
							}else{
								$("[id=play_text]:eq("+pos2+")").append("<li>"+(i+1)+"</li>");
							}
							//break;
						//}	
					}
				
			}else{
				$("[id=hydtDl]:eq("+pos+")").hide();
			}
		}	  
	});	
}


function getNewss(sj_id,pos){
	$.ajax({
		type: 'POST',
		url: '/portal/jeecms/getJeecmsInfo.action?random='+Math.random(),
		data:{
			method:"JEECMS.GET_CONTENT_LIST",
			channelId:sj_id,
			hasTitleImg:"0,1",
			typeId:'1,2,3,4',
			rownum:'4'
		},
		dataType:'json',
		cache : false,
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){			
			if(obj){
				var u = "";
				var simpleTitle="";
				for(var i=0;i<obj.length;i++){
						simpleTitle = obj[i].title;
						if(simpleTitle.length>22){
							simpleTitle = simpleTitle.substring(0,22)+"...";
						}
						u += " <li class='clearfix'><a style='width:380px' class='";
						if(ifNew(obj[i].releaseDate)){
							u += "new "; 
						}
						u += "fl' target='_blank' title='"+obj[i].title+"' href='/portal/jeecms/contentDetail.jsp?content="+obj[i].contentId+"'>"+
						"<b>"+simpleTitle+"</b>"+
						//"<div class='clear'></div>"+
						//"<span>"+obj[i].txt+"</span>"+
						"</a><span style='height:22px;font-size:11px;line-height:22px;color:#444;' class='fr'>"+obj[i].releaseDate.substring(5)+"</span></li>";
				}
				$("[id=hydtUl]:eq("+pos+")").html(u);
				if(obj.length==0){
					$("[id=hydtUl]:eq("+pos+")").html("&nbsp;&nbsp;无相关信息。");
				}
				
				
			}
		}	  
	});	
}

function mailto(){
	window.location.href="mailto:qzlx@shmetro.com";
}

function ifNew(pub_date){
	var result = false;
	var date = new Date();
    
    var pub_year = parseInt(pub_date.substring(0,4));
    var pub_month = parseInt(pub_date.substring(5,7));
    var pub_day = parseInt(pub_date.substring(8,10));
    var dd = new Date(pub_year,(pub_month-1),pub_day);
    if((date.getTime()-dd.getTime())/ 3600000 / 24<3){
    	result = true;
    }
    return result;
}
</script>

</head>
<body>
	<div class="body">
		<div class="main">
			<!--<h2 class="date"></h2>-->
			<div class="clearfix">
				<div class="news fl">
                    <h1>上级单位党的群众路线教育实践活动</h1>
                    <div class="imgnew" id="tit1">
                    	<div class="tit">
                    		<ul>
                    			<li><a>
                    			<h6 id="play_info"></h6>
                    			<h6 id="play_info" style="display:none"></h6>
                    			<h6 id="play_info" style="display:none"></h6>
                    			<h6 id="play_info" style="display:none"></h6>
                    			</a></li>
                    		</ul>
                    		<ul class="scrollnav" id="play_text">
                    			
                    		</ul>
                    	</div>
                    	 <ul>
                    		<li>
                    			<a>
                    				<div id="play_list"></div>
                    				<div id="play_list" style="display:none"></div>
                    				<div id="play_list" style="display:none"></div>
                    				<div id="play_list" style="display:none"></div>
                    			</a>
                    		</li>
                    	</ul>
                    	
                        
                    </div>
                    <ul id="hydtUl">
                    </ul>
                   <span style="float:right; font-size:11px; margin-bottom:10px; padding-right:10px;"> <a href="/portal/jeecms/findStfbNewsByPage.action?channelId=<%=channel1%>" target="_blank">更多></a></span>
                </div>
                <div class="news fl">
                    <h1>集团公司党的群众路线教育实践活动</h1>
                    <div class="imgnew" id="tit2">
                    	<div class="tit">
                    		<ul>
                    			<li><a>
                    			<h6 id="play_info"></h6>
                    			<h6 id="play_info" style="display:none"></h6>
                    			<h6 id="play_info" style="display:none"></h6>
                    			<h6 id="play_info" style="display:none"></h6>
                    			</a></li>
                    		</ul>
                    		<ul class="scrollnav" id="play_text">
                    			
                    		</ul>
                    	</div>
                    	 <ul>
                    		<li>
                    			<a>
                    				<div id="play_list"></div>
                    				<div id="play_list" style="display:none"></div>
                    				<div id="play_list" style="display:none"></div>
                    				<div id="play_list" style="display:none"></div>
                    			</a>
                    		</li>
                    	</ul>
                    	
                        
                    </div>
                    <ul id="hydtUl">
                    </ul>
                    <span style="float:right; font-size:11px; margin-bottom:10px; padding-right:10px;"> <a href="/portal/jeecms/findStfbNewsByPage.action?channelId=<%=channel2%>" target="_blank">更多></a></span>
                </div>
                <div style="clear:both"></div>
                <div class="clearfix imglink">
                	<a href="/portal/jeecms/findStfbNewsByPage.action?channelId=<%=channel3%>" target="_blank"><img src="images/img1.jpg" title="领导讲话"></a>
                	<a href="/portal/jeecms/findStfbNewsByPage.action?channelId=<%=channel4%>" target="_blank"><img src="images/img2.jpg" title="权威声音"></a>
                	<a href="/portal/jeecms/findStfbNewsByPage.action?channelId=<%=channel5%>" target="_blank"><img src="images/img3.jpg" title="简报资料"></a>
                	<a href="mailto:qzlx@shmetro.com"><img src="images/img4.jpg" title="意见箱"></a>
                </div>
                <!-- <footer>版权所有</footer> -->
            </div>
		</div>
	</div>
</body>
</html>