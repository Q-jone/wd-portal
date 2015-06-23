package com.wonders.stpt.metroCover.model.vo.common;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class MetroIndicatorVo implements Serializable{
    //变动规则：
    //1：线路  全变
    //2：时间  全变
    //3：运营公司    待定


    private String lineId = "";
    private String lineName = "";
    private String companyId = "";
    private String companyName = "";
    private String date = "";
    private MetroType type = MetroType.LINE;

    public MetroType getType() {
        return type;
    }

    public void setType(MetroType type) {
        this.type = type;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static void main(String[] args){
        System.out.println(new Gson().toJson(new MetroIndicatorVo()));
    }
}
