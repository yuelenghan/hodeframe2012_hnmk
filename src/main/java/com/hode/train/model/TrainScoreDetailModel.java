/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

import java.util.List;

import com.hode.framework.model.IbatisModel;

/**
 * @author xdju 考试学生明细表
 */
public class TrainScoreDetailModel extends IbatisModel {
    private static final long serialVersionUID = -6920422780502575548L;
    private String strStudentName;// 考生姓名
    private String strStudentIDCode;// 考生身份证号码

    private int intMainID;// 主表关联ID
    private String strTrainCount;// 培训期次
    private String strScoreCount;// 考试批次
    private float fltExamScore1;// 理论成绩
    private float fltExamScore2;// 实操成绩
    private int intIsImport;// 是否导入
    private String strExamRule;// 是否违纪
    private int intIsPass;// 是否审核通过
    private String strRemark;// 备注

    private int intMainID1;// 主表关联ID
    private String strTrainCount1;// 培训期次
    private String strScoreCount1;// 考试批次
    private float fltExamScore3;// 理论成绩
    private float fltExamScore4;// 实操成绩
    private String strExamRule1;// 是否违纪
    private int intIsPass1;// 是否审核通过
    private int intIsImport1;// 是否导入
    private String strRemark1;// 备注

    private float fltTopScore;// 最高成绩

    private int intIsTmpExam;// 是否补考

    private String strScoreExam; // 考试场次
    private String strScoreTrainCount; // 对应的培训期次

    private List<Integer> mainIDList;
    private String strTrainCountClass;

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

    public int getIntMainID() {
        return intMainID;
    }

    public void setIntMainID(int intMainID) {
        this.intMainID = intMainID;
    }

    public String getStrTrainCount() {
        return (strTrainCount == null) ? "" : strTrainCount;
    }

    public void setStrTrainCount(String strTrainCount) {
        this.strTrainCount = strTrainCount;
    }

    public String getStrScoreCount() {
        return (strScoreCount == null) ? "" : strScoreCount;
    }

    public void setStrScoreCount(String strScoreCount) {
        this.strScoreCount = strScoreCount;
    }

    public float getFltExamScore1() {
        return fltExamScore1;
    }

    public void setFltExamScore1(float fltExamScore1) {
        this.fltExamScore1 = fltExamScore1;
    }

    public float getFltExamScore2() {
        return fltExamScore2;
    }

    public void setFltExamScore2(float fltExamScore2) {
        this.fltExamScore2 = fltExamScore2;
    }

    public String getStrExamRule() {
        return (strExamRule == null) ? "" : strExamRule;
    }

    public void setStrExamRule(String strExamRule) {
        this.strExamRule = strExamRule;
    }

    public int getIntIsPass() {
        return intIsPass;
    }

    public void setIntIsPass(int intIsPass) {
        this.intIsPass = intIsPass;
    }

    public int getIntMainID1() {
        return intMainID1;
    }

    public void setIntMainID1(int intMainID1) {
        this.intMainID1 = intMainID1;
    }

    public String getStrTrainCount1() {
        return (strTrainCount1 == null) ? "" : strTrainCount1;
    }

    public void setStrTrainCount1(String strTrainCount1) {
        this.strTrainCount1 = strTrainCount1;
    }

    public String getStrScoreCount1() {
        return (strScoreCount1 == null) ? "" : strScoreCount1;
    }

    public void setStrScoreCount1(String strScoreCount1) {
        this.strScoreCount1 = strScoreCount1;
    }

    public float getFltExamScore3() {
        return fltExamScore3;
    }

    public void setFltExamScore3(float fltExamScore3) {
        this.fltExamScore3 = fltExamScore3;
    }

    public float getFltExamScore4() {
        return fltExamScore4;
    }

    public void setFltExamScore4(float fltExamScore4) {
        this.fltExamScore4 = fltExamScore4;
    }

    public String getStrExamRule1() {
        return (strExamRule1 == null) ? "" : strExamRule1;
    }

    public void setStrExamRule1(String strExamRule1) {
        this.strExamRule1 = strExamRule1;
    }

    public int getIntIsPass1() {
        return intIsPass1;
    }

    public void setIntIsPass1(int intIsPass1) {
        this.intIsPass1 = intIsPass1;
    }

    public int getIntIsTmpExam() {
        return intIsTmpExam;
    }

    public void setIntIsTmpExam(int intIsTmpExam) {
        this.intIsTmpExam = intIsTmpExam;
    }

    public int getIntIsImport() {
        return intIsImport;
    }

    public void setIntIsImport(int intIsImport) {
        this.intIsImport = intIsImport;
    }

    public int getIntIsImport1() {
        return intIsImport1;
    }

    public void setIntIsImport1(int intIsImport1) {
        this.intIsImport1 = intIsImport1;
    }

    public float getFltTopScore() {
        return fltTopScore;
    }

    public void setFltTopScore(float fltTopScore) {
        this.fltTopScore = fltTopScore;
    }

    public String getStrRemark() {
        return (strRemark == null) ? "" : strRemark;
    }

    public void setStrRemark(String strRemark) {
        this.strRemark = strRemark;
    }

    public String getStrRemark1() {
        return (strRemark1 == null) ? "" : strRemark1;
    }

    public void setStrRemark1(String strRemark1) {
        this.strRemark1 = strRemark1;
    }

    public String getStrScoreExam() {
        return strScoreExam;
    }

    public void setStrScoreExam(String strScoreExam) {
        this.strScoreExam = strScoreExam;
    }

    public String getStrScoreTrainCount() {
        return strScoreTrainCount;
    }

    public void setStrScoreTrainCount(String strScoreTrainCount) {
        this.strScoreTrainCount = strScoreTrainCount;
    }

    public List<Integer> getMainIDList() {
        return mainIDList;
    }

    public void setMainIDList(List<Integer> mainIDList) {
        this.mainIDList = mainIDList;
    }

    public String getStrTrainCountClass() {
        return strTrainCountClass;
    }

    public void setStrTrainCountClass(String strTrainCountClass) {
        this.strTrainCountClass = strTrainCountClass;
    }

}