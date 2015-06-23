/*    */ package com.wonders.stpt.contract.dao.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.ContractDao;
/*    */ import com.wonders.stpt.contract.entity.bo.Contract;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("contractDao")
/*    */ public class ContractDaoImpl
/*    */   implements ContractDao
/*    */ {
/*    */   private HibernateTemplate hibernateTemplate;
/*    */ 
/*    */   public HibernateTemplate getHibernateTemplate()
/*    */   {
/* 19 */     return this.hibernateTemplate;
/*    */   }
/* 23 */   @Resource(name="hibernateTemplate")
/*    */   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { this.hibernateTemplate = hibernateTemplate;
/*    */   }
/*    */ 
/*    */   public List<Contract> findByContractNoAndContractType(String contractNo, String contractType)
/*    */   {
/* 28 */     if ((contractNo == null) || ("".equals(contractNo))) return null;
/* 29 */     String hql = "from Contract t where t.removed='0' and t.contractNo like '%" + contractNo + "%'";
/* 30 */     if ((contractType != null) && (!"".equals(contractType))) {
/* 31 */       hql = hql + " and t.contractType like '" + contractType + "%'";
/*    */     }
/* 33 */     return getHibernateTemplate().find(hql);
/*    */   }
/*    */ 
/*    */   public List<Contract> findByContractTypeAndContractOwnerExecuteId(String contractType, String executeId)
/*    */   {
/* 38 */     String hql = "from Contract t where t.removed='0' and t.contractType like '" + contractType + "%' and t.contractOwnerExecuteId='" + executeId + "'";
/* 39 */     return getHibernateTemplate().find(hql);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.impl.ContractDaoImpl
 * JD-Core Version:    0.6.0
 */