/**
 * 
 */
package com.wonders.stpt.processInfo.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.wonders.stpt.processInfo.service.MsgInfoService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: TodoItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/msgInfo")
@Controller("msgInfoAction")
@Scope("prototype")
public class MsgInfoAction extends AbstractParamAction{

	private static final long serialVersionUID = 1L;	
	
	private MsgInfoService service;
	
	
	public MsgInfoService getService() {
		return service;
	}

	@Autowired(required=false)
	public void setService(@Qualifier("msgInfoService")MsgInfoService service) {
		this.service = service;
	}



	@Action(value="docSeMsgCount")
	public String docSeMsgCount(){
		
		String pname = StringUtil.getNotNullValueString(this.servletRequest.getParameter("modelNames"));
		String pincident = StringUtil.getNotNullValueString(this.servletRequest.getParameter("incidentNos"));
		String orders = StringUtil.getNotNullValueString(this.servletRequest.getParameter("orders"));

        ActionWriter aw = new ActionWriter(this.servletResponse);
      
        aw.writeJson(this.service.query(pname, pincident, orders));
        
		return null;
	}
	

}
