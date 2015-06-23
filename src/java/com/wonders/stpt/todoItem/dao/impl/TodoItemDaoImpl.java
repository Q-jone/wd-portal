/**
 * 
 */
package com.wonders.stpt.todoItem.dao.impl;

import com.google.gson.GsonBuilder;
import com.wonders.stpt.todoItem.dao.TodoItemDao;
import com.wonders.stpt.todoItem.model.bo.TodoItem;
import com.wonders.stpt.todoItem.model.vo.TodoInfo;
import com.wonders.stpt.todoItem.model.vo.TodoStatInfo;
import com.wonders.stpt.util.ConfigDbUtil;
import com.wonders.stpt.util.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * ddddddddddddddddd
* @ClassName: TodoItemDaoImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhoushun
* @date 2014年9月18日 上午10:32:30 
*  
*/
@Repository("todoItemDao")
public class TodoItemDaoImpl implements TodoItemDao{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource(name="hibernateTemplate2")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int countBySql(String sql) {
		return this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int countByHql(String hql) {
		List list = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,String>> findPaginationInfo(String sql, int startRow, int pageSize) {
		List<Map<String,String>> finlist = new ArrayList();
		SQLQuery query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		Iterator<String[]> it = list.iterator();
		while(it.hasNext()){
			Map<String,String> map = new HashMap<String,String>();
			Object[] objArr = it.next();
			String[] ret = new String[objArr.length];
			for(int i=0; i<objArr.length; i++){
				ret[i] = StringUtil.getNotNullValueString(objArr[i]);
			}
			finlist.add(map);
		}
		return finlist;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List findPaginationInfoByHql(String hql, int startRow, int pageSize) {
		Query query  = this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
        return query.setFirstResult(startRow).setMaxResults(pageSize).list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<TodoItem> getTodoItems(String loginName,String ologinName,String deptId,String type){
		String sql = "";
		if("".equals(type) || type==null){
			sql = "from TodoItem t where t.loginName in" +
					" ("+loginName+",'"+ologinName+"') " +
					" and t.status=0 and t.removed=0  order by t.type,t.occurTime desc";
		}else if("1".equals(type)){
			sql = "from TodoItem t where " +
					" (t.stepName!='传阅' or t.stepName is null) " +
					" and t.loginName ='"+ologinName+"'  and t.type=1 " +
					"and t.status=0 and t.removed=0  order by t.occurTime desc";
		}else if("0".equals(type)){
			sql = "from TodoItem t where " +
					" (t.stepName!='传阅' or t.stepName is null) " +
					" and t.loginName in ("+loginName+")  and t.type=0 " +
					"and t.status=0 and t.removed=0  order by t.occurTime desc";
		}
		//System.out.println(sql);
		List<TodoItem> list = this.getHibernateTemplate().find(sql);
		return list;
	}
	
	
	
	/**
     * ffffffffffffffffffffffffffffffff
	* @Title: getTodoInfo
    * @author zs
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param source sds
	* @return List<TodoInfo>    返回类型 
	* @date 2014年9月18日 上午10:34:36 
	* @throws
	*/
	public TodoStatInfo getTodoInfo(List<String> source){
		StringBuilder userSql = new StringBuilder();
		Object[] users = source.toArray();
		if(source.size() > 0){
			userSql.append("and t.loginname in (");
			for(int i=0;i<source.size();i++){
				if(i==source.size()-1){
					userSql.append("?");
				}else{
					userSql.append("?,");
				}
			}
			userSql.append(")");
		}

		StringBuilder sql = new StringBuilder();
		sql.append("select t.typename pname,t.title summary, ");
		sql.append("case when trunc(sysdate-to_date(t.occurtime,'yyyy-mm-dd hh24:mi:ss')-5) >0 then '超时'||trunc(sysdate-to_date(t.occurtime,'yyyy-mm-dd hh24:mi:ss')-5)||'天' ");
		sql.append("else ' ' end overtime,substr(t.occurtime,1,10) occurtime,t.url ");
		sql.append("from t_todo_item t where t.removed = 0");
		sql.append("and t.status = 0 ");
		sql.append(userSql);
		sql.append(" order by t.occurtime desc ");
		List<TodoInfo> list = ConfigDbUtil.getJdbcTemplate("").query(sql.toString(), 
				users,
				new BeanPropertyRowMapper(TodoInfo.class));
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) count,count( case when trunc(sysdate-to_date(t.occurtime,'yyyy-mm-dd hh24:mi:ss')-5) >0 then 1 else  null end) otCount ");
		countSql.append("from t_todo_item t where t.removed = 0");
		countSql.append("and t.status = 0 ");
		countSql.append(userSql);
		Map<String,Object> map = ConfigDbUtil.getJdbcTemplate("").queryForMap(countSql.toString(),
				users);
		TodoStatInfo todoStatInfo = new TodoStatInfo();
		todoStatInfo.setTodoInfo(list);
		todoStatInfo.setCount(((BigDecimal)map.get("count")).intValue());
		todoStatInfo.setOtCount(((BigDecimal)map.get("otCount")).intValue());
		return todoStatInfo;
	}
	
	public static void main(String[] args){
//		System.out.println(new Gson().toJson(null));
		TodoInfo t = new TodoInfo();
		t.setOccurtime("");
		System.out.println(new GsonBuilder().serializeNulls().create().toJson(t));
	}
}
