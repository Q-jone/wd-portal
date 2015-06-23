package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.JobContact;
import com.wonders.stpt.processDone.model.bo.TDocSend;

@XmlRootElement(name="Datas")  
public class ProjectData {
	@XmlElement(name="BasicData")
	private ProjectVo projectVo;
	
	@XmlElement(name="AttachFileList")
	private AttachFileList attachFileList;
	
	@XmlElement(name="TApprovedinfoList")
	private TApprovedinfoList tApprovedinfoList;
	
	@XmlElement(name="Assets")
	private Assets assets;
	
	public ProjectData(ProjectVo projectVo,AttachFileList attachFileList,TApprovedinfoList tApprovedinfoList,Assets assets){
		this.projectVo = projectVo;
		this.attachFileList = attachFileList;
		this.tApprovedinfoList = tApprovedinfoList;
		this.assets = assets;
	}
	
	public ProjectData(){
		
	}
}
