package com.wonders.stpt.metroCover.model.vo.production;

import com.google.gson.Gson;
import com.wonders.stpt.metroCover.model.vo.common.MetroIndicatorVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroType;
import com.wonders.stpt.metroCover.util.MetroCoverUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public class MetroProductionVo extends MetroIndicatorVo implements Serializable{

    private double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    //除以 10000 保留2位小数
    private Double getFormatData(Double data,Double division,String format){
        if(data==null) return 0.0;
        data = data / division;
        NumberFormat nf = new DecimalFormat(format);
        return Double.valueOf(nf.format(data));
    }

    //各线路XXX
    List<MetroProductionVo> list = new ArrayList<MetroProductionVo>();

    //设置更新数据
    public void refresh(String lineId, String companyId, String date,MetroType type){
        this.setType(type);
        this.setDate(date);
        List<Double> newPassCapMonthList = new ArrayList<Double>();
        List<Double> newIncomeMonthList = new ArrayList<Double>();
        List<Double> newMetroDistanceMonthList = new ArrayList<Double>();
        List<Integer> newUseMetroList = new ArrayList<Integer>();
        List<Integer> newRunMetroList = new ArrayList<Integer>();
        switch (type) {
            case LINE:
                this.setLineId(lineId);
                //日累计管控值
                this.passCapDailyControl = this.getFormatData(this.passCapDailyControl,10000.0,"0.00");
                this.passCapAvgControl = this.getFormatData(this.passCapAvgControl,10000.0,"0.00");
                this.passCapAvgLastYear = this.getFormatData(this.passCapAvgLastYear,10000.0,"0.00");
                this.passCapAvgMonth = this.getFormatData(this.passCapAvgMonth,10000.0,"0.00");
                this.passCapAvgYear = this.getFormatData(this.passCapAvgYear,10000.0,"0.00");
                this.passCapDaily = this.getFormatData(this.passCapDaily,10000.0,"0.00");
                this.passCapControlYear = this.getFormatData(this.passCapControlYear,10000.0,"0.00");
                this.passCapLast = this.getFormatData(this.passCapLast,10000.0,"0.00");
                this.passCapLastYear = this.getFormatData(this.passCapLastYear,10000.0,"0.00");
                this.passCapMaxLast = this.getFormatData(this.passCapMaxLast,10000.0,"0.00");
                this.passCapMaxYear = this.getFormatData(this.passCapMaxYear,10000.0,"0.00");
                this.passCapPlanMonth = this.getFormatData(this.passCapPlanMonth,10000.0,"0.00");
                this.passCapYear = this.getFormatData(this.passCapYear,10000.0,"0.00");
                //日累计管控值
                this.incomeDailyControl = this.getFormatData(this.incomeDailyControl,10000.0,"0.00");
                this.incomeAvgControl = this.getFormatData(this.incomeAvgControl,10000.0,"0.00");
                this.incomeAvgLastYear = this.getFormatData(this.incomeAvgLastYear,10000.0,"0.00");
                this.incomeAvgMonth = this.getFormatData(this.incomeAvgMonth,10000.0,"0.00");
                this.incomeAvgYear = this.getFormatData(this.incomeAvgYear,10000.0,"0.00");
                this.incomeDaily = this.getFormatData(this.incomeDaily,10000.0,"0.00");
                this.incomeControlYear = this.getFormatData(this.incomeControlYear,10000.0,"0.00");
                this.incomeLast = this.getFormatData(this.incomeLast,10000.0,"0.00");
                this.incomeLastYear = this.getFormatData(this.incomeLastYear,10000.0,"0.00");
                this.incomeMaxLast = this.getFormatData(this.incomeMaxLast,10000.0,"0.00");
                this.incomeMaxYear = this.getFormatData(this.incomeMaxYear,10000.0,"0.00");
                this.incomePlanMonth = this.getFormatData(this.incomePlanMonth,10000.0,"0.00");
                this.incomeYear = this.getFormatData(this.incomeYear,10000.0,"0.00");
                //日累计管控值
                this.metroDistanceDailyControl = this.getFormatData(this.metroDistanceDailyControl,10000000.0,"0.00");
                this.metroDistanceAvgControl = this.getFormatData(this.metroDistanceAvgControl,10000000.0,"0.00");
                this.metroDistanceAvgLastYear = this.getFormatData(this.metroDistanceAvgLastYear,10000000.0,"0.00");
                this.metroDistanceAvgMonth = this.getFormatData(this.metroDistanceAvgMonth,10000000.0,"0.00");
                this.metroDistanceAvgYear = this.getFormatData(this.metroDistanceAvgYear,10000000.0,"0.00");
                this.metroDistanceDaily = this.getFormatData(this.metroDistanceDaily,10000000.0,"0.00");
                this.metroDistanceControlYear = this.getFormatData(this.metroDistanceControlYear,10000000.0,"0.00");
                this.metroDistanceLast = this.getFormatData(this.metroDistanceLast,10000000.0,"0.00");
                this.metroDistanceLastYear = this.getFormatData(this.metroDistanceLastYear,10000000.0,"0.00");
                this.metroDistanceMaxLast = this.getFormatData(this.metroDistanceMaxLast,10000000.0,"0.00");
                this.metroDistanceMaxYear = this.getFormatData(this.metroDistanceMaxYear,10000000.0,"0.00");
                this.metroDistancePlanMonth = this.getFormatData(this.metroDistancePlanMonth,10000000.0,"0.00");
                this.metroDistanceYear = this.getFormatData(this.metroDistanceYear,10000000.0,"0.00");
                for(Double d : passCapMonthList){
                    newPassCapMonthList.add(this.getFormatData(d,10000.0,"0.00"));
                    this.passCapAvgMonthList.add(this.passCapAvgMonth);
                }
                this.passCapMonthList = newPassCapMonthList;
                for(Double d : incomeMonthList){
                    newIncomeMonthList.add(this.getFormatData(d,10000.0,"0.00"));
                    this.incomeAvgMonthList.add(this.incomeAvgMonth);
                }
                this.incomeMonthList = newIncomeMonthList;
                for(Double d : metroDistanceMonthList){
                    newMetroDistanceMonthList.add(this.getFormatData(d,10000000.0,"0.00"));
                    this.metroDistanceAvgMonthList.add(this.metroDistanceAvgMonth);
                }
                this.metroDistanceMonthList = newMetroDistanceMonthList;

                this.useMetroRateDaily = this.getFormatData(this.allSection == 0 ? 0 :
                        this.useMetroDaily.doubleValue()/this.allSection.doubleValue(),0.01,"0.00");


                break;
            case COMPANY:
                this.setCompanyId(companyId);
                this.passCapMaxLast = this.getFormatData(this.passCapMaxLast,10000.0,"0.00");
                this.passCapMaxYear = this.getFormatData(this.passCapMaxYear,10000.0,"0.00");
                this.incomeMaxLast = this.getFormatData(this.incomeMaxLast,10000.0,"0.00");
                this.incomeMaxYear = this.getFormatData(this.incomeMaxYear,10000.0,"0.00");
                this.metroDistanceMaxLast = this.getFormatData(this.metroDistanceMaxLast,10000000.0,"0.00");
                this.metroDistanceMaxYear = this.getFormatData(this.metroDistanceMaxYear,10000000.0,"0.00");
                for(MetroProductionVo vo : list){
                    this.allSection += vo.getAllSection();
                    this.useMetroDaily += vo.getUseMetroDaily();
                    this.runMetroDaily += vo.getRunMetroDaily();
                    this.runMetroMonth += vo.getRunMetroMonth();
                    //日累计管控值
                    this.passCapDailyControl = add(vo.getPassCapDailyControl(),this.passCapDailyControl);
                    this.passCapAvgControl = add(vo.getPassCapAvgControl(),this.passCapAvgControl);
                    this.passCapAvgLastYear = add(vo.getPassCapAvgLastYear(),this.passCapAvgLastYear);
                    this.passCapAvgMonth = add(vo.getPassCapAvgMonth(),this.passCapAvgMonth);
                    this.passCapAvgYear = add(vo.getPassCapAvgYear(),this.passCapAvgYear);
                    this.passCapDaily = add(vo.getPassCapDaily(),this.passCapDaily);
                    this.passCapControlYear = add(vo.getPassCapControlYear(),this.passCapControlYear);
                    this.passCapLast = add(vo.getPassCapLast(),this.passCapLast);
                    this.passCapLastYear = add(vo.getPassCapLastYear(),this.passCapLastYear);
                    //this.passCapMaxLast = add(vo.getPassCapMaxLast(),this.passCapMaxLast);
                    //this.passCapMaxYear = add(vo.getPassCapMaxYear(),this.passCapMaxYear );
                    this.passCapPlanMonth = add(vo.getPassCapPlanMonth(),this.passCapPlanMonth);
                    this.passCapYear = add(vo.getPassCapYear(),this.passCapYear);
                    this.incomeDailyControl = add(vo.getIncomeDailyControl(),this.incomeDailyControl);
                    this.incomeAvgControl = add(vo.getIncomeAvgControl(),this.incomeAvgControl);
                    this.incomeAvgLastYear = add(vo.getIncomeAvgLastYear(),this.incomeAvgLastYear);
                    this.incomeAvgMonth = add(vo.getIncomeAvgMonth(),this.incomeAvgMonth);
                    this.incomeAvgYear = add(vo.getIncomeAvgYear(),this.incomeAvgYear);
                    this.incomeDaily = add(vo.getIncomeDaily(),this.incomeDaily);
                    this.incomeControlYear = add(vo.getIncomeControlYear(),this.incomeControlYear);
                    this.incomeLast = add(vo.getIncomeLast(),this.incomeLast);
                    this.incomeLastYear = add(vo.getIncomeLastYear(),this.incomeLastYear);
                    //this.incomeMaxLast = add(vo.getIncomeMaxLast(),this.incomeMaxLast);
                    //this.incomeMaxYear = add(vo.getIncomeMaxYear(),this.incomeMaxYear);
                    this.incomePlanMonth = add(vo.getIncomePlanMonth(),this.incomePlanMonth);
                    this.incomeYear = add(vo.getIncomeYear(),this.incomeYear);
                    this.metroDistanceDailyControl = add(vo.getMetroDistanceDailyControl(),this.metroDistanceDailyControl);
                    this.metroDistanceAvgControl = add(vo.getMetroDistanceAvgControl(),this.metroDistanceAvgControl);
                    this.metroDistanceAvgLastYear = add(vo.getMetroDistanceAvgLastYear(),this.metroDistanceAvgLastYear);
                    this.metroDistanceAvgMonth = add(vo.getMetroDistanceAvgMonth(),this.metroDistanceAvgMonth);
                    this.metroDistanceAvgYear = add(vo.getMetroDistanceAvgYear(),this.metroDistanceAvgYear);
                    this.metroDistanceDaily = add(vo.getMetroDistanceDaily(),this.metroDistanceDaily);
                    this.metroDistanceControlYear = add(vo.getMetroDistanceControlYear(),this.metroDistanceControlYear);
                    this.metroDistanceLast = add(vo.getMetroDistanceLast(),this.metroDistanceLast);
                    this.metroDistanceLastYear = add(vo.getMetroDistanceLastYear(),this.metroDistanceLastYear);
                    //this.metroDistanceMaxLast = add(vo.getMetroDistanceMaxLast(),this.metroDistanceMaxLast);
                    //this.metroDistanceMaxYear = add(vo.getMetroDistanceMaxYear(),this.metroDistanceMaxYear);
                    this.metroDistancePlanMonth = add(vo.getMetroDistancePlanMonth(),this.metroDistancePlanMonth);
                    this.metroDistanceYear = add(vo.getMetroDistanceYear(),this.metroDistanceYear);
                    MetroCoverUtil.addDoubleList(newPassCapMonthList,vo.getPassCapMonthList());
                    MetroCoverUtil.addDoubleList(newIncomeMonthList,vo.getIncomeMonthList());
                    MetroCoverUtil.addDoubleList(newMetroDistanceMonthList,vo.getMetroDistanceMonthList());
                    MetroCoverUtil.addIntegerList(newUseMetroList,vo.getUseMetroList());
                    MetroCoverUtil.addIntegerList(newRunMetroList,vo.getRunMetroList());
                    this.passCapStartMonth = vo.getPassCapStartMonth();
                    this.passCapEndMonth = vo.getPassCapEndMonth();
                    this.incomeStartMonth = vo.getIncomeStartMonth();
                    this.incomeEndMonth = vo.getIncomeEndMonth();
                    this.metroDistanceStartMonth = vo.getMetroDistanceStartMonth();
                    this.metroDistanceEndMonth = vo.getMetroDistanceEndMonth();
                }
                this.passCapMonthList = newPassCapMonthList;
                this.incomeMonthList = newIncomeMonthList;
                this.metroDistanceMonthList = newMetroDistanceMonthList;
                this.useMetroList = newUseMetroList;
                this.runMetroList = newRunMetroList;
                for(int i=0;i<passCapMonthList.size();i++){
                    this.passCapAvgMonthList.add(passCapAvgMonth);
                }
                for(int i=0;i<incomeMonthList.size();i++){
                    this.incomeAvgMonthList.add(incomeAvgMonth);
                }
                for(int i=0;i<metroDistanceMonthList.size();i++){
                    this.metroDistanceAvgMonthList.add(metroDistanceAvgMonth);
                }

                this.useMetroRateDaily = this.getFormatData(this.allSection == 0 ? 0 :
                        this.useMetroDaily.doubleValue()/this.allSection.doubleValue(),0.01,"0.00");

                break;
            default:
                break;
        }

    }

    //运用列车数
    //日数据 单位：列 数据库：上线列车+备用列车
    private Integer useMetroDaily = 0;
    //所有运用列车数 由excel提供 由周1-7 分割
    private Integer allSection = 0;
    //上线8 7 6 4 3 备用 8 7 6 4 3
//    private Integer onlineEightMetro = 0;
//    private Integer onlineSevenMetro = 0;
//    private Integer onlineSixMetro = 0;
//    private Integer onlineFourMetro = 0;
//    private Integer onlineThreeMetro = 0;
//    private Integer backupEightMetro = 0;
//    private Integer backupSevenMetro = 0;
//    private Integer backupSixMetro = 0;
//    private Integer backupFourMetro = 0;
//    private Integer backupThreeMetro = 0;

    //运用车上线率
    private Double useMetroRateDaily = 0.0;
    
    //运用列车数 近两周
    private List<Integer> useMetroList = new ArrayList<Integer>();

    //开行列次
    //日数据
    private Integer runMetroDaily = 0;
    //月累计
    private Integer runMetroMonth = 0;
    //开行列车 近两周
    private List<Integer> runMetroList = new ArrayList<Integer>();

    //客流量

    //日数据管控累计值 example: 4.12  3月份管控累计值 + 4月份管控累计值 * 12（当日累计天数）/30（该月累计天数）
    private Double passCapDailyControl = 0.0;

    //日数据 数据库单位：人 显示：万人
    private Double passCapDaily = 0.0;
    //年累计实际值
    private Double passCapYear = 0.0;
    //去年总额
    private Double passCapLastYear = 0.0;
    //年管控值
    private Double passCapControlYear = 0.0;
    //月计划累计值
    private Double passCapPlanMonth = 0.0;
    //近12个月 月均值
    private Double passCapAvgMonth = 0.0;

    //近12个月 月均值 画图
    private List<Double> passCapAvgMonthList = new ArrayList<Double>();

    //近12个月值画图
    private List<Double> passCapMonthList = new ArrayList<Double>();
    //近12个月 开始月份-结束月份
    private String passCapStartMonth = "";
    private String passCapEndMonth = "";
    //昨日数据
    private Double passCapLast = 0.0;
    //去年日均
    private Double passCapAvgLastYear = 0.0;
    //今年日均
    private Double passCapAvgYear = 0.0;
    //日均管控
    private Double passCapAvgControl = 0.0;
    //今年峰值
    private Double passCapMaxYear = 0.0;
    //峰值日期
    private String passCapMaxYearDate = "";
    //历史峰值
    private Double passCapMaxLast = 0.0;
    //峰值日期
    private String passCapMaxLastDate = "";


    //客运收入

    private Double incomeDailyControl = 0.0;

    //日数据 数据库单位：元 显示：万元
    private Double incomeDaily = 0.0;
    //年累计实际值
    private Double incomeYear = 0.0;
    //去年总额
    private Double incomeLastYear = 0.0;
    //年管控值
    private Double incomeControlYear = 0.0;
    //月计划累计值
    private Double incomePlanMonth = 0.0;
    //近12个月 月均值
    private Double incomeAvgMonth = 0.0;
    //近12个月值画图
    private List<Double> incomeMonthList = new ArrayList<Double>();

    //近12个月值平均值 画图
    private List<Double> incomeAvgMonthList = new ArrayList<Double>();

    //近12个月 开始月份-结束月份
    private String incomeStartMonth = "";
    private String incomeEndMonth = "";
    //昨日数据
    private Double incomeLast = 0.0;
    //去年日均
    private Double incomeAvgLastYear = 0.0;
    //今年日均
    private Double incomeAvgYear = 0.0;
    //日均管控
    private Double incomeAvgControl = 0.0;
    //今年峰值
    private Double incomeMaxYear = 0.0;
    //峰值日期
    private String incomeMaxYearDate = "";
    //历史峰值
    private Double incomeMaxLast = 0.0;
    //峰值日期
    private String incomeMaxLastDate = "";


    //运营里程
    private Double metroDistanceDailyControl = 0.0;

    private Double metroDistanceDaily = 0.0;
    //年累计实际值
    private Double metroDistanceYear = 0.0;
    //去年总额
    private Double metroDistanceLastYear = 0.0;
    //年管控值
    private Double metroDistanceControlYear = 0.0;
    //月计划累计值
    private Double metroDistancePlanMonth = 0.0;
    //近12个月 月均值
    private Double metroDistanceAvgMonth = 0.0;
    //近12个月值画图
    private List<Double> metroDistanceMonthList = new ArrayList<Double>();
    //近12个月值画图 平均值
    private List<Double> metroDistanceAvgMonthList = new ArrayList<Double>();
    //近12个月 开始月份-结束月份
    private String metroDistanceStartMonth = "";
    private String metroDistanceEndMonth = "";
    //昨日数据
    private Double metroDistanceLast = 0.0;
    //去年日均
    private Double metroDistanceAvgLastYear = 0.0;
    //今年日均
    private Double metroDistanceAvgYear = 0.0;
    //日均管控
    private Double metroDistanceAvgControl = 0.0;
    //今年峰值
    private Double metroDistanceMaxYear = 0.0;
    //峰值日期
    private String metroDistanceMaxYearDate = "";
    //历史峰值
    private Double metroDistanceMaxLast = 0.0;
    //峰值日期
    private String metroDistanceMaxLastDate = "";

    public List<MetroProductionVo> getList() {
        return list;
    }

    public void setList(List<MetroProductionVo> list) {
        this.list = list;
    }

    public Integer getUseMetroDaily() {
        return useMetroDaily;
    }

    public void setUseMetroDaily(Integer useMetroDaily) {
        this.useMetroDaily = useMetroDaily;
    }

    public Integer getAllSection() {
        return allSection;
    }

    public void setAllSection(Integer allSection) {
        this.allSection = allSection;
    }

    public List<Integer> getUseMetroList() {
        return useMetroList;
    }

    public void setUseMetroList(List<Integer> useMetroList) {
        this.useMetroList = useMetroList;
    }

    public Integer getRunMetroDaily() {
        return runMetroDaily;
    }

    public void setRunMetroDaily(Integer runMetroDaily) {
        this.runMetroDaily = runMetroDaily;
    }

    public Integer getRunMetroMonth() {
        return runMetroMonth;
    }

    public void setRunMetroMonth(Integer runMetroMonth) {
        this.runMetroMonth = runMetroMonth;
    }

    public List<Integer> getRunMetroList() {
        return runMetroList;
    }

    public void setRunMetroList(List<Integer> runMetroList) {
        this.runMetroList = runMetroList;
    }

    public Double getPassCapDaily() {
        return passCapDaily;
    }

    public void setPassCapDaily(Double passCapDaily) {
        this.passCapDaily = passCapDaily;
    }

    public Double getPassCapYear() {
        return passCapYear;
    }

    public void setPassCapYear(Double passCapYear) {
        this.passCapYear = passCapYear;
    }

    public Double getPassCapLastYear() {
        return passCapLastYear;
    }

    public void setPassCapLastYear(Double passCapLastYear) {
        this.passCapLastYear = passCapLastYear;
    }

    public Double getPassCapControlYear() {
        return passCapControlYear;
    }

    public void setPassCapControlYear(Double passCapControlYear) {
        this.passCapControlYear = passCapControlYear;
    }

    public Double getPassCapPlanMonth() {
        return passCapPlanMonth;
    }

    public void setPassCapPlanMonth(Double passCapPlanMonth) {
        this.passCapPlanMonth = passCapPlanMonth;
    }

    public Double getPassCapAvgMonth() {
        return passCapAvgMonth;
    }

    public void setPassCapAvgMonth(Double passCapAvgMonth) {
        this.passCapAvgMonth = passCapAvgMonth;
    }

    public List<Double> getPassCapMonthList() {
        return passCapMonthList;
    }

    public void setPassCapMonthList(List<Double> passCapMonthList) {
        this.passCapMonthList = passCapMonthList;
    }

    public String getPassCapStartMonth() {
        return passCapStartMonth;
    }

    public void setPassCapStartMonth(String passCapStartMonth) {
        this.passCapStartMonth = passCapStartMonth;
    }

    public String getPassCapEndMonth() {
        return passCapEndMonth;
    }

    public void setPassCapEndMonth(String passCapEndMonth) {
        this.passCapEndMonth = passCapEndMonth;
    }

    public Double getPassCapLast() {
        return passCapLast;
    }

    public void setPassCapLast(Double passCapLast) {
        this.passCapLast = passCapLast;
    }

    public Double getPassCapAvgLastYear() {
        return passCapAvgLastYear;
    }

    public void setPassCapAvgLastYear(Double passCapAvgLastYear) {
        this.passCapAvgLastYear = passCapAvgLastYear;
    }

    public Double getPassCapAvgYear() {
        return passCapAvgYear;
    }

    public void setPassCapAvgYear(Double passCapAvgYear) {
        this.passCapAvgYear = passCapAvgYear;
    }

    public Double getPassCapAvgControl() {
        return passCapAvgControl;
    }

    public void setPassCapAvgControl(Double passCapAvgControl) {
        this.passCapAvgControl = passCapAvgControl;
    }

    public Double getPassCapMaxYear() {
        return passCapMaxYear;
    }

    public void setPassCapMaxYear(Double passCapMaxYear) {
        this.passCapMaxYear = passCapMaxYear;
    }

    public String getPassCapMaxYearDate() {
        return passCapMaxYearDate;
    }

    public void setPassCapMaxYearDate(String passCapMaxYearDate) {
        this.passCapMaxYearDate = passCapMaxYearDate;
    }

    public Double getPassCapMaxLast() {
        return passCapMaxLast;
    }

    public void setPassCapMaxLast(Double passCapMaxLast) {
        this.passCapMaxLast = passCapMaxLast;
    }

    public String getPassCapMaxLastDate() {
        return passCapMaxLastDate;
    }

    public void setPassCapMaxLastDate(String passCapMaxLastDate) {
        this.passCapMaxLastDate = passCapMaxLastDate;
    }

    public Double getIncomeDaily() {
        return incomeDaily;
    }

    public void setIncomeDaily(Double incomeDaily) {
        this.incomeDaily = incomeDaily;
    }

    public Double getIncomeYear() {
        return incomeYear;
    }

    public void setIncomeYear(Double incomeYear) {
        this.incomeYear = incomeYear;
    }

    public Double getIncomeLastYear() {
        return incomeLastYear;
    }

    public void setIncomeLastYear(Double incomeLastYear) {
        this.incomeLastYear = incomeLastYear;
    }

    public Double getIncomeControlYear() {
        return incomeControlYear;
    }

    public void setIncomeControlYear(Double incomeControlYear) {
        this.incomeControlYear = incomeControlYear;
    }

    public Double getIncomePlanMonth() {
        return incomePlanMonth;
    }

    public void setIncomePlanMonth(Double incomePlanMonth) {
        this.incomePlanMonth = incomePlanMonth;
    }

    public Double getIncomeAvgMonth() {
        return incomeAvgMonth;
    }

    public void setIncomeAvgMonth(Double incomeAvgMonth) {
        this.incomeAvgMonth = incomeAvgMonth;
    }

    public List<Double> getIncomeMonthList() {
        return incomeMonthList;
    }

    public void setIncomeMonthList(List<Double> incomeMonthList) {
        this.incomeMonthList = incomeMonthList;
    }

    public String getIncomeStartMonth() {
        return incomeStartMonth;
    }

    public void setIncomeStartMonth(String incomeStartMonth) {
        this.incomeStartMonth = incomeStartMonth;
    }

    public String getIncomeEndMonth() {
        return incomeEndMonth;
    }

    public void setIncomeEndMonth(String incomeEndMonth) {
        this.incomeEndMonth = incomeEndMonth;
    }

    public Double getIncomeLast() {
        return incomeLast;
    }

    public void setIncomeLast(Double incomeLast) {
        this.incomeLast = incomeLast;
    }

    public Double getIncomeAvgLastYear() {
        return incomeAvgLastYear;
    }

    public void setIncomeAvgLastYear(Double incomeAvgLastYear) {
        this.incomeAvgLastYear = incomeAvgLastYear;
    }

    public Double getIncomeAvgYear() {
        return incomeAvgYear;
    }

    public void setIncomeAvgYear(Double incomeAvgYear) {
        this.incomeAvgYear = incomeAvgYear;
    }

    public Double getIncomeAvgControl() {
        return incomeAvgControl;
    }

    public void setIncomeAvgControl(Double incomeAvgControl) {
        this.incomeAvgControl = incomeAvgControl;
    }

    public Double getIncomeMaxYear() {
        return incomeMaxYear;
    }

    public void setIncomeMaxYear(Double incomeMaxYear) {
        this.incomeMaxYear = incomeMaxYear;
    }

    public String getIncomeMaxYearDate() {
        return incomeMaxYearDate;
    }

    public void setIncomeMaxYearDate(String incomeMaxYearDate) {
        this.incomeMaxYearDate = incomeMaxYearDate;
    }

    public Double getIncomeMaxLast() {
        return incomeMaxLast;
    }

    public void setIncomeMaxLast(Double incomeMaxLast) {
        this.incomeMaxLast = incomeMaxLast;
    }

    public String getIncomeMaxLastDate() {
        return incomeMaxLastDate;
    }

    public void setIncomeMaxLastDate(String incomeMaxLastDate) {
        this.incomeMaxLastDate = incomeMaxLastDate;
    }

    public Double getMetroDistanceDaily() {
        return metroDistanceDaily;
    }

    public void setMetroDistanceDaily(Double metroDistanceDaily) {
        this.metroDistanceDaily = metroDistanceDaily;
    }

    public Double getMetroDistanceYear() {
        return metroDistanceYear;
    }

    public void setMetroDistanceYear(Double metroDistanceYear) {
        this.metroDistanceYear = metroDistanceYear;
    }

    public Double getMetroDistanceLastYear() {
        return metroDistanceLastYear;
    }

    public void setMetroDistanceLastYear(Double metroDistanceLastYear) {
        this.metroDistanceLastYear = metroDistanceLastYear;
    }

    public Double getMetroDistanceControlYear() {
        return metroDistanceControlYear;
    }

    public void setMetroDistanceControlYear(Double metroDistanceControlYear) {
        this.metroDistanceControlYear = metroDistanceControlYear;
    }

    public Double getMetroDistancePlanMonth() {
        return metroDistancePlanMonth;
    }

    public void setMetroDistancePlanMonth(Double metroDistancePlanMonth) {
        this.metroDistancePlanMonth = metroDistancePlanMonth;
    }

    public Double getMetroDistanceAvgMonth() {
        return metroDistanceAvgMonth;
    }

    public void setMetroDistanceAvgMonth(Double metroDistanceAvgMonth) {
        this.metroDistanceAvgMonth = metroDistanceAvgMonth;
    }

    public List<Double> getMetroDistanceMonthList() {
        return metroDistanceMonthList;
    }

    public void setMetroDistanceMonthList(List<Double> metroDistanceMonthList) {
        this.metroDistanceMonthList = metroDistanceMonthList;
    }

    public String getMetroDistanceStartMonth() {
        return metroDistanceStartMonth;
    }

    public void setMetroDistanceStartMonth(String metroDistanceStartMonth) {
        this.metroDistanceStartMonth = metroDistanceStartMonth;
    }

    public String getMetroDistanceEndMonth() {
        return metroDistanceEndMonth;
    }

    public void setMetroDistanceEndMonth(String metroDistanceEndMonth) {
        this.metroDistanceEndMonth = metroDistanceEndMonth;
    }

    public Double getMetroDistanceLast() {
        return metroDistanceLast;
    }

    public void setMetroDistanceLast(Double metroDistanceLast) {
        this.metroDistanceLast = metroDistanceLast;
    }

    public Double getMetroDistanceAvgLastYear() {
        return metroDistanceAvgLastYear;
    }

    public void setMetroDistanceAvgLastYear(Double metroDistanceAvgLastYear) {
        this.metroDistanceAvgLastYear = metroDistanceAvgLastYear;
    }

    public Double getMetroDistanceAvgYear() {
        return metroDistanceAvgYear;
    }

    public void setMetroDistanceAvgYear(Double metroDistanceAvgYear) {
        this.metroDistanceAvgYear = metroDistanceAvgYear;
    }

    public Double getMetroDistanceAvgControl() {
        return metroDistanceAvgControl;
    }

    public void setMetroDistanceAvgControl(Double metroDistanceAvgControl) {
        this.metroDistanceAvgControl = metroDistanceAvgControl;
    }

    public Double getMetroDistanceMaxYear() {
        return metroDistanceMaxYear;
    }

    public void setMetroDistanceMaxYear(Double metroDistanceMaxYear) {
        this.metroDistanceMaxYear = metroDistanceMaxYear;
    }

    public String getMetroDistanceMaxYearDate() {
        return metroDistanceMaxYearDate;
    }

    public void setMetroDistanceMaxYearDate(String metroDistanceMaxYearDate) {
        this.metroDistanceMaxYearDate = metroDistanceMaxYearDate;
    }

    public Double getMetroDistanceMaxLast() {
        return metroDistanceMaxLast;
    }

    public void setMetroDistanceMaxLast(Double metroDistanceMaxLast) {
        this.metroDistanceMaxLast = metroDistanceMaxLast;
    }

    public String getMetroDistanceMaxLastDate() {
        return metroDistanceMaxLastDate;
    }

    public void setMetroDistanceMaxLastDate(String metroDistanceMaxLastDate) {
        this.metroDistanceMaxLastDate = metroDistanceMaxLastDate;
    }

    public static void main(String[] args){
        MetroProductionVo vo = new MetroProductionVo();
        System.out.println(new Gson().toJson(vo));
    }

//    public Integer getOnlineEightMetro() {
//        return onlineEightMetro;
//    }
//
//    public void setOnlineEightMetro(Integer onlineEightMetro) {
//        this.onlineEightMetro = onlineEightMetro;
//    }
//
//    public Integer getOnlineSevenMetro() {
//        return onlineSevenMetro;
//    }
//
//    public void setOnlineSevenMetro(Integer onlineSevenMetro) {
//        this.onlineSevenMetro = onlineSevenMetro;
//    }
//
//    public Integer getOnlineSixMetro() {
//        return onlineSixMetro;
//    }
//
//    public void setOnlineSixMetro(Integer onlineSixMetro) {
//        this.onlineSixMetro = onlineSixMetro;
//    }
//
//    public Integer getOnlineFourMetro() {
//        return onlineFourMetro;
//    }
//
//    public void setOnlineFourMetro(Integer onlineFourMetro) {
//        this.onlineFourMetro = onlineFourMetro;
//    }
//
//    public Integer getOnlineThreeMetro() {
//        return onlineThreeMetro;
//    }
//
//    public void setOnlineThreeMetro(Integer onlineThreeMetro) {
//        this.onlineThreeMetro = onlineThreeMetro;
//    }
//
//    public Integer getBackupEightMetro() {
//        return backupEightMetro;
//    }
//
//    public void setBackupEightMetro(Integer backupEightMetro) {
//        this.backupEightMetro = backupEightMetro;
//    }
//
//    public Integer getBackupSevenMetro() {
//        return backupSevenMetro;
//    }
//
//    public void setBackupSevenMetro(Integer backupSevenMetro) {
//        this.backupSevenMetro = backupSevenMetro;
//    }
//
//    public Integer getBackupSixMetro() {
//        return backupSixMetro;
//    }
//
//    public void setBackupSixMetro(Integer backupSixMetro) {
//        this.backupSixMetro = backupSixMetro;
//    }
//
//    public Integer getBackupFourMetro() {
//        return backupFourMetro;
//    }
//
//    public void setBackupFourMetro(Integer backupFourMetro) {
//        this.backupFourMetro = backupFourMetro;
//    }
//
//    public Integer getBackupThreeMetro() {
//        return backupThreeMetro;
//    }
//
//    public void setBackupThreeMetro(Integer backupThreeMetro) {
//        this.backupThreeMetro = backupThreeMetro;
//    }

    public Double getUseMetroRateDaily() {
        return useMetroRateDaily;
    }

    public void setUseMetroRateDaily(Double useMetroRateDaily) {
        this.useMetroRateDaily = useMetroRateDaily;
    }

    public Double getPassCapDailyControl() {
        return passCapDailyControl;
    }

    public void setPassCapDailyControl(Double passCapDailyControl) {
        this.passCapDailyControl = passCapDailyControl;
    }

    public Double getIncomeDailyControl() {
        return incomeDailyControl;
    }

    public void setIncomeDailyControl(Double incomeDailyControl) {
        this.incomeDailyControl = incomeDailyControl;
    }

    public Double getMetroDistanceDailyControl() {
        return metroDistanceDailyControl;
    }

    public void setMetroDistanceDailyControl(Double metroDistanceDailyControl) {
        this.metroDistanceDailyControl = metroDistanceDailyControl;
    }
}
