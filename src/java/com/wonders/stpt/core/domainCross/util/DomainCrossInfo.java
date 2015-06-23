package com.wonders.stpt.core.domainCross.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wonders.stpt.core.publicFun.util.PublicFunction;
import com.wonders.stpt.organTree.entity.vo.DefaultUserVo;
import com.wonders.stpt.organTree.entity.vo.NodeVo;
import com.wonders.stpt.organTree.entity.vo.UserNodeVo;
import com.wonders.stpt.organTree.service.TorganRelationService;
import com.wonders.stpt.util.SpringBeanUtil;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;

public class DomainCrossInfo {
	private static UserService userService = null;
	private static TorganRelationService torganRelationService = null;
	private final static String DOMAINCROSSSTART = "<domainCross>";
	private final static String DOMAINCROSSEND = "</domainCross>";
	
	private static void init() {
		if (userService == null) {
			userService = (UserService) SpringBeanUtil.getBean("userService");
		}
		if (torganRelationService == null) {
			torganRelationService = (TorganRelationService) SpringBeanUtil.getBean("torganRelationService");
		}
	}
	
	static {
		init();
	}
	
	//得到指定节点信息
	public static String getNodesInfo(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			if(id==null||"".equals(id)){
				id = "2500";
			}
			List<NodeVo> list = torganRelationService.getNodesInfo(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	
	//得到指定节点相邻节点信息
	public static String getRelatedNodes(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			if(id==null||"".equals(id)){
				id = "2500";
			}
			List<NodeVo> list = torganRelationService.getRelatedNodes(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	//得到指定节点子节点信息
	public static String getChildNodes(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			if(id==null||"".equals(id)){
				id = "2500";
			}
			List<NodeVo> list = torganRelationService.getChildNodes(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	

	//得到指定部门领导层信息
	public static String getDeptLeaders(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			List<UserNodeVo> list = torganRelationService.getDeptLeaders(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	//得到指定部门大领导信息
	public static String getDeptSingleLeader(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			List<UserNodeVo> list = torganRelationService.getDeptSingleLeader(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	//得到指定部门收发员信息
	public static String getDeptReceivers(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			List<UserNodeVo> list = torganRelationService.getDeptReceivers(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	//得到指定部门人员信息
	public static String getDeptUsers(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			List<UserNodeVo> list = torganRelationService.getDeptUsers(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	//得到指定部门人员信息(去除领导)
	public static String getDeptUsersOffLeaders(String id,String token,String dataType){
		StringBuilder result = new StringBuilder();
		if(dataType!=null&&"json".equals(dataType)){
			List<UserNodeVo> list = torganRelationService.getDeptUsersOffLeaders(id);
			result.append(VOUtils.getJsonDataFromCollection(list));
		}else{
				
		}
		//System.out.println(result.toString());
		return result.toString();
	}
	
	
	
	
	
	
	
//获取登录信息-------------------------------------------------------------------------------------
	public static String getSession(String userId,String token,String dataType) {
		StringBuilder result = new StringBuilder();
		SecurityUser user = userService.loadUserWithLazyById(Long.parseLong(userId),new String[]{"organNodes"});
		if(user!=null){
			String userName = user.getName();
			String loginName = user.getLoginName();
			String deptId = "";
			String deptName = "";
			String xml = "";
			Set<OrganNode> nodes = user.getOrganNodes();
			 if (nodes != null && nodes.size() > 0) {
				 for(OrganNode node:nodes){
					 deptId= node.getId()+"";
					 deptName= node.getName();
		        }
			 }
			 if(userId != null && !"".equals(userId) && userName != null && !"".equals(userName)&& loginName != null && 
					 !"".equals(loginName) && deptId != null && !"".equals(deptId) && deptName != null && !"".equals(deptName)){
					if(dataType!=null&&"json".equals(dataType)){
						DefaultUserVo d = new DefaultUserVo();
						d.setUserId(userId+"");
						d.setLoginName(loginName);
						d.setUserName(userName);
						d.setDeptId(deptId);
						d.setDeptName(deptName);
						d.setToken(token);
						result.append(VOUtils.getJsonData(d));
					}else{
						result.append(DOMAINCROSSSTART);
						result.append("<userInfo>");	
						result.append("<userId>"+userId+"</userId>");
						result.append("<loginName>"+loginName+"</loginName>");
						result.append("<userName>"+userName+"</userName>");
						result.append("<deptId>"+deptId+"</deptId>");
						result.append("<deptName>"+deptName+"</deptName>");
						result.append("</userInfo>");	
						result.append("<token>"+token+"</token>");			   
						result.append(DOMAINCROSSEND);
					}
			 }else{
					 result.append("error");
			 }
		}else{
			result.append("error");
		}
		
		return result.toString();
	}
}
