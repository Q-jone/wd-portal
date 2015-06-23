<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>十八大专题</title>
<style type="text/css">
	body{
		background:#fff url(images/18_main2_bg.jpg) left top repeat-x;
		margin:0;
		padding:0;
  		font:12px/1.5 'Helvetica Neue', 'Microsoft YaHei', Arial, 'Liberation Sans', FreeSans, sans-serif;
	}
		
	h1 {
	  font-size: 26px;
	}
	
	h2 {
	  font-size: 20px;
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
	
	ul, li{
	  list-style: none;
	}
	
	li {
	  margin:0;
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
	/*
	  Override the default (display: inline) for
	  browsers that do not recognize HTML5 tags.
	
	  IE8 (and lower) requires a shiv:
	  http://ejohn.org/blog/html5-shiv
	*/
	  display: block;
	}
	
	html,
	body {
	  height: 100%;
	}
	
	
	b,
	strong {
	/*
	  Makes browsers agree.
	  IE + Opera = font-weight: bold.
	  Gecko + WebKit = font-weight: bolder.
	*/
	  font-weight: bold;
	}
	
	img {
	  color: transparent;
	  font-size: 0;
	  vertical-align: middle;
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
	
	.body{
		background:url(images/18_main2.jpg) center top no-repeat;
		padding-top:191px;
	}
	.main{
		width:980px;
		margin:0 auto;
		position:relative;
	}
	.news, .files, .study,.vidio{
		position:absolute;
		z-index:100;
		width:479px;
		background:#fff url(images/18_main2_lm.png) left bottom repeat-x;
	}
	.news h1, .files h1, .study h1{
		height:50px;
		background:url(images/h1.png) 5px 2px no-repeat;
		padding-left:56px;
		color:#a97608;
		line-height:50px;
		font-weight:normal;
	}
	.news h1 span, .files h1 span, .study h1 span {
		float:left;
	}
	.news .con, .files .con, .study .con,.vidio .con{
		background:url(images/conner.png) right bottom no-repeat;
	}
	.news ul, .files ul, .study ul,.vidio ul{
		padding:0 18px 18px 18px;
		background-repeat:no-repeat;
		background-position:407px bottom;
	}
	.news ul{
		background-image:url(images/news.png);
	}
	.vidio ul{
		background-image:url(images/vidio.png);
	}
	
	.files ul, .study ul{
		background-image:url(images/list.png);
	}
	.news li, .files li, .study li, .vidio li{
		border-bottom:#666 1px dashed;
		line-height:24px;
		background:url(images/dot.png) 6px 10px no-repeat;
		padding:0 12px;
		font-size:14px;
	}
	.news li a, .files li a, .study li a,.vidio li a{
		color:#323131;
		display:block;
		width:420px;
		overflow:hidden;
	}
	.vidio li a,.files li a,.news li a b,.study li a b{
		text-overflow: ellipsis;
		white-space: nowrap;
		-o-text-overflow: ellipsis;
		}
	.news li a b, .files li a b, .study li a b{
		color:#7f5907;
		display:block;
	}
	
	.news li a span, .study li a span{
		display:block;
		height:44px;
		line-height:20px;
	}
	.news{
		left:0;
		top:66px;
	}
	.files{
		left:0;
		top:66px;
	}
	.study{
		right:0;
		top:330px;
	}
	.vidio{
		right:0;
		top:66px;
	}
	.vidio .v1{
		border-bottom:#d3d3d3 1px solid;
		padding:18px 17px;
	}
	.news .imgnew{
		border-bottom:#d3d3d3 1px solid;
		padding:0 17px 18px 17px;
	}
	.news .imgnew img{
		width:445px;
		height:334px;
	}
	.news .imgnew dl{
		background:#000;
		filter:alpha(opacity=80);
		-moz-opacity:0.8;
		opacity:0.8;
		position:absolute;
		z-index:999;
		top:305px;
		left:17px;
		width:429px;
		height:67px;
		padding:6px 8px;
		overflow:hidden;
		color:#fff;
	}
	.news .imgnew dl a{
		color:#fff;
	}
	.news .imgnew dt{
		font-weight:bold;
		line-height:30px;
	}
	.news .imgnew dd{
		line-height:20px;
	}
	.news ul,.vidio ul{
		border-top:#fff 1px solid;
	}
	.more a{
		width:45px;
		height:20px;
		margin-right:10px;
		color:#333;
		display:block;
		float:right;
		font-size:12px;
		margin-top:15px;
		line-height:20px;
		background:url(images/dot_2.png) 27px 2px no-repeat;
	}
	.more a:hover{
		color:#a97608;
	}
	.date{
		height:66px;
		line-height:46px;
		padding-left:18px;
		padding-right:18px;
		color:#fffac7;
		font-size:18px;
	}
	.fl{
		float:left;
	}
	.fr{
		float:right;
	}
	.date .bfont{
		font-size:30px;
		color:#ffae00;
		padding:0 10px;
	}
	.clear{
		clear:both;
	}
	.study,.files{
		border-top:1px #d3d3d3 solid;
	}
</style>
	<script src="../js/jquery-1.7.1.js"></script>
	<script>
	var videos5 = {
		"name":"2012年11月8日十八大开幕式上胡锦涛做重要报告",
		"result":'<embed src="gddflvplayer.swf" flashvars="?&autoplay=false&sound=70&buffer=2&splashscreen=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F2%2Ejpg&vdo=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F2%2Eflv" width="445" height="334" allowFullScreen="true" quality="best" wmode="transparent" allowScriptAccess="always"  pluginspage="http://www.macromedia.com/go/getflashplayer"  type="application/x-shockwave-flash"></embed>'		
	};
	var videos6 = {
		"name":"2012年11月7日中国共产党十八大举行首场新闻发布会",
		"result":'<embed src="gddflvplayer.swf" flashvars="?&autoplay=false&sound=70&buffer=2&splashscreen=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F3%2Ejpg&vdo=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F3%2Eflv" width="445" height="334" allowFullScreen="true" quality="best" wmode="transparent" allowScriptAccess="always"  pluginspage="http://www.macromedia.com/go/getflashplayer"  type="application/x-shockwave-flash"></embed>'	
	};
	var videos4 = {
		"name":"11月10日下午在人民大会堂举行党的十八大主席团第二次会议",
		"result":'<embed src="gddflvplayer.swf" flashvars="?&autoplay=false&sound=70&buffer=2&splashscreen=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F1%2Ejpg&vdo=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F1%2Eflv" width="445" height="334" allowFullScreen="true" quality="best" wmode="transparent" allowScriptAccess="always"  pluginspage="http://www.macromedia.com/go/getflashplayer"  type="application/x-shockwave-flash"></embed>'	
	};
	var videos3 = {
		"name":"2012年11月13日 党的十八大主席团举行第三次会议",
		"result":'<embed src="gddflvplayer.swf" flashvars="?&autoplay=false&sound=70&buffer=2&splashscreen=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F4%2Ejpg&vdo=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F4%2Eflv" width="445" height="334" allowFullScreen="true" quality="best" wmode="transparent" allowScriptAccess="always"  pluginspage="http://www.macromedia.com/go/getflashplayer"  type="application/x-shockwave-flash"></embed>'	
	};
	var videos2 = {
		"name":"2012年11月14日-中国共产党十八大胜利闭幕",
		"result":'<embed src="gddflvplayer.swf" flashvars="?&autoplay=false&sound=70&buffer=2&splashscreen=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F5%2Ejpg&vdo=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F5%2Eflv" width="445" height="334" allowFullScreen="true" quality="best" wmode="transparent" allowScriptAccess="always"  pluginspage="http://www.macromedia.com/go/getflashplayer"  type="application/x-shockwave-flash"></embed>'	
	};
	var videos1 = {
		"name":"2012年11月15日 新一届中央政治局常委与中外记者见面，习近平发表讲话",
		"result":'<embed src="gddflvplayer.swf" flashvars="?&autoplay=false&sound=70&buffer=2&splashscreen=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F6%2Ejpg&vdo=http%3A%2F%2F10%2E1%2E14%2E20%3A8088%2Fportal%2Fshibada%2F6%2Eflv" width="445" height="334" allowFullScreen="true" quality="best" wmode="transparent" allowScriptAccess="always"  pluginspage="http://www.macromedia.com/go/getflashplayer"  type="application/x-shockwave-flash"></embed>'	
	};
	var arr = new Array();
	arr.push(videos1);	
	arr.push(videos2);	
	arr.push(videos3);	
	arr.push(videos4);	
	arr.push(videos5);	
	arr.push(videos6);	
		$(function(){
			
function getPicNews(sj_id){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbPicNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"pic_needed" : "1",
			"size":"1"
		},
		dataType:'json',
		cache : false,
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){			
			if(obj){
					for(var i=0;i<obj.length;i++){
						if(i==0){
							var m = "<a target='_blank' title='"+obj[i].memo+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"+
							"<dt>"+obj[i].title+"</dt>"+"<dd>"+obj[i].memo+"</dd>"+"</a>";
							var i = "<a target='_blank' href'=http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"+
							"<img src='http://10.1.44.18/stfb"+obj[i].picUrl+"' /></a>";
							$("#hydtDl").html(m);
							$("#hydtImg").html(i);
							break;
						}	
					}
				
			}
		}	  
	});	
}


