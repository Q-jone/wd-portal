package com.wonders.stpt.contractAgreement.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 01052621 on 2015/4/3.
 */
@Entity
@Table(name = "T_CONTRACT_CHANGE_AGREEMENT")
public class ContractAgreement {
    private String taskId;
    private String regTimeEnd;
    private String changePriceEnd;

    @Transient
    public String getChangePriceEnd() { return changePriceEnd;}

    public void setChangePriceEnd(String changePriceEnd) { this.changePriceEnd = changePriceEnd; }

    @Transient
    public String getRegTimeEnd() {
        return regTimeEnd;
    }

    public void setRegTimeEnd(String regTimeEnd) {
        this.regTimeEnd = regTimeEnd;
    }

    @Transient
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    private String id;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String originalContractName;

    @Column(name = "ORIGINAL_CONTRACT_NAME")
    public String getOriginalContractName() {
        return originalContractName;
    }

    public void setOriginalContractName(String originalContractName) {
        this.originalContractName = originalContractName;
    }

    private BigDecimal changePrice;

    @Column(name = "CHANGE_PRICE")
    public BigDecimal getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }

    private String changeItemType;

    @Column(name = "CHANGE_ITEM_TYPE")
    public String getChangeItemType() {
        return changeItemType;
    }

    public void setChangeItemType(String changeItemType) {
        this.changeItemType = changeItemType;
    }

    private String contractName;

    @Column(name = "CONTRACT_NAME")
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    private String contractNo;

    @Column(name = "CONTRACT_NO")
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    private String selfNo;

    @Column(name = "SELF_NO")
    public String getSelfNo() {
        return selfNo;
    }

    public void setSelfNo(String selfNo) {
        this.selfNo = selfNo;
    }

    private BigDecimal contractPrice;

    @Column(name = "CONTRACT_PRICE")
    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    private String oppositeCompany;

    @Column(name = "OPPOSITE_COMPANY")
    public String getOppositeCompany() {
        return oppositeCompany;
    }

    public void setOppositeCompany(String oppositeCompany) {
        this.oppositeCompany = oppositeCompany;
    }

    private String execPeriodStart;

    @Column(name = "EXEC_PERIOD_START")
    public String getExecPeriodStart() {
        return execPeriodStart;
    }

    public void setExecPeriodStart(String execPeriodStart) {
        this.execPeriodStart = execPeriodStart;
    }

    private String execPeriodEnd;

    @Column(name = "EXEC_PERIOD_END")
    public String getExecPeriodEnd() {
        return execPeriodEnd;
    }

    public void setExecPeriodEnd(String execPeriodEnd) {
        this.execPeriodEnd = execPeriodEnd;
    }

    private String dealPerson;

    @Column(name = "DEAL_PERSON")
    public String getDealPerson() {
        return dealPerson;
    }

    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }

    private String regPerson;

    @Column(name = "REG_PERSON")
    public String getRegPerson() {
        return regPerson;
    }

    public void setRegPerson(String regPerson) {
        this.regPerson = regPerson;
    }

    private String signTime;

    @Column(name = "SIGN_TIME")
    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    private String dealDeptSuggest;

    @Column(name = "DEAL_DEPT_SUGGEST")
    public String getDealDeptSuggest() {
        return dealDeptSuggest;
    }

    public void setDealDeptSuggest(String dealDeptSuggest) {
        this.dealDeptSuggest = dealDeptSuggest;
    }

    private String regLoginName;

    @Column(name = "REG_LOGIN_NAME")
    public String getRegLoginName() {
        return regLoginName;
    }

    public void setRegLoginName(String regLoginName) {
        this.regLoginName = regLoginName;
    }

    private String regTime;

    @Column(name = "REG_TIME")
    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    private String passTime;

    @Column(name = "PASS_TIME")
    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    private String attach;

    @Column(name = "ATTACH")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    private String process;

    @Column(name = "PROCESS")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    private String incidect;

    @Column(name = "INCIDECT")
    public String getIncidect() {
        return incidect;
    }

    public void setIncidect(String incidect) {
        this.incidect = incidect;
    }

    private String removed;

    @Column(name = "REMOVED")
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    private String flag;

    @Column(name = "FLAG")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String typeid;

    @Column(name = "TYPEID")
    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    private String operateTime;

    @Column(name = "OPERATE_TIME")
    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    private String memo;

    @Column(name = "MEMO")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String dept;

    @Column(name = "DEPT")
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    private String deptId;

    @Column(name = "DEPT_ID")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    private String contractId;

    @Column(name = "CONTRACT_ID")
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    private String corporationId;

    @Column(name = "CORPORATION_ID")
    public String getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    private String changeItemContent;

    @Column(name = "CHANGE_ITEM_CONTENT")
    public String getChangeItemContent() {
        return changeItemContent;
    }

    public void setChangeItemContent(String changeItemContent) {
        this.changeItemContent = changeItemContent;
    }

    private String contractType;

    @Column(name = "CONTRACT_TYPE")
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    private String contractNoFj;

    @Column(name = "CONTRACT_NO_FJ")
    public String getContractNoFj() {
        return contractNoFj;
    }

    public void setContractNoFj(String contractNoFj) {
        this.contractNoFj = contractNoFj;
    }

    private String selfNoFj;

    @Column(name = "SELF_NO_FJ")
    public String getSelfNoFj() {
        return selfNoFj;
    }

    public void setSelfNoFj(String selfNoFj) {
        this.selfNoFj = selfNoFj;
    }

    private String jbbmSuggest;

    @Column(name = "JBBM_SUGGEST")
    public String getJbbmSuggest() {
        return jbbmSuggest;
    }

    public void setJbbmSuggest(String jbbmSuggest) {
        this.jbbmSuggest = jbbmSuggest;
    }

    private String signCopId;

    @Column(name = "SIGN_COP_ID")
    public String getSignCopId() {
        return signCopId;
    }

    public void setSignCopId(String signCopId) {
        this.signCopId = signCopId;
    }

    private String lawReason;

    @Column(name = "LAW_REASON")
    public String getLawReason() {
        return lawReason;
    }

    public void setLawReason(String lawReason) {
        this.lawReason = lawReason;
    }

    private String changeResult;

    @Column(name = "CHANGE_RESULT")
    public String getChangeResult() {
        return changeResult;
    }

    public void setChangeResult(String changeResult) {
        this.changeResult = changeResult;
    }

    private String supervisionSuggest;

    @Column(name = "SUPERVISION_SUGGEST")
    public String getSupervisionSuggest() {
        return supervisionSuggest;
    }

    public void setSupervisionSuggest(String supervisionSuggest) {
        this.supervisionSuggest = supervisionSuggest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractAgreement that = (ContractAgreement) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (originalContractName != null ? !originalContractName.equals(that.originalContractName) : that.originalContractName != null)
            return false;
        if (changePrice != null ? !changePrice.equals(that.changePrice) : that.changePrice != null) return false;
        if (changeItemType != null ? !changeItemType.equals(that.changeItemType) : that.changeItemType != null)
            return false;
        if (contractName != null ? !contractName.equals(that.contractName) : that.contractName != null) return false;
        if (contractNo != null ? !contractNo.equals(that.contractNo) : that.contractNo != null) return false;
        if (selfNo != null ? !selfNo.equals(that.selfNo) : that.selfNo != null) return false;
        if (contractPrice != null ? !contractPrice.equals(that.contractPrice) : that.contractPrice != null)
            return false;
        if (oppositeCompany != null ? !oppositeCompany.equals(that.oppositeCompany) : that.oppositeCompany != null)
            return false;
        if (execPeriodStart != null ? !execPeriodStart.equals(that.execPeriodStart) : that.execPeriodStart != null)
            return false;
        if (execPeriodEnd != null ? !execPeriodEnd.equals(that.execPeriodEnd) : that.execPeriodEnd != null)
            return false;
        if (dealPerson != null ? !dealPerson.equals(that.dealPerson) : that.dealPerson != null) return false;
        if (regPerson != null ? !regPerson.equals(that.regPerson) : that.regPerson != null) return false;
        if (signTime != null ? !signTime.equals(that.signTime) : that.signTime != null) return false;
        if (dealDeptSuggest != null ? !dealDeptSuggest.equals(that.dealDeptSuggest) : that.dealDeptSuggest != null)
            return false;
        if (regLoginName != null ? !regLoginName.equals(that.regLoginName) : that.regLoginName != null) return false;
        if (regTime != null ? !regTime.equals(that.regTime) : that.regTime != null) return false;
        if (passTime != null ? !passTime.equals(that.passTime) : that.passTime != null) return false;
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
        if (changeItemContent != null ? !changeItemContent.equals(that.changeItemContent) : that.changeItemContent != null)
            return false;
        if (contractType != null ? !contractType.equals(that.contractType) : that.contractType != null) return false;
        if (contractNoFj != null ? !contractNoFj.equals(that.contractNoFj) : that.contractNoFj != null) return false;
        if (selfNoFj != null ? !selfNoFj.equals(that.selfNoFj) : that.selfNoFj != null) return false;
        if (jbbmSuggest != null ? !jbbmSuggest.equals(that.jbbmSuggest) : that.jbbmSuggest != null) return false;
        if (signCopId != null ? !signCopId.equals(that.signCopId) : that.signCopId != null) return false;
        if (lawReason != null ? !lawReason.equals(that.lawReason) : that.lawReason != null) return false;
        if (changeResult != null ? !changeResult.equals(that.changeResult) : that.changeResult != null) return false;
        if (supervisionSuggest != null ? !supervisionSuggest.equals(that.supervisionSuggest) : that.supervisionSuggest != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (originalContractName != null ? originalContractName.hashCode() : 0);
        result = 31 * result + (changePrice != null ? changePrice.hashCode() : 0);
        result = 31 * result + (changeItemType != null ? changeItemType.hashCode() : 0);
        result = 31 * result + (contractName != null ? contractName.hashCode() : 0);
        result = 31 * result + (contractNo != null ? contractNo.hashCode() : 0);
        result = 31 * result + (selfNo != null ? selfNo.hashCode() : 0);
        result = 31 * result + (contractPrice != null ? contractPrice.hashCode() : 0);
        result = 31 * result + (oppositeCompany != null ? oppositeCompany.hashCode() : 0);
        result = 31 * result + (execPeriodStart != null ? execPeriodStart.hashCode() : 0);
        result = 31 * result + (execPeriodEnd != null ? execPeriodEnd.hashCode() : 0);
        result = 31 * result + (dealPerson != null ? dealPerson.hashCode() : 0);
        result = 31 * result + (regPerson != null ? regPerson.hashCode() : 0);
        result = 31 * result + (signTime != null ? signTime.hashCode() : 0);
        result = 31 * result + (dealDeptSuggest != null ? dealDeptSuggest.hashCode() : 0);
        result = 31 * result + (regLoginName != null ? regLoginName.hashCode() : 0);
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        result = 31 * result + (passTime != null ? passTime.hashCode() : 0);
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
        result = 31 * result + (changeItemContent != null ? changeItemContent.hashCode() : 0);
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (contractNoFj != null ? contractNoFj.hashCode() : 0);
        result = 31 * result + (selfNoFj != null ? selfNoFj.hashCode() : 0);
        result = 31 * result + (jbbmSuggest != null ? jbbmSuggest.hashCode() : 0);
        result = 31 * result + (signCopId != null ? signCopId.hashCode() : 0);
        result = 31 * result + (lawReason != null ? lawReason.hashCode() : 0);
        result = 31 * result + (changeResult != null ? changeResult.hashCode() : 0);
        result = 31 * result + (supervisionSuggest != null ? supervisionSuggest.hashCode() : 0);
        return result;
    }
}
