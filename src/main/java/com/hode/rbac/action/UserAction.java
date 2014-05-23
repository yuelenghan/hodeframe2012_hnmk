/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.HardDiskInfo;
import com.hode.framework.commons.util.MD5Util;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.model.JsSelectModel;
import com.hode.rbac.model.UserLogModel;
import com.hode.rbac.model.UserModel;
import com.hode.rbac.model.UserSessionModel;
import com.hode.rbac.service.GroupIbatisService;
import com.hode.rbac.service.PrivilegeIbatisService;
import com.hode.rbac.service.RoleIbatisService;
import com.hode.rbac.service.UserIbatisService;
import com.hode.rbac.service.UserSessionCache;
import com.hode.rbac.service.UserSessionService;

public class UserAction extends AbstractBaseAction
{

    private final static Log log = LogFactory.getLog(UserAction.class);    
    private int[] intRoleID;
    private int[] intRolePrivilegeID;
    private int[] intDefinePrivilegeID;
    private List groupList;
    private List roleList;
    private List privilegeList;    
    private RoleIbatisService roleService;
    private GroupIbatisService groupService;
    private PrivilegeIbatisService privilegeService;
    private JsSelectModel jsSelectModel= new JsSelectModel();
    private int intGroupType;
    private int intGroupID;    
    public int intOnLineCount = 0;
    public List onLineUsers = null;

    private UserSessionCache cache = UserSessionCache.getInstance();
    private UserSessionService userService = UserSessionService.getInstance();
    public JsSelectModel getJsSelectModel()
    {
        return jsSelectModel;
    }
    public void setJsSelectModel(JsSelectModel jsSelectModel)
    {
        this.jsSelectModel = jsSelectModel;
    }
        
    
    public int getIntOnLineCount()
    {
        return intOnLineCount;
    }
    public void setIntOnLineCount(int intOnLineCount)
    {
        this.intOnLineCount = intOnLineCount;
    }
    public List getOnLineUsers()
    {
        return onLineUsers;
    }
    public void setOnLineUsers(List onLineUsers)
    {
        this.onLineUsers = onLineUsers;
    }
    public int getIntGroupID()
    {
        return intGroupID;
    }
    public void setIntGroupID(int intGroupID)
    {
        this.intGroupID = intGroupID;
    }
    public int getIntGroupType()
    {
        return intGroupType;
    }
    public void setIntGroupType(int intGroupType)
    {
        this.intGroupType = intGroupType;
    }
    public List getGroupList()
    {
        return groupList;
    }
    public void setGroupList(List groupList)
    {
        this.groupList = groupList;
    }
    public int[] getIntDefinePrivilegeID()
    {
        return intDefinePrivilegeID;
    }
    public void setIntDefinePrivilegeID(int[] intDefinePrivilegeID)
    {
        this.intDefinePrivilegeID = intDefinePrivilegeID;
    }
    public int[] getIntRoleID()
    {
        return intRoleID;
    }
    public void setIntRoleID(int[] intRoleID)
    {
        this.intRoleID = intRoleID;
    }
    public int[] getIntRolePrivilegeID()
    {
        return intRolePrivilegeID;
    }
    public void setIntRolePrivilegeID(int[] intRolePrivilegeID)
    {
        this.intRolePrivilegeID = intRolePrivilegeID;
    }
    public List getPrivilegeList()
    {
        return privilegeList;
    }
    public void setPrivilegeList(List privilegeList)
    {
        this.privilegeList = privilegeList;
    }
    public List getRoleList()
    {
        return roleList;
    }
    public void setRoleList(List roleList)
    {
        this.roleList = roleList;
    }

    public GroupIbatisService getGroupService()
    {
        return groupService;
    }
    public void setGroupService(GroupIbatisService groupService)
    {
        this.groupService = groupService;
    }
    public PrivilegeIbatisService getPrivilegeService()
    {
        return privilegeService;
    }
    public void setPrivilegeService(PrivilegeIbatisService privilegeService)
    {
        this.privilegeService = privilegeService;
    }
    public RoleIbatisService getRoleService()
    {
        return roleService;
    }
    public void setRoleService(RoleIbatisService roleService)
    {
        this.roleService = roleService;
    }
    protected void createObjInfo()
    {
        this.objInfo = new UserModel();        
    }

    protected void createObjSearch()
    {
        this.objSearch = new UserModel();
    }   
    public void checkDataBeforeAdd()
    {
        //设置数据的唯一性
        setIsUnique(true);
        String strPassword=MD5Util.calcMD5(((UserModel)objInfo).getStrPassword());
        ((UserModel)objInfo).setStrPassword(strPassword);
    }
    
