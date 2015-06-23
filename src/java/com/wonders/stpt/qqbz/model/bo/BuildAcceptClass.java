package com.wonders.stpt.qqbz.model.bo;
// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;



@XmlRootElement(name="T_BUILD_ACCEPT_CLASS")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildAcceptClass  implements java.io.Serializable {


	private	String	M_ID	;
	private	String	P_ID	;
	private	String	AC_CLSID	;
	private	String	AC_CLSNAME	;
	private	Integer	AC_ORDER	;
	private	String	AC_LEVEL	;
	private	String	AC_SELECTED	;
	private	String	AC_OPERATOR	;
	private	String	AC_OPERATOR_DEPT	;
	private	String	AC_OPERATE_DATE	;
	private	String	AC_EXT1	;
	private	String	AC_EXT2	;
	private	String	AC_EXT3	;
	private	String	AC_ID	;
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
	public String getAC_CLSID() {
		return AC_CLSID;
	}
	public void setAC_CLSID(String aCCLSID) {
		AC_CLSID = aCCLSID;
	}
	public String getAC_CLSNAME() {
		return AC_CLSNAME;
	}
	public void setAC_CLSNAME(String aCCLSNAME) {
		AC_CLSNAME = aCCLSNAME;
	}
	public Integer getAC_ORDER() {
		return AC_ORDER;
	}
	public void setAC_ORDER(Integer aCORDER) {
		AC_ORDER = aCORDER;
	}
	public String getAC_LEVEL() {
		return AC_LEVEL;
	}
	public void setAC_LEVEL(String aCLEVEL) {
		AC_LEVEL = aCLEVEL;
	}
	public String getAC_SELECTED() {
		return AC_SELECTED;
	}
	public void setAC_SELECTED(String aCSELECTED) {
		AC_SELECTED = aCSELECTED;
	}
	public String getAC_OPERATOR() {
		return AC_OPERATOR;
	}
	public void setAC_OPERATOR(String aCOPERATOR) {
		AC_OPERATOR = aCOPERATOR;
	}
	public String getAC_OPERATOR_DEPT() {
		return AC_OPERATOR_DEPT;
	}
	public void setAC_OPERATOR_DEPT(String aCOPERATORDEPT) {
		AC_OPERATOR_DEPT = aCOPERATORDEPT;
	}
	public String getAC_OPERATE_DATE() {
		return AC_OPERATE_DATE;
	}
	public void setAC_OPERATE_DATE(String aCOPERATEDATE) {
		AC_OPERATE_DATE = aCOPERATEDATE;
	}
	public String getAC_EXT1() {
		return AC_EXT1;
	}
	public void setAC_EXT1(String aCEXT1) {
		AC_EXT1 = aCEXT1;
	}
	public String getAC_EXT2() {
		return AC_EXT2;
	}
	public void setAC_EXT2(String aCEXT2) {
		AC_EXT2 = aCEXT2;
	}
	public String getAC_EXT3() {
		return AC_EXT3;
	}
	public void setAC_EXT3(String aCEXT3) {
		AC_EXT3 = aCEXT3;
	}
	public String getAC_ID() {
		return AC_ID;
	}
	public void setAC_ID(String aCID) {
		AC_ID = aCID;
	}

	

}