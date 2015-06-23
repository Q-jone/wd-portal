/*    */ package com.wonders.stpt.contract.dao.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractAssignTypeDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractAssignType;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("dwContractAssignTypeDao")
/*    */ public class DwContractAssignTypeDaoImpl
/*    */   implements DwContractAssignTypeDao
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
/*    */   public List<DwContractAssignType> findDwContractAssignType(String assignType, String controlYear, String contractType, String controlCompanyId, String type)
/*    */   {
/* 29 */     String hql = "from DwContractAssignType t where t.assignType = " + assignType + " and t.contractType = " + contractType + 
/* 30 */       " and t.controlYear='" + controlYear + "' ";
/* 31 */     if ((controlCompanyId != null) && (!"".equals(controlCompanyId))) {
/* 32 */       if ("other".equals(controlCompanyId))
/* 33 */         hql = hql + " and t.controlCompanyId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545')";
/*    */       else
/* 35 */         hql = hql + " and t.controlCompanyId ='" + controlCompanyId + "'";
/*    */     }
/*    */     else {
/* 38 */       hql = hql + " and t.controlCompanyId is null or t.controlCompanyId =''";
/*    */     }
/*    */ 
/* 41 */     hql = hql + " order by t.controlYear";
/* 42 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 43 */     Query query = session.createQuery(hql);
/* 44 */     List list = query.list();
/* 45 */     return list;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.impl.DwContractAssignTypeDaoImpl
 * JD-Core Version:    0.6.0
 */