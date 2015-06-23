package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildAcceptClass;
import com.wonders.stpt.qqbz.model.bo.BuildAcceptLatestItem;
import com.wonders.stpt.qqbz.model.bo.BuildAcceptLatestMain;

@XmlRootElement(name="root")
public class BuildAcceptClassList {
	@XmlElement(name="T_BUILD_ACCEPT_CLASS")
    private List<BuildAcceptClass> buildAcceptClassList;
	@XmlElement(name="T_BUILD_ACCEPT_LATEST_ITEM")
	private List<BuildAcceptLatestItem> buildAcceptLatestItemList;
	@XmlElement(name="T_BUILD_ACCEPT_LATEST_MAIN")
	private List<BuildAcceptLatestMain> buildAcceptLatestMainList;
 
    public BuildAcceptClassList(List<BuildAcceptClass> buildAcceptClassList,List<BuildAcceptLatestItem> buildAcceptLatestItemList,List<BuildAcceptLatestMain> buildAcceptLatestMainList){
        this.buildAcceptClassList = buildAcceptClassList;
        this.buildAcceptLatestItemList = buildAcceptLatestItemList;
        this.buildAcceptLatestMainList = buildAcceptLatestMainList;
    }
    
    public BuildAcceptClassList(){
    	
    }
}
