/** 
 * Copyright (c) 2003-2007 Wonders Information Co.,Ltd. All Rights Reserved.
 * 5-6/F, 20 Bldg, 481 Guiping RD. Shanghai 200233,PRC
 *
 * This software is the confidential and proprietary information of Wonders Group.
 * (Research & Development Center). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gun.org
 */

package com.wonders.stpt.processDone.model.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author 
 * @version $Revision: 1.9 $
 * @author (lastest modification by $Author: lixizhen $)
 * 
 * @hibernate.class
 * table = "T_DOC_RECEIVE"
 * dynamic-update = "true"
 */
@XmlRootElement(name="BasicData")  
@XmlAccessorType(XmlAccessType.FIELD)
public class DocReceiveBo {
	
	private String id;			//内部流水号 SEQ_DOC_RECEIVE
	private String drSwtype;		//收文类型
	private String drSwid;			//收文编号
	private String drSwdate;		//收文日期
	private String drSwunit;		//来文单位
	private String drFiledate;	//文件日期
	private String drSecretClass;	//密级
	private long drNum;			//份数
	private String drFilezh;		//文件字号
	private String drTitle;		//文件标题
	private String drKeyword;		//关键词
	private String drContent;		//文件内容
	private String drAttach;		//其他附件
	private String drNbOpinion;	//拟办意见
	private String drPbOpinion;	//批办意见
	private String drBlResult;	//办理结果
	private String drBlMode;		//办理方式
	private String drLastDate;	//修改时间
	private String instanceId;	//流程ID
	private String workitemId;	//工作项ID
	private String userId;		//用户ID
	private String modelId;		//流程模型ID
	private int removed;
    private java.lang.String operator;
    private java.lang.String operatetime;
    private String chiefDep;	//主办部门
    private String ordinartyDep;	//协办部门
    private String chiefPerson;	//批办主管领导 
    private String ordinartyPerson;	//批办分管领导
    private String receivePerson;	//传阅人
	private String receiveDept;		//传阅部门
	private String remark;		//备注
	private String flag;
	private String taskid;
    private String personName;//保存整个流程相关人员帐号
    private String attach;// 保存善后处理意见附件
	private String priorities;//表示缓急状态 
	
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public java.lang.String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(java.lang.String operatetime) {
		this.operatetime = operatetime;
	}

	public int getRemoved() {
		return removed;
	}

	public void setRemoved(int removed) {
		this.removed = removed;
	}
	
	public DocReceiveBo() {
  		super();
  		this.removed = 0;
  		//Date now = new Date(System.currentTimeMillis());
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	
//		/String cDate = format.format(now);
  		//this.operatetime = DateUtil.getCurrDate("yyyy-MM-dd");
  	}

	/**
	 * @return the id
	 * 
	 * @hibernate.id
     * column = "ID"
     * generator-class = "native" 
     * unsaved-value = "0"
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @hibernate.property column = "ATTACH"
	 * length="50"
	 * @return
	 */
	public String getDrAttach() {
		return drAttach;
	}

	public void setDrAttach(String drAttach) {
		this.drAttach = drAttach;
	}
	/**
	 * @hibernate.property column = "BL_MODE"
	 * @return
	 */
	public String getDrBlMode() {
		return drBlMode;
	}
	public void setDrBlMode(String drBlMode) {
		this.drBlMode = drBlMode;
	}
	
	/**
	 * @hibernate.property column = "BL_RESULT"
	 * length="500"
	 * @return
	 */
	public String getDrBlResult() {
		return drBlResult;
	}

	public void setDrBlResult(String drBlResult) {
		this.drBlResult = drBlResult;
	}
	
	/**
	 * @hibernate.property column = "CONTENT"
	 * length="50"
	 * @return
	 */
	public String getDrContent() {
		return drContent;
	}

	public void setDrContent(String drContent) {
		this.drContent = drContent;
	}
	/**
	 * @hibernate.property column = "FILEDATE"
	 * length="19"
	 * @return
	 */
	public String getDrFiledate() {
		return drFiledate;
	}

	public void setDrFiledate(String drFiledate) {
		this.drFiledate = drFiledate;
	}
	
	/**
	 * @hibernate.property column = "FILEZH"
	 * length="100"
	 * @return
	 */
	public String getDrFilezh() {
		return drFilezh;
	}

	public void setDrFilezh(String drFilezh) {
		this.drFilezh = drFilezh;
	}
	
	/**
	 * @hibernate.property column = "KEYWORD"
	 * length="100"
	 * @return
	 */
	public String getDrKeyword() {
		return drKeyword;
	}

