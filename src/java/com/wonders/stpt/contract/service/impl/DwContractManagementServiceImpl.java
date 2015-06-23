/*    */ package com.wonders.stpt.contract.service.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractManagementDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractManagement;
/*    */ import com.wonders.stpt.contract.service.DwContractManagementService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Repository;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Repository("dwContractManagementService")
/*    */ @Transactional(value="txManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
/*    */ public class DwContractManagementServiceImpl
/*    */   implements DwContractManagementService
/*    */ {
/*    */   private DwContractManagementDao dwContractManagementDao;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractManagementDao(@Qualifier("dwContractManagementDao") DwContractManagementDao dwContractManagementDao)
/*    */   {
/* 24 */     this.dwContractManagementDao = dwContractManagementDao;
/*    */   }
/*    */ 
/*    */   public List<DwContractManagement> findDwContractManagement()
/*    */   {
/* 29 */     return this.dwContractManagementDao.findDwContractManagement();
/*    */   }
/*    */ 
/*    */   public List<DwContractManagement> findDwContractManagement(String companyId)
/*    */   {
/* 35 */     return this.dwContractManagementDao.findDwContractManagement(companyId);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.impl.DwContractManagementServiceImpl
 * JD-Core Version:    0.6.0
 */