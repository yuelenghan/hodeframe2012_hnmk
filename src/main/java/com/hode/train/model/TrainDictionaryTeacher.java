package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainDictionaryTeacher extends IbatisModel{
    private static final long serialVersionUID = -3178742882024638436L;
    private int intID;
    private int intTeacher;
    private int intDictionary;
    private int intCreateGroup;
    public int getIntCreateGroup() {
        return intCreateGroup;
    }
    public void setIntCreateGroup(int intCreateGroup) {
        this.intCreateGroup = intCreateGroup;
    }
    private int intCreateUser;
    public int getIntID() {
        return intID;
    }
    public void setIntID(int intID) {
        this.intID = intID;
    }
    public int getIntTeacher() {
        return intTeacher;
    }
    public void setIntTeacher(int intTeacher) {
        this.intTeacher = intTeacher;
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
}
