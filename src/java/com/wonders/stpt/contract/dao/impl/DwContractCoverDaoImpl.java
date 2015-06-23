/*    */package com.wonders.stpt.contract.dao.impl;

/*    */
/*    */import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wonders.stpt.contract.dao.DwContractCoverDao;
import com.wonders.stpt.contract.entity.bo.CoverAlarm;
import com.wonders.stpt.contract.entity.bo.CoverContractBid;
import com.wonders.stpt.contract.entity.bo.CoverContractPrice;
import com.wonders.stpt.contract.entity.bo.CoverManagement;
import com.wonders.stpt.contract.entity.bo.CoverProjectContractStat; /*    */
import com.wonders.stpt.contract.entity.bo.DwContractCover;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar; /*    */
import java.util.List; /*    */
import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException; /*    */
import org.hibernate.Query;
import org.hibernate.SQLQuery; /*    */
import org.hibernate.Session; /*    */
import org.hibernate.SessionFactory; /*    */
import org.hibernate.Transaction; /*    */
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/*    */
/*    */@Repository("dwContractCoverDao")
/*    */public class DwContractCoverDaoImpl
/*    */implements DwContractCoverDao
/*    */{
	/*    */private HibernateTemplate hibernateTemplate;
			private HibernateTemplate hibernateTemplate2;

			
			public HibernateTemplate getHibernateTemplate2() {
				return hibernateTemplate2;
			}

			@Resource(name = "hibernateTemplate2")
			public void setHibernateTemplate2(HibernateTemplate hibernateTemplate2) {
				this.hibernateTemplate2 = hibernateTemplate2;
			}

	/*    */
	/*    */public HibernateTemplate getHibernateTemplate()
	/*    */{
		/* 43 */return this.hibernateTemplate;
		/*    */}

	/* 47 */@Resource(name = "hibernateTemplate")
	/*    */public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
		/*    */}

	/*    */
	/*    */public List<String> findAllContractByType(String type)
	/*    */{
		/* 52 */String hql = "select t.contractOwnerExecuteName,count(t.contractOwnerExecuteName) from Contract t where t.removed='0' and t.contractType like '%"
				+
				/* 53 */type
				+ "%' and t.contractOwnerExecuteName is not null group by t.contractOwnerExecuteName";
		/* 54 */return getHibernateTemplate().find(hql);
		/*    */}

	/*    */
	/*    */public List<DwContractCover> findByContractType(String contractType)
	/*    */{
		/* 59 */String hql = "from DwContractCover t where t.contractType='"
				+ contractType + "'";
		/* 60 */return getHibernateTemplate().find(hql);
		/*    */}

	/*    */
	/*    */public List<DwContractCover> findByCompanyType(String companyType)
	/*    */{
		/* 65 */String hql = "from DwContractCover t where t.companyType='"
				+ companyType + "' order by t.companyId ASC";
		/* 66 */return getHibernateTemplate().find(hql);
		/*    */}

	/*    */
	/*    */public List<DwContractCover> findByContractTypeWithFuzzySearch(
			String contractType)
	/*    */{
		/* 71 */String hql = "from DwContractCover t where t.contractType like '"
				+ contractType + "%' order by t.contractType ASC";
		/* 72 */return getHibernateTemplate().find(hql);
		/*    */}

	/*    */
	/*    */public List<Object> executeSql(String sql) {
		/* 76 */List list = null;
		/* 77 */Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		/* 78 */Transaction tx = session.beginTransaction();
		/* 79 */Query query = session.createSQLQuery(sql);
		/* 80 */list = query.list();
		/*    */
		/* 82 */tx.commit();
		/* 83 */session.close();
		/*    */
		/* 85 */return list;
		/*    */}

	@Override
	public List<CoverContractPrice> findCoverContractPrice(String type) {
		String hql = "from CoverContractPrice t ";
		if (type.equals("month")) {
			hql += " where t.controlDate < to_char(sysdate,'YYYY-MM-DD') and t.controlDate > to_char(add_months(sysdate,-12),'YYYY-MM-DD')";
		} else if (type.equals("year")) {
			hql += " where t.controlDate <= to_char(sysdate,'YYYY-MM-DD')";
		}
		hql += " order by t.controlDate ASC";
		List<CoverContractPrice> list = getHibernateTemplate().find(hql);
		;
		return list;
	}

	@Override
	public CoverProjectContractStat findCoverProjectContractStat() {
		String hql = "from CoverProjectContractStat";
		List<CoverProjectContractStat> list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Long> findProjectCountByDate(String date, int dayLength) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "";
		Calendar c = Calendar.getInstance();
		for (int i = dayLength - 1; i >= 0; i--) {
			try {
				c.setTime(sdf.parse(date));
				c.add(Calendar.DAY_OF_MONTH, -i);
				if (i == dayLength - 1) {
					sql += "select count(*) from c_project t where t.approval_date = '"
						+ sdf.format(c.getTime()) + "' and t.removed='0'";
				}else{
					sql += " union all select count(*) from c_project t where t.approval_date = '"
						+ sdf.format(c.getTime()) + "' and t.removed='0'";
				}
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Session session = null;
		List<Long> list = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			list = session.createSQLQuery(sql).list();
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public List<Double> findProjectPriceByDate(String date, int dayLength) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "";
		Calendar c = Calendar.getInstance();
		for (int i = dayLength - 1; i >= 0; i--) {
			try {
				c.setTime(sdf.parse(date));
				c.add(Calendar.DAY_OF_MONTH, -i);
				if (i == dayLength - 1) {
					sql += "select nvl(sum(t.project_budget_all),0) price from c_project t where t.approval_date = '"
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				} else {
					sql += " union all select nvl(sum(t.project_budget_all),0) price from c_project t where t.approval_date = '"
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Session session = null;
		List<Double> list = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("price", Hibernate.DOUBLE);
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public List<Long> findContractCountByDate(String date, int dayLength) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "";
		Calendar c = Calendar.getInstance();
		for (int i = dayLength - 1; i >= 0; i--) {
			try {
				c.setTime(sdf.parse(date));
				c.add(Calendar.DAY_OF_MONTH, -i);
				if (i == dayLength - 1) {
					sql += "select count(*) from c_contract t where t.contract_signed_date = '"
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				} else {
					sql += " union all select count(*) from c_contract t where t.contract_signed_date = ' "
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Session session = null;
		List<Long> list = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			list = session.createSQLQuery(sql).list();
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public List<Double> findContractPriceByDate(String date, int dayLength) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "";
		Calendar c = Calendar.getInstance();
		for (int i = dayLength - 1; i >= 0; i--) {
			try {
				c.setTime(sdf.parse(date));
				c.add(Calendar.DAY_OF_MONTH, -i);
				if (i == dayLength - 1) {
					sql += " select sum(t.contract_price) price from c_contract t where t.contract_signed_date = '"
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				} else {
					sql += " union all select sum(t.contract_price) price from c_contract t where t.contract_signed_date = ' "
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Session session = null;
		List<Double> list = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("price", Hibernate.DOUBLE);
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public List<Double> findChangedPriceByDate(String date, int dayLength,
			String type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "";
		Calendar c = Calendar.getInstance();
		for (int i = dayLength - 1; i >= 0; i--) {
			try {
				c.setTime(sdf.parse(date));
				c.add(Calendar.DAY_OF_MONTH, -i);
				if (i == dayLength - 1) {
					sql += "select nvl(sum(t.money),0) price from C_CONTRACT_STATUS t where t.operate_date = '"
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				} else {
					sql += " union all select nvl(sum(t.money),0) price from C_CONTRACT_STATUS t where t.operate_date = '"
							+ sdf.format(c.getTime()) + "' and t.removed='0'";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Session session = null;
		List<Double> list = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("price", Hibernate.DOUBLE);
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}
	
	@Override
	public Object[] findContractPriceAndCountByYearMonth(String yearMonth) {
		String sql = "select nvl(count(*),0) c,nvl(sum(t.contract_price),0) p from c_contract t where t.contract_signed_date like '"
				+ yearMonth + "%' and t.removed='0'";
		List<Object[]> list = null ;
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("c", Hibernate.LONG).addScalar("p", Hibernate.DOUBLE);
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(session!=null) session.close();
		}
		if (list != null && list.size() == 1)
			return list.get(0);
		return null;
	}

	@Override
	public CoverContractPrice findCoverContractPriceByControlDate(
			String controlDate) {
		String hql = "from CoverContractPrice t where t.controlDate =?";
		Session session = null;
		
		Query query;
		CoverContractPrice result = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			query = session.createQuery(hql);
			query.setString(0, controlDate);
			result =(CoverContractPrice) query.uniqueResult(); 
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(session!=null) session.close();
		}
		return result; 
	}

	@Override
	public void saveOrUpdateAllCoverContractPrice(
			List<CoverContractPrice> coverContractPriceList) {
		getHibernateTemplate().saveOrUpdateAll(coverContractPriceList);
	}

	@Override
	public Object[] findProjectCountAndPriceByDate(String date) {
		String sql= "select nvl(count(*),0) c, nvl(sum(t.project_budget_all),0) p from c_project t where t.approval_date like '"+date+"%' and t.removed='0'";
		Session session = null;
		Object[] result = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("c", Hibernate.LONG).addScalar("p", Hibernate.DOUBLE);
			result = (Object[]) query.uniqueResult(); 
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(session!=null) session.close();
		}
		return result;
	}

	@Override
	public Object[] findContractCountAndPriceByDate(String date) {
		String sql= "select nvl(count(*),0) c, nvl(sum(t.contract_price),0) p from c_contract t where t.contract_signed_date like '"+date+"%' and t.removed='0'";
		Session session = null;
		Object[] result = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("c", Hibernate.LONG).addScalar("p", Hibernate.DOUBLE);
			result = (Object[]) query.uniqueResult(); 
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(session!=null) session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Double> findContractStatusMoneyByTypeAndDate(String yearMonth, String year,String type) {
		String sql= "select nvl(sum(t.money),0) money from c_contract_status t where t.operate_date like '"+yearMonth+"%' and t.removed='0' and t.type='"+type+"'";
		sql+=" union all select nvl(sum(t.money),0) from c_contract_status t where t.operate_date like '"+year+"%' and t.removed='0' and t.type='"+type+"'";
		Session session = null;
		List<Double> result = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("money",Hibernate.DOUBLE);
			result =  query.list(); 
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			if(session!=null) session.close();
		}
		return result;
	}

	@Override
	public void saveOrUpdate(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoverManagement> findCoverManagement() {
		String hql="from CoverManagement t order by t.companyId ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Long> executeSQLByToFindCoverManagement(String sql) {
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("result", Hibernate.LONG);
			tx.commit();
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public List<Object[]> executeSqlWithResult(String sql) {
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			tx.commit();
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public void executeSQLWithNoResult(String sql) {
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			tx.commit();
			query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
	}

	@Override
	public Object executeSQLWithOneData(String sql) {
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			tx.commit();
			List<Object> result = query.list();
			if(result!=null && result.size()>0) return result.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	
	
	@Override
	public Object executeSQLWithOneDataByDatasource2(String sql) {
		Session session =null;
		try {
			session = hibernateTemplate2.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			tx.commit();
			List<Object> result = query.list();
			if(result!=null && result.size()>0) return result.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public List<Object[]> findContractBidByCompanyAndDate(String companyId,
			String yearMonth) {
		String sql="select count(*) ct,nvl(sum(c.contract_price),0) price from c_contract c where c.removed='0' and c.invite_bid_type='1' and c.contract_owner_execute_id='"+companyId+"' and c.contract_signed_date like '"+yearMonth+"%'";
		sql+="union all select count(*),nvl(sum(c.contract_price),0) from c_contract c where c.removed='0' and c.invite_bid_type='2' and c.contract_owner_execute_id='"+companyId+"' and c.contract_signed_date like '"+yearMonth+"%'";
		sql+="union all select count(*),nvl(sum(c.contract_price),0) from c_contract c where c.removed='0' and c.invite_bid_type='3' and c.contract_owner_execute_id='"+companyId+"' and c.contract_signed_date like '"+yearMonth+"%'";
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("ct",Hibernate.LONG).addScalar("price", Hibernate.DOUBLE);
			List<Object> t = query.list();
			List<Object[]> result = query.list();
			if(result!=null && result.size()>0) return result;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public List<Double> findChangedPriceByCompanyIdAndDate(String companyId,String yearMonth) {
		String sql="select nvl(sum(s.money),0) money from c_contract_status s where s.contract_id in (" +
				"select c.id from c_contract c where c.removed='0' and c.contract_owner_execute_id='"+companyId+"' and c.contract_signed_date like '"+yearMonth+"%' and c.invite_bid_type='1')" ;
		sql+="union all select nvl(sum(s.money),0) from c_contract_status s where s.contract_id in (" +
		"select c.id from c_contract c where c.removed='0' and c.contract_owner_execute_id='"+companyId+"' and c.contract_signed_date like '"+yearMonth+"%' and c.invite_bid_type='2')" ;
		sql+="union all select nvl(sum(s.money),0) from c_contract_status s where s.contract_id in (" +
		"select c.id from c_contract c where c.removed='0' and c.contract_owner_execute_id='"+companyId+"' and c.contract_signed_date like '"+yearMonth+"%' and c.invite_bid_type='3')" ;
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("money", Hibernate.DOUBLE);
			tx.commit();
			List<Double> result = query.list();
			if(result!=null && result.size()>0) return result;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public CoverContractBid findCoverContractBidByDateAndCompany(String controlDate,String companyId) {
		String hql="from CoverContractBid t where t.controlDate='"+controlDate+"' and t.companyId='"+companyId+"'";
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			CoverContractBid bid = (CoverContractBid) query.uniqueResult();
			tx.commit();
			return bid;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public void saveOrUpdateAllCoverContractBid(List<CoverContractBid> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public List<Object[]> findCoverContractBidCountByDateList(
			List<String> yearMonthList) {
		if(yearMonthList==null) return null;
		String sql="";
		for(int i=0;i<yearMonthList.size();i++){
			if(i!=0){
				sql +=" union all ";
				sql +="select sum(t.bid1_count),sum(t.bid2_count) ,sum(t.bid3_count)  from Cover_Contract_Bid t where t.control_Date = '"+yearMonthList.get(i)+"'";
			}else{
				sql +="select sum(t.bid1_count) c1,sum(t.bid2_count) c2,sum(t.bid3_count) c3 from Cover_Contract_Bid t where t.control_Date = '"+yearMonthList.get(i)+"'";
			}
			
		}
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery(sql);
			query.addScalar("c1",Hibernate.LONG).addScalar("c2", Hibernate.LONG).addScalar("c3",Hibernate.LONG);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public Object[] findCoverContractBidByYearAndCompany(String year,String companyId) {
		String sql="select sum(t.bid1_count),sum(t.bid1_money),sum(t.bid2_count),sum(t.bid2_money),sum(t.bid3_count),sum(t.bid3_money) " +
				"from cover_contract_bid t where t.control_date like '"+year+"%' and t.company_id ='"+companyId+"'";
		Session session =null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery(sql);
			List<Object[]> result = query.list();
			if(result!=null && result.size()>0)
			return result.get(0);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			if(session!=null) session.close();
		}
		return null;
	}

	@Override
	public CoverAlarm findCoverAlarm() {
		String hql="from CoverAlarm";
		List<CoverAlarm> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}


	/*    */
}

/*
 * Location: X:\app\portal\WEB-INF\classes\ Qualified Name:
 * com.wonders.stpt.contract.dao.impl.DwContractCoverDaoImpl JD-Core Version:
 * 0.6.0
 */