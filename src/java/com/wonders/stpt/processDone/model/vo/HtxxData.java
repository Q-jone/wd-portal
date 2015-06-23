package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.JobContact;
import com.wonders.stpt.processDone.model.bo.TDocSend;

@XmlRootElement(name="Datas")  
public class HtxxData {
	@XmlElement(name="BasicData")
	private HtBasicData htBasicData;
	
	@XmlElement(name="AttachFileList")
	private AttachFileList attachFileList;
	
	@XmlElement(name="TApprovedinfoList")
	private TApprovedinfoList tApprovedinfoList;
	
//	@XmlElement(name="AttMain")
//	private AttMain attMain;
//	
	public HtxxData(HtBasicData htBasicData,AttachFileList attachFileList,TApprovedinfoList tApprovedinfoList){
		this.htBasicData = htBasicData;
		this.attachFileList = attachFileList;
		this.tApprovedinfoList = tApprovedinfoList;
//		this.attMain = attMain;
	}
	
	public HtxxData(){
		
	}
}
