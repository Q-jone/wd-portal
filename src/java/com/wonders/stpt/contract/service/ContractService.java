package com.wonders.stpt.contract.service;

import com.wonders.stpt.contract.entity.bo.Contract;
import java.util.List;

public abstract interface ContractService
{
  public abstract List<Contract> findByContractNoAndContractType(String paramString1, String paramString2);

  public abstract List<Contract> findByContractTypeAndContractOwnerExecuteId(String paramString1, String paramString2);
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.service.ContractService
 * JD-Core Version:    0.6.0
 */