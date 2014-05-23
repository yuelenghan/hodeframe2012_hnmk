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
 *  限制名单
 */
public class TrainUserBlackModel extends IbatisModel
{

    private static final long serialVersionUID = -907961166087097834L;
    private String strStudentName;//姓名
    private String strStudentIDCode;//身份证
    private String strEndDate;//有效期
    private String strRemark;//备注

    private int intCreateUserID;        //撰写人ID
    private String strCreateUserName;   //撰写人姓名
    private int intCreateGroupID;       //撰写人部门ID
    private String strCreateGroupName;   //撰写人部门名称
    private int intCreateUnitID;       //撰写人所在单位ID
    private String strCreateUnitName;   //撰写人所在单位名称
    private String strCreateDate;//创建时间

    //辅助字段
    private String strSearchStartDate;
    private String strSearchEndDate;
    public String getStrStudentName() {
        return (strStudentName == null) ? "" : strStudentName;
    }
    public void setStrStudentName(String strStudentName) {
        this.strStudentName = strStudentName;
    }
    public String getStrStudentIDCode() {
        return (strStudentIDCode == null) ? "" : strStudentIDCode;
    }
    public void setStrStudentIDCode(String strStudentIDCode) {
        this.strStudentIDCode = strStudentIDCode;
    }
    public String getStrEndDate() {
        return (strEndDate == null) ? "" : strEndDate;
    }
    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }
    public String getStrRemark() {
        return (strRemark == null) ? "" : strRemark;
    }
    public void setStrRemark(String strRemark) {
        this.strRemark = strRemark;
    }
    public int getIntCreateUserID() {
        return  intCreateUserID;
    }
    public void setIntCreateUserID(int intCreateUserID) {
        this.intCreateUserID = intCreateUserID;
    }
    public String getStrCreateUserName() {
        return (strCreateUserName == null) ? "" : strCreateUserName;
    }
    public void setStrCreateUserName(String strCreateUserName) {
        this.strCreateUserName = strCreateUserName;
    }
    public int getIntCreateGroupID() {
        return   intCreateGroupID;
    }
    public void setIntCreateGroupID(int intCreateGroupID) {
        this.intCreateGroupID = intCreateGroupID;
    }
    public String getStrCreateGroupName() {
        return (strCreateGroupName == null) ? "" : strCreateGroupName;
    }
    public void setStrCreateGroupName(String strCreateGroupName) {
        this.strCreateGroupName = strCreateGroupName;
    }
    public int getIntCreateUnitID() {
        return intCreateUnitID;
    }
    public void setIntCreateUnitID(int intCreateUnitID) {
        this.intCreateUnitID = intCreateUnitID;
    }
    public String getStrCreateUnitName() {
        return (strCreateUnitName == null) ? "" : strCreateUnitName;
    }
    public void setStrCreateUnitName(String strCreateUnitName) {
        this.strCreateUnitName = strCreateUnitName;
    }
    public String getStrCreateDate() {
        return (strCreateDate == null) ? "" : strCreateDate;
    }
    public void setStrCreateDate(String strCreateDate) {
        this.strCreateDate = strCreateDate;
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




}