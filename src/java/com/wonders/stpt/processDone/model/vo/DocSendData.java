package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.TDocSend;

@XmlRootElement(name="Datas")  
public class DocSendData {
	@XmlElement(name="BasicData")
	private TDocSend tDocSend;
	
	@XmlElement(name="AttachFileList")
	private AttachFileList attachFileList;
	
	@XmlElement(name="TApprovedinfoList")
	private TApprovedinfoList tApprovedinfoList;
	
//	@XmlElement(name="AttMain")
//	private AttMain attMain;
//	
	public DocSendData(TDocSend tDocSend,AttachFileList attachFileList,TApprovedinfoList tApprovedinfoList){
		this.tDocSend = tDocSend;
		this.attachFileList = attachFileList;
		this.tApprovedinfoList = tApprovedinfoList;
//		this.attMain = attMain;
	}
	
	public DocSendData(){
		
	}
}
