package com.hode.train.model;

public class TrainExamineeModel extends TrainBaseModel{

    private static final long serialVersionUID = 913771613359482593L;

    private int intTrainApplyID;//办班ID

//	private String strExamType;//考核类型

    private String strExamineeName ="";//姓名

    private String strExamineeSex ="";//性别

    private String strIdentity ="";//身份证号

    private String strEducation ="";//文化程度

    private String strMajor ="";//专业

    private String strExamineeUnit ="";//工作单位

    private String strUnitAddr ="";//单位地址

    private String strUnitType ="";//单位类别

    private String strRelation ="";//联系方式

    private String strDepartment ="";//任职部门

    private String strJob ="";//岗位/职务

    private String strHealth ="";//身体状况

    private String strRecord ="";//违章记录

    private String strTrainApplyName ="";//所属期次名称

    private int intID;

//	private String strQualificationType;//资格类型
//
//	private String strOperation;//准操项目

//	private String strWorkAge;//工龄

//	private String strTrainUnit;//培训单位

//	private String strClasses;//培训班次

//	private String isMakeUp;//补考人员

//	private String isDelay;//是否延期

//	private String intDelayNum;//延期次数

    private String picPath ="";//多图片地址



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

    public String getStrExamineeName() {
        return strExamineeName;
    }

    public void setStrExamineeName(String strExamineeName) {
        this.strExamineeName = strExamineeName;
    }

    public String getStrExamineeSex() {
        return strExamineeSex;
    }

    public void setStrExamineeSex(String strExamineeSex) {
        this.strExamineeSex = strExamineeSex;
    }

    public String getStrIdentity() {
        return strIdentity;
    }

    public void setStrIdentity(String strIdentity) {
        this.strIdentity = strIdentity;
    }

    public String getStrEducation() {
        return strEducation;
    }

    public void setStrEducation(String strEducation) {
        this.strEducation = strEducation;
    }

    public String getStrMajor() {
        return strMajor;
    }

    public void setStrMajor(String strMajor) {
        this.strMajor = strMajor;
    }

    public String getStrExamineeUnit() {
        return strExamineeUnit;
    }

    public void setStrExamineeUnit(String strExamineeUnit) {
        this.strExamineeUnit = strExamineeUnit;
    }

    public String getStrUnitAddr() {
        return strUnitAddr;
    }

    public void setStrUnitAddr(String strUnitAddr) {
        this.strUnitAddr = strUnitAddr;
    }

    public String getStrUnitType() {
        return strUnitType;
    }

    public void setStrUnitType(String strUnitType) {
        this.strUnitType = strUnitType;
    }

    public String getStrRelation() {
        return strRelation;
    }

    public void setStrRelation(String strRelation) {
        this.strRelation = strRelation;
    }

    public String getStrDepartment() {
        return strDepartment;
    }

    public void setStrDepartment(String strDepartment) {
        this.strDepartment = strDepartment;
    }

    public String getStrJob() {
        return strJob;
    }

    public void setStrJob(String strJob) {
        this.strJob = strJob;
    }


    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getStrHealth() {
        return strHealth;
    }

    public void setStrHealth(String strHealth) {
        this.strHealth = strHealth;
    }

    public String getStrRecord() {
        return strRecord;
    }

    public void setStrRecord(String strRecord) {
        this.strRecord = strRecord;
    }

    public String getStrTrainApplyName() {
        return strTrainApplyName;
    }

    public void setStrTrainApplyName(String strTrainApplyName) {
        this.strTrainApplyName = strTrainApplyName;
    }

}
