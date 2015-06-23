/**
 * 
 */
package com.wonders.stpt.metroIndicator.entity.vo;

/** 
 * @ClassName: MetroIndicator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun
 * @date 2012-3-6 下午08:05:12 
 *  
 */
public class MetroIndicator {
	private String indicatorDate; // indicatorDate

	private String indicatorLine; // indicatorLine
	
	private Long allocateEightSection; // allocateEightSection

	private Long allocateFourSection; // allocateFourSection

	private Long allocateSevenSection; // allocateSevenSection

	private Long allocateSixSection; // allocateSixSection
	
	private Long allocateThreeSection; // allocateSixSection
	
	private Double lineDistance; // lineDistance

	private Long stationCount; // stationCount

	private Double metroDistanceDaily; // metroDistanceDaily

	private Double metroDistanceMonth; // metroDistanceMonth

	private Double metroDistanceYear; // metroDistanceYear
	
	private Double metroOntimeDaily; // metroOntimeDaily

	private Double metroOntimeMonth; // metroOntimeMonth

	private Double metroOntimeYear; // metroOntimeYear

	private Double metroOnworkDaily; // metroOnworkDaily

	private Double metroOnworkMonth; // metroOnworkMonth

	private Double metroOnworkYear; // metroOnworkYear
	
	private Long passengerCapacityDaily; // passengerCapacityDaily

	private Long passengerCapacityMonth; // passengerCapacityMonth

	private Long passengerCapacityYear; // passengerCapacityYear

	private Double ticketIncomeDaily; // ticketIncomeDaily

	private Double ticketIncomeMonth; // ticketIncomeMonth

	private Double ticketIncomeYear; // ticketIncomeYear

	private Double metroOntimeDailyLast;
	private Double metroOnworkDailyLast;
	private Long passengerCapacityDailyLast;
	private Double metroDistanceDailyLast;
	private Double ticketIncomeDailyLast;
	
	public MetroIndicator(){
		this.indicatorDate = "";
		this.metroDistanceDaily = 0.0;
		this.metroDistanceMonth = 0.0;
		this.metroDistanceYear = 0.0;
		this.metroOntimeYear = 0.0;
		this.metroOntimeDaily = 0.0;
		this.metroOntimeMonth = 0.0;
		this.metroOntimeYear = 0.0;
		this.metroOnworkDaily = 0.0;
		this.metroOnworkMonth = 0.0;
		this.metroOnworkYear = 0.0;
		this.passengerCapacityDaily = 0L;
		this.passengerCapacityMonth = 0L;
		this.passengerCapacityYear = 0L;
		this.ticketIncomeDaily = 0.0;
		this.ticketIncomeMonth = 0.0;
		this.ticketIncomeYear = 0.0;
		this.metroOntimeDailyLast = 0.0;
		this.metroOnworkDailyLast = 0.0;
		this.passengerCapacityDailyLast = 0L;
		this.metroDistanceDailyLast = 0.0;
		this.ticketIncomeDailyLast = 0.0;
		this.allocateThreeSection = 0L;
		this.allocateEightSection = 0L;
		this.allocateFourSection = 0L;
		this.allocateSevenSection = 0L;
		this.allocateSixSection = 0L;
		this.lineDistance = 0.0;
		this.stationCount = 0L;
	}
	
	
	
	public Long getAllocateEightSection() {
		return allocateEightSection;
	}



	public void setAllocateEightSection(Long allocateEightSection) {
		this.allocateEightSection = allocateEightSection;
	}



	public Long getAllocateFourSection() {
		return allocateFourSection;
	}



	public void setAllocateFourSection(Long allocateFourSection) {
		this.allocateFourSection = allocateFourSection;
	}



	public Long getAllocateSevenSection() {
		return allocateSevenSection;
	}



	public void setAllocateSevenSection(Long allocateSevenSection) {
		this.allocateSevenSection = allocateSevenSection;
	}



	public Long getAllocateSixSection() {
		return allocateSixSection;
	}



