package com.wonders.stpt.core.cfconsole.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.core.cfconsole.dao.ConsoleDao;
import com.wonders.stpt.core.cfconsole.entity.bo.TuserLog;
import com.wonders.stpt.core.cfconsole.service.ConsoleService;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;

@Service("consoleService")
@Scope("prototype")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ConsoleServiceImpl implements ConsoleService{
	private ConsoleDao consoleDao;

	public ConsoleDao getConsoleDao() {
		return consoleDao;
	}
	@Autowired(required=false)
	public void setConsoleDao(@Qualifier("consoleDao")ConsoleDao consoleDao) {
		this.consoleDao = consoleDao;
	}
	
	//Tuser 表 是否存在该工号
	public StptUser tuserExist(String loginName){
		return consoleDao.tuserExist(loginName);
	}
	//Tuser 表新增该工号信息
	public void tuserAdd(StptUser u){
		consoleDao.tuserAdd(u);
	}
	//Cuser 表 是否存在该工号
	public Map<String, String> cuserExist(String loginName){
		return consoleDao.cuserExist(loginName);
	}
	//批量增加
	public void relationAddPatch(List<TuserRelation> list){
		consoleDao.relationAddPatch(list);
	}
	//tusetlog
	public void tuserlogAdd(TuserLog l){
		consoleDao.tuserlogAdd(l);
	}

    /**
     * user relation 是否存在
     * @param tid
     * @param cid
     * @return
     */
    public boolean isRelationExist(long tid,long cid){
        return consoleDao.isRelationExist(tid,cid);
    }
}
