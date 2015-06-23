package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.DocReceiveBo;
import com.wonders.stpt.processDone.model.bo.TDocSend;

@XmlRootElement(name="Datas")  
public class DocReceiveData {
	@XmlElement(name="BasicData")
	private DocReceiveBo docReceiveBo;
	
	@XmlElement(name="AttachFileList")
	private AttachFileList attachFileList;
	
	@XmlElement(name="TApprovedinfoList")
	private TApprovedinfoList tApprovedinfoList;
	
//	@XmlElement(name="AttMain")
//	private AttMain attMain;
//	
	public DocReceiveData(DocReceiveBo docReceiveBo,AttachFileList attachFileList,TApprovedinfoList tApprovedinfoList){
		this.docReceiveBo = docReceiveBo;
		this.attachFileList = attachFileList;
		this.tApprovedinfoList = tApprovedinfoList;
//		this.attMain = attMain;
	}
	
	public DocReceiveData(){
		
	}
}