	public void setDrKeyword(String drKeyword) {
		this.drKeyword = drKeyword;
	}
	
	/**
	 * @hibernate.property column = "LASTDATE"
	 * length="19"
	 * @return
	 */
	public String getDrLastDate() {
		return drLastDate;
	}

	public void setDrLastDate(String drLastDate) {
		this.drLastDate = drLastDate;
	}
	/**
	 * @hibernate.property column = "NB_OPINION"
	 * length="500"
	 * @return
	 */
	public String getDrNbOpinion() {
		return drNbOpinion;
	}

	public void setDrNbOpinion(String drNbOpinion) {
		this.drNbOpinion = drNbOpinion;
	}
	
	/**
	 * @hibernate.property column = "NUM"
	 * @return
	 */
	public long getDrNum() {
		return drNum;
	}

	public void setDrNum(long drNum) {
		this.drNum = drNum;
	}
	
	/**
	 * @hibernate.property column = "PB_OPINION"
	 * length="500"
	 * @return
	 */
	public String getDrPbOpinion() {
		return drPbOpinion;
	}

	public void setDrPbOpinion(String drPbOpinion) {
		this.drPbOpinion = drPbOpinion;
	}
	/**
	 * @hibernate.property column = "SWTYPE"
	 * length="50"
	 * @return
	 */
	public String getDrSwtype() {
		return drSwtype;
	}

	public void setDrSwtype(String drSwtype) {
		this.drSwtype = drSwtype;
	}
	
	/**
	 * @hibernate.property column = "SECRET_CLASS"
	 * @return
	 */
	public String getDrSecretClass() {
		return drSecretClass;
	}

	public void setDrSecretClass(String drSecretClass) {
		this.drSecretClass = drSecretClass;
	}
	
	/**
	 * @hibernate.property column = "SWDATE"
	 * length="19"
	 * @return
	 */
	public String getDrSwdate() {
		return drSwdate;
	}

	public void setDrSwdate(String drSwdate) {
		this.drSwdate = drSwdate;
	}
	
	/**
	 * @hibernate.property column = "SWID"
	 * @return
	 */
	public String getDrSwid() {
		return drSwid;
	}

	public void setDrSwid(String drSwid) {
		this.drSwid = drSwid;
	}
	
	/**
	 * @hibernate.property column = "SWUNIT"
	 * length="100"
	 * @return
	 */
	public String getDrSwunit() {
		return drSwunit;
	}

	public void setDrSwunit(String drSwunit) {
		this.drSwunit = drSwunit;
	}
	
	/**
	 * @hibernate.property column = "TITLE"
	 * length="100"
	 * @return
	 */
	public String getDrTitle() {
		return drTitle;
	}

	public void setDrTitle(String drTitle) {
		this.drTitle = drTitle;
	}
	
	/**
	 * @hibernate.property column = "INSTANCEID"
	 * length="100"
	 * @return
	 */
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	/**
	 * @hibernate.property column = "MODELID"
	 * length="100"
	 * @return
	 */
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	/**
	 * @hibernate.property column = "OPERATOR"
	 * length="100"
	 * @return
	 */
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * @hibernate.property column = "USERID"
	 * length="100"
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @hibernate.property column = "WORKITEMID"
	 * length="100"
	 * @return
	 */
	public String getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}
	
	/**
	 * @hibernate.property column = "CHIEF_DEP"
	 * length="200"
	 * @return
	 */
	public String getChiefDep() {
		return chiefDep;
	}

	public void setChiefDep(String chiefDep) {
		this.chiefDep = chiefDep;
	}
	
	/**
	 * @hibernate.property column = "CHIEF_PERSON"
	 * length="200"
	 * @return
	 */
	public String getChiefPerson() {
		return chiefPerson;
	}
	
	public void setChiefPerson(String chiefPerson) {
		this.chiefPerson = chiefPerson;
	}
	
	/**
	 * @hibernate.property column = "ORDINARY_DEP"
	 * length="200"
	 * @return
	 */
	public String getOrdinartyDep() {
		return ordinartyDep;
	}

	public void setOrdinartyDep(String ordinartyDep) {
		this.ordinartyDep = ordinartyDep;
	}
	
	/**
	 * @hibernate.property column = "ORDINARY_PERSON"
	 * length="200"
	 * @return
	 */
	public String getOrdinartyPerson() {
		return ordinartyPerson;
	}

	public void setOrdinartyPerson(String ordinartyPerson) {
		this.ordinartyPerson = ordinartyPerson;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPriorities() {
		return priorities;
	}

	public void setPriorities(String priorities) {
		this.priorities = priorities;
	}

	

}

