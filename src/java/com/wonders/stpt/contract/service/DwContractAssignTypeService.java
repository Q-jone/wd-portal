package com.wonders.stpt.contract.service;

import com.wonders.stpt.contract.entity.bo.DwContractAssignType;
import java.util.List;

public abstract interface DwContractAssignTypeService
{
  public abstract List<DwContractAssignType> findDwContractAssignType(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.DwContractAssignTypeService
 * JD-Core Version:    0.6.0
 */