package com.wonders.stpt.project.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkEvent;
import com.wonders.stpt.project.service.IWorkEventService;
import com.wonders.stpt.util.ActionWriter;
import com.wondersgroup.framework.security.bo.SecurityUser;
@ParentPackage("struts-default")
@Namespace("/project/event")
@Component("workEventAction")
@Scope("prototype")
public final class WorkEventAction implements ServletResponseAware,
		SessionAware {
	
	private ActionWriter am;
    private Map<String, Object> session;
    private HttpServletResponse httpServletResponse;
    @Autowired
    private IWorkEventService workEventService;
    private final Logger logger = Logger.getLogger(WorkEvent.class);
    private PageResultSet pageResultSet;//页面上的相关信息
    private int page=1;//页码
    private int pageSize=10;//每页的记录数
    private WorkEvent event=new WorkEvent();
    private String id;
    
    /**
     * 报表功能
     * @return
     */
    @Action(value="report",results={@Result(name="success",location="/project/event/report.jsp")})
    public String report(){
    	if(event==null)
    		event=new WorkEvent();
    	System.out.println();
    	System.out.println("".equals(event.getMessageSource()));
    	if(StringUtils.isBlank(event.getClassification())){
    		event.setClassification(null);
    	}
    	if(StringUtils.isBlank(event.getMessageSource())){
    		event.setMessageSource(null);
    	}
    	try {
			pageResultSet = workEventService.find(event, 1, Integer.MAX_VALUE);
		} catch (Exception e) {
			logger.error("报表显示出错");
			e.printStackTrace();
		}
		return com.opensymphony.xwork2.Action.SUCCESS;
    }
    
    
    /**
     * 分页显示数据
     * @return
     */
   @Action(value="events",results={@Result(name="success",location="/project/event/work_event_list.jsp")})
    public String events(){
    	if(event==null)
    		event=new WorkEvent();
    	try {
    		logger.info("pageSize:"+pageSize);
			pageResultSet=workEventService.find(event, page, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("分页查询数据出错:"+event);
			e.printStackTrace();
		}
    	
    	return com.opensymphony.xwork2.Action.SUCCESS;
    }
    
   /**
    * 根据主键查询记录
    * @return
    */
    @Action(value="event",results={@Result(name="success",location="/project/event/work_event_save.jsp")})
   public String event(){
	   if(StringUtils.isNotBlank(id)){//编辑传过来的id参数有值
		   try {
			event=workEventService.load(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("根据主键查询记录出错");
			e.printStackTrace();
		}
	   }
	   return com.opensymphony.xwork2.Action.SUCCESS;
   }
   
    /**
     * 保存记录功能
     * @return
     */
    @Action(value="save",results={@Result(name="success",location="/project/event/events.action",type="redirectAction")})
    public String save(){
    	String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        WorkEvent workEvent;
        try {
			if (StringUtils.isNotBlank(id)) {//id有值为修改
				workEvent = workEventService.load(id);
				event.setId(id);
				event.setUpdateTime(new Date());
				if (StringUtils.isNotBlank(loginName)) {
					event.setUpdator(loginName);
				} else {
					event.setUpdator(null);
				}
				BeanUtils.copyProperties(event, workEvent, new String[] {
						"createTime", "creator", "removed" });
			}else{
				event.setId(null);
				if (StringUtils.isNotBlank(loginName)) {
					event.setUpdator(loginName);
					event.setCreator(loginName);
				} else {
					event.setUpdator(null);
					event.setCreator(null);
				}
				workEvent=event;
			}
			workEventService.save(workEvent);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return com.opensymphony.xwork2.Action.SUCCESS;
    }
   /**
    * 新增跳转页面
    * @return
    */
    @Action(value="goAdd",results={@Result(name="success",location="/project/event/work_event_save.jsp")})
    public String goAdd(){
    	logger.info("跳转页面");
    	return com.opensymphony.xwork2.Action.SUCCESS;
    }
    
    /**
     * 逻辑删除
     * @return
     */
    @Action(value="deletes",results={@Result(name="success",location="/project/event/events.action",type="redirectAction",params={"page","%{page}"})})
    public String deletes(){
    	
    	try {
			if (StringUtils.isNotBlank(id)) {//删除记录主键存在
				workEventService.deletes(id);
			}
		} catch (Exception e) {
			logger.error("逻辑删除错处");
		}
		return com.opensymphony.xwork2.Action.SUCCESS;
    }
   
    /**
     * 在信息管理页面显示信息
     * @return
     * @throws Exception
     */
    @Action(value="index")
    public String index() throws Exception{
    	String result=workEventService.countDataToJson(Calendar.getInstance().get(Calendar.YEAR));
    	httpServletResponse.getWriter().print(result);
    	return com.opensymphony.xwork2.Action.NONE;
    }
    
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		this.httpServletResponse=httpServletResponse;
		httpServletResponse.setContentType("text/html;charset=utf-8");
        am =new ActionWriter(httpServletResponse);
	}

	public PageResultSet getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet pageResultSet) {
		this.pageResultSet = pageResultSet;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public WorkEvent getEvent() {
		return event;
	}
	public void setEvent(WorkEvent event) {
		this.event = event;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
