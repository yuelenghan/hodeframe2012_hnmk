/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.commons.util.TreeUtil;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.PrivilegeModel;


public class PrivilegeIbatisService extends IbatisService {
	private final static Log log = LogFactory.getLog(PrivilegeIbatisService.class);
	
	/**
	 * 通过id取出PrivilegeModel对象
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
                if(((PrivilegeModel)obj).getIntID() == id)
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
	 * 递归求得所有符合条件((intID=id)&&(inFatherID=id))的组
	 */
	public List getAllPrivilegeListByFatherId(int id)
	{ 
	    List list = getObjectList(null);
	    List rtn = new ArrayList();
	    TreeUtil.getAllNodeListByFatherId(id,list,rtn);
	    return rtn;
	}
	
	/**
	 * 递归求得所有符合条件((intID=id)&&(inFatherID=id))的组，将这些组的id用逗号连接成字串
	 */
	public String getAllPrivilegeIdsByFatherId(int id)
	{ 
	    List list = getObjectList(null);
	    List rtnList = new ArrayList();
	    TreeUtil.getAllNodeListByFatherId(id,list,rtnList);
	    return TreeUtil.NodeListToIds(rtnList);
	}
	
	/**
	 * 覆写deleteObject(Object obj)
	 * 删除权限，同时删除其他所有子权限，以及同时删除其他相关信息
	 */
	public int deleteObject(Object obj)
	{
	    int rtn = 0;
	    int id = StringUtil.ObjectToInt(obj);
	    String pids = getAllPrivilegeIdsByFatherId(id);
	    if((pids != null)&&(!pids.equals("")))
	    {
		    //删除相关的角色权限关系
		    deleteObject("deleteRolePrivilegeByPids", pids);
		    //删除相关的用户权限关系
		    deleteObject("deleteUserPrivilegeByPids", pids);
		    //删除权限
		    rtn = deleteObject("deletePrivilege", pids);
	    }
	    return rtn;
	}
	
}