<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@ page import="com.wondersgroup.framework.security.bo.SecurityUser" %>
<%@ page import="com.wonders.stpt.core.login.constant.LoginConstant" %>
<%@ page import="com.wonders.stpt.util.SpringBeanUtil" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%!
public static String getCookieByName(HttpServletRequest request, String name) {
	String cookieValue=null;
	Cookie[] cookies = request.getCookies();
    if (null != cookies) {
        for (int i = 0; i < cookies.length; i++) {
        	Cookie cookie = cookies[i];
    		
    		if(name.equals(cookie.getName())){
    			try{
    				cookieValue = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
    			} catch (UnsupportedEncodingException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			break;
    		}
        }
    }
    return cookieValue;
}
%>
<%
	response.setHeader("Cache-Control", "no-store");// Public
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String urlCa=SpringBeanUtil.getProperties("classpath:config.properties").getProperty("urlCa").trim();
	String caCross =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caCross").trim();
	String caAppName =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caAppName").trim();
	//String caReturnUrl =SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caReturnUrl").trim();
	
	//ca	
 	String token = getCookieByName(request,"token");
	String loginName = (String) request.getSession().getAttribute(LoginConstant.SECURITY_USER_ID);
	        // 如果封装的user不为空,说明已经登陆,则继续执行用户的请求.下面的就不处理了  
	        if(loginName!=null){  
		         response.sendRedirect("/portal/mainFrame/frame.html");
	        }else if(urlCa!=null && !"".equals(urlCa)&&!"0".equals(caCross)){ 		
				response.sendRedirect(urlCa+"/login.jsp?appName="+caAppName);	
		    } 
%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>上海申通地铁集团综合业务协同平台</title>
<link rel="stylesheet" href="css/formalize.css" />

<link rel="stylesheet" href="css/page.css" />
<link rel="stylesheet" href="css/default/imgs.css" />
<link rel="stylesheet" href="css/reset.css" />
		<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       <script src="js/jquery-1.7.1.js"></script>
		<script src="js/html5.js"></script>
		<script src="js/jquery.formalize.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$("#deptTr").hide();
	$("#loginName").blur(function(){
		if($("#loginName").val()!=""){
			$.ajax({
			type: 'POST',
			url: 'showTuserDepts.action?random='+Math.random(),
			data:{
					"loginName":$("#loginName").val()
				},
			dataType:'json',
			cache : false,
			error:function(){alert('系统连接失败，请稍后再试！')},
			success: function(obj){			
				var deptOption = "";
				if(obj.length==0){
					deptOption = "<option value=''>请选择</option>";
					$("#deptId").html(deptOption);
					$("#deptTr").hide();
				}else{
					for(var i=0;i<obj.length;i++){
						deptOption +="<option value='"+obj[i].deptId+"'>"+obj[i].deptName+"</option>";	
					}
					$("#deptId").html(deptOption);
					$("#deptTr").show();
				}

			}	  
		});	
		}
	})
	afterLoader();
})

//去除空格
function strTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}
//表单提交
function loginSubmit(){
	if(isvalid()){
		var userId = $("#loginName").val();
		var pwd = $("#password").val();

		var serverip = window.location.host;
		var port_pos = serverip.indexOf(':');
		if (port_pos > 0) {
			serverip = serverip.substring(0, port_pos);
		}
		$("#serverip").val(serverip);
		writeCookie("userIdLgin", userId, 7);
		//document.getElementById('loginForm').action='login.action?loginName='+userId+'&password='+pwd+'&operationTime=';
		$("#loginForm").submit();
	}
	return false;
}

//表单提交
function resetForm(){
	$("#loginName").val("");
	$("#password").val("");
	$("#validate").val("");
	$("#loginName").focus();
	return false;
}


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
//存放cookie
function writeCookie(name, value, days) {
  var expires = "";
  if (days) {
	var date = new Date();
	date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
	expires = "; expires=" + date.toGMTString();
  }
  document.cookie = name + "=" + value + expires + "; path=/";
}

