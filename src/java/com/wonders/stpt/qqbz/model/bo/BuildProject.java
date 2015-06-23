package com.wonders.stpt.qqbz.model.bo;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;



@XmlRootElement(name="T_BUILD_PROJECT")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildProject  implements java.io.Serializable {

	private	String	P_ID	;
	private	String	P_NAME	;
	private	String	P_COMPANYID	;
	private	String	P_LINE	;
	private	String	P_OPERATEDATE	;
	private	String	P_MEMO	;
	private	String	P_DELFLAG	;
	private	String	P_TYPE	;
	private	String	P_SHORT_NAME	;
	public String getP_ID() {
		return P_ID;
	}
	public void setP_ID(String pID) {
		P_ID = pID;
	}
	public String getP_NAME() {
		return P_NAME;
	}
	public void setP_NAME(String pNAME) {
		P_NAME = pNAME;
	}
	public String getP_COMPANYID() {
		return P_COMPANYID;
	}
	public void setP_COMPANYID(String pCOMPANYID) {
		P_COMPANYID = pCOMPANYID;
	}
	public String getP_LINE() {
		return P_LINE;
	}
	public void setP_LINE(String pLINE) {
		P_LINE = pLINE;
	}
	public String getP_OPERATEDATE() {
		return P_OPERATEDATE;
	}
	public void setP_OPERATEDATE(String pOPERATEDATE) {
		P_OPERATEDATE = pOPERATEDATE;
	}
	public String getP_MEMO() {
		return P_MEMO;
	}
	public void setP_MEMO(String pMEMO) {
		P_MEMO = pMEMO;
	}
	public String getP_DELFLAG() {
		return P_DELFLAG;
	}
	public void setP_DELFLAG(String pDELFLAG) {
		P_DELFLAG = pDELFLAG;
	}
	public String getP_TYPE() {
		return P_TYPE;
	}
	public void setP_TYPE(String pTYPE) {
		P_TYPE = pTYPE;
	}
	public String getP_SHORT_NAME() {
		return P_SHORT_NAME;
	}
	public void setP_SHORT_NAME(String pSHORTNAME) {
		P_SHORT_NAME = pSHORTNAME;
	}

	

}