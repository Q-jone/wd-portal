package com.wonders.stpt.delayWarn.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wonders.stpt.delayWarn.model.bo.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wonders.stpt.beforeBuild.model.bo.ShortMsg;
import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.delayWarn.service.DelayWarnService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value = "/delayWarn")
@Controller("delayWarnAction")
@Scope("prototype")
public class DelayWarnAction {
    public ActionContext actionContext = ActionContext.getContext();
    public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
    public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
    public HttpSession session = request.getSession();

    @Autowired
    private DelayWarnService delayWarnService;

    ActionWriter ac = new ActionWriter(response);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat yDf = new SimpleDateFormat("yyyy");
    private String begin;
    private String end;
    private String status;
    private PageResultSet<DelayProcess> resultSet;
    private DelayProcess delayProcess;
    private String type;
    private int page;
    private List<DelayItemReportVo> delayItemReportVoList;
    private String item;
    private String query;
    private String showDept;
    private int incident;
    private String processname;


    /**
     * 生成工作日
     *
     * @return
     * @throws ParseException
     */
    @Action(value = "saveWorkdays")
    public String saveWorkdays() throws ParseException {
        Date date = simpleDf.parse("2014-01-01");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        List<Workdays> list = new ArrayList<Workdays>();
        for (int i = 0; i < 365; i++) {
            if (c.get(Calendar.DAY_OF_WEEK) != 7 && c.get(Calendar.DAY_OF_WEEK) != 1) {
                Workdays workdays = new Workdays();
                workdays.setYear("2014");
                workdays.setDay(simpleDf.format(c.getTime()));
                list.add(workdays);
            }
            c.add(Calendar.DATE, 1);
        }
        delayWarnService.saveOrUpdateAll(list);
        return null;
    }

