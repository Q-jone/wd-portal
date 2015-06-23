package com.wonders.stpt.contract.dao;

import com.wonders.stpt.contract.entity.bo.CoverAlarm;
import com.wonders.stpt.contract.entity.bo.CoverContractBid;
import com.wonders.stpt.contract.entity.bo.CoverContractPrice;
import com.wonders.stpt.contract.entity.bo.CoverManagement;
import com.wonders.stpt.contract.entity.bo.CoverProjectContractStat;
import com.wonders.stpt.contract.entity.bo.DwContractCover;
import java.util.List;

public abstract interface DwContractCoverDao
{
  public abstract List<String> findAllContractByType(String paramString);

  public abstract List<DwContractCover> findByContractType(String paramString);

  public abstract List<DwContractCover> findByCompanyType(String paramString);

  public abstract List<DwContractCover> findByContractTypeWithFuzzySearch(String paramString);

  public abstract List<Object> executeSql(String paramString);
  
public List<CoverContractPrice> findCoverContractPrice(String type);
  
  public CoverProjectContractStat findCoverProjectContractStat();
  
  public List<Long> findProjectCountByDate(String date,int dayLength);
  
  public List<Double> findProjectPriceByDate(String date,int dayLength);
  
  public List<Long> findContractCountByDate(String date,int dayLength);
  
  public List<Double> findContractPriceByDate(String date,int dayLength);
  
  public List<Double> findChangedPriceByDate(String date,int dayLength,String type);
  
  public Object[] findContractPriceAndCountByYearMonth(String yearMonth);
  
  public CoverContractPrice findCoverContractPriceByControlDate(String controlDate);
  
  public void saveOrUpdateAllCoverContractPrice(List<CoverContractPrice> coverContractPriceList);
  
  public Object[] findProjectCountAndPriceByDate(String date);
  
  public Object[] findContractCountAndPriceByDate(String date);
  
  public List<Double> findContractStatusMoneyByTypeAndDate(String yearMonth,String year,String type);
  
  public void saveOrUpdate(Object object);
  
  public List<CoverManagement> findCoverManagement();
  
  public List<Long> executeSQLByToFindCoverManagement(String sql);
  
  public List<Object[]> executeSqlWithResult(String sql);

  public void executeSQLWithNoResult(String sql);
  
  public Object executeSQLWithOneData(String sql);
  
  public Object executeSQLWithOneDataByDatasource2(String sql);
  
  public List<Object[]> findContractBidByCompanyAndDate(String companyId,String yearMonth);
  
  public List<Double> findChangedPriceByCompanyIdAndDate(String companyId,String yearDate);
  
  public CoverContractBid findCoverContractBidByDateAndCompany(String controlDate,String companyId);
  
  public void saveOrUpdateAllCoverContractBid(List<CoverContractBid> list);
  
  public List<Object[]> findCoverContractBidCountByDateList(List<String> yearMonthList);
  
  public Object[] findCoverContractBidByYearAndCompany(String year,String companyId);
  
  public CoverAlarm findCoverAlarm();
  
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.DwContractCoverDao
 * JD-Core Version:    0.6.0
 */