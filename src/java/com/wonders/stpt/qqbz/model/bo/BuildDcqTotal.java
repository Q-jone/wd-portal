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


@XmlRootElement(name="T_BUILD_DCQ_TOTAL")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildDcqTotal  implements java.io.Serializable {
	private	String	TO_ID	;
	private	String	P_ID	;
	private	String	P_NAME	;
	private	String	M_ID	;
	private	String	TO_AREA	;
	private	String	TO_HFZT	;
	private	String	TO_TZFGXY_HTMC	;
	private	String	TO_TZFGXY_HTBH	;
	private	String	TO_CQZBL_PLAN_START_DATE	;
	private	String	TO_CQZBL_PLAN_END_DATE	;
	private	String	TO_CQZBL_REAL_START_DATE	;
	private	String	TO_CQZBL_REAL_END_DATE	;
	private	String	TO_CQZBL_DATE_MODIFY_FLAG	;
	private	String	TO_CQZBL_STATUS	;
	private	String	TO_CQZBL_DELAY	;
	private	String	TO_DQQY_PLAN_DATE	;
	private	String	TO_DQQY_DATE_MODIFY_FLAG	;
	private	String	TO_DQQY_REAL_DATE	;
	private	String	TO_DQQY_STATUS	;
	private	String	TO_DQQY_DELAY	;
	private	String	TO_DCQXY_HTMC	;
	private	String	TO_DCQXY_HTBH	;
	private	String	TO_DQFY_GS	;
	private	String	TO_DQ_PLAN_END_DATE	;
	private	String	TO_DQ_END_DATE_MODIFY_FLAG	;
	private	String	TO_JD_PLAN_DATE	;
	private	String	TO_JD_YQ_DATE	;
	private	String	TO_JD_YQ_DATE_MODIFY_FLAG	;
	private	String	TO_JD_REAL_DATE	;
	private	String	TO_JD_REAL_STATUS	;
	private	String	TO_JD_REAL_STATUS_DESC	;
	private	String	TO_JD_REAL_DELAY	;
	private	String	TO_DW_JHDQ_ZS	;
	private	String	TO_DW_JHDQ_MJ	;
	private	String	TO_DW_SJXYDQ_ZS	;
	private	String	TO_DW_SJXYDQ_MJ	;
	private	String	TO_DW_BQWCDQ_MJ	;
	private	String	TO_DW_BQWCDQ_S	;
	private	String	TO_DW_LJYDQ_S	;
	private	String	TO_DW_LJYDQ_MJ	;
	private	String	TO_DW_SWDQ_S	;
	private	String	TO_DW_SWDQ_MJ	;
	private	String	TO_JM_JHDQ_ZS	;
	private	String	TO_JM_JHDQ_MJ	;
	private	String	TO_JM_SJXYDQ_ZS	;
	private	String	TO_JM_SJXYDQ_MJ	;
	private	String	TO_JM_BQWCDQ_MJ	;
	private	String	TO_JM_BQWCDQ_S	;
	private	String	TO_JM_LJYDQ_S	;
	private	String	TO_JM_LJYDQ_MJ	;
	private	String	TO_JM_SWDQ_S	;
	private	String	TO_JM_SWDQ_MJ	;
	private	String	TO_NM_JHDQ_ZS	;
	private	String	TO_NM_JHDQ_MJ	;
	private	String	TO_NM_SJXYDQ_ZS	;
	private	String	TO_NM_SJXYDQ_MJ	;
	private	String	TO_NM_BQWCDQ_MJ	;
	private	String	TO_NM_BQWCDQ_S	;
	private	String	TO_NM_LJYDQ_S	;
	private	String	TO_NM_LJYDQ_MJ	;
	private	String	TO_NM_SWDQ_S	;
	private	String	TO_NM_SWDQ_MJ	;
	private	String	TO_OPERATE_DATE	;
	private	String	TO_NO	;
	private	String	TO_MEMO	;
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
	public String getTO_HFZT() {
		return TO_HFZT;
	}
	public void setTO_HFZT(String tOHFZT) {
		TO_HFZT = tOHFZT;
	}
	public String getTO_TZFGXY_HTMC() {
		return TO_TZFGXY_HTMC;
	}
	public void setTO_TZFGXY_HTMC(String tOTZFGXYHTMC) {
		TO_TZFGXY_HTMC = tOTZFGXYHTMC;
	}
	public String getTO_TZFGXY_HTBH() {
		return TO_TZFGXY_HTBH;
	}
	public void setTO_TZFGXY_HTBH(String tOTZFGXYHTBH) {
		TO_TZFGXY_HTBH = tOTZFGXYHTBH;
	}
	public String getTO_CQZBL_PLAN_START_DATE() {
		return TO_CQZBL_PLAN_START_DATE;
	}
	public void setTO_CQZBL_PLAN_START_DATE(String tOCQZBLPLANSTARTDATE) {
		TO_CQZBL_PLAN_START_DATE = tOCQZBLPLANSTARTDATE;
	}
	public String getTO_CQZBL_PLAN_END_DATE() {
		return TO_CQZBL_PLAN_END_DATE;
	}
	public void setTO_CQZBL_PLAN_END_DATE(String tOCQZBLPLANENDDATE) {
		TO_CQZBL_PLAN_END_DATE = tOCQZBLPLANENDDATE;
	}
	public String getTO_CQZBL_REAL_START_DATE() {
		return TO_CQZBL_REAL_START_DATE;
	}
	public void setTO_CQZBL_REAL_START_DATE(String tOCQZBLREALSTARTDATE) {
		TO_CQZBL_REAL_START_DATE = tOCQZBLREALSTARTDATE;
	}
	public String getTO_CQZBL_REAL_END_DATE() {
		return TO_CQZBL_REAL_END_DATE;
	}
	public void setTO_CQZBL_REAL_END_DATE(String tOCQZBLREALENDDATE) {
		TO_CQZBL_REAL_END_DATE = tOCQZBLREALENDDATE;
	}
	public String getTO_CQZBL_DATE_MODIFY_FLAG() {
		return TO_CQZBL_DATE_MODIFY_FLAG;
	}
	public void setTO_CQZBL_DATE_MODIFY_FLAG(String tOCQZBLDATEMODIFYFLAG) {
		TO_CQZBL_DATE_MODIFY_FLAG = tOCQZBLDATEMODIFYFLAG;
	}
	public String getTO_CQZBL_STATUS() {
		return TO_CQZBL_STATUS;
	}
	public void setTO_CQZBL_STATUS(String tOCQZBLSTATUS) {
		TO_CQZBL_STATUS = tOCQZBLSTATUS;
	}
	public String getTO_CQZBL_DELAY() {
		return TO_CQZBL_DELAY;
	}
	public void setTO_CQZBL_DELAY(String tOCQZBLDELAY) {
		TO_CQZBL_DELAY = tOCQZBLDELAY;
	}
	public String getTO_DQQY_PLAN_DATE() {
		return TO_DQQY_PLAN_DATE;
	}
	public void setTO_DQQY_PLAN_DATE(String tODQQYPLANDATE) {
		TO_DQQY_PLAN_DATE = tODQQYPLANDATE;
	}
	public String getTO_DQQY_DATE_MODIFY_FLAG() {
		return TO_DQQY_DATE_MODIFY_FLAG;
	}
	public void setTO_DQQY_DATE_MODIFY_FLAG(String tODQQYDATEMODIFYFLAG) {
		TO_DQQY_DATE_MODIFY_FLAG = tODQQYDATEMODIFYFLAG;
	}
	public String getTO_DQQY_REAL_DATE() {
		return TO_DQQY_REAL_DATE;
	}
	public void setTO_DQQY_REAL_DATE(String tODQQYREALDATE) {
		TO_DQQY_REAL_DATE = tODQQYREALDATE;
	}
	public String getTO_DQQY_STATUS() {
		return TO_DQQY_STATUS;
	}
	public void setTO_DQQY_STATUS(String tODQQYSTATUS) {
		TO_DQQY_STATUS = tODQQYSTATUS;
	}
	public String getTO_DQQY_DELAY() {
		return TO_DQQY_DELAY;
	}
	public void setTO_DQQY_DELAY(String tODQQYDELAY) {
		TO_DQQY_DELAY = tODQQYDELAY;
	}
	public String getTO_DCQXY_HTMC() {
		return TO_DCQXY_HTMC;
	}
	public void setTO_DCQXY_HTMC(String tODCQXYHTMC) {
		TO_DCQXY_HTMC = tODCQXYHTMC;
	}
	public String getTO_DCQXY_HTBH() {
		return TO_DCQXY_HTBH;
	}
	public void setTO_DCQXY_HTBH(String tODCQXYHTBH) {
		TO_DCQXY_HTBH = tODCQXYHTBH;
	}
	public String getTO_DQFY_GS() {
		return TO_DQFY_GS;
	}
	public void setTO_DQFY_GS(String tODQFYGS) {
		TO_DQFY_GS = tODQFYGS;
	}
	public String getTO_DQ_PLAN_END_DATE() {
		return TO_DQ_PLAN_END_DATE;
	}
	public void setTO_DQ_PLAN_END_DATE(String tODQPLANENDDATE) {
		TO_DQ_PLAN_END_DATE = tODQPLANENDDATE;
	}
	public String getTO_DQ_END_DATE_MODIFY_FLAG() {
		return TO_DQ_END_DATE_MODIFY_FLAG;
	}
	public void setTO_DQ_END_DATE_MODIFY_FLAG(String tODQENDDATEMODIFYFLAG) {
		TO_DQ_END_DATE_MODIFY_FLAG = tODQENDDATEMODIFYFLAG;
	}
	public String getTO_JD_PLAN_DATE() {
		return TO_JD_PLAN_DATE;
	}
	public void setTO_JD_PLAN_DATE(String tOJDPLANDATE) {
		TO_JD_PLAN_DATE = tOJDPLANDATE;
	}
	public String getTO_JD_YQ_DATE() {
		return TO_JD_YQ_DATE;
	}
	public void setTO_JD_YQ_DATE(String tOJDYQDATE) {
		TO_JD_YQ_DATE = tOJDYQDATE;
	}
	public String getTO_JD_YQ_DATE_MODIFY_FLAG() {
		return TO_JD_YQ_DATE_MODIFY_FLAG;
	}
	public void setTO_JD_YQ_DATE_MODIFY_FLAG(String tOJDYQDATEMODIFYFLAG) {
		TO_JD_YQ_DATE_MODIFY_FLAG = tOJDYQDATEMODIFYFLAG;
	}
	public String getTO_JD_REAL_DATE() {
		return TO_JD_REAL_DATE;
	}
	public void setTO_JD_REAL_DATE(String tOJDREALDATE) {
		TO_JD_REAL_DATE = tOJDREALDATE;
	}
	public String getTO_JD_REAL_STATUS() {
		return TO_JD_REAL_STATUS;
	}
	public void setTO_JD_REAL_STATUS(String tOJDREALSTATUS) {
		TO_JD_REAL_STATUS = tOJDREALSTATUS;
	}
	public String getTO_JD_REAL_STATUS_DESC() {
		return TO_JD_REAL_STATUS_DESC;
	}
	public void setTO_JD_REAL_STATUS_DESC(String tOJDREALSTATUSDESC) {
		TO_JD_REAL_STATUS_DESC = tOJDREALSTATUSDESC;
	}
	public String getTO_JD_REAL_DELAY() {
		return TO_JD_REAL_DELAY;
	}
	public void setTO_JD_REAL_DELAY(String tOJDREALDELAY) {
		TO_JD_REAL_DELAY = tOJDREALDELAY;
	}
	public String getTO_DW_JHDQ_ZS() {
		return TO_DW_JHDQ_ZS;
	}
	public void setTO_DW_JHDQ_ZS(String tODWJHDQZS) {
		TO_DW_JHDQ_ZS = tODWJHDQZS;
	}
	public String getTO_DW_JHDQ_MJ() {
		return TO_DW_JHDQ_MJ;
	}
	public void setTO_DW_JHDQ_MJ(String tODWJHDQMJ) {
		TO_DW_JHDQ_MJ = tODWJHDQMJ;
	}
	public String getTO_DW_SJXYDQ_ZS() {
		return TO_DW_SJXYDQ_ZS;
	}
	public void setTO_DW_SJXYDQ_ZS(String tODWSJXYDQZS) {
		TO_DW_SJXYDQ_ZS = tODWSJXYDQZS;
	}
	public String getTO_DW_SJXYDQ_MJ() {
		return TO_DW_SJXYDQ_MJ;
	}
	public void setTO_DW_SJXYDQ_MJ(String tODWSJXYDQMJ) {
		TO_DW_SJXYDQ_MJ = tODWSJXYDQMJ;
	}
	public String getTO_DW_BQWCDQ_MJ() {
		return TO_DW_BQWCDQ_MJ;
	}
	public void setTO_DW_BQWCDQ_MJ(String tODWBQWCDQMJ) {
		TO_DW_BQWCDQ_MJ = tODWBQWCDQMJ;
	}
	public String getTO_DW_BQWCDQ_S() {
		return TO_DW_BQWCDQ_S;
	}
	public void setTO_DW_BQWCDQ_S(String tODWBQWCDQS) {
		TO_DW_BQWCDQ_S = tODWBQWCDQS;
	}
	public String getTO_DW_LJYDQ_S() {
		return TO_DW_LJYDQ_S;
	}
	public void setTO_DW_LJYDQ_S(String tODWLJYDQS) {
		TO_DW_LJYDQ_S = tODWLJYDQS;
	}
	public String getTO_DW_LJYDQ_MJ() {
		return TO_DW_LJYDQ_MJ;
	}
	public void setTO_DW_LJYDQ_MJ(String tODWLJYDQMJ) {
		TO_DW_LJYDQ_MJ = tODWLJYDQMJ;
	}
	public String getTO_DW_SWDQ_S() {
		return TO_DW_SWDQ_S;
	}
	public void setTO_DW_SWDQ_S(String tODWSWDQS) {
		TO_DW_SWDQ_S = tODWSWDQS;
	}
	public String getTO_DW_SWDQ_MJ() {
		return TO_DW_SWDQ_MJ;
	}
	public void setTO_DW_SWDQ_MJ(String tODWSWDQMJ) {
		TO_DW_SWDQ_MJ = tODWSWDQMJ;
	}
	public String getTO_JM_JHDQ_ZS() {
		return TO_JM_JHDQ_ZS;
	}
	public void setTO_JM_JHDQ_ZS(String tOJMJHDQZS) {
		TO_JM_JHDQ_ZS = tOJMJHDQZS;
	}
	public String getTO_JM_JHDQ_MJ() {
		return TO_JM_JHDQ_MJ;
	}
	public void setTO_JM_JHDQ_MJ(String tOJMJHDQMJ) {
		TO_JM_JHDQ_MJ = tOJMJHDQMJ;
	}
	public String getTO_JM_SJXYDQ_ZS() {
		return TO_JM_SJXYDQ_ZS;
	}
	public void setTO_JM_SJXYDQ_ZS(String tOJMSJXYDQZS) {
		TO_JM_SJXYDQ_ZS = tOJMSJXYDQZS;
	}
	public String getTO_JM_SJXYDQ_MJ() {
		return TO_JM_SJXYDQ_MJ;
	}
	public void setTO_JM_SJXYDQ_MJ(String tOJMSJXYDQMJ) {
		TO_JM_SJXYDQ_MJ = tOJMSJXYDQMJ;
	}
	public String getTO_JM_BQWCDQ_MJ() {
		return TO_JM_BQWCDQ_MJ;
	}
	public void setTO_JM_BQWCDQ_MJ(String tOJMBQWCDQMJ) {
		TO_JM_BQWCDQ_MJ = tOJMBQWCDQMJ;
	}
	public String getTO_JM_BQWCDQ_S() {
		return TO_JM_BQWCDQ_S;
	}
	public void setTO_JM_BQWCDQ_S(String tOJMBQWCDQS) {
		TO_JM_BQWCDQ_S = tOJMBQWCDQS;
	}
	public String getTO_JM_LJYDQ_S() {
		return TO_JM_LJYDQ_S;
	}
	public void setTO_JM_LJYDQ_S(String tOJMLJYDQS) {
		TO_JM_LJYDQ_S = tOJMLJYDQS;
	}
	public String getTO_JM_LJYDQ_MJ() {
		return TO_JM_LJYDQ_MJ;
	}
	public void setTO_JM_LJYDQ_MJ(String tOJMLJYDQMJ) {
		TO_JM_LJYDQ_MJ = tOJMLJYDQMJ;
	}
	public String getTO_JM_SWDQ_S() {
		return TO_JM_SWDQ_S;
	}
	public void setTO_JM_SWDQ_S(String tOJMSWDQS) {
		TO_JM_SWDQ_S = tOJMSWDQS;
	}
	public String getTO_JM_SWDQ_MJ() {
		return TO_JM_SWDQ_MJ;
	}
	public void setTO_JM_SWDQ_MJ(String tOJMSWDQMJ) {
		TO_JM_SWDQ_MJ = tOJMSWDQMJ;
	}
	public String getTO_NM_JHDQ_ZS() {
		return TO_NM_JHDQ_ZS;
	}
	public void setTO_NM_JHDQ_ZS(String tONMJHDQZS) {
		TO_NM_JHDQ_ZS = tONMJHDQZS;
	}
	public String getTO_NM_JHDQ_MJ() {
		return TO_NM_JHDQ_MJ;
	}
	public void setTO_NM_JHDQ_MJ(String tONMJHDQMJ) {
		TO_NM_JHDQ_MJ = tONMJHDQMJ;
	}
	public String getTO_NM_SJXYDQ_ZS() {
		return TO_NM_SJXYDQ_ZS;
	}
	public void setTO_NM_SJXYDQ_ZS(String tONMSJXYDQZS) {
		TO_NM_SJXYDQ_ZS = tONMSJXYDQZS;
	}
	public String getTO_NM_SJXYDQ_MJ() {
		return TO_NM_SJXYDQ_MJ;
	}
	public void setTO_NM_SJXYDQ_MJ(String tONMSJXYDQMJ) {
		TO_NM_SJXYDQ_MJ = tONMSJXYDQMJ;
	}
	public String getTO_NM_BQWCDQ_MJ() {
		return TO_NM_BQWCDQ_MJ;
	}
	public void setTO_NM_BQWCDQ_MJ(String tONMBQWCDQMJ) {
		TO_NM_BQWCDQ_MJ = tONMBQWCDQMJ;
	}
	public String getTO_NM_BQWCDQ_S() {
		return TO_NM_BQWCDQ_S;
	}
	public void setTO_NM_BQWCDQ_S(String tONMBQWCDQS) {
		TO_NM_BQWCDQ_S = tONMBQWCDQS;
	}
	public String getTO_NM_LJYDQ_S() {
		return TO_NM_LJYDQ_S;
	}
	public void setTO_NM_LJYDQ_S(String tONMLJYDQS) {
		TO_NM_LJYDQ_S = tONMLJYDQS;
	}
	public String getTO_NM_LJYDQ_MJ() {
		return TO_NM_LJYDQ_MJ;
	}
	public void setTO_NM_LJYDQ_MJ(String tONMLJYDQMJ) {
		TO_NM_LJYDQ_MJ = tONMLJYDQMJ;
	}
	public String getTO_NM_SWDQ_S() {
		return TO_NM_SWDQ_S;
	}
	public void setTO_NM_SWDQ_S(String tONMSWDQS) {
		TO_NM_SWDQ_S = tONMSWDQS;
	}
	public String getTO_NM_SWDQ_MJ() {
		return TO_NM_SWDQ_MJ;
	}
	public void setTO_NM_SWDQ_MJ(String tONMSWDQMJ) {
		TO_NM_SWDQ_MJ = tONMSWDQMJ;
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

	

}