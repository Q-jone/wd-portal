/**
 * 
 */
package com.wonders.stpt.processDone.dao;

import com.wonders.stpt.processDone.model.bo.ProcessDone;
import com.wonders.stpt.processDone.model.bo.TDeptContactMain;
import com.wonders.stpt.processDone.model.vo.Page;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/** 
 * @ClassName: TodoItemDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午03:10:51 
 *  
 */
public interface ProcessDoneDao {
	public ProcessDone loadById(String id);	
	public ProcessDone loadByPtypeAndPid(String ptype,String pid);
	public Page findListByPage(HashMap<String, String> filter,String sortWay);
	
	public List<Object> findByHQL(String hql);
	public List<Object[]> findDatasByHQL(String hql) ;
	public void saveOrUpdateAll(Collection cols);
	public void saveOrUpdate(Object obj);
	public List<Object[]> findDoneUsers(String pname,String pid);
	public String findTaskId(String pname,String pid);	
	
	public TDeptContactMain getDeptContactMainById(String id);
	
	public String findAdderDept(String pname,String pid);
	public String findAdderName(String pname,String pid);	
	
	public List<Object[]> findProjectAssets(String asset_ids);
	
	public String findNameByLoginName(String login_name);
	public void update(Object obj);
	public List<Object> findByHQLFromStptdemo(String hql);
	
	public List<Object> queryForList(String sql,Class e);
	
	public List<Object[]> findAllDocSendType();
	
	public String findProjectStatus(String incident, String processname);
	
	public List<Object[]> findCodeIds();

    public List getUserDeptInfo(String loginName);
}
