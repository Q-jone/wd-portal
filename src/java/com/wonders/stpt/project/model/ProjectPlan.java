package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/16.项目计划实体
 */
@Entity
@Table(name = "T_PROJECT_PLAN")
public class ProjectPlan {
	/**
	 * 项目计划主键
	 */
    private String projectPlanId;
    /**
     * 阶段
     */
    private String planName;
    /**
     * 子阶段
     */
    private String subPlanName;
    /**
     * 计划开始时间
     */
    private Date planBeginDate;
    /**
     * 计划结束时间
     */
    private Date planEndDate;
    /**
     * 实际开始时间
     */
    private Date realBeginDate;
    /**
     * 实际结束时间
     */
    private Date realEndDate;
    /**
     * 备注
     */
    private String memo;
    /**
     * 逻辑删除字段(0:显示     1:删除)
     */
    private String removed; 
    /**
     * 计划对应的项目
     */
    private Project project;
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
    private String manual;
    
    public ProjectPlan(){
    	createTime = new Date();
        updateTime = new Date();
        removed = "0";
    }


    private String planStatus;
    private int period;
    private int startPosition;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "PROJECT_PLAN_ID")
    public String getProjectPlanId() {
        return projectPlanId;
    }

    public void setProjectPlanId(String projectPlanId) {
        this.projectPlanId = projectPlanId;
    }

    @Column(name = "PLAN_NAME")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Column(name = "SUB_PLAN_NAME")
    public String getSubPlanName() {
        return subPlanName;
    }

    public void setSubPlanName(String subPlanName) {
        this.subPlanName = subPlanName;
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

    @Column(name = "REAL_BEGIN_DATE")
    public Date getRealBeginDate() {
        return realBeginDate;
    }

    public void setRealBeginDate(Date realBeginDate) {
        this.realBeginDate = realBeginDate;
    }

    @Column(name = "REAL_END_DATE")
    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
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

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="PROJECT_ID")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    @Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Column(name="UPDATER")
	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

    @Column(name="MANUAL")
    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    @Transient
    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    @Transient
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Transient
    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
}
