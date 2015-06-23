package com.wonders.stpt.core.cfconsole.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.cfconsole.entity.bo.TuserLog;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;

public interface ConsoleDao {
	//Tuser 表 是否存在该工号
	public StptUser tuserExist(String loginName);
	//Tuser 表新增该工号信息
	public void tuserAdd(StptUser u);
	//Cuser 表 是否存在该工号
	public Map<String, String> cuserExist(String loginName);
	//批量增加
	public void relationAddPatch(List<TuserRelation> list);
	//tuserlog
	public void tuserlogAdd(TuserLog l);

    /**
     * relation表是否存在
     * @param tid
     * @param cid
     * @return
     */
    public boolean isRelationExist(long tid,long cid);
}
