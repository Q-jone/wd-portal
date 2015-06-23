package com.wonders.stpt.operation.entity.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/13
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name="attachFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttachFileVo {
    private String fileName;
    private String fileExtName;
    private String path;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtName() {
        return fileExtName;
    }

    public void setFileExtName(String fileExtName) {
        this.fileExtName = fileExtName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
