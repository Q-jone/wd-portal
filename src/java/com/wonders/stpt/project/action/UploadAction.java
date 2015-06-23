package com.wonders.stpt.project.action;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.project.service.IWorkEventService;
import com.wonders.stpt.project.service.RiskManageService;
import com.wonders.stpt.project.service.WorkInspectService;
import com.wonders.stpt.project.service.WorkSecurityService;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/26.
 */

@ParentPackage("struts-default")
@Namespace(value = "/project")
@Controller("uploadAction")
@Scope("prototype")
public class UploadAction implements SessionAware {
    @Autowired
    private RiskManageService riskManageService;
    @Autowired
    private WorkSecurityService workSecurityService;
    @Autowired
    private WorkInspectService workInspectService;
    private final Logger logger = Logger.getLogger(UploadAction.class);

    private File upload; // 上传的文件
    private String uploadFileName; // 文件名称
    private String uploadContentType; // 文件类型
    private Map<String, Object> session;
    private String type;
    private List<String> result;
    @Autowired
    private IWorkEventService workEventService;
    @org.apache.struts2.convention.annotation.Action(value = "import", interceptorRefs = {
            @InterceptorRef(value = "fileUploadStack", params = {"allowedTypes", "application/vnd.ms-excel", "maximumSize", "20971520"})}, results = {
            @Result(name = "security", location = "security/report.action", type = "redirectAction"),
            @Result(name = "inspect", location = "inspect/report.action", type = "redirectAction"),
            @Result(name = "error", location = "/project/error.jsp"),
            @Result(name = "risk", location = "risk/report.action", type = "redirectAction"),
            @Result(name="event",location="event/report.action",type = "redirectAction")
    })
    public String imports() throws Exception {
        String forward = "";
        if ("2".equals(type)) {
           result =workInspectService.imports(upload, (String) session.get("loginName"));
            if (result == null || result.size() == 0)
                forward = "inspect";
            else
                forward = "error";
        } else if ("3".equals(type)) {
            result = riskManageService.imports(upload, (String) session.get("loginName"));

            if (result == null || result.size() == 0)
                forward = "risk";
            else
                forward = "error";
        } else if("4".equals(type)){//事件上传数据
        	result=workEventService.imports(upload, (String) session.get("loginName"));
        	if (result == null || result.size() == 0)
                forward = "event";
            else
                forward = "error";
        }else {
             result = workSecurityService.imports(upload, (String) session.get("loginName"));
            if (result == null || result.size() == 0)
                forward = "security";
            else
                forward = "error";
        }
        return forward;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
