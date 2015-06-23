package com.wonders.stpt.core.synchronize.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wonders.stpt.core.domainCross.util.DomainCrossUtil;
import com.wonders.stpt.util.SpringBeanUtil;

public class SynchronizeUtil {
	protected final static Log logger = LogFactory.getLog(SynchronizeUtil.class);
	private static DomainAuthenticationService domainAuthenticationService = null;

	static {
		init();
	}

	private static void init() {
		if (domainAuthenticationService == null) {
			domainAuthenticationService = (DomainAuthenticationService) SpringBeanUtil
					.getBean("domainAuthenticationService");
		}
	}
	
	public static void synchronizeSession(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 加密解密
		String appName = request.getParameter("appName");
		String token = request.getParameter("token");
		String method = request.getParameter("method");
		String sign = request.getParameter("sign");
		String loginName = request.getParameter("loginName");
		if (logger.isInfoEnabled()) {
			logger.info("用户=[" +loginName+ "] 正在同步信息==========================");
		}
		SynchronizeInfo.setSession(request, response, loginName);
		
		if (loginName != null && !"".equals(loginName) && appName != null && !"".equals(appName)&&token != null && !"".equals(token)
				&& sign != null && !"".equals(sign) && method!=null && !"".equals(method)) {
			// 本地验证
			DomainAuthentication domain = domainAuthenticationService.findDomainAuthenticationById(appName);
			String secret = domain.getSecret();
			if(secret==null||"".equals(secret)){
				return ;
			}
			String signTmp = CookieUtil.getMD5(appName+token+method+domain.getSecret());
			if (sign.equals(signTmp)) {			
				if(method!=null&&!"".equals(method)&&"setSession".equals(method)){
					if (logger.isInfoEnabled()) {
						logger.info("用户=[" +loginName+ "] 正在同步信息==========================");
					}
					SynchronizeInfo.setSession(request, response, loginName);
				}
			} else {
				return ;
			}
		} else {
			return ;
		}

	}
}
