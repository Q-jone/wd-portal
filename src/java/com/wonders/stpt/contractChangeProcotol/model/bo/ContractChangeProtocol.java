package com.wonders.stpt.contractChangeProcotol.model.bo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/4/2.
 */
@Entity
@javax.persistence.Table(name = "T_CONTRACT_CHANGE_PROTOCOL", schema = "STPT", catalog = "")
public class ContractChangeProtocol {
    private String id;

    @Id
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String originalContractName;

    @Basic
    @javax.persistence.Column(name = "ORIGINAL_CONTRACT_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getOriginalContractName() {
        return originalContractName;
    }

    public void setOriginalContractName(String originalContractName) {
        this.originalContractName = originalContractName;
    }

    private BigDecimal changePrice;

    @Basic
    @javax.persistence.Column(name = "CHANGE_PRICE", nullable = true, insertable = true, updatable = true, precision = 6)
    public BigDecimal getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }

    private String changeItemType;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_TYPE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getChangeItemType() {
        return changeItemType;
    }

    public void setChangeItemType(String changeItemType) {
        this.changeItemType = changeItemType;
    }

    private String dealPerson;

    @Basic
    @javax.persistence.Column(name = "DEAL_PERSON", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDealPerson() {
        return dealPerson;
    }

    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }

    private String regPerson;

    @Basic
    @javax.persistence.Column(name = "REG_PERSON", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRegPerson() {
        return regPerson;
    }

    public void setRegPerson(String regPerson) {
        this.regPerson = regPerson;
    }

    private String regLoginName;

    @Basic
    @javax.persistence.Column(name = "REG_LOGIN_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRegLoginName() {
        return regLoginName;
    }

    public void setRegLoginName(String regLoginName) {
        this.regLoginName = regLoginName;
    }

    private String regTime;

    @Basic
    @javax.persistence.Column(name = "REG_TIME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    private String passTime;

    @Basic
    @javax.persistence.Column(name = "PASS_TIME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    private String signTime;

    @Basic
    @javax.persistence.Column(name = "SIGN_TIME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    private String dealDeptSuggest;

    @Basic
    @javax.persistence.Column(name = "DEAL_DEPT_SUGGEST", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getDealDeptSuggest() {
        return dealDeptSuggest;
    }

    public void setDealDeptSuggest(String dealDeptSuggest) {
        this.dealDeptSuggest = dealDeptSuggest;
    }

    private String contractName;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    private String contractNo;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NO", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    private BigDecimal contractPrice;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_PRICE", nullable = true, insertable = true, updatable = true, precision = 6)
    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    private String oppositeCompany;

    @Basic
    @javax.persistence.Column(name = "OPPOSITE_COMPANY", nullable = true, insertable = true, updatable = true, length = 200)
    public String getOppositeCompany() {
        return oppositeCompany;
    }

    public void setOppositeCompany(String oppositeCompany) {
        this.oppositeCompany = oppositeCompany;
    }

    private String execPeriodStart;

    @Basic
    @javax.persistence.Column(name = "EXEC_PERIOD_START", nullable = true, insertable = true, updatable = true, length = 200)
    public String getExecPeriodStart() {
        return execPeriodStart;
    }

    public void setExecPeriodStart(String execPeriodStart) {
        this.execPeriodStart = execPeriodStart;
    }

    private String execPeriodEnd;

    @Basic
    @javax.persistence.Column(name = "EXEC_PERIOD_END", nullable = true, insertable = true, updatable = true, length = 200)
    public String getExecPeriodEnd() {
        return execPeriodEnd;
    }

    public void setExecPeriodEnd(String execPeriodEnd) {
        this.execPeriodEnd = execPeriodEnd;
    }

    private String attach;

    @Basic
    @javax.persistence.Column(name = "ATTACH", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    private String process;

    @Basic
    @javax.persistence.Column(name = "PROCESS", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    private String incidect;

    @Basic
    @javax.persistence.Column(name = "INCIDECT", nullable = true, insertable = true, updatable = true, length = 50)
    public String getIncidect() {
        return incidect;
    }

    public void setIncidect(String incidect) {
        this.incidect = incidect;
    }

    private String removed;

    @Basic
    @javax.persistence.Column(name = "REMOVED", nullable = true, insertable = true, updatable = true, length = 1)
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    private String flag;

    @Basic
    @javax.persistence.Column(name = "FLAG", nullable = true, insertable = true, updatable = true, length = 1)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String typeid;

    @Basic
    @javax.persistence.Column(name = "TYPEID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    private String operateTime;

    @Basic
    @javax.persistence.Column(name = "OPERATE_TIME", nullable = true, insertable = true, updatable = true, length = 20)
    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    private String memo;

    @Basic
    @javax.persistence.Column(name = "MEMO", nullable = true, insertable = true, updatable = true)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String dept;

    @Basic
    @javax.persistence.Column(name = "DEPT", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    private String deptId;

    @Basic
    @javax.persistence.Column(name = "DEPT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    private String contractId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    private String corporationId;

    @Basic
    @javax.persistence.Column(name = "CORPORATION_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    private String selfNo;

    @Basic
    @javax.persistence.Column(name = "SELF_NO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSelfNo() {
        return selfNo;
    }

    public void setSelfNo(String selfNo) {
        this.selfNo = selfNo;
    }

    private String contractType;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    private String contractNoFj;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NO_FJ", nullable = true, insertable = true, updatable = true, length = 100)
    public String getContractNoFj() {
        return contractNoFj;
    }

    public void setContractNoFj(String contractNoFj) {
        this.contractNoFj = contractNoFj;
    }

    private String selfNoFj;

    @Basic
    @javax.persistence.Column(name = "SELF_NO_FJ", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSelfNoFj() {
        return selfNoFj;
    }

    public void setSelfNoFj(String selfNoFj) {
        this.selfNoFj = selfNoFj;
    }

    private String jbbmSuggest;

    @Basic
    @javax.persistence.Column(name = "JBBM_SUGGEST", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getJbbmSuggest() {
        return jbbmSuggest;
    }

    public void setJbbmSuggest(String jbbmSuggest) {
        this.jbbmSuggest = jbbmSuggest;
    }

    private String changeItemContent;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_CONTENT", nullable = true, insertable = true, updatable = true)
    public String getChangeItemContent() {
        return changeItemContent;
    }

    public void setChangeItemContent(String changeItemContent) {
        this.changeItemContent = changeItemContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractChangeProtocol that = (ContractChangeProtocol) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (originalContractName != null ? !originalContractName.equals(that.originalContractName) : that.originalContractName != null)
            return false;
        if (changePrice != null ? !changePrice.equals(that.changePrice) : that.changePrice != null) return false;
        if (changeItemType != null ? !changeItemType.equals(that.changeItemType) : that.changeItemType != null)
            return false;
        if (dealPerson != null ? !dealPerson.equals(that.dealPerson) : that.dealPerson != null) return false;
        if (regPerson != null ? !regPerson.equals(that.regPerson) : that.regPerson != null) return false;
        if (regLoginName != null ? !regLoginName.equals(that.regLoginName) : that.regLoginName != null) return false;
        if (regTime != null ? !regTime.equals(that.regTime) : that.regTime != null) return false;
        if (passTime != null ? !passTime.equals(that.passTime) : that.passTime != null) return false;
        if (signTime != null ? !signTime.equals(that.signTime) : that.signTime != null) return false;
        if (dealDeptSuggest != null ? !dealDeptSuggest.equals(that.dealDeptSuggest) : that.dealDeptSuggest != null)
            return false;
        if (contractName != null ? !contractName.equals(that.contractName) : that.contractName != null) return false;
        if (contractNo != null ? !contractNo.equals(that.contractNo) : that.contractNo != null) return false;
        if (contractPrice != null ? !contractPrice.equals(that.contractPrice) : that.contractPrice != null)
            return false;
        if (oppositeCompany != null ? !oppositeCompany.equals(that.oppositeCompany) : that.oppositeCompany != null)
            return false;
        if (execPeriodStart != null ? !execPeriodStart.equals(that.execPeriodStart) : that.execPeriodStart != null)
            return false;
        if (execPeriodEnd != null ? !execPeriodEnd.equals(that.execPeriodEnd) : that.execPeriodEnd != null)
            return false;
        if (attach != null ? !attach.equals(that.attach) : that.attach != null) return false;
        if (process != null ? !process.equals(that.process) : that.process != null) return false;
        if (incidect != null ? !incidect.equals(that.incidect) : that.incidect != null) return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (typeid != null ? !typeid.equals(that.typeid) : that.typeid != null) return false;
        if (operateTime != null ? !operateTime.equals(that.operateTime) : that.operateTime != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (dept != null ? !dept.equals(that.dept) : that.dept != null) return false;
        if (deptId != null ? !deptId.equals(that.deptId) : that.deptId != null) return false;
        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
        if (corporationId != null ? !corporationId.equals(that.corporationId) : that.corporationId != null)
            return false;
        if (selfNo != null ? !selfNo.equals(that.selfNo) : that.selfNo != null) return false;
        if (contractType != null ? !contractType.equals(that.contractType) : that.contractType != null) return false;
        if (contractNoFj != null ? !contractNoFj.equals(that.contractNoFj) : that.contractNoFj != null) return false;
        if (selfNoFj != null ? !selfNoFj.equals(that.selfNoFj) : that.selfNoFj != null) return false;
        if (jbbmSuggest != null ? !jbbmSuggest.equals(that.jbbmSuggest) : that.jbbmSuggest != null) return false;
        if (changeItemContent != null ? !changeItemContent.equals(that.changeItemContent) : that.changeItemContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (originalContractName != null ? originalContractName.hashCode() : 0);
        result = 31 * result + (changePrice != null ? changePrice.hashCode() : 0);
        result = 31 * result + (changeItemType != null ? changeItemType.hashCode() : 0);
        result = 31 * result + (dealPerson != null ? dealPerson.hashCode() : 0);
        result = 31 * result + (regPerson != null ? regPerson.hashCode() : 0);
        result = 31 * result + (regLoginName != null ? regLoginName.hashCode() : 0);
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        result = 31 * result + (passTime != null ? passTime.hashCode() : 0);
        result = 31 * result + (signTime != null ? signTime.hashCode() : 0);
        result = 31 * result + (dealDeptSuggest != null ? dealDeptSuggest.hashCode() : 0);
        result = 31 * result + (contractName != null ? contractName.hashCode() : 0);
        result = 31 * result + (contractNo != null ? contractNo.hashCode() : 0);
        result = 31 * result + (contractPrice != null ? contractPrice.hashCode() : 0);
        result = 31 * result + (oppositeCompany != null ? oppositeCompany.hashCode() : 0);
        result = 31 * result + (execPeriodStart != null ? execPeriodStart.hashCode() : 0);
        result = 31 * result + (execPeriodEnd != null ? execPeriodEnd.hashCode() : 0);
        result = 31 * result + (attach != null ? attach.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (incidect != null ? incidect.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (typeid != null ? typeid.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (contractId != null ? contractId.hashCode() : 0);
        result = 31 * result + (corporationId != null ? corporationId.hashCode() : 0);
        result = 31 * result + (selfNo != null ? selfNo.hashCode() : 0);
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (contractNoFj != null ? contractNoFj.hashCode() : 0);
        result = 31 * result + (selfNoFj != null ? selfNoFj.hashCode() : 0);
        result = 31 * result + (jbbmSuggest != null ? jbbmSuggest.hashCode() : 0);
        result = 31 * result + (changeItemContent != null ? changeItemContent.hashCode() : 0);
        return result;
    }

    //    private String tasksId;
//
//    @Transient
//    public String getTasksId() {
//        return tasksId;
//    }
//
//    public void setTasksId(String tasksId) {
//        this.tasksId = tasksId;
//    }
}
