/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.file.FileWrite;
import com.hode.framework.model.IbatisModel;
import com.hode.framework.model.JsSelectModel;
import com.hode.rbac.model.GroupModel;
import com.hode.rbac.service.GroupIbatisService;

public class GroupAction extends AbstractBaseAction
{

	private final static Log log = LogFactory.getLog(GroupAction.class);
    private JsSelectModel jsSelectModel= new JsSelectModel();
    
    private int intGroupType;
    private int intGroupID;

    public void initOptDB()
    {
        setIntNeedAttr(2);
        setStrAttrPath("information/rbacgroup/upload/");  
    }
    
    public JsSelectModel getJsSelectModel()
    {
        return jsSelectModel;
    }
    public void setJsSelectModel(JsSelectModel jsSelectModel)
    {
        this.jsSelectModel = jsSelectModel;
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
    protected void createObjInfo()
    {
        this.objInfo = new GroupModel();        
    }

    protected void createObjSearch()
    {
        this.objSearch = new GroupModel();
    }   
    
    public void checkDataBeforeAdd()
    {
        //设置数据的唯一性
        setIsUnique(true);
    }
    
    public void checkDataBeforeUpdate()
    {
        //设置数据的唯一性
        setIsUnique(true);
    }
    public String selectGroupUnit()
    {
        jsSelectModel.setObjList(((GroupIbatisService)ibatisService).getAllGroupListByFatherId(intGroupID,intGroupType));
        return SUCCESS;
    }
    
    /**
     * 更改所在的部门
     * 
     */
    public String moveGroup()
    {
        if(((GroupModel)objInfo).getIntFatherID() > 0)
        {
            ibatisService.updateObject("moveGroup", objInfo);
        }
        return SUCCESS;
    }
    //修改公章
    public String updateGroupSign()
    {

        ((GroupModel)objInfo).setIntID(getUserSessionModel().getIntGroupID());
        setStrAttrPath("information/rbacgroup/upload/");  
        IbatisModel objOldInfo=new IbatisModel();
        objOldInfo = (IbatisModel)ibatisService.getObjectInfo(objInfo);
        objInfo.setStrDirPath(objOldInfo.getStrDirPath());
    
        /////////////////////////////////附件 start.......////////////////
      
        fileAttrUpload.setStrOldAttContent(objOldInfo.getStrAttrContent());
        fileAttrUpload.setFileUploadModel(fileAttrUploadModel);
        fileAttrUpload.uploadSingleFile(strAttrPath,  objInfo.getStrDirPath(), 0,intAttrSingleFileSize,intAttrCountFileSize);
        if(fileAttrUpload.getStrOldFileName() == null || fileAttrUpload.getStrOldFileName().equals(""))
        {
            fileAttrUpload.setStrOldFileName(objOldInfo.getStrAttrSourceName());
        }
        objInfo.setStrAttrContent(fileAttrUpload.getStrNewAttContent());
        objInfo.setStrAttrSourceName(fileAttrUpload.getStrOldFileName());

        /////////////////////////////////附件 end.......////////////////
        ibatisService.updateObject("updateGroupSign",getObjInfo());
        return SUCCESS;
    }
}