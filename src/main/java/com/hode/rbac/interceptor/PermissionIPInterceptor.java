/*
 * Created on 2005-1-26 Author hodesoft
 *
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.interceptor;


import javax.servlet.http.HttpServletRequest;

import com.hode.framework.commons.config.SysConfig;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.Interceptor;

public class PermissionIPInterceptor implements Interceptor
{  
    public long strToLongIP(String strIP)
    {
        if(strIP!=null && !strIP.equals("") && strIP.length() > 2)
        {
            String strUserIP = "";
            String strTmpArr[] = strIP.split("\\.");
            {
                for(int i=0;i<4;i++)
                {
                    switch(strTmpArr[i].length())
                    {
                        case 1:
                            strUserIP+="00"+strTmpArr[i];
                            break;
                        case 2:
                            strUserIP+="0"+strTmpArr[i];
                            break;   
                        case 3:
                            strUserIP+=strTmpArr[i];
                            break;                      
                    }
                }
            }
            return Long.parseLong(strUserIP);
        }
        else
        {
            return 255255255255L;
        }
    }
    public boolean checkIPPermit()
    {
        boolean rtn = true;
        HttpServletRequest request = ServletActionContext.getRequest();
        if( (request == null) || (request.getRemoteAddr() == null) )
        {
            rtn = false;
        }
        String strPermitIP = SysConfig.strPermitIP;
        if( (strPermitIP!=null) && (!strPermitIP.equals("")) && (strPermitIP.length() > 2 ) && (rtn))
        {
            rtn = false;
            long intCmp0 = strToLongIP(request.getRemoteAddr()); //动态获取IP
            long intCmp1 =0; //开始IP
            long intCmp2 =0; //结束IP
            String[] strPermitIPArr = strPermitIP.split(";");

            if(strPermitIPArr!=null && strPermitIPArr.length > 0)
            {
                for(int m=0;m<strPermitIPArr.length;m++)
                {
                	
                    if( (strPermitIPArr[m]!=null) && (!strPermitIPArr[m].equals("")) && (strPermitIPArr[m].length() > 2 ) )
                    {
                        String strArr[] = strPermitIPArr[m].split("\\-");                      
                        if(strArr.length==2)
                        {
                            intCmp1 = strToLongIP(strArr[0]);
                            intCmp2 = strToLongIP(strArr[1]);
                           if( (intCmp0 >= intCmp1 ) && (intCmp0 <= intCmp2 ) )
                            {
                                //正确的IP
                                rtn = true;
                                break;
                            }
                            else
                            {
                                
                            }
                        }
                    }
                }
            }
        }
        return rtn;
    }
    
    public String intercept(ActionInvocation invocation) throws Exception
    {

        if(checkIPPermit() == true)
        {
        }
        else
        {
            return "nopermission";
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
