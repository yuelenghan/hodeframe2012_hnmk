package com.hode.train.model;

public class TrainUnitModel extends TrainBaseModel {

    private static final long serialVersionUID = 6765850877644527517L;

    private int intID;
    private String strName;
    private int intFatherID;
    private int intLevel;
    private int intIsDelete;

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public int getIntFatherID() {
        return intFatherID;
    }

    public void setIntFatherID(int intFatherID) {
        this.intFatherID = intFatherID;
    }

    public int getIntLevel() {
        return intLevel;
    }

    public void setIntLevel(int intLevel) {
        this.intLevel = intLevel;
    }

    public int getIntIsDelete() {
        return intIsDelete;
    }

    public void setIntIsDelete(int intIsDelete) {
        this.intIsDelete = intIsDelete;
    }
}
