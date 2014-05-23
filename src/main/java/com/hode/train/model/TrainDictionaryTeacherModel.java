package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainDictionaryTeacherModel extends IbatisModel {
    private static final long serialVersionUID = -3616783876554047848L;
    private int intID;
    private int intDictionary;
    private int intTeacher;
    private int intCreateUser;
    private int intCreateGroup;
    public int getIntID() {
        return intID;
    }
    public void setIntID(int intID) {
        this.intID = intID;
    }
    public int getIntDictionary() {
        return intDictionary;
    }
    public void setIntDictionary(int intDictionary) {
        this.intDictionary = intDictionary;
    }
    public int getIntTeacher() {
        return intTeacher;
    }
    public void setIntTeacher(int intTeacher) {
        this.intTeacher = intTeacher;
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
