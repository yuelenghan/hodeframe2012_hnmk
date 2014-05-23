/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;


import com.hode.framework.action.AbstractBaseAction;
import com.hode.rbac.model.PrivilegeModel;

public class PrivilegeAction extends AbstractBaseAction
{

    protected void createObjInfo()
    {
        this.objInfo = new PrivilegeModel();        
    }

    protected void createObjSearch()
    {
        this.objSearch = new PrivilegeModel();
    }   

}
