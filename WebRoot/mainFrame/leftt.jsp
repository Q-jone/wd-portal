<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*"%>

<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.wondersgroup.framework.menu.service.MenuService"%>
<%@page import="com.wondersgroup.framework.menu.bo.MenuResource"%>
<%@page import="com.wondersgroup.framework.security.bo.SecurityUser"%>
<%@page import="com.wonders.stpt.core.login.constant.LoginConstant"%>
<%@page import="com.wondersgroup.framework.organization.service.OrganNodeService"%>
<%@page import="com.wondersgroup.framework.organization.bo.OrganNode"%>
<%@page import="com.wondersgroup.framework.organization.service.OrganTreeService"%>
<%@page import="com.wondersgroup.framework.organization.bo.OrganTree"%>
<%@page import="com.wonders.stpt.core.menu.util.MenuUtil"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/html5.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script type="text/javascript">
        	$(document).ready(function(){
        		//$("#outer").height($(window).height()-$(".gen_search").height()-$(".support").height());
        			  //	$("#outer").height($(window).height()-$(".gen_search").height()-10);
        			  $("#outer").height($(window).height()-10);
        	});
        </script>

<script src="../js/ui/jquery.ui.core.js"></script>
<script src="../js/ui/jquery.ui.widget.js"></script>
<script src="../js/ui/jquery.ui.accordion.js"></script>
<script src="../js/show.js"></script>
<script type="text/javascript">
		 //读取cookie中的值
	function readCookie(name) {
	  var searchName = name + "=";
	  var cookies = document.cookie.split(';');
	  for(var i=0; i < cookies.length; i++) {
		var c = cookies[i];
		while (c.charAt(0) == ' ')
		  c = c.substring(1, c.length);
		if (c.indexOf(searchName) == 0)
		  return c.substring(searchName.length, c.length);
	  }
	  return "";
	}
	$(document).ready(function(){
		//if ($(parent.document).find("#main").attr("cols") == "0,*") {
		//	$(parent.document).find("#main").attr("cols","226,*");
		//	window.location.href=window.location.href; 
		//}
		
		$(window).resize(function(){
			var c = readCookie("leftPath"); 
			if(c==""){
				c = window.location.href;
			}
			window.location.href = c; 
		});
		
		$( "#accordion" ).accordion({
			fillSpace: true
		});
		$(".menuTwo").hide();

		
		$(".menuOne").children().children("li").children("span").each(function(i,n){
			$(n).click(function(){
				//alert(1);
				$(".menuOne").children().children("li").removeClass("selected");
				$(".menuTwo li").removeClass("selected");
				$(n).parent().addClass("selected");
				$(n).parent().siblings("li").children(".menuTwo").hide();
				if($(n).parent().children(".menuTwo").length>0){
					$(n).parent().children(".menuTwo").toggle();
				}
			})
		})
	
		$(".menuTwo li").click(function(){
			$(".menuTwo li").removeClass("selected");
			$(this).addClass("selected");
		})
					
		$("h4").click(function(){
					$("h4").removeClass("open");
					$(this).addClass("open");
		})	


		$(".menuOne a").click(function(){
				var tar = $(this).attr("winTarget");
				var path = $(this).attr("linkPath");

				if(path==""){
					//return false;
				}else{
					if(tar == "_blank"){
						window.open(path);
					}else{
						$(parent.document).find("#mainFrame").attr("src",path);
					}
				}
		})
		
		$("#arrow").click(function(){
			showHideForLeft();
		})
		
		loadShowForLeft();
		
	});

	function main_click(url,target){
		if(target == "_blank"){
			window.open(url);
		}else{
			$(parent.document).find("#mainFrame").attr("src",url);
		}
		
	}
	</script>
	
	<%
		String userId = (String)session.getAttribute(LoginConstant.SECURITY_USER_ID);
		String topId=request.getParameter("topid");
		if(topId==null||"".equals(topId)){
			topId = "-1";
		}
		String deptId = (String)session.getAttribute(LoginConstant.USER_DEPT_ID);
	%>
</head>
<body>
<!-- <div id="leftDiv" class="transparent"></div>-->
<!--close-->
    <div id="arrow" class="close_2"><a href="javascript:void(0);">关闭</a></div>
<!--close end-->
<!--Gen_search--><!--
<div class="gen_search">
<div class="l_t"></div>
<div class="s_t"></div>
<div class="r_t"></div>
<div class="s_l">
<div class="s_r">
<div class="con clearfix"><input type="search" name="textfield"
	id="textfield" class="input_medium fl"> <input name="button"
	type="submit" class="fl" id="button" value="检索"></div>
</div>
</div>
<div class="l_b"></div>
<div class="s_b"></div>
<div class="r_b"></div>
</div>-->
<!--Gen_search End-->
<div class="demo">

