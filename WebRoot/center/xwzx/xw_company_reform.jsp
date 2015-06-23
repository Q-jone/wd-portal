<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>新闻资讯首页</title>
<link rel="stylesheet" href="../../css/formalize.css" />

<link rel="stylesheet" href="../../css/page.css" />
<link rel="stylesheet" href="../../css/default/imgs.css" />
<link rel="stylesheet" href="../../css/reset.css" />

<!--
　
链接地址
集团首页
http://localhost:8080/portal/center/xwzx/xw_index_reform.jsp
第一运营公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1324&activeId=1335&parentId=1334
第二运营公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1325&activeId=1326&parentId=1336
第三运营公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1331&activeId=1340&parentId=1330
第四运营公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1343&activeId=1332&parentId=1342
维保中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1347&activeId=1355&parentId=1354
运管中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1357&activeId=1348&parentId=1356
建管中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1349&activeId=1359&parentId=1358
技术中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1375&activeId=1362&parentId=1374
培训中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1377&activeId=1378&parentId=1376
信息中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1363&activeId=1380&parentId=1379
资金中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1381&activeId=1365&parentId=1364
资产中心
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1369&activeId=1370&parentId=1383
资产公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1371&activeId=1372&parentId=1384
隧道设计院
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1386&activeId=1373&parentId=1385
股份公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1389&activeId=1390&parentId=1395
黄浦江大桥建设公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1387&activeId=1388&parentId=1394
磁浮公司
http://localhost:8080/portal/center/xwzx/xw_company_reform.jsp?headId=1636&activeId=1654&parentId=1635
 


  -->

