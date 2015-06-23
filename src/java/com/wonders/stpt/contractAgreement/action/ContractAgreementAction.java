package com.wonders.stpt.contractAgreement.action;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.contractAgreement.model.ContractAgreement;
import com.wonders.stpt.contractAgreement.service.ContractAgreementService;
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
 * Created by 01052621 on 2015/4/3.
 */
@ParentPackage("struts-default")
@Namespace(value = "/contractAgreement")
@Controller("contractAgreementAction")
@Scope("prototype")
public class ContractAgreementAction implements ServletResponseAware,SessionAware {
    private PageResultSet pageResultSet;//页面上的相关信息
    private int page=1;//页码
    private int pageSize;//每页的记录数
    private Map<String, Object> session;
    private HttpServletResponse httpServletResponse;
    private ActionWriter actionWriter;
    @Autowired
    private ContractAgreementService contractAgreementService;
    private ContractAgreement contractAgreement = new ContractAgreement();

    @org.apache.struts2.convention.annotation.Action(value = "list", results = {
            @Result(name = "success", location = "/contractAgreement/contractAgreement_list.jsp")
    })
    public String list() throws Exception{
        System.out.println("进来了");
        if(contractAgreement==null){
            contractAgreement = new ContractAgreement();
        }

        pageResultSet =  contractAgreementService.getContractAgreement(contractAgreement, page, pageSize);
        return Action.SUCCESS;
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public ActionWriter getActionWriter() {
        return actionWriter;
    }

    public void setActionWriter(ActionWriter actionWriter) {
        this.actionWriter = actionWriter;
    }

    public ContractAgreementService getContractAgreementService() {
        return contractAgreementService;
    }

    public void setContractAgreementService(ContractAgreementService contractAgreementService) {
        this.contractAgreementService = contractAgreementService;
    }

    public ContractAgreement getContractAgreement() {
        return contractAgreement;
    }

    public void setContractAgreement(ContractAgreement contractAgreement) {
        this.contractAgreement = contractAgreement;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
        actionWriter =new ActionWriter(httpServletResponse);
    }
}
