/**
 * 
 */
package com.wonders.stpt.processStop.action;

import java.util.List;
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
import com.wonders.stpt.processStop.model.bo.ProcessStop;
import com.wonders.stpt.processStop.model.vo.ProcessStopVo;
import com.wonders.stpt.processStop.service.ProcessStopService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: ProcessStopAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:37:12 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/processStop")
@Controller("processStopAction")
@Scope("prototype")
public class ProcessStopAction extends AbstractParamAction implements ModelDriven<ProcessStopVo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1631323544896053619L;
	private ActionWriter aw = new ActionWriter(response);
	private ProcessStopVo vo = new ProcessStopVo();
	private ProcessStopService service;
	public ProcessStopService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("processStopService")ProcessStopService service) {
		this.service = service;
	}


	@Action(value="list",results={
			@Result(name="success",location="/processStop/list.jsp")
			})
	public String findPage(){
		String table = this.request.getParameter("table");
		if(table == null){
			table = "t_doc_receive";
		}
		String title = this.request.getParameter("title");
		String status = this.request.getParameter("status");
		String starttime = this.request.getParameter("starttime");
		String endtime = this.request.getParameter("endtime");
		String type = this.request.getParameter("type");
		if(type == null){
			type = "0";
		}
		
		this.request.setAttribute("table", table);
		this.request.setAttribute("title", title);
		this.request.setAttribute("status", status);
		this.request.setAttribute("starttime", starttime);
		this.request.setAttribute("endtime", endtime);
		
		this.request.setAttribute("type", type);
		
		int totalRows = this.service.count(table,
				title,status,starttime,endtime);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<Object[]> list = this.service.findByPage(pageinfo.getBeginIndex(), vo.pageSize,
				table,title,status,starttime,endtime);
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		return SUCCESS;
	}
	
	
	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public ProcessStopVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	@Action(value="addReason")
	public String saveReason(){
		String reason = StringUtil.getNotNullTime(this.request.getParameter("reason"));
		String attach = StringUtil.getNotNullTime(this.request.getParameter("attach"));
		String process = StringUtil.getNotNullTime(this.request.getParameter("process"));
		String incident = StringUtil.getNotNullTime(this.request.getParameter("incident"));
		String operator = StringUtil.getNotNullTime(this.request.getParameter("operator"));
		String mainId = StringUtil.getNotNullTime(this.request.getParameter("mainId"));
		boolean flag = this.service.save(reason, attach, operator, mainId, process, incident);
		if(flag){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		return null;
	}

	@Action(value="getReason")
	public String getReason(){
		String mainId = StringUtil.getNotNullTime(this.request.getParameter("mainId"));
		List<ProcessStop> list = this.service.find(mainId);
		aw.writeJson(list);
		return null;
	}
}