<%
String headId = request.getParameter("headId");
String activeId = request.getParameter("activeId");
String parentId = request.getParameter("parentId");
 %>

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
		<script src="js/xwzx_ajax.js"></script>
		<script type="text/javascript">
		var t ;
		var headHref;
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
		
		function changeColumn(obj,num){
		    $(obj).siblings("li").removeClass("selected");
			$(obj).addClass("selected");
			$(obj).parent().parent().parent().parent().next().children(".reportDiv").hide();
			$(".reportDiv:eq("+num+")").show();
			
			var name = $(obj).children("a").text();
			$(obj).parent().parent().parent().children("h5").html(name);
		}
		
		function getLatestMoreNews(sj_id){
			var names = new Array();
			var ids = new Array();
			var flag = 0;
			$.ajax({
				type: 'POST',
				url: '/portal/moreInfoSearch/findNameList.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					for(var i=0;i<obj.length;i++){
					    names[i] = obj[i].name;
					    ids[i] = obj[i].id;
					}
					
				}	  
			});	
						
			$.ajax({
				type: 'POST',
				url: '/portal/moreInfoSearch/findStfbMoreNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					var sb;	
					for(var i=0;i<names.length/5;i++){
						sb = "<div class=\"panel_3 mb10\"><header class=\"clearfix\"><div class=\"bg_3 clearfix\">"+
						"<h5 class=\"fl file\"></h5><div class=\"fr tabs_1\"><ul>";
						for(var j=0;j<5;j++){
						    if(names.length>j+5*i){
						        sb += "<li class=\"reportLi\" onclick=\"changeColumn(this,"+(j+5*i)+");\"><a href=\"javascript:void(0);\"><span>"+names[j+5*i]+"</span></a></li>";
						    }						    
						}
						sb += "</ul></div></div></header><div class=\"con\">";
						
						for(var j=0;j<5;j++){
						    if(names.length>j+5*i){
						        var p = 0;
						        sb += "<div class=\"reportDiv clearfix\"><ul class=\"columns clearfix mb10\">";
						        for(var k=0;k<obj.length;k++){
						            if(names[j+5*i]==obj[k].name){
						                flag = 1;
						                p = k;
						                break;
						            }
						        }
						        if(flag==1){
						            var m = 0;						            
						            for(var k=0;k<obj.length;k++){
							            if(names[j+5*i]==obj[k].name){
							                m = m+1;
							            }
							        }
							        for(var k=0;k<m;k++){
							            sb += "<li class='clearfix'><a target='_blank' title='"+obj[p+k].copyTitle+"'";
							            sb += " href='http://10.1.44.18/stfb"+obj[p+k].url+".htm'>"+obj[p+k].title+"</a><span>"+obj[p+k].createTime.substring(5)+"</span></li>";
							        }
						            
						        }else{
						             sb += "&nbsp;&nbsp;无相关信息。";
						        }
						        
						        sb += "</ul>";
						        if(flag==1){
						            sb += "<p class=\"fr\"><a target=\"_self\" class=\"more_3\" href=\"/portal/infoSearch/findStfbNewsByPage.action?sj_id="+ids[j+5*i]+"\">更多</a></p>"
						        }
						        sb += "</div>";
						        flag = 0;
						    }						    
						}
						
						sb += "</div></div>";
						$("#newsHead").append(sb);
						
					    $(".reportLi:eq("+i*5+")").click();
					}
				}	  
			});	
			return names.length;
		}
		
		function getGroupHeadNews(sj_id){
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbHeadNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					if(obj){
						for(var i=0;i<obj.length;i++){
							//$("#newsGroup hgroup a").attr("href","/portal/infoSearch/findStfbNewsByPage.action?sj_id="+obj[i].SJ_ID);
							//$("#newsHref").attr("href","http://10.1.44.18/stfb"+obj[i].url+".htm");
							//alert($("#headNews td h5").parent().parent().attr("href"));
							headHref = obj[i].url;
							$("#headNews td h5").html(obj[i].title);
							//$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
							if($(window).width()>1024){	
							    $("#content").html(obj[i].createTime+"&nbsp;&nbsp;"+obj[i].memo.substring(0,40)+"...");
							}else{
							    $("#content").html(obj[i].createTime+"&nbsp;&nbsp;"+obj[i].memo.substring(0,18)+"...");
							}
							//$("#newsCenter section a p").attr("title",obj[i].copyMemo);
							$("#headNews").attr("title",obj[i].copyMemo);
						}
						
					}
				}	  
			});	
		}
		
		function getCompanyHeadNews(sj_id,activeId){
			$.ajax({
				type: 'POST',
				url: '/portal/infoSearch/findStfbHeadNewsLatestEvents.action?random='+Math.random(),
				data:{
					"sj_id" : sj_id
				},
				dataType:'json',
				cache : false,
				error:function(){/**alert('系统连接失败，请稍后再试！')*/},
				success: function(obj){			
					if(obj){
						$("#newsCenter hgroup a").attr("href","/portal/infoSearch/findStfbNewsByPage.action?sj_id="+activeId);
						for(var i=0;i<obj.length;i++){
							
							$("#newsCenter section a").attr("href","http://10.1.44.18/stfb"+obj[i].url+".htm");
							$("#newsCenter section a h5").html(obj[i].title);
							//$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
							$("#newsCenter section a p").html(obj[i].memo);
							//$("#newsCenter section a p").attr("title",obj[i].copyMemo);
							$("#newsCenter section a").attr("title",obj[i].copyMemo);
						}
						
					}
				}	  
			});	
		}
		
		function openNews(){
		    window.open("http://10.1.44.18/stfb"+headHref+".htm");
		}
			
		$(document).ready(function(){ 
			
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
			
			var headId = $("#headId").val();
			var activeId = $("#activeId").val();
			var parentId = $("#parentId").val();
			getGroupHeadNews("37");
			getCompanyHeadNews(headId,activeId);
			getXWTTNews(activeId);
			getXWTTPicNews(activeId);
			getLatestMoreNews(parentId);
			
			$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			
			$(window).resize(function(){
				$("#newsCenter").width($(".news").width()-$("#play").width()-40);
			})
			
			
			//loadShow();
			t = setInterval("showAuto()", 6000);
			
			var number = 0;	
			var company = "";		
			switch(headId){
			    case '1324':number = 1;company = "第一运营公司";break;
			    case '1325':number = 2;company = "第二运营公司";break;
			    case '1331':number = 3;company = "第三运营公司";break;
			    case '1343':number = 4;company = "第四运营公司";break;
			    case '1347':number = 5;company = "维保中心";break;
			    case '1357':number = 6;company = "运管中心";break;
			    case '1349':number = 7;company = "建管中心";break;
			    case '1375':number = 8;company = "技术中心";break;
			    case '1377':number = 9;company = "培训中心";break;
			    case '1363':number = 10;company = "信息中心";break;
			    case '1381':number = 11;company = "资金中心";break;
			    case '1369':number = 12;company = "资产中心";break;
			    case '1371':number = 13;company = "资产公司";break;
			    case '1386':number = 14;company = "隧道设计院";break;
			    case '1389':number = 15;company = "股份公司";break;
			    case '1387':number = 16;company = "黄浦江大桥建设公司";break;
				case '1636':number = 17;company = "磁浮公司";break;
			}
			$("#companyList").children("li:eq("+number+")").attr("style","background-color:#33ddff");
			$("#newsCenter h2").html(company);
		});
		
		</script>
		
</head>

