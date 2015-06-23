/**
 * 
 */
package com.wonders.stpt.marquee.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.innerWork.action.AbstractParamAction;
import com.wonders.stpt.marquee.service.MarqueeService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: MarqueeAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月28日 下午1:58:40 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/marquee")
@Controller("marqueeAction")
@Scope("prototype")
public class MarqueeAction extends AbstractParamAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4787537184825647031L;

	private ActionWriter aw = new ActionWriter(response);
	private MarqueeService service;

	public MarqueeService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("marqueeService")MarqueeService service) {
		this.service = service;
	}
	
	
	@Action(value="getMarquee")
	public String getMarquee(){
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.request.getSession().getAttribute("cs_login_name"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.request.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		
		aw.writeJson(this.service.getMsg(loginNames+",'"+ologinName+"'"));
		return null;
	}
}
