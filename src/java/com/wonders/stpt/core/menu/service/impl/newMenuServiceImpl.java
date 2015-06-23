package com.wonders.stpt.core.menu.service.impl;

import java.util.List;

import com.wondersgroup.framework.menu.bo.MenuResource;
import com.wondersgroup.framework.menu.dao.MenuResourceDAO;
import com.wonders.stpt.core.menu.service.newMenuService;

/**
 * 左侧菜单减少数据库访问次数优化速度调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包方法
 * @author KaiFeng
 */
public class newMenuServiceImpl implements newMenuService{
	private MenuResourceDAO menuResourceDAO;

	/**
	 * 调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包中getChildMenuResourcesByParentMenu()方法
	 */
	public List getChildMenuResourcesByParentMenu(MenuResource menuResource){
		List result = null;
		result = this.menuResourceDAO.getChildMenuResourcesByParentMenu(menuResource);
		return result;
	}
	/**
	 * 调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包中getTopMenuResources()方法
	 */
	public List getTopMenuResources()
	{
		List result = this.menuResourceDAO.getTopMenuResources();
		return result;
	}
	public MenuResourceDAO getMenuResourceDAO() {
		return menuResourceDAO;
	}

	public void setMenuResourceDAO(MenuResourceDAO menuResourceDAO) {
		this.menuResourceDAO = menuResourceDAO;
	}
		   
}
