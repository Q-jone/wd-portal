package com.wonders.stpt.contract.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COVER_ALARM")
public class CoverAlarm {
	private String id;
	private Date createTime;
	private Integer attr1;
	private Integer attr2;
	private Integer attr3;
	private Integer attr4;
	private Integer attr5;
	private Integer attr6;
	private Integer attr7;
	private Integer attr8;
	private Integer attr9;
	private Integer attr10;
	private Integer attr11;
	private Integer attr12;
	
	private String arrt1Url;
	private String arrt2Url;
	private String arrt3Url;
	private String arrt4Url;
	private String arrt5Url;
	private String arrt6Url;
	private String arrt7Url;
	private String arrt8Url;
	private String arrt9Url;
	private String arrt10Url;
	private String arrt11Url;
	private String arrt12Url;
	
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

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ATTR1")
	public Integer getAttr1() {
		return attr1;
	}

	public void setAttr1(Integer attr1) {
		this.attr1 = attr1;
	}

	@Column(name = "ATTR2")
	public Integer getAttr2() {
		return attr2;
	}

	public void setAttr2(Integer attr2) {
		this.attr2 = attr2;
	}

	@Column(name = "ATTR3")
	public Integer getAttr3() {
		return attr3;
	}

	public void setAttr3(Integer attr3) {
		this.attr3 = attr3;
	}

	@Column(name = "ATTR4")
	public Integer getAttr4() {
		return attr4;
	}

	public void setAttr4(Integer attr4) {
		this.attr4 = attr4;
	}

	@Column(name = "ATTR5")
	public Integer getAttr5() {
		return attr5;
	}

	public void setAttr5(Integer attr5) {
		this.attr5 = attr5;
	}

	@Column(name = "ATTR6")
	public Integer getAttr6() {
		return attr6;
	}

	public void setAttr6(Integer attr6) {
		this.attr6 = attr6;
	}

	@Column(name = "ATTR7")
	public Integer getAttr7() {
		return attr7;
	}

	public void setAttr7(Integer attr7) {
		this.attr7 = attr7;
	}

	@Column(name = "ATTR8")
	public Integer getAttr8() {
		return attr8;
	}

	public void setAttr8(Integer attr8) {
		this.attr8 = attr8;
	}

	@Column(name = "ATTR9")
	public Integer getAttr9() {
		return attr9;
	}

	public void setAttr9(Integer attr9) {
		this.attr9 = attr9;
	}

	@Column(name = "ATTR10")
	public Integer getAttr10() {
		return attr10;
	}

	public void setAttr10(Integer attr10) {
		this.attr10 = attr10;
	}

	@Column(name = "ATTR11")
	public Integer getAttr11() {
		return attr11;
	}

	public void setAttr11(Integer attr11) {
		this.attr11 = attr11;
	}

	@Column(name = "ATTR12")
	public Integer getAttr12() {
		return attr12;
	}

	public void setAttr12(Integer attr12) {
		this.attr12 = attr12;
	}

	@Column(name = "ATTR1_URL")
	public String getArrt1Url() {
		return arrt1Url;
	}

	public void setArrt1Url(String arrt1Url) {
		this.arrt1Url = arrt1Url;
	}

	@Column(name = "ATTR2_URL")
	public String getArrt2Url() {
		return arrt2Url;
	}

	public void setArrt2Url(String arrt2Url) {
		this.arrt2Url = arrt2Url;
	}

	@Column(name = "ATTR3_URL")
	public String getArrt3Url() {
		return arrt3Url;
	}

	public void setArrt3Url(String arrt3Url) {
		this.arrt3Url = arrt3Url;
	}

	@Column(name = "ATTR4_URL")
	public String getArrt4Url() {
		return arrt4Url;
	}

	public void setArrt4Url(String arrt4Url) {
		this.arrt4Url = arrt4Url;
	}

	@Column(name = "ATTR5_URL")
	public String getArrt5Url() {
		return arrt5Url;
	}

	public void setArrt5Url(String arrt5Url) {
		this.arrt5Url = arrt5Url;
	}

	@Column(name = "ATTR6_URL")
	public String getArrt6Url() {
		return arrt6Url;
	}

	public void setArrt6Url(String arrt6Url) {
		this.arrt6Url = arrt6Url;
	}

	@Column(name = "ATTR7_URL")
	public String getArrt7Url() {
		return arrt7Url;
	}

	public void setArrt7Url(String arrt7Url) {
		this.arrt7Url = arrt7Url;
	}

	@Column(name = "ATTR8_URL")
	public String getArrt8Url() {
		return arrt8Url;
	}

	public void setArrt8Url(String arrt8Url) {
		this.arrt8Url = arrt8Url;
	}

	@Column(name = "ATTR9_URL")
	public String getArrt9Url() {
		return arrt9Url;
	}

	public void setArrt9Url(String arrt9Url) {
		this.arrt9Url = arrt9Url;
	}

	@Column(name = "ATTR10_URL")
	public String getArrt10Url() {
		return arrt10Url;
	}

	public void setArrt10Url(String arrt10Url) {
		this.arrt10Url = arrt10Url;
	}

	@Column(name = "ATTR11_URL")
	public String getArrt11Url() {
		return arrt11Url;
	}

	public void setArrt11Url(String arrt11Url) {
		this.arrt11Url = arrt11Url;
	}

	@Column(name = "ATTR12_URL")
	public String getArrt12Url() {
		return arrt12Url;
	}

	public void setArrt12Url(String arrt12Url) {
		this.arrt12Url = arrt12Url;
	}

	

	

	

}
