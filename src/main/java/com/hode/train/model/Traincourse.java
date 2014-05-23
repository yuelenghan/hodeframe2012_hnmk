package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class Traincourse extends IbatisModel {
    private static final long serialVersionUID = 4659454867097925152L;
    private int intID;
    private String strCourseName;
    private int intCourseNum;
    private int intCreateUser;
    private int intCreateGroup;
    private int intDictionary;

    private int intDictionaryFrom;
    private int intDictionaryTo;

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getStrCourseName() {
        return (strCourseName == null) ? "" : strCourseName;
    }

    public void setStrCourseName(String strCourseName) {
        this.strCourseName = strCourseName;
    }

    public int getIntCourseNum() {
        return intCourseNum;
    }

    public void setIntCourseNum(int intCourseNum) {
        this.intCourseNum = intCourseNum;
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

    public int getIntDictionary() {
        return intDictionary;
    }

    public void setIntDictionary(int intDictionary) {
        this.intDictionary = intDictionary;
    }

    public int getIntDictionaryFrom() {
        return intDictionaryFrom;
    }

    public void setIntDictionaryFrom(int intDictionaryFrom) {
        this.intDictionaryFrom = intDictionaryFrom;
    }

    public int getIntDictionaryTo() {
        return intDictionaryTo;
    }

    public void setIntDictionaryTo(int intDictionaryTo) {
        this.intDictionaryTo = intDictionaryTo;
    }
}
