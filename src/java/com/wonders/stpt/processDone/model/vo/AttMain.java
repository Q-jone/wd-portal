package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="AttMain")  
public class AttMain {
	@XmlElement(name="AttachFileList")
	private AttachFileList list;
	
	public AttMain(AttachFileList list){
		this.list = list;
	}
	
	public AttMain(){
		
	}
}
