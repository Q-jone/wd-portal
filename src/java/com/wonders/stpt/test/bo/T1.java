/**
 * 
 */
package com.wonders.stpt.test.bo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/** 
 * @ClassName: MetroExpress 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午06:56:03 
 *  
 */
@Entity
@Table(name = "T_METRO_EXPRESS")
public class T1 implements Serializable, BusinessObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416303768642574952L;
	private String id;
	private String accidentDetail;
	private String accidentDate;
	private String accidentTime;
	private String accidentTitle;
	private String accidentType;
	private String accidentSource;
	private String accidentLine;
	private String accidentEmergency;
	private String accidentRemark;
	private String accidentLocation;
	private String accidentActive;
	private String operatePerson;
	private String operateTime;
	private String removed;
	private String ext1;
	private String ext2;
	private String ext3;
	
	public T1(){
		//this.accidentActive = "0";
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	}
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "ACCIDENT_DETAIL",columnDefinition = "CLOB")
    
	/*@Column(name = "ACCIDENT_DETAIL", nullable = true, length = 500)*/
	public String getAccidentDetail() {
		return accidentDetail;
	}
	public void setAccidentDetail(String accidentDetail) {
		this.accidentDetail = accidentDetail;
	}
	
	
	@Column(name = "ACCIDENT_DATE", nullable = true, length = 50)
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	
	@Column(name = "ACCIDENT_TIME", nullable = true, length = 50)
	public String getAccidentTime() {
		return accidentTime;
	}
	public void setAccidentTime(String accidentTime) {
		this.accidentTime = accidentTime;
	}
	
	@Column(name = "ACCIDENT_LINE", nullable = true, length = 50)
	public String getAccidentLine() {
		return accidentLine;
	}
	public void setAccidentLine(String accidentLine) {
		this.accidentLine = accidentLine;
	}
	
	@Column(name = "ACCIDENT_TITLE", nullable = true, length = 50)
	public String getAccidentTitle() {
		return accidentTitle;
	}

	public void setAccidentTitle(String accidentTitle) {
		this.accidentTitle = accidentTitle;
	}

	@Column(name = "ACCIDENT_TYPE", nullable = true, length = 50)
	public String getAccidentType() {
		return accidentType;
	}

	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}
	
	@Column(name = "ACCIDENT_SOURCE", nullable = true, length = 50)
	public String getAccidentSource() {
		return accidentSource;
	}

	public void setAccidentSource(String accidentSource) {
		this.accidentSource = accidentSource;
	}

	@Column(name = "ACCIDENT_EMERGENCY", nullable = true, length = 50)
	public String getAccidentEmergency() {
		return accidentEmergency;
	}
	public void setAccidentEmergency(String accidentEmergency) {
		this.accidentEmergency = accidentEmergency;
	}
	
	@Column(name = "ACCIDENT_ACTIVE", nullable = true, length = 1)
	public String getAccidentActive() {
		return accidentActive;
	}
	public void setAccidentActive(String accidentActive) {
		this.accidentActive = accidentActive;
	}
	
	@Column(name = "ACCIDENT_LOCATION", nullable = true, length = 100)
	public String getAccidentLocation() {
		return accidentLocation;
	}
	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}
	
/*
	@Lob 
	@Basic(fetch = FetchType.LAZY)  clob blob 一般使用懒加载
	@Column(name = "CONTENT", columnDefinition = "BLOB",nullable=true) 
	public byte[] getContent() { 
	return this.content; 
	} 
	*/
	@Lob
    @Basic(fetch=FetchType.EAGER)
    @Column(name = "ACCIDENT_REMARK",columnDefinition = "CLOB")
    // @Column(name="ISPUBLIC" ,nullable=false,columnDefinition="INT default 0")  默认值
    //@Column(columnDefinition="varchar2(2) default '11'")
	/*@Column(name = "ACCIDENT_REMARK", nullable = true, length = 4000)*/
	public String getAccidentRemark() {
		return accidentRemark;
	}
	public void setAccidentRemark(String accidentRemark) {
		this.accidentRemark = accidentRemark;
	}
	
	@Column(name = "OPERATE_PERSON", nullable = true, length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}
	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}
	
	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	
	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}

	/**
	 * @return the ext1
	 */
	@Column(name = "EXT1", nullable = true, length = 100)
	public String getExt1() {
		return ext1;
	}

	/**
	 * @param ext1 the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	/**
	 * @return the ext2
	 */
	@Column(name = "EXT2", nullable = true, length = 100)
	public String getExt2() {
		return ext2;
	}

	/**
	 * @param ext2 the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	/**
	 * @return the ext3
	 */
	@Column(name = "EXT3", nullable = true, length = 100)
	public String getExt3() {
		return ext3;
	}

	/**
	 * @param ext3 the ext3 to set
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
	
	
}
