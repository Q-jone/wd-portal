/*    */ package com.wonders.stpt.contract.entity.bo;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import org.hibernate.annotations.GenericGenerator;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="DW_CONTRACT_P_MANAGEMENT")
/*    */ public class DwContractManagement
/*    */ {
/*    */   private String id;
/*    */   private String name;
/*    */   private BigDecimal value;
/*    */   private String createDate;
/*    */   private BigDecimal orderNo;
/*    */   private String url;
/*    */   private String companyId;
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="system-uuid")
/*    */   @GenericGenerator(name="system-uuid", strategy="uuid")
/*    */   @Column(name="ID", nullable=false, length=40)
/*    */   public String getId()
/*    */   {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 34 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @Column(name="NAME", length=200)
/*    */   public String getName() {
/* 40 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 44 */     this.name = name;
/*    */   }
/*    */ 
/*    */   @Column(name="VALUE", precision=22, scale=0)
/*    */   public BigDecimal getValue() {
/* 50 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(BigDecimal value) {
/* 54 */     this.value = value;
/*    */   }
/*    */ 
/*    */   @Column(name="CREATE_DATE")
/*    */   public String getCreateDate() {
/* 60 */     return this.createDate;
/*    */   }
/*    */ 
/*    */   public void setCreateDate(String createDate) {
/* 64 */     this.createDate = createDate;
/*    */   }
/*    */ 
/*    */   @Column(name="ORDER_NO", precision=22, scale=0)
/*    */   public BigDecimal getOrderNo() {
/* 70 */     return this.orderNo;
/*    */   }
/*    */ 
/*    */   public void setOrderNo(BigDecimal orderNo) {
/* 74 */     this.orderNo = orderNo;
/*    */   }
/*    */   @Column(name="URL", length=400)
/*    */   public String getUrl() {
/* 79 */     return this.url;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 83 */     this.url = url;
/*    */   }
/*    */   @Column(name="COMPANY_ID", length=100)
/*    */   public String getCompanyId() {
/* 88 */     return this.companyId;
/*    */   }
/*    */ 
/*    */   public void setCompanyId(String companyId) {
/* 92 */     this.companyId = companyId;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.bo.DwContractManagement
 * JD-Core Version:    0.6.0
 */