/*
 * Created on 2006-1-11 Author hodesoft
 *
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

public class TrainLogModel extends IbatisModel
{

    private static final long serialVersionUID = 705326863745249336L;
    private int intInfoID;      //信息ID
    private int intCheckFlag;      //审核标志  1：不同意		2：同意		3：发送申请
    private String strTableName;    //
    private String strOptDate;      //处理日期
    private String strOptContent;   //处理内容

    private int intOptUserID;      //处理人员ID
    private String strOptUserName;  //处理人员姓名
    private int intOptGroupID;          //处理人员所在部门ID
    private String strOptGroupName;     //处理人员所在部门名称
    private int intOptUnitID;          //处理人员所在单位ID
    private String strOptUnitName;     //处理人员所在单位名称


    public int getIntInfoID() {
        return  intInfoID;
    }
    public void setIntInfoID(int intInfoID) {
        this.intInfoID = intInfoID;
    }
    public String getStrTableName() {
        return (strTableName == null) ? "" : strTableName;
    }
    public void setStrTableName(String strTableName) {
        this.strTableName = strTableName;
    }
    public String getStrOptDate() {
        return (strOptDate == null) ? "" : strOptDate;
    }
    public void setStrOptDate(String strOptDate) {
        this.strOptDate = strOptDate;
    }
    public String getStrOptContent() {
        return (strOptContent == null) ? "" : strOptContent;
    }
    public void setStrOptContent(String strOptContent) {
        this.strOptContent = strOptContent;
    }
    public int getIntOptUserID() {
        return  intOptUserID;
    }
    public void setIntOptUserID(int intOptUserID) {
        this.intOptUserID = intOptUserID;
    }
    public String getStrOptUserName() {
        return (strOptUserName == null) ? "" : strOptUserName;
    }
    public void setStrOptUserName(String strOptUserName) {
        this.strOptUserName = strOptUserName;
    }
    public int getIntOptGroupID() {
        return  intOptGroupID;
    }
    public void setIntOptGroupID(int intOptGroupID) {
        this.intOptGroupID = intOptGroupID;
    }
    public String getStrOptGroupName() {
        return (strOptGroupName == null) ? "" : strOptGroupName;
    }
    public void setStrOptGroupName(String strOptGroupName) {
        this.strOptGroupName = strOptGroupName;
    }
    public int getIntOptUnitID() {
        return  intOptUnitID;
    }
    public void setIntOptUnitID(int intOptUnitID) {
        this.intOptUnitID = intOptUnitID;
    }
    public String getStrOptUnitName() {
        return (strOptUnitName == null) ? "" : strOptUnitName;
    }
    public void setStrOptUnitName(String strOptUnitName) {
        this.strOptUnitName = strOptUnitName;
    }
    public int getIntCheckFlag() {
        return  intCheckFlag;
    }
    public void setIntCheckFlag(int intCheckFlag) {
        this.intCheckFlag = intCheckFlag;
    }



}

