package com.wonders.stpt.contract.entity.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COVER_PROJECT_CONTRACT_STAT")
public class CoverProjectContractStat {
	private String id;
	private Long dayProjectCount;
	private Double dayProjectPrice;
	private Long monthProjectCount;
	private Double monthProjectPrice;
	private Long yearProjectCount;
	private Double yearProjectPrice;
	
	private Long dayContractCount;
	private Double dayContractPrice;
	private Long monthContractCount;
	private Double monthContractPrice;
	private Long yearContractCount;
	private Double yearContractPrice;
	
	private Double dayPayPrice;
	private Double monthPayPrice;
	private Double yearPayPrice;
	
	private String calDay;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DAY_PROJECT_COUNT")
	public Long getDayProjectCount() {
		return dayProjectCount;
	}

	public void setDayProjectCount(Long dayProjectCount) {
		this.dayProjectCount = dayProjectCount;
	}

	@Column(name = "DAY_PROJECT_PRICE")
	public Double getDayProjectPrice() {
		return dayProjectPrice;
	}

	public void setDayProjectPrice(Double dayProjectPrice) {
		this.dayProjectPrice = dayProjectPrice;
	}

	@Column(name = "MONTH_PROJECT_COUNT")
	public Long getMonthProjectCount() {
		return monthProjectCount;
	}

	public void setMonthProjectCount(Long monthProjectCount) {
		this.monthProjectCount = monthProjectCount;
	}

	@Column(name = "MONTH_PROJECT_PRICE")
	public Double getMonthProjectPrice() {
		return monthProjectPrice;
	}

	public void setMonthProjectPrice(Double monthProjectPrice) {
		this.monthProjectPrice = monthProjectPrice;
	}

	@Column(name = "YEAR_PROJECT_COUNT")
	public Long getYearProjectCount() {
		return yearProjectCount;
	}

	public void setYearProjectCount(Long yearProjectCount) {
		this.yearProjectCount = yearProjectCount;
	}

	@Column(name = "YEAR_PROJECT_PRICE")
	public Double getYearProjectPrice() {
		return yearProjectPrice;
	}

	public void setYearProjectPrice(Double yearProjectPrice) {
		this.yearProjectPrice = yearProjectPrice;
	}

	@Column(name = "DAY_CONTRACT_COUNT")
	public Long getDayContractCount() {
		return dayContractCount;
	}

	public void setDayContractCount(Long dayContractCount) {
		this.dayContractCount = dayContractCount;
	}

	@Column(name = "DAY_CONTRACT_PRICE")
	public Double getDayContractPrice() {
		return dayContractPrice;
	}

	public void setDayContractPrice(Double dayContractPrice) {
		this.dayContractPrice = dayContractPrice;
	}

	@Column(name = "MONTH_CONTRACT_COUNT")
	public Long getMonthContractCount() {
		return monthContractCount;
	}

	public void setMonthContractCount(Long monthContractCount) {
		this.monthContractCount = monthContractCount;
	}

	@Column(name = "MONTH_CONTRACT_PRICE")
	public Double getMonthContractPrice() {
		return monthContractPrice;
	}

	public void setMonthContractPrice(Double monthContractPrice) {
		this.monthContractPrice = monthContractPrice;
	}

	@Column(name = "YEAR_CONTRACT_COUNT")
	public Long getYearContractCount() {
		return yearContractCount;
	}

	public void setYearContractCount(Long yearContractCount) {
		this.yearContractCount = yearContractCount;
	}

	@Column(name = "YEAR_CONTRACT_PRICE")
	public Double getYearContractPrice() {
		return yearContractPrice;
	}

	public void setYearContractPrice(Double yearContractPrice) {
		this.yearContractPrice = yearContractPrice;
	}

	@Column(name = "DAY_PAY_PRICE")
	public Double getDayPayPrice() {
		return dayPayPrice;
	}

	public void setDayPayPrice(Double dayPayPrice) {
		this.dayPayPrice = dayPayPrice;
	}

	@Column(name = "MONTH_PAY_PRICE")
	public Double getMonthPayPrice() {
		return monthPayPrice;
	}

	public void setMonthPayPrice(Double monthPayPrice) {
		this.monthPayPrice = monthPayPrice;
	}

	@Column(name = "YEAR_PAY_PRICE")
	public Double getYearPayPrice() {
		return yearPayPrice;
	}

	public void setYearPayPrice(Double yearPayPrice) {
		this.yearPayPrice = yearPayPrice;
	}

	@Column(name = "CAL_DAY")
	public String getCalDay() {
		return calDay;
	}

	public void setCalDay(String calDay) {
		this.calDay = calDay;
	}
	
	

}
