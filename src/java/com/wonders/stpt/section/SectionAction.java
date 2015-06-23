package com.wonders.stpt.section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

public class SectionAction extends BaseAjaxAction{
	private SectionDaoImpl dao ;
	private AuditDaoImpl auditDao;
	public SectionDaoImpl getDao() {
		return dao;
	}
	public void setDao(SectionDaoImpl dao) {
		this.dao = dao;
	}

	public AuditDaoImpl getAuditDao() {
		return auditDao;
	}
	public void setAuditDao(AuditDaoImpl auditDao) {
		this.auditDao = auditDao;
	}
	
	public String getJrswDbx(){
		String jsonData = "";
		String deptId = this.servletRequest.getParameter("deptId");
	    String allDeptId = this.servletRequest.getParameter("allDeptId");
	    String tLoginName = StringUtil.dealNull((String)this.servletRequest.getSession().getAttribute("cs_login_name"));
	    if(deptId!=null&&allDeptId!=null){
	    	String[] allDeptIdSplit = allDeptId.split(",");
		    List<String> deptIds = new ArrayList<String>();//拿到所有的部门id
		    for(int i=0;i<allDeptIdSplit.length;i++){
		    	deptIds.add(allDeptIdSplit[i]);
		    }
			
			Map<String,String> mapDept = new HashMap<String,String>();
			Map<String,Integer> map = findTasksingByLoginName(tLoginName, deptIds, deptId);			
			//曾静 2010-8-6 start
			String countTimeOut = dao.countTimeOut(tLoginName);//统计超时流程的最大时间,没有则为0;
			//end
			if(countTimeOut==null){
				countTimeOut = "0";
			}
			Integer num = null;
			Integer num1 = null;
			Integer num2 = null;
			num = map.get(deptId);
			num1 = map.get(deptId+"急件");
			num2 = map.get(deptId+"超时");
			
			if (num!= null) {
				jsonData = "{\"dbx\":\""+num+"\"";
				if(num2 != null){
					jsonData += ",\"csx\":\""+num2+"\"";
					if(!"".equals(countTimeOut)&&!"null".equals(countTimeOut)&&countTimeOut!=null){
						jsonData += ",\"csTime\":\""+countTimeOut+"\"}";
					}
				}
				//end
			}
	    }
	    
		createJSonData(jsonData);
		return AJAX;
	}
	
	public Map<String,Integer> findTasksingByLoginName(String loginName,List<String> deptIds,String mainDeptId){
		if(dao.isNull(loginName)||deptIds == null || deptIds.isEmpty()) return null;
		
		Map<String,Integer> map = null;
		
		List<String[]> listTasks = dao.findTasksingByLoginName(loginName);
		//初始化

		map = new HashMap<String,Integer>();
		for(String strTemp : deptIds){
			map.put(strTemp, 0);
			map.put(strTemp+"急件", 0);//急件数量
			map.put(strTemp+"超时", 0);//超时数量
		}
		
		
		//没有代办项

		if(listTasks == null || listTasks.isEmpty()) return map;
		
		if(deptIds.size() == 1){
			map.put(deptIds.get(0), listTasks.size());
			//if()
			int mapValue = 0;
			//曾静 2010-08-05 start
			int mapValues = 0;//初始化超时的累计
			//end
			for(String[] listTasksArray:listTasks){
				if(!dao.isNull(listTasksArray[3])){//设置急件
					mapValue = map.get(deptIds.get(0)+"急件");
					mapValue ++;					
					map.put(deptIds.get(0)+"急件", mapValue);					
				}
				//曾静 2010-08-05 start
				if(!dao.isNull(listTasksArray[4])&&"3".equals(listTasksArray[4])&&!dao.isNull(listTasksArray[5])){//设置超时				
					mapValues = map.get(deptIds.get(0)+"超时");
					mapValues ++;//超时的累计

					map.put(deptIds.get(0)+"超时", mapValues);//超时的累计赋到map中

				}
				//end
			}
			return map;
		}
		//有代办项
		//查找登陆人所在的部门的等级

		Map<Long,Integer> deptLevelMap = dao.getDeptsLevel(deptIds);
		Set<Long> deptLevelMapKey = deptLevelMap.keySet();
		Integer deptLevelMapValue = null;
		if(!dao.isNull(mainDeptId)){
			for(Long l :deptLevelMapKey){
				for(String[] listTasksArray:listTasks)
					setMapTasks((mainDeptId.equals(l+"")),l,map,loginName,listTasksArray[2],listTasksArray[3],listTasksArray[4],listTasksArray[5]);
			}
		}else{
			//如果没有主部门

			for(Long l :deptLevelMapKey){
				deptLevelMapValue = deptLevelMap.get(l);
				if(deptLevelMapValue != null||deptLevelMapValue != 0){
					for(String[] listTasksArray:listTasks)
						setMapTasks((deptLevelMapValue == 1),l,map,loginName,listTasksArray[2],listTasksArray[3],listTasksArray[4],listTasksArray[5]);
				}
			}
		}
		return map;
	}
	
