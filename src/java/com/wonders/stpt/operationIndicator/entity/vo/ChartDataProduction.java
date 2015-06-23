/**
 * 
 */
package com.wonders.stpt.operationIndicator.entity.vo;

import java.util.List;

import com.wonders.stpt.indicatorControl.entity.vo.MetroProductionControlVO;

/** 
 * @ClassName: ChartData 
 * @Description: MetroProduction返回到页面的数据
 * @author ycl
 * @date 2012-4-26 上午11:11:54 
 *  
 */
public class ChartDataProduction {

	private ProductionVo lastestProduction;			//最近一条友数据
	private ProductionVo lastYearProduction;		//去年同期的数据
	private List<String> dateList;			//日期
	
	private List<Integer> columnList;	//开行列次
	private List<Double> distanceList;	//运营里程
	private List<Double> passengerCapacityList;		//客流量
	private List<Double> passengerTransferList;		//换乘人次
	private List<Double> ticketIncomeList;		//客运收入
	private List<Long>	onlineSectionList;		//上线车	
	private List<Long>	backupSectionList;		//备用车
	
	private List<String> columnControlType;	//开行列次，管控类型
	private List<String> distanceControlType;	//运营里程，管控类型
	private List<String> passengerCapacityControlType;	//客流量，管控类型
	private List<String> passengerTransferControlType;	//换乘人次，管控类型
	private List<String> ticketIncomeControlType;	//客运收入，管控类型
	
	private List<String> columnDescribe;		//管控说明
	private List<String> distanceDescribe;		//管控说明
	private List<String> passengerCapacityDescribe;		//管控说明
	private List<String> passengerTransferDescribe;		//管控说明
	private List<String> ticketIncomeDescribe;		//管控说明
	
	private List<String> columnControlValue;		//管控值
	private List<String> distanceControlValue;		//管控值
	private List<String> capacityControlValue;		//管控值
	private List<String> transferControlValue;		//管控值
	private List<String> ticketControlValue;		//管控值
	
	
	private MetroProductionControlVO metroProductionControlVO;
	
	public MetroProductionControlVO getMetroProductionControlVO() {
		return metroProductionControlVO;
	}
	public void setMetroProductionControlVO(
			MetroProductionControlVO metroProductionControlVO) {
		this.metroProductionControlVO = metroProductionControlVO;
	}
	public ProductionVo getLastestProduction() {
		return lastestProduction;
	}
	public void setLastestProduction(ProductionVo lastestProduction) {
		this.lastestProduction = lastestProduction;
	}
	
	public ProductionVo getLastYearProduction() {
		return lastYearProduction;
	}
	public void setLastYearProduction(ProductionVo lastYearProduction) {
		this.lastYearProduction = lastYearProduction;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public List<Integer> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Integer> columnList) {
		this.columnList = columnList;
	}
	public List<Double> getDistanceList() {
		return distanceList;
	}
	public void setDistanceList(List<Double> distanceList) {
		this.distanceList = distanceList;
	}
	
	public List<Double> getTicketIncomeList() {
		return ticketIncomeList;
	}
	public void setTicketIncomeList(List<Double> ticketIncomeList) {
		this.ticketIncomeList = ticketIncomeList;
	}
	public List<Double> getPassengerCapacityList() {
		return passengerCapacityList;
	}
	public void setPassengerCapacityList(List<Double> passengerCapacityList) {
		this.passengerCapacityList = passengerCapacityList;
	}
	public List<Double> getPassengerTransferList() {
		return passengerTransferList;
	}
	public void setPassengerTransferList(List<Double> passengerTransferList) {
		this.passengerTransferList = passengerTransferList;
	}
	public List<Long> getOnlineSectionList() {
		return onlineSectionList;
	}
	public void setOnlineSectionList(List<Long> onlineSectionList) {
		this.onlineSectionList = onlineSectionList;
	}
	public List<Long> getBackupSectionList() {
		return backupSectionList;
	}
	public void setBackupSectionList(List<Long> backupSectionList) {
		this.backupSectionList = backupSectionList;
	}
	public List<String> getColumnControlType() {
		return columnControlType;
	}
	public void setColumnControlType(List<String> columnControlType) {
		this.columnControlType = columnControlType;
	}
	public List<String> getDistanceControlType() {
		return distanceControlType;
	}
	public void setDistanceControlType(List<String> distanceControlType) {
		this.distanceControlType = distanceControlType;
	}
	public List<String> getPassengerCapacityControlType() {
		return passengerCapacityControlType;
	}
	public void setPassengerCapacityControlType(
			List<String> passengerCapacityControlType) {
		this.passengerCapacityControlType = passengerCapacityControlType;
	}
	public List<String> getPassengerTransferControlType() {
		return passengerTransferControlType;
	}
	public void setPassengerTransferControlType(
			List<String> passengerTransferControlType) {
		this.passengerTransferControlType = passengerTransferControlType;
	}
	public List<String> getTicketIncomeControlType() {
		return ticketIncomeControlType;
	}
	public void setTicketIncomeControlType(List<String> ticketIncomeControlType) {
		this.ticketIncomeControlType = ticketIncomeControlType;
	}
	public List<String> getColumnDescribe() {
		return columnDescribe;
	}
	public void setColumnDescribe(List<String> columnDescribe) {
		this.columnDescribe = columnDescribe;
	}
	public List<String> getDistanceDescribe() {
		return distanceDescribe;
	}
	public void setDistanceDescribe(List<String> distanceDescribe) {
		this.distanceDescribe = distanceDescribe;
	}
	public List<String> getPassengerCapacityDescribe() {
		return passengerCapacityDescribe;
	}
	public void setPassengerCapacityDescribe(List<String> passengerCapacityDescribe) {
		this.passengerCapacityDescribe = passengerCapacityDescribe;
	}
	public List<String> getPassengerTransferDescribe() {
		return passengerTransferDescribe;
	}
	public void setPassengerTransferDescribe(List<String> passengerTransferDescribe) {
		this.passengerTransferDescribe = passengerTransferDescribe;
	}
	public List<String> getTicketIncomeDescribe() {
		return ticketIncomeDescribe;
	}
	public void setTicketIncomeDescribe(List<String> ticketIncomeDescribe) {
		this.ticketIncomeDescribe = ticketIncomeDescribe;
	}
	public List<String> getColumnControlValue() {
		return columnControlValue;
	}
	public void setColumnControlValue(List<String> columnControlValue) {
		this.columnControlValue = columnControlValue;
	}
	public List<String> getDistanceControlValue() {
		return distanceControlValue;
	}
	public void setDistanceControlValue(List<String> distanceControlValue) {
		this.distanceControlValue = distanceControlValue;
	}
	public List<String> getCapacityControlValue() {
		return capacityControlValue;
	}
	public void setCapacityControlValue(List<String> capacityControlValue) {
		this.capacityControlValue = capacityControlValue;
	}
	public List<String> getTransferControlValue() {
		return transferControlValue;
	}
	public void setTransferControlValue(List<String> transferControlValue) {
		this.transferControlValue = transferControlValue;
	}
	public List<String> getTicketControlValue() {
		return ticketControlValue;
	}
	public void setTicketControlValue(List<String> ticketControlValue) {
		this.ticketControlValue = ticketControlValue;
	}
	
	
	
	
	
}
