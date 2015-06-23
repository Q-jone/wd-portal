package com.wonders.stpt.contract.dao;

import com.wonders.stpt.contract.entity.bo.DwContractPriceNumber;
import java.util.List;

public abstract interface DwContractPriceNumberDao
{
  public abstract List<DwContractPriceNumber> findDwContractPriceNumber(String paramString1, int paramInt, String paramString2, String paramString3);

  public abstract List<DwContractPriceNumber> findDwContractPriceNumberThisMonth(String paramString1, String paramString2, String paramString3);

  public abstract List<DwContractPriceNumber> findDwContractPriceNumberSum(String paramString1, String paramString2, String paramString3, String paramString4);
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.DwContractPriceNumberDao
 * JD-Core Version:    0.6.0
 */