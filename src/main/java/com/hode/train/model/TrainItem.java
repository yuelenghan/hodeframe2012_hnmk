package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainItem extends IbatisModel {

    private static final long serialVersionUID = 8918097629527997468L;

    private int intItemID;
    private String strItemName;
    private String strPrice;
    public String getStrPrice() {
        return strPrice;
    }
    public void setStrPrice(String strPrice) {
        this.strPrice = strPrice;
    }
    private int intCreateUser;
    private int intCreateGroup;
    public int getIntItemID() {
        return intItemID;
    }
    public void setIntItemID(int intItemID) {
        this.intItemID = intItemID;
    }
    public String getStrItemName() {
        return strItemName;
    }
    public void setStrItemName(String strItemName) {
        this.strItemName = strItemName;
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
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
