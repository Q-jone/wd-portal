package com.wonders.stpt.contract.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COVER_MANAGEMENT")
public class CoverManagement {
	private String id;
	private String companyName;
	private String companyId;
	private Long contractOvertime;
	private Long projectBudgetOverpay;
	private Long contractExecuteFirst;
	private Long contractOverpay;
	private Long numberAttr1;
	private Long numberAttr2;
	private Long numberAttr3;
	private Long numberAttr4;
	private Date createTime;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "COMPANY_NAME", nullable = true)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "COMPANY_ID", nullable = true)
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "CONTRACT_OVERTIME", nullable = true)
	public Long getContractOvertime() {
		return contractOvertime;
	}

	public void setContractOvertime(Long contractOvertime) {
		this.contractOvertime = contractOvertime;
	}

	@Column(name = "PROJECT_BUDGET_OVERPAY", nullable = true)
	public Long getProjectBudgetOverpay() {
		return projectBudgetOverpay;
	}

	public void setProjectBudgetOverpay(Long projectBudgetOverpay) {
		this.projectBudgetOverpay = projectBudgetOverpay;
	}

	@Column(name = "CONTRACT_EXECUTE_FIRST", nullable = true)
	public Long getContractExecuteFirst() {
		return contractExecuteFirst;
	}

	public void setContractExecuteFirst(Long contractExecuteFirst) {
		this.contractExecuteFirst = contractExecuteFirst;
	}

	@Column(name = "CONTRACT_OVERPAY", nullable = true)
	public Long getContractOverpay() {
		return contractOverpay;
	}

	public void setContractOverpay(Long contractOverpay) {
		this.contractOverpay = contractOverpay;
	}

	@Column(name = "NUMBER_ATTR1", nullable = true)
	public Long getNumberAttr1() {
		return numberAttr1;
	}

	public void setNumberAttr1(Long numberAttr1) {
		this.numberAttr1 = numberAttr1;
	}

	@Column(name = "NUMBER_ATTR2", nullable = true)
	public Long getNumberAttr2() {
		return numberAttr2;
	}

	public void setNumberAttr2(Long numberAttr2) {
		this.numberAttr2 = numberAttr2;
	}

	@Column(name = "NUMBER_ATTR3", nullable = true)
	public Long getNumberAttr3() {
		return numberAttr3;
	}

	public void setNumberAttr3(Long numberAttr3) {
		this.numberAttr3 = numberAttr3;
	}

	@Column(name = "NUMBER_ATTR4", nullable = true)
	public Long getNumberAttr4() {
		return numberAttr4;
	}

	public void setNumberAttr4(Long numberAttr4) {
		this.numberAttr4 = numberAttr4;
	}

	@Column(name = "CREATE_TIME", nullable = true)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

}