    public void checkDataBeforeUpdate()
    {
        setIsUnique(true);
        String strPassword = "";
        if(((UserModel)objInfo).getStrPassword()!=null && !((UserModel)objInfo).getStrPassword().equals("") && ((UserModel)objInfo).getStrPassword().length()>=2)
        {
	        strPassword=MD5Util.calcMD5(((UserModel)objInfo).getStrPassword());
        }
        else
        {
            strPassword = ((UserModel)objInfo).getStrOldPassword();
        }
        ((UserModel)objInfo).setStrPassword(strPassword);  
    }
    
    public String showRoleTreeForm()
    {
        groupList = groupService.getObjectList(null);	//找部门
        roleList = roleService.getObjectList(null);	//找角色
        List tmpList=((UserIbatisService)ibatisService).getRoleIDListByUserID(objInfo.getIntID());
        intRoleID = StringUtil.listToIntArray(tmpList);           
        return SUCCESS;
    }
    public String updateRoleTreeForm()
    {
        ((UserIbatisService)ibatisService).insertUserRole((UserModel)objInfo, intRoleID);
        return SUCCESS;
    }
    
    public String showPrivilegeTreeForm()
    {
        List tmpList = ((UserIbatisService)ibatisService).getDefinePrivilegeIDListByUserID(objInfo.getIntID());
        intDefinePrivilegeID = StringUtil.listToIntArray(tmpList);
        
        List tmpList1 = ((UserIbatisService)ibatisService).getRolePrivilegeIDListByUserID(objInfo.getIntID());
        intRolePrivilegeID = StringUtil.listToIntArray(tmpList1);
        
        privilegeList = privilegeService.getObjectList(null);
        return SUCCESS;
    }
    
    public String updatePrivilegeTreeForm()
    {
        ((UserIbatisService)ibatisService).insertUserPrivilege((UserModel)objInfo, intDefinePrivilegeID);
        return SUCCESS;
    }
    


    public String selectUser()
    {
        jsSelectModel.setObjList(groupService.getAllGroupListByFatherId(intGroupID,intGroupType));
        String strGroupID = groupService.getAllGroupIdsByFatherId(intGroupID,intGroupType);
        Map userMap=new HashMap();
        userMap.put("strGroupID",StringUtil.getIntSortByString("",strGroupID));
        jsSelectModel.setObjList1(ibatisService.getObjectList("getUserListByCondition",userMap));
        
        return SUCCESS;
    }
    
    public String selectUser_1()
    {
        jsSelectModel.setObjList(groupService.getAllGroupListByFatherId(intGroupID,intGroupType));
        //等待选中的用户
        if(intGroupID > 1 )
        {
            String strGroupID = StringUtil.getIntSortByString(groupService.getAllGroupIdsByFatherId(intGroupID,intGroupType),"");
            if(strGroupID.split(",").length > 1)
            {
                Map userMap_2=new HashMap();
                userMap_2.put("strGroupID",strGroupID);
                jsSelectModel.setObjList1(ibatisService.getObjectList("getUserListByCondition",userMap_2));
            }
        }
        //获取已经选中的用户ID
        String strUserIDs = StringUtil.getIntSortByString(jsSelectModel.getStrID(),"");
        if(strUserIDs.split(",").length > 1)
        {
            Map userMap_1=new HashMap();
            userMap_1.put("strUserID",strUserIDs);
            jsSelectModel.setObjList2(ibatisService.getObjectList("getUserListByCondition",userMap_1));
        }
        return SUCCESS;
    }  

    public String selectUser_2()
    {
        if(intGroupID > 1 )
        {
            String strGroupID = StringUtil.getIntSortByString(groupService.getAllGroupIdsByFatherId(intGroupID,intGroupType),"");
            if(strGroupID.split(",").length > 1)
            {
                Map userMap_2=new HashMap();
                userMap_2.put("strGroupID",strGroupID);
                jsSelectModel.setObjList1(ibatisService.getObjectList("getUserListByCondition",userMap_2));
            }
        }
        return SUCCESS;        
    }
    public void initDataBeforePageList()
    {
        String strName =((UserModel)objSearch).getStrName();
        if(strName == null )
        {
            strName = "";
        }
        else if(strName.equals(""))
        {
            strName = "";
        }
        else if(strName.trim().equals(""))
        {
            strName = "";
        }
        else
        {
            strName = strName.trim();
        }
        
        ((UserModel)objSearch).setStrName(strName);
    }
    
