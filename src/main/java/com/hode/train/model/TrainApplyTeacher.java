package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainApplyTeacher extends IbatisModel {

    private static final long serialVersionUID = -7977780695228209932L;
    private int intID;
    private String strTeacherID;
    public String getStrTeacherID() {
        return strTeacherID;
    }
    public void setStrTeacherID(String strTeacherID) {
        this.strTeacherID = strTeacherID;
    }
    private int intTrainApplyID;
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
