package com.wonders.stpt.processDone.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewAssetList")
public class NewAssetList {
	@XmlElement(name="NewAsset")
    private List<NewAsset> AssetList;
 
    public NewAssetList(List<NewAsset> AssetList){
        this.AssetList = AssetList;
    }
    
    public NewAssetList(){
    	
    }
}
