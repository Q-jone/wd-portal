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
/*     */ @Table(name="DW_CONTRACT_P_PRICE_NUMBER")
/*     */ public class DwContractPriceNumber
/*     */ {
/*     */   private String id;
/*     */   private String controlDate;
/*     */   private BigDecimal contractType;
/*     */   private String companyId;
/*     */   private String companyName;
/*     */   private String createDate;
/*     */   private BigDecimal contractNum;
/*     */   private BigDecimal contractPrice;
/*     */   private BigDecimal dataType;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   @GenericGenerator(name="system-uuid", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=40)
/*     */   public String getId()
/*     */   {
/*  31 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  35 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_DATE", length=40)
/*     */   public String getControlDate() {
/*  41 */     return this.controlDate;
/*     */   }
/*     */ 
/*     */   public void setControlDate(String controlDate) {
/*  45 */     this.controlDate = controlDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTRACT_TYPE", precision=22, scale=0)
/*     */   public BigDecimal getContractType() {
/*  51 */     return this.contractType;
/*     */   }
/*     */ 
/*     */   public void setContractType(BigDecimal contractType) {
/*  55 */     this.contractType = contractType;
/*     */   }
/*     */ 
/*     */   @Column(name="COMPANY_ID", length=40)
/*     */   public String getCompanyId() {
/*  61 */     return this.companyId;
/*     */   }
/*     */ 
/*     */   public void setCompanyId(String companyId) {
/*  65 */     this.companyId = companyId;
/*     */   }
/*     */ 
/*     */   @Column(name="COMPANY_NAME", length=400)
/*     */   public String getCompanyName() {
/*  71 */     return this.companyName;
/*     */   }
/*     */ 
/*     */   public void setCompanyName(String companyName) {
/*  75 */     this.companyName = companyName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE")
/*     */   public String getCreateDate() {
/*  81 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(String createDate) {
/*  85 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTRACT_NUM", precision=22, scale=0)
/*     */   public BigDecimal getContractNum() {
/*  91 */     return this.contractNum;
/*     */   }
/*     */ 
/*     */   public void setContractNum(BigDecimal contractNum) {
/*  95 */     this.contractNum = contractNum;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTRACT_PRICE", precision=22, scale=0)
/*     */   public BigDecimal getContractPrice() {
/* 101 */     return this.contractPrice;
/*     */   }
/*     */ 
/*     */   public void setContractPrice(BigDecimal contractPrice) {
/* 105 */     this.contractPrice = contractPrice;
/*     */   }
/*     */ 
/*     */   @Column(name="DATA_TYPE", precision=22, scale=0)
/*     */   public BigDecimal getDataType() {
/* 111 */     return this.dataType;
/*     */   }
/*     */ 
/*     */   public void setDataType(BigDecimal dataType) {
/* 115 */     this.dataType = dataType;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.DwContractPriceNumber
 * JD-Core Version:    0.6.0
 */