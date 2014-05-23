/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.UserSessionModel;


public class UserSessionService extends IbatisService
{
    public final static String SESSION_USER = "SESSION_USER";
    
    private static boolean loginOnceEnabled = false;
    
    private static UserSessionService self = new UserSessionService();    
    
    private UserSessionCache cache = UserSessionCache.getInstance();

    private final static Log log = LogFactory.getLog(UserSessionService.class);   
    private UserSessionService()
    {
        loginOnceEnabled = SysConfig.loginOnceEnabled; 
    }
    
    public static UserSessionService getInstance()
    {
        return self;
    }
    
    public synchronized void addSession(String sessionID, HttpSession session)
    {
        Map sessionUsers = cache.getUserCache(SESSION_USER);
        sessionUsers.put(sessionID, session);
    }
    
    public synchronized void removeSession(String sessionID)
    {
        Map sessionUsers = cache.getUserCache(SESSION_USER);
        HttpSession session = (HttpSession)sessionUsers.get(sessionID) ;
        if(session!=null)
        {
            session.removeAttribute("user");
        }
        sessionUsers.remove(sessionID);
    }
    


    public synchronized void login(String sessionID,int intUserID,String strUserAccount)
    {
        log.info("userSessionServices login starting ....");
        Map sessionUsers = cache.getUserCache(SESSION_USER);    //是否需要初始化堆栈，并返回堆栈对象
        
        Map tmp_sessionUsers = new HashMap();
        tmp_sessionUsers.putAll(sessionUsers);
        log.info("userSessionServices login loginOnceEnabled ...."+loginOnceEnabled);

        if(tmp_sessionUsers.containsKey(sessionID))
        {
            if(loginOnceEnabled)
            {
                //找出以同一账户登陆的所有用户session，并设为无效
                Iterator it = tmp_sessionUsers.values().iterator();
                Entry tmp_entry = null;
                HttpSession tmp_session = null;
                UserSessionModel tmp_userSession = null;
                Object tem_obj = null;
                while(it.hasNext())
                {
                    tmp_session = (HttpSession)it.next();
                    tem_obj = tmp_session.getAttribute("user");
                    if(tem_obj != null)
                    {
                        tmp_userSession = (UserSessionModel)tem_obj;
                        if(tmp_userSession.getStrUserAccount().equals(strUserAccount))
                        { 
                            tmp_session.invalidate();
                        }
                    }
                }
                
            }
        }
        tmp_sessionUsers = null;
    }
    
    public synchronized void loginOut(HttpSession session)
    {
        session.invalidate();
    }
    
    public List onlineUsers()
    {
        List list = new ArrayList();
        Map sessionUsers = cache.getUserCache(SESSION_USER);
        Map tmp_sessionUsers = new HashMap();
        tmp_sessionUsers.putAll(sessionUsers);
        Iterator it = tmp_sessionUsers.values().iterator();
        HttpSession tmp_session = null;
        UserSessionModel tmp_userSession = null;
        Object tem_obj = null;
        while(it.hasNext())
        {
            tmp_session = (HttpSession)it.next();
            if(tmp_session !=null)
            {
                tem_obj = tmp_session.getAttribute("user");
                if(tem_obj != null)
                {
                    tmp_userSession = (UserSessionModel)tem_obj;
                    List useran = new ArrayList();
                    useran.add(tmp_userSession.getStrUserAccount());
                    useran.add(tmp_userSession.getStrName());
                    list.add(useran);
                }
            }
        }
        tmp_sessionUsers = null;
        return list;
    }


    /**
     * intType:1  返回用户ID  2：返回帐号   3:返回cs代码
     * isNeedQuit: 是否需要加单引号 1:需要 0：不需要
     */
    public String getStringOnlineUserByType(int intType,int isNeedQuit)
    {
        StringBuffer strBuffer = new StringBuffer();
        
        Map sessionUsers = cache.getUserCache(SESSION_USER);
        Map tmp_sessionUsers = new HashMap();
        tmp_sessionUsers.putAll(sessionUsers);
        Iterator it = tmp_sessionUsers.values().iterator();
        HttpSession tmp_session = null;
        UserSessionModel tmp_userSession = null;
        if(intType == 1)
        {
            strBuffer.append(0);
        }
        else if(intType == 2 || intType == 3)
        {
            if(isNeedQuit == 1)
                strBuffer.append("'0'");
            else
                strBuffer.append("0");
        }

        Object tem_obj = null;
        while(it.hasNext())
        {
            tmp_session = (HttpSession)it.next();
            tem_obj = tmp_session.getAttribute("user");
            if(tem_obj != null)
            {
                tmp_userSession = (UserSessionModel)tem_obj;
                if(intType == 1)
                    strBuffer.append(","+tmp_userSession.getIntUserID());
                else if(intType == 2)
                {
                    if(isNeedQuit == 1)
                        strBuffer.append(",'"+tmp_userSession.getStrUserAccount()+"'");
                    else
                        strBuffer.append(","+tmp_userSession.getStrUserAccount());
                }
            }
        }
        return strBuffer.toString();
    }
        
   
}
