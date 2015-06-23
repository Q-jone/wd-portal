package com.wonders.stpt.deptDoc.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.deptDoc.model.bo.ZDocsFile;
import com.wonders.stpt.deptDoc.model.vo.ZDocsFileListVo;
import com.wonders.stpt.deptDoc.service.ZDocsFileService;
import com.wonders.stpt.deptDoc.service.ZDocsRightService;
import com.wonders.stpt.deptDoc.util.DeptDocUtil;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/zdocsFile")
@Controller("zdocsFileAction")
@Scope("prototype")
public class ZDocsFileAction extends AbstractParamAction  implements ModelDriven<ZDocsFileListVo>{
	
	private DeptDocUtil deptDocUtil;
	private ZDocsFileService zdocsFileService;
	private ZDocsRightService zdocsRightService;
	
	private ZDocsFileListVo vo = new ZDocsFileListVo();
	private ActionWriter aw = new ActionWriter(response);
	private PageResultSet<Map<String,Object>> pageResultSet;

	/**
	 * 文档列表
	 * @return
	 */
	@Action(value="documents",results={
			@Result(name="success",location="/deptDoc/document/documents.jsp"),
			@Result(name="noright",location="/deptDoc/document/noright.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String documents(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		vo.setLoginName("ST/"+loginName);
		vo.setParentFolders(deptDocUtil.findParentFolders(vo.getCatalogId()));
		zdocsRightService.initRights(vo, loginName, deptId);
		if(!vo.isRead()){
			return "noright";
		}
		
		this.pageResultSet = this.zdocsFileService.list(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageResultSet", pageResultSet);
		return "success";
	}
	
	/**
	 * 上传文件页面
	 * @return
	 */
	@Action(value="newDoc",results={
			@Result(name="success",location="/deptDoc/document/newDoc.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String newDocs(){
		return "success";
	}
	
	/**
	 * 编辑页面
	 * @return
	 */
	@Action(value="modifyDoc",results={
			@Result(name="success",location="/deptDoc/document/modifyDoc.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String edit(){
		String fileId = StringUtil.getNotNullValueString(request.getParameter("fileId"));
		ZDocsFile zf = this.zdocsFileService.findById(fileId);
		
		this.request.setAttribute("zf", zf);
		return "success";
	}
	
	/**
	 * 修改文档信息
	 * @return
	 */
	@Action(value="updateDoc")
	public String updateDoc(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		
		String fileId = StringUtil.getNotNullValueString(request.getParameter("fileId"));
		String fileName = StringUtil.getNotNullValueString(request.getParameter("fileName"));
		String keyword = StringUtil.getNotNullValueString(request.getParameter("keyword"));
		int result = this.zdocsFileService.updateDocInfo(fileId,fileName,keyword,loginName,userName);
		
		this.aw.writeAjax(String.valueOf(result));
		return "success";
	}
	
	/**
	 * 逻辑删除
	 * @return
	 */
	@Action(value="removeDoc")
	public String removeDoc(){
		String fileId = StringUtil.getNotNullValueString(request.getParameter("fileId"));
		int result = this.zdocsFileService.removeById(fileId);
		
		this.aw.writeAjax(String.valueOf(result));
		return "success";
	}
	
	/**
	 * 保存新文件
	 * @return
	 */
	@Action(value="uploadDocs")
	public String uploadDocs(){
		String attachGroup = StringUtil.getNotNullValueString(request.getParameter("attachGroup"));
		String catalogId = StringUtil.getNotNullValueString(request.getParameter("catalogId"));
		String flag = StringUtil.getNotNullValueString(request.getParameter("flag"));
		String fileName = StringUtil.getNotNullValueString(request.getParameter("fileName"));
		String keyword = StringUtil.getNotNullValueString(request.getParameter("keyword"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		
		this.zdocsFileService.uploadDocs(attachGroup, catalogId, flag, fileName,keyword, loginName, userName);
		this.aw.writeAjax("1");
		return null;
	}
	/**
	 * 复制或剪切文件
	 * @return
	 */
	@Action(value="copyToFolders")
	public String copyToFolders(){
		String fileIds = StringUtil.getNotNullValueString(request.getParameter("fileIds"));
		String destDirIds = StringUtil.getNotNullValueString(request.getParameter("destDirIds"));
		String op = StringUtil.getNotNullValueString(request.getParameter("op"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		
		this.zdocsFileService.copyToFolders(fileIds, destDirIds, loginName, userName, "move".equals(op)?true:false);
		this.aw.writeAjax("1");
		return null;
	}
	
	/** 
	* @Title: authorityPerson 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年11月2日 下午7:41:12 
	* @throws 
	*/
	@Action(value="authorityPerson",results={
			@Result(name="success",location="/deptDoc/document/authorityPerson.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String authorityPerson(){
		String deptId = StringUtil.getNotNullValueString(this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID));		
		List emps = deptDocUtil.getEmps(deptId);
		JSONArray jso = JSONArray.fromObject(emps);
		this.request.setAttribute("empNodes", jso.toString());
		
		return "success";
	}
	
	/** 
	* @Title: authority
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年11月2日 下午7:59:38 
	* @throws 
	*/
	@Action(value="authority")
	public String authority(){
		String fileIds = StringUtil.getNotNullValueString(request.getParameter("fileIds"));
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		String empIds = StringUtil.getNotNullValueString(request.getParameter("empIds"));
		String empNames = StringUtil.getNotNullValueString(request.getParameter("empNames"));
		
		if(this.zdocsRightService.authoity(fileIds, empIds, empNames, loginName, userName) > 0){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		
		return null;
	}
	
	
	/** 
	* @Title: cancel
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年11月2日 下午7:59:38 
	* @throws 
	*/
	@Action(value="cancel")
	public String cancel(){
		String fileIds = StringUtil.getNotNullValueString(request.getParameter("fileIds"));
		String empIds = StringUtil.getNotNullValueString(request.getParameter("empIds"));
		
		if(this.zdocsRightService.cancel(fileIds.split(","), empIds.split(",")) > 0){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		
		return null;
	}
	
	public DeptDocUtil getDeptDocUtil() {
		return deptDocUtil;
	}
	@Autowired(required=false)
	public void setDeptDocUtil(@Qualifier("deptDocUtil")DeptDocUtil deptDocUtil) {
		this.deptDocUtil = deptDocUtil;
	}

	public ZDocsFileService getZdocsFileService() {
		return zdocsFileService;
	}
	@Autowired(required=false)
	public void setZdocsFileService(@Qualifier("zdocsFileService")ZDocsFileService zdocsFileService) {
		this.zdocsFileService = zdocsFileService;
	}
	
	public ZDocsRightService getZdocsRightService() {
		return zdocsRightService;
	}
	@Autowired(required=false)
	public void setZdocsRightService(@Qualifier("zdocsRightService")ZDocsRightService zdocsRightService) {
		this.zdocsRightService = zdocsRightService;
	}
	
	@Override
	public ZDocsFileListVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}

	public ZDocsFileListVo getVo() {
		return vo;
	}

	public void setVo(ZDocsFileListVo vo) {
		this.vo = vo;
	}

}