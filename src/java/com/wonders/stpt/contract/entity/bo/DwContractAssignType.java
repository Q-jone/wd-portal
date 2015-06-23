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
/*     */ @Table(name="DW_CONTRACT_P_ASSIGN_TYPE")
/*     */ public class DwContractAssignType
/*     */ {
/*     */   private String id;
/*     */   private BigDecimal contractType;
/*     */   private BigDecimal assignType;
/*     */   private String createDate;
/*     */   private String controlYear;
/*     */   private String controlCompany;
/*     */   private String controlCompanyId;
/*     */   private BigDecimal exe1;
/*     */   private BigDecimal exe2;
/*     */   private BigDecimal exe3;
/*     */   private BigDecimal exe4;
/*     */   private BigDecimal exe5;
/*     */   private BigDecimal exe6;
/*     */   private BigDecimal exe7;
/*     */   private BigDecimal exe8;
/*     */   private BigDecimal exe9;
/*     */   private BigDecimal exe10;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="system-uuid")
/*     */   @GenericGenerator(name="system-uuid", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=40)
/*     */   public String getId()
/*     */   {
/*  39 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  43 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTRACT_TYPE", precision=22, scale=0)
/*     */   public BigDecimal getContractType() {
/*  49 */     return this.contractType;
/*     */   }
/*     */ 
/*     */   public void setContractType(BigDecimal contractType) {
/*  53 */     this.contractType = contractType;
/*     */   }
/*     */ 
/*     */   @Column(name="ASSIGN_TYPE", precision=22, scale=0)
/*     */   public BigDecimal getAssignType() {
/*  59 */     return this.assignType;
/*     */   }
/*     */ 
/*     */   public void setAssignType(BigDecimal assignType) {
/*  63 */     this.assignType = assignType;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE")
/*     */   public String getCreateDate() {
/*  69 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(String createDate) {
/*  73 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_YEAR", length=40)
/*     */   public String getControlYear() {
/*  79 */     return this.controlYear;
/*     */   }
/*     */ 
/*     */   public void setControlYear(String controlYear) {
/*  83 */     this.controlYear = controlYear;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_COMPANY", length=100)
/*     */   public String getControlCompany() {
/*  89 */     return this.controlCompany;
/*     */   }
/*     */ 
/*     */   public void setControlCompany(String controlCompany) {
/*  93 */     this.controlCompany = controlCompany;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTROL_COMPANY_ID", length=40)
/*     */   public String getControlCompanyId() {
/*  99 */     return this.controlCompanyId;
/*     */   }
/*     */ 
/*     */   public void setControlCompanyId(String controlCompanyId) {
/* 103 */     this.controlCompanyId = controlCompanyId;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_1", precision=22, scale=0)
/*     */   public BigDecimal getExe1() {
/* 109 */     return this.exe1;
/*     */   }
/*     */ 
/*     */   public void setExe1(BigDecimal exe1) {
/* 113 */     this.exe1 = exe1;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_2", precision=22, scale=0)
/*     */   public BigDecimal getExe2() {
/* 119 */     return this.exe2;
/*     */   }
/*     */ 
/*     */   public void setExe2(BigDecimal exe2) {
/* 123 */     this.exe2 = exe2;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_3", precision=22, scale=0)
/*     */   public BigDecimal getExe3() {
/* 129 */     return this.exe3;
/*     */   }
/*     */ 
/*     */   public void setExe3(BigDecimal exe3) {
/* 133 */     this.exe3 = exe3;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_4", precision=22, scale=0)
/*     */   public BigDecimal getExe4() {
/* 139 */     return this.exe4;
/*     */   }
/*     */ 
/*     */   public void setExe4(BigDecimal exe4) {
/* 143 */     this.exe4 = exe4;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_5", precision=22, scale=0)
/*     */   public BigDecimal getExe5() {
/* 149 */     return this.exe5;
/*     */   }
/*     */ 
/*     */   public void setExe5(BigDecimal exe5) {
/* 153 */     this.exe5 = exe5;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_6", precision=22, scale=0)
/*     */   public BigDecimal getExe6() {
/* 159 */     return this.exe6;
/*     */   }
/*     */ 
/*     */   public void setExe6(BigDecimal exe6) {
/* 163 */     this.exe6 = exe6;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_7", precision=22, scale=0)
/*     */   public BigDecimal getExe7() {
/* 169 */     return this.exe7;
/*     */   }
/*     */ 
/*     */   public void setExe7(BigDecimal exe7) {
/* 173 */     this.exe7 = exe7;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_8", precision=22, scale=0)
/*     */   public BigDecimal getExe8() {
/* 179 */     return this.exe8;
/*     */   }
/*     */ 
/*     */   public void setExe8(BigDecimal exe8) {
/* 183 */     this.exe8 = exe8;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_9", precision=22, scale=0)
/*     */   public BigDecimal getExe9() {
/* 189 */     return this.exe9;
/*     */   }
/*     */ 
/*     */   public void setExe9(BigDecimal exe9) {
/* 193 */     this.exe9 = exe9;
/*     */   }
/*     */ 
/*     */   @Column(name="EXE_10", precision=22, scale=0)
/*     */   public BigDecimal getExe10() {
/* 199 */     return this.exe10;
/*     */   }
/*     */ 
/*     */   public void setExe10(BigDecimal exe10) {
/* 203 */     this.exe10 = exe10;
/*     */   }
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.DwContractAssignType
 * JD-Core Version:    0.6.0
 */