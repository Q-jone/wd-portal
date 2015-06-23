package com.wonders.stpt.contractItem.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * Created by 01052621 on 2015/4/3.
 */
@Entity
@javax.persistence.Table(name = "T_CONTRACT_CHANGE_ITEM", schema = "STPT", catalog = "")
public class ContractItem {
    private String regTimeEnd;
    private String taskId;

    @Transient
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Transient
    public String getRegTimeEnd() {
        return regTimeEnd;
    }

    public void setRegTimeEnd(String regTimeEnd) {
        this.regTimeEnd = regTimeEnd;
    }



    private String id;

    @Id
    @javax.persistence.Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String changeContractNo;

    @Basic
    @javax.persistence.Column(name = "CHANGE_CONTRACT_NO")
    public String getChangeContractNo() {
        return changeContractNo;
    }

    public void setChangeContractNo(String changeContractNo) {
        this.changeContractNo = changeContractNo;
    }

    private String changeSerialNo;

    @Basic
    @javax.persistence.Column(name = "CHANGE_SERIAL_NO")
    public String getChangeSerialNo() {
        return changeSerialNo;
    }

    public void setChangeSerialNo(String changeSerialNo) {
        this.changeSerialNo = changeSerialNo;
    }

    private String changePutForword;

    @Basic
    @javax.persistence.Column(name = "CHANGE_PUT_FORWORD")
    public String getChangePutForword() {
        return changePutForword;
    }

    public void setChangePutForword(String changePutForword) {
        this.changePutForword = changePutForword;
    }

    private String changeItemType1;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_TYPE1")
    public String getChangeItemType1() {
        return changeItemType1;
    }

    public void setChangeItemType1(String changeItemType1) {
        this.changeItemType1 = changeItemType1;
    }

    private String changeItemType2;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_TYPE2")
    public String getChangeItemType2() {
        return changeItemType2;
    }

    public void setChangeItemType2(String changeItemType2) {
        this.changeItemType2 = changeItemType2;
    }

    private String changeItemDescription;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_DESCRIPTION")
    public String getChangeItemDescription() {
        return changeItemDescription;
    }

    public void setChangeItemDescription(String changeItemDescription) {
        this.changeItemDescription = changeItemDescription;
    }

    private String changeReasonType;

    @Basic
    @javax.persistence.Column(name = "CHANGE_REASON_TYPE")
    public String getChangeReasonType() {
        return changeReasonType;
    }

    public void setChangeReasonType(String changeReasonType) {
        this.changeReasonType = changeReasonType;
    }

    private String changeReasonDescription;

    @Basic
    @javax.persistence.Column(name = "CHANGE_REASON_DESCRIPTION")
    public String getChangeReasonDescription() {
        return changeReasonDescription;
    }

    public void setChangeReasonDescription(String changeReasonDescription) {
        this.changeReasonDescription = changeReasonDescription;
    }

    private String changeContent;

    @Basic
    @javax.persistence.Column(name = "CHANGE_CONTENT")
    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    private BigDecimal changePrice;

    @Basic
    @javax.persistence.Column(name = "CHANGE_PRICE")
    public BigDecimal getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(BigDecimal changePrice) {
        this.changePrice = changePrice;
    }

    private String changeType;

    @Basic
    @javax.persistence.Column(name = "CHANGE_TYPE")
    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    private String changeNature;

    @Basic
    @javax.persistence.Column(name = "CHANGE_NATURE")
    public String getChangeNature() {
        return changeNature;
    }

    public void setChangeNature(String changeNature) {
        this.changeNature = changeNature;
    }

    private String changeWorkload;

    @Basic
    @javax.persistence.Column(name = "CHANGE_WORKLOAD")
    public String getChangeWorkload() {
        return changeWorkload;
    }

    public void setChangeWorkload(String changeWorkload) {
        this.changeWorkload = changeWorkload;
    }

    private String isPicture;

    @Basic
    @javax.persistence.Column(name = "IS_PICTURE")
    public String getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(String isPicture) {
        this.isPicture = isPicture;
    }

