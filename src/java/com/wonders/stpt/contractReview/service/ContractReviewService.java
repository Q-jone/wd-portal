/**
 * 
 */
package com.wonders.stpt.contractReview.service;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: ContractReviewService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:56:34 
 *  
 */
public interface ContractReviewService {
	
	public boolean saveBatch(String ids,Map<String,List<String>> list);
	
	public List<Object[]> findHtspOaByPage(int startRow,int pageSize,String contract_num,
			String contract_name,String contract_money,String opposite_company,String project_charge,
			String sign_time_start,String sign_time_end,String pstatus,String projectChargeDept);
	
	public int countHtspOa(String contract_num,
			String contract_name,String contract_money,String opposite_company,String project_charge,
			String sign_time_start,String sign_time_end,String pstatus,String projectChargeDept);
	
	public List<Object[]> findTodoByPage(int startRow,int pageSize,
			String contract_name,
			String contract_identifier,
			String contract_money_type_id,String contract_type1_id,
			String contract_type2_id,
			String contract_moneyLt,String contract_moneyGt,
			String project_charge_dept,
			String contract_group_id,String purchase_type_id,String kpi_control,
			String assignedtouser);
	
	public int countTodo(String contract_name,String contract_identifier,
			String contract_money_type_id,String contract_type1_id,
			String contract_type2_id,
			String contract_moneyLt,String contract_moneyGt,
			String project_charge_dept,
			String contract_group_id,String purchase_type_id,String kpi_control,
			String assignedtouser);
	
	public List getContractReviewDataByType(String type);
}
