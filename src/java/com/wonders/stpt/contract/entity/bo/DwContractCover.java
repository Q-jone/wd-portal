/*     */ package com.wonders.stpt.contract.entity.bo;
/*     */ 
/*     */ import com.wondersgroup.framework.core5.model.bo.BusinessObject;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="DW_CONTRACT_COVER")
/*     */ public class DwContractCover
/*     */   implements Serializable, BusinessObject
/*     */ {
/*     */   private String id;
/*     */   private String companyName;
/*     */   private String contractType;
/*     */   private Date createDate;
/*     */   private String companyType;
/*     */   private String companyId;
/*     */   private BigDecimal numbers;
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  56 */     this.id = id; } 
/*  63 */   @Id
/*     */   @GeneratedValue(strategy=GenerationType.AUTO)
/*     */   @Column(name="ID")
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setCompanyName(String companyName)
/*     */   {
/*  67 */     this.companyName = companyName;
/*     */   }
/*     */   @Column(name="COMPANY_NAME", nullable=true, length=200)
/*     */   public String getCompanyName() {
/*  72 */     return this.companyName;
/*     */   }
/*     */ 
/*     */   public void setContractType(String contractType) {
/*  76 */     this.contractType = contractType;
/*     */   }
/*     */   @Column(name="CONTRACT_TYPE", nullable=true, length=20)
/*     */   public String getContractType() {
/*  81 */     return this.contractType;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate) {
/*  85 */     this.createDate = createDate;
/*     */   }
/*     */   @Column(name="CREATE_DATE", nullable=true, length=11)
/*     */   public Date getCreateDate() {
/*  90 */     return this.createDate;
/*     */   }
/*     */   @Column(name="COMPANY_TYPE", nullable=true, length=20)
/*     */   public String getCompanyType() {
/*  95 */     return this.companyType;
/*     */   }
/*     */ 
/*     */   public void setCompanyType(String companyType) {
/*  99 */     this.companyType = companyType;
/*     */   }
/*     */   @Column(name="COMPANY_ID", nullable=true, length=40)
/*     */   public String getCompanyId() {
/* 104 */     return this.companyId;
/*     */   }
/*     */ 
/*     */   public void setCompanyId(String companyId) {
/* 108 */     this.companyId = companyId;
/*     */   }
/*     */   @Column(name="NUMBERS", nullable=true, length=22)
/*     */   public BigDecimal getNumbers() {
/* 113 */     return this.numbers;
/*     */   }
/*     */ 
/*     */   public void setNumbers(BigDecimal numbers) {
/* 117 */     this.numbers = numbers;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.DwContractCover
 * JD-Core Version:    0.6.0
 */