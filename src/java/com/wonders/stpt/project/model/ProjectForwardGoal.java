package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/16.年度推进目标实体
 */


@Entity
@Table(name = "T_PROJECT_FORWARD_GOAL")
public class ProjectForwardGoal {
	/**
	 * 年度推进目标主键
	 */
    private String forwardGoalId;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 年度推进目标
     */
    private String forwardGoal;
    /**
     * 逻辑删除字段(0:显示     1:删除)
     */
    private String removed;
    /**
     * 对应项目
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
    
    public ProjectForwardGoal(){
    	createTime = new Date();
        updateTime = new Date();
        removed = "0";
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "FORWARD_GOAL_ID")
    public String getForwardGoalId() {
        return forwardGoalId;
    }

    public void setForwardGoalId(String forwardGoalId) {
        this.forwardGoalId = forwardGoalId;
    }

    @Column(name = "BEGIN_DATE")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }


    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Column(name = "FORWARD_GOAL")
    public String getForwardGoal() {
        return forwardGoal;
    }

    public void setForwardGoal(String forwardGoal) {
        this.forwardGoal = forwardGoal;
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
}
