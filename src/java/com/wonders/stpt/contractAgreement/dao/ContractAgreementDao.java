package com.wonders.stpt.contractAgreement.dao;

import com.wonders.stpt.contractAgreement.model.ContractAgreement;
import com.wonders.stpt.core.page.PageResultSet;

/**
 * Created by 01052621 on 2015/4/3.
 */
public interface ContractAgreementDao {
    PageResultSet<ContractAgreement> find(ContractAgreement contractAgreement,int page,int pageSize) throws Exception;

}
