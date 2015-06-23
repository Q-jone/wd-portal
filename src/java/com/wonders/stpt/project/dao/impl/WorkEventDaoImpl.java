package com.wonders.stpt.project.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor.GetterSetterReflection;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkEventDao;
import com.wonders.stpt.project.model.WorkEvent;
@Repository("workEventDao")
public class WorkEventDaoImpl implements WorkEventDao {

	
	@Override
	public void save(List<WorkEvent> workEvents) throws Exception {
		// TODO Auto-generated method stub
		for(int i=0;i<workEvents.size();i++){
			getSession().saveOrUpdate(workEvents.get(i));
			if(i%50==0){//以50个数据作为一个处理单元
				getSession().flush();
                getSession().clear();
			}
		}
	}

	@Override
	public PageResultSet<WorkEvent> find(WorkEvent workEvent, int page,
			int pageSize) throws Exception {
		// TODO Auto-generated method stub
		Criteria c=getSession().createCriteria(WorkEvent.class);
		c.add(Example.create(workEvent).enableLike(MatchMode.START).excludeNone().excludeZeroes()
				.excludeProperty("createTime").excludeProperty("updateTime"));
		c.setProjection(Projections.count("id"));//投影统计记录总数
		Integer totalRow=(Integer) c.uniqueResult();
		
		PageInfo pageInfo=new PageInfo(totalRow, pageSize, page);
		System.out.println(pageInfo.getPageSize());
		c.setProjection(null);//?
		c.setFirstResult(pageInfo.getBeginIndex());//页面的第一条记录
		
		c.setMaxResults(pageInfo.getPageSize());//页面显示的最大记录数(Criteria存储页面上需要的记录数)
		c.addOrder(Order.desc("updateTime"));
		PageResultSet<WorkEvent> pageResultSet=new PageResultSet<WorkEvent>();
		pageResultSet.setList(c.list());//将Criteria中存储的数据放入页面结果集中
		pageResultSet.setPageInfo(pageInfo);
		return pageResultSet;
	}

	@Override
	public int deleteAll() throws Exception{
		// TODO Auto-generated method stub
		return getSession().createSQLQuery("delete from T_WORK_EVENT").executeUpdate();//原生态的sql语句
	}

	@Override
	public WorkEvent load(String workEventId) throws Exception {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(WorkEvent.class, workEventId);
	}

	@Override
	public WorkEvent save(WorkEvent workEvent) throws Exception {
		// TODO Auto-generated method stub
		/*if(StringUtils.isNotBlank(workEvent.getId()))
			getSession().update(workEvent);
		else
			getSession().save(workEvent);
			h*/
		hibernateTemplate.saveOrUpdate(workEvent);
		return workEvent;
	}

	@Override
	public int deletes(String workEventId)throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("update WorkEvent t set t.removed=:removed where t.id=:workEventId").setString("removed", "1").setString("workEventId", workEventId).executeUpdate();
	}

	private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(RiskManageDaoImpl.class);

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
	public List count(Integer year) throws Exception {
		// TODO Auto-generated method stub
		HashMap params=new HashMap();
		Calendar month = new GregorianCalendar();
		params.put("removed", "0");
		
		params.put("startTime", new GregorianCalendar(year, 0, 1).getTime());
		params.put("endTime", new GregorianCalendar(year, 11, 31).getTime());
		//new String[]{"特别重大事件（Ⅰ级）","重大事件（II级）","较大事件（Ⅲ级）"}一般事件（Ⅳ级）一般以下事件（V级）
		params.put("ranks", new String[]{"特别重大事件（Ⅰ级）","重大事件（II级）","较大事件（Ⅲ级）"});
		
		params.put("startMonth",new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
		params.put("endMonth",new GregorianCalendar(year, month.get(Calendar.MONTH)+1, 1).getTime());
		StringBuffer sql=new StringBuffer();
		//统计年度事件总数
		sql.append("SELECT COUNT(1) FROM T_WORK_EVENT WHERE  REMOVED=:removed AND TEL_TIME>=:startTime AND TEL_TIME<=:endTime ");
		//联合多个查询语句
		sql.append(" UNION ALL ");
		//*特别重大事件（Ⅰ级）总数*//当月较大事件(III级和III级以上)总数
		sql.append("SELECT COUNT(1) FROM T_WORK_EVENT WHERE REMOVED=:removed AND RANKS in (:ranks)  ");
		sql.append(" UNION ALL ");
		//当月新发生事件总数(异常，红色字体)
		sql.append("SELECT COUNT(1) FROM T_WORK_EVENT WHERE REMOVED=:removed AND TEL_TIME>=:startMonth AND TEL_TIME<:endMonth  ");
		List list=getSession().createSQLQuery(sql.toString()).setProperties(params).list();
		return list;
	}

	@Override
	public  List<WorkEvent> monthTotal(int year, int pageSize, int page) throws Exception {
		HashMap params=new HashMap();//参数集合
		Calendar month=new GregorianCalendar();
		PageInfo pageInfo = new PageInfo(10,pageSize,page);
		params.put("removed", "0");
		//获取当月的时间段
		params.put("startday", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
		params.put("endday", new GregorianCalendar(year, month.get(Calendar.MONTH)+1,1).getTime());
		System.out.println(pageInfo.getBeginIndex()+"      "+pageInfo.getPageSize());
		StringBuffer sql=new StringBuffer();
		//显示当月新发生shi
		//SELECT ID,COMPANY_NAME,TEL_TIME,BEGIN_TIME,MESSAGE_SOURCE,EVENT_TYPE,RANKS FROM T_WORK_EVENT WHERE REMOVED=:removed AND TEL_TIME>=:startday AND TEL_TIME<:endday  
		sql.append("SELECT ID,COMPANY_NAME,TEL_TIME,BEGIN_TIME,MESSAGE_SOURCE,EVENT_TYPE,RANKS FROM T_WORK_EVENT WHERE REMOVED=:removed AND TEL_TIME>=:startday AND TEL_TIME<:endday  ");
		List<Object[]> result=getSession().createSQLQuery(sql.toString()).setProperties(params).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageInfo.getPageSize()).list();
		ArrayList<WorkEvent> workEvents=new ArrayList<WorkEvent>();
		if(result!=null){
			for(Object[] obj:result){
				WorkEvent workEvent=new WorkEvent();
				workEvent.setId((String)obj[0]);
				workEvent.setCompanyName((String)obj[1]);
				//workEvent.setTelTime(telTime)
				workEvent.setMessageSource((String)obj[4]);
				workEvent.setEventType((String)obj[5]);
				workEvent.setRanks((String)obj[6]);
				workEvents.add(workEvent);
			}
		}
		return workEvents;
	}
	/**
	 * 当前月每个公司发生事件数
	 */
	@Override
	public  List monthTotals(int year, int pageSize, int page) throws Exception {
		HashMap params=new HashMap();//参数集合
		Calendar month=new GregorianCalendar();
		params.put("removed", "0");
		PageInfo pageInfo = new PageInfo(pageSize,pageSize,page);
		//获取当月的时间段
		params.put("startday", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
		params.put("endday", new GregorianCalendar(year, month.get(Calendar.MONTH)+1, 1).getTime());
		
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT COMPANY_NAME,COUNT(1)  FROM T_WORK_EVENT WHERE REMOVED=:removed AND TEL_TIME>=:startday AND TEL_TIME<:endday GROUP BY COMPANY_NAME ORDER BY COUNT(1) DESC");
		List<Object[]> result=getSession().createSQLQuery(sql.toString()).setProperties(params).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();
		
		return result;
	}
}