    private String leader;

    @Basic
    @javax.persistence.Column(name = "LEADER")
    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    private String process;

    @Basic
    @javax.persistence.Column(name = "PROCESS")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    private String incidect;

    @Basic
    @javax.persistence.Column(name = "INCIDECT")
    public String getIncidect() {
        return incidect;
    }

    public void setIncidect(String incidect) {
        this.incidect = incidect;
    }

    private String removed;

    @Basic
    @javax.persistence.Column(name = "REMOVED")
    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    private String flag;

    @Basic
    @javax.persistence.Column(name = "FLAG")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String typeid;

    @Basic
    @javax.persistence.Column(name = "TYPEID")
    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    private String contractName;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NAME")
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    private String attach;

    @Basic
    @javax.persistence.Column(name = "ATTACH")
    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    private String operateTime;

    @Basic
    @javax.persistence.Column(name = "OPERATE_TIME")
    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    private String dept;

    @Basic
    @javax.persistence.Column(name = "DEPT")
    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    private String deptId;

    @Basic
    @javax.persistence.Column(name = "DEPT_ID")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    private String dealPerson;

    @Basic
    @javax.persistence.Column(name = "DEAL_PERSON")
    public String getDealPerson() {
        return dealPerson;
    }

    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }

    private String regPerson;

    @Basic
    @javax.persistence.Column(name = "REG_PERSON")
    public String getRegPerson() {
        return regPerson;
    }

    public void setRegPerson(String regPerson) {
        this.regPerson = regPerson;
    }

    private String regLoginName;

    @Basic
    @javax.persistence.Column(name = "REG_LOGIN_NAME")
    public String getRegLoginName() {
        return regLoginName;
    }

    public void setRegLoginName(String regLoginName) {
        this.regLoginName = regLoginName;
    }

    private String regTime;

    @Basic
    @javax.persistence.Column(name = "REG_TIME")
    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    private String passTime;

    @Basic
    @javax.persistence.Column(name = "PASS_TIME")
    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    private String dealDeptSuggest;

    @Basic
    @javax.persistence.Column(name = "DEAL_DEPT_SUGGEST")
    public String getDealDeptSuggest() {
        return dealDeptSuggest;
    }

    public void setDealDeptSuggest(String dealDeptSuggest) {
        this.dealDeptSuggest = dealDeptSuggest;
    }

    private String contractId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_ID")
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    private String contractType;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE")
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    private Integer contractSq;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_SQ")
    public Integer getContractSq() {
        return contractSq;
    }

    public void setContractSq(Integer contractSq) {
        this.contractSq = contractSq;
    }

    private String changeSort;

    @Basic
    @javax.persistence.Column(name = "CHANGE_SORT")
    public String getChangeSort() {
        return changeSort;
    }

    public void setChangeSort(String changeSort) {
        this.changeSort = changeSort;
    }

    private String contractChangeProtocolId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_CHANGE_PROTOCOL_ID")
    public String getContractChangeProtocolId() {
        return contractChangeProtocolId;
    }

    public void setContractChangeProtocolId(String contractChangeProtocolId) {
        this.contractChangeProtocolId = contractChangeProtocolId;
    }

    private String contractChangeAgreementId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_CHANGE_AGREEMENT_ID")
    public String getContractChangeAgreementId() {
        return contractChangeAgreementId;
    }

    public void setContractChangeAgreementId(String contractChangeAgreementId) {
        this.contractChangeAgreementId = contractChangeAgreementId;
    }

    private String changeMemo;

    @Basic
    @javax.persistence.Column(name = "CHANGE_MEMO")
    public String getChangeMemo() {
        return changeMemo;
    }

    public void setChangeMemo(String changeMemo) {
        this.changeMemo = changeMemo;
    }

    private String changeContentType;

    @Basic
    @javax.persistence.Column(name = "CHANGE_CONTENT_TYPE")
    public String getChangeContentType() {
        return changeContentType;
    }

    public void setChangeContentType(String changeContentType) {
        this.changeContentType = changeContentType;
    }

    private String changePriceType;

    @Basic
    @javax.persistence.Column(name = "CHANGE_PRICE_TYPE")
    public String getChangePriceType() {
        return changePriceType;
    }

    public void setChangePriceType(String changePriceType) {
        this.changePriceType = changePriceType;
    }

    private String changePriceDescription;

    @Basic
    @javax.persistence.Column(name = "CHANGE_PRICE_DESCRIPTION")
    public String getChangePriceDescription() {
        return changePriceDescription;
    }

    public void setChangePriceDescription(String changePriceDescription) {
        this.changePriceDescription = changePriceDescription;
    }

    private String supervisionSuggest;

    @Basic
    @javax.persistence.Column(name = "SUPERVISION_SUGGEST")
    public String getSupervisionSuggest() {
        return supervisionSuggest;
    }

    public void setSupervisionSuggest(String supervisionSuggest) {
        this.supervisionSuggest = supervisionSuggest;
    }

    private String changeItemType;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_TYPE")
    public String getChangeItemType() {
        return changeItemType;
    }

    public void setChangeItemType(String changeItemType) {
        this.changeItemType = changeItemType;
    }

    private String changeContentDescription;

    @Basic
    @javax.persistence.Column(name = "CHANGE_CONTENT_DESCRIPTION")
    public String getChangeContentDescription() {
        return changeContentDescription;
    }

    public void setChangeContentDescription(String changeContentDescription) {
        this.changeContentDescription = changeContentDescription;
    }

    private String contractHisinfoUrl;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_HISINFO_URL")
    public String getContractHisinfoUrl() {
        return contractHisinfoUrl;
    }

    public void setContractHisinfoUrl(String contractHisinfoUrl) {
        this.contractHisinfoUrl = contractHisinfoUrl;
    }

    private String contractHisinfo;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_HISINFO")
    public String getContractHisinfo() {
        return contractHisinfo;
    }

    public void setContractHisinfo(String contractHisinfo) {
        this.contractHisinfo = contractHisinfo;
    }

    private String contractHisinfoCount;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_HISINFO_COUNT")
    public String getContractHisinfoCount() {
        return contractHisinfoCount;
    }

    public void setContractHisinfoCount(String contractHisinfoCount) {
        this.contractHisinfoCount = contractHisinfoCount;
    }

    private String memo;

    @Basic
    @javax.persistence.Column(name = "MEMO")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String changeItemName;

    @Basic
    @javax.persistence.Column(name = "CHANGE_ITEM_NAME")
    public String getChangeItemName() {
        return changeItemName;
    }

    public void setChangeItemName(String changeItemName) {
        this.changeItemName = changeItemName;
    }

    private String lawReason;

    @Basic
    @javax.persistence.Column(name = "LAW_REASON")
    public String getLawReason() {
        return lawReason;
    }

    public void setLawReason(String lawReason) {
        this.lawReason = lawReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractItem that = (ContractItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (changeContractNo != null ? !changeContractNo.equals(that.changeContractNo) : that.changeContractNo != null)
            return false;
        if (changeSerialNo != null ? !changeSerialNo.equals(that.changeSerialNo) : that.changeSerialNo != null)
            return false;
        if (changePutForword != null ? !changePutForword.equals(that.changePutForword) : that.changePutForword != null)
            return false;
        if (changeItemType1 != null ? !changeItemType1.equals(that.changeItemType1) : that.changeItemType1 != null)
            return false;
        if (changeItemType2 != null ? !changeItemType2.equals(that.changeItemType2) : that.changeItemType2 != null)
            return false;
        if (changeItemDescription != null ? !changeItemDescription.equals(that.changeItemDescription) : that.changeItemDescription != null)
            return false;
        if (changeReasonType != null ? !changeReasonType.equals(that.changeReasonType) : that.changeReasonType != null)
            return false;
        if (changeReasonDescription != null ? !changeReasonDescription.equals(that.changeReasonDescription) : that.changeReasonDescription != null)
            return false;
        if (changeContent != null ? !changeContent.equals(that.changeContent) : that.changeContent != null)
            return false;
        if (changePrice != null ? !changePrice.equals(that.changePrice) : that.changePrice != null) return false;
        if (changeType != null ? !changeType.equals(that.changeType) : that.changeType != null) return false;
        if (changeNature != null ? !changeNature.equals(that.changeNature) : that.changeNature != null) return false;
        if (changeWorkload != null ? !changeWorkload.equals(that.changeWorkload) : that.changeWorkload != null)
            return false;
        if (isPicture != null ? !isPicture.equals(that.isPicture) : that.isPicture != null) return false;
        if (leader != null ? !leader.equals(that.leader) : that.leader != null) return false;
        if (process != null ? !process.equals(that.process) : that.process != null) return false;
        if (incidect != null ? !incidect.equals(that.incidect) : that.incidect != null) return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        if (typeid != null ? !typeid.equals(that.typeid) : that.typeid != null) return false;
        if (contractName != null ? !contractName.equals(that.contractName) : that.contractName != null) return false;
        if (attach != null ? !attach.equals(that.attach) : that.attach != null) return false;
        if (operateTime != null ? !operateTime.equals(that.operateTime) : that.operateTime != null) return false;
        if (dept != null ? !dept.equals(that.dept) : that.dept != null) return false;
        if (deptId != null ? !deptId.equals(that.deptId) : that.deptId != null) return false;
        if (dealPerson != null ? !dealPerson.equals(that.dealPerson) : that.dealPerson != null) return false;
        if (regPerson != null ? !regPerson.equals(that.regPerson) : that.regPerson != null) return false;
        if (regLoginName != null ? !regLoginName.equals(that.regLoginName) : that.regLoginName != null) return false;
        if (regTime != null ? !regTime.equals(that.regTime) : that.regTime != null) return false;
        if (passTime != null ? !passTime.equals(that.passTime) : that.passTime != null) return false;
        if (dealDeptSuggest != null ? !dealDeptSuggest.equals(that.dealDeptSuggest) : that.dealDeptSuggest != null)
            return false;
        if (contractId != null ? !contractId.equals(that.contractId) : that.contractId != null) return false;
        if (contractType != null ? !contractType.equals(that.contractType) : that.contractType != null) return false;
        if (contractSq != null ? !contractSq.equals(that.contractSq) : that.contractSq != null) return false;
        if (changeSort != null ? !changeSort.equals(that.changeSort) : that.changeSort != null) return false;
        if (contractChangeProtocolId != null ? !contractChangeProtocolId.equals(that.contractChangeProtocolId) : that.contractChangeProtocolId != null)
            return false;
        if (contractChangeAgreementId != null ? !contractChangeAgreementId.equals(that.contractChangeAgreementId) : that.contractChangeAgreementId != null)
            return false;
        if (changeMemo != null ? !changeMemo.equals(that.changeMemo) : that.changeMemo != null) return false;
        if (changeContentType != null ? !changeContentType.equals(that.changeContentType) : that.changeContentType != null)
            return false;
        if (changePriceType != null ? !changePriceType.equals(that.changePriceType) : that.changePriceType != null)
            return false;
        if (changePriceDescription != null ? !changePriceDescription.equals(that.changePriceDescription) : that.changePriceDescription != null)
            return false;
        if (supervisionSuggest != null ? !supervisionSuggest.equals(that.supervisionSuggest) : that.supervisionSuggest != null)
            return false;
        if (changeItemType != null ? !changeItemType.equals(that.changeItemType) : that.changeItemType != null)
            return false;
        if (changeContentDescription != null ? !changeContentDescription.equals(that.changeContentDescription) : that.changeContentDescription != null)
            return false;
        if (contractHisinfoUrl != null ? !contractHisinfoUrl.equals(that.contractHisinfoUrl) : that.contractHisinfoUrl != null)
            return false;
        if (contractHisinfo != null ? !contractHisinfo.equals(that.contractHisinfo) : that.contractHisinfo != null)
            return false;
        if (contractHisinfoCount != null ? !contractHisinfoCount.equals(that.contractHisinfoCount) : that.contractHisinfoCount != null)
            return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (changeItemName != null ? !changeItemName.equals(that.changeItemName) : that.changeItemName != null)
            return false;
        if (lawReason != null ? !lawReason.equals(that.lawReason) : that.lawReason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (changeContractNo != null ? changeContractNo.hashCode() : 0);
        result = 31 * result + (changeSerialNo != null ? changeSerialNo.hashCode() : 0);
        result = 31 * result + (changePutForword != null ? changePutForword.hashCode() : 0);
        result = 31 * result + (changeItemType1 != null ? changeItemType1.hashCode() : 0);
        result = 31 * result + (changeItemType2 != null ? changeItemType2.hashCode() : 0);
        result = 31 * result + (changeItemDescription != null ? changeItemDescription.hashCode() : 0);
        result = 31 * result + (changeReasonType != null ? changeReasonType.hashCode() : 0);
        result = 31 * result + (changeReasonDescription != null ? changeReasonDescription.hashCode() : 0);
        result = 31 * result + (changeContent != null ? changeContent.hashCode() : 0);
        result = 31 * result + (changePrice != null ? changePrice.hashCode() : 0);
        result = 31 * result + (changeType != null ? changeType.hashCode() : 0);
        result = 31 * result + (changeNature != null ? changeNature.hashCode() : 0);
        result = 31 * result + (changeWorkload != null ? changeWorkload.hashCode() : 0);
        result = 31 * result + (isPicture != null ? isPicture.hashCode() : 0);
        result = 31 * result + (leader != null ? leader.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (incidect != null ? incidect.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (typeid != null ? typeid.hashCode() : 0);
        result = 31 * result + (contractName != null ? contractName.hashCode() : 0);
        result = 31 * result + (attach != null ? attach.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        result = 31 * result + (dealPerson != null ? dealPerson.hashCode() : 0);
        result = 31 * result + (regPerson != null ? regPerson.hashCode() : 0);
        result = 31 * result + (regLoginName != null ? regLoginName.hashCode() : 0);
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        result = 31 * result + (passTime != null ? passTime.hashCode() : 0);
        result = 31 * result + (dealDeptSuggest != null ? dealDeptSuggest.hashCode() : 0);
        result = 31 * result + (contractId != null ? contractId.hashCode() : 0);
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (contractSq != null ? contractSq.hashCode() : 0);
        result = 31 * result + (changeSort != null ? changeSort.hashCode() : 0);
        result = 31 * result + (contractChangeProtocolId != null ? contractChangeProtocolId.hashCode() : 0);
        result = 31 * result + (contractChangeAgreementId != null ? contractChangeAgreementId.hashCode() : 0);
        result = 31 * result + (changeMemo != null ? changeMemo.hashCode() : 0);
        result = 31 * result + (changeContentType != null ? changeContentType.hashCode() : 0);
        result = 31 * result + (changePriceType != null ? changePriceType.hashCode() : 0);
        result = 31 * result + (changePriceDescription != null ? changePriceDescription.hashCode() : 0);
        result = 31 * result + (supervisionSuggest != null ? supervisionSuggest.hashCode() : 0);
        result = 31 * result + (changeItemType != null ? changeItemType.hashCode() : 0);
        result = 31 * result + (changeContentDescription != null ? changeContentDescription.hashCode() : 0);
        result = 31 * result + (contractHisinfoUrl != null ? contractHisinfoUrl.hashCode() : 0);
        result = 31 * result + (contractHisinfo != null ? contractHisinfo.hashCode() : 0);
        result = 31 * result + (contractHisinfoCount != null ? contractHisinfoCount.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (changeItemName != null ? changeItemName.hashCode() : 0);
        result = 31 * result + (lawReason != null ? lawReason.hashCode() : 0);
        return result;
    }
}
