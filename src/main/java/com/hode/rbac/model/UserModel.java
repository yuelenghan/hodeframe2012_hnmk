/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;
import com.hode.framework.model.IbatisModel;
public class UserModel extends IbatisModel 
{
    private String strAccount;              //帐号
    private String strPassword;             //密码
    private String strConfirmPassword;      //确认密码
    private String strOldPassword;          //就密码
    private int intUserType;            //用户类型 1-系统管理员 2-一般操作员
    private int intGroupID;             //对应的部门
    private int intIsCheck;             //是否审核
    
    private String strName;                 //姓名
    private String strUserUnitName;       //单位名称
    private String strAddress;          //联系地址
    private String strZipCode;          //邮政编码
    private String strTel;              //电话
    private String strMobile;           //手机
    private String strEmail;            //电子邮件
    
    private String strMachineCode;       //机器号
    private int intErrorLoginCount;		//错误登陆次数
    private String strLoginTime;		//登陆时间
    private String strLastLoginTime;	//上次登陆时间
    private String strLockTime;			//锁定时间
    private int intLineNum;              //显示条数

    
    private int intPersonID;	//其他关联ID人事
    private String strPersonName;
    
    private String strOtherSysUser1;
    private String strOtherSysPwd1;
    private String strOtherSysUser2;
    private String strOtherSysPwd2;
    private String strOtherSysUser3;
    private String strOtherSysPwd3;
    private String strOtherSysUser4;
    private String strOtherSysPwd4;
    private String strOtherSysUser5;
    private String strOtherSysPwd5;
    
    ///////////领导，最多是五级
    private int intParentUserID1;//撰写人所在领导1
    private String strParentUserName1;//撰写人所在领导1
    private int intParentUserID2;//撰写人所在领导2
    private String strParentUserName2;//撰写人所在领导2
    private int intParentUserID3;//撰写人所在领导3
    private String strParentUserName3;//撰写人所在领导3
    private int intParentUserID4;//撰写人所在领导4
    private String strParentUserName4;//撰写人所在领导4
    private int intParentUserID5;//撰写人所在领导5
    private String strParentUserName5;//撰写人所在领导5
    
