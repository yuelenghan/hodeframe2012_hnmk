/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.ITreeNodeModel;
import com.hode.framework.model.IbatisModel;

public class GroupModel extends IbatisModel implements ITreeNodeModel
{
    
    public final static int GROUP_SYSTEM = 0;
    public final static int GROUP_UNIT = 1;
    public final static int GROUP_DEPARTMENT = 2;
    
    private int intID;
    private String strName="";             //部门名称 
    private int intFatherID;            //父ID
    private int intLevel;               //所在层数
    private String strDescribe;         //描述
    private int intGroupType;           //部门类型
    private int intGroupFlag;            //性质   1:类别    2：信息
    private String strMachineCode;        //培训依据
    
    private String strFax;				//传真
    private String strTel;		        //电话
    private String strMobile;           //手机
    private String strAddress ;			//教学地点
    private String addr="";                //单位所在地
    private String strZipCode;		    //邮编
    private String strEmail;            //邮箱
    //增加
    private String strShortName;        //单位简称
    private String strType="";             //机构类型
    private String strQualification;    //资质证书号
    private int intQualificationLevel;   //资质级别
    private String strQualificationStart;  //资质初次 授予时间
	private String strQualificationPrev;   //上次资质复审时间
	private String strQualificationNext;   //资质下次复审时间
	private String strQualificationDept;   //资质审批机关
	private String strGroupCreateTime;     //单位组建时间
	private String strChartArea;          //行政区划
	private String strRegMoney;           //注册资金
	private String strChartMan;           //负责人
	private int intEmployeeNum;           //职工人数
	private int intTrainCapacity;         //最大培训能力
	private String strTrainRange;         //许可培训范围
	private String strContactMan;         //联系方式-联系人
	private String strContactPhone;       //联系方式-办公电话
	private String strContactIphone;      //联系方式-手机
	private String strContactFax;         //联系方式-传真
	private String strWebsite;            //单位网站地址
	private String strContactAddress;     //通讯地址
	private String strGroupDescrible;     //培训机构自我简介
	private String strAuthorizeDept;      //授权单位
	private String strExamRange;          //考核范围
	private String strSaftyCertNum="";       //安全生产许可证编号
	private String strSaftyCertEndtime;   //安全生产许可证有效期截止日期
	private String strCapacity="";           //核定能力;
	private String strGasLevel="";           //瓦斯等级
	private String strBelongsBranch;      //所属分局
	private int intState;                 //是否可用
	
	
	
	
    public int getIntID() {
		return intID;
	}
	public void setIntID(int intID) {
		this.intID = intID;
	}
	
    
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getStrShortName() {
		return strShortName;
	}
	public void setStrShortName(String strShortName) {
		this.strShortName = strShortName;
	}
	public String getStrType() {
		return strType;
	}
	public void setStrType(String strType) {
		this.strType = strType;
	}
	public String getStrQualification() {
		return strQualification;
	}
	public void setStrQualification(String strQualification) {
		this.strQualification = strQualification;
	}
	public int getIntQualificationLevel() {
		return intQualificationLevel;
	}
	public void setIntQualificationLevel(int intQualificationLevel) {
		this.intQualificationLevel = intQualificationLevel;
	}
	public String getStrQualificationStart() {
		return strQualificationStart;
	}
	public void setStrQualificationStart(String strQualificationStart) {
		this.strQualificationStart = strQualificationStart;
	}
	public String getStrQualificationPrev() {
		return strQualificationPrev;
	}
	public void setStrQualificationPrev(String strQualificationPrev) {
		this.strQualificationPrev = strQualificationPrev;
	}
	public String getStrQualificationNext() {
		return strQualificationNext;
	}
	public void setStrQualificationNext(String strQualificationNext) {
		this.strQualificationNext = strQualificationNext;
	}
	public String getStrQualificationDept() {
		return strQualificationDept;
	}
	public void setStrQualificationDept(String strQualificationDept) {
		this.strQualificationDept = strQualificationDept;
	}
	public String getStrGroupCreateTime() {
		return strGroupCreateTime;
	}
	public void setStrGroupCreateTime(String strGroupCreateTime) {
		this.strGroupCreateTime = strGroupCreateTime;
	}
	public String getStrChartArea() {
		return strChartArea;
	}
	public void setStrChartArea(String strChartArea) {
		this.strChartArea = strChartArea;
	}
	public String getStrRegMoney() {
		return strRegMoney;
	}
	public void setStrRegMoney(String strRegMoney) {
		this.strRegMoney = strRegMoney;
	}
	public String getStrChartMan() {
		return strChartMan;
	}
	public void setStrChartMan(String strChartMan) {
		this.strChartMan = strChartMan;
	}
	public int getIntEmployeeNum() {
		return intEmployeeNum;
	}
	public void setIntEmployeeNum(int intEmployeeNum) {
		this.intEmployeeNum = intEmployeeNum;
	}
	public int getIntTrainCapacity() {
		return intTrainCapacity;
	}
	public void setIntTrainCapacity(int intTrainCapacity) {
		this.intTrainCapacity = intTrainCapacity;
	}
	public String getStrTrainRange() {
		return strTrainRange;
	}
	public void setStrTrainRange(String strTrainRange) {
		this.strTrainRange = strTrainRange;
	}
	public String getStrContactMan() {
		return strContactMan;
	}
	public void setStrContactMan(String strContactMan) {
		this.strContactMan = strContactMan;
	}
	public String getStrContactPhone() {
		return strContactPhone;
	}
	public void setStrContactPhone(String strContactPhone) {
		this.strContactPhone = strContactPhone;
	}
	public String getStrContactIphone() {
		return strContactIphone;
	}
	public void setStrContactIphone(String strContactIphone) {
		this.strContactIphone = strContactIphone;
	}
	public String getStrContactFax() {
		return strContactFax;
	}
	public void setStrContactFax(String strContactFax) {
		this.strContactFax = strContactFax;
	}
	public String getStrWebsite() {
		return strWebsite;
	}
	public void setStrWebsite(String strWebsite) {
		this.strWebsite = strWebsite;
	}
	public String getStrContactAddress() {
		return strContactAddress;
	}
	public void setStrContactAddress(String strContactAddress) {
		this.strContactAddress = strContactAddress;
	}
	public String getStrGroupDescrible() {
		return strGroupDescrible;
	}
	public void setStrGroupDescrible(String strGroupDescrible) {
		this.strGroupDescrible = strGroupDescrible;
	}
	public String getStrAuthorizeDept() {
		return strAuthorizeDept;
	}
	public void setStrAuthorizeDept(String strAuthorizeDept) {
		this.strAuthorizeDept = strAuthorizeDept;
	}
	public String getStrExamRange() {
		return strExamRange;
	}
	public void setStrExamRange(String strExamRange) {
		this.strExamRange = strExamRange;
	}
	public String getStrSaftyCertNum() {
		return strSaftyCertNum;
	}
	public void setStrSaftyCertNum(String strSaftyCertNum) {
		this.strSaftyCertNum = strSaftyCertNum;
	}
	public String getStrSaftyCertEndtime() {
		return strSaftyCertEndtime;
	}
	public void setStrSaftyCertEndtime(String strSaftyCertEndtime) {
		this.strSaftyCertEndtime = strSaftyCertEndtime;
	}
	public String getStrCapacity() {
		return strCapacity;
	}
	public void setStrCapacity(String strCapacity) {
		this.strCapacity = strCapacity;
	}
	public String getStrGasLevel() {
		return strGasLevel;
	}
	public void setStrGasLevel(String strGasLevel) {
		this.strGasLevel = strGasLevel;
	}
	public int getIntState() {
		return intState;
	}
	public void setIntState(int intState) {
		this.intState = intState;
	}
	public String getStrName()
	{
		return (strName == null) ? "" : strName;
	}
	public void setStrName(String strName)
	{
		this.strName = strName;
	}
	public int getIntFatherID()
	{
		return intFatherID;
	}
	public void setIntFatherID(int intFatherID)
	{
		this.intFatherID = intFatherID;
	}
	public int getIntLevel()
	{
		return intLevel;
	}
	public void setIntLevel(int intLevel)
	{
		this.intLevel = intLevel;
	}
	public String getStrDescribe()
	{
		return (strDescribe == null) ? "" : strDescribe;
	}
	public void setStrDescribe(String strDescribe)
	{
		this.strDescribe = strDescribe;
	}
	public int getIntGroupType()
	{
		return intGroupType;
	}
	public void setIntGroupType(int intGroupType)
	{
		this.intGroupType = intGroupType;
	}
	public int getIntGroupFlag()
	{
		return intGroupFlag;
	}
	public void setIntGroupFlag(int intGroupFlag)
	{
		this.intGroupFlag = intGroupFlag;
	}
	public String getStrMachineCode()
	{
		return (strMachineCode == null) ? "" : strMachineCode;
	}
	public void setStrMachineCode(String strMachineCode)
	{
		this.strMachineCode = strMachineCode;
	}
	public String getStrFax()
	{
		return (strFax == null) ? "" : strFax;
	}
	public void setStrFax(String strFax)
	{
		this.strFax = strFax;
	}
	public String getStrTel()
	{
		return (strTel == null) ? "" : strTel;
	}
	public void setStrTel(String strTel)
	{
		this.strTel = strTel;
	}
	public String getStrMobile()
	{
		return (strMobile == null) ? "" : strMobile;
	}
	public void setStrMobile(String strMobile)
	{
		this.strMobile = strMobile;
	}
	public String getStrAddress()
	{
		return (strAddress == null) ? "" : strAddress;
	}
	public void setStrAddress(String strAddress)
	{
		this.strAddress = strAddress;
	}
	public String getStrZipCode()
	{
		return (strZipCode == null) ? "" : strZipCode;
	}
	public void setStrZipCode(String strZipCode)
	{
		this.strZipCode = strZipCode;
	}
	public String getStrEmail()
	{
		return (strEmail == null) ? "" : strEmail;
	}
	public void setStrEmail(String strEmail)
	{
		this.strEmail = strEmail;
	}
	public String getStrBelongsBranch() {
		return strBelongsBranch;
	}
	public void setStrBelongsBranch(String strBelongsBranch) {
		this.strBelongsBranch = strBelongsBranch;
	}
	
   
    
}
