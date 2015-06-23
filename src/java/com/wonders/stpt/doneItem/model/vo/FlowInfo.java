/**
 * 
 */
package com.wonders.stpt.doneItem.model.vo;

/** 
 * @ClassName: FlowInfo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-22 下午02:26:39 
 *  
 *  
 **apply_username
deptName
endtime
overduetime
summary
memo
taskuser_name
priorities_show
taskid
UserName
pname
pincident
steplabel
task_type
processname
incident
 */
//已办
public class FlowInfo {
	private String processname;
	private String memo;
	private String priorities;
	private String incident;
	private String summary;
	private String initiator;
	private String starttime;
	private String endtime;
	private String status;
	private String pstatus;
	private String username;
	private String userendtime;
	private String groupid;
	private String deptname;
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPriorities() {
		return priorities;
	}
	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserendtime() {
		return userendtime;
	}
	public void setUserendtime(String userendtime) {
		this.userendtime = userendtime;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	
	
	
	
	
}
