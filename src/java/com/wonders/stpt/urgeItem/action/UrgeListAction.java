/**
 * 
 */
package com.wonders.stpt.urgeItem.action;

import java.util.ArrayList;
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
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.page.service.PageService;
import com.wonders.stpt.urgeItem.model.vo.UrgeItemVo;
import com.wonders.stpt.urgeItem.util.UrgeItemFunc;
import com.wonders.stpt.userMsg.action.AbstractParamAction;

/** 
 * @ClassName: UrgeItemAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午02:40:00 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/urgeItem")
@Controller("urgeListAction")
@Scope("prototype")
public class UrgeListAction extends AbstractParamAction implements ModelDriven<UrgeItemVo>{

	private static final long serialVersionUID = 1L;
	
	private UrgeItemVo vo = new UrgeItemVo();
	
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
	
	
	@Action(value="urgeItemList",results={
			@Result(name="success",location="/urgeItem/urgeItemList.jsp")
			})
	public String todoItemList(){
//		String sql = UrgeItemFunc.generateSql(
//				(String)this.getServletRequest().getSession().getAttribute("oldUserId"),
//				(String)this.getServletRequest().getSession().getAttribute("cs_login_name"),
//				(String)this.getServletRequest().getSession().getAttribute("userName"),
//				(String)this.getServletRequest().getSession().getAttribute("oldDeptId"),
//				vo.pageSize+"");
		String sql = UrgeItemFunc.generateSql(
				"11772",
				"G01006000513",
				"忻然",
				"2111",
				vo.pageSize+"");
				
		sql = UrgeItemFunc.generateSql(sql,vo);
		/* 记录总数 */
		int totalRows = (int) this.pageService.countBySql(sql);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		/**summary
		memo
		priorities
		priorities_show
		pname
		pincident
		processname
		incident
		steplabel
		overduetime
		endtime
		taskid
		assignedtouser
		task_type
		连接字符串
		initiator
		apply_username
		taskuser_name
		UserName
		deptname*/
		List<String[]> list = this.pageService.findPaginationInfo(sql, pageinfo.getBeginIndex(), vo.pageSize);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		for(String[] s : list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("summary", s[0]);map.put("memo", s[1]);map.put("priorities", s[2]);
			map.put("priorities_show", s[3]);map.put("pname", s[4]);map.put("pincident", s[5]);
			map.put("processname", s[6]);map.put("incident", s[7]);map.put("steplabel", s[8]);
			map.put("overduetime", s[9]);map.put("endtime", s[10]);map.put("taskid", s[11]);
			map.put("assignedtouser", s[12]);map.put("task_type", s[13]);map.put("连接字符串", s[14]);
			map.put("initiator", s[15]);map.put("apply_username", s[16]);map.put("taskuser_name", s[17]);
			map.put("UserName", s[18]);map.put("deptname", s[19]);map.put("delayDate", s[20]);
			result.add(map);
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
	public UrgeItemVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
}
