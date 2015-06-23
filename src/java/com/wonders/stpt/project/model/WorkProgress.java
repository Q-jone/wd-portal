package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/23.历史表
 */
@Entity
@Table(name = "T_WORK_PROGRESS")
public class WorkProgress {
    private String workProgressId;
    private String workSecludeId;
    /**
     * 推进情况
     */
    private String progress;
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

    public WorkProgress(){

        removed = "0";
        createTime = new Date();
        updateTime = new Date();
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "WORK_PROGRESS_ID")
    public String getWorkProgressId() {
        return workProgressId;
    }

    public void setWorkProgressId(String workProgressId) {
        this.workProgressId = workProgressId;
    }

    @Column(name = "WORK_SECLUDE_ID")
    public String getWorkSecludeId() {
        return workSecludeId;
    }

    public void setWorkSecludeId(String workSecludeId) {
        this.workSecludeId = workSecludeId;
    }

    @Column(name = "PROGRESS")
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
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


}
