/*    */ package com.wonders.stpt.contract.service.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.ContractDao;
/*    */ import com.wonders.stpt.contract.entity.bo.Contract;
/*    */ import com.wonders.stpt.contract.service.ContractService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("contractService")
/*    */ public class ContractServiceImpl
/*    */   implements ContractService
/*    */ {
/*    */ 
/*    */   @Autowired(required=false)
/*    */   private ContractDao contractDao;
/*    */ 
/*    */   public void setContractDao(@Qualifier("contractDao") ContractDao contractDao)
/*    */   {
/* 20 */     this.contractDao = contractDao;
/*    */   }
/*    */ 
/*    */   public List<Contract> findByContractNoAndContractType(String contractNo, String contractType)
/*    */   {
/* 25 */     return this.contractDao.findByContractNoAndContractType(contractNo, contractType);
/*    */   }
/*    */ 
/*    */   public List<Contract> findByContractTypeAndContractOwnerExecuteId(String contractType, String executeId)
/*    */   {
/* 31 */     return this.contractDao.findByContractTypeAndContractOwnerExecuteId(contractType, executeId);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.impl.ContractServiceImpl
 * JD-Core Version:    0.6.0
 */