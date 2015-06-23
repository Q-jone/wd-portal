package com.wonders.stpt.core.login.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wonders.stpt.core.domainCross.util.DomainCrossUtil;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.login.entity.vo.DeptVo;
import com.wonders.stpt.core.login.entity.vo.LoginVo;
import com.wonders.stpt.core.login.service.TuserService;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.menu.service.MenuService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.AuthenticationService;
import com.wondersgroup.framework.security.service.UserService;

@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/")
@Action(value="xlogin",results={
		@Result(name="success",location="/mainFrame/frame.html",type="redirect")
		,@Result(name="input",location="/login.jsp",type="dispatcher")
		,@Result(name="cross",location="/service/domainCrossRedirect.jsp",type="dispatcher")
	})
public class TesterLoginAction extends BaseAjaxAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登陆错误信息表
	 */
	private final Map<String, String> errorMap = new HashMap<String, String>(1);

	/**
	 * T用户验证接口
	 */
	private TuserService tuserService;

	/**
	 * 组织树服务类
	 */
	private OrganTreeService organTreeService;
	/**
	 * 组织服务接口
	 */
	private OrganNodeService organNodeService;
	/**
	 * 用户验证接口
	 */
	private AuthenticationService authenticationService;

	/**
	 * 用户服务接口
	 */
	private UserService userService;

	/*认证表*/
	private DomainAuthenticationService domainAuthenticationService;
	
	@SuppressWarnings("unchecked")
	
	public String execute() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getServletRequest();
		HttpServletResponse response = super.getServletResponse();
		String loginName = (String)request.getParameter("loginName");
		String password = (String)request.getParameter("password");
		String deptId = "";
		if(loginName==null||password==null||!"wd-st-tester-2012".equals(password)){
			return INPUT;
		}
		loginName = loginName.toUpperCase();
		List<Tuser> list = tuserService.authenticationTuserByLoginName(loginName);
		if (list!=null && list.size()>0) {
			Tuser t = list.get(0);
			// 将用户信息存入session中  T表
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName);
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, list.get(0));
			request.getSession().setAttribute(LoginConstant.USER_DEPT_LIST,this.putDeptsList(t));
			request.getSession().setAttribute(LoginConstant.USER_DEPT_MAP,this.putDeptsMap(t));
			
			//COOKIE
			saveCookie(response, LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName, "/");
			
			List<TuserRelation> relationList = t.getTuserRelation();
			if(relationList!=null&&relationList.size()>0){
					SecurityUser user = userService.loadUserWithLazyById(relationList.get(0).getCid(),new String[]{"organNodes"});
					Set<OrganNode> nodes = user.getOrganNodes();
					 if (nodes != null && nodes.size() > 0) {
						 for(OrganNode node:nodes){
							 deptId = node.getId()+"";
							 break;
						 }
				       }else{
				    	   request.setAttribute("errLogin", "errDept");
				    	   return INPUT;
				       }
			}else{
				return INPUT;
			}
			SecurityUser user = userService.getUserByLoginName(loginName+deptId);
			user = userService.loadUserWithLazyById(user.getId(),new String[]{"organNodes"});
			request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName());
			
		    request.getSession().setAttribute(LoginConstant.SECURITY_USER_ID, user.getId()+"");
		    request.getSession().setAttribute(LoginConstant.SECURITY_USER_NAME, user.getName());
		    request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_USER, user);
		    
		    
		    //cookie
		    saveCookie(response, LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName(), "/");
		    saveCookie(response, LoginConstant.SECURITY_USER_ID, user.getId()+"", "/");
		    saveCookie(response, LoginConstant.SECURITY_USER_NAME, user.getName(), "/");
		    
			Set<OrganNode> nodes = user.getOrganNodes();
			 if (nodes != null && nodes.size() > 0) {
				 for(OrganNode node:nodes){
					 request.getSession().setAttribute(LoginConstant.USER_DEPT_ID, node.getId()+"");
					 request.getSession().setAttribute(LoginConstant.USER_DEPT_NAME, node.getName());
					 //cookie
					 saveCookie(response, LoginConstant.USER_DEPT_ID, node.getId()+"", "/");
					 saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getName(), "/");
		        }
			 }else{
				 request.setAttribute("errLogin", "errDept");
		    		   return INPUT;
			 }
			}else{
				 return INPUT;
			}
		String appName = request.getParameter("appName");
		if(appName!=null&&!"".equals(appName)){	
			return "cross";
		}
		
		
		return SUCCESS;
	
	}

	public List<DeptVo> putDeptsList(Tuser tuser){
		List<DeptVo> deptList = new ArrayList<DeptVo>();
		//Map<String,SecurityUser> deptMap = new HashMap<String,SecurityUser>();
		List<TuserRelation> relationList = tuser.getTuserRelation();
		if(relationList!=null&&relationList.size()>0){
			for(TuserRelation u : relationList){
				SecurityUser user = userService.loadUserWithLazyById(u.getCid(),new String[]{"organNodes"});
				Set<OrganNode> nodes = user.getOrganNodes();
				 if (nodes != null && nodes.size() > 0) {
					 for(OrganNode node:nodes){
		                DeptVo vo = new DeptVo();
		                vo.setDeptName(node.getName());
		                vo.setUserId(user.getId()+"");
		                vo.setDeptId(node.getId()+"");
		                deptList.add(vo);
		                //deptMap.put(node.getId()+"", user);
			        }
				 }
			 }
		}
		return deptList;
	}
	
	public Map<String,SecurityUser> putDeptsMap(Tuser tuser){
		//List<DeptVo> deptList = new ArrayList<DeptVo>();
		Map<String,SecurityUser> deptMap = new HashMap<String,SecurityUser>();
		List<TuserRelation> relationList = tuser.getTuserRelation();
		if(relationList!=null&&relationList.size()>0){
			for(TuserRelation u : relationList){
				SecurityUser user = userService.loadUserWithLazyById(u.getCid(),new String[]{"organNodes"});
				Set<OrganNode> nodes = user.getOrganNodes();
				 if (nodes != null && nodes.size() > 0) {
					 for(OrganNode node:nodes){
		                deptMap.put(node.getId()+"", user);
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

	public static String readCookie(HttpServletRequest request,String cookieName) throws UnsupportedEncodingException{
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(int i=0;i<cookies.length;i++){
				Cookie cookie = cookies[i];
				if(cookieName.equals(cookie.getName())){
					String value = cookie.getValue();
					String tmp = java.net.URLDecoder.decode(value,"utf-8");
					return tmp;
				}
			}
		}
		
		return "";
	}
	
	
	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	
	public TuserService getTuserService() {
		return tuserService;
	}

	public void setTuserService(TuserService tuserService) {
		this.tuserService = tuserService;
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	
	public OrganTreeService getOrganTreeService() {
		return organTreeService;
	}

	public OrganNodeService getOrganNodeService() {
		return organNodeService;
	}

	public void setMenuService(MenuService menuService) {
		
	}

	public void setOrganNodeService(OrganNodeService organNodeService) {
		this.organNodeService = organNodeService;
	}

	public void setOrganTreeService(OrganTreeService organTreeService) {
		this.organTreeService = organTreeService;
	}

	public DomainAuthenticationService getDomainAuthenticationService() {
		return domainAuthenticationService;
	}

	public void setDomainAuthenticationService(
			DomainAuthenticationService domainAuthenticationService) {
		this.domainAuthenticationService = domainAuthenticationService;
	}

}
