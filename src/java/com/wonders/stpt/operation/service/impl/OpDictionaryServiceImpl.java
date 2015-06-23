package com.wonders.stpt.operation.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.bo.OpDictionary;
import com.wonders.stpt.operation.entity.bo.OpDocSend;
import com.wonders.stpt.operation.service.OpDictionaryService;

@Repository("opDictionaryService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class OpDictionaryServiceImpl implements OpDictionaryService{
	
	private OpTDao<OpDictionary> dao;
	public OpTDao<OpDictionary> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("opTDao")OpTDao<OpDictionary> dao) {
		this.dao = dao;
	}
	
	private OpTDao<OpDocSend> newDao;
	public OpTDao<OpDocSend> getNewDao() {
		return newDao;
	}
	@Autowired(required=false)
	public void setNewDao(@Qualifier("opTNewDao")OpTDao<OpDocSend> newDao) {
		this.newDao = newDao;
	}

	@Override
	public OpDictionary find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,OpDictionary.class);
	}
	@Override
	public List<OpDictionary> findByParentId(String pid) {
		String hql = "from OpDictionary t where t.parentid ='"+pid+"' order by t.ordernum asc ";
		return dao.findByHql(hql);
	}
	@Override
	public List<OpDictionary> findByType(String typecode) {
		String hql = "from OpDictionary t where t.typecode ='"+typecode+"' order by t.ordernum asc ";
		return dao.findByHql(hql);
	}
	@Override
	public List<OpDictionary> findByCode(String code) {
		String hql = "from OpDictionary t where t.code ='"+code+"' order by t.ordernum asc ";
		return dao.findByHql(hql);
	}
	
	@Override
	public List<OpDictionary> findByIds(final String ids){
        return dao.getHibernateTemplate().execute(new HibernateCallback<List<OpDictionary>>() {  
            public List<OpDictionary> doInHibernate(Session session) throws HibernateException, SQLException {  
                Query q = session.createQuery("from OpDictionary t where t.id in(:ids)");
                q.setParameterList("ids", ids.split(","));
                return q.list();  
            }  
        });
	}
	
	@Override
	public int deleteById(String id) {
		dao.excuteHQLUpdate("delete from OpDictionary where id = ?", new Object[]{id});
		return 1;
	}
	
	@Override
	public List<OpDictionary> findByDescription(String des){
		String hql = "from OpDictionary t where t.description like '%"+des+"%' order by t.ordernum asc ";
		return dao.findByHql(hql);
	}
	
	@Override
	public List<OpDictionary> findByParentAndDesc(String pid,String desc){
		String hql = "from OpDictionary t where t.description like '%"+desc+"%' and t.parentid ='"+pid+"' order by t.ordernum asc ";
		return dao.findByHql(hql);		
	}
	
	@Override
	public OpDictionary save(OpDictionary bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	public Object[] getReceiver(String deptId){
		List<Object[]> list = dao.excuteSQLQuery("select t.login_name,t.dept,t.name,t.mobile from op_dept_receivers t where t.dept_id = ?", new Object[]{deptId});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public Object[] getLeader(String deptId){
		List<Object[]> list = newDao.excuteSQLQuery("select t.login_name,t.dept,t.name from v_dept_single_leader t where t.dept_id = ?", new Object[]{deptId});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
