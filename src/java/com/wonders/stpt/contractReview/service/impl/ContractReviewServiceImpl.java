/**
 * 
 */
package com.wonders.stpt.contractReview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.GsonBuilder;
import com.wonders.stpt.contractReview.dao.ContractReviewDao;
import com.wonders.stpt.contractReview.model.bo.ReviewMainBo;
import com.wonders.stpt.contractReview.model.bo.WorkflowActivity;
import com.wonders.stpt.contractReview.service.ContractReviewService;
import com.wonders.stpt.util.DateUtil;

/** 
 * @ClassName: ContractReviewServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:56:43 
 *  
 */

@Repository("contractReviewService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ContractReviewServiceImpl implements ContractReviewService{
	private ContractReviewDao dao;
	public ContractReviewDao getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("contractReviewDao")ContractReviewDao dao) {
		this.dao = dao;
	}
	
	public boolean saveBatch(String ids,Map<String,List<String>> source){
		boolean flag = false;
		try{
		String[] idArray = ids.split(",");
		List<Object> result = new ArrayList<Object>();
		Map<String, Object> args = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> ultimusMap = new HashMap<String,Object>();
		ultimusMap.put("后审直接入库", "1");
		map.put("ultimusMap", ultimusMap);
		args.put("id", idArray);
		String hql = "from ReviewMainBo t where t.removed=0 and t.id in (:id)";
		List<ReviewMainBo> list = this.dao.getList(hql,args);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				ReviewMainBo rBo = list.get(i);
				rBo.setFlag("1");
				rBo.setOperateTime(DateUtil.getCurrDate(DateUtil.TIME_FORMAT));
				WorkflowActivity wBo = new WorkflowActivity();
				
				wBo.setData(new GsonBuilder().disableHtmlEscaping().create().toJson(map));
				wBo.setProcess(rBo.getProcess());
				wBo.setIncident(rBo.getIncident());
				wBo.setSummary(rBo.getContractIdentifier() + " "+ rBo.getContractName());
				wBo.setStep(source.get(rBo.getId()).get(1));
				wBo.setOperator(source.get(rBo.getId()).get(0));
				wBo.setOperateTime(DateUtil.getCurrDate("yyyy-MM-dd HH:mm:ss"));
				wBo.setStatus("0");
				
				result.add(rBo);result.add(wBo);
			}
		}
			this.dao.addPatch(result);
			flag = true;
		}catch(Exception e){}
		return flag;
	}
	
	public int countHtspOa(String contract_num,
			String contract_name,String contract_money,String opposite_company,String project_charge,
			String sign_time_start,String sign_time_end,String pstatus,String projectChargeDept){
		return this.dao.countHtspOa(contract_num,
				 contract_name, contract_money, opposite_company, project_charge,
				 sign_time_start, sign_time_end, pstatus,projectChargeDept);
	}
	
	public List<Object[]> findHtspOaByPage(int startRow,int pageSize,String contract_num,
			String contract_name,String contract_money,String opposite_company,String project_charge,
			String sign_time_start,String sign_time_end,String pstatus,String projectChargeDept){
		return this.dao.findHtspOaByPage(startRow,pageSize,contract_num,
				 contract_name, contract_money, opposite_company, project_charge,
				 sign_time_start,sign_time_end, pstatus,projectChargeDept);
	}
	
	public List<Object[]> findTodoByPage(int startRow,int pageSize,
			String contract_name,
			String contract_identifier,
			String contract_money_type_id,String contract_type1_id,
			String contract_type2_id,
			String contract_moneyLt,String contract_moneyGt,
			String project_charge_dept,
			String contract_group_id,String purchase_type_id,String kpi_control,
			String assignedtouser){
		return this.dao.findTodoByPage(startRow, pageSize, contract_name,contract_identifier, contract_money_type_id, contract_type1_id, contract_type2_id, contract_moneyLt, contract_moneyGt, project_charge_dept, contract_group_id, purchase_type_id, kpi_control,assignedtouser);
	}
	
	public int countTodo(String contract_name,String contract_identifier,
			String contract_money_type_id,String contract_type1_id,
			String contract_type2_id,
			String contract_moneyLt,String contract_moneyGt,
			String project_charge_dept,
			String contract_group_id,String purchase_type_id,String kpi_control,
			String assignedtouser){
		return this.dao.countTodo( contract_name,contract_identifier, contract_money_type_id, contract_type1_id, contract_type2_id, contract_moneyLt, contract_moneyGt, project_charge_dept, contract_group_id, purchase_type_id, kpi_control,assignedtouser);
	}
	
