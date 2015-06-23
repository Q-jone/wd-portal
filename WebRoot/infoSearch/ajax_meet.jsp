<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%@ page import="com.wonders.stpt.util.DateUtil"%>
<%@ page import="com.wonders.module.common.ExecuteSql"%>
<%@ page import="com.wonders.stpt.util.ActionWriter"%>

<%!
	public List<String[]> findMeetListByUser(String userName){
		List<String[]> list = new ArrayList<String[]>();
		ResultSet rs = null;
		try {
			String todayStr = DateUtil.getCurrDate("yyyy-MM-dd");
			String curentTime = DateUtil.getNowTime();
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_YEAR);
		    calendar.set(Calendar.DAY_OF_YEAR, day + 7);
		    String dayStr = DateUtil.getDateStr(calendar.getTime(), "yyyy-MM-dd");

			ExecuteSql dealsql= new ExecuteSql("dataSource2");
			String sql = "select a.id,a.meet_id,a.MEET_PRESENT,b.MR_ADDR,a.meet_title,a.start_date,a.start_time,a.end_time,a.MEET_STATE from T_MET_MEETFLOW a inner join T_MET_MEETINGROOM b on a.MEET_ADDR=b.id " +
					"where a.removed='0' " +
					"and a.MEET_STATE<>'已驳回' " +
					"and a.MEET_PRESENT like '%"+userName+"%' " +
					"and ((a.start_date>'"+todayStr+"' and a.start_date<='"+dayStr+"') " +
					//"or (a.start_date='"+todayStr+"' and end_time>='"+curentTime.substring(0,16)+"')" +		//判断当天会议
					") " +		
					"order by START_DATE,START_TIME ";
			rs = dealsql.ExecuteDemandSql(sql);
			String[] temps = null;
			while(rs.next()){
				String[] strs = new String[10];
				strs[0] = rs.getString("id");
				strs[1] = rs.getString("meet_id");
				strs[2] = rs.getString("MR_ADDR");
				strs[3] = rs.getString("meet_title");
				strs[4] = rs.getString("start_date");
				strs[5] = rs.getString("start_time").substring(11,16);
				strs[6] = rs.getString("end_time").substring(11,16);
				strs[7] = "false";
				strs[8] = rs.getString("MEET_STATE");
				
				//下面判断会议时间是否冲突
				if(strs[8].equals("已通过")&&temps!=null&&temps[4].equals(strs[4])){
					if((temps[5].compareTo(strs[5])>0&&temps[5].compareTo(strs[6])<0)
							||(strs[5].compareTo(temps[5])>0&&strs[5].compareTo(temps[6])<0)){
						strs[7] = "true";
						temps[7] = "true";
					}
				}
				if(strs[8].equals("已通过")){
					temps = strs;
				}
				if(strs[8].equals("已通过")){
					strs[9] = "即将召开";
				}else{
					strs[9] = "申请中";
				}
				String nowTime = curentTime.substring(11,16);
				if(strs[8].equals("已通过")&&todayStr.equals(strs[4])){
					if(nowTime.compareTo(strs[5])>=0
						&&nowTime.compareTo(strs[6])<=0){
						strs[9] = "正在进行";
					}else if(nowTime.compareTo(strs[5])<0){
						strs[9] = "即将召开";
					}else if(nowTime.compareTo(strs[6])>0){
						strs[9] = "已结束";
					}
				}else if(strs[8].equals("已通过")&&todayStr.compareTo(strs[4])<0){
					strs[9] = "即将召开";
				}
				list.add(strs);
				
			}
			rs.close();
			dealsql.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		return list;
	}
%>




<%
	Donull donull = new Donull();
	String userName = donull.dealNull((String)session.getAttribute("userName"));
	ActionWriter aw = new ActionWriter(response);
	List<String[]> list = new ArrayList<String[]>();
	list = findMeetListByUser(userName);
	aw.writeJson(list);
	
%>