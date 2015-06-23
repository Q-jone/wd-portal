/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.indicatorControl.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.stpt.indicatorControl.entity.vo.MetroProductionControlVO;
import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_METRO_PRODUCTION_CONTROL")
public class MetroProductionControl implements Serializable, BusinessObject {

	private static final long serialVersionUID = -184055407295085109L;

	private String id; // id

	private Long backupEightSection; // backupEightSection

	private Long backupFourSection; // backupFourSection

	private Long backupSevenSection; // backupSevenSection

	private Long backupSixSection; // backupSixSection
	
	private Long backupThreeSection; // backupSixSection

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

	private String indicatorLine; // indicatorLine

	private Double metroColumnDaily; // metroColumnDaily

	private Double metroColumnMonth; // metroColumnMonth

	private Double metroColumnYear; // metroColumnYear

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

	private Long passengerCapacityDaily; // passengerCapacityDaily

	private Long passengerCapacityMonth; // passengerCapacityMonth

	private Long passengerCapacityYear; // passengerCapacityYear

	private Long passengerTransferDaily; // passengerTransferDaily

	private Long passengerTransferMonth; // passengerTransferMonth

	private Long passengerTransferYear; // passengerTransferYear

	private String removed; // removed

	private Double ticketIncomeDaily; // ticketIncomeDaily

	private Double ticketIncomeMonth; // ticketIncomeMonth

	private Double ticketIncomeYear; // ticketIncomeYear
	
	private String metroColumnControl;	//管控类型，1-高好，2-低好，3-接近好
	private String metroDistanceControl;	//管控类型，1-高好，2-低好，3-接近好
	private String passengerCapacityControl;	//管控类型，1-高好，2-低好，3-接近好
	private String passengerTransferControl;	//管控类型，1-高好，2-低好，3-接近好
	private String ticketIncomeControl;		//管控类型，1-高好，2-低好，3-接近好
	
	private String metroColumnDescribe;		//管控说明，用“|”分隔
	private String metroDistanceDescribe;	//管控说明，用“|”分隔
	private String passengerCapacityDescribe;	//管控说明，用“|”分隔
	private String passengerTransferDescribe;	//管控说明，用“|”分隔
	private String ticketIncomeDescribe;		//管控说明，用“|”分隔
	
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setBackupEightSection(Long backupEightSection) {
		this.backupEightSection = backupEightSection;
	}

	@Column(name = "BACKUP_EIGHT_SECTION", nullable = true, length = 20)
	public Long getBackupEightSection() {
		return backupEightSection;
	}

	public void setBackupFourSection(Long backupFourSection) {
		this.backupFourSection = backupFourSection;
	}

	@Column(name = "BACKUP_FOUR_SECTION", nullable = true, length = 20)
	public Long getBackupFourSection() {
		return backupFourSection;
	}

	public void setBackupSevenSection(Long backupSevenSection) {
		this.backupSevenSection = backupSevenSection;
	}

	@Column(name = "BACKUP_SEVEN_SECTION", nullable = true, length = 20)
	public Long getBackupSevenSection() {
		return backupSevenSection;
	}

	public void setBackupSixSection(Long backupSixSection) {
		this.backupSixSection = backupSixSection;
	}

