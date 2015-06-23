/*     */ package com.wonders.stpt.contract.entity.bo;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="DW_CONTRACT_P_ASSIGN_STATS")
/*     */ public class DwContractAssignStats
/*     */ {
/*     */   private String id;
/*     */   private BigDecimal contractType;
/*     */   private BigDecimal assignType;
/*     */   private String name1;
/*     */   private BigDecimal value1;
/*     */   private String name2;
/*     */   private BigDecimal value2;
/*     */   private String controlDate;
/*     */   private String createDate;
/*     */   private String controlCompany;
/*     */   private String controlCompanyId;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   @GenericGenerator(name="system-uuid", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=40)
/*     */   public String getId()
/*     */   {
/*  34 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  38 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTRACT_TYPE", precision=22, scale=0)
/*     */   public BigDecimal getContractType() {
/*  44 */     return this.contractType;
/*     */   }
/*     */ 
/*     */   public void setContractType(BigDecimal contractType) {
/*  48 */     this.contractType = contractType;
/*     */   }
/*     */ 
/*     */   @Column(name="ASSIGN_TYPE", precision=22, scale=0)
/*     */   public BigDecimal getAssignType() {
/*  54 */     return this.assignType;
/*     */   }
/*     */ 
/*     */   public void setAssignType(BigDecimal assignType) {
/*  58 */     this.assignType = assignType;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME_1", length=200)
/*     */   public String getName1() {
/*  64 */     return this.name1;
/*     */   }
/*     */ 
/*     */   public void setName1(String name1) {
/*  68 */     this.name1 = name1;
/*     */   }
/*     */ 
/*     */   @Column(name="VALUE_1", precision=22, scale=0)
/*     */   public BigDecimal getValue1() {
/*  74 */     return this.value1;
/*     */   }
/*     */ 
/*     */   public void setValue1(BigDecimal value1) {
/*  78 */     this.value1 = value1;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME_2", length=200)
/*     */   public String getName2() {
/*  84 */     return this.name2;
/*     */   }
/*     */ 
/*     */   public void setName2(String name2) {
/*  88 */     this.name2 = name2;
/*     */   }
/*     */ 
/*     */   @Column(name="VALUE_2", precision=22, scale=0)
/*     */   public BigDecimal getValue2() {
/*  94 */     return this.value2;
/*     */   }
/*     */ 
/*     */   public void setValue2(BigDecimal value2) {
/*  98 */     this.value2 = value2;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_DATE", length=40)
/*     */   public String getControlDate() {
/* 104 */     return this.controlDate;
/*     */   }
/*     */ 
/*     */   public void setControlDate(String controlDate) {
/* 108 */     this.controlDate = controlDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE")
/*     */   public String getCreateDate() {
/* 114 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(String createDate) {
/* 118 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_COMPANY", length=100)
/*     */   public String getControlCompany() {
/* 124 */     return this.controlCompany;
/*     */   }
/*     */ 
/*     */   public void setControlCompany(String controlCompany) {
/* 128 */     this.controlCompany = controlCompany;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_COMPANY_ID", length=40)
/*     */   public String getControlCompanyId() {
/* 134 */     return this.controlCompanyId;
/*     */   }
/*     */ 
/*     */   public void setControlCompanyId(String controlCompanyId) {
/* 138 */     this.controlCompanyId = controlCompanyId;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.DwContractAssignStats
 * JD-Core Version:    0.6.0
 */