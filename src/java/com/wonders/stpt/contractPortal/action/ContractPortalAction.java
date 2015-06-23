package com.wonders.stpt.contractPortal.action;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.opensymphony.xwork2.ActionSupport;
import com.wonders.stpt.contractPortal.model.bo.CoverContractRange;
import com.wonders.stpt.contractPortal.model.bo.CoverContractStat;
import com.wonders.stpt.contractPortal.model.bo.CoverProjectStat;
import com.wonders.stpt.contractPortal.service.ContractPortalService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/contractPortal")
@Controller("contractPortalAction")
@Scope("prototype")
public class ContractPortalAction extends ActionSupport{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private ContractPortalService contractPortalService;
	
	ActionWriter ac = new ActionWriter(response);
	
	DateFormat df_year = new SimpleDateFormat("yyyy");
	DateFormat df_month = new SimpleDateFormat("MM");
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Action(value="getManagementDatas")
	public String getManagementDatas(){
		String sql = "select name,value from dw_contract_p_management t where t.company_id is null order by t.order_no";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="getProjectDatas")
	public String getProjectDatas(){
		ac.writeAjax(getDatas("cover_project_stat"));
		return null;
	}
	
	@Action(value="getContractDatas")
	public String getContractDatas(){
		ac.writeAjax(getDatas("cover_contract_stat"));
		return null;
	}
	
	@Action(value="getRangeDatas")
	public String getRangeDatas(){
		String last_year = this.request.getParameter("last_year");
		String this_year = this.request.getParameter("this_year");
		String sql = "select t.year,t.type,sum(t.count_range1),sum(t.count_range2),sum(t.count_range3),sum(t.count_range4) "+
				 " from cover_contract_range t where t.year = '"+last_year+"' or t.year = '"+this_year+"' "+
				 " group by year,type order by t.year";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="getRangeDatasForPic")
	public String getRangeDatasForPic(){
		String this_year = this.request.getParameter("this_year");
		String sql = "select sum(t.count_range1),sum(t.count_range2),sum(t.count_range3),sum(t.count_range4) from cover_contract_range t "+ 
				" where t.year = '"+this_year+"' and t.type = '建设类'";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		List<Object[]> outList = new ArrayList<Object[]>();
		Object[] nullValue = {0,0,0,0};
		if(list!=null&&list.size()==1){
			outList.addAll(list);
		}else{
			outList.add(nullValue);
		}
		
		sql = "select sum(t.count_range1),sum(t.count_range2),sum(t.count_range3),sum(t.count_range4) from cover_contract_range t "+ 
			" where t.year = '"+this_year+"' and t.type = '运营类'";
		list = contractPortalService.getDatasBySql(sql);
		if(list!=null&&list.size()==1){
			outList.addAll(list);
		}else{
			outList.add(nullValue);
		}
		
		sql = "select sum(t.count_range1),sum(t.count_range2),sum(t.count_range3),sum(t.count_range4) from cover_contract_range t "+ 
			" where t.year = '"+this_year+"' and t.type = '维护类'";
		list = contractPortalService.getDatasBySql(sql);
		if(list!=null&&list.size()==1){
			outList.addAll(list);
		}else{
			outList.add(nullValue);
		}
		ac.writeJson(outList);
		return null;
	}
	
