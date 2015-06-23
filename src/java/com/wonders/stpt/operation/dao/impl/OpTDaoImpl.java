package com.wonders.stpt.operation.dao.impl;

import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.vo.AttachFileVo;
import com.wonders.stpt.operation.util.FlowWorkUtils;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.DbUtil;
import com.wonders.stpt.util.StringUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
@Repository("opTDao")
public class OpTDaoImpl<T>  implements OpTDao<T>{
	
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



    public Map<String,String> getWsUser(){ return null;}

    /**
     * 获得运营发文附件对象
     * @param groupId
     * @return
     */
    @Override
    public List<AttachFileVo> getAttach(String groupId){
        String sql = "select filename,fileextname, ? || id  as path " +
                "from t_attach t where t.removed='0' and t.groupid = ?";
        List<AttachFileVo> list = this.dbUtil.getJdbcTemplate().query(sql,new Object[]{FlowWorkUtils.ATT_URL,groupId},
                new BeanPropertyRowMapper<AttachFileVo>(AttachFileVo.class));
        return list;
    }
    /**
     * 获取用户账号手机
     * @param users
     * @return
     */
    @Override
    public Map<String,String> getUserMobile(List<String> users){
        Map<String,String> result = new HashMap<String,String>();
        String sql = "select login_name,case mobile1 when null then mobile2 else mobile1 end mobile from cs_user c where login_name in (:users)";
        NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dbUtil.getJdbcTemplate());
        Map<String, Object> paramMap = new HashMap<String, Object>();
       // MapSqlParameterSource
        paramMap.put("users", users);
        List<Map<String,Object>> list = namedJdbcTemplate.queryForList(sql, paramMap);
        for(Map<String,Object> map : list){
            result.put(StringUtil.getNotNullValueString(map.get("LOGIN_NAME")),StringUtil.getNotNullValueString(map.get("MOBILE")));
        }
        return result;
    }

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
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
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public List<T> findByPage(String sql,int startRow, int pageSize) {
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
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
}
