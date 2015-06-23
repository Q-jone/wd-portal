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
import com.wonders.stpt.beforeBuild.model.bo.History;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/history")
@Controller("historyAction")
@Scope("prototype")
public class HistoryAction implements ModelDriven<History>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	/**
	 * 1:已完成 0：正在进行
	 */
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private History history = new History();

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
	
	public History getModel(){
		return history;
	}
	
	@Action(value="save")
	public String save(){
		if(history.getId()!=null&&history.getId().length()>0){
			String sql = "select * from t_before_history where project_id = ? and monomer_id = ? and paper = ? and id != ? and removed='0'";
			List<Object> src = new ArrayList<Object>();
			src.add(history.getProjectId());
			src.add(history.getMonomerId());
			src.add(history.getPaper());
			src.add(history.getId());
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			if(list!=null&&list.size()>0){
				ac.writeAjax("{\"if_success\":\"no\"}");
			}else{
				history.setUpdateTime(df.format(new Date()));
				history.setUpdateUser((String)session.getAttribute("loginName"));
				beforeBuildService.save(history);
				ac.writeAjax("{\"if_success\":\"yes\"}");
			}
		}else{
			String sql = "select * from t_before_history where project_id = ? and monomer_id = ? and paper = ? and removed='0'";
			List<Object> src = new ArrayList<Object>();
			src.add(history.getProjectId());
			src.add(history.getMonomerId());
			src.add(history.getPaper());
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			if(list!=null&&list.size()>0){
				ac.writeAjax("{\"if_success\":\"no\"}");
			}else{
				history.setId(null);
				history.setCreateTime(df.format(new Date()));
				history.setCreateUser((String)session.getAttribute("loginName"));
				history.setUpdateTime(df.format(new Date()));
				history.setUpdateUser((String)session.getAttribute("loginName"));				
				beforeBuildService.save(history);
				ac.writeAjax("{\"if_success\":\"yes\"}");
			}
		}
		return null;
	}
	
	@Action(value="findProjectAndLine")//已完成项目
	public String findProjectAndLine(){
		String oldDeptId = request.getParameter("oldDeptId");
		String sql ="";
		if("0".equals(type)){
			sql = "select t.p_id,t.p_name,t.p_line,c.content from t_build_project t,t_list_status c "+
					" where t.p_line = c.sid and t.p_flag='0' and (t.p_delflag = '1063' or t.p_delflag = '1065') ";
		}else{
			sql = "select t.p_id,t.p_name,t.p_line,c.content from t_build_project t,t_list_status c "+
					" where t.p_line = c.sid and t.p_flag='1' and (t.p_delflag = '1063' or t.p_delflag = '1065') ";
		}
		List<Object> src = new ArrayList<Object>();
		if(oldDeptId!=null&&oldDeptId.length()>0){
			sql += " and t.p_companyid = ? ";
			src.add(oldDeptId);
		}
		sql += " order by p_operatedate desc ";
		ac.writeJson(beforeBuildService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findAllAreas")
	public String findAllAreas(){
		String sql = "select sid,content from t_list_status where infotype = 'xzqh_dic' order by optorder";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findAllTypes")
	public String findAllTypes(){
		String sql = "select sid,content from t_list_status where infotype = 'JS_UnitType' and refsid is null order by optorder";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findAllLines")
	public String findAllLines(){
		String sql = "select sid,content from t_list_status where infotype = 'JS_route_dic' and REFSID is null order by optorder";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findAllPapers")
	public String findAllPapers(){
		String sql = "select id,name from t_before_paper order by orders";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findMonomerByRouteAndType")
	public String findMonomerByRouteAndType(){
		String line = request.getParameter("routeId");
		String unitTypeName = request.getParameter("typeName");
		String projectId = request.getParameter("projectId");
		String typeId = request.getParameter("typeId");
		String sql = "";
		if (unitTypeName.equals("车站"))
		      sql = "select t.sid,t.CONTENT as CONTENT from t_list_status t where t.REFSID = '" + line + "' and t.CONTENT NOT IN ('区间风井','主变电站', '控制中心', '牵引变电站', '降压变电站', '高架区间', '地下区间', '地面区间', '停车场') and t.STATE = 1 ";
		    else if ((unitTypeName.equals("车站风井")) || (unitTypeName.equals("车站出入口")) || (unitTypeName.equals("过街天桥")) || (unitTypeName.equals("冷却塔")))
		    {
		        sql = "select abcd.sid,abcd.CONTENT as CONTENT from t_list_status abcd where abcd.refsid in (select abc.sid from t_list_status abc where abc.refsid in (select ab.sid from t_list_status ab) and abc.content = '" + unitTypeName + "')";

		    }
		    else 
		      sql = "select b.sid,b.content from t_list_status b where b.refsid in (select a.sid from t_list_status a where a.content = '" + unitTypeName + "')";
		
//		sql = "select t.m_id,c.content from t_build_monomer t,t_list_status c "+
//				" where m_type = '"+typeId+"' and p_id = '"+projectId+"' and c.sid = t.m_name";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="detail",results={@Result(name="success",location="/beforeBuild/history/detail.jsp")})
	public String detail(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			history = (History)beforeBuildService.load(id, History.class);
		}
		request.setAttribute("history", history);
		return "success";
	}
	
	@Action(value="findHistoryByPage",results={@Result(name="success",location="/beforeBuild/history/list.jsp")})
	public String findHistoryByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String projectName = request.getParameter("projectName");
		String routeId = request.getParameter("routeId");
		String typeId = request.getParameter("typeId");
		String monomerName = request.getParameter("monomerName");
		String paper = request.getParameter("paper");
		String realFinishTime_start = request.getParameter("realFinishTime_start");
		String realFinishTime_end = request.getParameter("realFinishTime_end");
		String status = request.getParameter("status");
		
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		request.setAttribute("realFinishTime_start", realFinishTime_start);
		request.setAttribute("realFinishTime_end", realFinishTime_end);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_history t,t_build_project c where t.removed = '0' and c.p_id = t.project_id ";
		List<Object> src = new ArrayList<Object>();
		if(oldDeptId!=null&&oldDeptId.length()>0){
			baseSql += " and c.p_companyid = ? ";
			src.add(oldDeptId);
		}
		if(projectName!=null&&projectName.length()>0){
			baseSql += " and t.project_name like ? ";
			src.add("%"+projectName+"%");
		}
		if(routeId!=null&&routeId.length()>0){
			baseSql += " and t.route_id = ? ";
			src.add(routeId);
		}
		if(typeId!=null&&typeId.length()>0){
			baseSql += " and t.type_id = ? ";
			src.add(typeId);
		}		
		if(monomerName!=null&&monomerName.length()>0){
			baseSql += " and t.monomer_name like ? ";
			src.add("%"+monomerName+"%");
		}
		if(paper!=null&&paper.length()>0){
			baseSql += " and t.paper like ? ";
			src.add("%"+paper+"%");
		}
		if(realFinishTime_start!=null&&realFinishTime_start.length()>0){
			baseSql += " and t.real_finish_time >= ? ";
			src.add(realFinishTime_start);
		}
		if(realFinishTime_end!=null&&realFinishTime_end.length()>0){
			baseSql += " and t.real_finish_time <= ? ";
			src.add(realFinishTime_end);
		}
		if(status!=null&&status.length()>0){
			baseSql += " and t.status = ? ";
			src.add(status);
		}
		
		String listSql = "select t.* " + baseSql +" order by t.ROUTE_NAME,t.PROJECT_NAME,t.MONOMER_NAME,t.REAL_FINISH_TIME ";
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		
		return "success";
	}
	
	@Action(value="load")
	public String load(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			history = (History)beforeBuildService.load(id, History.class);
		}
		ac.writeJson(history);
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			history = (History)beforeBuildService.load(id, History.class);
			history.setRemoved("1");
			history.setUpdateTime(df.format(new Date()));
			history.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(history);
		}
		return null;
	}
}
