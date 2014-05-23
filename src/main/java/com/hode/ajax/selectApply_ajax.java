package com.hode.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hode.train.model.ApplyExamineeModel;
import com.hode.train.model.TrainApplyModel;
import com.hode.train.util.TrainUtil;

public class selectApply_ajax  extends HttpServlet{
	private static final long serialVersionUID = 1687930683839308412L;
	@SuppressWarnings("rawtypes")
	public void doGet (HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	    {
	      String idStr = request.getParameter("intTrainApplyID");
	      int idInt = 0;
	      TrainApplyModel model = new TrainApplyModel();
	      if(idStr!=null&&idStr.length()>0){
	    	  idInt = Integer.parseInt(idStr);
	    	  model = TrainUtil.getTrainApplyByID(idInt);
	      }
	      StringBuffer sb = new StringBuffer();
	      if(model.getStrTrainAddr()!=null)
	    	  sb.append(model.getStrTrainAddr()).append(",");
	      if(model.getStrTrainUserNum()!=null)
	    	  sb.append(model.getStrTrainUserNum()).append(",");
	      ApplyExamineeModel bean = new ApplyExamineeModel();
	      List list = new ArrayList();
	      if(idInt>0)
	    	  bean.setIntTrainApplyID(idInt);
	      list = TrainUtil.getObjectListByTrainApplyID(bean);
	      sb.append(list.size());
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
