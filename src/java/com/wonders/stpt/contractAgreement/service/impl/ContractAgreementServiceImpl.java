package com.wonders.stpt.contractAgreement.service.impl;

import com.wonders.stpt.contractAgreement.dao.ContractAgreementDao;
import com.wonders.stpt.contractAgreement.model.ContractAgreement;
import com.wonders.stpt.contractAgreement.service.ContractAgreementService;
import com.wonders.stpt.core.page.PageResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 01052621 on 2015/4/3.
 */
@Repository("contractAgreementService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ContractAgreementServiceImpl implements ContractAgreementService {
    @Autowired
    private ContractAgreementDao contractAgreementDao;

    @Override
    public PageResultSet<ContractAgreement> getContractAgreement(ContractAgreement contractAgreement, int page, int pageSize) throws Exception {
        return contractAgreementDao.find(contractAgreement, page, pageSize);
    }
}
