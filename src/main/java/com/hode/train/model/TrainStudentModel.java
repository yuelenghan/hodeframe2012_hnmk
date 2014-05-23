/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

/**
 * @author xdju 学员信息上报审查
 */
public class TrainStudentModel extends TrainBaseModel {

    private static final long serialVersionUID = 3392123541650425310L;
    private int intTrainApplyID;// 办班ID
    private int intTrainUnitID; // 培训单位ID
    private String strTrainUnitName;// 培训单位
    private String strTrainStartDate; // 培训时间--开始时间
    private String strTrainEndDate; // 培训时间--结束时间
    private String strTrainCount;// 培训期数
    private String strTrainType; // 培训类别 培训 再培训
    private String strTrainObject; // 培训对象 id
    private String strTrainObjectDesc; // 培训对象描述
    private String strTrainRange; // 培训机构资质
    private String strTrainCode; // 资质证书号码
    private String strTrainUserNum; // 拟考试人数
    private String strTrainTime1;// 培训总学时数
    private String strTrainTime2;// 培训大纲要求学时数
    // 反填写
    private int intTotalUserNum;// 导入的培训人数，反填写
    private int intIsLeader;// 是否已设置督导人员
    private String strRemark;// 备注

    // ////////辅助字段
    private String strStudentPost_search;// 工作单位及职务
    private String strStudentSex_search;// 性别
    private String strStudentDegree_search;// 学历
    private String strStudentName_search;// 姓名
    private String strStudentIDCode_search;// 身份证号
    private String strBirthDay_search;// 出生日期
    private String strCertCode_search;// 证书号码
    private int intCertCode_search;
    private String strBirthDay1_search;// 出生日期--开始
    private String strBirthDay2_search;// 出生日期--结束
    private int intStartBirthday_search;// 年龄
    private int intEndBirthday_search;// 年龄
    private int intCertAllow_search;// 证书
    private int intCertBK_search; // 补考
    private int intCertWJ1_search; // 违纪1
    private int intCertWJ2_search; // 违纪2
    private int intCertDQ_search; // 当期考试人数

    private String strTrainCountClass;

    private int intStatus;

    public String getStrTrainUnitName() {
        return (strTrainUnitName == null) ? "" : strTrainUnitName;
    }

