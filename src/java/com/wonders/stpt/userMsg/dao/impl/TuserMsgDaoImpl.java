package com.wonders.stpt.userMsg.dao.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.wonders.stpt.userMsg.dao.TuserMsgDao;
import com.wonders.stpt.userMsg.entity.bo.TuserMsg;
import com.wonders.stpt.userMsg.entity.vo.UserSearchVo;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;

@Repository("tuserMsgDao")
public class TuserMsgDaoImpl extends HibernateDaoSupport implements TuserMsgDao{
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Autowired(required=false)
	public void setJdbcTemplate(@Qualifier("jdbcTemplate")JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//第一种方法 继承 daosupport 注入session
	@Autowired(required=false)
    public void setSessionFactory0(@Qualifier(value="sessionFactory")SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	//bo insert
	public void addMsg(TuserMsg bo){
		getHibernateTemplate().save(bo);
	}
	
	//bo update
	public void updateMsg(TuserMsg bo){
		getHibernateTemplate().update(bo);
	}
	
	//bo find
	public TuserMsg findMsg(String msgId){
		TuserMsg msg = null;
		try{
			msg = (TuserMsg)getHibernateTemplate().get(TuserMsg.class, msgId);
		} catch(DataAccessException e){
			
		}
		return msg;
	}
	
	//分页查询
	@SuppressWarnings("unchecked")
	public List<TuserMsg> getMsgsByPage(TuserMsg msgBo,int first,int size,String sendStart,String sendEnd,String seeStart,String seeEnd,String receiveFlag ){
		List<TuserMsg> list = new ArrayList<TuserMsg>();
		String filterPart = "";
		String hql = "select t from TuserMsg t where t.state = 0";
		if("1".equals(receiveFlag)){
			hql = hql + " and t.seeState !='2'";
		}
		Map<String,Object> filter = getFilter(msgBo);
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					filterPart += " and ";
					String paramName = i.next();
					if("rname".equals(paramName)||"sname".equals(paramName)||"title".equals(paramName)){
						filterPart += "t." + paramName + " like '%" + filter.get(paramName)+"%'";
					}else{
						filterPart += "t." + paramName + " = '" + filter.get(paramName)+"'";
					}
			}
		}
		if(!"".equals(sendStart)){
			filterPart += " and t.sendDate >='" + sendStart + "'";
		}
		if(!"".equals(sendEnd)){
			filterPart += " and t.sendDate <= '" + sendEnd + "'";
		}
		if(!"".equals(seeStart)){
			filterPart += " and t.seeDate >= '" + seeStart + "'";
		}
		if(!"".equals(seeEnd)){
			filterPart += " and t.seeDate <= '" + seeEnd + "'";
		}
		String orders = " order by t.sendDate desc";
		hql = hql + filterPart + orders;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(size);
		list = query.list();
		return list;
	} 
	
