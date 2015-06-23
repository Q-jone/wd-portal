package com.wonders.stpt.delayWarn.model.bo;

/**
 * Created by Administrator on 2014/6/9.
 */
public class DelayItemReportVo {
    private String itemName;
    private int itemQuantity;
    private int applayQuantity;
    private double percent;

    public int getApplayQuantity() {
        return applayQuantity;
    }

    public void setApplayQuantity(int applayQuantity) {
        this.applayQuantity = applayQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    
}