	@Action(value="getContractDatasForPic")
	public String getContractDatasForPic(){
		String this_year = this.request.getParameter("this_year");
		String start_year = String.valueOf(Integer.valueOf(this_year)-5);
		String sql = "select t.type,t.year,sum(t.count) count_num from cover_contract_stat t "+
					" where year > '"+start_year+"' and year <= '"+this_year+"' "+
					" group by t.type,t.year "+
					" order by t.type,t.year";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		Object[] strs_1 = new Object[5];
		Object[] strs_2 = new Object[5];
		Object[] strs_3 = new Object[5];
		List<Object[]> listOut = new ArrayList<Object[]>();
		int j = 0;
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if(j==5||(i>0&&!String.valueOf(list.get(i)[0]).equals(String.valueOf(list.get(i-1)[0])))){
					j = 0;
				}
				if("建设类".equals(list.get(i)[0])){
					strs_1[j] = list.get(i)[2];
				}else if("运营类".equals(list.get(i)[0])){
					strs_2[j] = list.get(i)[2];
				}else if("维护类".equals(list.get(i)[0])){
					strs_3[j] = list.get(i)[2];
				}
				j++;
				
			}
			listOut.add(strs_1);
			listOut.add(strs_2);
			listOut.add(strs_3);
		}
		ac.writeJson(listOut);
		return null;
	}
	
	@Action(value="getContractDatasForPic2")
	public String getContractDatasForPic2(){
		String sql = "select t.type,sum(t.sum) sum_num,sum(t.count) count_num from cover_contract_stat t "+
					" group by t.type "+
					" order by case t.type when '建设类' then 1 when '运营类' then 2 when '维护类' then 3 end";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		Object[] strs_1 = new Object[3];
		Object[] strs_2 = new Object[3];
		List<Object[]> listOut = new ArrayList<Object[]>();
		if(list!=null&&list.size()==3){
			for(int i=0;i<list.size();i++){
				strs_1[i] = list.get(i)[1];
				strs_2[i] = list.get(i)[2];
			}
			listOut.add(strs_1);
			listOut.add(strs_2);
		}
		ac.writeJson(listOut);
		return null;
	}
	
	private String getDatas(String table){
		String count_sum = "0";
		String money_sum = "0";
		String month_count_sum = "0";
		String month_money_sum = "0";
		String last_month_count_sum = "0";
		String last_month_money_sum = "0";
		String sql = "select sum(t.count) count_sum,sum(t.sum) money_sum from "+table+" t ";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		if(list!=null&&list.size()==1){
			count_sum = getZeroIfNull(list.get(0)[0]);
			money_sum = getFormatDouble2(getZeroIfNull(list.get(0)[1]));
		}
		
		Date date = new Date();
		String year = df_year.format(date);
		String month = df_month.format(date);
		String sql_where = " where t.year = '"+year+"' and t.month = '"+month+"' ";
		list = contractPortalService.getDatasBySql(sql+sql_where);
		if(list!=null&&list.size()==1){
			month_count_sum = getZeroIfNull(list.get(0)[0]);
			month_money_sum = getFormatDouble2(getZeroIfNull(list.get(0)[1]));
		}
		
		String last_month = getLastMonth(month);
		if("12".equals(last_month)){
			year = getLastYear(year);
		}
		sql_where = " where t.year = '"+year+"' and t.month = '"+last_month+"' ";
		list = contractPortalService.getDatasBySql(sql+sql_where);
		if(list!=null&&list.size()==1){
			last_month_count_sum = getZeroIfNull(list.get(0)[0]);
			last_month_money_sum = getZeroIfNull(list.get(0)[1]);
		}
		String count_percent = getProgressPersent(last_month_count_sum,month_count_sum);
		String money_percent = getProgressPersent(last_month_money_sum,month_money_sum);
		
		String returnJson = "{\"count_sum\":\""+count_sum+"\",\"money_sum\":\""+money_sum+
							"\",\"month_count_sum\":\""+month_count_sum+"\",\"month_money_sum\":\""+month_money_sum+
							"\",\"count_percent\":\""+count_percent+"\",\"money_percent\":\""+money_percent+"\"}";
		return returnJson;
	}
	
	private String getZeroIfNull(Object o){
		String showValue = "0";
		if(o!=null){
			showValue = o.toString();
			if(showValue.trim().equals("null")){
				showValue = "0";
			}
		}
		return showValue;
	}
	
	private String getLastMonth(String month){
		int this_month = Integer.valueOf(month);
		if(this_month==1){
			this_month = 13;
		}
		int last_month = this_month - 1;
		String return_value = "";
		if(last_month<10){
			return_value = "0"+String.valueOf(last_month);
		}else{
			return_value = String.valueOf(last_month);
		}
		return 	return_value;
	}
	
	private String getLastYear(String year){
		int this_year = Integer.valueOf(year);
		int last_year = this_year - 1;
		return String.valueOf(last_year);
	}
	
	private String getProgressPersent(String lastStr,String nowStr){
		double last = Double.valueOf(lastStr);
		double now = Double.valueOf(nowStr);
		String return_value = "";
		if("0".equals(lastStr)){
			if("0".equals(nowStr)){
				return_value = "0";
			}else{
				return_value = "∞";
			}
		}else{
			return_value = getFormatDouble((now-last)*100/last);
		}
		if(!"∞".equals(return_value)){
			return_value += "%";
		}
		return return_value;
	}
	
	private String getFormatDouble(Double d){
		DecimalFormat ddf=new DecimalFormat("#");//.##
		String st=ddf.format(d);
		return st;
	}
	
	private String getFormatDouble2(String str){
		double d = Double.valueOf(str);
		DecimalFormat ddf=new DecimalFormat(".####");//.##
		String st=ddf.format(d);
		if("0".equals(str)){
			return "0";
		}
		return st;
	}
	
	@Action(value="generateCoverContractStat")
	public String generateCoverContractStat(){
		String sql = "select year,month,type,count(*) count_num,sum(contract_price) price_sum from ( "+
			" select substr(t.contract_signed_date,0,4) year,substr(t.contract_signed_date,6,2) month, "+
			" (case when t.contract_type like '1,%' then '1'  "+
			" when t.contract_type like '2,%' and t.contract_owner_execute_name like '%运营有限公司%' then '2'  "+
			" when t.contract_type like '2,%' and (t.contract_owner_execute_name like '%维保%' or t.contract_owner_execute_name like '%维护保障%') then '3' end) type, "+ 
			" t.contract_price from c_contract t "+
			" where t.removed = 0 and t.contract_signed_date is not null and t.contract_type is not null "+
			" ) v where type is not null group by year,month,type "+
			" order by year,month,type";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		List<CoverContractStat> saveList = new ArrayList<CoverContractStat>();
		if(list!=null&&list.size()>0){
			contractPortalService.updateDatasBySql("delete from cover_contract_stat");
			for(int i=0;i<list.size();i++){
				CoverContractStat coverContractStat = new CoverContractStat();
				coverContractStat.setYear(String.valueOf(list.get(i)[0]));
				coverContractStat.setMonth(String.valueOf(list.get(i)[1]));
				coverContractStat.setType(String.valueOf(list.get(i)[2]).replace("1", "建设类").replace("2", "运营类").replace("3", "维护类"));
				coverContractStat.setCount(Long.valueOf(String.valueOf(list.get(i)[3])));
				coverContractStat.setSum(Double.valueOf(String.valueOf(list.get(i)[4])));
				coverContractStat.setUpdatetime(df.format(new Date()));
				saveList.add(coverContractStat);
			}
			contractPortalService.saveOrUpdateAll(saveList);
		}
		return null;
	}
	
	@Action(value="generateCoverProjectStat")
	public String generateCoverProjectStat(){
		String sql = "select year,month,project_type,"+
		    " count(*) count_num,sum(project_budget_all) price_sum from ( "+
			" select substr(t.approval_date,0,4) year,substr(t.approval_date,6,2) month,t.project_type, "+
			" t.project_budget_all from c_project t "+
			" where t.removed = 0 and t.approval_date is not null and t.project_budget_all is not null "+
			" ) v where project_type is not null group by year,month,project_type "+
			" order by year,month,project_type";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		List<CoverProjectStat> saveList = new ArrayList<CoverProjectStat>();
		if(list!=null&&list.size()>0){
			contractPortalService.updateDatasBySql("delete from cover_project_stat");
			for(int i=0;i<list.size();i++){
				CoverProjectStat coverProjectStat = new CoverProjectStat();
				coverProjectStat.setYear(String.valueOf(list.get(i)[0]));
				coverProjectStat.setMonth(String.valueOf(list.get(i)[1]));
				coverProjectStat.setType(String.valueOf(list.get(i)[2]).replace("1", "外部项目").replace("2", "内部项目资本类").replace("3", "内部项目大修类").replace("4", "内部项目能源类"));
				coverProjectStat.setCount(Long.valueOf(String.valueOf(list.get(i)[3])));
				coverProjectStat.setSum(Double.valueOf(String.valueOf(list.get(i)[4])));
				coverProjectStat.setUpdatetime(df.format(new Date()));
				saveList.add(coverProjectStat);
			}
			contractPortalService.saveOrUpdateAll(saveList);
		}
		return null;
	}
	
	@Action(value="generateCoverContractRange")
	public String generateCoverContractRange(){
		String sql = "select year,month,type,"+
			" count(case when contract_price>0 and contract_price<=200 then '1' end) count_num1,"+
			" count(case when contract_price>200 and contract_price<=400 then '2' end) count_num2,"+
			" count(case when contract_price>400 and contract_price<=600 then '3' end) count_num3,"+
			" count(case when contract_price>600 then '4' end) count_num4 from ( "+
			" select substr(t.contract_signed_date,0,4) year,substr(t.contract_signed_date,6,2) month, "+
			" (case when t.contract_type like '1,%' then '1'  "+
			" when t.contract_type like '2,%' and t.contract_owner_execute_name like '%运营有限公司%' then '2'  "+
			" when t.contract_type like '2,%' and (t.contract_owner_execute_name like '%维保%' or t.contract_owner_execute_name like '%维护保障%') then '3' end) type,"+
			" t.contract_price from c_contract t "+
			" where t.removed = 0 and t.contract_signed_date is not null and t.contract_type is not null "+
			" ) v where type is not null group by year,month,type "+
			" order by year,month,type";
		List<Object[]> list = contractPortalService.getDatasBySql(sql);
		List<CoverContractRange> saveList = new ArrayList<CoverContractRange>();
		if(list!=null&&list.size()>0){
			contractPortalService.updateDatasBySql("delete from cover_contract_range");
			for(int i=0;i<list.size();i++){
				CoverContractRange coverContractRange = new CoverContractRange();
				coverContractRange.setYear(String.valueOf(list.get(i)[0]));
				coverContractRange.setMonth(String.valueOf(list.get(i)[1]));
				coverContractRange.setType(String.valueOf(list.get(i)[2]).replace("1", "建设类").replace("2", "运营类").replace("3", "维护类"));
				coverContractRange.setCountRange1(Long.valueOf(String.valueOf(list.get(i)[3])));
				coverContractRange.setCountRange2(Long.valueOf(String.valueOf(list.get(i)[4])));
				coverContractRange.setCountRange3(Long.valueOf(String.valueOf(list.get(i)[5])));
				coverContractRange.setCountRange4(Long.valueOf(String.valueOf(list.get(i)[6])));
				coverContractRange.setUpdatetime(df.format(new Date()));
				saveList.add(coverContractRange);
			}
			contractPortalService.saveOrUpdateAll(saveList);
		}
		return null;
	}
}
