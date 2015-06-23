package com.wonders.stpt.attachOld.dao.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.attachOld.dao.FjshDaoOld;
import com.wonders.stpt.attachOld.model.bo.AttachFileOld;
import com.wonders.stpt.attachOld.util.AttachUtil;
import com.wonders.stpt.util.DbUtil;
import com.wonders.stpt.util.StringUtil;


@Repository("fjshDaoOld")
public class FjshDaoOldImpl extends HibernateDaoSupport implements FjshDaoOld {
	private DbUtil dbUtil;
	public  DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	//第一种方法 继承 daosupport 注入session
	@Autowired(required=false)
    public void setSessionFactory0(@Qualifier(value="sessionFactory2")SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	@SuppressWarnings("unchecked")
	public List findByHQL(String hql, Object... params) {
		List list = new ArrayList();
		if(params!=null&&params.length>0){
			list = this.getHibernateTemplate().find(hql,params);
		}else{
			list = this.getHibernateTemplate().find(hql);
		}
		return list;
	}
	
	
	public String getCurrentFileVersion(String groupId,String fileAllName){
		String r = null;
		try{
			if(!StringUtil.isNull(fileAllName)&&fileAllName.indexOf(".")>=0){
				String fileName = fileAllName.substring(0,fileAllName.indexOf("."));
				String fileExtName = fileAllName.substring(fileAllName.indexOf(".")+1,fileAllName.length());
				
				String sql = "select max(to_number(t.version)) from t_attach t where t.appname=? and t.filename=? and t.fileextname=? and t.groupid=? and t.status != ?";
				
				int count = dbUtil.getJdbcTemplate().queryForInt(sql, new Object[]{AttachUtil.APP_MODEL_NAME,fileName,fileExtName,groupId,AttachUtil.STATUS_DELETE});
				r = String.valueOf(count);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return r;
	}
	
	public void versionLocalFile(String filePathStr,String fileAllName,String version){
		File filePath = new File(filePathStr);
		if(filePath.isDirectory()&&filePath.exists()){
			File file = new File(filePathStr+"//"+fileAllName);
			if(file.exists()){
				String fileName = fileAllName.substring(0,fileAllName.indexOf("."));
				String fileExtName = fileAllName.substring(fileAllName.indexOf(".")+1,fileAllName.length());
				String newFileName = fileName;
				if(fileName.indexOf("_")>=0){
					newFileName = fileName.substring(0,fileName.indexOf("_"));
				}
				newFileName = newFileName+"_v"+version;
				File destFile = new File(filePathStr+"\\"+newFileName+"."+fileExtName);
				file.renameTo(destFile);
			}
		}
	}
	
	/*
	 * versionLocalFile的反向功能

	 */
	public void versionLocalFileRestore(String filePathStr,String fileAllName,String version){
		File filePath = new File(filePathStr);
		if(filePath.isDirectory() && filePath.exists()) {
			String fileName = fileAllName.substring(0,fileAllName.indexOf("."));
			String fileExtName = fileAllName.substring(fileAllName.indexOf(".")+1,fileAllName.length());
			if(fileName.indexOf("_") < 0){
				fileName = fileName + "_v" + version;
			}
			fileAllName = fileName + "." + fileExtName;
			File file = new File(filePathStr + "\\" + fileAllName);
			if(file.exists()){
				fileName = fileName.substring(0,fileName.indexOf("_"));
				File destFile = new File(filePathStr+"\\"+fileName+"."+fileExtName);
				file.renameTo(destFile);
			}
		}
	}

	public void saveFileToLocal(File sourceFile,String destFilePathStr,String destFileName){
		try {
			File destFilePath = new File(destFilePathStr);
			if(!destFilePath.exists()){
				destFilePath.mkdirs();
				destFilePath = null;
			}
			File destFile = new File(destFilePathStr+"//"+destFileName);
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			DataInputStream in = new DataInputStream(new FileInputStream(sourceFile));
			DataOutputStream out = new DataOutputStream(new FileOutputStream(destFile));
			byte[] c = new byte[(int) sourceFile.length()];
			in.read(c);
			out.write(c);
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文件名路径删除本地文件

	 */
	public void deleteLocalFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * 取得当天文件总数
	 */
	@SuppressWarnings("unchecked")
	public int getFileNumByDate(String dateStr){
		int count = 0;
		String hql = "select count(*) from AttachFileOld attach where substr(attach.uploadDate,1,10) = ?";
		
		try{
			List<Long> list = this.findByHQL(hql, dateStr);
			if(list!=null&&list.size()>0){
				count = list.get(0).intValue();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public List<AttachFileOld> findFilesByGroupId(String groupId){
//		String hql = "from AttachFileOld attach where " +
//				"attach.appName=? and attach.status=? and attach.groupId=? and attach.removed=0 order by attach.uploadDate desc";
//		return this.findByHQL(hql,AttachUtil.APP_MODEL_NAME,AttachUtil.STATUS_UPLOAD,groupId);
        String hql = "from AttachFileOld attach where " +
                " attach.status=? and attach.groupId=? and attach.removed=0 order by attach.uploadDate desc";
        return this.findByHQL(hql,AttachUtil.STATUS_UPLOAD,groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List findFilesByHql(String hql){
		return this.findByHQL(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List findFilesByHql(String hql,String orderByHql){
		hql = hql+" "+orderByHql;
		return this.findFilesByHql(hql);
	}
}
