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
 * @author xdju 培训机构提交办班申请
 */
public class TrainStudentDetailModel extends IbatisModel {

	private static final long serialVersionUID = 2388599229544561744L;

	private int intMainID;// 与主表关联ID

	private String strTrainCount;// 培训期数

	private String strStudentUnitType;// 单位类型
	private String strStudentIDCode;// 身份证号
	private String strStudentIDAddr; // 身份证地址
	private String strBirthDay;// 出生日期
	private String strStudentName;// 姓名
	private String strStudentSex;// 性别
	private int intStudentAge; // 年龄
	private String strStudentQualifications;// 资格类型
	private String strStudentPost;// 单位及部门
	private String strStudentTitles;// 职务
	private String strStudentDegree;// 文化程度
	private String strStudentFirstDate;// 初次领证时间
	private String strStudentHealth;// 身体状况
	private String strStudentRelation;// 联系方式
	private String strStudentCertCode;// 证书编号（仅再培训填写）
	private String strStudentRemark;// 备注
	private String strStudentViolate; // 违章作业情况
	private int intTrainYear;// 年份
	private int intIsSignUp; // 是否网上报名 1：是
	private String strTrainUnitName; // 培训机构名称（网上报名填写）
	private int intIsProcess; // 网上报名是否被处理 0：未处理 1：已处理
	private String strTrainType;

	private String strStudentPic1;// 相片
	private String strStudentPic2;// 学历
	private String strStudentPic3;// 体检表
	private String strStudentPic4;// 相关工作经历

	private int intIndex;

	private int intIsLeader;// 是否督导人员
	private int intIsBlackUser;// 是否黑名单
	private String strEndDate;// 日期
	// ///////////证书信息
	private int intCertAllow;// 是否允许发证 1:允许 2：不允许
	private String strCertRemark;// 备注
	private String strCertCode;// 证书号码
	private String strCertDate; // 发证日期

	// /////////辅助字段
	private int intIDArr[];
	private int intIDLeaderArr[];
	private String strStudentRemarkArr[];
	private List<Integer> mainIDList;
	private String strTrainCountClass;

	// 单个编辑学员信息
	private String flag;

	public int getIntMainID() {
		return intMainID;
	}

	public void setIntMainID(int intMainID) {
		this.intMainID = intMainID;
	}

	public String getStrStudentUnitType() {
		return (strStudentUnitType == null) ? "" : strStudentUnitType;
	}

	public void setStrStudentUnitType(String strStudentUnitType) {
		this.strStudentUnitType = strStudentUnitType;
	}

	public String getStrStudentIDCode() {
		return (strStudentIDCode == null) ? "" : strStudentIDCode;
	}

	public void setStrStudentIDCode(String strStudentIDCode) {
		this.strStudentIDCode = strStudentIDCode;
	}

	public String getStrStudentName() {
		return (strStudentName == null) ? "" : strStudentName;
	}

	public void setStrStudentName(String strStudentName) {
		this.strStudentName = strStudentName;
	}

	public String getStrStudentSex() {
		return (strStudentSex == null) ? "" : strStudentSex;
	}

	public void setStrStudentSex(String strStudentSex) {
		this.strStudentSex = strStudentSex;
	}

	public String getStrStudentQualifications() {
		return (strStudentQualifications == null) ? "" : strStudentQualifications;
	}

	public void setStrStudentQualifications(String strStudentQualifications) {
		this.strStudentQualifications = strStudentQualifications;
	}

	public String getStrStudentPost() {
		return (strStudentPost == null) ? "" : strStudentPost;
	}

	public void setStrStudentPost(String strStudentPost) {
		this.strStudentPost = strStudentPost;
	}

	public String getStrStudentDegree() {
		return (strStudentDegree == null) ? "" : strStudentDegree;
	}

	public void setStrStudentDegree(String strStudentDegree) {
		this.strStudentDegree = strStudentDegree;
	}

	public String getStrStudentFirstDate() {
		return (strStudentFirstDate == null) ? "" : strStudentFirstDate;
	}

	public void setStrStudentFirstDate(String strStudentFirstDate) {
		this.strStudentFirstDate = strStudentFirstDate;
	}

	public String getStrStudentHealth() {
		return (strStudentHealth == null) ? "" : strStudentHealth;
	}

	public void setStrStudentHealth(String strStudentHealth) {
		this.strStudentHealth = strStudentHealth;
	}

	public String getStrStudentRelation() {
		return (strStudentRelation == null) ? "" : strStudentRelation;
	}

	public void setStrStudentRelation(String strStudentRelation) {
		this.strStudentRelation = strStudentRelation;
	}

	public String getStrStudentCertCode() {
		return (strStudentCertCode == null) ? "" : strStudentCertCode;
	}

	public void setStrStudentCertCode(String strStudentCertCode) {
		this.strStudentCertCode = strStudentCertCode;
	}

	public String getStrStudentPic1() {
		return (strStudentPic1 == null) ? "" : strStudentPic1;
	}

	public void setStrStudentPic1(String strStudentPic1) {
		this.strStudentPic1 = strStudentPic1;
	}

