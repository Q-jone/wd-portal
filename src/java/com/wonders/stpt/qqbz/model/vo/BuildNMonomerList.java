package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildNMonomer;

@XmlRootElement(name="root")
public class BuildNMonomerList {
	@XmlElement(name="T_BUILD_N_MONOMER")
    private List<BuildNMonomer> buildNMonomerList;
 
    public BuildNMonomerList(List<BuildNMonomer> buildNMonomerList){
        this.buildNMonomerList = buildNMonomerList;
    }
    
    public BuildNMonomerList(){
    	
    }
}
