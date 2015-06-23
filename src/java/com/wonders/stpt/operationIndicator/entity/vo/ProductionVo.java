/**
 * 
 */
package com.wonders.stpt.operationIndicator.entity.vo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.wonders.stpt.indicatorControl.entity.vo.MetroProductionControlVO;
import com.wonders.stpt.metroIndicator.entity.bo.MetroProduction;


/** 
 * @ClassName: MetroIndicator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-6 下午08:05:12 
 *  
 */
public class ProductionVo {
	private String id; // id

	private Long backupEightSection; // backupEightSection
	
	private Long backupSixSection; // backupEightSection

	private Long backupFourSection; // backupFourSection

	private Long backupSevenSection; // backupSevenSection
	
	private Long backupThreeSection; // backupSevenSection

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

	private String indicatorDate; // indicatorDate

	private String indicatorLine; // indicatorLine

	private int metroColumnDaily; // metroColumnDaily

	private int metroColumnMonth; // metroColumnMonth

	private int metroColumnYear; // metroColumnYear

	private Double metroDistanceDaily; // metroDistanceDaily

	private Double metroDistanceMonth; // metroDistanceMonth

	private Double metroDistanceYear; // metroDistanceYear

	private Long onlineEightSection; // onlineEightSection

	private Long onlineFourSection; // onlineFourSection

	private Long onlineSevenSection; // onlineSevenSection

	private Long onlineSixSection; // onlineSixSection
	
	private Long onlineThreeSection; // onlineSixSection

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private Double passengerCapacityDaily; // passengerCapacityDaily

	private Double passengerCapacityMonth; // passengerCapacityMonth

	private Double passengerCapacityYear; // passengerCapacityYear

	private Double passengerTransferDaily; // passengerTransferDaily

	private Double passengerTransferMonth; // passengerTransferMonth

	private Double passengerTransferYear; // passengerTransferYear

	private String removed; // removed

	private Double ticketIncomeDaily; // ticketIncomeDaily

	private Double ticketIncomeMonth; // ticketIncomeMonth

	private Double ticketIncomeYear; // ticketIncomeYear
	
	private ProductionVo lastyearMetroProduction;		//去年同期
	
	private MetroProductionControlVO metroProductionControlVO;
	
	public MetroProductionControlVO getMetroProductionControlVO() {
		return metroProductionControlVO;
	}

	public void setMetroProductionControlVO(
			MetroProductionControlVO metroProductionControlVO) {
		this.metroProductionControlVO = metroProductionControlVO;
	}

	public ProductionVo getLastyearMetroProduction() {
		return lastyearMetroProduction;
	}

	public void setLastyearMetroProduction(ProductionVo lastyearMetroProduction) {
		this.lastyearMetroProduction = lastyearMetroProduction;
	}

	public ProductionVo() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getBackupEightSection() {
		return backupEightSection;
	}

	public void setBackupEightSection(Long backupEightSection) {
		this.backupEightSection = backupEightSection;
	}

	public Long getBackupSixSection() {
		return backupSixSection;
	}

	public void setBackupSixSection(Long backupSixSection) {
		this.backupSixSection = backupSixSection;
	}

	public Long getBackupFourSection() {
		return backupFourSection;
	}

	public void setBackupFourSection(Long backupFourSection) {
		this.backupFourSection = backupFourSection;
	}

	public Long getBackupSevenSection() {
		return backupSevenSection;
	}

	public void setBackupSevenSection(Long backupSevenSection) {
		this.backupSevenSection = backupSevenSection;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getIndicatorDate() {
		return indicatorDate;
	}

	public void setIndicatorDate(String indicatorDate) {
		this.indicatorDate = indicatorDate;
	}

	public String getIndicatorLine() {
		return indicatorLine;
	}

	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}

	public int getMetroColumnDaily() {
		return metroColumnDaily;
	}

	public void setMetroColumnDaily(int metroColumnDaily) {
		this.metroColumnDaily = metroColumnDaily;
	}

	public int getMetroColumnMonth() {
		return metroColumnMonth;
	}

	public void setMetroColumnMonth(int metroColumnMonth) {
		this.metroColumnMonth = metroColumnMonth;
	}

