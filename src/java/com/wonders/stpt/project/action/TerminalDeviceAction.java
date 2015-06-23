package com.wonders.stpt.project.action;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.TerminalDevice;
import com.wonders.stpt.project.service.TerminalDeviceService;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by Administrator on 2014/6/27.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/terminal")
@Controller("terminalDeviceAction")
@Scope("prototype")
public class TerminalDeviceAction {
    private TerminalDevice terminalDevice=new TerminalDevice();
    private Map<String, Object> session;
    @Autowired
    private TerminalDeviceService terminalDeviceService;
    private Integer page;
    private PageResultSet<TerminalDevice> pageResultSet;


    @org.apache.struts2.convention.annotation.Action(value = "report", results = {
            @Result(name = "success", location = "/project/terminal/report.jsp")
    })
    public String report() throws Exception {

        if (terminalDevice == null) {
            terminalDevice = new TerminalDevice();
        }

        pageResultSet = terminalDeviceService.getTerminalDevices(terminalDevice, 2014, 1, Integer.MAX_VALUE);
        return Action.SUCCESS;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public PageResultSet<TerminalDevice> getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet<TerminalDevice> pageResultSet) {
        this.pageResultSet = pageResultSet;
    }

    public TerminalDevice getTerminalDevice() {
        return terminalDevice;
    }

    public void setTerminalDevice(TerminalDevice terminalDevice) {
        this.terminalDevice = terminalDevice;
    }
}
