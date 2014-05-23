/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.service.GlobeSpringContext;
import com.hode.rbac.model.GroupModel;
import com.hode.rbac.model.PrivilegeModel;
import com.hode.rbac.model.UserModel;
import com.hode.rbac.service.GroupIbatisService;
import com.hode.rbac.service.PrivilegeIbatisService;
import com.hode.rbac.service.RoleIbatisService;
import com.hode.rbac.service.UserIbatisService;

public class RbacUtil {
	private final static Log log = LogFactory.getLog(RbacUtil.class);

	private static GroupIbatisService groupService = (GroupIbatisService) (GlobeSpringContext.getApplicationContext().getBean("rbac_groupIbatisService"));

	private static PrivilegeIbatisService privilegeService = (PrivilegeIbatisService) (GlobeSpringContext.getApplicationContext().getBean("rbac_privilegeIbatisService"));

	private static UserIbatisService userService = (UserIbatisService) (GlobeSpringContext.getApplicationContext().getBean("rbac_userIbatisService"));
	
	private static RoleIbatisService roleService = (RoleIbatisService) (GlobeSpringContext.getApplicationContext().getBean("rbac_roleIbatisServiceProxy"));

	public static boolean initCloseFlag = false;	//是否关闭 
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////部门信息
		/**
	 * 根据部门、单位的id获取部门或单位详细信息
	 */
	public static Object getGroupModelById(int intID) 
	{
		GroupModel groupModel = new GroupModel();
		groupModel.setIntID(intID);
		return groupService.getObjectInfo(groupModel);
	}

	/**
	 * 根据部门、单位的id（用，连接起来）获取部门、单位名称列表
	 */
	public static String getGroupNameByIds(String strGroupID) 
	{
		return groupService.getGroupNameByIds(strGroupID);
	}

	/**
	 * 根据部门id获取单位id
	 */
	public static int getIntUnitIDByGroupID(int intGroupID) {
		return groupService.getIntUnitIDByGroupID(intGroupID);
	}
    

    /**
     * 根据部门id获取单位Model
     */
    public static GroupModel getUnitModelByGroupID(int intGroupID) {
        return groupService.getUnitModelByGroupID(intGroupID);
    }
    

	/**
	 * 根据部门、单位的id（用，连接起来）获取部门、单位名称
	 */
	public static List getGroupListByIds(String strGroupID) {
		return groupService.getGroupListByIds(strGroupID);
	}
	
	/*
	 * 获取所以子部门
	 * **/
	public static List getAllObjectList()
	{
	    List list = groupService.getObjectList("getAllObjectList", null);
	    return list;
	}

	/**
	 * 根据部门id找所有的子部门 intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public static String getAllGroupIdsByFatherId(int intGroupID, int intType) {
		return groupService.getAllGroupIdsByFatherId(intGroupID, intType);
	}
	

    public static List getChildObjectListByFather(int intGroupID)
    {
        GroupModel groupModel = new GroupModel();
        groupModel.setIntFatherID(intGroupID);
        return groupService.getObjectList("getChildObjectList", groupModel);
    }
	/**
	 * 根据部门id找所有的父所有的ID，用，连接起来。
	 *  intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public static String getAllFatherIdsById(int intGroupID, int intType) {
		return groupService.getAllFatherIDsByID(intGroupID, intType);
	}	

	/**
	 * 根据部门id找所有的子部门 intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public static List getAllGroupListByFatherId(int intGroupID, int intType) {
		return groupService.getAllGroupListByFatherId(intGroupID, intType);
	}
   
	/**
	 * 根据部门id找兄弟id intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public static List getBrotherListById(int intGroupID, int intType) {
		return groupService.getBrotherGroupListById(intGroupID, intType);
	}

	/**
	 * 根据部门id找儿子 intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public static List getChildGroupListByFatherId(int intGroupID, int intType) {
		return groupService.getChildGroupListByFatherId(intGroupID, intType);
	}

	/**
	 * 根据部门id找儿子 intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public static String getChildGroupIdsByFatherId(int intGroupID, int intType) {
		return groupService.getChildGroupIdsByFatherId(intGroupID, intType);
	}

	/**
	 * 根据权限id获取权限详细数据
	 */
	public static PrivilegeModel getPrivailegeModelById(int intID) {
		PrivilegeModel privilegeModel = new PrivilegeModel();
		log.info("getPrivailegeModelById starting========" + intID);
		privilegeModel.setIntID(intID);
		return (PrivilegeModel) privilegeService.getObjectInfo(privilegeModel);
	}
	
	/**
	 * 获取所有的用户人员
	 * @return
	 */
	public static List getAllUserList()
	{
		return userService.getUserListByCondition(null);
	}
	public static List getAllRoleList()
	{
		return roleService.getObjectList("");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////用户信息
	/**
	 * 根据部门id；角色id；权限id获取的人员资料 strGroupID : 部门ｉｄ用，连接起来 strRoleID ：
	 * 角色ｉｄUserModel用，连接起来 strPrivilegeID ：权限ｉｄ,用，连接起来 strUserID : 用户的id
	 * strUserAccount : 用户帐号列表
	 */
	public static List getUserInfoListByCondition(String strGroupID,String strRoleID, String strPrivilegeID, String strUserID,String strUserAccount) {
        boolean rtnFlag = false;
		log.info("getUserInfoListByCondition starting========");
		Map userMap = new HashMap();
		if (strGroupID != null && !strGroupID.trim().equals(""))
        {
			userMap.put("strGroupID", StringUtil.getIntSortByString("",strGroupID));
            rtnFlag = true;
        }

		if (strRoleID != null && !strRoleID.trim().equals(""))
		{
            userMap.put("strRoleID", StringUtil.getIntSortByString("",strRoleID));
            rtnFlag = true;
        }

		if (strPrivilegeID != null && !strPrivilegeID.trim().equals(""))
		{
            userMap.put("strPrivilegeID", StringUtil.getIntSortByString("",strPrivilegeID));
            rtnFlag = true;
        }
					

		if (strUserID != null && !strUserID.trim().equals(""))
		{
            userMap.put("strUserID", StringUtil.getIntSortByString("",strUserID));
            rtnFlag = true;
        }

		if (strUserAccount != null && !strUserAccount.trim().equals(""))
		{
		    userMap.put("strUserAccount", StringUtil.getStringSortByString(strUserAccount));
            rtnFlag = true;
        }
        if(rtnFlag)
        {
            return userService.getUserListByCondition(userMap);
        }
        else
        {
            return null;
        }
	}

	/**
	 * 根据用户id或是帐号找此人的详细信息
	 */
	public static UserModel getUserModelObjectInfo(int intUserID, String strAccount) {
		return userService.getObjectInfo(intUserID,strAccount);
	}

	public static String getStrAllChild(String strGroupIDs)
	{
		return groupService.getstrAllChild(strGroupIDs);
	}
}