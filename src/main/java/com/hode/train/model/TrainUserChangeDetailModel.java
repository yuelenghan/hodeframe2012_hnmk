/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

/**
 * @author xdju
 *  人员变更
 */
public class TrainUserChangeDetailModel extends IbatisModel
{

    private static final long serialVersionUID = 1983063728230981107L;
    private int intMainID;//办班ID
    private int intOrderID;//排序号
    private String strCertCode;//证书编号
    private String strUserCode;//身份证
    private String strUserName;//姓名
    private String strUserDate;//补正日期
    private String strFromTitles;//原工作单位的职务
    private String strToTitles;//现工作单位的职务
    private String strFromUnit;//原工作单位的名称
    private String strToUnit;//现工作单位的名称

    //////////////辅助字段
    private String strCertCodeArr[];//临时--证书编号
    private String strUserCodeArr[];//临时--身份证
    private String strUserNameArr[];//临时--姓名
    private String strUserDateArr[];//临时--补正日期
    private String strFromTitlesArr[];//临时--原工作单位的职务
    private String strToTitlesArr[];//临时--现工作单位的职务
    private String strFromUnitArr[];//临时--原工作单位的名称
    private String strToUnitArr[];//临时--现工作单位的名称
    //辅助字段
    private String strSearchStartDate;
    private String strSearchEndDate;


    public int getIntMainID() {
        return  intMainID;
    }
    public void setIntMainID(int intMainID) {
        this.intMainID = intMainID;
    }
    public int getIntOrderID() {
        return  intOrderID;
    }
    public void setIntOrderID(int intOrderID) {
        this.intOrderID = intOrderID;
    }
    public String getStrUserCode() {
        return (strUserCode == null) ? "" : strUserCode;
    }
    public void setStrUserCode(String strUserCode) {
        this.strUserCode = strUserCode;
    }
    public String getStrUserName() {
        return (strUserName == null) ? "" : strUserName;
    }
    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }
    public String getStrUserDate() {
        return (strUserDate == null) ? "" : strUserDate;
    }
    public void setStrUserDate(String strUserDate) {
        this.strUserDate = strUserDate;
    }
    public String[] getStrUserCodeArr() {
        return  strUserCodeArr;
    }
    public void setStrUserCodeArr(String[] strUserCodeArr) {
        this.strUserCodeArr = strUserCodeArr;
    }
    public String[] getStrUserNameArr() {
        return  strUserNameArr;
    }
    public void setStrUserNameArr(String[] strUserNameArr) {
        this.strUserNameArr = strUserNameArr;
    }
    public String[] getStrUserDateArr() {
        return  strUserDateArr;
    }
    public void setStrUserDateArr(String[] strUserDateArr) {
        this.strUserDateArr = strUserDateArr;
    }
    public String getStrFromTitles() {
        return (strFromTitles == null) ? "" : strFromTitles;
    }
    public void setStrFromTitles(String strFromTitles) {
        this.strFromTitles = strFromTitles;
    }
    public String getStrToTitles() {
        return (strToTitles == null) ? "" : strToTitles;
    }
    public void setStrToTitles(String strToTitles) {
        this.strToTitles = strToTitles;
    }
    public String getStrFromUnit() {
        return (strFromUnit == null) ? "" : strFromUnit;
    }
    public void setStrFromUnit(String strFromUnit) {
        this.strFromUnit = strFromUnit;
    }
    public String getStrToUnit() {
        return (strToUnit == null) ? "" : strToUnit;
    }
    public void setStrToUnit(String strToUnit) {
        this.strToUnit = strToUnit;
    }
    public String[] getStrFromTitlesArr() {
        return  strFromTitlesArr;
    }
    public void setStrFromTitlesArr(String[] strFromTitlesArr) {
        this.strFromTitlesArr = strFromTitlesArr;
    }
    public String[] getStrToTitlesArr() {
        return  strToTitlesArr;
    }
    public void setStrToTitlesArr(String[] strToTitlesArr) {
        this.strToTitlesArr = strToTitlesArr;
    }
    public String[] getStrFromUnitArr() {
        return  strFromUnitArr;
    }
    public void setStrFromUnitArr(String[] strFromUnitArr) {
        this.strFromUnitArr = strFromUnitArr;
    }
    public String[] getStrToUnitArr() {
        return  strToUnitArr;
    }
    public void setStrToUnitArr(String[] strToUnitArr) {
        this.strToUnitArr = strToUnitArr;
    }
    public String getStrSearchStartDate() {
        return (strSearchStartDate == null) ? "" : strSearchStartDate;
    }
    public void setStrSearchStartDate(String strSearchStartDate) {
        this.strSearchStartDate = strSearchStartDate;
    }
    public String getStrSearchEndDate() {
        return (strSearchEndDate == null) ? "" : strSearchEndDate;
    }
    public void setStrSearchEndDate(String strSearchEndDate) {
        this.strSearchEndDate = strSearchEndDate;
    }
    public String getStrCertCode() {
        return (strCertCode == null) ? "" : strCertCode;
    }
    public void setStrCertCode(String strCertCode) {
        this.strCertCode = strCertCode;
    }
    public String[] getStrCertCodeArr() {
        return  strCertCodeArr;
    }
    public void setStrCertCodeArr(String[] strCertCodeArr) {
        this.strCertCodeArr = strCertCodeArr;
    }





}