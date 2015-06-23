/**
 * 
 */
package com.wonders.stpt.metroExpress.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.core.code.CodeUtil;
import com.wonders.stpt.metroExpress.dao.MetroExpressDao;
import com.wonders.stpt.metroExpress.entity.bo.MetroExpress;
import com.wonders.stpt.metroExpress.service.MetroExpressService;
import com.wondersgroup.framework.core.bo.Page;

/** 
 * @ClassName: MetroExpressServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午07:44:21 
 *  
 */
public class MetroExpressServiceImpl implements MetroExpressService{
	private MetroExpressDao metroExpressDao;

	public MetroExpressDao getMetroExpressDao() {
		return metroExpressDao;
	}

	public void setMetroExpressDao(MetroExpressDao metroExpressDao) {
		this.metroExpressDao = metroExpressDao;
	}
	
	public MetroExpress viewMetroExpress(String id){
		return (MetroExpress)metroExpressDao.load(id);
	}
	
	public void addMetroExpress(MetroExpress metroExpress){
		metroExpressDao.save(metroExpress);
	}
	
	public void updateMetroExpress(MetroExpress metroExpress){
		metroExpressDao.update(metroExpress);
	}
	
	public List<MetroExpress> findLatestMetroExpressEvents(String accidentLine, String accidentEmergency, int size){
		return metroExpressDao.findLatestMetroExpressEvents(accidentLine,accidentEmergency,size);
	}
	
	public List<String> findMetroExpressCode(String codeType_code, String codeInfo_code){
		return CodeUtil.findCodeInfoByCode(codeType_code, codeInfo_code);
	}
	
	public Page findMetroExpressByPage(Map<String, Object> filter,int pageNo, int pageSize){
		return metroExpressDao.findMetroExpressByPage(filter, pageNo, pageSize);
	}
	
	public List<String> findMetroLineConfig(){
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		String sql="select line_id,line_name from t_metro_line order by sorting_order ";
		List<String> lists = new ArrayList<String>();
		String name = "";
		String id = "";
		try{
			//System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				id = rs.getString("line_id");
				name = rs.getString("line_name");
				lists.add(id+":"+name);
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return lists;
	}
	
	public Map<String,String> findMetroLineConfigMap(){
		Map<String,String> map = new HashMap<String,String>();
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		String sql="select line_id,line_name from t_metro_line order by sorting_order ";
		String name = "";
		String id = "";
		try{
			//System.out.println("sql=:"+sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				map.put( rs.getString("line_id"),rs.getString("line_name"));
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return map;
	}
	
}
