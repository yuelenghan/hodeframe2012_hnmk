package com.hode.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hode.train.model.TrainApplyModel;
import com.hode.train.util.TrainUtil;

public class selectApplyOptions_ajax  extends HttpServlet{
	private static final long serialVersionUID = -7547359329467673344L;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	    {
	      String intGroupStr = request.getParameter("intGroupID");
	      int intGroupID = Integer.parseInt(intGroupStr);
	      System.out.println("intGroupID"+intGroupID);
	      List<TrainApplyModel> list = new ArrayList();
	      list = TrainUtil.getTrainApplyListByIntTrainLow(intGroupID);
	      StringBuffer sb = new StringBuffer();
	      sb.append("applys={");
	      for(TrainApplyModel model:list){
	    	  sb.append(model.getIntID()+"="+model.getStrTrainCount()+",");
	      }
	      sb.deleteCharAt(sb.length()-1);
	      sb.append("}");
	      System.out.println(sb);
	      response.setCharacterEncoding("UTF-8");
	      PrintWriter out = response.getWriter();
	      out.println(sb.toString());
	      out.flush();
	    }

	    public void doPost(HttpServletRequest request,HttpServletResponse response)
	    throws ServletException,IOException
	    {
	       doGet(request,response);
	    }

}
