package com.wonders.stpt.organTree.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.wonders.stpt.organTree.entity.vo.DefaultZtreeVO;
import com.wonders.stpt.organTree.entity.vo.NodeVo;
import com.wonders.stpt.organTree.entity.vo.OrganCheckNodeVO;
import com.wonders.stpt.organTree.entity.vo.OrganConstants;
import com.wonders.stpt.organTree.entity.vo.OrganNodeVo;
import com.wonders.stpt.organTree.entity.vo.OrganRadioNodeVO;
import com.wonders.stpt.organTree.entity.vo.UserCheckNodeVO;
import com.wonders.stpt.organTree.entity.vo.UserNodeVo;
import com.wonders.stpt.organTree.service.TorganRelationService;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.bo.OrganTree;
import com.wondersgroup.framework.organization.dao.NodeUserDAO;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;

public class OrganLoadAction extends BaseAjaxAction{
	private OrganNodeService organNodeService;
	private OrganTreeService organTreeService;
	private UserService userService;
	private TorganRelationService torganRelationService;
	
	
	public TorganRelationService getTorganRelationService() {
		return torganRelationService;
	}

	public void setTorganRelationService(TorganRelationService torganRelationService) {
		this.torganRelationService = torganRelationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public OrganNodeService getOrganNodeService() {
		return organNodeService;
	}

	public void setOrganNodeService(OrganNodeService organNodeService) {
		this.organNodeService = organNodeService;
	}

	public OrganTreeService getOrganTreeService() {
		return organTreeService;
	}

	public void setOrganTreeService(OrganTreeService organTreeService) {
		this.organTreeService = organTreeService;
	}

	public String formatNodeId(String id){
		String result = id.replace("d", "");
		result = result.replace("u", "");
		return result;
	}
	//----------------------------------------------------------------------------------
	//工作联系单接口1 获得指定节点信息
	public String getNodesInfo(){
		String nodesId = (String) super.getServletRequest().getParameter("id");	
		if(nodesId==null||"".equals(nodesId)){
			nodesId = "2500";
		}
		List<NodeVo> list = this.torganRelationService.getNodesInfo(nodesId);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}

	//工作联系单接口2 获得节点关联信息
	public String getRelatedNodes(){
		String nodesId = (String) super.getServletRequest().getParameter("id");	
		if(nodesId==null||"".equals(nodesId)){
			nodesId = "2500";
		}
		List<NodeVo> list = this.torganRelationService.getRelatedNodes(nodesId);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	
	//工作联系单接口3 异步加载子节点
	public String getChildNodes(){
		String pid = (String) super.getServletRequest().getParameter("id");	
		if(pid==null||"".equals(pid)){
			pid = "2500";
		}
		List<NodeVo> list = this.torganRelationService.getChildNodes(pid);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	//工作联系单接口4 获取部门领导层信息
	public String getDeptLeaders(){
		String pid = (String) super.getServletRequest().getParameter("id");	
		List<UserNodeVo> list = this.torganRelationService.getDeptLeaders(pid);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	//工作联系单接口5 获取部门大领导信息
	public String getDeptSingleLeader(){
		String pid = (String) super.getServletRequest().getParameter("id");	
		List<UserNodeVo> list = this.torganRelationService.getDeptSingleLeader(pid);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	//工作联系单接口4 获取部门收发员信息
	public String getDeptReceivers(){
		String pid = (String) super.getServletRequest().getParameter("id");	
		List<UserNodeVo> list = this.torganRelationService.getDeptReceivers(pid);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	//工作联系单接口4 获取部门人员信息
	public String getDeptUsers(){
		String pid = (String) super.getServletRequest().getParameter("id");	
		List<UserNodeVo> list = this.torganRelationService.getDeptUsers(pid);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	//工作联系单接口5 获取部门人员信息(去除领导)
	public String getDeptUsersOffLeaders(){
		String pid = (String) super.getServletRequest().getParameter("id");	
		List<UserNodeVo> list = this.torganRelationService.getDeptUsersOffLeaders(pid);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	
	//----------------------------------------------------------------------------------
	
	//返回给工作联系单接口
	public String getDeptRelation(){
		String rootId = (String) super.getServletRequest().getParameter("rootId");	
		if(rootId==null||"".equals(rootId)){
			rootId = "2500";
		}
		rootId = formatNodeId(rootId);
		String nodeId = (String) super.getServletRequest().getParameter("nodeId");	
		if(nodeId==null||"".equals(nodeId)){
			nodeId = "2500";
		}
		nodeId = formatNodeId(nodeId);
		List<OrganNodeVo> list = torganRelationService.findCrossNodeByCid(rootId, nodeId);
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	
	//通过节点查找属于的树
	public OrganTree getOrganTree(OrganNode node){
		OrganTree[] trees = organNodeService.getAllTrees(node);
		OrganTree tree = null;
		if(trees!=null&&trees.length>0){
			tree = trees[0];
		}else{
			tree=organTreeService.getOrganTreeByCode("stjt");
		}
		
		return tree;
	}
			
	//生成根节点
	public DefaultZtreeVO generateRootData(OrganNode node){
		DefaultZtreeVO rootVo = new DefaultZtreeVO();
		rootVo.setPid("0");
		rootVo.setName(node.getName());
		rootVo.setCid(node.getId()+"d");
		rootVo.setIsParent(true);
		rootVo.setNocheck(true);
		rootVo.setOpen(true);
		return rootVo;
	}
	
	//无选中节点 默认情况
	public List<DefaultZtreeVO> generateRootData(String rootId){
		List<DefaultZtreeVO> list = new ArrayList<DefaultZtreeVO>();
		List<OrganNode> childNodeList = new ArrayList<OrganNode>();
		if(rootId==null||"".equals(rootId)||!torganRelationService.judgeRootId(rootId)){
			rootId = "2500";
		}
		OrganNode node=organNodeService.loadOrganNodeById(Long.parseLong(rootId));
		//根节点
		list.add(generateRootData(node));
		OrganTree tree = getOrganTree(node);
		OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
		if(nodes!=null&&nodes.length>0){
			childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
		}	
		for (Iterator<OrganNode> iter = childNodeList.iterator(); iter.hasNext();) {
			DefaultZtreeVO vo = new DefaultZtreeVO ();
			OrganNode organNode = (OrganNode) iter.next();
			BeanUtils.copyProperties(organNode, vo);
			vo.setPid(rootId+"d");
			vo.setIsParent(true);
			vo.setCid(organNode.getId()+"d");
			list.add(vo);
		}
		return list;
	}
	
	//有节点选中 初始化
	public List<DefaultZtreeVO> generateInitData(String rootId,String checkNodeId){
		List<OrganNode> childNodeList = new ArrayList<OrganNode>();
		List<DefaultZtreeVO> list = generateRootData(rootId);
		List<OrganNodeVo> crossList = torganRelationService.findCrossNodeByCid(rootId, checkNodeId);
		for(OrganNodeVo nodeVo:crossList){
			if("1".equals(nodeVo.getIsRoot()) && !rootId.equals(nodeVo.getRootId()+"") && !"-1".equals(nodeVo.getRootId()+"")){
				OrganNode node = organNodeService.loadOrganNodeById(nodeVo.getRootId());
				OrganTree tree = getOrganTree(node);
				OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
				if(nodes!=null&&nodes.length>0){
					childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
				}	
				for (Iterator<OrganNode> iter = childNodeList.iterator(); iter.hasNext();) {
					DefaultZtreeVO vo = new DefaultZtreeVO ();
					OrganNode organNode = (OrganNode) iter.next();
					BeanUtils.copyProperties(organNode, vo);
					vo.setPid(nodeVo.getId()+"d");
					vo.setCid(organNode.getId()+"d");
					vo.setIsParent(true);
					list.add(vo);
				}
			}
		}	
		return list;
	}
	
	//AJAX  生成root节点、第一层节点及初始化节点
	public String generatePrimeData(){
		List<DefaultZtreeVO> list = new ArrayList<DefaultZtreeVO>();
		String rootId = (String) super.getServletRequest().getParameter("rootId");	
		String checkNodeId = (String) super.getServletRequest().getParameter("checkNodeId");
		if(rootId==null||"".equals(rootId)){
			rootId = "2500";
		}
		rootId = formatNodeId(rootId);
		if(checkNodeId==null || "".equals(checkNodeId)){
			list = generateRootData(rootId);
		}else{
			checkNodeId = formatNodeId(checkNodeId);
			list = generateInitData(rootId,checkNodeId);
		}
		
		createJSonData(VOUtils.getJsonDataFromCollection(list));
		//System.out.println(VOUtils.getJsonDataFromCollection(list));
		return AJAX;
	}
	
	
	// AJAX异步加载子节点
	//异步加载每层节点
	public String getZtreeNodes(){
		String nodeId = (String) super.getServletRequest().getParameter("cid");
		//格式化
		nodeId = formatNodeId(nodeId);
		List<DefaultZtreeVO> result = new ArrayList<DefaultZtreeVO>();
		String code = "";
		OrganNode node=organNodeService.loadOrganNodeById(Long.parseLong(nodeId));
		Map<Long,String> map = torganRelationService.findOrganRelation();
		List<OrganNode> childNodeList = new ArrayList<OrganNode>();
		if(map.containsKey(Long.parseLong(nodeId))){
			code = map.get(Long.parseLong(nodeId));
			//获取组织树
			OrganTree tree=organTreeService.getOrganTreeByCode(code);
			//获取根节点
			OrganNode rootNode = organNodeService.loadOrganNodeByCode(code);
			//获取该组织树下的根节点的子节点
			OrganNode[] nodes = organNodeService.getChildNodes(rootNode,tree);
			if(nodes!=null&&nodes.length>0){
				childNodeList=Arrays.asList(organNodeService.getChildNodes(rootNode,tree));	
			}
		}else{
			OrganTree tree = getOrganTree(node);
			OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
			if(nodes!=null&&nodes.length>0){
				childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
			}
		}
		
		for (Iterator<OrganNode> iter = childNodeList.iterator(); iter.hasNext();) {
			DefaultZtreeVO defaultZtreeVO = new DefaultZtreeVO ();
			OrganNode organNode = (OrganNode) iter.next();
			BeanUtils.copyProperties(organNode, defaultZtreeVO);
			defaultZtreeVO.setPid(nodeId+"d");
			defaultZtreeVO.setCid(organNode.getId()+"d");
			defaultZtreeVO.setIsParent(true);
			result.add(defaultZtreeVO);
		}
		//System.out.println(VOUtils.getJsonDataFromCollection(result));
		createJSonData(VOUtils.getJsonDataFromCollection(result));
		return AJAX;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//----------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	
	
	
	//一次性加载所有节点
	public String loadTreeForDeptAll() {
		//String rootId = (String) super.getServletRequest().getParameter("rootId");		
		List<DefaultZtreeVO> result = new ArrayList<DefaultZtreeVO>();
		OrganNode node=organNodeService.loadOrganNodeById(2500);
		DefaultZtreeVO defaultZtreeVO = new DefaultZtreeVO ();
		defaultZtreeVO.setPid("0");
		defaultZtreeVO.setName(node.getName());
		defaultZtreeVO.setCid(node.getId()+"");
		defaultZtreeVO.setIsParent(true);
		defaultZtreeVO.setNocheck(true);
		defaultZtreeVO.setOpen(false);
		result.add(defaultZtreeVO);
		addDept(result,2500);
		createJSonData(VOUtils.getJsonDataFromCollection(result));
		return AJAX;
	}
	
	public void addDept(List<DefaultZtreeVO> result,long nodeId){
		Map<Long,String> map = torganRelationService.findOrganRelation();
		List<OrganNode> childNodeList = new ArrayList<OrganNode>();
		OrganNode node=organNodeService.loadOrganNodeById(nodeId);
		String code = "";
		if(map.containsKey(nodeId)){
			code = map.get(nodeId);
			//获取组织树
			OrganTree tree=organTreeService.getOrganTreeByCode(code);
			//获取根节点
			OrganNode rootNode = organNodeService.loadOrganNodeByCode(code);
			//获取该组织树下的根节点的子节点
			OrganNode[] nodes = organNodeService.getChildNodes(rootNode,tree);
			if(nodes!=null&&nodes.length>0){
				childNodeList=Arrays.asList(organNodeService.getChildNodes(rootNode,tree));	
			}
		}else{
			OrganTree[] trees = organNodeService.getAllTrees(node);
			OrganTree tree = null;
			if(trees!=null&&trees.length>0){
				tree = trees[0];
			}else{
				tree=organTreeService.getOrganTreeByCode("stjt");
			}
			
			OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
			if(nodes!=null&&nodes.length>0){
				childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
			}
		}
		
		
		for (Iterator<OrganNode> iter = childNodeList.iterator(); iter.hasNext();) {
				DefaultZtreeVO defaultZtreeVO = new DefaultZtreeVO ();
				OrganNode organNode = (OrganNode) iter.next();
				addDept(result,organNode.getId());
				BeanUtils.copyProperties(organNode, defaultZtreeVO);
				defaultZtreeVO.setPid(nodeId+"");
				defaultZtreeVO.setCid(organNode.getId()+"");
				defaultZtreeVO.setIsParent(true);
				defaultZtreeVO.setNocheck(true);
				defaultZtreeVO.setOpen(false);
				result.add(defaultZtreeVO);
		}
		//createJSonData(VOUtils.getJsonDataFromCollection(result));
		//return AJAX;
	}
	
		
	//-----------------------------------------------------------------------------------------------------------------------------
	public String loadTreeForUser() {

		String parentId = (String) super.getServletRequest().getParameter("cid");
		//System.out.println("parentId="+parentId);
		List result = new ArrayList();
		List childNodeList = new ArrayList();
		String deptPid = "";
		if (parentId != null && !parentId.equalsIgnoreCase("") && !parentId.equals("0")){
			OrganNode parentNode=organNodeService.loadOrganNodeWithLazy(Long.parseLong(parentId.split(",")[0]),new String[]{"organNodeType","users"});
			//判断父节点的组织类型是否允许包含人员
			if(parentNode.getOrganNodeType().getPeople()==1)
			{	
				List<SecurityUser> users=organNodeService.getUsersByOrganOrder(Long.parseLong(parentId.split(",")[0]));
				//将所有人员添加到节点列表中
				childNodeList.addAll(users);
				//循环子节点列表，将节点对象转化为人员节点VO对象
				for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
					Map element = (Map) iter.next();
					String userId = element.get(NodeUserDAO.SECURITY_SUER_ID).toString();
					String order = element.get(NodeUserDAO.ORDERS).toString();
					SecurityUser user = userService.loadUserById(Long.parseLong(userId));
					user.setOrders(Long.parseLong(order));		
					UserCheckNodeVO userCheckNodeVO = new UserCheckNodeVO();
					BeanUtils.copyProperties(user, userCheckNodeVO);
					userCheckNodeVO.setPid(parentNode.getId()+","+"dept");
					userCheckNodeVO.setCid(userCheckNodeVO.getPid()+","+"user");
					userCheckNodeVO.setDeptId(parentNode.getId()+"");
					userCheckNodeVO.setDeptName(parentNode.getName());
					result.add(userCheckNodeVO);
				}
				
				createJSonData(VOUtils.getJsonDataFromCollection(result));
				return AJAX;
			}
			
			//如果是其他节点	
			else
			{
				OrganNode node = null;
				if(Long.valueOf(parentId.split(",")[0]).longValue()==2545){
					node=organNodeService.loadOrganNodeById(2568l);
				}else{
					node=organNodeService.loadOrganNodeById(Long.valueOf(parentId.split(",")[0]).longValue());
				}
				OrganTree[] trees = organNodeService.getAllTrees(node);
				OrganTree tree = null;
				if(trees!=null&&trees.length>0){
					tree = trees[0];
				}else{
					tree=organTreeService.getOrganTreeByCode("stjt");
				}
				
				//System.out.println("trees.length"+trees.length);
				for(OrganTree t:trees){
					//System.out.println("trees.name"+t.getName());
				}
				
				OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
				if(nodes!=null&&nodes.length>0){
					childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
					deptPid = node.getId()+","+"dept";
				}else{
					createJSonData("");
					return AJAX;
				}
				
			}
		}
		//否则就加载顶层节点列表
		else 
		{		
			childNodeList.add(organNodeService.loadOrganNodeByCode("stjt"));
		}
		//循环子节点列表，将节点对象转化为其他节点VO对象
		for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
			OrganRadioNodeVO organRadioNodeVO = new OrganRadioNodeVO ();
			OrganNode organNode = (OrganNode) iter.next();
			BeanUtils.copyProperties(organNode, organRadioNodeVO);
			if (parentId != null && !parentId.equalsIgnoreCase("") && !parentId.equals("0")){
				organRadioNodeVO.setPid(deptPid);
				organRadioNodeVO.setCid(organNode.getId()+","+"dept");
			}else{
				organRadioNodeVO.setPid("2500,dept");
			}
			result.add(organRadioNodeVO);
		}
		createJSonData(VOUtils.getJsonDataFromCollection(result));
		return AJAX;
	}

	
	public String loadTreeForDept() {

		String parentId = (String) super.getServletRequest().getParameter("cid");
		List result = new ArrayList();
		List childNodeList = new ArrayList();
		String deptPid = "";
		if (parentId != null && !parentId.equalsIgnoreCase("") && !parentId.equals("0")){
			OrganNode parentNode=organNodeService.loadOrganNodeWithLazy(Long.parseLong(parentId.split(",")[0]),new String[]{"organNodeType","users"});
			//如果是其他节点			
				OrganNode node = null;
				if(Long.valueOf(parentId.split(",")[0]).longValue()==2545){
					node=organNodeService.loadOrganNodeById(2568l);
				}else{
					node=organNodeService.loadOrganNodeById(Long.valueOf(parentId.split(",")[0]).longValue());
				}
				OrganTree[] trees = organNodeService.getAllTrees(node);
				OrganTree tree = null;
				if(trees!=null&&trees.length>0){
					tree = trees[0];
				}else{
					tree=organTreeService.getOrganTreeByCode("stjt");
				}
				
				//System.out.println("trees.length"+trees.length);
				for(OrganTree t:trees){
					//System.out.println("trees.name"+t.getName());
				}
				
				OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
				if(nodes!=null&&nodes.length>0){
					childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
					deptPid = node.getId()+","+"dept";
				}else{
					createJSonData("");
					return AJAX;
				}
				
			}
		//否则就加载顶层节点列表
		else 
		{		
			childNodeList.add(organNodeService.loadOrganNodeByCode("stjt"));
		}
		//循环子节点列表，将节点对象转化为其他节点VO对象
		for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
			OrganCheckNodeVO organCheckNodeVO = new OrganCheckNodeVO ();
			OrganNode organNode = (OrganNode) iter.next();
			BeanUtils.copyProperties(organNode, organCheckNodeVO);
			if (parentId != null && !parentId.equalsIgnoreCase("") && !parentId.equals("0")){
				organCheckNodeVO.setPid(deptPid);
				organCheckNodeVO.setCid(organNode.getId()+","+"dept");
			}else{
				organCheckNodeVO.setPid("2500,dept");
			}
			result.add(organCheckNodeVO);
		}
		createJSonData(VOUtils.getJsonDataFromCollection(result));
		return AJAX;
	}
	

	public String loadTreeForUserAll() {
		String rootId = (String) super.getServletRequest().getParameter("rootId");
		String secondId = (String) super.getServletRequest().getParameter("secondId");
		
		List<DefaultZtreeVO> result = new ArrayList<DefaultZtreeVO>();
		OrganNode node=organNodeService.loadOrganNodeById(Long.parseLong(rootId.split(",")[0]));
		DefaultZtreeVO defaultZtreeVO = new DefaultZtreeVO ();
		defaultZtreeVO.setPid("0");
		defaultZtreeVO.setName(node.getName());
		defaultZtreeVO.setCid(node.getId()+","+"dept");
		defaultZtreeVO.setIsParent(true);
		defaultZtreeVO.setNocheck(true);
		defaultZtreeVO.setOpen(true);
		result.add(defaultZtreeVO);
		addDeptNode(result,rootId);
		createJSonData(VOUtils.getJsonDataFromCollection(result));
		return AJAX;
	}
	
	//追加部门节点
	public void addDeptNode(List<DefaultZtreeVO> voList,String nodeId){
		List childNodeList = new ArrayList();
		String deptPid = "";
		if (nodeId != null && !nodeId.equalsIgnoreCase("") && !nodeId.equals("0")){
			OrganNode node=organNodeService.loadOrganNodeById(Long.parseLong(nodeId.split(",")[0]));
			OrganTree[] trees = organNodeService.getAllTrees(node);
			OrganTree tree = null;
			if(trees!=null&&trees.length>0){
				tree = trees[0];
			}else{
				tree=organTreeService.getOrganTreeByCode("stjt");
			}
			
			OrganNode[] nodes = organNodeService.getChildNodes(node,tree);
			if(nodes!=null&&nodes.length>0){
				childNodeList=Arrays.asList(organNodeService.getChildNodes(node,tree));	
				deptPid = node.getId()+","+"dept";
			}
			//否则就加载顶层节点列表
		}else 
		{		
			childNodeList.add(organNodeService.loadOrganNodeByCode("stjt"));
		}
		//循环子节点列表，将节点对象转化为其他节点VO对象
		for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
			DefaultZtreeVO defaultZtreeVO = new DefaultZtreeVO ();
			OrganNode organNode = (OrganNode) iter.next();
			addDeptNode(voList,organNode.getId()+"");
			addUserNode(voList,organNode.getId()+"");
			BeanUtils.copyProperties(organNode, defaultZtreeVO);
			defaultZtreeVO.setPid(deptPid);
			defaultZtreeVO.setCid(organNode.getId()+","+"dept");
			defaultZtreeVO.setIsParent(true);
			defaultZtreeVO.setNocheck(true);
			defaultZtreeVO.setOpen(false);
			voList.add(defaultZtreeVO);
		}
	}
	
	public void addUserNode(List<DefaultZtreeVO> voList,String nodeId){
		List childNodeList = new ArrayList();
		OrganNode parentNode=organNodeService.loadOrganNodeWithLazy(Long.parseLong(nodeId),new String[]{"organNodeType","users"});
		//判断父节点的组织类型是否允许包含人员
		if(parentNode.getOrganNodeType().getPeople()==1)
		{	
			List<SecurityUser> users=organNodeService.getUsersByOrganOrder(Long.parseLong(nodeId.split(",")[0]));
			//将所有人员添加到节点列表中
			childNodeList.addAll(users);
			//循环子节点列表，将节点对象转化为人员节点VO对象
			for (Iterator iter = childNodeList.iterator(); iter.hasNext();) {
				Map element = (Map) iter.next();
				String userId = element.get(NodeUserDAO.SECURITY_SUER_ID).toString();
				String order = element.get(NodeUserDAO.ORDERS).toString();
				SecurityUser user = userService.loadUserById(Long.parseLong(userId));
				user.setOrders(Long.parseLong(order));		
				DefaultZtreeVO defaultZtreeVO = new DefaultZtreeVO();
				BeanUtils.copyProperties(user, defaultZtreeVO);
				defaultZtreeVO.setPid(parentNode.getId()+","+"dept");
				defaultZtreeVO.setCid(defaultZtreeVO.getPid()+","+"user");
				defaultZtreeVO.setDeptId(parentNode.getId()+"");
				defaultZtreeVO.setDeptName(parentNode.getName());
				voList.add(defaultZtreeVO);
			}
		}
	}
	
}
