package com.wonders.stpt.core.domainCross.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.bo.TuserToken;
import com.wonders.stpt.core.login.service.TuserTokenService;
import com.wonders.stpt.util.SpringBeanUtil;


public class DomainCrossUtil {
	protected final static Log logger = LogFactory.getLog(DomainCrossUtil.class);
	private static DomainAuthenticationService domainAuthenticationService = null;
	private static TuserTokenService tuserTokenService = null;

	static {
		init();
	}

	private static void init() {
		if (domainAuthenticationService == null) {
			domainAuthenticationService = (DomainAuthenticationService) SpringBeanUtil
					.getBean("domainAuthenticationService");
		}
		if (tuserTokenService == null) {
			tuserTokenService = (TuserTokenService) SpringBeanUtil
					.getBean("tuserTokenService");
		}
	}
	
	public static String domainCrossAuthLogin(HttpServletRequest request,HttpServletResponse response) {
		String appName = request.getParameter("appName");
		if(appName==null||"".equals(appName)){
			return "error";
		}
		String callBackUrl = "";
		String returnUrl = request.getParameter("returnUrl");
		// 模块名
		DomainAuthentication domain = domainAuthenticationService.findDomainAuthenticationById(appName);
		if (logger.isInfoEnabled()) {
			logger.info("模块=[" +appName+ "] 跨域认证中==========================");
		}
		// 回调URL
		if(domain!=null){
			callBackUrl = domain.getCallBackUrl();
			if (returnUrl == null || "".equals(returnUrl)) {
				returnUrl = domain.getDefaultUrl();
			}
			TuserToken token = new TuserToken();
			token.setUserId((String) request.getSession().getAttribute(LoginConstant.SECURITY_USER_ID));
			tuserTokenService.addTuserToken(token);
			
			return callBackUrl+"?"+"token="+token.getId()+"&returnUrl="+returnUrl;
		}
		return "error";
	}

	public static String domainParamCallback(HttpServletRequest request,HttpServletResponse response) throws ParseException {
		String id = request.getParameter("id");
		String method = request.getParameter("method");
		if("getSession".equals(method)){
			return domainParamUserInfo(request,response);
		}else{
			return domainParamGetInfo(request,response,id);
		}
		
	}
	
	//获取用户信息
	public static String domainParamUserInfo(HttpServletRequest request,HttpServletResponse response) throws ParseException {
		// 加密解密
		String appName = request.getParameter("appName");
		String token = request.getParameter("token");
		String method = request.getParameter("method");
		String sign = request.getParameter("sign");
		String dataType = request.getParameter("dataType");
		String result = "";
	
		if (appName != null && !"".equals(appName)&&token != null && !"".equals(token)
				&& sign != null && !"".equals(sign) && method!=null && !"".equals(method)) {
			// 本地验证
			DomainAuthentication domain = domainAuthenticationService.findDomainAuthenticationById(appName);
			String userId = "";
			List<TuserToken> userTokenList = tuserTokenService.findTuserTokenByIdRemoved(token);
			TuserToken userToken = null;
			if(userTokenList != null && userTokenList.size()>0){
				userToken = userTokenList.get(0);
				userId = userToken.getUserId();
			}else{
				return "error";
			}
			if(userId==null||"".equals(userId)){
				return "error";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long nowTime = new Date().getTime();
			long authTime = sdf.parse(userToken.getCreateTime()).getTime();
			long stamp = (nowTime-authTime)/60000;
			if(stamp>5){
				userToken.setRemoved("1");
				tuserTokenService.updateTuserToken(userToken);
				return "error";
			}
			String secret = domain.getSecret();
			if(secret==null||"".equals(secret)){
				return "error";
			}
			String signTmp = CookieUtil.getMD5(appName+token+method+domain.getSecret());
			if (sign.equals(signTmp)) {			
				if(method!=null&&!"".equals(method)){
					if (logger.isInfoEnabled()) {
						logger.info("模块=[" +appName+ "] 中方法 =[" +method+ "] 正被POST请求，返回信息中===========");
					}
					result = methodInvoke(method,userId,userToken.getId(),dataType);
				}
			} else {
				return "error";
			}
		} else {
			return "error";
		}

		
		return result;
	}

	//http://10.1.41.252:8088/portal/service/domainCrossParam.jsp?id=2601&method=getRelatedNodes&dataType=json&token=8a818e9437ebd6300137f07c4cea0004
	//根据方法名获取相关信息
	public static String domainParamGetInfo(HttpServletRequest request,HttpServletResponse response,String id) throws ParseException {
		// 加密解密
		String token = request.getParameter("token");
		String method = request.getParameter("method");
		String dataType = request.getParameter("dataType");
		String result = "";
	
		if (token != null && !"".equals(token)
				&& method!=null && !"".equals(method)) {
			// 本地验证
			List<TuserToken> userTokenList = tuserTokenService.findTuserTokenByIdRemoved(token);
			if(userTokenList != null && userTokenList.size()>0){		
				if(method!=null&&!"".equals(method)){
					if (logger.isInfoEnabled()) {
						logger.info("方法 =[" +method+ "] 正被POST请求，返回信息中===========");
					}
					result = methodInvoke(method,id,token,dataType);
				}
			} else {
				return "error";
			}
		} else {
			return "error";
		}

		
		return result;
	}
	
	//统一方法调用
	public static String methodInvoke(String methodName,String arg1,String arg2,String arg3){
		Class<?> cls = DomainCrossInfo.class;
		 Method[] methods = cls.getDeclaredMethods();  
		 String methodsName = "";
		 String result = "error";
		 for (Method method : methods) { 
			 methodsName += method.getName() +",";
		 }
		 if(methodsName.indexOf(methodName)>=0){
			   //返回方法名为“testMethod”的一个 Method 对象，后面跟的是该方法参数
			   Method method;
			try {
				method = cls.getMethod(methodName,new Class[] { String.class, String.class,String.class });
				result = (String) method.invoke(null, new Object[]{arg1,arg2,arg3});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			   
		 }else{
			 return result;
		 }
		 
		 return result;
	}
	
	//通知app用户信息变更
	public static void appInform(HttpServletResponse response,String appUrl){
		String urls = appUrl;
		URL url = null;
		HttpURLConnection http = null;
		try {
			url = new URL(urls);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.connect(); 
			String param = "zs=1111111111111";
			http.getOutputStream().write(param.getBytes());    
			http.getOutputStream().flush();
			http.getOutputStream().close();
			//System.out.println("getResponseCode====="+http.getResponseCode());
			if (http.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
			http.getInputStream()));

			in.close();
			}
		} catch (Exception e) {
			System.out.println("err");
		} finally {
		if (http != null)
			http.disconnect();
		}
	}

}
