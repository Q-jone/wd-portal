/*    */ package com.wonders.stpt.contract.service.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractPriceNumberDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractPriceNumber;
/*    */ import com.wonders.stpt.contract.service.DwContractPriceNumberService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Repository;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Repository("dwContractPriceNumberService")
/*    */ @Transactional(value="txManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
/*    */ public class DwContractPriceNumberServiceImpl
/*    */   implements DwContractPriceNumberService
/*    */ {
/*    */   private DwContractPriceNumberDao dwContractPriceNumberDao;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractPriceNumberDao(@Qualifier("dwContractPriceNumberDao") DwContractPriceNumberDao dwContractPriceNumberDao)
/*    */   {
/* 23 */     this.dwContractPriceNumberDao = dwContractPriceNumberDao;
/*    */   }
/*    */ 
/*    */   public List<DwContractPriceNumber> findDwContractPriceNumber(String controlYear, int controlType, String companyId, String type) {
/* 27 */     return this.dwContractPriceNumberDao.findDwContractPriceNumber(controlYear, controlType, companyId, type);
/*    */   }
/*    */ 
/*    */   public List<DwContractPriceNumber> findDwContractPriceNumberThisMonth(String contractType, String companyId, String dataType) {
/* 31 */     return this.dwContractPriceNumberDao.findDwContractPriceNumberThisMonth(contractType, companyId, dataType);
/*    */   }
/*    */ 
/*    */   public List<DwContractPriceNumber> findDwContractPriceNumberSum(String controlYear, String contractType, String companyId, String dataType) {
/* 35 */     return this.dwContractPriceNumberDao.findDwContractPriceNumberSum(controlYear, contractType, companyId, dataType);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.impl.DwContractPriceNumberServiceImpl
 * JD-Core Version:    0.6.0
 */