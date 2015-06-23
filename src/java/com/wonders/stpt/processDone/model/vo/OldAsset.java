package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="OldAsset")  
@XmlAccessorType(XmlAccessType.FIELD)
public class OldAsset {
	private String assetInfo;
	private String id;
	private String assetName;
	private String useEndDate;
	private String repairEndDate;
	
	public String getAssetInfo() {
		return assetInfo;
	}
	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getUseEndDate() {
		return useEndDate;
	}
	public void setUseEndDate(String useEndDate) {
		this.useEndDate = useEndDate;
	}
	public String getRepairEndDate() {
		return repairEndDate;
	}
	public void setRepairEndDate(String repairEndDate) {
		this.repairEndDate = repairEndDate;
	}
	
	
}
