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
        
</head>

<body>
<div class='detail' style='z-index:9999;'></div>
	<footer id="foot">
      
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