    /**
     * 发送延时短信
     *
     * @return
     */
    @Action(value = "sendDelayMessage")
    public String sendDelayMessage() {
        String operateTime = df.format(new Date());
        String today = simpleDf.format(new Date());
        List<ShortMsg> msgList = new ArrayList<ShortMsg>();
        List<DelayWarnLog> delayList = new ArrayList<DelayWarnLog>();
        List<Object> src = new ArrayList<Object>();
        String sql = "select count(*) count_num from t_workdays t where t.day = ? ";
        src.add(today);
        if (Integer.parseInt(delayWarnService.findBySql(sql, src).get(0) + "") > 0) {//判断是否工作日
            sql = "select distinct t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime," +
                    " substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId," +
                    " s.pname pname,s.pincident,s.cname ,s.cincident " +
                    " from t_doc_receive t,t_subprocess s , incidents i , tasks k " +
                    " where t.priorities='急件' and t.flag = 0 and t.removed=0 and t.modelid in ('收文流程','新收文流程') " +
                    " and t.modelid=s.pname and t.instanceid=s.pincident and s.cname='部门内部子流程' " +
                    " and i.status=1 and i.processname=s.cname and i.incident=s.cincident " +
                    " and k.status=1 and k.assignedtouser like 'ST/%' and k.processname=s.cname and k.incident=s.cincident " +
                    " union all " +
                    " select distinct t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime," +
                    " substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId," +
                    " s.pname pname,s.pincident,s.cname ,s.cincident " +
                    " from t_receive_directive t,t_subprocess s , incidents i , tasks k " +
                    " where t.doclevel in ('加急','紧急') and t.flag = 0 and t.removed=0 and t.activeid in ('收呈批件','新收呈批件') " +
                    " and t.activeid=s.pname and t.processinstanceid=s.pincident and s.cname='部门内部子流程' " +
                    " and i.status=1 and i.processname=s.cname and i.incident=s.cincident " +
                    " and k.status=1 and k.assignedtouser like 'ST/%' and k.processname=s.cname and k.incident=s.cincident";
            List<Object[]> list = delayWarnService.findBySql(sql, null);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    String begintime = list.get(i)[1] + "";
                    sql = "select delay_times from t_delay_item t where removed = '0' and pname = ? and pincident = ? and cname = ? and cincident = ? ";
                    src = new ArrayList<Object>();
                    src.add(list.get(i)[3] + "");
                    src.add(list.get(i)[4] + "");
                    src.add(list.get(i)[5] + "");
                    src.add(list.get(i)[6] + "");
                    List<Object[]> delayTimesList = delayWarnService.findBySql(sql, src);
                    if (delayTimesList != null && delayTimesList.size() > 0) {
                        int applyDays = Integer.parseInt(delayTimesList.get(0) + "");
                        Date date;
                        try {
                            date = simpleDf.parse(begintime);
                            Calendar c = Calendar.getInstance();
                            c.setTime(date);
                            c.add(Calendar.DATE, applyDays);
                            date = c.getTime();
                            begintime = simpleDf.format(date);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    sql = "select count(*) count_num from t_workdays t where day > ? and day < to_char(sysdate,'yyyy-mm-dd')";
                    src = new ArrayList<Object>();
                    src.add(begintime);
                    int delayDays = Integer.parseInt(delayWarnService.findBySql(sql, src).get(0) + "");

                    if (delayDays != 0 && delayDays % 3 == 0) {//判断延期天数是否3的倍数
                        sql = "select count(*) count_num from t_delay_warn_log t where t.pname = ? and t.pincident = ? and t.cname = ? and t.cincident = ? ";
                        src = new ArrayList<Object>();
                        src.add(list.get(i)[3] + "");
                        src.add(list.get(i)[4] + "");
                        src.add(list.get(i)[5] + "");
                        src.add(list.get(i)[6] + "");
                        int warnTimes = Integer.parseInt(delayWarnService.findBySql(sql, src).get(0) + "");
                        if (warnTimes < 5) {//判断提醒是否小于5次
                            DelayWarnLog delayWarnLog = new DelayWarnLog();
                            delayWarnLog.setPname(list.get(i)[3] + "");
                            delayWarnLog.setPincident(list.get(i)[4] + "");
                            delayWarnLog.setCname(list.get(i)[5] + "");
                            delayWarnLog.setCincident(list.get(i)[6] + "");
                            delayWarnLog.setDeptId(list.get(i)[2] + "");
                            delayWarnLog.setOperateTime(operateTime);
                            sql = "select leader_login_name,phone from t_delay_leader t where ";
                            if (list.get(i)[3] != null && (list.get(i)[3] + "").indexOf("新") > -1) {
                                sql += " new_dept_id = ? ";
                            } else {
                                sql += " old_dept_id = ? ";
                            }
                            src = new ArrayList<Object>();
                            src.add(list.get(i)[2] + "");
                            List<Object[]> list2 = delayWarnService.findBySql(sql, src);
                            String leader_login_name = "";
                            String phone = "";
                            if (list2 != null && list2.size() > 0) {
                                leader_login_name = list2.get(0)[0] + "";
                                phone = list2.get(0)[1] + "";
                            }
                            delayWarnLog.setDeptLeader(leader_login_name);
                            delayList.add(delayWarnLog);

                            ShortMsg msg = new ShortMsg();
                            msg.setMobile(phone);
                            msg.setStatus("0");
                            msg.setContent("您部门有一条急件流程：【" + list.get(i)[0] + "】被提醒，请尽快完成。");
                            msg.setSenddate(today + " 08:30:00");
                            msgList.add(msg);
                        }
                    }
                }
                delayWarnService.saveOrUpdateAll(delayList);
                delayWarnService.saveOrUpdateAll(msgList);
            }
        }
        return null;
    }

