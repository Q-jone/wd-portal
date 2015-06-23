<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*"%>
<%@ page import="com.wonders.stpt.util.Donull"%>
<%@ page import="com.wonders.stpt.util.DateUtil"%>
<%@ page import="com.wonders.module.common.ExecuteSql"%>
<%@ page import="com.wonders.stpt.util.ActionWriter"%>

<%!
	public List<String> findKyglListByType(String type,String size){
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		try {
			ExecuteSql dealsql= new ExecuteSql("dataSource2");
			String sql ="select title from t_ky_message  where type =" + type.trim() + " and (removed != '1' or removed is null) order by optdate desc ";		
			sql = "select title from ("+sql+") where rownum<="+size;
			rs = dealsql.ExecuteDemandSql(sql);
			String title = "";
			while(rs.next()){
				title = rs.getString("title");
				list.add(title);	
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
	String type = donull.dealNull(request.getParameter("type"));
	String size = donull.dealNull(request.getParameter("size"));
	ActionWriter aw = new ActionWriter(response);
	List<String> list = new ArrayList<String>();
	list = findKyglListByType(type,size);
	aw.writeJson(list);
	
%>