	public String getStrStudentPic2() {
		return (strStudentPic2 == null) ? "" : strStudentPic2;
	}

	public void setStrStudentPic2(String strStudentPic2) {
		this.strStudentPic2 = strStudentPic2;
	}

	public String getStrStudentPic3() {
		return (strStudentPic3 == null) ? "" : strStudentPic3;
	}

	public void setStrStudentPic3(String strStudentPic3) {
		this.strStudentPic3 = strStudentPic3;
	}

	public String getStrStudentPic4() {
		return (strStudentPic4 == null) ? "" : strStudentPic4;
	}

	public void setStrStudentPic4(String strStudentPic4) {
		this.strStudentPic4 = strStudentPic4;
	}

	public String getStrStudentTitles() {
		return (strStudentTitles == null) ? "" : strStudentTitles;
	}

	public void setStrStudentTitles(String strStudentTitles) {
		this.strStudentTitles = strStudentTitles;
	}

	public int getIntIsLeader() {
		return intIsLeader;
	}

	public void setIntIsLeader(int intIsLeader) {
		this.intIsLeader = intIsLeader;
	}

	public int[] getIntIDArr() {
		return intIDArr;
	}

	public void setIntIDArr(int[] intIDArr) {
		this.intIDArr = intIDArr;
	}

	public int[] getIntIDLeaderArr() {
		return intIDLeaderArr;
	}

	public void setIntIDLeaderArr(int[] intIDLeaderArr) {
		this.intIDLeaderArr = intIDLeaderArr;
	}

	public int getIntTrainYear() {
		return intTrainYear;
	}

	public void setIntTrainYear(int intTrainYear) {
		this.intTrainYear = intTrainYear;
	}

	public String getStrBirthDay() {
		return (strBirthDay == null) ? "" : strBirthDay;
	}

	public void setStrBirthDay(String strBirthDay) {
		this.strBirthDay = strBirthDay;
	}

	public String getStrTrainCount() {
		return (strTrainCount == null) ? "" : strTrainCount;
	}

	public void setStrTrainCount(String strTrainCount) {
		this.strTrainCount = strTrainCount;
	}

	public int getIntIsBlackUser() {
		return intIsBlackUser;
	}

	public void setIntIsBlackUser(int intIsBlackUser) {
		this.intIsBlackUser = intIsBlackUser;
	}

	public int getIntCertAllow() {
		return intCertAllow;
	}

	public void setIntCertAllow(int intCertAllow) {
		this.intCertAllow = intCertAllow;
	}

	public String getStrCertRemark() {
		return (strCertRemark == null) ? "" : strCertRemark;
	}

	public void setStrCertRemark(String strCertRemark) {
		this.strCertRemark = strCertRemark;
	}

	public String getStrCertCode() {
		return (strCertCode == null) ? "" : strCertCode;
	}

	public void setStrCertCode(String strCertCode) {
		this.strCertCode = strCertCode;
	}

	public String getStrEndDate() {
		return (strEndDate == null) ? "" : strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	public String getStrStudentRemark() {
		return (strStudentRemark == null) ? "" : strStudentRemark;
	}

	public void setStrStudentRemark(String strStudentRemark) {
		this.strStudentRemark = strStudentRemark;
	}

	public String[] getStrStudentRemarkArr() {
		return strStudentRemarkArr;
	}

	public void setStrStudentRemarkArr(String[] strStudentRemarkArr) {
		this.strStudentRemarkArr = strStudentRemarkArr;
	}

	public String getStrStudentIDAddr() {
		return strStudentIDAddr;
	}

	public void setStrStudentIDAddr(String strStudentIDAddr) {
		this.strStudentIDAddr = strStudentIDAddr;
	}

	public String getStrStudentViolate() {
		return strStudentViolate;
	}

	public void setStrStudentViolate(String strStudentViolate) {
		this.strStudentViolate = strStudentViolate;
	}

	public int getIntStudentAge() {
		return intStudentAge;
	}

	public void setIntStudentAge(int intStudentAge) {
		this.intStudentAge = intStudentAge;
	}

	public String getStrCertDate() {
		return strCertDate;
	}

	public void setStrCertDate(String strCertDate) {
		this.strCertDate = strCertDate;
	}

	public int getIntIsSignUp() {
		return intIsSignUp;
	}

	public void setIntIsSignUp(int intIsSignUp) {
		this.intIsSignUp = intIsSignUp;
	}

	public String getStrTrainUnitName() {
		return strTrainUnitName;
	}

	public void setStrTrainUnitName(String strTrainUnitName) {
		this.strTrainUnitName = strTrainUnitName;
	}

	public int getIntIsProcess() {
		return intIsProcess;
	}

	public void setIntIsProcess(int intIsProcess) {
		this.intIsProcess = intIsProcess;
	}

	public String getStrTrainType() {
		return strTrainType;
	}

	public void setStrTrainType(String strTrainType) {
		this.strTrainType = strTrainType;
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

	public int getIntIndex() {
		return intIndex;
	}

	public void setIntIndex(int intIndex) {
		this.intIndex = intIndex;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}