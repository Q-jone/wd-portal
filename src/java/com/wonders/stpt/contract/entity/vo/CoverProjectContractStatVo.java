package com.wonders.stpt.contract.entity.vo;

import java.util.List;

import com.wonders.stpt.contract.entity.bo.CoverProjectContractStat;

public class CoverProjectContractStatVo {

	private CoverProjectContractStat coverProjectContractStat;
	
	private List<Long> projectCount;
	private List<Double> projectPrice;
	private List<Long> contractCount;
	private List<Double> contractPrice;
	private List<Double> payPrice;
	private List<String> dateList;
	public CoverProjectContractStat getCoverProjectContractStat() {
		return coverProjectContractStat;
	}
	public void setCoverProjectContractStat(
			CoverProjectContractStat coverProjectContractStat) {
		this.coverProjectContractStat = coverProjectContractStat;
	}
	public List<Long> getProjectCount() {
		return projectCount;
	}
	public void setProjectCount(List<Long> projectCount) {
		this.projectCount = projectCount;
	}
	public List<Double> getProjectPrice() {
		return projectPrice;
	}
	public void setProjectPrice(List<Double> projectPrice) {
		this.projectPrice = projectPrice;
	}
	public List<Long> getContractCount() {
		return contractCount;
	}
	public void setContractCount(List<Long> contractCount) {
		this.contractCount = contractCount;
	}
	public List<Double> getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(List<Double> countPrice) {
		this.contractPrice = countPrice;
	}
	public List<Double> getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(List<Double> payPrice) {
		this.payPrice = payPrice;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	
	
}
