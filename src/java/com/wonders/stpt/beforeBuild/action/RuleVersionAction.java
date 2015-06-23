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
import com.wonders.stpt.beforeBuild.model.bo.LinePlan;
import com.wonders.stpt.beforeBuild.model.bo.RuleVersion;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/ruleVersion")
@Controller("ruleVersionAction")
@Scope("prototype")
public class RuleVersionAction implements ModelDriven<RuleVersion>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private RuleVersion ruleVersion = new RuleVersion();
	
	public RuleVersion getRuleVersion() {
		return ruleVersion;
	}

	public void setRuleVersion(RuleVersion ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	public RuleVersion getModel(){
		return ruleVersion;
	}
	
	@Action(value="findRuleVersionByPage",results={@Result(name="success",location="/beforeBuild/ruleVersion/list.jsp")})
	public String findRuleVersionByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String versionNum = request.getParameter("versionNum");
		String versionName = request.getParameter("versionName");
		
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_rule_version t where t.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(versionNum!=null&&versionNum.length()>0){
			baseSql += " and t.version_num like ? ";
			src.add("%"+versionNum+"%");
		}
		if(versionName!=null&&versionName.length()>0){
			baseSql += " and t.version_name like ? ";
			src.add("%"+versionName+"%");
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
		String listSql = "select * " + baseSql + orderSql;
		//System.out.println(listSql);
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		return "success";
	}
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/ruleVersion/add.jsp")})
	public String toAdd(){
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/ruleVersion/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			ruleVersion = (RuleVersion)beforeBuildService.load(id,RuleVersion.class);
		}
		return "success";
	}
	
	@Action(value="save")
	public String save(){
		if(ruleVersion.getId()!=null&&ruleVersion.getId().length()>0){
			ruleVersion.setUpdateTime(df.format(new Date()));
			ruleVersion.setUpdateUser((String)session.getAttribute("loginName"));
		}else{
			ruleVersion.setId(null);
			ruleVersion.setCreateTime(df.format(new Date()));
			ruleVersion.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(ruleVersion);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		String if_have = request.getParameter("if_have");
		if(id!=null&&id.length()>0){
			ruleVersion = (RuleVersion)beforeBuildService.load(id,RuleVersion.class);
			ruleVersion.setRemoved("1");
			ruleVersion.setUpdateTime(df.format(new Date()));
			ruleVersion.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(ruleVersion);
			
			if(if_have!=null&&"yes".equals(if_have)){
				List<Object> src = new ArrayList<Object>();
				src.add(id);
				beforeBuildService.updateBySql("update t_before_rule_detail set removed = '1' where rule_version_id = ?", src);
			}
		}
		return null;
	}
	
	@Action(value="ifHaveRuleDetail")
	public String ifHaveRuleDetail(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			String sql = "select count(*) count_num from t_before_rule_detail where rule_version_id = ? and removed = '0' ";
			List<Object> src = new ArrayList<Object>();
			src.add(id);
			int totalRow = beforeBuildService.findPageSize(sql,src);
			if(totalRow==0){
				ac.writeAjax("{\"if_have\":\"no\"}");
			}else{
				ac.writeAjax("{\"if_have\":\"yes\"}");
			}
		}
		return null;
	}
	
	@Action(value="findRuleVersions")
	public String findRuleVersions(){
		String sql = "select id,version_name from t_before_rule_version where removed = '0' order by create_time desc ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="getNextVersionOrder")
	public String getNextVersionOrder(){
		String sql = "select to_char(max(version_order)+1) from t_before_rule_version t where t.removed = '0' ";
		List<Object[]> list = beforeBuildService.findBySql(sql, null);
		if(list!=null&&list.size()>0&&list.get(0)!=null){
			ac.writeJson(list.get(0));
		}else{
			ac.writeJson("101");
		}
		return null;
	}
}
