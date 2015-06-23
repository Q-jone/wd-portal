package com.wonders.stpt.operation.entity.vo;

import com.wonders.stpt.attach.model.bo.AttachFile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name="attMain")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttMainVo {
    private List<AttachFileVo> attachFileList = new ArrayList<AttachFileVo>();

    public List<AttachFileVo> getAttachFileList() {
        return attachFileList;
    }

    public void setAttachFileList(List<AttachFileVo> attachFileList) {
        this.attachFileList = attachFileList;
    }
}
