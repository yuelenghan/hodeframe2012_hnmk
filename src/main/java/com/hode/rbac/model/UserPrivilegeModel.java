/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.IbatisModel;

public class UserPrivilegeModel extends IbatisModel
{

    private UserModel user;
    private PrivilegeModel privilege;
    
    public PrivilegeModel getPrivilege()
    {
        return privilege;
    }
    public void setPrivilege(PrivilegeModel privilege)
    {
        this.privilege = privilege;
    }
   
    
    public UserModel getUser()
    {
        return user;
    }
    public void setUser(UserModel user)
    {
        this.user = user;
    }

}
