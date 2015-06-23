package com.wonders.stpt.contractChangeProcotol.service.impl;

import com.wonders.stpt.contractChangeProcotol.dao.ContractProcotolDao;
import com.wonders.stpt.contractChangeProcotol.service.ContractProcotolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
@Repository("contractProcotolService")
@Transactional(value="txManager2",propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
public class ContractProcotolServiceImpl implements ContractProcotolService {

    private ContractProcotolDao contractProcotolDao ;

    public ContractProcotolDao  getContractProcotolDao() {
        return contractProcotolDao;
    }
    @Autowired(required=false)
    public void setContractProcotolDao (@Qualifier("contractProcotolDao")ContractProcotolDao contractProcotolDao) {
        this.contractProcotolDao = contractProcotolDao;
    }

    public List<Object[]> findContractProcotolByPage(int startRow,int pageSize,String contract_name,
                                                     String  self_no,String  change_price_start ,String change_price_end,
                                                     String  reg_person, String reg_time_start,String reg_time_end,
                                                     String flag, String date_start, String date_end
            , String date_start1, String date_end1){
        return contractProcotolDao.findContractProcotolByPage(startRow, pageSize,contract_name, self_no, change_price_start ,change_price_end,
                reg_person, reg_time_start,reg_time_end, flag, date_start, date_end, date_start1, date_end1);

    }
    public int countContractProcotolOa(String contract_name,String self_no,
                                       String   change_price_start ,String change_price_end,
                                       String  reg_person,String  reg_time_start,String reg_time_end,String  flag,
                                       String  date_start, String date_end, String date_start1, String date_end1){
        return contractProcotolDao.countContractProcotol(contract_name, self_no,
                change_price_start ,change_price_end, reg_person, reg_time_start,reg_time_end, flag, date_start, date_end, date_start1, date_end1);

    }

}
