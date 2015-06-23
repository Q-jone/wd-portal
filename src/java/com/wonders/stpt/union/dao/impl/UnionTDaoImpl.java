package com.wonders.stpt.union.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.util.DbUtil;
@SuppressWarnings("unchecked")
@Repository("unionTDao")
public class UnionTDaoImpl<T>  implements UnionTDao<T>{
	
private HibernateTemplate hibernateTemplate;
private DbUtil dbUtil;
public  DbUtil getDbUtil() {
	return dbUtil;
}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public int updateBySql(String sql){
		return dbUtil.getJdbcTemplate().update(sql);
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Override
	public T find(String id,Class<T> c) {
		
		String hql = "from "+c.getName()+" t where t.removed=0 and id = ? ";
		List<T> list = this.hibernateTemplate.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public T find(Long id,Class<T> c) {
		
		String hql = "from "+c.getName()+" t where t.id = ? ";
		List<T> list = this.hibernateTemplate.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<T> findByHql(String hql) {
		
		return this.hibernateTemplate.find(hql);
	}
	
	@Override
	public List<T> findByHql(String hql,Object[] obj) {
		return this.hibernateTemplate.find(hql,obj);
	}
	
	@Override
	public T save(T t) {
		this.hibernateTemplate.save(t);
		return t;
	}
	@Override
	public T update(T t) {
		this.hibernateTemplate.update(t);
		return t;
	}
	@Override
	public void saveOrUpdateAll(Collection cols){
		this.hibernateTemplate.saveOrUpdateAll(cols);
	}
	@Override
	public PageResultSet findPageResult(String querySql,String countSql,
			int pageNo, int pageSize){
		PageResultSet result = new PageResultSet();
		
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(countSql);
		int totalRows = Integer.valueOf(query.uniqueResult().toString());
		
		List<T> list = this.findByPage(querySql,(pageNo-1)*pageSize,pageSize);
		
		result.setList(list);
		result.setPageInfo(new PageInfo(totalRows, pageSize, pageNo));

		return result;
	}
	
	@Override
	public int countByHql(String queryHql){
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryHql);
		if(query.uniqueResult() == null){
			return 0;
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public List<Map> findBySql(String sql) {
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map> list = (List<Map>)query.list();
		return list;
	}
	
	@Override
	public List<T> findByPage(String sql,int startRow, int pageSize) {
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<T> list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
	}
	
	@Override
    public int excuteHQLUpdate(final String hql, final Object[] obj){  
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {  
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createQuery(hql);
                if(obj != null && obj.length > 0){  
                    for(int i = 0;i < obj.length; i++){
                        q.setParameter(i, obj[i]);  
                    }  
                }  
                return q.executeUpdate();  
            }  
        });  
    }  
	
	@Override
    public List excuteSQLQuery(final String sql, final Object[] obj){  
        return getHibernateTemplate().execute(new HibernateCallback<List>() {  
            public List doInHibernate(Session session) throws HibernateException, SQLException {  
            	SQLQuery q = session.createSQLQuery(sql);
                if(obj != null && obj.length > 0){  
                    for(int i = 0;i < obj.length; i++){
                        q.setParameter(i, obj[i]);  
                    }  
                }  
                return q.list(); 
            }  
        });  
    }  
	
	public int updateSQLQuery(final String sql,final Map<String,Object[]> param){
		int result = 1;
		try{
			this.hibernateTemplate.execute(new HibernateCallback<Integer>() {  
		
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createSQLQuery(sql);
                for(Map.Entry<String, Object[]> entry : param.entrySet()){
                	 q.setParameterList(entry.getKey(), entry.getValue());
                }
               
                return q.executeUpdate();  
            }  
        });
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}	
}
