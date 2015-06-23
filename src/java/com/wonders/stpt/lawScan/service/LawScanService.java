package com.wonders.stpt.lawScan.service;

import java.util.List;

public interface LawScanService {
	public List<Object[]> findLawScanContractList(int startRow, int pageSize, 
			String plan_num, String contract_num, String self_num, String contract_name,
			String project_num, String sign_cop, String add_time_start, String add_time_end,
			String add_person, String contract_money, String pstatus,String ifRead);
	
	public int countLawScanContract(String plan_num, String contract_num, String self_num,
			String contract_name, String project_num, String sign_cop, String add_time_start, 
			String add_time_end, String add_person, String contract_money, String pstatus,String ifRead);
	
	public void save(Object obj);
	
	public boolean findIfExists(String cname,String cid);
	
	
}
