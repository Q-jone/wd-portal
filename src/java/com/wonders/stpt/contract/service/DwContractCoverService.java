package com.wonders.stpt.contract.service;

import java.util.List;

import com.wonders.stpt.contract.entity.bo.CoverAlarm;
import com.wonders.stpt.contract.entity.bo.CoverContractPrice;
import com.wonders.stpt.contract.entity.bo.CoverManagement;
import com.wonders.stpt.contract.entity.bo.CoverProjectContractStat;
import com.wonders.stpt.contract.entity.bo.DwContractCover;
import com.wonders.stpt.contract.entity.vo.CoverContractBidVo;

public abstract interface DwContractCoverService
{
  public abstract DwContractCover findByContractType(String paramString);

  public abstract List<DwContractCover> findByCompanyType(String paramString);

  public abstract List<DwContractCover> findByContractTypeWithFuzzySearch(String paramString);

  public abstract String executeSqlReturnOneResult(String paramString);
  
  public List<CoverContractPrice> findCoverContractPrice(String type);
  
  public CoverProjectContractStat findCoverProjectContractStat();
  
  public List<Long> findProjectCountByDate(String date,int dayLength);
  
  public List<Double> findProjectPriceByDate(String date,int dayLength);
  
  public List<Long> findContractCountByDate(String date,int dayLength);
  
  public List<Double> findContractPriceByDate(String date,int dayLength);
  
  public List<Double> findChangedPriceByDate(String date,int dayLength,String type);
  
  /**
   * 计算CoverContractPrice表
   */
  public void calCoverContractPrice();
  
  /**
   * 计算calCoverProjectContractStat表
   */
  public void calCoverProjectContractStat();
  
  public List<CoverManagement> findCoverManagement();
 
  public void calCoverManagement();
  
  public void calCoverContractBid();
  
  public CoverContractBidVo findCoverContractBidVo();
  
  public List<Object[]> findCoverContractBidByYear(String year);
 
  public void showCoverAlarm();
  
  public void calCoverAlarm();
  
  public CoverAlarm findCoverAlarm();
}
