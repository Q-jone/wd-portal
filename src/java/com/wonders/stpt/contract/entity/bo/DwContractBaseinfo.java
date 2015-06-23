/*     */ package com.wonders.stpt.contract.entity.bo;
/*     */ 
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="DW_CONTRACT_P_BASEINFO")
/*     */ public class DwContractBaseinfo
/*     */ {
/*     */   private String id;
/*     */   private String type;
/*     */   private String projectCountCurrentMonth;
/*     */   private String projectMoneyCurrentMonth;
/*     */   private String projectCountAll;
/*     */   private String projectMoneyAll;
/*     */   private String needToCompleteProject;
/*     */   private String contractCountCurrentMonth;
/*     */   private String contractMoneyCurrentMonth;
/*     */   private String contractCountAll;
/*     */   private String contractMoneyAll;
/*     */   private String needToCompleteContract;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system.uuid")
/*     */   @GenericGenerator(name="system.uuid", strategy="uuid")
/*     */   @Column(name="ID", length=40, nullable=false)
/*     */   public String getId()
/*     */   {
/*  34 */     return this.id;
/*     */   }
/*     */   public void setId(String id) {
/*  37 */     this.id = id;
/*     */   }
/*  41 */   @Column(name="TYPE", nullable=true, length=2)
/*     */   public String getType() { return this.type; }
/*     */ 
/*     */   public void setType(String type) {
/*  44 */     this.type = type;
/*     */   }
/*  48 */   @Column(name="PROJECT_COUNT_CURRENT_MONTH", nullable=true, length=100)
/*     */   public String getProjectCountCurrentMonth() { return this.projectCountCurrentMonth; }
/*     */ 
/*     */   public void setProjectCountCurrentMonth(String projectCountCurrentMonth) {
/*  51 */     this.projectCountCurrentMonth = projectCountCurrentMonth;
/*     */   }
/*     */   @Column(name="PROJECT_MONEY_CURRENT_MONTH", nullable=true, length=100)
/*     */   public String getProjectMoneyCurrentMonth() {
/*  56 */     return this.projectMoneyCurrentMonth;
/*     */   }
/*     */   public void setProjectMoneyCurrentMonth(String projectMoneyCurrentMonth) {
/*  59 */     this.projectMoneyCurrentMonth = projectMoneyCurrentMonth;
/*     */   }
/*     */   @Column(name="PROJECT_COUNT_ALL", nullable=true, length=100)
/*     */   public String getProjectCountAll() {
/*  64 */     return this.projectCountAll;
/*     */   }
/*     */   public void setProjectCountAll(String projectCountAll) {
/*  67 */     this.projectCountAll = projectCountAll;
/*     */   }
/*     */   @Column(name="PROJECT_MONEY_ALL", nullable=true, length=100)
/*     */   public String getProjectMoneyAll() {
/*  72 */     return this.projectMoneyAll;
/*     */   }
/*     */   public void setProjectMoneyAll(String projectMoneyAll) {
/*  75 */     this.projectMoneyAll = projectMoneyAll;
/*     */   }
/*  79 */   @Column(name="NEED_TO_COMPLETE_PROJECT", nullable=true, length=40)
/*     */   public String getNeedToCompleteProject() { return this.needToCompleteProject; }
/*     */ 
/*     */   public void setNeedToCompleteProject(String needToCompleteProject) {
/*  82 */     this.needToCompleteProject = needToCompleteProject;
/*     */   }
/*     */   @Column(name="CONTRACT_COUNT_CURRENT_MONTH", nullable=true, length=100)
/*     */   public String getContractCountCurrentMonth() {
/*  87 */     return this.contractCountCurrentMonth;
/*     */   }
/*     */   public void setContractCountCurrentMonth(String contractCountCurrentMonth) {
/*  90 */     this.contractCountCurrentMonth = contractCountCurrentMonth;
/*     */   }
/*     */   @Column(name="CONTRACT_MONEY_CURRENT_MONTH", nullable=true, length=100)
/*     */   public String getContractMoneyCurrentMonth() {
/*  95 */     return this.contractMoneyCurrentMonth;
/*     */   }
/*     */   public void setContractMoneyCurrentMonth(String contractMoneyCurrentMonth) {
/*  98 */     this.contractMoneyCurrentMonth = contractMoneyCurrentMonth;
/*     */   }
/*     */   @Column(name="CONTRACT_COUNT_ALL", nullable=true, length=100)
/*     */   public String getContractCountAll() {
/* 103 */     return this.contractCountAll;
/*     */   }
/*     */   public void setContractCountAll(String contractCountAll) {
/* 106 */     this.contractCountAll = contractCountAll;
/*     */   }
/*     */   @Column(name="CONTRACT_MONEY_ALL", nullable=true, length=100)
/*     */   public String getContractMoneyAll() {
/* 111 */     return this.contractMoneyAll;
/*     */   }
/*     */   public void setContractMoneyAll(String contractMoneyAll) {
/* 114 */     this.contractMoneyAll = contractMoneyAll;
/*     */   }
/*     */   @Column(name="NEED_TO_COMPLETE_CONTRACT", nullable=true, length=40)
/*     */   public String getNeedToCompleteContract() {
/* 119 */     return this.needToCompleteContract;
/*     */   }
/*     */   public void setNeedToCompleteContract(String needToCompleteContract) {
/* 122 */     this.needToCompleteContract = needToCompleteContract;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.DwContractBaseinfo
 * JD-Core Version:    0.6.0
 */