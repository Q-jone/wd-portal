package com.wonders.stpt.attachOld.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wonders.stpt.attachOld.model.vo.UploadFile;
import com.wonders.stpt.attachOld.service.FjshServiceOld;
import com.wonders.stpt.attachOld.util.FileUpProperties;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.userMsg.action.AbstractParamAction;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;


@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/attachOld")
@Component("fjshOldAction")
@Scope("prototype")
public class FjshOldAction extends AbstractParamAction{
	private static final long fileSize = Long.parseLong(FileUpProperties.getValueByKey("fileSize"));
	private FjshServiceOld fjshService;
	private String attachMemo1;

	String AJAX = null;
	
/*	@Action(value="/addFjsh",results={@Result(name="fjsh",location="/attach/fjsh.jsp")})
	public String addFjsh() {
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);
		//HttpServletRequest servletRequest = servletRequest;
		String fileGroup = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroup"));
		String fileGroupId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupId"));
		servletRequest.setAttribute("fileGroup", fileGroup);
		servletRequest.setAttribute("fileGroupId", fileGroupId);
		
		return "fjsh";
	}*/

	/*
	 * 文件下载
	 * 2009-11-27 zzg 修改为直接从action输出文件数据流，不转入jsp
	 */
	@Action(value="/downloadOldFile",results={@Result(name="downloadFile",location="/attach/downloadFile.jsp")})
	public String downloadOldFile() throws UnsupportedEncodingException{
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);

		String fileId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileId"));
		UploadFile upFile = this.fjshService.loadFileById(fileId);
		String path = upFile.getPath();					// 文件所在磁盘路径

		String fileName = upFile.getFileAllName();		// 真实文件名

		String saveFileName = upFile.getSaveFileName();	// 磁盘上的文件名

		String version = upFile.getVersion();

		if ("old".equals(StringUtil.getNotNullValueString(servletRequest.getParameter("ver")))){
			if (version != null){
				saveFileName = saveFileName.replace(".dat","_v"+version+".dat");
			}
		}

