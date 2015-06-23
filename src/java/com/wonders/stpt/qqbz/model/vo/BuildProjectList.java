package com.wonders.stpt.qqbz.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.qqbz.model.bo.BuildProject;

@XmlRootElement(name="root")
public class BuildProjectList {
	@XmlElement(name="T_BUILD_PROJECT")
    private List<BuildProject> buildProjectList;
 
    public BuildProjectList(List<BuildProject> buildProjectList){
        this.buildProjectList = buildProjectList;
    }
    
    public BuildProjectList(){
    	
    }
}
