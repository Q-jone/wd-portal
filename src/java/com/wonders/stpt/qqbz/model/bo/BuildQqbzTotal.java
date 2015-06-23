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


@XmlRootElement(name="T_BUILD_QQBZ_TOTAL")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildQqbzTotal  implements java.io.Serializable {

	private	String	TO_ID	;
	private	String	P_ID	;
	private	String	P_NAME	;
	private	String	M_ID	;
	private	String	TO_AREA	;
	private	String	TO_GK_SJ_STATUS	;
	private	String	TO_GK_SJ_F_STATUS	;
	private	String	TO_GK_SJ_PLAN_START_DATE	;
	private	String	TO_GK_SJ_PLAN_END_DATE	;
	private	String	TO_GK_SJ_REAL_START_DATE	;
	private	String	TO_GK_SJ_REAL_END_DATE	;
	private	String	TO_GK_SJ_DELAY	;
	private	String	TO_GK_SJ_MODIFY_FLAG	;
	private	String	TO_GK_SJ_CB	;
	private	String	TO_GK_SJ_FB	;
	private	String	TO_GK_SJ_MEMO	;
	private	String	TO_GK_SJ_ATTACH	;
	private	String	TO_KC_SJ_STATUS	;
	private	String	TO_KC_SJ_F_STATUS	;
	private	String	TO_KC_SJ_PLAN_START_DATE	;
	private	String	TO_KC_SJ_PLAN_END_DATE	;
	private	String	TO_KC_SJ_REAL_START_DATE	;
	private	String	TO_KC_SJ_REAL_END_DATE	;
	private	String	TO_KC_SJ_DELAY	;
	private	String	TO_KC_SJ_MODIFY_FLAG	;
	private	String	TO_KC_SJ_CB	;
	private	String	TO_KC_SJ_FB	;
	private	String	TO_KC_SJ_MEMO	;
	private	String	TO_KC_SJ_ATTACH	;
	private	String	TO_SGT_SJ_STATUS	;
	private	String	TO_SGT_SJ_F_STATUS	;
	private	String	TO_SGT_SJ_PLAN_START_DATE	;
	private	String	TO_SGT_SJ_PLAN_END_DATE	;
	private	String	TO_SGT_SJ_REAL_START_DATE	;
	private	String	TO_SGT_SJ_REAL_END_DATE	;
	private	String	TO_SGT_SJ_DELAY	;
	private	String	TO_SGT_SJ_MODIFY_FLAG	;
	private	String	TO_SGT_SJ_CB	;
	private	String	TO_SGT_SJ_FB	;
	private	String	TO_SGT_SJ_MEMO	;
	private	String	TO_SGT_SJ_ATTACH	;
	private	String	TO_KC_PF_STATUS	;
	private	String	TO_KC_PF_F_STATUS	;
	private	String	TO_KC_PF_PLAN_START_DATE	;
	private	String	TO_KC_PF_PLAN_END_DATE	;
	private	String	TO_KC_PF_REAL_START_DATE	;
	private	String	TO_KC_PF_REAL_END_DATE	;
	private	String	TO_KC_PF_DELAY	;
	private	String	TO_KC_PF_MODIFY_FLAG	;
	private	String	TO_KC_PF_CB	;
	private	String	TO_KC_PF_FB	;
	private	String	TO_KC_PF_MEMO	;
	private	String	TO_KC_PF_ATTACH	;
	private	String	TO_GH_XZ_STATUS	;
	private	String	TO_GH_XZ_F_STATUS	;
	private	String	TO_GH_XZ_PLAN_START_DATE	;
	private	String	TO_GH_XZ_PLAN_END_DATE	;
	private	String	TO_GH_XZ_REAL_START_DATE	;
	private	String	TO_GH_XZ_REAL_END_DATE	;
	private	String	TO_GH_XZ_DELAY	;
	private	String	TO_GH_XZ_MODIFY_FLAG	;
	private	String	TO_GH_XZ_CB	;
	private	String	TO_GH_XZ_FB	;
	private	String	TO_GH_XZ_MEMO	;
	private	String	TO_GH_XZ_ATTACH	;
	private	String	TO_GH_FA_STATUS	;
	private	String	TO_GH_FA_F_STATUS	;
	private	String	TO_GH_FA_PLAN_START_DATE	;
	private	String	TO_GH_FA_PLAN_END_DATE	;
	private	String	TO_GH_FA_REAL_START_DATE	;
	private	String	TO_GH_FA_REAL_END_DATE	;
	private	String	TO_GH_FA_DELAY	;
	private	String	TO_GH_FA_MODIFY_FLAG	;
	private	String	TO_GH_FA_CB	;
	private	String	TO_GH_FA_FB	;
	private	String	TO_GH_FA_MEMO	;
	private	String	TO_GH_FA_ATTACH	;
	private	String	TO_GH_YD_STATUS	;
	private	String	TO_GH_YD_F_STATUS	;
	private	String	TO_GH_YD_PLAN_START_DATE	;
	private	String	TO_GH_YD_PLAN_END_DATE	;
	private	String	TO_GH_YD_REAL_START_DATE	;
	private	String	TO_GH_YD_REAL_END_DATE	;
	private	String	TO_GH_YD_DELAY	;
	private	String	TO_GH_YD_MODIFY_FLAG	;
	private	String	TO_GH_YD_CB	;
	private	String	TO_GH_YD_FB	;
	private	String	TO_GH_YD_MEMO	;
	private	String	TO_GH_YD_ATTACH	;
	private	String	TO_KC_BG_STATUS	;
	private	String	TO_KC_BG_F_STATUS	;
	private	String	TO_KC_BG_PLAN_START_DATE	;
	private	String	TO_KC_BG_PLAN_END_DATE	;
	private	String	TO_KC_BG_REAL_START_DATE	;
	private	String	TO_KC_BG_REAL_END_DATE	;
	private	String	TO_KC_BG_DELAY	;
	private	String	TO_KC_BG_MODIFY_FLAG	;
	private	String	TO_KC_BG_CB	;
	private	String	TO_KC_BG_FB	;
	private	String	TO_KC_BG_MEMO	;
	private	String	TO_KC_BG_ATTACH	;
	private	String	TO_YD_ZX_STATUS	;
	private	String	TO_YD_ZX_F_STATUS	;
	private	String	TO_YD_ZX_PLAN_START_DATE	;
	private	String	TO_YD_ZX_PLAN_END_DATE	;
	private	String	TO_YD_ZX_REAL_START_DATE	;
	private	String	TO_YD_ZX_REAL_END_DATE	;
	private	String	TO_YD_ZX_DELAY	;
	private	String	TO_YD_ZX_MODIFY_FLAG	;
	private	String	TO_YD_ZX_CB	;
	private	String	TO_YD_ZX_FB	;
	private	String	TO_YD_ZX_MEMO	;
	private	String	TO_YD_ZX_ATTACH	;
	private	String	TO_YD_PW_STATUS	;
	private	String	TO_YD_PW_F_STATUS	;
	private	String	TO_YD_PW_PLAN_START_DATE	;
	private	String	TO_YD_PW_PLAN_END_DATE	;
	private	String	TO_YD_PW_REAL_START_DATE	;
	private	String	TO_YD_PW_REAL_END_DATE	;
	private	String	TO_YD_PW_DELAY	;
	private	String	TO_YD_PW_MODIFY_FLAG	;
	private	String	TO_YD_PW_CB	;
	private	String	TO_YD_PW_FB	;
	private	String	TO_YD_PW_MEMO	;
	private	String	TO_YD_PW_ATTACH	;
	private	String	TO_CQ_XK_STATUS	;
	private	String	TO_CQ_XK_F_STATUS	;
	private	String	TO_CQ_XK_PLAN_START_DATE	;
	private	String	TO_CQ_XK_PLAN_END_DATE	;
	private	String	TO_CQ_XK_REAL_START_DATE	;
	private	String	TO_CQ_XK_REAL_END_DATE	;
	private	String	TO_CQ_XK_DELAY	;
	private	String	TO_CQ_XK_MODIFY_FLAG	;
	private	String	TO_CQ_XK_CB	;
	private	String	TO_CQ_XK_FB	;
	private	String	TO_CQ_XK_MEMO	;
	private	String	TO_CQ_XK_ATTACH	;
	private	String	TO_HB_JDS_STATUS	;
	private	String	TO_HB_JDS_F_STATUS	;
	private	String	TO_HB_JDS_PLAN_START_DATE	;
	private	String	TO_HB_JDS_PLAN_END_DATE	;
	private	String	TO_HB_JDS_REAL_START_DATE	;
	private	String	TO_HB_JDS_REAL_END_DATE	;
	private	String	TO_HB_JDS_DELAY	;
	private	String	TO_HB_JDS_MODIFY_FLAG	;
	private	String	TO_HB_JDS_CB	;
	private	String	TO_HB_JDS_FB	;
	private	String	TO_HB_JDS_MEMO	;
	private	String	TO_HB_JDS_ATTACH	;
	private	String	TO_YD_PZS_STATUS	;
	private	String	TO_YD_PZS_F_STATUS	;
	private	String	TO_YD_PZS_PLAN_START_DATE	;
	private	String	TO_YD_PZS_PLAN_END_DATE	;
	private	String	TO_YD_PZS_REAL_START_DATE	;
	private	String	TO_YD_PZS_REAL_END_DATE	;
	private	String	TO_YD_PZS_DELAY	;
	private	String	TO_YD_PZS_MODIFY_FLAG	;
	private	String	TO_YD_PZS_CB	;
	private	String	TO_YD_PZS_FB	;
	private	String	TO_YD_PZS_MEMO	;
	private	String	TO_YD_PZS_ATTACH	;
	private	String	TO_WSYPJ_BG_STATUS	;
	private	String	TO_WSYPJ_BG_F_STATUS	;
	private	String	TO_WSYPJ_BG_PLAN_START_DATE	;
	private	String	TO_WSYPJ_BG_PLAN_END_DATE	;
	private	String	TO_WSYPJ_BG_REAL_START_DATE	;
	private	String	TO_WSYPJ_BG_REAL_END_DATE	;
	private	String	TO_WSYPJ_BG_DELAY	;
	private	String	TO_WSYPJ_BG_MODIFY_FLAG	;
	private	String	TO_WSYPJ_BG_CB	;
	private	String	TO_WSYPJ_BG_FB	;
	private	String	TO_WSYPJ_BG_MEMO	;
	private	String	TO_WSYPJ_BG_ATTACH	;
	private	String	TO_WS_CS_STATUS	;
	private	String	TO_WS_CS_F_STATUS	;
	private	String	TO_WS_CS_PLAN_START_DATE	;
	private	String	TO_WS_CS_PLAN_END_DATE	;
	private	String	TO_WS_CS_REAL_START_DATE	;
	private	String	TO_WS_CS_REAL_END_DATE	;
	private	String	TO_WS_CS_DELAY	;
	private	String	TO_WS_CS_MODIFY_FLAG	;
	private	String	TO_WS_CS_CB	;
	private	String	TO_WS_CS_FB	;
	private	String	TO_WS_CS_MEMO	;
	private	String	TO_WS_CS_ATTACH	;
	private	String	TO_WS_SGT_STATUS	;
	private	String	TO_WS_SGT_F_STATUS	;
	private	String	TO_WS_SGT_PLAN_START_DATE	;
	private	String	TO_WS_SGT_PLAN_END_DATE	;
	private	String	TO_WS_SGT_REAL_START_DATE	;
	private	String	TO_WS_SGT_REAL_END_DATE	;
	private	String	TO_WS_SGT_DELAY	;
	private	String	TO_WS_SGT_MODIFY_FLAG	;
	private	String	TO_WS_SGT_CB	;
	private	String	TO_WS_SGT_FB	;
	private	String	TO_WS_SGT_ATTACH	;
	private	String	TO_WS_SGT_MEMO	;
	private	String	TO_XF_CS_STATUS	;
	private	String	TO_XF_CS_F_STATUS	;
	private	String	TO_XF_CS_PLAN_START_DATE	;
	private	String	TO_XF_CS_PLAN_END_DATE	;
	private	String	TO_XF_CS_REAL_START_DATE	;
	private	String	TO_XF_CS_REAL_END_DATE	;
	private	String	TO_XF_CS_DELAY	;
	private	String	TO_XF_CS_MODIFY_FLAG	;
	private	String	TO_XF_CS_CB	;
	private	String	TO_XF_CS_FB	;
	private	String	TO_XF_CS_MEMO	;
	private	String	TO_XF_CS_ATTACH	;
	private	String	TO_XF_SGT_STATUS	;
	private	String	TO_XF_SGT_F_STATUS	;
	private	String	TO_XF_SGT_PLAN_START_DATE	;
	private	String	TO_XF_SGT_PLAN_END_DATE	;
	private	String	TO_XF_SGT_REAL_START_DATE	;
	private	String	TO_XF_SGT_REAL_END_DATE	;
	private	String	TO_XF_SGT_DELAY	;
	private	String	TO_XF_SGT_MODIFY_FLAG	;
	private	String	TO_XF_SGT_CB	;
	private	String	TO_XF_SGT_FB	;
	private	String	TO_XF_SGT_MEMO	;
	private	String	TO_XF_SGT_ATTACH	;
	private	String	TO_RF_CS_STATUS	;
	private	String	TO_RF_CS_F_STATUS	;
	private	String	TO_RF_CS_PLAN_START_DATE	;
	private	String	TO_RF_CS_PLAN_END_DATE	;
	private	String	TO_RF_CS_REAL_START_DATE	;
	private	String	TO_RF_CS_REAL_END_DATE	;
	private	String	TO_RF_CS_DELAY	;
	private	String	TO_RF_CS_MODIFY_FLAG	;
	private	String	TO_RF_CS_CB	;
	private	String	TO_RF_CS_FB	;
	private	String	TO_RF_CS_MEMO	;
	private	String	TO_RF_CS_ATTACH	;
	private	String	TO_RF_SGT_STATUS	;
	private	String	TO_RF_SGT_F_STATUS	;
	private	String	TO_RF_SGT_PLAN_START_DATE	;
	private	String	TO_RF_SGT_PLAN_END_DATE	;
	private	String	TO_RF_SGT_REAL_START_DATE	;
	private	String	TO_RF_SGT_REAL_END_DATE	;
	private	String	TO_RF_SGT_DELAY	;
	private	String	TO_RF_SGT_MODIFY_FLAG	;
	private	String	TO_RF_SGT_CB	;
	private	String	TO_RF_SGT_FB	;
	private	String	TO_RF_SGT_MEMO	;
	private	String	TO_RF_SGT_ATTACH	;
	private	String	TO_JJ_SH_STATUS	;
	private	String	TO_JJ_SH_F_STATUS	;
	private	String	TO_JJ_SH_PLAN_START_DATE	;
	private	String	TO_JJ_SH_PLAN_END_DATE	;
	private	String	TO_JJ_SH_REAL_START_DATE	;
	private	String	TO_JJ_SH_REAL_END_DATE	;
	private	String	TO_JJ_SH_DELAY	;
	private	String	TO_JJ_SH_MODIFY_FLAG	;
	private	String	TO_JJ_SH_CB	;
	private	String	TO_JJ_SH_FB	;
	private	String	TO_JJ_SH_MEMO	;
	private	String	TO_JJ_SH_ATTACH	;
	private	String	TO_LH_BQ_STATUS	;
	private	String	TO_LH_BQ_F_STATUS	;
	private	String	TO_LH_BQ_PLAN_START_DATE	;
	private	String	TO_LH_BQ_PLAN_END_DATE	;
	private	String	TO_LH_BQ_REAL_START_DATE	;
	private	String	TO_LH_BQ_REAL_END_DATE	;
	private	String	TO_LH_BQ_DELAY	;
	private	String	TO_LH_BQ_MODIFY_FLAG	;
	private	String	TO_LH_BQ_CB	;
	private	String	TO_LH_BQ_FB	;
	private	String	TO_LH_BQ_MEMO	;
	private	String	TO_LH_BQ_ATTACH	;
	private	String	TO_GH_XKZ_STATUS	;
	private	String	TO_GH_XKZ_F_STATUS	;
	private	String	TO_GH_XKZ_PLAN_START_DATE	;
	private	String	TO_GH_XKZ_PLAN_END_DATE	;
	private	String	TO_GH_XKZ_REAL_START_DATE	;
	private	String	TO_GH_XKZ_REAL_END_DATE	;
	private	String	TO_GH_XKZ_DELAY	;
	private	String	TO_GH_XKZ_MODIFY_FLAG	;
	private	String	TO_GH_XKZ_CB	;
	private	String	TO_GH_XKZ_FB	;
	private	String	TO_GH_XKZ_MEMO	;
	private	String	TO_GH_XKZ_ATTACH	;
	private	String	TO_SG_XKZ_STATUS	;
	private	String	TO_SG_XKZ_F_STATUS	;
	private	String	TO_SG_XKZ_PLAN_START_DATE	;
	private	String	TO_SG_XKZ_PLAN_END_DATE	;
	private	String	TO_SG_XKZ_REAL_START_DATE	;
	private	String	TO_SG_XKZ_REAL_END_DATE	;
	private	String	TO_SG_XKZ_DELAY	;
	private	String	TO_SG_XKZ_MODIFY_FLAG	;
	private	String	TO_SG_XKZ_CB	;
	private	String	TO_SG_XKZ_FB	;
	private	String	TO_SG_XKZ_MEMO	;
	private	String	TO_SG_XKZ_ATTACH	;
	private	String	TO_OPERATE_DATE	;
	private	Integer	TO_NO	;
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
	public String getTO_GK_SJ_STATUS() {
		return TO_GK_SJ_STATUS;
	}
	public void setTO_GK_SJ_STATUS(String tOGKSJSTATUS) {
		TO_GK_SJ_STATUS = tOGKSJSTATUS;
	}
	public String getTO_GK_SJ_F_STATUS() {
		return TO_GK_SJ_F_STATUS;
	}
	public void setTO_GK_SJ_F_STATUS(String tOGKSJFSTATUS) {
		TO_GK_SJ_F_STATUS = tOGKSJFSTATUS;
	}
	public String getTO_GK_SJ_PLAN_START_DATE() {
		return TO_GK_SJ_PLAN_START_DATE;
	}
	public void setTO_GK_SJ_PLAN_START_DATE(String tOGKSJPLANSTARTDATE) {
		TO_GK_SJ_PLAN_START_DATE = tOGKSJPLANSTARTDATE;
	}
	public String getTO_GK_SJ_PLAN_END_DATE() {
		return TO_GK_SJ_PLAN_END_DATE;
	}
	public void setTO_GK_SJ_PLAN_END_DATE(String tOGKSJPLANENDDATE) {
		TO_GK_SJ_PLAN_END_DATE = tOGKSJPLANENDDATE;
	}
	public String getTO_GK_SJ_REAL_START_DATE() {
		return TO_GK_SJ_REAL_START_DATE;
	}
	public void setTO_GK_SJ_REAL_START_DATE(String tOGKSJREALSTARTDATE) {
		TO_GK_SJ_REAL_START_DATE = tOGKSJREALSTARTDATE;
	}
	public String getTO_GK_SJ_REAL_END_DATE() {
		return TO_GK_SJ_REAL_END_DATE;
	}
	public void setTO_GK_SJ_REAL_END_DATE(String tOGKSJREALENDDATE) {
		TO_GK_SJ_REAL_END_DATE = tOGKSJREALENDDATE;
	}
	public String getTO_GK_SJ_DELAY() {
		return TO_GK_SJ_DELAY;
	}
	public void setTO_GK_SJ_DELAY(String tOGKSJDELAY) {
		TO_GK_SJ_DELAY = tOGKSJDELAY;
	}
	public String getTO_GK_SJ_MODIFY_FLAG() {
		return TO_GK_SJ_MODIFY_FLAG;
	}
	public void setTO_GK_SJ_MODIFY_FLAG(String tOGKSJMODIFYFLAG) {
		TO_GK_SJ_MODIFY_FLAG = tOGKSJMODIFYFLAG;
	}
	public String getTO_GK_SJ_CB() {
		return TO_GK_SJ_CB;
	}
	public void setTO_GK_SJ_CB(String tOGKSJCB) {
		TO_GK_SJ_CB = tOGKSJCB;
	}
	public String getTO_GK_SJ_FB() {
		return TO_GK_SJ_FB;
	}
	public void setTO_GK_SJ_FB(String tOGKSJFB) {
		TO_GK_SJ_FB = tOGKSJFB;
	}
	public String getTO_GK_SJ_MEMO() {
		return TO_GK_SJ_MEMO;
	}
	public void setTO_GK_SJ_MEMO(String tOGKSJMEMO) {
		TO_GK_SJ_MEMO = tOGKSJMEMO;
	}
	public String getTO_GK_SJ_ATTACH() {
		return TO_GK_SJ_ATTACH;
	}
	public void setTO_GK_SJ_ATTACH(String tOGKSJATTACH) {
		TO_GK_SJ_ATTACH = tOGKSJATTACH;
	}
	public String getTO_KC_SJ_STATUS() {
		return TO_KC_SJ_STATUS;
	}
	public void setTO_KC_SJ_STATUS(String tOKCSJSTATUS) {
		TO_KC_SJ_STATUS = tOKCSJSTATUS;
	}
	public String getTO_KC_SJ_F_STATUS() {
		return TO_KC_SJ_F_STATUS;
	}
	public void setTO_KC_SJ_F_STATUS(String tOKCSJFSTATUS) {
		TO_KC_SJ_F_STATUS = tOKCSJFSTATUS;
	}
	public String getTO_KC_SJ_PLAN_START_DATE() {
		return TO_KC_SJ_PLAN_START_DATE;
	}
	public void setTO_KC_SJ_PLAN_START_DATE(String tOKCSJPLANSTARTDATE) {
		TO_KC_SJ_PLAN_START_DATE = tOKCSJPLANSTARTDATE;
	}
	public String getTO_KC_SJ_PLAN_END_DATE() {
		return TO_KC_SJ_PLAN_END_DATE;
	}
	public void setTO_KC_SJ_PLAN_END_DATE(String tOKCSJPLANENDDATE) {
		TO_KC_SJ_PLAN_END_DATE = tOKCSJPLANENDDATE;
	}
	public String getTO_KC_SJ_REAL_START_DATE() {
		return TO_KC_SJ_REAL_START_DATE;
	}
	public void setTO_KC_SJ_REAL_START_DATE(String tOKCSJREALSTARTDATE) {
		TO_KC_SJ_REAL_START_DATE = tOKCSJREALSTARTDATE;
	}
	public String getTO_KC_SJ_REAL_END_DATE() {
		return TO_KC_SJ_REAL_END_DATE;
	}
	public void setTO_KC_SJ_REAL_END_DATE(String tOKCSJREALENDDATE) {
		TO_KC_SJ_REAL_END_DATE = tOKCSJREALENDDATE;
	}
	public String getTO_KC_SJ_DELAY() {
		return TO_KC_SJ_DELAY;
	}
	public void setTO_KC_SJ_DELAY(String tOKCSJDELAY) {
		TO_KC_SJ_DELAY = tOKCSJDELAY;
	}
	public String getTO_KC_SJ_MODIFY_FLAG() {
		return TO_KC_SJ_MODIFY_FLAG;
	}
	public void setTO_KC_SJ_MODIFY_FLAG(String tOKCSJMODIFYFLAG) {
		TO_KC_SJ_MODIFY_FLAG = tOKCSJMODIFYFLAG;
	}
	public String getTO_KC_SJ_CB() {
		return TO_KC_SJ_CB;
	}
	public void setTO_KC_SJ_CB(String tOKCSJCB) {
		TO_KC_SJ_CB = tOKCSJCB;
	}
	public String getTO_KC_SJ_FB() {
		return TO_KC_SJ_FB;
	}
	public void setTO_KC_SJ_FB(String tOKCSJFB) {
		TO_KC_SJ_FB = tOKCSJFB;
	}
	public String getTO_KC_SJ_MEMO() {
		return TO_KC_SJ_MEMO;
	}
	public void setTO_KC_SJ_MEMO(String tOKCSJMEMO) {
		TO_KC_SJ_MEMO = tOKCSJMEMO;
	}
	public String getTO_KC_SJ_ATTACH() {
		return TO_KC_SJ_ATTACH;
	}
	public void setTO_KC_SJ_ATTACH(String tOKCSJATTACH) {
		TO_KC_SJ_ATTACH = tOKCSJATTACH;
	}
	public String getTO_SGT_SJ_STATUS() {
		return TO_SGT_SJ_STATUS;
	}
	public void setTO_SGT_SJ_STATUS(String tOSGTSJSTATUS) {
		TO_SGT_SJ_STATUS = tOSGTSJSTATUS;
	}
	public String getTO_SGT_SJ_F_STATUS() {
		return TO_SGT_SJ_F_STATUS;
	}
	public void setTO_SGT_SJ_F_STATUS(String tOSGTSJFSTATUS) {
		TO_SGT_SJ_F_STATUS = tOSGTSJFSTATUS;
	}
	public String getTO_SGT_SJ_PLAN_START_DATE() {
		return TO_SGT_SJ_PLAN_START_DATE;
	}
	public void setTO_SGT_SJ_PLAN_START_DATE(String tOSGTSJPLANSTARTDATE) {
		TO_SGT_SJ_PLAN_START_DATE = tOSGTSJPLANSTARTDATE;
	}
	public String getTO_SGT_SJ_PLAN_END_DATE() {
		return TO_SGT_SJ_PLAN_END_DATE;
	}
	public void setTO_SGT_SJ_PLAN_END_DATE(String tOSGTSJPLANENDDATE) {
		TO_SGT_SJ_PLAN_END_DATE = tOSGTSJPLANENDDATE;
	}
	public String getTO_SGT_SJ_REAL_START_DATE() {
		return TO_SGT_SJ_REAL_START_DATE;
	}
	public void setTO_SGT_SJ_REAL_START_DATE(String tOSGTSJREALSTARTDATE) {
		TO_SGT_SJ_REAL_START_DATE = tOSGTSJREALSTARTDATE;
	}
	public String getTO_SGT_SJ_REAL_END_DATE() {
		return TO_SGT_SJ_REAL_END_DATE;
	}
	public void setTO_SGT_SJ_REAL_END_DATE(String tOSGTSJREALENDDATE) {
		TO_SGT_SJ_REAL_END_DATE = tOSGTSJREALENDDATE;
	}
	public String getTO_SGT_SJ_DELAY() {
		return TO_SGT_SJ_DELAY;
	}
	public void setTO_SGT_SJ_DELAY(String tOSGTSJDELAY) {
		TO_SGT_SJ_DELAY = tOSGTSJDELAY;
	}
	public String getTO_SGT_SJ_MODIFY_FLAG() {
		return TO_SGT_SJ_MODIFY_FLAG;
	}
	public void setTO_SGT_SJ_MODIFY_FLAG(String tOSGTSJMODIFYFLAG) {
		TO_SGT_SJ_MODIFY_FLAG = tOSGTSJMODIFYFLAG;
	}
	public String getTO_SGT_SJ_CB() {
		return TO_SGT_SJ_CB;
	}
	public void setTO_SGT_SJ_CB(String tOSGTSJCB) {
		TO_SGT_SJ_CB = tOSGTSJCB;
	}
	public String getTO_SGT_SJ_FB() {
		return TO_SGT_SJ_FB;
	}
	public void setTO_SGT_SJ_FB(String tOSGTSJFB) {
		TO_SGT_SJ_FB = tOSGTSJFB;
	}
	public String getTO_SGT_SJ_MEMO() {
		return TO_SGT_SJ_MEMO;
	}
	public void setTO_SGT_SJ_MEMO(String tOSGTSJMEMO) {
		TO_SGT_SJ_MEMO = tOSGTSJMEMO;
	}
	public String getTO_SGT_SJ_ATTACH() {
		return TO_SGT_SJ_ATTACH;
	}
	public void setTO_SGT_SJ_ATTACH(String tOSGTSJATTACH) {
		TO_SGT_SJ_ATTACH = tOSGTSJATTACH;
	}
	public String getTO_KC_PF_STATUS() {
		return TO_KC_PF_STATUS;
	}
	public void setTO_KC_PF_STATUS(String tOKCPFSTATUS) {
		TO_KC_PF_STATUS = tOKCPFSTATUS;
	}
	public String getTO_KC_PF_F_STATUS() {
		return TO_KC_PF_F_STATUS;
	}
	public void setTO_KC_PF_F_STATUS(String tOKCPFFSTATUS) {
		TO_KC_PF_F_STATUS = tOKCPFFSTATUS;
	}
	public String getTO_KC_PF_PLAN_START_DATE() {
		return TO_KC_PF_PLAN_START_DATE;
	}
	public void setTO_KC_PF_PLAN_START_DATE(String tOKCPFPLANSTARTDATE) {
		TO_KC_PF_PLAN_START_DATE = tOKCPFPLANSTARTDATE;
	}
	public String getTO_KC_PF_PLAN_END_DATE() {
		return TO_KC_PF_PLAN_END_DATE;
	}
	public void setTO_KC_PF_PLAN_END_DATE(String tOKCPFPLANENDDATE) {
		TO_KC_PF_PLAN_END_DATE = tOKCPFPLANENDDATE;
	}
	public String getTO_KC_PF_REAL_START_DATE() {
		return TO_KC_PF_REAL_START_DATE;
	}
	public void setTO_KC_PF_REAL_START_DATE(String tOKCPFREALSTARTDATE) {
		TO_KC_PF_REAL_START_DATE = tOKCPFREALSTARTDATE;
	}
	public String getTO_KC_PF_REAL_END_DATE() {
		return TO_KC_PF_REAL_END_DATE;
	}
	public void setTO_KC_PF_REAL_END_DATE(String tOKCPFREALENDDATE) {
		TO_KC_PF_REAL_END_DATE = tOKCPFREALENDDATE;
	}
	public String getTO_KC_PF_DELAY() {
		return TO_KC_PF_DELAY;
	}
	public void setTO_KC_PF_DELAY(String tOKCPFDELAY) {
		TO_KC_PF_DELAY = tOKCPFDELAY;
	}
	public String getTO_KC_PF_MODIFY_FLAG() {
		return TO_KC_PF_MODIFY_FLAG;
	}
	public void setTO_KC_PF_MODIFY_FLAG(String tOKCPFMODIFYFLAG) {
		TO_KC_PF_MODIFY_FLAG = tOKCPFMODIFYFLAG;
	}
	public String getTO_KC_PF_CB() {
		return TO_KC_PF_CB;
	}
	public void setTO_KC_PF_CB(String tOKCPFCB) {
		TO_KC_PF_CB = tOKCPFCB;
	}
	public String getTO_KC_PF_FB() {
		return TO_KC_PF_FB;
	}
	public void setTO_KC_PF_FB(String tOKCPFFB) {
		TO_KC_PF_FB = tOKCPFFB;
	}
	public String getTO_KC_PF_MEMO() {
		return TO_KC_PF_MEMO;
	}
	public void setTO_KC_PF_MEMO(String tOKCPFMEMO) {
		TO_KC_PF_MEMO = tOKCPFMEMO;
	}
	public String getTO_KC_PF_ATTACH() {
		return TO_KC_PF_ATTACH;
	}
	public void setTO_KC_PF_ATTACH(String tOKCPFATTACH) {
		TO_KC_PF_ATTACH = tOKCPFATTACH;
	}
	public String getTO_GH_XZ_STATUS() {
		return TO_GH_XZ_STATUS;
	}
	public void setTO_GH_XZ_STATUS(String tOGHXZSTATUS) {
		TO_GH_XZ_STATUS = tOGHXZSTATUS;
	}
	public String getTO_GH_XZ_F_STATUS() {
		return TO_GH_XZ_F_STATUS;
	}
	public void setTO_GH_XZ_F_STATUS(String tOGHXZFSTATUS) {
		TO_GH_XZ_F_STATUS = tOGHXZFSTATUS;
	}
	public String getTO_GH_XZ_PLAN_START_DATE() {
		return TO_GH_XZ_PLAN_START_DATE;
	}
	public void setTO_GH_XZ_PLAN_START_DATE(String tOGHXZPLANSTARTDATE) {
		TO_GH_XZ_PLAN_START_DATE = tOGHXZPLANSTARTDATE;
	}
	public String getTO_GH_XZ_PLAN_END_DATE() {
		return TO_GH_XZ_PLAN_END_DATE;
	}
	public void setTO_GH_XZ_PLAN_END_DATE(String tOGHXZPLANENDDATE) {
		TO_GH_XZ_PLAN_END_DATE = tOGHXZPLANENDDATE;
	}
	public String getTO_GH_XZ_REAL_START_DATE() {
		return TO_GH_XZ_REAL_START_DATE;
	}
	public void setTO_GH_XZ_REAL_START_DATE(String tOGHXZREALSTARTDATE) {
		TO_GH_XZ_REAL_START_DATE = tOGHXZREALSTARTDATE;
	}
	public String getTO_GH_XZ_REAL_END_DATE() {
		return TO_GH_XZ_REAL_END_DATE;
	}
	public void setTO_GH_XZ_REAL_END_DATE(String tOGHXZREALENDDATE) {
		TO_GH_XZ_REAL_END_DATE = tOGHXZREALENDDATE;
	}
	public String getTO_GH_XZ_DELAY() {
		return TO_GH_XZ_DELAY;
	}
	public void setTO_GH_XZ_DELAY(String tOGHXZDELAY) {
		TO_GH_XZ_DELAY = tOGHXZDELAY;
	}
	public String getTO_GH_XZ_MODIFY_FLAG() {
		return TO_GH_XZ_MODIFY_FLAG;
	}
	public void setTO_GH_XZ_MODIFY_FLAG(String tOGHXZMODIFYFLAG) {
		TO_GH_XZ_MODIFY_FLAG = tOGHXZMODIFYFLAG;
	}
	public String getTO_GH_XZ_CB() {
		return TO_GH_XZ_CB;
	}
	public void setTO_GH_XZ_CB(String tOGHXZCB) {
		TO_GH_XZ_CB = tOGHXZCB;
	}
	public String getTO_GH_XZ_FB() {
		return TO_GH_XZ_FB;
	}
	public void setTO_GH_XZ_FB(String tOGHXZFB) {
		TO_GH_XZ_FB = tOGHXZFB;
	}
	public String getTO_GH_XZ_MEMO() {
		return TO_GH_XZ_MEMO;
	}
	public void setTO_GH_XZ_MEMO(String tOGHXZMEMO) {
		TO_GH_XZ_MEMO = tOGHXZMEMO;
	}
	public String getTO_GH_XZ_ATTACH() {
		return TO_GH_XZ_ATTACH;
	}
	public void setTO_GH_XZ_ATTACH(String tOGHXZATTACH) {
		TO_GH_XZ_ATTACH = tOGHXZATTACH;
	}
	public String getTO_GH_FA_STATUS() {
		return TO_GH_FA_STATUS;
	}
	public void setTO_GH_FA_STATUS(String tOGHFASTATUS) {
		TO_GH_FA_STATUS = tOGHFASTATUS;
	}
	public String getTO_GH_FA_F_STATUS() {
		return TO_GH_FA_F_STATUS;
	}
	public void setTO_GH_FA_F_STATUS(String tOGHFAFSTATUS) {
		TO_GH_FA_F_STATUS = tOGHFAFSTATUS;
	}
	public String getTO_GH_FA_PLAN_START_DATE() {
		return TO_GH_FA_PLAN_START_DATE;
	}
	public void setTO_GH_FA_PLAN_START_DATE(String tOGHFAPLANSTARTDATE) {
		TO_GH_FA_PLAN_START_DATE = tOGHFAPLANSTARTDATE;
	}
	public String getTO_GH_FA_PLAN_END_DATE() {
		return TO_GH_FA_PLAN_END_DATE;
	}
	public void setTO_GH_FA_PLAN_END_DATE(String tOGHFAPLANENDDATE) {
		TO_GH_FA_PLAN_END_DATE = tOGHFAPLANENDDATE;
	}
	public String getTO_GH_FA_REAL_START_DATE() {
		return TO_GH_FA_REAL_START_DATE;
	}
	public void setTO_GH_FA_REAL_START_DATE(String tOGHFAREALSTARTDATE) {
		TO_GH_FA_REAL_START_DATE = tOGHFAREALSTARTDATE;
	}
	public String getTO_GH_FA_REAL_END_DATE() {
		return TO_GH_FA_REAL_END_DATE;
	}
	public void setTO_GH_FA_REAL_END_DATE(String tOGHFAREALENDDATE) {
		TO_GH_FA_REAL_END_DATE = tOGHFAREALENDDATE;
	}
	public String getTO_GH_FA_DELAY() {
		return TO_GH_FA_DELAY;
	}
	public void setTO_GH_FA_DELAY(String tOGHFADELAY) {
		TO_GH_FA_DELAY = tOGHFADELAY;
	}
	public String getTO_GH_FA_MODIFY_FLAG() {
		return TO_GH_FA_MODIFY_FLAG;
	}
	public void setTO_GH_FA_MODIFY_FLAG(String tOGHFAMODIFYFLAG) {
		TO_GH_FA_MODIFY_FLAG = tOGHFAMODIFYFLAG;
	}
	public String getTO_GH_FA_CB() {
		return TO_GH_FA_CB;
	}
	public void setTO_GH_FA_CB(String tOGHFACB) {
		TO_GH_FA_CB = tOGHFACB;
	}
	public String getTO_GH_FA_FB() {
		return TO_GH_FA_FB;
	}
	public void setTO_GH_FA_FB(String tOGHFAFB) {
		TO_GH_FA_FB = tOGHFAFB;
	}
	public String getTO_GH_FA_MEMO() {
		return TO_GH_FA_MEMO;
	}
	public void setTO_GH_FA_MEMO(String tOGHFAMEMO) {
		TO_GH_FA_MEMO = tOGHFAMEMO;
	}
	public String getTO_GH_FA_ATTACH() {
		return TO_GH_FA_ATTACH;
	}
	public void setTO_GH_FA_ATTACH(String tOGHFAATTACH) {
		TO_GH_FA_ATTACH = tOGHFAATTACH;
	}
	public String getTO_GH_YD_STATUS() {
		return TO_GH_YD_STATUS;
	}
	public void setTO_GH_YD_STATUS(String tOGHYDSTATUS) {
		TO_GH_YD_STATUS = tOGHYDSTATUS;
	}
	public String getTO_GH_YD_F_STATUS() {
		return TO_GH_YD_F_STATUS;
	}
	public void setTO_GH_YD_F_STATUS(String tOGHYDFSTATUS) {
		TO_GH_YD_F_STATUS = tOGHYDFSTATUS;
	}
	public String getTO_GH_YD_PLAN_START_DATE() {
		return TO_GH_YD_PLAN_START_DATE;
	}
	public void setTO_GH_YD_PLAN_START_DATE(String tOGHYDPLANSTARTDATE) {
		TO_GH_YD_PLAN_START_DATE = tOGHYDPLANSTARTDATE;
	}
	public String getTO_GH_YD_PLAN_END_DATE() {
		return TO_GH_YD_PLAN_END_DATE;
	}
	public void setTO_GH_YD_PLAN_END_DATE(String tOGHYDPLANENDDATE) {
		TO_GH_YD_PLAN_END_DATE = tOGHYDPLANENDDATE;
	}
	public String getTO_GH_YD_REAL_START_DATE() {
		return TO_GH_YD_REAL_START_DATE;
	}
	public void setTO_GH_YD_REAL_START_DATE(String tOGHYDREALSTARTDATE) {
		TO_GH_YD_REAL_START_DATE = tOGHYDREALSTARTDATE;
	}
	public String getTO_GH_YD_REAL_END_DATE() {
		return TO_GH_YD_REAL_END_DATE;
	}
	public void setTO_GH_YD_REAL_END_DATE(String tOGHYDREALENDDATE) {
		TO_GH_YD_REAL_END_DATE = tOGHYDREALENDDATE;
	}
	public String getTO_GH_YD_DELAY() {
		return TO_GH_YD_DELAY;
	}
	public void setTO_GH_YD_DELAY(String tOGHYDDELAY) {
		TO_GH_YD_DELAY = tOGHYDDELAY;
	}
	public String getTO_GH_YD_MODIFY_FLAG() {
		return TO_GH_YD_MODIFY_FLAG;
	}
	public void setTO_GH_YD_MODIFY_FLAG(String tOGHYDMODIFYFLAG) {
		TO_GH_YD_MODIFY_FLAG = tOGHYDMODIFYFLAG;
	}
	public String getTO_GH_YD_CB() {
		return TO_GH_YD_CB;
	}
	public void setTO_GH_YD_CB(String tOGHYDCB) {
		TO_GH_YD_CB = tOGHYDCB;
	}
	public String getTO_GH_YD_FB() {
		return TO_GH_YD_FB;
	}
	public void setTO_GH_YD_FB(String tOGHYDFB) {
		TO_GH_YD_FB = tOGHYDFB;
	}
	public String getTO_GH_YD_MEMO() {
		return TO_GH_YD_MEMO;
	}
	public void setTO_GH_YD_MEMO(String tOGHYDMEMO) {
		TO_GH_YD_MEMO = tOGHYDMEMO;
	}
	public String getTO_GH_YD_ATTACH() {
		return TO_GH_YD_ATTACH;
	}
	public void setTO_GH_YD_ATTACH(String tOGHYDATTACH) {
		TO_GH_YD_ATTACH = tOGHYDATTACH;
	}
	public String getTO_KC_BG_STATUS() {
		return TO_KC_BG_STATUS;
	}
	public void setTO_KC_BG_STATUS(String tOKCBGSTATUS) {
		TO_KC_BG_STATUS = tOKCBGSTATUS;
	}
	public String getTO_KC_BG_F_STATUS() {
		return TO_KC_BG_F_STATUS;
	}
	public void setTO_KC_BG_F_STATUS(String tOKCBGFSTATUS) {
		TO_KC_BG_F_STATUS = tOKCBGFSTATUS;
	}
	public String getTO_KC_BG_PLAN_START_DATE() {
		return TO_KC_BG_PLAN_START_DATE;
	}
	public void setTO_KC_BG_PLAN_START_DATE(String tOKCBGPLANSTARTDATE) {
		TO_KC_BG_PLAN_START_DATE = tOKCBGPLANSTARTDATE;
	}
	public String getTO_KC_BG_PLAN_END_DATE() {
		return TO_KC_BG_PLAN_END_DATE;
	}
	public void setTO_KC_BG_PLAN_END_DATE(String tOKCBGPLANENDDATE) {
		TO_KC_BG_PLAN_END_DATE = tOKCBGPLANENDDATE;
	}
	public String getTO_KC_BG_REAL_START_DATE() {
		return TO_KC_BG_REAL_START_DATE;
	}
	public void setTO_KC_BG_REAL_START_DATE(String tOKCBGREALSTARTDATE) {
		TO_KC_BG_REAL_START_DATE = tOKCBGREALSTARTDATE;
	}
	public String getTO_KC_BG_REAL_END_DATE() {
		return TO_KC_BG_REAL_END_DATE;
	}
	public void setTO_KC_BG_REAL_END_DATE(String tOKCBGREALENDDATE) {
		TO_KC_BG_REAL_END_DATE = tOKCBGREALENDDATE;
	}
	public String getTO_KC_BG_DELAY() {
		return TO_KC_BG_DELAY;
	}
	public void setTO_KC_BG_DELAY(String tOKCBGDELAY) {
		TO_KC_BG_DELAY = tOKCBGDELAY;
	}
	public String getTO_KC_BG_MODIFY_FLAG() {
		return TO_KC_BG_MODIFY_FLAG;
	}
	public void setTO_KC_BG_MODIFY_FLAG(String tOKCBGMODIFYFLAG) {
		TO_KC_BG_MODIFY_FLAG = tOKCBGMODIFYFLAG;
	}
	public String getTO_KC_BG_CB() {
		return TO_KC_BG_CB;
	}
	public void setTO_KC_BG_CB(String tOKCBGCB) {
		TO_KC_BG_CB = tOKCBGCB;
	}
	public String getTO_KC_BG_FB() {
		return TO_KC_BG_FB;
	}
	public void setTO_KC_BG_FB(String tOKCBGFB) {
		TO_KC_BG_FB = tOKCBGFB;
	}
	public String getTO_KC_BG_MEMO() {
		return TO_KC_BG_MEMO;
	}
	public void setTO_KC_BG_MEMO(String tOKCBGMEMO) {
		TO_KC_BG_MEMO = tOKCBGMEMO;
	}
	public String getTO_KC_BG_ATTACH() {
		return TO_KC_BG_ATTACH;
	}
	public void setTO_KC_BG_ATTACH(String tOKCBGATTACH) {
		TO_KC_BG_ATTACH = tOKCBGATTACH;
	}
	public String getTO_YD_ZX_STATUS() {
		return TO_YD_ZX_STATUS;
	}
	public void setTO_YD_ZX_STATUS(String tOYDZXSTATUS) {
		TO_YD_ZX_STATUS = tOYDZXSTATUS;
	}
	public String getTO_YD_ZX_F_STATUS() {
		return TO_YD_ZX_F_STATUS;
	}
	public void setTO_YD_ZX_F_STATUS(String tOYDZXFSTATUS) {
		TO_YD_ZX_F_STATUS = tOYDZXFSTATUS;
	}
	public String getTO_YD_ZX_PLAN_START_DATE() {
		return TO_YD_ZX_PLAN_START_DATE;
	}
	public void setTO_YD_ZX_PLAN_START_DATE(String tOYDZXPLANSTARTDATE) {
		TO_YD_ZX_PLAN_START_DATE = tOYDZXPLANSTARTDATE;
	}
	public String getTO_YD_ZX_PLAN_END_DATE() {
		return TO_YD_ZX_PLAN_END_DATE;
	}
	public void setTO_YD_ZX_PLAN_END_DATE(String tOYDZXPLANENDDATE) {
		TO_YD_ZX_PLAN_END_DATE = tOYDZXPLANENDDATE;
	}
	public String getTO_YD_ZX_REAL_START_DATE() {
		return TO_YD_ZX_REAL_START_DATE;
	}
	public void setTO_YD_ZX_REAL_START_DATE(String tOYDZXREALSTARTDATE) {
		TO_YD_ZX_REAL_START_DATE = tOYDZXREALSTARTDATE;
	}
	public String getTO_YD_ZX_REAL_END_DATE() {
		return TO_YD_ZX_REAL_END_DATE;
	}
	public void setTO_YD_ZX_REAL_END_DATE(String tOYDZXREALENDDATE) {
		TO_YD_ZX_REAL_END_DATE = tOYDZXREALENDDATE;
	}
	public String getTO_YD_ZX_DELAY() {
		return TO_YD_ZX_DELAY;
	}
	public void setTO_YD_ZX_DELAY(String tOYDZXDELAY) {
		TO_YD_ZX_DELAY = tOYDZXDELAY;
	}
	public String getTO_YD_ZX_MODIFY_FLAG() {
		return TO_YD_ZX_MODIFY_FLAG;
	}
	public void setTO_YD_ZX_MODIFY_FLAG(String tOYDZXMODIFYFLAG) {
		TO_YD_ZX_MODIFY_FLAG = tOYDZXMODIFYFLAG;
	}
	public String getTO_YD_ZX_CB() {
		return TO_YD_ZX_CB;
	}
	public void setTO_YD_ZX_CB(String tOYDZXCB) {
		TO_YD_ZX_CB = tOYDZXCB;
	}
	public String getTO_YD_ZX_FB() {
		return TO_YD_ZX_FB;
	}
	public void setTO_YD_ZX_FB(String tOYDZXFB) {
		TO_YD_ZX_FB = tOYDZXFB;
	}
	public String getTO_YD_ZX_MEMO() {
		return TO_YD_ZX_MEMO;
	}
	public void setTO_YD_ZX_MEMO(String tOYDZXMEMO) {
		TO_YD_ZX_MEMO = tOYDZXMEMO;
	}
	public String getTO_YD_ZX_ATTACH() {
		return TO_YD_ZX_ATTACH;
	}
	public void setTO_YD_ZX_ATTACH(String tOYDZXATTACH) {
		TO_YD_ZX_ATTACH = tOYDZXATTACH;
	}
	public String getTO_YD_PW_STATUS() {
		return TO_YD_PW_STATUS;
	}
	public void setTO_YD_PW_STATUS(String tOYDPWSTATUS) {
		TO_YD_PW_STATUS = tOYDPWSTATUS;
	}
	public String getTO_YD_PW_F_STATUS() {
		return TO_YD_PW_F_STATUS;
	}
	public void setTO_YD_PW_F_STATUS(String tOYDPWFSTATUS) {
		TO_YD_PW_F_STATUS = tOYDPWFSTATUS;
	}
	public String getTO_YD_PW_PLAN_START_DATE() {
		return TO_YD_PW_PLAN_START_DATE;
	}
	public void setTO_YD_PW_PLAN_START_DATE(String tOYDPWPLANSTARTDATE) {
		TO_YD_PW_PLAN_START_DATE = tOYDPWPLANSTARTDATE;
	}
	public String getTO_YD_PW_PLAN_END_DATE() {
		return TO_YD_PW_PLAN_END_DATE;
	}
	public void setTO_YD_PW_PLAN_END_DATE(String tOYDPWPLANENDDATE) {
		TO_YD_PW_PLAN_END_DATE = tOYDPWPLANENDDATE;
	}
	public String getTO_YD_PW_REAL_START_DATE() {
		return TO_YD_PW_REAL_START_DATE;
	}
	public void setTO_YD_PW_REAL_START_DATE(String tOYDPWREALSTARTDATE) {
		TO_YD_PW_REAL_START_DATE = tOYDPWREALSTARTDATE;
	}
	public String getTO_YD_PW_REAL_END_DATE() {
		return TO_YD_PW_REAL_END_DATE;
	}
	public void setTO_YD_PW_REAL_END_DATE(String tOYDPWREALENDDATE) {
		TO_YD_PW_REAL_END_DATE = tOYDPWREALENDDATE;
	}
	public String getTO_YD_PW_DELAY() {
		return TO_YD_PW_DELAY;
	}
	public void setTO_YD_PW_DELAY(String tOYDPWDELAY) {
		TO_YD_PW_DELAY = tOYDPWDELAY;
	}
	public String getTO_YD_PW_MODIFY_FLAG() {
		return TO_YD_PW_MODIFY_FLAG;
	}
	public void setTO_YD_PW_MODIFY_FLAG(String tOYDPWMODIFYFLAG) {
		TO_YD_PW_MODIFY_FLAG = tOYDPWMODIFYFLAG;
	}
	public String getTO_YD_PW_CB() {
		return TO_YD_PW_CB;
	}
	public void setTO_YD_PW_CB(String tOYDPWCB) {
		TO_YD_PW_CB = tOYDPWCB;
	}
	public String getTO_YD_PW_FB() {
		return TO_YD_PW_FB;
	}
	public void setTO_YD_PW_FB(String tOYDPWFB) {
		TO_YD_PW_FB = tOYDPWFB;
	}
	public String getTO_YD_PW_MEMO() {
		return TO_YD_PW_MEMO;
	}
	public void setTO_YD_PW_MEMO(String tOYDPWMEMO) {
		TO_YD_PW_MEMO = tOYDPWMEMO;
	}
	public String getTO_YD_PW_ATTACH() {
		return TO_YD_PW_ATTACH;
	}
	public void setTO_YD_PW_ATTACH(String tOYDPWATTACH) {
		TO_YD_PW_ATTACH = tOYDPWATTACH;
	}
	public String getTO_CQ_XK_STATUS() {
		return TO_CQ_XK_STATUS;
	}
	public void setTO_CQ_XK_STATUS(String tOCQXKSTATUS) {
		TO_CQ_XK_STATUS = tOCQXKSTATUS;
	}
	public String getTO_CQ_XK_F_STATUS() {
		return TO_CQ_XK_F_STATUS;
	}
	public void setTO_CQ_XK_F_STATUS(String tOCQXKFSTATUS) {
		TO_CQ_XK_F_STATUS = tOCQXKFSTATUS;
	}
	public String getTO_CQ_XK_PLAN_START_DATE() {
		return TO_CQ_XK_PLAN_START_DATE;
	}
	public void setTO_CQ_XK_PLAN_START_DATE(String tOCQXKPLANSTARTDATE) {
		TO_CQ_XK_PLAN_START_DATE = tOCQXKPLANSTARTDATE;
	}
	public String getTO_CQ_XK_PLAN_END_DATE() {
		return TO_CQ_XK_PLAN_END_DATE;
	}
	public void setTO_CQ_XK_PLAN_END_DATE(String tOCQXKPLANENDDATE) {
		TO_CQ_XK_PLAN_END_DATE = tOCQXKPLANENDDATE;
	}
	public String getTO_CQ_XK_REAL_START_DATE() {
		return TO_CQ_XK_REAL_START_DATE;
	}
	public void setTO_CQ_XK_REAL_START_DATE(String tOCQXKREALSTARTDATE) {
		TO_CQ_XK_REAL_START_DATE = tOCQXKREALSTARTDATE;
	}
	public String getTO_CQ_XK_REAL_END_DATE() {
		return TO_CQ_XK_REAL_END_DATE;
	}
	public void setTO_CQ_XK_REAL_END_DATE(String tOCQXKREALENDDATE) {
		TO_CQ_XK_REAL_END_DATE = tOCQXKREALENDDATE;
	}
	public String getTO_CQ_XK_DELAY() {
		return TO_CQ_XK_DELAY;
	}
	public void setTO_CQ_XK_DELAY(String tOCQXKDELAY) {
		TO_CQ_XK_DELAY = tOCQXKDELAY;
	}
	public String getTO_CQ_XK_MODIFY_FLAG() {
		return TO_CQ_XK_MODIFY_FLAG;
	}
	public void setTO_CQ_XK_MODIFY_FLAG(String tOCQXKMODIFYFLAG) {
		TO_CQ_XK_MODIFY_FLAG = tOCQXKMODIFYFLAG;
	}
	public String getTO_CQ_XK_CB() {
		return TO_CQ_XK_CB;
	}
	public void setTO_CQ_XK_CB(String tOCQXKCB) {
		TO_CQ_XK_CB = tOCQXKCB;
	}
	public String getTO_CQ_XK_FB() {
		return TO_CQ_XK_FB;
	}
	public void setTO_CQ_XK_FB(String tOCQXKFB) {
		TO_CQ_XK_FB = tOCQXKFB;
	}
	public String getTO_CQ_XK_MEMO() {
		return TO_CQ_XK_MEMO;
	}
	public void setTO_CQ_XK_MEMO(String tOCQXKMEMO) {
		TO_CQ_XK_MEMO = tOCQXKMEMO;
	}
	public String getTO_CQ_XK_ATTACH() {
		return TO_CQ_XK_ATTACH;
	}
	public void setTO_CQ_XK_ATTACH(String tOCQXKATTACH) {
		TO_CQ_XK_ATTACH = tOCQXKATTACH;
	}
	public String getTO_HB_JDS_STATUS() {
		return TO_HB_JDS_STATUS;
	}
	public void setTO_HB_JDS_STATUS(String tOHBJDSSTATUS) {
		TO_HB_JDS_STATUS = tOHBJDSSTATUS;
	}
	public String getTO_HB_JDS_F_STATUS() {
		return TO_HB_JDS_F_STATUS;
	}
	public void setTO_HB_JDS_F_STATUS(String tOHBJDSFSTATUS) {
		TO_HB_JDS_F_STATUS = tOHBJDSFSTATUS;
	}
	public String getTO_HB_JDS_PLAN_START_DATE() {
		return TO_HB_JDS_PLAN_START_DATE;
	}
	public void setTO_HB_JDS_PLAN_START_DATE(String tOHBJDSPLANSTARTDATE) {
		TO_HB_JDS_PLAN_START_DATE = tOHBJDSPLANSTARTDATE;
	}
	public String getTO_HB_JDS_PLAN_END_DATE() {
		return TO_HB_JDS_PLAN_END_DATE;
	}
	public void setTO_HB_JDS_PLAN_END_DATE(String tOHBJDSPLANENDDATE) {
		TO_HB_JDS_PLAN_END_DATE = tOHBJDSPLANENDDATE;
	}
	public String getTO_HB_JDS_REAL_START_DATE() {
		return TO_HB_JDS_REAL_START_DATE;
	}
	public void setTO_HB_JDS_REAL_START_DATE(String tOHBJDSREALSTARTDATE) {
		TO_HB_JDS_REAL_START_DATE = tOHBJDSREALSTARTDATE;
	}
	public String getTO_HB_JDS_REAL_END_DATE() {
		return TO_HB_JDS_REAL_END_DATE;
	}
	public void setTO_HB_JDS_REAL_END_DATE(String tOHBJDSREALENDDATE) {
		TO_HB_JDS_REAL_END_DATE = tOHBJDSREALENDDATE;
	}
	public String getTO_HB_JDS_DELAY() {
		return TO_HB_JDS_DELAY;
	}
	public void setTO_HB_JDS_DELAY(String tOHBJDSDELAY) {
		TO_HB_JDS_DELAY = tOHBJDSDELAY;
	}
	public String getTO_HB_JDS_MODIFY_FLAG() {
		return TO_HB_JDS_MODIFY_FLAG;
	}
	public void setTO_HB_JDS_MODIFY_FLAG(String tOHBJDSMODIFYFLAG) {
		TO_HB_JDS_MODIFY_FLAG = tOHBJDSMODIFYFLAG;
	}
	public String getTO_HB_JDS_CB() {
		return TO_HB_JDS_CB;
	}
	public void setTO_HB_JDS_CB(String tOHBJDSCB) {
		TO_HB_JDS_CB = tOHBJDSCB;
	}
	public String getTO_HB_JDS_FB() {
		return TO_HB_JDS_FB;
	}
	public void setTO_HB_JDS_FB(String tOHBJDSFB) {
		TO_HB_JDS_FB = tOHBJDSFB;
	}
	public String getTO_HB_JDS_MEMO() {
		return TO_HB_JDS_MEMO;
	}
	public void setTO_HB_JDS_MEMO(String tOHBJDSMEMO) {
		TO_HB_JDS_MEMO = tOHBJDSMEMO;
	}
	public String getTO_HB_JDS_ATTACH() {
		return TO_HB_JDS_ATTACH;
	}
	public void setTO_HB_JDS_ATTACH(String tOHBJDSATTACH) {
		TO_HB_JDS_ATTACH = tOHBJDSATTACH;
	}
	public String getTO_YD_PZS_STATUS() {
		return TO_YD_PZS_STATUS;
	}
	public void setTO_YD_PZS_STATUS(String tOYDPZSSTATUS) {
		TO_YD_PZS_STATUS = tOYDPZSSTATUS;
	}
	public String getTO_YD_PZS_F_STATUS() {
		return TO_YD_PZS_F_STATUS;
	}
	public void setTO_YD_PZS_F_STATUS(String tOYDPZSFSTATUS) {
		TO_YD_PZS_F_STATUS = tOYDPZSFSTATUS;
	}
	public String getTO_YD_PZS_PLAN_START_DATE() {
		return TO_YD_PZS_PLAN_START_DATE;
	}
	public void setTO_YD_PZS_PLAN_START_DATE(String tOYDPZSPLANSTARTDATE) {
		TO_YD_PZS_PLAN_START_DATE = tOYDPZSPLANSTARTDATE;
	}
	public String getTO_YD_PZS_PLAN_END_DATE() {
		return TO_YD_PZS_PLAN_END_DATE;
	}
	public void setTO_YD_PZS_PLAN_END_DATE(String tOYDPZSPLANENDDATE) {
		TO_YD_PZS_PLAN_END_DATE = tOYDPZSPLANENDDATE;
	}
	public String getTO_YD_PZS_REAL_START_DATE() {
		return TO_YD_PZS_REAL_START_DATE;
	}
	public void setTO_YD_PZS_REAL_START_DATE(String tOYDPZSREALSTARTDATE) {
		TO_YD_PZS_REAL_START_DATE = tOYDPZSREALSTARTDATE;
	}
	public String getTO_YD_PZS_REAL_END_DATE() {
		return TO_YD_PZS_REAL_END_DATE;
	}
	public void setTO_YD_PZS_REAL_END_DATE(String tOYDPZSREALENDDATE) {
		TO_YD_PZS_REAL_END_DATE = tOYDPZSREALENDDATE;
	}
	public String getTO_YD_PZS_DELAY() {
		return TO_YD_PZS_DELAY;
	}
	public void setTO_YD_PZS_DELAY(String tOYDPZSDELAY) {
		TO_YD_PZS_DELAY = tOYDPZSDELAY;
	}
	public String getTO_YD_PZS_MODIFY_FLAG() {
		return TO_YD_PZS_MODIFY_FLAG;
	}
	public void setTO_YD_PZS_MODIFY_FLAG(String tOYDPZSMODIFYFLAG) {
		TO_YD_PZS_MODIFY_FLAG = tOYDPZSMODIFYFLAG;
	}
	public String getTO_YD_PZS_CB() {
		return TO_YD_PZS_CB;
	}
	public void setTO_YD_PZS_CB(String tOYDPZSCB) {
		TO_YD_PZS_CB = tOYDPZSCB;
	}
	public String getTO_YD_PZS_FB() {
		return TO_YD_PZS_FB;
	}
	public void setTO_YD_PZS_FB(String tOYDPZSFB) {
		TO_YD_PZS_FB = tOYDPZSFB;
	}
	public String getTO_YD_PZS_MEMO() {
		return TO_YD_PZS_MEMO;
	}
	public void setTO_YD_PZS_MEMO(String tOYDPZSMEMO) {
		TO_YD_PZS_MEMO = tOYDPZSMEMO;
	}
	public String getTO_YD_PZS_ATTACH() {
		return TO_YD_PZS_ATTACH;
	}
	public void setTO_YD_PZS_ATTACH(String tOYDPZSATTACH) {
		TO_YD_PZS_ATTACH = tOYDPZSATTACH;
	}
	public String getTO_WSYPJ_BG_STATUS() {
		return TO_WSYPJ_BG_STATUS;
	}
	public void setTO_WSYPJ_BG_STATUS(String tOWSYPJBGSTATUS) {
		TO_WSYPJ_BG_STATUS = tOWSYPJBGSTATUS;
	}
	public String getTO_WSYPJ_BG_F_STATUS() {
		return TO_WSYPJ_BG_F_STATUS;
	}
	public void setTO_WSYPJ_BG_F_STATUS(String tOWSYPJBGFSTATUS) {
		TO_WSYPJ_BG_F_STATUS = tOWSYPJBGFSTATUS;
	}
	public String getTO_WSYPJ_BG_PLAN_START_DATE() {
		return TO_WSYPJ_BG_PLAN_START_DATE;
	}
	public void setTO_WSYPJ_BG_PLAN_START_DATE(String tOWSYPJBGPLANSTARTDATE) {
		TO_WSYPJ_BG_PLAN_START_DATE = tOWSYPJBGPLANSTARTDATE;
	}
	public String getTO_WSYPJ_BG_PLAN_END_DATE() {
		return TO_WSYPJ_BG_PLAN_END_DATE;
	}
	public void setTO_WSYPJ_BG_PLAN_END_DATE(String tOWSYPJBGPLANENDDATE) {
		TO_WSYPJ_BG_PLAN_END_DATE = tOWSYPJBGPLANENDDATE;
	}
	public String getTO_WSYPJ_BG_REAL_START_DATE() {
		return TO_WSYPJ_BG_REAL_START_DATE;
	}
	public void setTO_WSYPJ_BG_REAL_START_DATE(String tOWSYPJBGREALSTARTDATE) {
		TO_WSYPJ_BG_REAL_START_DATE = tOWSYPJBGREALSTARTDATE;
	}
	public String getTO_WSYPJ_BG_REAL_END_DATE() {
		return TO_WSYPJ_BG_REAL_END_DATE;
	}
	public void setTO_WSYPJ_BG_REAL_END_DATE(String tOWSYPJBGREALENDDATE) {
		TO_WSYPJ_BG_REAL_END_DATE = tOWSYPJBGREALENDDATE;
	}
	public String getTO_WSYPJ_BG_DELAY() {
		return TO_WSYPJ_BG_DELAY;
	}
	public void setTO_WSYPJ_BG_DELAY(String tOWSYPJBGDELAY) {
		TO_WSYPJ_BG_DELAY = tOWSYPJBGDELAY;
	}
	public String getTO_WSYPJ_BG_MODIFY_FLAG() {
		return TO_WSYPJ_BG_MODIFY_FLAG;
	}
	public void setTO_WSYPJ_BG_MODIFY_FLAG(String tOWSYPJBGMODIFYFLAG) {
		TO_WSYPJ_BG_MODIFY_FLAG = tOWSYPJBGMODIFYFLAG;
	}
	public String getTO_WSYPJ_BG_CB() {
		return TO_WSYPJ_BG_CB;
	}
	public void setTO_WSYPJ_BG_CB(String tOWSYPJBGCB) {
		TO_WSYPJ_BG_CB = tOWSYPJBGCB;
	}
	public String getTO_WSYPJ_BG_FB() {
		return TO_WSYPJ_BG_FB;
	}
	public void setTO_WSYPJ_BG_FB(String tOWSYPJBGFB) {
		TO_WSYPJ_BG_FB = tOWSYPJBGFB;
	}
	public String getTO_WSYPJ_BG_MEMO() {
		return TO_WSYPJ_BG_MEMO;
	}
	public void setTO_WSYPJ_BG_MEMO(String tOWSYPJBGMEMO) {
		TO_WSYPJ_BG_MEMO = tOWSYPJBGMEMO;
	}
	public String getTO_WSYPJ_BG_ATTACH() {
		return TO_WSYPJ_BG_ATTACH;
	}
	public void setTO_WSYPJ_BG_ATTACH(String tOWSYPJBGATTACH) {
		TO_WSYPJ_BG_ATTACH = tOWSYPJBGATTACH;
	}
	public String getTO_WS_CS_STATUS() {
		return TO_WS_CS_STATUS;
	}
	public void setTO_WS_CS_STATUS(String tOWSCSSTATUS) {
		TO_WS_CS_STATUS = tOWSCSSTATUS;
	}
	public String getTO_WS_CS_F_STATUS() {
		return TO_WS_CS_F_STATUS;
	}
	public void setTO_WS_CS_F_STATUS(String tOWSCSFSTATUS) {
		TO_WS_CS_F_STATUS = tOWSCSFSTATUS;
	}
	public String getTO_WS_CS_PLAN_START_DATE() {
		return TO_WS_CS_PLAN_START_DATE;
	}
	public void setTO_WS_CS_PLAN_START_DATE(String tOWSCSPLANSTARTDATE) {
		TO_WS_CS_PLAN_START_DATE = tOWSCSPLANSTARTDATE;
	}
	public String getTO_WS_CS_PLAN_END_DATE() {
		return TO_WS_CS_PLAN_END_DATE;
	}
	public void setTO_WS_CS_PLAN_END_DATE(String tOWSCSPLANENDDATE) {
		TO_WS_CS_PLAN_END_DATE = tOWSCSPLANENDDATE;
	}
	public String getTO_WS_CS_REAL_START_DATE() {
		return TO_WS_CS_REAL_START_DATE;
	}
	public void setTO_WS_CS_REAL_START_DATE(String tOWSCSREALSTARTDATE) {
		TO_WS_CS_REAL_START_DATE = tOWSCSREALSTARTDATE;
	}
	public String getTO_WS_CS_REAL_END_DATE() {
		return TO_WS_CS_REAL_END_DATE;
	}
	public void setTO_WS_CS_REAL_END_DATE(String tOWSCSREALENDDATE) {
		TO_WS_CS_REAL_END_DATE = tOWSCSREALENDDATE;
	}
	public String getTO_WS_CS_DELAY() {
		return TO_WS_CS_DELAY;
	}
	public void setTO_WS_CS_DELAY(String tOWSCSDELAY) {
		TO_WS_CS_DELAY = tOWSCSDELAY;
	}
	public String getTO_WS_CS_MODIFY_FLAG() {
		return TO_WS_CS_MODIFY_FLAG;
	}
	public void setTO_WS_CS_MODIFY_FLAG(String tOWSCSMODIFYFLAG) {
		TO_WS_CS_MODIFY_FLAG = tOWSCSMODIFYFLAG;
	}
	public String getTO_WS_CS_CB() {
		return TO_WS_CS_CB;
	}
	public void setTO_WS_CS_CB(String tOWSCSCB) {
		TO_WS_CS_CB = tOWSCSCB;
	}
	public String getTO_WS_CS_FB() {
		return TO_WS_CS_FB;
	}
	public void setTO_WS_CS_FB(String tOWSCSFB) {
		TO_WS_CS_FB = tOWSCSFB;
	}
	public String getTO_WS_CS_MEMO() {
		return TO_WS_CS_MEMO;
	}
	public void setTO_WS_CS_MEMO(String tOWSCSMEMO) {
		TO_WS_CS_MEMO = tOWSCSMEMO;
	}
	public String getTO_WS_CS_ATTACH() {
		return TO_WS_CS_ATTACH;
	}
	public void setTO_WS_CS_ATTACH(String tOWSCSATTACH) {
		TO_WS_CS_ATTACH = tOWSCSATTACH;
	}
	public String getTO_WS_SGT_STATUS() {
		return TO_WS_SGT_STATUS;
	}
	public void setTO_WS_SGT_STATUS(String tOWSSGTSTATUS) {
		TO_WS_SGT_STATUS = tOWSSGTSTATUS;
	}
	public String getTO_WS_SGT_F_STATUS() {
		return TO_WS_SGT_F_STATUS;
	}
	public void setTO_WS_SGT_F_STATUS(String tOWSSGTFSTATUS) {
		TO_WS_SGT_F_STATUS = tOWSSGTFSTATUS;
	}
	public String getTO_WS_SGT_PLAN_START_DATE() {
		return TO_WS_SGT_PLAN_START_DATE;
	}
	public void setTO_WS_SGT_PLAN_START_DATE(String tOWSSGTPLANSTARTDATE) {
		TO_WS_SGT_PLAN_START_DATE = tOWSSGTPLANSTARTDATE;
	}
	public String getTO_WS_SGT_PLAN_END_DATE() {
		return TO_WS_SGT_PLAN_END_DATE;
	}
	public void setTO_WS_SGT_PLAN_END_DATE(String tOWSSGTPLANENDDATE) {
		TO_WS_SGT_PLAN_END_DATE = tOWSSGTPLANENDDATE;
	}
	public String getTO_WS_SGT_REAL_START_DATE() {
		return TO_WS_SGT_REAL_START_DATE;
	}
	public void setTO_WS_SGT_REAL_START_DATE(String tOWSSGTREALSTARTDATE) {
		TO_WS_SGT_REAL_START_DATE = tOWSSGTREALSTARTDATE;
	}
	public String getTO_WS_SGT_REAL_END_DATE() {
		return TO_WS_SGT_REAL_END_DATE;
	}
	public void setTO_WS_SGT_REAL_END_DATE(String tOWSSGTREALENDDATE) {
		TO_WS_SGT_REAL_END_DATE = tOWSSGTREALENDDATE;
	}
	public String getTO_WS_SGT_DELAY() {
		return TO_WS_SGT_DELAY;
	}
	public void setTO_WS_SGT_DELAY(String tOWSSGTDELAY) {
		TO_WS_SGT_DELAY = tOWSSGTDELAY;
	}
	public String getTO_WS_SGT_MODIFY_FLAG() {
		return TO_WS_SGT_MODIFY_FLAG;
	}
	public void setTO_WS_SGT_MODIFY_FLAG(String tOWSSGTMODIFYFLAG) {
		TO_WS_SGT_MODIFY_FLAG = tOWSSGTMODIFYFLAG;
	}
	public String getTO_WS_SGT_CB() {
		return TO_WS_SGT_CB;
	}
	public void setTO_WS_SGT_CB(String tOWSSGTCB) {
		TO_WS_SGT_CB = tOWSSGTCB;
	}
	public String getTO_WS_SGT_FB() {
		return TO_WS_SGT_FB;
	}
	public void setTO_WS_SGT_FB(String tOWSSGTFB) {
		TO_WS_SGT_FB = tOWSSGTFB;
	}
	public String getTO_WS_SGT_ATTACH() {
		return TO_WS_SGT_ATTACH;
	}
	public void setTO_WS_SGT_ATTACH(String tOWSSGTATTACH) {
		TO_WS_SGT_ATTACH = tOWSSGTATTACH;
	}
	public String getTO_WS_SGT_MEMO() {
		return TO_WS_SGT_MEMO;
	}
	public void setTO_WS_SGT_MEMO(String tOWSSGTMEMO) {
		TO_WS_SGT_MEMO = tOWSSGTMEMO;
	}
	public String getTO_XF_CS_STATUS() {
		return TO_XF_CS_STATUS;
	}
	public void setTO_XF_CS_STATUS(String tOXFCSSTATUS) {
		TO_XF_CS_STATUS = tOXFCSSTATUS;
	}
	public String getTO_XF_CS_F_STATUS() {
		return TO_XF_CS_F_STATUS;
	}
	public void setTO_XF_CS_F_STATUS(String tOXFCSFSTATUS) {
		TO_XF_CS_F_STATUS = tOXFCSFSTATUS;
	}
	public String getTO_XF_CS_PLAN_START_DATE() {
		return TO_XF_CS_PLAN_START_DATE;
	}
	public void setTO_XF_CS_PLAN_START_DATE(String tOXFCSPLANSTARTDATE) {
		TO_XF_CS_PLAN_START_DATE = tOXFCSPLANSTARTDATE;
	}
	public String getTO_XF_CS_PLAN_END_DATE() {
		return TO_XF_CS_PLAN_END_DATE;
	}
	public void setTO_XF_CS_PLAN_END_DATE(String tOXFCSPLANENDDATE) {
		TO_XF_CS_PLAN_END_DATE = tOXFCSPLANENDDATE;
	}
	public String getTO_XF_CS_REAL_START_DATE() {
		return TO_XF_CS_REAL_START_DATE;
	}
	public void setTO_XF_CS_REAL_START_DATE(String tOXFCSREALSTARTDATE) {
		TO_XF_CS_REAL_START_DATE = tOXFCSREALSTARTDATE;
	}
	public String getTO_XF_CS_REAL_END_DATE() {
		return TO_XF_CS_REAL_END_DATE;
	}
	public void setTO_XF_CS_REAL_END_DATE(String tOXFCSREALENDDATE) {
		TO_XF_CS_REAL_END_DATE = tOXFCSREALENDDATE;
	}
	public String getTO_XF_CS_DELAY() {
		return TO_XF_CS_DELAY;
	}
	public void setTO_XF_CS_DELAY(String tOXFCSDELAY) {
		TO_XF_CS_DELAY = tOXFCSDELAY;
	}
	public String getTO_XF_CS_MODIFY_FLAG() {
		return TO_XF_CS_MODIFY_FLAG;
	}
	public void setTO_XF_CS_MODIFY_FLAG(String tOXFCSMODIFYFLAG) {
		TO_XF_CS_MODIFY_FLAG = tOXFCSMODIFYFLAG;
	}
	public String getTO_XF_CS_CB() {
		return TO_XF_CS_CB;
	}
	public void setTO_XF_CS_CB(String tOXFCSCB) {
		TO_XF_CS_CB = tOXFCSCB;
	}
	public String getTO_XF_CS_FB() {
		return TO_XF_CS_FB;
	}
	public void setTO_XF_CS_FB(String tOXFCSFB) {
		TO_XF_CS_FB = tOXFCSFB;
	}
	public String getTO_XF_CS_MEMO() {
		return TO_XF_CS_MEMO;
	}
	public void setTO_XF_CS_MEMO(String tOXFCSMEMO) {
		TO_XF_CS_MEMO = tOXFCSMEMO;
	}
	public String getTO_XF_CS_ATTACH() {
		return TO_XF_CS_ATTACH;
	}
	public void setTO_XF_CS_ATTACH(String tOXFCSATTACH) {
		TO_XF_CS_ATTACH = tOXFCSATTACH;
	}
	public String getTO_XF_SGT_STATUS() {
		return TO_XF_SGT_STATUS;
	}
	public void setTO_XF_SGT_STATUS(String tOXFSGTSTATUS) {
		TO_XF_SGT_STATUS = tOXFSGTSTATUS;
	}
	public String getTO_XF_SGT_F_STATUS() {
		return TO_XF_SGT_F_STATUS;
	}
	public void setTO_XF_SGT_F_STATUS(String tOXFSGTFSTATUS) {
		TO_XF_SGT_F_STATUS = tOXFSGTFSTATUS;
	}
	public String getTO_XF_SGT_PLAN_START_DATE() {
		return TO_XF_SGT_PLAN_START_DATE;
	}
	public void setTO_XF_SGT_PLAN_START_DATE(String tOXFSGTPLANSTARTDATE) {
		TO_XF_SGT_PLAN_START_DATE = tOXFSGTPLANSTARTDATE;
	}
	public String getTO_XF_SGT_PLAN_END_DATE() {
		return TO_XF_SGT_PLAN_END_DATE;
	}
	public void setTO_XF_SGT_PLAN_END_DATE(String tOXFSGTPLANENDDATE) {
		TO_XF_SGT_PLAN_END_DATE = tOXFSGTPLANENDDATE;
	}
	public String getTO_XF_SGT_REAL_START_DATE() {
		return TO_XF_SGT_REAL_START_DATE;
	}
	public void setTO_XF_SGT_REAL_START_DATE(String tOXFSGTREALSTARTDATE) {
		TO_XF_SGT_REAL_START_DATE = tOXFSGTREALSTARTDATE;
	}
	public String getTO_XF_SGT_REAL_END_DATE() {
		return TO_XF_SGT_REAL_END_DATE;
	}
	public void setTO_XF_SGT_REAL_END_DATE(String tOXFSGTREALENDDATE) {
		TO_XF_SGT_REAL_END_DATE = tOXFSGTREALENDDATE;
	}
	public String getTO_XF_SGT_DELAY() {
		return TO_XF_SGT_DELAY;
	}
	public void setTO_XF_SGT_DELAY(String tOXFSGTDELAY) {
		TO_XF_SGT_DELAY = tOXFSGTDELAY;
	}
	public String getTO_XF_SGT_MODIFY_FLAG() {
		return TO_XF_SGT_MODIFY_FLAG;
	}
	public void setTO_XF_SGT_MODIFY_FLAG(String tOXFSGTMODIFYFLAG) {
		TO_XF_SGT_MODIFY_FLAG = tOXFSGTMODIFYFLAG;
	}
	public String getTO_XF_SGT_CB() {
		return TO_XF_SGT_CB;
	}
	public void setTO_XF_SGT_CB(String tOXFSGTCB) {
		TO_XF_SGT_CB = tOXFSGTCB;
	}
	public String getTO_XF_SGT_FB() {
		return TO_XF_SGT_FB;
	}
	public void setTO_XF_SGT_FB(String tOXFSGTFB) {
		TO_XF_SGT_FB = tOXFSGTFB;
	}
	public String getTO_XF_SGT_MEMO() {
		return TO_XF_SGT_MEMO;
	}
	public void setTO_XF_SGT_MEMO(String tOXFSGTMEMO) {
		TO_XF_SGT_MEMO = tOXFSGTMEMO;
	}
	public String getTO_XF_SGT_ATTACH() {
		return TO_XF_SGT_ATTACH;
	}
	public void setTO_XF_SGT_ATTACH(String tOXFSGTATTACH) {
		TO_XF_SGT_ATTACH = tOXFSGTATTACH;
	}
	public String getTO_RF_CS_STATUS() {
		return TO_RF_CS_STATUS;
	}
	public void setTO_RF_CS_STATUS(String tORFCSSTATUS) {
		TO_RF_CS_STATUS = tORFCSSTATUS;
	}
	public String getTO_RF_CS_F_STATUS() {
		return TO_RF_CS_F_STATUS;
	}
	public void setTO_RF_CS_F_STATUS(String tORFCSFSTATUS) {
		TO_RF_CS_F_STATUS = tORFCSFSTATUS;
	}
	public String getTO_RF_CS_PLAN_START_DATE() {
		return TO_RF_CS_PLAN_START_DATE;
	}
	public void setTO_RF_CS_PLAN_START_DATE(String tORFCSPLANSTARTDATE) {
		TO_RF_CS_PLAN_START_DATE = tORFCSPLANSTARTDATE;
	}
	public String getTO_RF_CS_PLAN_END_DATE() {
		return TO_RF_CS_PLAN_END_DATE;
	}
	public void setTO_RF_CS_PLAN_END_DATE(String tORFCSPLANENDDATE) {
		TO_RF_CS_PLAN_END_DATE = tORFCSPLANENDDATE;
	}
	public String getTO_RF_CS_REAL_START_DATE() {
		return TO_RF_CS_REAL_START_DATE;
	}
	public void setTO_RF_CS_REAL_START_DATE(String tORFCSREALSTARTDATE) {
		TO_RF_CS_REAL_START_DATE = tORFCSREALSTARTDATE;
	}
	public String getTO_RF_CS_REAL_END_DATE() {
		return TO_RF_CS_REAL_END_DATE;
	}
	public void setTO_RF_CS_REAL_END_DATE(String tORFCSREALENDDATE) {
		TO_RF_CS_REAL_END_DATE = tORFCSREALENDDATE;
	}
	public String getTO_RF_CS_DELAY() {
		return TO_RF_CS_DELAY;
	}
	public void setTO_RF_CS_DELAY(String tORFCSDELAY) {
		TO_RF_CS_DELAY = tORFCSDELAY;
	}
	public String getTO_RF_CS_MODIFY_FLAG() {
		return TO_RF_CS_MODIFY_FLAG;
	}
	public void setTO_RF_CS_MODIFY_FLAG(String tORFCSMODIFYFLAG) {
		TO_RF_CS_MODIFY_FLAG = tORFCSMODIFYFLAG;
	}
	public String getTO_RF_CS_CB() {
		return TO_RF_CS_CB;
	}
	public void setTO_RF_CS_CB(String tORFCSCB) {
		TO_RF_CS_CB = tORFCSCB;
	}
	public String getTO_RF_CS_FB() {
		return TO_RF_CS_FB;
	}
	public void setTO_RF_CS_FB(String tORFCSFB) {
		TO_RF_CS_FB = tORFCSFB;
	}
	public String getTO_RF_CS_MEMO() {
		return TO_RF_CS_MEMO;
	}
	public void setTO_RF_CS_MEMO(String tORFCSMEMO) {
		TO_RF_CS_MEMO = tORFCSMEMO;
	}
	public String getTO_RF_CS_ATTACH() {
		return TO_RF_CS_ATTACH;
	}
	public void setTO_RF_CS_ATTACH(String tORFCSATTACH) {
		TO_RF_CS_ATTACH = tORFCSATTACH;
	}
	public String getTO_RF_SGT_STATUS() {
		return TO_RF_SGT_STATUS;
	}
	public void setTO_RF_SGT_STATUS(String tORFSGTSTATUS) {
		TO_RF_SGT_STATUS = tORFSGTSTATUS;
	}
	public String getTO_RF_SGT_F_STATUS() {
		return TO_RF_SGT_F_STATUS;
	}
	public void setTO_RF_SGT_F_STATUS(String tORFSGTFSTATUS) {
		TO_RF_SGT_F_STATUS = tORFSGTFSTATUS;
	}
	public String getTO_RF_SGT_PLAN_START_DATE() {
		return TO_RF_SGT_PLAN_START_DATE;
	}
	public void setTO_RF_SGT_PLAN_START_DATE(String tORFSGTPLANSTARTDATE) {
		TO_RF_SGT_PLAN_START_DATE = tORFSGTPLANSTARTDATE;
	}
	public String getTO_RF_SGT_PLAN_END_DATE() {
		return TO_RF_SGT_PLAN_END_DATE;
	}
	public void setTO_RF_SGT_PLAN_END_DATE(String tORFSGTPLANENDDATE) {
		TO_RF_SGT_PLAN_END_DATE = tORFSGTPLANENDDATE;
	}
	public String getTO_RF_SGT_REAL_START_DATE() {
		return TO_RF_SGT_REAL_START_DATE;
	}
	public void setTO_RF_SGT_REAL_START_DATE(String tORFSGTREALSTARTDATE) {
		TO_RF_SGT_REAL_START_DATE = tORFSGTREALSTARTDATE;
	}
	public String getTO_RF_SGT_REAL_END_DATE() {
		return TO_RF_SGT_REAL_END_DATE;
	}
	public void setTO_RF_SGT_REAL_END_DATE(String tORFSGTREALENDDATE) {
		TO_RF_SGT_REAL_END_DATE = tORFSGTREALENDDATE;
	}
	public String getTO_RF_SGT_DELAY() {
		return TO_RF_SGT_DELAY;
	}
	public void setTO_RF_SGT_DELAY(String tORFSGTDELAY) {
		TO_RF_SGT_DELAY = tORFSGTDELAY;
	}
	public String getTO_RF_SGT_MODIFY_FLAG() {
		return TO_RF_SGT_MODIFY_FLAG;
	}
	public void setTO_RF_SGT_MODIFY_FLAG(String tORFSGTMODIFYFLAG) {
		TO_RF_SGT_MODIFY_FLAG = tORFSGTMODIFYFLAG;
	}
	public String getTO_RF_SGT_CB() {
		return TO_RF_SGT_CB;
	}
	public void setTO_RF_SGT_CB(String tORFSGTCB) {
		TO_RF_SGT_CB = tORFSGTCB;
	}
	public String getTO_RF_SGT_FB() {
		return TO_RF_SGT_FB;
	}
	public void setTO_RF_SGT_FB(String tORFSGTFB) {
		TO_RF_SGT_FB = tORFSGTFB;
	}
	public String getTO_RF_SGT_MEMO() {
		return TO_RF_SGT_MEMO;
	}
	public void setTO_RF_SGT_MEMO(String tORFSGTMEMO) {
		TO_RF_SGT_MEMO = tORFSGTMEMO;
	}
	public String getTO_RF_SGT_ATTACH() {
		return TO_RF_SGT_ATTACH;
	}
	public void setTO_RF_SGT_ATTACH(String tORFSGTATTACH) {
		TO_RF_SGT_ATTACH = tORFSGTATTACH;
	}
	public String getTO_JJ_SH_STATUS() {
		return TO_JJ_SH_STATUS;
	}
	public void setTO_JJ_SH_STATUS(String tOJJSHSTATUS) {
		TO_JJ_SH_STATUS = tOJJSHSTATUS;
	}
	public String getTO_JJ_SH_F_STATUS() {
		return TO_JJ_SH_F_STATUS;
	}
	public void setTO_JJ_SH_F_STATUS(String tOJJSHFSTATUS) {
		TO_JJ_SH_F_STATUS = tOJJSHFSTATUS;
	}
	public String getTO_JJ_SH_PLAN_START_DATE() {
		return TO_JJ_SH_PLAN_START_DATE;
	}
	public void setTO_JJ_SH_PLAN_START_DATE(String tOJJSHPLANSTARTDATE) {
		TO_JJ_SH_PLAN_START_DATE = tOJJSHPLANSTARTDATE;
	}
	public String getTO_JJ_SH_PLAN_END_DATE() {
		return TO_JJ_SH_PLAN_END_DATE;
	}
	public void setTO_JJ_SH_PLAN_END_DATE(String tOJJSHPLANENDDATE) {
		TO_JJ_SH_PLAN_END_DATE = tOJJSHPLANENDDATE;
	}
	public String getTO_JJ_SH_REAL_START_DATE() {
		return TO_JJ_SH_REAL_START_DATE;
	}
	public void setTO_JJ_SH_REAL_START_DATE(String tOJJSHREALSTARTDATE) {
		TO_JJ_SH_REAL_START_DATE = tOJJSHREALSTARTDATE;
	}
	public String getTO_JJ_SH_REAL_END_DATE() {
		return TO_JJ_SH_REAL_END_DATE;
	}
	public void setTO_JJ_SH_REAL_END_DATE(String tOJJSHREALENDDATE) {
		TO_JJ_SH_REAL_END_DATE = tOJJSHREALENDDATE;
	}
	public String getTO_JJ_SH_DELAY() {
		return TO_JJ_SH_DELAY;
	}
	public void setTO_JJ_SH_DELAY(String tOJJSHDELAY) {
		TO_JJ_SH_DELAY = tOJJSHDELAY;
	}
	public String getTO_JJ_SH_MODIFY_FLAG() {
		return TO_JJ_SH_MODIFY_FLAG;
	}
	public void setTO_JJ_SH_MODIFY_FLAG(String tOJJSHMODIFYFLAG) {
		TO_JJ_SH_MODIFY_FLAG = tOJJSHMODIFYFLAG;
	}
	public String getTO_JJ_SH_CB() {
		return TO_JJ_SH_CB;
	}
	public void setTO_JJ_SH_CB(String tOJJSHCB) {
		TO_JJ_SH_CB = tOJJSHCB;
	}
	public String getTO_JJ_SH_FB() {
		return TO_JJ_SH_FB;
	}
	public void setTO_JJ_SH_FB(String tOJJSHFB) {
		TO_JJ_SH_FB = tOJJSHFB;
	}
	public String getTO_JJ_SH_MEMO() {
		return TO_JJ_SH_MEMO;
	}
	public void setTO_JJ_SH_MEMO(String tOJJSHMEMO) {
		TO_JJ_SH_MEMO = tOJJSHMEMO;
	}
	public String getTO_JJ_SH_ATTACH() {
		return TO_JJ_SH_ATTACH;
	}
	public void setTO_JJ_SH_ATTACH(String tOJJSHATTACH) {
		TO_JJ_SH_ATTACH = tOJJSHATTACH;
	}
	public String getTO_LH_BQ_STATUS() {
		return TO_LH_BQ_STATUS;
	}
	public void setTO_LH_BQ_STATUS(String tOLHBQSTATUS) {
		TO_LH_BQ_STATUS = tOLHBQSTATUS;
	}
	public String getTO_LH_BQ_F_STATUS() {
		return TO_LH_BQ_F_STATUS;
	}
	public void setTO_LH_BQ_F_STATUS(String tOLHBQFSTATUS) {
		TO_LH_BQ_F_STATUS = tOLHBQFSTATUS;
	}
	public String getTO_LH_BQ_PLAN_START_DATE() {
		return TO_LH_BQ_PLAN_START_DATE;
	}
	public void setTO_LH_BQ_PLAN_START_DATE(String tOLHBQPLANSTARTDATE) {
		TO_LH_BQ_PLAN_START_DATE = tOLHBQPLANSTARTDATE;
	}
	public String getTO_LH_BQ_PLAN_END_DATE() {
		return TO_LH_BQ_PLAN_END_DATE;
	}
	public void setTO_LH_BQ_PLAN_END_DATE(String tOLHBQPLANENDDATE) {
		TO_LH_BQ_PLAN_END_DATE = tOLHBQPLANENDDATE;
	}
	public String getTO_LH_BQ_REAL_START_DATE() {
		return TO_LH_BQ_REAL_START_DATE;
	}
	public void setTO_LH_BQ_REAL_START_DATE(String tOLHBQREALSTARTDATE) {
		TO_LH_BQ_REAL_START_DATE = tOLHBQREALSTARTDATE;
	}
	public String getTO_LH_BQ_REAL_END_DATE() {
		return TO_LH_BQ_REAL_END_DATE;
	}
	public void setTO_LH_BQ_REAL_END_DATE(String tOLHBQREALENDDATE) {
		TO_LH_BQ_REAL_END_DATE = tOLHBQREALENDDATE;
	}
	public String getTO_LH_BQ_DELAY() {
		return TO_LH_BQ_DELAY;
	}
	public void setTO_LH_BQ_DELAY(String tOLHBQDELAY) {
		TO_LH_BQ_DELAY = tOLHBQDELAY;
	}
	public String getTO_LH_BQ_MODIFY_FLAG() {
		return TO_LH_BQ_MODIFY_FLAG;
	}
	public void setTO_LH_BQ_MODIFY_FLAG(String tOLHBQMODIFYFLAG) {
		TO_LH_BQ_MODIFY_FLAG = tOLHBQMODIFYFLAG;
	}
	public String getTO_LH_BQ_CB() {
		return TO_LH_BQ_CB;
	}
	public void setTO_LH_BQ_CB(String tOLHBQCB) {
		TO_LH_BQ_CB = tOLHBQCB;
	}
	public String getTO_LH_BQ_FB() {
		return TO_LH_BQ_FB;
	}
	public void setTO_LH_BQ_FB(String tOLHBQFB) {
		TO_LH_BQ_FB = tOLHBQFB;
	}
	public String getTO_LH_BQ_MEMO() {
		return TO_LH_BQ_MEMO;
	}
	public void setTO_LH_BQ_MEMO(String tOLHBQMEMO) {
		TO_LH_BQ_MEMO = tOLHBQMEMO;
	}
	public String getTO_LH_BQ_ATTACH() {
		return TO_LH_BQ_ATTACH;
	}
	public void setTO_LH_BQ_ATTACH(String tOLHBQATTACH) {
		TO_LH_BQ_ATTACH = tOLHBQATTACH;
	}
	public String getTO_GH_XKZ_STATUS() {
		return TO_GH_XKZ_STATUS;
	}
	public void setTO_GH_XKZ_STATUS(String tOGHXKZSTATUS) {
		TO_GH_XKZ_STATUS = tOGHXKZSTATUS;
	}
	public String getTO_GH_XKZ_F_STATUS() {
		return TO_GH_XKZ_F_STATUS;
	}
	public void setTO_GH_XKZ_F_STATUS(String tOGHXKZFSTATUS) {
		TO_GH_XKZ_F_STATUS = tOGHXKZFSTATUS;
	}
	public String getTO_GH_XKZ_PLAN_START_DATE() {
		return TO_GH_XKZ_PLAN_START_DATE;
	}
	public void setTO_GH_XKZ_PLAN_START_DATE(String tOGHXKZPLANSTARTDATE) {
		TO_GH_XKZ_PLAN_START_DATE = tOGHXKZPLANSTARTDATE;
	}
	public String getTO_GH_XKZ_PLAN_END_DATE() {
		return TO_GH_XKZ_PLAN_END_DATE;
	}
	public void setTO_GH_XKZ_PLAN_END_DATE(String tOGHXKZPLANENDDATE) {
		TO_GH_XKZ_PLAN_END_DATE = tOGHXKZPLANENDDATE;
	}
	public String getTO_GH_XKZ_REAL_START_DATE() {
		return TO_GH_XKZ_REAL_START_DATE;
	}
	public void setTO_GH_XKZ_REAL_START_DATE(String tOGHXKZREALSTARTDATE) {
		TO_GH_XKZ_REAL_START_DATE = tOGHXKZREALSTARTDATE;
	}
	public String getTO_GH_XKZ_REAL_END_DATE() {
		return TO_GH_XKZ_REAL_END_DATE;
	}
	public void setTO_GH_XKZ_REAL_END_DATE(String tOGHXKZREALENDDATE) {
		TO_GH_XKZ_REAL_END_DATE = tOGHXKZREALENDDATE;
	}
	public String getTO_GH_XKZ_DELAY() {
		return TO_GH_XKZ_DELAY;
	}
	public void setTO_GH_XKZ_DELAY(String tOGHXKZDELAY) {
		TO_GH_XKZ_DELAY = tOGHXKZDELAY;
	}
	public String getTO_GH_XKZ_MODIFY_FLAG() {
		return TO_GH_XKZ_MODIFY_FLAG;
	}
	public void setTO_GH_XKZ_MODIFY_FLAG(String tOGHXKZMODIFYFLAG) {
		TO_GH_XKZ_MODIFY_FLAG = tOGHXKZMODIFYFLAG;
	}
	public String getTO_GH_XKZ_CB() {
		return TO_GH_XKZ_CB;
	}
	public void setTO_GH_XKZ_CB(String tOGHXKZCB) {
		TO_GH_XKZ_CB = tOGHXKZCB;
	}
	public String getTO_GH_XKZ_FB() {
		return TO_GH_XKZ_FB;
	}
	public void setTO_GH_XKZ_FB(String tOGHXKZFB) {
		TO_GH_XKZ_FB = tOGHXKZFB;
	}
	public String getTO_GH_XKZ_MEMO() {
		return TO_GH_XKZ_MEMO;
	}
	public void setTO_GH_XKZ_MEMO(String tOGHXKZMEMO) {
		TO_GH_XKZ_MEMO = tOGHXKZMEMO;
	}
	public String getTO_GH_XKZ_ATTACH() {
		return TO_GH_XKZ_ATTACH;
	}
	public void setTO_GH_XKZ_ATTACH(String tOGHXKZATTACH) {
		TO_GH_XKZ_ATTACH = tOGHXKZATTACH;
	}
	public String getTO_SG_XKZ_STATUS() {
		return TO_SG_XKZ_STATUS;
	}
	public void setTO_SG_XKZ_STATUS(String tOSGXKZSTATUS) {
		TO_SG_XKZ_STATUS = tOSGXKZSTATUS;
	}
	public String getTO_SG_XKZ_F_STATUS() {
		return TO_SG_XKZ_F_STATUS;
	}
	public void setTO_SG_XKZ_F_STATUS(String tOSGXKZFSTATUS) {
		TO_SG_XKZ_F_STATUS = tOSGXKZFSTATUS;
	}
	public String getTO_SG_XKZ_PLAN_START_DATE() {
		return TO_SG_XKZ_PLAN_START_DATE;
	}
	public void setTO_SG_XKZ_PLAN_START_DATE(String tOSGXKZPLANSTARTDATE) {
		TO_SG_XKZ_PLAN_START_DATE = tOSGXKZPLANSTARTDATE;
	}
	public String getTO_SG_XKZ_PLAN_END_DATE() {
		return TO_SG_XKZ_PLAN_END_DATE;
	}
	public void setTO_SG_XKZ_PLAN_END_DATE(String tOSGXKZPLANENDDATE) {
		TO_SG_XKZ_PLAN_END_DATE = tOSGXKZPLANENDDATE;
	}
	public String getTO_SG_XKZ_REAL_START_DATE() {
		return TO_SG_XKZ_REAL_START_DATE;
	}
	public void setTO_SG_XKZ_REAL_START_DATE(String tOSGXKZREALSTARTDATE) {
		TO_SG_XKZ_REAL_START_DATE = tOSGXKZREALSTARTDATE;
	}
	public String getTO_SG_XKZ_REAL_END_DATE() {
		return TO_SG_XKZ_REAL_END_DATE;
	}
	public void setTO_SG_XKZ_REAL_END_DATE(String tOSGXKZREALENDDATE) {
		TO_SG_XKZ_REAL_END_DATE = tOSGXKZREALENDDATE;
	}
	public String getTO_SG_XKZ_DELAY() {
		return TO_SG_XKZ_DELAY;
	}
	public void setTO_SG_XKZ_DELAY(String tOSGXKZDELAY) {
		TO_SG_XKZ_DELAY = tOSGXKZDELAY;
	}
	public String getTO_SG_XKZ_MODIFY_FLAG() {
		return TO_SG_XKZ_MODIFY_FLAG;
	}
	public void setTO_SG_XKZ_MODIFY_FLAG(String tOSGXKZMODIFYFLAG) {
		TO_SG_XKZ_MODIFY_FLAG = tOSGXKZMODIFYFLAG;
	}
	public String getTO_SG_XKZ_CB() {
		return TO_SG_XKZ_CB;
	}
	public void setTO_SG_XKZ_CB(String tOSGXKZCB) {
		TO_SG_XKZ_CB = tOSGXKZCB;
	}
	public String getTO_SG_XKZ_FB() {
		return TO_SG_XKZ_FB;
	}
	public void setTO_SG_XKZ_FB(String tOSGXKZFB) {
		TO_SG_XKZ_FB = tOSGXKZFB;
	}
	public String getTO_SG_XKZ_MEMO() {
		return TO_SG_XKZ_MEMO;
	}
	public void setTO_SG_XKZ_MEMO(String tOSGXKZMEMO) {
		TO_SG_XKZ_MEMO = tOSGXKZMEMO;
	}
	public String getTO_SG_XKZ_ATTACH() {
		return TO_SG_XKZ_ATTACH;
	}
	public void setTO_SG_XKZ_ATTACH(String tOSGXKZATTACH) {
		TO_SG_XKZ_ATTACH = tOSGXKZATTACH;
	}
	public String getTO_OPERATE_DATE() {
		return TO_OPERATE_DATE;
	}
	public void setTO_OPERATE_DATE(String tOOPERATEDATE) {
		TO_OPERATE_DATE = tOOPERATEDATE;
	}
	public Integer getTO_NO() {
		return TO_NO;
	}
	public void setTO_NO(Integer tONO) {
		TO_NO = tONO;
	}
	public String getTO_MEMO() {
		return TO_MEMO;
	}
	public void setTO_MEMO(String tOMEMO) {
		TO_MEMO = tOMEMO;
	}
	
	



}