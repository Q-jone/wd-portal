package com.wonders.stpt.doneConfig.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.doneConfig.service.ITDeptOrUserService;
@ParentPackage("struts-default")
@Namespace(value = "/doneConfig/dept")
@Controller("deptOrUserAction")
@Scope("prototype")
public class TDeptOrUserAction {
	@Autowired
	private ITDeptOrUserService deptOrUserService;
	private Logger logger=Logger.getLogger(TDeptOrUserAction.class);
	@Action(value="dept",results={@Result(name="dept",location="/index.jsp")})
	public String getDept(){
		logger.info("sdsd");
		deptOrUserService.getDept();
		return "dept";
	}
}
