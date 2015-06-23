/*    */package com.wonders.stpt.contract.service.impl;

/*    */
/*    */import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wonders.stpt.contract.dao.DwContractCoverDao;
import com.wonders.stpt.contract.entity.bo.CoverAlarm;
import com.wonders.stpt.contract.entity.bo.CoverContractBid;
import com.wonders.stpt.contract.entity.bo.CoverContractPrice;
import com.wonders.stpt.contract.entity.bo.CoverManagement;
import com.wonders.stpt.contract.entity.bo.CoverProjectContractStat;
import com.wonders.stpt.contract.entity.bo.DwContractCover;
import com.wonders.stpt.contract.entity.vo.CoverContractBidVo;
import com.wonders.stpt.contract.service.DwContractCoverService;

/*    */
/*    */@Service("dwContractCoverService")
/*    */public class DwContractCoverServiceImpl
/*    */implements DwContractCoverService
/*    */{
	/*    */
	/*    */@Autowired(required = false)
	/*    */private DwContractCoverDao dwContractCoverDao;

	/*    */
	/*    */public void setDwContractCoverDao(
			@Qualifier("dwContractCoverDao") DwContractCoverDao dwContractCoverDao)
	/*    */{
		/* 38 */this.dwContractCoverDao = dwContractCoverDao;
		/*    */}

	/*    */
	/*    */public DwContractCover findByContractType(String contractType)
	/*    */{
		/* 43 */List list = this.dwContractCoverDao
				.findByContractType(contractType);
		/* 44 */if ((list == null) || (list.size() < 1))
			return null;
		/* 45 */return (DwContractCover) list.get(0);
		/*    */}

	/*    */
	/*    */public List<DwContractCover> findByCompanyType(String conpanyType)
	/*    */{
		/* 51 */return this.dwContractCoverDao.findByCompanyType(conpanyType);
		/*    */}

	/*    */
	/*    */public List<DwContractCover> findByContractTypeWithFuzzySearch(
			String contractType)
	/*    */{
		/* 56 */return this.dwContractCoverDao
				.findByContractTypeWithFuzzySearch(contractType);
		/*    */}

	/*    */
	/*    */public String executeSqlReturnOneResult(String sql)
	/*    */{
		/* 61 */List list = this.dwContractCoverDao.executeSql(sql);
		/*    */try {
			/* 63 */if ((list != null) && (list.size() > 0)
					&& (list.get(0) != null)) {
				/* 64 */BigDecimal result = (BigDecimal) list.get(0);
				/* 65 */return list.get(0).toString();
				/*    */}
			/*    */} catch (Exception e) {
			/* 68 */e.printStackTrace();
			/*    */}
		/* 70 */return null;
		/*    */}

	@Override
	public List<CoverContractPrice> findCoverContractPrice(String type) {

		return dwContractCoverDao.findCoverContractPrice(type);
	}

	@Override
	public CoverProjectContractStat findCoverProjectContractStat() {
		return dwContractCoverDao.findCoverProjectContractStat();
	}

	@Override
	public List<Long> findProjectCountByDate(String date, int dayLength) {

		return dwContractCoverDao.findProjectCountByDate(date, dayLength);
	}

	@Override
	public List<Double> findProjectPriceByDate(String date, int dayLength) {
		return dwContractCoverDao.findProjectPriceByDate(date, dayLength);
	}

	@Override
	public List<Long> findContractCountByDate(String date, int dayLength) {
		return dwContractCoverDao.findContractCountByDate(date, dayLength);
	}

	@Override
	public List<Double> findContractPriceByDate(String date, int dayLength) {
		return dwContractCoverDao.findContractPriceByDate(date, dayLength);
	}

	@Override
	public List<Double> findChangedPriceByDate(String date, int dayLength,
			String type) {

		return dwContractCoverDao.findChangedPriceByDate(date, dayLength, type);
	}

	@Override
	public void calCoverContractPrice() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		int startYear = 2008; // 默认从2008年号开始
		int endYear = Calendar.getInstance().get(Calendar.YEAR);
		
		List<CoverContractPrice> saveList = new ArrayList<CoverContractPrice>();
		for(int m=startYear;m<=endYear; m++){
			int minMonth = 0;
			int maxMonth = 11;
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, m);
			if(m==endYear){
				maxMonth = c.get(Calendar.MONTH);
			}
			for(int i=minMonth; i<=maxMonth; i++){
				c.set(Calendar.MONTH, i);
				
				String controlDate = sdf.format(c.getTime());
				try {
					Object[] contractData = dwContractCoverDao
							.findContractPriceAndCountByYearMonth(controlDate);
					CoverContractPrice ccp = dwContractCoverDao
							.findCoverContractPriceByControlDate(controlDate);
					if (ccp == null) {
						ccp = new CoverContractPrice();
						ccp.setControlDate(controlDate);
						ccp.setCreateDate(new Date());
					}
					ccp.setContractCount((Long) contractData[0]);
					ccp.setContractPrice((Double) contractData[1]);
					saveList.add(ccp);
					//dwContractCoverDao.saveOrUpdateCoverContractPrice(ccp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		dwContractCoverDao.saveOrUpdateAllCoverContractPrice(saveList);
	}

	@Override
	public void calCoverProjectContractStat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Object[] projectMonthData = null;
		Object[] projectYearData = null;
		Object[] contractMonthData= null;
		Object[] contractYearData= null;
		List<Double> changedContractPrice = null;
		List<Double> payData = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
		
		int year = calendar.get(Calendar.YEAR);
		String yearMonth = sdf.format(new Date());
		String date = sdf2.format(calendar.getTime());
		
		CoverProjectContractStat cpcs = dwContractCoverDao.findCoverProjectContractStat();
		if(cpcs==null) cpcs = new CoverProjectContractStat();
		
		projectMonthData = dwContractCoverDao.findProjectCountAndPriceByDate(yearMonth);		//月，[项目数,项目金额]
		projectYearData = dwContractCoverDao.findProjectCountAndPriceByDate(year+"");			//年,[项目数，项目金额]
		
		contractMonthData = dwContractCoverDao.findContractCountAndPriceByDate(yearMonth);		//月，[合同数，合同金额]
		contractYearData = dwContractCoverDao.findContractCountAndPriceByDate(year+"");			//年，[合同数，合同金额]
		changedContractPrice = dwContractCoverDao.findContractStatusMoneyByTypeAndDate(yearMonth, (year+""),"1");				//变更支付，[月支付，年支付]
		payData = dwContractCoverDao.findContractStatusMoneyByTypeAndDate(yearMonth, (year+""),"3");				//实际支付，[月支付，年支付]
		
		double monthContractPrice=0.0,yearContractPrice=0.0;
		if(contractMonthData!=null && contractMonthData[1]!=null) monthContractPrice += (Double)contractMonthData[1];
		if(changedContractPrice!=null && changedContractPrice.get(0)!=null) monthContractPrice +=changedContractPrice.get(0);
		if(contractYearData!=null && contractYearData[1]!=null) yearContractPrice +=(Double)contractYearData[1];
		if(changedContractPrice!=null && changedContractPrice.get(1)!=null) yearContractPrice +=changedContractPrice.get(1);
		
		cpcs.setCalDay(date);
		cpcs.setMonthProjectCount((Long)projectMonthData[0]);
		cpcs.setMonthProjectPrice((Double)projectMonthData[1]);
		cpcs.setYearProjectCount((Long)projectYearData[0]);
		cpcs.setYearProjectPrice((Double)projectYearData[1]);
		
		cpcs.setMonthContractCount((Long)contractMonthData[0]);
		cpcs.setMonthContractPrice(monthContractPrice);
		cpcs.setYearContractCount((Long)contractYearData[0]);
		cpcs.setYearContractPrice(yearContractPrice);
		cpcs.setMonthPayPrice(payData.get(0));
		cpcs.setYearPayPrice(payData.get(1));
		
		dwContractCoverDao.saveOrUpdate(cpcs);
	}

	@Override
	public List<CoverManagement> findCoverManagement() {
		return dwContractCoverDao.findCoverManagement();
	}

	@Override
	public void calCoverManagement() {
		Map<String, String> companyMap = new HashMap<String, String>();
		companyMap.put("2718", "车辆分公司");
		companyMap.put("2719", "供电分公司");
		companyMap.put("2720", "通号分公司");
		companyMap.put("2721", "工务分公司");
		companyMap.put("2722", "物资分公司");
		companyMap.put("2545", "公司本部");
		
		//companyMap.put("", "公司本部");
		List<String> list = new ArrayList<String>();
		list.add("2718");
		list.add("2719");
		list.add("2720");
		list.add("2721");
		list.add("2722");
		list.add("2545");
		
		String sql1 ="";			//立项后合同签订
		String sql2="";				//项目批复概算超额
		String sql3="";				//合同先执行后签订
		String sql4="";				//合同支付超付
		
		List<Long> r2 = new ArrayList<Long>();
		List<Long> r4 = new ArrayList<Long>();
		for(int i=0;i<list.size();i++){
			if(i!=0){
				sql1 +=" union all ";
				//sql2 +=" union all ";
				sql3 +=" union all ";
			}
			sql1 += "select count(count(distinct p.id)) result from c_project p,c_contract c where p.project_adddept_id='"+list.get(i)+"' and p.removed=0"+ 
			" and p.id = c.project_id and c.removed=0 and c.project_id is not null"+
			" and p.approval_date is not null and c.contract_signed_date is not null"+
			" and to_date(p.approval_date,'yyyy-mm-dd') < (to_date(c.contract_signed_date,'yyyy-mm-dd')-120) " +
			" and p.id not in (" +
				"select distinct p.id from c_project p,c_contract c where p.removed=0"+ 
				" and p.id = c.project_id and c.removed=0 and c.project_id is not null"+
				" and p.approval_date is not null and c.contract_signed_date is not null"+
				" and to_date(p.approval_date,'yyyy-mm-dd') >= (to_date(c.contract_signed_date,'yyyy-mm-dd')-120)" +
			")"+
			"group by p.id";
			
			sql3 += "select count(id) result from c_contract c where c.contract_owner_execute_id='"+list.get(i)+"' and c.removed=0 and c.contract_start_date <  c.contract_signed_date";
			
			
			sql2 ="select c.project_id,sum(c.contract_price) from c_contract c where c.removed='0'  " +
			"and c.project_id in (select t.id from c_project t where t.project_adddept_id ='"+list.get(i)+"' and t.removed =0 and t.id not in (select t.id from c_project t where t.project_budget_all=0)) " +
			"group by c.project_id";
			
			List<Object[]> result2List = dwContractCoverDao.executeSqlWithResult(sql2);
			String sql2update ="";
			int result2 = 0 ;
			if (result2List!=null && result2List.size()>0) {
				String sql2_sub = "";
				
				for(Object[] array : result2List){
					sql2_sub = "select p.id from c_project p where p.id='"+array[0]+"' and to_number(p.project_budget_all)<"+array[1];
					List<Object[]> r2_sub = dwContractCoverDao.executeSqlWithResult(sql2_sub);
					if(r2_sub!=null && r2_sub.size()>0){
						result2 ++;
					}
				}
			}
			r2.add(Long.valueOf(result2));
			
			
			
			
			int result4 = 0 ;
			sql4 ="select t.contract_id,sum(t.money) from C_CONTRACT_STATUS t,C_CONTRACT C where t.type ='3' and t.removed='0' " +
			" and t.contract_id = c.id and c.removed='0' and c.contract_owner_execute_id='"+list.get(i)+"'  group by t.contract_id" ;
			List<Object[]> result4List = dwContractCoverDao.executeSqlWithResult(sql4);
			String sql4_sub = "";
			for(Object[] array : result4List){
				if(array[1]==null || "".equals(array[1]) || "0".equals(array[1])) continue;			//实际支付为空或为0，不计入
				String sql8_ = "select c.final_price,c.verify_price,c.nation_verify_price,c.contract_price,c.pay_type from c_contract c where c.removed='0' and c.id='"+array[0]+"'";
				List<Object[]> contract = dwContractCoverDao.executeSqlWithResult(sql8_);			//[投资监理，审价单位，国家审计，合同价,支付方式]
				if(contract!=null && contract.size()>0){
					if((contract.get(0)[0]==null || "".equals(contract.get(0)[0])) && (contract.get(0)[1]==null || "".equals(contract.get(0)[1])) 
							&& (contract.get(0)[2]==null || "".equals(contract.get(0)[2])) && (contract.get(0)[4]!=null && "单价核算".equals(contract.get(0)[4]))){
						continue;
					}
				}else{
					continue;
				}
				
				
				sql4_sub = "select sum(t.money) from C_CONTRACT_STATUS t where t.type ='3' and t.removed='0' and t.contract_id='"+array[0]+"' group by t.contract_id";
				String actualMoney = dwContractCoverDao.executeSQLWithOneData(sql4_sub).toString();//实际支付
				
				Double actual = null;
				if(actualMoney!=null && !"".equals(actualMoney) && !"0".equals(actualMoney)){
					actual = Double.valueOf(actualMoney);
				}
				if(contract.get(0)[0]!=null && !"".equals(contract.get(0)[0]) && actual > (0.0001+Double.valueOf(contract.get(0)[0].toString()))){		//finalPrice
					if(actual>Double.valueOf(contract.get(0)[0].toString())){
						result4++;
						continue;
					}
				}
				if(contract.get(0)[1]!=null && !"".equals(contract.get(0)[1]) && actual > (0.0001+Double.valueOf(contract.get(0)[1].toString()))){		//verifyPrice
					if(actual>Double.valueOf(contract.get(0)[1].toString())){
						result4++;
						continue;
					}
				}
				if(contract.get(0)[2]!=null && !"".equals(contract.get(0)[2]) && actual > (0.0001+Double.valueOf(contract.get(0)[2].toString()))){		//nationVerifyPrice
					if(actual>Double.valueOf(contract.get(0)[2].toString())){
						result4++;
						continue;
					}
				}
				sql4_sub = "select sum(t.money) from C_CONTRACT_STATUS t where t.type ='1' and t.removed='0' and t.contract_id='"+array[0]+"' group by t.contract_id";
				String changedMoney = dwContractCoverDao.executeSqlWithResult(sql4_sub).toString();//合同变更金额
				Double afterChanged = 0.0;
				if(contract.get(0)[3]!=null && !"".equals(contract.get(0)[3])){
					afterChanged = Double.valueOf(contract.get(0)[3].toString());
				}
				
				if(changedMoney!=null && !"0".equals(changedMoney) ){
					afterChanged = afterChanged + Double.valueOf(contract.get(0)[3].toString());
				}
				
				if(actual > (0.0001+afterChanged) && (afterChanged==0.0) && !"单价核算".equals(contract.get(0)[4])){			//实际支付大于变更后价钱，并且支付方式不等于单价核算
					result4++;
				}
			}
			r4.add(Long.valueOf(result4));
		} 
			
		List<Long> r1 = dwContractCoverDao.executeSQLByToFindCoverManagement(sql1);
		List<Long> r3 = dwContractCoverDao.executeSQLByToFindCoverManagement(sql3);
		
		List<CoverManagement> coverList = new ArrayList<CoverManagement>();
		dwContractCoverDao.executeSQLWithNoResult("delete cover_management");
		for(int i=0;i<list.size();i++){
			CoverManagement cover = new CoverManagement();
			cover.setCompanyId(list.get(i));
			cover.setCompanyName(companyMap.get(list.get(i)));
			cover.setContractOvertime(r1.get(i));
			cover.setContractExecuteFirst(r3.get(i));
			cover.setProjectBudgetOverpay(r2.get(i));
			cover.setContractOverpay(r4.get(i));
			coverList.add(cover);
			dwContractCoverDao.saveOrUpdate(cover);
		}
	}

	@Override
	public void calCoverContractBid() {
		Map<String, String> companyMap = new HashMap<String, String>();
		companyMap.put("2718", "车辆分公司");
		companyMap.put("2719", "供电分公司");
		companyMap.put("2720", "通号分公司");
		companyMap.put("2721", "工务分公司");
		companyMap.put("2722", "物资分公司");
		companyMap.put("2545", "公司本部");
		
		List<String> list = new ArrayList<String>();
		list.add("2718");
		list.add("2719");
		list.add("2720");
		list.add("2721");
		list.add("2722");
		list.add("2545");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		int startYear = 2008; // 默认从2008年号开始
		int endYear = Calendar.getInstance().get(Calendar.YEAR);
		
		List<CoverContractBid> saveList = new ArrayList<CoverContractBid>();
		for(int m=startYear;m<=endYear; m++){
			int minMonth = 0;
			int maxMonth = 11;
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, m);
			if(m==endYear){
				maxMonth = c.get(Calendar.MONTH);
			}
			for(int i=minMonth; i<=maxMonth; i++){
				c.set(Calendar.MONTH, i);
				String controlDate = sdf.format(c.getTime());
				
				for(int n=0;n<list.size();n++){
					CoverContractBid bid = dwContractCoverDao.findCoverContractBidByDateAndCompany(controlDate,list.get(n));
					if(bid==null) bid = new CoverContractBid();
						
					List<Object[]> result = dwContractCoverDao.findContractBidByCompanyAndDate(list.get(n), controlDate);
					List<Double> changedPrice = dwContractCoverDao.findChangedPriceByCompanyIdAndDate(list.get(n),controlDate);
					
					bid.setControlDate(controlDate);
					bid.setCompanyId(list.get(n));
					bid.setCompanyName(companyMap.get(list.get(n)));
					
					bid.setBid1Count(Long.valueOf(result.get(0)[0].toString()));
					bid.setBid1Money((Double)result.get(0)[1]+changedPrice.get(0));
					
					bid.setBid2Count(Long.valueOf(result.get(1)[0].toString()));
					bid.setBid2Money((Double)result.get(1)[1]+changedPrice.get(1));
					
					bid.setBid3Count(Long.valueOf(result.get(2)[0].toString()));
					bid.setBid3Money((Double)result.get(2)[1]+changedPrice.get(2));
					saveList.add(bid);
				}
			}
		}
		dwContractCoverDao.saveOrUpdateAllCoverContractBid(saveList);
	}

	@Override
	public CoverContractBidVo findCoverContractBidVo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		
		//测试，2013-08
		/*try {
			calendar.setTime(sdf.parse("2013-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		List<String> yearMonthList = new ArrayList<String>();
		for(int i=0;i<6;i++){
			
			if(i!=0){
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
			}else{
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-6);
			}
			yearMonthList.add(sdf.format(calendar.getTime()));
		}
		
		List<Object[]> result = dwContractCoverDao.findCoverContractBidCountByDateList(yearMonthList);
		CoverContractBidVo vo = new CoverContractBidVo();
		vo.setCategories(yearMonthList);
		if(result!=null){
			List<Long> bid1List =new ArrayList<Long>(),bid2List=new ArrayList<Long>(),bid3List = new ArrayList<Long>();
			
			for(int i=0;i<result.size();i++){
				Object[] a = result.get(i);
				
				bid1List.add(Long.valueOf(result.get(i)[0].toString()));
				bid2List.add(Long.valueOf(result.get(i)[1].toString()));
				bid3List.add(Long.valueOf(result.get(i)[2].toString()));
			}
			vo.setBid1List(bid1List);
			vo.setBid2List(bid2List);
			vo.setBid3List(bid3List);
		}
		
		return vo;
	}

	@Override
	public List<Object[]> findCoverContractBidByYear(String year) {
		Map<String, String> companyMap = new HashMap<String, String>();
		companyMap.put("2718", "车辆分公司");
		companyMap.put("2719", "供电分公司");
		companyMap.put("2720", "通号分公司");
		companyMap.put("2721", "工务分公司");
		companyMap.put("2722", "物资分公司");
		companyMap.put("2545", "公司本部");
		
		List<String> list = new ArrayList<String>();
		list.add("2718");
		list.add("2719");
		list.add("2720");
		list.add("2721");
		list.add("2722");
		list.add("2545");
		List<Object[]> result = new ArrayList<Object[]>();
		for(int i=0;i<list.size();i++){
			Object[] r1 = new Object[7];
			Object[] r1t= dwContractCoverDao.findCoverContractBidByYearAndCompany(year,list.get(i));
			for(int m=0;m<r1t.length;m++){
				r1[m] = r1t[m];
			}
			r1[r1t.length] = companyMap.get(list.get(i)); 
			
			result.add(r1);
		}
		return result;
	}
	
	
	public void showCoverAlarm(){
		
	}

	@Override
	public void calCoverAlarm() {
		CoverAlarm coverAlarm = dwContractCoverDao.findCoverAlarm();
		if(coverAlarm==null){
			coverAlarm = new CoverAlarm();
			coverAlarm.setCreateTime(new Date());
		}
		
		/**1立项后合同签订超时提示***************/
		String sql1 = "select count(count(distinct p.id)) from c_project p,c_contract c where p.removed=0"
				+ " and p.id = c.project_id  and c.removed=0"
				+ " and p.approval_date is not null and c.contract_signed_date is not null"
				+ " and to_date(p.approval_date,'yyyy-mm-dd') < (to_date(c.contract_signed_date,'yyyy-mm-dd')-120)"
				+ " and p.id not in ("
				+ "select distinct p.id from c_project p,c_contract c where p.removed=0"
				+ " and p.id = c.project_id and c.removed=0 and c.project_id is not null"
				+ " and p.approval_date is not null and c.contract_signed_date is not null"
				+ " and to_date(p.approval_date,'yyyy-mm-dd') >= (to_date(c.contract_signed_date,'yyyy-mm-dd')-120))"
				+ " group by p.id";
		Object r1 = dwContractCoverDao.executeSQLWithOneData(sql1);
		coverAlarm.setAttr1(Integer.valueOf(r1.toString()));
		
		/**2项目批复概算超额告警提示***************/
		String sql2 = "select c.project_id,sum(c.contract_price) from c_contract c where c.removed='0' "
				+ " and c.project_id in (select t.id from c_project t where t.removed =0 and t.project_budget_all!=0) "
				+ " group by c.project_id";
		List<Object[]> result2List = dwContractCoverDao.executeSqlWithResult(sql2);
		int result2 = 0 ;
		if(result2List!=null && result2List.size()>0){
			String sql2_sub = "";
			for(Object[] array : result2List){
				sql2_sub = "select * from c_project p where p.id='"+array[0]+"' and to_number(p.project_budget_all)<"+array[1];
				if(this.isResultExist(sql2_sub)){
					result2 ++;
				}
			}
		}
		coverAlarm.setAttr2(result2);
		
			/**3合同审批流程超时提示***************/
			//incidents,htxx,tasks 这3个表找不到
		String sql3 = "select count(*) from ("
				+ "select i.processname,i.incident from incidents i ,ht_xx h where i.processname=h.model_id and h.pinstance_id = i.incident and i.status=1 and h.removed=0"
				+ " and i.processname='合同审批流程' and sysdate-i.starttime > 30"
				+ " union"
				+ " select  i.processname,i.incident from tasks t,incidents i,ht_xx h where t.processname='合同审批流程' and t.Steplabel='备案' and t.status=1 "
				+ " and sysdate-t.starttime > 90 and t.processname=h.model_id and t.processname=i.processname and i.status=1 and i.processname=h.model_id"
				+ " and t.incident=i.incident and t.incident=h.pinstance_id  and i.incident=h.pinstance_id"
				+ " and h.removed=0 and t.assignedtouser like '%ST/%')";
		try {
			Object r3 = dwContractCoverDao.executeSQLWithOneDataByDatasource2(sql3);
			coverAlarm.setAttr3(Integer.valueOf(r3.toString()));
		} catch (Exception e) {
			coverAlarm.setAttr3(0);
			e.printStackTrace();
		}
		
			/**4合同先执行后签订风险提示***************/
		String sql4 ="select count(id) from c_contract c where c.removed=0 and c.contract_start_date <  c.contract_signed_date";
		Object r4 = dwContractCoverDao.executeSQLWithOneData(sql4);
		coverAlarm.setAttr4(Integer.valueOf(r4.toString()));
		
			/**5合同变更超额告警提示***************/
		String sql5 = "select t.contract_id,sum(t.money) from C_CONTRACT_STATUS t,C_CONTRACT c where t.type =1 and t.removed='0' " +
		" and t.contract_id = c.id and c.removed='0' group by t.contract_id";
		List<Object[]> resultList4 = dwContractCoverDao.executeSqlWithResult(sql5);
		int result5 = 0 ;
		if (resultList4!=null && resultList4.size()>0) {
			String sql5_sub = "";
			for(Object[] array : resultList4){
				sql5_sub = "select * from c_contract c where c.id='"+array[0]+"' and to_number(c.contract_price)*0.15<"+array[1];
				if(this.isResultExist(sql5_sub)){
					result5 ++;
				}
			}
		} 
		coverAlarm.setAttr5(result5);
		
			/**6合同支付超付告警提示***************/
		String sql6 = "select t.contract_id,sum(t.money) from C_CONTRACT_STATUS t,C_CONTRACT C where t.type ='3' and t.removed='0' " +
		" and t.contract_id = c.id and c.removed='0' group by t.contract_id" ;	//实际支付
		List<Object[]> resultList6 = dwContractCoverDao.executeSqlWithResult(sql6);		//[合同id,实际支付]
		int result6 = 0 ;
		if (resultList6!=null && resultList6.size()>0) {
			String sql6_sub = "";
			for(Object[] array : resultList6){
				if(array[1]==null || "".equals(array[1]) || "0".equals(array[1])) continue;			//实际支付为空或为0，不计入
				String sql6_ = "select c.final_price,c.verify_price,c.nation_verify_price,c.contract_price,c.pay_type from c_contract c where c.removed='0' and c.id='"+array[0]+"'";
				List<Object[]> contract = dwContractCoverDao.executeSqlWithResult(sql6_);			//[投资监理，审价单位，国家审计，合同价,支付方式]
				if(contract!=null && contract.size()>0){
					if((contract.get(0)[0]==null || "".equals(contract.get(0)[0])) && (contract.get(0)[1]==null || "".equals(contract.get(0)[1])) 
							&& (contract.get(0)[2]==null || "".equals(contract.get(0)[2])) && (contract.get(0)[4]!=null && "单价核算".equals(contract.get(0)[4]))){
						continue;
					}
				}else{
					continue;
				}
				
				
				sql6_sub = "select sum(t.money) from C_CONTRACT_STATUS t where t.type ='3' and t.removed='0' and t.contract_id='"+array[0]+"' group by t.contract_id";
				Object actualMoney = dwContractCoverDao.executeSQLWithOneData(sql6_sub);//实际支付
				Double actual = null;
				if(actualMoney!=null && !"".equals(actualMoney) && !"0".equals(actualMoney)){
					actual = Double.valueOf(actualMoney.toString());
				}
				if(contract.get(0)[0]!=null && !"".equals(contract.get(0)[0]) && actual > (0.0001+Double.valueOf(contract.get(0)[0].toString()))){		//finalPrice
					if(actual>Double.valueOf(contract.get(0)[0].toString())){
						result6++;
						continue;
					}
				}
				if(contract.get(0)[1]!=null && !"".equals(contract.get(0)[1]) && actual > (0.0001+Double.valueOf(contract.get(0)[1].toString()))){		//verifyPrice
					if(actual>Double.valueOf(contract.get(0)[1].toString())){
						result6++;
						continue;
					}
				}
				if(contract.get(0)[2]!=null && !"".equals(contract.get(0)[2]) && actual > (0.0001+Double.valueOf(contract.get(0)[2].toString()))){		//nationVerifyPrice
					if(actual>Double.valueOf(contract.get(0)[2].toString())){
						result6++;
						continue;
					}
				}
				sql6_sub = "select sum(t.money) from C_CONTRACT_STATUS t where t.type ='1' and t.removed='0' and t.contract_id='"+array[0]+"' group by t.contract_id";
				Object changedMoney = dwContractCoverDao.executeSQLWithOneData(sql6_sub);//合同变更金额
				Double afterChanged = 0.0;
				if(contract.get(0)[3]!=null && !"".equals(contract.get(0)[3])){
					afterChanged = Double.valueOf(contract.get(0)[3].toString());
				}
				
				if(changedMoney!=null && !"0".equals(changedMoney.toString()) ){
					afterChanged = afterChanged + Double.valueOf(changedMoney.toString());
				}
				if(actual > (0.0001+afterChanged) && !"单价核算".equals(contract.get(0)[4])){			//实际支付大于变更后价钱，并且支付方式不等于单价核算
					result6++;
				}
			}
		} 
		coverAlarm.setAttr6(result6);
		
			/**7合同进展信息风险提示***************/
		String sql7 = "select t.object_id from c_progress t,c_contract c where t.object_type ='1' and t.progress_type='1' and t.removed='0' "
				+ " and t.object_id = c.id and c.removed='0' group by t.object_id";
		List<Object[]> resultList7 = dwContractCoverDao.executeSqlWithResult(sql7);
		int result7 = 0;
		if (resultList7 != null && resultList7.size() > 0) {
			result7 = resultList7.size();
		}
		coverAlarm.setAttr7(result7);
		
			/**8合同销号条件异常提示***************/
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String sql8 = "select t.contract_id,sum(t.money) from c_contract_status t where t.removed='0' and t.type='2' and t.contract_id in"+ 
		"(select c.id from c_contract c where c.removed='0' and c.contract_destory_date<'"+nowDate+"') group by t.contract_id";
		List<Object[]> resultList8 = dwContractCoverDao.executeSqlWithResult(sql8);		//合同编号、实际支付
		int result8 = 0 ;
		if (resultList8!=null && resultList8.size()>0) {
			String sql8_sub = "";
			for(Object[] array : resultList8){
				if(array[1]==null || "".equals(array[1])) continue;
				sql8_sub = "select sum(t.money) from C_CONTRACT_STATUS t where t.type =2 and t.removed='0' and t.contract_id='"+array[0]+"' group by t.contract_id";
				Object planPay = dwContractCoverDao.executeSQLWithOneData(sql8_sub);
				if(planPay!=null && !"".equals(planPay.toString())){		//有变更信息
					if((Double)planPay > Double.valueOf(array[1].toString())){
						result8++;
					}
				}else{	//无变更信息,查询合同原价
					String sql8_sub2 = "select * from c_contract c where c.id='"+array[0]+"' and c.contract_price<"+array[1]+"";
					if(this.isResultExist(sql8_sub2)){
						result8++;
					}
				}
			}
		} 
		coverAlarm.setAttr8(result8);
			/**9合同质保期到期提示***************/
		coverAlarm.setAttr9(0);			//默认为0
		
			/**10采购竞价合同申报待办提示***************/
		coverAlarm.setAttr10(0);		//默认为0
		
			/**11待补全项目信息***************/
		String sql11 = "select count(*) from c_project p where p.removed=0 "
				+ " and (p.dispatch_no is null or  p.approval_date is null "
				+ " or p.project_moneysource is null or"
				+ " p.money_source_type  is null or  p.professional_type is null )";	
		Object r11 = dwContractCoverDao.executeSQLWithOneData(sql11);
		coverAlarm.setAttr11(Integer.valueOf(r11.toString()));
			/**12待补全合同信息***************/
		String sql12 = "select count(*) from c_contract c where c.removed=0 "
				+ " and (c.contract_name is null or c.contract_no is null or c.contract_price is null"
				+ " or c.contract_owner_name is null or c.contract_owner_execute_name is null or c.build_supplier_name  is null"
				+ " or c.pay_type is null or c.contract_passed_date is null or c.contract_start_date is null  or c.contract_signed_date is null"
				+ " or c.contract_end_date is null or c.contract_status is null or c.invite_bid_type is null or c.project_name is null)";	
		Object r12 = dwContractCoverDao.executeSQLWithOneData(sql12);
		coverAlarm.setAttr12(Integer.valueOf(r12.toString()));
		
		dwContractCoverDao.saveOrUpdate(coverAlarm);

	}

	
	
	
	@Override
	public CoverAlarm findCoverAlarm() {
		return dwContractCoverDao.findCoverAlarm();
	}

	public boolean isResultExist(String sql){
		List<Object[]> list = dwContractCoverDao.executeSqlWithResult(sql);
		if(list!=null && list.size()>0) return true;
		return false;
	}
	
}

/*
 * Location: X:\app\portal\WEB-INF\classes\ Qualified Name:
 * com.wonders.stpt.contract.service.impl.DwContractCoverServiceImpl JD-Core
 * Version: 0.6.0
 */