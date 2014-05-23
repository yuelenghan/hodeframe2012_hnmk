/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

/**
 * @author xdju 考试成绩
 */
public class TrainScoreModel extends TrainBaseModel {

    // intCheckFlag: 0:待提交 1:已提交待审核 9:已审核
    private static final long serialVersionUID = 4627218839226533918L;
    private String strScoreCount;// ;考试批次
    private String strScoreDate;// 考试时间
    private String strScoreUseNum;// 考试人数--用户填写
    private int intScoreUseNum;// 考试人数--实际导入
    private int intScoreUseNum1;// 补考人数

    private String strTrainCount;// 培训期次

    private String strScoreExam; // 考试场次

    private String strScoreTrainCount; // 对应的培训期次

    private String strTrainCountClass;

    private int intScoreType; // 0: 正考		1：补考

    public String getStrScoreCount() {
        return (strScoreCount == null) ? "" : strScoreCount;
    }

    public void setStrScoreCount(String strScoreCount) {
        this.strScoreCount = strScoreCount;
    }

    public String getStrScoreDate() {
        return (strScoreDate == null) ? "" : strScoreDate;
    }

    public void setStrScoreDate(String strScoreDate) {
        this.strScoreDate = strScoreDate;
    }

    public String getStrScoreUseNum() {
        return (strScoreUseNum == null) ? "" : strScoreUseNum;
    }

    public void setStrScoreUseNum(String strScoreUseNum) {
        this.strScoreUseNum = strScoreUseNum;
    }

    public int getIntScoreUseNum() {
        return intScoreUseNum;
    }

    public void setIntScoreUseNum(int intScoreUseNum) {
        this.intScoreUseNum = intScoreUseNum;
    }

    public int getIntScoreUseNum1() {
        return intScoreUseNum1;
    }

    public void setIntScoreUseNum1(int intScoreUseNum1) {
        this.intScoreUseNum1 = intScoreUseNum1;
    }

    public String getStrTrainCount() {
        return (strTrainCount == null) ? "" : strTrainCount;
    }

    public void setStrTrainCount(String strTrainCount) {
        this.strTrainCount = strTrainCount;
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

    public String getStrTrainCountClass() {
        return strTrainCountClass;
    }

    public void setStrTrainCountClass(String strTrainCountClass) {
        this.strTrainCountClass = strTrainCountClass;
    }

    public int getIntScoreType() {
        return intScoreType;
    }

    public void setIntScoreType(int intScoreType) {
        this.intScoreType = intScoreType;
    }

}