<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.security.NoSuchAlgorithmException"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="org.dom4j.DocumentHelper"%>
<%@ page import="org.dom4j.Document"%>
<%@ page import="org.dom4j.Element"%>
<%@ page import="org.dom4j.Node"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.DataOutputStream"%>
<%@ page import="java.io.OutputStreamWriter"%>
<%@ page import="com.wonders.stpt.core.cookie.util.CookieUtil"%>
<%@ page import="com.wonders.stpt.util.OldOaUtil"%>
<%@ page import="com.wonders.stpt.util.StringUtil"%>
<%@ page import="com.wonders.stpt.core.login.entity.vo.TaskUserVo"%>
<!-- 
<?xml version="1.0" encoding="UTF-8"?>
<result>
<code>100</code>
<description>success!</description>
<params>
<param>
<token>8a81aba6409a3b6201409a40a5610005</token>
<tuser>
<id>4456</id>
<loginName>G00100000030</loginName>
<name>董璁</name>
<password>1f82c942befda29b6ed487a51da199f78fce7f05</password>
<operateTime>2013-08-20 13:41:15</operateTime>
<operator>null</operator>
<removed>0</removed>
<rank>null</rank>
<company>null</company>
<dept>null</dept>
<sex>null</sex>
<phone>null</phone>
<mobile1>null</mobile1>
<mobile2>null</mobile2>
<flag>1</flag>
<idcard>null</idcard>
<birthday>null</birthday>
<nation>null</nation>
<birthplace>null</birthplace>
<political>null</political>
<degree>null</degree>
<address>null</address>
<postcode>null</postcode>
<grade>null</grade>
<title>null</title>
<major>null</major>
<cpostcode>null</cpostcode>
<cphone>null</cphone>
<household>null</household>
<retire>null</retire>
<caddress>null</caddress>
<remark>null</remark>
<nickName>null</nickName>
</tuser>
<csuser>
<userId>2559</userId>
<loginName>G001000000302507</loginName>
<userName>董璁</userName>
<deptId>2507</deptId>
<deptName>财务部</deptName>
</csuser>
<deptUsers>
<deptUser>
<userId>2559</userId>
<loginName>G001000000302507</loginName>
<userName>董璁</userName>
<deptId>2507</deptId>
<deptName>集团-财务部</deptName>
</deptUser>
<deptUser>
<userId>4065</userId>
<loginName>G001000000302813</loginName>
<userName>董璁</userName>
<deptId>2813</deptId>
<deptName>十号线项目公司-综合办公室</deptName>
</deptUser>
<deptUser>
<userId>6888</userId>
<loginName>G001000000302949</loginName>
<userName>董璁</userName>
<deptId>2949</deptId>
<deptName>集团-十号线项目公司</deptName>
</deptUser>
</deptUsers>
<oldPortal>
<oldLoginName>*13HYQ</oldLoginName>
</oldPortal>
</param>
</params>
</result>

 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Cache-Control", "no-store");// Public
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
String action=request.getParameter("action");
String sessionKey=request.getParameter("sessionKey");
String returnUrl=request.getParameter("returnUrl");
String oldUserId=StringUtil.getNotNullValueString(request.getParameter("oldUserId"));
String oldDeptId=StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
String appName="AUTO_LOGIN"; 
String secret="124a77748fcb48a7a0863f30970a2a04";       
String urlCa="";//="http://10.1.43.32:8088/ca";
String serverPath="";//="/services/api";
String apiName="";//="dataExchange";

Properties properties = new Properties();
String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
try {
	FileInputStream fis = new FileInputStream(configPath);
	properties.load(fis);
	urlCa = properties.getProperty("urlCa");
	serverPath = properties.getProperty("serverPath");
	apiName = properties.getProperty("apiName");	
	appName = properties.getProperty("caAppName");	
	//System.out.println(appName);
	fis.close();	
} catch (Exception e) {
	//e.printStackTrace();
	appName="AUTO_LOGIN"; 
	secret="124a77748fcb48a7a0863f30970a2a04";       
	urlCa="http://10.1.48.20/ca";
	serverPath="/services/api";
	apiName="dataExchange";
}

String thisPageUrl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getRequestURI();
if(returnUrl!=null && thisPageUrl.indexOf(returnUrl)!=-1){//若本页面地址被作为returnUrl参数传入,则默认回到首页面
  	returnUrl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
}
if(returnUrl!=null){
	//returnUrl = returnUrl.replace("&","%26").replace("?","%3F").replace("/","%2F");	
	request.getSession().setAttribute("returnUrl",returnUrl);	
}

