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


@XmlRootElement(name="T_BUILD_TOTAL")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildTotal  implements java.io.Serializable {

	private	String	TO_ID	;
	private	String	P_ID	;
	private	String	P_NAME	;
	private	String	M_ID	;
	private	String	TO_AREA	;
	private	String	TO_BQ_BZ_STATUS	;
	private	String	TO_BQ_BZ_PLAN_START_DATE	;
	private	String	TO_BQ_BZ_PLAN_END_DATE	;
	private	String	TO_BQ_BZ_REAL_START_DATE	;
	private	String	TO_BQ_BZ_REAL_END_DATE	;
	private	String	TO_BQ_BZ_DELAY	;
	private	String	TO_BQ_BZ_MODIFY_FLAG	;
	private	String	TO_BQ_QY_STATUS	;
	private	String	TO_BQ_QY_PLAN_START_DATE	;
	private	String	TO_BQ_QY_PLAN_END_DATE	;
	private	String	TO_BQ_QY_REAL_START_DATE	;
	private	String	TO_BQ_QY_REAL_END_DATE	;
	private	String	TO_BQ_QY_DELAY	;
	private	String	TO_BQ_QY_UNITPRICE	;
	private	String	TO_BQ_QY_TOTALPRICE	;
	private	String	TO_BQ_QY_SYSTEM_FLAG	;
	private	String	TO_BQ_QY_MODIFY_FLAG	;
	private	String	TO_BQ_SS_STATUS	;
	private	String	TO_BQ_SS_PLAN_START_DATE	;
	private	String	TO_BQ_SS_PLAN_END_DATE	;
	private	String	TO_BQ_SS_REAL_START_DATE	;
	private	String	TO_BQ_SS_REAL_END_DATE	;
	private	String	TO_BQ_SS_DELAY	;
	private	String	TO_BQ_SS_PLANAREA	;
	private	String	TO_BQ_SS_REALAREA	;
	private	String	TO_BQ_SS_TOTAL	;
	private	String	TO_BQ_SS_MODIFY_FLAG	;
	private	String	TO_BQ_HF_SS_MERGE_FLAG	;
	private	String	TO_HF_SS_SCALE	;
	private	String	TO_HF_QY_STATUS	;
	private	String	TO_HF_QY_PLAN_START_DATE	;
	private	String	TO_HF_QY_PLAN_END_DATE	;
	private	String	TO_HF_QY_REAL_START_DATE	;
	private	String	TO_HF_QY_REAL_END_DATE	;
	private	String	TO_HF_QY_DELAY	;
	private	String	TO_HF_QY_UNITPRICE	;
	private	String	TO_HF_QY_TOTALPRICE	;
	private	String	TO_HF_QY_SYSTEM_FLAG	;
	private	String	TO_HF_QY_MODIFY_FLAG	;
	private	String	TO_HF_JD_STATUS	;
	private	String	TO_HF_JD_PLAN_START_DATE	;
	private	String	TO_HF_JD_PLAN_END_DATE	;
	private	String	TO_HF_JD_REAL_START_DATE	;
	private	String	TO_HF_JD_REAL_END_DATE	;
	private	String	TO_HF_JD_DELAY	;
	private	String	TO_HF_JD_ATTACH	;
	private	String	TO_HF_JD_MODIFY_FLAG	;
	private	String	TO_HF_SS_STATUS	;
	private	String	TO_HF_SS_PLAN_START_DATE	;
	private	String	TO_HF_SS_PLAN_END_DATE	;
	private	String	TO_HF_SS_REAL_START_DATE	;
	private	String	TO_HF_SS_REAL_END_DATE	;
	private	String	TO_HF_SS_DELAY	;
	private	String	TO_HF_SS_PLANAREA	;
	private	String	TO_HF_SS_REALAREA	;
	private	String	TO_HF_SS_TOTAL	;
	private	String	TO_HF_SS_MODIFY_FLAG	;
	private	String	TO_OPERATE_DATE	;
	private	String	TO_NO	;
	private	String	TO_MEMO	;
	private	String	TO_HFZT	;
	public String getTO_ID() {
		return TO_ID;
	}
	public void setTO_ID(String tOID) {
		TO_ID = tOID;
	}
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
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String mID) {
		M_ID = mID;
	}
	public String getTO_AREA() {
		return TO_AREA;
	}
	public void setTO_AREA(String tOAREA) {
		TO_AREA = tOAREA;
	}
	public String getTO_BQ_BZ_STATUS() {
		return TO_BQ_BZ_STATUS;
	}
	public void setTO_BQ_BZ_STATUS(String tOBQBZSTATUS) {
		TO_BQ_BZ_STATUS = tOBQBZSTATUS;
	}
	public String getTO_BQ_BZ_PLAN_START_DATE() {
		return TO_BQ_BZ_PLAN_START_DATE;
	}
	public void setTO_BQ_BZ_PLAN_START_DATE(String tOBQBZPLANSTARTDATE) {
		TO_BQ_BZ_PLAN_START_DATE = tOBQBZPLANSTARTDATE;
	}
	public String getTO_BQ_BZ_PLAN_END_DATE() {
		return TO_BQ_BZ_PLAN_END_DATE;
	}
	public void setTO_BQ_BZ_PLAN_END_DATE(String tOBQBZPLANENDDATE) {
		TO_BQ_BZ_PLAN_END_DATE = tOBQBZPLANENDDATE;
	}
	public String getTO_BQ_BZ_REAL_START_DATE() {
		return TO_BQ_BZ_REAL_START_DATE;
	}
	public void setTO_BQ_BZ_REAL_START_DATE(String tOBQBZREALSTARTDATE) {
		TO_BQ_BZ_REAL_START_DATE = tOBQBZREALSTARTDATE;
	}
	public String getTO_BQ_BZ_REAL_END_DATE() {
		return TO_BQ_BZ_REAL_END_DATE;
	}
	public void setTO_BQ_BZ_REAL_END_DATE(String tOBQBZREALENDDATE) {
		TO_BQ_BZ_REAL_END_DATE = tOBQBZREALENDDATE;
	}
	public String getTO_BQ_BZ_DELAY() {
		return TO_BQ_BZ_DELAY;
	}
	public void setTO_BQ_BZ_DELAY(String tOBQBZDELAY) {
		TO_BQ_BZ_DELAY = tOBQBZDELAY;
	}
	public String getTO_BQ_BZ_MODIFY_FLAG() {
		return TO_BQ_BZ_MODIFY_FLAG;
	}
	public void setTO_BQ_BZ_MODIFY_FLAG(String tOBQBZMODIFYFLAG) {
		TO_BQ_BZ_MODIFY_FLAG = tOBQBZMODIFYFLAG;
	}
	public String getTO_BQ_QY_STATUS() {
		return TO_BQ_QY_STATUS;
	}
	public void setTO_BQ_QY_STATUS(String tOBQQYSTATUS) {
		TO_BQ_QY_STATUS = tOBQQYSTATUS;
	}
	public String getTO_BQ_QY_PLAN_START_DATE() {
		return TO_BQ_QY_PLAN_START_DATE;
	}
	public void setTO_BQ_QY_PLAN_START_DATE(String tOBQQYPLANSTARTDATE) {
		TO_BQ_QY_PLAN_START_DATE = tOBQQYPLANSTARTDATE;
	}
	public String getTO_BQ_QY_PLAN_END_DATE() {
		return TO_BQ_QY_PLAN_END_DATE;
	}
	public void setTO_BQ_QY_PLAN_END_DATE(String tOBQQYPLANENDDATE) {
		TO_BQ_QY_PLAN_END_DATE = tOBQQYPLANENDDATE;
	}
	public String getTO_BQ_QY_REAL_START_DATE() {
		return TO_BQ_QY_REAL_START_DATE;
	}
	public void setTO_BQ_QY_REAL_START_DATE(String tOBQQYREALSTARTDATE) {
		TO_BQ_QY_REAL_START_DATE = tOBQQYREALSTARTDATE;
	}
	public String getTO_BQ_QY_REAL_END_DATE() {
		return TO_BQ_QY_REAL_END_DATE;
	}
	public void setTO_BQ_QY_REAL_END_DATE(String tOBQQYREALENDDATE) {
		TO_BQ_QY_REAL_END_DATE = tOBQQYREALENDDATE;
	}
	public String getTO_BQ_QY_DELAY() {
		return TO_BQ_QY_DELAY;
	}
	public void setTO_BQ_QY_DELAY(String tOBQQYDELAY) {
		TO_BQ_QY_DELAY = tOBQQYDELAY;
	}
	public String getTO_BQ_QY_UNITPRICE() {
		return TO_BQ_QY_UNITPRICE;
	}
	public void setTO_BQ_QY_UNITPRICE(String tOBQQYUNITPRICE) {
		TO_BQ_QY_UNITPRICE = tOBQQYUNITPRICE;
	}
	public String getTO_BQ_QY_TOTALPRICE() {
		return TO_BQ_QY_TOTALPRICE;
	}
	public void setTO_BQ_QY_TOTALPRICE(String tOBQQYTOTALPRICE) {
		TO_BQ_QY_TOTALPRICE = tOBQQYTOTALPRICE;
	}
	public String getTO_BQ_QY_SYSTEM_FLAG() {
		return TO_BQ_QY_SYSTEM_FLAG;
	}
	public void setTO_BQ_QY_SYSTEM_FLAG(String tOBQQYSYSTEMFLAG) {
		TO_BQ_QY_SYSTEM_FLAG = tOBQQYSYSTEMFLAG;
	}
	public String getTO_BQ_QY_MODIFY_FLAG() {
		return TO_BQ_QY_MODIFY_FLAG;
	}
	public void setTO_BQ_QY_MODIFY_FLAG(String tOBQQYMODIFYFLAG) {
		TO_BQ_QY_MODIFY_FLAG = tOBQQYMODIFYFLAG;
	}
	public String getTO_BQ_SS_STATUS() {
		return TO_BQ_SS_STATUS;
	}
	public void setTO_BQ_SS_STATUS(String tOBQSSSTATUS) {
		TO_BQ_SS_STATUS = tOBQSSSTATUS;
	}
	public String getTO_BQ_SS_PLAN_START_DATE() {
		return TO_BQ_SS_PLAN_START_DATE;
	}
	public void setTO_BQ_SS_PLAN_START_DATE(String tOBQSSPLANSTARTDATE) {
		TO_BQ_SS_PLAN_START_DATE = tOBQSSPLANSTARTDATE;
	}
	public String getTO_BQ_SS_PLAN_END_DATE() {
		return TO_BQ_SS_PLAN_END_DATE;
	}
	public void setTO_BQ_SS_PLAN_END_DATE(String tOBQSSPLANENDDATE) {
		TO_BQ_SS_PLAN_END_DATE = tOBQSSPLANENDDATE;
	}
	public String getTO_BQ_SS_REAL_START_DATE() {
		return TO_BQ_SS_REAL_START_DATE;
	}
	public void setTO_BQ_SS_REAL_START_DATE(String tOBQSSREALSTARTDATE) {
		TO_BQ_SS_REAL_START_DATE = tOBQSSREALSTARTDATE;
	}
	public String getTO_BQ_SS_REAL_END_DATE() {
		return TO_BQ_SS_REAL_END_DATE;
	}
	public void setTO_BQ_SS_REAL_END_DATE(String tOBQSSREALENDDATE) {
		TO_BQ_SS_REAL_END_DATE = tOBQSSREALENDDATE;
	}
	public String getTO_BQ_SS_DELAY() {
		return TO_BQ_SS_DELAY;
	}
	public void setTO_BQ_SS_DELAY(String tOBQSSDELAY) {
		TO_BQ_SS_DELAY = tOBQSSDELAY;
	}
	public String getTO_BQ_SS_PLANAREA() {
		return TO_BQ_SS_PLANAREA;
	}
	public void setTO_BQ_SS_PLANAREA(String tOBQSSPLANAREA) {
		TO_BQ_SS_PLANAREA = tOBQSSPLANAREA;
	}
	public String getTO_BQ_SS_REALAREA() {
		return TO_BQ_SS_REALAREA;
	}
	public void setTO_BQ_SS_REALAREA(String tOBQSSREALAREA) {
		TO_BQ_SS_REALAREA = tOBQSSREALAREA;
	}
	public String getTO_BQ_SS_TOTAL() {
		return TO_BQ_SS_TOTAL;
	}
	public void setTO_BQ_SS_TOTAL(String tOBQSSTOTAL) {
		TO_BQ_SS_TOTAL = tOBQSSTOTAL;
	}
	public String getTO_BQ_SS_MODIFY_FLAG() {
		return TO_BQ_SS_MODIFY_FLAG;
	}
	public void setTO_BQ_SS_MODIFY_FLAG(String tOBQSSMODIFYFLAG) {
		TO_BQ_SS_MODIFY_FLAG = tOBQSSMODIFYFLAG;
	}
	public String getTO_BQ_HF_SS_MERGE_FLAG() {
		return TO_BQ_HF_SS_MERGE_FLAG;
	}
	public void setTO_BQ_HF_SS_MERGE_FLAG(String tOBQHFSSMERGEFLAG) {
		TO_BQ_HF_SS_MERGE_FLAG = tOBQHFSSMERGEFLAG;
	}
	public String getTO_HF_SS_SCALE() {
		return TO_HF_SS_SCALE;
	}
	public void setTO_HF_SS_SCALE(String tOHFSSSCALE) {
		TO_HF_SS_SCALE = tOHFSSSCALE;
	}
	public String getTO_HF_QY_STATUS() {
		return TO_HF_QY_STATUS;
	}
	public void setTO_HF_QY_STATUS(String tOHFQYSTATUS) {
		TO_HF_QY_STATUS = tOHFQYSTATUS;
	}
	public String getTO_HF_QY_PLAN_START_DATE() {
		return TO_HF_QY_PLAN_START_DATE;
	}
	public void setTO_HF_QY_PLAN_START_DATE(String tOHFQYPLANSTARTDATE) {
		TO_HF_QY_PLAN_START_DATE = tOHFQYPLANSTARTDATE;
	}
	public String getTO_HF_QY_PLAN_END_DATE() {
		return TO_HF_QY_PLAN_END_DATE;
	}
	public void setTO_HF_QY_PLAN_END_DATE(String tOHFQYPLANENDDATE) {
		TO_HF_QY_PLAN_END_DATE = tOHFQYPLANENDDATE;
	}
	public String getTO_HF_QY_REAL_START_DATE() {
		return TO_HF_QY_REAL_START_DATE;
	}
	public void setTO_HF_QY_REAL_START_DATE(String tOHFQYREALSTARTDATE) {
		TO_HF_QY_REAL_START_DATE = tOHFQYREALSTARTDATE;
	}
	public String getTO_HF_QY_REAL_END_DATE() {
		return TO_HF_QY_REAL_END_DATE;
	}
	public void setTO_HF_QY_REAL_END_DATE(String tOHFQYREALENDDATE) {
		TO_HF_QY_REAL_END_DATE = tOHFQYREALENDDATE;
	}
	public String getTO_HF_QY_DELAY() {
		return TO_HF_QY_DELAY;
	}
	public void setTO_HF_QY_DELAY(String tOHFQYDELAY) {
		TO_HF_QY_DELAY = tOHFQYDELAY;
	}
	public String getTO_HF_QY_UNITPRICE() {
		return TO_HF_QY_UNITPRICE;
	}
	public void setTO_HF_QY_UNITPRICE(String tOHFQYUNITPRICE) {
		TO_HF_QY_UNITPRICE = tOHFQYUNITPRICE;
	}
	public String getTO_HF_QY_TOTALPRICE() {
		return TO_HF_QY_TOTALPRICE;
	}
	public void setTO_HF_QY_TOTALPRICE(String tOHFQYTOTALPRICE) {
		TO_HF_QY_TOTALPRICE = tOHFQYTOTALPRICE;
	}
	public String getTO_HF_QY_SYSTEM_FLAG() {
		return TO_HF_QY_SYSTEM_FLAG;
	}
	public void setTO_HF_QY_SYSTEM_FLAG(String tOHFQYSYSTEMFLAG) {
		TO_HF_QY_SYSTEM_FLAG = tOHFQYSYSTEMFLAG;
	}
	public String getTO_HF_QY_MODIFY_FLAG() {
		return TO_HF_QY_MODIFY_FLAG;
	}
	public void setTO_HF_QY_MODIFY_FLAG(String tOHFQYMODIFYFLAG) {
		TO_HF_QY_MODIFY_FLAG = tOHFQYMODIFYFLAG;
	}
	public String getTO_HF_JD_STATUS() {
		return TO_HF_JD_STATUS;
	}
	public void setTO_HF_JD_STATUS(String tOHFJDSTATUS) {
		TO_HF_JD_STATUS = tOHFJDSTATUS;
	}
	public String getTO_HF_JD_PLAN_START_DATE() {
		return TO_HF_JD_PLAN_START_DATE;
	}
	public void setTO_HF_JD_PLAN_START_DATE(String tOHFJDPLANSTARTDATE) {
		TO_HF_JD_PLAN_START_DATE = tOHFJDPLANSTARTDATE;
	}
	public String getTO_HF_JD_PLAN_END_DATE() {
		return TO_HF_JD_PLAN_END_DATE;
	}
	public void setTO_HF_JD_PLAN_END_DATE(String tOHFJDPLANENDDATE) {
		TO_HF_JD_PLAN_END_DATE = tOHFJDPLANENDDATE;
	}
	public String getTO_HF_JD_REAL_START_DATE() {
		return TO_HF_JD_REAL_START_DATE;
	}
	public void setTO_HF_JD_REAL_START_DATE(String tOHFJDREALSTARTDATE) {
		TO_HF_JD_REAL_START_DATE = tOHFJDREALSTARTDATE;
	}
	public String getTO_HF_JD_REAL_END_DATE() {
		return TO_HF_JD_REAL_END_DATE;
	}
	public void setTO_HF_JD_REAL_END_DATE(String tOHFJDREALENDDATE) {
		TO_HF_JD_REAL_END_DATE = tOHFJDREALENDDATE;
	}
	public String getTO_HF_JD_DELAY() {
		return TO_HF_JD_DELAY;
	}
	public void setTO_HF_JD_DELAY(String tOHFJDDELAY) {
		TO_HF_JD_DELAY = tOHFJDDELAY;
	}
	public String getTO_HF_JD_ATTACH() {
		return TO_HF_JD_ATTACH;
	}
	public void setTO_HF_JD_ATTACH(String tOHFJDATTACH) {
		TO_HF_JD_ATTACH = tOHFJDATTACH;
	}
	public String getTO_HF_JD_MODIFY_FLAG() {
		return TO_HF_JD_MODIFY_FLAG;
	}
	public void setTO_HF_JD_MODIFY_FLAG(String tOHFJDMODIFYFLAG) {
		TO_HF_JD_MODIFY_FLAG = tOHFJDMODIFYFLAG;
	}
	public String getTO_HF_SS_STATUS() {
		return TO_HF_SS_STATUS;
	}
	public void setTO_HF_SS_STATUS(String tOHFSSSTATUS) {
		TO_HF_SS_STATUS = tOHFSSSTATUS;
	}
	public String getTO_HF_SS_PLAN_START_DATE() {
		return TO_HF_SS_PLAN_START_DATE;
	}
	public void setTO_HF_SS_PLAN_START_DATE(String tOHFSSPLANSTARTDATE) {
		TO_HF_SS_PLAN_START_DATE = tOHFSSPLANSTARTDATE;
	}
	public String getTO_HF_SS_PLAN_END_DATE() {
		return TO_HF_SS_PLAN_END_DATE;
	}
	public void setTO_HF_SS_PLAN_END_DATE(String tOHFSSPLANENDDATE) {
		TO_HF_SS_PLAN_END_DATE = tOHFSSPLANENDDATE;
	}
	public String getTO_HF_SS_REAL_START_DATE() {
		return TO_HF_SS_REAL_START_DATE;
	}
	public void setTO_HF_SS_REAL_START_DATE(String tOHFSSREALSTARTDATE) {
		TO_HF_SS_REAL_START_DATE = tOHFSSREALSTARTDATE;
	}
	public String getTO_HF_SS_REAL_END_DATE() {
		return TO_HF_SS_REAL_END_DATE;
	}
	public void setTO_HF_SS_REAL_END_DATE(String tOHFSSREALENDDATE) {
		TO_HF_SS_REAL_END_DATE = tOHFSSREALENDDATE;
	}
	public String getTO_HF_SS_DELAY() {
		return TO_HF_SS_DELAY;
	}
	public void setTO_HF_SS_DELAY(String tOHFSSDELAY) {
		TO_HF_SS_DELAY = tOHFSSDELAY;
	}
	public String getTO_HF_SS_PLANAREA() {
		return TO_HF_SS_PLANAREA;
	}
	public void setTO_HF_SS_PLANAREA(String tOHFSSPLANAREA) {
		TO_HF_SS_PLANAREA = tOHFSSPLANAREA;
	}
	public String getTO_HF_SS_REALAREA() {
		return TO_HF_SS_REALAREA;
	}
	public void setTO_HF_SS_REALAREA(String tOHFSSREALAREA) {
		TO_HF_SS_REALAREA = tOHFSSREALAREA;
	}
	public String getTO_HF_SS_TOTAL() {
		return TO_HF_SS_TOTAL;
	}
	public void setTO_HF_SS_TOTAL(String tOHFSSTOTAL) {
		TO_HF_SS_TOTAL = tOHFSSTOTAL;
	}
	public String getTO_HF_SS_MODIFY_FLAG() {
		return TO_HF_SS_MODIFY_FLAG;
	}
	public void setTO_HF_SS_MODIFY_FLAG(String tOHFSSMODIFYFLAG) {
		TO_HF_SS_MODIFY_FLAG = tOHFSSMODIFYFLAG;
	}
	public String getTO_OPERATE_DATE() {
		return TO_OPERATE_DATE;
	}
	public void setTO_OPERATE_DATE(String tOOPERATEDATE) {
		TO_OPERATE_DATE = tOOPERATEDATE;
	}
	public String getTO_NO() {
		return TO_NO;
	}
	public void setTO_NO(String tONO) {
		TO_NO = tONO;
	}
	public String getTO_MEMO() {
		return TO_MEMO;
	}
	public void setTO_MEMO(String tOMEMO) {
		TO_MEMO = tOMEMO;
	}
	public String getTO_HFZT() {
		return TO_HFZT;
	}
	public void setTO_HFZT(String tOHFZT) {
		TO_HFZT = tOHFZT;
	}

	

}