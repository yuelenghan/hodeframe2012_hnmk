/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.IbatisModel;

public class UserRoleModel extends IbatisModel
{

    private RoleModel role;
    private UserModel user;
    
    
    public UserModel getUser()
    {
        return user;
    }
    public void setUser(UserModel user)
    {
        this.user = user;
    }
    public RoleModel getRole()
    {
        return role;
    }
    public void setRole(RoleModel role)
    {
        this.role = role;
    }
    
}
