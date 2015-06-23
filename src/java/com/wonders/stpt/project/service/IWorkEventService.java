package com.wonders.stpt.project.service;

import java.io.File;
import java.util.List;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkEvent;

public interface IWorkEventService {

	/**
	 * 上传文件方法
	 * @param file
	 * @param user
	 * @throws Exception
	 */
	List<String> imports(File file,String user)throws Exception;
	
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
     * 根据年份获取信息
     * @param year
     * @return
     */
    String countDataToJson(Integer year)throws Exception;
    /**
     * 获取当前月份新发现事件集合
     * @param year
     * @return
     * @throws Exception
     */
    List<WorkEvent> monthTotal(int year)throws Exception;
}
