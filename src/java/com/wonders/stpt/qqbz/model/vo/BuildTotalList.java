package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildTotal;

@XmlRootElement(name="root")
public class BuildTotalList {
	@XmlElement(name="T_BUILD_TOTAL")
    private List<BuildTotal> buildTotalList;
 
    public BuildTotalList(List<BuildTotal> buildTotalList){
        this.buildTotalList = buildTotalList;
    }
    
    public BuildTotalList(){
    	
    }
}
