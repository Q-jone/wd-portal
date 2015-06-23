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

package com.wonders.stpt.metroIndicator.entity.bo;


import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TMetroProduction实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_METRO_PRODUCTION")
public class MetroProduction implements Serializable, BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7119286252206935892L;

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

	public MetroProduction(){
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setBackupEightSection(Long backupEightSection) {
		this.backupEightSection = backupEightSection;
	}

	@Column(name = "BACKUP_EIGHT_SECTION", precision=20)
	public Long getBackupEightSection() {
		return backupEightSection;
	}

	public void setBackupFourSection(Long backupFourSection) {
		this.backupFourSection = backupFourSection;
	}
	
	@Column(name = "BACKUP_SIX_SECTION", precision=20)
	public Long getBackupSixSection() {
		return backupSixSection;
	}

	public void setBackupSixSection(Long backupSixSection) {
		this.backupSixSection = backupSixSection;
	}

	@Column(name = "BACKUP_FOUR_SECTION", precision=20)
	public Long getBackupFourSection() {
		return backupFourSection;
	}

	public void setBackupSevenSection(Long backupSevenSection) {
		this.backupSevenSection = backupSevenSection;
	}

	@Column(name = "BACKUP_SEVEN_SECTION",precision=20)
	public Long getBackupSevenSection() {
		return backupSevenSection;
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

	public void setIndicatorDate(String indicatorDate) {
		this.indicatorDate = indicatorDate;
	}

	@Column(name = "INDICATOR_DATE", nullable = true, length = 50)
	public String getIndicatorDate() {
		return indicatorDate;
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

	@Column(name = "METRO_COLUMN_DAILY", precision=20,scale=4)
	public Double getMetroColumnDaily() {
		return metroColumnDaily;
	}

	public void setMetroColumnMonth(Double metroColumnMonth) {
		this.metroColumnMonth = metroColumnMonth;
	}

	@Column(name = "METRO_COLUMN_MONTH",precision=20,scale=4)
	public Double getMetroColumnMonth() {
		return metroColumnMonth;
	}

	public void setMetroColumnYear(Double metroColumnYear) {
		this.metroColumnYear = metroColumnYear;
	}

	@Column(name = "METRO_COLUMN_YEAR", precision=20,scale=4)
	public Double getMetroColumnYear() {
		return metroColumnYear;
	}

	public void setMetroDistanceDaily(Double metroDistanceDaily) {
		this.metroDistanceDaily = metroDistanceDaily;
	}

	@Column(name = "METRO_DISTANCE_DAILY",precision=20,scale=4)
	public Double getMetroDistanceDaily() {
		return metroDistanceDaily;
	}

	public void setMetroDistanceMonth(Double metroDistanceMonth) {
		this.metroDistanceMonth = metroDistanceMonth;
	}

	@Column(name = "METRO_DISTANCE_MONTH", precision=20,scale=4)
	public Double getMetroDistanceMonth() {
		return metroDistanceMonth;
	}

	public void setMetroDistanceYear(Double metroDistanceYear) {
		this.metroDistanceYear = metroDistanceYear;
	}

	@Column(name = "METRO_DISTANCE_YEAR", precision=20,scale=4)
	public Double getMetroDistanceYear() {
		return metroDistanceYear;
	}

	public void setOnlineEightSection(Long onlineEightSection) {
		this.onlineEightSection = onlineEightSection;
	}

	@Column(name = "ONLINE_EIGHT_SECTION", precision=20)
	public Long getOnlineEightSection() {
		return onlineEightSection;
	}

	public void setOnlineFourSection(Long onlineFourSection) {
		this.onlineFourSection = onlineFourSection;
	}

	@Column(name = "ONLINE_FOUR_SECTION", precision=20)
	public Long getOnlineFourSection() {
		return onlineFourSection;
	}

	public void setOnlineSevenSection(Long onlineSevenSection) {
		this.onlineSevenSection = onlineSevenSection;
	}

	@Column(name = "ONLINE_SEVEN_SECTION", precision=20)
	public Long getOnlineSevenSection() {
		return onlineSevenSection;
	}

	public void setOnlineSixSection(Long onlineSixSection) {
		this.onlineSixSection = onlineSixSection;
	}

	@Column(name = "ONLINE_SIX_SECTION", precision=20)
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

	@Column(name = "PASSENGER_CAPACITY_DAILY",precision=20)
	public Long getPassengerCapacityDaily() {
		return passengerCapacityDaily;
	}

	public void setPassengerCapacityMonth(Long passengerCapacityMonth) {
		this.passengerCapacityMonth = passengerCapacityMonth;
	}

	@Column(name = "PASSENGER_CAPACITY_MONTH", precision=20)
	public Long getPassengerCapacityMonth() {
		return passengerCapacityMonth;
	}

	public void setPassengerCapacityYear(Long passengerCapacityYear) {
		this.passengerCapacityYear = passengerCapacityYear;
	}

	@Column(name = "PASSENGER_CAPACITY_YEAR", precision=20)
	public Long getPassengerCapacityYear() {
		return passengerCapacityYear;
	}

	public void setPassengerTransferDaily(Long passengerTransferDaily) {
		this.passengerTransferDaily = passengerTransferDaily;
	}

	@Column(name = "PASSENGER_TRANSFER_DAILY", precision=20)
	public Long getPassengerTransferDaily() {
		return passengerTransferDaily;
	}

	public void setPassengerTransferMonth(Long passengerTransferMonth) {
		this.passengerTransferMonth = passengerTransferMonth;
	}

	@Column(name = "PASSENGER_TRANSFER_MONTH", precision=20)
	public Long getPassengerTransferMonth() {
		return passengerTransferMonth;
	}

	public void setPassengerTransferYear(Long passengerTransferYear) {
		this.passengerTransferYear = passengerTransferYear;
	}

	@Column(name = "PASSENGER_TRANSFER_YEAR", precision=20)
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

	@Column(name = "TICKET_INCOME_DAILY", precision=20,scale=4)
	public Double getTicketIncomeDaily() {
		return ticketIncomeDaily;
	}

	public void setTicketIncomeMonth(Double ticketIncomeMonth) {
		this.ticketIncomeMonth = ticketIncomeMonth;
	}

	@Column(name = "TICKET_INCOME_MONTH", precision=20,scale=4)
	public Double getTicketIncomeMonth() {
		return ticketIncomeMonth;
	}

	public void setTicketIncomeYear(Double ticketIncomeYear) {
		this.ticketIncomeYear = ticketIncomeYear;
	}

	@Column(name = "TICKET_INCOME_YEAR", precision=20,scale=4)
	public Double getTicketIncomeYear() {
		return ticketIncomeYear;
	}

	@Column(name = "BACKUP_THREE_SECTION", precision=20)
	public Long getBackupThreeSection() {
		return backupThreeSection;
	}

	public void setBackupThreeSection(Long backupThreeSection) {
		this.backupThreeSection = backupThreeSection;
	}

	@Column(name = "ONLINE_THREE_SECTION", precision=20)
	public Long getOnlineThreeSection() {
		return onlineThreeSection;
	}

	public void setOnlineThreeSection(Long onlineThreeSection) {
		this.onlineThreeSection = onlineThreeSection;
	}

	
}
