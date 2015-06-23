package com.wonders.stpt.delayWarn.model.bo;

import java.util.Date;

/**
 * Created by Administrator on 2014/6/17.
 */
public class DelayProcess {

    private String processName;
    private String summary;
    private String beginDate;
    private String endDate;
    private String status;
    private String department;
    private Integer delay;
    private Integer warn;
    private Integer maxWarn;
    private Integer minWarn;
    private String delayType;
    private String apply;
    private String cname;
    private String cincident;
    private String incident;
    private String workflowType;
    private String deptcode;

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }

    public DelayProcess() {
        delay = 0;
        warn = 0;
        maxWarn = 0;
        minWarn = 0;
//        beginDate = "2014-01-01";
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Integer getWarn() {
        return warn;
    }

    public void setWarn(Integer warn) {
        this.warn = warn;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCincident() {
        return cincident;
    }

    public void setCincident(String cincident) {
        this.cincident = cincident;
    }

    public Integer getMaxWarn() {
        return maxWarn;
    }

    public void setMaxWarn(Integer maxWarn) {
        this.maxWarn = maxWarn;
    }

    public Integer getMinWarn() {
        return minWarn;
    }

    public void setMinWarn(Integer minWarn) {
        this.minWarn = minWarn;
    }

    public String getDelayType() {
        return delayType;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public void setDelayType(String delayType) {
        this.delayType = delayType;
    }}


