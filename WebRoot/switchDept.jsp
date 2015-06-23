<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wondersgroup.framework.security.bo.*" %>
<%@ page import="com.wondersgroup.framework.organization.bo.*" %>
<%@ page import="com.wonders.stpt.core.login.constant.*" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@page import="com.wonders.stpt.core.domainCross.util.DomainCrossUtil"%>
<%@ page import="com.wonders.stpt.core.cookie.util.CookieUtil"%>
<%@ page import="com.wonders.stpt.util.SpringBeanUtil" %>
<%@ page import="com.wonders.stpt.core.login.entity.bo.*" %>
<script src="js/jquery-1.7.1.js"></script>
<%
	Map<String,SecurityUser> deptMap = new HashMap<String,SecurityUser>();
	deptMap = (Map<String,SecurityUser>)session.getAttribute(LoginConstant.USER_DEPT_MAP);
	String deptId = (String)request.getParameter("deptId");
	if(deptMap!=null&&deptMap.containsKey(deptId)){
		SecurityUser user = deptMap.get(deptId);
		String caAppName =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caAppName").trim();
		String caCross =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caCross").trim();
		String urlCa =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("urlCa").trim();
		//ca 切换部门
		if(urlCa!=null && !"".equals(urlCa)&&!"0".equals(caCross)){
			String token = CookieUtil.getCookieByName(request,"token");	
			String serverPath =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("serverPath").trim();
			String apiName2 =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("apiName2").trim();
			String method=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("switchDeptMethod").trim();;
			String secret=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("secret").trim();
			String appName=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caAppName").trim();
			String urls = urlCa+serverPath+"/"+apiName2;
			String dataParams="<?xml version=\"1.0\" encoding=\"utf-8\"?><params><newUserId>"+user.getId()+"</newUserId></params>";
			String sign=CookieUtil.getMD5(appName+token+method+secret);
			String dataType="json";
			%>
			<script>
				  $.ajax({
				    type: 'GET',
					url: '<%=urls%>',
					dataType:'jsonp',
					data:{
							"token" : '<%=token%>',	
							"method" : '<%=method%>',				
							"appName" : '<%=appName%>',	
							"secret" : '<%=secret%>',	
							"dataType" : 'json',	
							"dataParams" : '<%=dataParams%>',	
							"sign" : '<%=sign%>'
						},
						jsonp:'jsonpcallback',
					error: function(XmlHttpRequest,textStatus,errorThrown){
							alert("部门切换失败，请联系管理员!");
							top.location.href = '/portal/mainFrame/frame.html';
						},
					success: function(msg){		
							if(msg.code=="100"){
								top.location.href = "clearToken.jsp?appName=<%=caAppName%>";
							}else{
								alert("部门切换失败，请联系管理员!\n错误代码："+msg.code);
								top.location.href = '/portal/mainFrame/frame.html';
							}
									
						}		
				  });
			</script>
	<%
		}else{
			String agent = "";
			String tloginName = (String)session.getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
			Tuser t = (Tuser)session.getAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER);
			if(tloginName!=null&&user.getLoginName()!=null&&!user.getLoginName().startsWith(tloginName)){
			agent = "["+t.getName()+"代]";
			//System.out.println(agent+"dddddddddddddddddddddddddddd");
		}
			session.setAttribute(LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName());
		    session.setAttribute(LoginConstant.SECURITY_USER_ID, user.getId()+"");
		    session.setAttribute(LoginConstant.SECURITY_USER_NAME, user.getName()+agent);
		    session.setAttribute(LoginConstant.SECURITY_LOGIN_USER, user);
		    
		    //cookie
		   	CookieUtil.saveCookie(response, LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName(), "/");
		    CookieUtil.saveCookie(response, LoginConstant.SECURITY_USER_ID, user.getId()+"", "/");
		    CookieUtil.saveCookie(response, LoginConstant.SECURITY_USER_NAME, user.getName()+agent, "/");
		    
		   	Set<OrganNode> nodes = user.getOrganNodes();
			 if (nodes != null && nodes.size() > 0) {
				 for(OrganNode node:nodes){
					 session.setAttribute(LoginConstant.USER_DEPT_ID, node.getId()+"");
					 if(node.getDescription()!=null&&!"".equals(node.getDescription())){
						 session.setAttribute(LoginConstant.USER_DEPT_NAME, node.getDescription());
					 }else{
						 session.setAttribute(LoginConstant.USER_DEPT_NAME, node.getName());
					 }
					 //cookie
					 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_ID, node.getId()+"", "/");
					 if(node.getDescription()!=null&&!"".equals(node.getDescription())){
						 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getDescription(), "/");
					 }else{
						 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getName(), "/");
					 }
		        }
			 }
			  %>
		 <script type="text/javascript">
			top.location.href = '/portal/mainFrame/frame.html';
		</script>
		<%
		 }
		
	}
	
	
%>
