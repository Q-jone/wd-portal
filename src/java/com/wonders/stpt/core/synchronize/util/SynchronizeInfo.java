package com.wonders.stpt.core.synchronize.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.login.entity.vo.DeptVo;
import com.wonders.stpt.core.login.service.TuserService;
import com.wonders.stpt.util.SpringBeanUtil;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;

public class SynchronizeInfo {
	private static UserService userService = null;
	private static TuserService tuserService = null;
	
	
	 static {
			init();
		}

	    
    private static void init(){
    	if(userService == null){
    		userService = (UserService)SpringBeanUtil.getBean("userService");
    	}
    	if(tuserService == null){
    		tuserService = (TuserService)SpringBeanUtil.getBean("tuserService");
    	}
    }
	    
	public static void setSession(HttpServletRequest request,HttpServletResponse response,String loginName)
  	throws UnsupportedEncodingException{
		List<Tuser> list = tuserService.authenticationTuserByLoginName(loginName);
		String deptId = "";
		boolean flag = false;
		if (list!=null && list.size()>0) {
			Tuser u = list.get(0);
			List<TuserRelation> r = u.getTuserRelation();
			if(r!=null && r.size()>0){
				flag = true;
			}
		}
		if (list!=null && list.size()>0 && flag) {
			Tuser t = list.get(0);
			deptId = findMainDeptId(t);
			if("".equals(deptId)||deptId==null){
				return;
			}
			String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			t.setOperateTime(optTime);
			// 更新用户操作时间
			tuserService.updateTuser(t);
			// 将用户信息存入session中  T表
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName);
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, t);
			request.getSession().setAttribute(LoginConstant.USER_DEPT_LIST,CookieUtil.putDeptsList(t));
			request.getSession().setAttribute(LoginConstant.USER_DEPT_MAP,CookieUtil.putDeptsMap(t));
			//COOKIE
			CookieUtil.saveCookie(response, LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName, "/");
		}else{
			loginName = "G0000000TEST";
			Tuser t = tuserService.authenticationTuserByLoginName(loginName).get(0);
			deptId = findMainDeptId(t);
			if("".equals(deptId)||deptId==null){
				return;
			}
			String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			t.setOperateTime(optTime);
			// 更新用户操作时间
			tuserService.updateTuser(t);
			// 将用户信息存入session中  T表
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName);
			request.getSession().setAttribute(LoginConstant.STPT_SECURITY_LOGIN_USER, t);
			request.getSession().setAttribute(LoginConstant.USER_DEPT_LIST,CookieUtil.putDeptsList(t));
			request.getSession().setAttribute(LoginConstant.USER_DEPT_MAP,CookieUtil.putDeptsMap(t));
			//COOKIE
			CookieUtil.saveCookie(response, LoginConstant.STPT_SECURITY_LOGIN_NAME, loginName, "/");
		}
		
		SecurityUser user = userService.getUserByLoginName(loginName+deptId);
		user = userService.loadUserWithLazyById(user.getId(),new String[]{"organNodes"});
		request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName());
	    request.getSession().setAttribute(LoginConstant.SECURITY_USER_ID, user.getId()+"");
	    request.getSession().setAttribute(LoginConstant.SECURITY_USER_NAME, user.getName());
	    request.getSession().setAttribute(LoginConstant.SECURITY_LOGIN_USER, user);
	    //cookie
	    CookieUtil.saveCookie(response, LoginConstant.SECURITY_LOGIN_NAME, user.getLoginName(), "/");
	    CookieUtil.saveCookie(response, LoginConstant.SECURITY_USER_ID, user.getId()+"", "/");
	    CookieUtil.saveCookie(response, LoginConstant.SECURITY_USER_NAME, user.getName(), "/");
	    
		Set<OrganNode> nodes = user.getOrganNodes();
		 if (nodes != null && nodes.size() > 0) {
			 for(OrganNode node:nodes){
				 request.getSession().setAttribute(LoginConstant.USER_DEPT_ID, node.getId()+"");
				 request.getSession().setAttribute(LoginConstant.USER_DEPT_NAME, node.getName());
				 //cookie
				 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_ID, node.getId()+"", "/");
				 CookieUtil.saveCookie(response, LoginConstant.USER_DEPT_NAME, node.getName(), "/");
	        }
		 }
		 
  }
	
	
	public static String findMainDeptId(Tuser tuser){
		String deptId = "";
		//List<DeptVo> deptList = new ArrayList<DeptVo>();
		//Map<String,SecurityUser> deptMap = new HashMap<String,SecurityUser>();
		List<TuserRelation> relationList = tuser.getTuserRelation();
		if(relationList!=null&&relationList.size()==1){
			for(TuserRelation u : relationList){
				SecurityUser user = userService.loadUserWithLazyById(u.getCid(),new String[]{"organNodes"});
				Set<OrganNode> nodes = user.getOrganNodes();
				 if (nodes != null && nodes.size() > 0) {
					 for(OrganNode node:nodes){
		               deptId = node.getId()+"";
			        }
				 }
			 }
		}else if(relationList!=null&&relationList.size()>1){
			for(TuserRelation u : relationList){
				if(!"".equals(deptId)){
					break;
				}
				if("1".equals(u.getMainDept())){
					SecurityUser user = userService.loadUserWithLazyById(u.getCid(),new String[]{"organNodes"});
					Set<OrganNode> nodes = user.getOrganNodes();
					 if (nodes != null && nodes.size() > 0) {
						 for(OrganNode node:nodes){
			               deptId = node.getId()+"";
				        }
					 }
				}
			}
			if("".equals(deptId)){
				SecurityUser user = userService.loadUserWithLazyById(relationList.get(0).getCid(),new String[]{"organNodes"});
				Set<OrganNode> nodes = user.getOrganNodes();
				if (nodes != null && nodes.size() > 0) {
					 for(OrganNode node:nodes){
		               deptId = node.getId()+"";
			        }
				 }
			}
		}
		
		return deptId;
	}
}
