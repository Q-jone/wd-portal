package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.ReDirective;

@XmlRootElement(name="Datas")  
public class ReDirectiveData {
	@XmlElement(name="BasicData")
	private ReDirective reDirective;
	
	@XmlElement(name="AttachFileList")
	private AttachFileList attachFileList;
	
	@XmlElement(name="TApprovedinfoList")
	private TApprovedinfoList tApprovedinfoList;
	
//	@XmlElement(name="AttMain")
//	private AttMain attMain;
//	
	public ReDirectiveData(ReDirective reDirective,AttachFileList attachFileList,TApprovedinfoList tApprovedinfoList){
		this.reDirective = reDirective;
		this.attachFileList = attachFileList;
		this.tApprovedinfoList = tApprovedinfoList;
//		this.attMain = attMain;
	}
	
	public ReDirectiveData(){
		
	}
}
