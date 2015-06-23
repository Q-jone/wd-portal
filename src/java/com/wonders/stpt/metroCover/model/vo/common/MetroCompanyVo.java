package com.wonders.stpt.metroCover.model.vo.common;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2014/12/23
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class MetroCompanyVo implements Serializable{
    private String companyId;
    private String companyName;

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
}
