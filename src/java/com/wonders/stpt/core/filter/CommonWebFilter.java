package com.wonders.stpt.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wondersgroup.framework.common.Login;

public class CommonWebFilter implements Filter {
	protected final Log logger = LogFactory.getLog(getClass());
	
	protected final String P_NOT_LOGIN_PAGE = "notLoginPage";
	
	protected final String P_IGNORE_URLS = "ignoreUrls";
	
	protected final String URL_SPLITER = ",";
	
	private String notLoginPage = null;
	
	private String[] ignoreUrl = null;

	public void init(FilterConfig config) throws ServletException {
		notLoginPage = config.getInitParameter(P_NOT_LOGIN_PAGE);
		if (notLoginPage == null || "".equals(notLoginPage)) {
			String s = "未配置未登录跳转页，"+ this.getClass().getName() +"加载失败";
			logger.error(s);
			throw new ServletException(s);
		}
		String ignoreUrls = config.getInitParameter(P_IGNORE_URLS);
		if (ignoreUrls != null) {
			ignoreUrl = ignoreUrls.split(URL_SPLITER); 
		}
	}
	
	static String getCurrUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String ctxpath = request.getContextPath();
		if (! "".equals(ctxpath)) {
			return uri.substring(ctxpath.length());
		} else {
			return uri;
		}
	}
	

	/**
	 * 对是否登录做检查

	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = getCurrUrl(req);
		if (logger.isDebugEnabled()) {
			logger.debug("myurl=[" + url + "]");
		}

		// 排除跳转页和已配置的不需验证页面
		if (notLoginPage.equals(url)) {
			chain.doFilter(request, response);
			return ;
		}
		if (ignoreUrl != null && ignoreUrl.length > 0) {
			for (int i=0; i<ignoreUrl.length; i++) {
				if (logger.isDebugEnabled()) {
					logger.debug("ignoring url == [" + i + "][" + ignoreUrl[i] + "]");
				}
				if (url.equals(ignoreUrl[i].trim())) {
					chain.doFilter(request, response);
					return ;
				}
			}
		}

		if(url.startsWith("/exam/")){
			chain.doFilter(request, response);
			return;
		}
		
		if(url.startsWith("/infoSearch/")){
			chain.doFilter(request, response);
			return;
		}
		
		// 检查session
		String loginName = (String) req.getSession().getAttribute(LoginConstant.SECURITY_USER_ID);
		//System.out.println("-----------------------loginName"+loginName);
		if (loginName == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("未登录，退出");
			}
			//System.out.println("3"+getCurrUrl(req));
			RequestDispatcher disp = req.getRequestDispatcher(notLoginPage);
			disp.forward(request, response);
		} else {
			//System.out.println("4"+getCurrUrl(req));
			chain.doFilter(request, response);
		}
	}
	public void destroy() {

	}

}
