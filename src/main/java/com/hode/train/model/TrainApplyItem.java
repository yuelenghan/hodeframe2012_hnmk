package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainApplyItem extends IbatisModel{

    private static final long serialVersionUID = 4690014326754923435L;
    private int intID;
    private int intTrainApplyID;
    private String strItemName;
    public String getStrItemName() {
        return strItemName;
    }
    public void setStrItemName(String strItemName) {
        this.strItemName = strItemName;
    }
    private String strItemPrice;
    public String getStrItemPrice() {
        return strItemPrice;
    }
    public void setStrItemPrice(String strItemPrice) {
        this.strItemPrice = strItemPrice;
    }
    public int getIntID() {
        return intID;
    }
    public void setIntID(int intID) {
        this.intID = intID;
    }
    public int getIntTrainApplyID() {
        return intTrainApplyID;
    }
    public void setIntTrainApplyID(int intTrainApplyID) {
        this.intTrainApplyID = intTrainApplyID;
    }
}
