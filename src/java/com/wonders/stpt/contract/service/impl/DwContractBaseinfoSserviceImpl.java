/*    */ package com.wonders.stpt.contract.service.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractBaseinfoDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractBaseinfo;
/*    */ import com.wonders.stpt.contract.service.DwContractBaseinfoService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Propagation;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("dwContractBaseinfoService")
/*    */ @Transactional(value="txManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
/*    */ public class DwContractBaseinfoSserviceImpl
/*    */   implements DwContractBaseinfoService
/*    */ {
/*    */ 
/*    */   @Autowired(required=false)
/*    */   private DwContractBaseinfoDao dwContractBaseinfoDao;
/*    */ 
/*    */   public void setDwContractBaseinfoDao(@Qualifier("dwContractBaseinfoDao") DwContractBaseinfoDao dwContractBaseinfoDao)
/*    */   {
/* 21 */     this.dwContractBaseinfoDao = dwContractBaseinfoDao;
/*    */   }
/*    */ 
/*    */   public DwContractBaseinfo findByType(String type)
/*    */   {
/* 26 */     return this.dwContractBaseinfoDao.findByType(type);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.impl.DwContractBaseinfoSserviceImpl
 * JD-Core Version:    0.6.0
 */