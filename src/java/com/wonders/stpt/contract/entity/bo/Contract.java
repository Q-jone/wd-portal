/*     */ package com.wonders.stpt.contract.entity.bo;
/*     */ 
/*     */ import com.wondersgroup.framework.core5.model.bo.BusinessObject;
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="C_CONTRACT")
/*     */ public class Contract
/*     */   implements Serializable, BusinessObject
/*     */ {
/*     */   private String id;
/*     */   private String buildSupplierId;
/*     */   private String buildSupplierName;
/*     */   private String contractAttachment;
/*     */   private String contractContent;
/*     */   private String contractName;
/*     */   private String contractNo;
/*     */   private String contractOwnerId;
/*     */   private String contractOwnerName;
/*     */   private String contractPassedDate;
/*     */   private String contractPlanId;
/*     */   private String contractPlanNo;
/*     */   private String contractPrice;
/*     */   private String contractSignedDate;
/*     */   private String contractStatus;
/*     */   private String contractType;
/*     */   private String createDate;
/*     */   private String payType;
/*     */   private String projectId;
/*     */   private String projectName;
/*     */   private String projectNo;
/*     */   private String remark;
/*     */   private String removed;
/*     */   private String updateDate;
/*     */   private String contractEndDate;
/*     */   private String contractStartDate;
/*     */   private String contractOwnerExecuteId;
/*     */   private String contractOwnerExecuteName;
/*     */   private String verifyPrice;
/*     */   private String finalPrice;
/*     */   private String inviteBidType;
/*     */   private String nationVerifyPrice;
/*     */   private String selfNo;
/*     */   private String createType;
/*     */   private String contractDestoryDate;
/*     */ 
/*     */   @Column(name="CONTRACT_DESTORY_DATE", nullable=true, length=40)
/*     */   public String getContractDestoryDate()
/*     */   {
/* 107 */     return this.contractDestoryDate;
/*     */   }
/*     */ 
/*     */   public void setContractDestoryDate(String contractDestoryDate) {
/* 111 */     this.contractDestoryDate = contractDestoryDate;
/*     */   }
/*     */   @Column(name="CREATE_TYPE", nullable=true, length=200)
/*     */   public String getCreateType() {
/* 116 */     return this.createType;
/*     */   }
/*     */ 
/*     */   public void setCreateType(String createType) {
/* 120 */     this.createType = createType;
/*     */   }
/*     */   @Column(name="SELF_NO", nullable=true, length=200)
/*     */   public String getSelfNo() {
/* 125 */     return this.selfNo;
/*     */   }
/*     */ 
/*     */   public void setSelfNo(String selfNo) {
/* 129 */     this.selfNo = selfNo;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/* 133 */     this.id = id; } 
/* 141 */   @Id
/*     */   @GeneratedValue(generator="system.uuid")
/*     */   @GenericGenerator(name="system.uuid", strategy="uuid")
/*     */   @Column(name="ID")
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setBuildSupplierId(String buildSupplierId)
/*     */   {
/* 145 */     this.buildSupplierId = buildSupplierId;
/*     */   }
/*     */   @Column(name="BUILD_SUPPLIER_ID", nullable=true, length=40)
/*     */   public String getBuildSupplierId() {
/* 150 */     return this.buildSupplierId;
/*     */   }
/*     */ 
/*     */   public void setBuildSupplierName(String buildSupplierName) {
/* 154 */     this.buildSupplierName = buildSupplierName;
/*     */   }
/*     */   @Column(name="BUILD_SUPPLIER_NAME", nullable=true, length=40)
/*     */   public String getBuildSupplierName() {
/* 159 */     return this.buildSupplierName;
/*     */   }
/*     */ 
/*     */   public void setContractAttachment(String contractAttachment) {
/* 163 */     this.contractAttachment = contractAttachment;
/*     */   }
/*     */   @Column(name="CONTRACT_ATTACHMENT", nullable=true, length=40)
/*     */   public String getContractAttachment() {
/* 168 */     return this.contractAttachment;
/*     */   }
/*     */ 
/*     */   public void setContractContent(String contractContent) {
/* 172 */     this.contractContent = contractContent;
/*     */   }
/*     */   @Column(name="CONTRACT_CONTENT", nullable=true, length=4000)
/*     */   public String getContractContent() {
/* 177 */     return this.contractContent;
/*     */   }
/*     */ 
/*     */   public void setContractEndDate(String contractEndDate) {
/* 181 */     this.contractEndDate = contractEndDate;
/*     */   }
/*     */   @Column(name="CONTRACT_END_DATE", nullable=true, length=40)
/*     */   public String getContractEndDate() {
/* 186 */     return this.contractEndDate;
/*     */   }
/*     */ 
/*     */   public void setContractName(String contractName) {
/* 190 */     this.contractName = contractName;
/*     */   }
/*     */   @Column(name="CONTRACT_NAME", nullable=true, length=140)
/*     */   public String getContractName() {
/* 195 */     return this.contractName;
/*     */   }
/*     */ 
/*     */   public void setContractNo(String contractNo) {
/* 199 */     this.contractNo = contractNo;
/*     */   }
/*     */   @Column(name="CONTRACT_NO", nullable=true, length=100)
/*     */   public String getContractNo() {
/* 204 */     return this.contractNo;
/*     */   }
/*     */ 
/*     */   public void setContractOwnerId(String contractOwnerId) {
/* 208 */     this.contractOwnerId = contractOwnerId;
/*     */   }
/*     */   @Column(name="CONTRACT_OWNER_ID", nullable=true, length=40)
/*     */   public String getContractOwnerId() {
/* 213 */     return this.contractOwnerId;
/*     */   }
/*     */ 
/*     */   public void setContractOwnerName(String contractOwnerName) {
/* 217 */     this.contractOwnerName = contractOwnerName;
/*     */   }
/*     */   @Column(name="CONTRACT_OWNER_NAME", nullable=true, length=1000)
/*     */   public String getContractOwnerName() {
/* 222 */     return this.contractOwnerName;
/*     */   }
/*     */ 
/*     */   public void setContractPassedDate(String contractPassedDate) {
/* 226 */     this.contractPassedDate = contractPassedDate;
/*     */   }
/*     */   @Column(name="CONTRACT_PASSED_DATE", nullable=true, length=40)
/*     */   public String getContractPassedDate() {
/* 231 */     return this.contractPassedDate;
/*     */   }
/*     */ 
/*     */   public void setContractPlanId(String contractPlanId) {
/* 235 */     this.contractPlanId = contractPlanId;
/*     */   }
/*     */   @Column(name="CONTRACT_PLAN_ID", nullable=true, length=40)
/*     */   public String getContractPlanId() {
/* 240 */     return this.contractPlanId;
/*     */   }
/*     */ 
/*     */   public void setContractPlanNo(String contractPlanNo) {
/* 244 */     this.contractPlanNo = contractPlanNo;
/*     */   }
/*     */   @Column(name="CONTRACT_PLAN_NO", nullable=true, length=40)
/*     */   public String getContractPlanNo() {
/* 249 */     return this.contractPlanNo;
/*     */   }
/*     */ 
/*     */   public void setContractPrice(String contractPrice) {
/* 253 */     this.contractPrice = contractPrice;
/*     */   }
/*     */   @Column(name="CONTRACT_PRICE", nullable=true, length=40)
/*     */   public String getContractPrice() {
/* 258 */     return this.contractPrice;
/*     */   }
/*     */ 
/*     */   public void setContractSignedDate(String contractSignedDate) {
/* 262 */     this.contractSignedDate = contractSignedDate;
/*     */   }
/*     */   @Column(name="CONTRACT_SIGNED_DATE", nullable=true, length=40)
/*     */   public String getContractSignedDate() {
/* 267 */     return this.contractSignedDate;
/*     */   }
/*     */ 
/*     */   public void setContractStatus(String contractStatus) {
/* 271 */     this.contractStatus = contractStatus;
/*     */   }
/*     */   @Column(name="CONTRACT_STATUS", nullable=true, length=1)
/*     */   public String getContractStatus() {
/* 276 */     return this.contractStatus;
/*     */   }
/*     */ 
/*     */   public void setContractType(String contractType) {
/* 280 */     this.contractType = contractType;
/*     */   }
/*     */   @Column(name="CONTRACT_TYPE", nullable=true, length=40)
/*     */   public String getContractType() {
/* 285 */     return this.contractType;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(String createDate) {
/* 289 */     this.createDate = createDate;
/*     */   }
/*     */   @Column(name="CREATE_DATE", nullable=true, length=40)
/*     */   public String getCreateDate() {
/* 294 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setPayType(String payType) {
/* 298 */     this.payType = payType;
/*     */   }
/*     */   @Column(name="PAY_TYPE", nullable=true, length=400)
/*     */   public String getPayType() {
/* 303 */     return this.payType;
/*     */   }
/*     */ 
/*     */   public void setProjectId(String projectId) {
/* 307 */     this.projectId = projectId;
/*     */   }
/*     */   @Column(name="PROJECT_ID", nullable=true, length=100)
/*     */   public String getProjectId() {
/* 312 */     return this.projectId;
/*     */   }
/*     */ 
/*     */   public void setProjectName(String projectName) {
/* 316 */     this.projectName = projectName;
/*     */   }
/*     */   @Column(name="PROJECT_NAME", nullable=true, length=200)
/*     */   public String getProjectName() {
/* 321 */     return this.projectName;
/*     */   }
/*     */ 
/*     */   public void setProjectNo(String projectNo) {
/* 325 */     this.projectNo = projectNo;
/*     */   }
/*     */   @Column(name="PROJECT_NO", nullable=true, length=40)
/*     */   public String getProjectNo() {
/* 330 */     return this.projectNo;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark) {
/* 334 */     this.remark = remark;
/*     */   }
/*     */   @Column(name="REMARK", nullable=true, length=4000)
/*     */   public String getRemark() {
/* 339 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemoved(String removed) {
/* 343 */     this.removed = removed;
/*     */   }
/*     */   @Column(name="REMOVED", nullable=true, length=1)
/*     */   public String getRemoved() {
/* 348 */     return this.removed;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(String updateDate) {
/* 352 */     this.updateDate = updateDate;
/*     */   }
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=40)
/*     */   public String getUpdateDate() {
/* 357 */     return this.updateDate;
/*     */   }
/*     */   @Column(name="CONTRACT_START_DATE", nullable=true, length=40)
/*     */   public String getContractStartDate() {
/* 362 */     return this.contractStartDate;
/*     */   }
/*     */ 
/*     */   public void setContractStartDate(String contractStartDate) {
/* 366 */     this.contractStartDate = contractStartDate;
/*     */   }
/*     */   @Column(name="CONTRACT_OWNER_EXECUTE_ID", nullable=true, length=40)
/*     */   public String getContractOwnerExecuteId() {
/* 371 */     return this.contractOwnerExecuteId;
/*     */   }
/*     */ 
/*     */   public void setContractOwnerExecuteId(String contractOwnerExecuteId) {
/* 375 */     this.contractOwnerExecuteId = contractOwnerExecuteId;
/*     */   }
/*     */   @Column(name="CONTRACT_OWNER_EXECUTE_NAME", nullable=true, length=40)
/*     */   public String getContractOwnerExecuteName() {
/* 380 */     return this.contractOwnerExecuteName;
/*     */   }
/*     */ 
/*     */   public void setContractOwnerExecuteName(String contractOwnerExecuteName) {
/* 384 */     this.contractOwnerExecuteName = contractOwnerExecuteName;
/*     */   }
/*     */   @Column(name="VERIFY_PRICE", nullable=true, length=100)
/*     */   public String getVerifyPrice() {
/* 389 */     return this.verifyPrice;
/*     */   }
/*     */ 
/*     */   public void setVerifyPrice(String verifyPrice) {
/* 393 */     this.verifyPrice = verifyPrice;
/*     */   }
/*     */   @Column(name="FINAL_PRICE", nullable=true, length=100)
/*     */   public String getFinalPrice() {
/* 398 */     return this.finalPrice;
/*     */   }
/*     */ 
/*     */   public void setFinalPrice(String finalPrice) {
/* 402 */     this.finalPrice = finalPrice;
/*     */   }
/*     */   @Column(name="INVITE_BID_TYPE", nullable=true, length=20)
/*     */   public String getInviteBidType() {
/* 407 */     return this.inviteBidType;
/*     */   }
/*     */ 
/*     */   public void setInviteBidType(String inviteBidType) {
/* 411 */     this.inviteBidType = inviteBidType;
/*     */   }
/*     */   @Column(name="NATION_VERIFY_PRICE", nullable=true, length=100)
/*     */   public String getNationVerifyPrice() {
/* 416 */     return this.nationVerifyPrice;
/*     */   }
/*     */ 
/*     */   public void setNationVerifyPrice(String nationVerifyPrice) {
/* 420 */     this.nationVerifyPrice = nationVerifyPrice;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.Contract
 * JD-Core Version:    0.6.0
 */