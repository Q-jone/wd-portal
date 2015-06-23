package com.wonders.stpt.doneConfig.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TDoneConfigInfoDao;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
import com.wonders.stpt.doneConfig.model.TDoneConfigInfo;
@Repository("doneConfigInfoDao")
public class TDoneConfigInfoDaoImpl implements TDoneConfigInfoDao {
	private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(TDoneConfigInfoDaoImpl.class);
    public HibernateTemplate getHibernateTemplate(){
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate2")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
	@Override
	public TDoneConfigInfo save(TDoneConfigInfo tDoneConfigInfo)
			throws Exception {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(tDoneConfigInfo);
		return tDoneConfigInfo;
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("update TDoneConfigInfo d set d.removed=:removed where d.id in (:id)").setString("removed", "1").setParameterList("id", ids).executeUpdate();
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.delete(new String[]{id});
	}

	@Override
	public TDoneConfigInfo findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (TDoneConfigInfo)getSession().get(TDoneConfigInfo.class, id);
	}

	@Override
	public PageResultSet<TDoneConfigInfo> find(TDoneConfigInfo tDoneConfigInfo,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TDoneConfigInfo> findByIds(String[] ids, int type)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findByHQL(String hql,Map param)
			throws Exception {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		
		query.setProperties(param);
		logger.info("sad");
		return query.list();
	}

	@Override
	public List<TDoneConfigInfo> findByLoginName(String loginName) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("from TDoneConfigInfo p where removed=:removed and loginName=:loginName").setString("removed", "0").setString("loginName", loginName).list();
	}

	@Override
	public List<TDoneConfigInfo> findByHQL(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		if(obj!=null){
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
			
		}
		return query.list();
	}

	@Override
	public List<TDoneConfigClassic> getType(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		if(obj!=null){
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
		}
		return query.list();
	}

	@Override
	public String getMaxOrders(String hql, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		if(obj!=null){
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
		}
		if(query.list().get(0)==null)
			return "-1";
		return query.list().get(0).toString();
	}

	@Override
	public int delete(String hql, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		if(obj!=null){
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public int deleteByHql(String hql, Map param) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		
		query.setProperties(param);
		
		return query.executeUpdate();
	}

}
