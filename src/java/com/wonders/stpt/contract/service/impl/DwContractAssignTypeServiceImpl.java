/*    */ package com.wonders.stpt.contract.service.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractAssignTypeDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractAssignType;
/*    */ import com.wonders.stpt.contract.service.DwContractAssignTypeService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("dwContractAssignTypeService")
/*    */ @Transactional(value="txManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
/*    */ public class DwContractAssignTypeServiceImpl
/*    */   implements DwContractAssignTypeService
/*    */ {
/*    */   private DwContractAssignTypeDao dwContractAssignTypeDao;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractAssignTypeDao(@Qualifier("dwContractAssignTypeDao") DwContractAssignTypeDao dwContractAssignTypeDao)
/*    */   {
/* 24 */     this.dwContractAssignTypeDao = dwContractAssignTypeDao;
/*    */   }
/*    */ 
/*    */   public List<DwContractAssignType> findDwContractAssignType(String assignType, String controlYear, String contractType, String controlCompanyId, String type)
/*    */   {
/* 31 */     return this.dwContractAssignTypeDao.findDwContractAssignType(assignType, controlYear, contractType, controlCompanyId, type);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.impl.DwContractAssignTypeServiceImpl
 * JD-Core Version:    0.6.0
 */