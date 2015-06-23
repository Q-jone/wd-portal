package com.wonders.stpt.union.action.prize;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionProjectPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.prize.UnionAchivementPrizeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/**
 * 运营发文 
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionAchivementPrize")
@Controller("unionAchivementPrizeAction")
@Scope("prototype")
public class UnionAchivementPrizeAction extends AbstractParamAction implements ModelDriven<UnionAchivementPrize> {
	
	private List<File> fileupload;//这里的"fileName"一定要与表单中的文件域名相同  
    private List<String> fileuploadContentType;//格式同上"fileName"+ContentType  
    private List<String> fileuploadFileName;//格式同上"fileName"+FileName  
    
    public UnionFlowInfo params = new UnionFlowInfo();
	
	private UnionAchivementPrize unionAchivementPrize = new UnionAchivementPrize();
	
	private PageResultSet<UnionAchivementPrize> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);

	@Override
	public UnionAchivementPrize getModel() {
		return unionAchivementPrize;
	}

	public void setUnionAchivementPrize(UnionAchivementPrize unionAchivementPrize) {
		this.unionAchivementPrize = unionAchivementPrize;
	}

	public PageResultSet<UnionAchivementPrize> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<UnionAchivementPrize> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="list",results={
			@Result(name="success",location="/union/themeList.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		

		return SUCCESS;
	}
	
	@Action(value="showAdd",results={
			@Result(name="success",location="/union/prize/achivementPrizeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showAdd(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		return SUCCESS;
	}
	
	@Action(value="showEdit",results={
			@Result(name="success",location="/union/prize/achivementPrizeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showEdit(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String id = this.request.getParameter("id");
		UnionAchivementPrize achivementPrize = service.find(id);
		
		this.request.setAttribute("achivementPrize", achivementPrize);
		return SUCCESS;
	}
	
	@Action(value="showView",results={
			@Result(name="success",location="/union/prize/achivementPrizeView.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showView(){
		String id = this.request.getParameter("id");
		UnionAchivementPrize achivementPrize = service.find(id);
		UnionPrize prize = this.prizeService.findById(achivementPrize.getPrizeId());

		if(prize.getMatch().getStatus() == UnionMatch.PASS_STATUS){
			this.request.setAttribute("approvals", this.approvalService.findFinalApprovalInfo(achivementPrize.getApplyId(), prize.getMatchId(), prize.getMatch().getThemeId()));
		}
		this.request.setAttribute("achivementPrize", achivementPrize);
		this.request.setAttribute("prize", prize);
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		String deptName = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		
		String now = DateUtil.getNowTime();
		if(StringUtils.isEmpty(unionAchivementPrize.getId())){
			UnionApplyMatch applyMatch = this.applyMatchService.find(unionAchivementPrize.getApplyId());
			this.unionAchivementPrize.setcUser(loginName);
			this.unionAchivementPrize.setcTime(now);		
			this.unionAchivementPrize.setcUserName(userName);
			this.unionAchivementPrize.setDeptId(applyMatch.getDeptId());
			this.unionAchivementPrize.setDeptName(applyMatch.getDeptName());
		}
		
		this.unionAchivementPrize.setuUser(loginName);
		this.unionAchivementPrize.setuTime(now);
		
		unionAchivementPrize = this.service.save(unionAchivementPrize);
		
		this.aw.writeAjax("1");
		return null;
	}
	
	@Action(value="doDel")
	public String doDel(){
		String id = StringUtil.getNotNullValueNumber(request.getParameter("id"));
		
		this.service.logicDelete(id);
		return null;
	}
	
	@Action(value="batchUpload")
	public String batchUpload(){
		UnionProcessUtil.prepareFlowInfo(request, params);
		
		String result = this.service.readExcel(fileupload, fileuploadFileName, params);
		
		this.aw.writeAjax(result);
		return null;
	}
	
	private UnionAchivementPrizeService service;
	public UnionAchivementPrizeService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionAchivementPrizeService")UnionAchivementPrizeService service) {
		this.service = service;
	}
	
	private UnionPrizeService prizeService;
	public UnionPrizeService getPrizeService() {
		return prizeService;
	}
	@Autowired(required=false)
	public void setPrizeService(@Qualifier("unionPrizeService")UnionPrizeService prizeService) {
		this.prizeService = prizeService;
	}
	
	public UnionAchivementPrize getUnionAchivementPrize() {
		return unionAchivementPrize;
	}
	
	private UnionApplyMatchService applyMatchService;
	public UnionApplyMatchService getApplyMatchService() {
		return applyMatchService;
	}
	@Autowired(required=false)
	public void setApplyMatchService(@Qualifier("unionApplyMatchService")UnionApplyMatchService applyMatchService) {
		this.applyMatchService = applyMatchService;
	}
	
	private UnionApprovalService approvalService;
	public UnionApprovalService getApprovalService() {
		return approvalService;
	}
	@Autowired(required=false)
	public void setApprovalService(@Qualifier("unionApprovalService")UnionApprovalService approvalService) {
		this.approvalService = approvalService;
	}

	public List<File> getFileupload() {
		return fileupload;
	}

	public void setFileupload(List<File> fileupload) {
		this.fileupload = fileupload;
	}

	public List<String> getFileuploadContentType() {
		return fileuploadContentType;
	}

	public void setFileuploadContentType(List<String> fileuploadContentType) {
		this.fileuploadContentType = fileuploadContentType;
	}

	public List<String> getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(List<String> fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

}
