package com.wonders.stpt.exam.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.exam.entity.ExamGroup;
import com.wonders.stpt.exam.entity.ExamMain;
import com.wonders.stpt.exam.entity.ExamOption;
import com.wonders.stpt.exam.entity.ExamQuestion;
import com.wonders.stpt.exam.entity.ExamUserMain;
import com.wonders.stpt.exam.entity.ExamUserOption;
import com.wonders.stpt.exam.service.ExamGroupService;
import com.wonders.stpt.exam.service.ExamOptionService;
import com.wonders.stpt.exam.service.ExamQuestionService;
import com.wonders.stpt.exam.service.ExamService;
import com.wonders.stpt.exam.service.ExamUserMainService;
import com.wonders.stpt.exam.service.ExamUserOptionService;
import com.wonders.stpt.exam.util.ExamUtil;
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/**
 * 在线答题 
 * @author ICESUGAR
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/exam")
@Controller("examAction")
@Scope("prototype")
public class ExamAction extends AbstractParamAction implements ModelDriven<ExamMain> {

	
	private ExamMain examMain = new ExamMain();
	
	private PageResultSet<ExamMain> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);

	@Override
	public ExamMain getModel() {
		// TODO Auto-generated method stub
		return examMain;
	}

	public ExamMain getExamMain() {
		return examMain;
	}

	public void setExamMain(ExamMain examMain) {
		this.examMain = examMain;
	}

	public PageResultSet<ExamMain> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<ExamMain> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	
	
	/*@Action(value="list",results={
			@Result(name="success",location="/processStop/list.jsp")
			})
	public String findPage(){
		String table = this.request.getParameter("table");
		if(table == null){
			table = "t_doc_receive";
		}
		String title = this.request.getParameter("title");
		String status = this.request.getParameter("status");
		String starttime = this.request.getParameter("starttime");
		String endtime = this.request.getParameter("endtime");
		String type = this.request.getParameter("type");
		if(type == null){
			type = "0";
		}
		
		this.request.setAttribute("table", table);
		this.request.setAttribute("title", title);
		this.request.setAttribute("status", status);
		this.request.setAttribute("starttime", starttime);
		this.request.setAttribute("endtime", endtime);
		
		this.request.setAttribute("type", type);
		
		int totalRows = this.service.count(table,
				title,status,starttime,endtime);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<Object[]> list = this.service.findByPage(pageinfo.getBeginIndex(), vo.pageSize,
				table,title,status,starttime,endtime);
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		return SUCCESS;
	}*/
	
	@Action(value="edit",results={
			@Result(name="success",location="/exam/edit.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String edit(){
		String mainId = this.request.getParameter("examId");
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		if(loginName==null||"".equals(loginName)){
			loginName = this.request.getParameter("loginName");
		}
		if(loginName==null||"".equals(loginName)){
			loginName =UUID.randomUUID().toString();
		}
		if(deptId==null||"".equals(deptId)){
		    deptId = this.request.getParameter("deptId");
		}
	    
		if(deptId==null||"".equals(deptId)){
			return ERROR;
		}
	    
		if(mainId==null||"".equals(mainId)) return ERROR;
		
		examMain = service.find(mainId);
		
		List<ExamGroup> groups = examGroupService.findByMainId(mainId);
		List<ExamQuestion> questions = examQuestionService.findByMainId(mainId);
		List<ExamOption> options = examOptionService.findByMainId(mainId);
		ExamUserMain examUserMain = examUserMainService.findByMainIdAndLoginName(mainId,loginName);
		
		Map<String,String[]> map = new HashMap<String,String[]>();
		Map<String,Boolean> mapQ = new HashMap<String,Boolean>();
		if(examUserMain!=null){
			List<ExamUserOption> uoptions = examUserOptionService.findByMainIdAndLoginName(mainId,loginName);
			
			for(ExamUserOption uoption:uoptions){
				map.put(uoption.getOptionId(), new String[]{uoption.getId(),uoption.getAnswer(),uoption.getRemark()});
				mapQ.put(uoption.getQuestId(), true);
			}
			
		}else{
			examUserMain = new ExamUserMain();
			examUserMain.setBeginTime(ExamUtil.getNowTime());
			examUserMain.setDeptId(deptId);
			examUserMain.setLoginName(loginName);
			examUserMain.setMainId(mainId);
			
			examUserMainService.save(examUserMain);
		}
		
		boolean isUserOk = false;
		if(examUserMain.getState()==0&&examMain.getState()==0){
			isUserOk = true;
		}
		
		
		this.request.setAttribute("isUserOk", isUserOk);
		this.request.setAttribute("examUserMain", examUserMain);
		
		this.request.setAttribute("groups", groups);
		this.request.setAttribute("questions", questions);
		this.request.setAttribute("options", options);
		this.request.setAttribute("map", map);
		this.request.setAttribute("mapQ", mapQ);//问题是否答过
		
		return SUCCESS;
	}
	
	@Action(value="saveUserOption")
	public String saveUserOption(){
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		String groupId = StringUtil.getNotNullValueString(this.request.getParameter("groupId"));
		String questId = StringUtil.getNotNullValueString(this.request.getParameter("questId"));
		String optionId = StringUtil.getNotNullValueString(this.request.getParameter("optionId"));
		String answer = StringUtil.getNotNullValueString(this.request.getParameter("answer"));
		String uOptionId = StringUtil.getNotNullValueString(this.request.getParameter("uOptionId"));
		String remark = StringUtil.getNotNullValueString(this.request.getParameter("remark"));
		
		
		String loginName = this.request.getParameter("loginName");
		String deptId = this.request.getParameter("deptId");
		
		
//		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
//	    String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		ExamUserOption uo = new ExamUserOption();
		uo.setAnswer(answer);
		uo.setCTime(ExamUtil.getNowTime());
		uo.setDeptId(deptId);
		uo.setGroupId(groupId);
		uo.setId(uOptionId);
		uo.setLoginName(loginName);
		uo.setMainId(mainId);
		uo.setOptionId(optionId);
		uo.setQuestId(questId);
		uo.setRemark(remark);
		
		examUserOptionService.save(uo);
		
		boolean flag = true;// this.service.save(reason, attach, operator, mainId, process, incident);
		if(flag){
			aw.writeAjax(questId+","+uo.getId()+","+optionId);
		}else{
			aw.writeAjax("0");
		}
		return null;
	}
	
	@Action(value="saveUserMain")
	public String saveUserMain(){
		String uMainid = StringUtil.getNotNullValueString(this.request.getParameter("uMainid"));

		ExamUserMain examUserMain = examUserMainService.find(uMainid);

		examUserMain.setState(1);
		examUserMainService.save(examUserMain);
		aw.writeAjax("0");
		return null;
	}
	
	
	
	
	private ExamService service;
	public ExamService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("examService")ExamService service) {
		this.service = service;
	}
	
	private ExamGroupService examGroupService;
	private ExamOptionService examOptionService;
	private ExamQuestionService examQuestionService;
	private ExamUserMainService examUserMainService;
	private ExamUserOptionService examUserOptionService;

	public ExamGroupService getExamGroupService() {
		return examGroupService;
	}
	@Autowired(required=false)
	public void setExamGroupService(@Qualifier("examGroupService")ExamGroupService examGroupService) {
		this.examGroupService = examGroupService;
	}

	public ExamOptionService getExamOptionService() {
		return examOptionService;
	}
	@Autowired(required=false)
	public void setExamOptionService(@Qualifier("examOptionService")ExamOptionService examOptionService) {
		this.examOptionService = examOptionService;
	}

	public ExamQuestionService getExamQuestionService() {
		return examQuestionService;
	}
	@Autowired(required=false)
	public void setExamQuestionService(@Qualifier("examQuestionService")ExamQuestionService examQuestionService) {
		this.examQuestionService = examQuestionService;
	}

	public ExamUserMainService getExamUserMainService() {
		return examUserMainService;
	}
	@Autowired(required=false)
	public void setExamUserMainService(@Qualifier("examUserMainService")ExamUserMainService examUserMainService) {
		this.examUserMainService = examUserMainService;
	}

	public ExamUserOptionService getExamUserOptionService() {
		return examUserOptionService;
	}
	@Autowired(required=false)
	public void setExamUserOptionService(@Qualifier("examUserOptionService")ExamUserOptionService examUserOptionService) {
		this.examUserOptionService = examUserOptionService;
	}
	
	
	
	
	

}
