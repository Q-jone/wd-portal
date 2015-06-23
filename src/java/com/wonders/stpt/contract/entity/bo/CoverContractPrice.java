package com.wonders.stpt.contract.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COVER_CONTRACT_PRICE")
public class CoverContractPrice {
	private String id;
	private String controlDate;
	private Double contractPrice;
	private Long contractCount;
	private Date createDate;

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

	@Column(name = "CONTROL_DATE")
	public String getControlDate() {
		return controlDate;
	}

	public void setControlDate(String controlDate) {
		this.controlDate = controlDate;
	}

	@Column(name = "CONTRACT_PRICE", nullable = false)
	public Double getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_COUNT", nullable = false)
	public Long getContractCount() {
		return contractCount;
	}

	public void setContractCount(Long contractCount) {
		this.contractCount = contractCount;
	}

	@Column(name = "CREATE_DATE", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
