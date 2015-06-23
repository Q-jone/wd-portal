package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Assets")  
public class Assets {
	@XmlElement(name="OldAssetList")
	private OldAssetList oldAssetList;
	
	@XmlElement(name="NewAssetList")
	private NewAssetList newAssetList;
	
	public Assets(OldAssetList oldAssetList,NewAssetList newAssetList){
		this.oldAssetList = oldAssetList;
		this.newAssetList = newAssetList;
	}
	
	public Assets(){
		
	}
}
