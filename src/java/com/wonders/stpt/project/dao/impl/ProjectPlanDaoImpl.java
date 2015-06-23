package com.wonders.stpt.project.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.ProjectPlanDao;
import com.wonders.stpt.project.model.ProjectPlan;

/**
 * Created by Administrator on 2014/6/16.（项目计划底层实现）
 */
@Repository("projectPlanDao")
public class ProjectPlanDaoImpl implements ProjectPlanDao {
    private HibernateTemplate hibernateTemplate;
    private final Logger logger=Logger.getLogger(ProjectPlanDaoImpl.class);
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public ProjectPlan save(ProjectPlan projectPlan) {
    	logger.error("save");
        hibernateTemplate.save(projectPlan);
        return projectPlan;
    }
/**
    @Override
    public int delete(String id) {
        return getSession().createQuery("update ProjectPlan p set p.removed = :removed where p.projectPlanId = :projectPlanId").setString("removed","1").setString("projectPlanId", id).executeUpdate();
    }
*/
    @Override
    public void saveOrUpdateAll(List<ProjectPlan> projectPlans)throws Exception {
    	logger.error("dao");
        hibernateTemplate.saveOrUpdateAll(projectPlans);
    }

    @Override
    public ProjectPlan load(String id)throws Exception {
        return (ProjectPlan) getHibernateTemplate().get(ProjectPlan.class, id);
    }

    @Override
    public List<ProjectPlan> find(String projectId, Date beginDate, Date endDate)throws Exception {
        HashMap parameter = new HashMap();
        parameter.put("projectId",projectId);

        StringBuffer sb = new StringBuffer();
        sb.append("from ProjectPlan p where p.project.projectId = :projectId and removed='0' ");
        if(beginDate !=null ){
            sb.append(" and p.beginDate >= :beginDate");
            parameter.put("beginDate",beginDate);
        }
        if(endDate !=null ){
            sb.append(" and p.endDate <= :endDate");
            parameter.put("endDate",endDate);
        }
        return (List<ProjectPlan>)getSession().createQuery(sb.toString()).setProperties(parameter).list();

    }

	@Override
	public PageResultSet<ProjectPlan> find(ProjectPlan projectPlan, int page,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		if(page<1){
			page=1;
		}
		if(pageSize<1){//默认每页显示10条记录S
			pageSize=10;
		}
		StringBuffer sb=new StringBuffer();
		HashMap parameter=new HashMap();
		sb.append("from ProjectPlan p where 1=1 ");
		if(null!=projectPlan){//当参数对象吧部不为null时将参数对象添加到hql语句中去
			if(StringUtils.isNotBlank(projectPlan.getProject().getProjectId())){
				
			}
		}
		return null;
	}
 
	@Override
	public List<ProjectPlan> find(ProjectPlan projectPlan) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String[] ids) {
		// TODO Auto-generated method stub
		return getSession().createQuery("update ProjectPlan p set p.removed=:removed where p.projectPlanId in :projectPlanId ").setString("removed", "1").setParameterList("projectPlanId", ids).executeUpdate();
	}
}

