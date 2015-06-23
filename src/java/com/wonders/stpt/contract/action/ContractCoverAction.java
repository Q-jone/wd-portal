/*     */ package com.wonders.stpt.contract.action;
/*     */ 
/*     */ import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.contract.entity.bo.CoverAlarm;
import com.wonders.stpt.contract.entity.bo.CoverContractBid;
import com.wonders.stpt.contract.entity.bo.CoverContractPrice;
import com.wonders.stpt.contract.entity.bo.CoverManagement;
import com.wonders.stpt.contract.entity.bo.CoverProjectContractStat;
import com.wonders.stpt.contract.entity.bo.DwContractCover;
import com.wonders.stpt.contract.entity.vo.ContractCoverVo;
import com.wonders.stpt.contract.entity.vo.CoverContractBidVo;
import com.wonders.stpt.contract.entity.vo.CoverContractPriceVo;
import com.wonders.stpt.contract.entity.vo.CoverProjectContractStatVo;
import com.wonders.stpt.contract.service.ContractService;
import com.wonders.stpt.contract.service.DwContractCoverService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.ActionWriter;
/*     */ 
/*     */ @ParentPackage("struts-default")
/*     */ @Namespace("/htxx")
/*     */ @Controller("contractCoverAction")
/*     */ @Scope("prototype")
/*     */ public class ContractCoverAction extends AbstractParamAction
/*     */ {
/*     */   private DwContractCoverService dwContractCoverService;
/*     */   private ContractService contractService;
/*     */ 
/*     */   public void setContractService(@Qualifier("contractService") ContractService contractService)
/*     */   {
/*  34 */     this.contractService = contractService;
/*     */   }
/*     */ 
/*     */   public void setDwContractCoverService(@Qualifier("dwContractCoverService") DwContractCoverService dwContractCoverService)
/*     */   {
/*  39 */     this.dwContractCoverService = dwContractCoverService;
/*     */   }
/*     */   @Action("getContractCoverData")
/*     */   public String getContractCoverData() {
/*  44 */     String contractType = this.servletRequest.getParameter("contractType");
/*  45 */     String controlYear = this.servletRequest.getParameter("controlYear");
/*  46 */     Map companyContractPriceMap = new HashMap();
/*  47 */     if (contractType.equals("2"))
/*     */     {
/*  50 */       DwContractCover all = this.dwContractCoverService.findByContractType("2");
/*  51 */       String sql = "select sum(c.contract_price) from c_contract c where c.removed='0' and c.contract_type in ('2,1','2,2','2,3')";
/*  52 */       String contractPriceAll = this.dwContractCoverService.executeSqlReturnOneResult(sql);
/*  53 */       long otherPrice = Double.valueOf(contractPriceAll).longValue();
/*     */ 
/*  57 */       long companyAll1 = 0L;
/*  58 */       List company2List = this.dwContractCoverService.findByCompanyType("2");
/*  59 */       if ((company2List != null) && (company2List.size() > 0)) {
/*  60 */         for (int i = 0; i < company2List.size(); i++) {
/*  61 */           setCompanyContractPriceMap(((DwContractCover)company2List.get(i)).getCompanyId(), companyContractPriceMap);
/*  62 */           otherPrice -= Long.valueOf((String)companyContractPriceMap.get("c" + ((DwContractCover)company2List.get(i)).getCompanyId())).longValue();
/*  63 */           companyAll1 += Long.valueOf((String)companyContractPriceMap.get("c" + ((DwContractCover)company2List.get(i)).getCompanyId())).longValue();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  68 */       long companyAll2 = 0L;
/*  69 */       List company3List = this.dwContractCoverService.findByCompanyType("3");
/*  70 */       if ((company3List != null) && (company3List.size() > 0)) {
/*  71 */         for (int i = 0; i < company3List.size(); i++) {
/*  72 */           setCompanyContractPriceMap(((DwContractCover)company3List.get(i)).getCompanyId(), companyContractPriceMap);
/*  73 */           otherPrice -= Long.valueOf((String)companyContractPriceMap.get("c" + ((DwContractCover)company3List.get(i)).getCompanyId())).longValue();
/*  74 */           companyAll2 += Long.valueOf((String)companyContractPriceMap.get("c" + ((DwContractCover)company3List.get(i)).getCompanyId())).longValue();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  80 */       List contractList = this.contractService.findByContractTypeAndContractOwnerExecuteId(contractType + ",", "2540");
/*  81 */       setCompanyContractPriceMap("2540", companyContractPriceMap);
/*  82 */       companyAll1 += Long.valueOf((String)companyContractPriceMap.get("c2540")).longValue();
/*  83 */       companyContractPriceMap.put("companyAll1", companyAll1);
/*     */ 
/*  85 */       List eduList = this.contractService.findByContractTypeAndContractOwnerExecuteId(contractType + ",", "2546");
/*  86 */       setCompanyContractPriceMap("2546", companyContractPriceMap);
/*  87 */       companyAll1 += Long.valueOf((String)companyContractPriceMap.get("c2546")).longValue();
        otherPrice += Long.valueOf((String)companyContractPriceMap.get("c2546")).longValue();
/*  88 */       companyContractPriceMap.put("companyAll1", companyAll1);
/*     */ 
/*  93 */       List contractList2 = this.contractService.findByContractTypeAndContractOwnerExecuteId(contractType + ",", "2545");
/*  94 */       setCompanyContractPriceMap("2545", companyContractPriceMap);
/*  95 */       companyAll2 += Long.valueOf((String)companyContractPriceMap.get("c2545")).longValue();
/*  96 */       companyContractPriceMap.put("companyAll2", companyAll2);
/*     */ 
/* 100 */       DwContractCover dwContractCover = new DwContractCover();
/* 101 */       dwContractCover.setCompanyId("2540");
/* 102 */       dwContractCover.setCompanyName("运管中心");
/* 103 */       dwContractCover.setContractType(contractType);
/* 104 */       dwContractCover.setNumbers(new BigDecimal(contractList.size()));
/* 105 */       company2List.add(dwContractCover);
/*     */ 
///* 108 */       DwContractCover eduCover = new DwContractCover();
///* 109 */       eduCover.setCompanyId("2546");
///* 110 */       eduCover.setCompanyName("教培中心");
///* 111 */       eduCover.setContractType(contractType);
///* 112 */       eduCover.setNumbers(new BigDecimal(eduList.size()));
///* 113 */       company2List.add(eduCover);
/*     */ 
/* 122 */       DwContractCover other = new DwContractCover();
/* 123 */       int otherNumber = Integer.valueOf(all.getNumbers().toString()).intValue();
/* 124 */       if ((company2List != null) && (company2List.size() > 0)) {
/* 125 */         for (int i = 0; i < company2List.size(); i++) {
/* 126 */           otherNumber -= Integer.valueOf(((DwContractCover)company2List.get(i)).getNumbers().toString()).intValue();
/*     */         }
/*     */       }
/* 129 */       if ((company3List != null) && (company3List.size() > 0)) {
/* 130 */         for (int i = 0; i < company3List.size(); i++) {
/* 131 */           otherNumber -= Integer.valueOf(((DwContractCover)company3List.get(i)).getNumbers().toString()).intValue();
/*     */         }
/*     */       }
/* 134 */       other.setNumbers(new BigDecimal(otherNumber));
/* 135 */       other.setCompanyName("其他");
/* 136 */       companyContractPriceMap.put("other", otherPrice);
/*     */ 
/* 138 */       ContractCoverVo vo = new ContractCoverVo();
/* 139 */       vo.setAll(all);
/* 140 */       vo.setOther(other);
/* 141 */       vo.setCompany1List(company2List);
/* 142 */       vo.setCompany2List(company3List);
/* 143 */       vo.setMap(companyContractPriceMap);
/*     */ 
/* 145 */       ActionWriter actionWriter = new ActionWriter(this.servletResponse);
/* 146 */       actionWriter.writeJson(vo);
/*     */     }
/*     */ 
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */   public void setCompanyContractPriceMap(String companyId, Map<String, String> map)
/*     */   {
/* 154 */     String sql = "select sum(c.contract_price) from c_contract c where c.removed='0' and contract_owner_execute_id='" + companyId + "' and c.contract_type in ('2,1','2,2','2,3')";
/* 155 */     String result = this.dwContractCoverService.executeSqlReturnOneResult(sql);
/* 156 */     if ((result == null) || ("".equals(result))) result = "0";
/* 157 */     map.put("c" + companyId, Double.valueOf(result).longValue()+"");
/*     */   }

			@Action("findCoverContractPrice")
			public void findCoverContractPrice(){
				String type = servletRequest.getParameter("type");
				List<CoverContractPrice> list = dwContractCoverService.findCoverContractPrice(type);
				if(StringUtils.isNotBlank(type)){
					if(list!=null && list.size()>0){
						CoverContractPriceVo vo = new CoverContractPriceVo();
						List<Double> contractPriceList =new ArrayList<Double>();
						List<Long> contractCountList =new ArrayList<Long>();
						List<String> categroyList =new ArrayList<String>();
						
						if("month".equals(type)){
							for(int i=0;i<list.size(); i++){
								CoverContractPrice temp = list.get(i);
								contractPriceList.add(temp.getContractPrice());
								contractCountList.add(temp.getContractCount());
								categroyList.add(temp.getControlDate());
							}
						}else if("year".equals(type)){
							Double yearPrice =0.0;
							Long yearCount = 0l;
							
							
							String currentYear = "";
							for(int i=0; i<list.size(); i++){
								CoverContractPrice temp = list.get(i);
								String thisYear = temp.getControlDate().substring(0,4);
								if(StringUtils.isEmpty(currentYear)){
									currentYear = thisYear;
								}
								
								if(currentYear.equals(thisYear)){		//同一年
									yearPrice = yearPrice+temp.getContractPrice();
									yearCount = yearCount + temp.getContractCount();
									if(i==list.size()-1){
										contractPriceList.add(yearPrice);
										contractCountList.add(yearCount);
										categroyList.add(thisYear);
									}
								}else{	//不同年份
									contractPriceList.add(yearPrice);
									contractCountList.add(yearCount);
									categroyList.add((Integer.valueOf(thisYear)-1)+"");
									
									yearPrice = temp.getContractPrice();
									yearCount = temp.getContractCount();
									currentYear = thisYear;
								}
							}
						}
						vo.setContractPrice(contractPriceList);
						vo.setContractCount(contractCountList);
						vo.setControlYear(categroyList);
						ActionWriter actionWriter = new ActionWriter(this.servletResponse);
						actionWriter.writeJson(vo);
					}
				}
			}
			           
			@Action("findCoverProjectContractStat")
			public void findCoverProjectContractStat(){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				CoverProjectContractStatVo vo = new CoverProjectContractStatVo();
				int dateLength = 12;
				//List<String> dateList = new ArrayList<String>();
				CoverProjectContractStat stat = dwContractCoverService.findCoverProjectContractStat();
				vo.setCoverProjectContractStat(stat);
				if(stat!=null){
					List<Long> projectCount = dwContractCoverService.findProjectCountByDate(stat.getCalDay(),dateLength);
					List<Double> projectPrice = dwContractCoverService.findProjectPriceByDate(stat.getCalDay(),dateLength);
					List<Long> contractCount = dwContractCoverService.findContractCountByDate(stat.getCalDay(),dateLength);
					List<Double> contractPrice = dwContractCoverService.findContractPriceByDate(stat.getCalDay(),dateLength);
					List<Double> changedPrice = dwContractCoverService.findChangedPriceByDate(stat.getCalDay(), dateLength, "1");		//变更合同
					List<Double> payPrice = dwContractCoverService.findChangedPriceByDate(stat.getCalDay(), dateLength, "3");		//实际支付
					
					List<Double> afterChangePrice = new ArrayList<Double>();
					Calendar c =  Calendar.getInstance();
					for(int i=0;i<dateLength; i++){
						try {
							c.setTime(sdf.parse(stat.getCalDay()));
							c.add(Calendar.DAY_OF_MONTH, -i);
							//dateList.add(sdf.format(c.getTime()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						
					}
					
					if(contractPrice!=null && changedPrice!=null && contractPrice.size()==changedPrice.size()){
						for(int i=0; i<contractPrice.size(); i++){
							double current = 0.0;
							if(contractPrice.get(i)!=null){
								current += contractPrice.get(i);
							}
							if(changedPrice.get(i)!=null){
								current += changedPrice.get(i);
							}
							afterChangePrice.add(current);
						}
					}
					
					vo.setProjectCount(projectCount);
					vo.setProjectPrice(projectPrice);
					vo.setContractCount(contractCount);
					vo.setContractPrice(afterChangePrice);
					vo.setPayPrice(payPrice);
					//vo.setDateList(dateList);
					
					System.out.println(1);
				}
				ActionWriter actionWriter = new ActionWriter(this.servletResponse);
				actionWriter.writeJson(vo);
			}
			
			@Action("calCoverProjectContractStat")
			public void calCoverProjectContractStat() {
				dwContractCoverService.calCoverProjectContractStat();
			}

			@Action("calCoverContractPrice")
			public void calCoverContractPrice(){
				dwContractCoverService.calCoverContractPrice();
			}

			@Action("showCoverManagement")
			public void showCoverManagement(){
				List<CoverManagement> list = dwContractCoverService.findCoverManagement();
				ActionWriter actionWriter = new ActionWriter(this.servletResponse);
				actionWriter.writeJson(list);
			}

			@Action("calCoverManagement")
			public void calCoverManagement(){
				dwContractCoverService.calCoverManagement();
			}
			
			@Action("showCoverContractBid")
			public void showCoverContractBid(){
				CoverContractBidVo vo = dwContractCoverService.findCoverContractBidVo();
			
				ActionWriter actionWriter = new ActionWriter(this.servletResponse);
				actionWriter.writeJson(vo);
			}
			
			@Action("showCoverContractBidByYear")
			public void showCoverContractBidByYear(){
				String year = servletRequest.getParameter("year");
				List<Object[]> result = dwContractCoverService.findCoverContractBidByYear(year);
				ActionWriter actionWriter = new ActionWriter(this.servletResponse);
				actionWriter.writeJson(result);
			}
			
			@Action("calCoverContractBid")
			public void calCoverContractBid(){
				dwContractCoverService.calCoverContractBid();
			}
			
			@Action("showCoverAlarm")
			public void showCoverAlarm(){
				CoverAlarm coverAlarm = dwContractCoverService.findCoverAlarm();
				ActionWriter actionWriter = new ActionWriter(this.servletResponse);
				actionWriter.writeJson(coverAlarm);
			}

			@Action("calCoverAlarm")
			public void calCoverAlarm(){
				dwContractCoverService.calCoverAlarm();
			}
			
/*     */ }

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.action.ContractCoverAction
 * JD-Core Version:    0.6.0
 */