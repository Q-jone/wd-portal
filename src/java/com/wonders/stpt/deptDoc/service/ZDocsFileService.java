package com.wonders.stpt.deptDoc.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.deptDoc.model.bo.ZDocsCatalog;
import com.wonders.stpt.deptDoc.model.bo.ZDocsFile;
import com.wonders.stpt.deptDoc.model.vo.ZDocsFileListVo;
import com.wonders.stpt.page.model.PageResultSet;

public interface ZDocsFileService {
	
	public ZDocsFile findById(String fileId);
	
	public void uploadDocs(String attachGroup, String catalogId, String flag, String fileName,String keyword, String cUserId,String cUserName);
	
	public void copyToFolders(String fileIds, String destDirIds, String cUserId,String cUserName,boolean move);
	
	public int removeById(String fileId);
	
	public int updateDocInfo(String id, String fileName,String keyword, String uUserId, String uUserName);
	
	public List<ZDocsFile> findByCatalogId(String catalogId);
	
	public PageResultSet<Map<String,Object>> list(ZDocsFileListVo vo);
}
