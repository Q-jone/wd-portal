package com.wonders.stpt.contract.dao;

import com.wonders.stpt.contract.entity.bo.DwContractManagement;
import java.util.List;

public abstract interface DwContractManagementDao
{
  public abstract List<DwContractManagement> findDwContractManagement();

  public abstract List<DwContractManagement> findDwContractManagement(String paramString);
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.DwContractManagementDao
 * JD-Core Version:    0.6.0
 */