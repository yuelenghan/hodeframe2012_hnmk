/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.service;

import java.util.Hashtable;
import java.util.Map;

public class UserSessionCache
{

    private static UserSessionCache self = new UserSessionCache();

    private Map userMaps = new Hashtable();

    private UserSessionCache()
    {
        super();
    }

    public static UserSessionCache getInstance()
    {
        return self;
    }

    public Map getUserCache(String tag)
    {
        if (userMaps.containsKey(tag))
        {
            return (Map) userMaps.get(tag);
        }
        else
        {
            Map cache = new Hashtable();
            userMaps.put(tag, cache);
            return cache;
        }
    }
}
