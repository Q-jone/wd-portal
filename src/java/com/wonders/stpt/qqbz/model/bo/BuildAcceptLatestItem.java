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

@XmlRootElement(name="T_BUILD_ACCEPT_LATEST_ITEM")  
@XmlAccessorType(XmlAccessType.FIELD)
public class BuildAcceptLatestItem  implements java.io.Serializable {

	private	String	ALI_ID	;
	private	String	ALM_ID	;
	private	String	ALI_CLSID	;
	private	String	ALI_PLAN_START_DATE	;
	private	String	ALI_PLAN_END_DATE	;
	private	String	ALI_REAL_START_DATE	;
	private	String	ALI_REAL_END_DATE	;
	private	String	ALI_ATTACH	;
	private	String	ALI_STATUS	;
	private	String	ALI_NOTES	;
	private	String	ALI_OVERTIME	;
	private	String	ALI_UPDATED	;
	private	String	ALI_CB	;
	private	String	ALI_FB	;
	private	String	ALI_EXT1	;
	private	String	ALI_EXT2	;
	private	String	ALI_EXT3	;
	public String getALI_ID() {
		return ALI_ID;
	}
	public void setALI_ID(String aLIID) {
		ALI_ID = aLIID;
	}
	public String getALM_ID() {
		return ALM_ID;
	}
	public void setALM_ID(String aLMID) {
		ALM_ID = aLMID;
	}
	public String getALI_CLSID() {
		return ALI_CLSID;
	}
	public void setALI_CLSID(String aLICLSID) {
		ALI_CLSID = aLICLSID;
	}
	public String getALI_PLAN_START_DATE() {
		return ALI_PLAN_START_DATE;
	}
	public void setALI_PLAN_START_DATE(String aLIPLANSTARTDATE) {
		ALI_PLAN_START_DATE = aLIPLANSTARTDATE;
	}
	public String getALI_PLAN_END_DATE() {
		return ALI_PLAN_END_DATE;
	}
	public void setALI_PLAN_END_DATE(String aLIPLANENDDATE) {
		ALI_PLAN_END_DATE = aLIPLANENDDATE;
	}
	public String getALI_REAL_START_DATE() {
		return ALI_REAL_START_DATE;
	}
	public void setALI_REAL_START_DATE(String aLIREALSTARTDATE) {
		ALI_REAL_START_DATE = aLIREALSTARTDATE;
	}
	public String getALI_REAL_END_DATE() {
		return ALI_REAL_END_DATE;
	}
	public void setALI_REAL_END_DATE(String aLIREALENDDATE) {
		ALI_REAL_END_DATE = aLIREALENDDATE;
	}
	public String getALI_ATTACH() {
		return ALI_ATTACH;
	}
	public void setALI_ATTACH(String aLIATTACH) {
		ALI_ATTACH = aLIATTACH;
	}
	public String getALI_STATUS() {
		return ALI_STATUS;
	}
	public void setALI_STATUS(String aLISTATUS) {
		ALI_STATUS = aLISTATUS;
	}
	public String getALI_NOTES() {
		return ALI_NOTES;
	}
	public void setALI_NOTES(String aLINOTES) {
		ALI_NOTES = aLINOTES;
	}
	public String getALI_OVERTIME() {
		return ALI_OVERTIME;
	}
	public void setALI_OVERTIME(String aLIOVERTIME) {
		ALI_OVERTIME = aLIOVERTIME;
	}
	public String getALI_UPDATED() {
		return ALI_UPDATED;
	}
	public void setALI_UPDATED(String aLIUPDATED) {
		ALI_UPDATED = aLIUPDATED;
	}
	public String getALI_CB() {
		return ALI_CB;
	}
	public void setALI_CB(String aLICB) {
		ALI_CB = aLICB;
	}
	public String getALI_FB() {
		return ALI_FB;
	}
	public void setALI_FB(String aLIFB) {
		ALI_FB = aLIFB;
	}
	public String getALI_EXT1() {
		return ALI_EXT1;
	}
	public void setALI_EXT1(String aLIEXT1) {
		ALI_EXT1 = aLIEXT1;
	}
	public String getALI_EXT2() {
		return ALI_EXT2;
	}
	public void setALI_EXT2(String aLIEXT2) {
		ALI_EXT2 = aLIEXT2;
	}
	public String getALI_EXT3() {
		return ALI_EXT3;
	}
	public void setALI_EXT3(String aLIEXT3) {
		ALI_EXT3 = aLIEXT3;
	}

	

}