<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>上海地铁行业思想政治工作研究会 </title>
<script src="images/html5.js"></script>

<script src="js/jquery-1.9.1.js"></script>
<!-- <script src="../../js/jquery-1.7.1.js"></script> -->

<script src="js/syh_ajax.js"></script>
<script src="js/jCarouselLite.js"></script>
<style type="text/css">
	body{
		background:#dce7f0 url(images/bg.jpg) center top no-repeat;
		margin:0;
		padding:0;
  		font:12px/1.5 'Helvetica Neue', 'Microsoft YaHei', Arial, 'Liberation Sans', FreeSans, sans-serif;
}
	ul, li{
	  list-style: none;
	}
	
	li {
	  margin:0;
	}
	h1,
	h2,
	h3,
	h4,
	h5,
	h6,
	img,
	li,
	menu,
	p,
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
	a{
	  border: 0;
	  margin: 0;
	  padding: 0;
	  text-decoration:none;
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
	
	.wrapfix::after {
		height: 0px; clear: both; display: block; visibility: hidden; content: ".";
	}
	.wrapfix {
		display: inline-table;
	}
	* html .wrapfix {
		height: 0%;
	}
	.wrapfix {
		display: block;
	}
	
	
	.fl{
		float:left;
	}
	.fr{
		float:right;
	}
	.t_c{
		text-align:center;
	}
	.t_r{
		text-align:right;
	}
	
	
	.body{
		width:1002px;
		margin:0 auto;
		background:#fff url(images/body_bg.png) left top repeat-x;
	}
	header{
		padding-top:114px;
		height:40px;
		background:url(images/top_bg.jpg) left top no-repeat;
	}
	header ul{
		padding-right:5px;
		background-position:right -40px;
		height:40px;
	}
	header li{
		float:left;
		display:inline;
		_display:inline-block;
		line-height:36px;
	}
	header a, header li.left, header ul, header .bar{
		background-image:url(images/menu.png);
	}
	header li.left, header ul, header .bar{
		background-repeat:no-repeat;
	}
	header .bar{
		background-position:left -120px;
		padding-left:2px;
	}
	header li a{
		background-repeat:repeat-x;
		background-position:left -80px;
		padding:0 23.43px;
		font-size:14px;
		font-weight:bold;
		color:#fff;
		height:40px;
		display:block;
	}
	header li a span.home{
		display:block;
		width:22px;
		background:url(images/home.png) left top no-repeat;
		text-indent:-9999px;
		*padding:0 4px;
	}
	header li:hover a span.home, header li.selected a span.home{
		background-position:left -40px;
	}
	header li.left{
		padding-left:5px;
		background-position:left top;
	}
	header li:hover a, header li.selected a{
		background-position:left -160px;
		color:#c7ff1d;
		text-shadow:#0a4d7c 0 2px 2px;
		display:block;
	}
	footer{
		background:#dedede;
		border-top:#0e90e2 3px solid;
		border-left:#0e90e2 1px solid;
		border-right:#0e90e2 1px solid;
		text-align:center;
		color:#1c4762;
		line-height:30px;
		height:32px;
	}
	.main{
		padding:10px;
	}
	.main .left{
		width:730px;
		margin-right:10px;
	}
	.main .right{
		width:240px;
	}
	.c_1, .edu h3, .edu .list li a{
		color:#29619b;
	}
	.c_2{
		color:#749dc9;
		font-size:11px;
	}
	.c_3{
		color:#076abd;
	}
	.r_bor{
		border-right:#c1c1c1 1px solid;
		padding-right:12px;
		margin-right:12px;
	}
	.news hgroup{
		background:url(images/news_bg.png) left top repeat-x;
		height:31px;
		padding:12px 10px 0 10px;
		line-height:16px;
	}
	.more_1, .more_2, .more_3, .news .list li, .training .list li, .culture .list, .qy .list li, .edu .list li a, .focus nav li:hover i, .qy h4{
		background-image:url(images/icon_4.png);
		background-repeat:no-repeat;
	}
	.more_1{
		background-position:right 2px;
		padding-right:12px;
	}
	.more_2{
		background-position:left -687px;
		padding-left:12px;
	}
	.more_3{
		background-position:right -602px;
		padding-right:12px;
	}
	a.more_1, a.more_2, a.more_3{
		color:#0f4b93;
	}
	a.more_1:hover, a.more_2:hover, a.more_3:hover, .gqt a.type:hover{
		color:#009dff;
	}

	.mb10{
		margin-bottom:10px;
	}
	.focus{
		width:347px;
		height:242px;
		margin-right:10px;
	}
	.focus .imgs{
		width:343px;
		height:195px;
		padding:1px;
		background:#eeffff;
		border:#e1e3ef 1px solid;
		margin-bottom:1px;
		position:relative;
	}
	.focus .tit{
		position:absolute;
		height:43px;
		padding:0 8px;
		top:152px;
		left:1px;
		width:327px;
		background:#000;
		filter:alpha(opacity=30);
		-moz-opacity:0.3;
		opacity:0.3;
		
	}
	.focus .tit a{
		width:90%;
		overflow:hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		-o-text-overflow: ellipsis;
		position:relative;
		color:#fff;
		display:block;
	}
	.focus .tit b{
		display:block;
		line-height:24px;
	}
	.focus .tit span{
		display:block;
	}
	.focus .imgs img{
		width:343px;
		height:195px;
	}
	.focus nav{
		height:42px;
		padding:1px 16px;
		background:#c9d2d9;
	}
	.focus nav li{
		background-color:#fff;
		border:#8e9ea9 1px solid;
		padding:1px;
		width:66px;
		height:37px;
		float:left;
		display:inline;
		_display:inline-block;
		margin-right:3px;
		position:relative;
	}
	.focus nav li:hover, .focus nav li:focus{
		border:#7cbce7 1px solid;
	}
	.focus nav li i{
		display:none;
	}
	.focus nav li:hover i, .focus nav li:focus i{
		background-position:-5px -795px;
		position:absolute;
		width:10px;
		height:5px;
		top:-5px;
		left:10px;
		display:block;
	}
	.focus nav li img{
		width:66px;
		height:37px;
	}
	.news .txt, .training{
		width:372px;
	}
	h3, h4{
		*font-weight:normal;
	}
	.news .txt a h4{
		color:#c9730d;
	}
	.news .txt a p{
		color:#8f8f8f;
		height:35px;
		word-wrap: break-word; 
		word-break: normal; 
				
	}
	.news .txt a{
		overflow:hidden;
		text-overflow: ellipsis;
		overflow: hidden;
		-o-text-overflow: ellipsis;
		position:relative;
		padding-left:12px;
	}
	.news .txt .list li{
		background-position:left -80px;
	}
	.list li a{
		overflow:hidden;
		text-overflow: ellipsis;
		overflow: hidden;
		-o-text-overflow: ellipsis;
		line-height:180%;
		white-space: nowrap;
	}
	.news .list li a, .gqt a.con, .training .list li a, .qy .list li a, .culture .list li a{
		color:#383838;
	}
	.news .list li a:hover, .gqt a.con:hover, .training .list li a:hover, .qy .list li a:hover, .culture .list li a:hover{
		color:#ccc;
	}
	.news .list li a, .training .list li a{
		font-size:14px;
	}
	.news .list li a, .gqt a.con, .culture .list li a{
		width:85%;
	}
	.date{
		color:#8f8f8f;
		font-size:11px;
		display:block;
	}
	
	.culture, .gqt{
		width:345px;
		margin-bottom:10px;
		margin-right:10px;
		border:#d5dde4 1px solid;
		background:url(images/culture_bg.png) left top repeat-x;
	}
	.culture hgroup, .gqt hgroup{
		background-image:url(images/culture_hg.png);
		background-repeat:no-repeat;
		line-height:20px;
		padding-top:10px;
	}
	.culture hgroup h3,	.culture hgroup a, .gqt hgroup h3, .gqt hgroup a{
		margin-left:5px;
		margin-right:5px;
	}
	.culture hgroup{
		background-position:left -418px;
	}
	.gqt hgroup{
		background-position:left top;
	}
	.gqt a.type{
		font-weight:bold;
		color:#007aff;
	}
	.gqt .list li{
		color:#cad7df;
		border-bottom:#c1c1c1 1px dashed;
		margin:0 8px;
		padding:0 3px;
	}
	.tabs_1{
		border-bottom:#c1c1c1 1px solid;
		height:21px;
		padding-left:8px;
	}
	.tabs_1 li{
		margin-right:1px;
		background-color:#90bce1;
		border-left:#90bce1 1px solid;
		border-top:#90bce1 1px solid;
		border-right:#90bce1 1px solid;
		border-top-left-radius:2px;
		border-top-right-radius:2px;
		height:21px;
		float:left;
	}
	.tabs_1 li:hover, .tabs_1 li.selected{
		border-left:#d5dde4 1px solid;
		border-top:#d5dde4 1px solid;
		border-right:#d5dde4 1px solid;
		border-bottom:#fff 1px solid;
		background: #fff url(images/tabs_!_bg.png) left 1px repeat-x;
	}
	.tabs_1 li a{
		color:#fff;
	}
	.tabs_1 li:hover a, .tabs_1 li.selected a{
		color:#076abd;
	}
	.culture .list{
		margin:0 8px;
		background-position:left -477px;
		padding-left:18px;
	}
	.culture .list a{
		line-height:24px;
		display:block;
	}
	.training hgroup{
		border-bottom:#bad0e7 1px solid;
		line-height:16px;
		padding:0 8px;
	}
	.p8{
		padding:8px;
	}
	.training .type{
		color:#fe7e00;
		font-weight:bold;
	}
	.training dt{
		background:#fff;
		border:#9a9a9a 1px solid;
		padding:1px;
		margin-right:10px;
		width:72px;
		height:72px;
	}
	.training dt img{
		width:72px;
		height:72px;
	}
	.training .list li{
		background-position:left -424px;
	}
	.training .list li a{
		display:block;
		width:249px;
		padding-left:12px;
	}
	.people{
		border:#cfd8e2 1px solid;
		background:#eef7fd;
		padding-right:4px;
	}
	.people dd{
		background:#fff;
		padding:8px;
	}
	.people dt{
		padding:8px;
		width:18px;
	}
	.people li{
		width:74px;
		height:74px;
		padding:4px;
		border:#b4b4b4 1px solid;
		margin-right:8px;
		float:left;
	}
	.people li img{
		width:74px;
		height:74px;
	}
	.qy, .piclink{
		background:#eef6fc url(images/qy_bg.png) left top no-repeat;
		padding:6px;
	}
	.qy .con{
		border:#aecee5 1px solid;
		background:#fff;
	}
	.qy h4{
		line-height:21px;
		padding-left:20px;
		background-color:#c6dae8;
		color:#1f5486;
		background-position:3px -166px;
		font-weight:normal;
	}
	.qy .list li{
		padding-left:20px;
		padding-right:8px;
		background-position:8px -252px;
	}
	.qy .list li a{
		display:block;
		width:95%;
	}
	.piclink img{
		border:#aecee5 1px solid;
		width:226px;
		height:73px;
	}
	.edu{
		border:#d7dfe5 1px solid;
		background:#fff url(images/edu_bg.png) left top no-repeat;
	}
	.edu h3{		
		padding-left:22px;
		height:24px;
		padding-top:17px;
	}
	.edu .list li{
		background:url(images/edu_li.png) center top no-repeat;
		line-height:26px;
		height:26px;
		padding:0 8px;
		margin-bottom:1px;
	}
	.edu .list li a{
		display:block;
		background-position: left -337px;
		width:95%;
		padding-left:12px;		
		font-size:14px;
	}
</style>

<script type="text/javascript">
/*
	function selected(target){
		$("ul li").each(function(){
			$(this).removeClass("selected");
		});
		$(target).parent().addClass("selected");
	}*/
	
	$(function(){
		//显示栏目，从左到右
		showParty("2094","","10");								//1.显示党组织建设图片
		showCommonList("2095","","#leaderEdu","10");			//2.显示干部培育
		showGroup("2075","");									//3.显示共青团工作
		showCommonList("2096","","#companyManage","8");			//4.企业管理
		showCommonList("2076","","#companyCulture","8");		//5.企业文化
		showPersonEmotion("2077","","7");						//6.人物情怀
		showCommonList("2078","","#workerMorals","5");			//7.职工思想道德
		showCommonList("2097","","#workerEdu","5");				//8.职工思想教育
	});

	
</script>
</head>

<body>
	<div class="body">
    	<!--header-->
        <header>
        	<menu>
            	<ul>
                	<li class="left selected"><a href="#"><span class="home">123</span></a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2094/index.htm"  target="_blank">党组织建设</a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2095/index.htm"  target="_blank">干部培育</a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2075/index.htm"  target="_blank">共青团工作</a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2096/index.htm"  target="_blank">企业管理</a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2076/index.htm"  target="_blank">企业文化</a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2077/index.htm"  target="_blank">人物情怀</a></li>
                	<li class="bar"><a href="http://10.1.44.18/stfb/node2074/node2078/index.htm"  target="_blank">职工思想道德</a></li>
                	<li class="right bar"><a href="http://10.1.44.18/stfb/node2074/node2097/index.htm"  target="_blank">职工思想教育</a></li>
                </ul>
            </menu>
        </header>
        <!--header end-->
        <!--main-->
        <div class="main clearfix">
        	<!--lefe-->
        	<div class="left fl">
            	<!--news-->
            	<div class="news mb10">
                	<hgroup class="clearfix">
                    	<h3 class="fl r_bor c_1">党组织建设</h3>
                        <span class="fl c_2">News</span>
                        <a href="http://10.1.44.18/stfb/node2074/node2094/index.htm" target="_blank" class="more_1 fr">更多</a>
                    </hgroup>
                    <div class="clearfix">
                    	<div class="focus fl" >
                        	<div class="imgs" >
                                <ul>
                                    <li><a href="javascript:void(0);" target="_blank"><img src="" id="headImage" ></a></li>
                                </ul>
                                <!-- 
                                <ul class="tit" id="headImageInfo">
                                    <li><a href="javascript:void(0);" target="_blank"><b></b><span></span></a></li>
                                </ul>
                                -->
                            </div>
                            <nav>
                            	<ul id="headImagesList">
                                	<!-- <li><a href="#"><img src="images/bg.jpg"><i></i></a></li> -->
                                </ul>
                            </nav>
                        </div>
                        <div class="fl txt">
                        	<div class="headline mb10" id="partyHeadNews">
                            	<a href="javascript:void(0);" target="_blank" style="font-size: 15px; padding:0;">
                                    <h4></h4>
                                    <p></p>
                                </a>
                            </div>
                            <ul class="list" id="partyList">
                            	<!-- <li class="clearfix"><a href="#" class="fl">6号线港城路站携手滨江森林公园打造“园.6号线港城路站携手滨江森林公园打造“园.</a><span class="fr date">05-14</span></li> -->
                            </ul>
                        </div>
                    </div>
                </div>
                <!--news end-->
                <div class="clearfix">
                	<!--culture\gqt-->
                	<div class="fl">
                    	<div class="gqt">
                        	<hgroup class="clearfix mb10">
                            	<h3 class="c_3 fl">共青团工作</h3>
                                <a href="http://10.1.44.18/stfb/node2074/node2075/index.htm" target="_blank" class="more_2 fr">更多</a>
                            </hgroup>
                            <ul class="list mb10" id="groupList" style="font-size: 14px;">
                            	<!-- 
                            	<li class="clearfix"><span class="fl">[<a class="type" href="#">123</a>]</span><a class="fl con" href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li>
                            	 -->
                            </ul>
                        </div>
                        <div class="culture">
                        	<hgroup class="clearfix mb10">
                            	<h3 class="c_3 fl">企业文化</h3>
                                <a href="http://10.1.44.18/stfb/node2074/node2076/index.htm" target="_blank" class="more_2 fr">更多</a>
                                <div class="clear mb10"></div>
                                <!-- 
                                <ul class="tabs_1" >
                                	<li class="selected"><a href="#">123</a></li>
                                	<li><a href="#">123</a></li>
                                	<li><a href="#">123</a></li>
                                	<li><a href="#">123</a></li>
                                </ul>
                                 -->
                            </hgroup>
                            <ul class="list" id="companyCulture" style="font-size: 14px;">
                            	<!-- <li class="clearfix"><a href="#" class="fl">6号线港城路站携手滨江森林公园打造“园.6号线港城路站携手滨江森林公园打造“园.</a><span class="fr date">05-14</span></li> -->
                            </ul>
                        </div>
                    </div>
                	<!--culture\gqt end-->
                    <!--training-->
                    <div class="training fl">
                    	<hgroup class="clearfix">
                            <h3 class="fl r_bor c_1">干部培育</h3>
                            <span class="fl c_2">Training</span>
                            <a href="http://10.1.44.18/stfb/node2074/node2095/index.htm" target="_blank" class="more_3 fr">更多</a>
                    	</hgroup>
                        <div class="p8">
                        	<div class="fl type"></div>
                            <div class="clear"></div>
                            <dl class="clearfix">
                            	<!-- <dt class="fl"><img src="images/bg.jpg"></dt> -->
                                <dd class="fl">
                                	<ul class="list" id="leaderEdu">
                                        <!-- <li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li> -->
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <!-- 
                        <div class="p8">
                        	<div class="fl type">123</div>
                            <a href="#" class="more_3 fr">123</a>
                            <div class="clear"></div>
                            <dl class="clearfix">
                            	<dt class="fl"><img src="images/bg.jpg"></dt>
                                <dd class="fl">
                                	<ul class="list">
                                        <li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        <div class="p8">
                        	<div class="fl type">123</div>
                            <a href="#" class="more_3 fr">123</a>
                            <div class="clear"></div>
                            <dl class="clearfix">
                            	<dt class="fl"><img src="images/bg.jpg"></dt>
                                <dd class="fl">
                                	<ul class="list">
                                        <li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li>
                                    </ul>
                                </dd>
                            </dl>
                        </div>
                        -->
                    </div>
                    <!--training end-->
                </div>
                <div class="people clearfix">
                	<dl>
                        <dt class="fl"><a href="http://10.1.44.18/stfb/node2074/node2077/index.htm" target="_blank"><h3 class="c_1">人<br>物<br>情<br>怀</h3></a></dt>
                        <dd class="fl">
                        	<div id="scrollDiv">                        	
                        	<ul class="clearfix" id="personEmotion">
                            </ul>
                            </div>
                        </dd>
                    </dl>
                </div>
            </div>
            <!--left end-->
            <!--right-->
            <div class="right fr">
            	<!--qygl-->
            	<!-- <div class="qy mb10">
                	<a href="http://10.1.44.18/stfb/node2074/node2096/index.htm" target="_blank"><h3 class="c_1 mb10">企业管理</h3></a>
                    <div class="con">
                    	<h4></h4>
                        <ul class="list" id="companyManage">
                        	<li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li> 
                        </ul>
                    	<!-- <h4>123</h4>
                        <ul class="list">
                        	<li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li>
                        </ul>
                    </div>
                </div> -->
                
                
                
                <div class="edu mb10">
                	<h3><a href="http://10.1.44.18/stfb/node2074/node2096/index.htm" target="_blank" style="color:#29619b;">企业管理</a></h3>
                    <ul class="list" id="companyManage" >
                    	<!-- <li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li> -->
                    </ul>
                </div>
                
                
                
                <!--qygl end-->
                <!--piclink-->
                <div class="piclink mb10"><img src="images/index_syh_63.jpg"></div>
                <!--piclink end-->
                <div class="edu mb10">
                	<h3><a href="http://10.1.44.18/stfb/node2074/node2078/index.htm" target="_blank" style="color:#29619B;">职工思想道德</a></h3>
                    <ul class="list" id="workerMorals">
                    	<!-- <li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li> -->
                    </ul>
                </div>
                <!--edu1-->
                <!--edu1 end-->
                <div class="edu mb10">
                	<h3><a href="http://10.1.44.18/stfb/node2074/node2097/index.htm" target="_blank" style="color:#29619B;">职工思想教育</a></h3>
                    <ul class="list" id="workerEdu">
                    	<!-- <li><a href="#">申通地铁集团工会荣获上海职工文化艺术展优申通地铁集团工会荣获上海职工文化艺术展优</a></li> -->
                    </ul>
                </div>
                <!--edu2-->
                <!--edu2 end-->
            </div>
            <!--right end-->
        </div>
        <!--main-->
        <!--footer-->
        <footer>Copyright © 2008 上海申通地铁集团有限公司 版权所有 All Rights Reserved.
        </footer>
        <!--footer end-->
    </div>
</body>
</html>
