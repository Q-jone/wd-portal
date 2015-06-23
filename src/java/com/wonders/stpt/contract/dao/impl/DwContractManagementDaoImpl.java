/*    */ package com.wonders.stpt.contract.dao.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractManagementDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractManagement;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("dwContractManagementDao")
/*    */ public class DwContractManagementDaoImpl
/*    */   implements DwContractManagementDao
/*    */ {
/*    */   private HibernateTemplate hibernateTemplate;
/*    */ 
/*    */   public HibernateTemplate getHibernateTemplate()
/*    */   {
/* 20 */     return this.hibernateTemplate;
/*    */   }
/* 24 */   @Resource(name="hibernateTemplate")
/*    */   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { this.hibernateTemplate = hibernateTemplate;
/*    */   }
/*    */ 
/*    */   public List<DwContractManagement> findDwContractManagement()
/*    */   {
/* 29 */     String hql = "from DwContractManagement t order by t.orderNo";
/* 30 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 31 */     Query query = session.createQuery(hql);
/* 32 */     List list = query.list();
/* 33 */     return list;
/*    */   }
/*    */ 
/*    */   public List<DwContractManagement> findDwContractManagement(String companyId)
/*    */   {
/* 38 */     String hql = "";
/* 39 */     if ((companyId != null) && (!"".equals(companyId)))
/* 40 */       hql = "from DwContractManagement t where t.companyId ='" + companyId + "' order by t.orderNo";
/*    */     else {
/* 42 */       hql = "from DwContractManagement t where t.companyId is null order by t.orderNo";
/*    */     }
/* 44 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 45 */     Query query = session.createQuery(hql);
/* 46 */     List list = query.list();
/* 47 */     return list;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.impl.DwContractManagementDaoImpl
 * JD-Core Version:    0.6.0
 */