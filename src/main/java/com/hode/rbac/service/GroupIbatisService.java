/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
 package com.hode.rbac.service;


import java.util.ArrayList;
import java.util.List;

import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.commons.util.TreeUtil;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.GroupModel;


public class GroupIbatisService extends IbatisService
{
	/**
	 * 通过id取出GroupModel对象
	 * 此方法不同于Object getObjectInfo(Object obj)，后者是直接从数据库中找出数据，而本method则是从缓冲中找出数据
	 */
	public Object getObjectInfo(int id)
	{
	    List groupList = getObjectList(null);
	    Object obj = null;
	    if(groupList != null)
	    {     
	        for (int i = 0; i <groupList.size(); i++)
            {
	            obj = groupList.get(i);
                if(((GroupModel)obj).getIntID() == id)
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
	 * 根据部门ID，获取当前组的子组
	 * intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public List getChildGroupListByFatherId(int id,int intType)
	{ 
	    List grouplist = getObjectList(null);
	    List rtnList = new ArrayList();
	    if(grouplist != null)
	    {     
	        GroupModel groupModel = null;
	        for (int i = 0; i <grouplist.size(); i++)
            {
	            groupModel = (GroupModel)grouplist.get(i);
	            if(groupModel.getIntFatherID() == id)
                {
	                if(intType == 0)
	                {
	                    rtnList.add(groupModel);
	                }
	                else if(groupModel.getIntGroupType() == intType)
	                {
	                    rtnList.add(groupModel);
	                }
                }
            }
	    }	    
	    return rtnList;
	}
	/**
	 * 根据部门ID，获取当前组的子组，将这些组的id用逗号连接成字串
	 * intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public  String getChildGroupIdsByFatherId(int id,int intType)
	{ 
	    List rtnList=getChildGroupListByFatherId(id,intType);
	    return TreeUtil.NodeListToIds(rtnList);
	}
	
	
	/**
	 * 获取当前组的兄弟
	 * intType ：0表示所有，=1 表示单位，=2 表示部门
	 */    
    public  List getBrotherGroupListById(int id, int intType)
    {
	    GroupModel groupModel=(GroupModel)getObjectInfo(id);	
	    return getChildGroupListByFatherId(groupModel.getIntFatherID(),intType);
    }
	
    /**
	 * 递归求得所有符合条件((intID=id)&&(inFatherID=id))的组
	 * intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public List getAllGroupListByFatherId(int id,int intType)
	{ 
	    List groupList = getObjectList(null);
	    List rtnList = new ArrayList();
	    getAllNodeListByFatherId(id,id,intType, groupList, rtnList);
	    return rtnList;
	}
	
	
	/**
	 * 找出id找所有的父id,用,连接起来.
	 * intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public String getAllFatherIDsByID(int intID,int intType)
	{
	    StringBuffer strBuffer = new StringBuffer();
	    strBuffer.append(intID);
	    List groupList = getObjectList(null);
	    getAllFatherIdsById(intID,intType,groupList,strBuffer);
	    return strBuffer.toString();
	}
	
	
	
	/**
	 * 递归求得所有符合条件((intID=id)&&(inFatherID=id))的组，将这些组的id用逗号连接成字串
	 * intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public String getAllGroupIdsByFatherId(int id,int intType)
	{ 
	    List rtnList=getAllGroupListByFatherId(id,intType);
        if(rtnList !=null  && rtnList.size() > 0)
        {
            return TreeUtil.NodeListToIds(rtnList);
        }
        else
        {
            return "-1";
        }
	}
	
	/**
	 * 根据组的id 找出 所有组的名称。
	 */
	public List getGroupListByIds(String ids)
	{ 
	   return getObjectList("getGroupListByIds",StringUtil.getIntSortByString("",ids));
	}

	/**
	 * 根据组的id 找出 所有组的名称。，将这些组的名称用逗号连接成字串
	 */
	public String getGroupNameByIds(String ids)
	{ 
	    List list = getGroupListByIds(ids);
	    return TreeUtil.NodeListToNames(list);
	}
	
	/**
	 * 根据部门id找单位id
	 */
	public int getIntUnitIDByGroupID(int intGroupID)
	{
	    List list = getObjectList(null);
	    boolean isFound=false;
	    int intRtn = 0;
	    GroupModel groupModel=new GroupModel();
	    if(list != null)
	    {
		    while(!isFound)
		    {
		        for (int i = 0; i <list.size(); i++)
	            {
		            groupModel = (GroupModel)list.get(i);
	                if(groupModel.getIntID() == intGroupID)
	                {
	                    intRtn = intGroupID;
	                    intGroupID = groupModel.getIntFatherID();
	                    if((groupModel.getIntGroupType() !=2) || ( intGroupID == 0))
	                    {
	                        isFound=true;
	                        break;
	                    }
	                    
	                }
	            }
		    }
	    }
	    return intRtn;
	}
	

    /**
     * 根据部门id找单位对象
     */
    public GroupModel getUnitModelByGroupID(int intGroupID)
    {
        List list = getObjectList(null);
        boolean isFound=false;
        GroupModel rtnGroupModel =null;
        GroupModel groupModel=new GroupModel();
        if(list != null)
        {
            while(!isFound)
            {
                for (int i = 0; i <list.size(); i++)
                {
                    groupModel = (GroupModel)list.get(i);
                    if(groupModel.getIntID() == intGroupID)
                    {
                        rtnGroupModel = groupModel;
                        intGroupID = groupModel.getIntFatherID();
                        if((groupModel.getIntGroupType() !=2) || ( intGroupID == 0))
                        {
                            isFound=true;
                            break;
                        }
                        
                    }
                }
            }
        }
        return rtnGroupModel;
    }
	/**
	 * 找出某些用户组下的所有用户ID
	 */
	public List getUserIDListByGroupID(String gids)
	{
	    List list = getObjectList("getUserIDListByGroupIDs", gids);
	    return list;
	}
	
	/**
	 * 找出某些用户组下的所有角色ID
	 */
	public List getRoleIDListByGroupID(String gids)
	{
	    List list = getObjectList("getRoleIDListByGroupIDs", gids);
	    return list;
	}
		
	/**
	 * 覆写deleteObject(Object obj)
	 * 删除用户组，同时删除其他所有子用户组，以及同时删除其他相关信息
	 */
	public int deleteObject(Object obj)
	{
	    int rtn = 0;
	    int id = StringUtil.ObjectToInt(obj);
	    String gids = getAllGroupIdsByFatherId(id,0);
	    if(gids != null)
	    {
		    String uids = StringUtil.join(",", getUserIDListByGroupID(gids));
		    if((uids != null)&&(!uids.equals("")))
		    {
			    //删除相关用户的相关角色关系
			    deleteObject("deleteUserRoleByUserIDs", uids);
			    //删除相关用户的相关权限
			    deleteObject("deleteUserPrivilegeByUserIDs", uids);
			    //删除相关用户
			    deleteObject("deleteUserByUserIDs", uids);
		    }
		    
		    String rids = StringUtil.join(",", getRoleIDListByGroupID(gids));
		    if((rids != null)&&(!rids.equals("")))
		    {
			    //删除相关角色的相关权限
			    deleteObject("deleteRolePrivilegeByRoleIDs", rids);
			    //删除相关角色的相关用户角色关系
			    deleteObject("deleteUserRoleByRoleIDs", rids);
			    //删除相关角色
			    deleteObject("deleteRoleByRoleIDs", rids);
		    }
		    
		    //删除相关用户组
		    rtn = deleteObject("deleteGroup", gids);
	    }
	    return rtn;
	}


    /**
	 * 递归求得所有父id。用，连接起来
	 *  intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public void getAllFatherIdsById(int id,int intType,List objList,StringBuffer strBuffer)
	{    
	    if(objList != null)
	    {     
	        GroupModel groupModel = null;
	        for (int i = 0; i <objList.size(); i++)
            {
	            groupModel = (GroupModel)objList.get(i);
	            if(groupModel.getIntID() == id)
                {
	                if(intType == 0)
	                {
	                    strBuffer.append(","+groupModel.getIntFatherID());
	                }
	                else if(groupModel.getIntGroupType() == intType)
	                {
	                    strBuffer.append(","+groupModel.getIntFatherID());
	                }
	                if(groupModel.getIntFatherID() >0)
	                {
	                    getAllFatherIdsById(groupModel.getIntFatherID(),intType,objList,strBuffer);
	                }	                
                }
            }

           
	    }
	}
	

    /**
	 * 递归求得所有符合条件((intID=id)&&(inFatherID=id))的对象
	 *  intType ：0表示所有，=1 表示单位，=2 表示部门
	 */
	public void getAllNodeListByFatherId(int intOldID,int id,int intType,List objList,List rtnList)
	{    
	    if(objList != null)
	    {     
	        GroupModel groupModel = null;
	        for (int i = 0; i <objList.size(); i++)
            {
	            groupModel = (GroupModel)objList.get(i);
	            if(groupModel.getIntID() == id)
                {
	                if(intType == 0)
	                {
	                    rtnList.add(groupModel);
	                }
	                else if(groupModel.getIntID()== intOldID || groupModel.getIntGroupType() == intType)
	                {
	                    rtnList.add(groupModel);
	                }
                }
                if(groupModel.getIntFatherID() == id)
                {
                    getAllNodeListByFatherId(intOldID,groupModel.getIntID(),intType,objList,rtnList);
                }
            }
	    }
	}

       /**
        * 根据部门IDs，查找所有的子IDS
        */
       public String  getstrAllChild(String StrGroupIDs){
           String strAllChild="-1";
           int GroupFatherID;
           if(StrGroupIDs!=null)
           {
               StrGroupIDs = StringUtil.getIntSortByString("",StrGroupIDs);
               String[] GroupID=StrGroupIDs.split(",");
               for(int i=0;i<GroupID.length;i++)
               {
                   GroupFatherID=StringUtil.ObjectToInt(GroupID[i]);
                   if(GroupFatherID > 0)
                   {
                       strAllChild=strAllChild+","+getAllGroupIdsByFatherId(GroupFatherID,0);
                   }
               }
           }

           return strAllChild;
       }
}
