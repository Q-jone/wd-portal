package com.wonders.stpt.metroCover.model.vo.common;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class MetroLineVo implements Serializable{
    private String lineId;
    private String lineName;

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
}
