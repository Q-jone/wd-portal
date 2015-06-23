/**
 * 
 */
package com.wonders.stpt.core.cookie.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.login.entity.vo.DeptVo;
import com.wonders.stpt.core.login.entity.vo.LoginVo;
import com.wonders.stpt.core.login.service.TuserService;
import com.wonders.stpt.core.login.util.SHAEncode;
import com.wonders.stpt.util.SpringBeanUtil;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;

/** 
 * @ClassName: CookieUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-26 上午09:24:28 
 *  
 */
public class CookieUtil{
	
	private static UserService userService = null;
	private static TuserService tuserService = null;
	private static DomainAuthenticationService domainAuthenticationService = null;
	private static String autoLoginKey;  
    
	 // 保存cookie时的cookieName  
    private final static String AUTOCOOKIE = "stpt.autologin";  
    
    // 设置cookie有效期是两个星期，根据需要自定义  
    //private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;  
    
    static {
    	autoLoginKey = SpringBeanUtil.getProperties("classpath:appID.properties").getProperty("autoLoginKey").trim();
		init();
	}

    
    private static void init(){
    	if(userService == null){
    		userService = (UserService)SpringBeanUtil.getBean("userService");
    	}
    	if(tuserService == null){
    		tuserService = (TuserService)SpringBeanUtil.getBean("tuserService");
    	}
    	if(domainAuthenticationService == null){
    		domainAuthenticationService = (DomainAuthenticationService)SpringBeanUtil.getBean("domainAuthenticationService");
    	}
    }
    
    
    public static void saveCookie(LoginVo loginVo, HttpServletResponse response, String webKey,long cookieMaxAge) {  
        // cookie的有效期  
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);  
        // MD5加密用户详细信息  
        String cookieValueWithMd5 =getMD5(loginVo.getCloginName()+":" + SHAEncode.encodeInternal(loginVo.getPassword())+ ":" + validTime + ":" + webKey);  
        // 将要被保存的完整的Cookie值  
        String cookieValue = loginVo.getLoginName() + ":"+loginVo.getCloginName() + ":" +loginVo.getDeptId() + ":" + validTime + ":" + cookieValueWithMd5;  
        // 再一次对Cookie的值进行BASE64编码  
        String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));  
        // 开始保存Cookie  
        Cookie cookie = new Cookie(AUTOCOOKIE, cookieValueBase64);  
        // 存两年(这个值应该大于或等于validTime)  
        cookie.setMaxAge((int) cookieMaxAge);   
        // cookie有效路径是网站根目录  
        cookie.setPath("/");  
        // 向客户端写入  
        response.addCookie(cookie);  
 }  
  
 // 读取Cookie,自动完成登陆操作------------------  
    // 在Filter程序中调用该方法,见AutoLogonFilter.java  
    public static int readCookieAndLogon(HttpServletRequest request, HttpServletResponse response,FilterChain chain) throws IOException, ServletException,UnsupportedEncodingException{  
        // 根据cookieName取cookieValue  
        Cookie cookies[] = request.getCookies();  
        String cookieValue = null;  
        if(cookies!=null){  
            for(int i=0;i<cookies.length;i++){  
                if (AUTOCOOKIE.equals(cookies[i].getName())) {  
                    cookieValue = cookies[i].getValue();  
                    break;  
                }          
            }  
        }  
        // 如果cookieValue为空,返回,  
        if(cookieValue==null){  
            return 0;  
        }  
        // 如果cookieValue不为空,才执行下面的代码  
        // 先得到的CookieValue进行Base64解码  
        String cookieValueAfterDecode = new String (Base64.decode(cookieValue),"utf-8");   
        // 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆  
        String cookieValues[] = cookieValueAfterDecode.split(":");  
        if(cookieValues.length!=5){  
           // System.out.println("你正在用非正常方式进入本站...");  
            return 0;  
        }  
        // 判断是否在有效期内,过期就删除Cookie  
        long validTimeInCookie = new Long(cookieValues[3]);  
        if(validTimeInCookie < System.currentTimeMillis()){  
            // 删除Cookie  
            clearCookie(response);  
           // System.out.println("<a href=’logon.jsp’>你的Cookie已经失效,请重新登陆</a>");  
            return 0;  
        }  
        // 取出cookie中的用户名,并到数据库中检查这个用户名,  
        
        DomainAuthentication domain = domainAuthenticationService.findDomainAuthenticationById(autoLoginKey);
        SecurityUser user = userService.getUserByLoginName(cookieValues[1]);
    	String password = "";
        List<Tuser> list = tuserService.authenticationTuserByLoginName(cookieValues[0]);
		if (list!=null && list.size()>0) {
			Tuser t = list.get(0);
			password = t.getPassword();
		}
        // 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密  
        if(user!=null){  
            String md5ValueInCookie = cookieValues[4];  
            String md5ValueFromUser =getMD5(user.getLoginName() + ":" + password + ":" + validTimeInCookie + ":" + domain.getWebKey());  
            // 将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求  
            if(md5ValueFromUser.equals(md5ValueInCookie)){  
            	sessionAdd(request,response,cookieValues[0],cookieValues[1],cookieValues[2]);
               	return 1;
            }  
         }else{  
             // 返回为空执行  
        	// System.out.println("cookie验证错误！");  
             return 0;  

         }  
        return 0;
    }  
    
    
    // 用户注销时,清除Cookie,在需要时可随时调用------------------------------------------------------------  
    public static void clearCookie( HttpServletResponse response){  
        Cookie cookie = new Cookie(AUTOCOOKIE, null);  
        cookie.setMaxAge(0);  
        cookie.setPath("/");  
        response.addCookie(cookie);  
    }  
    
 // 获取Cookie组合字符串的MD5码的字符串----------------------------------------------------------------------------  
    public static String getMD5(String value) {  
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
    private static String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    }  
    
    public static void sessionAdd(HttpServletRequest request,HttpServletResponse response,String loginName,String cloginName,String deptId)
    	throws UnsupportedEncodingException{
    	String agent = "";
    	List<Tuser> list = tuserService.authenticationTuserByLoginName(loginName);
		if (list!=null && list.size()>0) {
			Tuser t = list.get(0);
			String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			t.setOperateTime(optTime);
			// 更新用户操作时间
			tuserService.updateTuser(t);
			// 将用户信息存入session中  T表
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName);
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, t);
			request.getSession().setAttribute(LoginConstant.USER_DEPT_LIST,putDeptsList(t));
			request.getSession().setAttribute(LoginConstant.USER_DEPT_MAP,putDeptsMap(t));

            //trackStatus
            request.getSession().setAttribute(LoginConstant.USER_TRACK_STATUS,t.getTrackStatus());
            saveCookie(response, LoginConstant.USER_TRACK_STATUS, t.getTrackStatus(), "/");

			//COOKIE
			saveCookie(response, LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName, "/");
			SecurityUser user = userService.getUserByLoginName(cloginName);
			if(loginName!=null&&cloginName!=null&&!cloginName.startsWith(loginName)){
				agent = "["+t.getName()+"代]";
			}
			user = userService.loadUserWithLazyById(user.getId(),new String[]{"organNodes"});
			request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName());
			//request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID, cloginName.replace(deptId,""));
		    request.getSession().setAttribute(LoginConstant.SECURITY_USER_ID, user.getId()+"");
		    request.getSession().setAttribute(LoginConstant.SECURITY_USER_NAME, user.getName()+agent);
		    request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_USER, user);
		    
		    //cookie
		    saveCookie(response, LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName(), "/");
		    saveCookie(response, LoginConstant.SECURITY_USER_ID, user.getId()+"", "/");
		    saveCookie(response, LoginConstant.SECURITY_USER_NAME, user.getName()+agent, "/");
		    
			Set<OrganNode> nodes = user.getOrganNodes();
			 if (nodes != null && nodes.size() > 0) {
				 for(OrganNode node:nodes){
					 request.getSession().setAttribute(LoginConstant.USER_DEPT_ID, node.getId()+"");
					 if(node.getDescription()!=null&&!"".equals(node.getDescription())){
						 request.getSession().setAttribute(LoginConstant.USER_DEPT_NAME, node.getDescription());
					 }else{
						 request.getSession().setAttribute(LoginConstant.USER_DEPT_NAME, node.getName());
					 }
					 //cookie
					 saveCookie(response, LoginConstant.USER_DEPT_ID, node.getId()+"", "/");
					 if(node.getDescription()!=null&&!"".equals(node.getDescription())){
						 saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getDescription(), "/");
					 }else{
						 saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getName(), "/");
					 }
		        }
			 }
		}
		
		
		 
    }
    
    public static List<DeptVo> putDeptsList(Tuser tuser){
		List<DeptVo> deptList = new ArrayList<DeptVo>();
		//Map<String,SecurityUser> deptMap = new HashMap<String,SecurityUser>();
		List<TuserRelation> relationList = tuser.getTuserRelation();
		String agent = "";
		if(relationList!=null&&relationList.size()>0){
			for(TuserRelation u : relationList){
				SecurityUser user = userService.loadUserWithLazyById(u.getCid(),new String[]{"organNodes"});
				if("1".equals(u.getAgent())){
					agent = "["+user.getName()+"]";
				}else{
					agent = "";
				}
				Set<OrganNode> nodes = user.getOrganNodes();
				 if (nodes != null && nodes.size() > 0) {
					 for(OrganNode node:nodes){
		                DeptVo vo = new DeptVo();
		                if(node.getDescription()!=null&&!"".equals(node.getDescription())){
		                	vo.setDeptName(agent+node.getDescription());
		                }else{
		                	vo.setDeptName(agent+node.getName());
		                }
		                vo.setUserId(user.getId()+"");
		                vo.setDeptId(user.getLoginName()+","+node.getId());
		                deptList.add(vo);
		                //deptMap.put(node.getId()+"", user);
			        }
				 }
			 }
		}
		return deptList;
	}
	
	public static Map<String,SecurityUser> putDeptsMap(Tuser tuser){
		//List<DeptVo> deptList = new ArrayList<DeptVo>();
		Map<String,SecurityUser> deptMap = new HashMap<String,SecurityUser>();
		List<TuserRelation> relationList = tuser.getTuserRelation();
		if(relationList!=null&&relationList.size()>0){
			for(TuserRelation u : relationList){
				SecurityUser user = userService.loadUserWithLazyById(u.getCid(),new String[]{"organNodes"});
				Set<OrganNode> nodes = user.getOrganNodes();
				 if (nodes != null && nodes.size() > 0) {
					 for(OrganNode node:nodes){
		                deptMap.put(user.getLoginName()+","+node.getId(), user);
			        }
				 }
			 }
		}
		return deptMap;
	}
	
	public static void saveCookie(HttpServletResponse response, String name,
			String value, String path) throws UnsupportedEncodingException {
		if (value == null) {
			return;
		} else {
			Cookie cookie = new Cookie(name, java.net.URLEncoder.encode(value,"utf-8"));
			cookie.setPath(path);
			response.addCookie(cookie);
			return;
		}
	}

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

	public static UserService getUserService() {
		return userService;
	}


	public static void setUserService(UserService userService) {
		CookieUtil.userService = userService;
	}


	public static TuserService getTuserService() {
		return tuserService;
	}


	public static void setTuserService(TuserService tuserService) {
		CookieUtil.tuserService = tuserService;
	}


	public static DomainAuthenticationService getDomainAuthenticationService() {
		return domainAuthenticationService;
	}


	public static void setDomainAuthenticationService(
			DomainAuthenticationService domainAuthenticationService) {
		CookieUtil.domainAuthenticationService = domainAuthenticationService;
	}
	
	
}
