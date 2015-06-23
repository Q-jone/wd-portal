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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;


@XmlRootElement(name="T_BUILD_N_MONOMER")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildNMonomer  implements java.io.Serializable {

	private	String	ID	;
	private	String	M_NAME	;
	private	String	PARENT_ID	;
	private	String	MT_ID	;
	private	String	M_MERGE_SPLIT	;
	private	String	ORDER_NUM	;
	private	String	OPERATE_USER	;
	private	String	OPERATE_NAME	;
	private	String	OPERATE_DATE	;
	private	String	REMOVED	;
	private	String	EXT1	;
	private	String	EXT2	;
	private	String	EXT3	;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getM_NAME() {
		return M_NAME;
	}
	public void setM_NAME(String mNAME) {
		M_NAME = mNAME;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENTID) {
		PARENT_ID = pARENTID;
	}
	public String getMT_ID() {
		return MT_ID;
	}
	public void setMT_ID(String mTID) {
		MT_ID = mTID;
	}
	public String getM_MERGE_SPLIT() {
		return M_MERGE_SPLIT;
	}
	public void setM_MERGE_SPLIT(String mMERGESPLIT) {
		M_MERGE_SPLIT = mMERGESPLIT;
	}
	public String getORDER_NUM() {
		return ORDER_NUM;
	}
	public void setORDER_NUM(String oRDERNUM) {
		ORDER_NUM = oRDERNUM;
	}
	public String getOPERATE_USER() {
		return OPERATE_USER;
	}
	public void setOPERATE_USER(String oPERATEUSER) {
		OPERATE_USER = oPERATEUSER;
	}
	public String getOPERATE_NAME() {
		return OPERATE_NAME;
	}
	public void setOPERATE_NAME(String oPERATENAME) {
		OPERATE_NAME = oPERATENAME;
	}
	public String getOPERATE_DATE() {
		return OPERATE_DATE;
	}
	public void setOPERATE_DATE(String oPERATEDATE) {
		OPERATE_DATE = oPERATEDATE;
	}
	public String getREMOVED() {
		return REMOVED;
	}
	public void setREMOVED(String rEMOVED) {
		REMOVED = rEMOVED;
	}
	public String getEXT1() {
		return EXT1;
	}
	public void setEXT1(String eXT1) {
		EXT1 = eXT1;
	}
	public String getEXT2() {
		return EXT2;
	}
	public void setEXT2(String eXT2) {
		EXT2 = eXT2;
	}
	public String getEXT3() {
		return EXT3;
	}
	public void setEXT3(String eXT3) {
		EXT3 = eXT3;
	}
	
	

}