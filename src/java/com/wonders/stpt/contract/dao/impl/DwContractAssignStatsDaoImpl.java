/*    */ package com.wonders.stpt.contract.dao.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractAssignStatsDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractAssignStats;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("dwContractAssignStatsDao")
/*    */ public class DwContractAssignStatsDaoImpl
/*    */   implements DwContractAssignStatsDao
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
/*    */   public List<DwContractAssignStats> findDwContractAssignStats(String assignType, String controlYear, String contractType, String controlCompanyId, String type)
/*    */   {
/* 31 */     String hql = "from DwContractAssignStats t where t.assignType = " + assignType + " and t.contractType = " + contractType + " and t.controlDate like '" + controlYear + "%'";
/*    */ 
/* 39 */     if ((controlCompanyId != null) && (!"".equals(controlCompanyId))) {
/* 40 */       if ("other".equals(controlCompanyId))
/* 41 */         hql = hql + " and t.controlCompanyId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545')";
/*    */       else
/* 43 */         hql = hql + " and t.controlCompanyId ='" + controlCompanyId + "'";
/*    */     }
/*    */     else {
/* 46 */       hql = hql + " and t.controlCompanyId is null or t.controlCompanyId =''";
/*    */     }
/* 48 */     hql = hql + " order by t.controlDate";
/* 49 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 50 */     Query query = session.createQuery(hql);
/* 51 */     List list = query.list();
/* 52 */     return list;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.impl.DwContractAssignStatsDaoImpl
 * JD-Core Version:    0.6.0
 */