	@Column(name = "BACKUP_SIX_SECTION", nullable = true, length = 20)
	public Long getBackupSixSection() {
		return backupSixSection;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT1", nullable = true, length = 200)
	public String getExt1() {
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT2", nullable = true, length = 200)
	public String getExt2() {
		return ext2;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT3", nullable = true, length = 200)
	public String getExt3() {
		return ext3;
	}

	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}

	@Column(name = "INDICATOR_LINE", nullable = true, length = 50)
	public String getIndicatorLine() {
		return indicatorLine;
	}

	public void setMetroColumnDaily(Double metroColumnDaily) {
		this.metroColumnDaily = metroColumnDaily;
	}

	@Column(name = "METRO_COLUMN_DAILY", nullable = true, length = 20)
	public Double getMetroColumnDaily() {
		return metroColumnDaily;
	}

	public void setMetroColumnMonth(Double metroColumnMonth) {
		this.metroColumnMonth = metroColumnMonth;
	}

	@Column(name = "METRO_COLUMN_MONTH", nullable = true, length = 20)
	public Double getMetroColumnMonth() {
		return metroColumnMonth;
	}

	public void setMetroColumnYear(Double metroColumnYear) {
		this.metroColumnYear = metroColumnYear;
	}

	@Column(name = "METRO_COLUMN_YEAR", nullable = true, length = 20)
	public Double getMetroColumnYear() {
		return metroColumnYear;
	}

	public void setMetroDistanceDaily(Double metroDistanceDaily) {
		this.metroDistanceDaily = metroDistanceDaily;
	}

	@Column(name = "METRO_DISTANCE_DAILY", nullable = true, length = 20)
	public Double getMetroDistanceDaily() {
		return metroDistanceDaily;
	}

	public void setMetroDistanceMonth(Double metroDistanceMonth) {
		this.metroDistanceMonth = metroDistanceMonth;
	}

	@Column(name = "METRO_DISTANCE_MONTH", nullable = true, length = 20)
	public Double getMetroDistanceMonth() {
		return metroDistanceMonth;
	}

	public void setMetroDistanceYear(Double metroDistanceYear) {
		this.metroDistanceYear = metroDistanceYear;
	}

	@Column(name = "METRO_DISTANCE_YEAR", nullable = true, length = 20)
	public Double getMetroDistanceYear() {
		return metroDistanceYear;
	}

	public void setOnlineEightSection(Long onlineEightSection) {
		this.onlineEightSection = onlineEightSection;
	}

	@Column(name = "ONLINE_EIGHT_SECTION", nullable = true, length = 20)
	public Long getOnlineEightSection() {
		return onlineEightSection;
	}

	public void setOnlineFourSection(Long onlineFourSection) {
		this.onlineFourSection = onlineFourSection;
	}

	@Column(name = "ONLINE_FOUR_SECTION", nullable = true, length = 20)
	public Long getOnlineFourSection() {
		return onlineFourSection;
	}

	public void setOnlineSevenSection(Long onlineSevenSection) {
		this.onlineSevenSection = onlineSevenSection;
	}

	@Column(name = "ONLINE_SEVEN_SECTION", nullable = true, length = 20)
	public Long getOnlineSevenSection() {
		return onlineSevenSection;
	}

	public void setOnlineSixSection(Long onlineSixSection) {
		this.onlineSixSection = onlineSixSection;
	}

	@Column(name = "ONLINE_SIX_SECTION", nullable = true, length = 20)
	public Long getOnlineSixSection() {
		return onlineSixSection;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	@Column(name = "OPERATE_PERSON", nullable = true, length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}

	public void setPassengerCapacityDaily(Long passengerCapacityDaily) {
		this.passengerCapacityDaily = passengerCapacityDaily;
	}

	@Column(name = "PASSENGER_CAPACITY_DAILY", nullable = true, length = 20)
	public Long getPassengerCapacityDaily() {
		return passengerCapacityDaily;
	}

	public void setPassengerCapacityMonth(Long passengerCapacityMonth) {
		this.passengerCapacityMonth = passengerCapacityMonth;
	}

	@Column(name = "PASSENGER_CAPACITY_MONTH", nullable = true, length = 20)
	public Long getPassengerCapacityMonth() {
		return passengerCapacityMonth;
	}

	public void setPassengerCapacityYear(Long passengerCapacityYear) {
		this.passengerCapacityYear = passengerCapacityYear;
	}

	@Column(name = "PASSENGER_CAPACITY_YEAR", nullable = true, length = 20)
	public Long getPassengerCapacityYear() {
		return passengerCapacityYear;
	}

	public void setPassengerTransferDaily(Long passengerTransferDaily) {
		this.passengerTransferDaily = passengerTransferDaily;
	}

	@Column(name = "PASSENGER_TRANSFER_DAILY", nullable = true, length = 20)
	public Long getPassengerTransferDaily() {
		return passengerTransferDaily;
	}

	public void setPassengerTransferMonth(Long passengerTransferMonth) {
		this.passengerTransferMonth = passengerTransferMonth;
	}

	@Column(name = "PASSENGER_TRANSFER_MONTH", nullable = true, length = 20)
	public Long getPassengerTransferMonth() {
		return passengerTransferMonth;
	}

	public void setPassengerTransferYear(Long passengerTransferYear) {
		this.passengerTransferYear = passengerTransferYear;
	}

	@Column(name = "PASSENGER_TRANSFER_YEAR", nullable = true, length = 20)
	public Long getPassengerTransferYear() {
		return passengerTransferYear;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}

	public void setTicketIncomeDaily(Double ticketIncomeDaily) {
		this.ticketIncomeDaily = ticketIncomeDaily;
	}

	@Column(name = "TICKET_INCOME_DAILY", nullable = true, length = 20)
	public Double getTicketIncomeDaily() {
		return ticketIncomeDaily;
	}

	public void setTicketIncomeMonth(Double ticketIncomeMonth) {
		this.ticketIncomeMonth = ticketIncomeMonth;
	}

	@Column(name = "TICKET_INCOME_MONTH", nullable = true, length = 20)
	public Double getTicketIncomeMonth() {
		return ticketIncomeMonth;
	}

	public void setTicketIncomeYear(Double ticketIncomeYear) {
		this.ticketIncomeYear = ticketIncomeYear;
	}

	@Column(name = "TICKET_INCOME_YEAR", nullable = true, length = 20)
	public Double getTicketIncomeYear() {
		return ticketIncomeYear;
	}

	
	@Column(name = "METRO_COLUMN_CONTROL", nullable = true, length = 1)
	public String getMetroColumnControl() {
		return metroColumnControl;
	}

	public void setMetroColumnControl(String metroColumnControl) {
		this.metroColumnControl = metroColumnControl;
	}

	@Column(name = "METRO_DISTANCE_CONTROL", nullable = true, length = 1)
	public String getMetroDistanceControl() {
		return metroDistanceControl;
	}

	public void setMetroDistanceControl(String metroDistanceControl) {
		this.metroDistanceControl = metroDistanceControl;
	}

	@Column(name = "PASSENGER_CAPACITY_CONTROL", nullable = true, length = 1)
	public String getPassengerCapacityControl() {
		return passengerCapacityControl;
	}

	public void setPassengerCapacityControl(String passengerCapacityControl) {
		this.passengerCapacityControl = passengerCapacityControl;
	}

	@Column(name = "PASSENGER_TRANSFER_CONTROL", nullable = true, length = 1)
	public String getPassengerTransferControl() {
		return passengerTransferControl;
	}

	public void setPassengerTransferControl(String passengerTransferControl) {
		this.passengerTransferControl = passengerTransferControl;
	}

	@Column(name = "TICKET_INCOME_CONTROL", nullable = true, length = 1)
	public String getTicketIncomeControl() {
		return ticketIncomeControl;
	}

	public void setTicketIncomeControl(String ticketIncomeControl) {
		this.ticketIncomeControl = ticketIncomeControl;
	}

	@Column(name = "METRO_COLUMN_DESCRIBE", nullable = true, length = 100)
	public String getMetroColumnDescribe() {
		return metroColumnDescribe;
	}

	public void setMetroColumnDescribe(String metroColumnDescribe) {
		this.metroColumnDescribe = metroColumnDescribe;
	}

	@Column(name = "METRO_DISTANCE_DESCRIBE", nullable = true, length = 100)
	public String getMetroDistanceDescribe() {
		return metroDistanceDescribe;
	}

	public void setMetroDistanceDescribe(String metroDistanceDescribe) {
		this.metroDistanceDescribe = metroDistanceDescribe;
	}

	@Column(name = "PASSENGER_CAPACITY_DESCRIBE", nullable = true, length = 100)
	public String getPassengerCapacityDescribe() {
		return passengerCapacityDescribe;
	}

	public void setPassengerCapacityDescribe(String passengerCapacityDescribe) {
		this.passengerCapacityDescribe = passengerCapacityDescribe;
	}

	@Column(name = "PASSENGER_TRANSFER_DESCRIBE", nullable = true, length = 100)
	public String getPassengerTransferDescribe() {
		return passengerTransferDescribe;
	}

	public void setPassengerTransferDescribe(String passengerTransferDescribe) {
		this.passengerTransferDescribe = passengerTransferDescribe;
	}

	@Column(name = "TICKET_INCOME_DESCRIBE", nullable = true, length = 100)
	public String getTicketIncomeDescribe() {
		return ticketIncomeDescribe;
	}

	public void setTicketIncomeDescribe(String ticketIncomeDescribe) {
		this.ticketIncomeDescribe = ticketIncomeDescribe;
	}

	@Column(name = "BACKUP_THREE_SECTION", nullable = true, length = 20)
	public Long getBackupThreeSection() {
		return backupThreeSection;
	}

	public void setBackupThreeSection(Long backupThreeSection) {
		this.backupThreeSection = backupThreeSection;
	}

	@Column(name = "ONLINE_THREE_SECTION", nullable = true, length = 20)
	public Long getOnlineThreeSection() {
		return onlineThreeSection;
	}

	public void setOnlineThreeSection(Long onlineThreeSection) {
		this.onlineThreeSection = onlineThreeSection;
	}

	public MetroProductionControl(MetroProductionControlVO vo) {
		this.id = vo.getId();
		this.backupEightSection = Long.valueOf(vo.getBackupEightSection());
		this.backupFourSection = Long.valueOf(vo.getBackupFourSection());
		this.backupSevenSection = Long.valueOf(vo.getBackupEightSection());
		this.backupSixSection = Long.valueOf(vo.getBackupSixSection());
		this.backupThreeSection = Long.valueOf(vo.getBackupThreeSection());
		this.ext1 = vo.getExt1();
		this.ext2 = vo.getExt2();
		this.ext3 = vo.getExt3();
		this.indicatorLine = vo.getIndicatorLine();
		this.metroColumnDaily = Double.valueOf(vo.getMetroColumnDaily());
		this.metroColumnMonth = Double.valueOf(vo.getMetroColumnMonth());
		this.metroColumnYear = Double.valueOf(vo.getMetroColumnYear());
		this.metroDistanceDaily = Double.valueOf(vo.getMetroDistanceDaily());
		this.metroDistanceMonth = Double.valueOf(vo.getMetroDistanceMonth());
		this.metroDistanceYear = Double.valueOf(vo.getMetroDistanceYear());
		this.onlineEightSection = Long.valueOf(vo.getOnlineEightSection());
		this.onlineFourSection = Long.valueOf(vo.getOnlineFourSection());
		this.onlineSevenSection = Long.valueOf(vo.getOnlineSevenSection());
		this.onlineSixSection = Long.valueOf(vo.getOnlineSixSection());
		this.onlineThreeSection = Long.valueOf(vo.getOnlineThreeSection());
		this.operatePerson = vo.getOperatePerson();
		this.operateTime = vo.getOperateTime();
		this.passengerCapacityDaily = Double.valueOf(Double.valueOf(vo.getPassengerCapacityDaily())*10000).longValue();
		this.passengerCapacityMonth = Double.valueOf(Double.valueOf(vo.getPassengerCapacityMonth())*10000).longValue();
		this.passengerCapacityYear = Double.valueOf(Double.valueOf(vo.getPassengerCapacityYear())*10000).longValue();
		this.passengerTransferDaily = Double.valueOf(Double.valueOf(vo.getPassengerTransferDaily())*10000).longValue();
		this.passengerTransferMonth = Double.valueOf(Double.valueOf(vo.getPassengerTransferMonth())*10000).longValue();
		this.passengerTransferYear = Double.valueOf(Double.valueOf(vo.getPassengerTransferYear())*10000).longValue();
		this.removed = vo.getRemoved();
		this.ticketIncomeDaily = Double.valueOf(vo.getTicketIncomeDaily());
		this.ticketIncomeMonth = Double.valueOf(vo.getTicketIncomeMonth());
		this.ticketIncomeYear = Double.valueOf(vo.getTicketIncomeYear());
		
		this.metroColumnControl = vo.getMetroColumnControl();
		this.metroDistanceControl = vo.getMetroDistanceControl();
		this.passengerCapacityControl = vo.getPassengerCapacityControl();
		this.passengerTransferControl = vo.getPassengerTransferControl();
		this.ticketIncomeControl = vo.getTicketIncomeControl();
		
		this.metroColumnDescribe = vo.getMetroColumnDescribe();
		this.metroDistanceDescribe = vo.getMetroDistanceDescribe();
		this.passengerCapacityDescribe = vo.getPassengerCapacityDescribe();
		this.passengerTransferDescribe = vo.getPassengerTransferDescribe();
		this.ticketIncomeDescribe = vo.getTicketIncomeDescribe();
	}

	public MetroProductionControl() {
		super();
	}

	
	

}
