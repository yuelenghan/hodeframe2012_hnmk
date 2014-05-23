/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.model;

import com.hode.framework.model.IbatisModel;

public class RolePrivilegeModel extends IbatisModel
{
    private RoleModel role;
    private PrivilegeModel privilege;
    
    public PrivilegeModel getPrivilege()
    {
        return privilege;
    }
    public void setPrivilege(PrivilegeModel privilege)
    {
        this.privilege = privilege;
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
