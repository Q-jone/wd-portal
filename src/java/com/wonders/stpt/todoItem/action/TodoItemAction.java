/**
 * 
 */
package com.wonders.stpt.todoItem.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.page.service.PageService;
import com.wonders.stpt.todoItem.model.bo.TodoItem;
import com.wonders.stpt.todoItem.model.vo.FlowInfo;
import com.wonders.stpt.todoItem.model.vo.TodoItemVo;
import com.wonders.stpt.todoItem.util.ConfigUtil;
import com.wonders.stpt.todoItem.util.GenerateSqlUtil;
import com.wonders.stpt.todoItem.util.TodoItemFunc;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.StringUtil;
 
/** 
 * @ClassName: TodoItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/todo")
@Controller("todoItemAction")
@Scope("prototype")
public class TodoItemAction extends AbstractParamAction implements ModelDriven<TodoItemVo>{

	private static final String workflowControllerIp = ConfigUtil.getValueByKey("config.properties", "workflowControllerIp");
	
	private static final long serialVersionUID = 1L;
	
	private Gson gson = new Gson();
	
	private TodoItemVo vo = new TodoItemVo();
	
	private PageService pageService;
	
	public PageService getPageService() {
		return pageService;
	}

	@Autowired(required = false)
	public void setPageService(@Qualifier(value = "pageService")PageService pageService) {
		this.pageService = pageService;
	}


	private PageResultSet<Map<String,String>> pageResultSet;

	public PageResultSet<Map<String, String>> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<Map<String, String>> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value="todoItemList",results={
			@Result(name="success",location="/todoItem/todoItemList.jsp")
			})
	public String todoItemList(){
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("loginName"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		
		Map<String,String> wfMap = new HashMap<String,String>();
		wfMap.put("loginNames", loginNames);
		wfMap.put("loginName", ologinName);
		wfMap.put("userId", ouserId);
		wfMap.put("deptId", odeptId);
		Client c = Client.create();  
       WebResource r=c.resource(workflowControllerIp);
       // WebResource r=c.resource("http://10.1.41.252:8080/workflowController/service/todo/statOld");
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("data",gson.toJson(wfMap));
        String response = r.type("application/x-www-form-urlencoded")
                                             .post(String.class, formData);
        if("1".equals(response.toString())){ 
					
			String sql = GenerateSqlUtil.generateSql(loginNames+","+"'"+ologinName+"'",vo);
			/* 记录总数 */
			int totalRows = (int) this.pageService.countBySql(sql);
			PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
			List<String[]> list = this.pageService.findPaginationInfo(sql, pageinfo.getBeginIndex(), vo.pageSize);
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			for(String[] s : list){
				Map<String,String> map = new HashMap<String,String>();
				map.put("title", s[0]);map.put("pname", s[1]);map.put("pincident", s[2]);
				map.put("cname", s[3]);map.put("cincident", s[4]);map.put("stepname", s[5]);
				map.put("occurtime", s[6]);map.put("taskid", s[7]);map.put("tasktype", s[8]);
				map.put("loginname", s[9]);map.put("url", s[10]);
				result.add(map);
			}
			this.pageResultSet = new PageResultSet<Map<String, String>>();
			pageResultSet.setList(result);
			pageResultSet.setPageInfo(pageinfo);
        }
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value="reviewTodoItemList",results={
			@Result(name="success",location="/todoItem/reviewTodoItemList.jsp")
			})
	public String reviewTodoItemList(){
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("loginName"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		
		Map<String,String> wfMap = new HashMap<String,String>();
		wfMap.put("loginNames", loginNames);
		wfMap.put("loginName", ologinName);
		wfMap.put("userId", ouserId);
		wfMap.put("deptId", odeptId);
		Client c = Client.create();  
       WebResource r=c.resource(workflowControllerIp);
       // WebResource r=c.resource("http://10.1.41.252:8080/workflowController/service/todo/statOld");
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("data",gson.toJson(wfMap));
        String response = r.type("application/x-www-form-urlencoded")
                                             .post(String.class, formData);
        if("1".equals(response.toString())){ 
					
			String sql = GenerateSqlUtil.generateReviewSql(loginNames+","+"'"+ologinName+"'",vo);
			/* 记录总数 */
			int totalRows = (int) this.pageService.countBySql(sql);
			PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
			List<String[]> list = this.pageService.findPaginationInfo(sql, pageinfo.getBeginIndex(), vo.pageSize);
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			for(String[] s : list){
				Map<String,String> map = new HashMap<String,String>();
				map.put("title", s[0]);map.put("pname", s[1]);map.put("pincident", s[2]);
				map.put("cname", s[3]);map.put("cincident", s[4]);map.put("stepname", s[5]);
				map.put("occurtime", s[6]);map.put("taskid", s[7]);map.put("tasktype", s[8]);
				map.put("loginname", s[9]);map.put("url", s[10]);
				result.add(map);
			}
			this.pageResultSet = new PageResultSet<Map<String, String>>();
			pageResultSet.setList(result);
			pageResultSet.setPageInfo(pageinfo);
        }
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value="elseTodoItemList",results={
			@Result(name="success",location="/todoItem/elseTodoItemList.jsp")
			})
	public String elseTodoItemList(){
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("loginName"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		
		Map<String,String> wfMap = new HashMap<String,String>();
		wfMap.put("loginNames", loginNames);
		wfMap.put("loginName", ologinName);
		wfMap.put("userId", ouserId);
		wfMap.put("deptId", odeptId);
		Client c = Client.create();  
       WebResource r=c.resource(workflowControllerIp);
       // WebResource r=c.resource("http://10.1.41.252:8080/workflowController/service/todo/statOld");
        
        MultivaluedMap formData = new MultivaluedMapImpl();
        formData.add("data",gson.toJson(wfMap));
        String response = r.type("application/x-www-form-urlencoded")
                                             .post(String.class, formData);
        if("1".equals(response.toString())){ 
					
			String sql = GenerateSqlUtil.generateElseSql(loginNames+","+"'"+ologinName+"'",vo);
			/* 记录总数 */
			int totalRows = (int) this.pageService.countBySql(sql);
			PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
			List<String[]> list = this.pageService.findPaginationInfo(sql, pageinfo.getBeginIndex(), vo.pageSize);
			List<Map<String,String>> result = new ArrayList<Map<String,String>>();
			for(String[] s : list){
				Map<String,String> map = new HashMap<String,String>();
				map.put("title", s[0]);map.put("pname", s[1]);map.put("pincident", s[2]);
				map.put("cname", s[3]);map.put("cincident", s[4]);map.put("stepname", s[5]);
				map.put("occurtime", s[6]);map.put("taskid", s[7]);map.put("tasktype", s[8]);
				map.put("loginname", s[9]);map.put("url", s[10]);
				result.add(map);
			}
			this.pageResultSet = new PageResultSet<Map<String, String>>();
			pageResultSet.setList(result);
			pageResultSet.setPageInfo(pageinfo);
        }
		return "success";
	}
	
	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public TodoItemVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
}
