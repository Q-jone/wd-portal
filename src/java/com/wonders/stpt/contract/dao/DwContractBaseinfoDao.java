package com.wonders.stpt.contract.dao;

import com.wonders.stpt.contract.entity.bo.DwContractBaseinfo;

public abstract interface DwContractBaseinfoDao
{
  public abstract DwContractBaseinfo findByType(String paramString);
}

/* Location:           X:\app\portal\WEB-INF\classes\
 * Qualified Name:     com.wonders.stpt.contract.dao.DwContractBaseinfoDao
 * JD-Core Version:    0.6.0
 */