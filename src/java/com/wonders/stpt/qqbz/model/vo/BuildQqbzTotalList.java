package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildQqbzTotal;

@XmlRootElement(name="root")
public class BuildQqbzTotalList {
	@XmlElement(name="T_BUILD_QQBZ_TOTAL")
    private List<BuildQqbzTotal> buildQqbzTotalList;
 
    public BuildQqbzTotalList(List<BuildQqbzTotal> buildQqbzTotalList){
        this.buildQqbzTotalList = buildQqbzTotalList;
    }
    
    public BuildQqbzTotalList(){
    	
    }
}
