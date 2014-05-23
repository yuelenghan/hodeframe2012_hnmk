/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import java.util.List;

import com.hode.framework.model.IbatisModel;


public class UserSessionModel extends IbatisModel
{ 
    private int intUserID;	//用户ID
    private String strUserAccount;  //用户帐号
    private String strUserPassword; //用户密码，经过MD5加密
    public String strName;         //用户姓名
    private String strTel;          //单位电话
    private String strUserUnitName; //单位名称
    private int[] intRoleIDs;       //用户角色IDs
    private String[] strRoleNames;  //用户角色名称
    private int intGroupID;         //用户所在用户组ID
    private int intUnitID;	        //用户所在的单位id
    private String strMachineCode;  //机器码

    private int intUnitOrderID; //用户所在用户单位名称排序号 
    private int intGroupOrderID; //用户所在用户部门名称排序号  
    private String strGroupName;    //用户所在用户组名称
    private String strUnitName;     //用户所在用户单位名称
    private int[] intPrivilegeIDs;  //用户权限IDs
    private int intLineNum;         //每页显示行数
    private int intUserType;        //用户类型 1-系统管理员 2-一般操作员
    private String strLastLoginTime;//上次登录时间
    private String strUserIP;           //登陆IP
    private int intSpaceSize;
    private List onLineUserList;
    private int intPersonID;
    private String strAllowUserID; //允许访问ID,指授权
    
    
    private int intUserGroupType;
    private int intPersonUnitID;
    private int intPersonGroupID;
    
	public int getIntUserID()
	{
		return intUserID;
	}
	public void setIntUserID(int intUserID)
	{
		this.intUserID = intUserID;
	}
	public String getStrUserAccount()
	{
		return (strUserAccount == null) ? "" : strUserAccount;
	}
	public void setStrUserAccount(String strUserAccount)
	{
		this.strUserAccount = strUserAccount;
	}
	public String getStrUserPassword()
	{
		return (strUserPassword == null) ? "" : strUserPassword;
	}
	public void setStrUserPassword(String strUserPassword)
	{
		this.strUserPassword = strUserPassword;
	}
	public String getStrName()
	{
		return (strName == null) ? "" : strName;
	}
	public void setStrName(String strName)
	{
		this.strName = strName;
	}
	public String getStrTel()
	{
		return (strTel == null) ? "" : strTel;
	}
	public void setStrTel(String strTel)
	{
		this.strTel = strTel;
	}
	public String getStrUserUnitName()
	{
		return (strUserUnitName == null) ? "" : strUserUnitName;
	}
	public void setStrUserUnitName(String strUserUnitName)
	{
		this.strUserUnitName = strUserUnitName;
	}
	public int[] getIntRoleIDs()
	{
		return intRoleIDs;
	}
	public void setIntRoleIDs(int[] intRoleIDs)
	{
		this.intRoleIDs = intRoleIDs;
	}
	public String[] getStrRoleNames()
	{
		return strRoleNames;
	}
	public void setStrRoleNames(String[] strRoleNames)
	{
		this.strRoleNames = strRoleNames;
	}
	public int getIntGroupID()
	{
		return intGroupID;
	}
	public void setIntGroupID(int intGroupID)
	{
		this.intGroupID = intGroupID;
	}
	public int getIntUnitID()
	{
		return intUnitID;
	}
	public void setIntUnitID(int intUnitID)
	{
		this.intUnitID = intUnitID;
	}
	public String getStrMachineCode()
	{
		return (strMachineCode == null) ? "" : strMachineCode;
	}
	public void setStrMachineCode(String strMachineCode)
	{
		this.strMachineCode = strMachineCode;
	}
	public int getIntUnitOrderID()
	{
		return intUnitOrderID;
	}
	public void setIntUnitOrderID(int intUnitOrderID)
	{
		this.intUnitOrderID = intUnitOrderID;
	}
	public int getIntGroupOrderID()
	{
		return intGroupOrderID;
	}
	public void setIntGroupOrderID(int intGroupOrderID)
	{
		this.intGroupOrderID = intGroupOrderID;
	}
	public String getStrGroupName()
	{
		return (strGroupName == null) ? "" : strGroupName;
	}
	public void setStrGroupName(String strGroupName)
	{
		this.strGroupName = strGroupName;
	}
	public String getStrUnitName()
	{
		return (strUnitName == null) ? "" : strUnitName;
	}
	public void setStrUnitName(String strUnitName)
	{
		this.strUnitName = strUnitName;
	}
	public int[] getIntPrivilegeIDs()
	{
		return intPrivilegeIDs;
	}
	public void setIntPrivilegeIDs(int[] intPrivilegeIDs)
	{
		this.intPrivilegeIDs = intPrivilegeIDs;
	}
	public int getIntLineNum()
	{
		return intLineNum;
	}
	public void setIntLineNum(int intLineNum)
	{
		this.intLineNum = intLineNum;
	}
	public int getIntUserType()
	{
		return intUserType;
	}
	public void setIntUserType(int intUserType)
	{
		this.intUserType = intUserType;
	}
	public String getStrLastLoginTime()
	{
		return (strLastLoginTime == null) ? "" : strLastLoginTime;
	}
	public void setStrLastLoginTime(String strLastLoginTime)
	{
		this.strLastLoginTime = strLastLoginTime;
	}
	public String getStrUserIP()
	{
		return (strUserIP == null) ? "" : strUserIP;
	}
	public void setStrUserIP(String strUserIP)
	{
		this.strUserIP = strUserIP;
	}
	public int getIntSpaceSize()
	{
		return intSpaceSize;
	}
	public void setIntSpaceSize(int intSpaceSize)
	{
		this.intSpaceSize = intSpaceSize;
	}
	public List getOnLineUserList()
	{
		return onLineUserList;
	}
	public void setOnLineUserList(List onLineUserList)
	{
		this.onLineUserList = onLineUserList;
	}
	public int getIntPersonID()
	{
		return intPersonID;
	}
	public void setIntPersonID(int intPersonID)
	{
		this.intPersonID = intPersonID;
	}
	public int getIntUserGroupType()
	{
		return 1;
	}
	public void setIntUserGroupType(int intUserGroupType)
	{
		this.intUserGroupType = intUserGroupType;
	}
	public int getIntPersonUnitID()
	{
		return intPersonUnitID;
	}
	public void setIntPersonUnitID(int intPersonUnitID)
	{
		this.intPersonUnitID = intPersonUnitID;
	}
	public int getIntPersonGroupID()
	{
		return intPersonGroupID;
	}
	public void setIntPersonGroupID(int intPersonGroupID)
	{
		this.intPersonGroupID = intPersonGroupID;
	}
	public String getStrAllowUserID()
	{
		return (strAllowUserID == null) ? "" : strAllowUserID;
	}
	public void setStrAllowUserID(String strAllowUserID)
	{
		this.strAllowUserID = strAllowUserID;
	}

    

    
}
