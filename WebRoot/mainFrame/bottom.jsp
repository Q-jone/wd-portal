<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.wonders.module.common.ExecuteSql"%>
<%@page import="java.sql.ResultSet"%>
<%
	String path = request.getContextPath();
%>
<%
	/**ExecuteSql dealsql= new ExecuteSql("dataSource");
	String tuserNum="0";
	String csuserNum="0";
	String onlineUser="0";
	String activeUser="0";		
	String sql="";
	ResultSet rs=null;
	try{
		sql="select count(*) as tuserNum from t_user";
		rs=dealsql.ExecuteDemandSql(sql);
		if(rs.next()){
			tuserNum=rs.getString("tuserNum");
		}
		
		sql="select count(*) as csuserNum from cs_user";
		rs=dealsql.ExecuteDemandSql(sql);
		if(rs.next()){
			csuserNum=rs.getString("csuserNum");
		}
		
		sql="select count(distinct userid) as onlineUser from ca_visit_log where appname='AUTO_LOGIN.shmetrointra.com' and visit_time>sysdate-1/24";
		rs=dealsql.ExecuteDemandSql(sql);
		if(rs.next()){
			onlineUser=rs.getString("onlineUser");
		}
		
		sql="select count(distinct userid) as activeUser from ca_visit_log where appname='AUTO_LOGIN.shmetrointra.com' and visit_time>sysdate-14";
		rs=dealsql.ExecuteDemandSql(sql);
		if(rs.next()){
			activeUser=rs.getString("activeUser");
		}
				
		rs.close();
		dealsql.close();
	}catch(Exception e){
	}**/
%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>Untitled Document</title>
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       <style>
			.detail {display:none;position:absolute;width:380px;height:auto;background:#FBFACC;}
		</style>
        <script src="../js/html5.js"></script>
        <script src="/portal/js/jquery-1.7.1.js"></script>
        <script type="text/javascript">
		var sobj;
		var first = true;
		var intHand = null;
		var objLength = 0;
		var k = 0;
    		
			(function($){
				/**$(document).on("mouseenter","a",function(){
					var offset = $(this).offset();
					var i = $(this).attr("ct");
					$(".detail").html(i);
	                $(".detail").css("left", offset.left+500);
	                $(".detail").css("top", offset.top+10);
				    $(".detail").show();
				}).on("mouseleave","a",function(){
					$(".detail").html("");
				    $(".detail").hide();
				    $(".detail").css("left", "");
	               	$(".detail").css("top", "");
				});*/
				
				
				$.getSN = function(options){
					if (typeof(options) == "undefined" || options== null){
						return "";
					//}else if(options.length > 75){
					//	return options.substr(0,75) + "... [请点击]";
					}else{
						return options +" [请点击]";
					}
				}
				
					$.getSN2 = function(options){
					if (typeof(options) == "undefined" || options== null){
						return "";
					//}else if(options.length > 75){
					//	return options.substr(0,75) + "... [请点击]";
					}else{
						return options;
					}
				}
			
				$.fn.myScroll = function(options){
				//默认配置
				var defaults = {
					speed:40,  //滚动速度,值越大速度越慢
					rowHeight:24 //每行的高度
				};
				
				var opts = $.extend({}, defaults, options),intId = [];
				
				function marquee(obj, step){
					obj.find("ul").animate({
						marginTop: '-='+opts["rowHeight"]
					},1000,function(){
							var s = Math.abs(parseInt($(this).css("margin-top")));
							if(s >= step){
								
								$(this).find("li").slice(0, 1).remove();
								$(this).css("margin-top", 0);
							}
							k++;
							//console.log(k);
							
							if(k==objLength-1){
								k = 0;
								showMarquee();
							}
						});
					}
					
					this.each(function(i){
						var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
						
						intId[i] = setInterval(function(){
							if(_this.find("ul").height()<=_this.height()){
								clearInterval(intId[i]);
							}else{
								marquee(_this, sh);
							}
						}, speed);
			
						_this.hover(function(){
							clearInterval(intId[i]);
						},function(){
							intId[i] = setInterval(function(){
								if(_this.find("ul").height()<=_this.height()){
									clearInterval(intId[i]);
								}else{
									marquee(_this, sh);
								}
							}, speed);
						});
					
					});
			
				}
			
			})(jQuery);
			
        	$(function(){
        		//intHand = setInterval("getMarquee()",11*60*1000);
        		getMarquee();
        		
        	})
        	
        	function getMarquee(){
       			$.ajax({
       				url: "<%=path%>/marquee/getMarquee.action?random="+Math.random(),
       				type: 'post', 
       				dataType: 'json', 
       				error:function(){
       					//alert("系统连接失败，请稍后再试！");
       				},
       				success: function(obj){			
       	              if(obj!=null&&obj.list!=null){
       	            	sobj = obj;
       	           	  	objLength = obj.list.length;
       	              }
       	              if(first){
         	            	showMarquee();
       	              }
       	           	setTimeout("getMarquee()",11*60*1000);
       				}
       			});	
        	}
        	
        	function showMarquee(){
        					//alert(obj);
        					var obj = sobj;
        					if(obj !=null && obj.list != null && obj.list.length > 0){
        						var ls = "";
        						var msg = obj.list;
        						for(var i in msg){
        							ls += "<li>";
        							if(msg[i].url != null && (msg[i].url).length >0){
        								ls += "<a  href='"+msg[i].url+"' target='_blank'>";
        								if(msg[i].type !=null && msg[i].type.length > 0){
        									ls += "<font class='"+msg[i].app+"'>["+msg[i].type+"] </font>";
        								}
        								ls += $.getSN(msg[i].title)+"</a>"
        							}else{
        								if(msg[i].type !=null && msg[i].type.length > 0){
        									ls += "<font class='"+msg[i].app+"'>["+msg[i].type+"] </font>";
        								}
        								ls += $.getSN2(msg[i].title);
        							}
        							
        							
        							ls+="</li>";
        							
        							if(msg[i].content !=null && msg[i].content.length > 0){
        								ls += "<li class='priority"+msg[i].priority+
            							"' >";
            							if(msg[i].url != null && (msg[i].url).length >0){
            								ls += "<a class='priority"+msg[i].priority+ 
            								"' href='"+msg[i].url+"' target='_blank'>"+
            								""+$.getSN(msg[i].content)+"</a>"
            							}else{
            								ls += $.getSN(msg[i].content);
            							}
            							
            							
            							ls+="</li>";
        							}
        						}
        						//alert(ls);
        						
        						if(first){
        							$("#cul").html(ls);
        							first = false;
	        						$("#copyright").myScroll({
	        							speed:obj.speed,
	        							rowHeight:30
	        						});
        						}else{
        							$("#cul").append(ls);
            					}
        					}
        				}

        	
        </script>
        
        <style>
        	a{
        		color:black;
        		overflow:hidden;
				text-overflow:ellipsis;
				-o-text-overflow:ellipsis;
				white-space:nowrap;    		
        	}
        
        	font{
        		display:inline;
        	}
        	
        	#copyright ul li{
        		font-weight:normal;
        		font-size:12px;
        		line-height:30px;
        		
        	}
        	
        	.ldjh,.hotnews{
        		color:#53868B;
        	}
        	.mail,.todo{
        		color:#FF1493;
        	}
        	.help{
        		
        		color:#778899;
        	}
        	.yysb,.yyzb{
        		color:blue;
        	}
        	
        	.build,.train{
        		color:blue;
        	}
        </style>
