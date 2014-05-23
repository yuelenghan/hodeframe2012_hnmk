/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.model.JsSelectModel;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.PrivilegeModel;
import com.hode.rbac.model.RoleModel;
import com.hode.rbac.service.GroupIbatisService;
import com.hode.rbac.service.RoleIbatisService;

public class RoleAction extends AbstractBaseAction
{
    private int[] intPrivilegeID;
    private int[] intPrivilegesByRoleID;
    private List privilegesList;
    
    public IbatisService privilegeService;
    private JsSelectModel jsSelectModel= new JsSelectModel();
    
    private IbatisService groupService;    
    private int intGroupType;
    private int intGroupID;   
    
    public void setPrivilegeService(IbatisService privilegeService)
    {
        this.privilegeService = privilegeService;
    }
    public int[] getIntPrivilegeID()
    {
        return intPrivilegeID;
    }
    
    public int[] getIntPrivilegesByRoleID()
    {
        return intPrivilegesByRoleID;
    }
    public void setIntPrivilegesByRoleID(int[] intPrivilegesByRoleID)
    {
        this.intPrivilegesByRoleID = intPrivilegesByRoleID;
    }
    public void setIntPrivilegeID(int[] intPrivilegeID)
    {
        this.intPrivilegeID = intPrivilegeID;
    }
    
    public List getPrivilegesList()
    {
        return privilegesList;
    }
    public void setPrivilegesList(List privilegesList)
    {
        this.privilegesList = privilegesList;
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
    public JsSelectModel getJsSelectModel()
    {
        return jsSelectModel;
    }
    public void setJsSelectModel(JsSelectModel jsSelectModel)
    {
        this.jsSelectModel = jsSelectModel;
    }
    public void setGroupService(IbatisService groupService)
    {
        this.groupService = groupService;
    }
    protected void createObjInfo()
    {
        this.objInfo = new RoleModel();        
    }

    protected void createObjSearch()
    {
        this.objSearch = new RoleModel();
    }
    public String showPrivilegeTreeForm()
    {
        List tmp = ((RoleIbatisService)ibatisService).getPrivilegeByRoleID(objInfo.getIntID());
        StringBuffer s=new StringBuffer();
        PrivilegeModel privilegeModel=new PrivilegeModel();
        for(int i=0;i<tmp.size();i++)
        {
            privilegeModel =(PrivilegeModel)tmp.get(i);
            s.append(",");
            s.append(privilegeModel.getIntID());
        }
        intPrivilegesByRoleID=StringUtil.getIntAryByString(s.toString());
        privilegesList  = privilegeService.getObjectList(null);
        return SUCCESS;
    }
    public String updatePrivilegeTreeForm()
    {
        ((RoleIbatisService)ibatisService).insertRolePrivilege((RoleModel)objInfo, intPrivilegeID);
        return SUCCESS;
    }
    public String selectRole()
    {
        jsSelectModel.setObjList(((GroupIbatisService)groupService).getAllGroupListByFatherId(intGroupID,intGroupType));
        String strGroupID = ((GroupIbatisService)groupService).getAllGroupIdsByFatherId(intGroupID,intGroupType);
        Map userMap=new HashMap();
        userMap.put("strGroupID",StringUtil.getIntSortByString("",strGroupID));
        jsSelectModel.setObjList1(ibatisService.getObjectList("getRoleListByCondition",userMap));
        return SUCCESS;
    }   
    
    
    
    
    /////////////////////////////////////////去掉部门
    public void checkDataBeforeAdd()
    {
        ((RoleModel)objSearch).setIntGroupID(1);
        ((RoleModel)objInfo).setIntGroupID(1);
    }
    public void checkDataBeforeList()
    {
        ((RoleModel)objSearch).setIntGroupID(1);
        ((RoleModel)objInfo).setIntGroupID(1);
    }
    public void checkDataBeforePageList()
    {
        ((RoleModel)objSearch).setIntGroupID(1);
        ((RoleModel)objInfo).setIntGroupID(1);
    }
}
