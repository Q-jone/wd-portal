package com.wonders.stpt.deptDoc.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.deptDoc.constants.DeptDocConstants;
import com.wonders.stpt.deptDoc.model.bo.ZDocsRight;
import com.wonders.stpt.deptDoc.model.vo.RecvQueryVo;
import com.wonders.stpt.deptDoc.model.vo.RedvQueryVo;
import com.wonders.stpt.deptDoc.model.vo.SendQueryVo;
import com.wonders.stpt.deptDoc.service.DeptDocProcessService;
import com.wonders.stpt.deptDoc.util.DeptDocProcessUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/deptDoc/process")
@Controller("deptDocProcessAction")
@Scope("prototype")
public class DeptDocProcessAction extends AbstractParamAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1903871642653612322L;
	@Autowired
	private DeptDocProcessService service;
	
	@Action(value="recvList")
	public String recvList(){
		String loginName = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		ActionWriter aw = new ActionWriter(response);
		RecvQueryVo vo = new RecvQueryVo();
		DeptDocProcessUtil.getVo(request, vo);
		aw.writeJson(this.service.list(DeptDocConstants.getDeptDocRecvSql(deptId,loginName), vo));
		return null;
	}
	
	@Action(value="redvList")
	public String redvList(){
		String loginName = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		ActionWriter aw = new ActionWriter(response);
		RedvQueryVo vo = new RedvQueryVo();
		DeptDocProcessUtil.getVo(request, vo);
		aw.writeJson(this.service.list(DeptDocConstants.getDeptDocRedvSql(deptId,loginName), vo));
		return null;
	}

	@Action(value="sendList")
	public String sendList(){
		String loginName = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		
		ActionWriter aw = new ActionWriter(response);
		SendQueryVo vo = new SendQueryVo();
		DeptDocProcessUtil.getVo(request, vo);
		aw.writeJson(this.service.list(DeptDocConstants.getDeptDocSendSql(deptId,loginName), vo));
		return null;
	}
	
	@Action(value="partysendList")
	public String partysendList(){
		String loginName = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		ActionWriter aw = new ActionWriter(response);
		SendQueryVo vo = new SendQueryVo();
		DeptDocProcessUtil.getVo(request, vo);
		aw.writeJson(this.service.list(DeptDocConstants.getDeptDocPartysendSql(deptId,loginName), vo));
		return null;
	}
	
	@Action(value="dcpsendList")
	public String dcpsendList(){
		String loginName = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		ActionWriter aw = new ActionWriter(response);
		SendQueryVo vo = new SendQueryVo();
		DeptDocProcessUtil.getVo(request, vo);
		aw.writeJson(this.service.list(DeptDocConstants.getDeptDocDcpsendSql(deptId,loginName), vo));
		return null;
	}
	
	@Action(value="normativeList")
	public String normativeList(){
		String loginName = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		
		ActionWriter aw = new ActionWriter(response);
		SendQueryVo vo = new SendQueryVo();
		DeptDocProcessUtil.getVo(request, vo);
		aw.writeJson(this.service.list(DeptDocConstants.getDeptDocNormativeSql(deptId,loginName), vo));
		return null;
	}
	
	@Action(value="authority")
	public String authority(){
		ActionWriter aw = new ActionWriter(response);
		String fileIds = StringUtil.getNotNullValueString(request.getParameter("fileIds"));
		String empIds = StringUtil.getNotNullValueString(request.getParameter("empIds"));
		String operator = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		List<ZDocsRight> rights = new ArrayList<ZDocsRight>();
		for(String f : fileIds.split(",")){
			for(String e : empIds.split(",")){
				ZDocsRight bo = new ZDocsRight();
				bo.setType(DeptDocConstants.FILE_RIGHT_TYPE);
				bo.setRightid(f);
				bo.setUptdate(new java.util.Date());
				bo.setUptuser("ST/"+operator);
				bo.setEmpid(e);
				rights.add(bo);
			}
		}
		if(DeptDocProcessUtil.existInGroup(DeptDocConstants.CODE_PROCESS_DEPT_DOC, operator) && 
				this.service.authority(rights)){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		
		return null;
	}
	
	@Action(value="cancel")
	public String cancel(){
		ActionWriter aw = new ActionWriter(response);
		String fileIds = StringUtil.getNotNullValueString(request.getParameter("fileIds"));
		String empIds = StringUtil.getNotNullValueString(request.getParameter("empIds"));
		String operator = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("loginName"));
		
		String[] fileId = fileIds.split(",");
		String[] empId = empIds.split(",");
		
		if(DeptDocProcessUtil.existInGroup(DeptDocConstants.CODE_PROCESS_DEPT_DOC, operator) && 
				this.service.cancel(fileId,empId)){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		
		return null;
	}
	
	@Action(value="getDeptPerson")
	public String getDeptPerson(){
		ActionWriter aw = new ActionWriter(response);
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute("deptId"));
		aw.writeJson(DeptDocProcessUtil.getDeptPerson(deptId));
		return null;
	}
}
