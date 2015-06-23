package com.wonders.stpt.beforeBuild.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.beforeBuild.model.bo.Report;
import com.wonders.stpt.beforeBuild.model.bo.ShortMsg;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/report")
@Controller("reportAction")
@Scope("prototype")
public class ReportAction implements ModelDriven<Report>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Report report = new Report();

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	public Report getModel(){
		return report;
	}
	
	@Action(value="showMessage")
	public String showMessage(){
		String oldDeptId = request.getParameter("oldDeptId");
		String role = request.getParameter("role");
		List<Object> src = new ArrayList<Object>();
		src.add(role);
		String sql = "select t.id,t.type,t.content,t.monomer_plan_id from t_before_report t "+
			" where t.removed = '0' and t.status = '0' and t.role = ? ";
		if("1".equals(role)){
			sql += "and t.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		sql += " order by t.receive_time";
		ac.writeJson(beforeBuildService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="readMessage")
	public String readMessage(){
		String id = request.getParameter("id");
		String sql = "update t_before_report set status = '1',read_time = ? where id = ? ";
		List<Object> src = new ArrayList<Object>();
		src.add(df.format(new Date()));
		src.add(id);
		beforeBuildService.updateBySql(sql, src);
		return null;
	}
	
	@Action(value="pushMessage")
	public String pushMessage(){
		String id = request.getParameter("id");
		//String name = (String)session.getAttribute("userName");
		String content = request.getParameter("content");
		String dsql = "select t.phone,t1.paper_name,t1.plan_finish_time from t_before_user t,t_before_monomer_plan t1 "+
			" where t.removed = '0' and t1.removed = '0' and t1.id = ? "+
			" and t.role = '1' and t.name = t1.main_person ";
		List<Object> src = new ArrayList<Object>();
		src.add(id);
		List<Object[]> dlist = beforeBuildService.findBySql(dsql, src);
		if(dlist!=null&&dlist.size()>0){
			ShortMsg msg = new ShortMsg();
			msg.setMobile(dlist.get(0)[0]+"");
			msg.setStatus("0");
			msg.setContent(content);
			beforeBuildService.save(msg);
			ac.writeAjax("{\"if_success\":\"yes\"}");
		}else{
			ac.writeAjax("{\"if_success\":\"no\"}");
		}
		return null;
	}
	
	@Action(value="findReportByPage",results={@Result(name="success",location="/beforeBuild/report/list.jsp")})
	public String findReportByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String role = StringUtil.getNotNullValueString(request.getParameter("role"));
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		request.setAttribute("role", role);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_report t where t.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(status!=null&&status.length()>0){
			baseSql += " and t.status = ? ";
			src.add(status);
		}
		if(type!=null&&type.length()>0){
			baseSql += " and t.type = ? ";
			src.add(type);
		}
		if(content!=null&&content.length()>0){
			baseSql += " and t.content like ? ";
			src.add("%"+content+"%");
		}
		if(role!=null&&role.length()>0){
			baseSql += " and t.role = ? ";
			src.add(role);
		}
		if(oldDeptId!=null&&oldDeptId.length()>0){
			baseSql += " and t.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		
		String orderSql = "";
		String orderParam = request.getParameter("orderParam");
		String orderValue = request.getParameter("orderValue");
		request.setAttribute("orderParam", orderParam);
		request.setAttribute("orderValue", orderValue);
		if(orderParam==null||orderParam.length()==0){
			orderSql = " order by create_time desc ";
		}else{
			orderSql = " order by "+orderParam;
			if(orderValue.length()>0){
				orderSql += " "+ orderValue;
			}
		}
		String listSql = "select t.* " + baseSql + orderSql;
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		return "success";
	}
	
	//业务提醒，每天一次
	@Action(value="sendMessage")
	public String sendMessage(){
//		ShortMsg msg = new ShortMsg();
//		msg.setMobile("13601753981");
//		msg.setStatus("0");
//		msg.setContent("测试");
//		msg.setSenddate("2014-04-24 16:45:00");
//		beforeBuildService.save(msg);
		
		List<Report> saveList = new ArrayList<Report>();
		List<ShortMsg> msgList = new ArrayList<ShortMsg>();
		Date date = new Date();
		String dateStr = df.format(date);
		String sql = "";
		List<Object[]> list = new ArrayList<Object[]>();
		
//		//1.1.当前日期已到开始办证提醒日期时，自动向执行者发送短信（发送规则同协同平台） ，提醒执行者开始办证
//		sql = "select t.task_name,t.old_dept_id from t_before_task t where t.warn_time = to_char(sysdate,'yyyy-mm-dd') "+
//				" and t.real_start_time is null and t.removed = '0' ";
//		list = beforeBuildService.findBySql(sql, null);
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				Report report = new Report();
//				report.setType("前期办证");
//				report.setReceiveTime(dateStr);
//				report.setContent("您有一条计划任务\""+list.get(i)[0]+"\"需开始办证。");
//				report.setStatus("0");
//				report.setRemoved("0");
//				report.setCreateTime(dateStr);
//				report.setOldDeptId(list.get(i)[1]+"");
//				report.setRole("1");
//				saveList.add(report);
//			}
//		}
//		
//		//1.2.当前日期 > 开始办证日期，且尚未开始办证时，系统主动推送办证延误信息
//		sql = "select t.task_name,t.old_dept_id,t1.if_node,t1.if_milestone,t1.phone "+
//			 " from t_before_task t,t_before_monomer_plan t1 "+
//			 " where t.plan_start_time < to_char(sysdate,'yyyy-mm-dd') "+
//			   " and t.removed = '0' and t.real_start_time is null "+
//			   " and t.monomer_plan_id = t1.id and t1.removed = '0'";
//		list = beforeBuildService.findBySql(sql, null);
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				if(list.get(i)[1]!=null&&!"".equals(list.get(i)[1])&&!"null".equals(list.get(i)[1])&&list.get(i)[0]!=null&&!"".equals(list.get(i)[0])&&!"null".equals(list.get(i)[0])){
//					String content = "计划任务\""+list.get(i)[0]+"\"尚未开始办证，已延误。";
//					String dsql = "select * from t_before_report t where t.removed = '0' and t.status = '0' and t.old_dept_id = '"+list.get(i)[1]+"' and t.content = '"+content+"' ";
//					List<Object[]> dlist = beforeBuildService.findBySql(dsql+" and t.role = '1' ", null);
//					if(dlist==null||dlist.size()==0){
//						Report report = new Report();
//						report.setType("前期办证");
//						report.setReceiveTime(dateStr);
//						report.setContent(content);
//						report.setStatus("0");
//						report.setRemoved("0");
//						report.setCreateTime(dateStr);
//						report.setOldDeptId(list.get(i)[1]+"");
//						report.setRole("1");
//						saveList.add(report);
//					}
//					
//					if("1".equals(list.get(i)[3])){
//						dlist = beforeBuildService.findBySql(dsql+" and t.role = '2' ", null);
//						if(dlist==null||dlist.size()==0){
//							report = new Report();
//							report.setType("前期办证");
//							report.setReceiveTime(dateStr);
//							report.setContent(content);
//							report.setStatus("0");
//							report.setRemoved("0");
//							report.setCreateTime(dateStr);
//							report.setOldDeptId(list.get(i)[1]+"");
//							report.setRole("2");
//							saveList.add(report);
//						}
//						
//						dlist = beforeBuildService.findBySql(dsql+" and t.role = '3' ", null);
//						if(dlist==null||dlist.size()==0){
//							report = new Report();
//							report.setType("前期办证");
//							report.setReceiveTime(dateStr);
//							report.setContent(content);
//							report.setStatus("0");
//							report.setRemoved("0");
//							report.setCreateTime(dateStr);
//							report.setOldDeptId(list.get(i)[1]+"");
//							report.setRole("3");
//							saveList.add(report);
//						}
//					}else if("1".equals(list.get(i)[2])){
//						dlist = beforeBuildService.findBySql(dsql+" and t.role = '2' ", null);
//						if(dlist==null||dlist.size()==0){
//							report = new Report();
//							report.setType("前期办证");
//							report.setReceiveTime(dateStr);
//							report.setContent(content);
//							report.setStatus("0");
//							report.setRemoved("0");
//							report.setCreateTime(dateStr);
//							report.setOldDeptId(list.get(i)[1]+"");
//							report.setRole("2");
//							saveList.add(report);
//						}
//					}
//				}
//			}
//		}
//		
//		//1.4.当前序证件延误日期数 > 后续证件的冗余天数时，系统主动推送办证延误导致后续证件延误信息
////		sql = "select t.monomer_name,t.rule_version_id,t2.paper_name,t2.pre_paper_name, "+
////			" t1.real_start_time,t1.real_finish_time,t2.mini_days,t2.valid_days "+
////			" from t_before_monomer_plan t,t_before_task t1,t_before_rule_detail t2 "+
////			" where t.rule_version_id = t2.rule_version_id and t.paper_id = t2.paper_id "+ 
////			" and t.id = t1.monomer_plan_id "+
////			" and t.removed = '0' and t1.removed = '0' and t2.removed = '0'";
////		list = beforeBuildService.findBySql(sql, null);
////		if(list!=null&&list.size()>0){
////			for(int i=0;i<list.size();i++){
////				for(int j=0;j<list.size();j++){
////					if(i!=j&&(list.get(i)[0]+""+list.get(i)[1]+list.get(i)[3]+"").equals(list.get(j)[0]+""+list.get(j)[1]+list.get(j)[2]+"")){
////						
////					}
////				}
////			}
////		}
//		
//		
//		//1.5.办证时，主动向执行者推送“办证”知道信息，如：办证流程、需填写哪些表格、需要提供什么材料、注意事项等
////		sql = "select t1.paper_name,t2.id,t.old_dept_id from t_before_task t,t_before_monomer_plan t1,t_before_knowledge t2 "+
////			" where t.real_start_time = to_char(sysdate,'yyyy-mm-dd') "+
////			" and t.removed = '0' and t1.removed = '0' and t2.removed = '0' "+
////			" and t.monomer_plan_id = t1.id and t2.paper_id = t1.paper_id";
////		list = beforeBuildService.findBySql(sql, null);
////		if(list!=null&&list.size()>0){
////			for(int i=0;i<list.size();i++){
////				Report report = new Report();
////				report.setType("前期办证");
////				report.setReceiveTime(dateStr);
////				report.setContent("证件\""+list.get(i)[0]+"\"有知识库可供参考：<a href=\"/portal/knowledge/toEdit.action?id="+list.get(i)[1]+"&oldDeptId="+list.get(i)[2]+"\" target=\"_blank\">点此查看</a>。");
////				report.setStatus("0");
////				report.setRemoved("0");
////				report.setCreateTime(dateStr);
////				report.setOldDeptId(list.get(i)[2]+"");
////				report.setRole("1");
////				saveList.add(report);
////			}
////		}
//		
//		//1.6.本月新增证件数
//		sql = "select old_dept_id,count(*) from ( "+
//			" select distinct t1.monomer_id,t1.paper_id,t.old_dept_id from t_before_task t,t_before_monomer_plan t1 "+
//			" where substr(t.real_start_time,0,7) = to_char(sysdate,'yyyy-mm') "+
//			" and t.removed = '0' and t1.removed = '0' and t.monomer_plan_id = t1.id "+
//			" ) group by old_dept_id";
//		list = beforeBuildService.findBySql(sql, null);
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				if(list.get(i)[0]!=null&&!"".equals(list.get(i)[0])&&!"null".equals(list.get(i)[0])){
//					String content = "本月新增证件数："+list.get(i)[1];
//					String dsql = "select * from t_before_report t where t.removed = '0' and t.status = '0' and substr(t.create_time,0,7) = to_char(sysdate,'yyyy-mm') and t.old_dept_id = '"+list.get(i)[0]+"' and t.content = '"+content+"' ";
//					List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
//					if(dlist==null||dlist.size()==0){
//						Report report = new Report();
//						report.setType("前期办证");
//						report.setReceiveTime(dateStr);
//						report.setContent(content);
//						report.setStatus("0");
//						report.setRemoved("0");
//						report.setCreateTime(dateStr);
//						report.setOldDeptId(list.get(i)[0]+"");
//						report.setRole("2");
//						saveList.add(report);
//					}
//				}
//			}
//		}
//		
//		//3.2.当前日期 > 计划完成日期，且尚未完成时，系统主动推送办证实际延误信息
//		sql = "select t.task_name,t.old_dept_id,t1.if_node,t1.if_milestone "+
//			 " from t_before_task t,t_before_monomer_plan t1 "+
//			 " where t.plan_finish_time < to_char(sysdate,'yyyy-mm-dd') "+
//			   " and t.removed = '0' and t.real_finish_time is null "+
//			   " and t.monomer_plan_id = t1.id and t1.removed = '0'";
//		list = beforeBuildService.findBySql(sql, null);
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				if(list.get(i)[1]!=null&&!"".equals(list.get(i)[1])&&!"null".equals(list.get(i)[1])&&list.get(i)[0]!=null&&!"".equals(list.get(i)[0])&&!"null".equals(list.get(i)[0])){
//					String content = "计划任务\""+list.get(i)[0]+"\"尚未完成办证，已延误。";
//					String dsql = "select * from t_before_report t where t.removed = '0' and t.status = '0' and t.old_dept_id = '"+list.get(i)[1]+"' and t.content = '"+content+"' ";
//					List<Object[]> dlist = beforeBuildService.findBySql(dsql+" and t.role = '1' ", null);
//					if(dlist==null||dlist.size()==0){
//						Report report = new Report();
//						report.setType("前期办证");
//						report.setReceiveTime(dateStr);
//						report.setContent(content);
//						report.setStatus("0");
//						report.setRemoved("0");
//						report.setCreateTime(dateStr);
//						report.setOldDeptId(list.get(i)[1]+"");
//						report.setRole("1");
//						saveList.add(report);
//					}
//					
//					if("1".equals(list.get(i)[3])){
//						dlist = beforeBuildService.findBySql(dsql+" and t.role = '2' ", null);
//						if(dlist==null||dlist.size()==0){
//							report = new Report();
//							report.setType("前期办证");
//							report.setReceiveTime(dateStr);
//							report.setContent(content);
//							report.setStatus("0");
//							report.setRemoved("0");
//							report.setCreateTime(dateStr);
//							report.setOldDeptId(list.get(i)[1]+"");
//							report.setRole("2");
//							saveList.add(report);
//						}
//						
//						dlist = beforeBuildService.findBySql(dsql+" and t.role = '3' ", null);
//						if(dlist==null||dlist.size()==0){
//							report = new Report();
//							report.setType("前期办证");
//							report.setReceiveTime(dateStr);
//							report.setContent(content);
//							report.setStatus("0");
//							report.setRemoved("0");
//							report.setCreateTime(dateStr);
//							report.setOldDeptId(list.get(i)[1]+"");
//							report.setRole("3");
//							saveList.add(report);
//						}
//					}else if("1".equals(list.get(i)[2])){
//						dlist = beforeBuildService.findBySql(dsql+" and t.role = '2' ", null);
//						if(dlist==null||dlist.size()==0){
//							report = new Report();
//							report.setType("前期办证");
//							report.setReceiveTime(dateStr);
//							report.setContent(content);
//							report.setStatus("0");
//							report.setRemoved("0");
//							report.setCreateTime(dateStr);
//							report.setOldDeptId(list.get(i)[1]+"");
//							report.setRole("2");
//							saveList.add(report);
//						}
//					}
//				}
//			}
//		}
//		
//		//3.5.本月办结证件数
//		sql = "select old_dept_id,count(*) from ( "+
//			" select distinct t1.monomer_id,t1.paper_id,t.old_dept_id from t_before_task t,t_before_monomer_plan t1 "+
//			" where substr(t.real_finish_time,0,7) = to_char(sysdate,'yyyy-mm') and t.status = '4' "+
//			" and t.removed = '0' and t1.removed = '0' and t.monomer_plan_id = t1.id "+
//			" ) group by old_dept_id";
//		list = beforeBuildService.findBySql(sql, null);
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				if(list.get(i)[0]!=null&&!"".equals(list.get(i)[0])&&!"null".equals(list.get(i)[0])){
//					String content = "本月办结证件数："+list.get(i)[1];
//					String dsql = "select * from t_before_report t where t.removed = '0' and t.status = '0' and substr(t.create_time,0,7) = to_char(sysdate,'yyyy-mm') and t.old_dept_id = '"+list.get(i)[0]+"' and t.content = '"+content+"' ";
//					List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
//					if(dlist==null||dlist.size()==0){
//						Report report = new Report();
//						report.setType("前期办证");
//						report.setReceiveTime(dateStr);
//						report.setContent(content);
//						report.setStatus("0");
//						report.setRemoved("0");
//						report.setCreateTime(dateStr);
//						report.setOldDeptId(list.get(i)[0]+"");
//						report.setRole("2");
//						saveList.add(report);
//					}
//				}
//			}
//		}
//		
//		//3.6.当前完成的证件为节点或里程碑证件（施工证）时，需向管理层汇报
//		sql = "select t.task_name,t.old_dept_id,t1.if_node,t1.if_milestone,t1.monomer_name,t1.paper_name " +
//			" from t_before_task t,t_before_monomer_plan t1 "+
//			" where t.monomer_plan_id = t1.id and t.removed = '0' and t1.removed = '0' "+
//			" and t.real_finish_time = to_char(sysdate-1,'yyyy-mm-dd') and t.status = '4' ";
//		list = beforeBuildService.findBySql(sql, null);
//		if(list!=null&&list.size()>0){
//			for(int i=0;i<list.size();i++){
//				if("1".equals(list.get(i)[2])){
//					Report report = new Report();
//					report.setType("前期办证");
//					report.setReceiveTime(dateStr);
//					report.setContent(list.get(i)[4]+"的"+list.get(i)[5]+"证件已完成办证。");
//					report.setStatus("0");
//					report.setRemoved("0");
//					report.setCreateTime(dateStr);
//					report.setOldDeptId(list.get(i)[0]+"");
//					report.setRole("2");
//					saveList.add(report);
//				}else if("1".equals(list.get(i)[3])){
//					Report report = new Report();
//					report.setType("前期办证");
//					report.setReceiveTime(dateStr);
//					report.setContent(list.get(i)[4]+"的"+list.get(i)[5]+"证件已完成办证。");
//					report.setStatus("0");
//					report.setRemoved("0");
//					report.setCreateTime(dateStr);
//					report.setOldDeptId(list.get(i)[0]+"");
//					report.setRole("2");
//					saveList.add(report);
//					
//					report = new Report();
//					report.setType("前期办证");
//					report.setReceiveTime(dateStr);
//					report.setContent(list.get(i)[4]+"的"+list.get(i)[5]+"证件已完成办证。");
//					report.setStatus("0");
//					report.setRemoved("0");
//					report.setCreateTime(dateStr);
//					report.setOldDeptId(list.get(i)[0]+"");
//					report.setRole("3");
//					saveList.add(report);
//				}
//			}
//		}
		
		//待办提醒
		sql = "select t.paper_name,t.old_dept_id,t.main_person,t.id from t_before_monomer_plan t where removed = '0' and check_status = '3' "+
			" and to_char(to_date(t.plan_start_time,'yyyy-mm-dd')-(case when warn_days is null then 0 else to_number(warn_days) end),'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')";
		list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String content = "【待办】您有“"+list.get(i)[0]+"证件”待办理，请关注！";
				Report report = new Report();
				report.setType("前期办证");
				report.setReceiveTime(dateStr);
				report.setContent(content);
				report.setStatus("0");
				report.setRemoved("0");
				report.setCreateTime(dateStr);
				report.setOldDeptId(list.get(i)[1]+"");
				report.setRole("1");
				report.setMonomer_plan_id(list.get(i)[3]+"");
				saveList.add(report);
				
				String dsql = "select phone from t_before_user where removed = '0' and name = '"+list.get(i)[2]+"' and old_dept_id = '"+list.get(i)[1]+"' and role = '1' ";
				List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
				if(dlist!=null&&dlist.size()>0){
					ShortMsg msg = new ShortMsg();
					msg.setMobile(dlist.get(0)+"");
					msg.setStatus("0");
					msg.setContent(content);
					msgList.add(msg);
				}
			}
		}
		
		//新增提醒
		if(date.getDate()==1){
			sql = "select t.old_dept_id,count(*) from t_before_monomer_plan t where t.removed = '0' "+
				" and t.plan_finish_time like to_char(sysdate,'yyyy-mm')||'%' group by old_dept_id";
			list = beforeBuildService.findBySql(sql, null);
			if(list!=null&&list.size()>0){
				int count_sum = 0;
				for(int i=0;i<list.size();i++){
					String content = "【新增】本月新增 ["+list.get(i)[1]+"]个证照办理任务";
					Report report = new Report();
					report.setType("前期办证");
					report.setReceiveTime(dateStr);
					report.setContent(content);
					report.setStatus("0");
					report.setRemoved("0");
					report.setCreateTime(dateStr);
					report.setOldDeptId(list.get(i)[0]+"");
					report.setRole("5");
					saveList.add(report);
					count_sum += Integer.parseInt(String.valueOf(list.get(i)[1]));
					
					String dsql = "select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[0]+"' and role = '5' ";
					List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
					if(dlist!=null&&dlist.size()>0){
						ShortMsg msg = new ShortMsg();
						msg.setMobile(dlist.get(0)+"");
						msg.setStatus("0");
						msg.setContent(content);
						msgList.add(msg);
					}
				}
				String content = "【新增】本月新增 ["+count_sum+"]个证照办理任务";
				Report report = new Report();
				report.setType("前期办证");
				report.setReceiveTime(dateStr);
				report.setContent(content);
				report.setStatus("0");
				report.setRemoved("0");
				report.setCreateTime(dateStr);
				report.setRole("2");
				saveList.add(report);
				
				String dsql = "select phone from t_before_user where removed = '0' and role = '2' ";
				List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
				if(dlist!=null&&dlist.size()>0){
					ShortMsg msg = new ShortMsg();
					msg.setMobile(dlist.get(0)+"");
					msg.setStatus("0");
					msg.setContent(content);
					msgList.add(msg);
				}
			}
		}
		
		//办结提醒
		sql = "select t1.plan_finish_time,t1.old_dept_id,t1.paper_name,t1.id from t_before_task t,t_before_monomer_plan t1 "+ 
	       " where t.removed = '0' and t1.removed = '0' and t.monomer_plan_id = t1.id "+
	       " and t.real_finish_time=to_char(sysdate-1,'yyyy-mm-dd')";
		list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String content = "【办结】“"+list.get(i)[2]+"证件”计划于“"+list.get(i)[0]+"”完成办证，现已办结！";
				for(int j=1;j<6;j++){
					if(j==2||j==3||j==5){
						Report report = new Report();
						report.setType("前期办证");
						report.setReceiveTime(dateStr);
						report.setContent(content);
						report.setStatus("0");
						report.setRemoved("0");
						report.setCreateTime(dateStr);
						report.setOldDeptId(list.get(i)[1]+"");
						report.setRole(String.valueOf(j));
						report.setMonomer_plan_id(list.get(i)[3]+"");
						saveList.add(report);
					}
				}
				
				String dsql = "select phone from t_before_user where removed = '0' and (role = '2' or role = '3') " +
						" union all select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[1]+"' and role = '5'";
				List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
				if(dlist!=null&&dlist.size()>0){
					for(int j=0;j<dlist.size();j++){
						ShortMsg msg = new ShortMsg();
						msg.setMobile(dlist.get(j)+"");
						msg.setStatus("0");
						msg.setContent(content);
						msgList.add(msg);
					}
				}
			}
		}
		
		//过期提醒
		sql = "select t.invalid_finish_time,t1.old_dept_id,t1.main_person,t1.paper_name,t1.id from t_before_task t,t_before_monomer_plan t1 "+
		       " where t.removed = '0' and t1.removed = '0' and t.monomer_plan_id = t1.id "+
		       " and to_char(sysdate+3,'yyyy-mm-dd') = t.invalid_finish_time";
		list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String content = "【过期】“"+list.get(i)[3]+"证件”即将于“"+list.get(i)[0]+"”过期，请关注！";
				for(int j=1;j<6;j++){
					Report report = new Report();
					report.setType("前期办证");
					report.setReceiveTime(dateStr);
					report.setContent(content);
					report.setStatus("0");
					report.setRemoved("0");
					report.setCreateTime(dateStr);
					report.setOldDeptId(list.get(i)[1]+"");
					report.setRole(String.valueOf(j));
					report.setMonomer_plan_id(list.get(i)[4]+"");
					saveList.add(report);
				}
				
				String dsql = "select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[1]+"' and name = '"+list.get(i)[2]+"' and role = '1' " +
						" union all select phone from t_before_user where removed = '0' and (role = '2' or role = '3') " +
						" union all select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[1]+"' and (role = '4' or role = '5')";
				List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
				if(dlist!=null&&dlist.size()>0){
					for(int j=0;j<dlist.size();j++){
						ShortMsg msg = new ShortMsg();
						msg.setMobile(dlist.get(j)+"");
						msg.setStatus("0");
						msg.setContent(content);
						msgList.add(msg);
					}
				}
			}
		}
		
		//warmFunc(1,saveList,msgList);
		if(saveList!=null&&saveList.size()>0){
			beforeBuildService.saveOrUpdateAll(saveList);
		}
		if(msgList!=null&&msgList.size()>0){
			beforeBuildService.saveOrUpdateAll(msgList);
		}
		
		return null;
	}
	
	
	
	//业务提醒，每3天一次
	@Action(value="sendMessage2")
	public String sendMessage2(){
		
		List<Report> saveList = new ArrayList<Report>();
		List<ShortMsg> msgList = new ArrayList<ShortMsg>();
		Date date = new Date();
		String dateStr = df.format(date);
		String sql = "";
		List<Object[]> list = new ArrayList<Object[]>();
		
		
		//审核提醒
		sql = "select t.old_dept_id,t.check_status,count(*) from t_before_monomer_plan t where t.removed = '0' "+
			" and (t.check_status = '1' or t.check_status = '2') group by old_dept_id,check_status";
		list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String content = "【审核】您有["+list.get(i)[2]+"]项“前期办证业务”待审核，请关注！";
				String role = "";
				if("1".equals(list.get(i)[1]+"")){
					role = "4";
				}else if("2".equals(list.get(i)[1]+"")){
					role = "5";
				}
				Report report = new Report();
				report.setType("前期办证");
				report.setReceiveTime(dateStr);
				report.setContent(content);
				report.setStatus("0");
				report.setRemoved("0");
				report.setCreateTime(dateStr);
				report.setOldDeptId(list.get(i)[0]+"");
				report.setRole(role);
				saveList.add(report);
				
				String dsql = "select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[0]+"' and role = '"+role+"' ";
				List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
				if(dlist!=null&&dlist.size()>0){
					for(int j=0;j<dlist.size();j++){
						ShortMsg msg = new ShortMsg();
						msg.setMobile(dlist.get(j)+"");
						msg.setStatus("0");
						msg.setContent(content);
						msgList.add(msg);
					}
				}
			}
		}
		
		//延误提醒
		sql = "select t1.plan_finish_time,t1.old_dept_id,t1.main_person,t1.paper_name,t1.id from t_before_task t,t_before_monomer_plan t1 "+
			" where t.removed = '0' and t1.removed = '0' and t.monomer_plan_id = t1.id "+
			" and t1.plan_finish_time<to_char(sysdate,'yyyy-mm-dd') and t.real_finish_time is null";
		list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String content = "【延误】“"+list.get(i)[3]+"证件”计划于“"+list.get(i)[0]+"”办结，现已延误，请关注！";
				for(int j=1;j<6;j++){
					Report report = new Report();
					report.setType("前期办证");
					report.setReceiveTime(dateStr);
					report.setContent(content);
					report.setStatus("0");
					report.setRemoved("0");
					report.setCreateTime(dateStr);
					report.setOldDeptId(list.get(i)[1]+"");
					report.setRole(String.valueOf(j));
					report.setMonomer_plan_id(list.get(i)[4]+"");
					saveList.add(report);
				}
				
				String dsql = "select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[1]+"' and name = '"+list.get(i)[2]+"' and role = '1' " +
						" union all select phone from t_before_user where removed = '0' and (role = '2' or role = '3') " +
						" union all select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[1]+"' and (role = '4' or role = '5')";
				List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
				if(dlist!=null&&dlist.size()>0){
					for(int j=0;j<dlist.size();j++){
						ShortMsg msg = new ShortMsg();
						msg.setMobile(dlist.get(j)+"");
						msg.setStatus("0");
						msg.setContent(content);
						msgList.add(msg);
					}
				}
			}
		}
		//warmFunc(2,saveList,msgList);
		if(saveList!=null&&saveList.size()>0){
			beforeBuildService.saveOrUpdateAll(saveList);
		}
		if(msgList!=null&&msgList.size()>0){
			beforeBuildService.saveOrUpdateAll(msgList);
		}
		return null;
	}
	
	@Action(value="changeStatusAuto")
	public String changeStatusAuto(){
		//自动设置延误
		String sql = "select t.id from t_before_task t,t_before_monomer_plan t1 "+
			" where t.removed = '0' and t1.removed = '0' and t.monomer_plan_id = t1.id "+
			" and t1.plan_finish_time<to_char(sysdate,'yyyy-mm-dd') and t.real_finish_time is null " +
			" and (t.status = '1' or t.status = '2' or t.status is null) ";
		List<Object[]> list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			String temp = "";
			List<Object> src = new ArrayList<Object>();
			for(int i=0;i<list.size();i++){
				if(i>0){
					temp += ",";
				}
				temp += "?";
				src.add(list.get(i));
			}
			sql = "update t_before_task t set t.status = '3' where t.id in ("+temp+")";
			beforeBuildService.updateBySql(sql, src);
		}
		
		//自动设置过期
		sql = "select t.id from t_before_task t where t.removed = '0' and t.status != '5' and t.status != '6' "+
			" and (t.invalid_start_time > to_char(sysdate,'yyyy-mm-dd') or t.invalid_finish_time < to_char(sysdate,'yyyy-mm-dd'))";
		list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0){
			String temp = "";
			List<Object> src = new ArrayList<Object>();
			for(int i=0;i<list.size();i++){
				if(i>0){
					temp += ",";
				}
				temp += "?";
				src.add(list.get(i));
			}
			sql = "update t_before_task t set t.status = '6' where t.id in ("+temp+")";
			beforeBuildService.updateBySql(sql, src);
			
			String sqlHistory = "update t_before_history t set t.status = '6' where t.source_id in ("+temp+")";
			beforeBuildService.updateBySql(sqlHistory, src);
		}
		return null;
	}
	
	//业务预警,通过对当前单体办证信息的分析，自动计算出办证信息是否延误，并提醒经办人员及相关领导
	public void warmFunc(int roleType,List<Report> saveList,List<ShortMsg> msgList){
		Date date = new Date();
		String dateStr = df.format(date);
		String sql = "select t1.monomer_id,t1.paper_name,t1.paper_id,t3.next_paper_id,t1.plan_finish_time,t3.plan_days,t1.monomer_name,t1.old_dept_id,t4.status "+
			" from t_before_line_plan t,t_before_monomer_plan t1,t_before_rule_version t2,t_before_rule_detail t3,t_before_task t4 "+
			" where t.removed = '0' and t1.removed = '0' and t2.removed = '0' and t3.removed = '0' and t4.removed = '0' "+
			" and t.id = t1.line_plan_id and t1.rule_version_id = t2.id and t2.id = t3.rule_version_id "+
			" and t1.paper_id =t3.paper_id and t1.id = t4.monomer_plan_id and (t4.status = '1' or t4.status = '2')" +
			" order by monomer_id,plan_finish_time desc ";
		List<Object[]> list = beforeBuildService.findBySql(sql, null);
		String monomer_id = "";
		List<Object[]> monomerList = new ArrayList<Object[]>();
		Map<String,List<Object[]>> map = new HashMap<String,List<Object[]>>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if("".equals(monomer_id)||monomer_id.equals(list.get(i)[0]+"")){
					monomerList.add(list.get(i));
				}else{
					List<Object[]> sList = monomerList;
					map.put(monomer_id, sList);
					monomerList = new ArrayList<Object[]>();
					monomerList.add(list.get(i));
					monomer_id = list.get(i)[0]+"";
				}
				if(i==list.size()-1){
					List<Object[]> sList = monomerList;
					map.put(monomer_id, sList);
				}
			}
			for (String key : map.keySet()) {
				List<Object[]> sList = map.get(key);
				String plan_finish_time = sList.get(0)[4]+"";
				try {
					Date plan_finish_time_date = sdf.parse(plan_finish_time);
					double leftDays = (plan_finish_time_date.getTime()-date.getTime())/1000/3600/24;
					Map<String,Object[]> paperMap = new HashMap<String,Object[]>();
					double max_plan_days = 0d;
					for(int i=0;i<sList.size();i++){
						paperMap.put(sList.get(i)[2]+"", sList.get(i));
					}
					for (String key1 : paperMap.keySet()) {
						double plus_plan_days = getPlusDays(paperMap,key1);
						if(plus_plan_days>max_plan_days){
							max_plan_days = plus_plan_days;
						}
					}
					double redundance = max_plan_days - leftDays;
					if(redundance>0){
						if(roleType==1){//提醒经办人
							for(int i=0;i<sList.size();i++){
								if("2".equals(sList.get(i)[8]+"")){
									String plan_finish_time2 = sList.get(0)[4]+"";
									Date plan_finish_time_date2 = sdf.parse(plan_finish_time2);
									double left_days2 = (plan_finish_time_date2.getTime()-date.getTime())/1000/3600/24+redundance;
									String content = "【预警】“"+sList.get(i)[6]+"”的“"+sList.get(i)[1]+"”证件办证剩余天数为【"+left_days2+"】天，请抓紧办理！";
									Report report = new Report();
									report.setType("前期办证");
									report.setReceiveTime(dateStr);
									report.setContent(content);
									report.setStatus("0");
									report.setRemoved("0");
									report.setCreateTime(dateStr);
									report.setOldDeptId(sList.get(i)[7]+"");
									report.setRole("1");
									saveList.add(report);
									
									String dsql = "select phone from t_before_user where removed = '0' and old_dept_id = '"+list.get(i)[0]+"' and role = '1' ";
									List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
									if(dlist!=null&&dlist.size()>0){
										ShortMsg msg = new ShortMsg();
										msg.setMobile(dlist.get(0)+"");
										msg.setStatus("0");
										msg.setContent(content);
										msgList.add(msg);
									}
								}
							}
						}else{//提醒领导
							String content = "【预警】“"+sList.get(0)[6]+"”前期办证预计延误【"+redundance+"】天，请关注！";
							for(int j=1;j<6;j++){
								if(j==2||j==4||j==5){
									Report report = new Report();
									report.setType("前期办证");
									report.setReceiveTime(dateStr);
									report.setContent(content);
									report.setStatus("0");
									report.setRemoved("0");
									report.setCreateTime(dateStr);
									report.setOldDeptId(sList.get(0)[7]+"");
									report.setRole(String.valueOf(j));
									saveList.add(report);
								}
							}
							
							String dsql = "select phone from t_before_user where removed = '0' and role = '2' " +
									" union all select phone from t_before_user where removed = '0' and old_dept_id = '"+sList.get(0)[7]+"' and (role = '4' or role = '5') ";
							List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
							if(dlist!=null&&dlist.size()>0){
								for(int j=0;j<dlist.size();j++){
									ShortMsg msg = new ShortMsg();
									msg.setMobile(dlist.get(j)+"");
									msg.setStatus("0");
									msg.setContent(content);
									msgList.add(msg);
								}
							}
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private double getPlusDays(Map<String,Object[]> paperMap,String key1){
		Object[] obj = paperMap.get(key1);
		if(obj!=null&&obj.length>0){
			String next_paper_id = obj[3]+"";
			double plan_days = Double.parseDouble(obj[5]+"");
			if(next_paper_id!=null&&!"null".equals(next_paper_id)&&next_paper_id.length()>0){
				return plan_days + getPlusDays(paperMap,next_paper_id);
			}else{
				return plan_days;
			}
		}else{
			return 0d;
		}
		
	}
}
