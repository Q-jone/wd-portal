package com.wonders.stpt.operation.dao.impl;

import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.vo.AttachFileVo;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.DbUtil;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SuppressWarnings("unchecked")
@Repository("opTNewDao")
public class OpTNewDaoImpl<T>  implements OpTDao<T>{
	
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
    public List<AttachFileVo> getAttach(String groupId){return null;}

    /**
     * 获取对应webservice 的type值
     * @return
     */
    @Override
    public Map<String,String> getWsUser(){
        Map<String,String> map = new HashMap<String, String>();
        String sql = "select data_type from dw_data_exchange_user t where data_type like 'opSend%' and user_name = 'wonder'";
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql).addScalar("data_type", Hibernate.STRING);
        List<Object> list = query.list();
        for(Object o : list){
            map.put(o.toString().replace("opSend",""),o.toString());
        }

        return map;
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
	public PageResultSet findPageResult(String queryHql,String countHql,
			int pageNo, int pageSize){
		PageResultSet result = new PageResultSet();
		
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(countHql);
		int totalRows = Integer.valueOf(query.uniqueResult().toString());
		
		List<T> list = this.findByPage(queryHql,(pageNo-1)*pageSize,pageSize);
		
		result.setList(list);
		result.setPageInfo(new PageInfo(totalRows, pageSize, pageNo));

		return result;
	}
	
	@Override
	public int countByHql(String queryHql){
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryHql);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public List<T> findByPage(String hql,int startRow, int pageSize) {
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
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

    @Override
    public Map<String, String> getUserMobile(List<String> users) {
        return null;
    }
}
