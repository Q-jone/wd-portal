package com.wonders.stpt.core.publicFun.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.organTree.entity.vo.DefaultUserVo;
import com.wonders.stpt.util.StringUtil;

public class PublicFunction {

	
	/** 
	* @Title: getQyDl 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return Map<String,Double>    返回类型 
	* @throws 
	* y1 1 5 9 10
	* y22 11 13
	* y33 4 7
	* y46 8 12
	* cf
	*/
	
	public static Map<String,Double> getQyDl(){
		Map<String,Double> map = new HashMap<String,Double>();
		map.put("y1", 0.0);
		map.put("y2", 0.0);
		map.put("y3", 0.0);
		map.put("y4", 0.0);
		map.put("cf", 0.0);
		ExecuteSql dealsql= new ExecuteSql("wonderPublic");
		String sql = "select line_name line,dl value from qianyin_dl t where t.flag=1";
		try{
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				String line = StringUtil.getNotNullValueString(rs.getString("line"));
				double value = rs.getDouble("value");
				if("1号线".equals(line) || "5号线".equals(line) || 
						"9号线".equals(line) || "10号线".equals(line) ){
					map.put("y1", map.get("y1")+value);
				}else if("2号线".equals(line) ||"11号线".equals(line) 
						||"13号线".equals(line) ){
					map.put("y2", map.get("y2")+value);
				}else if("3号线".equals(line) || "4号线".equals(line) 
						||"7号线".equals(line) ){
					map.put("y3", map.get("y3")+value);
					//System.out.println(value);
				}else if("6号线".equals(line) ||"8号线".equals(line) 
						||"12号线".equals(line) ){
					map.put("y4", map.get("y4")+value);
				}
			}
			dealsql.close();
		}catch(Exception e){
		}
		return map;
	}
	
	public static List<DefaultUserVo> findDeptLeadersByUserId(String userId){
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql="select t.leader_id,t.Leader_LoginName,t.Leader_Name,t.DEPTID,t.DEPT from v_user_deptleader t where t.id="+userId;
		List<DefaultUserVo> list = new ArrayList<DefaultUserVo>();
		try{
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				DefaultUserVo d = new DefaultUserVo();
				d.setUserId(rs.getInt("leader_id")+"");
				d.setLoginName(rs.getString("Leader_LoginName"));
				d.setUserName(rs.getString("Leader_Name"));
				d.setDeptId(rs.getInt("DEPTID")+"");
				d.setDeptName(rs.getString("DEPT"));
				list.add(d);
			}
			dealsql.close();
		}catch(Exception e){
		}
		return list;
	}
	
}
