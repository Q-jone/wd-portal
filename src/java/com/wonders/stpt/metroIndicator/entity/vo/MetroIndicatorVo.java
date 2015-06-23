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
public class MetroIndicatorVo {
	private String indicatorDate; // indicatorDate

	private String indicatorLine; // indicatorLine

	private String allocateEightSection; // allocateEightSection

	private String allocateFourSection; // allocateFourSection

	private String allocateSevenSection; // allocateSevenSection

	private String allocateSixSection; // allocateSixSection
	
	private String allocateThreeSection; // allocateThreeSection
	
	private String lineDistance; // lineDistance

	private String stationCount; // stationCount

	
	private String metroDistanceDaily; // metroDistanceDaily

	private String metroDistanceMonth; // metroDistanceMonth

	private String metroDistanceYear; // metroDistanceYear
	
	
	private String metroOntimeDaily; // metroOntimeDaily

	private String metroOntimeMonth; // metroOntimeMonth

	private String metroOntimeYear; // metroOntimeYear

	private String metroOnworkDaily; // metroOnworkDaily

	private String metroOnworkMonth; // metroOnworkMonth

	private String metroOnworkYear; // metroOnworkYear
	
	private String passengerCapacityDaily; // passengerCapacityDaily

	private String passengerCapacityMonth; // passengerCapacityMonth

	private String passengerCapacityYear; // passengerCapacityYear

	private String ticketIncomeDaily; // ticketIncomeDaily

	private String ticketIncomeMonth; // ticketIncomeMonth

	private String ticketIncomeYear; // ticketIncomeYear
	
	private String metroOntimeDailyLast;	//去年同期
	private String metroOnworkDailyLast;//去年同期
	private String passengerCapacityDailyLast;//去年同期
	private String metroDistanceDailyLast;
	private String ticketIncomeDailyLast;
	
	private String metroOntimeDailyCompare;	//去年同比
	private String metroOnworkDailyCompare;//去年同比
	private String passengerCapacityDailyCompare;//去年同比
	
	public String convertDoubleToDouble(java.text.DecimalFormat df,Double s){
		String result = "";
		if(s==null){
			result = " ";
		}else{
			result = df.format(s*100);
		}
		return result;
	}
	
	public String convertLongToDouble(java.text.DecimalFormat df,Long s){
		String result = "";
		if(s==null){
			result = " ";
		}else{
			result = df.format((double)s/10000);
		}
		return result;
	}
	
	public String convertDoubleToDouble2(java.text.DecimalFormat df,Double s){
		String result = "";
		if(s==null){
			result = " ";
		}else{
			result = df.format((double)s/10000);
		}
		return result;
	}
	
	
	
