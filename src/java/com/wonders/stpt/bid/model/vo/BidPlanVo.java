/**
 * 
 */
package com.wonders.stpt.bid.model.vo;

import org.springframework.beans.BeanUtils;
import com.wonders.stpt.bid.model.bo.TBidPlan;
/** 
 * @ClassName: BidPlanVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:07:25 
 *  
 */
public class BidPlanVo {
	private String id;
	private String typeName;
	private String catalogName;
	private String majorName;
	private String projectCompany;
	private String line;
	private String bidNum;
	private String projectName;
	private String bidPlanDate;
	private String bidAmount;
	private String bidInfoDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getProjectCompany() {
		return projectCompany;
	}
	public void setProjectCompany(String projectCompany) {
		this.projectCompany = projectCompany;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getBidNum() {
		return bidNum;
	}
	public void setBidNum(String bidNum) {
		this.bidNum = bidNum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getBidPlanDate() {
		return bidPlanDate;
	}
	public void setBidPlanDate(String bidPlanDate) {
		this.bidPlanDate = bidPlanDate;
	}
	public String getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getBidInfoDate() {
		return bidInfoDate;
	}
	public void setBidInfoDate(String bidInfoDate) {
		this.bidInfoDate = bidInfoDate;
	}
	
	
}
