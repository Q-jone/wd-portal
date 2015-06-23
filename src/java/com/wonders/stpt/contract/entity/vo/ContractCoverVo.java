/*    */ package com.wonders.stpt.contract.entity.vo;
/*    */ 
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractCover;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ContractCoverVo
/*    */ {
/*    */   private DwContractCover all;
/*    */   private DwContractCover other;
/*    */   private List<DwContractCover> company1List;
/*    */   private List<DwContractCover> company2List;
/* 15 */   private Map<String, String> map = new HashMap();
/*    */ 
/*    */   public DwContractCover getAll() {
/* 18 */     return this.all;
/*    */   }
/*    */   public void setAll(DwContractCover all) {
/* 21 */     this.all = all;
/*    */   }
/*    */   public List<DwContractCover> getCompany1List() {
/* 24 */     return this.company1List;
/*    */   }
/*    */   public void setCompany1List(List<DwContractCover> company1List) {
/* 27 */     this.company1List = company1List;
/*    */   }
/*    */   public List<DwContractCover> getCompany2List() {
/* 30 */     return this.company2List;
/*    */   }
/*    */   public void setCompany2List(List<DwContractCover> company2List) {
/* 33 */     this.company2List = company2List;
/*    */   }
/*    */   public DwContractCover getOther() {
/* 36 */     return this.other;
/*    */   }
/*    */   public void setOther(DwContractCover other) {
/* 39 */     this.other = other;
/*    */   }
/*    */   public Map<String, String> getMap() {
/* 42 */     return this.map;
/*    */   }
/*    */   public void setMap(Map<String, String> map) {
/* 45 */     this.map = map;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.entity.vo.ContractCoverVo
 * JD-Core Version:    0.6.0
 */