function getNewss(sj_id,flag){
	$.ajax({
		type: 'POST',
		url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
		data:{
			"sj_id" : sj_id,
			"size":"4",
			"isContent":"1"
		},
		dataType:'json',
		cache : false,
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){			
			if(obj){
				var u = "";
				if(flag){
					for(var i=0;i<obj.length;i++){
							u += " <li><a target='_blank' title='"+obj[i].content+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"+
							"<b class='fl'>"+obj[i].title+"</b>"+
							"<span style='height:22px;font-size:11px;line-height:22px;color:#444;' class='fr'>"+obj[i].createTime.substring(5)+"</span>"+
							"<div class='clear'></div>"+
							"<span>"+obj[i].content+"</span>"+
							"</a></li>";
					}
					$("#hydtUl").html(u);
					if(obj.length==0){
						$("#hydtUl").html("&nbsp;&nbsp;无相关信息。");
					}
					var h = $(".news").height()-$(".imgnew").height()+$(".date").height()+334;
					//alert(h);
					$(".files").css("top",(h+15)+"px");
				}else{
					for(var i=0;i<obj.length;i++){
							u += " <li><a target='_blank' title='"+obj[i].content+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"+
							"<b class='fl'>"+obj[i].title+"</b>"+
							"<span style='height:22px;font-size:11px;line-height:22px;color:#444;' class='fr'>"+obj[i].createTime.substring(5)+"</span>"+
							"<div class='clear'></div>"+
							"<span>"+obj[i].content+"</span>"+
							"</a></li>";
					}
					$("#jingshen").html(u);
					if(obj.length==0){
						$("#jingshen").html("&nbsp;&nbsp;无相关信息。");
					}
				}
				
			}
		}	  
	});	
}

