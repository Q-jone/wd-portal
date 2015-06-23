/**
 * 
 */
package com.wonders.stpt.deptDoc.model.vo;

import java.util.List;

import com.wonders.stpt.deptDoc.model.bo.ZDocsCatalog;

public class ZDocsFileListVo {
	public String parent_sid_equal;
	public String file_name_like;
	public String keyword_like;
	public String create_user_name_like;
	public String create_date_startDate;
	public String create_date_endDate;
	public String order;
	public String pageSize;
	public String page;
	
	public ZDocsCatalog folder;
	public String flag; //100:部门内部资料,200:共享文件夹
	public String catalogId;
	public String catalogLevel;
	public List parentFolders;
	
	public String loginName;
	
	public boolean leader;
	
	public boolean read;
	public boolean write;

	public String getParent_sid_equal() {
		return parent_sid_equal;
	}
	public void setParent_sid_equal(String parent_sid_equal) {
		this.parent_sid_equal = parent_sid_equal;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}

	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCatalogLevel() {
		return catalogLevel;
	}
	
	public void setCatalogLevel(String catalogLevel) {
		this.catalogLevel = catalogLevel;
	}
	
	public String getCatalogId() {
		return catalogId;
	}
	
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
		this.parent_sid_equal = catalogId;
	}
	public List getParentFolders() {
		return parentFolders;
	}
	public void setParentFolders(List parentFolders) {
		this.parentFolders = parentFolders;
	}
	
	public ZDocsCatalog getFolder() {
		return folder;
	}
	public void setFolder(ZDocsCatalog folder) {
		this.folder = folder;
	}
	public String getFile_name_like() {
		return file_name_like;
	}
	public void setFile_name_like(String file_name_like) {
		this.file_name_like = file_name_like;
	}
	public String getKeyword_like() {
		return keyword_like;
	}
	public void setKeyword_like(String keyword_like) {
		this.keyword_like = keyword_like;
	}
	public String getCreate_user_name_like() {
		return create_user_name_like;
	}
	public void setCreate_user_name_like(String create_user_name_like) {
		this.create_user_name_like = create_user_name_like;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean isWrite() {
		return write;
	}
	public void setWrite(boolean write) {
		this.write = write;
	}
	public String getCreate_date_startDate() {
		return create_date_startDate;
	}
	public void setCreate_date_startDate(String create_date_startDate) {
		this.create_date_startDate = create_date_startDate;
	}
	public String getCreate_date_endDate() {
		return create_date_endDate;
	}
	public void setCreate_date_endDate(String create_date_endDate) {
		this.create_date_endDate = create_date_endDate;
	}
	public boolean isLeader() {
		return leader;
	}
	public void setLeader(boolean leader) {
		this.leader = leader;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