	//总数查询
	@SuppressWarnings("unchecked")
	public int getMsgsCounts(TuserMsg msgBo,String sendStart,String sendEnd,String seeStart,String seeEnd ,String receiveFlag){
		int result = 0;
		List<TuserMsg> list = new ArrayList<TuserMsg>();
		String filterPart = "";
		String hql = "select t from TuserMsg t where t.state = 0";
		if("1".equals(receiveFlag)){
			hql = hql + " and t.seeState !='2'";
		}
		Map<String,Object> filter = getFilter(msgBo);
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					filterPart += " and ";
					String paramName = i.next();
					if("rname".equals(paramName)||"sname".equals(paramName)||"title".equals(paramName)){
						filterPart += "t." + paramName + " like '%" + filter.get(paramName)+"%'";
					}else{
						filterPart += "t." + paramName + " = '" + filter.get(paramName)+"'";
					}
			}
		}
		if(!"".equals(sendStart)){
			filterPart += " and t.sendDate >='" + sendStart + "'";
		}
		if(!"".equals(sendEnd)){
			filterPart += " and t.sendDate <= '" + sendEnd + "'";
		}
		if(!"".equals(seeStart)){
			filterPart += " and t.seeDate >= '" + seeStart + "'";
		}
		if(!"".equals(seeEnd)){
			filterPart += " and t.seeDate <= '" + seeEnd + "'";
		}
		String orders = " order by t.sendDate desc";
		hql = hql + filterPart + orders;
		System.out.println(hql);
		list = getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			result = list.size();
		}
		return result;
	} 
	
	
	//垃圾箱
	//分页查询
	@SuppressWarnings("unchecked")
	public List<TuserMsg> getLitterMsgsByPage(TuserMsg msgBo,int first,int size,String sendStart,String sendEnd,String seeStart,String seeEnd ){
		List<TuserMsg> list = new ArrayList<TuserMsg>();
		String filterPart = "";
		String hql = "select t from TuserMsg t where t.state = 0 and " +
				" ( (t.rid="+msgBo.getRid()+" and t.seeState='2')or (t.sid="+msgBo.getSid()+" and t.sendState=2))";
		Map<String,Object> filter = getFilter(msgBo);
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					
					String paramName = i.next();
					if("rname".equals(paramName)||"sname".equals(paramName)||"title".equals(paramName)){
						filterPart += " and ";
						filterPart += "t." + paramName + " like '%" + filter.get(paramName)+"%'";
					}
			}
		}
		if(!"".equals(sendStart)){
			filterPart += " and t.sendDate >='" + sendStart + "'";
		}
		if(!"".equals(sendEnd)){
			filterPart += " and t.sendDate <= '" + sendEnd + "'";
		}
		if(!"".equals(seeStart)){
			filterPart += " and t.seeDate >= '" + seeStart + "'";
		}
		if(!"".equals(seeEnd)){
			filterPart += " and t.seeDate <= '" + seeEnd + "'";
		}
		String orders = " order by t.sendDate desc";
		hql = hql + filterPart + orders;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(size);
		list = query.list();
		return list;
	} 
	
	//总数查询
	@SuppressWarnings("unchecked")
	public int getLitterMsgsCounts(TuserMsg msgBo,String sendStart,String sendEnd,String seeStart,String seeEnd){
		int result = 0;
		List<TuserMsg> list = new ArrayList<TuserMsg>();
		String filterPart = "";
		String hql = "select t from TuserMsg t where t.state = 0 and " +
		" ( (t.rid="+msgBo.getRid()+" and t.seeState='2')or (t.sid="+msgBo.getSid()+" and t.sendState=2))";
		Map<String,Object> filter = getFilter(msgBo);
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					
					String paramName = i.next();
					if("rname".equals(paramName)||"sname".equals(paramName)||"title".equals(paramName)){
						filterPart += " and ";
						filterPart += "t." + paramName + " like '%" + filter.get(paramName)+"%'";
					}
			}
		}
		if(!"".equals(sendStart)){
			filterPart += " and t.sendDate >='" + sendStart + "'";
		}
		if(!"".equals(sendEnd)){
			filterPart += " and t.sendDate <= '" + sendEnd + "'";
		}
		if(!"".equals(seeStart)){
			filterPart += " and t.seeDate >= '" + seeStart + "'";
		}
		if(!"".equals(seeEnd)){
			filterPart += " and t.seeDate <= '" + seeEnd + "'";
		}
		String orders = " order by t.sendDate desc";
		hql = hql + filterPart + orders;
		System.out.println(hql);
		list = getHibernateTemplate().find(hql);
		if(list!=null&&list.size()>0){
			result = list.size();
		}
		return result;
	} 
	
	
	//获取用户信息
	public List<UserSearchVo> getUserInfo(String maxRows , String name_startsWith){
		List<UserSearchVo> list = new ArrayList<UserSearchVo>();
		String sql=" select distinct u.name,v.id as cid,v.login_name as login_name," +
				" u.id as tid ,v.dept_name as dept_name,u.operate_time,u.rank  " +
				" from v_userdep v,t_user u,t_user_relation r where u.removed=0 and r.removed=0 and u.id=r.t_id and r.c_id=v.id" +
				" order by u.name ";
		sql = "select * from ( "+sql+") where name like '"+name_startsWith+"%' and rownum <="+maxRows;
		List<Map<String, Object>> objList = getJdbcTemplate().queryForList(sql);
		for (int i = 0; i < objList.size(); i++) {
			UserSearchVo vo = new UserSearchVo();
			Map<String, Object> map = objList.get(i);
			vo.setName(StringUtil.getNotNullValueString((map.get("name"))));
			vo.setLoginName(StringUtil.getNotNullValueString((map.get("login_name"))));
			vo.setDept(StringUtil.getNotNullValueString((map.get("dept_name"))));
			vo.setCid(StringUtil.getNotNullValueString((map.get("cid"))));
			vo.setTid(StringUtil.getNotNullValueString((map.get("tid"))));
			list.add(vo);
		}
		
		return list;
	}
	
	//获取用户姓名
	public String getUserName(String userId){
		String userName = null;
		String sql=" select distinct c.name from cs_user c where c.removed = 0 and c.id =?";
		List<Map<String, Object>> objList = getJdbcTemplate().queryForList(sql, new Object[]{userId});
		if(objList!=null&&objList.size()>0){
			Map<String, Object> map = objList.get(0);
			userName = StringUtil.getNotNullValueString((map.get("name")));
		}
		
		return userName;
	}
	
	public static Map<String,Object> getFilter(TuserMsg msgBo){
		String key = null;
		Map<String, Object> filter = new HashMap<String, Object>();
		JSONObject obj = JSONObject.fromObject(msgBo);
		Iterator it = obj.keys();
		while (it.hasNext()) {
			key = (String) it.next();
			Object res = getValueByParamName(msgBo,
					key);
			if (res != null && !"".equals(res)) {
				filter.put(key, res);
			}
		}
		return filter;
	}
	
	public static Object getValueByParamName(Object obj, String paramName) {
		if (paramName == null || paramName.trim().length() == 0) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
			if (paramName.equalsIgnoreCase(varName)) {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
				return res;
			}
		}
		return null;
	}
}
