/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.util.MD5Util;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.service.IbatisService;
import com.hode.rbac.model.UserModel;
import com.hode.rbac.model.UserSessionModel;
import com.hode.rbac.service.PermissionService;
import com.hode.rbac.service.UserSessionCache;
import com.hode.rbac.service.UserSessionService;
import com.opensymphony.webwork.ServletActionContext;


public class PermissionAction extends AbstractBaseAction
{

    private final static Log log = LogFactory.getLog(PermissionAction.class);
    

    private int intLineNum;
    private String strAccount;
    private String strPassword;
    private String strMachineCode;
    private String strRand; //随机码
    private int intErrorFlag = 0;
    private int intLoginType;//判断是啥身份登陆
    
    private UserSessionCache cache = UserSessionCache.getInstance();
    public UserSessionCache getCache() {
		return cache;
	}
	public void setCache(UserSessionCache cache) {
		this.cache = cache;
	}
	public UserSessionService getUserService() {
		return userService;
	}
	public void setUserService(UserSessionService userService) {
		this.userService = userService;
	}
	private UserSessionService userService = UserSessionService.getInstance();
    
    private IbatisService ibatisService;
    
    public String getStrMachineCode()
    {
        return strMachineCode;
    }
    public void setStrMachineCode(String strMachineCode)
    {
        this.strMachineCode = strMachineCode;
    }
    protected void createObjInfo()
    {
       this.objInfo = new UserModel(); 
    }
    protected void createObjSearch()
    {
        this.objInfo = new UserModel();
    }
    
	public void setIbatisService(IbatisService ibatisService)
	{
	    this.ibatisService = ibatisService;
	}
    public int getIntLineNum()
    {
        return intLineNum;
    }
    public void setIntLineNum(int intLineNum)
    {
        this.intLineNum = intLineNum;
    }
    public String getStrAccount()
    {
        return strAccount;
    }
    public void setStrAccount(String strAccount)
    {
        this.strAccount = strAccount;
    }
    public String getStrPassword()
    {
        return strPassword;
    }
    public void setStrPassword(String strPassword)
    {
        this.strPassword = strPassword;
    }
    
    public int getIntErrorFlag()
    {
        return intErrorFlag;
    }
    public void setIntErrorFlag(int intErrorFlag)
    {
        this.intErrorFlag = intErrorFlag;
    }
    public String getStrRand()
    {
        return strRand;
    }
    public void setStrRand(String strRand)
    {
        this.strRand = strRand;
    }
    public String loginout()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        ((PermissionService)ibatisService).loginout(session,getUserSessionModel());
		HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("intLoginType_session",intLoginType+"");
        
