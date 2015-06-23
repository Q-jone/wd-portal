/**
 * 
 */
package com.wonders.stpt.bid.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/** 
 * @ClassName: BidUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月8日 上午8:51:47 
 *  
 */

@Component("bidUtil")
public class BidUtil {
	private static BidDbUtil dbUtil;

	public static BidDbUtil getDbUtil() {
		return dbUtil;
	}

	@Autowired(required=false)
	public void setDbUtil(@Qualifier("bidDbUtil")BidDbUtil dbUtil) {
		BidUtil.dbUtil = dbUtil;
	}
	
	//0 项目公司 1 线路 2 子目 3类别 4专业
	public static List<Map<String,Object>> getBidType(String type){
		String sql = "select id as typeId,type_name as typeName from"
				+ " t_bid_type t where removed=0 and t.flag = ?";
		List<Map<String,Object>> list = dbUtil.getJdbcTemplate().
				queryForList(sql,new Object[]{type});
		return list;
	}
	
	public static int addBidType(String typeName,String flag){
		String sql = "insert into t_bid_type t (id,type_name,flag,removed)"
				+ " values(sys_guid(),?,?,'0')";
		int result = dbUtil.getJdbcTemplate().update(sql, new Object[]{typeName,flag});
		return result;
	}
	
	public static Map<String,Object> generateFilterMap(Object obj) {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);
					if(res!=null && res.toString().length() > 0){
						map.put(varName, res);
					}

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
			}
		return map;
	}
	
	public static int getPage(String s){
		int page = 1;
		try{
			page = Integer.parseInt(s);
		}catch(Exception e){
			page = 1;
		}
		return page;
	}
	
	public static int getPageSize(String s){
		int pageSize = 10;
		try{
			pageSize = Integer.parseInt(s);
		}catch(Exception e){
			pageSize = 10;
		}
		return pageSize;
	}
}
