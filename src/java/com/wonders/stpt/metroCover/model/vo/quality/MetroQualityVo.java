package com.wonders.stpt.metroCover.model.vo.quality;

import com.wonders.stpt.metroCover.model.vo.common.MetroIndicatorVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroType;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class MetroQualityVo extends MetroIndicatorVo implements Serializable {
    //所有参数均为 实际值 * 100 即：0.98 - > 98

    //乘以100 保留一位小数
    private Double getFormatData(Double data){
        if(data==null) return 0.0;
        data = data * 100;
        NumberFormat nf = new DecimalFormat("0.0");
        return Double.valueOf(nf.format(data));
    }

    public void refresh(String lineId, String companyId, String date,MetroType type) {
        this.setType(type);
        this.setDate(date);
        switch (type) {
            case LINE:
                List<Double> newOnTimeList = new ArrayList<Double>();
                List<Double> newOnWorkList = new ArrayList<Double>();
                this.setLineId(lineId);
                this.onTimeYear = this.getFormatData(onTimeYear);
                this.onTimeLastYear = this.getFormatData(onTimeLastYear);
                this.onTimeDaily = this.getFormatData(onTimeDaily);
                this.onTimeControl = this.getFormatData(onTimeControl);
                for(Double d : onTimeList){
                    newOnTimeList.add(this.getFormatData(d));
                    onTimeControlList.add(onTimeControl);
                }
                this.onTimeList = newOnTimeList;

                this.onWorkYear = this.getFormatData(onWorkYear);
                this.onWorkLastYear = this.getFormatData(onWorkLastYear);
                this.onWorkDaily = this.getFormatData(onWorkDaily);
                this.onWorkControl = this.getFormatData(onWorkControl);
                for(Double d : onWorkList){
                    newOnWorkList.add(this.getFormatData(d));
                    onWorkControlList.add(onWorkControl);
                }
                this.onWorkList = newOnWorkList;

                break;
            case COMPANY:
                this.setCompanyId(companyId);
                break;
            default:
                break;
        }
    }

    //各线路列表
    private List<MetroQualityVo> list = new ArrayList<MetroQualityVo>();

    //今年累计正点率
    private Double onTimeYear = 0.0;
    //去年累计正点率
    private Double onTimeLastYear = 0.0;
    //今日正点率
    private Double onTimeDaily = 0.0;
    //管控值 暂取值年管控值
    private Double onTimeControl = 0.0;
    //近两周正点率数据 画图
    private List<Double> onTimeList = new ArrayList<Double>();
    //近两周正点率数据管控值
    private List<Double> onTimeControlList = new ArrayList<Double>();

    //今年累计兑现率
    private Double onWorkYear = 0.0;
    //去年累计兑现率
    private Double onWorkLastYear = 0.0;
    //今日兑现率
    private Double onWorkDaily = 0.0;
    //管控值 暂取值年管控值
    private Double onWorkControl = 0.0;
    //近两周兑现率数据 画图
    private List<Double> onWorkList = new ArrayList<Double>();
    //近两周兑现率数据管控值
    private List<Double> onWorkControlList = new ArrayList<Double>();

    public Double getOnWorkYear() {
        return onWorkYear;
    }

    public void setOnWorkYear(Double onWorkYear) {
        this.onWorkYear = onWorkYear;
    }

    public Double getOnWorkLastYear() {
        return onWorkLastYear;
    }

    public void setOnWorkLastYear(Double onWorkLastYear) {
        this.onWorkLastYear = onWorkLastYear;
    }

    public Double getOnWorkDaily() {
        return onWorkDaily;
    }

    public void setOnWorkDaily(Double onWorkDaily) {
        this.onWorkDaily = onWorkDaily;
    }

    public Double getOnWorkControl() {
        return onWorkControl;
    }

    public void setOnWorkControl(Double onWorkControl) {
        this.onWorkControl = onWorkControl;
    }

    public List<Double> getOnWorkList() {
        return onWorkList;
    }

    public void setOnWorkList(List<Double> onWorkList) {
        this.onWorkList = onWorkList;
    }

    public List<Double> getOnWorkControlList() {
        return onWorkControlList;
    }

    public void setOnWorkControlList(List<Double> onWorkControlList) {
        this.onWorkControlList = onWorkControlList;
    }

    public Double getOnTimeYear() {
        return onTimeYear;
    }

    public void setOnTimeYear(Double onTimeYear) {
        this.onTimeYear = onTimeYear;
    }

    public Double getOnTimeLastYear() {
        return onTimeLastYear;
    }

    public void setOnTimeLastYear(Double onTimeLastYear) {
        this.onTimeLastYear = onTimeLastYear;
    }

    public Double getOnTimeDaily() {
        return onTimeDaily;
    }

    public void setOnTimeDaily(Double onTimeDaily) {
        this.onTimeDaily = onTimeDaily;
    }

    public Double getOnTimeControl() {
        return onTimeControl;
    }

    public void setOnTimeControl(Double onTimeControl) {
        this.onTimeControl = onTimeControl;
    }

    public List<Double> getOnTimeList() {
        return onTimeList;
    }

    public void setOnTimeList(List<Double> onTimeList) {
        this.onTimeList = onTimeList;
    }

    public List<Double> getOnTimeControlList() {
        return onTimeControlList;
    }

    public void setOnTimeControlList(List<Double> onTimeControlList) {
        this.onTimeControlList = onTimeControlList;
    }

    public List<MetroQualityVo> getList() {
        return list;
    }

    public void setList(List<MetroQualityVo> list) {
        this.list = list;
    }
}
