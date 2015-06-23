package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildDcqTotal;

@XmlRootElement(name="root")
public class BuildDcqTotalList {
	@XmlElement(name="T_BUILD_DCQ_TOTAL")
    private List<BuildDcqTotal> buildDcqTotalList;
 
    public BuildDcqTotalList(List<BuildDcqTotal> buildDcqTotalList){
        this.buildDcqTotalList = buildDcqTotalList;
    }
    
    public BuildDcqTotalList(){
    	
    }
}
