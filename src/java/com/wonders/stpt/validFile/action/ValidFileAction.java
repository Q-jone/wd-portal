/**
 * 
 */
package com.wonders.stpt.validFile.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;
import com.wonders.stpt.validFile.service.ValidFileService;
import com.wonders.stpt.validFile.util.ValidFileUtil;

/** 
 * @ClassName: ValidFileAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月22日 上午11:37:18 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/validFile")
@Controller("validFileList")
@Scope("prototype")
public class ValidFileAction extends AbstractParamAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1506902245737557876L;
	private ValidFileService service ;
	private ActionWriter aw = new ActionWriter(response);
	
	@Action("getReceipt")
	public String getReceipt(){
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		this.aw.writeJson(ValidFileUtil.getReceipt(mainId));
		return null;
	}
	
	@Action("getRemark")
	public String getRemark(){
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		this.aw.writeJson(ValidFileUtil.getRemark(mainId));
		return null;
	}
	
	@Action("addRemark")
	public String addRemark(){
		String attach = StringUtil.getNotNullValueString(this.request.getParameter("attach"));
		String remark = StringUtil.getNotNullValueString(this.request.getParameter("remark"));
		String operator = StringUtil.getNotNullValueString(this.request.getParameter("operator"));
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		boolean flag = ValidFileUtil.addRemark(remark, attach, operator, mainId);
		if(flag){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		
		return null;
	}
	
	@Action("setInvalid")
	public String setInvalid(){
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		String status = StringUtil.getNotNullValueString(this.request.getParameter("status"));
		boolean flag = ValidFileUtil.setInvalid(mainId,status);
		if(flag){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		return null;
	}

	public ValidFileService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("validFileService")ValidFileService service) {
		this.service = service;
	}

	
}
