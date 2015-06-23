/**
 * 
 */
package com.wonders.stpt.applyProject.model.bo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/** 
 * @ClassName: TApplyProject 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月30日 上午10:47:41 
 *  
 */
@Entity
@Table(name = "T_APPLY_PROJECT")
public class TApplyProject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4043736101029302221L;
	
	private String id;
	private String planName;
	private String planAttach;
	private String accidentContent;
	private String projectName;
	private String projectAchievement;
	private String projectAchievementAttach;
	private String projectStatus;
	private String projectProgress;
	private String projectRemark;
	private String type;
	private String operateTime;
	private String operatePerson;
	private String removed;
	
	public TApplyProject (){
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	}
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "PLAN_NAME", length = 2000)
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	@Column(name = "PLAN_ATTACH", length = 50)
	public String getPlanAttach() {
		return planAttach;
	}
	public void setPlanAttach(String planAttach) {
		this.planAttach = planAttach;
	}
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "ACCIDENT_CONTENT",columnDefinition = "CLOB")
	public String getAccidentContent() {
		return accidentContent;
	}
	public void setAccidentContent(String accidentContent) {
		this.accidentContent = accidentContent;
	}
	@Column(name = "PROJECT_NAME", length = 50)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "PROJECT_ACHIEVEMENT",columnDefinition = "CLOB")
	public String getProjectAchievement() {
		return projectAchievement;
	}
	public void setProjectAchievement(String projectAchievement) {
		this.projectAchievement = projectAchievement;
	}
	
	@Column(name = "PROJECT_ACHIEVEMENT_ATTACH", length = 50)
	public String getProjectAchievementAttach() {
		return projectAchievementAttach;
	}
	public void setProjectAchievementAttach(String projectAchievementAttach) {
		this.projectAchievementAttach = projectAchievementAttach;
	}
	
	@Column(name = "PROJECT_STATUS", length = 50)
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "PROJECT_PROGRESS",columnDefinition = "CLOB")
	public String getProjectProgress() {
		return projectProgress;
	}
	public void setProjectProgress(String projectProgress) {
		this.projectProgress = projectProgress;
	}
	
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "PROJECT_REMARK",columnDefinition = "CLOB")
	public String getProjectRemark() {
		return projectRemark;
	}
	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}
	
	@Column(name = "TYPE", length = 1)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "OPERATE_TIME", length = 50)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "OPERATE_PERSON", length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}
	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}
	
	@Column(name = "REMOVED", length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	
	
	
}
