package com.hode.train.model;

/**
 * 校验人员重复信息的视图文件
 *
 * @author yuelenghan
 *
 */
public class TrainStudentDetailRepeatVO {

    /**
     * 身份证号
     */
    private String strStudentIDCode;

    /**
     * 重复次数
     */
    private int repeatNum;

    public String getStrStudentIDCode() {
        return strStudentIDCode;
    }

    public void setStrStudentIDCode(String strStudentIDCode) {
        this.strStudentIDCode = strStudentIDCode;
    }

    public int getRepeatNum() {
        return repeatNum;
    }

    public void setRepeatNum(int repeatNum) {
        this.repeatNum = repeatNum;
    }

}
