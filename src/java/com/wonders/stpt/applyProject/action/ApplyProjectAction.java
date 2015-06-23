/**
 * 
 */
package com.wonders.stpt.applyProject.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.applyProject.model.bo.TApplyProject;
import com.wonders.stpt.applyProject.service.ApplyProjectJdbcService;
import com.wonders.stpt.applyProject.service.ApplyProjectService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: ApplyProjectAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月30日 上午10:38:40 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/applyProject")
@Controller("applyProjectAction")
@Scope("prototype")
public class ApplyProjectAction extends AbstractParamAction implements ModelDriven<TApplyProject>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2101729786327838248L;
	private TApplyProject bo = new TApplyProject();
	private ApplyProjectService service;
	private ApplyProjectJdbcService jdbcService;
	private ActionWriter aw = new ActionWriter(response);
	public TApplyProject getBo() {
		return bo;
	}
	public void setBo(TApplyProject bo) {
		this.bo = bo;
	}
	public ApplyProjectService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("applyProjectService")ApplyProjectService service) {
		this.service = service;
	}
	
	
	public ApplyProjectJdbcService getJdbcService() {
		return jdbcService;
	}
	@Autowired(required=false)
	public void setJdbcService(@Qualifier("applyProjectJdbcService")ApplyProjectJdbcService jdbcService) {
		this.jdbcService = jdbcService;
	}
	@Override
	public TApplyProject getModel() {
		// TODO Auto-generated method stub
		return bo;
	}
	
	@Action(value="planEdit",results={
			@Result(name="success",location="/applyProject/plan/edit.jsp")
			})
	public String planEdit(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="planView",results={
			@Result(name="success",location="/applyProject/plan/view.jsp")
			})
	public String planView(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="planAdd")
	public String planAdd(){
		aw.writeAjax(this.service.save(bo));
		return null;
	}
	
	@Action(value="planUpdate")
	public String planUpdate(){
		aw.writeAjax(this.service.update(bo));
		return null;
	}
	
	//-----------------------------------------
	
	@Action(value="projectEdit",results={
			@Result(name="success",location="/applyProject/project/edit.jsp")
			})
	public String projectEdit(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="projectView",results={
			@Result(name="success",location="/applyProject/project/view.jsp")
			})
	public String projectView(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="projectAdd")
	public String projectAdd(){
		aw.writeAjax(this.service.save(bo));
		return null;
	}
	
	@Action(value="projectUpdate")
	public String projectUpdate(){
		aw.writeAjax(this.service.update(bo));
		return null;
	}
	
	//-----------------------
	
	@Action(value="accidentEdit",results={
			@Result(name="success",location="/applyProject/accident/edit.jsp")
			})
	public String accidentEdit(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="accidentView",results={
			@Result(name="success",location="/applyProject/accident/view.jsp")
			})
	public String accidentView(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		this.bo = this.service.load(id);
		return "success";
	}
	
	@Action(value="accidentAdd")
	public String accidentAdd(){
		aw.writeAjax(this.service.save(bo));
		return null;
	}
	
	@Action(value="accidentUpdate")
	public String accidentUpdate(){
		aw.writeAjax(this.service.update(bo));
		return null;
	}

	@Action(value="delete")
	public String delete(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		int result = this.jdbcService.delete(id);
		if(result > 0){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		return null;
	}
}
