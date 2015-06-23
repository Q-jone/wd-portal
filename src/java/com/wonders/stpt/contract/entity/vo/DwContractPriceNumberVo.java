/*    */ package com.wonders.stpt.contract.entity.vo;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DwContractPriceNumberVo
/*    */ {
/*    */   private List<BigDecimal> contractPrice;
/*    */   private List<BigDecimal> contractNum;
/*    */   private List<String> controlDate;
/*    */   private String yearMoney;
/*    */   private String yearCount;
/*    */ 
/*    */   public List<BigDecimal> getContractPrice()
/*    */   {
/* 11 */     return this.contractPrice;
/*    */   }
/*    */   public void setContractPrice(List<BigDecimal> contractPrice) {
/* 14 */     this.contractPrice = contractPrice;
/*    */   }
/*    */   public List<BigDecimal> getContractNum() {
/* 17 */     return this.contractNum;
/*    */   }
/*    */   public void setContractNum(List<BigDecimal> contractNum) {
/* 20 */     this.contractNum = contractNum;
/*    */   }
/*    */   public List<String> getControlDate() {
/* 23 */     return this.controlDate;
/*    */   }
/*    */   public void setControlDate(List<String> controlDate) {
/* 26 */     this.controlDate = controlDate;
/*    */   }
/*    */   public String getYearMoney() {
/* 29 */     return this.yearMoney;
/*    */   }
/*    */   public void setYearMoney(String yearMoney) {
/* 32 */     this.yearMoney = yearMoney;
/*    */   }
/*    */   public String getYearCount() {
/* 35 */     return this.yearCount;
/*    */   }
/*    */   public void setYearCount(String yearCount) {
/* 38 */     this.yearCount = yearCount;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.vo.DwContractPriceNumberVo
 * JD-Core Version:    0.6.0
 */