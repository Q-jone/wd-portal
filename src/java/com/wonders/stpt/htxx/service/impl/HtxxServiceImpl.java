/**
 * 
 */
package com.wonders.stpt.htxx.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.htxx.service.HtxxService;

/** 
 * @ClassName: HtxxServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-5-15 下午2:12:38 
 *  
 */

@Repository("htxxService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class HtxxServiceImpl implements HtxxService{
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Resource(name="jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public List<Map<String,Object>> getData(){
		String sql = "select c.contract_no,c.contract_name,c.contract_type," +
				" c.contract_owner_execute_name,c.build_supplier_name," +
				" c.contract_signed_date,c.contract_price," +
				" t.stat_date," +
				" t.process_count_month,t.process_count_year,t.process_count_total," +
				" t.change_count_month,t.change_count_year,t.change_count_total," +
				" t.change_money_month,t.change_money_year,t.change_money_total," +
				" t.change_percent_month,t.change_percent_year,t.change_percent_total," +
				" t.pay_plan_money_month,t.pay_plan_money_year,t.pay_plan_money_total," +
				" t.pay_plan_percent_month,t.pay_plan_percent_year,t.pay_plan_percent_total," +
				" t.pay_actual_money_month,t.pay_actual_money_year,t.pay_actual_money_total," +
				" t.pay_actual_percent_month,t.pay_actual_percent_year,t.pay_actual_percent_total " +
				" from dw_contract_info  t , c_contract c where c.removed=0 and t.contract_id=c.id";
		
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		
		return list;
		
	}
	
	public int getCount(){
		String sql = "select count(t.id)" +
				" from dw_contract_info  t , c_contract c where c.removed=0 and t.contract_id=c.id";
		int count = this.jdbcTemplate.queryForInt(sql);
		
		return count;
	}
	
	public List<Map<String,Object>> executeSQl(String sql){
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		return list;
	}
	

}
