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


@XmlRootElement(name="T_BUILD_MONOMER")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildMonomer  implements java.io.Serializable {

	private	String	M_ID	;
	private	String	P_ID	;
	private	String	M_TYPE	;
	private	String	M_NAME	;
	private	String	M_OPERATEDATE	;
	private	String	M_MEMO	;
	private	String	M_DELFLAG	;
	private	String	M_MERGE	;
	private	String	M_AREA	;
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
	public String getM_TYPE() {
		return M_TYPE;
	}
	public void setM_TYPE(String mTYPE) {
		M_TYPE = mTYPE;
	}
	public String getM_NAME() {
		return M_NAME;
	}
	public void setM_NAME(String mNAME) {
		M_NAME = mNAME;
	}
	public String getM_OPERATEDATE() {
		return M_OPERATEDATE;
	}
	public void setM_OPERATEDATE(String mOPERATEDATE) {
		M_OPERATEDATE = mOPERATEDATE;
	}
	public String getM_MEMO() {
		return M_MEMO;
	}
	public void setM_MEMO(String mMEMO) {
		M_MEMO = mMEMO;
	}
	public String getM_DELFLAG() {
		return M_DELFLAG;
	}
	public void setM_DELFLAG(String mDELFLAG) {
		M_DELFLAG = mDELFLAG;
	}
	public String getM_MERGE() {
		return M_MERGE;
	}
	public void setM_MERGE(String mMERGE) {
		M_MERGE = mMERGE;
	}
	public String getM_AREA() {
		return M_AREA;
	}
	public void setM_AREA(String mAREA) {
		M_AREA = mAREA;
	}

	


}