package com.wonders.stpt.core.userManage.dao;



import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.core.userManage.entity.vo.ManagerVo;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface StptUserDao extends AbstractHibernateDao<StptUser> {
	public List<StptUser> stptUserExist(String loginName);
	public void addPatch(List<StptUser> list);
	public Page findStptUserByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	public List<StptUser> stptUserList();
	public List<ManagerVo> getAgentInfo(String maxRows , String name_startsWith);
	public void saveAgent(String tid,String cid);
	public void deleteAgent(String tid,String cid);
}