</head>

<body>
<div class='detail' style='z-index:9999;'></div>
	<footer id="foot">
        <!-- <div id="copyright" class="t_c"> Copyright © 2008-2013  上海申通地铁集团有限公司 版权所有 All Rights Reserved</div>  -->
    	 <div id="copyright" class="fl l_bor" style="width:950px;padding-right:30px;padding-left:30px;height:30px;overflow:hidden;">
    	 	<ul id="cul">
    	 	</ul>
    	 </div>
        <div style="margin-right: 20px;padding-left:60px;background-position-x:30px;" class="fr l_bor clearfix">服务热线：<b>55310018</b></div>

    </footer>
</body>
</html>
 <%
            	String portal_caClient = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/caClient.jsp?action=setOldSession";
							portal_caClient = portal_caClient.replace("&","%26").replace("?","%3F");
            %> 
 <!--jeus 44.18(16/15)-->    
             <iframe style="position:absolute;top:1px;right:1px;" src="http://10.1.48.20/ca/login.jsp?appName=OLD_PORTAL&returnUrl=<%=portal_caClient%>"  frameborder="no" border="0" framespacing="0" name="oldFrame" scrolling="No" height="2px" width="2px" noresize="noresize" id="oldFrame" title="oldFrame"></iframe>

 						<iframe style="position:absolute;top:1px;right:9px;" src="http://10.1.48.20/ca/clearAllAppSession.jsp"  frameborder="no" border="0" framespacing="0" name="clearSession" scrolling="No" height="0px" width="0px" noresize="noresize" id="clearSession" title="clearSession"></iframe>             
        
       <!--  <iframe style="position:absolute;top:1px;right:1px;" src="http://10.1.48.20/ca/login.jsp?appName=OLD_PORTAL.wl64&returnUrl=<%=portal_caClient%>2"  frameborder="no" border="0" framespacing="0" name="oldFrame" scrolling="No" height="2px" width="2px" noresize="noresize" id="oldFrame" title="oldFrame"></iframe>-->