	public void setAllocateSixSection(Long allocateSixSection) {
		this.allocateSixSection = allocateSixSection;
	}



	public Double getLineDistance() {
		return lineDistance;
	}



	public void setLineDistance(Double lineDistance) {
		this.lineDistance = lineDistance;
	}



	public Long getStationCount() {
		return stationCount;
	}



	public void setStationCount(Long stationCount) {
		this.stationCount = stationCount;
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



	public Double getMetroDistanceDailyLast() {
		return metroDistanceDailyLast;
	}



	public void setMetroDistanceDailyLast(Double metroDistanceDailyLast) {
		this.metroDistanceDailyLast = metroDistanceDailyLast;
	}



	public Double getTicketIncomeDailyLast() {
		return ticketIncomeDailyLast;
	}



	public void setTicketIncomeDailyLast(Double ticketIncomeDailyLast) {
		this.ticketIncomeDailyLast = ticketIncomeDailyLast;
	}



	public Double getMetroOntimeDailyLast() {
		return metroOntimeDailyLast;
	}


	public void setMetroOntimeDailyLast(Double metroOntimeDailyLast) {
		this.metroOntimeDailyLast = metroOntimeDailyLast;
	}


	public Double getMetroOnworkDailyLast() {
		return metroOnworkDailyLast;
	}


	public void setMetroOnworkDailyLast(Double metroOnworkDailyLast) {
		this.metroOnworkDailyLast = metroOnworkDailyLast;
	}


	public Long getPassengerCapacityDailyLast() {
		return passengerCapacityDailyLast;
	}


	public void setPassengerCapacityDailyLast(Long passengerCapacityDailyLast) {
		this.passengerCapacityDailyLast = passengerCapacityDailyLast;
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

	public Double getMetroOntimeDaily() {
		return metroOntimeDaily;
	}

	public void setMetroOntimeDaily(Double metroOntimeDaily) {
		this.metroOntimeDaily = metroOntimeDaily;
	}

	public Double getMetroOntimeMonth() {
		return metroOntimeMonth;
	}

	public void setMetroOntimeMonth(Double metroOntimeMonth) {
		this.metroOntimeMonth = metroOntimeMonth;
	}

	public Double getMetroOntimeYear() {
		return metroOntimeYear;
	}

	public void setMetroOntimeYear(Double metroOntimeYear) {
		this.metroOntimeYear = metroOntimeYear;
	}

	public Double getMetroOnworkDaily() {
		return metroOnworkDaily;
	}

	public void setMetroOnworkDaily(Double metroOnworkDaily) {
		this.metroOnworkDaily = metroOnworkDaily;
	}

	public Double getMetroOnworkMonth() {
		return metroOnworkMonth;
	}

	public void setMetroOnworkMonth(Double metroOnworkMonth) {
		this.metroOnworkMonth = metroOnworkMonth;
	}

	public Double getMetroOnworkYear() {
		return metroOnworkYear;
	}

	public void setMetroOnworkYear(Double metroOnworkYear) {
		this.metroOnworkYear = metroOnworkYear;
	}

	public Long getPassengerCapacityDaily() {
		return passengerCapacityDaily;
	}

	public void setPassengerCapacityDaily(Long passengerCapacityDaily) {
		this.passengerCapacityDaily = passengerCapacityDaily;
	}

	public Long getPassengerCapacityMonth() {
		return passengerCapacityMonth;
	}

	public void setPassengerCapacityMonth(Long passengerCapacityMonth) {
		this.passengerCapacityMonth = passengerCapacityMonth;
	}

	public Long getPassengerCapacityYear() {
		return passengerCapacityYear;
	}

	public void setPassengerCapacityYear(Long passengerCapacityYear) {
		this.passengerCapacityYear = passengerCapacityYear;
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



	public Long getAllocateThreeSection() {
		return allocateThreeSection;
	}



	public void setAllocateThreeSection(Long allocateThreeSection) {
		this.allocateThreeSection = allocateThreeSection;
	}
	
	
	
}
