/**
 * 
 */
package com.wonders.stpt.processDone.service.impl;

import com.wonders.stpt.processDone.dao.ProcessDoneDao;
import com.wonders.stpt.processDone.model.bo.ProcessDone;
import com.wonders.stpt.processDone.model.bo.TDeptContactMain;
import com.wonders.stpt.processDone.model.vo.Page;
import com.wonders.stpt.processDone.service.ProcessDoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/** 
 * @ClassName: TodoItemServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-1-21 下午03:15:46 
 *  
 */
@Repository("processDoneService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ProcessDoneServiceImpl implements ProcessDoneService{
	@Autowired
	private ProcessDoneDao processDoneDao;

	public ProcessDone loadById(String id){
		ProcessDone msg=processDoneDao.loadById(id);
		return msg;
	}	
	
	public ProcessDone loadByPtypeAndPid(String ptype,String pid){
		ProcessDone msg=processDoneDao.loadByPtypeAndPid(ptype,pid);
		return msg;
	}	
	
	public Page findByPage(HashMap<String, String> filter,String sortWay){
		Page page=processDoneDao.findListByPage(filter, sortWay);
		return page;
	}	
		
	public List<Object> findByHQL(String hql){
		return processDoneDao.findByHQL(hql);
	}
	
	public List<Object[]> findDatasByHQL(String hql) {
		return processDoneDao.findDatasByHQL(hql);
	}
	
	public void saveOrUpdateAll(Collection cols){
		processDoneDao.saveOrUpdateAll(cols);
	}
	
	public void saveOrUpdate(Object obj){
		processDoneDao.saveOrUpdate(obj);
	}
	
	public List<Object[]> findDoneUsers(String pname,String pid){
		return processDoneDao.findDoneUsers(pname, pid);
	}
	
	public String findTaskId(String pname,String pid){
		return processDoneDao.findTaskId(pname, pid);
	}
	
	public TDeptContactMain getDeptContactMainById(String id){
		return processDoneDao.getDeptContactMainById(id);
	}

	public String findAdderDept(String pname,String pid){
		return processDoneDao.findAdderDept(pname, pid);
	}
	
	public String findAdderName(String pname,String pid){
		return processDoneDao.findAdderName(pname, pid);
	}
	
	public List<Object[]> findProjectAssets(String asset_ids){
		return processDoneDao.findProjectAssets(asset_ids);
	}
	public String findNameByLoginName(String login_name){
		return processDoneDao.findNameByLoginName(login_name);
	}
	public void update(Object obj){
		processDoneDao.update(obj);
	}
	public List<Object> findByHQLFromStptdemo(String hql){
		return processDoneDao.findByHQLFromStptdemo(hql);
	}
	
	public List<Object> queryForList(String sql,Class e){
		return processDoneDao.queryForList(sql, e);
	}
	
	public List<Object[]> findAllDocSendType(){
		return processDoneDao.findAllDocSendType();
	}

	@Override
	public String findProjectStatus(String incident, String processname) {
		return processDoneDao.findProjectStatus(incident, processname);
	}
	
	public List<Object[]> findCodeIds(){
		return processDoneDao.findCodeIds();
	}

    public List getUserDeptInfo(String loginName){
        return this.processDoneDao.getUserDeptInfo(loginName);
    }
}
