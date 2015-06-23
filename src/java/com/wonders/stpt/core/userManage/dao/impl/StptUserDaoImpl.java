package com.wonders.stpt.core.userManage.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.wonders.stpt.core.userManage.dao.StptUserDao;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.core.userManage.entity.vo.ManagerVo;
import com.wonders.stpt.util.StringUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Tuser实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */
@Repository("stptUserDao")
public class StptUserDaoImpl extends AbstractHibernateDaoImpl<StptUser> implements StptUserDao {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public DataSource getDataSource() {  
        return this.dataSource;  
    } 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired(required=false)
	public void setJdbcTemplate(@Qualifier("jdbcTemplate")JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired(required=false)
    public void setSessionFactory0(@Qualifier(value="sessionFactory")SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	//
	@SuppressWarnings("unchecked")
	public List<StptUser> stptUserExist(String loginName){
		String hql = "from StptUser t where t.removed = '0' and t.loginName=?";
		List<StptUser> list =  getHibernateTemplate().find(hql, new Object[] {
				loginName
            });

		return list;
	}
	
	public List<StptUser> stptUserList(){
		String hql = "from StptUser t where t.removed = '0' and t.loginName!='ADMIN'";
		List<StptUser> list =  getHibernateTemplate().find(hql);

		return list;
	}

	
	//批量增加
	public void addPatch(List<StptUser> list){
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();

		for(int i=0;i<list.size();i++)
		{
		    session.save(list.get(i));

		    // 以每50个数据作为一个处理单元

		    if(i%50==0)  
		    {
		        // 只是将Hibernate缓存中的数据提交到数据库，保持与数据库数据的同步

		        session.flush();  

		        // 清除内部缓存的全部数据，及时释放出占用的内存
		        session.clear();  
		    }
		}
	}
	
	//like 关键字
	public Page findStptUserByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from StptUser t where t.removed=0 and t.id!=1";
		String countHql = "select count(*) from StptUser t where t.removed=0 and t.id!=1";
		String filterPart = "";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					filterPart += " and ";
				String paramName = i.next();
				 // key=key.replaceAll("%","/%");
				  //key=key.replaceAll("_","/_");
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
			}
		}
		filterPart += " order by t.loginName asc";
		
		
		//System.out.println(hql + filterPart+"++++++++++++++");
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	public List<ManagerVo> getAgentInfo(String maxRows , String name_startsWith){
		List<ManagerVo> list = new ArrayList<ManagerVo>();
		String sql="select name,wmsys.wm_concat(tid) as tid," +
				" wmsys.wm_concat(cid) as cid ," +
				" wmsys.wm_concat(login_name) as login_name," +
				" wmsys.wm_concat(dept_name) as dept_name," +
				" wmsys.wm_concat(operate_time) as operate_time," +
				" wmsys.wm_concat(rank) as rank" +
				" from (" +
				" select distinct u.name,v.id as cid,v.login_name as login_name,u.id as tid ,v.dept_name as dept_name,u.operate_time,u.rank  " +
				" from v_userdep v,t_user u,t_user_relation r where u.removed=0 and r.removed=0 and u.id=r.t_id and r.c_id=v.id" +
				" order by u.name " +
				" )group by name,tid order by name";
		sql = "select * from ( "+sql+") where name like '"+name_startsWith+"%' and rownum <="+maxRows;
		List<Map<String, Object>> objList = getJdbcTemplate().queryForList(sql);
		for (int i = 0; i < objList.size(); i++) {
			ManagerVo vo = new ManagerVo();
			Map<String, Object> map = objList.get(i);
			vo.setName(StringUtil.getNotNullValueString((map.get("name"))));
			vo.setLoginName(StringUtil.getNotNullValueString((map.get("login_name"))));
			vo.setNickName("");
			vo.setDept(StringUtil.getNotNullValueString((map.get("dept_name"))));
			vo.setRole("");
			vo.setTid(StringUtil.getNotNullValueString((map.get("tid"))));
			vo.setCid(StringUtil.getNotNullValueString((map.get("cid"))));
			vo.setCreateDate(StringUtil.getNotNullValueString((map.get("operate_time"))));
			vo.setRank(StringUtil.getNotNullValueString((map.get("rank"))));
			list.add(vo);
		}
		
		return list;
	}
	
	public void saveAgent(String tid,String cid){
		getJdbcTemplate().update("insert into T_USER_RELATION (T_ID,C_ID,ORDERS,REMOVED,AGENT) values (?,?,?,?,?)", new Object[] {tid,cid,"0","0","1"}); 
	}
	
	public void deleteAgent(String tid,String cid){
		getJdbcTemplate().update("DELETE FROM  T_USER_RELATION WHERE T_ID =? AND C_ID=?", new Object[] {tid,cid}); 
	}
}
