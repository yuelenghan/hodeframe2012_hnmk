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
 *  培训机构提交考核申请
 */
public class TrainCheckDetailModel extends IbatisModel
{

    private static final long serialVersionUID = 4566989524598032118L;
    private int intTrainCheckID;//办班ID
    private String strSubjectName;//课程名称
    private String strSubjectTime;//时数
    private int intOrderID;//排序号



    //////////////辅助字段
    private String strSubjectNameArr[];//临时--课程名称
    private String strSubjectTimeArr[];//临时--学时数



    public int getIntTrainCheckID() {
        return  intTrainCheckID;
    }
    public void setIntTrainCheckID(int intTrainCheckID) {
        this.intTrainCheckID = intTrainCheckID;
    }
    public String getStrSubjectName() {
        return (strSubjectName == null) ? "" : strSubjectName;
    }
    public void setStrSubjectName(String strSubjectName) {
        this.strSubjectName = strSubjectName;
    }
    public String getStrSubjectTime() {
        return (strSubjectTime == null) ? "" : strSubjectTime;
    }
    public void setStrSubjectTime(String strSubjectTime) {
        this.strSubjectTime = strSubjectTime;
    }
    public int getIntOrderID() {
        return  intOrderID;
    }
    public void setIntOrderID(int intOrderID) {
        this.intOrderID = intOrderID;
    }


    public String[] getStrSubjectNameArr() {
        return strSubjectNameArr;
    }
    public void setStrSubjectNameArr(String[] strSubjectNameArr) {
        this.strSubjectNameArr = strSubjectNameArr;
    }
    public String[] getStrSubjectTimeArr() {
        return strSubjectTimeArr;
    }
    public void setStrSubjectTimeArr(String[] strSubjectTimeArr) {
        this.strSubjectTimeArr = strSubjectTimeArr;
    }

}