 	//授权访问
    private int intAllowUserID;	
    private String strAllowUserName;
    private String strAllowStartDate;//授权时间开始时间
    private String strAllowEndDate;//授权时间结束时间
	public String getStrAccount()
	{
		return (strAccount == null) ? "" : strAccount;
	}
	public void setStrAccount(String strAccount)
	{
		this.strAccount = strAccount;
	}
	public String getStrPassword()
	{
		return (strPassword == null) ? "" : strPassword;
	}
	public void setStrPassword(String strPassword)
	{
		this.strPassword = strPassword;
	}
	public String getStrConfirmPassword()
	{
		return (strConfirmPassword == null) ? "" : strConfirmPassword;
	}
	public void setStrConfirmPassword(String strConfirmPassword)
	{
		this.strConfirmPassword = strConfirmPassword;
	}
	public String getStrOldPassword()
	{
		return (strOldPassword == null) ? "" : strOldPassword;
	}
	public void setStrOldPassword(String strOldPassword)
	{
		this.strOldPassword = strOldPassword;
	}
	public int getIntUserType()
	{
		return intUserType;
	}
	public void setIntUserType(int intUserType)
	{
		this.intUserType = intUserType;
	}
	public int getIntGroupID()
	{
		return intGroupID;
	}
	public void setIntGroupID(int intGroupID)
	{
		this.intGroupID = intGroupID;
	}
	public int getIntIsCheck()
	{
		return intIsCheck;
	}
	public void setIntIsCheck(int intIsCheck)
	{
		this.intIsCheck = intIsCheck;
	}
	public String getStrName()
	{
		return (strName == null) ? "" : strName;
	}
	public void setStrName(String strName)
	{
		this.strName = strName;
	}
	public String getStrUserUnitName()
	{
		return (strUserUnitName == null) ? "" : strUserUnitName;
	}
	public void setStrUserUnitName(String strUserUnitName)
	{
		this.strUserUnitName = strUserUnitName;
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
	public String getStrEmail()
	{
		return (strEmail == null) ? "" : strEmail;
	}
	public void setStrEmail(String strEmail)
	{
		this.strEmail = strEmail;
	}
	public String getStrMachineCode()
	{
		return (strMachineCode == null) ? "" : strMachineCode;
	}
	public void setStrMachineCode(String strMachineCode)
	{
		this.strMachineCode = strMachineCode;
	}
	public int getIntErrorLoginCount()
	{
		return intErrorLoginCount;
	}
	public void setIntErrorLoginCount(int intErrorLoginCount)
	{
		this.intErrorLoginCount = intErrorLoginCount;
	}
	public String getStrLoginTime()
	{
		return (strLoginTime == null) ? "" : strLoginTime;
	}
	public void setStrLoginTime(String strLoginTime)
	{
		this.strLoginTime = strLoginTime;
	}
	public String getStrLastLoginTime()
	{
		return (strLastLoginTime == null) ? "" : strLastLoginTime;
	}
	public void setStrLastLoginTime(String strLastLoginTime)
	{
		this.strLastLoginTime = strLastLoginTime;
	}
	public String getStrLockTime()
	{
		return (strLockTime == null) ? "" : strLockTime;
	}
	public void setStrLockTime(String strLockTime)
	{
		this.strLockTime = strLockTime;
	}
	public int getIntLineNum()
	{
		return intLineNum;
	}
	public void setIntLineNum(int intLineNum)
	{
		this.intLineNum = intLineNum;
	}
	public int getIntPersonID()
	{
		return intPersonID;
	}
	public void setIntPersonID(int intPersonID)
	{
		this.intPersonID = intPersonID;
	}
	public String getStrOtherSysUser1()
	{
		return (strOtherSysUser1 == null) ? "" : strOtherSysUser1;
	}
	public void setStrOtherSysUser1(String strOtherSysUser1)
	{
		this.strOtherSysUser1 = strOtherSysUser1;
	}
	public String getStrOtherSysPwd1()
	{
		return (strOtherSysPwd1 == null) ? "" : strOtherSysPwd1;
	}
	public void setStrOtherSysPwd1(String strOtherSysPwd1)
	{
		this.strOtherSysPwd1 = strOtherSysPwd1;
	}
	public String getStrOtherSysUser2()
	{
		return (strOtherSysUser2 == null) ? "" : strOtherSysUser2;
	}
	public void setStrOtherSysUser2(String strOtherSysUser2)
	{
		this.strOtherSysUser2 = strOtherSysUser2;
	}
	public String getStrOtherSysPwd2()
	{
		return (strOtherSysPwd2 == null) ? "" : strOtherSysPwd2;
	}
	public void setStrOtherSysPwd2(String strOtherSysPwd2)
	{
		this.strOtherSysPwd2 = strOtherSysPwd2;
	}
	public String getStrOtherSysUser3()
	{
		return (strOtherSysUser3 == null) ? "" : strOtherSysUser3;
	}
	public void setStrOtherSysUser3(String strOtherSysUser3)
	{
		this.strOtherSysUser3 = strOtherSysUser3;
	}
	public String getStrOtherSysPwd3()
	{
		return (strOtherSysPwd3 == null) ? "" : strOtherSysPwd3;
	}
	public void setStrOtherSysPwd3(String strOtherSysPwd3)
	{
		this.strOtherSysPwd3 = strOtherSysPwd3;
	}
	public String getStrOtherSysUser4()
	{
		return (strOtherSysUser4 == null) ? "" : strOtherSysUser4;
	}
	public void setStrOtherSysUser4(String strOtherSysUser4)
	{
		this.strOtherSysUser4 = strOtherSysUser4;
	}
	public String getStrOtherSysPwd4()
	{
		return (strOtherSysPwd4 == null) ? "" : strOtherSysPwd4;
	}
	public void setStrOtherSysPwd4(String strOtherSysPwd4)
	{
		this.strOtherSysPwd4 = strOtherSysPwd4;
	}
	public String getStrOtherSysUser5()
	{
		return (strOtherSysUser5 == null) ? "" : strOtherSysUser5;
	}
	public void setStrOtherSysUser5(String strOtherSysUser5)
	{
		this.strOtherSysUser5 = strOtherSysUser5;
	}
	public String getStrOtherSysPwd5()
	{
		return (strOtherSysPwd5 == null) ? "" : strOtherSysPwd5;
	}
	public void setStrOtherSysPwd5(String strOtherSysPwd5)
	{
		this.strOtherSysPwd5 = strOtherSysPwd5;
	}
	public int getIntAllowUserID()
	{
		return  intAllowUserID;
	}
	public void setIntAllowUserID(int intAllowUserID)
	{
		this.intAllowUserID = intAllowUserID;
	}
	public String getStrAllowUserName()
	{
		return (strAllowUserName == null) ? "" : strAllowUserName;
	}
	public void setStrAllowUserName(String strAllowUserName)
	{
		this.strAllowUserName = strAllowUserName;
	}
	public int getIntParentUserID1()
	{
		return  intParentUserID1;
	}
	public void setIntParentUserID1(int intParentUserID1)
	{
		this.intParentUserID1 = intParentUserID1;
	}

	public int getIntParentUserID4()
	{
		return  intParentUserID4;
	}
	public void setIntParentUserID4(int intParentUserID4)
	{
		this.intParentUserID4 = intParentUserID4;
	}
	public String getStrParentUserName4()
	{
		return (strParentUserName4 == null) ? "" : strParentUserName4;
	}
	public void setStrParentUserName4(String strParentUserName4)
	{
		this.strParentUserName4 = strParentUserName4;
	}
	public int getIntParentUserID2()
	{
		return intParentUserID2;
	}
	public void setIntParentUserID2(int intParentUserID2)
	{
		this.intParentUserID2 = intParentUserID2;
	}
	public String getStrParentUserName2()
	{
		return (strParentUserName2 == null) ? "" : strParentUserName2;
	}
	public void setStrParentUserName2(String strParentUserName2)
	{
		this.strParentUserName2 = strParentUserName2;
	}
	public String getStrPersonName()
	{
		return (strPersonName == null) ? "" : strPersonName;
	}
	public void setStrPersonName(String strPersonName)
	{
		this.strPersonName = strPersonName;
	}
	public String getStrAllowStartDate()
	{
		return (strAllowStartDate == null) ? "" : strAllowStartDate;
	}
	public void setStrAllowStartDate(String strAllowStartDate)
	{
		this.strAllowStartDate = strAllowStartDate;
	}
	public String getStrAllowEndDate()
	{
		return (strAllowEndDate == null) ? "" : strAllowEndDate;
	}
	public void setStrAllowEndDate(String strAllowEndDate)
	{
		this.strAllowEndDate = strAllowEndDate;
	}
	public int getIntParentUserID3()
	{
		return intParentUserID3;
	}
	public void setIntParentUserID3(int intParentUserID3)
	{
		this.intParentUserID3 = intParentUserID3;
	}
	public String getStrParentUserName3()
	{
		return (strParentUserName3 == null) ? "" : strParentUserName3;
	}
	public void setStrParentUserName3(String strParentUserName3)
	{
		this.strParentUserName3 = strParentUserName3;
	}
	public String getStrParentUserName1()
	{
		return (strParentUserName1 == null) ? "" : strParentUserName1;
	}
	public void setStrParentUserName1(String strParentUserName1)
	{
		this.strParentUserName1 = strParentUserName1;
	}
	public int getIntParentUserID5()
	{
		return intParentUserID5;
	}
	public void setIntParentUserID5(int intParentUserID5)
	{
		this.intParentUserID5 = intParentUserID5;
	}
	public String getStrParentUserName5()
	{
		return (strParentUserName5 == null) ? "" : strParentUserName5;
	}
	public void setStrParentUserName5(String strParentUserName5)
	{
		this.strParentUserName5 = strParentUserName5;
	}

}
