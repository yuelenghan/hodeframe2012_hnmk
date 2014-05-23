package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainApplyMaterial extends IbatisModel {

    private static final long serialVersionUID = 2597816112310059587L;
    private int intID;
    private int intTrainApplyID;
    private String strMaterialName;
    private String strMaterialVersion;
    private String strMaterialPublisher;
    private String strMaterialPrice;
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
    public String getStrMaterialName() {
        return strMaterialName;
    }
    public void setStrMaterialName(String strMaterialName) {
        this.strMaterialName = strMaterialName;
    }
    public String getStrMaterialVersion() {
        return strMaterialVersion;
    }
    public void setStrMaterialVersion(String strMaterialVersion) {
        this.strMaterialVersion = strMaterialVersion;
    }
    public String getStrMaterialPublisher() {
        return strMaterialPublisher;
    }
    public void setStrMaterialPublisher(String strMaterialPublisher) {
        this.strMaterialPublisher = strMaterialPublisher;
    }
    public String getStrMaterialPrice() {
        return strMaterialPrice;
    }
    public void setStrMaterialPrice(String strMaterialPrice) {
        this.strMaterialPrice = strMaterialPrice;
    }
}
