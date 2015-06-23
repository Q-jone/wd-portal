package com.wonders.stpt.processDone.model.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BasicData")  
@XmlAccessorType(XmlAccessType.FIELD)
public class ReDirective {
		private String id;
		private String submitdept;
	    private String submitdate;
	    private String deptid;
	    private String title;
	    private String content;
	    private String operator;
	    private String deptmaster;
	    private String nbopinion;
	    private String deptopinion;
	    private String leaderopinion;
	    private String chairmanopinion;
	    private String handle;
	    private String processinstanceid;
	    private String activeid;
	    private String status;
	    private String doclevel;
	    private String keyword;
	    private String attach;
	    private String theme;
	    private String zdept;
	    private String xdept;
	    private String zleader;
	    private String fleader;
	    private String zbopinion;
	    private String xbopinion;
	    private String pbopinion;
	    private String workitemid;
	    private String saved;
	    private String receivePerson;
	    private String receiveDept;
	    private String xdeptamount;
	    private String attachs;
	    private String taskid;
	    
	    private String personName;//保存整个流程相关人员帐号
	    private String redattach;// 保存善后处理意见附件
	    
	    private String operateTime;
	    private String removed;
	    private String flag;	//归档标志
	    
	    
	    private String chiefPerson;	//区分2级领导
	    private String typeTitle; //区分title
	    
	    
	    public String getTypeTitle() {
			return typeTitle;
		}

		public void setTypeTitle(String typeTitle) {
			this.typeTitle = typeTitle;
		}

		public String getChiefPerson() {
			return chiefPerson;
		}

		public void setChiefPerson(String chiefPerson) {
			this.chiefPerson = chiefPerson;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public ReDirective(){
	    	operateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	    	removed = "0";
	    	flag= "0";
	    }
	    
		public String getRemoved() {
			return removed;
		}
		public void setRemoved(String removed) {
			this.removed = removed;
		}
		/*//id
		private String submitDept;				//呈报部门
		private String submitDate;				//呈报日期
		private String deptId;					//部门编号
		private String title;					//标题
		private String content;					//主要内容
		private String operator;				//经办人
		private String deptMaster;				//部门负责人
		private String nbOpinion;				//拟办意见
		private String deptOpinion; 			//会稿部门意见
		private String leaderOpinion;			//公司分管领导批示
		private String chairmanOpinion;			//董事长批示
		private String handle;		 			//办理情况
		private String processInstanceId;	 	//流程实例ID
		private String activeId;	 			//活动ID
		private String status;					//呈批件状态
		private String docLevel;				//缓急
		private String keyWord;					//关键词
		private String attach;					//附件
		private String attachs;					//意见附件
		private String taskId;					//流程ID
		private String theme;					//主题词
		private String zdept;					//主办部门
		private String xdept;					//协办部门
		private String zleader;					//批办主管领导
		private String fleader;					//批办分管领导
		private String zbopinion;				//主办意见
		private String xbopinion;				//协办意见
		private String pbopinion;				//批办意见
		private String workitemId;	//工作项ID
		private String saved;	//存档位
		private String receivePerson;	//传阅人
		private String receiveDept;		//传阅部门
		private String xdeptamount;		//协办部门数量
		
		private Long operateTime;
		private int removed;*/
		public String getActiveid() {
			return activeid;
		}
		public void setActiveid(String activeid) {
			this.activeid = activeid;
		}
		public String getAttach() {
			return attach;
		}
		public void setAttach(String attach) {
			this.attach = attach;
		}
		public String getAttachs() {
			return attachs;
		}
		public void setAttachs(String attachs) {
			this.attachs = attachs;
		}
		public String getChairmanopinion() {
			return chairmanopinion;
		}
		public void setChairmanopinion(String chairmanopinion) {
			this.chairmanopinion = chairmanopinion;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getDeptid() {
			return deptid;
		}
		public void setDeptid(String deptid) {
			this.deptid = deptid;
		}
		public String getDeptmaster() {
			return deptmaster;
		}
		public void setDeptmaster(String deptmaster) {
			this.deptmaster = deptmaster;
		}
		public String getDeptopinion() {
			return deptopinion;
		}
		public void setDeptopinion(String deptopinion) {
			this.deptopinion = deptopinion;
		}
		public String getDoclevel() {
			return doclevel;
		}
		public void setDoclevel(String doclevel) {
			this.doclevel = doclevel;
		}
		public String getFleader() {
			return fleader;
		}
		public void setFleader(String fleader) {
			this.fleader = fleader;
		}
		public String getHandle() {
			return handle;
		}
		public void setHandle(String handle) {
			this.handle = handle;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getLeaderopinion() {
			return leaderopinion;
		}
		public void setLeaderopinion(String leaderopinion) {
			this.leaderopinion = leaderopinion;
		}
		public String getNbopinion() {
			return nbopinion;
		}
		public void setNbopinion(String nbopinion) {
			this.nbopinion = nbopinion;
		}
		
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getPbopinion() {
			return pbopinion;
		}
		public void setPbopinion(String pbopinion) {
			this.pbopinion = pbopinion;
		}
		public String getProcessinstanceid() {
			return processinstanceid;
		}
		public void setProcessinstanceid(String processinstanceid) {
			this.processinstanceid = processinstanceid;
		}
		public String getReceiveDept() {
			return receiveDept;
		}
		public void setReceiveDept(String receiveDept) {
			this.receiveDept = receiveDept;
		}
		public String getReceivePerson() {
			return receivePerson;
		}
		public void setReceivePerson(String receivePerson) {
			this.receivePerson = receivePerson;
		}

		public String getSaved() {
			return saved;
		}
		public void setSaved(String saved) {
			this.saved = saved;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getSubmitdate() {
			return submitdate;
		}
		public void setSubmitdate(String submitdate) {
			this.submitdate = submitdate;
		}
		public String getSubmitdept() {
			return submitdept;
		}
		public void setSubmitdept(String submitdept) {
			this.submitdept = submitdept;
		}
		public String getTaskid() {
			return taskid;
		}
		public void setTaskid(String taskid) {
			this.taskid = taskid;
		}
		public String getTheme() {
			return theme;
		}
		public void setTheme(String theme) {
			this.theme = theme;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getWorkitemid() {
			return workitemid;
		}
		public void setWorkitemid(String workitemid) {
			this.workitemid = workitemid;
		}
		public String getXbopinion() {
			return xbopinion;
		}
		public void setXbopinion(String xbopinion) {
			this.xbopinion = xbopinion;
		}
		public String getXdept() {
			return xdept;
		}
		public void setXdept(String xdept) {
			this.xdept = xdept;
		}
		public String getXdeptamount() {
			return xdeptamount;
		}
		public void setXdeptamount(String xdeptamount) {
			this.xdeptamount = xdeptamount;
		}
		public String getZbopinion() {
			return zbopinion;
		}
		public void setZbopinion(String zbopinion) {
			this.zbopinion = zbopinion;
		}
		public String getZdept() {
			return zdept;
		}
		public void setZdept(String zdept) {
			this.zdept = zdept;
		}
		public String getZleader() {
			return zleader;
		}
		public void setZleader(String zleader) {
			this.zleader = zleader;
		}
		public String getOperateTime() {
			return operateTime;
		}
		public void setOperateTime(String operateTime) {
			this.operateTime = operateTime;
		}

		public String getPersonName() {
			return personName;
		}

		public void setPersonName(String personName) {
			this.personName = personName;
		}

		public String getRedattach() {
			return redattach;
		}

		public void setRedattach(String redattach) {
			this.redattach = redattach;
		}

}
