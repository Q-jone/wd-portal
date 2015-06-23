package com.wonders.stpt.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2014/6/27.
 */
@Entity
@Table(name = "T_TERMINAL_DEVICE")
public class TerminalDevice {
    private String terminalDeviceId;
    private String department;

    private Date webServicePlanBeginDate;
    private Date webServicePlanEndDate;
    private String webServiceStatus;

    private Date netNewPlanBeginDate;
    private Date netNewPlanEndDate;
    private String netNewStatus;

    private Date netOldPlanBeginDate;
    private Date netOldPlanEndDate;
    private String netOldStatus;

    private Date isolationPlanBeginDate;
    private Date isolationPlanEndDate;
    private String isolationStatus;

    private Date wifiPlanBeginDate;
    private Date wifiPlanEndDate;
    private String wifiStatus;

    private Date mgrPlanBeginDate;
    private Date mgrPlanEndDate;
    private String mgrStatus;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "TERMINAL_DEVICE_ID")
    public String getTerminalDeviceId() {
        return terminalDeviceId;
    }

    public void setTerminalDeviceId(String terminalDeviceId) {
        this.terminalDeviceId = terminalDeviceId;
    }
    @Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "WEB_SERVICE_PLAN_BEGIN_DATE")
    public Date getWebServicePlanBeginDate() {
        return webServicePlanBeginDate;
    }

    public void setWebServicePlanBeginDate(Date webServicePlanBeginDate) {
        this.webServicePlanBeginDate = webServicePlanBeginDate;
    }

    @Column(name = "WEB_SERVICE_PLAN_END_DATE")
    public Date getWebServicePlanEndDate() {
        return webServicePlanEndDate;
    }

    public void setWebServicePlanEndDate(Date webServicePlanEndDate) {
        this.webServicePlanEndDate = webServicePlanEndDate;
    }

    @Column(name = "WEB_SERVICE_STATUS")
    public String getWebServiceStatus() {
        return webServiceStatus;
    }

    public void setWebServiceStatus(String webServiceStatus) {
        this.webServiceStatus = webServiceStatus;
    }

    @Column(name = "NET_NEW_PLAN_BEGIN_DATE")
    public Date getNetNewPlanBeginDate() {
        return netNewPlanBeginDate;
    }

    public void setNetNewPlanBeginDate(Date netNewPlanBeginDate) {
        this.netNewPlanBeginDate = netNewPlanBeginDate;
    }

    @Column(name = "NET_NEW_PLAN_END_DATE")
    public Date getNetNewPlanEndDate() {
        return netNewPlanEndDate;
    }

    public void setNetNewPlanEndDate(Date netNewPlanEndDate) {
        this.netNewPlanEndDate = netNewPlanEndDate;
    }

    @Column(name = "NET_NEW_STATUS")
    public String getNetNewStatus() {
        return netNewStatus;
    }

    public void setNetNewStatus(String netNewStatus) {
        this.netNewStatus = netNewStatus;
    }

    @Column(name = "NET_OLD_PLAN_BEGIN_DATE")
    public Date getNetOldPlanBeginDate() {
        return netOldPlanBeginDate;
    }

    public void setNetOldPlanBeginDate(Date netOldPlanBeginDate) {
        this.netOldPlanBeginDate = netOldPlanBeginDate;
    }

    @Column(name = "NET_OLD_PLAN_END_DATE")
    public Date getNetOldPlanEndDate() {
        return netOldPlanEndDate;
    }

    public void setNetOldPlanEndDate(Date netOldPlanEndDate) {
        this.netOldPlanEndDate = netOldPlanEndDate;
    }

    @Column(name = "NET_OLD_STATUS")
    public String getNetOldStatus() {
        return netOldStatus;
    }

    public void setNetOldStatus(String netOldStatus) {
        this.netOldStatus = netOldStatus;
    }

    @Column(name = "ISOLATION_PLAN_BEGIN_DATE")
    public Date getIsolationPlanBeginDate() {
        return isolationPlanBeginDate;
    }

    public void setIsolationPlanBeginDate(Date isolationPlanBeginDate) {
        this.isolationPlanBeginDate = isolationPlanBeginDate;
    }

    @Column(name = "ISOLATION_PLAN_END_DATE")
    public Date getIsolationPlanEndDate() {
        return isolationPlanEndDate;
    }

    public void setIsolationPlanEndDate(Date isolationPlanEndDate) {
        this.isolationPlanEndDate = isolationPlanEndDate;
    }

    @Column(name = "ISOLATION_STATUS")
    public String getIsolationStatus() {
        return isolationStatus;
    }

    public void setIsolationStatus(String isolationStatus) {
        this.isolationStatus = isolationStatus;
    }

    @Column(name = "WIFI_PLAN_BEGIN_DATE")
    public Date getWifiPlanBeginDate() {
        return wifiPlanBeginDate;
    }

    public void setWifiPlanBeginDate(Date wifiPlanBeginDate) {
        this.wifiPlanBeginDate = wifiPlanBeginDate;
    }

    @Column(name = "WIFI_PLAN_END_DATE")
    public Date getWifiPlanEndDate() {
        return wifiPlanEndDate;
    }

    public void setWifiPlanEndDate(Date wifiPlanEndDate) {
        this.wifiPlanEndDate = wifiPlanEndDate;
    }

    @Column(name = "WIFI_STATUS")
    public String getWifiStatus() {
        return wifiStatus;
    }

    public void setWifiStatus(String wifiStatus) {
        this.wifiStatus = wifiStatus;
    }

    @Column(name = "MGR_PLAN_BEGIN_DATE")
    public Date getMgrPlanBeginDate() {
        return mgrPlanBeginDate;
    }

    public void setMgrPlanBeginDate(Date mgrPlanBeginDate) {
        this.mgrPlanBeginDate = mgrPlanBeginDate;
    }

    @Column(name = "MGR_PLAN_END_DATE")
    public Date getMgrPlanEndDate() {
        return mgrPlanEndDate;
    }

    public void setMgrPlanEndDate(Date mgrPlanEndDate) {
        this.mgrPlanEndDate = mgrPlanEndDate;
    }
    @Column(name = "MGR_STATUS")
    public String getMgrStatus() {
        return mgrStatus;
    }

    public void setMgrStatus(String mgrStatus) {
        this.mgrStatus = mgrStatus;
    }
}
