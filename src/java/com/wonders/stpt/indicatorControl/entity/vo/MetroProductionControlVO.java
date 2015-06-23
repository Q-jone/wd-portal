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

package com.wonders.stpt.indicatorControl.entity.vo;

import java.text.DecimalFormat;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wonders.stpt.indicatorControl.entity.bo.MetroProductionControl;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * MetroProductionControlʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroProductionControlVO implements ValueObject {

	
	private static final long serialVersionUID = -2718504464805090216L;
	private String id;
	private String backupEightSection;
	private String backupFourSection;
	private String backupSevenSection;
	private String backupSixSection;
	private String backupThreeSection;
	private String ext1;
	private String ext2;
	private String ext3;
	private String indicatorLine;
	private String metroColumnDaily;
	private String metroColumnMonth;
	private String metroColumnYear;
	private String metroDistanceDaily;
	private String metroDistanceMonth;
	private String metroDistanceYear;
	private String onlineEightSection;
	private String onlineFourSection;
	private String onlineSevenSection;
	private String onlineSixSection;
	private String onlineThreeSection;
	private String operatePerson;
	private String operateTime;
	private String passengerCapacityDaily;
	private String passengerCapacityMonth;
	private String passengerCapacityYear;
	private String passengerTransferDaily;
	private String passengerTransferMonth;
	private String passengerTransferYear;
	private String removed;
	private String ticketIncomeDaily;
	private String ticketIncomeMonth;
	private String ticketIncomeYear;
	private final static DecimalFormat df = new DecimalFormat("0.0000");
	
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

	public String getId() {
		return id;
	}

	public void setBackupEightSection(String backupEightSection) {
		this.backupEightSection = backupEightSection;
	}

	public String getBackupEightSection() {
		return backupEightSection;
	}

	public void setBackupFourSection(String backupFourSection) {
		this.backupFourSection = backupFourSection;
	}

	public String getBackupFourSection() {
		return backupFourSection;
	}

	public void setBackupSevenSection(String backupSevenSection) {
		this.backupSevenSection = backupSevenSection;
	}

	public String getBackupSevenSection() {
		return backupSevenSection;
	}

	public void setBackupSixSection(String backupSixSection) {
		this.backupSixSection = backupSixSection;
	}

	public String getBackupSixSection() {
		return backupSixSection;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt3() {
		return ext3;
	}

	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}

	public String getIndicatorLine() {
		return indicatorLine;
	}

	public void setMetroColumnDaily(String metroColumnDaily) {
		this.metroColumnDaily = metroColumnDaily;
	}

	public String getMetroColumnDaily() {
		return metroColumnDaily;
	}

	public void setMetroColumnMonth(String metroColumnMonth) {
		this.metroColumnMonth = metroColumnMonth;
	}

	public String getMetroColumnMonth() {
		return metroColumnMonth;
	}

	public void setMetroColumnYear(String metroColumnYear) {
		this.metroColumnYear = metroColumnYear;
	}

	public String getMetroColumnYear() {
		return metroColumnYear;
	}

	public void setMetroDistanceDaily(String metroDistanceDaily) {
		this.metroDistanceDaily = metroDistanceDaily;
	}

	public String getMetroDistanceDaily() {
		return metroDistanceDaily;
	}

	public void setMetroDistanceMonth(String metroDistanceMonth) {
		this.metroDistanceMonth = metroDistanceMonth;
	}

	public String getMetroDistanceMonth() {
		return metroDistanceMonth;
	}

	public void setMetroDistanceYear(String metroDistanceYear) {
		this.metroDistanceYear = metroDistanceYear;
	}

	public String getMetroDistanceYear() {
		return metroDistanceYear;
	}

	public void setOnlineEightSection(String onlineEightSection) {
		this.onlineEightSection = onlineEightSection;
	}

	public String getOnlineEightSection() {
		return onlineEightSection;
	}

	public void setOnlineFourSection(String onlineFourSection) {
		this.onlineFourSection = onlineFourSection;
	}

	public String getOnlineFourSection() {
		return onlineFourSection;
	}

	public void setOnlineSevenSection(String onlineSevenSection) {
		this.onlineSevenSection = onlineSevenSection;
	}

	public String getOnlineSevenSection() {
		return onlineSevenSection;
	}

	public void setOnlineSixSection(String onlineSixSection) {
		this.onlineSixSection = onlineSixSection;
	}

	public String getOnlineSixSection() {
		return onlineSixSection;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setPassengerCapacityDaily(String passengerCapacityDaily) {
		this.passengerCapacityDaily = passengerCapacityDaily;
	}

	public String getPassengerCapacityDaily() {
		return passengerCapacityDaily;
	}

	public void setPassengerCapacityMonth(String passengerCapacityMonth) {
		this.passengerCapacityMonth = passengerCapacityMonth;
	}

	public String getPassengerCapacityMonth() {
		return passengerCapacityMonth;
	}

	public void setPassengerCapacityYear(String passengerCapacityYear) {
		this.passengerCapacityYear = passengerCapacityYear;
	}

	public String getPassengerCapacityYear() {
		return passengerCapacityYear;
	}

	public void setPassengerTransferDaily(String passengerTransferDaily) {
		this.passengerTransferDaily = passengerTransferDaily;
	}

	public String getPassengerTransferDaily() {
		return passengerTransferDaily;
	}

	public void setPassengerTransferMonth(String passengerTransferMonth) {
		this.passengerTransferMonth = passengerTransferMonth;
	}

	public String getPassengerTransferMonth() {
		return passengerTransferMonth;
	}

	public void setPassengerTransferYear(String passengerTransferYear) {
		this.passengerTransferYear = passengerTransferYear;
	}

	public String getPassengerTransferYear() {
		return passengerTransferYear;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public void setTicketIncomeDaily(String ticketIncomeDaily) {
		this.ticketIncomeDaily = ticketIncomeDaily;
	}

	public String getTicketIncomeDaily() {
		return ticketIncomeDaily;
	}

	public void setTicketIncomeMonth(String ticketIncomeMonth) {
		this.ticketIncomeMonth = ticketIncomeMonth;
	}

	public String getTicketIncomeMonth() {
		return ticketIncomeMonth;
	}

	public void setTicketIncomeYear(String ticketIncomeYear) {
		this.ticketIncomeYear = ticketIncomeYear;
	}

	public String getTicketIncomeYear() {
		return ticketIncomeYear;
	}
	
	public String getMetroColumnControl() {
		return metroColumnControl;
	}

	public void setMetroColumnControl(String metroColumnControl) {
		this.metroColumnControl = metroColumnControl;
	}

	public String getMetroDistanceControl() {
		return metroDistanceControl;
	}

	public void setMetroDistanceControl(String metroDistanceControl) {
		this.metroDistanceControl = metroDistanceControl;
	}

	public String getPassengerCapacityControl() {
		return passengerCapacityControl;
	}

	public void setPassengerCapacityControl(String passengerCapacityControl) {
		this.passengerCapacityControl = passengerCapacityControl;
	}

	public String getPassengerTransferControl() {
		return passengerTransferControl;
	}

	public void setPassengerTransferControl(String passengerTransferControl) {
		this.passengerTransferControl = passengerTransferControl;
	}

	public String getTicketIncomeControl() {
		return ticketIncomeControl;
	}

	public void setTicketIncomeControl(String ticketIncomeControl) {
		this.ticketIncomeControl = ticketIncomeControl;
	}

	public String getMetroColumnDescribe() {
		return metroColumnDescribe;
	}

	public void setMetroColumnDescribe(String metroColumnDescribe) {
		this.metroColumnDescribe = metroColumnDescribe;
	}

	public String getMetroDistanceDescribe() {
		return metroDistanceDescribe;
	}

	public void setMetroDistanceDescribe(String metroDistanceDescribe) {
		this.metroDistanceDescribe = metroDistanceDescribe;
	}

	public String getPassengerCapacityDescribe() {
		return passengerCapacityDescribe;
	}

	public void setPassengerCapacityDescribe(String passengerCapacityDescribe) {
		this.passengerCapacityDescribe = passengerCapacityDescribe;
	}

	public String getPassengerTransferDescribe() {
		return passengerTransferDescribe;
	}

	public void setPassengerTransferDescribe(String passengerTransferDescribe) {
		this.passengerTransferDescribe = passengerTransferDescribe;
	}

	public String getTicketIncomeDescribe() {
		return ticketIncomeDescribe;
	}

	public void setTicketIncomeDescribe(String ticketIncomeDescribe) {
		this.ticketIncomeDescribe = ticketIncomeDescribe;
	}

	
	public String getBackupThreeSection() {
		return backupThreeSection;
	}

	public void setBackupThreeSection(String backupThreeSection) {
		this.backupThreeSection = backupThreeSection;
	}

	public String getOnlineThreeSection() {
		return onlineThreeSection;
	}

	public void setOnlineThreeSection(String onlineThreeSection) {
		this.onlineThreeSection = onlineThreeSection;
	}

	private String killLastZero(String str){
		if(str==null)
			return null;
		if(str.contains(".") && (str.endsWith("0") || str.endsWith("."))){
			str = str.substring(0,str.length()-1);
			return killLastZero(str);
		}else{
			return str;
		}
	}

	public MetroProductionControlVO(MetroProductionControl mpc) {
		this.id = mpc.getId();
		this.backupEightSection = String.valueOf(mpc.getBackupEightSection());
		this.backupFourSection = String.valueOf(mpc.getBackupFourSection());
		this.backupSevenSection = String.valueOf(mpc.getBackupEightSection());
		this.backupSixSection = String.valueOf(mpc.getBackupSixSection());
		this.backupThreeSection = String.valueOf(mpc.getBackupThreeSection());
		this.ext1 = mpc.getExt1();
		this.ext2 = mpc.getExt2();
		this.ext3 = mpc.getExt3();
		this.indicatorLine = mpc.getIndicatorLine();
		this.metroColumnDaily = killLastZero(mpc.getMetroColumnDaily()!=null ? df.format(mpc.getMetroColumnDaily()) : null);
		this.metroColumnMonth = killLastZero(mpc.getMetroColumnMonth()!=null ? df.format(mpc.getMetroColumnMonth()) : null);
		this.metroColumnYear = killLastZero(mpc.getMetroColumnYear()!=null ? df.format(mpc.getMetroColumnYear()) : null);
		this.metroDistanceDaily = killLastZero(mpc.getMetroDistanceDaily()!=null ? df.format(mpc.getMetroDistanceDaily()/10000) :null);
		this.metroDistanceMonth = killLastZero(mpc.getMetroDistanceMonth()!=null ? df.format(mpc.getMetroDistanceMonth()/10000) :null);
		this.metroDistanceYear = killLastZero(mpc.getMetroDistanceYear() !=null ? df.format(mpc.getMetroDistanceYear()/10000):null);
		this.onlineEightSection = String.valueOf(mpc.getOnlineEightSection());
		this.onlineFourSection = String.valueOf(mpc.getOnlineFourSection());
		this.onlineSevenSection = String.valueOf(mpc.getOnlineSevenSection());
		this.onlineSixSection = String.valueOf(mpc.getOnlineSixSection());
		this.onlineThreeSection = String.valueOf(mpc.getOnlineThreeSection());
		this.operatePerson = mpc.getOperatePerson();
		this.operateTime = mpc.getOperateTime();
		this.passengerCapacityDaily = killLastZero(mpc.getPassengerCapacityDaily()!=null ? df.format(Double.valueOf(mpc.getPassengerCapacityDaily())/10000) : null);
		this.passengerCapacityMonth = killLastZero(mpc.getPassengerCapacityMonth()!=null ? df.format(Double.valueOf(mpc.getPassengerCapacityMonth())/10000):null);
		this.passengerCapacityYear = killLastZero(mpc.getPassengerCapacityYear()!=null ? df.format(Double.valueOf(mpc.getPassengerCapacityYear())/10000):null);
		this.passengerTransferDaily = killLastZero(mpc.getPassengerTransferDaily()!=null ? df.format(Double.valueOf(mpc.getPassengerTransferDaily())/10000):null);
		this.passengerTransferMonth = killLastZero(mpc.getPassengerTransferMonth()!=null ? df.format(Double.valueOf(mpc.getPassengerTransferMonth())/10000):null);
		this.passengerTransferYear = killLastZero(mpc.getPassengerTransferYear()!=null ? df.format(Double.valueOf(mpc.getPassengerTransferYear())/10000):null);
		this.removed = mpc.getRemoved();
		this.ticketIncomeDaily = killLastZero(mpc.getTicketIncomeDaily()!=null ? df.format(mpc.getTicketIncomeDaily()/10000):null);
		this.ticketIncomeMonth = killLastZero(mpc.getTicketIncomeMonth()!=null ? df.format(mpc.getTicketIncomeMonth()/10000):null);
		this.ticketIncomeYear = killLastZero(mpc.getTicketIncomeYear()!=null ? df.format(mpc.getTicketIncomeYear()/10000):null);
		
		this.metroColumnControl = mpc.getMetroColumnControl();
		this.metroDistanceControl = mpc.getMetroDistanceControl();
		this.passengerCapacityControl = mpc.getPassengerCapacityControl();
		this.passengerTransferControl = mpc.getPassengerTransferControl();
		this.ticketIncomeControl = mpc.getTicketIncomeControl();
		
		this.metroColumnDescribe = mpc.getMetroColumnDescribe();
		this.metroDistanceDescribe = mpc.getMetroDistanceDescribe();
		this.passengerCapacityDescribe = mpc.getPassengerCapacityDescribe();
		this.passengerTransferDescribe = mpc.getPassengerTransferDescribe();
		this.ticketIncomeDescribe = mpc.getTicketIncomeDescribe();
	}

	public MetroProductionControlVO() {
		super();
	}
	
	private String convertVoValueToBoValue(String data){
		if(data==null)
			return data;
		return killLastZero(df.format(Double.valueOf(data)*10000));
	}

	public MetroProductionControlVO getVoValueToBoValue(MetroProductionControlVO vo){
		vo.setMetroDistanceDaily(convertVoValueToBoValue(vo.getMetroDistanceDaily()));
		vo.setMetroDistanceMonth(convertVoValueToBoValue(vo.getMetroDistanceMonth()));
		vo.setMetroDistanceYear(convertVoValueToBoValue(vo.getMetroDistanceYear()));
		
		vo.setPassengerCapacityDaily(convertVoValueToBoValue(vo.getPassengerCapacityDaily()));
		vo.setPassengerCapacityMonth(convertVoValueToBoValue(vo.getPassengerCapacityMonth()));
		vo.setPassengerCapacityYear(convertVoValueToBoValue(vo.getPassengerCapacityYear()));

		vo.setPassengerTransferDaily(convertVoValueToBoValue(vo.getPassengerTransferDaily()));
		vo.setPassengerTransferMonth(convertVoValueToBoValue(vo.getPassengerTransferMonth()));
		vo.setPassengerTransferYear(convertVoValueToBoValue(vo.getPassengerTransferYear()));
		
		vo.setTicketIncomeDaily(convertVoValueToBoValue(vo.getTicketIncomeDaily()));
		vo.setTicketIncomeMonth(convertVoValueToBoValue(vo.getTicketIncomeMonth()));
		vo.setTicketIncomeYear(convertVoValueToBoValue(vo.getTicketIncomeYear()));
		
		return vo;
	}
	
	
}
