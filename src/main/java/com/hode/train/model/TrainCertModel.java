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
 * @author xdju 证书处理数据
 */
public class TrainCertModel extends IbatisModel {

    private static final long serialVersionUID = -5811791560710802540L;
    private String strTrainUnitName;// 培训单位
    private String strTrainCount;// 培训期数
    private String strTrainType; // 培训类别 培训 再培训
    private String strTrainObject; // 培训对象 id
    private String strTrainObjectDesc; // 培训对象描述
    private String strTrainRange; // 培训机构资质
    private String strTrainCode; // 资质证书号码
    private int intIsNew;// 是否处理 0:未有成绩无法处理 1:有成绩待受理 2：已受理未审核 3：已受理已审核 4:证书已生成
    // 5:证书已导出

    private String strApplyCode;// 受理编号
    private String strApplyDate;// 受理日期
    private String strApplyNum;// 受理人数

    // ///////////辅助字段
    // /////////辅助字段
    private String strIsNewVal;

    private int intIDArr[];
    private int intCertScoreArr[];
    private int intCertAllowArr[];
    private int intIsBKArr[];// 是否补考
    private String strWJArr[];// 是否违纪
    private String strCertRemarkArr[];

    private int intID_1;
    private String strTrainCount_1;// 培训期数
    private String strStudentUnitType_1;// 单位类型
    private String strStudentIDCode_1;// 身份证号
    private String strBirthDay_1;// 出生日期
    private String strStudentName_1;// 姓名
    private String strStudentSex_1;// 性别
    private String strStudentQualifications_1;// 资格类型
    private String strStudentPost_1;// 职务
    private String strStudentTitles_1;// 职称
    private String strStudentDegree_1;// 文化程度
    private String strStudentHealth_1;// 身体状况
    private String strStudentRelation_1;// 联系方式
    private String strStudentCertCode_1;// 证书编号（仅再培训填写）
    private int intIsLeader_1;// 是否督导人员
    private int intIsBlackUser_1;// 是否黑名单

    private float fltExamScore1_1;// 理论成绩
    private float fltExamScore2_1;// 实操成绩
    private String strExamRule_1;// 是否违纪
    private int intIsImport_1;// 是否导入

    private float fltExamScore3_1;// 理论成绩
    private float fltExamScore4_1;// 实操成绩
    private String strExamRule1_1;// 是否违纪
    private int intIsImport1_1;// 是否导入

    // ///////////证书信息
    private int intCertAllow_1;// 是否允许发证
    private String strCertRemark_1;// 备注
    private String strCertCode_1;// 证书号码
    private int intCertCode_1;// 证书号码
    private int intCertYear_1;// 证书年份
    private String strCertDate_1;// 发证日期

    private String strTrainStartDate; // 培训时间--开始时间
    private String strTrainEndDate; // 培训时间--结束时间
    private String strCondition;

    private int intTrainApplyID;
    private String strTrainCountClass;

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

    public int getIntIsNew() {
        return intIsNew;
    }

    public void setIntIsNew(int intIsNew) {
        this.intIsNew = intIsNew;
    }

    public String getStrApplyCode() {
        return (strApplyCode == null) ? "" : strApplyCode;
    }

    public void setStrApplyCode(String strApplyCode) {
        this.strApplyCode = strApplyCode;
    }

    public String getStrApplyDate() {
        return (strApplyDate == null) ? "" : strApplyDate;
    }

    public void setStrApplyDate(String strApplyDate) {
        this.strApplyDate = strApplyDate;
    }

    public String getStrApplyNum() {
        return (strApplyNum == null) ? "" : strApplyNum;
    }

    public void setStrApplyNum(String strApplyNum) {
        this.strApplyNum = strApplyNum;
    }

    // //////////////
    public String getStrTrainCount_1() {
        return (strTrainCount_1 == null) ? "" : strTrainCount_1;
    }

    public void setStrTrainCount_1(String strTrainCount_1) {
        this.strTrainCount_1 = strTrainCount_1;
    }

    public String getStrStudentUnitType_1() {
        return (strStudentUnitType_1 == null) ? "" : strStudentUnitType_1;
    }

    public void setStrStudentUnitType_1(String strStudentUnitType_1) {
        this.strStudentUnitType_1 = strStudentUnitType_1;
    }

    public String getStrStudentIDCode_1() {
        return (strStudentIDCode_1 == null) ? "" : strStudentIDCode_1;
    }

