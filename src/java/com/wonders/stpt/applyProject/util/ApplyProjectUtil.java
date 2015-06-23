/**
 * 
 */
package com.wonders.stpt.applyProject.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/** 
 * @ClassName: BidUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月8日 上午8:51:47 
 *  
 */

public class ApplyProjectUtil {
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
