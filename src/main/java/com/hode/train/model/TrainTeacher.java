package com.hode.train.model;

import java.util.List;

import com.hode.framework.model.IbatisModel;
import com.hode.train.util.MyStringUtil;

public class TrainTeacher extends IbatisModel {
    private static final long serialVersionUID = 4009626787226905458L;
    private int intID;
    private String strName;
    private String strEducation;
    private String strMajor;
    private String strCertificate;
    private String strTitle;
    private int intGroup;
    private int intCreateUser;
    private String strCertCode; // 证书号码
    private int intCourseID; // 所教授的课程id
    private String strCourseName; // 所教授的课程name
    private String strTrainObject;
    private String strTrainObjectDesc;

    private List<Integer> courseIDList;
    private String strTrainObjectFrom;
    private String strTrainObjectTo;

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getStrName() {
        return MyStringUtil.processNullStr(strName);
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrEducation() {
        return MyStringUtil.processNullStr(strEducation);
    }

    public void setStrEducation(String strEducation) {
        this.strEducation = strEducation;
    }

    public String getStrMajor() {
        return MyStringUtil.processNullStr(strMajor);
    }

    public void setStrMajor(String strMajor) {
        this.strMajor = strMajor;
    }

    public String getStrCertificate() {
        return MyStringUtil.processNullStr(strCertificate);
    }

    public void setStrCertificate(String strCertificate) {
        this.strCertificate = strCertificate;
    }

    public String getStrTitle() {
        return MyStringUtil.processNullStr(strTitle);
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public int getIntGroup() {
        return intGroup;
    }

    public void setIntGroup(int intGroup) {
        this.intGroup = intGroup;
    }

    public int getIntCreateUser() {
        return intCreateUser;
    }

    public void setIntCreateUser(int intCreateUser) {
        this.intCreateUser = intCreateUser;
    }

    public String getStrCertCode() {
        return MyStringUtil.processNullStr(strCertCode);
    }

    public void setStrCertCode(String strCertCode) {
        this.strCertCode = strCertCode;
    }

    public int getIntCourseID() {
        return intCourseID;
    }

    public void setIntCourseID(int intCourseID) {
        this.intCourseID = intCourseID;
    }

    public String getStrCourseName() {
        return MyStringUtil.processNullStr(strCourseName);
    }

    public void setStrCourseName(String strCourseName) {
        this.strCourseName = strCourseName;
    }

    public String getStrTrainObject() {
        return strTrainObject;
    }

    public void setStrTrainObject(String strTrainObject) {
        this.strTrainObject = strTrainObject;
    }

    public String getStrTrainObjectDesc() {
        return strTrainObjectDesc;
    }

    public void setStrTrainObjectDesc(String strTrainObjectDesc) {
        this.strTrainObjectDesc = strTrainObjectDesc;
    }

    public List<Integer> getCourseIDList() {
        return courseIDList;
    }

    public void setCourseIDList(List<Integer> courseIDList) {
        this.courseIDList = courseIDList;
    }

    public String getStrTrainObjectFrom() {
        return strTrainObjectFrom;
    }

    public void setStrTrainObjectFrom(String strTrainObjectFrom) {
        this.strTrainObjectFrom = strTrainObjectFrom;
    }

    public String getStrTrainObjectTo() {
        return strTrainObjectTo;
    }

    public void setStrTrainObjectTo(String strTrainObjectTo) {
        this.strTrainObjectTo = strTrainObjectTo;
    }
}
