/**
 * 
 */
package com.wonders.stpt.applyProject.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.applyProject.model.vo.ApplyProjectListVo;
import com.wonders.stpt.applyProject.service.ApplyProjectService;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;

/** 
 * @ClassName: ApplyProjectAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月30日 上午10:38:40 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/applyProjectList")
@Controller("applyProjectListAction")
@Scope("prototype")
public class ApplyProjectListAction extends AbstractParamAction implements ModelDriven<ApplyProjectListVo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9190938457503538589L;
	private ApplyProjectListVo vo = new ApplyProjectListVo();
	private ApplyProjectService service;
	private ActionWriter aw = new ActionWriter(response);
	private PageResultSet<Map<String,Object>> pageResultSet;
	public ApplyProjectListVo getVo() {
		return vo;
	}
	public void setVo(ApplyProjectListVo vo) {
		this.vo = vo;
	}
	public ApplyProjectService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("applyProjectService")ApplyProjectService service) {
		this.service = service;
	}
	@Override
	public ApplyProjectListVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	
	public PageResultSet<Map<String, Object>> getPageResultSet() {
		return pageResultSet;
	}
	public void setPageResultSet(PageResultSet<Map<String, Object>> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	@Action(value="planList")
	public String planList(){
		this.pageResultSet = this.service.list(vo);
		aw.writeJson(pageResultSet);
		return null;
	}
	
	@Action(value="accidentList")
	public String accidentList(){
		this.pageResultSet = this.service.list(vo);
		aw.writeJson(pageResultSet);
		return null;
	}
	@Action(value="projectList")
	public String projectList(){
		this.pageResultSet = this.service.list(vo);
		aw.writeJson(pageResultSet);
		return null;
	}

}