    /**
     * 延时申请页面
     *
     * @return
     */
    @Action(value = "showApplyList", results = {@Result(name = "success", location = "/delayWarn/applyList.jsp")})
    public String showApplyList() {
        String pname = request.getParameter("pname");
        String title = request.getParameter("title");
        String starttime1 = request.getParameter("starttime1");
        String starttime2 = request.getParameter("starttime2");
        request.setAttribute("pname", pname);
        request.setAttribute("title", title);
        request.setAttribute("starttime1", starttime1);
        request.setAttribute("starttime2", starttime2);
        List<String> newDeptIdList = new ArrayList<String>();
        List<String> oldDeptIdList = new ArrayList<String>();
        String params = "";
        List<Object> src = new ArrayList<Object>();
        Map<String, TaskUserVo> userMap = (Map<String, TaskUserVo>) request.getSession().getAttribute("deptUsers");
        if (userMap == null) {
            return "success";
        }
        for (Map.Entry<String, TaskUserVo> entry : userMap.entrySet()) {
            newDeptIdList.add(entry.getValue().getDeptId());
        }
        for (int i = 0; i < newDeptIdList.size(); i++) {
            if (i > 0) {
                params += ",";
            }
            params += "?";
            src.add(newDeptIdList.get(i));
        }
        String sql = "select t.old_dept_id from t_delay_leader t where t.new_dept_id in (" + params + ")";
        List<Object[]> list = delayWarnService.findBySql(sql, src);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                oldDeptIdList.add(list.get(i) + "");
            }
        }
        List<Object> src1 = new ArrayList<Object>();
        String params1 = "";
        if (oldDeptIdList != null && oldDeptIdList.size() > 0) {
            for (int i = 0; i < oldDeptIdList.size(); i++) {
                if (i > 0) {
                    params1 += ",";
                }
                params1 += "?";
                src1.add(oldDeptIdList.get(i));
                src1.add(oldDeptIdList.get(i));
            }
        }
        String params2 = "";
        for (int i = 0; i < newDeptIdList.size(); i++) {
            if (i > 0) {
                params2 += ",";
            }
            params2 += "?";
            src1.add(newDeptIdList.get(i));
            src1.add(newDeptIdList.get(i));
        }
        sql = "select * from (" +
                " select v.*,d.dept_name,'' param1,'' param2 from ( " +
                " select distinct t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime," +
                " substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId," +
                " s.pname pname,s.pincident,s.cname ,s.cincident, k.taskid " +
                " from t_doc_receive t,t_subprocess s , incidents i , tasks k " +
                " where t.priorities='急件' and t.flag = 0 and t.removed=0 and t.modelid = '收文流程' " +
                " and t.modelid=s.pname and t.instanceid=s.pincident and s.cname='部门内部子流程' " +
                " and i.status=1 and i.processname=s.cname and i.incident=s.cincident " +
                " and k.status=1 and k.assignedtouser like 'ST/%' and k.processname=s.cname and k.incident=s.cincident " +
                " ) v,t_delay_leader d where d.old_dept_id = v.deptid " +
                " and v.deptid in (" + params1 + ") " +
                " union all " +
                " select v.*,d.dept_name,'' param1,'' param2 from ( " +
                " select distinct t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime," +
                " substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId," +
                " s.pname pname,s.pincident,s.cname ,s.cincident,k.taskid " +
                " from t_receive_directive t,t_subprocess s , incidents i , tasks k " +
                " where t.doclevel in ('加急','紧急') and t.flag = 0 and t.removed=0 and t.activeid ='收呈批件' " +
                " and t.activeid=s.pname and t.processinstanceid=s.pincident and s.cname='部门内部子流程' " +
                " and i.status=1 and i.processname=s.cname and i.incident=s.cincident " +
                " and k.status=1 and k.assignedtouser like 'ST/%' and k.processname=s.cname and k.incident=s.cincident " +
                " ) v,t_delay_leader d where d.old_dept_id = v.deptid " +
                " and v.deptid in (" + params1 + ") " +
                " union all " +
                " select v.*,d.dept_name,'' param1,'' param2 from ( " +
                " select distinct t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime," +
                " substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId," +
                " s.pname pname,s.pincident,s.cname ,s.cincident, k.taskid " +
                " from t_doc_receive t,t_subprocess s , incidents i , tasks k " +
                " where t.priorities='急件' and t.flag = 0 and t.removed=0 and t.modelid = '新收文流程' " +
                " and t.modelid=s.pname and t.instanceid=s.pincident and s.cname='部门内部子流程' " +
                " and i.status=1 and i.processname=s.cname and i.incident=s.cincident " +
                " and k.status=1 and k.assignedtouser like 'ST/%' and k.processname=s.cname and k.incident=s.cincident " +
                " ) v,t_delay_leader d where d.new_dept_id = v.deptid " +
                " and v.deptid in (" + params2 + ") " +
                " union all " +
                " select v.*,d.dept_name,'' param1,'' param2 from ( " +
                " select distinct t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime," +
                " substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId," +
                " s.pname pname,s.pincident,s.cname ,s.cincident,k.taskid " +
                " from t_receive_directive t,t_subprocess s , incidents i , tasks k " +
                " where t.doclevel in ('加急','紧急') and t.flag = 0 and t.removed=0 and t.activeid ='新收呈批件' " +
                " and t.activeid=s.pname and t.processinstanceid=s.pincident and s.cname='部门内部子流程' " +
                " and i.status=1 and i.processname=s.cname and i.incident=s.cincident " +
                " and k.status=1 and k.assignedtouser like 'ST/%' and k.processname=s.cname and k.incident=s.cincident " +
                " ) v,t_delay_leader d where d.new_dept_id = v.deptid " +
                " and v.deptid in (" + params2 + ") " +
                " ) where 1=1 ";
        if (pname != null && pname.length() > 0) {
            sql += " and pname like ? ";
            src1.add("%" + pname + "%");
        }
        if (title != null && title.length() > 0) {
            sql += " and title like ? ";
            src1.add("%" + title + "%");
        }
        if (starttime1 != null && starttime1.length() > 0) {
            sql += " and beginTime >= ? ";
            src1.add(starttime1);
        }
        if (starttime2 != null && starttime2.length() > 0) {
            sql += " and beginTime <= ? ";
            src1.add(starttime2);
        }
        List<Object[]> showlist = delayWarnService.findBySql(sql, src1);
        if (showlist != null && showlist.size() > 0) {
            for (int i = 0; i < showlist.size(); i++) {
                String begintime = showlist.get(i)[1] + "";
                sql = "select case when v.count_num < 0 then 0 else v.count_num end count_num from ( " +
                        " select count(*)-3 count_num from t_workdays t where day > ? and day < to_char(sysdate,'yyyy-mm-dd')) v ";
                src = new ArrayList<Object>();
                src.add(begintime);
                showlist.get(i)[9] = delayWarnService.findBySql(sql, src).get(0) + "";

                sql = "select count(*) count_num from t_delay_item t where t.pname = ? and t.pincident = ? and t.cname = ? and t.cincident = ? and t.removed = '0' ";
                src = new ArrayList<Object>();
                src.add(showlist.get(i)[3]);
                src.add(showlist.get(i)[4]);
                src.add(showlist.get(i)[5]);
                src.add(showlist.get(i)[6]);
                showlist.get(i)[10] = delayWarnService.findBySql(sql, src).get(0) + "";
            }
            request.setAttribute("showlist", showlist);
        }
        return "success";
    }


    /**
     * 延时申请
     *
     * @return
     */
    @Action(value = "checkFunc")
    public String checkFunc() {
        String pname = request.getParameter("pname");
        String pincident = request.getParameter("pincident");
        String cname = request.getParameter("cname");
        String cincident = request.getParameter("cincident");
        String starttime = request.getParameter("starttime");
        String times = request.getParameter("times");
        String title = request.getParameter("title");
        String dept = request.getParameter("dept");
        String taskid = request.getParameter("taskid");
        DelayItem delayItem = new DelayItem();
        delayItem.setPincident(pincident);
        delayItem.setPname(pname);
        delayItem.setCincident(cincident);
        delayItem.setCname(cname);
        delayItem.setOverDate(starttime);
        delayItem.setDelayPerson((String) session.getAttribute("cs_login_name"));
        delayItem.setDelayTimes(times);
        delayItem.setUpdateTime(df.format(new Date()));
        delayItem.setRemoved("0");
        delayItem.setTitle(title);
        delayItem.setDept(dept);
        delayItem.setTaskid(taskid);
        delayWarnService.save(delayItem);
        return null;
    }

    /**
     * 延时申请历史页面
     *
     * @return
     */
    @Action(value = "showApplyHistoryList", results = {@Result(name = "success", location = "/delayWarn/applyHistoryList.jsp")})
    public String showApplyHistoryList() {
        String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
        String page = StringUtil.getNotNullValueString(request.getParameter("page"));
        String pname = request.getParameter("pname");
        String title = request.getParameter("title");
        String starttime1 = request.getParameter("starttime1");
        String starttime2 = request.getParameter("starttime2");
        String applytime1 = request.getParameter("applytime1");
        String applytime2 = request.getParameter("applytime2");
        String status = request.getParameter("status");
        String dept = request.getParameter("dept");
        request.setAttribute("pname", pname);
        request.setAttribute("title", title);
        request.setAttribute("starttime1", starttime1);
        request.setAttribute("starttime2", starttime2);
        request.setAttribute("applytime1", applytime1);
        request.setAttribute("applytime2", applytime2);
        request.setAttribute("status", status);
        request.setAttribute("dept", dept);
        if (pagesize == null || "".equals(pagesize)) {
            pagesize = "10";
        }
        if (page == null || "".equals(page)) {
            page = "1";
        }
        String sql = " from t_delay_item t,incidents s where t.removed = '0' " +
                " and s.processname = t.pname and s.incident = t.pincident ";
        List<Object> src1 = new ArrayList<Object>();
        if (pname != null && pname.length() > 0) {
            sql += " and t.pname like ? ";
            src1.add("%" + pname + "%");
        }
        if (title != null && title.length() > 0) {
            sql += " and t.title like ? ";
            src1.add("%" + title + "%");
        }
        if (starttime1 != null && starttime1.length() > 0) {
            sql += " and t.over_date >= ? ";
            src1.add(starttime1);
        }
        if (starttime2 != null && starttime2.length() > 0) {
            sql += " and t.over_date <= ? ";
            src1.add(starttime2);
        }
        if (applytime1 != null && applytime1.length() > 0) {
            sql += " and t.update_time > ? ";
            src1.add(applytime1);
        }
        if (applytime2 != null && applytime2.length() > 0) {
            sql += " and t.update_time < ? ";
            src1.add(applytime2);
        }
        if (status != null && status.length() > 0) {
            sql += " and s.status = ? ";
            src1.add(status);
        }
        if (dept != null && dept.length() > 0) {
            sql += " and t.dept like ? ";
            src1.add("%" + dept + "%");
        }
        String countSql = "select count(*) count_num " + sql;
        int totalRow = delayWarnService.findPageSize(countSql, src1);
        sql = "select t.*,s.endtime,s.status,'' exe " + sql + " order by t.update_time desc ";
        PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));
        PageResultSet<Map<String, Object>> pageResultSet = new PageResultSet<Map<String, Object>>();
        List<Map<String, Object>> showlist = delayWarnService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), sql, src1);
        if (showlist != null && showlist.size() > 0) {
            for (int i = 0; i < showlist.size(); i++) {
                String begintime = showlist.get(i).get("over_date") + "";
                List<Object> src = new ArrayList<Object>();
                sql = "select case when v.count_num < 0 then 0 else v.count_num end count_num from ( " +
                        " select count(*)-3 count_num from t_workdays t where day > ? and day < to_char(sysdate,'yyyy-mm-dd')) v ";
                src.add(begintime);
                showlist.get(i).put("exe", delayWarnService.findBySql(sql, src).get(0));
            }
        }
        pageResultSet.setList(showlist);
        pageResultSet.setPageInfo(pageInfo);
        request.setAttribute("pageResultSet", pageResultSet);
        return "success";
    }


    @Action(value = "delayDays")
    public String delayDays() throws Exception {
        List<DelayItemReportVo> list = delayWarnService.getDelayDaysData(null,begin, end, status);
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(jsonArray.toString());
        return null;
    }

    @Action(value = "delayDepartment")
    public String delayDepartment() throws Exception {
        List<DelayItemReportVo> list = delayWarnService.getDelayDepartmentsData(null,begin, end, status);
        JSONArray jsonArray = JSONArray.fromObject(list);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(jsonArray.toString());
        return null;
    }

    @Action(value = "delayDetail", results = {@Result(name = "success", location = "/delayWarn/delay_detail_list.jsp")})
    public String delayDetail() throws Exception {



        if ("dept".equals(type)){
            delayItemReportVoList = delayWarnService.getDelayDepartmentsData(getValue(item), begin, end, status);
        }if("day".equals(type)){
            delayItemReportVoList = delayWarnService.getDelayDaysData(item, begin, end, status);
        }

        return com.opensymphony.xwork2.Action.SUCCESS;
    }


    @Action(value = "listProcess", results = {@Result(name = "success", location = "/delayWarn/delay_process_list.jsp")})
    public String listProcess() throws Exception {

        if (delayProcess == null) {
            delayProcess = new DelayProcess();
        }

        if(!"sub".equals(type)) {
            String loginName = (String) session.getAttribute("cs_login_name");
            int count = delayWarnService.isAuthority("ST/" + loginName);
            if (count > 0) {
                showDept = "1";
                String deptNames = delayWarnService.getDeptNames(loginName, (Map<String, TaskUserVo>) request.getSession().getAttribute("deptUsers"));
                delayProcess.setDepartment(deptNames);
            } else {
                showDept = "0";
            }
        }else{
            showDept = "0";
        }

        delayProcess.setDelayType(getValue(delayProcess.getDelayType()));

        resultSet = delayWarnService.seach(delayProcess, page, 10);
        if(delayProcess.getMinWarn() == null || delayProcess.getMinWarn() == 0)
            delayProcess.setMinWarn(null);
        if(delayProcess.getMaxWarn() == null || delayProcess.getMaxWarn() == 0)
            delayProcess.setMaxWarn(null);
        return com.opensymphony.xwork2.Action.SUCCESS;
    }

    private String getValue(String key){
        String value ="";

        if("3天以下".equals(key))
            value = "1";
        else if("3-10天".equals(key))
            value = "2";
        else if("10-30天".equals(key))
            value = "3";
        else if("30-100天".equals(key))
            value = "4";
        else if("100天以上".equals(key))
            value = "5";
        else
            value = key;
        return value;
    }

    @Action(value = "monitor")
    public String monitor() throws Exception {
        String taskid = delayWarnService.getDelayProcessTaskId(processname,incident,type);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(taskid);
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public PageResultSet<DelayProcess> getResultSet() {
        return resultSet;
    }

    public void setResultSet(PageResultSet<DelayProcess> resultSet) {
        this.resultSet = resultSet;
    }

    public DelayProcess getDelayProcess() {
        return delayProcess;
    }

    public void setDelayProcess(DelayProcess delayProcess) {
        this.delayProcess = delayProcess;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DelayItemReportVo> getDelayItemReportVoList() {
        return delayItemReportVoList;
    }

    public void setDelayItemReportVoList(List<DelayItemReportVo> delayItemReportVoList) {
        this.delayItemReportVoList = delayItemReportVoList;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getShowDept() {
        return showDept;
    }

    public void setShowDept(String showDept) {
        this.showDept = showDept;
    }

    public int getIncident() {
        return incident;
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    public void setIncident(int incident) {
        this.incident = incident;
    }
}
