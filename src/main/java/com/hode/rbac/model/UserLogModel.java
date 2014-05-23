/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.IbatisModel;

public class UserLogModel extends IbatisModel
{
    
    private int intUserID;          //用户ID
    private String strAccount;         //账号
    private String strName;         //姓名
    private String strCreateDate;   //时间
    private String strType;     //说明
    private String strUserIP;       //来源IP
	public int getIntUserID()
	{
		return intUserID;
	}
	public void setIntUserID(int intUserID)
	{
		this.intUserID = intUserID;
	}
	public String getStrAccount()
	{
		return (strAccount == null) ? "" : strAccount;
	}
	public void setStrAccount(String strAccount)
	{
		this.strAccount = strAccount;
	}
	public String getStrName()
	{
		return (strName == null) ? "" : strName;
	}
	public void setStrName(String strName)
	{
		this.strName = strName;
	}
	public String getStrCreateDate()
	{
		return (strCreateDate == null) ? "" : strCreateDate;
	}
	public void setStrCreateDate(String strCreateDate)
	{
		this.strCreateDate = strCreateDate;
	}
	public String getStrType()
	{
		return (strType == null) ? "" : strType;
	}
	public void setStrType(String strType)
	{
		this.strType = strType;
	}
	public String getStrUserIP()
	{
		return (strUserIP == null) ? "" : strUserIP;
	}
	public void setStrUserIP(String strUserIP)
	{
		this.strUserIP = strUserIP;
	}


    

    
}
