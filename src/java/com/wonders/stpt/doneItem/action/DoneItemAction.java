/**
 * 
 */
package com.wonders.stpt.doneItem.action;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.doneItem.model.vo.DoneItemVo;
import com.wonders.stpt.doneItem.util.DoneItemFunc;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.page.service.PageService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: TodoItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/done")
@Controller("doneItemAction")
@Scope("prototype")
public class DoneItemAction extends AbstractParamAction implements ModelDriven<DoneItemVo>{

	private static final long serialVersionUID = 1L;

	private DoneItemVo vo = new DoneItemVo();
	
	private PageService pageService;
	
	public PageService getPageService() {
		return pageService;
	}

	@Autowired(required = false)
	public void setPageService(@Qualifier(value = "pageService")PageService pageService) {
		this.pageService = pageService;
	}


	private PageResultSet<Map<String,String>> pageResultSet;

	public PageResultSet<Map<String, String>> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<Map<String, String>> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	
	@Action(value="doneItemList",results={
			@Result(name="success",location="/doneItem/doneItemList.jsp")
			})
	public String doneItemList(){
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("loginName"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		List<String> loginArr = new ArrayList<String>();
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
			loginArr.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		List filter = new ArrayList();
		
		String sql = DoneItemFunc.generateSql(
				(String)this.getServletRequest().getSession().getAttribute("oldUserId"),
				loginNames,
				(String)this.getServletRequest().getSession().getAttribute("cs_login_name"),
				(String)this.getServletRequest().getSession().getAttribute("userName"),
				(String)this.getServletRequest().getSession().getAttribute("oldDeptId"),
				vo.pageSize+"",loginArr,filter);
//		String sql = DoneItemFunc.generateSql(
//				"1062",
//				"G001000001612549",
//				"G00100000161",
//				"李名敏",
//				"2116",
//				vo.pageSize+"");
				
		/* 记录总数 */
	
		sql = DoneItemFunc.generateSql(sql,vo,filter);
		//System.out.println(sql);
		int totalRows = (int) this.pageService.countBySql(sql,filter);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		
		List<String[]> list = this.pageService.findPageInfo(sql, pageinfo.getBeginIndex(), vo.pageSize,filter);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(String[] s : list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("processname", s[0]);map.put("memo", s[1]);map.put("priorities", s[2]);
			map.put("incident", s[3]);map.put("summary", s[4]);map.put("initiator", s[5]);
			map.put("starttime", s[6]);map.put("endtime", s[7]);map.put("status", s[8]);
			map.put("pstatus", s[9]);map.put("username", s[10]);map.put("userendtime", s[11]);
			map.put("groupid", s[12]);map.put("deptname", s[13]);map.put("cname", s[14]);
			map.put("cincident", s[15]);
			result.add(map);
			//System.out.println(s[8]);
		}
		this.pageResultSet = new PageResultSet<Map<String, String>>();
		pageResultSet.setList(result);
		pageResultSet.setPageInfo(pageinfo);
		return "success";
	}

	
	
	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public DoneItemVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	
	
	@Action(value="doneItemListThisYear",results={
			@Result(name="success",location="/doneItem/doneItemList.jsp")
			})
	public String doneItemListThisYear() throws ParseException{
		long days = 0L;
		try{
			days = Long.parseLong(vo.getDays());
		}catch(Exception e){
			
		}
		
		String loginName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("loginName"));
		String ologinName = "ST/"+StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("cs_login_name"));
		String ouserId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldUserId"));
		String odeptId = StringUtil.getNotNullValueString(this.servletRequest.getSession().getAttribute("oldDeptId"));
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.servletRequest.getSession().getAttribute("deptUsers");
		List<String> loginArr = new ArrayList<String>();
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
			loginArr.add(StringUtil.getNotNullValueString(entry.getKey()));
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		List filter = new ArrayList();
		
		String sql = null;
		if(days==0){
			sql = DoneItemFunc.generateSql(
					(String)this.getServletRequest().getSession().getAttribute("oldUserId"),
					loginNames,
					(String)this.getServletRequest().getSession().getAttribute("cs_login_name"),
					(String)this.getServletRequest().getSession().getAttribute("userName"),
					(String)this.getServletRequest().getSession().getAttribute("oldDeptId"),
					vo.pageSize+"",loginArr,filter);
		}else{
			sql = DoneItemFunc.generateSqlThisYear(
				(String)this.getServletRequest().getSession().getAttribute("oldUserId"),
				loginNames,
				(String)this.getServletRequest().getSession().getAttribute("cs_login_name"),
				(String)this.getServletRequest().getSession().getAttribute("userName"),
				(String)this.getServletRequest().getSession().getAttribute("oldDeptId"),
				vo.pageSize+"",loginArr,filter,days);
		}
//		String sql = DoneItemFunc.generateSql(
//				"1062",
//				"G001000001612549",
//				"G00100000161",
//				"李名敏",
//				"2116",
//				vo.pageSize+"");
				
		/* 记录总数 */
	
		sql = DoneItemFunc.generateSql(sql,vo,filter);
		//System.out.println(sql);
		int totalRows = (int) this.pageService.countBySql(sql,filter);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		
		List<String[]> list = this.pageService.findPageInfo(sql, pageinfo.getBeginIndex(), vo.pageSize,filter);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(String[] s : list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("processname", s[0]);map.put("memo", s[1]);map.put("priorities", s[2]);
			map.put("incident", s[3]);map.put("summary", s[4]);map.put("initiator", s[5]);
			map.put("starttime", s[6]);map.put("endtime", s[7]);map.put("status", s[8]);
			map.put("pstatus", s[9]);map.put("username", s[10]);map.put("userendtime", s[11]);
			map.put("groupid", s[12]);map.put("deptname", s[13]);map.put("cname", s[14]);
			map.put("cincident", s[15]);
			result.add(map);
			//System.out.println(s[8]);
		}
		this.pageResultSet = new PageResultSet<Map<String, String>>();
		pageResultSet.setList(result);
		pageResultSet.setPageInfo(pageinfo);
		return "success";
	}
}
