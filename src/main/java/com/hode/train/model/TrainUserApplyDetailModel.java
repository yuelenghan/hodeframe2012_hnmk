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
 * @author xdju 人员补证
 */
public class TrainUserApplyDetailModel extends IbatisModel {

    private static final long serialVersionUID = -567917192602528899L;
    private int intMainID;// 办班ID
    private int intOrderID;// 排序号
    private String strUserCode;// 身份证
    private String strCertCode;// 证书编号
    private String strUserName;// 姓名
    private String strUserDate;// 补正日期
    private String strUserUnitName;// 单位名称
    private String strUserPost;// 职务
    private String strApplyReason;// 补证理由

    // ////////////辅助字段
    private String strCertCodeArr[];// 临时--证书编号
    private String strUserCodeArr[];// 临时--身份证
    private String strUserNameArr[];// 临时--姓名
    private String strUserDateArr[];// 临时--补正日期
    private String strUserUnitNameArr[];// 临时--单位名称
    private String strUserPostArr[];// 临时--职务
    private String strApplyReasonArr[];// 临时--补证理由

    // 辅助字段
    private String strSearchStartDate;
    private String strSearchEndDate;

    public int getIntMainID() {
        return intMainID;
    }

    public void setIntMainID(int intMainID) {
        this.intMainID = intMainID;
    }

    public int getIntOrderID() {
        return intOrderID;
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
        return strUserCodeArr;
    }

    public void setStrUserCodeArr(String[] strUserCodeArr) {
        this.strUserCodeArr = strUserCodeArr;
    }

    public String[] getStrUserNameArr() {
        return strUserNameArr;
    }

    public void setStrUserNameArr(String[] strUserNameArr) {
        this.strUserNameArr = strUserNameArr;
    }

    public String[] getStrUserDateArr() {
        return strUserDateArr;
    }

    public void setStrUserDateArr(String[] strUserDateArr) {
        this.strUserDateArr = strUserDateArr;
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
        return strCertCodeArr;
    }

    public void setStrCertCodeArr(String[] strCertCodeArr) {
        this.strCertCodeArr = strCertCodeArr;
    }

    public String getStrUserUnitName() {
        return strUserUnitName;
    }

    public void setStrUserUnitName(String strUserUnitName) {
        this.strUserUnitName = strUserUnitName;
    }

    public String getStrUserPost() {
        return strUserPost;
    }

    public void setStrUserPost(String strUserPost) {
        this.strUserPost = strUserPost;
    }

    public String getStrApplyReason() {
        return strApplyReason;
    }

    public void setStrApplyReason(String strApplyReason) {
        this.strApplyReason = strApplyReason;
    }

    public String[] getStrUserUnitNameArr() {
        return strUserUnitNameArr;
    }

    public void setStrUserUnitNameArr(String[] strUserUnitNameArr) {
        this.strUserUnitNameArr = strUserUnitNameArr;
    }

    public String[] getStrUserPostArr() {
        return strUserPostArr;
    }

    public void setStrUserPostArr(String[] strUserPostArr) {
        this.strUserPostArr = strUserPostArr;
    }

    public String[] getStrApplyReasonArr() {
        return strApplyReasonArr;
    }

    public void setStrApplyReasonArr(String[] strApplyReasonArr) {
        this.strApplyReasonArr = strApplyReasonArr;
    }

}