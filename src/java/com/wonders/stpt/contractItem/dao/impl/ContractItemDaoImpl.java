package com.wonders.stpt.contractItem.dao.impl;

import javax.annotation.Resource;

import com.wonders.stpt.core.page.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.contractItem.dao.ContractItemDao;
import com.wonders.stpt.contractItem.model.ContractItem;
import com.wonders.stpt.core.page.PageResultSet;

import java.util.HashMap;
import java.util.List;

@Repository("contractItemDao")
public class ContractItemDaoImpl implements ContractItemDao{
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
	public PageResultSet<ContractItem> find(ContractItem contractItem,
			int page, int pageSize) throws Exception {
		if(page<1){
			page=1;
		}
		if(pageSize<1){
			pageSize=10;
		}

		StringBuffer sb = new StringBuffer();
		HashMap parameter = new HashMap();
		sb.append("select c2.taskid taskId,f.change_Contract_No changeContractNo,f.change_serial_no changeSerialNo,f.change_put_forword  changePutForword" +
				",f.change_Price changePrice,f.contract_Name contractName" +
				",f.change_Item_Name changeItemName" +
				",f.reg_Time regTime,f.reg_Person regPerson,cast(f.flag as varchar2(1)) flag" +
				",f.incidect incidect,f.process process " +
				" from ( select * from t_Contract_change_Item c  where 1=1 ");// and removed='0'
		if(contractItem!=null){
			if(StringUtils.isNotBlank(contractItem.getContractName())){
				sb.append(" and c.contract_Name like :contractName");
				parameter.put("contractName","%"+contractItem.getContractName()+"%");
			}
			if(StringUtils.isNotBlank(contractItem.getChangeItemName())){
				sb.append(" and c.change_Item_Name like :changeItemName");
				parameter.put("changeItemName","%"+contractItem.getChangeItemName()+"%");
			}
			if(StringUtils.isNotBlank(contractItem.getChangeSerialNo())){
				sb.append(" and c.change_Serial_No like :changeSerialNo");
				parameter.put("changeSerialNo","%"+contractItem.getChangeSerialNo()+"%");
			}
			if(StringUtils.isNotBlank(contractItem.getRegPerson())){
				sb.append(" and c.reg_Person like :regPerson");
				parameter.put("regPerson","%"+contractItem.getRegPerson()+"%");
			}
			if(StringUtils.isNotBlank(contractItem.getFlag())){
				sb.append(" and c.flag like :flag");
				parameter.put("flag","%"+contractItem.getFlag()+"%");
			}
			//hql 比较时间
			if(StringUtils.isNotBlank(contractItem.getRegTime())){
				sb.append(" and c.reg_Time >= :regTime");
				parameter.put("regTime",contractItem.getRegTime());
			}
			if(StringUtils.isNotBlank(contractItem.getRegTimeEnd())){
				sb.append(" and c.reg_Time <= :regTimeEnd");
				parameter.put("regTimeEnd",contractItem.getRegTimeEnd());
			}
		}

		int totalRow=0;

		PageInfo pageInfo;
		sb.append(")  f ,tasks c2 where c2.incident = f.incidect and c2.processname = f.process and c2.steplabel = 'Begin'  order by f.reg_Time desc ");
		try {
			Query query=this.getSession().createSQLQuery("select count(1) from (" + sb.toString()+")");
			query.setProperties(parameter);
			totalRow =Integer.valueOf(query.uniqueResult().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		pageInfo = new PageInfo(totalRow, pageSize, page);
		//changePrice
		List list = getSession().createSQLQuery(sb.toString()).addScalar("taskId").
				addScalar("changeContractNo").addScalar("changeSerialNo").addScalar("changePutForword").addScalar("contractName").
				addScalar("incidect").
				addScalar("process").
				addScalar("changeItemName").
				addScalar("changePrice").
				addScalar("regTime").addScalar("regPerson").
						addScalar("flag").
				setProperties(parameter).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageInfo.getPageSize()).setResultTransformer(Transformers.aliasToBean(ContractItem.class)).list();


		pageInfo.setTotalRow(totalRow);
		PageResultSet<ContractItem> pageResultSet = new PageResultSet<ContractItem>();
		pageResultSet.setList(list);
		pageResultSet.setPageInfo(pageInfo);
		return pageResultSet;
	}
}
