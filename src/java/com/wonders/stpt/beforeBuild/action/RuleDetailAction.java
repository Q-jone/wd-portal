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
import com.wonders.stpt.beforeBuild.model.bo.RuleDetail;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/ruleDetail")
@Controller("ruleDetailAction")
@Scope("prototype")
public class RuleDetailAction implements ModelDriven<RuleDetail>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private RuleDetail ruleDetail = new RuleDetail();

	public RuleDetail getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(RuleDetail ruleDetail) {
		this.ruleDetail = ruleDetail;
	}
	
	public RuleDetail getModel(){
		return ruleDetail;
	}
	
	@Action(value="findRuleDetailByPage",results={@Result(name="success",location="/beforeBuild/ruleDetail/list.jsp")})
	public String findRuleVersionByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String versionName = request.getParameter("versionName");
		String orderNum = request.getParameter("orderNum");
		String paperName = request.getParameter("paperName");
		String prePaperName = request.getParameter("prePaperName");
		String nextPaperName = request.getParameter("nextPaperName");
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		request.setAttribute("versionName", versionName);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_rule_detail t,t_before_rule_version t1 where t.removed = '0' and t1.removed = '0' and t.rule_version_id = t1.id ";
		List<Object> src = new ArrayList<Object>();
		if(versionName!=null&&versionName.length()>0){
			baseSql += " and t1.version_name like ? ";
			src.add("%"+versionName+"%");
		}
		if(orderNum!=null&&orderNum.length()>0){
			baseSql += " and t.order_num like ? ";
			src.add("%"+orderNum+"%");
		}
		if(paperName!=null&&paperName.length()>0){
			baseSql += " and t.paper_name like ? ";
			src.add("%"+paperName+"%");
		}
		if(prePaperName!=null&&prePaperName.length()>0){
			baseSql += " and t.pre_paper_name like ? ";
			src.add("%"+prePaperName+"%");
		}
		if(nextPaperName!=null&&nextPaperName.length()>0){
			baseSql += " and t.next_paper_name like ? ";
			src.add("%"+nextPaperName+"%");
		}
		if(id!=null&&id.length()>0){
			baseSql += " and t.rule_version_id = ? ";
			src.add(id);
		}
		
		String orderSql = "";
		String orderParam = request.getParameter("orderParam");
		String orderValue = request.getParameter("orderValue");
		request.setAttribute("orderParam", orderParam);
		request.setAttribute("orderValue", orderValue);
		if(orderParam==null||orderParam.length()==0){
			orderSql = " order by to_number(t.order_num) ";
		}else{
			if("order_num".equals(orderParam)||"valid_days".equals(orderParam)||"mini_days".equals(orderParam)||"plan_days".equals(orderParam)){
				orderParam = "to_number("+orderParam+")";
			}
			orderSql = " order by "+orderParam;
			if(orderValue.length()>0){
				orderSql += " "+ orderValue;
			}
		}
		String listSql = "select t.*,t1.version_name " + baseSql + orderSql;
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		
		if(id!=null&&id.length()>0){
			List<Object> src1 = new ArrayList<Object>();
			src1.add(id);
			List<Object[]> list1 = beforeBuildService.findBySql("select version_name from t_before_rule_version where id = ? ", src1);
			if(list1!=null&&list1.size()>0){
				request.setAttribute("versionName", list1.get(0));
			}
		}
		return "success";
	}
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/ruleDetail/add.jsp")})
	public String toAdd(){
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/ruleDetail/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			ruleDetail = (RuleDetail)beforeBuildService.load(id,RuleDetail.class);
		}
		return "success";
	}
	
	@Action(value="save")
	public String save(){
		if(ruleDetail.getId()!=null&&ruleDetail.getId().length()>0){
			ruleDetail.setUpdateTime(df.format(new Date()));
			ruleDetail.setUpdateUser((String)session.getAttribute("loginName"));
		}else{
			ruleDetail.setId(null);
			ruleDetail.setCreateTime(df.format(new Date()));
			ruleDetail.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(ruleDetail);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			ruleDetail = (RuleDetail)beforeBuildService.load(id,RuleDetail.class);
			ruleDetail.setRemoved("1");
			ruleDetail.setUpdateTime(df.format(new Date()));
			ruleDetail.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(ruleDetail);
		}
		return null;
	}
	
	@Action(value="getNodeAndMilestone")
	public String getNodeAndMilestone(){
		String id = request.getParameter("ruleVersionId");
		String paperId = request.getParameter("paperId");
		if(id==null||"".equals(id)){
			id = "111";
		}
		if(paperId==null||"".equals(paperId)){
			paperId = "111";
		}
		String sql = "select t1.if_node,t1.if_milestone from t_before_rule_version t,t_before_rule_detail t1 "+
			" where t.removed = '0' and t1.removed = '0' and t.id = t1.rule_version_id "+
			" and t.id = ? and t1.paper_id = ? order by t1.create_time desc";
		List<Object> src = new ArrayList<Object>();
		src.add(id);
		src.add(paperId);
		if(id!=null&&id.length()>0&&paperId!=null&&paperId.length()>0){
			List<Object[]> list = beforeBuildService.findBySql(sql,src);
			if(list!=null&&list.size()>0){
				ac.writeJson(list.get(0));
			}
		}
		return null;
	}
	
	@Action(value="findDefaultDays")
	public String findDefaultDays(){
		String paperId = this.request.getParameter("paperId");
		String sql = "select default_days from t_before_paper t where t.id = ? ";
		List<Object> src = new ArrayList<Object>();
		src.add(paperId);
		if(paperId!=null&&paperId.length()>0){
			List<Object[]> list = beforeBuildService.findBySql(sql,src);
			if(list!=null&&list.size()>0){
				ac.writeJson(list.get(0));
			}
		}
		return null;
	}
}
