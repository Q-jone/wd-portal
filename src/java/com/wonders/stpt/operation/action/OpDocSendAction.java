package com.wonders.stpt.operation.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.exam.entity.ExamMain;
import com.wonders.stpt.operation.entity.bo.FlowWorkThread;
import com.wonders.stpt.operation.entity.bo.OpDictionary;
import com.wonders.stpt.operation.entity.bo.OpDocSend;
import com.wonders.stpt.operation.service.OpDictionaryService;
import com.wonders.stpt.operation.service.OpDocSendService;
import com.wonders.stpt.operation.service.flowwork.FlowWorkThreadService;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/**
 * 运营发文 
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/opDocSend")
@Controller("opDocSendAction")
@Scope("prototype")
public class OpDocSendAction extends AbstractParamAction implements ModelDriven<OpDocSend> {

	
	private OpDocSend opDocSend = new OpDocSend();
	
	private PageResultSet<ExamMain> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);

	@Override
	public OpDocSend getModel() {
		return opDocSend;
	}

	public void setOpDocSend(OpDocSend opDocSend) {
		this.opDocSend = opDocSend;
	}

	public PageResultSet<ExamMain> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<ExamMain> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="selDept",results={
			@Result(name="success",location="/operation/docSend/deptSel.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String selDept(){
		
		List<OpDictionary> kjDepts = opDictionaryService.findByDescription("KJ");
		List<OpDictionary> jtDepts = opDictionaryService.findByDescription("JT");
		List<OpDictionary> xmDepts = opDictionaryService.findByDescription("XM");
		List<OpDictionary> zsDepts = opDictionaryService.findByDescription("ZS");
		
		request.setAttribute("kjDepts", kjDepts);
		request.setAttribute("jtDepts", jtDepts);
		request.setAttribute("xmDepts", xmDepts);
		request.setAttribute("zsDepts", zsDepts);
		
		return SUCCESS;
	}
	
	@Action(value="selLine",results={
			@Result(name="success",location="/operation/docSend/lineSel.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String selLine(){
		
		List<OpDictionary> xlDepts = opDictionaryService.findByType("XL");
		List<OpDictionary> occDepts = opDictionaryService.findByType("OCC");
		List<OpDictionary> zyDepts = opDictionaryService.findByType("ZY");
		
		request.setAttribute("xlDepts", xlDepts);
		request.setAttribute("occDepts", occDepts);
		request.setAttribute("zyDepts", zyDepts);
		
		return SUCCESS;
	}
	
	@Action(value="add",results={
			@Result(name="success",location="/operation/docSend/add.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String fileCode = service.genFileCode(deptId);
		
		request.setAttribute("fileCode", fileCode);

		List<OpDictionary> fileTypes;
		List<Object[]> leaders;
		
		String isUrgent = StringUtil.getNotNullValueString(this.request.getParameter("isUrgent"));
		if("1".equals(isUrgent)){
			fileTypes = opDictionaryService.findByParentAndDesc("1", "URGENT");
			leaders = this.service.getUrgentApprovers();
			request.setAttribute("isUrgent", "1");			
		}else{
			fileTypes = opDictionaryService.findByCode("FILE_TYPE_"+deptId);
			leaders = this.service.getDeptLeaders(deptId);
			request.setAttribute("isUrgent", "0");
		}
		
		request.setAttribute("fileTypes", fileTypes);
		request.setAttribute("leaders", leaders);
		request.setAttribute("today", DateUtil.getNowDate());
		
		return SUCCESS;
	}
	
	@Action(value="edit",results={
			@Result(name="success",location="/operation/docSend/edit.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String edit(){
		OpDocSend opDocSend = (OpDocSend)request.getAttribute("opDocSend");
		
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		List<OpDictionary> fileTypes;
		List<Object[]> leaders;
		if(opDocSend.getIsUrgent()==1){
			fileTypes = opDictionaryService.findByParentAndDesc("1", "URGENT");
			leaders = this.service.getUrgentApprovers();
		}else{
			fileTypes = opDictionaryService.findByCode("FILE_TYPE_"+deptId);
			leaders = this.service.getDeptLeaders(deptId);
		}

		if(!StringUtil.isNull(opDocSend.getFileType())){
			List<OpDictionary> fileSubTypes = opDictionaryService.findByParentId(opDocSend.getFileType());
			if(fileSubTypes != null && fileSubTypes.size() > 0){
				request.setAttribute("fileSubTypes", fileSubTypes);				
			}
		}
		
		request.setAttribute("fileTypes", fileTypes);
		request.setAttribute("leaders", leaders);
		
		return SUCCESS;
	}
	
	@Action(value="view",results={
			@Result(name="success",location="/operation/docSend/view.jsp"),
			@Result(name="edit",location="edit", type = "chain"),
			@Result(name="error",location="/404.jsp")
			})
	public String view(){
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		String deal = StringUtil.getNotNullValueString(this.request.getParameter("deal"));
		String print = StringUtil.getNotNullValueString(this.request.getParameter("print"));
		
		OpDocSend opDocSend = this.service.find(id);

		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		FlowWorkThread thread = this.flowWorkThreadService.findOngoingThread(opDocSend.getFlowGroup(), tloginName);

		request.setAttribute("opDocSend", opDocSend);
		request.setAttribute("thread", thread);
		request.setAttribute("deal", deal);
		request.setAttribute("print", print);
		
		fetchThreads(opDocSend.getFlowGroup(),request);
		
		if(!"n".equals(deal) && thread != null && thread.getDefOrderIndex() == 0){
			return "edit";
		}
		
		return SUCCESS;
	}
	
	@Action(value="startFlow",results={
			//@Result(name="success",location="/operation/docSend/view.jsp"),
			@Result( name = "success" , location = "view.action" , type = "redirectAction" , params = {"id","${opDocSend.id}"}),
			@Result(name="error",location="/404.jsp")
			})
	public String startFlow(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		String deptName = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_NAME);
		
		
		String now = DateUtil.getNowTime();
		this.opDocSend.setDeptId(deptId);
		this.opDocSend.setcUser(loginName);
		this.opDocSend.setuUser(loginName);
		this.opDocSend.setcTime(now);
		this.opDocSend.setuTime(now);
		this.opDocSend.setDeptName(deptName);
		this.opDocSend.setcUserName(userName);
		
		opDocSend = this.service.save(this.opDocSend,request);

		return SUCCESS;
	}
	
	@Action(value="dealFlow")
	public String dealFlow(){
		FlowWorkThread flowThread = new FlowWorkThread();
		
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		String threadId = StringUtil.getNotNullValueString(this.request.getParameter("threadId"));
		String choice = StringUtil.getNotNullValueString(this.request.getParameter("choice"));
		String suggest = StringUtil.getNotNullValueString(this.request.getParameter("suggest"));
		String contentAttMain = StringUtil.getNotNullValueString(this.request.getParameter("contentAttMain"));
		String attachId = StringUtil.getNotNullValueString(this.request.getParameter("attachId"));
		String attachCnt = StringUtil.getNotNullValueString(this.request.getParameter("attachCnt"));

        String isLeader = StringUtil.getNotNullValueString(this.request.getParameter("isLeader"));
		
		if(!"".equals(id) && !"".equals(contentAttMain)){
			this.service.updateAttMain(id, contentAttMain);
		}

        if(!"".equals(id) && !"".equals(isLeader)){
            this.service.updateIsLeader(id, isLeader);
        }
		
		if(!"".equals(threadId)){
			
			flowThread = flowWorkThreadService.find(Long.parseLong(threadId.toString()));//findOne(flowType.getId());
			if(flowThread == null || flowThread.getState()!=1){
				aw.writeAjax("{success: false,message:'保存失败，您可能与其他操作人操作冲突，请刷新页面后在进行操作！'}");
				return null;
			}
			
			flowThread.setOperationType(Long.parseLong(choice));
			flowThread.setContents(suggest);
			flowThread.setAttachGroup(attachId);
			flowThread.setAttachCnt(attachCnt);
			
			String now = DateUtil.getNowTime();
			flowThread.setEndTime(now);
			flowThread.setState(2);
			
			String message = flowWorkThreadService.saveThreadAndDoNext(flowThread,request);
			if(message==null){
				aw.writeAjax("{success: false,message:'请刷新后重试！'}");
				return null;				
			}
		}
		
		aw.writeAjax("{\"success\": \"true\"}");
		return null;
	}
	
	@Action(value="list",results={
			@Result(name="success",location="/operation/docSend/list.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String docTitle = StringUtil.getNotNullValueString(this.request.getParameter("docTitle"));
		String fileCode = StringUtil.getNotNullValueString(this.request.getParameter("fileCode"));
		String deptName = StringUtil.getNotNullValueString(this.request.getParameter("deptName"));
		String fileType = StringUtil.getNotNullValueString(this.request.getParameter("fileType"));
		String fileTypeSub = StringUtil.getNotNullValueString(this.request.getParameter("fileTypeSub"));
		String startDate = StringUtil.getNotNullValueString(this.request.getParameter("startDate"));
		String endDate = StringUtil.getNotNullValueString(this.request.getParameter("endDate"));
		String startVDate = StringUtil.getNotNullValueString(this.request.getParameter("startVDate"));
		String endVDate = StringUtil.getNotNullValueString(this.request.getParameter("endVDate"));
		String g = StringUtil.getNotNullValueString(this.request.getParameter("g"));

        //zhoushun 2014 12 10
        String sendStatus = StringUtil.getNotNullValueString(this.request.getParameter("sendStatus"));
		
		String pageNoString = this.request.getParameter("page");
		if(!StringUtils.isNotEmpty(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.id,t.pass_date,t.file_code,t.pub_date,t.valid_date,t.doc_title,t.send_main_w,t.send_line_w,t.remark,t.pass_cnt"
				+",CASE WHEN t.sent_depts is not null THEN (select count(p.id) from T_DEPT_PASS p where p.MAIN_ID = t.id) ELSE -1 END cnt"
				+",CASE WHEN t.valid_date<'"+DateUtil.getNowDate()+"' THEN 0 ELSE 1 END isValid,t.c_time,t.apply_date,t.FILE_TYPE_SUB,t.FILE_TYPE,t.DEPT_NAME,t.sent_depts,t.dept_id"
				+" from OP_DOC_SEND t where t.removed=0";
        queryHql = "select * from ("+queryHql+") t where 1=1 ";
		String countHql = "select count(t.id) from (" +queryHql+ ") t where 1=1";

		String filter = "";
		
		if("dept".equals(g)){
			filter += " and (t.dept_id = '"+deptId+"' or instr(t.sent_depts,'"+deptId+"')>0)";
		}else if("manager".equals(g)){
			filter += " and exists(select f.id from OP_FLOWWORK_THREAD f where f.FLOW_UID = t.FLOW_GROUP and f.USER_ID like '"+tloginName+"%')";
		}else if("admin".equals(g)){
			
		}else{
			filter += " and 1=2";
		}
		
		if(!"".equals(docTitle)){
			queryHql += " and t.doc_title like '%"+docTitle+"%'";
			countHql += " and t.doc_title like '%"+docTitle+"%'";
		}
		if(!"".equals(fileCode)){
			queryHql += " and t.file_code like '%"+fileCode+"%'";
			countHql += " and t.file_code like '%"+fileCode+"%'";
		}
		if(!"".equals(deptName)){
			queryHql += " and t.dept_name like '%"+deptName+"%'";
			countHql += " and t.dept_name like '%"+deptName+"%'";
		}
		if(!"".equals(fileType)){
			queryHql += " and t.file_type = '"+fileType+"'";
			countHql += " and t.file_type = '"+fileType+"'";
		}
		if(!"".equals(fileTypeSub)){
			queryHql += " and t.file_type_sub = '"+fileTypeSub+"'";
			countHql += " and t.file_type_sub = '"+fileTypeSub+"'";
		}		
		if(!"".equals(startDate)){
			queryHql += " and t.apply_date >= '"+startDate+"'";
			countHql += " and t.apply_date >= '"+startDate+"'";
		}
		if(!"".equals(endDate)){
			queryHql += " and t.apply_date <= '"+endDate+" 23:59:59'";
			countHql += " and t.apply_date <= '"+endDate+" 23:59:59'";
		}
		if(!"".equals(startVDate)){
			queryHql += " and t.valid_date >= '"+startVDate+"'";
			countHql += " and t.valid_date >= '"+startVDate+"'";
		}
		if(!"".equals(endVDate)){
			queryHql += " and t.valid_date <= '"+endVDate+"'";
			countHql += " and t.valid_date <= '"+endVDate+"'";
		}
        if(!"".equals(sendStatus)){
            queryHql += " and t.isValid = '"+sendStatus+"'";
            countHql += " and t.isValid = '"+sendStatus+"'";
        }
		
		
		queryHql += filter;
		countHql += filter;
		//queryHql += " order by CASE WHEN cnt=t.pass_cnt THEN 1 ELSE 0 END desc,NVL(t.pass_date,'1') desc";
		queryHql += " order by NVL(t.pass_date,'1') desc,t.c_time desc";
		
		PageResultSet result = this.service.findPageResult(queryHql,countHql,page, pageSize);
		
		List<OpDictionary> fileTypes = opDictionaryService.findByType("FILE_TYPE");
		request.setAttribute("fileTypes", fileTypes);
		if(!StringUtil.isNull(fileType)){
			List<OpDictionary> fileSubTypes = opDictionaryService.findByParentId(fileType);
			if(fileSubTypes != null && fileSubTypes.size() > 0){
				request.setAttribute("fileSubTypes", fileSubTypes);				
			}
		}
		
		this.request.setAttribute("pageResultSet", result);
		this.request.setAttribute("docTitle", docTitle);
		this.request.setAttribute("fileCode", fileCode);
		this.request.setAttribute("deptName", deptName);
		this.request.setAttribute("fileType", fileType);
		this.request.setAttribute("fileTypeSub", fileTypeSub);
		this.request.setAttribute("startDate", startDate);
		this.request.setAttribute("endDate", endDate);
		this.request.setAttribute("startVDate", startVDate);
		this.request.setAttribute("endVDate", endVDate);		
		this.request.setAttribute("g", g);
		this.request.setAttribute("today", DateUtil.getNowDate());
        this.request.setAttribute("sendStatus",sendStatus);
		return SUCCESS;
	}
	
	@Action("getReceipt")
	public String getReceipt(){
		String id = StringUtil.getNotNullValueString(this.request.getParameter("id"));
		OpDocSend opDocSend = this.service.find(id);
		
		Map signMap = new HashMap();
		List results = new ArrayList();
		if(opDocSend != null && !StringUtil.isNull(opDocSend.getSentDepts())){
			String toLeaderDepts = "";
			List<OpDictionary> toLeaders = opDictionaryService.findByType("TO_LEADER");
			if(toLeaders != null && toLeaders.size() > 0){
				toLeaderDepts = toLeaders.get(0).getName();
			}
			
			String sentDepts = opDocSend.getSentDepts();
			String[] detps = sentDepts.split(",");
			
			for(String deptId : detps){
				Map<String,String> signResult = new HashMap<String,String>();
	        	if(toLeaderDepts.indexOf(deptId) < 0){
	        		Object[] receiver = this.opDictionaryService.getReceiver(deptId);
	        		signResult.put("dept",(String)receiver[1]);
	        		signResult.put("receiver",(String)receiver[2]);
	        	}else{
	        		Object[] leader = this.opDictionaryService.getLeader(deptId);
	        		signResult.put("dept",(String)leader[1]);
	        		signResult.put("receiver",(String)leader[2]);	        		
	        	}
	        	Object[] readInfo = this.service.getReadInfo(id, deptId);
        		if(readInfo != null){
	        		signResult.put("receiver",(String)readInfo[0]);	        		
	        		signResult.put("isread","已读");
	        		signResult.put("readtime",(String)readInfo[2]);
        		}else{
	        		signResult.put("isread","未读");
	        		signResult.put("readtime","");        			
        		}
        		
        		results.add(signResult);
			}
			
			signMap.put("results", results);
		}

		signMap.put("docTitle", opDocSend.getDocTitle());
		signMap.put("fileCode", opDocSend.getFileCode());
		signMap.put("cTime", opDocSend.getPassDate());
		
		this.aw.writeJson(JSONObject.fromObject(signMap));
		return null;
	}
	
	private void fetchThreads(String flowUid, HttpServletRequest request){
		List<FlowWorkThread> list = this.flowWorkThreadService.findThreadsByFlowUid(flowUid);
		List<FlowWorkThread> approveList = new ArrayList<FlowWorkThread>();
		
		if(list != null && list.size() > 0){
			for(FlowWorkThread thread : list){
				if(thread.getOrderIndex() == 0){
					request.setAttribute("startThread", thread);
				}else if("运营发文套头".equals(thread.getNodeName())){
					request.setAttribute("endThread", thread);
				}else if("运营发文审批".equals(thread.getNodeName()) && thread.getState() == 2){
					approveList.add(thread);
				}
			}
			request.setAttribute("approveThreads", approveList);
		}
		
	}
	
	/**
	 * 获取二级文件类型
	 * @return
	 */
	@Action(value="subFileTypeList")
	public String subFileTypeList(){
		String pid = StringUtil.getNotNullValueString(this.request.getParameter("pid"));
		String desc = StringUtil.getNotNullValueString(this.request.getParameter("desc"));
		
		List<OpDictionary> fileTypes;
		if(!"".equals(desc)){
			fileTypes = opDictionaryService.findByParentAndDesc(pid, desc);
		}else{
			fileTypes = opDictionaryService.findByParentId(pid);
		}
		
		JSONArray jso = JSONArray.fromObject(fileTypes);
		
		aw.writeAjax(jso.toString());
		return null;
	}
	
	@Action(value="getNodesInfo")
	public String getNodesInfo(){
		String typecode = StringUtil.getNotNullValueString(this.request.getParameter("typecode"));
		List<OpDictionary> nodes = opDictionaryService.findByType(typecode);
		
		if(nodes != null && nodes.size() > 0){
			JSONObject jso = JSONObject.fromObject(nodes.get(0));
			aw.writeAjax(jso.toString());			
		}
		return null;
	}
	
	private OpDocSendService service;
	public OpDocSendService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("opDocSendService")OpDocSendService service) {
		this.service = service;
	}
	
	private OpDictionaryService opDictionaryService;
	public OpDictionaryService getOpDictionaryService() {
		return opDictionaryService;
	}
	@Autowired(required=false)
	public void setOpDictionaryService(@Qualifier("opDictionaryService")OpDictionaryService opDictionaryService) {
		this.opDictionaryService = opDictionaryService;
	}
	
	private FlowWorkThreadService flowWorkThreadService;
	public FlowWorkThreadService getFlowWorkThreadService() {
		return flowWorkThreadService;
	}
	@Autowired(required=false)
	public void setFlowWorkThreadService(@Qualifier("flowWorkThreadService")FlowWorkThreadService flowWorkThreadService) {
		this.flowWorkThreadService = flowWorkThreadService;
	}

	public OpDocSend getOpDocSend() {
		return opDocSend;
	}

}