//将cookie中的值放入表单

function setUserId(){
	$("#loginName").val(readCookie("userIdLgin"));
}

//页面登录时执行动作
function afterLoader(){
		 setUserId();
			 $("#loginName").focus();
}

function refreshImg(){
	$("#validateimg").attr("src","validateNumber.action?id="+Math.random());
}
</script>
<body class="Login">
<form id="loginForm" action="login.action" method="post">
<input type="hidden" name="appName" id="appName" value="<s:property value="#parameters.appName"/>">
<input type="hidden" name="returnUrl" id="returnUrl" value="<s:property value="#parameters.returnUrl"/>">
	<div class="mainDiv">
    	<div class="logo"></div>
        <div class="loginBox">
        	<hgroup class="clearfix">
            	<h3 class="fl mr5">用户登录</h3>
                <!--<h6>User Login</h6>-->
        	</hgroup>
        	<div id="errmsg" class="field" style="display:none;padding-left:110px;"></div>
            <div class="field">
            	<label>用户名</label>
                <input name="loginName" value="G00100000161" id="loginName" type="text" class="input_large">
            </div>
            <div class="field">
            	<label>密码</label>
                <input name="password" value="403411" id="password" type="password" class="input_large">
            </div>
            <div class="field">
            	<label>选择部门</label>
                <select id="deptId" name="deptId" class="input_large">
				</select>
            </div>
            <div class="field clearfix">
            	<label class="fl">验证码</label>
	                <input id="validate" name="validate" type="text" class="input_small fl mr5">
	                <img class="fl" name='validateimg' id='validateimg' src="validateNumber.action" onclick='refreshImg()' title="看不清，换一张" style="cursor:pointer"/>
            </div>
            <div class="saveCk">
            	<input name="autoLogin" id="autoLogin" type="checkbox" >
                <label>两周内自动登录</label>
            </div>
            <div class="subBox pt5">
              <input type="submit"  value="登 录" onClick="return loginSubmit();">
              <!--<input type="reset" name="Reset" id="button" value="取 消">-->
            </div>
        </div>
        <div class="cr t_c">Copyright ? 2008-2013 上海申通地铁集团有限公司 版权所有 All Rights Reserved.</div>
    </div>
    </form>
</body>
</html>

<script type="text/javascript">

function showErrorMsg(msg) {
	$("#errmsg").show();
	$("#errmsg").html("<font color='red'>"+ msg +"</font>");
}

//表单验证
function isvalid(){
	var name = $("#loginName").val();
	if(name == ''){
		showErrorMsg('请输入登录名！');
		$("#loginName").focus();
		return false;
	}
	
	var pwd = $("#password").val();
	if(pwd == ''){
		showErrorMsg('请输入密码！');
		$("#password").focus();
		return false;
	}
	var validate = $("#validate").val();
	if(validate == ''){
		showErrorMsg('请输入验证码！');
		$("#validate").focus();
		return false;
	}	
	
	var dept = $("#deptId").val();
	if(dept == ''){
		showErrorMsg('该账号未配置部门，请重新输入账号或联系信息中心！');
		return false;
	}
	$("#loginName").val(strTrim(name));
	return true;
}

<%
String errLogin = (String)request.getAttribute("errLogin");
if(errLogin == null || "".equals(errLogin))
	errLogin = request.getParameter("errLogin");

String errmsg = "", inputId = "", inputFlag = "";
if ("errValidate".equals(errLogin)) {
	errmsg = "注 意：验证码错误，请重新登录！";
} if("errLogin".equals(errLogin)){
	errmsg = "注 意：登录名或密码错误，请重新登录！";
}else if("error".equals(errLogin)){
	errmsg = "注 意：登录超时! 请您重新登录系统！";
}else if("errDept".equals(errLogin)){
	errmsg = "注 意：该用户未配置部门，请联系管理员！";
}

if (! "".equals(errmsg)) {
	out.println("showErrorMsg('"+ errmsg +"');");
	}
%>
</script>