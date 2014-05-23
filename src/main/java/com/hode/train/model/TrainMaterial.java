package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainMaterial extends IbatisModel{

    private static final long serialVersionUID = 1L;

    private int intMaterialID;
    private String strName;
    private String strVersion;
    private String strPublisherName;
    private String strPrice;
    private int intDictionary;
    private int intCreateUser;
    private int intCreateGroup;
    public int getIntMaterialID() {
        return intMaterialID;
    }
    public void setIntMaterialID(int intMaterialID) {
        this.intMaterialID = intMaterialID;
    }
    public String getStrName() {
        return strName;
    }
    public void setStrName(String strName) {
        this.strName = strName;
    }
    public String getStrVersion() {
        return strVersion;
    }
    public void setStrVersion(String strVersion) {
        this.strVersion = strVersion;
    }
    public String getStrPublisherName() {
        return strPublisherName;
    }
    public void setStrPublisherName(String strPublisherName) {
        this.strPublisherName = strPublisherName;
    }
    public String getStrPrice() {
        return strPrice;
    }
    public void setStrPrice(String strPrice) {
        this.strPrice = strPrice;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public int getIntDictionary() {
        return intDictionary;
    }
    public void setIntDictionary(int intDictionary) {
        this.intDictionary = intDictionary;
    }
    public int getIntCreateUser() {
        return intCreateUser;
    }
    public void setIntCreateUser(int intCreateUser) {
        this.intCreateUser = intCreateUser;
    }
    public int getIntCreateGroup() {
        return intCreateGroup;
    }
    public void setIntCreateGroup(int intCreateGroup) {
        this.intCreateGroup = intCreateGroup;
    }
}
