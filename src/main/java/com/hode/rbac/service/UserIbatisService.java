/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.service;


import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.PrivilegeModel;
import com.hode.rbac.model.RoleModel;
import com.hode.rbac.model.UserLogModel;
import com.hode.rbac.model.UserModel;
import com.hode.rbac.model.UserPrivilegeModel;
import com.hode.rbac.model.UserRoleModel;
import com.hode.rbac.model.UserSessionModel;

public class UserIbatisService extends IbatisService
{
    
	private final static Log log = LogFactory.getLog(UserIbatisService.class);
	
	/**
	 * 通过id取出UserModel对象
	 * 此方法不同于Object getObjectInfo(Object obj)，后者是直接从数据库中找出数据，而本method则是从缓冲中找出数据
	 */
	public Object getObjectInfo(int id)
	{

        UserModel userModel=new UserModel();
        userModel.setIntID(id);
	    return (UserModel)getObjectInfo("getObjectInfo", userModel);
	}
	public UserModel getObjectInfo(int intUserID,String strAccount)
	{
        UserModel userModel=new UserModel();
        userModel.setIntID(intUserID);
        userModel.setStrAccount(strAccount);
	    return (UserModel)getObjectInfo("getObjectInfo", userModel);
	}
	/**
	 * 覆写deleteObject(Object obj)
	 * 删除用户时同时删除相关数据
	 */
	public int deleteObject(Object obj)
	{
//	    UserModel user = (UserModel)obj;
	    //删除用户权限关系
	    deleteObject("deleteUserPrivilegeByUserID", obj);
	    //删除用户角色关系
	    deleteObject("deleteUserRoleByUserID", obj);
	    //删除用户
	    return super.deleteObject(obj);
	}
	
	/**
	 * 设置用户角色
	 * 先将当前用户的角色全部删除，再增加新的用户角色
	 */
	public void insertUserRole(UserModel user,int[] rids)
	{
	    deleteObject("deleteUserRoleByUserID", String.valueOf(user.getIntID()));
	    if(rids != null)
	    {
	        UserRoleModel urm = new UserRoleModel();
	        urm.setUser(user);
	        RoleModel r = new RoleModel();
	        for (int i = 0; i < rids.length; i++)
            {
	            r.setIntID(rids[i]);
	            urm.setRole(r);
	            insertObject("insertUserRole",urm);
            }
	    }
	}
	
	/**
	 * 设置用户权限
	 * 先将当前用户的权限全部删除，再增加新的用户权限
	 */
	public void insertUserPrivilege(UserModel user,int[] pids)
	{
	    deleteObject("deleteUserPrivilegeByUserID", String.valueOf(user.getIntID()));
	    if(pids != null)
	    {
	        UserPrivilegeModel upm = new UserPrivilegeModel();
	        upm.setUser(user);
	        PrivilegeModel p = new PrivilegeModel();
	        for (int i = 0; i < pids.length; i++)
            {
	            p.setIntID(pids[i]);
	            upm.setPrivilege(p);
	            insertObject("insertUserPrivilege",upm);
            }
	    }
	}
	
	/**
	 *根据用户ID获取用户的角色IDList
	 */
	public List getRoleIDListByUserID(int uid)
	{
	    List list = getObjectList("getRoleIDListByUserID", new Integer(uid));
	    return list;
	}
	/**
	 *根据用户ID获取用户的角色的权限IDList
	 */
	public List getRolePrivilegeIDListByUserID(int uid)
	{
	    return getObjectList("getRolePrivilegeIDListByUserID", new Integer(uid));
	}
	
	/**
	 *根据用户ID获取用户的自定义的权限IDList
	 */
	public List getDefinePrivilegeIDListByUserID(int uid)
	{
	    return getObjectList("getDefinePrivilegeIDListByUserID", new Integer(uid));
	}
	
	public List getUserListByCondition(Map userMap)
	{
	    return getObjectList("getUserListByCondition", userMap);
	}
	
    /////////////////////////////////插入用户登陆日志
    public void insertUserLog(String strType,UserSessionModel new_userSession)
    {
        if(new_userSession!=null && new_userSession.getIntUserID() > 0 && new_userSession.getIntUserID() != 999999999)
        {
            UserLogModel userLogModel = new UserLogModel();
            userLogModel.setIntUserID(new_userSession.getIntUserID());
            userLogModel.setStrAccount(new_userSession.getStrUserAccount());
            userLogModel.setStrName(new_userSession.getStrName());
            userLogModel.setStrCreateDate(DateUtil.getNowDateByFormat(""));
            userLogModel.setStrType(strType);
            userLogModel.setStrUserIP(new_userSession.getStrUserIP());
            this.insertObject("insertUserLog", userLogModel);
        }
    }
    /////////////////////////////////插入用户登陆日志
    
   //授权用户
	public UserModel getAllowUserInfo(String strUserAccount){
		return (UserModel)getObjectInfo("getAllowUserInfo",strUserAccount);
	}
	public void updateAllowUser(UserModel userModel){
		updateObject("updateAllowUser",userModel);
	}
}