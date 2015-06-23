package com.wonders.stpt.deptDoc.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.deptDoc.model.bo.ZDocsCatalog;
import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.service.ZDocsCatalogService;
import com.wonders.stpt.deptDoc.service.ZDocsRightService;
import com.wonders.stpt.deptDoc.util.DeptDocUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/zdocsCatalog")
@Controller("zdocsCatalogAction")
@Scope("prototype")
public class ZDocsCatalogAction extends AbstractParamAction{
	
	private ZDocsCatalogService zdocsCatalogService;
	private ZDocsRightService zdocsRightService;
	private DeptDocUtil deptDocUtil;
	private ActionWriter aw = new ActionWriter(response);
	
	@Action(value="main",results={
			@Result(name="success",location="/deptDoc/document/folder_main.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String main(){
		String flag = StringUtil.getNotNullValueString(request.getParameter("flag"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		List folders = zdocsCatalogService.getFolders(flag,loginName,deptId);
		JSONArray jso = JSONArray.fromObject(folders);
		this.request.setAttribute("folderNodes", jso.toString());
		
		return "success";
	}
	
	/**
	 * 复制/剪切 文件夹选择树
	 * @return
	 */
	@Action(value="copyDestTree",results={
			@Result(name="success",location="/deptDoc/document/copy_dest_tree.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String selectTree(){
		String catalogId = StringUtil.getNotNullValueString(request.getParameter("catalogId"));
		String flag = StringUtil.getNotNullValueString(request.getParameter("flag"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		List folders = zdocsCatalogService.getSelectableFolders(flag,loginName,deptId,catalogId);
		JSONArray jso = JSONArray.fromObject(folders);
		this.request.setAttribute("folderNodes", jso.toString());
		return "success";
	}
	
	@Action(value="folderTree")
	public String folderTree(){
		String flag = StringUtil.getNotNullValueString(request.getParameter("flag"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		List folders = zdocsCatalogService.getFolders(flag,loginName,deptId);
		JSONArray jso = JSONArray.fromObject(folders);
		this.aw.writeJson(jso.toString());
		return null;
	}
	
	@Action(value="newDir",results={
			@Result(name="success",location="/deptDoc/document/newDir.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String newDir(){
		String catalogId = StringUtil.getNotNullValueString(request.getParameter("catalogId"));
		ZDocsCatalog catalog = this.zdocsCatalogService.findById(catalogId);
		
		this.request.setAttribute("parentDir", catalog);
		return "success";
	}
	
	@Action(value="removeDir")
	public String removeDir(){
		String catalogId = StringUtil.getNotNullValueString(request.getParameter("catalogId"));
		int result = this.zdocsCatalogService.removeById(catalogId);
		
		this.aw.writeAjax(String.valueOf(result));
		return null;
	}
	
	@Action(value="modifyDir",results={
			@Result(name="success",location="/deptDoc/document/modifyDir.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String modifyDir(){
		String catalogId = StringUtil.getNotNullValueString(request.getParameter("catalogId"));
		ZDocsCatalog catalog = this.zdocsCatalogService.findById(catalogId);
		List<ZDocsRight> rights = this.zdocsRightService.findByRightId(catalogId);
		
		if(rights != null && rights.size() > 0){
			String empIds = "",empNames = "";
			for(ZDocsRight right : rights){
				empIds += "," + right.getEmpid();
				empNames += "," + right.getEmpname();
			}			
			empIds = empIds.substring(1);
			empNames = empNames.substring(1);
			this.request.setAttribute("empIds", empIds);
			this.request.setAttribute("empNames", empNames);
		}
			
		this.request.setAttribute("catalog", catalog);
		return "success";
	}
	
	@Action(value="selEmp",results={
			@Result(name="success",location="/deptDoc/document/selEmp.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String selEmp(){
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID));		
		List emps = deptDocUtil.getEmps(deptId);
		JSONArray jso = JSONArray.fromObject(emps);
		this.request.setAttribute("empNodes", jso.toString());
		
		return "success";
	}
	
	@Action(value="saveDir")
	public String saveDir(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		String pid = StringUtil.getNotNullValueString(request.getParameter("pid"));
		String catalogName = StringUtil.getNotNullValueString(request.getParameter("catalogName"));
		String empIds = StringUtil.getNotNullValueString(request.getParameter("empIds"));
		String empNames = StringUtil.getNotNullValueString(request.getParameter("empNames"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		
		ZDocsCatalog catalog = this.zdocsCatalogService.saveDir(id, pid, catalogName, empIds, empNames, loginName, userName);
		
		this.request.setAttribute("catalog", catalog);
		this.aw.writeAjax("1");
		return null;
	}
	
	public ZDocsCatalogService getZdocsCatalogService() {
		return zdocsCatalogService;
	}
	@Autowired(required=false)
	public void setZdocsCatalogService(@Qualifier("zdocsCatalogService")ZDocsCatalogService zdocsCatalogService) {
		this.zdocsCatalogService = zdocsCatalogService;
	}
	public DeptDocUtil getDeptDocUtil() {
		return deptDocUtil;
	}
	@Autowired(required=false)
	public void setDeptDocUtil(@Qualifier("deptDocUtil")DeptDocUtil deptDocUtil) {
		this.deptDocUtil = deptDocUtil;
	}

	public ZDocsRightService getZdocsRightService() {
		return zdocsRightService;
	}
	@Autowired(required=false)
	public void setZdocsRightService(@Qualifier("zdocsRightService")ZDocsRightService zdocsRightService) {
		this.zdocsRightService = zdocsRightService;
	}
	
	
}