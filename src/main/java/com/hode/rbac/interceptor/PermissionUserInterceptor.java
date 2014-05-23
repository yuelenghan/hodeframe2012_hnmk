/*
 * Created on 2005-1-26 Author hodesoft
 *
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.interceptor;



import com.hode.framework.commons.util.StringUtil;
import com.hode.rbac.model.UserSessionModel;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class PermissionUserInterceptor implements Interceptor
{  
  
    
    public String intercept(ActionInvocation invocation) throws Exception
    {
        ActionContext context = invocation.getInvocationContext();
        UserSessionModel userSession = (UserSessionModel)context.getSession().get("user");
        
        if(userSession == null)
        {
            String permissionIDs = (String)invocation.getProxy().getConfig().getParams().get("permissionIDs");
             if((permissionIDs!=null)&&(!permissionIDs.equals("")))
            {
                return "nopermission";
            }
            else
            {

               return "loginFrm";
            }
        }
        else if(userSession.getIntUserType() != 1)//系统管理员
        {

            String permissionIDs = (String)invocation.getProxy().getConfig().getParams().get("permissionIDs");
            if((permissionIDs!=null)&&(!permissionIDs.equals("")))
            {
                String[] ps = permissionIDs.split(",");
                int[] pids = new int[ps.length];
                for (int i = 0; i < pids.length; i++)
                {
                    pids[i] = Integer.valueOf(ps[i]).intValue();
                }
                int[] allowPrivilegeIDs =  userSession.getIntPrivilegeIDs();
                boolean allow = false;
                for (int i = 0; i < pids.length; i++)
                {
                    if(StringUtil.in_array(pids[i],allowPrivilegeIDs))
                    {
                        allow = true;
                        break;
                    }
                }
                if(!allow)
                    return "nopermission";
            }
        }
        return invocation.invoke();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#destroy()
     */
    public void destroy()
    {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.interceptor.Interceptor#init()
     */
    public void init()
    {
        // TODO Auto-generated method stub

    }
}
