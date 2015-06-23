/**
 * 
 */
package com.wonders.stpt.contractManage.service;

import java.util.List;

import com.wonders.stpt.contractManage.model.bo.HtxxStop;

/** 
 * @ClassName: ContractManageService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:56:34 
 *  
 */
public interface ContractManageService {
	public List<Object[]> findHtspOaViewByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus,String userName);
	
	public int countHtspOaView(String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus,String userName);
	
	public List<Object[]> findHtspOaByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus);
	
	public int countHtspOa(String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus);

	public List<Object[]> findHtNewByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
										  String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
										  String add_person,String contract_money,String contract_money_end,String pstatus);
	public int countHtNew(String plan_num,String contract_num,String self_num,
						  String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
						  String add_person,String contract_money,String contract_money_end,String pstatus);
	
	public boolean save(String reason,String attach,String operator,String mainId,String process,String incident);
	
	public List<HtxxStop> find(String id);
}