    public void setStrStudentIDCode_1(String strStudentIDCode_1) {
        this.strStudentIDCode_1 = strStudentIDCode_1;
    }

    public String getStrBirthDay_1() {
        return (strBirthDay_1 == null) ? "" : strBirthDay_1;
    }

    public void setStrBirthDay_1(String strBirthDay_1) {
        this.strBirthDay_1 = strBirthDay_1;
    }

    public String getStrStudentName_1() {
        return (strStudentName_1 == null) ? "" : strStudentName_1;
    }

    public void setStrStudentName_1(String strStudentName_1) {
        this.strStudentName_1 = strStudentName_1;
    }

    public String getStrStudentSex_1() {
        return (strStudentSex_1 == null) ? "" : strStudentSex_1;
    }

    public void setStrStudentSex_1(String strStudentSex_1) {
        this.strStudentSex_1 = strStudentSex_1;
    }

    public String getStrStudentQualifications_1() {
        return (strStudentQualifications_1 == null) ? ""
                : strStudentQualifications_1;
    }

    public void setStrStudentQualifications_1(String strStudentQualifications_1) {
        this.strStudentQualifications_1 = strStudentQualifications_1;
    }

    public String getStrStudentPost_1() {
        return (strStudentPost_1 == null) ? "" : strStudentPost_1;
    }

    public void setStrStudentPost_1(String strStudentPost_1) {
        this.strStudentPost_1 = strStudentPost_1;
    }

    public String getStrStudentTitles_1() {
        return (strStudentTitles_1 == null) ? "" : strStudentTitles_1;
    }

    public void setStrStudentTitles_1(String strStudentTitles_1) {
        this.strStudentTitles_1 = strStudentTitles_1;
    }

    public String getStrStudentDegree_1() {
        return (strStudentDegree_1 == null) ? "" : strStudentDegree_1;
    }

    public void setStrStudentDegree_1(String strStudentDegree_1) {
        this.strStudentDegree_1 = strStudentDegree_1;
    }

    public String getStrStudentHealth_1() {
        return (strStudentHealth_1 == null) ? "" : strStudentHealth_1;
    }

    public void setStrStudentHealth_1(String strStudentHealth_1) {
        this.strStudentHealth_1 = strStudentHealth_1;
    }

    public String getStrStudentRelation_1() {
        return (strStudentRelation_1 == null) ? "" : strStudentRelation_1;
    }

    public void setStrStudentRelation_1(String strStudentRelation_1) {
        this.strStudentRelation_1 = strStudentRelation_1;
    }

    public String getStrStudentCertCode_1() {
        return (strStudentCertCode_1 == null) ? "" : strStudentCertCode_1;
    }

    public void setStrStudentCertCode_1(String strStudentCertCode_1) {
        this.strStudentCertCode_1 = strStudentCertCode_1;
    }

    public int getIntIsLeader_1() {
        return intIsLeader_1;
    }

    public void setIntIsLeader_1(int intIsLeader_1) {
        this.intIsLeader_1 = intIsLeader_1;
    }

    public int getIntIsBlackUser_1() {
        return intIsBlackUser_1;
    }

    public void setIntIsBlackUser_1(int intIsBlackUser_1) {
        this.intIsBlackUser_1 = intIsBlackUser_1;
    }

    public float getFltExamScore1_1() {
        return fltExamScore1_1;
    }

    public void setFltExamScore1_1(float fltExamScore1_1) {
        this.fltExamScore1_1 = fltExamScore1_1;
    }

    public float getFltExamScore2_1() {
        return fltExamScore2_1;
    }

    public void setFltExamScore2_1(float fltExamScore2_1) {
        this.fltExamScore2_1 = fltExamScore2_1;
    }

    public String getStrExamRule_1() {
        return (strExamRule_1 == null) ? "" : strExamRule_1;
    }

    public void setStrExamRule_1(String strExamRule_1) {
        this.strExamRule_1 = strExamRule_1;
    }

    public int getIntIsImport_1() {
        return intIsImport_1;
    }

    public void setIntIsImport_1(int intIsImport_1) {
        this.intIsImport_1 = intIsImport_1;
    }

    public float getFltExamScore3_1() {
        return fltExamScore3_1;
    }

    public void setFltExamScore3_1(float fltExamScore3_1) {
        this.fltExamScore3_1 = fltExamScore3_1;
    }

