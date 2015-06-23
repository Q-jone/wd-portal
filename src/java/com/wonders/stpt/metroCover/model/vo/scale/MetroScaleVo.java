package com.wonders.stpt.metroCover.model.vo.scale;

import com.wonders.stpt.metroCover.model.vo.common.MetroIndicatorVo;
import com.wonders.stpt.metroCover.model.vo.common.MetroType;
import com.wonders.stpt.metroIndicator.entity.bo.MetroScale;

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
public class MetroScaleVo extends MetroIndicatorVo implements Serializable {
    //除1000 不保留小数
    private Double getFormatData(Double data){
        if(data==null) return 0.0;
        data = data / 1000;
        NumberFormat nf = new DecimalFormat("0.0");
        return Double.valueOf(nf.format(data));
    }

    //线路长度 单位公里 数据库单位-米
    private Double lineDistance = 0.0;
    //配属数 单位：列 8节+6节+4节+3节
    private Integer sections = 0;
    //车战数 站
    private Integer stationCount = 0;
    //配属数 8 7 6 4 3
    private Integer eightSections = 0;
    private Integer sevenSections = 0;
    private Integer sixSections = 0;
    private Integer fourSections = 0;
    private Integer threeSections = 0;

    //线路所属运营公司
    private List<MetroScaleVo> list = new ArrayList<MetroScaleVo>();

    //设置更新数据
    public void refresh(String lineId, String companyId, String date,MetroType type){
        this.setType(type);
        this.setDate(date);
        switch (type){
            case LINE:
                this.setLineId(lineId);
                this.sections = this.eightSections + this.sevenSections + this.sixSections + this.fourSections + this.threeSections;
                this.lineDistance = this.getFormatData(this.lineDistance);
                break;
            case COMPANY:
                this.setCompanyId(companyId);
                for(MetroScaleVo vo : list){
                    //vo.refresh(vo.getLineId(),companyId,date,MetroType.LINE);
                    this.sections += vo.getSections();
                    this.lineDistance += vo.getLineDistance();
                    this.stationCount += vo.getStationCount();
                }
                break;

            default:
                break;
        }


    }

    public Double getLineDistance() {
        return lineDistance;
    }

    public void setLineDistance(Double lineDistance) {
        this.lineDistance = lineDistance;
    }

    public Integer getSections() {
        return sections;
    }

    public void setSections(Integer sections) {
        this.sections = sections;
    }

    public Integer getStationCount() {
        return stationCount;
    }

    public void setStationCount(Integer stationCount) {
        this.stationCount = stationCount;
    }

    public Integer getEightSections() {
        return eightSections;
    }

    public void setEightSections(Integer eightSections) {
        this.eightSections = eightSections;
    }

    public Integer getSevenSections() {
        return sevenSections;
    }

    public void setSevenSections(Integer sevenSections) {
        this.sevenSections = sevenSections;
    }

    public Integer getSixSections() {
        return sixSections;
    }

    public void setSixSections(Integer sixSections) {
        this.sixSections = sixSections;
    }

    public Integer getFourSections() {
        return fourSections;
    }

    public void setFourSections(Integer fourSections) {
        this.fourSections = fourSections;
    }

    public Integer getThreeSections() {
        return threeSections;
    }

    public void setThreeSections(Integer threeSections) {
        this.threeSections = threeSections;
    }

    public List<MetroScaleVo> getList() {
        return list;
    }

    public void setList(List<MetroScaleVo> list) {
        this.list = list;
    }

    public void test(){
        System.out.println(this.getLineName());
        System.out.println(super.getLineName());
    }
}