	public MetroIndicatorVo(MetroIndicator m){
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		
		java.text.DecimalFormat df2 = new java.text.DecimalFormat("0.0000");
		 
		this.indicatorDate = m.getIndicatorDate();
		this.indicatorLine = m.getIndicatorLine();
		this.allocateEightSection = m.getAllocateEightSection()+"";
		this.allocateFourSection = m.getAllocateFourSection()+"";
		this.allocateSevenSection = m.getAllocateSevenSection()+"";
		this.allocateSixSection = m.getAllocateSixSection()+"";
		this.allocateThreeSection = m.getAllocateThreeSection()+"";
		this.stationCount = m.getStationCount()+"";
		this.lineDistance = df2.format(m.getLineDistance()*0.001);
		this.metroOntimeDaily = this.convertDoubleToDouble(df, m.getMetroOntimeDaily());
		this.metroOntimeMonth = this.convertDoubleToDouble(df, m.getMetroOntimeMonth());
		this.metroOntimeYear = this.convertDoubleToDouble(df, m.getMetroOntimeYear());
		this.metroOnworkDaily = this.convertDoubleToDouble(df, m.getMetroOnworkDaily());
		this.metroOnworkMonth = this.convertDoubleToDouble(df, m.getMetroOnworkMonth());
		this.metroOnworkYear = this.convertDoubleToDouble(df, m.getMetroOnworkYear());
		
		this.metroDistanceDaily = this.convertDoubleToDouble2(df2, m.getMetroDistanceDaily()*0.001);
		this.metroDistanceMonth = this.convertDoubleToDouble2(df2, m.getMetroDistanceMonth()*0.001);
		this.metroDistanceYear = this.convertDoubleToDouble2(df2, m.getMetroDistanceYear()*0.001);
		this.ticketIncomeDaily = this.convertDoubleToDouble2(df2, m.getTicketIncomeDaily());
		this.ticketIncomeMonth = this.convertDoubleToDouble2(df2, m.getTicketIncomeMonth());
		this.ticketIncomeYear = this.convertDoubleToDouble2(df2, m.getTicketIncomeYear());

		this.passengerCapacityDaily = this.convertLongToDouble(df2, m.getPassengerCapacityDaily());
		this.passengerCapacityMonth = this.convertLongToDouble(df2, m.getPassengerCapacityMonth());
		this.passengerCapacityYear = this.convertLongToDouble(df2, m.getPassengerCapacityYear());

		this.metroOntimeDailyLast = this.convertDoubleToDouble(df, m.getMetroOntimeDailyLast());
		this.metroOnworkDailyLast = this.convertDoubleToDouble(df, m.getMetroOnworkDailyLast());
		this.passengerCapacityDailyLast = this.convertLongToDouble(df2, m.getPassengerCapacityDailyLast());
		
		this.metroDistanceDailyLast = this.convertDoubleToDouble2(df2, m.getMetroDistanceDailyLast()*0.001);
		this.ticketIncomeDailyLast = this.convertDoubleToDouble2(df2, m.getTicketIncomeDailyLast());
		
		if(m.getMetroOntimeDaily()==null||m.getMetroOntimeDailyLast()==null||m.getMetroOntimeDailyLast()==0.0){
			this.metroOntimeDailyCompare = df.format(0.0);
		}else{
			this.metroOntimeDailyCompare = df.format(m.getMetroOntimeDaily()/m.getMetroOntimeDailyLast()*100);
		}
		if(m.getMetroOnworkDaily()==null||m.getMetroOnworkDailyLast()==null||m.getMetroOnworkDailyLast()==0.0){
			this.metroOnworkDailyCompare = df.format(0.0);
		}else{
			this.metroOnworkDailyCompare = df.format(m.getMetroOnworkDaily()/m.getMetroOnworkDailyLast()*100);
		}
		if(m.getPassengerCapacityDaily()==null||m.getPassengerCapacityDailyLast()==null||m.getPassengerCapacityDailyLast()==0L){
			this.passengerCapacityDailyCompare = df.format(0.0);
		}else{
			this.passengerCapacityDailyCompare = df.format((double)m.getPassengerCapacityDaily()/(double)m.getPassengerCapacityDailyLast()*100);
		}
	}
	
	
	public String getAllocateEightSection() {
		return allocateEightSection;
	}

	public void setAllocateEightSection(String allocateEightSection) {
		this.allocateEightSection = allocateEightSection;
	}

	public String getAllocateFourSection() {
		return allocateFourSection;
	}

	public void setAllocateFourSection(String allocateFourSection) {
		this.allocateFourSection = allocateFourSection;
	}

	public String getAllocateSevenSection() {
		return allocateSevenSection;
	}

	public void setAllocateSevenSection(String allocateSevenSection) {
		this.allocateSevenSection = allocateSevenSection;
	}

	public String getAllocateSixSection() {
		return allocateSixSection;
	}

	public void setAllocateSixSection(String allocateSixSection) {
		this.allocateSixSection = allocateSixSection;
	}

	public String getLineDistance() {
		return lineDistance;
	}

	public void setLineDistance(String lineDistance) {
		this.lineDistance = lineDistance;
	}

	public String getStationCount() {
		return stationCount;
	}

	public void setStationCount(String stationCount) {
		this.stationCount = stationCount;
	}

	public String getMetroDistanceDaily() {
		return metroDistanceDaily;
	}

	public void setMetroDistanceDaily(String metroDistanceDaily) {
		this.metroDistanceDaily = metroDistanceDaily;
	}

	public String getMetroDistanceMonth() {
		return metroDistanceMonth;
	}

	public void setMetroDistanceMonth(String metroDistanceMonth) {
		this.metroDistanceMonth = metroDistanceMonth;
	}

	public String getMetroDistanceYear() {
		return metroDistanceYear;
	}

	public void setMetroDistanceYear(String metroDistanceYear) {
		this.metroDistanceYear = metroDistanceYear;
	}

	public String getTicketIncomeDaily() {
		return ticketIncomeDaily;
	}

	public void setTicketIncomeDaily(String ticketIncomeDaily) {
		this.ticketIncomeDaily = ticketIncomeDaily;
	}

	public String getTicketIncomeMonth() {
		return ticketIncomeMonth;
	}

	public void setTicketIncomeMonth(String ticketIncomeMonth) {
		this.ticketIncomeMonth = ticketIncomeMonth;
	}

