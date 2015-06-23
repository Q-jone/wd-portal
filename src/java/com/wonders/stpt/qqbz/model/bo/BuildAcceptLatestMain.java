package com.wonders.stpt.qqbz.model.bo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;


@XmlRootElement(name="T_BUILD_ACCEPT_LATEST_MAIN")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildAcceptLatestMain  implements java.io.Serializable {


	private	String	ALM_ID	;
	private	String	M_ID	;
	private	String	P_ID	;
	private	String	ALM_OPERATOR	;
	private	String	ALM_OPERATOR_DEPT	;
	private	String	ALM_OPERATE_DATE	;
	private	String	ALM_TIMES	;
	private	String	ALM_RECORD_STATUS	;
	private	String	ALM_EXT1	;
	private	String	ALM_EXT2	;
	private	String	ALM_EXT3	;
	private	String	ALM_NOTES	;
	public String getALM_ID() {
		return ALM_ID;
	}
	public void setALM_ID(String aLMID) {
		ALM_ID = aLMID;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String mID) {
		M_ID = mID;
	}
	public String getP_ID() {
		return P_ID;
	}
	public void setP_ID(String pID) {
		P_ID = pID;
	}
	public String getALM_OPERATOR() {
		return ALM_OPERATOR;
	}
	public void setALM_OPERATOR(String aLMOPERATOR) {
		ALM_OPERATOR = aLMOPERATOR;
	}
	public String getALM_OPERATOR_DEPT() {
		return ALM_OPERATOR_DEPT;
	}
	public void setALM_OPERATOR_DEPT(String aLMOPERATORDEPT) {
		ALM_OPERATOR_DEPT = aLMOPERATORDEPT;
	}
	public String getALM_OPERATE_DATE() {
		return ALM_OPERATE_DATE;
	}
	public void setALM_OPERATE_DATE(String aLMOPERATEDATE) {
		ALM_OPERATE_DATE = aLMOPERATEDATE;
	}
	public String getALM_TIMES() {
		return ALM_TIMES;
	}
	public void setALM_TIMES(String aLMTIMES) {
		ALM_TIMES = aLMTIMES;
	}
	public String getALM_RECORD_STATUS() {
		return ALM_RECORD_STATUS;
	}
	public void setALM_RECORD_STATUS(String aLMRECORDSTATUS) {
		ALM_RECORD_STATUS = aLMRECORDSTATUS;
	}
	public String getALM_EXT1() {
		return ALM_EXT1;
	}
	public void setALM_EXT1(String aLMEXT1) {
		ALM_EXT1 = aLMEXT1;
	}
	public String getALM_EXT2() {
		return ALM_EXT2;
	}
	public void setALM_EXT2(String aLMEXT2) {
		ALM_EXT2 = aLMEXT2;
	}
	public String getALM_EXT3() {
		return ALM_EXT3;
	}
	public void setALM_EXT3(String aLMEXT3) {
		ALM_EXT3 = aLMEXT3;
	}
	public String getALM_NOTES() {
		return ALM_NOTES;
	}
	public void setALM_NOTES(String aLMNOTES) {
		ALM_NOTES = aLMNOTES;
	}

	

}