/*    */ package com.wonders.stpt.contract.dao.impl;
/*    */ 
/*    */ import com.wonders.stpt.contract.dao.DwContractBaseinfoDao;
/*    */ import com.wonders.stpt.contract.entity.bo.DwContractBaseinfo;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.SessionFactory;
/*    */ import org.springframework.orm.hibernate3.HibernateTemplate;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("dwContractBaseInfoDao")
/*    */ public class DwContractBaseInfoDaoImpl
/*    */   implements DwContractBaseinfoDao
/*    */ {
/*    */   private HibernateTemplate hibernateTemplate;
/*    */ 
/*    */   public HibernateTemplate getHibernateTemplate()
/*    */   {
/* 21 */     return this.hibernateTemplate;
/*    */   }
/*    */   @Resource(name="hibernateTemplate")
/*    */   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
/* 26 */     this.hibernateTemplate = hibernateTemplate;
/*    */   }
/*    */ 
/*    */   public DwContractBaseinfo findByType(String type)
/*    */   {
/* 31 */     if ((type == null) || ("".equals(type))) return null;
/* 32 */     String hql = "from DwContractBaseinfo t where t.type='" + type + "'";
/* 33 */     Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
/* 34 */     Query query = session.createQuery(hql).setMaxResults(1);
/* 35 */     List list = query.list();
/* 36 */     if ((list == null) || (list.size() < 1)) return null;
/* 37 */     return (DwContractBaseinfo)list.get(0);
/*    */   }
/*    */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.impl.DwContractBaseInfoDaoImpl
 * JD-Core Version:    0.6.0
 */