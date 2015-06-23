/*    */ package com.wonders.stpt.contract.service.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractAssignStatsDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractAssignStats;
/*    */ import com.wonders.stpt.contract.service.DwContractAssignStatsService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Repository;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Repository("dwContractAssignStatsService")
/*    */ @Transactional(value="txManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
/*    */ public class DwContractAssignStatsServiceImpl
/*    */   implements DwContractAssignStatsService
/*    */ {
/*    */   private DwContractAssignStatsDao dwContractAssignStatsDao;
/*    */ 
/*    */   @Autowired(required=false)
/*    */   public void setDwContractAssignStatsDao(@Qualifier("dwContractAssignStatsDao") DwContractAssignStatsDao dwContractAssignStatsDao)
/*    */   {
/* 25 */     this.dwContractAssignStatsDao = dwContractAssignStatsDao;
/*    */   }
/*    */ 
/*    */   public List<DwContractAssignStats> findDwContractAssignStats(String assignType, String controlYear, String contractType, String controlCompanyId, String type)
/*    */   {
/* 32 */     return this.dwContractAssignStatsDao.findDwContractAssignStats(assignType, controlYear, contractType, controlCompanyId, type);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.impl.DwContractAssignStatsServiceImpl
 * JD-Core Version:    0.6.0
 */