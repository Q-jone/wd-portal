package com.wonders.stpt.pxzx.action;

import java.text.DateFormat;
import java.text.DecimalFormat;
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
import com.wonders.stpt.pxzx.service.PxzxService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/pxzx")
@Controller("pxzxAction")
@Scope("prototype")
public class PxzxAction {
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	private String orderSql = " order by case t.dept_name when '运一' then 0 when '运二' then 1 when '运三' then 2 when '运四' then 3 when '运管' then 4 when '维保' then 5 when '技术' then 6 when '资产' then 7 when '大桥' then 8 when '隧道院' then 9 when '磁浮' then 10 when '股份公司' then 11 when '培训中心' then 12 end";
	
	@Autowired
	private PxzxService pxzxService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat yDf = new SimpleDateFormat("yyyy");
	
	@Action(value="findTrainDeptDatas")
	public String findTrainDeptDatas(){
		String sql = "select t.passed from t_train_level t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? order by levels desc ";
		List<Object> src = new ArrayList<Object>();
		String year ="2014"; //yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="findTrainPeopleDatas")
	public String findTrainPeopleDatas(){
		String sql = "select t.dept_name,t.dept_id,case t.manage_plan+t.prod_plan when 0 then 0 else round(100*(t.manage_result+t.prod_result)/(t.manage_plan+t.prod_plan),2) end persent "+
			" from t_train_dept t,t_train_main t1 where t.main_id = t1.id and t1.year = ? "+orderSql;
		List<Object> src = new ArrayList<Object>();
		String year = "2014";yDf.format(new Date());
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		List olist = new ArrayList();
		if(list!=null&&list.size()>0){
			String[] datas1 = new String[list.size()];
			String[] datas2 = new String[list.size()];
			Double[] datas3 = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				datas1[i] = list.get(i)[0]+"";
				datas2[i] = list.get(i)[1]+"";
				datas3[i] = Double.parseDouble(list.get(i)[2]+"");
			}
			olist.add(datas1);
			olist.add(datas2);
			olist.add(datas3);
		}
		ac.writeJson(olist);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="findTrainLessonDatas")
	public String findTrainLessonDatas(){
		String sql = "select t.dept_name,t.dept_id,t.finish_rate from t_train_dept t,t_train_main t1 where t.main_id = t1.id and t1.year = ? "+orderSql;
		List<Object> src = new ArrayList<Object>();
		String year ="2014"; yDf.format(new Date());
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		List olist = new ArrayList();
		if(list!=null&&list.size()>0){
			String[] datas1 = new String[list.size()];
			String[] datas2 = new String[list.size()];
			Double[] datas3 = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				datas1[i] = list.get(i)[0]+"";
				datas2[i] = list.get(i)[1]+"";
				datas3[i] = Double.parseDouble(list.get(i)[2]+"");
			}
			olist.add(datas1);
			olist.add(datas2);
			olist.add(datas3);
		}
		ac.writeJson(olist);
		return null;
	}
	
	@Action(value="findTrainDatas")
	public String findTrainDatas(){
		String sql = "select case sum(t.manage_plan+t.prod_plan) when 0 then '0%' else round(100*sum(t.manage_result+t.prod_result)/sum(t.manage_plan+t.prod_plan),2)||'%' end persent from t_train_main t where year = ? "+
			" union all select case sum(t.manage_plan+t.prod_plan) when 0 then '0%' else round(100*sum(t.manage_result+t.prod_result)/sum(t.manage_plan+t.prod_plan),2)||'%' end persent from t_train_main t where year = ? and type = 1 "+
			" union all select case sum(t.manage_plan+t.prod_plan) when 0 then '0%' else round(100*sum(t.manage_result+t.prod_result)/sum(t.manage_plan+t.prod_plan),2)||'%' end persent from t_train_main t where year = ? and type = 2 "+
			" union all select case sum(t.manage_plan+t.prod_plan) when 0 then '0%' else round(100*sum(t.manage_result+t.prod_result)/sum(t.manage_plan+t.prod_plan),2)||'%' end persent from t_train_main t where year = ? and type = 3 ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		src.add(year);
		src.add(year);
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		ac.writeJson(list);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="findMonthlyTrained")
	public String findMonthlyTrained(){
		String sql = "select t.month,t.trained from t_train_month t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? order by t.month ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		Map<String,Integer> map = new HashMap<String,Integer>();
		List olist = new ArrayList();
		if(list!=null&&list.size()>0){
			List<String> datas1 = new ArrayList<String>();
			List<Integer> datas2 = new ArrayList<Integer>();
			for(int i=0;i<list.size();i++){
				map.put(list.get(i)[0]+"", Integer.parseInt(list.get(i)[1]+""));
			}
			for(int i=1;i<13;i++){
				datas1.add(i+"月");
				if(map.containsKey(i+"")){
					datas2.add(map.get(i+""));
				}else{
					datas2.add(0);
				}
			}
			olist.add(datas1);
			olist.add(datas2);
			for(int i=12;i>0;i--){
				if(datas2.get(i-1)==0){
					datas1.remove(i-1);
					datas2.remove(i-1);
				}else{
					break;
				}
			}
		}
		ac.writeJson(olist);
		return null;
	}
	
	@Action(value="findYearTrainDatas")
	public String findYearTrainDatas(){
		String sql = "select (t.manage_plan+t.prod_plan),(t.manage_result+t.prod_result),"+
			" case t.manage_plan+t.prod_plan when 0 then 0 else round(100*(t.manage_result+t.prod_result)/(t.manage_plan+t.prod_plan),2) end "+
			" from t_train_main t where t.year = ? and t.type = 3 ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		ac.writeJson(list);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="findMonthlySkill")
	public String findMonthlySkill(){
		String sql = "select t.month,t.tested from t_train_month t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? order by t.month ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		Map<String,Integer> map = new HashMap<String,Integer>();
		List olist = new ArrayList();
		if(list!=null&&list.size()>0){
			List<String> datas1 = new ArrayList<String>();
			List<Integer> datas2 = new ArrayList<Integer>();
			for(int i=0;i<list.size();i++){
				map.put(list.get(i)[0]+"", Integer.parseInt(list.get(i)[1]+""));
			}
			for(int i=1;i<13;i++){
				datas1.add(i+"月");
				if(map.containsKey(i+"")){
					datas2.add(map.get(i+""));
				}else{
					datas2.add(0);
				}
			}
			olist.add(datas1);
			olist.add(datas2);
			for(int i=12;i>0;i--){
				if(datas2.get(i-1)==0){
					datas1.remove(i-1);
					datas2.remove(i-1);
				}else{
					break;
				}
			}
		}
		ac.writeJson(olist);
		return null;
	}
	
	@Action(value="findMonthlySkillDatas")
	public String findMonthlySkillDatas(){
		String sql = "select sum(t.tested),sum(t.passed),case sum(t.tested) when 0 then 0 else round(100*sum(t.passed)/sum(t.tested),2) end from t_train_month t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		List<Object[]> list = pxzxService.findBySql(sql, src);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="findPxzxDatas")
	public String findPxzxDatas(){
		String sql = "select t.manage_result,t.prod_result from t_train_main t where t.year = ? and t.type = 3";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findManageDatas")
	public String findManageDatas(){
		String sql = "select t.manage_result,t.manage_plan-t.manage_result from t_train_main t where t.type = 3 and t.year = ?";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findProdDatas")
	public String findProdDatas(){
		String sql = "select t.prod_result,t.prod_plan-t.prod_result from t_train_main t where t.type = 3 and t.year = ?";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findAllDatas")
	public String findAllDatas(){
		String sql = "select sum(t.manage_result),sum(t.prod_result) from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findAllManageDatas")
	public String findAllManageDatas(){
		String sql = "select sum(t.manage_result),sum(t.manage_plan)-sum(t.manage_result) from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findAllProdDatas")
	public String findAllProdDatas(){
		String sql = "select sum(t.prod_result),sum(t.prod_plan)-sum(t.prod_result) from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? ";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findDeptDatas")
	public String findDeptDatas(){
		String deptId = request.getParameter("deptId");
		String sql = "select t.manage_result,t.prod_result from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? and t.dept_id = ?";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		src.add(deptId);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findDeptManageDatas")
	public String findDeptManageDatas(){
		String deptId = request.getParameter("deptId");
		String sql = "select t.manage_result,t.manage_plan-t.manage_result from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? and t.dept_id = ?";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		src.add(deptId);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findDeptProdDatas")
	public String findDeptProdDatas(){
		String deptId = request.getParameter("deptId");
		String sql = "select t.prod_result,t.prod_plan-t.prod_result from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? and t.dept_id = ?";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		src.add(deptId);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findEndMonth")
	public String findEndMonth(){
		String sql = "select deadline from t_train_profile t where t.year = ?";
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findAllDepts")
	public String findAllDepts(){
		String sql = "select distinct t.dept_name,t.dept_id from t_train_dept t,t_train_main t1 "+
			" where t.main_id = t1.id and t1.year = ? "+orderSql;
		List<Object> src = new ArrayList<Object>();
		String year = yDf.format(new Date());
		src.add(year);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findIfTrainLeader")
	public String findIfTrainLeader(){
		String loginName = request.getParameter("loginName");
		String sql = "select * from t_train_leader where login_name = ?";
		List<Object> src = new ArrayList<Object>();
		src.add(loginName);
		ac.writeJson(pxzxService.findBySql(sql, src));
		return null;
	}
	
	private String getFormatDouble(Double d){
		DecimalFormat ddf=new DecimalFormat("#");//.##
		String st=ddf.format(d);
		return st;
	}
}