    public void setStrTrainUnitName(String strTrainUnitName) {
        this.strTrainUnitName = strTrainUnitName;
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

    public String getStrTrainCount() {
        return (strTrainCount == null) ? "" : strTrainCount;
    }

    public void setStrTrainCount(String strTrainCount) {
        this.strTrainCount = strTrainCount;
    }

    public String getStrTrainType() {
        return (strTrainType == null) ? "" : strTrainType;
    }

    public void setStrTrainType(String strTrainType) {
        this.strTrainType = strTrainType;
    }

    public String getStrTrainObject() {
        return (strTrainObject == null) ? "" : strTrainObject;
    }

    public void setStrTrainObject(String strTrainObject) {
        this.strTrainObject = strTrainObject;
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

    public String getStrTrainUserNum() {
        return (strTrainUserNum == null) ? "" : strTrainUserNum;
    }

    public void setStrTrainUserNum(String strTrainUserNum) {
        this.strTrainUserNum = strTrainUserNum;
    }

    public int getIntTotalUserNum() {
        return intTotalUserNum;
    }

    public void setIntTotalUserNum(int intTotalUserNum) {
        this.intTotalUserNum = intTotalUserNum;
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

    public String getStrRemark() {
        return (strRemark == null) ? "" : strRemark;
    }

    public void setStrRemark(String strRemark) {
        this.strRemark = strRemark;
    }

    public int getIntIsLeader() {
        return intIsLeader;
    }

    public void setIntIsLeader(int intIsLeader) {
        this.intIsLeader = intIsLeader;
    }

    public String getStrStudentPost_search() {
        return (strStudentPost_search == null) ? "" : strStudentPost_search;
    }

    public void setStrStudentPost_search(String strStudentPost_search) {
        this.strStudentPost_search = strStudentPost_search;
    }

    public String getStrStudentSex_search() {
        return (strStudentSex_search == null) ? "" : strStudentSex_search;
    }

    public void setStrStudentSex_search(String strStudentSex_search) {
        this.strStudentSex_search = strStudentSex_search;
    }

    public String getStrStudentDegree_search() {
        return (strStudentDegree_search == null) ? "" : strStudentDegree_search;
    }

    public void setStrStudentDegree_search(String strStudentDegree_search) {
        this.strStudentDegree_search = strStudentDegree_search;
    }

    public String getStrStudentName_search() {
        return (strStudentName_search == null) ? "" : strStudentName_search;
    }

    public void setStrStudentName_search(String strStudentName_search) {
        this.strStudentName_search = strStudentName_search;
    }

    public String getStrStudentIDCode_search() {
        return (strStudentIDCode_search == null) ? "" : strStudentIDCode_search;
    }

    public void setStrStudentIDCode_search(String strStudentIDCode_search) {
        this.strStudentIDCode_search = strStudentIDCode_search;
    }

    public String getStrBirthDay_search() {
        return (strBirthDay_search == null) ? "" : strBirthDay_search;
    }

    public void setStrBirthDay_search(String strBirthDay_search) {
        this.strBirthDay_search = strBirthDay_search;
    }

    public int getIntStartBirthday_search() {
        return intStartBirthday_search;
    }

    public void setIntStartBirthday_search(int intStartBirthday_search) {
        this.intStartBirthday_search = intStartBirthday_search;
    }

    public int getIntEndBirthday_search() {
        return intEndBirthday_search;
    }

    public void setIntEndBirthday_search(int intEndBirthday_search) {
        this.intEndBirthday_search = intEndBirthday_search;
    }

    public String getStrBirthDay1_search() {
        return (strBirthDay1_search == null) ? "" : strBirthDay1_search;
    }

    public void setStrBirthDay1_search(String strBirthDay1_search) {
        this.strBirthDay1_search = strBirthDay1_search;
    }

    public String getStrBirthDay2_search() {
        return (strBirthDay2_search == null) ? "" : strBirthDay2_search;
    }

    public void setStrBirthDay2_search(String strBirthDay2_search) {
        this.strBirthDay2_search = strBirthDay2_search;
    }

    public int getIntTrainApplyID() {
        return intTrainApplyID;
    }

    public void setIntTrainApplyID(int intTrainApplyID) {
        this.intTrainApplyID = intTrainApplyID;
    }

    public String getStrCertCode_search() {
        return (strCertCode_search == null) ? "" : strCertCode_search;
    }

    public void setStrCertCode_search(String strCertCode_search) {
        this.strCertCode_search = strCertCode_search;
    }

    public int getIntCertCode_search() {
        return intCertCode_search;
    }

    public void setIntCertCode_search(int intCertCode_search) {
        this.intCertCode_search = intCertCode_search;
    }

    public int getIntCertAllow_search() {
        return intCertAllow_search;
    }

    public void setIntCertAllow_search(int intCertAllow_search) {
        this.intCertAllow_search = intCertAllow_search;
    }

    public int getIntCertBK_search() {
        return intCertBK_search;
    }

    public void setIntCertBK_search(int intCertBK_search) {
        this.intCertBK_search = intCertBK_search;
    }

    public int getIntCertWJ1_search() {
        return intCertWJ1_search;
    }

    public void setIntCertWJ1_search(int intCertWJ1_search) {
        this.intCertWJ1_search = intCertWJ1_search;
    }

    public int getIntCertWJ2_search() {
        return intCertWJ2_search;
    }

    public void setIntCertWJ2_search(int intCertWJ2_search) {
        this.intCertWJ2_search = intCertWJ2_search;
    }

    public int getIntCertDQ_search() {
        return intCertDQ_search;
    }

    public void setIntCertDQ_search(int intCertDQ_search) {
        this.intCertDQ_search = intCertDQ_search;
    }

    public int getIntTrainUnitID() {
        return intTrainUnitID;
    }

    public void setIntTrainUnitID(int intTrainUnitID) {
        this.intTrainUnitID = intTrainUnitID;
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

    public int getIntStatus() {
        return intStatus;
    }

    public void setIntStatus(int intStatus) {
        this.intStatus = intStatus;
    }

}