	public int getMetroColumnYear() {
		return metroColumnYear;
	}

	public void setMetroColumnYear(int metroColumnYear) {
		this.metroColumnYear = metroColumnYear;
	}

	public Double getMetroDistanceDaily() {
		return metroDistanceDaily;
	}

	public void setMetroDistanceDaily(Double metroDistanceDaily) {
		this.metroDistanceDaily = metroDistanceDaily;
	}

	public Double getMetroDistanceMonth() {
		return metroDistanceMonth;
	}

	public void setMetroDistanceMonth(Double metroDistanceMonth) {
		this.metroDistanceMonth = metroDistanceMonth;
	}

	public Double getMetroDistanceYear() {
		return metroDistanceYear;
	}

	public void setMetroDistanceYear(Double metroDistanceYear) {
		this.metroDistanceYear = metroDistanceYear;
	}

	public Long getOnlineEightSection() {
		return onlineEightSection;
	}

	public void setOnlineEightSection(Long onlineEightSection) {
		this.onlineEightSection = onlineEightSection;
	}

	public Long getOnlineFourSection() {
		return onlineFourSection;
	}

	public void setOnlineFourSection(Long onlineFourSection) {
		this.onlineFourSection = onlineFourSection;
	}

	public Long getOnlineSevenSection() {
		return onlineSevenSection;
	}

	public void setOnlineSevenSection(Long onlineSevenSection) {
		this.onlineSevenSection = onlineSevenSection;
	}

	public Long getOnlineSixSection() {
		return onlineSixSection;
	}

