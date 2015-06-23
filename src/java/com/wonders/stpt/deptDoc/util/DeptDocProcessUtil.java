package com.wonders.stpt.deptDoc.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.wonders.stpt.deptDoc.model.vo.PageQueryVo;
import com.wonders.stpt.deptDoc.model.vo.RecvQueryVo;
import com.wonders.stpt.util.ConfigDbUtil;

@Component("deptDocProcessUtil")
public class DeptDocProcessUtil {
	
	//获取部门人员
	public static List<Map<String,Object>> getDeptPerson(String deptId){
		String sql = "select 'ST/'||v.login_name loginname,v.name username "
				+ " from v_userdep v where v.dept_id=?";
		return ConfigDbUtil.getJdbcTemplate("dataSource").queryForList(sql,new Object[]{deptId});
	}
	
	//确认是否在群组中
	public static boolean existInGroup(List<String> code,String loginName){
		String sql = "select pp.id,pp.login_name as login_name,"
				+ " pp.name,d.id as Dept_id,d.name as Dept, "
				+ " d.description as description,  pp.orders"
				+ " from (select u.id,u.login_name,u.name,m.org_node_id,o.orders"
				+ " from cs_user u,cs_user_organnode o,cs_organ_node d,"
				+ " cs_user_group g,cs_group p,cs_organ_model m "
				+ " where o.security_user_id=u.id"
				+ " and o.organ_node_id=d.id"
				+ " and g.security_user_id=u.id"
				+ " and g.security_group_id=p.id"
				+ " and m.org_node_id = d.id"
				+ " and p.code in  (:code) "
				+ "  and u.removed=0 and d.removed=0"
				+ " and p.removed=0 and m.removed=0) pp,cs_organ_node d"
				+ " where pp.org_node_id=d.id and pp.login_name= :loginName ";
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;  
		//namedParameterJdbcTemplate =  
//		    new NamedParameterJdbcTemplate(dataSource);  
		namedParameterJdbcTemplate =  
		new NamedParameterJdbcTemplate(ConfigDbUtil.getJdbcTemplate("dataSource"));  
		Map<String, Object> paramMap = new HashMap<String, Object>();  
		paramMap.put("code", code);  
		paramMap.put("loginName", loginName);  
		int result = namedParameterJdbcTemplate.queryForList(sql, paramMap).size();
		if(result > 0){
			return true;
		}
		return false;
	}
	
	public static void getFields(Object obj,Map<String,Object> map){
		Field[] fields = obj.getClass().getSuperclass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);
					if(res!=null && res.toString().length() > 0){
						map.put(varName, res);
					}

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
			}
	}
	
	public static Map<String,Object> generateFilterMap(Object obj) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(obj.getClass().getSuperclass() != null && 
				!"Object".equals(obj.getClass().getSuperclass().getSimpleName())){
			getFields(obj,map);
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);
					if(res!=null && res.toString().length() > 0){
						map.put(varName, res);
					}

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
			}
		return map;
	}
	
	public static int getPage(String s){
		int page = 1;
		try{
			page = Integer.parseInt(s);
		}catch(Exception e){
			page = 1;
		}
		return page;
	}
	
	public static int getPageSize(String s){
		int pageSize = 10;
		try{
			pageSize = Integer.parseInt(s);
		}catch(Exception e){
			pageSize = 10;
		}
		return pageSize;
	}
	
	public static <T> void getVo(HttpServletRequest request,T obj){
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(obj.getClass());
		for (PropertyDescriptor targetPd : targetPds) {
			if(targetPd.getWriteMethod() != null){ 
				Object value = request.getParameter(targetPd.getName());
			 // 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
				if (value != null) {
					Method writeMethod = targetPd.getWriteMethod();
					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
	            try {
					writeMethod.invoke(obj, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			}
		}
	}
		
	public static void main(String[] args){
//		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(RecvQueryVo.class);
//		for (PropertyDescriptor targetPd : targetPds) {
//			System.out.println(targetPd.getDisplayName()+"-"+targetPd.getName());
//		}
		RecvQueryVo vo = new RecvQueryVo();
		System.out.println(vo.getClass());
		System.out.println(vo.getClass().getSuperclass());
		System.out.println(PageQueryVo.class.getSuperclass().getName());
		System.out.println(RecvQueryVo.class.getSuperclass().getSimpleName());
		System.out.println(RecvQueryVo.class.getSuperclass().getName());
		System.out.println(RecvQueryVo.class.getSuperclass().getCanonicalName());
		System.out.println(Object.class.getSuperclass());
	}
}
