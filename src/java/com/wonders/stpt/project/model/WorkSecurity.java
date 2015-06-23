package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/25.
 */

@Entity
@Table(name = "T_WORK_SECURITY")
public class WorkSecurity {
    private String workSecurityId;
    private String sysName;
    private String category;
    private String important;
    private String sysLevel;
    private Integer excuteQuantity;
    private String department;
    private Date planBeginDate;
    private Date planEndDate;
    private Date realBeginDate;
    private Date realEndDate;
    private String memo;
    private String confirm;

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

    private Integer year;


    public WorkSecurity() {
        removed = "0";
        confirm = "0";
        createTime = new Date();
        updateTime = new Date();
        excuteQuantity=0;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "WORK_SECURITY_ID")
    public String getWorkSecurityId() {
        return workSecurityId;
    }

    public void setWorkSecurityId(String workSecurityId) {
        this.workSecurityId = workSecurityId;
    }

    @Column(name = "SYS_NAME")
    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "IMPORTANT")
    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    @Column(name = "SYS_LEVEL")
    public String getSysLevel() {
        return sysLevel;
    }

    public void setSysLevel(String sysLevel) {
        this.sysLevel = sysLevel;
    }

    @Column(name = "EXCUTE_QUANTITY")
    public Integer getExcuteQuantity() {
        return excuteQuantity;
    }

    public void setExcuteQuantity(Integer excuteQuantity) {
        this.excuteQuantity = excuteQuantity;
    }

    @Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    @Column(name = "CONFIRM")
    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Transient
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    /**@Transient的作用 :
    	用于注释pojo对象中的属性，被注释的属性将成为短暂的，不会持久化到数据库的“短暂”属性。*/
    @Transient
    public Integer getDelay() {
        Integer value = getDelayed();
        if (value == 1) {
            return (int) ((realEndDate.getTime() - this.planEndDate.getTime()) / (24 * 60 * 60 * 1000));
        } else
            return 0;
    }


    @Transient
    public Integer getDelayed() {
        if (this.realEndDate != null) {
            return realEndDate.compareTo(planEndDate);
        } else
            return null;
    }
}
