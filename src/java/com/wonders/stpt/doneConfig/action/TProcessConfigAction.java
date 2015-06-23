package com.wonders.stpt.doneConfig.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.model.TProcessConfig;
import com.wonders.stpt.doneConfig.service.ITDoneConfigClassicService;
import com.wonders.stpt.doneConfig.service.ITProcessConfigService;
import com.wonders.stpt.doneConfig.service.TDoneConfigInfoService;

@ParentPackage("struts-default")
@Namespace(value = "/doneConfig/process")
@Controller("tProcessConfigAction")
@Scope("prototype")
public class TProcessConfigAction implements SessionAware,ServletResponseAware{
	@Autowired
	private ITProcessConfigService processConfigService;
	@Autowired
	private TDoneConfigInfoService doneConfigInfoService;
	@Autowired
	private ITDoneConfigClassicService doneConfigClassicService;
	private PageResultSet pageResultSet;
	private Logger logger=Logger.getLogger(TProcessConfigAction.class);
	private TProcessConfig process=new TProcessConfig();
	public String id;
	private HttpServletResponse response;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Action(value="checkName",results={@Result(name="checkName",location="/doneConfig/classic/save.jsp")})
	public void checkName()throws Exception {//验证重复
		if(StringUtils.isBlank(id)){
			response.getWriter().print(processConfigService.findByHQL("from TProcessConfig t where t.name=? and t.removed='0'", new Object[]{name}).size());
		}else{
			response.getWriter().print(processConfigService.findByHQL("from TProcessConfig t where t.name=? and t.id!=? and t.removed='0'", new Object[]{name,id}).size());
		}
		}
	@Action(value="save",results={@Result(name="save",type="redirectAction",location="list.action")})//location="/doneConfig/process/save.jsp")
	public String save()throws Exception{
		
		processConfigService.save(process);
		return "save";
	}
	@Action(value="list",results={@Result(name="list",location="/doneConfig/process/list.jsp")})
	public String getList()throws Exception{
		
		pageResultSet=processConfigService.find(process, 1, Integer.MAX_VALUE);
		return "list";
	}
	@Action(value="toAdd",results={@Result(name="toAdd",location="/doneConfig/process/save.jsp")})
	public String toAdd()throws Exception{
		//pageResultSet=processConfigService.find(new TProcessConfig(), 1, Integer.MAX_VALUE);
		return "toAdd";
	}
	@Action(value="delete",results={@Result(name="delete",type="redirectAction",location="list.action")})
	public String delete()throws Exception{
		//删除info中recordID=id的记录
		//String hql="update TDoneConfigInfo set removed='1' where recordId=?";
		//doneConfigInfoService.delete(hql, new Object[]{id});
		processConfigService.delete(id);
		return "delete";
	}
	@Action(value="update",results={@Result(name="update",location="/doneConfig/process/save.jsp")})//
	public String update()throws Exception{
		process=processConfigService.findById(id);
		return "update";
	}
	
	public TProcessConfig getProcess() {
		return process;
	}
	public void setProcess(TProcessConfig process) {
		this.process = process;
	}
	public PageResultSet getPageResultSet() {
		return pageResultSet;
	}
	public void setPageResultSet(PageResultSet pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		response = httpServletResponse;
        response.setContentType("text/html;charset=utf-8");
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
