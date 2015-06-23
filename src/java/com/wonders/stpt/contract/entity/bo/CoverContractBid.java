package com.wonders.stpt.contract.entity.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COVER_CONTRACT_BID")
public class CoverContractBid {
	private String id;
	private String companyName;
	private String companyId;
	private String controlDate;
	
	
	private Long bid1Count;
	private Double bid1Money;
	private Long bid2Count;
	private Double bid2Money;
	private Long bid3Count;
	private Double bid3Money;

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

	@Column(name = "CONTROL_DATE", nullable = true)
	public String getControlDate() {
		return controlDate;
	}

	public void setControlDate(String controlDate) {
		this.controlDate = controlDate;
	}

	@Column(name = "BID1_COUNT", nullable = true)
	public Long getBid1Count() {
		return bid1Count;
	}

	public void setBid1Count(Long bid1Count) {
		this.bid1Count = bid1Count;
	}

	@Column(name = "BID1_MONEY", nullable = true)
	public Double getBid1Money() {
		return bid1Money;
	}

	public void setBid1Money(Double bid1Money) {
		this.bid1Money = bid1Money;
	}

	@Column(name = "BID2_COUNT", nullable = true)
	public Long getBid2Count() {
		return bid2Count;
	}

	public void setBid2Count(Long bid2Count) {
		this.bid2Count = bid2Count;
	}

	@Column(name = "BID2_MONEY", nullable = true)
	public Double getBid2Money() {
		return bid2Money;
	}

	public void setBid2Money(Double bid2Money) {
		this.bid2Money = bid2Money;
	}

	@Column(name = "BID3_COUNT", nullable = true)
	public Long getBid3Count() {
		return bid3Count;
	}

	public void setBid3Count(Long bid3Count) {
		this.bid3Count = bid3Count;
	}

	@Column(name = "BID3_MONEY", nullable = true)
	public Double getBid3Money() {
		return bid3Money;
	}

	public void setBid3Money(Double bid3Money) {
		this.bid3Money = bid3Money;
	}

	

	

}
