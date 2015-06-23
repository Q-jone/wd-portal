package com.wonders.stpt.core.cfconsole.util;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wonders.stpt.core.cfconsole.entity.bo.TuserLog;
import com.wonders.stpt.core.cfconsole.entity.vo.UserVo;
import com.wonders.stpt.core.cfconsole.service.ConsoleService;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.util.DbUtil;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.security.bo.SecurityGroup;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;

@Component("consoleCheckUtil")
public class ConsoleCheckUtil {
	private static ConsoleService consoleService;
	private static UserService userService;
	private static OrganNodeService organNodeService;
	private static DbUtil dbUtil;
	@Autowired(required=false)
	public void setUserService(@Qualifier("userService")UserService userService) {
		ConsoleCheckUtil.userService = userService;
	}

	public static UserService getUserService() {
		return userService;
	}
	
	public static DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		ConsoleCheckUtil.dbUtil = dbUtil;
	}

	public static OrganNodeService getOrganNodeService() {
		return organNodeService;
	}
	@Autowired(required=false)
	public void setOrganNodeService(@Qualifier("organNodeService")OrganNodeService organNodeService) {
		ConsoleCheckUtil.organNodeService = organNodeService;
	}
	
	public static ConsoleService getConsoleService() {
		return consoleService;
	}
	@Autowired(required=false)
	public void setConsoleService(@Qualifier("consoleService")ConsoleService consoleService) {
		ConsoleCheckUtil.consoleService = consoleService;
	}

	public static List<TuserRelation> userCheck(UserVo vo){
		System.out.println("userCheck==============================");
		List<TuserRelation> list = new ArrayList<TuserRelation>();
		List<String> loginNames = vo.getLoginName();
		List<String> userNames = vo.getUserName();
		List<String> groups = vo.getGroup();
		String deptId = vo.getDeptId();
		String orders = vo.getOrders();
		String password = vo.getPassword();
		System.out.println(loginNames.size()+"-"+deptId+"-"+orders+"-"+password);
		tuserCheck(list,loginNames,userNames,groups,deptId,orders,password);
		return list;
 	}
		
	public static void tuserCheck(List<TuserRelation> list,List<String> loginNames,List<String> userNames,List<String> groups,String deptId,String orders,String password){
		System.out.println("tuserCheck==============================");
		String loginName,userName,group= "";
		long tid,cid = 0;
		if(loginNames!=null&&loginNames.size()>0){
			OrganNode organ = organNodeService.loadOrganNodeWithLazy(Long.parseLong(deptId), new String[] {
	            "users"
	        });
			Set<SecurityUser> users = new HashSet<SecurityUser>();
			for(int i=0;i<loginNames.size();i++){
				loginName = loginNames.get(i);
				userName = userNames.get(i);
				group = groups.get(i);
				StptUser u = consoleService.tuserExist(loginName);
				if(u==null){
					u = new StptUser();
					u.setLoginName(loginName);
					u.setName(userName);
					u.setFlag("1");
					u.setPassword("1f82c942befda29b6ed487a51da199f78fce7f05");
					consoleService.tuserAdd(u);
				}
				tid = u.getId();
				cid = cuserCheck(loginName,u.getName(),deptId,orders,group,password,users,u.getMobile1());
				if(tid!=0&&cid!=0){
                    boolean exist = consoleService.isRelationExist(tid,cid);
                    if(exist){

                    }else {
                        TuserRelation r = new TuserRelation();
                        r.setOrders(0);
                        r.setRemoved("0");
                        r.setCid(cid);
                        r.setTid(tid);
                        list.add(r);
                    }
				}
			}
			if(users.size()>0){
				organ.getUsers().addAll(users);
		        organNodeService.updateOrganNode(organ);
				//this.organNodeService.addUserToOrganNode(user, organ);
				organNodeService.updateOrganOrderByUserOrder(organ, users);
			}
		}
	}
	
	public static long cuserCheck(String loginName,String userName,String deptId,
			String orders,String group,String password,Set<SecurityUser> users,String mobile){
		System.out.println("cuserCheck==============================");
		long cid = 0;
        Map<String, String> result = consoleService.cuserExist(loginName+deptId);
		if(result.size() > 0){
			TuserLog l = new TuserLog();
			l.setLoginName(loginName);
			l.setDeptId(deptId);
			l.setName(userName);
			l.setReason("cs_user_exist");
			consoleService.tuserlogAdd(l);

            SecurityUser user = userService.loadUserById(Long.valueOf(result.get("id")));
            user.setRemoved(0);
            userService.updateUser(user);
            cid = user.getId();

		}else{
			TuserLog l = new TuserLog();
			l.setLoginName(loginName);
			l.setDeptId(deptId);
			l.setName(userName);
			l.setReason("cs_user_insert");
			consoleService.tuserlogAdd(l);
			
			SecurityUser user = new SecurityUser();
			user.setLoginName(loginName+deptId);
			user.setName(userName);
			user.setPassword(password);
			user.setStatus(1);
			user.setAccountType(1);
			user.setOrders(Integer.parseInt(orders));
			userService.createNewUser(user);
			users.add(user);
			cid = user.getId();
			addUserToGroups(cid,new long[]{2561});
			addOldOaForUltimus(user,mobile);
			addTCsuser(user);
		}
		return cid;
		
	}
	
	
	public static void addOldOaForUltimus(SecurityUser user,String mobile){
		dbUtil.getJdbcTemplate().execute("insert into cs_user c " +
				" (c.id,c.authentic_type,c.status," +
				" c.login_name,c.name,c.password,c.accounttype,c.removed,c.mobile1) values " +
				"("+(-1*user.getId())+",0,1,'"+user.getLoginName()+"','"+user.getName()+"'," +
						"'1f82c942befda29b6ed487a51da199f78fce7f05',1,0,"+mobile+")");
		
		dbUtil.getJdbcTemplate().execute("insert into cs_user_group g " +
				" ( g.security_group_id,g.security_user_id,g.id) values " +
				"(10864,"+(-1*user.getId())+","+(-1*user.getId())+")");
	}
	
	public static void addTCsuser(SecurityUser user){
		dbUtil.getJdbcTemplate().execute("insert into t_cs_user g " +
				" ( g.id,g.msg_notice) values " +
				"("+(-1*user.getId())+",1)");
	}
	
	public static void addUserToGroups(long userId, long groupIds[])
    {
        SecurityUser user = userService.loadUserById(userId);
        for(int i = 0; i < groupIds.length; i++)
        {
            long groupId = groupIds[i];
            SecurityGroup group = userService.loadGroupById(groupId);
            userService.addUserToGroup(user, group);
        }

    }

}
