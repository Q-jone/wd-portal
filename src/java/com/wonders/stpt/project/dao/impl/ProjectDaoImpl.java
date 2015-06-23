package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.ProjectDao;
import com.wonders.stpt.project.model.Project;
import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.service.impl.ProjectServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2014/6/16.
 */
@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao {
    private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(ProjectDaoImpl.class);
    public HibernateTemplate getHibernateTemplate(){
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }


    @Override
    public Project save(Project project)  throws Exception{
    	getSession().saveOrUpdate(project);
        return project;
    }

    @Override
    public int delete(String[] id) throws Exception {
        return getSession().createQuery("update Project p set p.removed=:removed where p.projectId in :projectId").setString("removed", "1").setParameterList("projectId", id).executeUpdate();
    }

    @Override
    public Project load(String id) throws Exception {
    	
        return (Project)getHibernateTemplate().get(Project.class,new String(id));
    }

    @Override
    public List<Project> find(Date beginDate, Date endDate) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT P.PROJECT_ID,P.PROJECT_NAME,P.DEPARTMENT,P.LEADER,P.PROJECT_GOAL,PF.FORWARD_GOAL,P.PROJECT_STATUS,P.RESPONSIBLE,PP.PLAN_NAME,");
        sb.append("DECODE(PP.SUB_PLAN_NAME,'12','目标及效果','13','需求及功能','14','技术方案','4','立项审价','5','立项审核','6','采购技术文件','7','完成采购','8','项目开工','9','实施方案','10','项目初验','11','项目验收','15','试运行','16','阶段验收','17','软件升级完成','18','项目实施') SUB_PLAN_NAME_DESCRIPT,");
        sb.append("PP.PLAN_BEGIN_DATE, PP.PLAN_END_DATE, CASE WHEN PP.PLAN_END_DATE >= NVL(REAL_END_DATE,SYSDATE) THEN '0' ELSE '1' END IS_DELAY ,");
        sb.append("0,");
        sb.append("REAL_BEGIN_DATE,REAL_END_DATE,PP.PROJECT_PLAN_ID,");
        sb.append("MAX(PLAN_END_DATE) OVER(PARTITION BY PP.PLAN_NAME,PP.PROJECT_ID),COUNT(PP.PROJECT_PLAN_ID) OVER(PARTITION BY P.PROJECT_ID),P.CATEGORY,P.PROJECT_SN,P.PROJECT_DESCRIPTION,P.PROJECT_LEVEL,P.MEMO,PP.MANUAL ");

        sb.append("FROM T_PROJECT P ");
        sb.append("LEFT JOIN T_PROJECT_PLAN PP ON P.PROJECT_ID = PP.PROJECT_ID ");
        sb.append("LEFT JOIN T_PROJECT_FORWARD_GOAL PF ON P.PROJECT_ID = PF.PROJECT_ID ");
        sb.append("WHERE PF.BEGIN_DATE  >= :beginDate AND PF.END_DATE <=:endDate ");
        sb.append("   AND PP.PLAN_BEGIN_DATE >= :beginDate");
        sb.append("   AND PP.PLAN_END_DATE <= :endDate AND P.FINISH = :finish AND P.REMOVED= :removed and PP.REMOVED = :removed ");
        sb.append("   ORDER BY P.CREATE_TIME ASC, PP.PLAN_BEGIN_DATE ASC");
        System.out.println(sb.toString());//AND PP.PROJECT_ID='8a818e9446d19f5c0146d2374adc006a'
        List<Object[]> list = getSession().createSQLQuery(sb.toString()).setDate("beginDate", beginDate).setString("removed","0").setDate("endDate", endDate).setString("finish","0").list();

        ArrayList<Project> projectList = new ArrayList<Project>();
        HashMap<String, Project> hashMap = new HashMap<String, Project>();
        int temp = 0;
        for (int i = 0; i < list.size(); i++) {

            Object[] objects = list.get(i);
            Project project = null;
            if (hashMap.containsKey((String) objects[0])) {
                project = hashMap.get((String) objects[0]);
            } else {
                project = new Project();
                project.setProjectId((String) objects[0]);
                project.setProjectName((String) objects[1]);
                project.setDepartment((String) objects[2]);
                project.setLeader((String) objects[3]);
                project.setProjectGoal((String) objects[4]);
                project.setProjectForwardGoals((String) objects[5]);
                project.setProjectStatus((String) objects[6]);
                project.setResponsible((String) objects[7]);
                project.setProjectType((String) objects[19]);
                project.setProjectNumber((String) objects[20]);
                project.setProjectDiscription((String) objects[21]);
                project.setLevel((String) objects[22]);
                project.setMemo((String) objects[23]);
                project.setProjectPlans(new ArrayList<ProjectPlan>());
                hashMap.put(project.getProjectId(),project);
                projectList.add(project);
            }
            ProjectPlan projectPlan = new ProjectPlan();
            projectPlan.setPlanName((String) objects[8]);
            projectPlan.setSubPlanName((String) objects[9]);
            projectPlan.setPlanBeginDate((Date) objects[10]);
            projectPlan.setPlanEndDate((Date) objects[11]);
            projectPlan.setPlanStatus(((Character) objects[12]).toString());
            projectPlan.setPeriod((int)compareDate(projectPlan.getPlanBeginDate(),projectPlan.getPlanEndDate()));
            projectPlan.setRealBeginDate((Date) objects[14]);
            projectPlan.setRealEndDate((Date) objects[15]);
            projectPlan.setProjectPlanId((String) objects[16]);
            if(objects[24]!=null)
            projectPlan.setManual(((Character) objects[24]).toString());

            if (project.getCurrentPlan() == null) {
                Date maxPlanEndDate = (Date) objects[17];
                if (projectPlan.getRealEndDate() == null) {
                    Calendar currentDate = new GregorianCalendar();
                    currentDate.set(Calendar.HOUR_OF_DAY, 0);
                    currentDate.set(Calendar.MINUTE, 0);
                    currentDate.set(Calendar.SECOND, 0);
                    currentDate.set(Calendar.MILLISECOND, 0);
                    project.setCurrentPlan(projectPlan);

                    if ("1".equals(projectPlan.getPlanStatus())) {
                        if (maxPlanEndDate.compareTo(currentDate.getTime()) == -1) {
                            projectPlan.setPlanStatus("2");
                        }
                    }

                } else if (project.getProjectPlans().size() == ((BigDecimal) objects[18]).intValue() - 1) {
                    project.setCurrentPlan(projectPlan);
                    if ("1".equals(projectPlan.getPlanStatus())) {
                        projectPlan.setPlanStatus("2");
                    }

                }
            }

            if(project.getProjectPlans().size() > 0){
                ProjectPlan lastPlan = project.getProjectPlans().get(project.getProjectPlans().size()-1);
                projectPlan.setStartPosition((int)compareDate(lastPlan.getPlanEndDate(),projectPlan.getPlanBeginDate())-2);
                int offset = 0;
                if(compareDate(lastPlan.getPlanEndDate(),projectPlan.getPlanBeginDate())==0)
                    offset = -1;
                projectPlan.setPeriod((int)compareDate(projectPlan.getPlanBeginDate(),projectPlan.getPlanEndDate())+offset);
            }else{
                projectPlan.setStartPosition((int)compareDate(beginDate,projectPlan.getPlanBeginDate())-1);
                projectPlan.setPeriod((int) compareDate(projectPlan.getPlanBeginDate(), projectPlan.getPlanEndDate()));
            }

            project.getProjectPlans().add(projectPlan);
        }
        return projectList;
    }

    @Override
    public PageResultSet<Project> find(Project project,int page,int pageSize)  throws Exception{
    	
    	if(page<1){
    		page=1;
    	}
    	if(pageSize<1){
    		pageSize=10;
    	}
        StringBuffer sb = new StringBuffer();
        HashMap parameter = new HashMap();
        sb.append("from Project p where 1=1 and removed='0' and finish='0' ");
        if(null!=project){
	        if(StringUtils.isNotBlank(project.getProjectName())) {
	            sb.append(" and p.projectName like :projectName");
	            parameter.put("projectName","%"+project.getProjectName()+"%");
	        }
	        if(StringUtils.isNotBlank(project.getProjectType())) {
	            sb.append(" and p.projectType like :projectType");
	            parameter.put("projectType","%"+project.getProjectType()+"%"); 
	        }
	        if(StringUtils.isNotBlank(project.getProjectNumber())) {
	            sb.append(" and p.projectNumber like :projectNumber");
	            parameter.put("projectNumber","%"+project.getProjectNumber()+"%"); 
	        }
        }
        if(null!=project)
        logger.error(project.getProjectType()+"   ？？？？");
        logger.error("dao");
      //先求出总记录数
    	int totalRow=0;
    	logger.error("sb:"+sb);
    	PageInfo pageInfo;
    	sb.append(" order by p.updateTime desc  ");
		try {
			Query query=this.getSession().createQuery("select count(*) "+sb.toString());
			query.setProperties(parameter);
			totalRow =Integer.valueOf(query.uniqueResult().toString());
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("错误信息："+e);
			e.printStackTrace();
		}
		
		pageInfo = new PageInfo(totalRow, pageSize, page);
		logger.error("---------------------");
        List list = getSession().createQuery(sb.toString())
					.setProperties(parameter)
					.setFirstResult(pageInfo.getBeginIndex())
					.setMaxResults(pageInfo.getPageSize()).list();
		
        sb.insert(0,"select count(*) ");
        logger.error("sb:"+sb);
        logger.error("getBeginIndex:"+pageInfo.getBeginIndex()+"     getPageSize: "+pageInfo.getPageSize());
        
        pageInfo.setTotalRow(totalRow);
        PageResultSet<Project> pageResultSet = new PageResultSet<Project>();
        pageResultSet.setList(list);
        pageResultSet.setPageInfo(pageInfo);
        return pageResultSet;
    }

    private double compareDate(Date beginDate,Date endDate){
        Calendar begin= new GregorianCalendar();
        Calendar end= new GregorianCalendar();
        begin.setTime(beginDate);
        end.setTime(endDate);
        int diffDay = ((end.get(Calendar.YEAR)-begin.get(Calendar.YEAR))*12+ end.get(Calendar.MONTH)-begin.get(Calendar.MONTH))*3;//计算当前计划开始时间距离项目计划结束时间还有几旬
        double beginDay = Math.ceil(begin.get(Calendar.DATE) / 10f) > 3 ? 3 :Math.ceil(begin.get(Calendar.DATE) / 10f);//
        double endDay = Math.ceil(end.get(Calendar.DATE) / 10f) > 3 ? 3 :Math.ceil(end.get(Calendar.DATE) / 10f);//
        double result = endDay+diffDay-beginDay;
        return result == 0 ? 0 : result+1;
    }

    public static void main(String[] args) {
        Calendar begin= new GregorianCalendar();
        begin.setTime(new GregorianCalendar(2014,4,4).getTime());
        System.out.println(begin.get(Calendar.DATE));
        ProjectDaoImpl dao = new ProjectDaoImpl();
        System.out.println(dao.compareDate(new GregorianCalendar(2014,4,4).getTime(),new GregorianCalendar(2014,7,15).getTime()));
    }

	@Override
	public int finished(String id) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("update Project p set p.finish=:finish where p.projectId=:projectId").setString("finish", "1").setString("projectId", id).executeUpdate();
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("update Project p set p.removed=:removed where p.projectId =:projectId").setString("removed", "1").setString("projectId", id).executeUpdate();
	}
}
