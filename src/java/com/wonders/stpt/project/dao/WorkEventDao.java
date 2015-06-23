package com.wonders.stpt.project.dao;

import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.RiskManage;
import com.wonders.stpt.project.model.WorkEvent;

public interface WorkEventDao {
	/**
	 * 批量保存
	 * @param workEvents
	 * @throws Exception
	 */
	void save(List<WorkEvent> workEvents) throws Exception;
	/**
	 * 分页查找
	 * @param workEvent
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
    PageResultSet<WorkEvent> find(WorkEvent workEvent,int page,int pageSize) throws Exception;
    /**
     * 物理删除所有记录
     * @return
     */
    int deleteAll()throws Exception;
    /**
     * 根据id值查找记录
     * @param riskManageId
     * @return
     * @throws Exception
     */
    WorkEvent load(String workEventId)throws Exception;
    /**
     * 保存记录
     * @param riskManage
     * @return
     * @throws Exception
     */
    WorkEvent save(WorkEvent workEvent)throws Exception;
    /**
     * 根据主键逻辑删除记录
     * @param riskManageId
     * @return
     */
    int deletes(String workEventId)throws Exception;
    /**
     * 统计当年数据（）
     * @param year
     * @return
     * @throws Exception
     */
    List count(Integer year)throws Exception;
    /**
     * 获取当前月份的数据 
     * @return
     * @throws Exception
     */
    List monthTotal(int year,int pageSize,int page)throws Exception;
    /**
     * 获取当前月份每个公司的数据 
     * @return
     * @throws Exception
     */
    List monthTotals(int year,int pageSize,int page)throws Exception;
}
