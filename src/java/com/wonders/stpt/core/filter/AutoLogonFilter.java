package com.wonders.stpt.core.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.util.SpringBeanUtil;
import com.wondersgroup.framework.common.Login;

public class AutoLogonFilter implements Filter {
	protected final Log logger = LogFactory.getLog(getClass());
protected final String P_IGNORE_URLS = "ignoreUrls";
	
	protected final String URL_SPLITER = ",";
	
	private String[] ignoreUrl = null;
	private final static String AUTOCOOKIE = "stpt.autologin";  

	public void init(FilterConfig config) throws ServletException {
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
		HttpServletResponse rep = (HttpServletResponse) response;
		String url = getCurrUrl(req);
		if (logger.isDebugEnabled()) {
			logger.debug("myurl=[" + url + "]");
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
		
		String caCross = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("caCross").trim();
		if(!"0".equals(caCross)){
			//ca	
		    String token=CookieUtil.getCookieByName(req,"token");
		    if(token==null){
		    	String returnUrl=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+ req.getRequestURI();
				if(req.getQueryString()!=null){   
					//连接中中文已编码  encodeURI encodeURIComponent
					//queryString  获取连接中若有中文，中文仍为编码后UTF8码，而getParameter为中文
					returnUrl+="?"+req.getQueryString();
					//对? & 编码 避免参数截取错误
					returnUrl = returnUrl.replace("&","%26").replace("?","%3F").replace("/","%2F");		
				}
		    	rep.sendRedirect(req.getContextPath()+"/caClient.jsp?returnUrl="+returnUrl);
		    	return;
		    }else{
		    	chain.doFilter(request, response);
		    	return;
		    }
		}else{
			//portal
			String loginName = (String) req.getSession().getAttribute(LoginConstant.SECURITY_USER_ID);
			if(loginName!=null){
				 chain.doFilter(request,response);  
		         return;
			}
			
			// user为空，说明用户还没有登陆,就尝试得到浏览器传送过来的Cookie  
	        Cookie cookies[] = req.getCookies();  
	        String cookieValue = null;  
	        if(cookies!=null){  
	            for(int i=0;i<cookies.length;i++){  
	                if (AUTOCOOKIE.equals(cookies[i].getName())) {  
	                    cookieValue = cookies[i].getValue();  
	                    break;  
	                }  
	            }  
	        }  
	        // 如果cookieValue为空,也继续执行用户请求  
	        if(cookieValue==null){  
	        	//System.out.println("0"+getCurrUrl(req));
	            chain.doFilter(request,response);  
	            return;  
	        }else{
	        	int r = 0;
	        	r = CookieUtil.readCookieAndLogon(req, rep, chain);  
	        	if(r==0){
	        		CookieUtil.clearCookie(rep);
	        	}
	            chain.doFilter(request,response);  
	            return;
	        } 
		}
		

	}
	    

	public void destroy() {

	}

}
