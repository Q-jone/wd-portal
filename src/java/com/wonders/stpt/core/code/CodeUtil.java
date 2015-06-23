package com.wonders.stpt.core.code;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.util.StringUtil;

		
public class CodeUtil {
	public static List<String> findCodeInfoByCode(String codeType_code, String codeInfo_code){
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		String sql="select t.code, t.name,t.id,t.description from cf_code_info t where t.removed = 0 and  t.type_id =(select  a.id from cf_code_type a where a.removed =0 and a.code = '"+codeType_code+"') and t.code_info_id=(select  c.id from cf_code_info c where c.removed = 0 and c.code = '"+codeInfo_code+"' and c.type_id =(select  b.id from cf_code_type b where b.removed = 0 and b.code = '"+codeType_code+"'))  order by t.disp_order desc";
		List<String> lists = new ArrayList<String>();
		String code = "";
		String name = "";
		String description = "";
		String id = "";
		try{
			//System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				code = rs.getString("code");
				name = rs.getString("name");
				description = rs.getString("description");
				id = rs.getInt("id") + "";
				lists.add(name);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return lists;
	}
	
	public static List<String> findStatusByCode(String infoType_code, String codeInfo_id){
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		String sql="select t.sid, t.content from t_list_status t where t.state = 1 and  t.infotype='"+infoType_code+"' ";
		//if(codeInfo_id!=null&&codeInfo_id.length()>0){
			sql+=" and t.sid='"+codeInfo_id+"' ";
		//}
		sql+= " order by t.optorder";
		List<String> lists = new ArrayList<String>();
		String name = "";
		String id = "";
		try{
			//System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				name = rs.getString("content");
				id = rs.getInt("sid") + "";
				lists.add(id + ":" + name);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return lists;
	}
	
	public static void main(String args[]){

	}

}
