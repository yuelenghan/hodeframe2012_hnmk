/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.IbatisModel;

public class RoleModel extends IbatisModel 
{

    private String strName;
    private String strDescribe;
    private int intGroupID;
	public String getStrName()
	{
		return (strName == null) ? "" : strName;
	}
	public void setStrName(String strName)
	{
		this.strName = strName;
	}
	public String getStrDescribe()
	{
		return (strDescribe == null) ? "" : strDescribe;
	}
	public void setStrDescribe(String strDescribe)
	{
		this.strDescribe = strDescribe;
	}
	public int getIntGroupID()
	{
		return intGroupID;
	}
	public void setIntGroupID(int intGroupID)
	{
		this.intGroupID = intGroupID;
	}


    
    
}
