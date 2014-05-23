/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.ITreeNodeModel;
import com.hode.framework.model.IbatisModel;

public class PrivilegeModel extends IbatisModel implements ITreeNodeModel
{
    
    private int intFatherID;
    private String strName;
    private int intLevel;
    private int intPrivilegeType;
    private int intPrivilegeLevel;
    private String strDescribe;
    
	public int getIntFatherID()
	{
		return intFatherID;
	}
	public void setIntFatherID(int intFatherID)
	{
		this.intFatherID = intFatherID;
	}
	public String getStrName()
	{
		return (strName == null) ? "" : strName;
	}
	public void setStrName(String strName)
	{
		this.strName = strName;
	}
	public int getIntLevel()
	{
		return intLevel;
	}
	public void setIntLevel(int intLevel)
	{
		this.intLevel = intLevel;
	}
	public int getIntPrivilegeType()
	{
		return intPrivilegeType;
	}
	public void setIntPrivilegeType(int intPrivilegeType)
	{
		this.intPrivilegeType = intPrivilegeType;
	}
	public int getIntPrivilegeLevel()
	{
		return intPrivilegeLevel;
	}
	public void setIntPrivilegeLevel(int intPrivilegeLevel)
	{
		this.intPrivilegeLevel = intPrivilegeLevel;
	}
	public String getStrDescribe()
	{
		return (strDescribe == null) ? "" : strDescribe;
	}
	public void setStrDescribe(String strDescribe)
	{
		this.strDescribe = strDescribe;
	}
    
    
}
