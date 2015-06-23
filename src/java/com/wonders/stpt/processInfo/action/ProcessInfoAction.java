package com.wonders.stpt.processInfo.action;

import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.processInfo.model.TodoItemVo;
import com.wonders.stpt.processInfo.service.ProcessInfoService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProcessInfoAction extends BaseAjaxAction
{
  private ProcessInfoService processInfoService;
  private TodoItemVo vo = new TodoItemVo();

  public TodoItemVo getModel()
  {
    return this.vo;
  }

  public void setProcessInfoService(ProcessInfoService processInfoService) {
    this.processInfoService = processInfoService;
  }

  public String findDocSendByPage() {
    String myPage = this.servletRequest.getParameter("myPage");
    this.servletRequest.setAttribute("myPage", myPage);
    String oldUserId = (String)this.servletRequest.getSession().getAttribute("oldUserId");

    String oldLoginName = this.processInfoService.findLoginNameById(oldUserId);
    String newLoginName = (String)this.servletRequest.getSession().getAttribute("loginName");

    String doc_title = this.servletRequest.getParameter("doc_title");
    String send_id = this.servletRequest.getParameter("send_id");
    String doc_count = this.servletRequest.getParameter("doc_count");
    String send_date_start = this.servletRequest.getParameter("send_date_start");
    String send_date_end = this.servletRequest.getParameter("send_date_end");
    String content = this.servletRequest.getParameter("content");
    String pstatus = this.servletRequest.getParameter("pstatus");
    String name = this.servletRequest.getParameter("name");
    String operator = this.servletRequest.getParameter("operator");
    String msgState = this.servletRequest.getParameter("msgState");
    this.servletRequest.setAttribute("doc_title", doc_title);
    this.servletRequest.setAttribute("send_id", send_id);
    this.servletRequest.setAttribute("doc_count", doc_count);
    this.servletRequest.setAttribute("send_date_start", send_date_start);
    this.servletRequest.setAttribute("send_date_end", send_date_end);
    this.servletRequest.setAttribute("content", content);
    this.servletRequest.setAttribute("pstatus", pstatus);
    this.servletRequest.setAttribute("name", name);
    this.servletRequest.setAttribute("operator", operator);
    this.servletRequest.setAttribute("msgState", msgState);

    int totalRows = this.processInfoService.countDocSend(doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator, msgState, oldLoginName, newLoginName, myPage);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findDocSendByPage(pageinfo.getBeginIndex(), this.vo.pageSize, doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator, msgState, oldLoginName, newLoginName, myPage);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    return "success";
  }

  public String findDocReceiveByPage() {
    String myPage = this.servletRequest.getParameter("myPage");
    this.servletRequest.setAttribute("myPage", myPage);
    String oldUserId = (String)this.servletRequest.getSession().getAttribute("oldUserId");

    String oldLoginName = this.processInfoService.findLoginNameById(oldUserId);
    String newLoginName = (String)this.servletRequest.getSession().getAttribute("loginName");

    String title = this.servletRequest.getParameter("title");
    String swid = this.servletRequest.getParameter("swid");
    String num = this.servletRequest.getParameter("num");
    String swunit = this.servletRequest.getParameter("swunit");
    String filezh = this.servletRequest.getParameter("filezh");
    String pstatus = this.servletRequest.getParameter("pstatus");
    String swdate_start = this.servletRequest.getParameter("swdate_start");
    String swdate_end = this.servletRequest.getParameter("swdate_end");

    this.servletRequest.setAttribute("title", title);
    this.servletRequest.setAttribute("swid", swid);
    this.servletRequest.setAttribute("num", num);
    this.servletRequest.setAttribute("swunit", swunit);
    this.servletRequest.setAttribute("filezh", filezh);
    this.servletRequest.setAttribute("pstatus", pstatus);
    this.servletRequest.setAttribute("swdate_start", swdate_start);
    this.servletRequest.setAttribute("swdate_end", swdate_end);

    int totalRows = this.processInfoService.countDocReceive(title, 
      swid, num, swunit, filezh, pstatus, swdate_start, swdate_end, oldLoginName, newLoginName, myPage);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findDocReceiveByPage(pageinfo.getBeginIndex(), this.vo.pageSize, title, 
      swid, num, swunit, filezh, pstatus, swdate_start, swdate_end, oldLoginName, newLoginName, myPage);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    return "success";
  }

  public String findDocDirectiveByPage() {
    String myPage = this.servletRequest.getParameter("myPage");
    this.servletRequest.setAttribute("myPage", myPage);

    String title = this.servletRequest.getParameter("title");
    String deptid = this.servletRequest.getParameter("deptid");
    String zleader = this.servletRequest.getParameter("zleader");
    String submitdate_start = this.servletRequest.getParameter("submitdate_start");
    String submitdate_end = this.servletRequest.getParameter("submitdate_end");
    String submitdept = this.servletRequest.getParameter("submitdept");
    String pstatus = this.servletRequest.getParameter("pstatus");

    this.servletRequest.setAttribute("title", title);
    this.servletRequest.setAttribute("deptid", deptid);
    this.servletRequest.setAttribute("zleader", zleader);
    this.servletRequest.setAttribute("submitdate_start", submitdate_start);
    this.servletRequest.setAttribute("submitdate_end", submitdate_end);
    this.servletRequest.setAttribute("submitdept", submitdept);
    this.servletRequest.setAttribute("pstatus", pstatus);

    int totalRows = this.processInfoService.countDocDirective(title, deptid, zleader, submitdate_start, 
      submitdate_end, submitdept, pstatus);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findDocDirectiveByPage(pageinfo.getBeginIndex(), this.vo.pageSize, 
      title, deptid, zleader, submitdate_start, submitdate_end, submitdept, pstatus);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    List listZleader = this.processInfoService.findZleader();
    this.servletRequest.setAttribute("listZleader", listZleader);
    return "success";
  }

  public String findHtspOaByPage() {
    String plan_num = this.servletRequest.getParameter("plan_num");
    String contract_num = this.servletRequest.getParameter("contract_num");
    String self_num = this.servletRequest.getParameter("self_num");
    String contract_name = this.servletRequest.getParameter("contract_name");
    String project_num = this.servletRequest.getParameter("project_num");
    String sign_cop = this.servletRequest.getParameter("sign_cop");
    String add_time_start = this.servletRequest.getParameter("add_time_start");
    String add_time_end = this.servletRequest.getParameter("add_time_end");
    String add_person = this.servletRequest.getParameter("add_person");
    String contract_money = this.servletRequest.getParameter("contract_money");
    String pstatus = this.servletRequest.getParameter("pstatus");

    this.servletRequest.setAttribute("plan_num", plan_num);
    this.servletRequest.setAttribute("contract_num", contract_num);
    this.servletRequest.setAttribute("self_num", self_num);
    this.servletRequest.setAttribute("contract_name", contract_name);
    this.servletRequest.setAttribute("project_num", project_num);
    this.servletRequest.setAttribute("sign_cop", sign_cop);
    this.servletRequest.setAttribute("add_time_start", add_time_start);
    this.servletRequest.setAttribute("add_time_end", add_time_end);
    this.servletRequest.setAttribute("add_person", add_person);
    this.servletRequest.setAttribute("contract_money", contract_money);
    this.servletRequest.setAttribute("pstatus", pstatus);

    int totalRows = this.processInfoService.countHtspOa(plan_num, contract_num, self_num, 
      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
      add_person, contract_money, pstatus);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findHtspOaByPage(pageinfo.getBeginIndex(), this.vo.pageSize, 
      plan_num, contract_num, self_num, 
      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
      add_person, contract_money, pstatus);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    return "success";
  }

  public String findDbByPage() {
    String bh_all = this.servletRequest.getParameter("bh_all");
    String depement_z = this.servletRequest.getParameter("depement_z");
    String depement_x = this.servletRequest.getParameter("depement_x");
    String flags = this.servletRequest.getParameter("flags");

    this.servletRequest.setAttribute("bh_all", bh_all);
    this.servletRequest.setAttribute("depement_z", depement_z);
    this.servletRequest.setAttribute("depement_x", depement_x);
    this.servletRequest.setAttribute("flags", flags);

    int totalRows = this.processInfoService.countDb(bh_all, depement_z, depement_x, flags);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findDbByPage(pageinfo.getBeginIndex(), this.vo.pageSize, 
      bh_all, depement_z, depement_x, flags);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    return "success";
  }

  public String findJobContactByPage() {
    String serial = this.servletRequest.getParameter("serial");
    String theme = this.servletRequest.getParameter("theme");
    String main_unit = this.servletRequest.getParameter("main_unit");
    String copy_unit = this.servletRequest.getParameter("copy_unit");
    String flag = this.servletRequest.getParameter("flag");
    String processType = this.servletRequest.getParameter("processType");

    this.servletRequest.setAttribute("serial", serial);
    this.servletRequest.setAttribute("theme", theme);
    this.servletRequest.setAttribute("main_unit", main_unit);
    this.servletRequest.setAttribute("copy_unit", copy_unit);
    this.servletRequest.setAttribute("flag", flag);
    this.servletRequest.setAttribute("processType", processType);

    int totalRows = this.processInfoService.countJobContact(serial, theme, main_unit, copy_unit, flag,processType);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findJobContactByPage(pageinfo.getBeginIndex(), this.vo.pageSize, 
      serial, theme, main_unit, copy_unit, flag,processType);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    return "success";
  }

  public String findNewDocSendByPage() {
    String doc_title = this.servletRequest.getParameter("doc_title");
    String send_id = this.servletRequest.getParameter("send_id");
    String doc_count = this.servletRequest.getParameter("doc_count");
    String send_date_start = this.servletRequest.getParameter("send_date_start");
    String send_date_end = this.servletRequest.getParameter("send_date_end");
    String content = this.servletRequest.getParameter("content");
    String pstatus = this.servletRequest.getParameter("pstatus");
    String name = this.servletRequest.getParameter("name");
    String operator = this.servletRequest.getParameter("operator");

    this.servletRequest.setAttribute("doc_title", doc_title);
    this.servletRequest.setAttribute("send_id", send_id);
    this.servletRequest.setAttribute("doc_count", doc_count);
    this.servletRequest.setAttribute("send_date_start", send_date_start);
    this.servletRequest.setAttribute("send_date_end", send_date_end);
    this.servletRequest.setAttribute("content", content);
    this.servletRequest.setAttribute("pstatus", pstatus);
    this.servletRequest.setAttribute("name", name);
    this.servletRequest.setAttribute("operator", operator);

    int totalRows = this.processInfoService.countNewDocSend(doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator);
    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
    List list = this.processInfoService.findNewDocSendByPage(pageinfo.getBeginIndex(), this.vo.pageSize, doc_title, send_id, doc_count, 
      send_date_start, send_date_end, content, pstatus, name, operator);

    PageResultSet result = new PageResultSet();
    result.setList(list);
    result.setPageInfo(pageinfo);
    this.servletRequest.setAttribute("result", result);
    return "success";
  }
  
  public String findPartySendByPage() {
	    String dept = this.servletRequest.getParameter("dept");
	    String title = this.servletRequest.getParameter("title");
	    String code = this.servletRequest.getParameter("code");
	    String filezh = this.servletRequest.getParameter("filezh");
	    String pstatus = this.servletRequest.getParameter("pstatus");
	    String processType = this.servletRequest.getParameter("processType");
	    String date_start = this.servletRequest.getParameter("date_start");
	    String date_end = this.servletRequest.getParameter("date_end");
	    this.servletRequest.setAttribute("dept", dept);
	    this.servletRequest.setAttribute("title", title);
	    this.servletRequest.setAttribute("code", code);
	    this.servletRequest.setAttribute("filezh", filezh);
	    this.servletRequest.setAttribute("pstatus", pstatus);
	    this.servletRequest.setAttribute("processType", processType);
	    this.servletRequest.setAttribute("date_start", date_start);
	    this.servletRequest.setAttribute("date_end", date_end);

	    int totalRows = this.processInfoService.countPartySend(dept, title, code, filezh, pstatus, processType, date_start, date_end);
	    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
	    List list = this.processInfoService.findPartySendByPage(pageinfo.getBeginIndex(), this.vo.pageSize, dept, title, code, filezh, pstatus, processType, date_start, date_end);

	    PageResultSet result = new PageResultSet();
	    result.setList(list);
	    result.setPageInfo(pageinfo);
	    this.servletRequest.setAttribute("result", result);
	    return "success";
	  }
  
  public String findDisciplineSendByPage() {
	    String dept = this.servletRequest.getParameter("dept");
	    String title = this.servletRequest.getParameter("title");
	    String code = this.servletRequest.getParameter("code");
	    String filezh = this.servletRequest.getParameter("filezh");
	    String pstatus = this.servletRequest.getParameter("pstatus");
	    String processType = this.servletRequest.getParameter("processType");
	    String date_start = this.servletRequest.getParameter("date_start");
	    String date_end = this.servletRequest.getParameter("date_end");
	    this.servletRequest.setAttribute("dept", dept);
	    this.servletRequest.setAttribute("title", title);
	    this.servletRequest.setAttribute("code", code);
	    this.servletRequest.setAttribute("filezh", filezh);
	    this.servletRequest.setAttribute("pstatus", pstatus);
	    this.servletRequest.setAttribute("processType", processType);
	    this.servletRequest.setAttribute("date_start", date_start);
	    this.servletRequest.setAttribute("date_end", date_end);

	    int totalRows = this.processInfoService.countDisciplineSend(dept, title, code, filezh, pstatus, processType, date_start, date_end);
	    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
	    List list = this.processInfoService.findDisciplineSendByPage(pageinfo.getBeginIndex(), this.vo.pageSize, dept, title, code, filezh, pstatus, processType, date_start, date_end);

	    PageResultSet result = new PageResultSet();
	    result.setList(list);
	    result.setPageInfo(pageinfo);
	    this.servletRequest.setAttribute("result", result);
	    return "success";
	  }
}