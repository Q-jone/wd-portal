package com.wonders.stpt.contract.service;

import com.wonders.stpt.contract.entity.bo.DwContractAssignStats;
import java.util.List;

public abstract interface DwContractAssignStatsService
{
  public abstract List<DwContractAssignStats> findDwContractAssignStats(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.DwContractAssignStatsService
 * JD-Core Version:    0.6.0
 */