package com.wonders.stpt.contract.entity.vo;

import java.util.List;


public class CoverContractPriceVo {
	private List<Double> contractPrice;
	private List<Long> contractCount;
	private List<String> controlYear;
	public List<Double> getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(List<Double> contractPrice) {
		this.contractPrice = contractPrice;
	}
	public List<Long> getContractCount() {
		return contractCount;
	}
	public void setContractCount(List<Long> contractCount) {
		this.contractCount = contractCount;
	}
	public List<String> getControlYear() {
		return controlYear;
	}
	public void setControlYear(List<String> controlYear) {
		this.controlYear = controlYear;
	}
	
	

}
