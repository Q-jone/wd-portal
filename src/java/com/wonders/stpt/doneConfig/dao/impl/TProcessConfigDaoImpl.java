package com.wonders.stpt.doneConfig.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.impl.ProjectDaoImpl;
import com.wonders.stpt.project.model.Project;
import com.wonders.stpt.doneConfig.dao.TProcessConfigDao;
import com.wonders.stpt.doneConfig.model.TProcessConfig;
@Repository("processConfigDao")
public class TProcessConfigDaoImpl implements TProcessConfigDao {
	private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(ProjectDaoImpl.class);
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
	public TProcessConfig save(TProcessConfig tProcessConfig) throws Exception {
		// TODO Auto-generated method stub
		//getSession().saveOrUpdate(tProcessConfig);
		if(StringUtils.isBlank(tProcessConfig.getId())){
			getSession().save(tProcessConfig);
		}else{
			getSession().update(tProcessConfig);
		}
		return tProcessConfig;
	}

	@Override
	public int delete(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("update  TProcessConfig t set t.removed=:removed where t.id in (:id) ").setString("removed", "1").setParameterList("id", ids).executeUpdate();
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.delete(new String[]{id});
	}

	@Override
	public TProcessConfig findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (TProcessConfig) getSession().get(TProcessConfig.class, id);
	}

	@Override
	public PageResultSet<TProcessConfig> find(TProcessConfig tProcessConfig,
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
        sb.append("from TProcessConfig p where 1=1 and removed='0'");
        if(null!=tProcessConfig){
	        if(StringUtils.isNotBlank(tProcessConfig.getName())) {
	            sb.append(" and p.name like :name");
	            parameter.put("name","%"+tProcessConfig.getName()+"%");
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
        PageResultSet<TProcessConfig> pageResultSet = new PageResultSet<TProcessConfig>();
        pageResultSet.setList(list);
        pageResultSet.setPageInfo(pageInfo);
		return pageResultSet;
	}

	@Override
	public List<TProcessConfig> findByIds(String[] ids, int type)
			throws Exception {
		// TODO Auto-generated method stub
		List<TProcessConfig> list=new ArrayList<TProcessConfig>();
		if(type==0){//获取当前主键记录
			if(ids.length==0)
				return list;
			list=getSession().createQuery("from TProcessConfig t where t.removed=:removed and t.id in :ids").setString("removed", "0").setParameterList("ids", ids).list();
		}else{//获取除当前主键记录
			if(ids.length==0)//当前没有记录归于类型中
				return getSession().createQuery("from TProcessConfig t where t.removed=:removed").setString("removed", "0").list();
			list=getSession().createQuery("from TProcessConfig t where t.removed=:removed and t.id not in :ids").setString("removed", "0").setParameterList("ids", ids).list();
		}
		return list;
	}

	@Override
	public List<TProcessConfig> findByHQL(String hql, Object[] obj)
			throws Exception {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		if(obj!=null){
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
		}
		return query.list();
	}

	@Override
	public int deleteByType(String hql, Object[] obj) throws Exception {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery(hql);
		if(obj!=null){
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
		}
		return query.executeUpdate();
	}

}
