package com.wonders.stpt.jsgl.action;

import java.text.DateFormat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wonders.stpt.jsgl.service.JsglService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/jsgl")
@Controller("jsglAction")
@Scope("prototype")
public class JsglAction {
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	public String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	/**
	 * 建设动态中的证件id
	 */
	public String paperId;
	/**
	 * 建设动态中的年份选择
	 */
	public String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Autowired
	private JsglService jsglService;
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat yDf = new SimpleDateFormat("yyyy");
	/**
	 * 项目维度 项目名称（未完成项目名称）
	 * @return
	 */
	@Action(value="findProjects")
	public String findProjects(){//计划数的标题（包括已完成、未完成）
		/*String sql = "select distinct replace(replace(t.project_name,'轨道交通',''),'工程','') project_name "+
	       " from t_before_history t where t.status = '4' "+
	       " and t.paper_id = '20' and t.project_name not like '%配套工程%' "+
	       " order by project_name";*///原版
		/*String sql ="select distinct replace(replace(t.project_name,'轨道交通',''),'工程','') project_name "+
				"from t_before_history t where t.status = '4' and t.paper_id = '20' and t.project_name not like '%配套工程%' and t.removed='0' "+
				" union "+
				"select distinct replace(replace(t2.project_name,'轨道交通',''),'工程','')project_name "+//distinct
				"from t_before_monomer_plan t,t_before_task t1 ,t_before_line_plan t2 "+
				"where t.removed='0' and t1.removed='0' and t.id=t1.monomer_plan_id and t.paper_id = '20' and t2.project_name not like '%配套工程%' and "+ 
				"t.line_plan_id=t2.id and t2.removed='0' and (t1.status = '1' or t1.status = '2' or t1.status = '3' or t1.status is null) "+
				"group by t2.project_name,t2.project_id order by project_name ";*/
		String sql=" select replace(replace(t.p_name,'轨道交通',''),'工程','')p_name from t_build_project t where t.p_flag='0'  order by t.p_name";
		List<Object[]> list=jsglService.findBySql(sql, null);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="findPaperDatas")
	public String findPaperDatas() throws Exception{
		//历史完成数据
		/*String sql = "select t.project_name,t.project_id,count(*) from t_before_history t where t.status = '4' "+
			" and t.paper_id = '20' and t.project_name not like '%配套工程%' "+
			" group by t.project_name,t.project_id order by t.project_name";*///原版
		/*String sql="select  distinct replace(replace(t.project_name,'轨道交通',''),'工程','')project_name,t.project_id id,count(*) from t_before_history t where t.status = '4' "+
			"and t.paper_id = '20' and t.project_name not like '%配套工程%' and t.removed='0' "+
			"group by t.project_name,t.project_id "+// --order by project_name
			"union "+
			"select distinct replace(replace(t2.project_name,'轨道交通',''),'工程','')project_name,t2.project_id id,0 "+//--count(*)
			"from t_before_monomer_plan t,t_before_task t1 ,t_before_line_plan t2 "+
			"where t.removed='0' and t1.removed='0' and t.id=t1.monomer_plan_id and t.paper_id = '20' and t2.project_name not like '%配套工程%' and "+
			"t.line_plan_id=t2.id and t2.removed='0' and (t1.status = '1' or t1.status = '2' or t1.status = '3' or t1.status is null) "+
			"group by t2.project_name,t2.project_id order by project_name,id ";
		List<Object[]> doneList =distinct(jsglService.findBySql(sql, null));
		String projectIds = "(";
		if(doneList!=null&&doneList.size()>0){
			for(int i=0;i<doneList.size();i++){
				if(i>0){
					projectIds += ",";
				}
				projectIds += "'"+doneList.get(i)[1]+"'";
			}
		}else{
			projectIds += "''";
		}
		projectIds += ")";
		//剩余未完成数据
		sql = "select t1.project_name,t1.project_id,count(*) count_num "+
	        " from t_before_monomer_plan t,t_before_line_plan t1,t_before_task t2 "+
	        " where t.line_plan_id = t1.id and t.id = t2.monomer_plan_id "+
	        " and (t2.status = '1' or t2.status = '2' or t2.status = '3' or t2.status is null) " +
	        " and t.paper_id = '20' and t1.project_id in "+projectIds+
	        " and t.removed = '0' and t1.removed = '0' and t2.removed = '0' "+
		    " group by t1.project_name,t1.project_id order by t1.project_name";
		List<Object[]> leftList = jsglService.findBySql(sql, null);
		
		Map<String,Integer> leftMap = new HashMap<String,Integer>();
		if(leftList!=null&&leftList.size()>0){
			for(int i=0;i<leftList.size();i++){
				leftMap.put(leftList.get(i)[1]+"", Integer.parseInt(String.valueOf(leftList.get(i)[2])));
			}
		}
		
		List<Integer[]> outList = new ArrayList<Integer[]>();
		if(doneList!=null&&doneList.size()>0){
			Integer[] allStrs = new Integer[doneList.size()];
			Integer[] doneStrs = new Integer[doneList.size()];
			Integer[] leftStrs = new Integer[doneList.size()];
			for(int j=0;j<doneList.size();j++){
				doneStrs[j] = Integer.parseInt(String.valueOf(doneList.get(j)[2]));
				
				if(leftMap.containsKey(doneList.get(j)[1]+"")){
					leftStrs[j] = leftMap.get(doneList.get(j)[1]+"");
				}else{
					leftStrs[j] = 0;
				}
				
				allStrs[j] = doneStrs[j]+leftStrs[j];
			}
			outList.add(allStrs);
			outList.add(doneStrs);
			outList.add(leftStrs);
		}*/
		//以上是原版代码
		//项目名称
		System.out.println(paperId+"------------------");
		String title=" select replace(replace(t.p_name,'轨道交通',''),'工程','')p_name,t.p_id from t_build_project t where t.p_flag='0' order by t.p_name";
		List<Object[]> tlist=jsglService.findBySql(title, null);
		
		
		List<Integer[]> outList = new ArrayList<Integer[]>();//返回list
		//项目已完成记录(所有填在计划中的记录)
		String done="select replace(replace(t2.project_name,'轨道交通',''),'工程','')project_name,t2.project_id,count(*) from t_before_monomer_plan t,t_before_task t1,t_before_line_plan t2,t_build_project t3"
				+" where t.id=t1.monomer_plan_id and t.removed='0' and t1.removed='0' and t.paper_id='"+paperId+"' and t2.removed='0' and t.line_plan_id=t2.id and t2.project_id=t3.p_id and t3.p_flag='0'"
				+" and (t1.status ='4' or t1.status ='5' or t1.status ='6') ";
				
		if("all".equals(year)||year==null||""==year){
			done+="  group by t2.project_name,t2.project_id ";
		}else if("now".equals(year)){
			done+=" and t.plan_finish_time is not null and t.plan_finish_time<='"+date+"' and t1.REAL_FINISH_TIME<='"+date+"' group by t2.project_name,t2.project_id ";
		}else{
			done+=" and t.plan_finish_time is not null and t.plan_finish_time >= '"+year+"-01-01' and t.plan_finish_time<='"+year+"-12-31'"
					+"and t1.REAL_START_TIME >= '"+year+"-01-01' and t1.REAL_FINISH_TIME<='"+year+"-12-31' group by t2.project_name,t2.project_id";
		}
		List<Object[]> doneList =distinct(jsglService.findBySql(done, null));//完成记录统计
		//总记录数
		String total="select replace(replace(t2.project_name,'轨道交通',''),'工程','')project_name,t2.project_id,count(*) from t_before_monomer_plan t,t_before_line_plan t2 ,t_build_project t3"
				+" where t.line_plan_id=t2.id and t.removed='0' and t2.removed='0' and t.paper_id='"+paperId+"' and t2.project_id=t3.p_id and t3.p_flag='0'";
				//+" group by t2.project_name,t2.project_id";
		if("all".equals(year)||year==null||""==year){
			total+=" group by t2.project_name,t2.project_id ";
		}else if("now".equals(year)){
			total+=" and t.plan_finish_time is not null and t.plan_finish_time<='"+date+"' group by t2.project_name,t2.project_id ";
		}else{
			total+=" and t.plan_finish_time is not null and t.plan_finish_time >= '"+year+"-01-01' and t.plan_finish_time<='"+year+"-12-31' group by t2.project_name,t2.project_id";
		}
		List<Object[]> totalList = jsglService.findBySql(total, null);//总记录
		
		//将完成数、总数存放在map中
		Map<String,Integer> donetMap = new HashMap<String,Integer>();
		if(doneList!=null&&doneList.size()>0){
			for(int i=0;i<doneList.size();i++){
				donetMap.put(doneList.get(i)[1]+"", Integer.parseInt(String.valueOf(doneList.get(i)[2])));
			}
		}
		Map<String,Integer> totaltMap = new HashMap<String,Integer>();//总数
		if(totalList!=null&&totalList.size()>0){
			for(int i=0;i<totalList.size();i++){
				totaltMap.put(totalList.get(i)[1]+"", Integer.parseInt(String.valueOf(totalList.get(i)[2])));
			}
		}
		//处理剩余数、完成数、总数
		if(tlist!=null&&tlist.size()>0){
			Integer[] allStrs = new Integer[tlist.size()];//总数
			Integer[] doneStrs = new Integer[tlist.size()];//完成数
			Integer[] leftStrs = new Integer[tlist.size()];//剩余数
			for(int j=0;j<tlist.size();j++){//存在未完成的项目工程
				
				if(totaltMap.containsKey(tlist.get(j)[1]+"")){//项目标题与总数中的标题对应
					allStrs[j]=totaltMap.get(String.valueOf(tlist.get(j)[1]));
					if(donetMap.containsKey(tlist.get(j)[1]+"")){//完成数中与标题对应的数据
						doneStrs[j]=donetMap.get(String.valueOf(tlist.get(j)[1]));
					}else{
						doneStrs[j]=0;
					}
					leftStrs[j]=allStrs[j]-doneStrs[j];
				}else{
					allStrs[j]=0;
					doneStrs[j]=0;
					leftStrs[j]=0;
				}
			}
			outList.add(allStrs);
			outList.add(doneStrs);
			outList.add(leftStrs);
		}
		ac.writeJson(outList);
		return null;
	}
	
