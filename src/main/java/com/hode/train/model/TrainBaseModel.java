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
 *  基础信息
 */
public class TrainBaseModel extends IbatisModel
{

    private static final long serialVersionUID = 839591158018288175L;
    private int intCreateUserID;        //撰写人ID
    private String strCreateUserName;   //撰写人姓名
    private int intCreateGroupID;       //撰写人部门ID
    private String strCreateGroupName;   //撰写人部门名称
    private int intCreateUnitID;       //撰写人所在单位ID
    private String strCreateUnitName;   //撰写人所在单位名称
    private String strCreateDate;//创建时间


    private int intCheckFlag; //见详细说明
    private String strCheckVal; //审核意见

    //辅助字段
    private String strCheckFlag;
    public int intNextFlag;//审核标志， 1：不同意		2：同意		3：发送申请






    public int getIntCreateUserID() {
        return intCreateUserID;
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
        return intCreateGroupID;
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

    public int getIntCheckFlag() {
        return  intCheckFlag;
    }
    public void setIntCheckFlag(int intCheckFlag) {
        this.intCheckFlag = intCheckFlag;
    }
    public String getStrCheckVal() {
        return (strCheckVal == null) ? "" : strCheckVal;
    }
    public void setStrCheckVal(String strCheckVal) {
        this.strCheckVal = strCheckVal;
    }
    public String getStrCheckFlag() {
        return (strCheckFlag == null) ? "" : strCheckFlag;
    }
    public void setStrCheckFlag(String strCheckFlag) {
        this.strCheckFlag = strCheckFlag;
    }
    public int getIntNextFlag() {
        return intNextFlag;
    }
    public void setIntNextFlag(int intNextFlag) {
        this.intNextFlag = intNextFlag;
    }




}