	public String getTicketIncomeYear() {
		return ticketIncomeYear;
	}

	public void setTicketIncomeYear(String ticketIncomeYear) {
		this.ticketIncomeYear = ticketIncomeYear;
	}

	public String getMetroDistanceDailyLast() {
		return metroDistanceDailyLast;
	}

	public void setMetroDistanceDailyLast(String metroDistanceDailyLast) {
		this.metroDistanceDailyLast = metroDistanceDailyLast;
	}

	public String getTicketIncomeDailyLast() {
		return ticketIncomeDailyLast;
	}

	public void setTicketIncomeDailyLast(String ticketIncomeDailyLast) {
		this.ticketIncomeDailyLast = ticketIncomeDailyLast;
	}

	public String getMetroOntimeDailyCompare() {
		return metroOntimeDailyCompare;
	}



	public void setMetroOntimeDailyCompare(String metroOntimeDailyCompare) {
		this.metroOntimeDailyCompare = metroOntimeDailyCompare;
	}



	public String getMetroOnworkDailyCompare() {
		return metroOnworkDailyCompare;
	}



	public void setMetroOnworkDailyCompare(String metroOnworkDailyCompare) {
		this.metroOnworkDailyCompare = metroOnworkDailyCompare;
	}



	public String getPassengerCapacityDailyCompare() {
		return passengerCapacityDailyCompare;
	}



	public void setPassengerCapacityDailyCompare(
			String passengerCapacityDailyCompare) {
		this.passengerCapacityDailyCompare = passengerCapacityDailyCompare;
	}



	public String getMetroOntimeDailyLast() {
		return metroOntimeDailyLast;
	}


	public void setMetroOntimeDailyLast(String metroOntimeDailyLast) {
		this.metroOntimeDailyLast = metroOntimeDailyLast;
	}


	public String getMetroOnworkDailyLast() {
		return metroOnworkDailyLast;
	}


	public void setMetroOnworkDailyLast(String metroOnworkDailyLast) {
		this.metroOnworkDailyLast = metroOnworkDailyLast;
	}


	public String getPassengerCapacityDailyLast() {
		return passengerCapacityDailyLast;
	}


	public void setPassengerCapacityDailyLast(String passengerCapacityDailyLast) {
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

	public String getMetroOntimeDaily() {
		return metroOntimeDaily;
	}

	public void setMetroOntimeDaily(String metroOntimeDaily) {
		this.metroOntimeDaily = metroOntimeDaily;
	}

	public String getMetroOntimeMonth() {
		return metroOntimeMonth;
	}

	public void setMetroOntimeMonth(String metroOntimeMonth) {
		this.metroOntimeMonth = metroOntimeMonth;
	}

	public String getMetroOntimeYear() {
		return metroOntimeYear;
	}

	public void setMetroOntimeYear(String metroOntimeYear) {
		this.metroOntimeYear = metroOntimeYear;
	}

	public String getMetroOnworkDaily() {
		return metroOnworkDaily;
	}

	public void setMetroOnworkDaily(String metroOnworkDaily) {
		this.metroOnworkDaily = metroOnworkDaily;
	}

	public String getMetroOnworkMonth() {
		return metroOnworkMonth;
	}

	public void setMetroOnworkMonth(String metroOnworkMonth) {
		this.metroOnworkMonth = metroOnworkMonth;
	}

	public String getMetroOnworkYear() {
		return metroOnworkYear;
	}

	public void setMetroOnworkYear(String metroOnworkYear) {
		this.metroOnworkYear = metroOnworkYear;
	}

	public String getPassengerCapacityDaily() {
		return passengerCapacityDaily;
	}

	public void setPassengerCapacityDaily(String passengerCapacityDaily) {
		this.passengerCapacityDaily = passengerCapacityDaily;
	}

	public String getPassengerCapacityMonth() {
		return passengerCapacityMonth;
	}

	public void setPassengerCapacityMonth(String passengerCapacityMonth) {
		this.passengerCapacityMonth = passengerCapacityMonth;
	}

	public String getPassengerCapacityYear() {
		return passengerCapacityYear;
	}

	public void setPassengerCapacityYear(String passengerCapacityYear) {
		this.passengerCapacityYear = passengerCapacityYear;
	}

	public String getAllocateThreeSection() {
		return allocateThreeSection;
	}

	public void setAllocateThreeSection(String allocateThreeSection) {
		this.allocateThreeSection = allocateThreeSection;
	}

	

	
}
