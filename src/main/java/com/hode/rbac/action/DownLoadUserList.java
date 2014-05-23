/*
 * Copyright (c) 2003-2005 by HodeSoft
 * All rights reserved.
 */
package com.hode.rbac.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.service.GlobeSpringContext;
import com.hode.rbac.model.GroupModel;
import com.hode.rbac.model.UserModel;
import com.hode.rbac.service.UserIbatisService;

public class DownLoadUserList extends HttpServlet
{
	private static UserIbatisService userService = (UserIbatisService)GlobeSpringContext.getApplicationContext().getBean("rbac_userIbatisService");
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		int intFatherID = 0; 
		String strFatherID = request.getParameter("intFatherID");
		if(strFatherID != null || !strFatherID.equals(""))
		{
			intFatherID = Integer.parseInt(strFatherID);
		}
		
		Map inputMap = new HashMap();
		if(intFatherID > 1)
		{
			String strGroupID = RbacUtil.getAllGroupIdsByFatherId(intFatherID,0);
			inputMap.put("strGroupID",strGroupID);
		}
		
		String fileName = createExcelForUserList(inputMap);
		
		//重写response,提供excel下载		
		response.reset();
	    response.setContentType("application/vnd.ms-excel");
	      
	    response.setHeader("content-disposition","attachment; filename="+fileName);
	            
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    try 
	    {
	    	bis = new BufferedInputStream(new FileInputStream(SysConfig.strHodeRealPath+fileName));
	        bos = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesread;

            while(-1 != (bytesread = bis.read(buff, 0, buff.length))) 
            {
                bos.write(buff,0,bytesread);
            }

        } 
        catch(final IOException e) 
        {
        } 
        finally 
        {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return;
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		doPost(request,response);
	}
	
	public static String createExcelForUserList(Map inputMap)
	{
		//获取该组的全部信息
		List list = userService.getObjectList("getUserListByCondition",inputMap);
		
		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		String fileName = "userList.xls";
		try 
		{
			//初始化要生成excel文件,若存在则删除重建
			File strFileName = new File(SysConfig.strHodeRealPath+fileName);
			if(strFileName.exists())
			{
				strFileName.delete();
			}
			wwb = Workbook.createWorkbook(strFileName);
			ws = wwb.createSheet("用户列表",0);
			//excel 单元格格式
			WritableFont wf = new WritableFont(WritableFont.createFont("黑体"),25,WritableFont.BOLD,false);		//大标题格式
			WritableFont wf1 = new WritableFont(WritableFont.createFont("宋体"),18,WritableFont.NO_BOLD,false);	//表头格式
			WritableFont wf2 = new WritableFont(WritableFont.createFont("宋体"),12,WritableFont.NO_BOLD,false);	//内容格式
			WritableCellFormat wcf = new WritableCellFormat(wf);
			WritableCellFormat wcf1 = new WritableCellFormat(wf1);
			WritableCellFormat wcf2 = new WritableCellFormat(wf2);
			
			//设置单元格数据居中显示
			wcf.setAlignment(Alignment.CENTRE);
			wcf1.setAlignment(Alignment.CENTRE);
			wcf2.setAlignment(Alignment.CENTRE);
			
			//表格线
			wcf1.setBorder(Border.ALL,BorderLineStyle.THIN);
			wcf2.setBorder(Border.ALL,BorderLineStyle.THIN);

			//自动调整行高
			wcf.setWrap(true);
			wcf1.setWrap(true);
			wcf2.setWrap(true);
			
			//设置列宽
			ws.setColumnView(0,20);
			ws.setColumnView(1,20);
			ws.setColumnView(2,30);
			ws.setColumnView(3,40);
			
			//初始化表头
			Label lb = new Label(0,0,"用户列表",wcf);
			ws.setRowView(0,700);
			ws.mergeCells(0,0,3,0); 
			ws.addCell(lb);
			
			Label lb0 = new Label(0,1,"帐号",wcf1);
			Label lb1 = new Label(1,1,"姓名",wcf1);
			Label lb2 = new Label(2,1,"部门",wcf1);
			Label lb3 = new Label(3,1,"上次登录时间",wcf1);
			
			ws.setRowView(1,500);
			ws.addCell(lb0);
			ws.addCell(lb1);
			ws.addCell(lb2);
			ws.addCell(lb3);
			
			int listSize = list.size();
			int rowNum = 2;		//行号
			for(int i = 0;i < listSize; i++)
			{
				UserModel userModel = (UserModel)list.get(i);
				GroupModel groupModel = (GroupModel)RbacUtil.getGroupModelById(userModel.getIntGroupID());
				
				Label lab0 = new Label(0,rowNum,userModel.getStrAccount(),wcf2);
				Label lab1 = new Label(1,rowNum,userModel.getStrName(),wcf2);
				Label lab2 = new Label(2,rowNum,groupModel.getStrName(),wcf2);
				Label lab3 = new Label(3,rowNum,userModel.getStrLastLoginTime(),wcf2);
				
				
				//将内容写入excel
				ws.addCell(lab0);
				ws.addCell(lab1);
				ws.addCell(lab2);
				ws.addCell(lab3);
				
				rowNum ++;
			}
			
			wwb.write();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				wwb.close();
			} 
			catch (WriteException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return fileName;
	}

}
