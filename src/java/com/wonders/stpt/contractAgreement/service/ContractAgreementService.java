package com.wonders.stpt.contractAgreement.service;

import com.wonders.stpt.contractAgreement.model.ContractAgreement;
import com.wonders.stpt.core.page.PageResultSet;

/**
 * Created by 01052621 on 2015/4/3.
 */
public interface ContractAgreementService {
    PageResultSet<ContractAgreement> getContractAgreement(ContractAgreement contractAgreement,int page,int pageSize) throws Exception;
}
