package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/26.
 */
@Entity
@Table(name = "T_WORK_INSPECT")
public class WorkInspect {
	/**
	 * 主键
	 */
    private String workInspectId;
    /**
	 * 单位名称
	 */
    private String department;
    /**
	 * 检查日期
	 */
    private Date inspectDate;
    /**
	 * 检查问题描述
	 */
    private String inspectInfo;
    /**
	 * 严重程度
	 */
    private String degree;
    /**
	 * 业务类型
	 */
    private String category;
    /**
	 * 科目
	 */
    private String subject;
    /**
	 * 科目子类
	 */
    private String subSubject;
    /**
	 * 整改方案
	 */
    private String plan;
    /**
	 * 计划开始时间
	 */
    private Date planBeginDate;
    /**
	 * 计划结束时间
	 */
    private Date planEndDate;
    /**
	 * 落实情况
	 */
    private String workable;
    /**
	 * 整改备注
	 */
    private String reformMemo;
    /**
	 * 跟踪日期
	 */
    private Date tractDate;
    /**
	 * 复核情况
	 */
    private String review;
    /**
	 * 跟踪备注
	 */
    private String tractMemo;
    /**
	 * 备注
	 */
    private String memo;
    /**
	 * 
	 */
    private Date beginInspectDate;
    /**
	 * 
	 */
    private Date endInspectDate;
    /**
     * 逻辑删除字段(0:显示     1:删除)
     */
    private String removed;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新人
     */
    private String updater;

    private String sysName;


    public WorkInspect() {
        removed = "0";
        createTime = new Date();
        updateTime = new Date();
    }


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "WORK_INSPECT_ID")
    public String getWorkInspectId() {
        return workInspectId;
    }

    public void setWorkInspectId(String workInspectId) {
        this.workInspectId = workInspectId;
    }

    @Column(name = "SYS_NAME")
    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    @Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }



    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "INSPECT_DATE")
    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    @Column(name = "INSPECT_INFO")
    public String getInspectInfo() {
        return inspectInfo;
    }

    public void setInspectInfo(String inspectInfo) {
        this.inspectInfo = inspectInfo;
    }

    @Column(name = "DEGREE")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Column(name = "SUBJECT")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Column(name = "SUB_SUBJECT")
    public String getSubSubject() {
        return subSubject;
    }

    public void setSubSubject(String subSubject) {
        this.subSubject = subSubject;
    }
    @Column(name = "PLAN")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
    @Column(name = "PLAN_BEGIN_DATE")
    public Date getPlanBeginDate() {
        return planBeginDate;
    }

    public void setPlanBeginDate(Date planBeginDate) {
        this.planBeginDate = planBeginDate;
    }
    @Column(name = "PLAN_END_DATE")
    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }
    @Column(name = "WORKABLE")
    public String getWorkable() {
        return workable;
    }

    public void setWorkable(String workable) {
        this.workable = workable;
    }
    @Column(name = "REFORM_MEMO")
    public String getReformMemo() {
        return reformMemo;
    }

    public void setReformMemo(String reformMemo) {
        this.reformMemo = reformMemo;
    }
    @Column(name = "TRACT_DATE")
    public Date getTractDate() {
        return tractDate;
    }

    public void setTractDate(Date tractDate) {
        this.tractDate = tractDate;
    }
    @Column(name = "REVIEW")
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    @Column(name = "TRACT_MEMO")
    public String getTractMemo() {
        return tractMemo;
    }

    public void setTractMemo(String tractMemo) {
        this.tractMemo = tractMemo;
    }
    @Column(name = "MEMO")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    @Column(name = "REMOVED")
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    @Column(name = "UPDATER")
    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Transient
    public Date getBeginInspectDate() {
        return beginInspectDate;
    }

    public void setBeginInspectDate(Date beginInspectDate) {
        this.beginInspectDate = beginInspectDate;
    }

    @Transient
    public Date getEndInspectDate() {
        return endInspectDate;
    }

    public void setEndInspectDate(Date endInspectDate) {
        this.endInspectDate = endInspectDate;
    }
}
