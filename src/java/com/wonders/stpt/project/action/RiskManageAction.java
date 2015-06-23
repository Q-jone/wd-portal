package com.wonders.stpt.project.action;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.RiskManage;
import com.wonders.stpt.project.service.RiskManageService;
import com.wondersgroup.framework.security.bo.SecurityUser;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/30.
 */
@ParentPackage("struts-default")
@Namespace(value="/project/risk")
@Controller("riskManageAction")
@Scope("prototype")
public class RiskManageAction  implements SessionAware,ServletResponseAware {

    private RiskManage risk=new RiskManage();
    @Autowired
    private RiskManageService riskManageService;
    private PageResultSet<RiskManage> pageResultSet;
    private Map<String, Object> session;
    private Integer page=1;
    private Integer pageSize=10;
    private String riskManageId;
    private HttpServletResponse httpServletResponse;

    @org.apache.struts2.convention.annotation.Action(value = "report", results = {
            @Result(name = "success", location = "/project/risk/report.jsp")
    })
    public String report() throws Exception {

        if (risk == null) {
            risk = new RiskManage();
        }

        pageResultSet = riskManageService.getRiskManages(risk, 2014, 1, Integer.MAX_VALUE);
        return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "riskManages", results = {
            @Result(name = "success", location = "/project/risk/risk_list.jsp")
    })
    public String riskManages() throws Exception{
    	if (risk == null) {
            risk = new RiskManage();
        }

        pageResultSet = riskManageService.getRiskManages(risk, 2014, page, pageSize);
    	return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "riskManage", results = {
            @Result(name = "success", location = "/project/risk/risk_save.jsp")
    })
    public String riskManage(){
    	try {
    		if(StringUtils.isNotBlank(riskManageId))
    			risk=riskManageService.riskManage(riskManageId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("出错");
			e.printStackTrace();
		}
    	return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "delete", results = {
            @Result(name = "success", location = "riskManages.action",type="redirectAction",params={"page","%{page}"})
    })
    public String deletes(){
    	if(StringUtils.isNotBlank(riskManageId)){
    		riskManageService.deletes(riskManageId);
    	}
    	return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "index")
    public String index() throws Exception{
        String result = riskManageService.countDataToJson(Calendar.getInstance().get(Calendar.YEAR));
        httpServletResponse.getWriter().print(result);
        return Action.NONE;
    }

    
    @org.apache.struts2.convention.annotation.Action(value = "goSave", results = {
            @Result(name = "success", location = "/project/risk/risk_save.jsp")
    })
	public String goSave(){
		
		return Action.SUCCESS;
    }
	/**
	 * 保存安全风险管理记录
	 * @return
	 */
	@org.apache.struts2.convention.annotation.Action(value = "save", results = {
            @Result(name = "success", location = "riskManages.action",type="redirectAction")
    })
	public String save(){
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        RiskManage riskManage;
        	try {
        		if(StringUtils.isNotBlank(riskManageId)){//修改
        			riskManage=riskManageService.riskManage(riskManageId);
					risk.setRiskManageId(riskManageId);
					risk.setUpdater(loginName);
					risk.setUpdateTime(new Date());
					BeanUtils.copyProperties(risk, riskManage, new String[]{"createTime", "creator", "removed"});
        		}else{//添加
                	risk.setRiskManageId(null);
                	risk.setUpdater(loginName);
					risk.setCreator(loginName);
					riskManage=risk;
                }
        		riskManageService.save(riskManage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	        
		return Action.SUCCESS;
	}

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }

    public RiskManage getRisk() {
        return risk;
    }

    public void setRisk(RiskManage risk) {
        this.risk = risk;
    }

    public PageResultSet<RiskManage> getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet<RiskManage> pageResultSet) {
        this.pageResultSet = pageResultSet;
    }

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getRiskManageId() {
		return riskManageId;
	}

	public void setRiskManageId(String riskManageId) {
		this.riskManageId = riskManageId;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
    }
}
