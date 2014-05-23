package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

/**
 * 人员档案实体类
 *
 * @author 鹤
 *
 */
public class TrainPersonModel extends IbatisModel {
    private static final long serialVersionUID = 183913451447750261L;

    private int intID;
    private String strPersonName; // 姓名
    private String strPersonID; // 身份证号
    private String strSex; // 性别
    private String strHomeTown; // 籍贯
    private int intGroupID; // 导入此人员的培训机构id
    private String strEdu; // 学历
    private String strWorkUnit; // 工作单位
    private String strPost; // 职务
    private String strSchool; // 毕业院校
    private String strSpecialty; // 所学专业
    private String strPolitical; // 政治面貌
    private String strJobDate; // 参加工作时间
    private int intAge; // 年龄
    private int intPic1; // 照片（1：有；0：无）
    private int intPic2; // 学历（1：有；0：无）
    private int intPic3; // 体检表（1：有；0：无）
    private int intPic4; // 工作证明（1：有；0：无）

    // 辅助字段
    private int intStartAge;
    private int intEndAge;
    private int intMainID; // 导入档案库照片时用来确定文件路径
    private String strPic1; // 照片路径
    private String strPic2; // 学历路径
    private String strPic3; // 体检表路径
    private String strPic4; // 工作证明路径

    // 视图字段
    private String strGroupName; // 所属培训机构

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getStrPersonName() {
        return strPersonName;
    }

    public void setStrPersonName(String strPersonName) {
        this.strPersonName = strPersonName;
    }

    public String getStrPersonID() {
        return strPersonID;
    }

    public void setStrPersonID(String strPersonID) {
        this.strPersonID = strPersonID;
    }

    public String getStrSex() {
        return strSex;
    }

    public void setStrSex(String strSex) {
        this.strSex = strSex;
    }

    public String getStrHomeTown() {
        return strHomeTown;
    }

    public void setStrHomeTown(String strHomeTown) {
        this.strHomeTown = strHomeTown;
    }

    public int getIntGroupID() {
        return intGroupID;
    }

    public void setIntGroupID(int intGroupID) {
        this.intGroupID = intGroupID;
    }

    public String getStrEdu() {
        return strEdu;
    }

    public void setStrEdu(String strEdu) {
        this.strEdu = strEdu;
    }

    public String getStrWorkUnit() {
        return strWorkUnit;
    }

    public void setStrWorkUnit(String strWorkUnit) {
        this.strWorkUnit = strWorkUnit;
    }

    public String getStrPost() {
        return strPost;
    }

    public void setStrPost(String strPost) {
        this.strPost = strPost;
    }

    public String getStrSchool() {
        return strSchool;
    }

    public void setStrSchool(String strSchool) {
        this.strSchool = strSchool;
    }

    public String getStrSpecialty() {
        return strSpecialty;
    }

    public void setStrSpecialty(String strSpecialty) {
        this.strSpecialty = strSpecialty;
    }

    public String getStrPolitical() {
        return strPolitical;
    }

    public void setStrPolitical(String strPolitical) {
        this.strPolitical = strPolitical;
    }

    public String getStrJobDate() {
        return strJobDate;
    }

    public void setStrJobDate(String strJobDate) {
        this.strJobDate = strJobDate;
    }

    public String getStrGroupName() {
        return strGroupName;
    }

    public void setStrGroupName(String strGroupName) {
        this.strGroupName = strGroupName;
    }

    public int getIntAge() {
        return intAge;
    }

    public void setIntAge(int intAge) {
        this.intAge = intAge;
    }

    public int getIntStartAge() {
        return intStartAge;
    }

    public void setIntStartAge(int intStartAge) {
        this.intStartAge = intStartAge;
    }

    public int getIntEndAge() {
        return intEndAge;
    }

    public void setIntEndAge(int intEndAge) {
        this.intEndAge = intEndAge;
    }

    public int getIntPic1() {
        return intPic1;
    }

    public void setIntPic1(int intPic1) {
        this.intPic1 = intPic1;
    }

    public int getIntPic2() {
        return intPic2;
    }

    public void setIntPic2(int intPic2) {
        this.intPic2 = intPic2;
    }

    public int getIntPic3() {
        return intPic3;
    }

    public void setIntPic3(int intPic3) {
        this.intPic3 = intPic3;
    }

    public int getIntPic4() {
        return intPic4;
    }

    public void setIntPic4(int intPic4) {
        this.intPic4 = intPic4;
    }

    public String getStrPic1() {
        return strPic1;
    }

    public void setStrPic1(String strPic1) {
        this.strPic1 = strPic1;
    }

    public String getStrPic2() {
        return strPic2;
    }

    public void setStrPic2(String strPic2) {
        this.strPic2 = strPic2;
    }

    public String getStrPic3() {
        return strPic3;
    }

    public void setStrPic3(String strPic3) {
        this.strPic3 = strPic3;
    }

    public String getStrPic4() {
        return strPic4;
    }

    public void setStrPic4(String strPic4) {
        this.strPic4 = strPic4;
    }

    public int getIntMainID() {
        return intMainID;
    }

    public void setIntMainID(int intMainID) {
        this.intMainID = intMainID;
    }
}
