/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.PrivilegeModel;
import com.hode.rbac.model.RoleModel;
import com.hode.rbac.model.RolePrivilegeModel;


public class RoleIbatisService extends IbatisService 
{
    
	private final static Log log = LogFactory.getLog(RoleIbatisService.class);
	
	/**
	 * 通过id取出RoleModel对象
	 * 此方法不同于Object getObjectInfo(Object obj)，后者是直接从数据库中找出数据，而本method则是从缓冲中找出数据
	 */
	public Object getObjectInfo(int id)
	{
	    List list = getObjectList(null);
	    Object obj = null;
	    if(list != null)
	    {     
	        for (int i = 0; i <list.size(); i++)
            {
	            obj = list.get(i);
                if(((RoleModel)obj).getIntID() == id)
                {
                    break;
                }
                else
                {
                    obj = null;
                }
            }
	    }
	    return obj;
	}
	
	/**
	 * 覆写deleteObject(Object obj)
	 * 删除角色时同时删除相关数据
	 */
	public int deleteObject(Object obj)
	{
	    int id = StringUtil.ObjectToInt(obj);
	    //删除角色权限关系
	    deleteObject("deleteRolePrivilegeByRoleID", String.valueOf(id));
	    //删除角色用户关系
	    deleteObject("deleteUserRoleByRoleID", String.valueOf(id));
	    //删除角色
	    return super.deleteObject(String.valueOf(id));
	}
	
	
	/**
	 * 设置角色权限
	 * 先将当前角色的权限全部删除，再增加新的角色权限
	 */
	public void insertRolePrivilege(RoleModel role ,int[] pids)
	{
	    deleteObject("deleteRolePrivilegeByRoleID", String.valueOf(role.getIntID()));
	    if(pids != null)
	    {
	        RolePrivilegeModel rpm = new RolePrivilegeModel();
	        rpm.setRole(role);
	        PrivilegeModel p = new PrivilegeModel();
	        for (int i = 0; i < pids.length; i++)
            {
	            p.setIntID(pids[i]);
	            rpm.setPrivilege(p);
	            insertObject("insertRolePrivilege",rpm);
            }
	    }
	}
	
	/**
	 * 获取某角色的所有权限
	 */
	public List getPrivilegeByRoleID(int id)
	{
	    List list = null;
	    list = getObjectList("getPrivilegeByRoleID", new Integer(id));
	    return list;
	}
	
	/**
	 * 获取某些角色的所有权限
	 */
	public List getPrivilegeByRoleIDList(List ridList)
	{
	    List list = null;
	    String rids = StringUtil.join(",", ridList);
	    if(rids != null)
	        list = getObjectList("getPrivilegeByRoleIDs", rids);
	    return list;
	}
	
}