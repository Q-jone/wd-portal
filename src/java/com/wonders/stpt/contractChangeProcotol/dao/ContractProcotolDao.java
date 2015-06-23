package com.wonders.stpt.contractChangeProcotol.dao;

import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
public interface ContractProcotolDao {
    public List<Object[]> findContractProcotolByPage(int startRow,int pageSize,String contract_name,String  self_no,
                                                     String  change_price_start ,String change_price_end,
                                                     String  reg_person, String reg_time_start,String reg_time_end,
                                                     String flag, String date_start, String date_end,
                                                     String date_start1, String date_end1);
    public int countContractProcotol(String contract_name,String self_no,
                                     String change_price_start ,String change_price_end,
                                     String  reg_person,String  reg_time_start,String reg_time_end,
                                     String  flag,String  date_start, String date_end,
                                     String date_start1, String date_end1);
}
