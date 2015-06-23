package com.wonders.stpt.innerWork.service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wonders.stpt.jeecms.ShowJeecmsInfo;

public class CaRestAPIService {
	private static Logger logger=LoggerFactory.getLogger(CaRestAPIService.class);
	private static HashMap<String,String> cookies=new HashMap<String,String>();
	private static void init() {
		
	}
	
	static {
		init();
	}		

	
	public static String getDataFromCa(HttpServletRequest request,HttpServletResponse response,String method,String paramsXml) {
		
		ShowJeecmsInfo showJeecmsInfo=null;
		try {
			
			showJeecmsInfo=new ShowJeecmsInfo(request,response);
		}catch (Exception e) {
			logger.error("Exception Throwable", e);
		}

		return showJeecmsInfo.getInfoList(method,paramsXml).toString();
	
	}
	
	public static Map<String,Object> getCurrentLoginInfoFromCa(HttpServletRequest request,HttpServletResponse response){
		String[] cookieNames={"token","deptId","deptName","userName","loginName"};
		cookies=getCookieValue(request,Arrays.asList(cookieNames));
		//保存页面需要的数据
		Map<String,Object> returnData=new HashMap<String,Object>();
		returnData.put("cookies",cookies);
		
		String params="{'id':'"+cookies.get("deptId")+"'}";
		

		//get dept leaders
		String deptLeaderOptions= getDataFromCa(request,response,"getDeptLeaders",params);
		JSONArray  jsonArryDeptLeader=JSONArray.fromObject(deptLeaderOptions);
		//部门领导
        returnData.put("deptLeaderOptions", jsonArryDeptLeader);
		
		
		String deptUserOptions= getDataFromCa(request,response,"getDeptUsers",params);
		JSONArray  jsonArryDeptUser=JSONArray.fromObject(deptUserOptions);
		returnData.put("deptUserOptions", jsonArryDeptUser);
		return returnData;
	}
	
	


	private static HashMap<String,String> getCookieValue(HttpServletRequest request,List<String> cookieName){
		Cookie[] cookies = request.getCookies();
		HashMap<String,String> hmCookies=new HashMap<String,String>();
		if(cookies !=null){
			int j=0;
			for(int i=0;i<cookies.length;i++){
				if(j>=cookieName.size()){
					break;
				}
				Cookie cookie = cookies[i];				
				if(cookieName.contains(cookie.getName())){
					j++;
					try{
						hmCookies.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error("Exception Throwable", e);
					}
					
					
				}						
			}
		}
		
		return hmCookies;
	}
	
}


