package com.wonders.stpt.contractPortal.model.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COVER_CONTRACT_RANGE")
public class CoverContractRange {
	private String id;
	private String year;
	private String month;
	private String type;
	private long countRange1;
	private long countRange2;
	private long countRange3;
	private long countRange4;
	private String updatetime;
	
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid") 
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name = "MONTH", nullable = true, length = 2)
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	@Column(name = "TYPE", nullable = true, length = 50)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "COUNT_RANGE1", nullable = true)
	public long getCountRange1() {
		return countRange1;
	}
	public void setCountRange1(long countRange1) {
		this.countRange1 = countRange1;
	}
	
	@Column(name = "COUNT_RANGE2", nullable = true)
	public long getCountRange2() {
		return countRange2;
	}
	public void setCountRange2(long countRange2) {
		this.countRange2 = countRange2;
	}
	
	@Column(name = "COUNT_RANGE3", nullable = true)
	public long getCountRange3() {
		return countRange3;
	}
	public void setCountRange3(long countRange3) {
		this.countRange3 = countRange3;
	}
	
	@Column(name = "COUNT_RANGE4", nullable = true)
	public long getCountRange4() {
		return countRange4;
	}
	public void setCountRange4(long countRange4) {
		this.countRange4 = countRange4;
	}
	
	@Column(name = "UPDATETIME", nullable = true, length = 20)
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	
}
