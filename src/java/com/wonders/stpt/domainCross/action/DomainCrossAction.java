package com.wonders.stpt.domainCross.action;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.wonders.stpt.core.cookie.util.CookieUtil;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wonders.stpt.core.domainCross.util.DomainCrossInfo;
import com.wonders.stpt.core.publicFun.util.PublicFunction;
import com.wonders.stpt.organTree.entity.vo.DefaultUserVo;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;

public class DomainCrossAction extends BaseAjaxAction {
	private DomainAuthenticationService domainAuthenticationService;
	
	public DomainAuthenticationService getDomainAuthenticationService() {
		return domainAuthenticationService;
	}

	public void setDomainAuthenticationService(
			DomainAuthenticationService domainAuthenticationService) {
		this.domainAuthenticationService = domainAuthenticationService;
	}

	public String getSessionInfo() {

		return null;
	}

	public String getDeptLeaders() {
		String callback = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("jsoncallback"));
		String deptUserId = super.getServletRequest().getParameter("deptUserId");
		String appName = super.getServletRequest().getParameter("appName");
		String token = super.getServletRequest().getParameter("token");
		String method = super.getServletRequest().getParameter("method");
		String sign = super.getServletRequest().getParameter("sign");
		String dataType = super.getServletRequest().getParameter("dataType");
		String result = "";
	
		if (appName != null && !"".equals(appName)&&token != null && !"".equals(token)
				&& sign != null && !"".equals(sign) && method!=null && !"".equals(method)) {
			// 本地验证
			DomainAuthentication domain = domainAuthenticationService.findDomainAuthenticationById(appName);
			String secret = domain.getSecret();
			if(secret==null||"".equals(secret)){
			}else{
				String signTmp = CookieUtil.getMD5(appName+token+method+domain.getSecret()+deptUserId);
				if (sign.equals(signTmp)) {			
					if(method!=null&&!"".equals(method)){
						if (logger.isInfoEnabled()) {
							logger.info("模块=[" +appName+ "] 中方法 =[" +method+ "] 正被POST请求，返回信息中===========");
						}
						result = DomainCrossInfo.getDeptLeaders(deptUserId, token, dataType);
					}
				} 
			}
		
		}
		result = callback+"("+result+")";
		Writer w = null;
		try {
			super.getServletResponse().setContentType("text/html");
			super.getServletResponse().setCharacterEncoding("UTF-8");
			w = super.getServletResponse().getWriter();
			w.write(result);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (w != null)
					w.flush();
				if (w != null)
					w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}
	
	public String test() {
		String callback = StringUtil.getNotNullValueString(super.getServletRequest().getParameter("jsoncallback"));
		String result = "";
		List<DefaultUserVo> list = new ArrayList<DefaultUserVo>();
		list = PublicFunction.findDeptLeadersByUserId("2855");
		for(DefaultUserVo vo:list){
			vo.setToken("1111111111");
		}
		result = VOUtils.getJsonDataFromCollection(list);
		//result = JSONArray.fromObject(list).toString();
		result = callback+"("+result+")";
		Writer w = null;
		try {
			super.getServletResponse().setContentType("text/html");
			super.getServletResponse().setCharacterEncoding("UTF-8");
			w = super.getServletResponse().getWriter();
			w.write(result);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (w != null)
					w.flush();
				if (w != null)
					w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}
}
