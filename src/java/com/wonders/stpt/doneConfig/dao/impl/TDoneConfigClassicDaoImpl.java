package com.wonders.stpt.doneConfig.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.doneConfig.dao.TDoneConfigClassicDao;
import com.wonders.stpt.doneConfig.model.TDoneConfigClassic;
@Repository("doneConfigClassicDao")
public class TDoneConfigClassicDaoImpl implements TDoneConfigClassicDao {
	private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(TDoneConfigClassicDaoImpl.class);
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
	public TDoneConfigClassic save(TDoneConfigClassic doneConfigClassic) throws Exception {
		// TODO Auto-generated method stub
		//getSession().saveOrUpdate(doneConfigClassic);
		if(StringUtils.isBlank(doneConfigClassic.getId())){
			getSession().save(doneConfigClassic);
		}else{
			getSession().update(doneConfigClassic);
		}
		return doneConfigClassic;
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("update TDoneConfigClassic t set removed=:removed where t.id in (:ids)").setString("removed", "1").setParameterList("ids", ids).executeUpdate();
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.delete(new String[]{id});
	}

	@Override
	public TDoneConfigClassic findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (TDoneConfigClassic) getSession().get(TDoneConfigClassic.class, id);
	}

	@Override
	public PageResultSet<TDoneConfigClassic> find(TDoneConfigClassic doneConfigClassic,
			Integer pageindex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(pageindex<1){
			pageindex=1;
    	}
    	if(pageSize<1){
    		pageSize=10;
    	}
        StringBuffer sb = new StringBuffer();
        HashMap parameter = new HashMap();
        sb.append("from TDoneConfigClassic p where 1=1 and removed='0'");
        if(null!=doneConfigClassic){
	        if(StringUtils.isNotBlank(doneConfigClassic.getName())) {
	            sb.append(" and p.name like :name");
	            parameter.put("name","%"+doneConfigClassic.getName()+"%");
	        }
        }
      //先求出总记录数
    	int totalRow=0;
    	logger.error("sb:"+sb);
    	PageInfo pageInfo;
    	//sb.append(" order by p.updateTime desc  ");
		try {
			Query query=this.getSession().createQuery("select count(*) "+sb.toString());
			query.setProperties(parameter);
			totalRow =Integer.valueOf(query.uniqueResult().toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("错误信息："+e);
			e.printStackTrace();
		}
		
		pageInfo = new PageInfo(totalRow, pageSize, pageindex);
		logger.error("---------------------");
        List list = getSession().createQuery(sb.toString())
					.setProperties(parameter)
					.setFirstResult(pageInfo.getBeginIndex())
					.setMaxResults(pageInfo.getPageSize()).list();
		
        sb.insert(0,"select count(*) ");
        logger.error("sb:"+sb);
        logger.error("getBeginIndex:"+pageInfo.getBeginIndex()+"     getPageSize: "+pageInfo.getPageSize());
        
        pageInfo.setTotalRow(totalRow);
        PageResultSet<TDoneConfigClassic> pageResultSet = new PageResultSet<TDoneConfigClassic>();
        pageResultSet.setList(list);
        pageResultSet.setPageInfo(pageInfo);
		return pageResultSet;
	}

	@Override
	public List<String> getRefId(String[] name) throws Exception {
		// TODO Auto-generated method stub
		//List<String> list=getSession().createQuery("select t.refId from TTodoClassic t where t.removed=:removed and t.name in :name").setString("removed", "0").setParameterList("name", name).list();
		List<String> list=getSession().createQuery("select t.refId from TDoneConfigClassic t where t.removed=:removed and t.name in (:name)").setString("removed", "0").setParameterList("name", name).list();
		System.out.println("list.length"+list.size());
		Collection nuCon = new Vector(); 
		nuCon.add(null); 
		list.removeAll(nuCon);
		for(String str : list){
			 System.out.println(str);    
		}
		return list;
	}
	
	public List<TDoneConfigClassic> findByHql(String hql,Map param)throws Exception{
		Query query=getSession().createQuery(hql);
		query.setProperties(param);
		logger.info("sad");
		return query.list();
	}
	@Override
	public List<String> getName() throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("select distinct name from TDoneConfigClassic t where removed='0'").list();
	}

}
