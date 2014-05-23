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
 *  培训机构提交办班申请
 */
public class TrainApplyDetailModel extends IbatisModel
{
    private static final long serialVersionUID = 4053812950359727594L;
    private int intTrainApplyID;//办班ID
    private String strSubjectName;//课程名称
    private String strSubjectTime;//时数
    private String strTeacherName;//任课教师--姓名
    private String strTeacherDegree;//任课教师--学历
    private String strTeacherProfessional;//任课教师--所学专业
    private String strTeacherTitles;//任课教师--职称
    private String strTeacherCode;//任课教师--证书号码
    private int intOrderID;//排序号



    //////////////辅助字段
    private String strSubjectNameArr[];//临时--课程名称
    private String strSubjectTimeArr[];//临时--学时数
    private String strTeacherNameArr[];//临时--任课教师--姓名
    private String strTeacherDegreeArr[];//临时--任课教师--学历
    private String strTeacherProfessionalArr[];//临时--任课教师--所学专业
    private String strTeacherTitlesArr[];//临时--任课教师--职称
    private String strTeacherCodeArr[];//临时--任课教师--证书号码

    public int getIntTrainApplyID() {
        return intTrainApplyID;
    }
    public void setIntTrainApplyID(int intTrainApplyID) {
        this.intTrainApplyID = intTrainApplyID;
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
    public String getStrTeacherName() {
        return (strTeacherName == null) ? "" : strTeacherName;
    }
    public void setStrTeacherName(String strTeacherName) {
        this.strTeacherName = strTeacherName;
    }
    public String getStrTeacherDegree() {
        return (strTeacherDegree == null) ? "" : strTeacherDegree;
    }
    public void setStrTeacherDegree(String strTeacherDegree) {
        this.strTeacherDegree = strTeacherDegree;
    }
    public String getStrTeacherProfessional() {
        return (strTeacherProfessional == null) ? "" : strTeacherProfessional;
    }
    public void setStrTeacherProfessional(String strTeacherProfessional) {
        this.strTeacherProfessional = strTeacherProfessional;
    }
    public String getStrTeacherTitles() {
        return (strTeacherTitles == null) ? "" : strTeacherTitles;
    }
    public void setStrTeacherTitles(String strTeacherTitles) {
        this.strTeacherTitles = strTeacherTitles;
    }
    public String getStrTeacherCode() {
        return (strTeacherCode == null) ? "" : strTeacherCode;
    }
    public void setStrTeacherCode(String strTeacherCode) {
        this.strTeacherCode = strTeacherCode;
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
    public String[] getStrTeacherNameArr() {
        return strTeacherNameArr;
    }
    public void setStrTeacherNameArr(String[] strTeacherNameArr) {
        this.strTeacherNameArr = strTeacherNameArr;
    }
    public String[] getStrTeacherDegreeArr() {
        return strTeacherDegreeArr;
    }
    public void setStrTeacherDegreeArr(String[] strTeacherDegreeArr) {
        this.strTeacherDegreeArr = strTeacherDegreeArr;
    }
    public String[] getStrTeacherProfessionalArr() {
        return strTeacherProfessionalArr;
    }
    public void setStrTeacherProfessionalArr(String[] strTeacherProfessionalArr) {
        this.strTeacherProfessionalArr = strTeacherProfessionalArr;
    }
    public String[] getStrTeacherTitlesArr() {
        return strTeacherTitlesArr;
    }
    public void setStrTeacherTitlesArr(String[] strTeacherTitlesArr) {
        this.strTeacherTitlesArr = strTeacherTitlesArr;
    }
    public String[] getStrTeacherCodeArr() {
        return strTeacherCodeArr;
    }
    public void setStrTeacherCodeArr(String[] strTeacherCodeArr) {
        this.strTeacherCodeArr = strTeacherCodeArr;
    }



}