	private void setMapTasks(boolean flag,Long l,Map<String,Integer> map,String loginName,String helpUrl,String properties,String substatus,String overduetime){
		Integer mapValue = null;
		Integer mapValues = null;
		if(flag){
			if(dao.isNull(helpUrl)
					||helpUrl.indexOf("ST/"+loginName+":"+l+"<+>")>=0
					||helpUrl.indexOf("ST/"+loginName+":<+>")>=0){
				mapValue = map.get(l+"");
				mapValue ++;
				map.put(l+"", mapValue);
				
				if(!dao.isNull(properties)){//设置急件
					mapValue = map.get(l+"急件");
					mapValue ++;
					map.put(l+"急件", mapValue);
				}
				if(!dao.isNull(substatus)&&"3".equals(substatus)&&!dao.isNull(overduetime)){//设置超时
					mapValues = map.get(l+"超时");
					mapValues ++;
					map.put(l+"超时", mapValues);
				}
			}
		}else{
			if(dao.isNull(helpUrl)||helpUrl.indexOf("ST/"+loginName+":"+l+"<+>")>=0){
				mapValue = map.get(l+"");
				mapValue ++;
				map.put(l+"", mapValue);
				
				if(!dao.isNull(properties)){//设置急件
					mapValue = map.get(l+"急件");
					mapValue ++;
					map.put(l+"急件", mapValue);
				}
				
				if(!dao.isNull(substatus)&&"3".equals(substatus)&&!dao.isNull(overduetime)){//设置超时
					mapValues = map.get(l+"超时");
					mapValues ++;
					map.put(l+"超时", mapValues);
				}
			}
		}
	}
	
	public String getUrgeCount(){
		String jsonData = "";
		String deptId = this.servletRequest.getParameter("deptId");
	    String allDeptId = this.servletRequest.getParameter("allDeptId");
	    String tLoginName = StringUtil.dealNull((String)this.servletRequest.getSession().getAttribute("cs_login_name"));
	    if(deptId!=null&&allDeptId!=null){
	    	String[] allDeptIdSplit = allDeptId.split(",");
		    List<String> deptIds = new ArrayList<String>();//拿到所有的部门id
		    for(int i=0;i<allDeptIdSplit.length;i++){
		    	deptIds.add(allDeptIdSplit[i]);
		    }
		    boolean multiDept = false;
		    if(allDeptIdSplit!=null && allDeptIdSplit.length>1){
				multiDept = true;
			}
		    int num = dao.getUrgeCount(tLoginName, deptId, multiDept, deptIds);
		    jsonData = "{\"num\":\""+num+"\"}";
	    }
	    
	    
		createJSonData(jsonData);
		return AJAX;
	}
	
	public String countMessage(){
		String tLoginName = StringUtil.dealNull((String)this.servletRequest.getSession().getAttribute("cs_login_name"));
		long num = dao.countMessage(tLoginName);
		String jsonData = "";
	    jsonData = "{\"num\":\""+num+"\"}";
		createJSonData(jsonData);
		return AJAX;
	}
	
	public String countJbx(){
		String jsonData = "";
		String deptId = this.servletRequest.getParameter("deptId");
		String tLoginName = StringUtil.dealNull((String)this.servletRequest.getSession().getAttribute("cs_login_name"));
		if(deptId!=null){
			int num = dao.countJbx(tLoginName, deptId);
			jsonData = "{\"num\":\""+num+"\"}";
		}
		createJSonData(jsonData);
		return AJAX;
	}
	
	public String findIfActiveUser(){
		String tLoginName = StringUtil.dealNull((String)this.servletRequest.getSession().getAttribute("cs_login_name"));
		long num = auditDao.findIfActiveUser(tLoginName);
		String jsonData = "";
	    jsonData = "{\"num\":\""+num+"\"}";
		createJSonData(jsonData);
		return AJAX;
	}
	
	public String findDataFromCaAuditInfo(){
		String jsonData = "";
		String deptId = this.servletRequest.getParameter("deptId");
		String tLoginName = StringUtil.dealNull((String)this.servletRequest.getSession().getAttribute("cs_login_name"));
		if(deptId!=null){
			List<Object[]> list = auditDao.findDataFromCaAuditInfo(tLoginName, deptId);
			if(list!=null&&list.size()==1){
				if(list.get(0).length==6){
					jsonData = "{\"dbx\":\""+list.get(0)[0]+"\",\"csx\":\""+list.get(0)[1]+"\",\"csTime\":\""+list.get(0)[2]
					         +"\",\"cbx\":\""+list.get(0)[3]+"\",\"notice\":\""+list.get(0)[4]+"\",\"jbx\":\""+list.get(0)[5]+"\"}";
				}
			}
		}
		createJSonData(jsonData);
		return AJAX;
	}
}