    public float getFltExamScore4_1() {
        return fltExamScore4_1;
    }

    public void setFltExamScore4_1(float fltExamScore4_1) {
        this.fltExamScore4_1 = fltExamScore4_1;
    }

    public String getStrExamRule1_1() {
        return (strExamRule1_1 == null) ? "" : strExamRule1_1;
    }

    public void setStrExamRule1_1(String strExamRule1_1) {
        this.strExamRule1_1 = strExamRule1_1;
    }

    public int getIntIsImport1_1() {
        return intIsImport1_1;
    }

    public void setIntIsImport1_1(int intIsImport1_1) {
        this.intIsImport1_1 = intIsImport1_1;
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

    public int[] getIntIDArr() {
        return intIDArr;
    }

    public void setIntIDArr(int[] intIDArr) {
        this.intIDArr = intIDArr;
    }

    public int[] getIntCertAllowArr() {
        return intCertAllowArr;
    }

    public void setIntCertAllowArr(int[] intCertAllowArr) {
        this.intCertAllowArr = intCertAllowArr;
    }

    public String[] getStrCertRemarkArr() {
        return strCertRemarkArr;
    }

    public void setStrCertRemarkArr(String[] strCertRemarkArr) {
        this.strCertRemarkArr = strCertRemarkArr;
    }

    public int getIntCertAllow_1() {
        return intCertAllow_1;
    }

    public void setIntCertAllow_1(int intCertAllow_1) {
        this.intCertAllow_1 = intCertAllow_1;
    }

    public String getStrCertRemark_1() {
        return (strCertRemark_1 == null) ? "" : strCertRemark_1;
    }

    public void setStrCertRemark_1(String strCertRemark_1) {
        this.strCertRemark_1 = strCertRemark_1;
    }

    public String getStrCertCode_1() {
        return (strCertCode_1 == null) ? "" : strCertCode_1;
    }

    public void setStrCertCode_1(String strCertCode_1) {
        this.strCertCode_1 = strCertCode_1;
    }

    public int getIntID_1() {
        return intID_1;
    }

    public void setIntID_1(int intID_1) {
        this.intID_1 = intID_1;
    }

    public int getIntCertCode_1() {
        return intCertCode_1;
    }

    public void setIntCertCode_1(int intCertCode_1) {
        this.intCertCode_1 = intCertCode_1;
    }

    public int getIntCertYear_1() {
        return intCertYear_1;
    }

    public void setIntCertYear_1(int intCertYear_1) {
        this.intCertYear_1 = intCertYear_1;
    }

    public String getStrIsNewVal() {
        return strIsNewVal;
    }

    public void setStrIsNewVal(String strIsNewVal) {
        this.strIsNewVal = strIsNewVal;
    }

    public String getStrCertDate_1() {
        return (strCertDate_1 == null) ? "" : strCertDate_1;
    }

    public void setStrCertDate_1(String strCertDate_1) {
        this.strCertDate_1 = strCertDate_1;
    }

    public int[] getIntCertScoreArr() {
        return intCertScoreArr;
    }

    public void setIntCertScoreArr(int[] intCertScoreArr) {
        this.intCertScoreArr = intCertScoreArr;
    }

    public int[] getIntIsBKArr() {
        return intIsBKArr;
    }

    public void setIntIsBKArr(int[] intIsBKArr) {
        this.intIsBKArr = intIsBKArr;
    }

    public String getStrCondition() {
        return (strCondition == null) ? "" : strCondition;
    }

    public void setStrCondition(String strCondition) {
        this.strCondition = strCondition;
    }

    public String[] getStrWJArr() {
        return strWJArr;
    }

    public void setStrWJArr(String[] strWJArr) {
        this.strWJArr = strWJArr;
    }

    public String getStrTrainObjectDesc() {
        return strTrainObjectDesc;
    }

    public void setStrTrainObjectDesc(String strTrainObjectDesc) {
        this.strTrainObjectDesc = strTrainObjectDesc;
    }

    public int getIntTrainApplyID() {
        return intTrainApplyID;
    }

    public void setIntTrainApplyID(int intTrainApplyID) {
        this.intTrainApplyID = intTrainApplyID;
    }

    public String getStrTrainCountClass() {
        return strTrainCountClass;
    }

    public void setStrTrainCountClass(String strTrainCountClass) {
        this.strTrainCountClass = strTrainCountClass;
    }

}