<body>
	<input type="hidden" id="parentId" value="<%=parentId %>">
	<input type="hidden" id="activeId" value="<%=activeId %>">
	<input type="hidden" id="headId" value="<%=headId %>">
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
        	
        	<div class="right_main mb10" id = "newsHead">
        	
        	<div class="mb10 jttt">
                	<div class="jttt_l ">
                    	<div class="jttt_r">
                        	<a href="#">
                                <table cellpadding="0" cellspacing="0" border="0" onclick="openNews();">
                                
                                    <tr id="headNews">
                                        <td><h5></h5></td>
                                        <td><div class="con" id="content" ></div></td>
                                    </tr>
                                
                                </table>
                           </a>
                        </div>
                    </div>
                </div>
                
                
            	<!--News
            	<div class="news clearfix" >
            	
                  <div class="news_r">
                        <div class="news_l">
                	
                    <div class="fl" id="newsGroup">
                    	<hgroup class="clearfix mb10">
                        	<h2 class="fl">集团头条</h2>
                        	<h6 class="fl">News</h6>
                            <p class="t_r"><a href="#" target="_self" class="more_2">更多</a></p>
                        </hgroup>
                        <section>
                        	<a href="#" target="_blank">
                        	<h5></h5>
                            <p></p>
                            </a>
                        </section>
                        
                    </div>
                	
                </div>
                </div>
                </div>
            	-->
            	
            	<div class="news clearfix" >
            	
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
                        	<h2 class="fl"></h2>
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
                 
            </div>
           
        	<!--Aside-->
            <!-- <aside class="fr">
            <table>
            	<tr><td colspan="2"></td></tr>
            	<tr>
            		
            		<td>
            		<div class="panel_1" style="margin-right:5px;"><div class="bg_2"><div class="bg_3">
           		  <hgroup class="asideH" style="margin-bottom:15px;">
                    	<h5>新闻站点链接</h5>
                  </hgroup>
                 <ul class="list" id="companyList">
                   	<li><a href="xw_index.jsp" target="_self">申通集团</a></li> 
				    <li><a href="xw_company2.jsp?headId=1324&activeId=1335&parentId=1334" target="_self">第一运营公司</a></li>
					<li ><a href="xw_company.jsp?headId=1325&activeId=1326&parentId=1336" target="_self">第二运营公司</a></li>
					<li><a href="xw_company.jsp?headId=1331&activeId=1340&parentId=1330" target="_self">第三运营公司</a></li>
					<li><a href="xw_company.jsp?headId=1343&activeId=1332&parentId=1342" target="_self">第四运营公司</a></li>
					<li><a href="xw_company.jsp?headId=1347&activeId=1355&parentId=1354" target="_self">维保中心</a></li>
					<li><a href="xw_company.jsp?headId=1357&activeId=1348&parentId=1356" target="_self">运管中心</a></li>
					<li><a href="xw_company.jsp?headId=1349&activeId=1359&parentId=1358" target="_self">建管中心</a></li>
					<li><a href="xw_company.jsp?headId=1375&activeId=1362&parentId=1374" target="_self">技术中心</a></li>
					<li><a href="xw_company.jsp?headId=1377&activeId=1378&parentId=1376" target="_self">培训中心</a></li>
					<li><a href="xw_company.jsp?headId=1363&activeId=1380&parentId=1379" target="_self">信息中心</a></li>
					<li><a href="xw_company.jsp?headId=1381&activeId=1365&parentId=1364" target="_self">资金中心</a></li>
					<li><a href="xw_company.jsp?headId=1369&activeId=1370&parentId=1383" target="_self">资产中心</a></li>
					<li><a href="xw_company.jsp?headId=1371&activeId=1372&parentId=1384" target="_self">资产公司</a></li>
					<li><a href="xw_company.jsp?headId=1386&activeId=1373&parentId=1385" target="_self">隧道设计院</a></li>
					<li><a href="xw_company.jsp?headId=1389&activeId=1390&parentId=1395" target="_self">股份公司</a></li>
					<li><a href="xw_company.jsp?headId=1387&activeId=1388&parentId=1394" target="_self">黄浦江大桥建设公司</a></li>
					<li><a href="xw_company.jsp?headId=1636&activeId=1654&parentId=1635" target="_self">磁浮公司</a></li>
                 </ul>
                 
            	</div></div></div>
            		</td>
            		</tr>
            	
            	</table>
            	
            	
            	
				<a href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/prophase/improvePlanSearch.do?b_query=true" target="_blank">
                 	<img src="../../css/default/images/improve_plan.jpg"></img>
                 </a>
               
			
          </aside> -->
        	<!--Aside End-->
        </div>
	</div>
</body>
</html>