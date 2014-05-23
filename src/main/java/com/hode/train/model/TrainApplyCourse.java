package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainApplyCourse extends IbatisModel {

    private static final long serialVersionUID = -6581632579609504774L;
    private int intID;
    private int intTrainApplyID;
    private String strCourseName; // 课程名称
    private String strCourseTime; // 课时数

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

    public String getStrCourseName() {
        return strCourseName;
    }

    public void setStrCourseName(String strCourseName) {
        this.strCourseName = strCourseName;
    }

    public String getStrCourseTime() {
        return strCourseTime;
    }

    public void setStrCourseTime(String strCourseTime) {
        this.strCourseTime = strCourseTime;
    }
}