		servletResponse.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		servletResponse.setContentType("application/octet-stream");
		try {
			InputStream is = new FileInputStream(path + saveFileName);
			OutputStream os = servletResponse.getOutputStream();
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = is.read(buffer, 0, 1024)) > 0) {
				os.write(buffer, 0, n);
			}
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
/*	@SuppressWarnings("unchecked")
	@Action(value="/loadFiles")
	public String loadFiles() {
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);
		String fileGroupId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupId"));
		@SuppressWarnings("unused")
		List list = new ArrayList();
		if(!StringUtil.isNull(fileGroupId)){
			list = this.fjshService.loadFilesByGroupId(fileGroupId);
		}
		return AJAX;
	}*/
	
	/**
	 * 适用jsp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Action(value="/loadFileOldList",results={@Result(name="attachList",location="/attachOld/attachList.jsp")})
	public String loadFileOldList(){
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);

		String fileGroupId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupId"));
		List list = new ArrayList();
		if(!StringUtil.isNull(fileGroupId)){
			list = this.fjshService.loadFilesByGroupId(fileGroupId);
		}
		servletRequest.setAttribute("attachList", list);
		return "attachList";
	}
	
	@SuppressWarnings("unchecked")
	@Action(value="/loadVersionOldFilesList",results={@Result(name="versionFileList",location="/attachOld/versionFileList.jsp")})
	public String loadVersionOldFilesList(){
		String groupId = StringUtil.getNotNullValueString(servletRequest.getParameter("groupId"));
		String fileId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileId"));
		List fileList = this.fjshService.findVersionFilesByGroupId(groupId, fileId);
		servletRequest.setAttribute("fileList", fileList);
		return "versionFileList";
	}
	
/*	@Action(value="/deleteFiles")
	public String deleteFiles(){
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);
		String[] deleteData = (String[]) servletRequest.getParameterValues("deleteData");
		if (deleteData != null) {
			JSONArray array = JSONArray.fromObject("[" + deleteData[0] + "]");
			for (int i = 0; i < array.size(); i++) {
				Object obj = array.get(i);
				try{
					Integer id = (Integer) PropertyUtils.getProperty(obj, "id");
					this.fjshService.deleteFileById(String.valueOf(id));
				}catch(Exception e){
					e.printStackTrace();
					createJSonData("{\"success\":false}");
				}
			}
			createJSonData("{\"success\":true}");
		}else {
			createJSonData("{\"success\":false}");
		}
		return AJAX;
	}*/
	
	@Action(value="/deleteFilesOldJsp",results={@Result(name="delFileRes",location="/attachOld/upFileRes.jsp")})
	public String deleteFilesOldJsp(){
		String attachMemo = StringUtil.getNotNullValueString(servletRequest.getParameter("attachMemo1"));
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);
		String[] deleteData = servletRequest.getParameterValues("deleteData");
		if(deleteData==null){
			String delId = servletRequest.getParameter("deleteData");
			if(!StringUtil.isNull(delId)){
				deleteData = new String[1];
				deleteData[0] = delId;
			}
		}
		if (deleteData != null) {
			for (int i = 0; i < deleteData.length; i++) {
				try{
					this.fjshService.deleteFileById(deleteData[i]);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		servletRequest.setAttribute("attachMemo1", attachMemo);
		return "delFileRes";
	}

/*	@Action(value="/upFile")
	public String upFile() {
		//System.out.println("==========FjshAction upFile==================");
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);
		MultiPartRequestWrapper multi = (MultiPartRequestWrapper) servletRequest;
		
		String fileGroupId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupId"));
		String fileGroupName = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupName"));
		String uploader = StringUtil.getNotNullValueString(servletRequest.getSession().getAttribute("user_name"));
		String uploaderLoginName = StringUtil.getNotNullValueString(servletRequest.getSession().getAttribute("login_name"));
		String memo = StringUtil.getNotNullValueString(servletRequest.getParameter("attachMemo"));
		//System.out.println("fileGroupId========"+fileGroupId+"|fileGroupName==="+fileGroupName+"|uploader==="+uploader);
		
		//String uploader = "attila";
		String uploadTime = DateUtil.getNowTime();
		String newGroupId = "";
		if (multi.getFiles(fileGroupName) != null&& multi.getFiles(fileGroupName).length > 0) {
			if(StringUtil.isNull(fileGroupId)||fileGroupId.trim().equalsIgnoreCase("undefined")){	//如果取不到文件组编号，说明是首次上传
				System.out.println("fileGroupId is null");
				newGroupId = this.fjshService.uploadNewFiles(null,multi.getFiles(fileGroupName),multi.getFileNames(fileGroupName),uploader,uploaderLoginName,uploadTime,memo);
			}else{	//上传文件至原有目录，并且同名覆盖
				System.out.println("fileGroupId is not null");
				this.fjshService.uploadOverrideFiles(fileGroupId,multi.getFiles(fileGroupName),multi.getFileNames(fileGroupName),uploader,uploaderLoginName,uploadTime,memo);
				newGroupId = fileGroupId;
			}
		}else{
			//System.out.println("===no file======");
		}
		//System.out.println("newGroupId========="+newGroupId);
		try {
			servletResponse.getWriter().print("{success:true, msg:true,newGroupId:'"+newGroupId+"'}");// Possible!
			//createJSonData("{\"success\":true,\"msg\":true,\"newGroupId\":" + newGroupId + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AJAX;
	}*/
	
	@Action(value="/upFileOldJsp",results={@Result(name = "upFileRes", location="/attachOld/upFileRes.jsp")})
	public String upFileOldJsp(){
		//System.out.println("===================upFileJsp===========================");
		servletRequest = ServletActionContext.getRequest();
		this.procParams(servletRequest);
		MultiPartRequestWrapper multi = (MultiPartRequestWrapper) servletRequest;
		
		String fileGroupId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupId"));
		String fileGroupName = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupName"));
		String uploader = StringUtil.getNotNullValueString(servletRequest.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME));
		String uploaderLoginName = StringUtil.getNotNullValueString(servletRequest.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME));
		
		String memo = StringUtil.getNotNullValueString(servletRequest.getParameter("attachMemo"));
		String attachMemo1 = StringUtil.getNotNullValueString(servletRequest.getParameter("attachMemo1"));
		
		if(multi.getFiles(fileGroupName) != null && multi.getFiles(fileGroupName).length > 0){
			File src = multi.getFiles(fileGroupName)[0];
			long size = src.length();
			if(size > fileSize){
				servletRequest.setAttribute("error", "1");
				return "upFileRes";
			}
		}

		String uploadTime = DateUtil.getNowTime();
		String newGroupId = "";
		if (multi.getFiles(fileGroupName) != null&& multi.getFiles(fileGroupName).length > 0) {
			if(StringUtil.isNull(fileGroupId)||fileGroupId.trim().equalsIgnoreCase("undefined")){	//如果取不到文件组编号，说明是首次上传
				newGroupId = this.fjshService.uploadNewFiles(null,multi.getFiles(fileGroupName),multi.getFileNames(fileGroupName),uploader,uploaderLoginName,uploadTime,memo);
			}else{	//上传文件至原有目录，并且同名覆盖
				//System.out.println("fileGroupId is not null");
				this.fjshService.uploadOverrideFiles(fileGroupId,multi.getFiles(fileGroupName),multi.getFileNames(fileGroupName),uploader,uploaderLoginName,uploadTime,memo);
				newGroupId = fileGroupId;
			}
		}else{
			//System.out.println("===no file======");
		}
		servletRequest.setAttribute("newGroupId", newGroupId);
		setAttachMemo1(attachMemo1);
		return "upFileRes";
	}
	
	public String copyLocalFiles(){
		String type = servletRequest.getParameter("type");
		Boolean isNewestVerson = null;
		if(type != null && "1".equals(type)){
			isNewestVerson = true;
		}else{
			isNewestVerson = false;
		}
		String fileGroupId = StringUtil.getNotNullValueString(servletRequest.getParameter("fileGroupId"));
		String uploader = StringUtil.getNotNullValueString(servletRequest.getSession().getAttribute("user_name"));
		String uploaderLoginName = StringUtil.getNotNullValueString(servletRequest.getSession().getAttribute("login_name"));
		String filegroupId = fjshService.copyLocalFiles(fileGroupId, isNewestVerson, uploaderLoginName, uploader);
		servletRequest.setAttribute("filegroupId", filegroupId);
		return FjshOldAction.SUCCESS;
	}
	
	private void procParams(HttpServletRequest servletRequest){
		String procType = StringUtil.getNotNullValueString(servletRequest.getParameter("procType"));
		if(!StringUtil.isNull(procType)){
			servletRequest.setAttribute("procType", procType);
		}
		//System.out.println("fj procParams procType===="+procType);
		String targetType = StringUtil.getNotNullValueString(servletRequest.getParameter("targetType"));
		if(!StringUtil.isNull(targetType)){
			servletRequest.setAttribute("targetType", targetType);
		}
		//System.out.println("fj procParams targetType===="+targetType);
	}

	private void createJSonData(String json){
		Writer w = null;
		try{
			w = servletResponse.getWriter();
			w.write(json);
		}catch(Exception e){
			
		}finally{
			try {
				w.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getAttachMemo1() {
		return attachMemo1;
	}

	public void setAttachMemo1(String attachMemo1) {
		this.attachMemo1 = attachMemo1;
	}
	
	public FjshServiceOld getFjshServiceOld() {
		return fjshService;
	}
	
	@Autowired(required=false)
	public void setFjshServiceOld(@Qualifier("fjshServiceOld")FjshServiceOld fjshService) {
		this.fjshService = fjshService;
	}

}
