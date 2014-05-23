/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

/**
 * @author xdju 培训机构提交考核申请
 */
public class TrainCheckModel extends TrainBaseModel {

    // intCheckFlag: 0:待提交 1:已提交待审核 9:已审核

    private static final long serialVersionUID = -6163619778342170221L;

    private int intTrainApplyID;// 办班ID

    private String strTrainUnitName;// 培训单位
    private String strTrainStartDate; // 拟考试时间
    private String strTrainEndDate; // 批准考试时间
    private String strTrainCount;// 培训期数
    private String strTrainType; // 培训类别 培训 再培训
    private String strTrainObject; // 培训对象id
    private String strTrainRange; // 培训机构资质
    private String strTrainCode; // 资质证书号码
    private String strTrainUserNum; // 培训班计划人数
    private String strMakeupUserNum; // 补考人数

    private String strTrainTime1;// 培训总学时数
    private String strTrainTime2;// 培训大纲要求学时数

    private int intIsSeal1;// 培训单位----是否盖章
    private String strSealVal1;// 培训单位----盖章地址
    private int intIsSeal2;// 省中心----是否盖章
    private String strSealVal2;// 省中心----盖章地址
    private String strFinishIdea;// 考核点审核意见

    private String strApplyDate;// 申请日期
    private String strCheckDate;// 审核日期

    private String strTrainObjectDesc; // 培训对象描述

    private String strTrainCountClass; // 期次分班

    private String ifPrintTicket; // 是否为打印准考证的请求，用于查询sql

    public int getIntTrainApplyID() {
        return intTrainApplyID;
    }

    public void setIntTrainApplyID(int intTrainApplyID) {
        this.intTrainApplyID = intTrainApplyID;
    }

    public String getStrTrainUnitName() {
        return (strTrainUnitName == null) ? "" : strTrainUnitName;
    }

    public void setStrTrainUnitName(String strTrainUnitName) {
        this.strTrainUnitName = strTrainUnitName;
    }

    public String getStrTrainCount() {
        return (strTrainCount == null) ? "" : strTrainCount;
    }

    public void setStrTrainCount(String strTrainCount) {
        this.strTrainCount = strTrainCount;
    }

    public String getStrTrainObject() {
        return (strTrainObject == null) ? "" : strTrainObject;
    }

    public void setStrTrainObject(String strTrainObject) {
        this.strTrainObject = strTrainObject;
    }

    public String getStrTrainType() {
        return (strTrainType == null) ? "" : strTrainType;
    }

    public void setStrTrainType(String strTrainType) {
        this.strTrainType = strTrainType;
    }

    public String getStrTrainStartDate() {
        return (strTrainStartDate == null) ? "" : strTrainStartDate;
    }

    public void setStrTrainStartDate(String strTrainStartDate) {
        this.strTrainStartDate = strTrainStartDate;
    }

    public String getStrTrainEndDate() {
        return (strTrainEndDate == null) ? "" : strTrainEndDate;
    }

    public void setStrTrainEndDate(String strTrainEndDate) {
        this.strTrainEndDate = strTrainEndDate;
    }

    public String getStrTrainUserNum() {
        return (strTrainUserNum == null) ? "" : strTrainUserNum;
    }

    public void setStrTrainUserNum(String strTrainUserNum) {
        this.strTrainUserNum = strTrainUserNum;
    }

    public String getStrTrainRange() {
        return (strTrainRange == null) ? "" : strTrainRange;
    }

    public void setStrTrainRange(String strTrainRange) {
        this.strTrainRange = strTrainRange;
    }

    public String getStrTrainCode() {
        return (strTrainCode == null) ? "" : strTrainCode;
    }

    public void setStrTrainCode(String strTrainCode) {
        this.strTrainCode = strTrainCode;
    }

    public int getIntIsSeal1() {
        return intIsSeal1;
    }

    public void setIntIsSeal1(int intIsSeal1) {
        this.intIsSeal1 = intIsSeal1;
    }

    public int getIntIsSeal2() {
        return intIsSeal2;
    }

    public void setIntIsSeal2(int intIsSeal2) {
        this.intIsSeal2 = intIsSeal2;
    }

    public String getStrSealVal1() {
        return (strSealVal1 == null) ? "" : strSealVal1;
    }

    public void setStrSealVal1(String strSealVal1) {
        this.strSealVal1 = strSealVal1;
    }

    public String getStrSealVal2() {
        return (strSealVal2 == null) ? "" : strSealVal2;
    }

    public void setStrSealVal2(String strSealVal2) {
        this.strSealVal2 = strSealVal2;
    }

    public String getStrTrainTime1() {
        return (strTrainTime1 == null) ? "" : strTrainTime1;
    }

    public void setStrTrainTime1(String strTrainTime1) {
        this.strTrainTime1 = strTrainTime1;
    }

    public String getStrTrainTime2() {
        return (strTrainTime2 == null) ? "" : strTrainTime2;
    }

    public void setStrTrainTime2(String strTrainTime2) {
        this.strTrainTime2 = strTrainTime2;
    }

    public String getStrFinishIdea() {
        return (strFinishIdea == null) ? "" : strFinishIdea;
    }

    public void setStrFinishIdea(String strFinishIdea) {
        this.strFinishIdea = strFinishIdea;
    }

    public String getStrApplyDate() {
        return (strApplyDate == null) ? "" : strApplyDate;
    }

    public void setStrApplyDate(String strApplyDate) {
        this.strApplyDate = strApplyDate;
    }

    public String getStrCheckDate() {
        return (strCheckDate == null) ? "" : strCheckDate;
    }

    public void setStrCheckDate(String strCheckDate) {
        this.strCheckDate = strCheckDate;
    }

    public String getStrTrainObjectDesc() {
        return strTrainObjectDesc;
    }

    public void setStrTrainObjectDesc(String strTrainObjectDesc) {
        this.strTrainObjectDesc = strTrainObjectDesc;
    }

    public String getStrTrainCountClass() {
        return strTrainCountClass;
    }

    public void setStrTrainCountClass(String strTrainCountClass) {
        this.strTrainCountClass = strTrainCountClass;
    }

    public String getIfPrintTicket() {
        return ifPrintTicket;
    }

    public void setIfPrintTicket(String ifPrintTicket) {
        this.ifPrintTicket = ifPrintTicket;
    }

    public String getStrMakeupUserNum() {
        return strMakeupUserNum;
    }

    public void setStrMakeupUserNum(String strMakeupUserNum) {
        this.strMakeupUserNum = strMakeupUserNum;
    }

}