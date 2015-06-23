package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/26.
 */
@Entity
@Table(name = "T_RISK_MANAGE")
public class RiskManage {
    private String riskManageId;
    private String department;
    private String riskFrom;
    private Date discovery;
    private String category;
    private String riskLevel;
    private String plan;
    private String dateLimit;
    private String riskInfo;
    private String trackInfo;
    private String sysName;
    private Date trackDate;

    private Date discoveryBeginDate;
    private Date discoveryEndDate;
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


    public RiskManage() {
        removed = "0";
        createTime = new Date();
        updateTime = new Date();
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "RISK_MANAGE_ID")
    public String getRiskManageId() {
        return riskManageId;
    }

    public void setRiskManageId(String riskManageId) {
        this.riskManageId = riskManageId;
    }

    @Column(name = "SYS_NAME")
    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    @Column(name = "RISK_INFO")
    public String getRiskInfo() {
        return riskInfo;
    }

    public void setRiskInfo(String riskInfo) {
        this.riskInfo = riskInfo;
    }

    @Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "RISK_FROM")
    public String getRiskFrom() {
        return riskFrom;
    }

    public void setRiskFrom(String riskFrom) {
        this.riskFrom = riskFrom;
    }

    @Column(name = "DISCOVERY")
    public Date getDiscovery() {
        return discovery;
    }

    public void setDiscovery(Date discovery) {
        this.discovery = discovery;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "RISK_LEVEL")
    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Column(name = "PLAN")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Column(name = "DATE_LIMIT")
    public String getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(String dateLimit) {
        this.dateLimit = dateLimit;
    }

    @Column(name = "TRACK_INFO")
    public String getTrackInfo() {
        return trackInfo;
    }

    public void setTrackInfo(String trackInfo) {
        this.trackInfo = trackInfo;
    }

    @Column(name = "TRACK_DATE")
    public Date getTrackDate() {
        return trackDate;
    }

    public void setTrackDate(Date trackDate) {
        this.trackDate = trackDate;
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

//注释为非数据库字段
    @Transient
    public Date getDiscoveryBeginDate() {
        return discoveryBeginDate;
    }

    public void setDiscoveryBeginDate(Date discoveryBeginDate) {
        this.discoveryBeginDate = discoveryBeginDate;
    }

    @Transient
    public Date getDiscoveryEndDate() {
        return discoveryEndDate;
    }

    public void setDiscoveryEndDate(Date discoveryEndDate) {
        this.discoveryEndDate = discoveryEndDate;
    }
}
