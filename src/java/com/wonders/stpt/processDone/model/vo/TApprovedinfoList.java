package com.wonders.stpt.processDone.model.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wonders.stpt.processDone.model.bo.TApprovedinfo;
import com.wonders.stpt.processDone.model.vo.ApprovedinfoXml;

@XmlRootElement(name="TApprovedinfoList")
public class TApprovedinfoList {
	@XmlElement(name="TApprovedinfo")
    private List<ApprovedinfoXml> tApprovedinfoList;
 
    public TApprovedinfoList(List<ApprovedinfoXml> tApprovedinfoList){
        this.tApprovedinfoList = tApprovedinfoList;
    }
    
    public TApprovedinfoList(){
    	
    }
}
