package com.wonders.stpt.union.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.service.UnionOrganService;
import com.wonders.stpt.util.ActionWriter;

/**
 * 运营发文 
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionOrgan")
@Controller("unionOrganAction")
@Scope("prototype")
public class UnionOrganAction extends AbstractParamAction implements ModelDriven<UnionMatch> {

	private UnionMatch unionMatch = new UnionMatch();
	
	private ActionWriter aw = new ActionWriter(response);
	
	@Override
	public UnionMatch getModel() {
		return unionMatch;
	}

	public void setUnionMatch(UnionMatch unionMatch) {
		this.unionMatch = unionMatch;
	}

	@Action(value="getDepartments")
	public String getDepartments(){
		
		List<Map> depts = this.service.getDepts();
		
        Map root =  depts.get(0);
        root.put("children",depts.subList(1,depts.size()-1));
        
		JSONObject jso = JSONObject.fromObject(root);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	@Action(value="getEmployees")
	public String getEmployees(){
		String deptId = this.request.getParameter("deptId");
		
		List<Map> emps = this.service.getEmployees(deptId);
        
		JSONArray jso = JSONArray.fromObject(emps);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	@Action(value="getDeptLeaders")
	public String getDeptLeaders(){
		String deptId = this.request.getParameter("deptId");
		
		List<Map> leaders = this.service.getDeptLeaders(deptId);
        
		JSONArray jso = JSONArray.fromObject(leaders);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	@Action(value="fuzzySearchEmployees")
	public String fuzzySearchEmployees(){
		String deptId = this.request.getParameter("deptId");
		String key = this.request.getParameter("key");
		
		List<Map> emps = this.service.fuzzySearchEmployees(deptId,key);
        
		JSONArray jso = JSONArray.fromObject(emps);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	private UnionOrganService service;
	public UnionOrganService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionOrganService")UnionOrganService service) {
		this.service = service;
	}
	
}