<div id="outer">
<div id="accordion" class="sidebar">
<%
	boolean showFlag = false;
	String title="";
	if("-1".equals(topId)){	
%>
<ul><li ><h4 class="open"><a href="#">&nbsp;</a></h4></li></ul>
<div class="menuOne">
<p>&nbsp;</p>
</div>
<%
	}else{
	List navTopMenu = MenuUtil.getMenus(topId,userId,deptId);

	if(navTopMenu != null && navTopMenu.size() > 0) {
	for(int i=0;i<navTopMenu.size(); i++){ 
		MenuResource oneNavTopMenu=(MenuResource)navTopMenu.get(i);
		String	resourceName = oneNavTopMenu.getResourceName();
		String	resourceId = oneNavTopMenu.getResourceId();
		String	url=oneNavTopMenu.getLinkPath();
		if(url==null) url="";
		if(title.equalsIgnoreCase(""))
		{ 
			title=resourceName;
		}else{
			title=resourceName;
		}
%>

<ul>
	<li>
	<h4 <%if(i==0){ %> class="open" <%} %>><a href="#"><%=title %></a></h4>
	</li>
</ul>
<div class="menuOne">
<ul>
<%
     	List nTopMenu = MenuUtil.getMenus(resourceId,userId,deptId);
		//if (nTopMenu.size() > 0) out.print(",");
		for(int j=0;j<nTopMenu.size();j++)
	    {
	    	boolean flag = false;
			oneNavTopMenu=(MenuResource)nTopMenu.get(j);
			String resourceName2 = oneNavTopMenu.getResourceName();
			String resourceId2 = oneNavTopMenu.getResourceId();
			String url2=oneNavTopMenu.getLinkPath();
			if(url2==null) url2="";
			//String  icon=oneNavTopMenu.getIcon();
			String target2 = oneNavTopMenu.getTarget();
			if(target2==null || "".equals(target2)){
				target2 = "";
			}
			List tTopMenu = MenuUtil.getMenus(resourceId2,userId,deptId);
			if(tTopMenu!=null&&tTopMenu.size()>0){
				flag = true;
			}
%>
	<li><span>
	<a  <%if(!flag&&"".equals(url2)){ %> class="disable" <%} %>  href="javascript:void(0);" title="<%=resourceName2 %>" linkPath="<%=url2 %>" winTarget="<%=target2 %>"><%=resourceName2 %></a>
	</span>
	<%
		//List tTopMenu = MenuUtil.getMenus(resourceId2,userId,deptId);
			for(int m=0;m<tTopMenu.size();m++)
		    {
				oneNavTopMenu=(MenuResource)tTopMenu.get(m);
				String resourceName3 = oneNavTopMenu.getResourceName();
				String	resourceId3 = oneNavTopMenu.getResourceId();
				String url3=oneNavTopMenu.getLinkPath();
				if(url3==null) url3="";
				String target3 = oneNavTopMenu.getTarget();
				if(target3==null || "".equals(target3)){
					target3 = "";
				}
				
	%>
		<ul class="menuTwo">
			<li><a <%if("".equals(url3)){ %> class="disable" <%} %> href="javascript:void(0);" title="<%=resourceName3 %>" linkPath="<%=url3 %>" winTarget="<%=target3 %>"><%=resourceName3 %></a></li>		
		</ul>
	<%} %>
	</li>
	 <%} %>         
</ul>
</div>
<%} 
}else{
		showFlag = true;
	}
 }%> 	

</div>
</div>
</div>
<!-- End demo -->


<!--Support-->
<!--  <div class="support"><img style="padding-top:10px;"  src="../css/default/images/support.png" width="211" height="42" border="0" usemap="#Map">
      <map name="Map">
        <area shape="rect" coords="1,0,30,32" href="#" alt="Andriod">
        <area shape="rect" coords="41,-4,71,34" href="#" alt="Symbian">
        <area shape="rect" coords="83,-1,113,31" href="#" alt="Apple">
        <area shape="rect" coords="124,0,155,30" href="#" alt="Wmobile">
        <area shape="rect" coords="165,-3,196,31" href="#" alt="Blackberry">
      </map>
    </div> -->
<!--Support End-->
</body>
</html>
<%if(showFlag){%>
		<script type="text/javascript">
			$(parent.document).find("frameset[id=main]").attr("cols","7,*");
			$(".demo").hide();
			$("#arrow").removeClass();
			$("#arrow").addClass("open_2");
			$(parent.frames["mainFrame"].document).find("#show").attr("src","/portal/css/default/images/sideBar_arrow_right.jpg");
			$(parent.frames["mainFrame"].document).find("#show").attr("title","展开");
		</script>	
<%}%>