/** 
* @Title: Constants.java 
* @Package com.wonders.stpt.processDone.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com 
* @date 2013-7-29 下午02:27:39 
* @version V1.0 
*/
package com.wonders.stpt.processDone.util;

import java.util.HashMap;


public class Constants {
	public static final int PAGE_SIZE = 10;
	
	private static HashMap<String, String> hmParams = new HashMap<String, String>();
	static {
		init();
	}
	
	public static String getFieldNameByParamName(String str) {
		return hmParams.get(str);
	}
										
	private static void init() {
		hmParams.put("processname","pname");
		hmParams.put("starttimes","startTimes");
		hmParams.put("starttimee","startTimee");
		hmParams.put("username","applyUser");
		hmParams.put("deptname","applyDept");
		hmParams.put("endtimes","endTimes");	
		hmParams.put("endtimee","endTimee");	
	}
}
