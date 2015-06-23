/*    */ package com.wonders.stpt.contract.dao.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractPriceNumberDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractPriceNumber;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("dwContractPriceNumberDao")
/*    */ public class DwContractPriceNumberDaoImpl
/*    */   implements DwContractPriceNumberDao
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
/*    */   public List<DwContractPriceNumber> findDwContractPriceNumber(String controlYear, int controlType, String companyId, String type)
/*    */   {
/* 30 */     String hql = "from DwContractPriceNumber t where t.dataType = 1 and t.contractType = " + controlType;
/* 31 */     if (!"".equals(companyId)) {
/* 32 */       if ("other".equals(companyId)) {
/* 33 */         hql = hql + " and t.companyId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545')";
/*    */       }
/*    */       else
/*    */       {
/* 37 */         hql = hql + " and t.companyId = '" + companyId + "'";
/*    */       }
/*    */     }
/* 40 */     if ("default".equals(type))
/* 41 */       hql = hql + " and t.controlDate < to_char(sysdate,'YYYY-MM-DD') and t.controlDate > to_char(add_months(sysdate,-12),'YYYY-MM-DD') ";
/* 42 */     else if ("month".equals(type))
/* 43 */       hql = hql + " and t.controlDate like '%" + controlYear + "%' ";
/* 44 */     else if ("year".equals(type)) {
/* 45 */       hql = hql + " and t.controlDate < '" + (Integer.valueOf(controlYear).intValue() + 1) + "' ";
/*    */     }
/* 47 */     hql = hql + " order by t.controlDate ";
/* 48 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 49 */     Query query = session.createQuery(hql);
/* 50 */     List list = query.list();
/* 51 */     return list;
/*    */   }
/*    */ 
/*    */   public List<DwContractPriceNumber> findDwContractPriceNumberThisMonth(String contractType, String companyId, String dataType)
/*    */   {
/* 57 */     String hql = "from DwContractPriceNumber t where t.dataType = " + dataType + " and t.controlDate = to_char(sysdate,'YYYY-MM') and t.contractType = " + contractType;
/* 58 */     if (("3".equals(dataType)) || ("4".equals(dataType))) {
/* 59 */       hql = "from DwContractPriceNumber t where t.dataType = " + dataType;
/*    */     }
/* 61 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 62 */     Query query = session.createQuery(hql);
/* 63 */     List list = query.list();
/* 64 */     return list;
/*    */   }
/*    */ 
/*    */   public List<DwContractPriceNumber> findDwContractPriceNumberSum(String controlYear, String contractType, String companyId, String dataType)
/*    */   {
/* 70 */     String hql = "from DwContractPriceNumber t where t.dataType = " + dataType + " and t.contractType = " + contractType;
/* 71 */     Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
/* 72 */     Query query = session.createQuery(hql);
/* 73 */     List list = query.list();
/* 74 */     return list;
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.impl.DwContractPriceNumberDaoImpl
 * JD-Core Version:    0.6.0
 */