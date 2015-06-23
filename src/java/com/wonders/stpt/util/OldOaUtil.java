package com.wonders.stpt.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.module.common.ExecuteSql;


public class OldOaUtil {
	public static List<Map<String,String>> getOldUserDeptId(String userId){
		String ext5 = "";
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<Map<String,String>> tmp = new ArrayList<Map<String,String>>();
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		ResultSet rs = null;
		String sql = "select u.ext5,o.orders,n.id,n.name from cs_user_organnode o,cs_user u ,cs_organ_node  n" +
				" where o.organ_node_id=n.id and o.security_user_id=u.id and u.id=" +userId+
				" and u.removed=0 and n.removed=0 ";
		try {
			rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", rs.getInt("id")+"");
				map.put("name", rs.getString("name"));
				tmp.add(map);
				ext5 = rs.getString("ext5");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(dealsql!=null){try {dealsql.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		final String mainDeptId = ext5;
		for(Map<String,String> m:tmp){
			Map<String,String> m2 = getParentNode(m.get("id"));
			if(m2.size()>0){
				getAllDept(m2.get("id"),m2.get("name"),list);
			}
		}
		
		Collections.sort(list, new Comparator<Map<String, String>>() {
			public int compare(Map<String, String> o1,
					Map<String, String> o2) {
				String id1 = o1.get("id");
				String id2 = o2.get("id");
				try {
					if(!StringUtil.isNull(mainDeptId)){
						if(id1.equals(mainDeptId)){
							return -1;
						}else{
							return 1;
						}
					}else{
						return (Integer.parseInt(id1) - Integer.parseInt(id2));
					}
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		});
		
		return list;
	}
	public static Map<String,String> getParentNode(String nodeId){
		Map<String,String> map = new HashMap<String,String>();
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		ResultSet rs = null;
		String sql = "select id,name from cs_organ_node nn," +
				" (select m.parent_node_id from cs_organ_node n , cs_organ_model m where n.id=m.id and n.removed=0 and m.removed=0" +
				" and n.id="+nodeId+"  and m.org_tree_id=1040) b where nn.id = b.parent_node_id and nn.removed=0";
		try {
			rs=dealsql.ExecuteDemandSql(sql);
			if(rs.next()){
				map.put("id", rs.getInt("id")+"");
				map.put("name", rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(dealsql!=null){try {dealsql.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}
		return map;
	}
	public static void getAllDept(String nodeId,String nodeName, List<Map<String,String>> retLst){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id",nodeId);
		map.put("name",nodeName);
		if(!retLst.contains(map)){
    		retLst.add(map); 
    	}
		Map<String,String> m2 = getParentNode(nodeId);
		if (m2.size()>0&&!"2100".equals(m2.get("id"))) {// 2100:集团公司
			getAllDept(m2.get("id"),m2.get("name"),retLst);
    	}
	}
	
	public static String getOldUserName(String userId){
		String name = "";
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		ResultSet rs = null;
		String sql = " select name from cs_user WHERE t.REMOVED = 0 and t.ID = '"+userId+"'";
		try {
			rs=dealsql.ExecuteDemandSql(sql);
			if(rs.next()){
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			}
			if(dealsql!=null){try {dealsql.close();} catch (SQLException e) {e.printStackTrace();}
			}
		}	
		return name;
	}
}
