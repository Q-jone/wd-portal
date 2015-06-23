package com.wonders.stpt.processDone.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.AttachFile2;

@XmlRootElement(name="AttachFileList")
public class AttachFileList {
	@XmlElement(name="AttachFile")
    private List<AttachFile2> attachFileList;
 
    public AttachFileList(List<AttachFile2> attachFileList){
        this.attachFileList = attachFileList;
    }
    
    public AttachFileList(){
    	
    }
}
