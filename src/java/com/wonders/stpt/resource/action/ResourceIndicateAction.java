/**   
* @Title: ResourceIndicateAction.java 
* @Package com.wonders.stpt.resource.action 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午9:55:23 
* @version V1.0   
*/
package com.wonders.stpt.resource.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.resource.service.ResourceIndicateService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;
import com.wonders.stpt.validFile.action.AbstractParamAction;

/** 
 * @ClassName: ResourceIndicateAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午9:55:23 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/resourceIndicate")
@Controller("resourceIndicateAction")
@Scope("prototype")
public class ResourceIndicateAction extends AbstractParamAction{
	
	private ResourceIndicateService service;
	
	public ResourceIndicateService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("resourceIndicate")ResourceIndicateService service) {
		this.service = service;
	}

	private ActionWriter aw = new ActionWriter(response);
	
	@Action("getResourceIndicate")
	public String getResourceIndicate(){
		String lineNo = StringUtil.getNotNullValueString(this.request.getParameter("lineNo"));
		String date = StringUtil.getNotNullValueString(this.request.getParameter("date"));
		String dateSpan = StringUtil.getNotNullValueString(this.request.getParameter("dateSpan"));
		
		
		return null;
	}
	
}