        return SUCCESS;
    }

    /**
     * 修改个人资料，表单
     */
    public String updatePersonnelUserForm()
    {
        UserModel uModel = new UserModel();
        uModel.setIntID(getUserSessionModel().getIntUserID());
        objInfo = ((PermissionService)ibatisService).getUserModelByModel(uModel);  
        return SUCCESS;
    }

    /**
     * 修改个人资料，处理数据库
     */
    public String updatePersonnelUser()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        ((UserModel)objInfo).setIntID(getUserSessionModel().getIntUserID());
        ((PermissionService)ibatisService).updatePersonnelUser(session,(UserModel)objInfo);  
        return SUCCESS;
    }
    public String updatePersonnelUserOther()
    {
        ((UserModel)objInfo).setIntID(getUserSessionModel().getIntUserID());
        ((PermissionService)ibatisService).updatePersonnelUserOther((UserModel)objInfo);  
        return SUCCESS;
    }
    
    /**
     * 修改个人密码
     */
    public String updatePassword()
    {
        HttpSession session = ServletActionContext.getRequest().getSession();
        
        UserModel userModel =new UserModel();
        
        userModel = (UserModel)objInfo;
        String strOldPasswd = MD5Util.calcMD5(userModel.getStrOldPassword());
        userModel.setStrOldPassword(MD5Util.calcMD5(userModel.getStrOldPassword()));
        
        if(userModel.getStrPassword().equals("") || userModel.getStrConfirmPassword().equals(""))
        {
            userModel.setStrPassword(strOldPasswd);
            userModel.setStrConfirmPassword(strOldPasswd);
        }
        else
        {
            userModel.setStrPassword(MD5Util.calcMD5(userModel.getStrPassword()));
            userModel.setStrConfirmPassword(MD5Util.calcMD5(userModel.getStrConfirmPassword()));
        }
        
        if(((PermissionService)ibatisService).updatePassword( userModel) == false)
        {
            addActionError("原密码不正确，请重新输入");
            return ERROR;
        }
         return SUCCESS;
    }
    
    /**
     * 登陆进行身份验证
     */
    public String login()
    {
    	HttpServletRequest request =ServletActionContext.getRequest();
        HttpSession httpSession =request.getSession();
        String strSessionRand=(String)(httpSession.getAttribute("strSessionRand"));
        UserModel user= new UserModel();
        user.setStrAccount(strAccount);
        if(strPassword != null && !strPassword.equals(""))
        {
            String tmpPwd = "";
            if(strPassword!=null && strPassword.length() > 64)
            {
                tmpPwd = strPassword.substring(0,32);
                strMachineCode = strPassword.substring(32, 64);
            }
            else
            {
                tmpPwd = MD5Util.calcMD5(strPassword);
            }

            strMachineCode = (strMachineCode == null || strMachineCode.length() < 5)?"":strMachineCode;
            
            user.setStrPassword(tmpPwd);
            user.setStrMachineCode(strMachineCode);
            user.setIntLineNum(intLineNum);
            //System.out.println("------------------------------------------------");
            Map sessionUsers = cache.getUserCache(UserSessionService.SESSION_USER);
            
            Map tmp_sessionUsers = new HashMap();
            tmp_sessionUsers.putAll(sessionUsers);
            Iterator it = tmp_sessionUsers.values().iterator();
            StringBuffer sb = new StringBuffer();
            while(it.hasNext())
            {
                HttpSession temp_session_1 = (HttpSession)it.next();
                if(temp_session_1!=null)
                {
                    UserSessionModel temp_sessionModel = (UserSessionModel)temp_session_1.getAttribute("user");
                    if(temp_sessionModel != null)
                    {
                        sb.append(temp_sessionModel.getStrUserAccount());
                        //System.out.println("login_username:"+temp_sessionModel.getStrUserAccount());
                        if(temp_sessionModel.getStrUserAccount().equals(strAccount)){
//                        	addActionError("该账号已在其他地方登陆！");
//                        	return ERROR;
                        }
                    }
                }
            }
            
	        try
	        {   
                if(SysConfig.isRand)
                {
                    if(!strRand.equalsIgnoreCase(strSessionRand)){
                        addActionError("验证码不正确,请重新再试");
                        return ERROR;
                    }
                }
                 
	        	int intRtn = ((PermissionService)ibatisService).checkLogin(httpSession,user);
	        	
	            if(intRtn == 0)
	            {
	                return SUCCESS;
	            }
	            else if(intRtn == 1)
	            {
                    if(SysConfig.intLoginMaxNum > 0 && SysConfig.intLoginErrTime > 0) 
                    {
                        addActionError("用户名或密码错，输错10次将锁定");
                    }
                    else
                    {
                        addActionError("用户名或密码错");
                    }
	            	return ERROR;
	            }
	            else if(intRtn == 2)
	            {
                    if(SysConfig.intLoginMaxNum > 0 && SysConfig.intLoginErrTime > 0) 
                    {
                        addActionError("该帐号已经被锁定，请30分钟后再试");
                    }
                    else
                    {
                        addActionError("用户名或密码错");
                    }
	            	return ERROR;
	            }
                else if(intRtn == 4)
                {
                    addActionError("外部用户，无法登陆本系统");
                    return ERROR;
                }
                else if(intRtn == 10)
                {
                    addActionError("您没有授权，系统不允许您访问");
                    return "noallowuser";
                }
	            else
	            {
	                setIntErrorFlag(1);
	                return "loginFrm";
	            }
	        }
	        catch (Exception e)
	        {
	            log.info("error ");
	            e.printStackTrace();
	            return "loginFrm";
	        }
        }
        else
        {
            return "loginFrm";
        }
    }
    
    /**
     * 切换身份
     * @return
     */
    public String changeUserInfo()
    {
    	HttpServletRequest request =ServletActionContext.getRequest();
        HttpSession httpSession =request.getSession();
        UserModel user= RbacUtil.getUserModelObjectInfo(objInfo.getIntID(), "");
        user.setIntLineNum(intLineNum);
        int intRtn = ((PermissionService)ibatisService).checkLogin(httpSession,user);
        if(intRtn == 0)
        {
            return SUCCESS;
        }
        else if(intRtn == 1)
        {
            if(SysConfig.intLoginMaxNum > 0 && SysConfig.intLoginErrTime > 0) 
            {
                addActionError("用户名或密码错，输错10次将锁定");
            }
            else
            {
                addActionError("用户名或密码错");
            }
        	return ERROR;
        }
        return SUCCESS;
    }
	public int getIntLoginType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int intOldSelectType = StringUtil.ObjectToInt(request.getSession().getAttribute(("intLoginType_session")));
		intLoginType = (intLoginType > 0)?intLoginType:intOldSelectType;
		return intLoginType;
	}
	public void setIntLoginType(int intLoginType) 
	{
		if(intLoginType == 0)
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			int intOldSelectType = StringUtil.ObjectToInt(request.getSession().getAttribute(("intLoginType_session")));
			intLoginType = (intLoginType > 0)?intLoginType:intOldSelectType;
	        request.getSession().setAttribute("intLoginType_session",intLoginType+"");
		}
		this.intLoginType = intLoginType;
	}
	public String loginFrm2()
	{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		int intOldSelectType = StringUtil.ObjectToInt(request.getSession().getAttribute(("intLoginType_session")));
		intLoginType = (intLoginType > 0)?intLoginType:intOldSelectType;
        request.getSession().setAttribute("intLoginType_session",intLoginType+"");
        return SUCCESS;
		
	}
}
