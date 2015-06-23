package com.wonders.stpt.beforeBuild.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.wonders.stpt.beforeBuild.model.bo.Knowledge;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/knowledge")
@Controller("knowledgeAction")
@Scope("prototype")
public class KnowledgeAction implements ModelDriven<Knowledge>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Knowledge knowledge = new Knowledge();

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}
	
	public Knowledge getModel(){
		return knowledge;
	}
	
	@Action(value="findKnowledgeByPage",results={@Result(name="success",location="/beforeBuild/knowledge/list.jsp")})
	public String findKnowledgeByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String paperName = request.getParameter("paperName");
		String type = request.getParameter("type");
		String taskId = request.getParameter("taskId");
		String showType = request.getParameter("showType");
		if(taskId!=null&&taskId.length()>0){
			String sql = "select t1.paper_name from t_before_task t,t_before_monomer_plan t1 "+
				" where t.monomer_plan_id = t1.id and t.removed = '0' and t1.removed = '0' and t.id = ? "+
				" order by t.create_time desc ";
			List<Object> src1 = new ArrayList<Object>();
			src1.add(taskId);
			List<Object[]> list1 = beforeBuildService.findBySql(sql, src1);
			if(list1!=null&&list1.size()>0){
				paperName = (list1.get(0)+"").replace("null", "");
			}
		}
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		request.setAttribute("paperName", paperName);
		request.setAttribute("showType", showType);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_knowledge t where t.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(paperName!=null&&paperName.length()>0){
			baseSql += " and t.paper_name = ? ";
			src.add(paperName);
		}
		if(type!=null&&type.length()>0){
			baseSql += " and t.type = ? ";
			src.add(type);
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
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/knowledge/add.jsp")})
	public String toAdd(){
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/knowledge/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			knowledge = (Knowledge)beforeBuildService.load(id,Knowledge.class);
		}
		return "success";
	}
	
	@Action(value="toView",results={@Result(name="success",location="/beforeBuild/knowledge/view.jsp")})
	public String toView(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			knowledge = (Knowledge)beforeBuildService.load(id,Knowledge.class);
		}
		return "success";
	}
	
	@Action(value="save")
	public String save(){
		if(knowledge.getId()!=null&&knowledge.getId().length()>0){
			knowledge.setUpdateTime(df.format(new Date()));
			knowledge.setUpdateUser((String)session.getAttribute("loginName"));
		}else{
			knowledge.setId(null);
			knowledge.setCreateTime(df.format(new Date()));
			knowledge.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(knowledge);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			knowledge = (Knowledge)beforeBuildService.load(id,Knowledge.class);
			knowledge.setRemoved("1");
			knowledge.setUpdateTime(df.format(new Date()));
			knowledge.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(knowledge);
		}
		return null;
	}
}
