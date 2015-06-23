package com.wonders.stpt.core.login.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.login.entity.vo.DeptVo;
import com.wonders.stpt.core.login.entity.vo.LoginVo;
import com.wonders.stpt.core.login.service.TuserService;
import com.wonders.stpt.util.SpringBeanUtil;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.action.xwork.support.ServerValidationSupport;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.menu.service.MenuService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.exception.BadCredentialsException;
import com.wondersgroup.framework.security.service.AuthenticationService;
import com.wondersgroup.framework.security.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/")
@Scope("prototype")
@Action(value="login",results={
		@Result(name="success",location="/mainFrame/frame.html",type="redirect")
		,@Result(name="input",location="/login.jsp",type="dispatcher")
		,@Result(name="auth",location="/service/domainCrossRedirect.jsp",type="dispatcher")
		,@Result(name="logout",location="/logout.jsp",type="redirect")
	})

public class LoginAction extends BaseAjaxAction implements
		ServerValidationSupport {
	private String returnUrl;
	private String appName;
	private static String autoLoginKey; 
	private static long cookieMaxAge; 
	private static long cookieMaxAgeAuto;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登录用户VO对象
	 */
	private LoginVo loginVo = new LoginVo();

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
	
	public Map<String, String> getErrorFieldsInfo() {
		return errorMap;
	}

	static {
    	autoLoginKey = SpringBeanUtil.getProperties("classpath:appID.properties").getProperty("autoLoginKey").trim();
    	cookieMaxAgeAuto = Long.parseLong(SpringBeanUtil.getProperties("classpath:appID.properties").getProperty("cookieMaxAgeAuto").trim());
		cookieMaxAge = Long.parseLong(SpringBeanUtil.getProperties("classpath:appID.properties").getProperty("cookieMaxAge").trim());
	}
	
	/**
	 * 验证登陆是否有效
	 * 
	 * @see com.wondersgroup.framework.core.web.action.xwork.support.ServerValidationSupport#isValid()
	 */
	public boolean isValid() {
		HttpServletRequest request = super.getServletRequest();
		HttpServletResponse response = super.getServletResponse();
		// 验证码
		String validCode = request.getParameter("validate");
		if (validCode == null
				|| !validCode.equalsIgnoreCase((String) request.getSession()
						.getAttribute("rand"))) {
			// 无效验证码
			logger.info("验证码无效:" + validCode + " <> "
					+ request.getSession().getAttribute("rand"));
			request.setAttribute("errLogin", "errValidate");
			return false;
		}
		// end 验证码
		try {
			String loginName = loginVo.getLoginName();
			loginName = loginName.toUpperCase();
			
			try {
					List<Tuser> list = tuserService.authenticationTuser(loginName,loginVo.getPassword());
					if (list!=null && list.size()>0) {
						loginVo.setLoginName(loginName);
						return true;
					}
			} catch (Exception e) {
			}

			request.setAttribute("errLogin", "errLogin");
			return false;

		} catch (BadCredentialsException e) {
			if (BadCredentialsException.USER_NOT_EXISTED.equals(e.getMessage())) {
				errorMap.put("loginName", e.getMessage());
			}
			if (BadCredentialsException.PASSWORD_ERROR.equals(e.getMessage())) {

				errorMap.put("password", e.getMessage());
			}
			if (BadCredentialsException.USER_STATUS_UNNORMAL.equals(e
					.getMessage())) {
				errorMap.put("loginName", e.getMessage());
			}
		}
		super.getServletRequest().setAttribute("errLogin", "errLogin");
		return false;
	}

	/**
	 * @see com.wondersgroup.framework.core.web.action.xwork.AbstractAct
	 */

	@SuppressWarnings("unchecked")
	public String execute() throws UnsupportedEncodingException{
		HttpServletRequest request = super.getServletRequest();
		HttpServletResponse response = super.getServletResponse();
		if (!isValid()) {
			return INPUT;
		}
		String loginName = loginVo.getLoginName();
		String agent = "";
		List<Tuser> list = tuserService.authenticationTuser(loginName,loginVo.getPassword());
		String cloginName = loginVo.getDeptId().split(",")[0];
		String deptId = loginVo.getDeptId().split(",")[1];
		loginVo.setCloginName(cloginName);
		loginVo.setDeptId(deptId);
		// 将用户信息存入session中  T表					
		Tuser t = list.get(0);
		String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		t.setOperateTime(optTime);
		// 更新用户操作时间
		tuserService.updateTuser(t);
		loginVo.setTuser(t);
		
		request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName);
		request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, t);
		request.getSession().setAttribute(LoginConstant.USER_DEPT_LIST,this.putDeptsList(t));
		request.getSession().setAttribute(LoginConstant.USER_DEPT_MAP,this.putDeptsMap(t));
		//COOKIE
		CookieUtil.saveCookie(response, LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName, "/");
		
		logger.info("当前登录用户为:" + loginName);
			
		SecurityUser user = userService.getUserByLoginName(loginVo.getCloginName());
		if(loginName!=null&&cloginName!=null&&!cloginName.startsWith(loginName)){
			agent = "["+t.getName()+"代]";
			//System.out.println(agent+"dddddddddddddddddddddddddddd");
		}
		user = userService.loadUserWithLazyById(user.getId(),new String[]{"organNodes"});
		request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName());
	    request.getSession().setAttribute(LoginConstant.SECURITY_USER_ID, user.getId()+"");
	    request.getSession().setAttribute(LoginConstant.SECURITY_USER_NAME, user.getName()+agent);
	    request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_USER, user);
	    
	    //cookie
	    CookieUtil.saveCookie(response, LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName(), "/");
	    CookieUtil.saveCookie(response, LoginConstant.SECURITY_USER_ID, user.getId()+"", "/");
	    CookieUtil.saveCookie(response, LoginConstant.SECURITY_USER_NAME, user.getName()+agent, "/");
	    
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
				 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_ID, node.getId()+"", "/");
				 if(node.getDescription()!=null&&!"".equals(node.getDescription())){
					 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getDescription(), "/");
				 }else{
					 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getName(), "/");
				 }
				 
	        }
		 }
		
		DomainAuthentication domain = domainAuthenticationService.findDomainAuthenticationById(autoLoginKey);
		String autoLogin = request.getParameter("autoLogin");  
		if ("on".equals(autoLogin)) {  
            CookieUtil.saveCookie(loginVo, response,domain.getWebKey(),cookieMaxAgeAuto);  
        } else{
        	CookieUtil.saveCookie(loginVo, response,domain.getWebKey(),cookieMaxAge);  
        } 
		
		this.appName = request.getParameter("appName");
		this.returnUrl = request.getParameter("returnUrl");
		if(appName==null||"".equals(appName)||returnUrl==null||"".equals(returnUrl)){
			
		}else{
			return "auth";
		}
		return SUCCESS;
	
	}

	public List<DeptVo> putDeptsList(Tuser tuser){
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
		                deptMap.put(user.getLoginName()+","+node.getId(), user);
			        }
				 }
			 }
		}
		return deptMap;
	}
	
	@Action(value="showTuserDepts")
	public String showTuserDepts(){
		String loginName = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("loginName"));
		loginName = loginName.toUpperCase();
		String agent = "";
		List<Tuser> userlist = tuserService.authenticationTuserByLoginName(loginName);
		List<DeptVo> volist = new ArrayList<DeptVo>();
		if(userlist!=null&&userlist.size()>0){
			Tuser t = userlist.get(0);
			List<TuserRelation> relationList = t.getTuserRelation();
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
			                volist.add(vo);
				        }
					 }
				 }
			}
			
		}
		String json = VOUtils.getJsonDataFromCollection(volist);
		createJSonData(json);
		return AJAX;
	}
	
	@Action(value="switchDepts")
	public String switchDepts(){
		List<DeptVo> deptList = new ArrayList<DeptVo>();
		deptList = (List<DeptVo>) super.getServletRequest().getSession().getAttribute(LoginConstant.USER_DEPT_LIST);
		String json = VOUtils.getJsonDataFromCollection(deptList);
		createJSonData(json);
		return AJAX;
		
	}
	
	
	public String genRandom(){
		 Random random = new Random();
		 String sRand = "";
		    for (int i = 0; i < 10; i++) {
		        String rand = String.valueOf(random.nextInt(10));
		        sRand += rand;
		    }
		 return sRand;
	}
	
	
	@Override
	public LoginVo getModel() {
		// TODO Auto-generated method stub
		return loginVo;
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

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	
}
