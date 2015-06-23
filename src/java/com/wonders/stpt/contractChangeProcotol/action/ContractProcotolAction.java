package com.wonders.stpt.contractChangeProcotol.action;

import com.wonders.stpt.contractChangeProcotol.model.vo.ContractChangeProtocolVo;
import com.wonders.stpt.contractChangeProcotol.service.ContractProcotolService;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
@ParentPackage("struts-default")
@Namespace(value="/contractProcotol")
@Controller("contractProcotolAction")
@Scope("prototype")
public class ContractProcotolAction extends com.wonders.stpt.contractManage.action.AbstractParamAction implements ModelDriven<ContractChangeProtocolVo> {

    private static final long serialVersionUID = 1631323544896056619L;
    private ActionWriter aw = new ActionWriter(response);
    private ContractChangeProtocolVo vo = new ContractChangeProtocolVo();

    private ContractProcotolService contractProcotolService ;

    public ContractProcotolService getContractProcotolService() {
        return contractProcotolService;
    }
    @Autowired(required=false)
    public void setContractProcotolService(@Qualifier("contractProcotolService")ContractProcotolService contractProcotolService) {
        this.contractProcotolService = contractProcotolService;
    }

    @Action(value="list",results={
            @Result(name="success",location="/contractChangeProtocol/list.jsp")
    })
    public String findHtspOaByPage(){
        String  contract_name= this.request.getParameter("contract_name");
        String self_no = this.request.getParameter("self_no");
        String change_price_start = this.request.getParameter("change_price_start");
        String change_price_end = this.request.getParameter("change_price_end");
        String reg_person = this.request.getParameter("reg_person");
        String reg_time_start = this.request.getParameter("reg_time_start");
        String reg_time_end = this.request.getParameter("reg_time_end");
        String flag = this.request.getParameter("flag");
        String date_start = this.request.getParameter("date_start");
        String date_end = this.request.getParameter("date_end");
        String date_start1 = this.request.getParameter("date_start1");
        String date_end1 = this.request.getParameter("date_end1");


        this.request.setAttribute("contract_name", contract_name);
        this.request.setAttribute("self_no", self_no);
        this.request.setAttribute("change_price_start", change_price_start);
        this.request.setAttribute("change_price_end", change_price_end);
        this.request.setAttribute("reg_person", reg_person);
        this.request.setAttribute("reg_time_start", reg_time_start);
        this.request.setAttribute("reg_time_end", reg_time_end);
        this.request.setAttribute("flag", flag);
        this.request.setAttribute("date_start", date_start);
        this.request.setAttribute("date_end", date_end);
        this.request.setAttribute("date_start1", date_start1);
        this.request.setAttribute("date_end1", date_end1);

        int totalRows = contractProcotolService.countContractProcotolOa(contract_name, self_no,
                change_price_start ,change_price_end, reg_person, reg_time_start,reg_time_end,
                flag, date_start, date_end, date_start1, date_end1);

        PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);
        List<Object[]> list = contractProcotolService.findContractProcotolByPage(pageinfo.getBeginIndex(), vo.pageSize,
                contract_name, self_no,change_price_start, change_price_end,
                reg_person, reg_time_start,reg_time_end, flag, date_start, date_end, date_start1, date_end1);
        //System.out.println("list.size======"+list.size());
        PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
        result.setList(list);
        result.setPageInfo(pageinfo);
        this.request.setAttribute("result", result);
        return SUCCESS;
    }
    @Override
    public  ContractChangeProtocolVo getModel(){
        return vo;
    }
}
