package com.hode.train.model;

public class ApplyExamineeModel {

    private int intID;
    private int intTrainApplyID;
    private int intExamineeID;
    private int intTrainCheckID;
    private int intTrainCheckPass;
    private String isMakeUp="";//是否补考
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
    public int getIntExamineeID() {
        return intExamineeID;
    }
    public void setIntExamineeID(int intExamineeID) {
        this.intExamineeID = intExamineeID;
    }
    public String getIsMakeUp() {
        return isMakeUp;
    }
    public void setIsMakeUp(String isMakeUp) {
        this.isMakeUp = isMakeUp;
    }
    public int getIntTrainCheckID() {
        return intTrainCheckID;
    }
    public void setIntTrainCheckID(int intTrainCheckID) {
        this.intTrainCheckID = intTrainCheckID;
    }
    public int getIntTrainCheckPass() {
        return intTrainCheckPass;
    }
    public void setIntTrainCheckPass(int intTrainCheckPass) {
        this.intTrainCheckPass = intTrainCheckPass;
    }
}
