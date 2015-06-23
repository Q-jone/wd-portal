/**
 * 
 */
package com.wonders.stpt.todoItem.action;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.todoItem.model.bo.TodoItem;
import com.wonders.stpt.todoItem.model.vo.TodoStatInfo;
import com.wonders.stpt.todoItem.service.TodoItemService;
import com.wonders.stpt.todoItem.util.ConfigUtil;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: TodoItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/todoNew")
@Controller("newTodoItemAction")
@Scope("prototype")
public class NewTodoItemAction extends AbstractParamAction{

	private static final String workflowControllerIp = ConfigUtil.getValueByKey("config.properties", "workflowControllerIp");
	
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	
	
	private TodoItemService service;
	
	
	public TodoItemService getService() {
		return service;
	}

	@Autowired(required=false)
	public void setService(@Qualifier("todoItemService")TodoItemService service) {
		this.service = service;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value="todoNew")
	public String todoNew(){
		String ndeptId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("ndeptId"));
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		if(!"".equals(ndeptId) && ndeptId.length() > 0){
			odeptId = ndeptId;
			this.servletRequest.getSession().setAttribute("oldDeptId",odeptId);
		}
		String loginNames = "";
		Map<String, TaskUserVo> userMap =
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		//
//		String loginName = "ST/G001000001612549";
//		String ologinName = "ST/G00100000161";
//		String ouserId = "1062";
//		String odeptId = "2116";
		String type = "0";

		Map<String,String> map = new HashMap<String,String>();
		map.put("loginNames", loginNames);
		map.put("loginName", ologinName);
		map.put("userId", ouserId);
		map.put("deptId", odeptId);
		map.put("type", type);
		Client c = Client.create();
       // WebResource r=c.resource("http://10.1.48.60/workflowController/service/todo/statNew");
        WebResource r=c.resource("http://10.1.48.60/workflowController/service/todo/statNew");

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("data",gson.toJson(map));
        String response = r.type("application/x-www-form-urlencoded")
                                             .post(String.class, formData);
        ActionWriter aw = new ActionWriter(this.servletResponse);
        List<TodoItem> list = new ArrayList<TodoItem>();
        if("1".equals(response.toString())){
        	list = this.service.getTodoItems(loginNames,ologinName,odeptId,type);
        }

        aw.writeJson(list);

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value="todoOld")
	public String todoOld(){
		String ndeptId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("ndeptId"));
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		if(!"".equals(ndeptId) && ndeptId.length() > 0){
			odeptId = ndeptId;
			this.servletRequest.getSession().setAttribute("oldDeptId",odeptId);
		}

		//
//		String loginName = "ST/G001000001612549";
//		String ologinName = "ST/G00100000161";
//		String ouserId = "1062";
//		String odeptId = "2116";
		String type = "1";

		Map<String,String> map = new HashMap<String,String>();
		map.put("loginNames", "'"+loginName+"'");
		map.put("loginName", ologinName);
		map.put("userId", ouserId);
		map.put("deptId", odeptId);
		map.put("type", type);
		System.out.println("---------------------"+map);
		Client c = Client.create();
       WebResource r=c.resource("http://10.1.48.60/workflowController/service/todo/statOld");
       // WebResource r=c.resource("http://10.1.41.252:8080/workflowController/service/todo/statOld");

        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("data",gson.toJson(map));
        String response = r.type("application/x-www-form-urlencoded")
                                             .post(String.class, formData);
        ActionWriter aw = new ActionWriter(this.servletResponse);
        List<TodoItem> list = new ArrayList<TodoItem>();
        if("1".equals(response.toString())){
        	list = this.service.getTodoItems(loginName,ologinName,odeptId,type);
        }

        aw.writeJson(list);

		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value="todoItem")
	public String todo(){
		String ndeptId = StringUtil.getNotNullValueString(this.servletRequest.getParameter("ndeptId"));
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME_WITHOUT_DEPTID));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		if(!"".equals(ndeptId) && ndeptId.length() > 0){
			odeptId = ndeptId;
			this.servletRequest.getSession().setAttribute("oldDeptId",odeptId);
		}
		List<String> source = new ArrayList<String>();
		source.add(ologinName);
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
			source.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		
		//		
//		String loginName = "ST/G001000001612549";
//		String ologinName = "ST/G00100000161";
//		String ouserId = "1062";
//		String odeptId = "2116";
		String type = "";
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("loginNames", loginNames);
		map.put("loginName", ologinName);
		map.put("userId", ouserId);
		map.put("deptId", odeptId);
		//map.put("type", type);
		Client c = Client.create();  
        WebResource r=c.resource(workflowControllerIp);
        //WebResource r=c.resource("http://10.1.41.252:8080/workflowController/service/todo/stat");
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("data",gson.toJson(map));
        String response = r.type("application/x-www-form-urlencoded")
                                             .post(String.class, formData);
        ActionWriter aw = new ActionWriter(this.servletResponse);
        TodoStatInfo result = null;
        if("1".equals(response.toString())){
        	result = this.service.getTodoInfo(source);
        }

        aw.writeJson(result);
        
		return null;
	}
}
