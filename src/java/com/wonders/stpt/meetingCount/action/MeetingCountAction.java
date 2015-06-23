package com.wonders.stpt.meetingCount.action;

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
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wonders.stpt.meetingCount.service.MeetingCountService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/meetingCount")
@Controller("meetingCountAction")
@Scope("prototype")
public class MeetingCountAction {
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private MeetingCountService meetingCountService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat ydf = new SimpleDateFormat("yyyy");
	
	@Action(value="meetingRoomCount",results={@Result(name="success",location="/meetingCount/meetingRoomCount.jsp")})
	public String meetingRoomCount(){
		String countType = request.getParameter("countType");
		String timeFrame = request.getParameter("timeFrame");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String meetingType = request.getParameter("meetingType");
		if(timeFrame==null){
			timeFrame = "1";
		}
		if(year==null){
			year = ydf.format(new Date());
		}
		if(month==null){
			month = "1";
		}
		request.setAttribute("timeFrame", timeFrame);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("countType", countType);
		request.setAttribute("meetingType", meetingType);
		String startDateMin = "";
		String startDateMax = "";
		String dateString = "";
		String huanbi_startDateMin = "";
		String huanbi_startDateMax = "";
		String tongbi_startDateMin = "";
		String tongbi_startDateMax = "";
		if("1".equals(timeFrame)){
			dateString = year + "年" + month + "月";
			int imonth = Integer.parseInt(month);
			if(imonth<10){
				month = "0"+month;
			}
			startDateMin = year + "-" + month + "-01";
			String tongbi_year = String.valueOf((Integer.parseInt(year)-1));
			tongbi_startDateMin = tongbi_year + "-" + month + "-01";
			int inextMonth = imonth +1;
			String maxYear = year;
			if(inextMonth==13){
				inextMonth = 1;
				maxYear = String.valueOf((Integer.parseInt(year)+1));
			}
			month = String.valueOf(inextMonth);
			if(inextMonth<10){
				month = "0"+month;
			}
			startDateMax = maxYear + "-" + month + "-01";
			String tongbi_year_max = String.valueOf((Integer.parseInt(maxYear)-1));
			tongbi_startDateMax = tongbi_year_max + "-" + month + "-01";
			
			int huanbi_imonth = imonth -1;
			String huanbi_year = year;
			if(huanbi_imonth==0){
				huanbi_imonth = 12;
				huanbi_year = String.valueOf((Integer.parseInt(year)-1));
			}
			month = String.valueOf(huanbi_imonth);
			if(huanbi_imonth<10){
				month = "0"+month;
			}
			huanbi_startDateMin = huanbi_year + "-" + month + "-01";
			
			
		}else if("2".equals(timeFrame)){
			dateString = year + "年" + month + "季度";
			if("1".equals(month)){
				startDateMin = year + "-01-01";
				startDateMax = year + "-04-01";
				year = String.valueOf((Integer.parseInt(year)-1));
				huanbi_startDateMin = year + "-10-01";
				tongbi_startDateMin = year + "-01-01";
				tongbi_startDateMax = year + "-04-01";
			}else if("2".equals(month)){
				startDateMin = year + "-04-01";
				startDateMax = year + "-07-01";
				huanbi_startDateMin = year + "-01-01";
				year = String.valueOf((Integer.parseInt(year)-1));
				tongbi_startDateMin = year + "-04-01";
				tongbi_startDateMax = year + "-07-01";
			}
			else if("3".equals(month)){
				startDateMin = year + "-07-01";
				startDateMax = year + "-10-01";
				huanbi_startDateMin = year + "-04-01";
				year = String.valueOf((Integer.parseInt(year)-1));
				tongbi_startDateMin = year + "-07-01";
				tongbi_startDateMax = year + "-10-01";
			}
			else if("4".equals(month)){
				startDateMin = year + "-10-01";
				huanbi_startDateMin = year + "-07-01";
				tongbi_startDateMin = String.valueOf((Integer.parseInt(year)-1)) + "-10-01";
				tongbi_startDateMax = year + "-01-01";
				year = String.valueOf((Integer.parseInt(year)+1));
				startDateMax = year + "-01-01";
			}
		}else if("3".equals(timeFrame)){
			dateString = year + "年" + month.replace("1", "上半年度").replace("2", "下半年度");
			if("1".equals(month)){
				startDateMin = year + "-01-01";
				startDateMax = year + "-07-01";
				year = String.valueOf((Integer.parseInt(year)-1));
				huanbi_startDateMin = year + "-07-01";
				tongbi_startDateMin = year + "-01-01";
				tongbi_startDateMax = year + "-07-01";
			}else if("2".equals(month)){
				startDateMin = year + "-07-01";
				huanbi_startDateMin = year + "-01-01";
				tongbi_startDateMin = String.valueOf((Integer.parseInt(year)-1)) + "-07-01";
				tongbi_startDateMax = year + "-01-01";
				year = String.valueOf((Integer.parseInt(year)+1));
				startDateMax = year + "-01-01";
			}
		}else if("4".equals(timeFrame)){
			dateString = year + "年";
			startDateMin = year + "-01-01";
			year = String.valueOf((Integer.parseInt(year)+1));
			startDateMax = year + "-01-01";
			year = String.valueOf((Integer.parseInt(year)-2));
			huanbi_startDateMin = year + "-01-01";
			tongbi_startDateMin = huanbi_startDateMin;
			tongbi_startDateMax = startDateMin;
		}
		huanbi_startDateMax = startDateMin;
		
//		System.out.println("startDateMin=="+startDateMin);
//		System.out.println("startDateMax=="+startDateMax);
//		System.out.println("huanbi_startDateMin=="+huanbi_startDateMin);
//		System.out.println("huanbi_startDateMax=="+huanbi_startDateMax);
//		System.out.println("tongbi_startDateMin=="+tongbi_startDateMin);
//		System.out.println("tongbi_startDateMax=="+tongbi_startDateMax);
		request.setAttribute("dateString", dateString);
		String basesql = "";
		basesql = " (case when (t.people_count is null and t.meet_addr = 1) then 60 "+
				" when (t.people_count is null and t.meet_addr = 3) then 20 "+
				" when (t.people_count is null and (t.meet_addr = 4 or t.meet_addr = 5 or t.meet_addr = 6 or t.meet_addr = 7)) then 10 "+
				" when (t.people_count is null and t.meet_addr = 10040) then 20 "+
				" when (t.people_count is null and t.meet_addr = 2) then 25 "+
				" when (t.people_count is null and (t.meet_addr = 10080 or t.meet_addr = 10081)) then 0 "+
				" else t.people_count end) people_count, "+
				" t.inner_dept_count,t.outter_dept_count,t.all_dept_count "+
				" from t_met_meetflow t where t.apply_date >= '2014-01-01' "+
				" and t.start_date >= ? and t.start_date < ? "+
				" and t.removed = '0' and t.meet_state = '已通过' ";
		List<Object> src = new ArrayList<Object>();
		src.add(startDateMin);
		src.add(startDateMax);
		
		String sql = "";
		//会议室统计
		if("1".equals(countType)){
			//按等级分类-集团会议室会议
			sql = "select level_type,count(*),sum(people_count) from (  select t.level_type,t.theme_type,"+basesql+" and t.meeting_type = '1' ) c group by level_type order by level_type ";
			List<Object[]> list1 = meetingCountService.findBySql(sql, src);
			list1 = addEmptyData(list1,4);
			request.setAttribute("list1", list1);
			//按等级分类-视频会议
			sql = "select level_type,count(*),sum(people_count) from (  select t.level_type,t.theme_type,"+basesql+" and t.meeting_type = '2' ) c group by level_type order by level_type ";
			List<Object[]> list2 = meetingCountService.findBySql(sql, src);
			list2 = addEmptyData(list2,4);
			request.setAttribute("list2", list2);
			//按会议主题分类-集团会议室会议
			sql = "select theme_type,count(*),sum(people_count) from (  select t.level_type,t.theme_type,"+basesql+" and t.meeting_type = '1' ) c group by theme_type order by theme_type ";
			List<Object[]> list3 = meetingCountService.findBySql(sql, src);
			list3 = addEmptyData(list3,3);
			request.setAttribute("list3", list3);
			//按会议主题分类-视频会议
			sql = "select theme_type,count(*),sum(people_count) from (  select t.level_type,t.theme_type,"+basesql+" and t.meeting_type = '2' ) c group by theme_type order by theme_type ";
			List<Object[]> list4 = meetingCountService.findBySql(sql, src);
			list4 = addEmptyData(list4,3);
			request.setAttribute("list4", list4);
			//环比
			sql = "select count(*),sum(people_count) from(  select t.level_type,t.theme_type,"+basesql+" ) ";
			src = new ArrayList<Object>();
			src.add(huanbi_startDateMin);
			src.add(huanbi_startDateMax);
			List<Object[]> huanbi_list = meetingCountService.findBySql(sql, src);
			//同比
			src = new ArrayList<Object>();
			src.add(tongbi_startDateMin);
			src.add(tongbi_startDateMax);
			List<Object[]> tongbi_list = meetingCountService.findBySql(sql, src);
			
			//合计
			Integer[] sum1 = new Integer[7];
			sum1[0] = Integer.parseInt(list1.get(0)[1]+"")+Integer.parseInt(list2.get(0)[1]+"");
			sum1[1] = Integer.parseInt(list1.get(1)[1]+"")+Integer.parseInt(list2.get(1)[1]+"");
			sum1[2] = Integer.parseInt(list1.get(2)[1]+"")+Integer.parseInt(list2.get(2)[1]+"");
			sum1[3] = Integer.parseInt(list1.get(3)[1]+"")+Integer.parseInt(list2.get(3)[1]+"");
			sum1[4] = Integer.parseInt(list3.get(0)[1]+"")+Integer.parseInt(list4.get(0)[1]+"");
			sum1[5] = Integer.parseInt(list3.get(1)[1]+"")+Integer.parseInt(list4.get(1)[1]+"");
			sum1[6] = Integer.parseInt(list3.get(2)[1]+"")+Integer.parseInt(list4.get(2)[1]+"");
			request.setAttribute("sum1", sum1);
			
			Integer[] sum2 = new Integer[7];
			sum2[0] = Integer.parseInt(list1.get(0)[2]+"")+Integer.parseInt(list2.get(0)[2]+"");
			sum2[1] = Integer.parseInt(list1.get(1)[2]+"")+Integer.parseInt(list2.get(1)[2]+"");
			sum2[2] = Integer.parseInt(list1.get(2)[2]+"")+Integer.parseInt(list2.get(2)[2]+"");
			sum2[3] = Integer.parseInt(list1.get(3)[2]+"")+Integer.parseInt(list2.get(3)[2]+"");
			sum2[4] = Integer.parseInt(list3.get(0)[2]+"")+Integer.parseInt(list4.get(0)[2]+"");
			sum2[5] = Integer.parseInt(list3.get(1)[2]+"")+Integer.parseInt(list4.get(1)[2]+"");
			sum2[6] = Integer.parseInt(list3.get(2)[2]+"")+Integer.parseInt(list4.get(2)[2]+"");
			request.setAttribute("sum2", sum2);
			
			int sumAll1 = sum1[0] +sum1[1] +sum1[2] +sum1[3];
			request.setAttribute("sumAll1", sumAll1);
			int sumAll2 = sum2[0] +sum2[1] +sum2[2] +sum2[3];
			request.setAttribute("sumAll2", sumAll2);
			
			int huanbi_sumAll1 = Integer.parseInt(huanbi_list.get(0)[0]+"");
			String huanbi1 = "";
			String huanbi2 = "";
			if(huanbi_sumAll1==0){
				huanbi1 = "";
			}else{
				huanbi1 = getFormatDouble((sumAll1*100.0)/getNoZero(huanbi_sumAll1*1.0));
			}
			if(huanbi_list.get(0)[1]==null||"".equals(huanbi_list.get(0)[1])){
				huanbi2 = "";
			}else{
				int huanbi_sumAll2 = Integer.parseInt(huanbi_list.get(0)[1]+"");
				huanbi2 = getFormatDouble((sumAll2*100.0)/getNoZero(huanbi_sumAll2*1.0));
			}
			request.setAttribute("huanbi1", huanbi1);
			request.setAttribute("huanbi2", huanbi2);
			
			int tongbi_sumAll1 = Integer.parseInt(tongbi_list.get(0)[0]+"");
			String tongbi1 = "";
			String tongbi2 = "";
			if(tongbi_sumAll1==0){
				tongbi1 = "";
			}else{
				tongbi1 = getFormatDouble((sumAll1*100.0)/getNoZero(tongbi_sumAll1*1.0));
			}
			if(tongbi_list.get(0)[1]==null||"".equals(tongbi_list.get(0)[1])){
				tongbi2 = "";
			}else{
				int tongbi_sumAll2 = Integer.parseInt(tongbi_list.get(0)[1]+"");
				tongbi2 = getFormatDouble((sumAll2*100.0)/getNoZero(tongbi_sumAll2*1.0));
			}
			request.setAttribute("tongbi1", tongbi1);
			request.setAttribute("tongbi2", tongbi2);
			
			String[] per1 = new String[7];
			per1[0] = getFormatDouble((sum1[0]*100.0)/getNoZero(sumAll1*1.0));
			per1[1] = getFormatDouble((sum1[1]*100.0)/getNoZero(sumAll1*1.0));
			per1[2] = getFormatDouble((sum1[2]*100.0)/getNoZero(sumAll1*1.0));
			per1[3] = getFormatDouble((sum1[3]*100.0)/getNoZero(sumAll1*1.0));
			per1[4] = getFormatDouble((sum1[4]*100.0)/getNoZero(sumAll1*1.0));
			per1[5] = getFormatDouble((sum1[5]*100.0)/getNoZero(sumAll1*1.0));
			per1[6] = getFormatDouble((sum1[6]*100.0)/getNoZero(sumAll1*1.0));
			request.setAttribute("per1", per1);
			
			String[] per2 = new String[7];
			per2[0] = getFormatDouble((sum2[0]*100.0)/getNoZero(sumAll2*1.0));
			per2[1] = getFormatDouble((sum2[1]*100.0)/getNoZero(sumAll2*1.0));
			per2[2] = getFormatDouble((sum2[2]*100.0)/getNoZero(sumAll2*1.0));
			per2[3] = getFormatDouble((sum2[3]*100.0)/getNoZero(sumAll2*1.0));
			per2[4] = getFormatDouble((sum2[4]*100.0)/getNoZero(sumAll2*1.0));
			per2[5] = getFormatDouble((sum2[5]*100.0)/getNoZero(sumAll2*1.0));
			per2[6] = getFormatDouble((sum2[6]*100.0)/getNoZero(sumAll2*1.0));
			request.setAttribute("per2", per2);
			
			Integer[] sumDown = new Integer[4];
			sumDown[0] = Integer.parseInt(list1.get(0)[1]+"")+Integer.parseInt(list1.get(1)[1]+"")+Integer.parseInt(list1.get(2)[1]+"")+Integer.parseInt(list1.get(3)[1]+"");
			sumDown[1] = Integer.parseInt(list1.get(0)[2]+"")+Integer.parseInt(list1.get(1)[2]+"")+Integer.parseInt(list1.get(2)[2]+"")+Integer.parseInt(list1.get(3)[2]+"");
			sumDown[2] = Integer.parseInt(list2.get(0)[1]+"")+Integer.parseInt(list2.get(1)[1]+"")+Integer.parseInt(list2.get(2)[1]+"")+Integer.parseInt(list2.get(3)[1]+"");
			sumDown[3] = Integer.parseInt(list2.get(0)[2]+"")+Integer.parseInt(list2.get(1)[2]+"")+Integer.parseInt(list2.get(2)[2]+"")+Integer.parseInt(list2.get(3)[2]+"");
			request.setAttribute("sumDown", sumDown);
			
			String[] perDown = new String[4];
			perDown[0] = getFormatDouble((sumDown[0]*100.0)/getNoZero((sumDown[0]+sumDown[2])*1.0));
			perDown[1] = getFormatDouble((sumDown[1]*100.0)/getNoZero((sumDown[1]+sumDown[3])*1.0));
			perDown[2] = getFormatDouble((sumDown[2]*100.0)/getNoZero((sumDown[0]+sumDown[2])*1.0));
			perDown[3] = getFormatDouble((sumDown[3]*100.0)/getNoZero((sumDown[1]+sumDown[3])*1.0));
			request.setAttribute("perDown", perDown);
			return "success";
		}else{
			//按部室统计
			sql = "select main_dept,count(*) count_num,sum(people_count) from ( select t.main_dept,"+basesql;
			if("2".equals(meetingType)){
				sql += " and t.meeting_type = '1' ";
			}else if("3".equals(meetingType)){
				sql += " and t.meeting_type = '2' ";
			}
			sql += ") c group by main_dept order by count_num desc ";
			List<Object[]> deptlist = meetingCountService.findBySql(sql, src);
			List<String[]> showlist = new ArrayList<String[]>();
			src = new ArrayList<Object>();
			src.add(huanbi_startDateMin);
			src.add(huanbi_startDateMax);
			List<Object[]> huanbi_deptlist = meetingCountService.findBySql(sql, src);
			src = new ArrayList<Object>();
			src.add(tongbi_startDateMin);
			src.add(tongbi_startDateMax);
			List<Object[]> tongbi_deptlist = meetingCountService.findBySql(sql, src);
			int count_sum = 0;
			int people_sum = 0;
			if(deptlist!=null&&deptlist.size()>0){
				for(int i=0;i<deptlist.size();i++){
					count_sum += Integer.parseInt(deptlist.get(i)[1]+"");
					people_sum += Integer.parseInt(deptlist.get(i)[2]+"");
				}
				Map<String,String> huanbi_map = new HashMap<String,String>();
				for(int i=0;i<huanbi_deptlist.size();i++){
					huanbi_map.put(huanbi_deptlist.get(i)[0]+"", huanbi_deptlist.get(i)[1]+"");
				}
				Map<String,String> tongbi_map = new HashMap<String,String>();
				for(int i=0;i<tongbi_deptlist.size();i++){
					tongbi_map.put(tongbi_deptlist.get(i)[0]+"", tongbi_deptlist.get(i)[1]+"");
				}
				for(int i=0;i<deptlist.size();i++){
					String[] strs = new String[7];
					strs[0] = deptlist.get(i)[0]+"";
					strs[1] = deptlist.get(i)[1]+"";
					strs[2] = getFormatDouble((Integer.parseInt(deptlist.get(i)[1]+"")*100.0)/getNoZero(count_sum*1.0));
					strs[3] = deptlist.get(i)[2]+"";
					strs[4] = getFormatDouble((Integer.parseInt(deptlist.get(i)[2]+"")*100.0)/getNoZero(people_sum*1.0));
					if(huanbi_map.containsKey(deptlist.get(i)[0]+"")){
						strs[5] = getFormatDouble((Integer.parseInt(deptlist.get(i)[1]+"")*100.0)/getNoZero(Integer.parseInt(huanbi_map.get(deptlist.get(i)[0]+""))*1.0));
					}
					if(tongbi_map.containsKey(deptlist.get(i)[0]+"")){
						strs[6] = getFormatDouble((Integer.parseInt(deptlist.get(i)[1]+"")*100.0)/getNoZero(Integer.parseInt(tongbi_map.get(deptlist.get(i)[0]+""))*1.0));
					}
					showlist.add(strs);
				}
			}
			request.setAttribute("count_sum", count_sum);
			request.setAttribute("people_sum", people_sum);
			request.setAttribute("showlist", showlist);
			return "success";
		}
	}
	
	private List<Object[]> addEmptyData(List<Object[]> list,int length){
		boolean flag = false;
		for(int i=1;i<length+1;i++){
			if(list!=null&&list.size()>0){
				for(int j=0;j<list.size();j++){
					if((i+"").equals(list.get(j)[0])){
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				Object[] emptyData = {i,0,0};
				list.add(emptyData);
			}
			flag = false;
		}
		return list;
	}
	
	private String getFormatDouble(Double value){
		DecimalFormat ddf=new DecimalFormat("#.#");
		String st = ddf.format(value)+"%";
		return st;
	}
	
	private double getNoZero(double num){
		if(num==0d){
			num = 1d;
		}
		return num;
	}
}
