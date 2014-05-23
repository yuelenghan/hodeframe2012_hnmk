package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainingProgramsModel extends IbatisModel {
    private static final long serialVersionUID = 303421334160426733L;
    private int intID;
    private String strItemName;
    private String strPrice;
    private int intCreateUser;
    private int intCreateGroup;
    public int getIntID() {
        return intID;
    }
    public void setIntID(int intID) {
        this.intID = intID;
    }
    public String getStrItemName() {
        return (strItemName==null)?"":strItemName;
    }
    public void setStrItemName(String strItemName) {
        this.strItemName = strItemName;
    }
    public String getStrPrice() {
        return strPrice;
    }
    public void setStrPrice(String strPrice) {
        this.strPrice = strPrice;
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
