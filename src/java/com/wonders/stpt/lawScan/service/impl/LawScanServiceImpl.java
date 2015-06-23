package com.wonders.stpt.lawScan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.lawScan.dao.LawScanDao;
import com.wonders.stpt.lawScan.service.LawScanService;

@Repository("lawScanService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class LawScanServiceImpl implements LawScanService{
	@Autowired
	private LawScanDao lawScanDao;
	
	public List<Object[]> findLawScanContractList(int startRow, int pageSize, 
			String plan_num, String contract_num, String self_num, String contract_name,
			String project_num, String sign_cop, String add_time_start, String add_time_end,
			String add_person, String contract_money, String pstatus,String ifRead){
		return lawScanDao.findLawScanContractList(startRow, pageSize, plan_num, contract_num,
				self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end,
				add_person, contract_money, pstatus, ifRead);
	}
	
	public int countLawScanContract(String plan_num, String contract_num, String self_num,
			String contract_name, String project_num, String sign_cop, String add_time_start, 
			String add_time_end, String add_person, String contract_money, String pstatus,String ifRead){
		return lawScanDao.countLawScanContract(plan_num, contract_num, self_num, contract_name,
				project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money, pstatus, ifRead);
	}
	
	public void save(Object obj){
		lawScanDao.save(obj);
	}
	
	public boolean findIfExists(String cname,String cid){
		return lawScanDao.findIfExists(cname, cid);
	}
}