	public void setOnlineSixSection(Long onlineSixSection) {
		this.onlineSixSection = onlineSixSection;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	

	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public Double getTicketIncomeDaily() {
		return ticketIncomeDaily;
	}

	public void setTicketIncomeDaily(Double ticketIncomeDaily) {
		this.ticketIncomeDaily = ticketIncomeDaily;
	}

	public Double getTicketIncomeMonth() {
		return ticketIncomeMonth;
	}

	public void setTicketIncomeMonth(Double ticketIncomeMonth) {
		this.ticketIncomeMonth = ticketIncomeMonth;
	}

	public Double getTicketIncomeYear() {
		return ticketIncomeYear;
	}

	public void setTicketIncomeYear(Double ticketIncomeYear) {
		this.ticketIncomeYear = ticketIncomeYear;
	}
	
	

	public Double getPassengerCapacityDaily() {
		return passengerCapacityDaily;
	}

	public void setPassengerCapacityDaily(Double passengerCapacityDaily) {
		this.passengerCapacityDaily = passengerCapacityDaily;
	}

	public Double getPassengerCapacityMonth() {
		return passengerCapacityMonth;
	}

	public void setPassengerCapacityMonth(Double passengerCapacityMonth) {
		this.passengerCapacityMonth = passengerCapacityMonth;
	}

	public Double getPassengerCapacityYear() {
		return passengerCapacityYear;
	}

	public void setPassengerCapacityYear(Double passengerCapacityYear) {
		this.passengerCapacityYear = passengerCapacityYear;
	}

	public Double getPassengerTransferDaily() {
		return passengerTransferDaily;
	}

	public void setPassengerTransferDaily(Double passengerTransferDaily) {
		this.passengerTransferDaily = passengerTransferDaily;
	}

	public Double getPassengerTransferMonth() {
		return passengerTransferMonth;
	}

	public void setPassengerTransferMonth(Double passengerTransferMonth) {
		this.passengerTransferMonth = passengerTransferMonth;
	}

	public Double getPassengerTransferYear() {
		return passengerTransferYear;
	}

	public void setPassengerTransferYear(Double passengerTransferYear) {
		this.passengerTransferYear = passengerTransferYear;
	}

	
	
	public Long getBackupThreeSection() {
		return backupThreeSection;
	}

	public void setBackupThreeSection(Long backupThreeSection) {
		this.backupThreeSection = backupThreeSection;
	}

	public Long getOnlineThreeSection() {
		return onlineThreeSection;
	}

	public void setOnlineThreeSection(Long onlineThreeSection) {
		this.onlineThreeSection = onlineThreeSection;
	}

	//除以1000,保留4位小数
	public double getFormatData(Double data){
		if(data!=null){
			data = data*0.0001;
			NumberFormat nf = new DecimalFormat("0.0000"); 
			return Double.valueOf(nf.format(data));
		}else{
			return 0;
		}
	}
	
	//除以(1000*100)
	public double getFormatDataByDevide(Double data){
		if(data!=null){
			data = data*0.0001*0.001;
			NumberFormat nf = new DecimalFormat("0.0000"); 
			return Double.valueOf(nf.format(data));
		}else{
			return 0;
		}
	}
	
	
	//保留2位小数
	public double getFormatDataByTwoDecimal(Double data){
		if(data!=null){
			NumberFormat nf = new DecimalFormat("0.00"); 
			return Double.valueOf(nf.format(data));
		}else{
			return 0;
		}
		
	}
	
	public double getFormatData(Long data){
		if(data!=null){
			NumberFormat nf = new DecimalFormat("0.0000"); 
			return Double.valueOf(nf.format((Double.valueOf(data)*0.0001)));
		}else{
			return 0;
		}
		
	}
	
	public int killDecimalPoint(Double data){
		return data == null ? 0 : data.intValue();
	}
	
	
	public ProductionVo(MetroProduction production) {
		this.id = production.getId();
		this.backupEightSection = production.getBackupEightSection();
		this.backupSixSection = production.getBackupSixSection();
		this.backupFourSection = production.getBackupFourSection();
		this.backupSevenSection = production.getBackupSevenSection();
		this.backupThreeSection = production.getBackupThreeSection();
		this.ext1 = production.getExt1();
		this.ext2 = production.getExt2();
		this.ext3 = production.getExt3();
		this.indicatorDate = production.getIndicatorDate();
		this.indicatorLine = production.getIndicatorLine();
		this.metroColumnDaily = killDecimalPoint(production.getMetroColumnDaily());
		this.metroColumnMonth = killDecimalPoint(production.getMetroColumnMonth());
		this.metroColumnYear = killDecimalPoint(production.getMetroColumnYear());
		/**
		 * 数据单位修改前，单位为：万公里
		this.metroDistanceDaily = getFormatData(production.getMetroDistanceDaily());
		this.metroDistanceMonth = getFormatData(production.getMetroDistanceMonth());
		this.metroDistanceYear = getFormatData(production.getMetroDistanceYear());
		**/
		//修改数据单位后，单位为：米,数据库中数据的单位也为米，不做处理直接显示
		this.metroDistanceDaily = getFormatDataByDevide(production.getMetroDistanceDaily());
		this.metroDistanceMonth = getFormatDataByDevide(production.getMetroDistanceMonth());
		this.metroDistanceYear = getFormatDataByDevide(production.getMetroDistanceYear());
		
		this.onlineEightSection = production.getOnlineEightSection();
		this.onlineFourSection = production.getOnlineFourSection();
		this.onlineSevenSection = production.getOnlineSevenSection();
		this.onlineSixSection = production.getOnlineSixSection();
		this.onlineThreeSection = production.getOnlineThreeSection();
		this.operatePerson = production.getOperatePerson();
		this.operateTime = production.getOperateTime();
		this.passengerCapacityDaily = getFormatData(production.getPassengerCapacityDaily());
		this.passengerCapacityMonth = getFormatData(production.getPassengerCapacityMonth());
		this.passengerCapacityYear = getFormatData(production.getPassengerCapacityYear());
		this.passengerTransferDaily = getFormatData(production.getPassengerTransferDaily());
		this.passengerTransferMonth = getFormatData(production.getPassengerTransferMonth());
		this.passengerTransferYear = getFormatData(production.getPassengerTransferYear());
		this.removed = production.getRemoved();
		
		this.ticketIncomeDaily = getFormatData(production.getTicketIncomeDaily());
		this.ticketIncomeMonth = getFormatData(production.getTicketIncomeMonth());
		this.ticketIncomeYear = getFormatData(production.getTicketIncomeYear());
		
	}
	
}
