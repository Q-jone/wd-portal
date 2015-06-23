/**
 * 
 */
package com.wonders.stpt.metroExpress.entity.vo;
/** 
 * @ClassName: MetroExpress 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-2-29 下午06:56:03 
 *  
 */
public class MetroExpressVo{
	private String id;
	private String accidentDetail;
	private String detailSimple;
	private String accidentDate;
	private String accidentTime;
	private String accidentLine;
	private String accidentType;
	private String accidentTitle;
	//private String accidentSource;
	private String accidentRemark;
	private String remarkSimple;
	private String accidentEmergency;
	private String accidentLocation;
	private String operatePerson;
	private String accidentRemarkAll;
	
	public MetroExpressVo(){
		//this.accidentActive = "0";
		this.id = "";
		this.detailSimple = "";
		this.accidentDetail ="";
		this.accidentDate ="";
		this.accidentTime ="";
		this.accidentLine ="";
		this.accidentEmergency ="";
		this.accidentLocation ="";
		this.operatePerson ="";
		this.accidentTitle = "";
		this.accidentType = "";
		this.accidentRemark = "";
		this.remarkSimple = "";
		this.accidentRemarkAll = "";
	}

	
	public String getAccidentType() {
		return accidentType;
	}


	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}


	public String getAccidentTitle() {
		return accidentTitle;
	}


	public void setAccidentTitle(String accidentTitle) {
		this.accidentTitle = accidentTitle;
	}


	public String getAccidentRemark() {
		return accidentRemark;
	}


	public void setAccidentRemark(String accidentRemark) {
		this.accidentRemark = accidentRemark;
	}


	public String getRemarkSimple() {
		return remarkSimple;
	}


	public void setRemarkSimple(String remarkSimple) {
		this.remarkSimple = remarkSimple;
	}


	public String getDetailSimple() {
		return detailSimple;
	}


	public void setDetailSimple(String detailSimple) {
		this.detailSimple = detailSimple;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAccidentDetail() {
		return accidentDetail;
	}

	public void setAccidentDetail(String accidentDetail) {
		this.accidentDetail = accidentDetail;
	}

	public String getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}

	public String getAccidentTime() {
		return accidentTime;
	}

	public void setAccidentTime(String accidentTime) {
		this.accidentTime = accidentTime;
	}

	public String getAccidentLine() {
		return accidentLine;
	}

	public void setAccidentLine(String accidentLine) {
		this.accidentLine = accidentLine;
	}

	public String getAccidentEmergency() {
		return accidentEmergency;
	}

	public void setAccidentEmergency(String accidentEmergency) {
		this.accidentEmergency = accidentEmergency;
	}

	public String getAccidentLocation() {
		return accidentLocation;
	}

	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}


	public String getAccidentRemarkAll() {
		return accidentRemarkAll;
	}


	public void setAccidentRemarkAll(String accidentRemarkAll) {
		this.accidentRemarkAll = accidentRemarkAll;
	}
	
	
}
