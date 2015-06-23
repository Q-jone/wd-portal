package com.wonders.stpt.core.menu.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.menu.bo.MenuResource;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.security.service.ACLService;
import com.wonders.stpt.core.menu.service.newMenuService;
import com.wondersgroup.framework.menu.service.MenuService;
import com.wonders.stpt.util.SpringBeanUtil;

/**
 * 左侧菜单减少数据库访问次数优化速度 
 * 配置文件为MenuContext.xml
 * 
 * @author KaiFeng
 * @version 1.0
 * @since 1.0
 */
public class MenuUtil {
	/**
	 * MENU_TYPE_CODE 标明改菜单列表类型Menu对应为"系统菜单"
	 */
	private static final String MENU_TYPE_CODE = "Menu";
	/**
	 * MENU_OPERATION_CODE  Read1对应为"显示"
	 */
	private static final String MENU_OPERATION_CODE = "Read1";
	private static OrganNodeService torganNodeService = null;
	private static ACLService taclService = null;
	private static HashMap<Long, MenuResource> map = null;
	private static HashMap<Long, OrganNode> mapDept = null;
	private static HashMap<MenuResource, List> mapResult = null;
	private static HashMap<OrganNode, List> mapNode = null;
	private static List resultAll = null;
	private static OrganTreeService tOrganTreeService = null;
	/**
	 * tnewMenuService 调用cuteframework-appmgr-4.0-RC3-SNAPSHOT.jar包中
	 * 的getChildMenuResourcesByParentMenu()和getTopMenuResources()方法
	 */
	private static newMenuService tnewMenuService = null;
	private static MenuService tMenuService = null;
	private static OrganTree organTree = null;

	static {
		init();
	}

	/**
	 * 数据初始化，公用数据写入内存
	 */
	private static void init() {
		if (mapDept == null) {
			mapDept = new HashMap<Long, OrganNode>();
		}
		if (map == null) {
			map = new HashMap<Long, MenuResource>();
		}
		if (mapResult == null) {
			mapResult = new HashMap<MenuResource, List>();
		}
		if (mapNode == null) {
			mapNode = new HashMap<OrganNode, List>();
		}
		if (tMenuService == null) {
			tMenuService = (MenuService) SpringBeanUtil.getBean("menuService");
		}
		if (tnewMenuService == null) {
			tnewMenuService = (newMenuService) SpringBeanUtil.getBean("newMenuService");
		}
		if (tOrganTreeService == null) {
			tOrganTreeService = (OrganTreeService) SpringBeanUtil.getBean("organTreeService");
		}
		if (torganNodeService == null) {
			torganNodeService = (OrganNodeService) SpringBeanUtil.getBean("organNodeService");
		}
		if (taclService == null) {
			taclService = (ACLService) SpringBeanUtil.getBean("aclService");
		}
		if (organTree == null) {
			organTree = tOrganTreeService.getOrganTreeByCode("stjt");
		}
	}

	/**
	 * 获取菜单信息
	 * 
	 * @param topid横向菜单标示，userId 用户信息，deptid 所属部门
	 * @return 用户所能查看该栏目的所有左侧菜单信息
	 */
	public static List getMenus(String topid, String userId, String deptid) {
		OrganNode organNode = loadOrganNodeById(Long.parseLong(deptid));// 第一次从数据库中取出，之后从内存中读取
		MenuResource parentMenu = getMenuResourceByIdNew(Long.parseLong(topid));// 第一次从数据库中取出，之后从内存中读取
		List navTopMenu = Arrays.asList(getAuthMenusByParentMenuAndOrgan(parentMenu, userId, organTree, organNode));
		return navTopMenu;
	}

	/**
	 * 获取菜单信息
	 * 
	 * @param menuResource每个topid对应的左侧菜单信息，userId 用户信息，node 每个deptid对应的左侧菜单信息
	 * @return 用户所能查看该栏目的所有左侧菜单信息
	 */
	public static MenuResource[] getAuthMenusByParentMenuAndOrgan(MenuResource menuResource, String userId, OrganTree tree,OrganNode node) {
		List removedList = new ArrayList();
		
		List result = getChildMenuResourcesByParentMenu(menuResource);
		List resultTemp = new ArrayList();
		for(Object o:result){
			resultTemp.add(o);
		}
		
		if ((resultTemp == null) || (resultTemp.size() == 0))
			return new MenuResource[0];
		List resourceIds = getResourceIds(resultTemp);
		//List nodes = getNodes(node);
		Map map3 = taclService.checkByCode(userId,  resourceIds,
				MENU_TYPE_CODE, MENU_OPERATION_CODE);
		for (Iterator iter = resultTemp.iterator(); iter.hasNext();) {
			MenuResource element = (MenuResource) iter.next();
			String resouceId = String.valueOf(element.getId());
			if (!Boolean.TRUE.equals(map3.get(resouceId))) {
				removedList.add(element);
			}
		}
		resultTemp.removeAll(removedList);
		return (MenuResource[]) resultTemp.toArray(new MenuResource[resultTemp.size()]);
	}

