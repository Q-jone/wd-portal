package com.wonders.stpt.processDone.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="OldAssetList")
public class OldAssetList {
	@XmlElement(name="OldAsset")
    private List<OldAsset> AssetList;
 
    public OldAssetList(List<OldAsset> AssetList){
        this.AssetList = AssetList;
    }
    
    public OldAssetList(){
    	
    }
}
