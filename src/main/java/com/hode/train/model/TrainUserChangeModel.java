/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;


/**
 * @author xdju
 *  人员变更
 */
public class TrainUserChangeModel extends TrainBaseModel
{

    private static final long serialVersionUID = 2026376984144832337L;
    private String strCode;//受理编号
    private int intYear;//受理年份
    private String strApplyDate;//受理日期
    private int intApplyNum;//受理人数
    //辅助字段
    private String strSearchStartDate;
    private String strSearchEndDate;

    public String getStrCode() {
        return (strCode == null) ? "" : strCode;
    }
    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }
    public int getIntYear() {
        return intYear;
    }
    public void setIntYear(int intYear) {
        this.intYear = intYear;
    }
    public String getStrApplyDate() {
        return (strApplyDate == null) ? "" : strApplyDate;
    }
    public void setStrApplyDate(String strApplyDate) {
        this.strApplyDate = strApplyDate;
    }
    public int getIntApplyNum() {
        return intApplyNum;
    }
    public void setIntApplyNum(int intApplyNum) {
        this.intApplyNum = intApplyNum;
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