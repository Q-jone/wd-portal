<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.*"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%@ page import="com.wonders.stpt.util.StringUtil"%>
<%@ page import="com.wonders.stpt.util.ActionWriter"%>
<%@ page import="com.wonders.module.common.ExecuteSql"%>
<%@ page import="com.wonders.stpt.util.DateUtil"%>

<%!
    static String[] times = {"全天","06:00","06:15","06:30","06:45","07:00","07:15",
            "07:30","07:45","08:00","08:15","08:30","08:45","09:00","09:15","09:30",
            "09:45","10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45",
            "12:00","12:15","12:30","12:45","13:00","13:15","13:30","13:45","14:00",
            "14:15","14:30","14:45","15:00","15:15","15:30","15:45","16:00","16:15",
            "16:30","16:45","17:00","17:15","17:30","17:45","18:00","18:15","18:30",
            "18:45","19:00","19:15","19:30","19:45","20:00","20:15","20:30","20:45",
            "21:00","21:15","21:30","21:45","22:00","22:15","22:30","22:45","23:00",
            "23:30"};
%>
<%!
    public static Date getTheDate(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }

    public static List<List<Map<String,String>>> getManagerInfo(String loginName,String userId){
        List<List<Map<String,String>>> list = new ArrayList<List<Map<String,String>>>();
        List<Map<String,String>> list1 = getManagerInfoByDay(loginName,userId);
        List<Map<String,String>> list2 = getManagerInfoByWeek(loginName,userId);
        list.add(list1);
        list.add(list2);
        return list;
    }
    //取得今天日程安排
    public static List<Map<String,String>> getManagerInfoByDay(String loginName,String userId){
        String date = DateUtil.getCurrDate("yyyy-MM-dd");
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        ExecuteSql dealsql= new ExecuteSql("dataSource2");
        String sql = "select * from t_date_manager_info t where t.datetime1='"+date+"'" +
                " and  t.userName='"+loginName+"' and t.removed=0 order by t.dateTime2";

        try{
            ResultSet rs=dealsql.ExecuteDemandSql(sql);
            while(rs.next()){
                Map<String,String> map = new HashMap<String,String>();
                map.put("content",rs.getString("content"));
                map.put("username",rs.getString("username"));
                map.put("datetime1",rs.getString("datetime1"));
                map.put("id",rs.getLong("id")+"");
                map.put("topic",rs.getString("topic"));
                map.put("datetime2",times[Integer.parseInt(rs.getString("datetime2"))]);
                map.put("datetime3",times[Integer.parseInt(rs.getString("datetime3"))]);
                map.put("userid",rs.getString("userid"));
                map.put("assigner",rs.getString("assigner"));
                list.add(map);
            }
            rs.close();
            dealsql.close();
        }catch(Exception e){
        }
        return list;
    }
    //取得这周日程安排
    public static List<Map<String,String>> getManagerInfoByWeek(String loginName,String userId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = sdf.format(getTheDate(new Date(),1));
        String end = sdf.format(getTheDate(new Date(),7));
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        ExecuteSql dealsql= new ExecuteSql("dataSource2");
        String sql = "select * from (select * from t_date_manager_info t  where t.removed=0 and t.datetime1 between('"+ start
                + "') and ('"
                + end
                + "') and (t.username='"+loginName+"' )"
                + " ) o order by o.datetime1 asc,o.datetime2 asc,o.datetime3 asc";
        try {

            ResultSet rs = dealsql.ExecuteDemandSql(sql);
            while(rs.next()) {
                Map<String,String> map = new HashMap<String,String>();
                map.put("content",rs.getString("content"));
                map.put("username",rs.getString("username"));
                map.put("datetime1",rs.getString("datetime1"));
                map.put("id",rs.getLong("id")+"");
                map.put("topic",rs.getString("topic"));
                map.put("datetime2",times[Integer.parseInt(rs.getString("datetime2"))]);
                map.put("datetime3",times[Integer.parseInt(rs.getString("datetime3"))]);
                map.put("userid",rs.getString("userid"));
                map.put("assigner",rs.getString("assigner"));
                list.add(map);
            }
            dealsql.close();
        } catch (Exception e) {
        }
        return list;
    }
%>



<%
    Donull donull = new Donull();
    String type = donull.dealNull(request.getParameter("type"));
    String loginName = donull.dealNull((String)session.getAttribute("cs_login_name"));
    String userId = donull.dealNull((String)session.getAttribute("oldUserId"));
    //String userId = "1469";
    ActionWriter aw = new ActionWriter(response);
    List<List<Map<String,String>>> list = new ArrayList<List<Map<String,String>>>();
    list = getManagerInfo(loginName,userId);

    aw.writeJson(list);

%>
