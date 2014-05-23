/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hode.rbac.service.UserSessionService;


public class UserSessionListener implements HttpSessionListener
{
    

    public void sessionCreated(HttpSessionEvent event)
    {
        try
        {
            HttpSession session = event.getSession();
            UserSessionService userSessionService = UserSessionService.getInstance();            
            userSessionService.addSession(session.getId(),session);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    

    public void sessionDestroyed(HttpSessionEvent event)
    {
		try
		{
		    HttpSession session = event.getSession();
            UserSessionService userSessionService = UserSessionService.getInstance();            
            userSessionService.removeSession(session.getId());
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
        
    }
}