	/**
	 * 根据topId获取相应左侧菜单
	 * 
	 * 返回值时判断是否已被加载入内存，有者从内存中直接取，没有者从数据库中取 
	 * @param topId 横向菜单标示
	 * @return topId 对应左侧菜单信息
	 */
	public static MenuResource getMenuResourceByIdNew(long topId) {
		synchronized (map) {
			if (map.containsKey(topId)) {
				return map.get(topId);
			} else {
				MenuResource menuRe = (MenuResource) tMenuService.getMenuResourceById(new Long(topId));
				map.put(topId, menuRe);
				return menuRe;
			}
		}
	}

	/**
	 * 根据deptid获取相应左侧菜单
	 * 
	 * @param deptid 所属部门
	 * @return 返回该deptid所对应左侧菜单信息
	 */
	public static OrganNode loadOrganNodeById(long deptid) {
		synchronized (mapDept) {
			if (mapDept.containsKey(deptid)) {
				return mapDept.get(deptid);
			} else {
				OrganNode organNode = torganNodeService.loadOrganNodeById(new Long(deptid));
				mapDept.put(deptid, organNode);
				return organNode;
			}
		}
	}

	/**
	 * 根据部门菜单信息获取子菜单信息
	 * 
	 * @return 返回菜单信息
	 */
	public static List getNodes(OrganNode node) {
		synchronized (mapNode) {
			if (mapNode.containsKey(node)) {
				return mapNode.get(node);
			} else {
				List nodes = new ArrayList();
				nodes.addAll((Collection) Arrays.asList(torganNodeService.getAllChildNodes(node, organTree)));
				nodes.add(node);
				mapNode.put(node, nodes);
				return nodes;
			}
		}
	}

	/**
	 * 获取所有ResourceIds列表
	 * 
	 * @param result 菜单结果集
	 * @return 返回ResourceIds列表
	 */
	public static List getResourceIds(List result) {
		List resourceIds = new ArrayList();
		for (Iterator iter = result.iterator(); iter.hasNext();) {
			MenuResource element = (MenuResource) iter.next();
			resourceIds.add(String.valueOf(element.getId()));
		}
		return resourceIds;
	}

	/**
	 * 获取子菜单信息
	 * 
	 * @return 返回子菜单信息
	 */
	public static List getChildMenuResourcesByParentMenu(MenuResource menuResource) {
		if (menuResource != null) {
			synchronized (mapResult) {
				if (mapResult.containsKey(menuResource)){
					return mapResult.get(menuResource);
				} else {
					resultAll = tnewMenuService.getChildMenuResourcesByParentMenu(menuResource);
					mapResult.put(menuResource, resultAll);
					return resultAll;
				}
			}
		} else {
			List result = getTopMenuResources();
			return result;
		}
	}
	/**
	 * 重新加载内存 运行reMenuAgain.jsp
	 */
	public static void ReMenuAgain() {
		mapDept = null;
		mapResult = null;
		mapNode = null;
		map = null;
		organTree = null;
		init();
	}

	public static List getTopMenuResources() {
		List resultError = tnewMenuService.getTopMenuResources();
		return resultError;
	}

	public static OrganNodeService getTorganNodeService() {
		return torganNodeService;
	}

	public static void setTorganNodeService(OrganNodeService torganNodeService) {
		MenuUtil.torganNodeService = torganNodeService;
	}

	public static ACLService getTaclService() {
		return taclService;
	}

	public static void setTaclService(ACLService taclService) {
		MenuUtil.taclService = taclService;
	}

	public static newMenuService getTnewMenuService() {
		return tnewMenuService;
	}

	public static void setTnewMenuService(newMenuService tnewMenuService) {
		MenuUtil.tnewMenuService = tnewMenuService;
	}

	public static OrganTreeService getOrganTreeService() {

		return tOrganTreeService;
	}

	public static OrganTreeService getTOrganTreeService() {
		return tOrganTreeService;
	}

	public static void setTOrganTreeService(OrganTreeService organTreeService) {
		tOrganTreeService = organTreeService;
	}

	public static MenuService getTMenuService() {
		return tMenuService;
	}

	public static void setTMenuService(MenuService menuService) {
		tMenuService = menuService;
	}

	public static OrganTree getOrganTree() {
		return organTree;
	}

	public static void setOrganTree(OrganTree organTree) {
		MenuUtil.organTree = organTree;
	}

	public static MenuService getMenuService() {
		return tMenuService;
	}
}
