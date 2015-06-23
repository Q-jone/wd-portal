package com.wonders.stpt.exam.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/**
 * 在线答题 
 * @author ICESUGAR
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/examManage")
@Controller("examManageAction")
@Scope("prototype")
public class ExamManageAction extends AbstractParamAction implements ModelDriven<ExamMain> {

	
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
	/**
	 * 试卷管理列表
	 * @return
	 */
	@Action(value="list",results={
			@Result(name="success",location="/exam/m_paper_list.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String title = StringUtil.getNotNullValueString(this.request.getParameter("title"));
		
		String pageNoString = this.request.getParameter("page");
		if(!StringUtils.isNotEmpty(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.id,t.title,t.cUser,t.cTime,t.state,(select count(q.id) from ExamQuestion q where q.removed = 0 and q.mainId = t.id) as total_q from ExamMain t where t.removed=0";
		String countHql = "select count(t.id) from ExamMain t where t.removed=0";
		if(!"".equals(title)){
			queryHql += " and t.title like '%"+title+"%'";
			countHql += " and t.title like '%"+title+"%'";
		}
		queryHql += " order by t.cTime desc";
		
		PageResultSet result = this.service.findPageResult(queryHql,countHql,page, pageSize);
		
		this.request.setAttribute("pageResultSet", result);
		this.request.setAttribute("title", title);
		return SUCCESS;
	}
	/**
	 * 添加编辑试卷
	 * @return
	 */
	@Action(value="add",results={
			@Result(name="success",location="/exam/m_paper_add.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		if(!"".equals(id)){
			ExamMain examMain = service.find(id);
			request.setAttribute("paper", examMain);
		}
		
		return SUCCESS;
	}
	/**
	 * 保存试卷
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success",location="/exam/message.jsp")
			})
	public String save(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		String title = StringUtil.getNotNullValueString(this.request.getParameter("title"));
		String desp = StringUtil.getNotNullValueString(this.request.getParameter("desp"));
		String state = StringUtil.getNotNullValueString(this.request.getParameter("state"));
		
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);

		ExamMain examMain = new ExamMain();
		if(!"".equals(id)){
			examMain = service.find(id);
		}
		
		examMain.setTitle(title);
		examMain.setDesp(desp);
		examMain.setState(Long.valueOf(state));
		examMain.setDeptId(deptId);
		examMain.setcUser(userName);
		examMain.setLoginName(loginName);
		
		service.save(examMain);
		
		request.setAttribute("smsg", "操作成功");
		request.setAttribute("turl", "list.action");
		return "success";
	}
	/**
	 * 删除试卷
	 * @return
	 */
	@Action(value="delete",results={
			@Result(name="success",location="/exam/message.jsp")
			})
	public String delete(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		service.deleteById(id);
		
		request.setAttribute("smsg", "操作成功");
		request.setAttribute("turl", "list.action");
		return "success";
	}
	/**
	 * 问题列表
	 * @return
	 */
	@Action(value="listQ",results={
			@Result(name="success",location="/exam/m_q_list.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String listQ(){
		String pid = StringUtil.getNotNullValueString(this.request.getParameter("pid"));
		ExamMain examMain = service.find(pid);
		request.setAttribute("paper", examMain);
		
		List<ExamGroup> groupList = this.examGroupService.findByMainId(pid);
		this.request.setAttribute("groups", groupList);
		
		List<ExamQuestion> qList = examQuestionService.findByMainId(pid);
		this.request.setAttribute("questions", qList);
		
		List<ExamOption> opList = examOptionService.findByMainId(pid);
		this.request.setAttribute("options", opList);
		
		return SUCCESS;
	}
	/**
	 * 新增/修改问题
	 * @return
	 */
	@Action(value="addQ",results={
			@Result(name="success",location="/exam/m_q_add.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String addQ(){
		
		String pid = StringUtil.getNotNullValueString(this.request.getParameter("pid"));
		String qid = StringUtil.getNotNullValueString(this.request.getParameter("qid"));
		
		ExamMain examMain = service.find(pid);
		this.request.setAttribute("paper", examMain);
		
		List<ExamGroup> groupList = this.examGroupService.findByMainId(pid);
		this.request.setAttribute("groups", groupList);
		
		if(!"".equals(qid)){
			ExamQuestion q = examQuestionService.find(qid);
			this.request.setAttribute("q", q);	
			
			List<ExamOption> options = examOptionService.findByQuestionId(qid);
			JSONArray jsonArray = JSONArray.fromObject(options);
			this.request.setAttribute("OPTION_LIST", jsonArray.toString());
		}
		
		return SUCCESS;
	}
	/**
	 * 问题详情
	 * @return
	 */
	@Action(value="viewQ")
	public String viewQ(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		ExamQuestion q = examQuestionService.find(id);
		
		List<ExamOption> options = this.examOptionService.findByQuestionId(id);
		
		Map map = new HashMap();
		map.put("options", options);
		map.put("q", q);
		
		JSONObject jso = JSONObject.fromObject(map);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	/**
	 * 删除问题
	 * @return
	 */
	@Action(value="deleteQ",results={
			@Result(name="success",location="/exam/message.jsp")
			})
	public String deleteQ(){
		String pid = StringUtil.getNotNullValueString(this.request.getParameter("pid"));
		String qid = StringUtil.getNotNullValueString(this.request.getParameter("qid"));
		int i = examQuestionService.deleteById(qid);
		
	    if (i == 1){
	        request.setAttribute("smsg", "删除成功");
	    }else {
	        request.setAttribute("smsg", "删除失败");
	    }
	    request.setAttribute("turl", "listQ.action?pid="+pid);
	    
		return "success";
	}
	/**
	 * 保存问题
	 * @return
	 */
	@Action(value="saveQ",results={
			@Result(name="success",location="/exam/message.jsp")
			})
	public String saveQ(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		String groupId = StringUtil.getNotNullValueString(this.request.getParameter("groupId"));
		String questType = StringUtil.getNotNullValueString(this.request.getParameter("questType"));
		String title = StringUtil.getNotNullValueString(this.request.getParameter("title"));
		String remark = StringUtil.getNotNullValueString(this.request.getParameter("remark"));
		String questNum = StringUtil.getNotNullValueString(this.request.getParameter("questNum"));
		String showNum = StringUtil.getNotNullValueString(this.request.getParameter("showNum"));

	    String SKEY = "";
	    String qcomplex;
	    JSONArray jsonArray;
	    if (("1".equals(questType)) || ("2".equals(questType))) {
	      String[] _SEKYS = request.getParameterValues("skey");
	      if ((_SEKYS != null) && (_SEKYS.length > 0))
	        for (String s : _SEKYS) SKEY = SKEY + s; 
	    }
	    else {
	      SKEY = StringUtil.getNotNullValueString(this.request.getParameter("skey"));
	    }
	    
	    ExamQuestion q = new ExamQuestion();
		
	    q.setId(id);
		q.setMainId(mainId);
		q.setGroupId(groupId);
		q.setTitle(title);
		q.setQuestType(Long.valueOf(questType));
		q.setRightAnswer(SKEY);
		q.setRemoved(0l);
		q.setRemark(remark);
		q.setQuestNum(Long.valueOf(questNum));		
		q.setShowNum(Long.valueOf(showNum));
		
		examQuestionService.save(q, request.getParameterValues("sid"), request.getParameterValues("soption"));
		
		request.setAttribute("smsg", "操作成功");
		request.setAttribute("turl", "listQ.action?pid="+mainId);
		return "success";
	}

	/**
	 * 试卷分组列表
	 * @return
	 */
	@Action(value="showGroup",results={
			@Result(name="success",location="/exam/m_group_list.jsp")
			})
	public String showGroup(){
		
		String op = request.getParameter("op");
		
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		String mainId = StringUtil.getNotNullValueString(this.request.getParameter("mainId"));
		String title = StringUtil.getNotNullValueString(this.request.getParameter("title"));
		String groupNum = StringUtil.getNotNullValueString(this.request.getParameter("groupNum"));

		if("add".equals(op)){
			ExamGroup examGroup = new ExamGroup(); 
			examGroup.setTitle(title);
			examGroup.setGroupNum(Long.valueOf(groupNum));
			examGroup.setMainId(mainId);
			examGroupService.save(examGroup);
		}else if("update".equals(op)){
			ExamGroup examGroup = examGroupService.find(id);
			examGroup.setTitle(title);
			examGroup.setGroupNum(Long.valueOf(groupNum));
			examGroupService.save(examGroup);
		}else if("load".equals(op)){
			ExamGroup examGroup = examGroupService.find(id);
			this.request.setAttribute("examGroup", examGroup);
		}else if("delete".equals(op)){
			examGroupService.deleteById(id);
		}
		
		List<ExamGroup> list = this.examGroupService.findByMainId(mainId);
		this.request.setAttribute("list", list);
		return "success";
	}
	/**
	 * 删除选择题选项
	 * @return
	 */
	@Action(value="deleteOp")
	public String deleteOp(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));

		int r = examOptionService.deleteById(id);
		aw.writeAjax(String.valueOf(r));
		return null;
	}
	
	/**
	 * 我的试卷(显示所有开放的试卷)
	 * @return
	 */
	@Action(value="ulist",results={
			@Result(name="success",location="/exam/u_paper_list.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String ulist(){
		String title = StringUtil.getNotNullValueString(this.request.getParameter("title"));
		
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String pageNoString = this.request.getParameter("page");
		if(!StringUtils.isNotEmpty(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.id,t.title,t.cUser,t.cTime,(select count(q.id) from ExamQuestion q where q.removed = 0 and q.mainId = t.id) as total_q"+
					",(select count(u.id) from ExamUserMain u where u.removed = 0 and u.state = 1 and u.mainId = t.id and u.loginName = '"+loginName+"') as is_done"+
					" from ExamMain t where t.removed=0 and t.state=0";
		String countHql = "select count(t.id) from ExamMain t where t.removed=0 and t.state=0";
		
		if(!"".equals(title)){
			queryHql += " and t.title like '%"+title+"%'";
			countHql += " and t.title like '%"+title+"%'";
		}
		queryHql += " order by t.cTime desc";
		
		PageResultSet result = this.service.findPageResult(queryHql,countHql,page, pageSize);
		
		this.request.setAttribute("pageResultSet", result);
		this.request.setAttribute("title", title);
		return SUCCESS;
	}
	/**
	 * 统计分析列表
	 * @return
	 */
	@Action(value="rlist",results={
			@Result(name="success",location="/exam/r_paper_list.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String rlist(){
		String title = StringUtil.getNotNullValueString(this.request.getParameter("title"));
		
		String pageNoString = this.request.getParameter("page");
		if(!StringUtils.isNotEmpty(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.id,t.title,t.cUser,t.cTime,(select count(u.id) from ExamUserMain u where u.state = 1 and u.removed=0 and u.mainId = t.id) as total_user from ExamMain t where t.removed=0 and t.state=0";
		String countHql = "select count(t.id) from ExamMain t where t.removed=0 and t.state=0";
		
		if(!"".equals(title)){
			queryHql += " and t.title like '%"+title+"%'";
			countHql += " and t.title like '%"+title+"%'";
		}
		queryHql += " order by t.cTime desc";
		
		PageResultSet result = this.service.findPageResult(queryHql,countHql,page, pageSize);
		
		this.request.setAttribute("pageResultSet", result);		
		this.request.setAttribute("title", title);
		return SUCCESS;
	}

	/**
	 * 部门答题统计
	 * @return
	 */
	@Action(value="rDept",results={
			@Result(name="success",location="/exam/r_dept.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String rDept(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		this.request.setAttribute("report", service.getReport(id));
		return SUCCESS;
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
