package com.wonders.stpt.contractAgreement.dao.impl;

import com.wonders.stpt.contractAgreement.dao.ContractAgreementDao;
import com.wonders.stpt.contractAgreement.model.ContractAgreement;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 01052621 on 2015/4/3.
 */
@Repository("contractAgreementDao")
public class ContractAgreementDaoImpl implements ContractAgreementDao {
    private HibernateTemplate hibernateTemplate;
    public HibernateTemplate getHibernateTemplate(){
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate2")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public PageResultSet<ContractAgreement> find(ContractAgreement contractAgreement, int page, int pageSize) throws Exception {
        if(page<1){
            page=1;
        }
        if(pageSize<1){
            pageSize=10;
        }
        StringBuffer sb = new StringBuffer();
        HashMap parameter = new HashMap();
        sb.append("select c2.taskid taskId,cast(f.flag as varchar2(1)) flag,f.change_price changePrice,f.self_no selfNo,f.opposite_company oppositeCompany,f.original_contract_name originalContractName," +
                "f.contract_name contractName,f.contract_no contractNo," +
                "f.reg_time regTime,f.reg_person regPerson " +
                ",f.incidect incidect,f.process process " +
                " from ( select * from t_Contract_change_Agreement c  where 1=1 ");
        if(contractAgreement!=null){
            if(StringUtils.isNotBlank(contractAgreement.getContractName())){
                sb.append(" and c.contract_Name like :contractName");
                parameter.put("contractName","%"+contractAgreement.getContractName()+"%");
            }
            if(StringUtils.isNotBlank(contractAgreement.getContractNo())){
                sb.append(" and c.contract_No like :contractNo");
                parameter.put("contractNo","%"+contractAgreement.getContractNo()+"%");
            }
            if(StringUtils.isNotBlank(contractAgreement.getOriginalContractName())){
                sb.append(" and c.original_Contract_Name like :originalContractName");
                parameter.put("originalContractName","%"+contractAgreement.getOriginalContractName()+"%");
            }
            if(StringUtils.isNotBlank(contractAgreement.getRegPerson())){
                sb.append(" and c.reg_Person like :regPerson");
                parameter.put("regPerson","%"+contractAgreement.getRegPerson()+"%");
            }
            if(StringUtils.isNotBlank(contractAgreement.getFlag())){
                sb.append(" and c.flag like :flag");
                parameter.put("flag","%"+contractAgreement.getFlag()+"%");
            }
            if(StringUtils.isNotBlank(contractAgreement.getRegTime())){
                sb.append(" and c.reg_Time >= :regTime");
                parameter.put("regTime",contractAgreement.getRegTime());
            }

            if(StringUtils.isNotBlank(contractAgreement.getRegTimeEnd())){
                sb.append(" and c.reg_Time <= :regTimeEnd");
                parameter.put("regTimeEnd",contractAgreement.getRegTimeEnd());
            }
            if(contractAgreement.getChangePrice()!=null&&!"".equals(contractAgreement.getChangePrice())){
                sb.append(" and c.change_price >= :changePrice");
                parameter.put("changePrice",contractAgreement.getChangePrice());
            }
            /*if(StringUtils.isNotBlank(contractAgreement.getChangePrice().toString())){

            }*/


            if(StringUtils.isNotBlank(contractAgreement.getChangePriceEnd())){
                sb.append(" and c.change_price <= :changePriceEnd");
                parameter.put("changePriceEnd",contractAgreement.getChangePriceEnd());
            }
        }
        int totalRow=0;

        PageInfo pageInfo;
        sb.append(")  f ,tasks c2 where c2.incident = f.incidect and c2.processname = f.process and c2.steplabel = 'Begin'  order by f.reg_Time desc ");
        System.out.println(sb.toString());
        try {
            Query query=this.getSession().createSQLQuery("select count(1) from (" + sb.toString() + ")");
            query.setProperties(parameter);
            totalRow =Integer.valueOf(query.uniqueResult().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        pageInfo = new PageInfo(totalRow, pageSize, page);

        List list = getSession().createSQLQuery(sb.toString()).
                addScalar("taskId").
//                addScalar("changeContractNo").addScalar("changeSerialNo").addScalar("changePutForword").addScalar("contractName").
                addScalar("originalContractName").
                addScalar("changePrice").
                addScalar("regTime").addScalar("regPerson").
                addScalar("flag").
                addScalar("incidect").
                addScalar("process").
                addScalar("contractName").
                addScalar("contractNo").
                addScalar("oppositeCompany").
                addScalar("selfNo").
                setProperties(parameter).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageInfo.getPageSize()).setResultTransformer(Transformers.aliasToBean(ContractAgreement.class)).list();

        pageInfo.setTotalRow(totalRow);
        PageResultSet<ContractAgreement> pageResultSet = new PageResultSet<ContractAgreement>();
        pageResultSet.setList(list);
        pageResultSet.setPageInfo(pageInfo);
        return pageResultSet;
    }
}
