/**
 * 
 */
package com.wonders.stpt.validFile.model.vo;

/** 
 * @ClassName: BidPlanList 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月9日 下午2:41:44 
 *  
 */
public class ValidFileListVo {
	public String code1_equal;
	public String code2_equal;
	public String sendcode_like;
	public String senddate_startDate;
	public String senddate_endDate;
	public String title_like;
	public String addperson_like;
	public String senddept_like;
	public String status_in;
	public String remark_like;
	public String order;
	public String pageSize;
	public String page;
    public String normative_equal;
	
	
	public String getCode1_equal() {
		return code1_equal;
	}
	public void setCode1_equal(String code1_equal) {
		this.code1_equal = code1_equal;
	}
	public String getCode2_equal() {
		return code2_equal;
	}
	public void setCode2_equal(String code2_equal) {
		this.code2_equal = code2_equal;
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
	public String getSendcode_like() {
		return sendcode_like;
	}
	public void setSendcode_like(String sendcode_like) {
		this.sendcode_like = sendcode_like;
	}
	public String getSenddate_startDate() {
		return senddate_startDate;
	}
	public void setSenddate_startDate(String senddate_startDate) {
		this.senddate_startDate = senddate_startDate;
	}
	public String getSenddate_endDate() {
		return senddate_endDate;
	}
	public void setSenddate_endDate(String senddate_endDate) {
		this.senddate_endDate = senddate_endDate;
	}
	public String getTitle_like() {
		return title_like;
	}
	public void setTitle_like(String title_like) {
		this.title_like = title_like;
	}
	public String getAddperson_like() {
		return addperson_like;
	}
	public void setAddperson_like(String addperson_like) {
		this.addperson_like = addperson_like;
	}
	public String getSenddept_like() {
		return senddept_like;
	}
	public void setSenddept_like(String senddept_like) {
		this.senddept_like = senddept_like;
	}
	public String getStatus_in() {
		return status_in;
	}
	public void setStatus_in(String status_in) {
		this.status_in = status_in;
	}
	public String getRemark_like() {
		return remark_like;
	}
	public void setRemark_like(String remark_like) {
		this.remark_like = remark_like;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

    public String getNormative_equal() {
        return normative_equal;
    }

    public void setNormative_equal(String normative_equal) {
        this.normative_equal = normative_equal;
    }
}
