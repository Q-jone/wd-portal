/**
 * 
 */
package com.wonders.stpt.contractManage.model.vo;

/** 
 * @ClassName: contractManageVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午10:24:04 
 *  
 */
public class ContractManageVo {
	public int pageSize;
	public int page;
	
	public ContractManageVo(){
		this.pageSize = 10;
		this.page = 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
