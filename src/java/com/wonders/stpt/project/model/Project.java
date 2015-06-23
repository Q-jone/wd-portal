package com.wonders.stpt.project.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 204/6/6.项目基本表
 */
@Entity
@Table(name = "T_PROJECT")
public class Project {
	/**
	 * 项目表主键
	 */
    private String projectId;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 项目编号
     */
    private String projectNumber;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目描述
     */
    private String projectDiscription;
    /**
     * 等保定级
     */
    private String level;
    /**
     * 计划建成时间
     */
    private Date planActivateTime;
    /**
     * 总投资估算
     */
    private String investEstimate;
    /**
     * 责任单位(建议实施主体)
     */
    private String department;
    /**
     * 分管领导
     */
    private String leader;
    /**
     * 责任人
     */
    private String responsible;
    /**
     * 项目目标
     */
    private String projectGoal;
    /**
     * 年度推进目标
     */
    private String projectForwardGoals;
    /**
     * 项目状态
     */
    private String projectStatus;
    /**
     * 备注
     */
    private String memo;
    /**
     * 是否结束(Y/N)
     */
    private String finish;
    /**
     * 逻辑删除字段(0:显示     :删除)
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
    /**
     * 项目包含计划列表
     */
    @Expose(serialize=false)
    private List<ProjectPlan> projectPlans;


    private ProjectPlan currentPlan;

    public Project() {
        createTime = new Date();
        updateTime = new Date();
        removed = "0";
        finish="0";
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "PROJECT_ID")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    @Column(name = "PROJECT_NAME")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "LEADER")
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Column(name = "RESPONSIBLE")
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    @Column(name = "PROJECT_GOAL")
    public String getProjectGoal() {
        return projectGoal;
    }

    public void setProjectGoal(String projectGoal) {
        this.projectGoal = projectGoal;
    }

    @Column(name = "PROJECT_STATUS")
    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
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

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "project")
    public List<ProjectPlan> getProjectPlans() {
        return projectPlans;
    }

    public void setProjectPlans(List<ProjectPlan> projectPlans) {
        this.projectPlans = projectPlans;
    }

    @Column(name="PROJECT_FORWARD_GOALS")
    public String getProjectForwardGoals() {
		return projectForwardGoals;
	}

	public void setProjectForwardGoals(String projectForwardGoals) {
		this.projectForwardGoals = projectForwardGoals;
	}

	@Column(name = "CATEGORY")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Column(name="PROJECT_SN")
	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	@Column(name="PROJECT_DESCRIPTION")
	public String getProjectDiscription() {
		return projectDiscription;
	}

	public void setProjectDiscription(String projectDiscription) {
		this.projectDiscription = projectDiscription;
	}
	
	@Column(name="PROJECT_LEVEL")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Column(name="PLAN_COMPLETED_DATE")
	public Date getPlanActivateTime() {
		return planActivateTime;
	}

	public void setPlanActivateTime(Date planActivateTime) {
		this.planActivateTime = planActivateTime;
	}
	@Column(name="INVESTMENT")
	public String getInvestEstimate() {
		return investEstimate;
	}

	public void setInvestEstimate(String investEstimate) {
		this.investEstimate = investEstimate;
	}
	@Column(name="FINISH")
	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

    @Transient
    public ProjectPlan getCurrentPlan() {
        return currentPlan;
    }

    public void setCurrentPlan(ProjectPlan currentPlan) {
        this.currentPlan = currentPlan;
    }


}
