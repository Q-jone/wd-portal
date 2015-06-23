package com.wonders.stpt.core.menu.service;

import java.util.List;

import com.wondersgroup.framework.menu.bo.MenuResource;

/**
 * 左侧菜单减少数据库访问次数优化速度调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包方法
 * @author KaiFeng
 */
public interface newMenuService {
	/**
	 * 调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包中getChildMenuResourcesByParentMenu()方法
	 * @return 子菜单信息
	 */
	public List getChildMenuResourcesByParentMenu(MenuResource menuResource);
	/**
	 * 调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包中getTopMenuResources()方法
	 * 
	 * 当menuResource为空时调用该方法
	 * @return 
	 */
	public List getTopMenuResources();
}