//cms
function getNews(sj_id){
			
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id,
					"size":"8"
				},
				dataType:'json',
				cache : false,
				error:function(){alert('系统连接失败，请稍后再试！')},
				success: function(obj){			
					var newsLi = "";
					var newsP = "javascript:void(0)";
					if(obj){
						var u ="";
						for(var i=0;i<obj.length;i++){
							u += " <li><a target='_blank' title='"+obj[i].copyTitle+"' href='"+"http://10.1.44.18/stfb"+obj[i].url+".htm"+"'>"+
							obj[i].title+
							"</a></li>";
						}
						$("#ziliao").html(u);
					}
					var h = parseInt($(".vidio").css("top").replace("px",""));
					//$(".files").css("top",($(".vidio").height()+h+15)+"px");
					$(".study").css("top",($(".vidio").height()+h+30)+"px");	
					
			
				}	  
			});	
			
		}
			var temp = "";
			for(var i=0;i<arr.length;i++){
				
				temp += "<li><a href='javascript:void(0);' title='"+arr[i].name+"' class='va'>"+arr[i].name+
				"</a></li>"
			}
			$("#vu").html(temp);
			$(".va").each(function(i,n){
				$(n).live("click",function(){
					$("#vd").html(arr[i].result);
				})
			})
			$(".va").eq(0).click();
			getNews(1874);
			getPicNews(1855);
			getNewss(1855,true);
			getNewss(1856,false);
			
			var d = new Date();
			var strYear = d.getFullYear();    
		    var strDay = d.getDate();    
		    var strMonth = d.getMonth()+1;  
		    var strWeek = d.getDay();  
		    /*if(strMonth<10)    
		    {    
		        strMonth="0"+strMonth;    
		    }    
		    if(strDay<10)    
		    {    
		    	strDay="0"+strDay;    
		    }  */
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
		    $("#today").html(datastr);
			//$("#days").html(Math.floor(span));
			
		})
	</script>
</head>

<body>
	<div class="body">
        <div class="main">
        	<div class="date">
            	<span id="today" class="fl">今天是 2012年11月09日</span>
               <!--<span class="fr">十八大召开第<span id="days" class="bfont">2</span>天</span>  --> 
              
            	<div class="clear"></div>
            </div>
        	
        	<div class="news">
            	<div class="con">
                    <h1><span>十八大会议动态 </span><div class="more"><a href="/portal/infoSearch/findStfbNewsByPage.action?sj_id=1855" target="_blank">更多</a></div></h1>
                    <div class="imgnew">
                        <dl id="hydtDl"></dl>
                    	<div id="hydtImg">
                        	
                        </div>
                    </div>
                    <ul id="hydtUl">
                        </ul>
                </div>
            </div>
            <div class="files">
            	<div class="con">
                    <h1><span>十八大资料</span><div class="more"><a href="/portal/infoSearch/findStfbNewsByPage.action?sj_id=1874" target="_blank">更多</a></div></h1>
                    <ul id="ziliao">
                    </ul>
                </div>
            </div>
            <div class="vidio">
            	<div class="con">
                	<div id="vd" class="v1">
                    </div>
                    <ul id="vu">
                    	
                    </ul>
                </div>
            </div>
        	<div class="study">
            	<div class="con">
                    <h1><span>学习贯彻十八大精神</span><div class="more"><a href="/portal/infoSearch/findStfbNewsByPage.action?sj_id=1856" target="_blank">更多</a></div></h1>
                    <ul id="jingshen">
                       </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

