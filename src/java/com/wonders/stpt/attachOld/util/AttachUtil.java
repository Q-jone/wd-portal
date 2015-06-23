package com.wonders.stpt.attachOld.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.attach.util.FileUpProperties;
import com.wonders.stpt.attachOld.service.FjshServiceOld;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.RandomGUID;
import com.wonders.stpt.util.StringUtil;

@Repository("attachUtilOld")
public class AttachUtil implements ApplicationContextAware{
	
	private FjshServiceOld fjshService;
	
	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		AttachUtil.context = context;
	}
	
	public static final String STATUS_UPLOAD = "upload";
	public static final String STATUS_DELETE = "delete";
	public static final String STATUS_OVERWRITE = "overwrite";
	
	public static final String APP_MODEL_NAME = FileUpProperties.getValueByKey("app_model_name_workflow");
	
	/**
	 * 服务器存放上传文件的物理路径
	 */
	public static final String UPLOAD_FILE_SERVER_PATH = FileUpProperties.getValueByKey(AttachUtil.APP_MODEL_NAME+".upload_file_path");
	
	public static final String UPLOAD_FILE_EXT_NAME = FileUpProperties.getValueByKey(AttachUtil.APP_MODEL_NAME+".upload_file_ext_name");
	
	public static final String DEFAULT_FILE_IMAGE_NAME = FileUpProperties.getValueByKey("default_file_image_name");
	
	private static final AttachUtil initStance = new AttachUtil();
	
	private int fileNum = 1;
	
	//private int fileGroupNum = 1;
	
	private boolean initFileNumFlag = false;
	
	//private boolean initFileGroupNumFlag = false;
	
	private String currentFileDay = DateUtil.getCurrDate("yyyy-MM-dd");;
	
	//private String currentFileGroupDay = DateUtil.getCurrDate("yyyy-mm-dd");
	
	@SuppressWarnings("unchecked")
	private Map extNameMap;
	
	private boolean initFlag = false;
	
	private synchronized void init(){
		if(!this.initFlag){
			this.initExtNameMap();
			this.initFlag = true;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initExtNameMap(){
		this.extNameMap = new HashMap();
		String extNameStr = FileUpProperties.getValueByKey(AttachUtil.APP_MODEL_NAME+".ext_image");
		String[] extNames = extNameStr.split(",");
		for(int i=0;i<extNames.length;i++){
			if(!StringUtil.isNull(extNames[i])&&!extNames[i].equals(",")){
				this.extNameMap.put(extNames[i], "");
			}
		}
	}
	
	public String getFileImageName(String extName){
		if(!this.initFlag){
			this.init();
		}
		String rStr = AttachUtil.DEFAULT_FILE_IMAGE_NAME;
		if(!StringUtil.isNull(extName)){
			if(this.extNameMap.get(extName)!=null){
				rStr = extName;
			}
		}
		return rStr;
	}
	
	public static AttachUtil getInstance(){
		return initStance;
	}
	
	/**
	 * 取得文件编号，保证唯一
	 * @return
	 */
	public synchronized String getFileCode(){
		String r = "";
		try {
			String nowDay = DateUtil.getCurrDate("yyyy-MM-dd");
			if(!this.initFileNumFlag){	//还未初始化
				this.fileNum = this.getFileNumByDate(nowDay);
				//System.out.println("==================fileNum========================="+this.fileNum);
				this.initFileNumFlag = true;
			}else{
				if(!nowDay.equals(this.currentFileDay)){
					this.fileNum = 0;
				}
			}
			this.currentFileDay = nowDay;
			this.fileNum++;
			StringBuffer strBuf = new StringBuffer(nowDay);
			strBuf.append("-");
			for(int i=0;i<(5-String.valueOf(this.fileNum).length());i++){
				strBuf.append("0");
			}
			strBuf.append(String.valueOf(this.fileNum));
			r = strBuf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 取得文件组编号，保证唯一
	 * @return
	 */
	public synchronized String getFileGroupCode(){
		return (new RandomGUID()).toString();
	}
	
	public String getFileGroupCodeFromDirs(){
		String r = null;
		try {
			String currentDate = DateUtil.getCurrDate("yyyyMMdd");
			String fileGroupCode = this.getFileGroupCode();
			String filePathStr = AttachUtil.UPLOAD_FILE_SERVER_PATH+currentDate+"//"+fileGroupCode+"//";
			File filePath = new File(filePathStr);
			if(!filePath.exists()){	//判断以日期为名的文件夹是否存在
				filePath.mkdirs();
			}
			r = fileGroupCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public int getFileNumByDate(String dateStr){
		int r = 0;
		fjshService = (FjshServiceOld)AttachUtil.context.getBean("fjshServiceOld");
		r = fjshService.getFileNumByDate(dateStr);
		return r;
	}
	
	/**
	 * 将文件从临时文件夹移至正式文件夹，编号更新
	 * @param groupId	文件组编号
	 * @return	返回新的文件组编号
	 */
	public String frushFileByGroupId(String groupId){
		FjshServiceOld fjshService = (FjshServiceOld)AttachUtil.context.getBean("fjshServiceOld");
		return fjshService.flushFileByGroupId(groupId);
	}
		
	public static void main(String[] s){
		//AttachUtil a = AttachUtil.getInstance();
		//int r = a.getMaxFileGroupNum("2008-10-01");
		//System.out.println("r========="+r);
		//String str = a.getFileCode();
		//System.out.println("str========="+str);
		
	}
	
}
