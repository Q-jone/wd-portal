package com.wonders.stpt.core.cfconsole.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.core.cfconsole.dao.ConsoleDao;
import com.wonders.stpt.core.cfconsole.entity.bo.TuserLog;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;
@Repository("consoleDao")
public class ConsoleDaoImpl extends HibernateDaoSupport implements ConsoleDao{
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
	
	
	//Tuser 表 是否存在该工号
	@SuppressWarnings("unchecked")
	public StptUser tuserExist(String loginName){
		StptUser u = null;
		String hql = "from StptUser t where t.removed = '0' and t.loginName=?";
		List<StptUser> list =  getHibernateTemplate().find(hql, new Object[] {
				loginName
            });
		if(list!=null&&list.size()>0){
			u = list.get(0);
		}
		return u;
	}

    /**
     * 判断是否存在relation
     * @param cid
     * @param tid
     * @return
     */
    public boolean isRelationExist(long tid,long cid){
        boolean flag = false;
        String hql = "from TuserRelation t where t.tid= ? and t.cid = ? and t.removed='0'";
        List list = getHibernateTemplate().find(hql,new Object[]{tid,cid});
        if(list.size() > 0){
            flag = true;
        }
        return flag;
    }

	//Tuser 表新增该工号信息
	public void tuserAdd(StptUser u){
		getHibernateTemplate().save(u);
	}
	
	
	//Cuser 表 是否存在该工号
	@SuppressWarnings("unchecked")
	public Map<String, String> cuserExist(String loginName){
        Map<String, String> result = new HashMap<String, String>();
		String sql = "select id,removed from cs_user c where c.login_name=?";
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql, new Object[]{loginName});
		if(list!=null&&list.size()>0){
			for(Map<String,Object> map : list){
                result.put("id",String.valueOf(map.get("id")));
                result.put("removed",String.valueOf(map.get("removed")));
            }
		}
		return result;
	} 
	
	//TuserRelation表关键记录
	//批量增加
	public void relationAddPatch(List<TuserRelation> list){
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
	
	//TuserLog
	public void tuserlogAdd(TuserLog l){
		getHibernateTemplate().save(l);
	}
	
}