	/**
	 * 去除重复（自定义条件）
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> distinct(List<Object[]> list){
		Object[] obj;
		System.out.println("");
		for(int i=0;i<list.size()-1;i++){
			obj=list.get(i);
			/*if(Integer.parseInt(obj[2].toString())>0){//剩余数为0
				if(list.get(i+1)[1].toString().equals(obj[1].toString()))
					list.remove(i+1);
			}*/
			for(int j=i+1;j<list.size();j++){
				if(obj[1].toString().equals(list.get(j)[1].toString())){
					if(Integer.parseInt(list.get(j)[2].toString())>0){
						list.get(i)[2]=list.get(j)[2];
					}
					list.remove(j);
				}
			}
		}
		return list;
	}
	//以上是项目维度(施工许可证)
	/**
	 * 证件维度
	 * @return
	 */
	@Action(value="findPapers")
	public String findPapers(){
		/*String sql = "select distinct paper from t_before_history t "+
			" where removed = '0' and status = '4' and paper_id is not null "+
			" group by t.paper,t.paper_id order by paper";*/
		/*String sql="select distinct paper from t_before_history t "+
			"where removed = '0' and status = '4' and paper_id is not null "+
			"group by t.paper,t.paper_id  "+//--order by paper
			"union "+
			"select distinct t.paper_name paper from t_before_monomer_plan t,t_before_task t1  "+
			"where t.id = t1.monomer_plan_id  "+
			"and (t1.status = '1' or t1.status = '2' or t1.status = '3' or t1.status is null) "+
			"and t.removed = '0' and t1.removed = '0'  "+
			"group by t.paper_name,t.paper_id order by paper";*/
		String sql=" select distinct t.paper_name from t_before_monomer_plan t,t_before_line_plan t1,t_build_project t2 "
				+" where t1.removed='0' and t.removed='0' and t.line_plan_id=t1.id and t1.project_id=t2.p_id and t2.p_flag='0' order by t.paper_name";
		ac.writeJson(jsglService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findDatasByPaper")
	public String findDatasByPaper(){
		/*String sql = "select t.paper,t.paper_id,count(*) from t_before_history t "+
			" where removed = '0' and status = '4' and paper_id is not null "+
			" group by t.paper,t.paper_id order by t.paper";*/
		/*String sql="select t.paper,t.paper_id,count(*) sun from t_before_history t "+
			"where removed = '0' and status = '4' and paper_id is not null "+
			"group by t.paper,t.paper_id "+//--order by t.paper 
			"union  "+
			"select  t.paper_name paper,t.paper_id,0 "+//--count(*)
			"from t_before_monomer_plan t,t_before_task t1 "+
			"where t.id = t1.monomer_plan_id "+
			"and (t1.status = '1' or t1.status = '2' or t1.status = '3' or t1.status is null) "+
			"and t.removed = '0' and t1.removed = '0' "+
			"group by t.paper_name,t.paper_id order by paper";
		List<Object[]> doneList =distinct(jsglService.findBySql(sql, null));//获得符合要求分证件及其统计值(历史数据)
		String paperIds = "(";
		if(doneList!=null&&doneList.size()>0){
			for(int i=0;i<doneList.size();i++){
				if(i>0){
					paperIds += ",";
				}
				paperIds += "'"+doneList.get(i)[1]+"'";
			}
		}else{
			paperIds += "''";
		}
		paperIds += ")";
		
		sql = "select t.paper_name,t.paper_id,count(*) from t_before_monomer_plan t,t_before_task t1 "+
			" where t.id = t1.monomer_plan_id and t.paper_id in "+paperIds+
			" and (t1.status = '1' or t1.status = '2' or t1.status = '3' or t1.status is null) "+
			" and t.removed = '0' and t1.removed = '0' "+
			" group by t.paper_name,t.paper_id order by t.paper_name";
		List<Object[]> leftList = jsglService.findBySql(sql, null);//根据要求获取相关证件及其统计值（未完成的记录）
		
		Map<String,Integer> leftMap = new HashMap<String,Integer>();
		if(leftList!=null&&leftList.size()>0){
			for(int i=0;i<leftList.size();i++){//证件id和统计值
				leftMap.put(leftList.get(i)[1]+"", Integer.parseInt(String.valueOf(leftList.get(i)[2])));
			}
		}
		
		List<Integer[]> outList = new ArrayList<Integer[]>();
		if(doneList!=null&&doneList.size()>0){
			Integer[] allStrs = new Integer[doneList.size()];
			Integer[] doneStrs = new Integer[doneList.size()];
			Integer[] leftStrs = new Integer[doneList.size()];
			for(int j=0;j<doneList.size();j++){
				doneStrs[j] = Integer.parseInt(String.valueOf(doneList.get(j)[2]));
				
				if(leftMap.containsKey(doneList.get(j)[1]+"")){//办结证件
					leftStrs[j] = leftMap.get(doneList.get(j)[1]+"");
				}else{
					leftStrs[j] = 0;
				}
				allStrs[j] = doneStrs[j]+leftStrs[j];
			}
			outList.add(allStrs);
			outList.add(doneStrs);
			outList.add(leftStrs);
		}*/
		//以上是原先代码
		
		List<Integer[]> outList = new ArrayList<Integer[]>();
		String title="select distinct t.paper_name,t.paper_id   from t_before_monomer_plan t,t_before_line_plan t1,t_build_project t2 "
				+" where t1.removed='0' and t.removed='0' and t.line_plan_id=t1.id and t1.project_id=t2.p_id and t2.p_flag='0' order by t.paper_name";
		List<Object[]> tlist = jsglService.findBySql(title, null);//标题部分(与findPapers中的标题对应)
		
		String done="select  t.paper_name,t.paper_id,count(*) from  t_before_monomer_plan t,t_before_task t1,t_before_line_plan t2,t_build_project t3"
				+"  where t.id=t1.monomer_plan_id and t2.removed='0' and t.line_plan_id=t2.id and t2.project_id=t3.p_id and t3.p_flag='0'"
				+" and t.removed='0' and t1.removed='0' and "
				+" (t1.status ='4' or t1.status ='5' or t1.status ='6') ";
		if("all".equals(year)||year==null||""==year){
			done+="  group by t.paper_name,t.paper_id order by t.paper_name ";
		}else if("now".equals(year)){
			done+=" and t.plan_finish_time is not null and t.plan_finish_time<='"+date+"' and t1.REAL_FINISH_TIME<='"+date+"' group by t.paper_name,t.paper_id order by t.paper_name ";
		}else{
			done+=" and t.plan_finish_time is not null and t.plan_finish_time >= '"+year+"-01-01' and t.plan_finish_time<='"+year+"-12-31'"
					+" and t1.REAL_START_TIME >= '"+year+"-01-01' and t1.REAL_FINISH_TIME<='"+year+"-12-31' group by t.paper_name,t.paper_id order by t.paper_name";
		}
		
		List<Object[]> doneList = jsglService.findBySql(done, null);//（未完成项目）证件已完成数据
		
		String total=" select t.paper_name,t.paper_id,count(*) from t_before_monomer_plan t,t_before_line_plan t2,t_build_project t3"
				+"  where t.removed='0' and t2.removed='0' and t.line_plan_id=t2.id and t2.project_id=t3.p_id and t3.p_flag='0'";
		if("all".equals(year)||year==null||""==year){//+"    group by t.paper_name,t.paper_id order by t.paper_name"
			total+=" group by t.paper_name,t.paper_id order by t.paper_name ";
		}else if("now".equals(year)){
			total+=" and t.plan_finish_time is not null and t.plan_finish_time<='"+date+"' group by t.paper_name,t.paper_id order by t.paper_name ";
		}else{
			total+=" and t.plan_finish_time is not null and t.plan_finish_time >= '"+year+"-01-01' and t.plan_finish_time<='"+year+"-12-31' group by t.paper_name,t.paper_id order by t.paper_name";
		}
		
		List<Object[]> totalList = jsglService.findBySql(total, null);//（未完成项目）证件总数数据
		
		//将完成数、总数存放在map中
		Map<String,Integer> donetMap = new HashMap<String,Integer>();
		if(doneList!=null&&doneList.size()>0){
			for(int i=0;i<doneList.size();i++){
				donetMap.put(doneList.get(i)[1]+"", Integer.parseInt(String.valueOf(doneList.get(i)[2])));
			}
		}
		Map<String,Integer> totaltMap = new HashMap<String,Integer>();//总数
		if(totalList!=null&&totalList.size()>0){
			for(int i=0;i<totalList.size();i++){
				totaltMap.put(totalList.get(i)[1]+"", Integer.parseInt(String.valueOf(totalList.get(i)[2])));
			}
		}
		//处理剩余数、完成数、总数
		if(tlist!=null&&tlist.size()>0){
			Integer[] allStrs = new Integer[tlist.size()];//总数
			Integer[] doneStrs = new Integer[tlist.size()];//完成数
			Integer[] leftStrs = new Integer[tlist.size()];//剩余数
			for(int j=0;j<tlist.size();j++){//存在未完成的项目工程
						
				if(totaltMap.containsKey(tlist.get(j)[1]+"")){//项目标题与总数中的标题对应
					allStrs[j]=totaltMap.get(String.valueOf(tlist.get(j)[1]));
					if(donetMap.containsKey(tlist.get(j)[1]+"")){//完成数中与标题对应的数据
						doneStrs[j]=donetMap.get(String.valueOf(tlist.get(j)[1]));
					}else{
						doneStrs[j]=0;
					}
					leftStrs[j]=allStrs[j]-doneStrs[j];
				}else{
					allStrs[j]=0;
					doneStrs[j]=0;
					leftStrs[j]=0;
				}
			}
			outList.add(allStrs);
			outList.add(doneStrs);
			outList.add(leftStrs);
		}
		ac.writeJson(outList);
		return null;
	}
	//以上是证件维度
	@Action(value="findDepts")
	public String findDepts(){
		/*String sql = "select distinct t1.deal_unit from t_before_history t,t_before_paper t1 "+
			" where t.removed = '0' and t1.removed = '0' and t.status = '4' "+
			" and t.paper_id = t1.id order by t1.deal_unit";*/
		String sql="select distinct t3.deal_unit from t_before_monomer_plan t,t_before_line_plan t1,t_build_project t2,t_before_paper t3"
				+" where t.removed='0' and t1.removed='0' and t3.removed='0' and t2.p_flag='0' and t.paper_id=t3.id and t.line_plan_id=t1.id "
				+" and t1.project_id=t2.p_id order by t3.deal_unit";
		ac.writeJson(jsglService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findDatasByDept")
	public String findDatasByDept(){
		/*String sql = "select t1.deal_unit,count(*) from t_before_history t,t_before_paper t1 "+
			" where t.removed = '0' and t1.removed = '0' and t.status = '4' "+
			" and t.paper_id = t1.id group by t1.deal_unit order by t1.deal_unit";
		List<Object[]> doneList = jsglService.findBySql(sql, null);
		String depts = "(";
		if(doneList!=null&&doneList.size()>0){
			for(int i=0;i<doneList.size();i++){
				if(i>0){
					depts += ",";
				}
				depts += "'"+doneList.get(i)[0]+"'";
			}
		}else{
			depts += "''";
		}
		depts += ")";
		
		sql = "select t2.deal_unit,count(*) from t_before_monomer_plan t,t_before_task t1,t_before_paper t2 "+
			" where t.id = t1.monomer_plan_id and t.paper_id = t2.id and t2.deal_unit in "+depts+
			" and (t1.status = '1' or t1.status = '2' or t1.status = '3' or t1.status is null) "+
			" and t.removed = '0' and t1.removed = '0' and t2.removed = '0' "+
			" group by t2.deal_unit order by t2.deal_unit";
		List<Object[]> leftList = jsglService.findBySql(sql, null);
		
		Map<String,Integer> leftMap = new HashMap<String,Integer>();
		if(leftList!=null&&leftList.size()>0){
			for(int i=0;i<leftList.size();i++){
				leftMap.put(leftList.get(i)[0]+"", Integer.parseInt(String.valueOf(leftList.get(i)[1])));
			}
		}
		
		List<Integer[]> outList = new ArrayList<Integer[]>();
		if(doneList!=null&&doneList.size()>0){
			Integer[] allStrs = new Integer[doneList.size()];
			Integer[] doneStrs = new Integer[doneList.size()];
			Integer[] leftStrs = new Integer[doneList.size()];
			for(int j=0;j<doneList.size();j++){
				doneStrs[j] = Integer.parseInt(String.valueOf(doneList.get(j)[1]));
				
				if(leftMap.containsKey(doneList.get(j)[0]+"")){
					leftStrs[j] = leftMap.get(doneList.get(j)[0]+"");
				}else{
					leftStrs[j] = 0;
				}
				
				allStrs[j] = doneStrs[j]+leftStrs[j];
			}
			outList.add(allStrs);
			outList.add(doneStrs);
			outList.add(leftStrs);
		}*/
		//以上是原先代码
		
		List<Integer[]> outList = new ArrayList<Integer[]>();
		String title="select distinct t3.deal_unit from t_before_monomer_plan t,t_before_line_plan t1,t_build_project t2,t_before_paper t3 "
				+" where t.removed='0' and t1.removed='0' and t3.removed='0' and t2.p_flag='0' and t.paper_id=t3.id and t.line_plan_id=t1.id "
				+" and t1.project_id=t2.p_id order by t3.deal_unit";
		List<Object[]> tlist = jsglService.findBySql(title, null);//标题部分(与findPapers中的标题对应)
		
		String done="select t3.deal_unit,count(*) from t_before_monomer_plan t,t_before_line_plan t1,t_build_project t2,t_before_paper t3,t_before_task t4 "
				+" where t.removed='0' and t1.removed='0' and t3.removed='0' and t2.p_flag='0' and t.paper_id=t3.id and t.line_plan_id=t1.id "
				+" and t1.project_id=t2.p_id  and t4.removed='0' and t.id=t4.monomer_plan_id and ((t4.status ='4' or t4.status ='5' or t4.status ='6')) ";
				//+" group by t3.deal_unit order by t3.deal_unit";
		if("all".equals(year)||year==null||""==year){
			done+="  group by t3.deal_unit order by t3.deal_unit ";
		}else if("now".equals(year)){
			done+=" and t.plan_finish_time is not null and t.plan_finish_time<='"+date+"' and t4.REAL_FINISH_TIME<='"+date+"' group by t3.deal_unit order by t3.deal_unit ";
		}else{
			done+=" and t.plan_finish_time is not null and t.plan_finish_time >= '"+year+"-01-01' and t.plan_finish_time<='"+year+"-12-31'"
					+" and t4.REAL_START_TIME >= '"+year+"-01-01' and t4.REAL_FINISH_TIME<='"+year+"-12-31'	group by t3.deal_unit order by t3.deal_unit";
		}
		
		List<Object[]> doneList = jsglService.findBySql(done, null);//（未完成项目）证件已完成数据
		
		String total="select t3.deal_unit,count(*) from t_before_monomer_plan t,t_before_line_plan t1,t_build_project t2,t_before_paper t3 "
				+" where t.removed='0' and t1.removed='0' and t3.removed='0' and t2.p_flag='0' and t.paper_id=t3.id and t.line_plan_id=t1.id ";
				//+" and t1.project_id=t2.p_id group by t3.deal_unit order by t3.deal_unit";
		
		if("all".equals(year)||year==null||""==year){
			total+="  group by t3.deal_unit order by t3.deal_unit ";
		}else if("now".equals(year)){
			total+=" and t.plan_finish_time is not null and t.plan_finish_time<='"+date+"' group by t3.deal_unit order by t3.deal_unit ";
		}else{
			total+=" and t.plan_finish_time is not null and t.plan_finish_time >= '"+year+"-01-01' and t.plan_finish_time<='"+year+"-12-31' group by t3.deal_unit order by t3.deal_unit";
		}
		List<Object[]> totalList = jsglService.findBySql(total, null);//（未完成项目）证件总数数据
		
		//将完成数、总数存放在map中
		Map<String,Integer> donetMap = new HashMap<String,Integer>();
		if(doneList!=null&&doneList.size()>0){
			for(int i=0;i<doneList.size();i++){
				donetMap.put(doneList.get(i)[0]+"", Integer.parseInt(String.valueOf(doneList.get(i)[1])));
			}
		}
		Map<String,Integer> totaltMap = new HashMap<String,Integer>();//总数
		if(totalList!=null&&totalList.size()>0){
			for(int i=0;i<totalList.size();i++){
				totaltMap.put(totalList.get(i)[0]+"", Integer.parseInt(String.valueOf(totalList.get(i)[1])));
			}
		}
		//处理剩余数、完成数、总数
		if(tlist!=null&&tlist.size()>0){
			Integer[] allStrs = new Integer[tlist.size()];//总数
			Integer[] doneStrs = new Integer[tlist.size()];//完成数
			Integer[] leftStrs = new Integer[tlist.size()];//剩余数
			for(int j=0;j<tlist.size();j++){//存在未完成的项目工程
						System.out.println((tlist.get(j)));
				if(totaltMap.containsKey(tlist.get(j)+"")){//项目标题与总数中的标题对应
					allStrs[j]=totaltMap.get(String.valueOf(tlist.get(j)));
					if(donetMap.containsKey(tlist.get(j)+"")){//完成数中与标题对应的数据
						doneStrs[j]=donetMap.get(String.valueOf(tlist.get(j)));
					}else{
						doneStrs[j]=0;
					}
					leftStrs[j]=allStrs[j]-doneStrs[j];
				}else{
					allStrs[j]=0;
					doneStrs[j]=0;
					leftStrs[j]=0;
				}
			}
			outList.add(allStrs);
			outList.add(doneStrs);
			outList.add(leftStrs);
		}
		
		ac.writeJson(outList);
		return null;
	}
	/**
	 * 投资进度
	 * @return
	 */
	@Action(value="findInvestDatas")
	public String findInvestDatas(){
		String sql = "select data from (select * from (select t.*,row_number() over(partition by t.row_flag || ':' || t.col_flag "+
			" order by t.save_dt desc) rn from stpt.t_report_data t where type='1' and t.removed = 0 order by save_dt desc) "+
			" where rn = 1 order by report_date desc,row_flag asc,col_flag asc) where rownum<=105";
		List<Object[]> list = jsglService.findBySql(sql, null);
		List<List<String>> showList = new ArrayList<List<String>>();
		if(list!=null&&list.size()>0){
			List<String> strList = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				strList.add(String.valueOf(list.get(i)));
				if((i+1)%7==0){
					showList.add(strList);
					strList = new ArrayList<String>();
				}
			}
		}
		ac.writeJson(showList);
		return null;
	}
	/**
	 * 平台应用
	 * @return
	 */
	@Action(value="findPlatformDatas")
	public String findPlatformDatas(){
		String sql = "select data from (select t.*,row_number() over(partition by t.row_flag || ':' || t.col_flag order by t.save_dt desc) rn "+
			" from stpt.t_report_data t where type='3' and t.removed = 0) where rn = 1 order by row_flag asc,col_flag asc";
		List<Object[]> list = jsglService.findBySql(sql, null);
		List<List<String>> showList = new ArrayList<List<String>>();
		if(list!=null&&list.size()>0){
			List<String> strList = new ArrayList<String>();
			for(int i=0;i<list.size();i++){
				strList.add(String.valueOf(list.get(i)));
				if((i+1)%9==0||i==list.size()-1){
					showList.add(strList);
					strList = new ArrayList<String>();
				}
			}
		}
		ac.writeJson(showList);
		return null;
	}
	
	@Action(value="findZttp")
	public String findZttp(){
		String sql = "select * from (select id,name from stfb.zttp t where pub_flag = 1 and part = 'czyj' order by id desc) where rownum < 6";
		List<Object[]> list = jsglService.findBySql(sql, null);
		ac.writeJson(list);
		return null;
	}
}
