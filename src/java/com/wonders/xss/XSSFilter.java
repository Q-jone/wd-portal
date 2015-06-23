/**   
 * @Title: XSSFilter.java 
 * @Package com.wonders.xss 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zhoushun   
 * @date 2014年7月2日 上午9:48:16 
 * @version V1.0   
 */
package com.wonders.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.HtmlUtils;

/**
 * @ClassName: XSSFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhoushun
 * @date 2014年7月2日 上午9:48:16
 * 
 */
public class XSSFilter implements Filter {

	@Override
	public void destroy() {
	}

	/**
	 * now the doFilter will filter the request ,using the Wrapper class to wrap
	 * the request and in the wrapper class, it will handle the XSS issue
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		XSSHttpServletRequestWrapper xssRequest = new XSSHttpServletRequestWrapper(
				(HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	 public static void main(String[] args) { 
	        String specialStr = "<div id=\"testDiv\">test1;test2</div>"; 
	        String str1 = HtmlUtils.htmlEscape(specialStr);// ①转换为HTML转义字符表示 
	        System.out.println(str1); 
	       
	        String str2 = HtmlUtils.htmlEscapeDecimal(specialStr);// ②转换为数据转义表示 
	        System.out.println(str2); 
	       
	        String str3 = HtmlUtils.htmlEscapeHex(specialStr); //③转换为十六进制数据转义表示 
	        System.out.println(str3); 
	       
	       // ④下面对转义后字符串进行反向操作 
	        System.out.println(HtmlUtils.htmlUnescape(str1)); 
	        System.out.println(HtmlUtils.htmlUnescape(str2)); 
	        System.out.println(HtmlUtils.htmlUnescape(str3)); 
	    } 
}