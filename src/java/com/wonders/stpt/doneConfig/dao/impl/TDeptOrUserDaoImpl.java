package com.wonders.stpt.doneConfig.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.doneConfig.dao.TDeptOrUserDao;
@Repository("deptOrUserDao")
public class TDeptOrUserDaoImpl extends HibernateDaoSupport implements TDeptOrUserDao {
	//private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(TDoneConfigClassicDaoImpl.class);
   /* public HibernateTemplate getHibernateTemplate(){
        return hibernateTemplate;
    }
    @Resource(name = "hibernateTemplate2")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }*/
    
    private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Autowired(required=false)
	public void setJdbcTemplate(@Qualifier("jdbcTemplate2")JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//第一种方法 继承 daosupport 注入session
	@Autowired(required=false)
    public void setSessionFactory0(@Qualifier(value="sessionFactory2")SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public List getDept() {
		// TODO Auto-generated method stub
		String hql="select * from v_userdep u";
		/*Query query=getSession().createQuery(hql);
		List list=query.list();
		for(int i=0;i<list.size();i++){
			
			logger.info("        "+list.get(i));
		}*/
		
		List<Map<String, Object>> objList =getJdbcTemplate().queryForList(hql);
		for(int i=0;i<objList.size();i++){//ID, LOGIN_NAME, NAME, DEPT_ID, DEPT_NAME, DESCRIPTION, ORDERS    stptdemo
			//[ID, EXT1, EXT2, EXT3, EXT4, EXT5, EXT6, EXT7, EXT8, EMAIL, ADDRESS, MOBILE1, MOBILE2, FAX, HOME_PHONE, OFFICE_PHONE, AUTHENTIC_TYPE, CERTIFICATE, STATUS, LOGIN_NAME, NAME, SEX, PASSWORD, USERTYPE, ACCOUNTTYPE, OPERATE_TIME, OPERATOR, REMOVED, DEPT_CODE, PARENT_NODE_ID, DEPTNAME, DESCRIPTION, DUTY, DEPTORDERS]
			//stpt
			logger.info(objList.get(i).keySet());
			//logger.info(objList.get(i).get("ID")+"     "+objList.get(i).get("LOGIN_NAME")+"     "+objList.get(i).get("NAME"));
		}
		return objList;
	}

}
