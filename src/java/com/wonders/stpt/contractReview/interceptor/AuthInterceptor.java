/**   
* @Title: AuthInterceptor.java 
* @Package com.wonders.stpt.contractReview.interceptor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年8月8日 下午3:25:49 
* @version V1.0   
*/
package com.wonders.stpt.contractReview.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wonders.stpt.core.login.constant.LoginConstant;

/** 
 * @ClassName: AuthInterceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年8月8日 下午3:25:49 
 *  
 */
public class AuthInterceptor extends AbstractInterceptor{

	/** 
	* @Title: intercept 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param arg0
	* @param @return
	* @param @throws Exception    设定文件 
	* @throws 
	*/
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		   // 取得请求相关的ActionContext实例  
        String result = "";
		ActionContext ctx = invocation.getInvocationContext();  
        Map session = ctx.getSession();  
        String user = (String) session.get(LoginConstant.STPT_SECURITY_LOGIN_NAME);  
        t("begin");
        
        if (user != null && "G00100000123".equals(user)) {  
            result = invocation.invoke();          
            System.out.print(invocation.getProxy().getMethod());
            System.out.print(invocation.getProxy().getActionName());
        }  else{
        	result = "error";
        }
        t("end");
        return result;  
	}

	private void t(String s){
		System.out.println(s);
	}
}