%>
<%! 
public String getUserLoginName(String loginName){
	if(loginName.length()==0) return "";
	String prefix = "ST/";
	
	if(!loginName.startsWith(prefix)){
		loginName=prefix + loginName;
	}
	
	return loginName;
}


	public String getMD5(String value) {  
        String result = null;  
        try{  
            byte[] valueByte = value.getBytes();  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(valueByte);  
            result = toHex(md.digest());  
        }catch(NoSuchAlgorithmException e1){  
            e1.printStackTrace();  
        }  
        return result;  
    }  

    // 将传递进来的字节数组转换成十六进制的字符串形式并返回  
    private String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    } 
    

     private void saveCookie(HttpServletResponse response,String Name,String value) throws UnsupportedEncodingException{
    		Cookie cookie = new Cookie(Name, java.net.URLEncoder.encode(value,"utf-8"));
			cookie.setPath("/");		
			response.addCookie(cookie);
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
    <title>portal中转页面</title>
</head>
  
  <body> 
  portal中转页面 <br></br>
  <%
  	if(action!=null&&action.equalsIgnoreCase("logout")){
  		Cookie cookie = new Cookie("token", null);  
      cookie.setMaxAge(0);  
     	cookie.setPath("/");	
      response.addCookie(cookie);	 
			response.sendRedirect(urlCa+"/logout.jsp");	
			
  	}else if(action!=null && action.equalsIgnoreCase("setOldSession")){
			saveCookie(response,"oldUserId",oldUserId);	
			saveCookie(response,"oldDeptId",oldDeptId);	
			request.getSession().setAttribute("oldUserId",oldUserId);
			request.getSession().setAttribute("oldDeptId",oldDeptId);
			//放入老OA该用户所有部门
			request.getSession().setAttribute("oldDeptList",OldOaUtil.getOldUserDeptId(oldUserId));
			if(oldUserId!=null && oldDeptId!=null && oldUserId!="-1" && oldDeptId!="-1"){
				%>
				 <script type="text/javascript">
				 	document.body.style.backgroundColor="red";
				 </script>					
				<%
			//	response.sendRedirect("http://10.1.44.18/stfb/publicConn.jsp?urlPath=/system/index2.jsp");
			}
  	}else if(action!=null && action.equalsIgnoreCase("setOldSession2")){
			saveCookie(response,"oldUserId2",oldUserId);	
			saveCookie(response,"oldDeptId2",oldDeptId);	
			request.getSession().setAttribute("oldUserId2",oldUserId);
			request.getSession().setAttribute("oldDeptId2",oldDeptId);
			//放入老OA该用户所有部门
			request.getSession().setAttribute("oldDeptList2",OldOaUtil.getOldUserDeptId(oldUserId));
			if(oldUserId!=null && oldDeptId!=null && oldUserId!="-1" && oldDeptId!="-1"){
				%>
				 <script type="text/javascript">
				 	document.body.style.backgroundColor="red";
				 </script>					
				<%
			//	response.sendRedirect("http://10.1.48.101:8088/stfb/publicConn.jsp?urlPath=/system/index2.jsp");
			}
  	}else if(action!=null && action.equalsIgnoreCase("setOldSession3")){
			saveCookie(response,"oldUserId3",oldUserId);	
			saveCookie(response,"oldDeptId3",oldDeptId);	
			request.getSession().setAttribute("oldUserId3",oldUserId);
			request.getSession().setAttribute("oldDeptId3",oldDeptId);
			//放入老OA该用户所有部门
			request.getSession().setAttribute("oldDeptList3",OldOaUtil.getOldUserDeptId(oldUserId));
			if(oldUserId!=null && oldDeptId!=null && oldUserId!="-1" && oldDeptId!="-1"){
				%>
				 <script type="text/javascript">
				 	document.body.style.backgroundColor="red";
				 </script>					
				<%
			//	response.sendRedirect("http://10.1.48.101:8088/stfb/publicConn.jsp?urlPath=/system/index2.jsp");
			}
  	}else if(sessionKey==null){  
  		if (returnUrl != null) {
					returnUrl = returnUrl.replace("&","%26").replace("?","%3F").replace("/","%2F");	
				}
			response.sendRedirect(urlCa+"/login.jsp?appName="+appName+"&returnUrl="+returnUrl);
			
	}else{
		URL url = null;
		HttpURLConnection http = null;
		String textEntity="";
		try {
			url = new URL(urlCa+serverPath+"/"+apiName);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");	
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + sessionKey + "&method=SESSIONINFO.GET&dataType=xml&sign=" + getMD5(appName+sessionKey+"SESSIONINFO.GET"+secret);

         	OutputStreamWriter osw=new OutputStreamWriter(http.getOutputStream(),"utf-8");
          	osw.write(param);
          	osw.flush();
          	osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
				try {

				Document doc = DocumentHelper.parseText(textEntity);
	
				Element root = doc.getRootElement();
				if(root.element("code").getTextTrim().equalsIgnoreCase("100")){
					Element params=root.element("params");
					List<Element> list=params.elements();
					Element eParam=(Element)list.get(0);
					
					Element eToken = eParam.element("token");
					Element eTuser = eParam.element("tuser");
					Element eCsuser = eParam.element("csuser");
					
					Element edeptUsers = eParam.element("deptUsers");
					
					Element eoldUsers = eParam.element("oldPortal");
					
					List<Element> userlist = edeptUsers.elements();
					if(userlist != null && userlist.size() > 0){
						Map<String,TaskUserVo> userMap = new HashMap<String,TaskUserVo>();
						for(Element e : userlist){
							String deptUserId = e.element("userId").getTextTrim();
							String deptLoginName = getUserLoginName(e.element("loginName").getTextTrim());
							String deptUserName = e.element("userName").getTextTrim();
							String deptDeptId = e.element("deptId").getTextTrim();
							String deptDeptName = e.element("deptName").getTextTrim();
							String deptLoginNameNoSt = e.element("loginName").getTextTrim();
							TaskUserVo vo = new TaskUserVo(deptUserId,deptLoginName,deptUserName,deptDeptId,deptDeptName,deptLoginNameNoSt);
							userMap.put(deptLoginName,vo);
						}
						session.setAttribute("deptUsers", userMap);
					}
					
					Element etloginName = eTuser.element("loginName");
					Element eUserId = eCsuser.element("userId");
					Element eLoginName = eCsuser.element("loginName");
					Element eUserName = eCsuser.element("userName");
					Element eDeptId = eCsuser.element("deptId");
					Element eDeptName = eCsuser.element("deptName");
					
					Element eOldLoginName = eoldUsers.element("oldLoginName");
					String oldLoginName = eOldLoginName.getTextTrim();
					request.getSession().setAttribute("cs_login_name", StringUtil.getNotNullValueString(oldLoginName));
					//Element eOldUserId = eCsuser.element("oldUserId");
					//Element eOldDeptId = eCsuser.element("oldDeptId");									
					saveCookie(response,"token",eToken.getTextTrim());	
/*					saveCookie(response,"oldUserId",oldUserId);	
					saveCookie(response,"oldDeptId",oldDeptId);	
					request.getSession().setAttribute("oldUserId",oldUserId);
					request.getSession().setAttribute("oldDeptId",oldDeptId);
					//放入老OA该用户所有部门
					request.getSession().setAttribute("oldDeptList",OldOaUtil.getOldUserDeptId(oldUserId));
*/					
					String cloginName = eLoginName.getTextTrim();
					String tloginName = etloginName.getTextTrim();
					CookieUtil.sessionAdd(request,response,tloginName,cloginName,eDeptId.getTextTrim());
					response.sendRedirect(returnUrl);
					
				}else{
%>
			无法取得相关认证用户信息:<%=root.element("description").getTextTrim() %><br>
			请重新<a href="<%=urlCa %>/logout.jsp?appName=<%=appName%>&returnUrl=<%=returnUrl%>" target="_self">登录认证中心！</a>
			
<%				
				}
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	%>
					CA认证中心服务端返回数据格式错误，请联系管理员！
				
	<%					
				}    	       			
						
			}else{
			
%>
			无法取得相关认证用户信息<br>
			请重新<a href="<%=urlCa%>/logout.jsp?appName=<%=appName%>&returnUrl=<%=returnUrl%>" target="_self">登录认证中心！</a>
			
<%			
			
			}
		} catch (Exception e) {
	%>
					链接CA服务请求出错，请联系管理员！
				
	<%	
		} finally {
			if (http != null) http.disconnect();
		}
		
}
	
	
%>
   </body>
</html>
