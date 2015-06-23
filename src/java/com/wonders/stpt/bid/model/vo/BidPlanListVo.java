/**
 * 
 */
package com.wonders.stpt.bid.model.vo;

/** 
 * @ClassName: BidPlanList 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月9日 下午2:41:44 
 *  
 */
public class BidPlanListVo {
	public String projectCompanyId_equal;
	public String lineId_equal;
	public String catalogId_equal;
	public String typeId_equal;
	public String majorId_equal;
	public String bidNum_like;
	public String projectName_like;
	public String bidPlanDate_startDate;
	public String bidPlanDate_endDate;
	public String bidAmount_like;
	public String bidInfoDate_startDate;
	public String bidInfoDate_endDate;
	public String statusId_equal;
	public String pageSize;
	public String page;
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
	public String getProjectCompanyId_equal() {
		return projectCompanyId_equal;
	}
	public void setProjectCompanyId_equal(String projectCompanyId_equal) {
		this.projectCompanyId_equal = projectCompanyId_equal;
	}
	public String getLineId_equal() {
		return lineId_equal;
	}
	public void setLineId_equal(String lineId_equal) {
		this.lineId_equal = lineId_equal;
	}
	public String getCatalogId_equal() {
		return catalogId_equal;
	}
	public void setCatalogId_equal(String catalogId_equal) {
		this.catalogId_equal = catalogId_equal;
	}
	public String getTypeId_equal() {
		return typeId_equal;
	}
	public void setTypeId_equal(String typeId_equal) {
		this.typeId_equal = typeId_equal;
	}
	public String getMajorId_equal() {
		return majorId_equal;
	}
	public void setMajorId_equal(String majorId_equal) {
		this.majorId_equal = majorId_equal;
	}
	public String getBidNum_like() {
		return bidNum_like;
	}
	public void setBidNum_like(String bidNum_like) {
		this.bidNum_like = bidNum_like;
	}
	public String getProjectName_like() {
		return projectName_like;
	}
	public void setProjectName_like(String projectName_like) {
		this.projectName_like = projectName_like;
	}
	public String getBidPlanDate_startDate() {
		return bidPlanDate_startDate;
	}
	public void setBidPlanDate_startDate(String bidPlanDate_startDate) {
		this.bidPlanDate_startDate = bidPlanDate_startDate;
	}
	public String getBidPlanDate_endDate() {
		return bidPlanDate_endDate;
	}
	public void setBidPlanDate_endDate(String bidPlanDate_endDate) {
		this.bidPlanDate_endDate = bidPlanDate_endDate;
	}
	public String getBidAmount_like() {
		return bidAmount_like;
	}
	public void setBidAmount_like(String bidAmount_like) {
		this.bidAmount_like = bidAmount_like;
	}
	public String getBidInfoDate_startDate() {
		return bidInfoDate_startDate;
	}
	public void setBidInfoDate_startDate(String bidInfoDate_startDate) {
		this.bidInfoDate_startDate = bidInfoDate_startDate;
	}
	public String getBidInfoDate_endDate() {
		return bidInfoDate_endDate;
	}
	public void setBidInfoDate_endDate(String bidInfoDate_endDate) {
		this.bidInfoDate_endDate = bidInfoDate_endDate;
	}
	public String getStatusId_equal() {
		return statusId_equal;
	}
	public void setStatusId_equal(String statusId_equal) {
		this.statusId_equal = statusId_equal;
	}
	
	
}
