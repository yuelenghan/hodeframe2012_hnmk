package com.hode.train.model;

import java.util.List;
import java.util.Map;

/**
 * 封装证书打印需要的详细信息
 *
 * @author lh
 *
 */
public class TrainCertDetailModel {

    private String certDate; // 发证日期
    private String validDate; // 有效期结束日期
    private String certCode; // 证书编号
    private List<Map<String, String>> courseList; // 课程信息
    private String applyStartDate; // 培训开始日期
    private String applyEndDate; // 培训结束日期
    private String studentName; // 姓名
    private String sex; // 性别
    private String titleLevel; // 职称
    private String degree; // 文化程度
    private String idCard; // 身份证号
    private String unitType; // 单位类型
    private String qualifications; // 资格类型
    private String post; // 单位
    private String title; // 职务
    private String culDate; // 当前日期
    private String studentRemark;// 备注
    private String trainType; // 培训类型
    private String QRCodePath; // 二维码存放路径
    private String studentPic1Path; // 照片存放路径

    private String ifPic; // 是否有照片信息 1.有
    private String ifQRCode; // 是否有二维码 1.有

    public String getCertDate() {
        return certDate;
    }

    public void setCertDate(String certDate) {
        this.certDate = certDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public List<Map<String, String>> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Map<String, String>> courseList) {
        this.courseList = courseList;
    }

    public String getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public String getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(String applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCulDate() {
        return culDate;
    }

    public void setCulDate(String culDate) {
        this.culDate = culDate;
    }

    public String getStudentRemark() {
        return studentRemark;
    }

    public void setStudentRemark(String studentRemark) {
        this.studentRemark = studentRemark;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getQRCodePath() {
        return QRCodePath;
    }

    public void setQRCodePath(String qRCodePath) {
        QRCodePath = qRCodePath;
    }

    public String getStudentPic1Path() {
        return studentPic1Path;
    }

    public void setStudentPic1Path(String studentPic1Path) {
        this.studentPic1Path = studentPic1Path;
    }

    public String getIfPic() {
        return ifPic;
    }

    public void setIfPic(String ifPic) {
        this.ifPic = ifPic;
    }

    public String getIfQRCode() {
        return ifQRCode;
    }

    public void setIfQRCode(String ifQRCode) {
        this.ifQRCode = ifQRCode;
    }
}