    /**
     * 更改所在的部门
     * 
     */
    public String moveUserGroup()
    {
        if(((UserModel)objInfo).getIntGroupID() > 0)
        {
            ibatisService.updateObject("moveUserGroup", objInfo);
        }
        return SUCCESS;
    }
    
    
    //统计在线人数和在线人员列表
    public String statisticsOnLineUnit()
    {
        onLineUsers = new ArrayList();
        Map sessionUsers = cache.getUserCache(UserSessionService.SESSION_USER);
        
        Map tmp_sessionUsers = new HashMap();
        tmp_sessionUsers.putAll(sessionUsers);
        Iterator it = tmp_sessionUsers.values().iterator();
        StringBuffer sb = new StringBuffer();
        while(it.hasNext())
        {
            HttpSession temp_session_1 = (HttpSession)it.next();
            if(temp_session_1!=null)
            {
                UserSessionModel temp_sessionModel = (UserSessionModel)temp_session_1.getAttribute("user");
                if(temp_sessionModel != null)
                {
                    sb.append(temp_sessionModel.getIntUnitOrderID());
                    sb.append(";");
                    sb.append(temp_sessionModel.getStrUnitName());
                    sb.append(";");
                    sb.append(temp_sessionModel.getStrName());
                    sb.append("%%%%");
                }
            }
        }
        String[] arrStr = StringUtil.getStrAryByString(sb.toString(),"%%%%");
        if(arrStr!=null && arrStr.length >= 2)
        {
            Arrays.sort(arrStr);
        }
        String tmpValue = "",strOldGroupValue="";
        List rtnList = new ArrayList();
        List subList = null;
        boolean isRemain = false;
        for(int i=0;i<arrStr.length;i++)
        {
            tmpValue=arrStr[i];
            if(tmpValue!=null && tmpValue.length() > 2)
            {
                String[] tmp = tmpValue.split(";"); 
                if(tmp!=null && tmp.length == 3)
                {

                    if(tmp[1].equals(strOldGroupValue))
                    {
                        subList.add(tmp[2]);
                        isRemain = true;
                    }
                    else
                    {
                        if(i > 0)
                        {
                            rtnList.add(subList);
                        }
                        strOldGroupValue = tmp[1];
                        subList = new ArrayList();
                        subList.add(tmp[1]);
                        subList.add(tmp[2]);

                    }
                }
            }
        }
        if(isRemain)
        {
            rtnList.add(subList);
        }
        if(arrStr!=null && arrStr.length == 1)  //判断，是否只有一个
        {
            rtnList.add(subList);
        }
        int size = rtnList.size(); 
        intOnLineCount = 0;
        for(int j = 0; j < size; j ++)
        {
            List subList_1 = (List)rtnList.get(j);
            UserSessionModel userModel = new UserSessionModel();
            List userList = new ArrayList();
            if(subList_1 != null && subList_1.size() > 0)
            {
                for(int n = 0;n < subList_1.size();n++)
                {

                    if(n == 0)
                    {
                        userModel.setStrUnitName((String)subList_1.get(n));
                    }
                    else
                    {
                        UserSessionModel userModel_1 = new UserSessionModel();
                        userModel_1.setStrName((String)subList_1.get(n));
                        userList.add(userModel_1);
                        intOnLineCount ++;
                    }
                }
            }
            userModel.setOnLineUserList(userList);
            onLineUsers.add(userModel);
        }
        return SUCCESS;
    }
    
    //统计在线人数和在线人员列表
    public String statisticsOnLineList()
    {
        onLineUsers = userService.onlineUsers();
        if(onLineUsers!=null)
        {
            intOnLineCount=onLineUsers.size();
        }
        return SUCCESS;
    }
    
    /** 
     * 显示分页所有记录 
     */
    public String showUserLogList()
    {
        UserLogModel rbac_userLogModel = new UserLogModel();
        rbac_userLogModel.setIntUserID(objSearch.getIntID());
        log.info("执行doShowPageListData()，按条件从数据库中取出一批分页记录集");
        pagination.setUserSessionDM(getUserSessionModel());
        int intTotal = ibatisService.getRecordCount("getUserLogRecordCount",rbac_userLogModel);
        pagination.setIntTotalNum(intTotal);
        objInfoList = ibatisService.getPageObjectList("getUserLogPageObjectList",rbac_userLogModel, pagination.getIntStartNum(), pagination.getIntLineNum());
        return SUCCESS;

    }
    public String updateAllowUserForm()
    {
		 UserModel uModel = new UserModel();
	     uModel.setIntID(getUserSessionModel().getIntUserID());
	     uModel.setStrAccount(getUserSessionModel().getStrUserAccount());
	     objInfo = (UserModel)ibatisService.getObjectInfo(uModel);  
	     return SUCCESS;
    }
    public String updateAllowUser()
    {
    	ibatisService.updateObject("updateAllowUser", objInfo);
    	return SUCCESS;
    }
    
    public String strSigarVal;
    
    public String getStrSigarVal()
	{
		return (strSigarVal == null) ? "" : strSigarVal;
	}
	public void setStrSigarVal(String strSigarVal)
	{
		this.strSigarVal = strSigarVal;
	}
	public String getHardUtil()
    {
		strSigarVal = "";
		HardDiskInfo hardDiskInfotil = new HardDiskInfo();
    	try
    	{

    	}
    	catch(Exception e)
    	{
    		
    	}
		strSigarVal += "strSysVal:"+hardDiskInfotil.strSysVal+"<br>";
        return SUCCESS;
    }
    
}