public List getContractReviewDataByType(String type){
		
		List<Object[]> singleCheck = this.dao.getSingleCheckByContractType(type);
		List<Object[]> batchStore = this.dao.getBatchStoreByContractType(type);
		List<Object[]> returnCount = this.dao.getReturnCountByContractType(type);
		List<Object[]> statusCount = this.dao.getStatusCountByContractType(type);
		
//		int totalRows = singleCheck.size()+batchStore.size()+returnCount.size()+statusCount.size();
//		Object[][] result = new Object[totalRows][];
		Map<String, Map<String,Object>> map = new HashMap<String, Map<String,Object>>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		for(Object[] obj : statusCount){
            Map<String,Object> resultMap = null;
            String key="";
            if("1".equals(type)){
            	key=(String)obj[0]+(String)obj[4];
            }else{
            	key=(String)obj[0];
            }
            if(map.containsKey(key)){
                resultMap = map.get(key);
            }else{
                resultMap = new HashMap<String, Object>();
                map.put(key,resultMap);
            }
            resultMap.put("name",(String)obj[0]);
            resultMap.put("working",obj[1]);
            resultMap.put("completed",obj[2]);
            resultMap.put("total",obj[3]);
            if("1".equals(type)){
            	resultMap.put("line",(String)obj[4]);
            }
            list.add(resultMap);
		}
    //DIRECT_NUM
		for(Object[] obj : batchStore){
            Map<String,Object> resultMap = null;
            String key="";
            if("1".equals(type)){
            	key=(String)obj[0]+(String)obj[2];
            }else{
            	key=(String)obj[0];
            }
			if(map.containsKey(key)){
                resultMap = map.get(key);
			}else{
                resultMap = new HashMap<String, Object>();
                map.put(key,resultMap);
			}
			resultMap.put("name",(String)obj[0]);
			resultMap.put("direct",obj[1]);
			if("1".equals(type)){
				resultMap.put("line", obj[2]);
			}
		}
    //STEP_NUM
		for(Object[] obj : singleCheck){
            Map<String,Object> resultMap = null;
            String key="";
            if("1".equals(type)){
            	key=(String)obj[0]+(String)obj[2];
            }else{
            	key=(String)obj[0];
            }
            if(map.containsKey(key)){
                resultMap = map.get(key);
            }else{
                resultMap = new HashMap<String, Object>();
                map.put(key,resultMap);
            }
            resultMap.put("name",(String)obj[0]);
            resultMap.put("step",obj[1]);
            if("1".equals(type)){
            	resultMap.put("line", obj[2]);
            }
		}

		for(Object[] obj : returnCount){
            Map<String,Object> resultMap = null;
            String key="";
            if("1".equals(type)){
            	key=(String)obj[0]+(String)obj[2];
            }else{
            	key=(String)obj[0];
            }
            if(map.containsKey(key)){
                resultMap = map.get(key);
            }else{
                resultMap = new HashMap<String, Object>();
                map.put(key,resultMap);
            }
            resultMap.put("name",(String)obj[0]);
            resultMap.put("return",obj[1]);
            if("1".equals(type)){
            	resultMap.put("line", obj[2]);
            }
		}

//		for(int i=0; i<totalRows; i++){
//			if(result[i]==null){
//				continue;
//			}
//			for(int j=1; j<7; j++){
//				if(result[i][j]==null || result[i][j]==""){
//					result[i][j] = 0;
//				}
//
//			}
//		}
		return list;
	}

}
