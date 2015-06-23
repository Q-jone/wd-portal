package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildMonomer;

@XmlRootElement(name="root")
public class BuildMonomerList {
	@XmlElement(name="T_BUILD_MONOMER")
    private List<BuildMonomer> buildMonomerList;
 
    public BuildMonomerList(List<BuildMonomer> buildMonomerList){
        this.buildMonomerList = buildMonomerList;
    }
    
    public BuildMonomerList(){
    	
    }
}
