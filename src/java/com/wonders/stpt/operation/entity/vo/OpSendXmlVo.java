package com.wonders.stpt.operation.entity.vo;


import com.wonders.stpt.processDone.model.vo.AttMain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/13
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name="BasicData")
@XmlAccessorType(XmlAccessType.FIELD)
public class OpSendXmlVo implements Serializable{
    private String id;
    private String fileType;
    private String fileCode;
    private String docTitle;
    private String sendMainW;
    private String sendOutW;
    private String sendLineW;


    private String applyDate;
    private String pubDate;
    private String validDate;
    private String passDate;
    private AttMainVo attMain;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getSendMainW() {
        return sendMainW;
    }

    public void setSendMainW(String sendMainW) {
        this.sendMainW = sendMainW;
    }

    public String getSendOutW() {
        return sendOutW;
    }

    public void setSendOutW(String sendOutW) {
        this.sendOutW = sendOutW;
    }

    public String getSendLineW() {
        return sendLineW;
    }

    public void setSendLineW(String sendLineW) {
        this.sendLineW = sendLineW;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getPassDate() {
        return passDate;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    public AttMainVo getAttMain() {
        return attMain;
    }

    public void setAttMain(AttMainVo attMain) {
        this.attMain = attMain;
    }



}
