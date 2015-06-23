package com.wonders.stpt.doneConfig.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TDoneConfigUserDao;
import com.wonders.stpt.doneConfig.model.TDoneConfigUser;
@Repository("doneConfigUserDao")
public class TDoneConfigUserDaoImpl implements TDoneConfigUserDao {

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
	public TDoneConfigUser save(TDoneConfigUser doneConfigUser)
			throws Exception {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(doneConfigUser);
		return doneConfigUser;
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		getSession().createQuery("update TDoneConfigUser where removed=:removed and id in (:ids)").setString("removed", "0").setParameterList("ids", ids);
		return 0;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.delete(new String[]{id});
	}

	@Override
	public TDoneConfigUser findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (TDoneConfigUser)getSession().get(TDoneConfigUser.class, id);
	}

	@Override
	public PageResultSet<TDoneConfigUser> find(TDoneConfigUser doneConfigUser,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TDoneConfigUser> findList(String hql, Object[] obj)
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

}
