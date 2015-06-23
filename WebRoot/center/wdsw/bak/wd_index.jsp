<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>我的事务首页</title>
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
		
		
		
		<script src="js/wdsw_ajax.js"></script>
		
		
		<script type="text/javascript">
		var t ;
		function scroll(obj,n){
			if(t){
				clearTimeout(t);
			}
			if (temp==0){
				return;
			}
			var temp=n;
		 	$("#"+obj).scrollTop($("#"+obj).scrollTop()+temp);
			
			t = window.setTimeout(function(){ scroll(obj,temp);},100);
		}
		
		
		$(document).ready(function(){ 
			getDateInfo();
			getMeetInfo();
			getLatestNews("40","0");
			$(".up a").bind({
				mouseover:function(){scroll($(this).parent().parent("div").find("ul").attr("id"),-2)},
    			mouseout:function(){scroll($(this).parent().parent("div").find("ul").attr("id"),0)},
    			mousedown:function(){scroll($(this).parent().parent("div").find("ul").attr("id"),-5)}  
			})
			$(".down a").bind({
				mouseover:function(){scroll($(this).parent().parent("div").find("ul").attr("id"),2)},
    			mouseout:function(){scroll($(this).parent().parent("div").find("ul").attr("id"),0)},
    			mousedown:function(){scroll($(this).parent().parent("div").find("ul").attr("id"),5)}  
			})
			$("#oldDept").change(function(){
				deptSwitch($("#oldDept").val());
			})
			
			
			findDbx("");
			findNewDbx();
			
		});
		
		
		
		
		$(function(){
			showWeather360();
			showCountInfo();
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
                	<li><a href="javascript:window.location.href='/portal/center/xwzx/xw_index.jsp'">我的事务</a></li>
                	<li class="fin">首页</li>
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
               <div style="padding-top:5px">
                <select class="fr" id="oldDept" name="oldDept">
               		<s:iterator value="#session.oldDeptList" id="d">
               			<option <s:if test="#session.oldDeptId==#d.id">selected</s:if>
               		 	value="<s:property value="#d.id" />"/><s:property value="#d.name" /></option>
               		</s:iterator>
               		
              </select>
              </div>
            </div>
   		</div>
        <!--Ctrl End-->
         <div class="clearfix pt45">
        	<div class="right_main mb10">
            	  <!---->
        <div class="mb10 clearfix">
			<!--Panel_3-->
                <div class="panel_3 m6 fl w49p">
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl richeng">日程安排</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="clearfix">
                        	<ul id="dateManage" class="clearfix wd">
                            </ul>
                            <p class="fr"><a class="more_3" href="#">更多</a></p>
                            <p class="fr down"><a href="javascript:void(0);">向下</a></p>
                            <p class="fr up"><a href="javascript:void(0);">向上</a></p>   
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->
			<!--Panel_3-->
                <div class="panel_3 m6 fr w49p">
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl meeting">会议安排</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="clearfix">
                        	<ul id="meetManage" class="clearfix wd">
                            </ul>
                            <p class="fr"><a class="more_3" href="#">更多</a></p>
                            <p class="fr down"><a href="javascript:void(0);">向下</a></p>
                            <p class="fr up"><a href="javascript:void(0);">向上</a></p>   
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->
                <div class="clear"></div>
			<!--Panel_3-->
                <div class="panel_3 m6 fl w49p" >
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl wlist1" style="cursor:pointer;" onclick="window.location.href='http://10.1.44.18/ultimus/dbx.jsp'">待办事项</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="clearfix">
                        	<ul id="todolist" class="clearfix wd">
                            </ul>
                            <p class="fr"><a class="more_3" href="#">更多</a></p>
                            <p class="fr down"><a href="javascript:void(0);">向下</a></p>
                            <p class="fr up"><a href="javascript:void(0);">向上</a></p>   
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->
			<!--Panel_3-->
                <div class="panel_3 m6 fr w49p">
                	<header class="clearfix">
                    	<div class="bg_3 clearfix">
                            <h5 class="fl wlist2">待办事项（新）</h5>
                        </div>
                    </header>
                    <div class="con">
                        <div class="clearfix">
                        	<ul id="todolistnew"  class="clearfix wd">
                            </ul>
                            <p class="fr"><a class="more_3" href="#">更多</a></p>
                            <p class="fr down"><a href="#">向下</a></p>
                            <p class="fr up"><a href="#">向上</a></p>   
                        </div>
                    </div>
                </div>
                <!--Panel_3 End-->
                
                 
        </div>
        
        <!--Panel_3-->
                <div class="panel_3 mb10">
                	<header class="clearfix"><div class="bg_3 clearfix">
                    	<h5 class="fl announce">通知通告</h5>
                        <div class="fr tabs_1">
                            <ul>
                            	
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
                <!--Panel_3 End-->
                
        <!-- End-->	
            </div>
        	<!--Aside-->
            <aside class="fr">
            	<div class="panel_1" ><div class="bg_2" ><div class="bg_3" >
           		  <hgroup class="asideH weather">
                    	<h5><b>天气预报</b></h5>
                        
                  </hgroup>
            	<ul id="weather360" class="asideUl mb10">
                 	<li class="clearfix t_c b_bor hlight p8">
						<div class="fl" style="height:45px;width:30%">
							<img id="img1" src="" width="75px" height="50px">
						</div>
						<div class="fl" style="width:35%">
							<b class="L_08"><p id="p1" align="center"></p><p id="p2" align="center"></p></b>
						</div>
						<div class="fl" style="width:35%">
							<b class="L_08"><p id="p3" align="center"></p><p id="p4" align="center"></p></b>
						</div>
					</li> 
					<li class="clearfix t_c b_bor p8">
						<div class="fl" style="height:45px;width:30%">
							<img id="img2" src="" width="75px" height="50px">
						</div>
						<div class="fl" style="width:35%">
							<b><p id="p5" align="center"></p><p id="p6" align="center"></p></b>
						</div>
						<div class="fl" style="width:35%">
							<b><p id="p7" align="center"></p><p id="p8" align="center"></p></b>
						</div>
					</li> 
           <li class="clearfix t_c b_bor p8">
						<div class="fl" style="height:45px;width:30%">
							<img id="img3" src="" width="75px" height="50px">
						</div>
						<div class="fl" style="width:35%">
							<b><p id="p9" align="center"></p><p id="p10" align="center"></p></b>
						</div>
						<div class="fl" style="width:35%">
							<b><p id="p11" align="center"></p><p id="p12" align="center"></p></b>
						</div>
					</li>       	
                 </ul>
                  
            	
            	<table id="weatherBak" border="0" cellspacing="0" cellpadding="0" style="margin:10px auto;display:none">
			      
			      <tr>
			        <td ><table width="230" border="0" align="center" cellpadding="0" cellspacing="0">
			          <tr>
			            <td>
						<iframe style = "FILTER: chroma(color:#D7F3FF)" src="tqyb.jsp" width="230" height="120" border="0" frameborder="0" framespacing="0" marginheight="0" marginwidth="0" scrolling="no"></iframe></td>
			          </tr>
			        </table></td>
			      </tr>
			      <tr>
			        <td>
					</td>
			      </tr>
			    </table>
			    </div></div></div>
			    <div class="panel_1" ><div class="bg_2" ><div class="bg_3">
           		  <hgroup class="asideH stats_2">
                    	<h5><b>业务统计</b></h5>
                        
                  </hgroup>
                 <ul class="stats_2 stats_2_bg1" id="rightUl">
                 		<li class="blue"><a href="http://10.1.44.18/ultimus/dbx.jsp" target="_self" style="display:inline">流程待办事项：<h1 id="dbx_num" class="mr5 L_08"></h1><h6>条</h6></a></li> 
                 		<li class="blue"><a href="http://10.1.44.18/ultimus/jbx.jsp" target="_self" style="display:inline" title="仅供预览，将办事项需等到业务流程节点到达后方可办理。">部门将办事项：<h1 id="jbx_num" class="mr5 L_08"></h1><h6>条</h6></a></li> 
                 		<li class="blue"><a href="http://10.1.44.18/ultimus/dbx.jsp" target="_self" style="display:inline">流程超时事项：<h1 id="csx_num" class="mr5 L_08"></h1><h6>条</h6></a></li>
                 		<li class="blue"><a href="http://10.1.44.18/ultimus/dbx.jsp" target="_self" style="display:inline">最大超时时间：<h1 id="csTime_num" class="mr5 L_08"></h1><h6>天</h6></a></li>
                 		<li class="blue"><a href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/urge/urgeFrame.jsp" target="_self" style="display:inline">催办业务流程：<h1 id="cbx_num" class="mr5 L_08"></h1><h6>条</h6></a></li>
                 		<li class="blue"><a href="http://10.1.44.18/stoa/publicConn.jsp?urlPath=/message/msgFrame.jsp" target="_self" style="display:inline">未读通知总数：<h1 id="notice_num" class="mr5 L_08"></h1><h6>个</h6></a></li>
                    	<!--<li class="blue"><a href="javascript:void(0);" style="display:inline">平均处理时间：<h1 id="csTime_num" class="mr5 L_08">/</h1><h6>天</h6></a></li>-->
                    	<li class="blue"><a href="javascript:void(0);" style="display:inline">平均得分：<h1 id="csTime_num" class="mr5 L_08">/</h1><h6>分</h6></a></li>
                    </ul>
            	</div></div></div>
			    
			<div style="display:none;" class="panel_1" ><div class="bg_2" ><div class="bg_3" >
           		  <hgroup class="asideH calendar">
                    	<h5><b>我的日历</b></h5>
                        <a target="_blank" href="http://10.1.44.18/datemanager/modifyDateManagerInfoRes.jsp?type=add" class="more_1">新 增</a>
                  </hgroup>    
			<iframe src="dateManager.jsp" width="230" height="220" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" style="margin:auto"></iframe>
			</div></div></div>	    
			   
          </aside>
        	<!--Aside End-->
        </div>
	</div>
</body>
</html>
