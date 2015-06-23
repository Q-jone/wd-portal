package com.wonders.stpt.contractItem.action;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.contractItem.model.ContractItem;
import com.wonders.stpt.contractItem.service.ContractItemService;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by Administrator on 2014/6/16.
 */
@ParentPackage("struts-default")
@Namespace(value = "/contractItem")
@Controller("contractItemAction")
@Scope("prototype")
public class ContractItemAction implements ServletResponseAware,SessionAware{
	private PageResultSet pageResultSet;//页面上的相关信息
    private int page=1;//页码
    private int pageSize;//每页的记录数
    private Map<String, Object> session;
    private HttpServletResponse httpServletResponse;
    @Autowired
    private ContractItemService contractItemService;
    private ActionWriter actionWriter;
    private ContractItem contractItem = new ContractItem();

    @org.apache.struts2.convention.annotation.Action(value = "list", results = {
            @Result(name = "success", location = "/contractItem/contractItem_list.jsp")
    })
    public String list() throws Exception{
        System.out.println("进来了");
        if(contractItem==null){
            contractItem = new ContractItem();
        }

        pageResultSet =  contractItemService.getContractItem(contractItem, page, pageSize);
    	return Action.SUCCESS;
    }
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.httpServletResponse = arg0;
        httpServletResponse.setContentType("text/html;charset=utf-8");
        actionWriter =new ActionWriter(httpServletResponse);
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

    public Map<String, Object> getSession() {
        return session;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public ContractItemService getContractItemService() {
        return contractItemService;
    }

    public void setContractItemService(ContractItemService contractItemService) {
        this.contractItemService = contractItemService;
    }

    public ContractItem getContractItem() {
        return contractItem;
    }

    public void setContractItem(ContractItem contractItem) {
        this.contractItem = contractItem;
    }
}
